package org.arbusta.model.hits;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.arbusta.db.DBPool;
import org.arbusta.model.account.Price;

public class HitType {
	long id;
	String title;

	String description;
	Price reward;
	String keywords;
	long assignmentDurationInSeconds;
	long autoApprovalDelayInSeconds;

	private HitType() {
	}

	public HitType(String title, String description,
			Price reward, String keywords, 
			Long assignmentDurationInSeconds,
			Long autoApprovalDelayInSeconds) {
		this.title = title;
		this.description = description;
		this.reward = reward;
		this.keywords = keywords;
		if(assignmentDurationInSeconds == null)
			throw new IllegalArgumentException("assignmentDurationInSeconds cannot be null");
		this.assignmentDurationInSeconds = assignmentDurationInSeconds;
		this.autoApprovalDelayInSeconds = autoApprovalDelayInSeconds;
	}

	public void save() throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = (Connection)DBPool.getPool().borrowObject();
			pst = con.prepareStatement(
					"insert into hit_types (title, description, keywords, reward, "
					+ "assignment_duration_in_seconds, auto_approval_delay_in_seconds) "
					+ "values (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

			pst.setString(1, title);
			pst.setString(2, description);
			pst.setString(3, keywords);
			pst.setDouble(4, reward.getPrice());
			pst.setLong(5, assignmentDurationInSeconds);
			pst.setLong(6, autoApprovalDelayInSeconds);

			pst.execute();

			ResultSet rs = pst.getGeneratedKeys();

			if (rs != null && rs.next())
			    id = rs.getLong(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error saving HitType: \n "+ toString() , e);
		} finally {
			if(con!=null)
				try {
					DBPool.getPool().returnObject(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	public static HitType load(long id) throws Exception{
		HitType ret = new HitType();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			con = (Connection)DBPool.getPool().borrowObject();
			st = con.createStatement();
			rs = st.executeQuery("select * from hit_types where hit_type_id = \""+id +"\"");
			while(rs.next()) {
			      ret.id = rs.getLong("hit_type_id");
			      ret.title = rs.getString("title");
			      ret.description = rs.getString("description");
			      ret.keywords = rs.getString("keywords");
			      ret.reward = new Price(rs.getDouble("reward"));
			      ret.assignmentDurationInSeconds = rs.getLong("assignment_duration_in_seconds");
			      ret.autoApprovalDelayInSeconds = rs.getLong("auto_approval_delay_in_seconds");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error reading HitType "+ id , e);
		} finally {
			if(con!=null){
				try {
					DBPool.getPool().returnObject(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
	    buff.append("\n** Hit Type **");
		buff.append("\nID..........................: " + id);
	    buff.append("\nTitle.......................: " + title);
	    buff.append("\nDescription.................: " + description);
	    buff.append("\nKeywords....................: " + keywords);
	    buff.append("\nReward......................: " + reward);
	    buff.append("\nAssignmentDurationInSeconds.: " + assignmentDurationInSeconds);
	    buff.append("\nAutoApprovalDelayInSeconds..: " + autoApprovalDelayInSeconds);
		return buff.toString();
	}

	public static void main(String[] args) {
		
		HitType ht1= new HitType("Categorizacion de imagenes", 
								 "Por favor elija la imagen que mejor represente el producto en cuestion", 
								 new Price(0.1), 
								 "categorizacion, imagenes, productos", 
								 3600L, 3600L*48);
		HitType ht2;
		
		System.out.println("BEFORE SAVE");
		System.out.println(ht1.toString());

		try {
			ht1.save();

			System.out.println("AFTER SAVE");
			System.out.println(ht1.toString());

			ht2 = HitType.load(ht1.getId());

			System.out.println("AFTER LOAD");
			System.out.println(ht2.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Price getReward() {
		return reward;
	}

	public String getKeywords() {
		return keywords;
	}

	public long getAssignmentDurationInSeconds() {
		return assignmentDurationInSeconds;
	}

	public long getAutoApprovalDelayInSeconds() {
		return autoApprovalDelayInSeconds;
	}

}

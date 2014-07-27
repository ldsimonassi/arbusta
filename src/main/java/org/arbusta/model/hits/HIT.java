package org.arbusta.model.hits;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.arbusta.db.DBPool;


public class HIT {
	public HIT(Long hitTypeId, String groupId, String question, long lifetimeInSeconds, 
			   Integer maxAssignments, String requesterAnnotation) {
		this.hitTypeId = hitTypeId;
		this.groupId = groupId;
		this.question = question;
		this.lifetimeInSeconds = lifetimeInSeconds;
		this.maxAssignments = maxAssignments;
		this.requesterAnnotation = requesterAnnotation;
		this.reviewStatus = "NotReviewed";
	}
	
	Long id;
	Long hitTypeId;
	String groupId;
	String question;
	long lifetimeInSeconds;
	int maxAssignments;
	String requesterAnnotation;
	String reviewStatus;

	public void save() throws Exception {
		if(id == null)
			insert();
		else
			update();
	}

	private void update() {
		// TODO Implement
	}

	private void insert() throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = (Connection)DBPool.getPool().borrowObject();
			pst = con.prepareStatement("insert into hits (hit_type_id, question,group_id,"
									+ "lifetime_in_seconds, max_assignments, requester_annotation,"
									+ "review_status) values (?, ?, ?, ?, ?, ?, ?)", 
									PreparedStatement.RETURN_GENERATED_KEYS);

			pst.setLong(1, hitTypeId);
			pst.setString(2, groupId);
			pst.setString(3, question);
			pst.setLong(4, lifetimeInSeconds);
			pst.setInt(5,  maxAssignments);
			pst.setString(6, requesterAnnotation);
			pst.setString(7, reviewStatus);

			pst.execute();

			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs != null && rs.next()) 
			    id = rs.getLong(1);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error saving Hit: \n "+ toString() , e);
		} finally {
			if(con!=null)
				try {
					DBPool.getPool().returnObject(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * TODO: Implement 
	 * Layout layout;
	 * LayoutParameter layoutParameter;
	 * ReviewPolicy reviewPolicy;
	 */
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("\n** HIT **");
		buff.append("\nID....................:"+id);
		buff.append("\nHitTypeId.............:"+hitTypeId);
		buff.append("\nGroupId...............:"+groupId);
		buff.append("\nQuestion..............:"+question);
		buff.append("\nLifetime Sec..........:"+lifetimeInSeconds);
		buff.append("\nMax Assignments.......:"+maxAssignments);
		buff.append("\nRequester Annotation..:"+requesterAnnotation);
		buff.append("\nReview Status.........:"+reviewStatus);
		return buff.toString();
	}
}
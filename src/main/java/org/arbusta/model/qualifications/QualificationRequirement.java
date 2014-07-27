package org.arbusta.model.qualifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.arbusta.db.DBPool;

public class QualificationRequirement {
	public static String LESS_THAN = "LessThan";
	public static String LESS_THAN_OR_EQUAL_TO = "LessThanOrEqualTo";
	public static String GREATER_THAN = "GreaterThan";
	public static String GREATER_THAN_OR_EQUAL_TO = "GreaterThanOrEqualTo";
	public static String EQUAL_TO = "EqualTo";
	public static String NOT_EQUAL_TO = "NotEqualTo";
	public static String EXISTS = "Exists";
	
	long hitTypeId;
	long qualificationTypeId;
	String comparator;
	int integerValue;

	@Override
	public int hashCode() {
		return (""+hitTypeId+"-"+qualificationTypeId+"-"+comparator).hashCode();
	}

	private QualificationRequirement () {
	}
	
	public QualificationRequirement(long hitTypeId, long qualificationTypeId, String comparator, int integerValue) {
		this.hitTypeId = hitTypeId;
		this.qualificationTypeId = qualificationTypeId;
		this.comparator = comparator;
		this.integerValue = integerValue;
	}
	
	public void save() throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = (Connection)DBPool.getPool().borrowObject();
			pst = con.prepareStatement(
					"insert into qualification_requirements "
					+ "(hit_type_id, qualification_type_id, comparator, integer_value) "
					+ "values (?, ?, ?, ?)");

			pst.setLong(1, hitTypeId);
			pst.setLong(2, qualificationTypeId);
			pst.setString(3, comparator);
			pst.setInt(4, integerValue);

			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error saving Qualification Requirement: \n "+ toString() , e);
		} finally {
			if(con!=null)
				try {
					DBPool.getPool().returnObject(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	public static Set<QualificationRequirement> load(long hitTypeId) throws Exception{
		HashSet<QualificationRequirement> ret = new HashSet<QualificationRequirement>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			con = (Connection)DBPool.getPool().borrowObject();
			st = con.createStatement();
			rs = st.executeQuery("select * from qualification_requirements where hit_type_id = " + hitTypeId);
			while(rs.next()) {
				QualificationRequirement q = new QualificationRequirement();
				q.hitTypeId = rs.getLong("hit_type_id");
				q.qualificationTypeId = rs.getLong("qualification_type_id");
				q.comparator = rs.getString("comparator");
				q.integerValue = rs.getInt("integer_value");
				ret.add(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error reading QualificationRequirements for HitTypeId "+ hitTypeId , e);
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
	public boolean equals(Object obj) {
		if(!(obj instanceof QualificationRequirement))
			return false;
		QualificationRequirement other = (QualificationRequirement) obj;

		return (other.hitTypeId == hitTypeId && 
				other.qualificationTypeId == qualificationTypeId && 
				comparator != null && 
				comparator.equals(other.comparator)); 
	}
	
	public long getHitTypeId() {
		return hitTypeId;
	}

	public void setHitTypeId(long hitTypeId) {
		this.hitTypeId = hitTypeId;
	}

	public long getQualificationTypeId() {
		return qualificationTypeId;
	}

	public void setQualificationTypeId(long qualificationTypeId) {
		this.qualificationTypeId = qualificationTypeId;
	}

	public String getComparator() {
		return comparator;
	}

	public void setComparator(String comparator) {
		this.comparator = comparator;
	}

	public int getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(int integerValue) {
		this.integerValue = integerValue;
	}
}

package org.arbusta.model.qualifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

import org.arbusta.db.DBPool;


public class QualificationType {
	public QualificationType(String name, String description, String keywords, 
							long retryDelayInSeconds, String status, 
							String test, String answerKey,  
							long testDurationInSeconds, boolean autoGranted,
							Integer autoGrantedValue) {
		this.id = null;
		this.name = name;
		this.description = description;
		this.keywords = keywords;
		this.retryDelayInSeconds = retryDelayInSeconds;
		this.status = status;
		this.test = test;
		this.answerKey = answerKey;
		this.testDurationInSeconds = testDurationInSeconds;
		this.autoGranted = autoGranted;
		this.autoGrantedValue = autoGrantedValue;
	}
	
	private QualificationType() {
		// Nothing, just for private instantiation.
	}

	Long id;
	String name;
	String description;
	String keywords;
	long retryDelayInSeconds;
	String status; // Active | Inactive
	String test; //QuestionForm xml
	String answerKey; //AnswerKey xml
	long testDurationInSeconds;
	boolean autoGranted;
	Integer autoGrantedValue;
	

	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		
		buff.append("\n** QualificationType **");
		buff.append("\nId......................: [" + id + "]");
		buff.append("\nDescription.............: [" + description + "]");
		buff.append("\nRetry Delay In Seconds..: [" + retryDelayInSeconds + "]");
		buff.append("\nStatus..................: [" + status + "]");
		buff.append("\nTest....................: [" + test + "]");
		buff.append("\nAnswer Key..............: [" + answerKey + "]");
		buff.append("\nTest Duration (sec).....: [" + testDurationInSeconds + "]");
		buff.append("\nAuto Granted............: [" + autoGranted + "]");
		buff.append("\nAuto Granted Value......: [" + autoGrantedValue + "]");
		return buff.toString();
	}


	public static QualificationType load(Long id) throws Exception {
		QualificationType ret = new QualificationType();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			con = (Connection)DBPool.getPool().borrowObject();
			st = con.createStatement();
			rs = st.executeQuery("select * from qualification_types where qualification_type_id = \""+id +"\"");
			
			
			while(rs.next()) {
			      ret.id = rs.getLong("qualification_type_id");
			      ret.name = rs.getString("name");
			      ret.description = rs.getString("description");
			      ret.keywords = rs.getString("keywords");
			      ret.retryDelayInSeconds = rs.getLong("retry_delay_in_seconds");
			      ret.status = rs.getString("status");
			      ret.test = rs.getString("test");
			      ret.answerKey = rs.getString("answer_key");
			      ret.testDurationInSeconds = rs.getLong("test_duration_in_seconds");
			      ret.autoGranted = rs.getBoolean("auto_granted");
			      ret.autoGrantedValue = rs.getInt("auto_granted_value");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error reading QualificationType "+ id , e);
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
	
	public void save() throws Exception {
		if(id == null)
			insert();
		else
			update();
	}

	/**
	 * Update using the Id that must not be null
	 */
	private void update() {
		//TODO Implement
	}


	/**
	 * Insert a new register
	 */
	private void insert() throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = (Connection)DBPool.getPool().borrowObject();
			pst = con.prepareStatement(
					"insert into qualification_types (description, keywords, retry_delay_in_seconds,  "
					+ "status, test, answer_key, test_duration_in_seconds, auto_granted, auto_granted_value, name) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
	
			pst.setString(1, description);
			pst.setString(2, keywords);
			pst.setLong(3, retryDelayInSeconds);
			pst.setString(4, status);
			pst.setString(5, test);
			pst.setString(6, answerKey);
			pst.setLong(7, testDurationInSeconds);
			pst.setString(8, ""+autoGranted);
			if(autoGrantedValue != null)
				pst.setInt(9, autoGrantedValue);
			else
				pst.setNull(9, Types.INTEGER);
			
			pst.setString(10, name);
			
			pst.execute();
			
			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs != null && rs.next()) {
			    id = rs.getLong(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error saving Qualification Type: \n "+ toString() , e);
		} finally {
			if(con!=null)
				try {
					DBPool.getPool().returnObject(con);
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
	}
	
	
	
	

	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public long getRetryDelayInSeconds() {
		return retryDelayInSeconds;
	}


	public void setRetryDelayInSeconds(long retryDelayInSeconds) {
		this.retryDelayInSeconds = retryDelayInSeconds;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTest() {
		return test;
	}


	public void setTest(String test) {
		this.test = test;
	}


	public String getAnswerKey() {
		return answerKey;
	}


	public void setAnswerKey(String answerKey) {
		this.answerKey = answerKey;
	}


	public long getTestDurationInSeconds() {
		return testDurationInSeconds;
	}


	public void setTestDurationInSeconds(long testDurationInSeconds) {
		this.testDurationInSeconds = testDurationInSeconds;
	}


	public boolean isAutoGranted() {
		return autoGranted;
	}


	public void setAutoGranted(boolean autoGranted) {
		this.autoGranted = autoGranted;
	}


	public int getAutoGrantedValue() {
		return autoGrantedValue;
	}


	public void setAutoGrantedValue(int autoGrantedValue) {
		this.autoGrantedValue = autoGrantedValue;
	}
	
	public static void main(String[] args) {
		
	}
}

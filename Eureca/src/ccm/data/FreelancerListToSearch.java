package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FreelancerListToSearch implements DTO
{
	private String jobState;
	private String freeId;
	private String freeName;
	private String primaryLangs;
	private String availableFrames;
	private int projCareerYears;
	private String freeKosa;
	private double freeScore;
	private int leftJoinDays;
	private String freeJoinDate;
	private String freePhone;
	private String freeEmail;
	
	
	public FreelancerListToSearch() { super(); }
	public FreelancerListToSearch(String jobState, String freeId, String freeName, String primaryLangs,
			String availableFrames, int projCareerYears, String freeKosa, double freeScore, int leftJoinDays,
			String freeJoinDate, String freePhone, String freeEmail)
	{
		super();
		this.jobState = jobState;
		this.freeId = freeId;
		this.freeName = freeName;
		this.primaryLangs = primaryLangs;
		this.availableFrames = availableFrames;
		this.projCareerYears = projCareerYears;
		this.freeKosa = freeKosa;
		this.freeScore = freeScore;
		this.leftJoinDays = leftJoinDays;
		this.freeJoinDate = freeJoinDate;
		this.freePhone = freePhone;
		this.freeEmail = freeEmail;
	}

	@Override
	public void setParams(ResultSet rs) throws SQLException
	{
		this.jobState = rs.getString("JOBSTATE");
		this.freeId = rs.getString("FREEID");
		this.freeName = rs.getString("FREENAME");
		this.primaryLangs = rs.getString("PRIMARYLANGS");
		this.availableFrames = rs.getString("AVAILABLEFRAMES");
		this.projCareerYears = rs.getInt("PROJCAREERYEARS");
		this.freeKosa = rs.getString("FREEKOSA");
		this.freeScore = rs.getDouble("FREESCORE");
		this.leftJoinDays = rs.getInt("LEFTJOINDAYS");
		this.freeJoinDate = rs.getString("FREEJOINDATE");
		this.freePhone = rs.getString("FREEPHONE");
		this.freeEmail = rs.getString("FREEEMAIL");
	}

	public String getJobState() { return jobState; }
	public void setJobState(String jobState) { this.jobState = jobState; }
	public String getFreeId() { return freeId; }
	public void setFreeId(String freeId) { this.freeId = freeId; }
	public String getFreeName() { return freeName; }
	public void setFreeName(String freeName) { this.freeName = freeName; }
	public String getPrimaryLangs() { return primaryLangs; }
	public void setPrimaryLangs(String primaryLangs) { this.primaryLangs = primaryLangs; }
	public String getAvailableFrames() { return availableFrames; }
	public void setAvailableFrames(String availableFrames) { this.availableFrames = availableFrames; }
	public int getProjCareerYears() { return projCareerYears; }
	public void setProjCareerYears(int projCareerYears) { this.projCareerYears = projCareerYears; }
	public String getFreeKosa() { return freeKosa; }
	public void setFreeKosa(String freeKosa) { this.freeKosa = freeKosa; }
	public double getFreeScore() { return freeScore; }
	public void setFreeScore(double freeScore) { this.freeScore = freeScore; }
	public int getLeftJoinDays() { return leftJoinDays; }
	public void setLeftJoinDays(int leftJoinDays) { this.leftJoinDays = leftJoinDays; }
	public String getFreeJoinDate() { return freeJoinDate; }
	public void setFreeJoinDate(String freeJoinDate) { this.freeJoinDate = freeJoinDate; }
	public String getFreePhone() { return freePhone; }
	public void setFreePhone(String freePhone) { this.freePhone = freePhone; }
	public String getFreeEmail() { return freeEmail; }
	public void setFreeEmail(String freeEmail) { this.freeEmail = freeEmail; }
}

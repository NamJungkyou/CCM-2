package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProjJoinedFreelancerForList
{
	private int joinNum;
	private String freeState;
	private String freeName;
	private String freeId;
	private String projName;
	private Timestamp applicationDate;
	private Timestamp projRecruitEndDate;
	private Integer projNum;
	
	public ProjJoinedFreelancerForList()
	{
		super();
	}
	public ProjJoinedFreelancerForList(int joinNum, String freeState, String freeName, String freeId,
			String projName, Timestamp applicationDate, Timestamp projRecruitEndDate, int projNum)
	{
		super();
		this.joinNum = joinNum;
		this.freeState = freeState;
		this.freeName = freeName;
		this.freeId = freeId;
		this.projName = projName;
		this.applicationDate = applicationDate;
		this.projRecruitEndDate = projRecruitEndDate;
		this.projNum = projNum;
	}
	
	public void setParams(ResultSet rs) throws SQLException
	{
		this.joinNum = rs.getInt("JOINNUM");
		this.freeState = rs.getString("FREESTATE");
		this.freeName = rs.getString("FREENAME");
		this.freeId = rs.getString("FREEID");
		this.projName = rs.getString("PROJNAME");
		this.applicationDate = rs.getTimestamp("APPLICATIONDATE");
		this.projRecruitEndDate = rs.getTimestamp("PROJRECRUITENDDATE");
		this.projNum = rs.getInt("PROJNUM");
	}
	
	public int getJoinNum() { return joinNum; }
	public void setJoinNum(int joinNum) { this.joinNum = joinNum; }
	public String getFreeState() { return freeState; }
	public void setFreeState(String freeState) { this.freeState = freeState; }
	public String getFreeName() { return freeName; }
	public void setFreeName(String freeName) { this.freeName = freeName; }
	public String getFreeId() { return freeId; }
	public void setFreeId(String freeId) { this.freeId = freeId; }
	public String getProjName() { return projName; }
	public void setProjName(String projName) { this.projName = projName; }
	public Timestamp getApplicationDate() { return this.applicationDate; }
	public void setApplicationDate(Timestamp applicationDate) { this.applicationDate = applicationDate; }
	public Timestamp getProjRecruitEndDate() { return projRecruitEndDate; }
	public void setProjRecruitEndDate(Timestamp projRecruitEndDate) { this.projRecruitEndDate = projRecruitEndDate; }
	public int getProjNum() { return projNum; }
	public void setProjNum(int projNum) { this.projNum = projNum; }
	public void setProjNum(Integer projNum) { this.projNum = projNum; }
}

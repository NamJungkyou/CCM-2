package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProjListForSearch
{
	private int projNum;
	private String projRecruitState;
	private String projState;
	private String projName;
	private Timestamp projStartDate;
	private Timestamp projEndDate;
	private String projDevelopSort;
	private int projLeftTime;
	private int joinNum;
	private int requireNum;
	
	public void setParams(ResultSet rs) throws SQLException
	{
		this.projNum = rs.getInt("PROJNUM");
		this.projRecruitState = rs.getString("PROJRECRUITSTATE");
		this.projState = rs.getString("PROJSTATE");
		this.projDevelopSort = rs.getString("PROJDEVELOPSORT");
		this.projName = rs.getString("PROJNAME");
		this.projStartDate = rs.getTimestamp("PROJSTARTDATE");
		this.projEndDate = rs.getTimestamp("PROJENDDATE");
		int leftTime = rs.getInt("PROJLEFTTIME");
		this.projLeftTime = leftTime >= 0 ? leftTime : 0;
		this.joinNum = rs.getInt("JOINNUM");
		this.requireNum = rs.getInt("REQUIRENUM");
	}
	
	public int getProjNum() { return projNum; }
	public void setProjNum(int projNum) { this.projNum = projNum; }
	public String getProjRecruitState() { return projRecruitState; }
	public void setProjRecruitState(String projRecruitState) { this.projRecruitState = projRecruitState; }
	public String getProjState() { return projState; }
	public void setProjState(String projState) { this.projState = projState; }
	public String getProjName() { return projName; }
	public void setProjName(String projName) { this.projName = projName; }
	public Timestamp getProjStartDate() { return projStartDate; }
	public void setProjStartDate(Timestamp projStartDate) { this.projStartDate = projStartDate; }
	public Timestamp getProjEndDate() { return projEndDate; }
	public void setProjEndDate(Timestamp projEndDate) { this.projEndDate = projEndDate; }
	public String getProjDevelopSort() { return projDevelopSort; }
	public void setProjDevelopSort(String projDevelopSort) { this.projDevelopSort = projDevelopSort; }
	public int getProjLeftTime() { return projLeftTime; }
	public void setProjLeftTime(int projLeftTime) { this.projLeftTime = projLeftTime; }
	public int getJoinNum() { return joinNum; }
	public void setJoinNum(int joinNum) { this.joinNum = joinNum; }
	public int getRequireNum() { return requireNum; }
	public void setRequireNum(int requireNum) { this.requireNum = requireNum; }
}

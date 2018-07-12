package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectInfo extends Project{

	private String frames;				// 프로젝트 사용 프레임워크와 툴들
	private String languages;			// 프로젝트 사용 언어들
	private String requirePeople;		// 프로젝트 모집 인원
	private String joinPeople;			// 프로젝트에 조인한 사람들 
	private String dbName;				// 프로젝트 개발 DB 이름
	
	public String getFrames() {
		return frames;
	}
	public void setFrames(String frames) {
		this.frames = frames;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getRequirePeople() {
		return requirePeople;
	}
	public void setRequirePeople(String requirePeople) {
		this.requirePeople = requirePeople;
	}
	public String getJoinPeople() {
		return joinPeople;
	}
	public void setJoinPeople(String joinPeople) {
		this.joinPeople = joinPeople;
	}
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	@Override
	public String toString() {
		return "Project_Info_view [frames=" + frames + ", languages=" + languages + ", requirePeople=" + requirePeople
				+ ", joinPeople=" + joinPeople + ", dbName=" + dbName + "]";
	}
	
	public void setParams(ResultSet rs) throws SQLException
	{
		setProjNum(rs.getInt("PROJNUM"));
		setProjCompany(rs.getString("PROJCOMPANY"));
		setExtern(rs.getBoolean("ISEXTERN"));
		setProjField(rs.getString("PROJFIELD"));
		setProjName(rs.getString("PROJNAME"));
		setProjState(rs.getString("PROJSTATE"));
		setProjRegisterDate(rs.getString("PROJREGISTERDATE"));
		setProjRegisterer(rs.getString("PROJREGISTERER"));
		setProjReviseDate(rs.getString("PROJREVISEDATE"));
		setProjReviser(rs.getString("PROJREVISER"));
		setProjStartDate(rs.getString("PROJSTARTDATE"));
		setProjEndDate(rs.getString("PROJENDDATE"));
		setProjExpectedTime(rs.getInt("PROJEXPECTEDTIME"));
		setProjTarget(rs.getString("PROJTARGET"));
		setProjPartner(rs.getString("PROJPARTNER"));
		setProjPlan(rs.getString("PROJPLAN"));
		setProjRecruitStartDate(rs.getString("PROJRECRUITSTARTDATE"));
		setProjRecruitEndDate(rs.getString("PROJRECRUITENDDATE"));
		setProjDevelopSort(rs.getString("PROJDEVELOPSORT"));
// --------------------------------------------------------------------------------------
		this.frames = rs.getString("frameWorks");
		this.languages = rs.getString("languages");
		this.requirePeople = rs.getString("requirePeople");
		this.joinPeople = rs.getString("joinPeople");
		this.dbName = rs.getString("dbName");
	}
}

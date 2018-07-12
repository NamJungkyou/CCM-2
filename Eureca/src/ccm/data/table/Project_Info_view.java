package ccm.data.table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Project_Info_view{
	private int projNum;				// 프로젝트 번호
	private String projCompany;			// 프로젝트 시행 회사
	private boolean isExtern;			// 외부 프로젝트 유무
	private String projField;			// 프로젝트 유형(SM or SI)
	private String projName;			// 프로젝트 이름
	private String projState;			// 프로젝트 진행상태
	private Date projRegisterDate;		// 프로젝트 등록일
	private String projRegisterer;		// 프로젝트 등록자
	private Date projReviseDate;		// 프로젝트 수정일
	private String projReviser;			// 프로젝트 수정자
	private Date projStartDate;			// 프로젝트 시작일
	private Date projEndDate;			// 프로젝트 종료일
	private int projExpectedTime;		// 프로젝트 예상기간
	private String projTarget;			// 프로젝트 고객사
	private String projPartner;			// 프로젝트 협력사
	private String projPlan;			// 프로젝트 세부내용
	private Date projRecruitStartDate;	// 프로젝트 인원 모집 시작일
	private Date projRecruitEndDate;	// 프로젝트 인원 모집 종료일
	private String projDevelopSort;		// 개발 분야
	private Integer dbNum;				// 프로젝트 개발 DB 번호
	private String frames;				// 프로젝트 사용 프레임워크와 툴들
	private String languages;			// 프로젝트 사용 언어들
	private String requirePeople;		// 프로젝트 모집 인원
	private String joinPeople;			// 프로젝트에 조인한 사람들 
	private String dbName;				// 프로젝트 개발 DB 이름
	
	public int getProjNum() {
		return projNum;
	}
	public void setProjNum(int projNum) {
		this.projNum = projNum;
	}
	public String getProjCompany() {
		return projCompany;
	}
	public void setProjCompany(String projCompany) {
		this.projCompany = projCompany;
	}
	public boolean isExtern() {
		return isExtern;
	}
	public void setExtern(boolean isExtern) {
		this.isExtern = isExtern;
	}
	public String getProjField() {
		return projField;
	}
	public void setProjField(String projField) {
		this.projField = projField;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjState() {
		return projState;
	}
	public void setProjState(String projState) {
		this.projState = projState;
	}
	public Date getProjRegisterDate() {
		return projRegisterDate;
	}
	public void setProjRegisterDate(Date projRegisterDate) {
		this.projRegisterDate = projRegisterDate;
	}
	public String getProjRegisterer() {
		return projRegisterer;
	}
	public void setProjRegisterer(String projRegisterer) {
		this.projRegisterer = projRegisterer;
	}
	public Date getProjReviseDate() {
		return projReviseDate;
	}
	public void setProjReviseDate(Date projReviseDate) {
		this.projReviseDate = projReviseDate;
	}
	public String getProjReviser() {
		return projReviser;
	}
	public void setProjReviser(String projReviser) {
		this.projReviser = projReviser;
	}
	public Date getProjStartDate() {
		return projStartDate;
	}
	public void setProjStartDate(Date projStartDate) {
		this.projStartDate = projStartDate;
	}
	public Date getProjEndDate() {
		return projEndDate;
	}
	public void setProjEndDate(Date projEndDate) {
		this.projEndDate = projEndDate;
	}
	public int getProjExpectedTime() {
		return projExpectedTime;
	}
	public void setProjExpectedTime(int projExpectedTime) {
		this.projExpectedTime = projExpectedTime;
	}
	public String getProjTarget() {
		return projTarget;
	}
	public void setProjTarget(String projTarget) {
		this.projTarget = projTarget;
	}
	public String getProjPartner() {
		return projPartner;
	}
	public void setProjPartner(String projPartner) {
		this.projPartner = projPartner;
	}
	public String getProjPlan() {
		return projPlan;
	}
	public void setProjPlan(String projPlan) {
		this.projPlan = projPlan;
	}
	public Date getProjRecruitStartDate() {
		return projRecruitStartDate;
	}
	public void setProjRecruitStartDate(Date projRecruitStartDate) {
		this.projRecruitStartDate = projRecruitStartDate;
	}
	public Date getProjRecruitEndDate() {
		return projRecruitEndDate;
	}
	public void setProjRecruitEndDate(Date projRecruitEndDate) {
		this.projRecruitEndDate = projRecruitEndDate;
	}
	public String getProjDevelopSort() {
		return projDevelopSort;
	}
	public void setProjDevelopSort(String projDevelopSort) {
		this.projDevelopSort = projDevelopSort;
	}
	public Integer getDbNum() {
		return dbNum;
	}
	public void setDbNum(Integer dbNum) {
		this.dbNum = dbNum;
	}
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
		return "ProjectInfo [frames=" + frames + ", languages=" + languages + ", requirePeople=" + requirePeople
				+ ", joinPeople=" + joinPeople + ", dbName=" + dbName + ", projNum=" + projNum + ", projCompany="
				+ projCompany + ", isExtern=" + isExtern + ", projField=" + projField + ", projName=" + projName
				+ ", projState=" + projState + ", projRegisterDate=" + projRegisterDate + ", projRegisterer="
				+ projRegisterer + ", projReviseDate=" + projReviseDate + ", projReviser=" + projReviser
				+ ", projStartDate=" + projStartDate + ", projEndDate=" + projEndDate + ", projExpectedTime="
				+ projExpectedTime + ", projTarget=" + projTarget + ", projPartner=" + projPartner + ", projPlan="
				+ projPlan + ", projRecruitStartDate=" + projRecruitStartDate + ", projRecruitEndDate="
				+ projRecruitEndDate + ", projDevelopSort=" + projDevelopSort + ", dbNum=" + dbNum + "]";
	}
	
	public void setParams(ResultSet rs) throws SQLException
	{
		this.projNum = rs.getInt("PROJNUM");
		this.projCompany = rs.getString("PROJCOMPANY");
		this.isExtern = rs.getBoolean("ISEXTERN");
		this.projField = rs.getString("PROJFIELD");
		this.projName = rs.getString("PROJNAME");
		this.projState = rs.getString("PROJSTATE");
		this.projRegisterDate = rs.getDate("PROJREGISTERDATE");
		this.projRegisterer = rs.getString("PROJREGISTERER");
		this.projReviseDate = rs.getDate("PROJREVISEDATE");
		this.projReviser = rs.getString("PROJREVISER");
		this.projStartDate = rs.getDate("PROJSTARTDATE");
		this.projEndDate = rs.getDate("PROJENDDATE");
		this.projExpectedTime = rs.getInt("PROJEXPECTEDTIME");
		this.projTarget = rs.getString("PROJTARGET");
		this.projPartner = rs.getString("PROJPARTNER");
		this.projPlan = rs.getString("PROJPLAN");
		this.projRecruitStartDate = rs.getDate("PROJRECRUITSTARTDATE");
		this.projRecruitEndDate = rs.getDate("PROJRECRUITENDDATE");
		this.projDevelopSort = rs.getString("PROJDEVELOPSORT");
// --------------------------------------------------------------------------------------
		this.frames = rs.getString("frameWorks");
		this.languages = rs.getString("languages");
		this.requirePeople = rs.getString("requirePeople");
		this.joinPeople = rs.getString("joinPeople");
		this.dbName = rs.getString("dbName");
	}
}

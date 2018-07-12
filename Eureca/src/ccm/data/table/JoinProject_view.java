package ccm.data.table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinProject_view {
	private String projNum;				// 프로젝트 번호
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
	private String joinNum;				// 참여 번호
	private Date joinProjDate;			// 투입일
	private Date exitProjDate;			// 철수일
	private String projRole;			// 역할
	private String freeState;			// 프리랜서 상태
	private Date applicationDate;		// 신청일
	private Date joinAcceptDate;		// 접수일
	private String freeId;				// 프리랜서 번호
	
	public String getProjNum() {
		return projNum;
	}
	public void setProjNum(String projNum) {
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
	public String getJoinNum() {
		return joinNum;
	}
	public void setJoinNum(String joinNum) {
		this.joinNum = joinNum;
	}
	public Date getJoinProjDate() {
		return joinProjDate;
	}
	public void setJoinProjDate(Date joinProjDate) {
		this.joinProjDate = joinProjDate;
	}
	public Date getExitProjDate() {
		return exitProjDate;
	}
	public void setExitProjDate(Date exitProjDate) {
		this.exitProjDate = exitProjDate;
	}
	public String getProjRole() {
		return projRole;
	}
	public void setProjRole(String projRole) {
		this.projRole = projRole;
	}
	public String getFreeState() {
		return freeState;
	}
	public void setFreeState(String freeState) {
		this.freeState = freeState;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public Date getJoinAcceptDate() {
		return joinAcceptDate;
	}
	public void setJoinAcceptDate(Date joinAcceptDate) {
		this.joinAcceptDate = joinAcceptDate;
	}
	public String getFreeId() {
		return freeId;
	}
	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}
	
	@Override
	public String toString() {
		return "JoinProject [projNum=" + projNum + ", projCompany=" + projCompany + ", isExtern=" + isExtern
				+ ", projField=" + projField + ", projName=" + projName + ", projState=" + projState
				+ ", projRegisterDate=" + projRegisterDate + ", projRegisterer=" + projRegisterer + ", projReviseDate="
				+ projReviseDate + ", projReviser=" + projReviser + ", projStartDate=" + projStartDate
				+ ", projEndDate=" + projEndDate + ", projExpectedTime=" + projExpectedTime + ", projTarget="
				+ projTarget + ", projPartner=" + projPartner + ", projPlan=" + projPlan + ", projRecruitStartDate="
				+ projRecruitStartDate + ", projRecruitEndDate=" + projRecruitEndDate + ", projDevelopSort="
				+ projDevelopSort + ", dbNum=" + dbNum + ", joinNum=" + joinNum + ", joinProjDate=" + joinProjDate
				+ ", exitProjDate=" + exitProjDate + ", projRole=" + projRole + ", freeState=" + freeState
				+ ", applicationDate=" + applicationDate + ", joinAcceptDate=" + joinAcceptDate + ", freeId=" + freeId
				+ "]";
	}
	
	public void setParams(ResultSet rs) throws SQLException
	{
		this.joinNum = rs.getString("joinNum");
		this.joinProjDate = rs.getDate("joinProjDate");
		this.exitProjDate = rs.getDate("exitProjDate");
		this.projRole = rs.getString("projRole");
		this.freeState = rs.getString("freeState");
		this.applicationDate = rs.getDate("applicationDate");
		this.joinAcceptDate = rs.getDate("joinAcceptDate");
		this.projNum = rs.getString("projNum");
		this.freeId = rs.getString("freeId");
		this.projCompany = rs.getString("projCompany");
		this.isExtern = rs.getBoolean("isExtern");
		this.projName = rs.getString("projName");
		this.projField = rs.getString("projField");
		this.projState = rs.getString("projState");
		this.projRegisterDate = rs.getDate("projRegisterDate");
		this.projRegisterer = rs.getString("projRegisterer");
		this.projReviseDate = rs.getDate("projReviseDate");
		this.projReviser = rs.getString("projReviser");
		this.projStartDate = rs.getDate("projStartDate");
		this.projEndDate = rs.getDate("projEndDate");
		this.projExpectedTime = rs.getInt("projExpectedTime");
		this.projTarget = rs.getString("projTarget");
		this.projPartner = rs.getString("projPartner");
		this.projPlan = rs.getString("projPlan");
		this.projRecruitStartDate = rs.getDate("projRecruitStartDate");
		this.projRecruitEndDate = rs.getDate("projRecruitEndDate");
		this.projDevelopSort = rs.getString("projDevelopSort");
		this.dbNum = rs.getInt("DBNum");
	}
}

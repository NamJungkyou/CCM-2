package ccm.data.table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinProjectView extends ProjectView {

	private String joinNum; // 참여 번호
	private Date joinProjDate; // 투입일
	private Date exitProjDate; // 철수일
	private String projRole; // 역할
	private String freeState; // 프리랜서 상태
	private Date applicationDate; // 신청일
	private Date joinAcceptDate; // 접수일
	private String freeId; // 프리랜서 번호

	
	
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

	public void setParams(ResultSet rs) throws SQLException {
		this.joinNum = rs.getString("joinNum");
		this.joinProjDate = rs.getDate("joinProjDate");
		this.exitProjDate = rs.getDate("exitProjDate");
		this.projRole = rs.getString("projRole");
		this.freeState = rs.getString("freeState");
		this.applicationDate = rs.getDate("applicationDate");
		this.joinAcceptDate = rs.getDate("joinAcceptDate");
		this.freeId = rs.getString("freeId");
		setProjNum(Integer.parseInt(rs.getString("projNum")));
		setProjCompany(rs.getString("projCompany"));
		setIsExtern(rs.getBoolean("isExtern"));
		setProjName(rs.getString("projName"));
		setProjField(rs.getString("projField"));
		setProjState(rs.getString("projState"));
		setProjRegisterDate(rs.getString("projRegisterDate"));
		setProjRegisterer(rs.getString("projRegisterer"));
		setProjReviseDate(rs.getString("projReviseDate"));
		setProjReviser(rs.getString("projReviser"));
		setProjStartDate(rs.getString("projStartDate"));
		setProjEndDate(rs.getString("projEndDate"));
		setProjExpectedTime(rs.getInt("projExpectedTime"));
		setProjTarget(rs.getString("projTarget"));
		setProjPartner(rs.getString("projPartner"));
		setProjPlan(rs.getString("projPlan"));
		setProjRecruitStartDate(rs.getString("projRecruitStartDate"));
		setProjRecruitEndDate(rs.getString("projRecruitEndDate"));
		setProjDevelopSort(rs.getString("projDevelopSort"));
		setDbNum(rs.getInt("DBNum"));
	}

	@Override
	public String toString() {
		return "JoinProjectView [joinNum=" + joinNum + ", joinProjDate=" + joinProjDate + ", exitProjDate="
				+ exitProjDate + ", projRole=" + projRole + ", freeState=" + freeState + ", applicationDate="
				+ applicationDate + ", joinAcceptDate=" + joinAcceptDate + ", freeId=" + freeId + "projName="+getProjName()+"]";
	}
	
	
}

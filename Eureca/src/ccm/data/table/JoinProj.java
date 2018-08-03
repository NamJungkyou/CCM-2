package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 생성된 Joinproj 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class JoinProj extends Project {
	// 참여 번호
	private Integer joinNum;
	
	// 투입일
	private String joinProjDate;
	
	// 철수일
	private String exitProjDate;
	
	// 역할
	private String projRole;
	
	// 프리랜서 상태
	private String freeState;
	
	// 참여신청일
	private String applicationDate;
	
	// 참여신청 접수일
	private String joinAcceptDate;
	
	// 프로젝트 번호
	private Integer projNum;
	
	// 프리랜서 아이디
	private String freeId;

	
	/*******************************  게터 세터  **********************************/
	public Integer getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}

	public String getJoinProjDate() {
		return joinProjDate;
	}

	public void setJoinProjDate(String joinProjDate) {
		this.joinProjDate = joinProjDate;
	}

	public String getExitProjDate() {
		return exitProjDate;
	}

	public void setExitProjDate(String exitProjDate) {
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

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getJoinAcceptDate() {
		return joinAcceptDate;
	}

	public void setJoinAcceptDate(String joinAcceptDate) {
		this.joinAcceptDate = joinAcceptDate;
	}

	public int getProjNum() {
		return projNum;
	}

	public void setProjNum(Integer projNum) {
		this.projNum = projNum;
	}

	public String getFreeId() {
		return freeId;
	}

	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}

	@Override
	public String toString() {
		return "JoinProj [joinNum=" + joinNum + ", joinProjDate=" + joinProjDate + ", exitProjDate=" + exitProjDate
				+ ", projRole=" + projRole + ", freeState=" + freeState + ", applicationDate=" + applicationDate
				+ ", joinAcceptDate=" + joinAcceptDate + ", projNum=" + projNum + ", freeId=" + freeId + "]";
	}

	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 * 
	 * @param paramResultSet
	 * @throws SQLException
	 */
	public void setParams(ResultSet rs) throws SQLException {
		this.joinNum = rs.getInt("joinNum");
		this.joinProjDate = rs.getString("joinProjDate");
		this.exitProjDate = rs.getString("exitProjDate");
		this.projRole = rs.getString("projRole");
		this.freeState = rs.getString("freeState");
		this.applicationDate = rs.getString("applicationDate");
		this.joinAcceptDate = rs.getString("joinAcceptDate");
		this.projNum = rs.getInt("projNum");
		this.freeId = rs.getString("freeId");
	}

	public void setParamsIncludeProject(ResultSet rs) throws SQLException {
		this.joinNum = rs.getInt("joinNum");
		this.joinProjDate = rs.getString("joinProjDate");
		this.exitProjDate = rs.getString("exitProjDate");
		this.projRole = rs.getString("projRole");
		this.freeState = rs.getString("freeState");
		this.applicationDate = rs.getString("applicationDate");
		this.joinAcceptDate = rs.getString("joinAcceptDate");
		this.projNum = rs.getInt("projNum");
		this.freeId = rs.getString("freeId");

		// 상속받은 프로젝트 파일의 변수들 관련
		/*
		 * this.projCompany = rs.getString("projCompany"); this.isExtern =
		 * rs.getBoolean("isExtern"); this.projName = rs.getString("projName");
		 * this.projField = rs.getString("projField"); this.projState =
		 * rs.getString("projState"); this.projRegisterDate =
		 * rs.getDate("projRegisterDate"); this.projRegisterer =
		 * rs.getString("projRegisterer"); this.projReviseDate =
		 * rs.getDate("projReviseDate"); this.projReviser = rs.getString("projReviser");
		 * this.projStartDate = rs.getDate("projStartDate"); this.projEndDate =
		 * rs.getDate("projEndDate"); this.projExpectedTime =
		 * rs.getInt("projExpectedTime"); this.projTarget = rs.getString("projTarget");
		 * this.projPartner = rs.getString("projPartner"); this.projPlan =
		 * rs.getString("projPlan"); this.projRecruitStartDate =
		 * rs.getDate("projRecruitStartDate"); this.projRecruitEndDate =
		 * rs.getDate("projRecruitEndDate"); this.projDevelopSort =
		 * rs.getString("projDevelopSort"); this.dbNum = rs.getInt("DBNum");
		 */
	}
}

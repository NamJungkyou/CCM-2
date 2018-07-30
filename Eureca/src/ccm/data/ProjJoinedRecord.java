package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * 프로젝트 참여 기록에 대한 정보를 저장하는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ProjJoinedRecord implements DTO {
	
	// 모집 종료일
	private String projRecruitEndDate;
	
	// 채용 현황
	private String recruitState;
	
	// 프로젝트 상태
	private String projState;
	
	// 프로젝트 이름
	private String projName;
	
	// 프로젝트 당시 또는 현재 역할
	private String projRole;
	
	// 참여 인원
	private Integer numOfJoined;
	
	// 필요인원
	private Integer sumRequireNum;
	
	// 참여시작일
	private String joinProjDate;
	
	// 철수일
	private String exitProjDate;
	
	// 참여기간
	private int joinTerm;

	/**
	 * 기본생성자
	 */
	public ProjJoinedRecord() {
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param projRecruitEndDate
	 * @param recruitState
	 * @param projState
	 * @param projName
	 * @param projRole
	 * @param numOfJoined
	 * @param sumRequireNum
	 * @param joinProjDate
	 * @param exitProjDate
	 * @param joinTerm
	 */
	public ProjJoinedRecord(String projRecruitEndDate, String recruitState, String projState, String projName,
			String projRole, Integer numOfJoined, Integer sumRequireNum, String joinProjDate, String exitProjDate,
			int joinTerm) {
		this.projRecruitEndDate = projRecruitEndDate;
		this.recruitState = recruitState;
		this.projState = projState;
		this.projName = projName;
		this.projRole = projRole;
		this.numOfJoined = numOfJoined;
		this.sumRequireNum = sumRequireNum;
		this.joinProjDate = joinProjDate;
		this.exitProjDate = exitProjDate;
		this.joinTerm = joinTerm;
	}

	/*********************************  게터 세터  *********************************/
	public String getProjRecruitEndDate() {
		return projRecruitEndDate;
	}

	public void setProjRecruitEndDate(String projRecruitEndDate) {
		this.projRecruitEndDate = projRecruitEndDate;
	}

	public String getRecruitState() {
		return recruitState;
	}

	public void setRecruitState(String recruitState) {
		this.recruitState = recruitState;
	}

	public String getProjState() {
		return projState;
	}

	public void setProjState(String projState) {
		this.projState = projState;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjRole() {
		return projRole;
	}

	public void setProjRole(String projRole) {
		this.projRole = projRole;
	}

	public Integer getNumOfJoined() {
		return numOfJoined;
	}

	public void setNumOfJoined(Integer numOfJoined) {
		this.numOfJoined = numOfJoined;
	}

	public Integer getSumRequireNum() {
		return sumRequireNum;
	}

	public void setSumRequireNum(Integer sumRequireNum) {
		this.sumRequireNum = sumRequireNum;
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

	public int getJoinTerm() {
		return joinTerm;
	}

	public void setJoinTerm(int joinTerm) {
		this.joinTerm = joinTerm;
	}

	/**
	 * DAO에서 간편하게 값을 세팅할 수 있는 메소드
	 * 
	 */
	public void setParams(ResultSet rs) throws SQLException {
		projRecruitEndDate = rs.getString("PROJRECRUITENDDATE");
		recruitState = rs.getString("RECRUITSTATE");
		projState = rs.getString("PROJSTATE");
		projName = rs.getString("PROJNAME");
		projRole = rs.getString("PROJROLE");
		numOfJoined = Integer.valueOf(rs.getInt("NUMOFJOINED"));
		sumRequireNum = Integer.valueOf(rs.getInt("SUMREQUIRENUM"));
		joinProjDate = rs.getString("JOINPROJDATE");
		exitProjDate = rs.getString("EXITPROJDATE");
		joinTerm = rs.getInt("JOINTERM");
	}
}
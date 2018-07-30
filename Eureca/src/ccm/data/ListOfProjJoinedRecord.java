package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 프로젝트 참여기록 목록에 넣기위한 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ListOfProjJoinedRecord implements DTO {
	
	// 채용 현황
	private String recruitState;
	
	// 프로젝트 진행 상태
	private String projState;
	
	// 프로젝트 이름
	private String projName;
	
	// 프로젝트 역할
	private String projRole;
	
	// 채용인원 상태
	private String requireState;
	
	// 참여시작일
	private String joinProjDate;
	
	// 철수일
	private String exitProjDate;
	
	// 참여기간
	private int joinTerm;

	/**
	 * 기본생성자
	 */
	public ListOfProjJoinedRecord() {
	}

	/***********************************  게터 세터  *****************************************/
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

	public String getRequireState() {
		return requireState;
	}

	public void setRequireState(String requireState) {
		this.requireState = requireState;
	}

	public String getProjRole() {
		return projRole;
	}

	public void setProjRole(String projRole) {
		this.projRole = projRole;
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
	 * DAO에서 편리하게 값을 세팅할 수 있는 메소드
	 */
	public void setParams(ResultSet rs) throws SQLException {
		recruitState = rs.getString("RECRUITSTATE");
		projState = rs.getString("PROJSTATE");
		projName = rs.getString("PROJNAME");
		projRole = rs.getString("PROJROLE");
		requireState = rs.getString("REQUIRESTATE");
		joinProjDate = rs.getString("JOINPROJDATE");
		exitProjDate = rs.getString("EXITPROJDATE");
		joinTerm = rs.getInt("JOINTERM");
	}
}
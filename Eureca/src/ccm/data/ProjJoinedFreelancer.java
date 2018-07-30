package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 프로젝트에 참여하고있는 프리랜서 정보를 저장하는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ProjJoinedFreelancer {
	
	// 프로젝트 번호
	private int projNum;
	
	// 참여 번호
	private int joinNum;
	
	// 프리랜서 아이디
	private String freeId;
	
	// 프리랜서 이름
	private String freeName;
	
	// 참여 역할
	private String projRole;
	
	// 프로젝트 참여일
	private Timestamp joinProjDate;
	
	// 철수일
	private Timestamp exitProjDate;
	
	// 프리랜서 핸드폰번호
	private String freePhone;
	
	// 프리랜서 이메일
	private String freeEmail;

	/**
	 * 기본 생성자
	 */
	public ProjJoinedFreelancer() {
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param projNum
	 * @param joinNum
	 * @param freeId
	 * @param freeName
	 * @param projRole
	 * @param joinProjDate
	 * @param exitProjDate
	 * @param freePhone
	 * @param freeEmail
	 */
	public ProjJoinedFreelancer(int projNum, int joinNum, String freeId, String freeName, String projRole,
			Timestamp joinProjDate, Timestamp exitProjDate, String freePhone, String freeEmail) {
		this.projNum = projNum;
		this.joinNum = joinNum;
		this.freeId = freeId;
		this.freeName = freeName;
		this.projRole = projRole;
		this.joinProjDate = joinProjDate;
		this.exitProjDate = exitProjDate;
		this.freePhone = freePhone;
		this.freeEmail = freeEmail;
	}

	/**
	 * 
	 * ResultSet에서 필드를 세팅하는 메소드
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public void setParams(ResultSet rs) throws SQLException {
		projNum = rs.getInt("PROJNUM");
		joinNum = rs.getInt("JOINNUM");
		freeId = rs.getString("fREEID");
		freeName = rs.getString("FREENAME");
		projRole = rs.getString("PROJROLE");
		joinProjDate = rs.getTimestamp("JOINPROJDATE");
		exitProjDate = rs.getTimestamp("EXITPROJDATE");
		freePhone = rs.getString("FREEPHONE");
		freeEmail = rs.getString("FREEEMAIL");
	}

	/************************************  게터 세터  **************************************/
	public int getProjNum() {
		return projNum;
	}

	public void setProjNum(int projNum) {
		this.projNum = projNum;
	}

	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	public String getFreeId() {
		return freeId;
	}

	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}

	public String getFreeName() {
		return freeName;
	}

	public void setFreeName(String freeName) {
		this.freeName = freeName;
	}

	public String getProjRole() {
		return projRole;
	}

	public void setProjRole(String projRole) {
		this.projRole = projRole;
	}

	public Timestamp getJoinProjDate() {
		return joinProjDate;
	}

	public void setJoinProjDate(Timestamp joinProjDate) {
		this.joinProjDate = joinProjDate;
	}

	public Timestamp getExitProjDate() {
		return exitProjDate;
	}

	public void setExitProjDate(Timestamp exitProjDate) {
		this.exitProjDate = exitProjDate;
	}

	public String getFreePhone() {
		return freePhone;
	}

	public void setFreePhone(String freePhone) {
		this.freePhone = freePhone;
	}

	public String getFreeEmail() {
		return freeEmail;
	}

	public void setFreeEmail(String freeEmail) {
		this.freeEmail = freeEmail;
	}
}
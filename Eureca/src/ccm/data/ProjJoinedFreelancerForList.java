package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 * 
 * 프로젝트 참여신청한 프리랜서 정보를 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */
public class ProjJoinedFreelancerForList {
	
	// 참여 번호
	private int joinNum;
	
	// 프리랜서 현재 상태
	private String freeState;
	
	// 프리랜서 이름
	private String freeName;
	
	// 프리랜서 아이디
	private String freeId;
	
	// 프로젝트 이름
	private String projName;
	
	// 참여신청일
	private Timestamp applicationDate;
	
	// 모집 종료일
	private Timestamp projRecruitEndDate;
	
	// 프로젝트 번호
	private Integer projNum;

	
	/**
	 * 기본 생성자
	 */
	public ProjJoinedFreelancerForList() {
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param joinNum
	 * @param freeState
	 * @param freeName
	 * @param freeId
	 * @param projName
	 * @param applicationDate
	 * @param projRecruitEndDate
	 * @param projNum
	 */
	public ProjJoinedFreelancerForList(int joinNum, String freeState, String freeName, String freeId, String projName,
			Timestamp applicationDate, Timestamp projRecruitEndDate, int projNum) {
		this.joinNum = joinNum;
		this.freeState = freeState;
		this.freeName = freeName;
		this.freeId = freeId;
		this.projName = projName;
		this.applicationDate = applicationDate;
		this.projRecruitEndDate = projRecruitEndDate;
		this.projNum = Integer.valueOf(projNum);
	}

	/**
	 * 
	 * ResultSet 객체에서 받아온 값들을 필드에 세팅하는 메소드
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public void setParams(ResultSet rs) throws SQLException {
		joinNum = rs.getInt("JOINNUM");
		freeState = rs.getString("FREESTATE");
		freeName = rs.getString("FREENAME");
		freeId = rs.getString("FREEID");
		projName = rs.getString("PROJNAME");
		applicationDate = rs.getTimestamp("APPLICATIONDATE");
		projRecruitEndDate = rs.getTimestamp("PROJRECRUITENDDATE");
		projNum = Integer.valueOf(rs.getInt("PROJNUM"));
	}

	/*********************************  게터 세터  *********************************/
	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	public String getFreeState() {
		return freeState;
	}

	public void setFreeState(String freeState) {
		this.freeState = freeState;
	}

	public String getFreeName() {
		return freeName;
	}

	public void setFreeName(String freeName) {
		this.freeName = freeName;
	}

	public String getFreeId() {
		return freeId;
	}

	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Timestamp getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Timestamp applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Timestamp getProjRecruitEndDate() {
		return projRecruitEndDate;
	}

	public void setProjRecruitEndDate(Timestamp projRecruitEndDate) {
		this.projRecruitEndDate = projRecruitEndDate;
	}

	public int getProjNum() {
		return projNum.intValue();
	}

	public void setProjNum(int projNum) {
		this.projNum = Integer.valueOf(projNum);
	}

	public void setProjNum(Integer projNum) {
		this.projNum = projNum;
	}
}
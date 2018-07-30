package ccm.data;

import java.sql.ResultSet;

/**
 * 프로젝트 검색에서 프로젝트 목록에 있는 데이터를
 * 나타내기 위한 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ProjListForSearch {
	
	// 프로젝트 번호
	private int projNum;
	
	// 채용 현황
	private String projRecruitState;
	
	// 프로젝트 상태
	private String projState;
	
	// 프로젝트 이름
	private String projName;
	
	// 프로젝트 시작일
	private java.sql.Timestamp projStartDate;
	
	// 프로젝트 종료일
	private java.sql.Timestamp projEndDate;
	
	// 개발 분야
	private String projDevelopSort;
	
	// 남은 기간
	private int projLeftTime;
	
	// 참여 변호
	private int joinNum;
	
	// 필요 인원
	private int requireNum;

	/**
	 * 기본생성자
	 */
	public ProjListForSearch() {
	}

	/**
	 * DAO에서 객체 값을 편하게 세팅하기 위한 메소드 
	 * 
	 * @param rs
	 * @throws java.sql.SQLException
	 */
	public void setParams(ResultSet rs) throws java.sql.SQLException {
		projNum = rs.getInt("PROJNUM");
		projRecruitState = rs.getString("PROJRECRUITSTATE");
		projState = rs.getString("PROJSTATE");
		projDevelopSort = rs.getString("PROJDEVELOPSORT");
		projName = rs.getString("PROJNAME");
		projStartDate = rs.getTimestamp("PROJSTARTDATE");
		projEndDate = rs.getTimestamp("PROJENDDATE");
		int leftTime = rs.getInt("PROJLEFTTIME");
		
		// 남은 기간이 초과되면 음수가 될수도 있는데 프로젝트 남은기간이 0보다 작아지면
		// 그냥 계속 0으로 표시되게 만듦
		projLeftTime = (leftTime >= 0 ? leftTime : 0);
		joinNum = rs.getInt("JOINNUM");
		requireNum = rs.getInt("REQUIRENUM");
	}

	
	/**********************************  게터 세터  *************************************/
	public int getProjNum() {
		return projNum;
	}

	public void setProjNum(int projNum) {
		this.projNum = projNum;
	}

	public String getProjRecruitState() {
		return projRecruitState;
	}

	public void setProjRecruitState(String projRecruitState) {
		this.projRecruitState = projRecruitState;
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

	public java.sql.Timestamp getProjStartDate() {
		return projStartDate;
	}

	public void setProjStartDate(java.sql.Timestamp projStartDate) {
		this.projStartDate = projStartDate;
	}

	public java.sql.Timestamp getProjEndDate() {
		return projEndDate;
	}

	public void setProjEndDate(java.sql.Timestamp projEndDate) {
		this.projEndDate = projEndDate;
	}

	public String getProjDevelopSort() {
		return projDevelopSort;
	}

	public void setProjDevelopSort(String projDevelopSort) {
		this.projDevelopSort = projDevelopSort;
	}

	public int getProjLeftTime() {
		return projLeftTime;
	}

	public void setProjLeftTime(int projLeftTime) {
		this.projLeftTime = projLeftTime;
	}

	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	public int getRequireNum() {
		return requireNum;
	}

	public void setRequireNum(int requireNum) {
		this.requireNum = requireNum;
	}
}
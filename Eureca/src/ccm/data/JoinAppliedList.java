package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 참여신청한 프리랜서의 정보를 저장하는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class JoinAppliedList implements DTO {
	
	// 프로젝트 채용 현황
	private String recruitState;
	
	// 프로젝트 진행 상태
	private String projState;
	
	// 프로젝트 이름
	private String projName;
	
	// 개발 분야
	private String projDevelopSort;
	
	// 참여 인원
	private int sumJoined;
	
	// 필요 인원
	private int sumRequire;
	
	// 프로젝트 시작일
	private String projStartDate;
	
	// 프로젝트 종료일
	private String projEndDate;
	
	// 남은 기간
	private int leftTerm;

	/**
	 * 기본 생성자
	 */
	public JoinAppliedList() {
	}

	/**
	 * 매개변수가 있는 생성자
	 * 
	 * @param recruitState
	 * @param projState
	 * @param projName
	 * @param projDevelopSort
	 * @param sumJoined
	 * @param sumRequire
	 * @param projStartDate
	 * @param projEndDate
	 * @param leftTerm
	 */
	public JoinAppliedList(String recruitState, String projState, String projName, String projDevelopSort,
			int sumJoined, int sumRequire, String projStartDate, String projEndDate, int leftTerm) {
		this.recruitState = recruitState;
		this.projState = projState;
		this.projName = projName;
		this.projDevelopSort = projDevelopSort;
		this.sumJoined = sumJoined;
		this.sumRequire = sumRequire;
		this.projStartDate = projStartDate;
		this.projEndDate = projEndDate;
		this.leftTerm = leftTerm;
	}

	/********************************  게터 세터  ********************************/
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

	public String getProjDevelopSort() {
		return projDevelopSort;
	}

	public void setProjDevelopSort(String projDevelopSort) {
		this.projDevelopSort = projDevelopSort;
	}

	public int getSumJoined() {
		return sumJoined;
	}

	public void setSumJoined(int sumJoined) {
		this.sumJoined = sumJoined;
	}

	public int getSumRequire() {
		return sumRequire;
	}

	public void setSumRequire(int sumRequire) {
		this.sumRequire = sumRequire;
	}

	public String getProjStartDate() {
		return projStartDate;
	}

	public void setProjStartDate(String projStartDate) {
		this.projStartDate = projStartDate;
	}

	public String getProjEndDate() {
		return projEndDate;
	}

	public void setProjEndDate(String projEndDate) {
		this.projEndDate = projEndDate;
	}

	public int getLeftTerm() {
		return leftTerm;
	}

	public void setLeftTerm(int leftTerm) {
		this.leftTerm = leftTerm;
	}

	/**
	 * DAO에서 객체의 갮을 편리하게 세팅할수 있게 해주는 메소드
	 */
	public void setParams(ResultSet rs) throws SQLException {
		recruitState = rs.getString("RECRUITSTATE");
		projState = rs.getString("PROJSTATE");
		projName = rs.getString("PROJNAME");
		projDevelopSort = rs.getString("PROJDEVELOPSORT");
		sumJoined = rs.getInt("SUMJOINED");
		sumRequire = rs.getInt("SUMREQUIRE");
		projStartDate = rs.getString("PROJSTARTDATE");
		projEndDate = rs.getString("PROJENDDATE");
		leftTerm = rs.getInt("LEFTTERM");
	}
}
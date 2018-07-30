package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 참여신청한 프로젝트 리스트를 만들기 위한 클래스
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ListOfJoinAppliedList implements DTO {
	// 프로젝트 채용 현황
	private String recruitState;
	
	// 프로젝트 진행상태
	private String projState;
	
	// 프로젝트 이름
	private String projName;
	
	// 개발 분야
	private String projDevSort;
	
	// 필요인원 채용인원 현황
	private String requireState;
	
	// 프로젝트 시작일
	private String projStartDate;
	
	// 프로젝트 종료일
	private String projEndDate;
	
	// 프로젝트 진행 기간
	private int projTerm;

	/**
	 * 기본생성자
	 */
	public ListOfJoinAppliedList() {
	}

	/*************************************  게터 세터  **************************************/
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

	public String getProjDevSort() {
		return projDevSort;
	}

	public void setProjDevSort(String projDevSort) {
		this.projDevSort = projDevSort;
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

	public int getProjTerm() {
		return projTerm;
	}

	public void setProjTerm(int projTerm) {
		this.projTerm = projTerm;
	}

	/**
	 * DAO에서 편리하게 값을 세팅해주는 메소드
	 */
	public void setParams(ResultSet rs) throws SQLException {
		recruitState = rs.getString("RECRUITSTATE");
		projState = rs.getString("PROJSTATE");
		projName = rs.getString("PROJNAME");
		projDevSort = rs.getString("PROJDEVSORT");
		requireState = rs.getString("REQUIRESTATE");
		projStartDate = rs.getString("projStartDate");
		projEndDate = rs.getString("PROJENDDATE");
		projTerm = rs.getInt("PROJTERM");
	}
}
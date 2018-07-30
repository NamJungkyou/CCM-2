package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 추천 프리랜서 정보를 저장하는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class RecommendedFreelancer implements DTO {
	// 프리랜서 아이디
	private String freeId;
	
	// 프리랜서 프로젝트 참여 상태
	private String freeState;
	
	// 프리랜서 이름
	private String freeName;
	
	// 프로젝트 남은 기간
	private String leftJoinDays;
	
	// 주 언어
	private String primaryLangs;
	
	// 주 프레임워크
	private String primaryFrames;
	
	// 경력기간 년수
	private int projCareerYears;
	
	// 코사등급
	private String freeKosa;
	
	// 프리랜서 평점
	private double freeScore;

	/*
	 * 기본생성자
	 */
	public RecommendedFreelancer() {
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param freeId
	 * @param freeState
	 * @param freeName
	 * @param leftJoinDays
	 * @param primaryLangs
	 * @param primaryFrames
	 * @param projCareerYears
	 * @param freeKosa
	 * @param freeScore
	 */
	public RecommendedFreelancer(String freeId, String freeState, String freeName, String leftJoinDays,
			String primaryLangs, String primaryFrames, int projCareerYears, String freeKosa, double freeScore) {
		this.freeId = freeId;
		this.freeState = freeState;
		this.freeName = freeName;
		this.leftJoinDays = leftJoinDays;
		this.primaryLangs = primaryLangs;
		this.primaryFrames = primaryFrames;
		this.projCareerYears = projCareerYears;
		this.freeKosa = freeKosa;
		this.freeScore = freeScore;
	}

	/***********************************  게터 세터  ****************************************/
	public String getFreeId() {
		return freeId;
	}

	public void setFreeId(String freeId) {
		this.freeId = freeId;
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

	public String getLeftJoinDays() {
		return leftJoinDays;
	}

	public void setLeftJoinDays(String leftJoinDays) {
		this.leftJoinDays = leftJoinDays;
	}

	public String getPrimaryLangs() {
		return primaryLangs;
	}

	public void setPrimaryLangs(String primaryLangs) {
		this.primaryLangs = primaryLangs;
	}

	public String getPrimaryFrames() {
		return primaryFrames;
	}

	public void setPrimaryFrames(String primaryFrames) {
		this.primaryFrames = primaryFrames;
	}

	public int getProjCareerYears() {
		return projCareerYears;
	}

	public void setProjCareerYears(int projCareerYears) {
		this.projCareerYears = projCareerYears;
	}

	public String getFreeKosa() {
		return freeKosa;
	}

	public void setFreeKosa(String freeKosa) {
		this.freeKosa = freeKosa;
	}

	public double getFreeScore() {
		return freeScore;
	}

	public void setFreeScore(double freeScore) {
		this.freeScore = freeScore;
	}

	/**
	 * DAO에서 값을 세팅하기 편하게 만드는 메소드
	 */
	public void setParams(ResultSet rs) throws SQLException {
		freeId = rs.getString("FREEID");
		freeState = rs.getString("FREESTATE");
		freeName = rs.getString("FREENAME");
		leftJoinDays = rs.getString("LEFTJOINDAYS");
		primaryLangs = rs.getString("PRIMARYLANGS");
		primaryFrames = rs.getString("PRIMARYFRAMES");
		projCareerYears = rs.getInt("PROJCAREERYEARS");

		// 코사등급의 숫자에 따라
		// 초급 ~ 특급으로 등급이 정해짐
		switch (rs.getInt("freeKosa")) {
		case 1:
			freeKosa = "초급기능사";
			break;
		case 2:
			freeKosa = "중급기능사";
			break;
		case 3:
			freeKosa = "고급기능사";
			break;
		case 4:
			freeKosa = "초급기술자";
			break;
		case 5:
			freeKosa = "중급기술자";
			break;
		case 6:
			freeKosa = "고급기술자";
			break;
		case 7:
			freeKosa = "특급기술자";
			break;
		default:
			freeKosa = "등급 없음";
		}

		freeScore = rs.getDouble("FREESCORE");
	}
}

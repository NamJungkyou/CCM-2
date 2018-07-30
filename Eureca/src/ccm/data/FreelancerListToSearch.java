package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 
 * 프리랜서 검색에서 프리랜서 리스트를 나타낼때
 * 사용하는 클래스
 * 
 * @author 글로벌 IT 진재환
 *
 */

public class FreelancerListToSearch implements DTO {
	
	// 구직상태
	private String jobState;
	
	// 프리랜서 아이디
	private String freeId;
	
	// 프리랜서 이름
	private String freeName;
	
	// 주 언어
	private String primaryLangs;
	
	// 사용가능한 프레임워크
	private String availableFrames;
	
	// 근무경력 년수
	private int projCareerYears;
	
	// 코사등급
	private String freeKosa;
	
	// 프리랜서 평점
	private double freeScore;
	
	// 남은기간
	private int leftJoinDays;
	
	// 가입일
	private String freeJoinDate;
	
	// 핸드폰번호
	private String freePhone;
	
	// 이메일
	private String freeEmail;

	/**
	 * 기본 생성자
	 */
	public FreelancerListToSearch() {
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param jobState
	 * @param freeId
	 * @param freeName
	 * @param primaryLangs
	 * @param availableFrames
	 * @param projCareerYears
	 * @param freeKosa
	 * @param freeScore
	 * @param leftJoinDays
	 * @param freeJoinDate
	 * @param freePhone
	 * @param freeEmail
	 */
	public FreelancerListToSearch(String jobState, String freeId, String freeName, String primaryLangs,
			String availableFrames, int projCareerYears, String freeKosa, double freeScore, int leftJoinDays,
			String freeJoinDate, String freePhone, String freeEmail) {
		this.jobState = jobState;
		this.freeId = freeId;
		this.freeName = freeName;
		this.primaryLangs = primaryLangs;
		this.availableFrames = availableFrames;
		this.projCareerYears = projCareerYears;
		this.freeKosa = freeKosa;
		this.freeScore = freeScore;
		this.leftJoinDays = leftJoinDays;
		this.freeJoinDate = freeJoinDate;
		this.freePhone = freePhone;
		this.freeEmail = freeEmail;
	}

	/**
	 * DAO ResultSet에서 가져온 값을 세팅하는 메소드
	 */
	public void setParams(ResultSet rs) throws SQLException {
		jobState = rs.getString("JOBSTATE");
		freeId = rs.getString("FREEID");
		freeName = rs.getString("FREENAME");
		primaryLangs = rs.getString("PRIMARYLANGS");
		availableFrames = rs.getString("AVAILABLEFRAMES");
		projCareerYears = rs.getInt("PROJCAREERYEARS");
		freeKosa = rs.getString("FREEKOSA");
		freeScore = rs.getDouble("FREESCORE");
		leftJoinDays = rs.getInt("LEFTJOINDAYS");
		freeJoinDate = rs.getString("FREEJOINDATE");
		freePhone = rs.getString("FREEPHONE");
		freeEmail = rs.getString("FREEEMAIL");
	}

	/**********************************************  게터 세터  *********************************************************/
	public String getJobState() {
		return jobState;
	}

	public void setJobState(String jobState) {
		this.jobState = jobState;
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

	public String getPrimaryLangs() {
		return primaryLangs;
	}

	public void setPrimaryLangs(String primaryLangs) {
		this.primaryLangs = primaryLangs;
	}

	public String getAvailableFrames() {
		return availableFrames;
	}

	public void setAvailableFrames(String availableFrames) {
		this.availableFrames = availableFrames;
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

	public int getLeftJoinDays() {
		return leftJoinDays;
	}

	public void setLeftJoinDays(int leftJoinDays) {
		this.leftJoinDays = leftJoinDays;
	}

	public String getFreeJoinDate() {
		return freeJoinDate;
	}

	public void setFreeJoinDate(String freeJoinDate) {
		this.freeJoinDate = freeJoinDate;
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
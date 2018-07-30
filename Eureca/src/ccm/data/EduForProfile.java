package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 프리랜서 검색에서 특정 프리랜서를 선택하면 프리랜서 프로필이 나오는데 그곳에 리스트로 뜨는 학력사항 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class EduForProfile implements DTO {

	// 출신 학교
	private String eduSchool;
	
	// 전공
	private String eduMajor;
	
	// 입학년월일
	private String schoolJoinDate;
	
	// 졸업년월일
	private String schoolGraduatedDate;

	/**
	 * 기본생성자
	 */
	public EduForProfile() {
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param eduSchool
	 * @param eduMajor
	 * @param schoolJoinDate
	 * @param schoolGraduatedDate
	 */
	public EduForProfile(String eduSchool, String eduMajor, String schoolJoinDate, String schoolGraduatedDate) {
		this.eduSchool = eduSchool;
		this.eduMajor = eduMajor;
		this.schoolJoinDate = schoolJoinDate;
		this.schoolGraduatedDate = schoolGraduatedDate;
	}

	/*********************************************  게터 세터  ******************************************************/
	public String getEduSchool() {
		return eduSchool;
	}

	public void setEduSchool(String eduSchool) {
		this.eduSchool = eduSchool;
	}

	public String getEduMajor() {
		return eduMajor;
	}

	public void setEduMajor(String eduMajor) {
		this.eduMajor = eduMajor;
	}

	public String getSchoolJoinDate() {
		return schoolJoinDate;
	}

	public void setSchoolJoinDate(String schoolJoinDate) {
		this.schoolJoinDate = schoolJoinDate;
	}

	public String getSchoolGraduatedDate() {
		return schoolGraduatedDate;
	}

	public void setSchoolGraduatedDate(String schoolGraduatedDate) {
		this.schoolGraduatedDate = schoolGraduatedDate;
	}

	/**
	 * ResultSet 객체에서 나온 값들을
	 * 이 클래스 객체에 세팅해주는 메소드 
	 */
	public void setParams(ResultSet rs) throws SQLException {
		eduSchool = rs.getString("EDUSCHOOL");
		eduMajor = rs.getString("EDUMAJOR");
		schoolJoinDate = rs.getString("SCHOOLJOINDATE");
		schoolGraduatedDate = rs.getString("SCHOOLGRADUATEDDATE");
	}
}
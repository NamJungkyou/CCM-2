package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 프리랜서 프로필의 근무경력을 나타내는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class CareerForProfile implements DTO {
	
	// 근무 당시 소속회사
	private String careerCompany;
	
	// 근무기간
	private String careerTerm;
	
	// 직책
	private String careerPosition;
	
	// 역할
	private String careerJob;
	
	
	/**
	 * 기본생성자
	 */
	public CareerForProfile() {
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param careerCompany
	 * @param careerTerm
	 * @param careerPosition
	 * @param careerJob
	 */
	public CareerForProfile(String careerCompany, String careerTerm, String careerPosition, String careerJob) {
		this.careerCompany = careerCompany;
		this.careerTerm = careerTerm;
		this.careerPosition = careerPosition;
		this.careerJob = careerJob;
	}

	/*********************************    게터 세터          *****************************************/
	public String getCareerCompany() {
		return careerCompany;
	}

	public void setCareerCompany(String careerCompany) {
		this.careerCompany = careerCompany;
	}

	public String getCareerTerm() {
		return careerTerm;
	}

	public void setCareerTerm(String careerTerm) {
		this.careerTerm = careerTerm;
	}

	public String getCareerPosition() {
		return careerPosition;
	}

	public void setCareerPosition(String careerPosition) {
		this.careerPosition = careerPosition;
	}

	public String getCareerJob() {
		return careerJob;
	}

	public void setCareerJob(String careerJob) {
		this.careerJob = careerJob;
	}

	/**
	 * DAO에서 받아온 DB 데이터를 쉽게 세팅해주는 메소드
	 */
	public void setParams(ResultSet rs) throws SQLException {
		careerCompany = rs.getString("CAREERCOMPANY");
		careerTerm = rs.getString("CAREERTERM");
		careerPosition = rs.getString("CAREERPOSITION");
		careerJob = rs.getString("CAREERJOB");
	}
}
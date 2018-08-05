package ccm.data.table;

/**
 * DB에 생성된 Career 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class Career
{
	// 경력 번호
	private Integer careerNum;
	
	// 경력기간 내 근무 회사
	private String careerCompany;
	
	// 입사일
	private String companyJoinDate;
	
	// 퇴사일
	private String companyDropDate;
	
	// 직위
	private String careerPosition;
	
	// 담당 업무
	private String careerJob;
	
	// 프리랜서 아이디
	private String freeId;
	
	// 직원 아이디
	private String empId;


	/************************************  게터 세터  ****************************************/
	public Integer getCareerNum() {
		return careerNum;
	}
	public void setCareerNum(Integer careerNum) {
		this.careerNum = careerNum;
	}
	public String getCareerCompany() {
		return careerCompany;
	}
	public void setCareerCompany(String careerCompany) {
		this.careerCompany = careerCompany;
	}
	public String getCompanyJoinDate() {
		return companyJoinDate;
	}
	public void setCompanyJoinDate(String companyJoinDate) {
		this.companyJoinDate = companyJoinDate;
	}
	public String getCompanyDropDate() {
		return companyDropDate;
	}
	public void setCompanyDropDate(String companyDropDate) {
		this.companyDropDate = companyDropDate;
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
	public String getFreeId() {
		return freeId;
	}
	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
	// 객체 문자열 표현 메소드
	@Override
	public String toString() {
		return "Career [careerNum=" + careerNum + ", careerCompany=" + careerCompany + ", companyJoinDate="
				+ companyJoinDate + ", companyDropDate=" + companyDropDate + ", careerPosition=" + careerPosition
				+ ", careerJob=" + careerJob + ", freeId=" + freeId + ", empId=" + empId + "]";
	}
}

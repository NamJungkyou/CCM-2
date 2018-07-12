package ccm.data.table;


public class Career
{
/*	  CAREERNUM INT NOT NULL AUTO_INCREMENT, #경력번호
	  CAREERCOMPANY VARCHAR(15) NOT NULL, #근무회사
	  COMPANYJOINDATE DATETIME NOT NULL, #입사일
	  COMPANYDROPDATE DATETIME NULL, #퇴사일(예정일)
	  CAREERPOSITION VARCHAR(15) NOT NULL, #직위
	  CAREERJOB VARCHAR(15) NOT NULL, #담당업무
	  FREEID VARCHAR(15) NULL, #프리랜서아이디
	  EMPID VARCHAR(15) NULL, #직원아이디*/
	
	private Integer careerNum;
	private String careerCompany;
	private String companyJoinDate;
	private String companyDropDate;
	private String careerPosition;
	private String careerJob;
	private String freeId;
	private String empId;
	
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
	
	@Override
	public String toString() {
		return "Career [careerNum=" + careerNum + ", careerCompany=" + careerCompany + ", companyJoinDate="
				+ companyJoinDate + ", companyDropDate=" + companyDropDate + ", careerPosition=" + careerPosition
				+ ", careerJob=" + careerJob + ", freeId=" + freeId + ", empId=" + empId + "]";
	}
	
	
	
	

	
	
	
	
	

		
}

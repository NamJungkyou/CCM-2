package ccm.data.table;


public class Education
{
	/*	  EDUNUM INT NOT NULL AUTO_INCREMENT, #학력번호
	  EDUSCHOOL VARCHAR(15) NOT NULL, #학교명
	  EDUDEPLOMA VARCHAR(2) NULL, #학위
	  EDUMAJOR VARCHAR(15) NOT NULL, #전공
	  SCHOOLJOINDATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(), #입학일
	  SCHOOLGRADUATEDDATE DATETIME NULL, #졸업일(예정일)
	  FREEID VARCHAR(15) NULL, #프리랜서아이디
	  EMPID VARCHAR(15) NULL, #직원아이디*/
	private Integer eduNum;
	private String eduSchool;
	private String eduDeploma;
	private String eduMajor;
	private String schoolJoinDate;
	private String schoolGraduatedDate;
	private String freeId;
	private String EmpId;
	
	public Integer getEduNum() {
		return eduNum;
	}
	public void setEduNum(Integer eduNum) {
		this.eduNum = eduNum;
	}
	public String getEduSchool() {
		return eduSchool;
	}
	public void setEduSchool(String eduSchool) {
		this.eduSchool = eduSchool;
	}
	public String getEduDeploma() {
		return eduDeploma;
	}
	public void setEduDeploma(String eduDeploma) {
		this.eduDeploma = eduDeploma;
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
	public String getFreeId() {
		return freeId;
	}
	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}
	public String getEmpId() {
		return EmpId;
	}
	public void setEmpId(String empId) {
		EmpId = empId;
	}
	
	@Override
	public String toString() {
		return "Education [eduNum=" + eduNum + ", eduSchool=" + eduSchool + ", eduDeploma=" + eduDeploma + ", eduMajor="
				+ eduMajor + ", schoolJoinDate=" + schoolJoinDate + ", schoolGraduatedDate=" + schoolGraduatedDate
				+ ", freeId=" + freeId + ", EmpId=" + EmpId + "]";
	}
	
	
	
	

	
	
}

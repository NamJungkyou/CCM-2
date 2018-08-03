package ccm.data.table;

/**
 * DB에 생성된 Education 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class Education
{
	// 학력 번호
	private Integer eduNum;
	
	// 교육기관 이름
	private String eduSchool;
	
	// 학위
	private String eduDeploma;
	
	// 전공
	private String eduMajor;
	
	// 입학일
	private String schoolJoinDate;
	
	// 졸업일
	private String schoolGraduatedDate;
	
	// 프리랜서 아이디
	private String freeId;
	
	// 직원 아이디
	private String EmpId;
	
	/********************************  게터 세터  **********************************/
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
	
	
	// 객체 문자열 표현
	@Override
	public String toString() {
		return "Education [eduNum=" + eduNum + ", eduSchool=" + eduSchool + ", eduDeploma=" + eduDeploma + ", eduMajor="
				+ eduMajor + ", schoolJoinDate=" + schoolJoinDate + ", schoolGraduatedDate=" + schoolGraduatedDate
				+ ", freeId=" + freeId + ", EmpId=" + EmpId + "]";
	}
}

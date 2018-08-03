package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 생성된 Employee 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class Employee {
	
	// 직원 아이디
	private String empId;
	
	// 직원 패스워드
	private String empPw;
	
	// 직원 직급
	private String empDuty;
	
	// 이름
	private String empName;
	
	// 부서
	private String empDept;
	
	// 사진
	private String empPicture;
	
	// 입사일
	private String empJoinDate;
	
	// 퇴사일
	private String empDropDate;
	
	// 전화번호
	private String empPhone;
	
	// 이메일
	private String empEmail;
	
	// 생년월일
	private String empBirth;
	
	// 성별
	private Boolean empSex;
	
	// 혼인여부
	private Boolean empMarried;
	
	// 도로명주소
	private String roadAddrPart1;
	
	// 나머지주소
	private String addrDetail;
	
	// 계좌은행
	private String empBank;
	
	// 계좌이름
	private String empAccName;
	
	// 계좌번호
	private String empAccount;
	
	// 권한
	private Boolean empAuth;
	
	// 파일경로
	private String empFilePath;

	/**
	 * 기본 생성자
	 */
	public Employee() {
		super();
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param empId
	 * @param empPw
	 * @param empDuty
	 * @param empName
	 * @param empDept
	 * @param empPicture
	 * @param empJoinDate
	 * @param empDropDate
	 * @param empPhone
	 * @param empEmail
	 * @param empBirth
	 * @param empSex
	 * @param empMarried
	 * @param empFrontAddr
	 * @param empRearAddr
	 * @param empBank
	 * @param empAccName
	 * @param empAccount
	 * @param empAuth
	 * @param empFilePath
	 */
	public Employee(String empId, String empPw, String empDuty, String empName, String empDept, String empPicture,
			String empJoinDate, String empDropDate, String empPhone, String empEmail, String empBirth, Boolean empSex,
			Boolean empMarried, String empFrontAddr, String empRearAddr, String empBank, String empAccName,
			String empAccount, Boolean empAuth, String empFilePath) {
		super();
		this.empId = empId;
		this.empPw = empPw;
		this.empDuty = empDuty;
		this.empName = empName;
		this.empDept = empDept;
		this.empPicture = empPicture;
		this.empJoinDate = empJoinDate;
		this.empDropDate = empDropDate;
		this.empPhone = empPhone;
		this.empEmail = empEmail;
		this.empBirth = empBirth;
		this.empSex = empSex;
		this.empMarried = empMarried;
		this.roadAddrPart1 = empFrontAddr;
		this.addrDetail = empRearAddr;
		this.empBank = empBank;
		this.empAccName = empAccName;
		this.empAccount = empAccount;
		this.empAuth = empAuth;
		this.empFilePath = empFilePath;
	}

	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 * 
	 * @param paramResultSet
	 * @throws SQLException
	 */
	public void setParams(ResultSet rs) throws SQLException {
		this.empId = rs.getString("EMPID");
		this.empPw = rs.getString("EMPPW");
		this.empDuty = rs.getString("EMPDUTY");
		this.empName = rs.getString("EMPNAME");
		this.empDept = rs.getString("EMPDEPT");
		this.empPicture = rs.getString("EMPPICTURE");
		this.empJoinDate = rs.getString("EMPJOINDATE");
		this.empDropDate = rs.getString("EMPDROPDATE");
		this.empPhone = rs.getString("EMPPHONE");
		this.empEmail = rs.getString("EMPEMAIL");
		this.empBirth = rs.getString("EMPBIRTH");
		this.empSex = rs.getBoolean("EMPSEX");
		this.empMarried = rs.getBoolean("EMPMARRIED");
		this.roadAddrPart1 = rs.getString("EMPFRONTADDR");
		this.addrDetail = rs.getString("EMPREARADDR");
		this.empBank = rs.getString("EMPBANK");
		this.empAccName = rs.getString("EMPACCNAME");
		this.empAccount = rs.getString("EMPACCOUNT");
		this.empAuth = rs.getBoolean("EMPAUTH");
		this.empFilePath = rs.getString("EMPFILEPATH");
	}

	/*************************  게터 세터  ***************************/
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpPw() {
		return empPw;
	}

	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}

	public String getEmpDuty() {
		return empDuty;
	}

	public void setEmpDuty(String empDuty) {
		this.empDuty = empDuty;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public String getEmpPicture() {
		return empPicture;
	}

	public void setEmpPicture(String empPicture) {
		this.empPicture = empPicture;
	}

	public String getEmpJoinDate() {
		return empJoinDate;
	}

	public void setEmpJoinDate(String empJoinDate) {
		this.empJoinDate = empJoinDate;
	}

	public String getEmpDropDate() {
		return empDropDate;
	}

	public void setEmpDropDate(String empDropDate) {
		this.empDropDate = empDropDate;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpBirth() {
		return empBirth;
	}

	public void setEmpBirth(String empBirth) {
		this.empBirth = empBirth;
	}

	public Boolean getEmpSex() {
		return empSex;
	}

	public void setEmpSex(Boolean empSex) {
		this.empSex = empSex;
	}

	public Boolean getEmpMarried() {
		return empMarried;
	}

	public void setEmpMarried(Boolean empMarried) {
		this.empMarried = empMarried;
	}

	public String getEmpFrontAddr() {
		return roadAddrPart1;
	}

	public void setEmpFrontAddr(String empFrontAddr) {
		this.roadAddrPart1 = empFrontAddr;
	}

	public String getEmpRearAddr() {
		return addrDetail;
	}

	public void setEmpRearAddr(String empRearAddr) {
		this.addrDetail = empRearAddr;
	}

	public String getEmpBank() {
		return empBank;
	}

	public void setEmpBank(String empBank) {
		this.empBank = empBank;
	}

	public String getEmpAccName() {
		return empAccName;
	}

	public void setEmpAccName(String empAccName) {
		this.empAccName = empAccName;
	}

	public String getEmpAccount() {
		return empAccount;
	}

	public void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}

	public Boolean getEmpAuth() {
		return empAuth;
	}

	public void setEmpAuth(Boolean empAuth) {
		this.empAuth = empAuth;
	}
	
	public String getEmpFilePath() {
		return empFilePath;
	}
	
	public void setEmpFilePath(String empFilePath) {
		this.empFilePath = empFilePath;
	}
}
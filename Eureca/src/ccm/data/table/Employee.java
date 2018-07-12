package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

/***************************
 * 
 * 
 * 지권 지건 int형은 nullable이어야되니까 Integer 자료형으로 쓰도록
 * 
 * 작성자 :
 * 
 * 수정자 :
 * 
 * 수정일 :
 *
 *
 ***************************/

public class Employee {
	/*
	 * `EMPID` VARCHAR(15) NOT NULL COLLATE 'utf8mb4_unicode_ci', `EMPPW`
	 * VARCHAR(100) NOT NULL COLLATE 'utf8mb4_unicode_ci', `EMPDUTY` VARCHAR(10)
	 * NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci', `EMPNAME` VARCHAR(15)
	 * NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci', `EMPDEPT` VARCHAR(5) NULL
	 * DEFAULT NULL COLLATE 'utf8mb4_unicode_ci', `EMPPICTURE` MEDIUMBLOB NULL,
	 * `EMPJOINDATE` DATETIME NOT NULL, `EMPDROPDATE` DATETIME NULL DEFAULT
	 * NULL, `EMPPHONE` VARCHAR(11) NULL DEFAULT NULL COLLATE
	 * 'utf8mb4_unicode_ci', `EMPEMAIL` VARCHAR(50) NULL DEFAULT NULL COLLATE
	 * 'utf8mb4_unicode_ci', `EMPBIRTH` DATETIME NULL DEFAULT NULL, `EMPSEX`
	 * TINYINT(1) NULL DEFAULT NULL, `EMPMARRIED` TINYINT(1) NULL DEFAULT NULL,
	 * `EMPFRONTADDR` VARCHAR(30) NULL DEFAULT NULL COLLATE
	 * 'utf8mb4_unicode_ci', `EMPREARADDR` VARCHAR(30) NULL DEFAULT NULL COLLATE
	 * 'utf8mb4_unicode_ci', `EMPBANK` VARCHAR(15) NULL DEFAULT NULL COLLATE
	 * 'utf8mb4_unicode_ci', `EMPACCNAME` VARCHAR(12) NULL DEFAULT NULL COLLATE
	 * 'utf8mb4_unicode_ci', `EMPACCOUNT` VARCHAR(30) NULL DEFAULT NULL COLLATE
	 * 'utf8mb4_unicode_ci', `EMPAUTH` INT(11) NOT NULL,
	 */
	private String empId;
	private String empPw;
	private String empDuty;
	private String empName;
	private String empDept;
	private String empPicture;
	private String empJoinDate;
	private String empDropDate;
	private String empPhone;
	private String empEmail;
	private String empBirth;
	private Boolean empSex;
	private Boolean empMarried;
	private String roadAddrPart1;
	private String addrDetail;
	private String empBank;
	private String empAccName;
	private String empAccount;
	private Boolean empAuth;
	private String empFilePath;

	public Employee() {
		super();
	}

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
package ccm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ccm.data.table.Career;
import ccm.data.table.Education;
import ccm.data.table.Employee;
import ccm.data.table.Freelancer;
import ccm.data.table.Interview;
import ccm.data.table.JoinFreelancerInterview_view;
import ccm.data.table.JoinFreelancerSkillInventory_view;
import ccm.data.table.JoinProj;
import ccm.util.DBManager;
/**
 * 사원관련 메소드가 저장된 EmployeeDAO
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmployeeDAO {
	private static EmployeeDAO instance = new EmployeeDAO();

	private EmployeeDAO() {
		super();
	}

	public static EmployeeDAO getInstance() {
		return instance;
	}

	public Employee EmpLogin(Employee loginEmp) {
		return loginEmp;
	}
	
	// 등록하려는 사원아이디가 기존에 존재하고 있는 아이디와 중복되는지 체크
	public int confirmID(String empId) {
		int result = -1;
		// 데이터베이스내에 저장된 empid값 중 where empid 값과 중복되는게 있는지 검색
		String sql = "select empid from employee where empid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId); // sql문에 empid=? 부분에 empId값을 받음
		
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 등록하려는 사원 휴대폰 번호가 중복되지 않는지 체크
	// jsp페이지에서 따로 중복검사를 해야할 필요는 일반적으로 없지만, empPhone는 DB에서 unique로 설정되어있어 값 입력테스트를 할 때 오류발생을 막고자 사용하기 위해 제작
	public int confirmPhone(String empPhone) {
		int result = -1;
		// 데이터베이스내에 저장된 empphone값 중 where empphone 값과 중복되는게 있는지 검색
		String sql = "select empphone from employee where empphone=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empPhone);
		
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 등록하려는 사원 이메일이 중복되지 않는지 체크
	// jsp페이지에서 따로 중복검사를 해야할 필요는 일반적으로 없지만, empEmail는 DB에서 unique로 설정되어있어 값 입력테스트를 할 때 오류발생을 막고자 사용하기 위해 제작
	public int confirmEmail(String empEmail) {
		int result = -1;
		// 데이터베이스내에 저장된 empemail값 중 where empemail 값과 중복되는게 있는지 검색
		String sql = "select empemail from employee where empemail=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empEmail);
		
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 사원 기본정보 조회(출력)
	public Employee showProfile(String Empid) {
		// employee 테이블에서 empId값을 기준으로 사원 기본정보 출력 sql문
		String sql = "select empId, empPw, empPicture, empName, empDept, empDuty, "
				+ "date_format(empJoinDate, '%Y-%m-%d') as empJoinDate, date_format(empDropDate, '%Y-%m-%d') as empDropDate, empAuth, date_format(empBirth, '%Y-%m-%d') as empBirth, empSex, "
				+ "empMarried, empPhone, empEmail, empFrontAddr, empRearAddr, "
				+ "empBank, empAccount, empAccName from Employee where empId=?";

		Employee eVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Empid); // sql문을 실행하는데 검색기준인 empid값을 넣어줌

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				
				
				eVo = new Employee();

				eVo.setEmpId(rs.getString("empId")); // 아이디
				eVo.setEmpPw(rs.getString("empPw")); // 비밀번호
				eVo.setEmpPicture(rs.getString("empPicture")); // 사진
				eVo.setEmpName(rs.getString("empName")); // 이름
				eVo.setEmpDept(rs.getString("empDept")); // 부서
				eVo.setEmpDuty(rs.getString("empDuty")); // 직책
				eVo.setEmpAuth(rs.getBoolean("empAuth")); // 관리권한			
				eVo.setEmpSex(rs.getBoolean("empSex")); // 성별
				eVo.setEmpMarried(rs.getBoolean("empMarried")); // 결혼여부
				eVo.setEmpPhone(rs.getString("empPhone")); // 전화번호
				eVo.setEmpEmail(rs.getString("empEmail")); // 이메일
				eVo.setEmpFrontAddr(rs.getString("empFrontAddr")); // 주소
				eVo.setEmpRearAddr(rs.getString("empRearAddr")); // 나머지주소
				eVo.setEmpBank(rs.getString("empBank")); // 은행명
				eVo.setEmpAccount(rs.getString("empAccount")); // 게좌번호
				eVo.setEmpAccName(rs.getString("empAccName")); // 계좌명의				
				eVo.setEmpBirth(rs.getString("empBirth")); // 생년월일
				eVo.setEmpJoinDate(rs.getString("empJoinDate")); // 입사일
				eVo.setEmpDropDate(rs.getString("empDropDate")); // 퇴사일

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return eVo;
	}
	
	// 프리랜서 기본정보 조회(출력)
	public Freelancer showFreelancerProfile(String freeId) {
		// freelancer 테이블에서 freeId값을 기준으로 프리랜서 기본정보 출력 sql문
		String sql = "select freeId, freeReviseDate, freeName, date_format(freeBirth, '%Y-%m-%d') as freeBirth, " 
				+ "freeKosa, freeSex, freeMarried, freeScore, freePhone, freeEmail, "
				+ "freeFrontAddr, freeRearAddr from Freelancer where freeId=?";

		Freelancer fVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				
				
				fVo = new Freelancer();
				
				fVo.setFreeId(rs.getString("freeId")); // 아이디
				fVo.setFreeReviseDate(rs.getTimestamp("freeRevDate")); // 정보수정날짜
				fVo.setFreeName(rs.getString("freeName")); // 이름
				fVo.setFreeBirth(rs.getString("freeBirth")); // 생년월일
				fVo.setFreeKosa(rs.getInt("freeKosa")); // 등급
				fVo.setFreeSex(rs.getBoolean("freeSex")); // 성별
				fVo.setFreeMarried(rs.getBoolean("freeMarried")); // 결혼여부
				fVo.setFreeScore(rs.getDouble("freeScore")); // 평점
				fVo.setFreePhone(rs.getString("freePhone")); // 휴대폰번호
				fVo.setFreeEmail(rs.getString("freeEmail")); // 이메일
				fVo.setFreeFrontAddr(rs.getString("freeFrontAddr")); // 주소
				fVo.setFreeRearAddr(rs.getString("freeRearAddr")); // 나머지 주소

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return fVo;
	}
	
	// 프리랜서 정보수정
	public void updateFreelancerProfile(Freelancer fVo) {
		// 정보수정을 위해 선택된 프리랜서 중 freeId를 기준으로 정보수정을 하는 sql문
		String sql = "update freelancer set freeName=?, freeBirth=date_format(?, '%Y-%m-%d'), "
				+ "freeKosa=?, freeSex=?, freeMarried=?, freeScore=?, freePhone=?, "
				+ "freeEmail=?, freeFrontAddr=?, freeRearAddr=?, freePic=?, freeFilePath=? where freeId=?";
		
		/*System.out.println("이메일이 들어가는지 테스트" + fVo.getFreeEmail().toString());
		System.out.println("이름이 들어가는지 테스트" + fVo.getFreeName().toString());*/
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fVo.getFreeName()); // 이름
			pstmt.setString(2, fVo.getFreeBirth()); // 생년월일
			pstmt.setInt(3, fVo.getFreeKosa()); // 등급
			pstmt.setInt(4, fVo.getFreeSex() == true ? 1 : 0); // 성별(boolean)
			pstmt.setInt(5, fVo.getFreeMarried() == true ? 1 : 0); // 결혼여부(boolean)
			pstmt.setDouble(6, fVo.getFreeScore()); // 평점(정확한 평점을 위해 소수점까지)
			pstmt.setString(7, fVo.getFreePhone()); // 휴대폰번호
			pstmt.setString(8, fVo.getFreeEmail()); // 이메일
			pstmt.setString(9, fVo.getFreeFrontAddr()); // 주소
			pstmt.setString(10, fVo.getFreeRearAddr()); // 나머지주소
			pstmt.setString(11, fVo.getFreePic()); // 사진
			pstmt.setString(12, fVo.getfreeFilePath()); // 사진경로
			pstmt.setString(13, fVo.getFreeId()); // 아이디
			
			pstmt.executeUpdate();
		}	catch (SQLException e) {
			e.printStackTrace();
		} 	finally {
			DBManager.close(conn, pstmt);
		}
	}

	
	// 프리랜서 기본정보 등록
	public void insertFreelancerProfile(Freelancer fVo) {
		// 프리랜서 기본정보 등록(아이디, 비밀번호, 이름 등)하는 sql문
		String sql = "insert into freelancer(freeId, freePw, freeName, freeBirth, freeSex, freePhone, "
				+ "freeEmail, freeMarried, freePic, freeFilePath, freeFrontAddr, freeRearAddr, freeBank, freeAccName, freeAccount) " 
				+ "values(?, ?, ?, date_format(?, '%Y-%m-%d'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";			
		
		/* System.out.println("이메일이 들어가는지 테스트" + fVo.getFreeEmail().toString());
		 * System.out.println("이름이 들어가는지 테스트" + fVo.getFreeName().toString()); */

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fVo.getFreeId()); // 아이디
			pstmt.setString(2, fVo.getFreePw()); // 비밀번호
			pstmt.setString(3, fVo.getFreeName()); // 이름
			pstmt.setString(4, fVo.getFreeBirth()); // 생년월일	
			pstmt.setInt(5, fVo.getFreeSex() == true ? 1 : 0); // 성별(남자:true,1 여자:false,0)
			pstmt.setString(6, fVo.getFreePhone()); // 전화번호
			/*pstmt.setString(7, eVo.getEmpDropDate());*/ // 등록할때는 퇴사일 입력이 필요없음
			pstmt.setString(7, fVo.getFreeEmail()); // 이메일
			pstmt.setInt(8, fVo.getFreeMarried() == true ? 1 : 0); // 결혼여부(기혼:true,1 여자:false,0)
			pstmt.setString(9, fVo.getFreePic()); // 사진
			pstmt.setString(10, fVo.getfreeFilePath()); // 사진저장경로
			pstmt.setString(11, fVo.getFreeFrontAddr()); // 주소
			pstmt.setString(12, fVo.getFreeRearAddr()); // 나머지주소
			pstmt.setString(13, fVo.getFreeBank()); // 은행명
			pstmt.setString(14, fVo.getFreeAccName()); // 계좌명의
			pstmt.setString(15, fVo.getFreeAccount()); // 계좌번호

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 사원계정 추가정보 등록 및 수정(로그인한 사원계정)
	public void updateEmployee(Employee eVo) {
		// empDropDate(퇴사일)값이 입력됬을때 실행되는 sql문
		String sql = "update employee set empName=?, empPw=?, empDept=?, empDuty=?, "
				+ "empJoinDate=date_format(?, '%Y-%m-%d'), empDropDate=date_format(?, '%Y-%m-%d'), empPicture=?, empFilePath=?, empAuth=?, empBirth=?, "
				+ "empSex=?, empMarried=?, empPhone=?, empEmail=?, empFrontAddr=?, empRearAddr=?, empBank=?, empAccName=?, empAccount=? where empId=?";
		// empDropDate(퇴사일)값이 null일 때, 실행되는 sql문
		// 위의 sql문과 달리 empDropDate값은 들어가지 않음
		if(eVo.getEmpDropDate() == null || eVo.getEmpDropDate().equals(""))
			sql = "update employee set empName=?, empPw=?, empDept=?, empDuty=?, "
					+ "empJoinDate=date_format(?, '%Y-%m-%d'), empPicture=?, empFilePath=?, empAuth=?, empBirth=?, "
					+ "empSex=?, empMarried=?, empPhone=?, empEmail=?, empFrontAddr=?, empRearAddr=?, empBank=?, empAccName=?, empAccount=? where empId=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			int i = 1;
			
			pstmt.setString(i++, eVo.getEmpName()); // 이름
			pstmt.setString(i++, eVo.getEmpPw()); // 비밀번호
			pstmt.setString(i++, eVo.getEmpDept()); // 부서
			pstmt.setString(i++, eVo.getEmpDuty()); // 직책
			pstmt.setString(i++, eVo.getEmpJoinDate()); // 입사일
		
			// empDropDate(퇴사일)가 null값이 아닐 때, 값이 입력되면 DropDate값을 입력
			// 만일 값이 입력되지 않으면 그냥 넘어감
			if (eVo.getEmpDropDate() != null && !eVo.getEmpDropDate().equals(""))
			{
				pstmt.setString(i++, eVo.getEmpDropDate()); // 퇴사일
				System.out.println(i);
			}
			
			pstmt.setString(i++, eVo.getEmpPicture()); // 사진
			pstmt.setString(i++, eVo.getEmpFilePath()); // 사진저장경로		
			pstmt.setInt(i++, eVo.getEmpAuth() == true ? 1 : 0); // 사이트 관리권한(boolean)
			pstmt.setString(i++, eVo.getEmpBirth()); // 생년월일
			pstmt.setInt(i++, eVo.getEmpSex() == true ? 1 : 0); // 성별(boolean)
			pstmt.setInt(i++, eVo.getEmpMarried() == true ? 1 : 0); // 결혼여부(boolean)
			pstmt.setString(i++, eVo.getEmpPhone()); // 전화번호
			pstmt.setString(i++, eVo.getEmpEmail()); // 이메일
			pstmt.setString(i++, eVo.getEmpFrontAddr()); // 주소
			pstmt.setString(i++, eVo.getEmpRearAddr()); // 나머지주소
			pstmt.setString(i++, eVo.getEmpBank()); // 은행명
			pstmt.setString(i++, eVo.getEmpAccName()); // 계좌명의
			pstmt.setString(i++, eVo.getEmpAccount()); // 계좌번호
			pstmt.setString(i++, eVo.getEmpId()); // 아이디
			pstmt.executeUpdate();
		}	catch (SQLException e) {
			e.printStackTrace();
		} 	finally {
			DBManager.close(conn, pstmt);
		}
	}
		
	// 프리랜서 학력 등록에서 새로운 학력을 등록할 때 생성되는 학력번호
	public int getNewFreeEduNum() {
		int eduNum = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			// 기존에 있는 학력번호(순번)중 최대(Max)값을 검색하여 그 값에 +1을 하여 NewEduNum을 생성
			pstmt = conn.prepareStatement("SELECT MAX(EDUNUM) + 1 as NEWEDUNUM FROM EDUCATION");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// eduNum에 위에서 생성한 NewEduNum값을 넣어줌
				eduNum = rs.getInt("NEWEDUNUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return eduNum;
	}
	
	// 학력정보 추가등록 및 수정
	public void updateFreeEducation(ArrayList<Education> eVos) {
		// freeId를 기준으로 선택된 프리랜서의 학력정보를 추가등록하거나 수정하는 sql문 
		String sql = "INSERT INTO education (edunum, eduschool, edumajor, edudeploma, schooljoindate, schoolgraduateddate, freeid) VALUES (?, ?, ?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?) "
				+ "ON DUPLICATE KEY UPDATE edunum=?, eduschool=?, edumajor=?, edudeploma=?, schooljoindate=date_format(?, '%Y-%m-%d'), schoolgraduateddate=date_format(?, '%Y-%m-%d'), freeid=?";

		// System.out.println("edunum이 들어가는지 테스트" + eVos.get(getNewEduNum()).toString());
		// System.out.println("입학일이 들어가는지 테스트" + eVo.getSchoolJoinDate().toString());

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			for (Education e : eVos) {
				pstmt = conn.prepareStatement(sql);
				// Insert 구문이 실행될때 들어가는 값
				pstmt.setInt(1, e.getEduNum()); // 학력번호(순번)
				pstmt.setString(2, e.getEduSchool()); // 학교명
				pstmt.setString(3, e.getEduMajor()); // 전공
				pstmt.setString(4, e.getEduDeploma()); // 학위
				pstmt.setString(5, e.getSchoolJoinDate()); // 입학일
				pstmt.setString(6, e.getSchoolGraduatedDate()); // 졸업일
				pstmt.setString(7, e.getFreeId()); // 아이디
				// Update 구문이 실행될때 들어가는 값
				pstmt.setInt(8, e.getEduNum()); // 학력번호(순번)
				pstmt.setString(9, e.getEduSchool()); // 학교명
				pstmt.setString(10, e.getEduMajor()); // 전공
				pstmt.setString(11, e.getEduDeploma()); // 학위
				pstmt.setString(12, e.getSchoolJoinDate()); // 입학일
				pstmt.setString(13, e.getSchoolGraduatedDate()); // 졸업일
				pstmt.setString(14, e.getFreeId()); // 아이디

				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, null);
		}
	}
	
	// 새로운 경력번호 생성
	public int getNewFreeCareerNum() {
		int careerNum = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			// career테이블에서 현재 존재하는 careerNum 값 중 최대값(max)을 검색하고 그 값에 +1 하여 newCareerNum생성
			pstmt = conn.prepareStatement("SELECT MAX(CAREERNUM) + 1 as NEWCAREERNUM FROM CAREER");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// careerNum에 새롭게 생성된 newCareerNum입력
				careerNum = rs.getInt("NEWCAREERNUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return careerNum;
	}
	
	// 프리랜서 경력 추가등록 및 수정
	public void updateFreeCareer(ArrayList<Career> cVos) {
		// freeId를 기준으로 선택된 프리랜서의 경력정보를 추가등록하거나 수정하는 sql문  
		String sql = "INSERT INTO career (careernum, careercompany, companyjoindate, companydropdate, careerposition, careerjob, freeid) VALUES (?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?, ?, ?) "
					+ "ON DUPLICATE KEY UPDATE careernum=?, careercompany=?, companyjoindate=date_format(?, '%Y-%m-%d'), companydropdate=date_format(?, '%Y-%m-%d'), careerposition=?, careerjob=?, freeid=?";
		
		/*System.out.println("프리랜서 경력정보가 들어가는지 테스트" +cVos.toString());*/
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DBManager.getConnection();
			
			for(Career c : cVos) {
				pstmt = conn.prepareStatement(sql);			
				// Insert 구문이 실행될때 들어가는 값
				pstmt.setInt(1, c.getCareerNum()); // 경력번호(순번)
				pstmt.setString(2, c.getCareerCompany()); // 회사명
				pstmt.setString(3, c.getCompanyJoinDate()); // 입사일
				pstmt.setString(4, c.getCompanyDropDate()); // 퇴사일
				pstmt.setString(5, c.getCareerPosition()); // 직책
				pstmt.setString(6, c.getCareerJob()); // 회사내 역할
				pstmt.setString(7, c.getFreeId());  // 아이디 
				// Update 구문이 실행될때 들어가는 값
				pstmt.setInt(8, c.getCareerNum()); // 경력번호(순번)
				pstmt.setString(9, c.getCareerCompany()); // 회사명
				pstmt.setString(10, c.getCompanyJoinDate()); // 입사일
				pstmt.setString(11, c.getCompanyDropDate()); // 퇴사일
				pstmt.setString(12, c.getCareerPosition()); // 직책
				pstmt.setString(13, c.getCareerJob()); // 회사내 역할
				pstmt.setString(14, c.getFreeId()); // 아이디
				
				pstmt.executeUpdate();
				
				pstmt.close();			
			}			
		}	catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			DBManager.close(conn, null);
		}
	}
		
	// 사원계정등록
	   public void insertProfile(Employee eVo) {
		   // employee 테이블에 사원계정정보를 등록하는 sql문   
		   String sql = "insert into employee(empId, empPw, empName, empDept, empDuty, empJoinDate, empPicture, empFilePath, "
		            + "empAuth, empBirth, empSex, empMarried, empPhone, empEmail, empFrontAddr, empRearAddr, "
		            + "empBank, empAccName, empAccount) " 
		            + "values(?, ?, ?, ?, ?, date_format(?, '%Y-%m-%d'), ?, ?, ?, date_format(?, '%Y-%m-%d'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		            /*System.out.println("empBirth 값이 들어가는지 테스트" + eVo.getEmpBirth().toString());
		            System.out.println("empJoinDate 값이 들어가는지 테스트" + eVo.getEmpJoinDate().toString());*/		            

		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      try {
		         conn = DBManager.getConnection();
		         pstmt = conn.prepareStatement(sql);
		         
		         pstmt.setString(1, eVo.getEmpId()); // 아이디
		         pstmt.setString(2, eVo.getEmpPw()); // 비밀번호
		         pstmt.setString(3, eVo.getEmpName()); // 이름
		         pstmt.setString(4, eVo.getEmpDept()); // 부서
		         pstmt.setString(5, eVo.getEmpDuty()); // 직책
		         pstmt.setString(6, eVo.getEmpJoinDate()); // 입사일
		         pstmt.setString(7, eVo.getEmpPicture()); // 사진
		         pstmt.setString(8, eVo.getEmpFilePath()); // 사진저장경로
		         
		         /*pstmt.setString(7, eVo.getEmpDropDate());*/
		         pstmt.setInt(9, eVo.getEmpAuth() == true ? 2 : 1); // 사이트내 관리권한
		         pstmt.setString(10, eVo.getEmpBirth()); // 생년월일
		         pstmt.setInt(11, eVo.getEmpSex() == true ? 1 : 0); // 성별(boolean)
		         pstmt.setInt(12, eVo.getEmpMarried() == true ? 1 : 0); // 결혼여부(boolean)
		         pstmt.setString(13, eVo.getEmpPhone()); // 전화번호
		         pstmt.setString(14, eVo.getEmpEmail()); // 이메일
		         pstmt.setString(15, eVo.getEmpFrontAddr()); // 주소
		         pstmt.setString(16, eVo.getEmpRearAddr()); // 나머지주소
		         pstmt.setString(17, eVo.getEmpBank()); // 은행명
		         pstmt.setString(18, eVo.getEmpAccName()); // 계좌명의
		         pstmt.setString(19, eVo.getEmpAccount()); // 계좌번호		 

		         pstmt.executeUpdate();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         DBManager.close(conn, pstmt);
		      }
		   }

		// 사원학력정보를 등록할 때, 새로운 학력을 등록하면 생성되는 학력번호   
		public int getNewEduNum() {
			int eduNum = -1;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = DBManager.getConnection();
				// 기존의 EduNum의 최대값(Max)를 검색하고, +1을 하여 새로운 newEduNum값을 생성
				pstmt = conn.prepareStatement("SELECT MAX(EDUNUM) + 1 as NEWEDUNUM FROM EDUCATION");

				rs = pstmt.executeQuery();
				while (rs.next()) {
					// eduNum에 위에서 생성된 newEduNum값을 입력
					eduNum = rs.getInt("NEWEDUNUM");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}

			return eduNum;
		}
		
		// 사원계정학력정보 조회(출력)
		public ArrayList<Education> showEducation(String empid) {
			// empid값을 기준으로 등록되어있는 학력정보를 education 테이블에서 schoolGraduatedDate(졸업일)순으로 출력하는 sql문
			String sql = "select eduNum, eduSchool, eduMajor, eduDeploma, date_format(schoolJoinDate, '%Y-%m-%d') as schoolJoinDate, date_format(schoolGraduatedDate, '%Y-%m-%d') as schoolGraduatedDate from education where empId=? order by schoolGraduatedDate asc";
			
			ArrayList<Education> eVo = null;
			eVo = new ArrayList<Education>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empid); // sql문에 있는 empid=?에 empid값을 입력
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					Education edu = new Education();
					edu.setEduNum(rs.getInt("eduNum")); // 학력번호
					edu.setEduSchool(rs.getString("eduSchool")); // 학교
					edu.setEduMajor(rs.getString("eduMajor")); // 전공
					edu.setEduDeploma(rs.getString("eduDeploma")); // 학위
					edu.setSchoolJoinDate(rs.getString("schoolJoinDate")); // 입학일
					edu.setSchoolGraduatedDate(rs.getString("schoolGraduatedDate")); // 졸업일
					
					eVo.add(edu);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return eVo;
		}
		
		// 사원계정 학력정보 등록 및 수정
		public void updateEducation(ArrayList<Education> eVos) {
			// 사용자가 입력한 정보 중 eduNum을 기준으로 education 테이블에 eduNum이 존재하면 update(수정)를 하고, 존재하지 않으면 insert(등록)을 하는 sql문
			// insert가 실행될때는 on duplicate 전까지의 구문이 실행되고, update는 on duplicate 뒤의 구문이 실행
			String sql = "INSERT INTO education (edunum, eduschool, edumajor, edudeploma, schooljoindate, schoolgraduateddate, empid) VALUES (?, ?, ?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?) "
					+ "ON DUPLICATE KEY UPDATE edunum=?, eduschool=?, edumajor=?, edudeploma=?, schooljoindate=date_format(?, '%Y-%m-%d'), schoolgraduateddate=date_format(?, '%Y-%m-%d'), empid=?";
			
			/*System.out.println("학력이 제대로 들어가는지 테스트" + eVos.toString());*/
			// System.out.println("널값인지 아닌지 테스트" + eVos.get(getNewEduNum()).toString());
			// System.out.println("널값인지 아닌지 테스트" + eVo.getSchoolJoinDate().toString());

			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DBManager.getConnection();

				for (Education e : eVos) {
					pstmt = conn.prepareStatement(sql);
					
					// 새로운 eduNum이 등록되어 insert
					pstmt.setInt(1, e.getEduNum()); // 학력번호
					pstmt.setString(2, e.getEduSchool()); // 학교
					pstmt.setString(3, e.getEduMajor()); // 전공
					pstmt.setString(4, e.getEduDeploma()); // 학위
					pstmt.setString(5, e.getSchoolJoinDate()); // 입학일
					pstmt.setString(6, e.getSchoolGraduatedDate()); // 졸업일
					pstmt.setString(7, e.getEmpId()); // 아이디
					
					// 기존에 존재하는 eduNum이 있어 update
					pstmt.setInt(8, e.getEduNum()); // 학력번호 
					pstmt.setString(9, e.getEduSchool()); // 학교
					pstmt.setString(10, e.getEduMajor()); // 전공
					pstmt.setString(11, e.getEduDeploma()); // 학위
					pstmt.setString(12, e.getSchoolJoinDate()); // 입학일
					pstmt.setString(13, e.getSchoolGraduatedDate()); // 졸업일
					pstmt.setString(14, e.getEmpId()); // 아이디

					pstmt.executeUpdate();

					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, null);
			}
		}

		// 사원경럭정보를 등록할 때, 새로운 경력을 등록하면 생성되는 경력번호
		public int getNewCareerNum() {
			int careerNum = -1;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = DBManager.getConnection();
				// career 테이블에서 기존의 careerNum중 최대값(max)를 검색하여 +1을 하고 newCareerNum을 출력
				pstmt = conn.prepareStatement("SELECT MAX(CAREERNUM) + 1 as NEWCAREERNUM FROM CAREER");

				rs = pstmt.executeQuery();
				while (rs.next()) {
					// 위에서 출력한 newCareerNum을 careerNum에 입력
					careerNum = rs.getInt("NEWCAREERNUM");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}

			return careerNum;
		}
	
		// 사원경력정보를 조회(출력)
		public ArrayList<Career> showCareer(String empid) {
			// career 테이블에서 경력정보를 empId 기준으로 검색하여 companyDropDate(퇴사일)순으로 출력하는 sql문
			String sql = "select careerNum, careerCompany, date_format(companyJoinDate, '%Y-%m-%d') as companyJoinDate, date_format(companyDropDate, '%Y-%m-%d') as companyDropDate, careerPosition, careerJob from career where empId=? order by companyDropDate asc";
			
			ArrayList<Career> cVo = null;
			cVo = new ArrayList<Career>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empid); // sql문에 있는 empid=?에 empid 값을 입력
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {

					Career car = new Career();
					car.setCareerNum(rs.getInt("careerNum")); // 경력번호
					car.setCareerCompany(rs.getString("careerCompany")); // 회사명
					car.setCompanyJoinDate(rs.getString("companyJoinDate")); // 입사일
					car.setCompanyDropDate(rs.getString("companyDropDate")); // 퇴사일
					car.setCareerPosition(rs.getString("careerPosition")); // 직책
					car.setCareerJob(rs.getString("careerJob")); // 회사내 역할
					
					cVo.add(car);						
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return cVo;
		}

		// 사원경력정보 등록 및 수정
		public void updateCareer(ArrayList<Career> cVos) {
			// careerNum을 기준으로 careerNum이 존재하면 update, 존재하지 않으면 insert하는 sql문
			String sql = "INSERT INTO career (careernum, careercompany, companyjoindate, companydropdate, careerposition, careerjob, empid) VALUES (?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?, ?, ?) "
						+ "ON DUPLICATE KEY UPDATE careernum=?, careercompany=?, companyjoindate=date_format(?, '%Y-%m-%d'), companydropdate=date_format(?, '%Y-%m-%d'), careerposition=?, careerjob=?, empid=?";
			
			/*System.out.println("경력정보가 제대로 들어가고 있는지 테스트" +cVos.toString());*/
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn=DBManager.getConnection();
				
				for(Career c : cVos) {
					pstmt = conn.prepareStatement(sql);
					
					// careerNum이 존재하지 않을 때 insert
					pstmt.setInt(1, c.getCareerNum()); // 경력번호
					pstmt.setString(2, c.getCareerCompany()); // 회사명
					pstmt.setString(3, c.getCompanyJoinDate()); // 입사일
					pstmt.setString(4, c.getCompanyDropDate()); // 퇴사일
					pstmt.setString(5, c.getCareerPosition()); // 직책
					pstmt.setString(6, c.getCareerJob()); // 회사내 역할
					pstmt.setString(7, c.getEmpId()); // 아이디
					
					// careerNum이 존재할 때 update
					pstmt.setInt(8, c.getCareerNum()); // 경력번호
					pstmt.setString(9, c.getCareerCompany()); // 회사명
					pstmt.setString(10, c.getCompanyJoinDate()); // 입사일
					pstmt.setString(11, c.getCompanyDropDate()); // 퇴사일
					pstmt.setString(12, c.getCareerPosition()); // 직책
					pstmt.setString(13, c.getCareerJob()); // 회사내 역할
					pstmt.setString(14, c.getEmpId()); // 아이디
					
					pstmt.executeUpdate();
					
					pstmt.close();			
				}			
			}	catch (SQLException e) {
				e.printStackTrace();
			}	finally {
				DBManager.close(conn, null);
			}
		}
	
		// 사원계정 삭제
		public void deleteEmployee(String empid) {
			// empId를 기준으로 employee 테이블에서 사원계정을 삭제하는 sql문
			String sql = "delete from employee where empId=?";
			 
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, empid); // sql문에서 empId=?에 empid 값을 입력
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	public List<Employee> selectAllEmp() {
		// 사원들을 전부 가져오는 메소드
		String sql = "select * from employee order by empid desc";

		List<Employee> list = new ArrayList<Employee>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Employee eVo = new Employee();

				eVo.setEmpId(rs.getString("empId"));
				eVo.setEmpPw(rs.getString("empPw"));
				eVo.setEmpDuty(rs.getString("empDuty"));
				eVo.setEmpName(rs.getString("empName"));
				eVo.setEmpDept(rs.getString("empDept"));
				eVo.setEmpPicture(rs.getString("empPicture"));
				eVo.setEmpJoinDate(rs.getString("empJoindate"));
				eVo.setEmpDropDate(rs.getString("empDropdate"));
				eVo.setEmpPhone(rs.getString("empPhone"));
				eVo.setEmpEmail(rs.getString("empEmail"));
				eVo.setEmpBirth(rs.getString("empBirth"));
				eVo.setEmpSex(rs.getBoolean("empSex"));
				eVo.setEmpMarried(rs.getBoolean("empMarried"));
				eVo.setEmpFrontAddr(rs.getString("empFrontaddr"));
				eVo.setEmpRearAddr(rs.getString("empRearaddr"));
				eVo.setEmpBank(rs.getString("empBank"));
				eVo.setEmpAccName(rs.getString("empAccName"));
				eVo.setEmpAccount(rs.getString("empAccount"));
				
				list.add(eVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	// joinFreelancerSkillInventory뷰에 참여번호(joinNum)로 접근하여 참여상태(freeState)를 업데이트 하는 메소드
	public JoinProj updateFreeStateByJoinNum(String joinNo, String freeState) {
		String sql = "update joinProj set freeState = ? where joinNum = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, freeState);
			pstmt.setString(2, joinNo);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;

	}
	
	public Interview selectOneInterviewByFreeId(String freeId) {
		// 프리랜서 번호로 면접에서 프리랜서의 면접정보를 가져온다.
		String sql = "select * from interview where freeId = ?";

		Interview iVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, freeId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				iVo = new Interview();

				iVo.setParams(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return iVo;
	}
	
	// joinFreelancerInterview 뷰에 joinNum으로 접근하여 면접상태를 1(면접중)로 업데이트 하는 메소드
	public JoinFreelancerInterview_view updateInterviewStateByJoinNum(String joinNum) {
		String sql = "update joinFreelancerInterview "
				+ "set interviewState = 1  where joinNum = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, joinNum);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;

	}
	
	// 인터뷰 한 결과에 대한 사유와 면접상태를 2(면접완료)로 업데이트 하는  메소드
	public Interview updateInterviewReasonByinterviewNo(String no, String interviewReason) {
		String sql = "update interview set nothireReason = ?, interviewState='2'"
				+ "where interviewNum = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, interviewReason);
			pstmt.setString(2, no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;

	}
	
	public Interview updateInterviewStateByfreeId(String freeId, String location, String date) {
		// joinFreelancerInterview 내부의 데이터를 참여번호로 찾아 면접상태와 면접장소를 업데이트 하는 메소드
		String sql = "update interview "
				+ "set interviewState = 1, interviewLocation = ?, interviewDate = ?  where freeId = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, location);
			pstmt.setString(2, date);
			pstmt.setString(3, freeId);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;

	}
	
	
	
	// joinFreelancerInterview 뷰에 접근하여 상태(freeState)가 접수완료인 필드를 가져오는 메소드
	public List<JoinFreelancerInterview_view> selectJoinFreeInterview() {
		String sql = "select * from joinFreeLancerInterview where freeState=" +
					" '접수완료' or freeState = '보류' order by joinNum";

		List<JoinFreelancerInterview_view> list = new ArrayList<JoinFreelancerInterview_view>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			System.out.println("-----------------------------");
			System.out.println(rs);
			System.out.println("-----------------------------");
			
			while (rs.next()) {
				JoinFreelancerInterview_view jVo = new JoinFreelancerInterview_view();
				
				System.out.println("-----------------------------");
				System.out.println("joinFreelancerInterview_view에 값 삽입 시작");
				System.out.println("-----------------------------");
				
				jVo.setFreeId(rs.getString("freeId"));
				jVo.setFreeName(rs.getString("freeName"));
				jVo.setInterviewNum(rs.getString("interviewNum"));
				jVo.setInterviewDate(rs.getDate("interviewDate"));
				jVo.setInterviewLocation(rs.getString("interviewLocation"));
				jVo.setInterviewState(rs.getString("interviewState"));
				jVo.setJoinNum(rs.getString("joinNum"));
				jVo.setFreeState(rs.getString("freeState"));
				
				System.out.println("-----------------------------");
				System.out.println("joinFreelancerInterview_view에 값 삽입 끝");
				System.out.println("-----------------------------");
				
				list.add(jVo);

				System.out.println("-----------------------------");
				System.out.println("리스트에 추가");
				System.out.println("-----------------------------");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}

		return list;
	}


	public List<JoinFreelancerSkillInventory_view> selectAllJoinFreeSkillInventory() {
		// joinFreelancerSkillInventory뷰에 joinNum으로 접근하여 null이 아닌 데이터를 가져오는 메소드
		String sql = "select * from joinFreelancerSkillInventory "
				+ "where joinNum is not null and freeState = '대기중' order by joinNum";

		List<JoinFreelancerSkillInventory_view> list = new ArrayList<JoinFreelancerSkillInventory_view>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				JoinFreelancerSkillInventory_view jVo = new JoinFreelancerSkillInventory_view();

				jVo.setJoinNum(rs.getString("joinNum"));
				jVo.setFreeId(rs.getString("freeId"));
				jVo.setFreeName(rs.getString("freeName"));
				jVo.setFreeState(rs.getString("freeState"));
				jVo.setLanguages(rs.getString("languages"));
				jVo.setFrameworks(rs.getString("frameworks"));
				jVo.setCareerYear(rs.getString("projCareerYears"));
				jVo.setJoinProjTime(rs.getString("joinProjTime"));
				jVo.setFreeKosa(rs.getString("freeKosa"));
				jVo.setFreeScore(rs.getString("freeScore"));
				
				list.add(jVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	
	public JoinFreelancerInterview_view getJoinFreeInterviewByNo(String no) {
		// joinNum을 통해 joinProj의 데이터를 가져오는 메소드
		// TODO Auto-generated method stub
		String sql = "select * from joinFreelancerInterview where joinNum = ?";

		JoinFreelancerInterview_view jVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				jVo = new JoinFreelancerInterview_view();

				jVo.setParams(rs);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jVo;
	}
	
	public JoinFreelancerSkillInventory_view getJoinFreeSkillInventoryByNo(String no) {
		// joinNum을 통해 joinFreelancerSkillInventory의 데이터를 가져오는 메소드
		// TODO Auto-generated method stub
		String sql = "select * from joinFreelancerSkillInventory where joinNum = ?";

		JoinFreelancerSkillInventory_view jVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				jVo = new JoinFreelancerSkillInventory_view();

				jVo.setParams(rs);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jVo;
	}
	public void insertInterview(String freeId, String location, String date) {
		// 사원 데이터 삽입 작성 필요
		String sql = "insert into interview(interviewNum, interviewDate, interviewLocation, interviewState, freeId)"
				+ " VALUES(null, ?, ?, ?, ?)";
		System.out.println("면접 삽입 시작");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, date);
			pstmt.setString(2, location);
			pstmt.setString(3, "1");
			pstmt.setString(4, freeId);
			System.out.println("면접 삽입 진행중");	
			System.out.println(pstmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("면접 삽입 종료");
	}
	
	public Employee geteVo(String id) {

		Connection conn = null;
		String sql = "select * from employee where empid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee eVo = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				eVo = new Employee();

				eVo.setParams(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eVo;
	}
	
	public int getNewMassageCount(String empId) {
		String sql="select count(*) as newmessage from message where empreceiver=? and msgchecked=0";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int newMessageCount=0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			newMessageCount = rs.getInt("newmessage");
			}
			System.out.println("신규메세지 : " +newMessageCount);
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return newMessageCount;
	}
	
	public int getWaitingProjCount() {
		String sql="select count(*) as waitingproj from joinproj where freestate='접수대기'";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int waitingCount=0;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				waitingCount = rs.getInt("waitingproj");
			}
			System.out.println("접수대기 : " +waitingCount);
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, stmt, rs);
		}
		return waitingCount;
	}
	public int getDoingProjCount() {
		String sql="select count(*) as doingProj from project where projstate='진행중'";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int doingCount=0;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				doingCount = rs.getInt("doingProj");
			}
			System.out.println("접수대기 : " +doingCount);
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, stmt, rs);
		}
		return doingCount;
	}
}

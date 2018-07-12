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
	
	public int confirmID(String empId) {
		int result = -1;
		String sql = "select empid from employee where empid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
		
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
	
	public int confirmPhone(String empPhone) {
		int result = -1;
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
	
	public int confirmEmail(String empEmail) {
		int result = -1;
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

	public Employee showProfile(String Empid) {
		// 검색목록중 선택한 사람 아이디 기준으로 프로필 출력
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
			pstmt.setString(1, Empid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				
				
				eVo = new Employee();

				eVo.setEmpId(rs.getString("empId"));
				eVo.setEmpPw(rs.getString("empPw"));
				eVo.setEmpPicture(rs.getString("empPicture"));
				eVo.setEmpName(rs.getString("empName"));
				eVo.setEmpDept(rs.getString("empDept"));
				eVo.setEmpDuty(rs.getString("empDuty"));
				

				eVo.setEmpAuth(rs.getBoolean("empAuth"));
				
				eVo.setEmpSex(rs.getBoolean("empSex"));
				eVo.setEmpMarried(rs.getBoolean("empMarried"));
				eVo.setEmpPhone(rs.getString("empPhone"));
				eVo.setEmpEmail(rs.getString("empEmail"));
				eVo.setEmpFrontAddr(rs.getString("empFrontAddr"));
				eVo.setEmpRearAddr(rs.getString("empRearAddr"));
				eVo.setEmpBank(rs.getString("empBank"));
				eVo.setEmpAccount(rs.getString("empAccount"));
				eVo.setEmpAccName(rs.getString("empAccName"));
				
				eVo.setEmpBirth(rs.getString("empBirth"));
				eVo.setEmpJoinDate(rs.getString("empJoinDate"));
				eVo.setEmpDropDate(rs.getString("empDropDate"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return eVo;
	}
	
	public Freelancer showFreelancerProfile(String freeId) {
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
				
				fVo.setFreeId(rs.getString("freeId"));
				fVo.setFreeReviseDate(rs.getTimestamp("freeRevDate"));
				fVo.setFreeName(rs.getString("freeName"));
				fVo.setFreeBirth(rs.getString("freeBirth"));
				fVo.setFreeKosa(rs.getInt("freeKosa"));
				fVo.setFreeSex(rs.getBoolean("freeSex"));
				fVo.setFreeMarried(rs.getBoolean("freeMarried"));
				fVo.setFreeScore(rs.getDouble("freeScore"));
				fVo.setFreePhone(rs.getString("freePhone"));
				fVo.setFreeEmail(rs.getString("freeEmail"));
				fVo.setFreeFrontAddr(rs.getString("freeFrontAddr"));
				fVo.setFreeRearAddr(rs.getString("freeRearAddr"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return fVo;
	}
	
	
	public void updateFreelancerProfile(Freelancer fVo) {
		String sql = "update freelancer set freeName=?, freeBirth=date_format(?, '%Y-%m-%d'), "
				+ "freeKosa=?, freeSex=?, freeMarried=?, freeScore=?, freePhone=?, "
				+ "freeEmail=?, freeFrontAddr=?, freeRearAddr=?, freePic=?, freeFilePath=? where freeId=?";
		
		/*System.out.println(fVo.getFreeEmail().toString());
		System.out.println(fVo.getFreeName().toString());*/
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fVo.getFreeName());
			pstmt.setString(2, fVo.getFreeBirth());
			pstmt.setInt(3, fVo.getFreeKosa());
			pstmt.setInt(4, fVo.getFreeSex() == true ? 1 : 0);
			pstmt.setInt(5, fVo.getFreeMarried() == true ? 1 : 0);
			pstmt.setDouble(6, fVo.getFreeScore());
			pstmt.setString(7, fVo.getFreePhone());
			pstmt.setString(8, fVo.getFreeEmail());
			pstmt.setString(9, fVo.getFreeFrontAddr());
			pstmt.setString(10, fVo.getFreeRearAddr());
			pstmt.setString(11, fVo.getFreePic());
			pstmt.setString(12, fVo.getfreeFilePath());
			pstmt.setString(13, fVo.getFreeId());
			
			pstmt.executeUpdate();
		}	catch (SQLException e) {
			e.printStackTrace();
		} 	finally {
			DBManager.close(conn, pstmt);
		}
	}

	
	public void insertFreelancerProfile(Freelancer fVo) {
		String sql = "insert into freelancer(freeId, freePw, freeName, freeBirth, freeSex, freePhone, "
				+ "freeEmail, freeMarried, freePic, freeFilePath, freeFrontAddr, freeRearAddr, freeBank, freeAccName, freeAccount) " 
				+ "values(?, ?, ?, date_format(?, '%Y-%m-%d'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
				/*System.out.println(eVo.getEmpBirth().toString());
				System.out.println(eVo.getEmpJoinDate().toString());*/
				
		/*
		 * System.out.println(fVo.getFreeEmail().toString());
		 * System.out.println(fVo.getFreeName().toString());
		 */

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fVo.getFreeId());
			pstmt.setString(2, fVo.getFreePw());
			pstmt.setString(3, fVo.getFreeName());
			pstmt.setString(4, fVo.getFreeBirth());			
			pstmt.setInt(5, fVo.getFreeSex() == true ? 1 : 0);
			pstmt.setString(6, fVo.getFreePhone());
			/*pstmt.setString(7, eVo.getEmpDropDate());*/
			pstmt.setString(7, fVo.getFreeEmail());
			pstmt.setInt(8, fVo.getFreeMarried() == true ? 1 : 0);
			pstmt.setString(9, fVo.getFreePic());
			pstmt.setString(10, fVo.getfreeFilePath());
			pstmt.setString(11, fVo.getFreeFrontAddr());
			pstmt.setString(12, fVo.getFreeRearAddr());
			pstmt.setString(13, fVo.getFreeBank());
			pstmt.setString(14, fVo.getFreeAccName());
			pstmt.setString(15, fVo.getFreeAccount());


			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public void updateEmployee(Employee eVo) {
/*		System.out.println(eVo.getEmpDropDate().toString() + "이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트"
				+ "이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트이엠피드랍데이트");*/
		String sql = "update employee set empName=?, empPw=?, empDept=?, empDuty=?, "
				+ "empJoinDate=date_format(?, '%Y-%m-%d'), empDropDate=date_format(?, '%Y-%m-%d'), empPicture=?, empFilePath=?, empAuth=?, empBirth=?, "
				+ "empSex=?, empMarried=?, empPhone=?, empEmail=?, empFrontAddr=?, empRearAddr=?, empBank=?, empAccName=?, empAccount=? where empId=?";
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
			
			pstmt.setString(i++, eVo.getEmpName());
			pstmt.setString(i++, eVo.getEmpPw());
			pstmt.setString(i++, eVo.getEmpDept());
			pstmt.setString(i++, eVo.getEmpDuty());
			pstmt.setString(i++, eVo.getEmpJoinDate());
			
			/*String empDropDate = ("empDropDate");		
			if(empDropDate != null)
			{
			pstmt.setString(5, eVo.getEmpDropDate());
			}*/
			
			if (eVo.getEmpDropDate() != null && !eVo.getEmpDropDate().equals(""))
			{
				pstmt.setString(i++, eVo.getEmpDropDate());
				System.out.println(i);
			}
			
			pstmt.setString(i++, eVo.getEmpPicture());
			pstmt.setString(i++, eVo.getEmpFilePath());
			
			pstmt.setInt(i++, eVo.getEmpAuth() == true ? 1 : 0);
			pstmt.setString(i++, eVo.getEmpBirth());
			pstmt.setInt(i++, eVo.getEmpSex() == true ? 1 : 0);
			pstmt.setInt(i++, eVo.getEmpMarried() == true ? 1 : 0);
			pstmt.setString(i++, eVo.getEmpPhone());
			pstmt.setString(i++, eVo.getEmpEmail());
			pstmt.setString(i++, eVo.getEmpFrontAddr());
			pstmt.setString(i++, eVo.getEmpRearAddr());
			pstmt.setString(i++, eVo.getEmpBank());
			pstmt.setString(i++, eVo.getEmpAccName());
			pstmt.setString(i++, eVo.getEmpAccount());
			pstmt.setString(i++, eVo.getEmpId());
//			System.out.println("피에스티엠티" + pstmt);
			pstmt.executeUpdate();
		}	catch (SQLException e) {
			e.printStackTrace();
		} 	finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	
	public int getNewFreeEduNum() {
		int eduNum = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("SELECT MAX(EDUNUM) + 1 as NEWEDUNUM FROM EDUCATION");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				eduNum = rs.getInt("NEWEDUNUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return eduNum;
	}
	
	
	public void updateFreeEducation(ArrayList<Education> eVos) {

		String sql = "INSERT INTO education (edunum, eduschool, edumajor, edudeploma, schooljoindate, schoolgraduateddate, freeid) VALUES (?, ?, ?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?) "
				+ "ON DUPLICATE KEY UPDATE edunum=?, eduschool=?, edumajor=?, edudeploma=?, schooljoindate=date_format(?, '%Y-%m-%d'), schoolgraduateddate=date_format(?, '%Y-%m-%d'), freeid=?";

		// System.out.println("느얼" + eVos.get(getNewEduNum()).toString());
		// System.out.println("느얼" + eVo.getSchoolJoinDate().toString());

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			for (Education e : eVos) {
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, e.getEduNum());
				pstmt.setString(2, e.getEduSchool());
				pstmt.setString(3, e.getEduMajor());
				pstmt.setString(4, e.getEduDeploma());
				pstmt.setString(5, e.getSchoolJoinDate());
				pstmt.setString(6, e.getSchoolGraduatedDate());
				pstmt.setString(7, e.getFreeId());
				pstmt.setInt(8, e.getEduNum());
				pstmt.setString(9, e.getEduSchool());
				pstmt.setString(10, e.getEduMajor());
				pstmt.setString(11, e.getEduDeploma());
				pstmt.setString(12, e.getSchoolJoinDate());
				pstmt.setString(13, e.getSchoolGraduatedDate());
				pstmt.setString(14, e.getFreeId());

				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, null);
		}
	}
	
	public int getNewFreeCareerNum() {
		int careerNum = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("SELECT MAX(CAREERNUM) + 1 as NEWCAREERNUM FROM CAREER");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				careerNum = rs.getInt("NEWCAREERNUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return careerNum;
	}
	

	public void updateFreeCareer(ArrayList<Career> cVos) {
		String sql = "INSERT INTO career (careernum, careercompany, companyjoindate, companydropdate, careerposition, careerjob, freeid) VALUES (?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?, ?, ?) "
					+ "ON DUPLICATE KEY UPDATE careernum=?, careercompany=?, companyjoindate=date_format(?, '%Y-%m-%d'), companydropdate=date_format(?, '%Y-%m-%d'), careerposition=?, careerjob=?, freeid=?";
		
		/*System.out.println("성공해라 제발 아니 진짜 들어가라" +cVos.toString());*/
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DBManager.getConnection();
			
			for(Career c : cVos) {
				pstmt = conn.prepareStatement(sql);
								
				pstmt.setInt(1, c.getCareerNum());
				pstmt.setString(2, c.getCareerCompany());
				pstmt.setString(3, c.getCompanyJoinDate());
				pstmt.setString(4, c.getCompanyDropDate());
				pstmt.setString(5, c.getCareerPosition());
				pstmt.setString(6, c.getCareerJob());
				pstmt.setString(7, c.getFreeId());
				pstmt.setInt(8, c.getCareerNum());
				pstmt.setString(9, c.getCareerCompany());
				pstmt.setString(10, c.getCompanyJoinDate());
				pstmt.setString(11, c.getCompanyDropDate());
				pstmt.setString(12, c.getCareerPosition());
				pstmt.setString(13, c.getCareerJob());
				pstmt.setString(14, c.getFreeId());
				
				pstmt.executeUpdate();
				
				pstmt.close();			
			}			
		}	catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			DBManager.close(conn, null);
		}
	}
	
	
	   public void insertProfile(Employee eVo) {
		      String sql = "insert into employee(empId, empPw, empName, empDept, empDuty, empJoinDate, empPicture, empFilePath, "
		            + "empAuth, empBirth, empSex, empMarried, empPhone, empEmail, empFrontAddr, empRearAddr, "
		            + "empBank, empAccName, empAccount) " 
		            + "values(?, ?, ?, ?, ?, date_format(?, '%Y-%m-%d'), ?, ?, ?, date_format(?, '%Y-%m-%d'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		      /*String sql = "insert into employee (empid, emppw, empname, empdept, empduty, empjoindate, empdropdate, empauth, empbirth, empsex, empmarried, empphone, empemail, empfrontaddr, emprearaddr, empbank, empaccname, empaccount)" 
		            + "values(?, ?, ?, ?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?, date_format(?, '%Y-%m-%d'), ?, ?, ?, ?, ?, ?, ?, ?, ?)" 
		            + "on duplicate key update empid=?, empname=?, empdept=?, empduty=?, empjoindate=date_format(?, '%Y-%m-%d'), empdropdate=date_format(?, '%Y-%m-%d'), empauth=?, empbirth=date_format(?, '%Y-%m-%d'), empsex=?, empmarried=?, "
		            + "empphone=?, empemail=?, empfrontaddr=?, emprearaddr=?, empbank=?, empaccname=?, empaccount=?";*/
		            /*System.out.println(eVo.getEmpBirth().toString());
		            System.out.println(eVo.getEmpJoinDate().toString());*/
		            
		      /*
		       * System.out.println(fVo.getFreeEmail().toString());
		       * System.out.println(fVo.getFreeName().toString());
		       */

		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      try {
		         conn = DBManager.getConnection();
		         pstmt = conn.prepareStatement(sql);
		         
		         pstmt.setString(1, eVo.getEmpId());
		         pstmt.setString(2, eVo.getEmpPw());
		         pstmt.setString(3, eVo.getEmpName());
		         pstmt.setString(4, eVo.getEmpDept());
		         pstmt.setString(5, eVo.getEmpDuty());
		         pstmt.setString(6, eVo.getEmpJoinDate());
		         pstmt.setString(7, eVo.getEmpPicture());
		         pstmt.setString(8, eVo.getEmpFilePath());
		         
		         /*pstmt.setString(7, eVo.getEmpDropDate());*/
		         pstmt.setInt(9, eVo.getEmpAuth() == true ? 2 : 1);
		         pstmt.setString(10, eVo.getEmpBirth());
		         pstmt.setInt(11, eVo.getEmpSex() == true ? 1 : 0);
		         pstmt.setInt(12, eVo.getEmpMarried() == true ? 1 : 0);
		         pstmt.setString(13, eVo.getEmpPhone());
		         pstmt.setString(14, eVo.getEmpEmail());
		         pstmt.setString(15, eVo.getEmpFrontAddr());
		         pstmt.setString(16, eVo.getEmpRearAddr());
		         pstmt.setString(17, eVo.getEmpBank());
		         pstmt.setString(18, eVo.getEmpAccName());
		         pstmt.setString(19, eVo.getEmpAccount());
		         
		/*         pstmt.setString(19, eVo.getEmpId());
		         pstmt.setString(20, eVo.getEmpName());
		         pstmt.setString(21, eVo.getEmpDept());
		         pstmt.setString(22, eVo.getEmpDuty());
		         pstmt.setString(23, eVo.getEmpJoinDate());
		         pstmt.setString(24, eVo.getEmpDropDate());
		         pstmt.setInt(25, eVo.getEmpAuth() == true ? 2 : 1);
		         pstmt.setString(26, eVo.getEmpBirth());
		         pstmt.setInt(27, eVo.getEmpSex() == true ? 1 : 0);
		         pstmt.setInt(28, eVo.getEmpMarried() == true ? 1 : 0);
		         pstmt.setString(29, eVo.getEmpPhone());
		         pstmt.setString(30, eVo.getEmpEmail());
		         pstmt.setString(31, eVo.getEmpFrontAddr());
		         pstmt.setString(32, eVo.getEmpRearAddr());
		         pstmt.setString(33, eVo.getEmpBank());
		         pstmt.setString(34, eVo.getEmpAccName());
		         pstmt.setString(35, eVo.getEmpAccount());*/

		         pstmt.executeUpdate();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         DBManager.close(conn, pstmt);
		      }
		   }

	
/*	public Date stringToDateSchoolJoinDate(Education education) {
		String schoolJoinyear = education.getSchoolJoinDateyy();
		String schoolJoinmonth = education.getSchoolJoinDatemm();
		String schoolJoinday = education.getSchoolJoinDatedd();

		Date SchoolJoinDate = null;

		if (schoolJoinyear != null && schoolJoinmonth != null && schoolJoinday != null)
			SchoolJoinDate = Date.valueOf(schoolJoinyear + "-" + schoolJoinmonth + "-" + schoolJoinday);

		return SchoolJoinDate;
	}

	public Date stringToDateSchoolGraduatedDate(Education education) {
		String schoolGraduatedYear = education.getSchoolGraduatedDateyy();
		String schoolGraduatedMonth = education.getSchoolGraduatedDatemm();
		String schoolGraduatedDay = education.getSchoolGraduatedDatedd();

		Date SchoolGraduatedDate = null;

		if (schoolGraduatedYear != null && schoolGraduatedMonth != null && schoolGraduatedDay != null)
			SchoolGraduatedDate = Date
					.valueOf(schoolGraduatedYear + "-" + schoolGraduatedMonth + "-" + schoolGraduatedDay);

		return SchoolGraduatedDate;
	}*/

	public int getNewEduNum() {
		int eduNum = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("SELECT MAX(EDUNUM) + 1 as NEWEDUNUM FROM EDUCATION");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				eduNum = rs.getInt("NEWEDUNUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return eduNum;
	}
	
	public ArrayList<Education> showEducation(String empid) {
		String sql = "select eduNum, eduSchool, eduMajor, eduDeploma, date_format(schoolJoinDate, '%Y-%m-%d') as schoolJoinDate, date_format(schoolGraduatedDate, '%Y-%m-%d') as schoolGraduatedDate from education where empId=? order by schoolGraduatedDate asc";
		
		ArrayList<Education> eVo = null;
		eVo = new ArrayList<Education>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				/*String SchoolJoinDate = rs.getString("SchoolJoinDate").toString();
				String SchoolJoinDatesub = SchoolJoinDate.substring(0, 10);
				
				String SchoolGraduatedDate = rs.getString("SchoolGraduatedDate").toString();
				String SchoolGraduatedDatesub = SchoolGraduatedDate.substring(0, 10);*/
				
				Education edu = new Education();
				edu.setEduNum(rs.getInt("eduNum"));
				edu.setEduSchool(rs.getString("eduSchool"));
				edu.setEduMajor(rs.getString("eduMajor"));
				edu.setEduDeploma(rs.getString("eduDeploma"));
				edu.setSchoolJoinDate(rs.getString("schoolJoinDate"));
				edu.setSchoolGraduatedDate(rs.getString("schoolGraduatedDate"));
				
				eVo.add(edu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return eVo;
	}

	public void updateEducation(ArrayList<Education> eVos) {

		String sql = "INSERT INTO education (edunum, eduschool, edumajor, edudeploma, schooljoindate, schoolgraduateddate, empid) VALUES (?, ?, ?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?) "
				+ "ON DUPLICATE KEY UPDATE edunum=?, eduschool=?, edumajor=?, edudeploma=?, schooljoindate=date_format(?, '%Y-%m-%d'), schoolgraduateddate=date_format(?, '%Y-%m-%d'), empid=?";
		
		/*System.out.println("교육이는 들어가는지 안들어가는지 알슈가 읍네" + eVos.toString());*/
		// System.out.println("느얼" + eVos.get(getNewEduNum()).toString());
		// System.out.println("느얼" + eVo.getSchoolJoinDate().toString());

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			for (Education e : eVos) {
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, e.getEduNum());
				pstmt.setString(2, e.getEduSchool());
				pstmt.setString(3, e.getEduMajor());
				pstmt.setString(4, e.getEduDeploma());
				pstmt.setString(5, e.getSchoolJoinDate());
				pstmt.setString(6, e.getSchoolGraduatedDate());
				pstmt.setString(7, e.getEmpId());
				pstmt.setInt(8, e.getEduNum());
				pstmt.setString(9, e.getEduSchool());
				pstmt.setString(10, e.getEduMajor());
				pstmt.setString(11, e.getEduDeploma());
				pstmt.setString(12, e.getSchoolJoinDate());
				pstmt.setString(13, e.getSchoolGraduatedDate());
				pstmt.setString(14, e.getEmpId());

				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, null);
		}
	}

	public int getNewCareerNum() {
		int careerNum = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("SELECT MAX(CAREERNUM) + 1 as NEWCAREERNUM FROM CAREER");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				careerNum = rs.getInt("NEWCAREERNUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return careerNum;
	}
	
	public ArrayList<Career> showCareer(String empid) {
		String sql = "select careerNum, careerCompany, date_format(companyJoinDate, '%Y-%m-%d') as companyJoinDate, date_format(companyDropDate, '%Y-%m-%d') as companyDropDate, careerPosition, careerJob from career where empId=? order by companyDropDate asc";
		
		ArrayList<Career> cVo = null;
		cVo = new ArrayList<Career>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				

				/*String CompanyJoinDate = rs.getString("CompanyJoinDate").toString();
				String CompanyJoinDatesub = CompanyJoinDate.substring(0, 10);
				
				String CompanyDropDate = rs.getString("CompanyDropDate").toString();
				String CompanyDropDatesub = CompanyDropDate.substring(0, 10);*/
				
				
				Career car = new Career();
				car.setCareerNum(rs.getInt("careerNum"));
				car.setCareerCompany(rs.getString("careerCompany"));
				car.setCompanyJoinDate(rs.getString("companyJoinDate"));
				car.setCompanyDropDate(rs.getString("companyDropDate"));
				car.setCareerPosition(rs.getString("careerPosition"));
				car.setCareerJob(rs.getString("careerJob"));
				
				cVo.add(car);						
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return cVo;
	}

	public void updateCareer(ArrayList<Career> cVos) {
		String sql = "INSERT INTO career (careernum, careercompany, companyjoindate, companydropdate, careerposition, careerjob, empid) VALUES (?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?, ?, ?) "
					+ "ON DUPLICATE KEY UPDATE careernum=?, careercompany=?, companyjoindate=date_format(?, '%Y-%m-%d'), companydropdate=date_format(?, '%Y-%m-%d'), careerposition=?, careerjob=?, empid=?";
		
		/*System.out.println("성공해라 제발 아니 진짜 들어가라" +cVos.toString());*/
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DBManager.getConnection();
			
			for(Career c : cVos) {
				pstmt = conn.prepareStatement(sql);
								
				pstmt.setInt(1, c.getCareerNum());
				pstmt.setString(2, c.getCareerCompany());
				pstmt.setString(3, c.getCompanyJoinDate());
				pstmt.setString(4, c.getCompanyDropDate());
				pstmt.setString(5, c.getCareerPosition());
				pstmt.setString(6, c.getCareerJob());
				pstmt.setString(7, c.getEmpId());
				pstmt.setInt(8, c.getCareerNum());
				pstmt.setString(9, c.getCareerCompany());
				pstmt.setString(10, c.getCompanyJoinDate());
				pstmt.setString(11, c.getCompanyDropDate());
				pstmt.setString(12, c.getCareerPosition());
				pstmt.setString(13, c.getCareerJob());
				pstmt.setString(14, c.getEmpId());
				
				pstmt.executeUpdate();
				
				pstmt.close();			
			}			
		}	catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			DBManager.close(conn, null);
		}
	}
	
	public void deleteEmployee(String empid) {
		String sql = "delete from employee where empId=?";
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empid);
			
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
			// ?占쏙옙 占쏙옙 占쏙옙占쏙옙

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
		// TODO Auto-generated method stub

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

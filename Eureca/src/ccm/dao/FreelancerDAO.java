package ccm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ccm.data.table.Career;
import ccm.data.table.DBMS;
import ccm.data.table.Education;
import ccm.data.table.Framework;
import ccm.data.table.Freelancer;
import ccm.data.table.JoinProjectView;
import ccm.data.table.ProgLang;
import ccm.data.table.SkillInventory;
import ccm.util.DBManager;

public class FreelancerDAO {
	private static FreelancerDAO instance = new FreelancerDAO();

	private FreelancerDAO() {
		super();
	}

	public static FreelancerDAO getInstance() {
		return instance;
	}

	public Freelancer freeLogin(Freelancer loginFree) {
		return loginFree;
	}

	public int confirmID(String freeId) {
		int result = -1;
		String sql = "select freeid from freelancer where freeid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeId);

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
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int confirmEmail(String freeEmail) {
		int result = -1;
		String sql = "select freeemail from freelancer where freeemail=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeEmail);

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
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int confirmPhone(String freePhone) {
		int result = -1;
		String sql = "select freephone from freelancer where freephone=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freePhone);

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
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Freelancer showProfile(String freeid) {
		// 로그인한 사람 아이디 기준으로 내프로필 출력
		String sql = "select freeId, freePw, freeEmail, freeName, freePic, "
				+ "date_format(freeBirth, '%Y-%m-%d') as freeBirth, freeSex, freePhone, freeMarried, freeFrontAddr, "
				+ "freeRearAddr from freelancer where freeId=?";

		Freelancer fVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeid);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				fVo = new Freelancer();

				fVo.setFreeId(rs.getString("freeId"));
				fVo.setFreePw(rs.getString("freePw"));
				fVo.setFreeEmail(rs.getString("freeEmail"));
				fVo.setFreeName(rs.getString("freeName"));
				fVo.setFreePic(rs.getString("freePic"));

				fVo.setFreeSex(rs.getBoolean("freeSex"));
				fVo.setFreePhone(rs.getString("freePhone"));
				fVo.setFreeMarried(rs.getBoolean("freeMarried"));
				fVo.setFreeFrontAddr(rs.getString("freeFrontAddr"));
				fVo.setFreeRearAddr(rs.getString("freeRearAddr"));
				fVo.setFreeBirth(rs.getString("freeBirth"));

				/*
				 * String freeBirth = rs.getString("freeBirth").toString(); String freeBirthsub
				 * = freeBirth.substring(0, 10);
				 * 
				 * fVo.setFreeBirth(freeBirthsub);
				 */

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return fVo;
	}

	public void updateProfile(Freelancer fVo) {
		String sql = "update freelancer set freeEmail=?, freePic=?, freeFilePath=?, freePw=?, freeName=?, "
				+ "freeBirth=date_format(?, '%Y-%m-%d'), freeSex=?, freeMarried=?, freePhone=?,  "
				+ "freeFrontAddr=?, freeRearAddr=? where freeId=?";

		// System.out.println(fVo.getFreeEmail().toString());
		// System.out.println(fVo.getFreeName().toString());

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, fVo.getFreeEmail());
			pstmt.setString(2, fVo.getFreePic());
			pstmt.setString(3, fVo.getfreeFilePath());
			pstmt.setString(4, fVo.getFreePw());
			pstmt.setString(5, fVo.getFreeName());
			pstmt.setString(6, fVo.getFreeBirth());
			pstmt.setInt(7, fVo.getFreeSex() == true ? 1 : 0);
			pstmt.setInt(8, fVo.getFreeMarried() == true ? 1 : 0);
			pstmt.setString(9, fVo.getFreePhone());
			pstmt.setString(10, fVo.getFreeFrontAddr());
			pstmt.setString(11, fVo.getFreeRearAddr());
			pstmt.setString(12, fVo.getFreeId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void updatePicture(Freelancer fVo) {
		String sql = "update freelancer set freePic=? where freeId=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, fVo.getFreePic());
			pstmt.setString(2, fVo.getFreeId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public Freelancer showAccount(String freeid) {
		// 로그인한 아이디 기준 계좌등록 화면 출력
		String sql = "select freeBank, freeAccName, freeAccount from freelancer where freeId=?";

		Freelancer fVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				fVo = new Freelancer();

				fVo.setFreeBank(rs.getString("freeBank"));
				fVo.setFreeAccName(rs.getString("freeAccName"));
				fVo.setFreeAccount(rs.getString("freeAccount"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return fVo;
	}

	public void updateAccount(Freelancer fVo) {
		String sql = "update freelancer set freeBank=?, freeAccName=?, " + "freeAccount=? where freeId=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, fVo.getFreeBank());
			pstmt.setString(2, fVo.getFreeAccName());
			pstmt.setString(3, fVo.getFreeAccount());
			pstmt.setString(4, fVo.getFreeId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

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

	public ArrayList<Education> showEducation(String freeid) {
		String sql = "select eduNum, eduSchool, eduMajor, eduDeploma, schoolJoinDate, schoolGraduatedDate from education where freeId=?";

		ArrayList<Education> eVo = null;
		eVo = new ArrayList<Education>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String SchoolJoinDate = rs.getString("SchoolJoinDate").toString();
				String SchoolJoinDatesub = SchoolJoinDate.substring(0, 10);

				String SchoolGraduatedDate = rs.getString("SchoolGraduatedDate").toString();
				String SchoolGraduatedDatesub = SchoolGraduatedDate.substring(0, 10);

				Education edu = new Education();
				edu.setEduNum(rs.getInt("eduNum"));
				edu.setEduSchool(rs.getString("eduSchool"));
				edu.setEduMajor(rs.getString("eduMajor"));
				edu.setEduDeploma(rs.getString("eduDeploma"));
				edu.setSchoolJoinDate(SchoolJoinDatesub);
				edu.setSchoolGraduatedDate(SchoolGraduatedDatesub);

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

	public ArrayList<Career> showCareer(String freeid) {
		String sql = "select careerNum, careerCompany, companyJoinDate, companyDropDate, careerPosition, careerJob from career where freeId=?";

		ArrayList<Career> cVo = null;
		cVo = new ArrayList<Career>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String CompanyJoinDate = rs.getString("CompanyJoinDate").toString();
				String CompanyJoinDatesub = CompanyJoinDate.substring(0, 10);

				String CompanyDropDate = rs.getString("CompanyDropDate").toString();
				String CompanyDropDatesub = CompanyDropDate.substring(0, 10);

				Career car = new Career();
				car.setCareerNum(rs.getInt("careerNum"));
				car.setCareerCompany(rs.getString("careerCompany"));
				car.setCompanyJoinDate(CompanyJoinDatesub);
				car.setCompanyDropDate(CompanyDropDatesub);
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
		String sql = "INSERT INTO career (careernum, careercompany, companyjoindate, companydropdate, careerposition, careerjob, freeid) VALUES (?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE careernum=?, careercompany=?, companyjoindate=date_format(?, '%Y-%m-%d'), companydropdate=date_format(?, '%Y-%m-%d'), careerposition=?, careerjob=?, freeid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			for (Career c : cVos) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, null);
		}
	}

	public int getNewProjNum() {
		int projNum = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("SELECT MAX(PROJNUM) + 1 as NEWPROJNUM FROM SKILLINVENTORY");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				projNum = rs.getInt("NEWPROJNUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return projNum;
	}

	public ArrayList<SkillInventory> showSkillInventoryPro(String freeid) {
		String projSql = "select * from skillinventoryproj where freeId=? order by joinprojdate asc";
		String dbSql = "select * from projdbmsname where projnum = ?";
		String frameSql = "select * from skillinventoryframe where freeId=? and projnum = ?";
		String langSql = "select * from skillinventorylang where freeId=? and projnum = ?";

		ArrayList<SkillInventory> skillInven = null;
		skillInven = new ArrayList<SkillInventory>();
		Connection conn = null;
		PreparedStatement dbPstmt = null;
		PreparedStatement projPstmt = null;
		PreparedStatement framePstmt = null;
		PreparedStatement langPstmt = null;
		ResultSet dbRs = null;
		ResultSet projRs = null;
		ResultSet frameRs = null;
		ResultSet langRs = null;

		try {
			conn = DBManager.getConnection();

			projPstmt = conn.prepareStatement(projSql);
			projPstmt.setString(1, freeid);

			projRs = projPstmt.executeQuery();

			while (projRs.next()) {
				SkillInventory inven = new SkillInventory();
				inven.setParams(projRs);
				System.out.println(inven.getFreeId());

				langPstmt = conn.prepareStatement(langSql);
				langPstmt.setString(1, inven.getFreeId());
				langPstmt.setInt(2, inven.getProjNum());
				langRs = langPstmt.executeQuery();
				while (langRs.next()) {
					ProgLang newLang = new ProgLang();
					newLang.setParams(langRs);
					inven.getProjlangs().add(newLang);
					System.out.println(newLang.getLangName());
				}
				if (langRs != null)
					langRs.close();
				if (langPstmt != null)
					langPstmt.close();

				framePstmt = conn.prepareStatement(frameSql);
				framePstmt.setString(1, inven.getFreeId());
				framePstmt.setInt(2, inven.getProjNum());
				frameRs = framePstmt.executeQuery();
				while (frameRs.next()) {
					Framework newFrame = new Framework();
					newFrame.setParams(frameRs);
					inven.getFrames().add(newFrame);
				}
				if (frameRs != null)
					frameRs.close();
				if (framePstmt != null)
					framePstmt.close();
				dbPstmt = conn.prepareStatement(dbSql);
				dbPstmt.setInt(1, inven.getProjNum());
				dbRs = dbPstmt.executeQuery();
				while (dbRs.next()) {
					DBMS newDBMS = new DBMS();
					newDBMS.setParams(dbRs);
					System.out.println("디비디비디비디비넘" + newDBMS.getDbNum());
					inven.setDb(newDBMS);
				}
				if (dbRs != null)
					dbRs.close();
				if (dbPstmt != null)
					dbPstmt.close();

				skillInven.add(inven);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, projPstmt, projRs);
		}
		return skillInven;
	}

	public void deleteFreelancer(String freeid) {
		String sql = "delete from freelancer where freeId=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, freeid);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertSkillInventory(List<SkillInventory> skillInvList, ArrayList<String[]> langNumList,
			ArrayList<String[]> frameNumList) {
		String projInsertSql = "insert into project (projname, isextern, dbNum, projcompany, projtarget) values (?, ?, ?, ?, ?)";
		String joinProjInsertSql = "insert into joinproj (projnum, freeid, projrole, joinprojdate, exitprojdate) values((select last_insert_id()), ?, ?, date_format(?,'%Y-%m-%d'), date_format(?,'%Y-%m-%d'))";
		String langSkillInsertSql = "insert into langskill(joinnum, langnum) values((select last_insert_id()), ?)";
		String langSkillInsert2Sql = "insert into langskill(joinnum, langnum) values((select lsk.joinnum from langskill lsk where langskillnum=(select last_insert_id())), ?)";
		String frameSkillInsertSql = "insert into frameworkskill(joinnum, framenum) values((select lsk.joinnum from langskill lsk where langskillnum=(select last_insert_id())), ?)";
		String frameSkillInsert2Sql = "insert into frameworkSkill(joinnum, framenum) values((select fmw.joinnum from frameworkskill fmw where frameskillnum=(select last_insert_id())),?)";
		String projUpdateSql = "update project set projname=?, dbNum=?, projcompany=?, projtarget=? where projnum=?";
		String joinProjUpdateSql = "update joinproj set projrole=?, joinprojdate=?, exitprojdate=? where joinnum=(select * from (select pjoin.joinnum from joinproj as pjoin where projnum=?)j)";
		String langSkillDeleteSql = "delete from langskill where joinnum=(select joinnum from joinproj where projnum=?)";
		String langSkillInsertSql2 = "insert into langskill(joinnum, langnum) values((select joinnum from joinproj where projnum=?), ?)";
		String frameSkillDeleteSql = "delete from frameworkskill where joinnum=(select joinnum from joinproj where projnum=?)";
		String frameSkillInsertSql2 = "insert into frameworkskill(joinnum, framenum) values((select joinnum from joinproj where projnum=?), ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			System.out.println("업데이트 메소드 실행");
			conn = DBManager.getConnection();
			for (int i = 0; i < skillInvList.size(); i++) {
				System.out.println("프로젝트 번호 : " + skillInvList.get(i).getProjNum() + " 외부프로젝트 여부 : "
						+ skillInvList.get(i).getIsExtern());
				if ((skillInvList.get(i).getProjNum() == 0) && (skillInvList.get(i).getIsExtern() == 1)) {
					System.out.println("프로젝트 등록 실행");
					pstmt = conn.prepareStatement(projInsertSql);
					pstmt.setString(1, skillInvList.get(i).getProjName());
					pstmt.setInt(2, skillInvList.get(i).getIsExtern());
					pstmt.setInt(3, skillInvList.get(i).getDbNum());
					pstmt.setString(4, skillInvList.get(i).getProjCompany());
					pstmt.setString(5, skillInvList.get(i).getProjTarget());

					pstmt.executeUpdate();
					pstmt.close();

					pstmt = conn.prepareStatement(joinProjInsertSql);

					pstmt.setString(1, skillInvList.get(i).getFreeId());
					pstmt.setString(2, skillInvList.get(i).getProjRole());
					pstmt.setString(3, skillInvList.get(i).getJoinProjDate());
					pstmt.setString(4, skillInvList.get(i).getExitProjDate());

					pstmt.executeUpdate();
					pstmt.close();

					if (langNumList.get(i) != null) {
						pstmt = conn.prepareStatement(langSkillInsertSql);
						pstmt.setInt(1, Integer.parseInt(langNumList.get(i)[0]));
						pstmt.executeUpdate();
						pstmt.close();
						System.out.println("등록 언어 : " + langNumList.get(i)[0]);

						for (int j = 1; j < langNumList.get(i).length; j++) {
							pstmt = conn.prepareStatement(langSkillInsert2Sql);
							pstmt.setInt(1, Integer.parseInt(langNumList.get(i)[j]));
							pstmt.executeUpdate();
							pstmt.close();
							System.out.println("등록 언어 : " + langNumList.get(i)[j]);
						}
					}
					if (frameNumList.get(i) != null) {
						pstmt = conn.prepareStatement(frameSkillInsertSql);
						pstmt.setInt(1, Integer.parseInt(frameNumList.get(i)[0]));
						pstmt.executeUpdate();
						pstmt.close();
						System.out.println("등록 프레임워크 : " + frameNumList.get(i)[0]);
						for (int j = 1; j < frameNumList.get(i).length; j++) {
							pstmt = conn.prepareStatement(frameSkillInsert2Sql);
							pstmt.setInt(1, Integer.parseInt(frameNumList.get(i)[j]));
							pstmt.executeUpdate();
							pstmt.close();
						}
					}
				} else if ((skillInvList.get(i).getProjNum() != 0) && (skillInvList.get(i).getIsExtern() == 1)) {
					System.out.println("프로젝트 업데이트 실행");
					pstmt = conn.prepareStatement(projUpdateSql);

					pstmt.setString(1, skillInvList.get(i).getProjName());
					pstmt.setInt(2, skillInvList.get(i).getDbNum());
					pstmt.setString(3, skillInvList.get(i).getProjCompany());
					pstmt.setString(4, skillInvList.get(i).getProjTarget());
					pstmt.setInt(5, skillInvList.get(i).getProjNum());

					pstmt.executeUpdate();
					pstmt.close();

					pstmt = conn.prepareStatement(joinProjUpdateSql);

					pstmt.setString(1, skillInvList.get(i).getProjRole());
					pstmt.setString(2, skillInvList.get(i).getJoinProjDate());
					pstmt.setString(3, skillInvList.get(i).getExitProjDate());
					pstmt.setInt(4, skillInvList.get(i).getProjNum());

					pstmt.executeUpdate();
					pstmt.close();

					if (langNumList.get(i) != null) {

						pstmt = conn.prepareStatement(langSkillDeleteSql);
						pstmt.setInt(1, skillInvList.get(i).getProjNum());
						pstmt.executeUpdate();
						pstmt.close();

						for (String temp : langNumList.get(i)) {
							pstmt = conn.prepareStatement(langSkillInsertSql2);
							pstmt.setInt(1, skillInvList.get(i).getProjNum());
							pstmt.setInt(2, Integer.parseInt(temp));
							pstmt.executeUpdate();
							pstmt.close();
						}
					}

					if (frameNumList.get(i) != null) {
						pstmt = conn.prepareStatement(frameSkillDeleteSql);
						pstmt.setInt(1, skillInvList.get(i).getProjNum());
						pstmt.executeUpdate();
						pstmt.close();

						for (String temp : frameNumList.get(i)) {
							pstmt = conn.prepareStatement(frameSkillInsertSql2);
							pstmt.setInt(1, skillInvList.get(i).getProjNum());
							pstmt.setInt(2, Integer.parseInt(temp));
							pstmt.executeUpdate();
							pstmt.close();
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn);
		}
	}

	public void freelancerJoinApply(String freeId, int projNum) {
		String sql = "insert into joinproj (freeid, projnum, applicationdate, freestate) values (?, ?, now(), '접수대기')";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			System.out.println("아이디 : " + freeId);
			System.out.println("프로젝트번호 : " + projNum);
			pstmt.setString(1, freeId);
			pstmt.setInt(2, projNum);

			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public String getFreeState(String freeId) {
		String sql = "select freestate from joinproj where freeid=? group by freestate";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<String> freeStateList = new ArrayList<String>();
		String freestate = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				freestate = rs.getString("freestate");

				freeStateList.add(freestate);
			}

			if (freeStateList.contains("참여중")) {
				freestate = "참여중";
			} else if (freeStateList.contains("접수완료")) {
				freestate = "접수완료";
			} else if (freeStateList.contains("접수대기")) {
				freestate = "접수대기";
			} else {
				freestate = "대기중";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return freestate;
	}

	public List<JoinProjectView> selectJoinProjectViewList(String freeId) {
		String sql = "select * from projoinfreeframelangcnt_view where projnum = ? group by projnum";
		List<JoinProjectView> list = new ArrayList<JoinProjectView>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			JoinProjectView proj = new JoinProjectView();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				proj.setProjNum(rs.getInt("projNum"));
				proj.setProjName(rs.getString("projName"));
				proj.setJoinFLCount(rs.getInt("joinFreeCount"));
				proj.setRequireCount(rs.getInt("requireCount"));
				proj.setProjState(rs.getString("projState"));
				proj.setProjStartDate(rs.getString("projStartDate"));
				proj.setJoinProjDate(rs.getDate("joinProjDate"));
//				proj.setExitProjDate(rs.getDate("exitProjDate"));
				proj.setProjPlan(rs.getString("projPlan"));

				list.add(proj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public int getNewMassageCount(String freeId) {
		String sql = "select count(*) as newmessage from message where freereceiver=? and msgchecked=0";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int newMessageCount = 0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				newMessageCount = rs.getInt("newmessage");
			}
			System.out.println("신규메세지 : " + newMessageCount);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return newMessageCount;
	}

	public Freelancer getfVo(String id) {

		Connection conn = null;
		String sql = "select * from freelancer where freeId=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Freelancer fVo = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				fVo = new Freelancer();

				fVo.setFreeId(rs.getString("freeId"));
				fVo.setFreePw(rs.getString("freePw"));
				fVo.setFreeName(rs.getString("freeName"));
				fVo.setFreePic(rs.getString("freePic"));
				fVo.setFreeBirth(rs.getString("freeBirth"));
				fVo.setFreeSex(rs.getBoolean("freeSex"));
				fVo.setFreePhone(rs.getString("freePhone"));
				fVo.setFreeEmail(rs.getString("freeEmail"));
				fVo.setFreeJoinDate(rs.getTimestamp("freeJoindate"));
				fVo.setFreeDropDate(rs.getTimestamp("freeDropDate"));
				fVo.setFreeClass(rs.getInt("freeClass"));
				fVo.setFreeKosa(rs.getInt("freeKosa"));
				fVo.setFreeMarried(rs.getBoolean("freeMarried"));
				fVo.setFreeFrontAddr(rs.getString("freeFrontaddr"));
				fVo.setFreeRearAddr(rs.getString("freeRearaddr"));
				fVo.setFreeBank(rs.getString("freeBank"));
				fVo.setFreeAccName(rs.getString("freeAccName"));
				fVo.setFreeAccount(rs.getString("freeAccount"));
				fVo.setFreeReviser(rs.getString("freeReviser"));
				fVo.setFreeReviseDate(rs.getTimestamp("freeReviseDate"));
				fVo.setFreeScore(rs.getInt("freeScore"));

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
		return fVo;
	}

	public List<Freelancer> selectAllFree() {
		String sql = "select * from freelancer order by freeId desc";

		List<Freelancer> list = new ArrayList<Freelancer>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Freelancer fVo = new Freelancer();

				fVo.setFreeId(rs.getString("freeId"));
				fVo.setFreePw(rs.getString("freePw"));
				fVo.setFreeName(rs.getString("freeName"));
				fVo.setFreePic(rs.getString("freePic"));
				fVo.setFreeBirth(rs.getString("freeBirth"));
				fVo.setFreeSex(rs.getBoolean("freeSex"));
				fVo.setFreePhone(rs.getString("freePhone"));
				fVo.setFreeEmail(rs.getString("freeEmail"));
				fVo.setFreeJoinDate(rs.getTimestamp("freeJoindate"));
				fVo.setFreeDropDate(rs.getTimestamp("freeDropDate"));
				fVo.setFreeClass(rs.getInt("freeClass"));
				fVo.setFreeKosa(rs.getInt("freeKosa"));
				fVo.setFreeMarried(rs.getBoolean("freeMarried"));
				fVo.setFreeFrontAddr(rs.getString("freeFrontaddr"));
				fVo.setFreeRearAddr(rs.getString("freeRearaddr"));
				fVo.setFreeBank(rs.getString("freeBank"));
				fVo.setFreeAccName(rs.getString("freeAccName"));
				fVo.setFreeAccount(rs.getString("freeAccount"));
				fVo.setFreeReviser(rs.getString("freeReviser"));
				fVo.setFreeReviseDate(rs.getTimestamp("freeReviseDate"));
				fVo.setFreeScore(rs.getInt("freeScore"));

				list.add(fVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	/*-------------------------------박태근------------------------------------------*/
	/*
	 * public Freelancer getfVo(String id) { //
	 * 
	 * Connection conn = null; String sql =
	 * "select * from freelancer where freeId=?"; PreparedStatement pstmt = null;
	 * ResultSet rs = null; Freelancer fVo = null;
	 * 
	 * try { conn = DBManager.getConnection(); pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, id);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) { fVo = new Freelancer();
	 * 
	 * fVo.setFreeId(rs.getString("freeId")); fVo.setFreePw(rs.getString("freePw"));
	 * fVo.setFreeName(rs.getString("freeName"));
	 * fVo.setFreePic(rs.getString("freePic"));
	 * fVo.setFreeBirth(rs.getDate("freeBirth"));
	 * fVo.setFreesex(rs.getBoolean("freeSex"));
	 * fVo.setFreePhone(rs.getString("freePhone"));
	 * fVo.setFreeEmail(rs.getString("freeEmail"));
	 * fVo.setFreeJoinDate(rs.getDate("freeJoindate"));
	 * fVo.setFreeDropDate(rs.getDate("freeDropDate"));
	 * fVo.setFreeClass(rs.getInt("freeClass"));
	 * fVo.setFreeKosa(rs.getInt("freeKosa"));
	 * fVo.setFreeMarried(rs.getBoolean("freeMarried"));
	 * fVo.setFreeFrontAddr(rs.getString("freeFrontaddr"));
	 * fVo.setFreeRearAddr(rs.getString("freeRearaddr"));
	 * fVo.setFreeBank(rs.getString("freeBank"));
	 * fVo.setFreeAccName(rs.getString("freeAccName"));
	 * fVo.setFreeAccount(rs.getString("freeAccount"));
	 * fVo.setFreeReviser(rs.getString("freeReviser"));
	 * fVo.setFreeReviseDate(rs.getDate("freeReviseDate"));
	 * fVo.setFreeScore(rs.getInt("freeScore"));
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); } finally { try { rs.close();
	 * pstmt.close(); conn.close(); } catch (SQLException e) { e.printStackTrace();
	 * } } return fVo; }
	 * 
	 * public List<Freelancer> selectAllFree() { String sql =
	 * "select * from freelancer order by freeId desc";
	 * 
	 * List<Freelancer> list = new ArrayList<Freelancer>(); Connection conn = null;
	 * Statement stmt = null; ResultSet rs = null;
	 * 
	 * try { conn = DBManager.getConnection(); stmt = conn.createStatement();
	 * 
	 * rs = stmt.executeQuery(sql);
	 * 
	 * while (rs.next()) { Freelancer fVo = new Freelancer();
	 * 
	 * fVo.setFreeId(rs.getString("freeId")); fVo.setFreePw(rs.getString("freePw"));
	 * fVo.setFreeName(rs.getString("freeName"));
	 * fVo.setFreePic(rs.getString("freePic"));
	 * fVo.setFreeBirth(rs.getDate("freeBirth"));
	 * fVo.setFreesex(rs.getBoolean("freeSex"));
	 * fVo.setFreePhone(rs.getString("freePhone"));
	 * fVo.setFreeEmail(rs.getString("freeEmail"));
	 * fVo.setFreeJoinDate(rs.getDate("freeJoindate"));
	 * fVo.setFreeDropDate(rs.getDate("freeDropDate"));
	 * fVo.setFreeClass(rs.getInt("freeClass"));
	 * fVo.setFreeKosa(rs.getInt("freeKosa"));
	 * fVo.setFreeMarried(rs.getBoolean("freeMarried"));
	 * fVo.setFreeFrontAddr(rs.getString("freeFrontaddr"));
	 * fVo.setFreeRearAddr(rs.getString("freeRearaddr"));
	 * fVo.setFreeBank(rs.getString("freeBank"));
	 * fVo.setFreeAccName(rs.getString("freeAccName"));
	 * fVo.setFreeAccount(rs.getString("freeAccount"));
	 * fVo.setFreeReviser(rs.getString("freeReviser"));
	 * fVo.setFreeReviseDate(rs.getDate("freeReviseDate"));
	 * fVo.setFreeScore(rs.getInt("freeScore"));
	 * 
	 * list.add(fVo);
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); } finally {
	 * DBManager.close(conn, stmt, rs); } return list; }
	 */
	/*
	 * -----------------------------------박태근
	 * 끝--------------------------------------
	 */
}

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
/**
 * 프리랜서에 관련된 메소드들을 정의해 놓은 클래스
 * 
 * @author 글로벌IT경영 남정규
 * 
 * @추가작성자 글로벌IT경영 김민현
 *
 */
public class FreelancerDAO {
	private static FreelancerDAO instance = new FreelancerDAO();

	private FreelancerDAO() {
		super();
	}

	public static FreelancerDAO getInstance() {
		return instance;
	}

	/**
	 * 로그인한 프리랜서를 리턴하는 메소드
	 * 
	 * @param loginFree
	 * @return
	 */
	public Freelancer freeLogin(Freelancer loginFree) {
		return loginFree;
	}

	// 프리랜서가 회원가입을 하거나, 프리랜서 계정등록을 할 때, 등록하려는 아이디가 기존에 존재하는지 중복검사
	public int confirmID(String freeId) {
		int result = -1;
		// 데이터베이스내에 저장된 freeid값 중 where freeid 값과 중복되는게 있는지 검색
		String sql = "select freeid from freelancer where freeid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeId); // sql문에 freeid=? 부분에 freeid값을 받음

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

	// 등록하려는 프리랜서 이메일이 중복되지 않는지 체크
	// jsp페이지에서 따로 중복검사를 해야할 필요는 일반적으로 없지만, freeemail는 DB에서 unique로 설정되어있어 값 입력테스트를 할 때 오류발생을 막고자 사용하기 위해 제작
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

	// 등록하려는 프리랜서 휴대폰 번호가 중복되지 않는지 체크
	// jsp페이지에서 따로 중복검사를 해야할 필요는 일반적으로 없지만, freephone는 DB에서 unique로 설정되어있어 값 입력테스트를 할 때 오류발생을 막고자 사용하기 위해 제작
	public int confirmPhone(String freePhone) {
		int result = -1;
		// 데이터베이스내에 저장된 freephone값 중 where freephone 값과 중복되는게 있는지 검색
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

	// 로그인한 프리랜서의 마이프로필을 출력
	public Freelancer showProfile(String freeid) {
		// 로그인한 사람 아이디 기준으로 내프로필 출력하는 sql문
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
			pstmt.setString(1, freeid); // sql문에 freeid=? 부분에 freeid값을 받음

			rs = pstmt.executeQuery();

			if (rs.next()) {

				fVo = new Freelancer();

				fVo.setFreeId(rs.getString("freeId")); // 아이디
				fVo.setFreePw(rs.getString("freePw")); // 비밀번호
				fVo.setFreeEmail(rs.getString("freeEmail")); // 이메일
				fVo.setFreeName(rs.getString("freeName")); // 이름
				fVo.setFreePic(rs.getString("freePic")); // 사진
				fVo.setFreeSex(rs.getBoolean("freeSex")); // 성별
				fVo.setFreePhone(rs.getString("freePhone")); // 전화번호 
				fVo.setFreeMarried(rs.getBoolean("freeMarried")); // 결혼여부
				fVo.setFreeFrontAddr(rs.getString("freeFrontAddr")); // 주소
				fVo.setFreeRearAddr(rs.getString("freeRearAddr")); // 나머지주소
				fVo.setFreeBirth(rs.getString("freeBirth")); // 생년월일

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return fVo;
	}

	// 프리랜서 마이프로필 기본정보 수정
	public void updateProfile(Freelancer fVo) {
		// 로그인한 프리랜서의 기본정보를 추가등록하거나 수정하는 sql문
		String sql = "update freelancer set freeEmail=?, freePic=?, freeFilePath=?, freePw=?, freeName=?, "
				+ "freeBirth=date_format(?, '%Y-%m-%d'), freeSex=?, freeMarried=?, freePhone=?,  "
				+ "freeFrontAddr=?, freeRearAddr=? where freeId=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, fVo.getFreeEmail()); // 이메일
			pstmt.setString(2, fVo.getFreePic()); // 사진
			pstmt.setString(3, fVo.getfreeFilePath()); // 사진저장경로
			pstmt.setString(4, fVo.getFreePw()); // 비밀번호
			pstmt.setString(5, fVo.getFreeName()); // 이름
			pstmt.setString(6, fVo.getFreeBirth()); // 생년월일
			pstmt.setInt(7, fVo.getFreeSex() == true ? 1 : 0); // 성별
			pstmt.setInt(8, fVo.getFreeMarried() == true ? 1 : 0); // 결혼여부
			pstmt.setString(9, fVo.getFreePhone()); // 전화번호
			pstmt.setString(10, fVo.getFreeFrontAddr()); // 주소
			pstmt.setString(11, fVo.getFreeRearAddr()); // 나머지주소
			pstmt.setString(12, fVo.getFreeId()); // 아이디

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	/* 기존에 여기에 위치해있던 updatePicture 메소드 삭제했음 사진 업데이트 하는데 전혀 필요없는 메소드*/

	// 프리랜서 계좌정보 출력
	public Freelancer showAccount(String freeid) {
		// 로그인한 아이디 기준 계좌정보 출력 sql문
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

				fVo.setFreeBank(rs.getString("freeBank")); // 은행명
				fVo.setFreeAccName(rs.getString("freeAccName")); // 계좌명의
				fVo.setFreeAccount(rs.getString("freeAccount")); // 계좌번호

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return fVo;
	}

	// 계좌정보 등록 및 수정
	public void updateAccount(Freelancer fVo) {
		// 프리랜서 아이디값을 기준으로 프리랜서 계좌정보 등록 및 수정 sql문
		String sql = "update freelancer set freeBank=?, freeAccName=?, " + "freeAccount=? where freeId=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, fVo.getFreeBank()); // 은행명
			pstmt.setString(2, fVo.getFreeAccName()); // 계좌명의
			pstmt.setString(3, fVo.getFreeAccount()); // 계좌번호
			pstmt.setString(4, fVo.getFreeId()); // 아이디

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 새로운 학력정보를 추가할때 필요한 학력번호 생성
	public int getNewEduNum() {
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

	// 프리랜서 학력정보 출력
	public ArrayList<Education> showEducation(String freeid) {
		// 프리랜서 아이디 기준으로 등록된 학력정보를 불러오는 sql문
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
				// 입학일을 DB에서 불러올때, 시간을 제외한, YYYY-MM-DD와 같이 년도,월,일만 불러오도록 0번째에서 10번째까지 잘라줌
				String SchoolJoinDate = rs.getString("SchoolJoinDate").toString();
				String SchoolJoinDatesub = SchoolJoinDate.substring(0, 10);
				// 졸업일을 DB에서 불러올때, 시간을 제외한, YYYY-MM-DD와 같이 년도,월,일만 불러오도록 0번째에서 10번째까지 잘라줌
				String SchoolGraduatedDate = rs.getString("SchoolGraduatedDate").toString();
				String SchoolGraduatedDatesub = SchoolGraduatedDate.substring(0, 10);

				Education edu = new Education();
				edu.setEduNum(rs.getInt("eduNum")); // 학력번호
				edu.setEduSchool(rs.getString("eduSchool")); // 학교명
				edu.setEduMajor(rs.getString("eduMajor")); // 전공
				edu.setEduDeploma(rs.getString("eduDeploma")); // 학위
				edu.setSchoolJoinDate(SchoolJoinDatesub); // 입학일(String으로 선언된 년,월,일로 자른 입학일값)
				edu.setSchoolGraduatedDate(SchoolGraduatedDatesub); // 졸업일 (String으로 선언된 년,월,일로 자른 졸업일값)

				eVo.add(edu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return eVo;
	}

	// 학력정보 추가 등록 및 수정
	public void updateEducation(ArrayList<Education> eVos) {
		// 사용자가 입력한 정보 중 eduNum을 기준으로 education 테이블에 eduNum이 존재하면 update(수정)를 하고, 존재하지 않으면 insert(등록)을 하는 sql문
		// insert가 실행될때는 on duplicate 전까지의 구문이 실행되고, update는 on duplicate 뒤의 구문이 실행
		String sql = "INSERT INTO education (edunum, eduschool, edumajor, edudeploma, schooljoindate, schoolgraduateddate, freeid) VALUES (?, ?, ?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?) "
				+ "ON DUPLICATE KEY UPDATE edunum=?, eduschool=?, edumajor=?, edudeploma=?, schooljoindate=date_format(?, '%Y-%m-%d'), schoolgraduateddate=date_format(?, '%Y-%m-%d'), freeid=?";

		// System.out.println("학력번호가 들어갔는지 테스트" + eVos.get(getNewEduNum()).toString());
		// System.out.println("입학일이 들어갔는지 테스트" + eVo.getSchoolJoinDate().toString());

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			for (Education e : eVos) {
				pstmt = conn.prepareStatement(sql);
				
				// 새로운 eduNum이 등록되어 insert
				pstmt.setInt(1, e.getEduNum()); // 학력번호
				pstmt.setString(2, e.getEduSchool()); // 학교명
				pstmt.setString(3, e.getEduMajor()); // 전공
				pstmt.setString(4, e.getEduDeploma()); // 학위
				pstmt.setString(5, e.getSchoolJoinDate()); // 입학일
				pstmt.setString(6, e.getSchoolGraduatedDate()); // 졸업일
				pstmt.setString(7, e.getFreeId()); // 아이디
				
				// 기존에 존재하는 eduNum이 있어 update
				pstmt.setInt(8, e.getEduNum()); // 학력번호
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

	// 새로운 경력정보를 추가할때 필요한 경력번호 생성
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

	// 프리랜서 경럭정보 출력
	public ArrayList<Career> showCareer(String freeid) {
		// 프리랜서 아이디값을 기준으로 경력정보 출력 sql문
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
				// 입사일을 DB에서 불러올때, 시간을 제외한, YYYY-MM-DD와 같이 년도,월,일만 불러오도록 0번째에서 10번째까지 잘라줌
				String CompanyJoinDate = rs.getString("CompanyJoinDate").toString();
				String CompanyJoinDatesub = CompanyJoinDate.substring(0, 10);
				// 퇴사일을 DB에서 불러올때, 시간을 제외한, YYYY-MM-DD와 같이 년도,월,일만 불러오도록 0번째에서 10번째까지 잘라줌
				String CompanyDropDate = rs.getString("CompanyDropDate").toString();
				String CompanyDropDatesub = CompanyDropDate.substring(0, 10);

				Career car = new Career();
				car.setCareerNum(rs.getInt("careerNum")); // 경력번호
				car.setCareerCompany(rs.getString("careerCompany")); // 회사명
				car.setCompanyJoinDate(CompanyJoinDatesub); // 입사일(String으로 선언된 년,월,일로 자른 입사일값)
				car.setCompanyDropDate(CompanyDropDatesub); // 퇴사일(String으로 선언된 년,월,일로 자른 퇴사일값)
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

	// 경력정보 추가 등록 및 수정
	public void updateCareer(ArrayList<Career> cVos) {
		// careerNum을 기준으로 careerNum이 존재하면 update, 존재하지 않으면 insert하는 sql문
		String sql = "INSERT INTO career (careernum, careercompany, companyjoindate, companydropdate, careerposition, careerjob, freeid) VALUES (?, ?, date_format(?, '%Y-%m-%d'), date_format(?, '%Y-%m-%d'), ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE careernum=?, careercompany=?, companyjoindate=date_format(?, '%Y-%m-%d'), companydropdate=date_format(?, '%Y-%m-%d'), careerposition=?, careerjob=?, freeid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();

			for (Career c : cVos) {
				pstmt = conn.prepareStatement(sql);

				// careerNum이 존재하지 않을 때 insert
				pstmt.setInt(1, c.getCareerNum()); // 경력번호
				pstmt.setString(2, c.getCareerCompany()); // 회사명
				pstmt.setString(3, c.getCompanyJoinDate()); // 입사일
				pstmt.setString(4, c.getCompanyDropDate()); // 퇴사일
				pstmt.setString(5, c.getCareerPosition()); // 직책
				pstmt.setString(6, c.getCareerJob()); // 회사내 역할
				pstmt.setString(7, c.getFreeId()); // 아이디
				
				// careerNum이 존재할 때 update
				pstmt.setInt(8, c.getCareerNum()); // 경력번호
				pstmt.setString(9, c.getCareerCompany()); // 회사명
				pstmt.setString(10, c.getCompanyJoinDate()); // 입사일
				pstmt.setString(11, c.getCompanyDropDate()); // 퇴사일
				pstmt.setString(12, c.getCareerPosition()); // 직책
				pstmt.setString(13, c.getCareerJob()); // 회사내 역할
				pstmt.setString(14, c.getFreeId()); // 아이디

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
					System.out.println("DB번호" + newDBMS.getDbNum());
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

	// 프리랜서 계정 탈퇴(삭제)
	public void deleteFreelancer(String freeid) {
		// 프리랜서 아이디값을 기준으로 프리랜서 계정 삭제 sql문
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

	/**
	 * 프리랜서 등록화면에서 스킬인벤토리를 등록하는 메소드
	 * 
	 * 같은 화면에서 등록과 수정이 이루어지므로 
	 * 
	 * 프로젝트 번호의 존재 여부를 검사한 후 INSERT와 UPDATE 구문을 실행
	 * 
	 * 회사 내부의 프로젝트는 등록/수정 할 필요가 없으므로 외부 프로젝트일 때만 등록/수정 수행
	 * 
	 * @param skillInvList
	 * @param langNumList
	 * @param frameNumList
	 */
	public void insertSkillInventory(List<SkillInventory> skillInvList, ArrayList<String[]> langNumList,
			ArrayList<String[]> frameNumList) {
		/**
		 *  스킬인벤토리는 데이터베이스상에 존재하는 테이블이 아니기 때문에 등록할 때 
		 *  
		 *  각각 다른 테이블에 입력받은 정보들을 등록해야 하는데 프로젝트 등록과 마찬가지로 
		 *  
		 *  하나의 커넥션 안에서 이루어져야 한다. (ProjectViewDAO.java의 insertProject 메소드 참고)
		 */
		
		// project 테이블에 신규 프로젝트를 등록하는 쿼리
		String projInsertSql = "INSERT INTO project (projname, isextern, dbNum, projcompany, projtarget) "
						     + "VALUES (?, ?, ?, ?, ?)";
		
		// joinproj 테이블에 신규 프로젝트의 참여정보를 등록하기 위해서 프로젝트번호를 last_insert_id() 값으로 받아와 등록하는 쿼리
		String joinProjInsertSql = "INSERT INTO joinproj (projnum, freeid, projrole, joinprojdate, exitprojdate) "
								 + "VALUES ((select last_insert_id()), ?, ?, date_format(?,'%Y-%m-%d'), date_format(?,'%Y-%m-%d'))";
		
		// langskill 테이블에 프리랜서가 사용한 첫번째 언어를 등록하기 위해 joinnum을 last_insert_id()로 받아와 등록하는 쿼리
		String langSkillInsertSql = "INSERT INTO langskill(joinnum, langnum) "
								  + "VALUES((SELECT last_insert_id()), ?)";
		
		// 첫번째 언어 등록을 수행한 후 같은 쿼리로 INSERT구문을 수행하면 joinnum에 last_insert_id()값(langskillnum)을 등록하게 되므로
		// 서브쿼리를 이용하여 lasst_insert_id()값(lanskillnum)으로 조회한 langskill의 joinnum을 찾아 다음 언어를 등록하는 쿼리
		String langSkillInsert2Sql = "INSERT INTO langskill(joinnum, langnum) "
								   + "VALUES((SELECT lsk.joinnum "
								   			 + "FROM langskill lsk "
								   			+ "WHERE langskillnum=(SELECT last_insert_id())), ?)";
		
		// frameworkskill 테이블에 서브쿼리를 이용하여 lasst_insert_id()값(langskillnum)으로 조회한 
		// langskill의 joinnum을 찾아 첫번쨰 프레임워크를 등록하는 쿼리
		String frameSkillInsertSql = "INSERT INTO frameworkskill(joinnum, framenum) "
								   + "VALUES((SELECT lsk.joinnum "
								   			 + "FROM langskill lsk "
								   			+ "WHERE langskillnum=(SELECT last_insert_id())), ?)";
		
		// 첫번째 프레임워크 등록을 수행한 후 서브쿼리를 이용하여 lasst_insert_id()값(frameskillnum)으로 조회한 
		// frameworkskill의 joinnum을 찾아 다음 프레임워크를 등록하는 쿼리
		String frameSkillInsert2Sql = "INSERT INTO frameworkSkill(joinnum, framenum) "
								 	+ "VALUES((SELECT fmw.joinnum "
								 			  + "FROM frameworkskill fmw "
								 			 + "WHERE frameskillnum=(SELECT last_insert_id())),?)";
		
		// 프로젝트 번호를 받아 해당 번호의 프로젝트 정보를 수정하는 쿼리
		String projUpdateSql = "UPDATE project SET projname=?, dbNum=?, projcompany=?, projtarget=? WHERE projnum=?";
		
		// 서브쿼리를 이용하여 projnum으로 joinnum을 찾아 해당 joinnum의 프로젝트참여 정보를 수정하는 쿼리
		String joinProjUpdateSql = "UPDATE joinproj SET projrole=?, joinprojdate=?, exitprojdate=? "
								  + "WHERE joinnum=(SELECT * "
								  				   + "FROM (SELECT pjoin.joinnum "
								  				   		   + "FROM joinproj as pjoin "
								  				   		  + "WHERE projnum=?)j)";
		
		/* 언어는 하나의 프로젝트에 여러개가 사용될 수 있다.
		   프로젝트 또한 한명의 프리랜서가 여러개의 프로젝트에 참여할 수 있다.
		   따라서 여러개의 언어가 사용된 프로젝트를 여러개 업데이트 해야 한다.
		   이를 한번에 수행하기 위해서 사용된 언어의 번호(langnum)를 배열에 담는다.
		   이 배열들은 프로젝트의 개수만큼 생성되고 그 배열들을 ArrayList에 담는다.
		   이렇게 생성된 ArrayList를 매개변수로 받아와 쿼리들을 실행한다.
		   스킬인벤토리를 수정 할 때 여러개의 프로젝트에 사용된 여러개의 언어를 하나씩 수정하려면
		   projnum으로 joinnum을 잦아 joinnum이 해당하는 langskill중 langnum이 일치하는 행의 langnum을 수정해야하는데,
		   그러면 쿼리와 소스코드가 너무 복잡해 지므로 프로젝트에 사용된 언어 전체를 삭제한 후 새로 등록한다.*/
		
		// langskill 테이블에서 projnum으로 해당 프로젝트의 joinnum을 찾아 그 joinnum이 있는 행을 모두 삭제하는 쿼리
		String langSkillDeleteSql = "DELETE FROM langskill "
										+ "WHERE joinnum=(SELECT joinnum "
														 + "FROM joinproj "
														+ "WHERE projnum=?)";
		
		// 삭제 후 projnum으로 joinproj 테이블에서 joinnum을 찾아 해당 프로젝트에 사용된 언어를 등록하는 쿼리
		String langSkillInsertSql2 = "INSERT INTO langskill(joinnum, langnum) "
								   + "VALUES((SELECT joinnum "
								   			 + "FROM joinproj "
								   		    + "WHERE projnum=?), ?)";
		
		// frameworkskill 테이블에서 projnum으로 해당 프로젝트의 joinnum을 찾아 그 joinnum이 있는 행을 모두 삭제하는 쿼리
		String frameSkillDeleteSql = "DELETE FROM frameworkskill "
									+ "WHERE joinnum=(SELECT joinnum "
													 + "FROM joinproj "
													+ "WHERE projnum=?)";
		
		// 삭제 후 projnum으로 joinproj 테이블에서 joinnum을 찾아 해당 프로젝트에 사용된 프레임워크를 등록하는 쿼리
		String frameSkillInsertSql2 = "INSERT INTO frameworkskill(joinnum, framenum) "
									+ "VALUES((SELECT joinnum "
													  + "FROM joinproj "
													 + "WHERE projnum=?), ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			System.out.println("인서트 메소드 실행");
			// 커넥션 연결
			conn = DBManager.getConnection();
			
			// skillInvList의 길이는 프로젝트 개수와 같음
			for (int i = 0; i < skillInvList.size(); i++) {
				// 프로젝트 번호와 외부 프로젝트 여부를 확인
				System.out.println("프로젝트 번호 : " + skillInvList.get(i).getProjNum() + " 외부프로젝트 여부 : "
						+ skillInvList.get(i).getIsExtern());
				
				// project 테이블에 번호가 0인 것은 없으므로 프로젝트 번호가 0이고 외부 프로젝트일 때 등록 실행
				if ((skillInvList.get(i).getProjNum() == 0) && (skillInvList.get(i).getIsExtern() == 1)) {
					System.out.println("프로젝트 등록 실행");
					
					// 프로젝트 등록 쿼리 준비
					pstmt = conn.prepareStatement(projInsertSql);
					// skillInvList의 i번째 인덱스에 있는 프로젝트에서 각 변수에 해당하는 값을 받아옴
					pstmt.setString(1, skillInvList.get(i).getProjName());
					pstmt.setInt(2, skillInvList.get(i).getIsExtern());
					pstmt.setInt(3, skillInvList.get(i).getDbNum());
					pstmt.setString(4, skillInvList.get(i).getProjCompany());
					pstmt.setString(5, skillInvList.get(i).getProjTarget());

					// 쿼리 실행
					pstmt.executeUpdate();
					pstmt.close();

					// 프로젝트 참여 등록 쿼리 준비
					pstmt = conn.prepareStatement(joinProjInsertSql);
					// skillInvList의 i번째 인덱스에 있는 프로젝트에서 각 변수에 해당하는 값을 받아옴
					pstmt.setString(1, skillInvList.get(i).getFreeId());
					pstmt.setString(2, skillInvList.get(i).getProjRole());
					pstmt.setString(3, skillInvList.get(i).getJoinProjDate());
					pstmt.setString(4, skillInvList.get(i).getExitProjDate());

					pstmt.executeUpdate();
					pstmt.close();

					// langNumList의 i번째 인덱스가 null이 아니면
					if (langNumList.get(i) != null) {
						pstmt = conn.prepareStatement(langSkillInsertSql);
						// langNumList의 i번째 인덱스에 있는 배열의 0번째 값을 얻어와서
						pstmt.setInt(1, Integer.parseInt(langNumList.get(i)[0]));
						// 쿼리 실행
						pstmt.executeUpdate();
						pstmt.close();
						System.out.println("등록 언어 : " + langNumList.get(i)[0]);

						// langNumList의 i번째 인데스에 있는 배열의 길이만큼 반복
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
		String sql = "select * from projoinfreeframelangcnt_view where freeid = ? group by projnum";
		List<JoinProjectView> list = new ArrayList<JoinProjectView>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println(freeId);
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				JoinProjectView proj = new JoinProjectView();
				System.out.println("프로젝트 명 = " +rs.getString("projname"));
				proj.setProjNum(rs.getInt("projnum"));
				proj.setProjName(rs.getString("projname"));
				proj.setJoinFLCount(rs.getInt("joinfreecount"));
				proj.setRequireCount(rs.getInt("requirecount"));
				proj.setProjState(rs.getString("projstate"));
				proj.setProjStartDate(rs.getString("projstartdate"));
				proj.setJoinProjDate(rs.getDate("joinprojdate"));
//				proj.setExitProjDate(rs.getDate("exitProjDate"));
				proj.setProjPlan(rs.getString("projPlan"));

				list.add(proj);
			}
			for(JoinProjectView proj : list) {
				System.out.println(proj);
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

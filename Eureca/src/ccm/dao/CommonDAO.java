package ccm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ccm.data.table.DBMS;
import ccm.data.table.Employee;
import ccm.data.table.Framework;
import ccm.data.table.Freelancer;
import ccm.data.table.JoinProj;
import ccm.data.table.JoinProjectView;
import ccm.data.table.LangAndDBAndFrame;
import ccm.data.table.Message;
import ccm.data.table.ProgLang;
import ccm.data.table.Project;
import ccm.data.table.Project_Info_view;
import ccm.util.DBManager;;

public class CommonDAO {
	private static CommonDAO instance = new CommonDAO();

	private CommonDAO() {
		super();
	}

	public static CommonDAO getInstance() {
		return instance;
	}

	// 프리랜서 회원가입
	public void insertMember(Freelancer fVo) {
		// 회원가입에 입력된 프리랜서아이디, 이메일, 패스워드 정보를 DB에 등록하는 sql문
		String sql = "insert into freelancer(" + "freeid, freeemail, freepw) " + "values(?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, fVo.getFreeId()); // 아이디
			pstmt.setString(2, fVo.getFreeEmail()); // 이메일
			pstmt.setString(3, fVo.getFreePw()); // 비밀번호

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}

	// 아이디 중복체크
	public int confirmID(String freeid) {
		int result = -1;
		// 입력된 아이디값과 DB에 프리랜서 아이디값이 있는지 검색하는 sql문
		String sql = "select freeid from freelancer where freeid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeid); // sql문에 freeid=? 부분에 freeid값을 받음
		
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
	
	// 이메일 중복체크
	public int confirmEmail(String freeemail) {
		int result = -1;
		// 입력된 이메일값과 DB에 프리랜서 이메일값이 있는지 검색하는 sql문
		String sql = "select freeemail from freelancer where freeemail=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeemail); // sql문에 freeemail=? 부분에 freeemail값을 받음
		
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
	
	public int loginCheck(String id, String pw, Freelancer loginFree, Employee loginEmp) // 로그인할때 아이디랑 비번이 맞는지 체크해주는거
	{
		// 쿼리를 둘다 실행해서 맞는값이 있는 쿼리일 때 프리랜서 또는 직원 로그인이 정해짐
		String freeSql = "SELECT * FROM FREELANCER WHERE FREEID = ? AND FREEPW = ?";
		String empSql = "SELECT * FROM EMPLOYEE WHERE EMPID = ? AND EMPPW = ?";

		// 결과값 -1은 로그인실패 1은 프리랜서로그인 2는 직원로그인
		int res = -1;
		Connection conn = null;
		ResultSet freers = null;
		ResultSet emprs = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(freeSql);

			// 프리페어드 스테이트먼트에 아이디와 패스워드를 넣어줌
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			freers = pstmt.executeQuery();

			// 결과값이 있으면 프리랜서로그인
			while (freers.next()) {
				if (freers.getString("FREEID") != null)
				{
					// 프리랜서 로그인
					loginFree.setParams(freers);
					res = 1;
					break;
				}
			}

			pstmt.close();
			pstmt = conn.prepareStatement(empSql);
			// 프리페어드 스테이트먼트에 아이디와 패스워드를 넣어줌
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			emprs = pstmt.executeQuery();

			// 결과값이 있으면 직원로그인
			while (emprs.next()) {
				if (emprs.getString("EMPID") != null)
				{
					loginEmp.setParams(emprs);
					res = 2;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("왜안되지");
		} finally {
			DBManager.close(conn, pstmt, freers, emprs);
		}

		return res;
	}

	// DBMS 리스트를 출력하는 메소드
	public List<DBMS> DBMSList() {
		// DBMS를 가져오는 쿼리
		String sql = "select * from dbms order by dbnum";

		// 리스트를 새로 생성해줌
		List<DBMS> list = new ArrayList<DBMS>();

		DBMS db = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			// 루프 안에서 결과값을 모두 생성한 리스트에 넣어줌 
			while (rs.next()) {
				// DBMS 객체를 생성함
				db = new DBMS();
				db.setDbNum(rs.getInt("dbnum"));
				db.setDbName(rs.getString("dbname"));

				list.add(db);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	// 프로그래밍 언어 리스트를 가져오는 메소드
	public List<ProgLang> ProgLangList() {
		// 프로그래밍 언어를 가져오는 쿼리문
		String sql = "SELECT * FROM PROGLANG ORDER BY LANGNUM";

		// 리스트를 새로 생성해줌
		List<ProgLang> list = new ArrayList<ProgLang>();

		ProgLang plang = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			// 루프 안에서 결과값을 모두 생성한 리스트에 넣어줌
			while (rs.next()) {
				// 프로그래밍 언어 객체를 생성함
				plang = new ProgLang();
				plang.setLangNum(rs.getInt("langnum"));
				plang.setLangName(rs.getString("langname"));

				list.add(plang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	// 프레임워크 리스트를 가져오는 메소드
	public List<Framework> FameworkList() {
		
		// 프레임워크를 모두 가져오는 쿼리문
		String sql = "SELECT * FROM FRAMEWORK ORDER BY FRAMENUM";

		// 리스트를 새로 생성함
		List<Framework> list = new ArrayList<Framework>();

		Framework fwk = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			// 루프 안에서 모든 결과값을 리스트에 넣어줌
			while (rs.next()) {
				// 프레임워크 객체를 생성
				fwk = new Framework();
				fwk.setFrameNum(rs.getInt("framenum"));
				fwk.setFrameName(rs.getString("framename"));
				fwk.setFrameDevelopField(rs.getString("framedevelopfield"));

				list.add(fwk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	/*
	 * public ParamInt paging(int pageNum, String sql) { // 쿼리에 컬럼 명은 count(*) as
	 * rowcount로 할것 ParamInt paramInt = new ParamInt(); Connection conn = null;
	 * Statement stmt = null; ResultSet rs = null;
	 * 
	 * try { conn = DBManager.getConnection(); stmt = conn.createStatement(); rs =
	 * stmt.executeQuery(sql);
	 * 
	 * while (rs.next()) { paramInt.setRowCount(rs.getInt("rowcount")); } } catch
	 * (Exception e) { e.printStackTrace(); } finally { DBManager.close(conn, stmt,
	 * rs); }
	 * 
	 * paramInt.setPageCount(((paramInt.getRowCount() - 1) / 10) + 1);
	 * paramInt.setFirstPage(((pageNum - 1) / 10) * 10 + 1);
	 * paramInt.setLastPage(Math.min(paramInt.getFirstPage() + 9,
	 * paramInt.getPageCount()));
	 * 
	 * return paramInt; }
	 */

	// DB에서 프로그래밍 언어, DBMS, 프레임워크를 모두 가져오는 메소드 
	public LangAndDBAndFrame getLangDBFrame() {
		
		// LangAndDBAndFrame 객체를 생성함
		// 객체에는 언어, DBMS, 프레임워크 리스트가 모두 있음
		LangAndDBAndFrame ldf = new LangAndDBAndFrame();
		Connection conn = null;
		
		// 언어를 모두 가져오는 쿼리
		String langSql = "SELECT * FROM PROGLANG ORDER BY LANGNUM";
		// DBMS를 모두 가져오는 쿼리
		String dbmsSql = "SELECT * FROM DBMS ORDER BY DBNUM";
		// 프레임워크를 모두 가져오는 쿼리
		String frameSql = "SELECT * FROM FRAMEWORK ORDER BY FRAMENUM";
		ResultSet langRs = null;
		ResultSet dbmsRs = null;
		ResultSet frameRs = null;
		PreparedStatement langPstmt = null;
		PreparedStatement dbmsPstmt = null;
		PreparedStatement framePstmt = null;

		try {
			conn = DBManager.getConnection();

			// 언어부터 쿼리를 던짐
			langPstmt = conn.prepareStatement(langSql);
			langRs = langPstmt.executeQuery();
			
			// 루프 안에서 언어 데이터를 모두 가져와서
			// ldf 객체의 lang 필드 리스트에 모두 넣어줌
			while (langRs.next()) {
				// 언어 객체 생성
				ProgLang newLang = new ProgLang();
				
				// ResultSet 객체에서 값을 모두 가져와서
				// 객체의 필드를 초기화함
				newLang.setParams(langRs);
				
				// 리스트에 넣어줌
				ldf.getLang().add(newLang);
			}
			if (langRs != null)
				langRs.close();
			if (langPstmt != null)
				langPstmt.close();

			// 프레임워크 쿼리 실행
			framePstmt = conn.prepareStatement(frameSql);
			frameRs = framePstmt.executeQuery();
			
			// 루프 안에서 프레임워크 데이터를 모두 가져와서
			// ldf 객체의 frame 필드 리스트에 모두 넣어줌
			while (frameRs.next()) {
				// 프레임워크 객체 생성
				Framework newFrame = new Framework();
				
				// ResultSet 객체에서 값을 모두 가져와서
				// 객체의 필드를 초기화함
				newFrame.setParams(frameRs);
				
				// 리스트에 넣어줌
				ldf.getFrame().add(newFrame);
			}
			if (frameRs != null)
				frameRs.close();
			if (framePstmt != null)
				framePstmt.close();

			// DBMS 쿼리 실행
			dbmsPstmt = conn.prepareStatement(dbmsSql);
			dbmsRs = dbmsPstmt.executeQuery();
			
			// 루프 안에서 DBMS 데이터를 모두 가져와서
			// ldf 객체의 dbms 필드 리스트에 모두 넣어줌
			while (dbmsRs.next()) {
				// DBMS 객체 생성
				DBMS newDbms = new DBMS();
				
				// ResultSet 객체에서 값을 모두 가져와서
				// 객체의 필드를 초기화함
				newDbms.setParams(dbmsRs);
				
				// 리스트에 넣어줌
				ldf.getDbms().add(newDbms);
			}
			if (dbmsRs != null)
				dbmsRs.close();
			if (dbmsPstmt != null)
				dbmsPstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn);
		}

		return ldf;
	}

	public List<Message> selectAllMsgFree(String id) {
		// 이전에 보내진 메시지를 프리랜서의 아이디로 전부 불러오는 메소드
		String sql = "select * from Message where freeReceiver='"+ id + "' order by msgNum desc";

		List<Message> list = new ArrayList<Message>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Message msgVo = new Message();

				msgVo.setParams(rs);
				
				list.add(msgVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}

		return list;
	}
	
	public List<Message> selectAllMsgEmp(String id) {
		// 이전에 보내진 메시지를 사원(관리자)의 아이디로 전부 불러오는 메소드
		String sql = "select * from Message where empReceiver='"+ id + "' order by msgNum desc";

		List<Message> list = new ArrayList<Message>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Message msgVo = new Message();

				msgVo.setParams(rs);
				
				list.add(msgVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}

		return list;
	}

	public Message selectOneMsgByNo(String msgNum) {
		// 메시지 번호를 통해 메시지의 정보를 가져오는 메소드
		String sql = "select * from message where msgNum=?";

		Message msgVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, msgNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				msgVo = new Message();

				msgVo.setParams(rs);

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DBManager.close(conn, pstmt, rs);

		}

		return msgVo;
	}
	
	public void insertMsg(Message msgVo) {
		// 새로운 메시지를 보내는 메소드
		String sql = "insert into message(msgNum, prevMsgNum, freeWriter, empWriter, freeReceiver, "
				+ "empReceiver, msgTitle, msgContent, msgSendDate, msgChecked, projNum) "
				+ "values(null, ?, ?, ?, ?, ?, ?, ?, now(), false, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		System.out.println("insertMsg 메소드");
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			// 이전메시지가 없으면 널을 넣어준다.
			if(msgVo.getPrevMsgNum() == null || msgVo.getPrevMsgNum()=="") {
				pstmt.setString(1, null);
				System.out.println("이전 메시지 번호 : null");
			} else {
				pstmt.setInt(1, Integer.parseInt(msgVo.getPrevMsgNum()));
				System.out.println("이전 메시지 번호 : " + msgVo.getPrevMsgNum());
			}
			
			// 프리랜서 작성자가 널이면 널을 넣어준다.
			if(msgVo.getFreeWriter() == null || msgVo.getFreeWriter() == "") {
				pstmt.setString(2, null);
				System.out.println("발신하는 프리랜서 : null");
			} else {
				pstmt.setString(2, msgVo.getFreeWriter());
				System.out.println("발신하는 프리랜서 : " + msgVo.getFreeWriter());
			}
			
			// 사원 작성자가 널이면 널을 넣어준다.
			if(msgVo.getEmpWriter() == null || msgVo.getEmpWriter() == "") {
				pstmt.setString(3, null);
				System.out.println("발신하는 사원(관리자) : null");
			} else {
				pstmt.setString(3, msgVo.getEmpWriter());
				System.out.println("발신하는 사원(관리자) : " + msgVo.getEmpWriter());
			}
			
			// 프리랜서 수신자가 널이면 널을 넣어준다.
			if(msgVo.getFreeReceiver() == null || msgVo.getFreeReceiver() == "") {
				pstmt.setString(4, null);
				System.out.println("수신받는 프리랜서 : null");
			} else {
				pstmt.setString(4, msgVo.getFreeReceiver());
				System.out.println("수신받는 프리랜서 : " + msgVo.getFreeReceiver());
			}
			
			// 사원 수신자가 널이면 널을 넣어준다.
			if(msgVo.getEmpReceiver() == null || msgVo.getEmpReceiver() == "") {
				pstmt.setString(5, null);
				System.out.println("수신받는 사원(관리자) : null");
			} else {
				pstmt.setString(5, msgVo.getEmpReceiver());
				System.out.println("수신받는 사원(관리자) : " + msgVo.getEmpReceiver());
			}

			pstmt.setString(6, msgVo.getMsgTitle());
			pstmt.setString(7, msgVo.getMsgContent());
			
			// 연관된 프로젝트가 없으면(연관 프로젝트가 널이면) 널을 넣어준다.
			if(msgVo.getProjNum() == null || msgVo.getProjNum() == "") {
				pstmt.setString(8, null);
				System.out.println("연관된 프로젝트 번호 : null");
			} else {
				pstmt.setInt(8, Integer.parseInt(msgVo.getProjNum()));
				System.out.println("연관된 프로젝트 번호 : " + msgVo.getProjNum());
			}
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}
	
	public void sendInterviewMsg(Message msgVo, Integer joinNum) {
		// 인터뷰할 사람들에게 면접일정과 장소에 대한 통지를 하는 메소드
		String sql = "insert into message(msgNum, prevMsgNum, freeWriter, empWriter, freeReceiver, "
				+ "empReceiver, msgTitle, msgContent, msgSendDate, msgChecked, projNum) "
				+ "values(null, ?, ?, ?, ?, ?, ?, ?, now(), false, (select projNum from joinProj where joinNum = ?))";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			// 이전메시지가 없으면 널을 넣어준다.
			if(msgVo.getPrevMsgNum() == null || msgVo.getPrevMsgNum()=="") {
				pstmt.setString(1, null);
			} else {
				pstmt.setInt(1, Integer.parseInt(msgVo.getPrevMsgNum()));
			}
			
			// 프리랜서 작성자가 널이면 널을 넣어준다.
			if(msgVo.getFreeWriter() == null || msgVo.getFreeWriter() == "") {
				pstmt.setString(2, null);
			} else {
				pstmt.setString(2, msgVo.getFreeWriter());
			}
			
			// 사원 작성자가 널이면 널을 넣어준다.
			if(msgVo.getEmpWriter() == null || msgVo.getEmpWriter() == "") {
				pstmt.setString(3, null);
			} else {
				pstmt.setString(3, msgVo.getEmpWriter());
			}
			
			// 프리랜서 수신자가 널이면 널을 넣어준다.
			if(msgVo.getFreeReceiver() == null || msgVo.getFreeReceiver() == "") {
				pstmt.setString(4, null);
			} else {
				pstmt.setString(4, msgVo.getFreeReceiver());
			}
			
			// 사원 수신자가 널이면 널을 넣어준다.
			if(msgVo.getEmpReceiver() == null || msgVo.getEmpReceiver() == "") {
				pstmt.setString(5, null);
			} else {
				pstmt.setString(5, msgVo.getEmpReceiver());
			}

			pstmt.setString(6, msgVo.getMsgTitle());
			pstmt.setString(7, msgVo.getMsgContent());
			
			// 연관된 프로젝트가 없으면(연관 프로젝트가 널이면) 널을 넣어준다.
			if(joinNum != null) {
				pstmt.setInt(8,joinNum);
			}
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}
	
	public void updateMsgCheckedDate(String msgNo) {
		// 확인이 되지 않은 메시지를 확인하는 첫 회에 메시지 확인을 해주는 메소드
		String sql = "update message set msgCheckedDate = now(), msgChecked = 1"
				+ " where msgNum = ? and msgChecked = 0";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, msgNo);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public Project selectLastJoinProject() {
		// 가장 최근에 등록된 프로젝트를 가져오는 메소드
		String sql = "select * from project order by projRegisterDate desc limit 1";

		Project pVo = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				
				pVo = new Project();

				pVo.setParams(rs);
				
			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DBManager.close(conn, stmt, rs);

		}

		return pVo;
	}
	
	public Project selectProjectByNo(int projNo) {
		// 프로젝트 테이블에서 정보를 가져오는 메소드
		String sql = "select * from project from projNum = ?";

		Project pVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				pVo = new Project();

				pVo.setParams(rs);

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DBManager.close(conn, pstmt, rs);

		}

		return pVo;
	}
	
	public Project_Info_view selectLastRegistProject() {
		// 가장 최근에 등록된 프로젝트의 정보와 그에 관련된 정보(필요인원, 신청인원, 사용언어들, 사용프레임워크들)를 가져오는 메소드
		String sql = "select * from project_info3 order by projRegisterDate desc limit 1";

		Project_Info_view pVo = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				
				pVo = new Project_Info_view();

				pVo.setParams(rs);
				
			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DBManager.close(conn, stmt, rs);

		}

		return pVo;
	}
	
	public List<Project_Info_view> selectAllJoinableProject() {
		// 시작하지 않은 프로젝트들을 가져오는 메소드 
		String sql = "select * from project_info3 where projStartDate >= now() order by projNum desc";

		List<Project_Info_view> list = new ArrayList<Project_Info_view>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Project_Info_view pVo = new Project_Info_view();

				pVo.setParams(rs);
				
				list.add(pVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}

		return list;
	}
	
	public Project_Info_view selectOneProjByNo(String No) {
		// 프로젝트 번호로 project_info3 뷰에서 정보를 받아오는 메소드
		String sql = "select * from project_info3 where projNum=?";

		Project_Info_view pVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, No);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				pVo = new Project_Info_view();

				pVo.setParams(rs);

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DBManager.close(conn, pstmt, rs);

		}

		return pVo;
	}
	
	public List<JoinProjectView> selectJoinProjectByFreeId(String id) {
		String sql = "select * from joinProject where freeId='"+ id + "' order by joinNum desc";

		List<JoinProjectView> list = new ArrayList<JoinProjectView>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				JoinProjectView jVo = new JoinProjectView();
				
				jVo.setParams(rs);
				
				list.add(jVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}

		return list;
	}
	
	public JoinProj getJoinProjByNo(String no) {
		// joinNum을 통해 joinProj의 데이터를 가져오는 메소드
		// TODO Auto-generated method stub
		String sql = "select * from joinProj where joinNum = ?";

		System.out.println("getJoinProjByNo 시작");
		JoinProj jVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				jVo = new JoinProj();

				System.out.println("getJoinProjByNo 진행중");

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
		System.out.println("getJoinProjByNo 종료");

		return jVo;
	}

}
package ccm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ccm.data.ParamInt;
import ccm.data.table.DBMS;
import ccm.data.table.Framework;
import ccm.data.table.Freelancer;
import ccm.data.table.JoinProj;
import ccm.data.table.ProgLang;
import ccm.data.table.Project;
import ccm.data.table.ProjectView;
import ccm.util.DBManager;

/**
 * 프로젝트에 관련된 메소드를 정의해 놓은 클래스
 * 
 * @author 글로벌IT경영 남정규
 *
 */
public class ProjectViewDAO {
	// 
	public static ProjectViewDAO instance = new ProjectViewDAO();

	public static ProjectViewDAO getInstance() {
		return instance;
	}

	/**
	 * project테이블의 모든 정보를 가져오는 메소드
	 * @return
	 */
	public List<Project> selectProjectList() {

		// 필요한 객체 선언
		List<Project> list = new ArrayList<Project>();

		Project pro = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 커넥션 연결
			conn = DBManager.getConnection();
			
			// project테이블의 모든 정보를 조회하는 쿼리 준비
			String sql = "select * from project";
			stmt = conn.createStatement();
			
			// 쿼리 실행
			rs = stmt.executeQuery(sql);

			// 실행 결과 처리
			while (rs.next()) {
				// 프로젝트 객체 생성
				pro = new Project();
				// 객체의 멤버변수에 테이블의 각 컬럼에 해당하는 정보를 수정자를 통해 설정
				pro.setProjNum(rs.getInt("projnum"));
				pro.setIsExtern(rs.getBoolean("isextern"));
				pro.setProjDevelopSort(rs.getString("projdevelopsort"));
				pro.setProjEndDate(rs.getString("projenddate"));
				pro.setProjExpectedTime(rs.getInt("projexpectedtime"));
				pro.setProjField(rs.getString("projfield"));
				pro.setProjName(rs.getString("projname"));
				pro.setProjPartner(rs.getString("projpartner"));
				pro.setProjPlan(rs.getString("projplan"));
				pro.setProjRecruitEndDate(rs.getString("projRecruitEndDate"));
				pro.setProjRecruitStartDate(rs.getString("projRecruitStartDate"));
				pro.setProjRegisterDate(rs.getString("projregisterdate"));
				pro.setProjRegisterer(rs.getString("projRegisterer"));
				pro.setProjReviseDate(rs.getString("projrevisedate"));
				pro.setProjReviser(rs.getString("projreviser"));
				pro.setProjStartDate(rs.getString("projstartdate"));
				pro.setProjState(rs.getString("projstate"));
				pro.setProjTarget(rs.getString("projtarget"));
				pro.setDbNum(rs.getInt("dbnum"));
				
				//list에 pro를 추가
				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	/**
	 *  project 테이블과 dbms 테이블을 조인하여 프로젝트에 사용된 DBMS 정보를 가져오는 메소드
	 * @return
	 */
	public List<DBMS> selectProjectDBMSList() {
		
		// 필요한 객체 선언
		List<DBMS> list = new ArrayList<DBMS>();

		DBMS db = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 커넥션 연결
			conn = DBManager.getConnection();
			
			// project 테이블과 dbms 테이블을 조인하여 생성한 
			//projdbmsview 뷰의 모든 정보를 중복 제거하여 조회하는 쿼리 준비
			String sql = "select distinct * from projdbmsview";
			stmt = conn.createStatement();
			
			// 쿼리 실행
			rs = stmt.executeQuery(sql);

			// 실행결과 처리
			while (rs.next()) {
				db = new DBMS();
				// 실행결과에서 원하는 컬럼의 값들만 가져와 수정자를 통해 db에 할당
				db.setDbNum(rs.getInt("dbnum"));
				db.setDbName(rs.getString("dbname"));
				
				// list에 db를 추가
				list.add(db);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	/**
	 * 프로젝트에 사용된 모든 언어의 정보를 가져오는 메소드
	 * @return
	 */
	public List<ProgLang> selectProjLangList() {
		
		// 필요한 객체 선언
		List<ProgLang> list = new ArrayList<ProgLang>();

		ProgLang plang = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 커넥션 연결
			
			conn = DBManager.getConnection();
			
			// project, proglang, projlang 테이블을 조인하여 생성한 
			// projlangview 뷰의 모든 정보를 중복 제거하여 조회하는 쿼리 준비
			String sql = "select distinct * from projlangview";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				plang = new ProgLang();
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

	/**
	 * 프로젝트에 사용된 모든 프레임워크 정보를 가져오는 메소드
	 * @return
	 */
	public List<Framework> selectProjFameworkList() {

		// 필요한 객체 선언
		List<Framework> list = new ArrayList<Framework>();

		Framework fwk = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 커넥션 연결
			conn = DBManager.getConnection();
			
			// project, projfamework, framework 테이블을 조인하여 생성한
			// projfameview 뷰의 모든 정보를 중복 제거하여 조회하는 쿼리 준비
			String sql = "select distinct * from projframeview";
			stmt = conn.createStatement();
			
			//쿼리 실행
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				fwk = new Framework();
//				fwk.setFrameNum(rs.getInt("framenum"));  // 현재 프레임워크 번호는 필요하지 않아 주석처리 하였음
				fwk.setFrameName(rs.getString("framename"));
//				fwk.setFrameDevelopField(rs.getString("framedevelopfield")); // 현재 개발 분야는 필요하지 않아 주석처리 하였음
				 
				
				list.add(fwk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	/**
	 * 참여한 프리랜서 수, db 명, 예상 종료일, 남은 기간은 project 테이블에 없기때문에 
	 * project와 dbms 테이블, projjoinedfreelancer 뷰를 조인하여 조회한 결과를
	 * ProjectView 리스트로 받는 메소드
	 * @return
	 */
	public List<ProjectView> selectAllProjectInfo() {
		
		List<ProjectView> proViewList = new ArrayList<ProjectView>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ProjectView pjv = null;

		try {
			conn = DBManager.getConnection();
			
			String sql = "select * from projoinfreecount_view";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				pjv = new ProjectView();
				pjv.setProjNum(rs.getInt("projnum"));
				pjv.setIsExtern(rs.getBoolean("isextern"));
				pjv.setProjDevelopSort(rs.getString("projdevelopsort"));
				pjv.setProjEndDate(rs.getString("projenddate"));
				pjv.setProjExpectedTime(rs.getInt("projexpectedtime"));
				pjv.setProjField(rs.getString("projfield"));
				pjv.setProjName(rs.getString("projname"));
				pjv.setProjPartner(rs.getString("projpartner"));
				pjv.setProjPlan(rs.getString("projplan"));
				pjv.setProjRecruitEndDate(rs.getString("projRecruitEndDate"));
				pjv.setProjRecruitStartDate(rs.getString("projRecruitStartDate"));
				pjv.setProjRegisterDate(rs.getString("projregisterdate"));
				pjv.setProjRegisterer(rs.getString("projRegisterer"));
				pjv.setProjReviseDate(rs.getString("projrevisedate"));
				pjv.setProjReviser(rs.getString("projreviser"));
				pjv.setProjStartDate(rs.getString("projstartdate"));
				pjv.setProjState(rs.getString("projstate"));
				pjv.setProjTarget(rs.getString("projtarget"));
				pjv.setDbNum(rs.getInt("dbnum"));
				pjv.setUsedDbName(rs.getString("dbname"));
				pjv.setExpectedEndDate(rs.getDate("expectedenddate"));
				pjv.setRemainDays(rs.getInt("remaindays"));
				pjv.setJoinFLCount(rs.getInt("joinfreecount"));

				proViewList.add(pjv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return proViewList;
	}

	/**
	 * 프로젝트 번호를 매개변수로 받아 조회한 결과를 받는 메소드
	 * @param projNum
	 * @return
	 */
	public ProjectView selectAllProjectInfoByProjNum(int projNum) {
		String sql = "select * from projoinfreecount_view where projnum=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProjectView projView = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				projView = new ProjectView();

				projView.setProjNum(rs.getInt("projnum"));
				projView.setIsExtern(rs.getBoolean("isextern"));
				projView.setProjDevelopSort(rs.getString("projdevelopsort"));
				projView.setProjEndDate(rs.getString("projenddate"));
				projView.setProjExpectedTime(rs.getInt("projexpectedtime"));
				projView.setProjField(rs.getString("projfield"));
				projView.setProjName(rs.getString("projname"));
				projView.setProjPartner(rs.getString("projpartner"));
				projView.setProjPlan(rs.getString("projplan"));
				projView.setProjRecruitEndDate(rs.getString("projRecruitEndDate"));
				projView.setProjRecruitStartDate(rs.getString("projRecruitStartDate"));
				projView.setProjRegisterDate(rs.getString("projregisterdate"));
				projView.setProjRegisterer(rs.getString("projRegisterer"));
				projView.setProjReviseDate(rs.getString("projrevisedate"));
				projView.setProjReviser(rs.getString("projreviser"));
				projView.setProjStartDate(rs.getString("projstartdate"));
				projView.setProjState(rs.getString("projstate"));
				projView.setProjTarget(rs.getString("projtarget"));
				projView.setDbNum(rs.getInt("dbnum"));
				projView.setUsedDbName(rs.getString("dbname"));
				projView.setExpectedEndDate(rs.getDate("expectedenddate"));
				projView.setRemainDays(rs.getInt("remaindays"));
				projView.setJoinFLCount(rs.getInt("joinfreecount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return projView;
	}

	/**
	 * 프로젝트 번호를 매개변수로 받아 해당 프로젝트에 사용된 모든 프레임워크를 조회한 결과를 
	 * 
	 * Framework 리스트로 받는 메소드
	 * @param projNum
	 * @return
	 */
	public List<Framework> selectProjFrameworkByProjNum(int projNum) {
		String sql = "select * from projframeview where projnum=?";

		List<Framework> list = new ArrayList<Framework>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Framework fw = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fw = new Framework();

				fw.setFrameNum(rs.getInt("framenum"));
				fw.setFrameName(rs.getString("framename"));
				fw.setFrameDevelopField(rs.getString("framedevelopfield"));

				list.add(fw);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 프로젝트 번호를 매개변수로 받아 해당 프로젝트에 사용된 모든 언어 정보를 조회한 결과를
	 * 
	 * ProgLang 리스트로 받는 메소드
	 * @param projNum
	 * @return
	 */
	public List<ProgLang> selectProjProgLangByProjNum(int projNum) {
		String sql = "select * from projlangview where projnum=?";

		List<ProgLang> list = new ArrayList<ProgLang>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProgLang pl = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pl = new ProgLang();

				pl.setLangNum(rs.getInt("langnum"));
				pl.setLangName(rs.getString("langname"));

				list.add(pl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}


	String sql;// 페이징을 하기 위해 전체 정보를 조회하는 쿼리문을 담을 String타입 멤버 변수 선언

	/**
	 * 프로젝트명, 개발분야, 언어, 데이터베이스, 프레임워크, 기간, 정렬기준을 매개변수로 받아 
	 * 
	 * 프로젝트를 검색하고 그 결과를 ProjectView 리스트로 받는 메소드
	 * 
	 * 같은 컬럼끼리는 or, 다른 컬럼끼리는 and로 검색
	 * 
	 * @param projName //프로젝트명
	 * @param devCount //개발분야를 담은 배열
	 * @param langCount //언어를 담은 배열
	 * @param dbCount //데이터베이스를 담은 배열
	 * @param tfwCount // 툴,프레임워크를 담은 배열
	 * @param time1 // 날짜1
	 * @param time2 // 날짜2
	 * @param order // 정렬기준
	 * @param pageNum // 페이지 번호
	 * @return
	 */
	public List<ProjectView> searchAllCheckedProject(String projName, String[] devCount, String[] langCount,
			String[] dbCount, String[] tfwCount, String time1, String time2, String order, int pageNum) {

		// 전체 정보를 조회하는 기본 쿼리
		String sql = "select * from projoinfreeframelang_view";

		/*
		 * 매개변수를 올바르게 받았는지 확인하기 위한 출력문 
		System.out.println("dao 프로젝트명 = " + projName);
		System.out.println("dao 분야 = " + devCount);
		System.out.println("dao 언어 = " + langCount);
		System.out.println("dao 데이터베이스 = " + dbCount);
		System.out.println("dao 툴프레임 = " + tfwCount);
		System.out.println("dao 시간1 = " + time1);
		System.out.println("dao 시간2 = " + time2);*/

		// 검색조건에 프로젝트명이 있으면
		if (projName != null && !projName.equals("")) {
			// 기본쿼리에 프로젝트명을 검색하는 조건을 추가
			sql += " where projname like '%" + projName + "%'";
			
			// 개발분야가 있으면 
			if (devCount != null) {
				// 쿼리에 개발분야를 검색하는 조건을 추가
				sql += " and projdevelopsort = ?";
				// devCount의 길이만큼 반복
				for (int i = 1; i < devCount.length; i++) {
					sql += " or projdevelopsort = ?";
				}
			}
			// 언어가 있으면
			if (langCount != null) {
				// 쿼리에 언어를 검색하는 조건을 추가
				sql += " and langname =?";
				// langCount의 길이만큼 반복
				for (int i = 1; i < langCount.length; i++) {
					sql += " or langname =?";
				}
			}
			// 데이터베이스가 있으면
			if (dbCount != null) {
				// 쿼리에 데이터베이스를 검색하는 조건을 추가
				sql += " and dbname=?";
				// dbCount의 길이만큼 반복
				for (int i = 1; i < dbCount.length; i++) {
					sql += " or dbname=?";
				}
			}
			// 툴/프레임워크가 있으면
			if (tfwCount != null) {
				// 쿼리에 툴/프레임워크를 검색하는 조건을 추가
				sql += " and framename=?";
				// tfwCount의 길이만큼 반복
				for (int i = 1; i < tfwCount.length; i++) {
					sql += " or framename=?";
				}
			}
			// 기간이 있으면
			if (time1 != null && !time1.equals("")) {
				// 쿼리에 기간을 검색하는 조건을 추가
				sql += " and projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d')";
			}
			// 쿼리에 검색결과를 프로젝트 번호를 기준으로 중복 제거하는 조건을 추가
			sql += " group by projnum";
			
			// 멤버변수 sql에 현재 쿼리를 저장
			this.sql = sql;

			// 정렬기준이 없으면
			if (order == null || order.equals("")) {
				// 프로젝트 번호를 기준으로 내림차순으로 정렬하고
				// 한 페이지에 출력되는 결과 수를 10개로 제한하는 쿼리 추가
				sql += " order by projnum desc LIMIT ?, 10";
				
			// '남은기간'을 기준으로 정렬할 때
			} else if (order.equals("남은기간")) {
				// 남은기간이 적은 순으로 정렬하고
				// 한 페이지에 출력되는 결과 수를 10개로 제한하는 쿼리 추가
				sql += " order by remaindays asc LIMIT ?, 10";
				
			// '등록일'을 기준으로 정렬할 때
			} else if (order.equals("등록일")) {
				// 최신 등록일 순으로 정렬하고
				// 한 페이지에 출력되는 결과 수를 10개로 제한하는 쿼리 추가
				sql += " order by projregisterdate desc LIMIT ?, 10";
				
			// '시작일'을 기준으로 정렬할 때
			} else if (order.equals("시작일")) {
				// 시작일이 오래된 순으로 정렬하고
				// 한 페이지에 출력되는 결과 수를 10개로 제한하는 쿼리 추가
				sql += " order by projstartdate asc LIMIT ?, 10";
				
			// '종료(예정)일'을 기준으로 정렬할 때
			} else if (order.equals("종료(예정)일")) {
				// 종료(예정)일이 가까운 순으로 정렬하고
				// 한 페이지에 출력되는 결과 수를 10개로 제한하는 쿼리 추가
				sql += " order by projenddate desc LIMIT ?, 10";
			}

			// 쿼리가 올바르게 작성 되는지 확인하기 위해 콘솔창에 출력
			System.out.println("프로젝트명부터 = " + sql);

		// 검색조건에 프로젝트명이 없고 
			//개발분야가 있으면
		} else if ((projName == null || projName.equals("")) && devCount != null) {
			// 기본 쿼리에 개발분야를 검색하는 조건을 추가
			sql += " where projdevelopsort =?";
			// devCount의 길이만큼 반복
			for (int i = 1; i < devCount.length; i++) {
				sql += " or projdevelopsort =?";
			}
			
			// 검색조건에 언어가 있으면
			if (langCount != null) {
				// 쿼리에 언어를 검색하는 조건을 추가
				sql += " and langname =?";
				// langCount의 길이만큼 반복
				for (int i = 1; i < langCount.length; i++) {
					sql += " or langname =?";
				}
			}
			
			// 검색조건에 데이터베스가 있으면
			if (dbCount != null) {
				// 쿼리에 데이터베이스를 검색하는 조건을 추가
				sql += " and dbname=?";
				// dbCount의 길이만큼 반복
				for (int i = 1; i < dbCount.length; i++) {
					sql += " or dbname=?";
				}
			}
			
			// 검색조건에 툴/프레임워크가 있으면
			if (tfwCount != null) {
				// 쿼리에 툴/프레임워크를 검색하는 조건을 추가
				sql += " and framename=?";
				// tfwCount의 길이만큼 반복
				for (int i = 1; i < tfwCount.length; i++) {
					sql += " or framename=?";
				}
			}
			
			// 검색조건에 기간이 있으면
			if (time1 != null && !time1.equals("")) {
				// 쿼리에 기간을 검색하는 조건을 추가
				sql += " and projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d')";
			}
			
			// 쿼리에 검색결과를 프로젝트 번호를 기준으로 중복 제거하는 조건을 추가
			sql += " group by projnum";
			
			// 멤버변수 sql에 현재 쿼리를 저장
			this.sql = sql;

			// 정렬기준에 따라 쿼리에 조건 추가
			if (order == null || order.equals("")) {
				sql += " order by projnum desc LIMIT ?, 10";
			} else if (order.equals("남은기간")) {
				sql += " order by remaindays asc LIMIT ?, 10";
			} else if (order.equals("등록일")) {
				sql += " order by projregisterdate desc LIMIT ?, 10";
			} else if (order.equals("시작일")) {
				sql += " order by projstartdate asc LIMIT ?, 10";
			} else if (order.equals("종료(예정)일")) {
				sql += " order by projenddate desc LIMIT ?, 10";
			}
			// 쿼리가 올바르게 작성되었는지 확인하기 위해서 콘솔에 출력
			System.out.println("개발분야부터 = " + sql);
			
		// 검색조건에 프로젝트명, 개발분야가 없고 언어가 있으면
		} else if ((projName == null || projName.equals("")) && devCount == null && langCount != null) {
			// 기본 쿼리에 언어를 검색하는 조건을 추가
			sql += " where langname=?";
			// langCount의 길이만큼 반복
			for (int i = 1; i < langCount.length; i++) {
				sql += " or langname =?";
			}
			
			// 검색조건에 데이터베이스가 있으면
			if (dbCount != null) {
				// 쿼리에 데이터베이스를 검색하는 조건을 추가
				sql += " and dbname=?";
				// dbCount의 길이만큼 반복
				for (int i = 1; i < dbCount.length; i++) {
					sql += " or dbname=?";
				}
			}
			
			// 검색조건에 툴/프레임워크가 있으면
			if (tfwCount != null) {
				// 쿼리에 툴/프레임워크를 검색하는 조건을 추가
				sql += " and framename=?";
				// tfwCount의 길이만큼 반복
				for (int i = 1; i < tfwCount.length; i++) {
					sql += " or framename=?";
				}
			}
			
			// 검색조건에 기간이 있으면
			if (time1 != null && !time1.equals("")) {
				// 쿼리에 기간을 검색하는 조건을 추가
				sql += " and projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d')";
			}
			
			// 쿼리에 검색결과를 프로젝트 번호를 기준을 중복 제거하는 조건을 추가
			sql += " group by projnum";
			// 멤버변수 sql에 현재 쿼리를 저장
			this.sql = sql;

			// 정렬기준에 따라 쿼리에 조건을 추가
			if (order == null || order.equals("")) {
				sql += " order by projnum desc LIMIT ?, 10";
			} else if (order.equals("남은기간")) {
				sql += " order by remaindays asc LIMIT ?, 10";
			} else if (order.equals("등록일")) {
				sql += " order by projregisterdate desc LIMIT ?, 10";
			} else if (order.equals("시작일")) {
				sql += " order by projstartdate asc LIMIT ?, 10";
			} else if (order.equals("종료(예정)일")) {
				sql += " order by projenddate desc LIMIT ?, 10";
			}
			// 쿼리가 올바르게 작성되었는지 확인하기 위해서 콘솔창에 출력
			System.out.println("언어부터 = " + sql);
			
		// 검색조건에 프로젝트명, 개발분야, 언어가 없고 데이터베이스가 있으면
		} else if ((projName == null || projName.equals("")) && devCount == null && langCount == null
				&& dbCount != null) {
			// 기본 쿼리에 데이터베이스르 검색하는 조건을 추가
			sql += " where dbname=?";
			// dbCount의 길이만큼 반복
			for (int i = 1; i < dbCount.length; i++) {
				sql += " or dbname=?";
			}
			
			// 검색조건에 툴/프레임워크가 있으면
			if (tfwCount != null) {
				// 쿼리에 툴/프레임워크를 검색하는 조건을 추가
				sql += " and framename=?";
				// tfwCount의 길이만큼 반복
				for (int i = 1; i < tfwCount.length; i++) {
					sql += " or framename=?";
				}
			}
			
			// 검색조건에 기간이 있으면
			if (time1 != null && !time1.equals("")) {
				// 쿼리에 기간을 검색하는 조건을 추가
				sql += " and projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d')";
			}
			
			// 쿼리에 검색결과를 프로젝트 번호를 기준으로 중복 제거하는 조건을 추가
			sql += " group by projnum";
			// 멤버변수 sql에 현재 쿼리를 저장
			this.sql = sql;

			// 정렬기준에 따라 쿼리에 조건을 추가
			if (order == null || order.equals("")) {
				sql += " order by projnum desc LIMIT ?, 10";
			} else if (order.equals("남은기간")) {
				sql += " order by remaindays asc LIMIT ?, 10";
			} else if (order.equals("등록일")) {
				sql += " order by projregisterdate desc LIMIT ?, 10";
			} else if (order.equals("시작일")) {
				sql += " order by projstartdate asc LIMIT ?, 10";
			} else if (order.equals("종료(예정)일")) {
				sql += " order by projenddate desc LIMIT ?, 10";
			}
			// 쿼리가 올바르게 작성되었는지 확인하기 위해 콘솔창에 출력
			System.out.println("DBMS부터 = " + sql);

		// 검색조건에 프로젝트명, 개발분야, 언어, 데이터베이스가 없고 툴/프레임워크가 있으면
		} else if ((projName == null || projName.equals("")) && devCount == null && langCount == null && dbCount == null
				&& tfwCount != null) {
			// 기본 쿼리에 툴/프레임워크를 검색하는 조건을 추가
			sql += " where framename=?";
			// tfwCount의 길이만큼 반복
			for (int i = 1; i < tfwCount.length; i++) {
				sql += " or framename=?";
			}
			
			// 검색조건에 기간이 있으면
			if (time1 != null && !time1.equals("")) {
				// 쿼릭에 기간을 검색하는 조건을 추가
				sql += " and projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d')";
			}
			
			// 쿼리에 검색결과를 프로젝트 번호를 기준으로 중복 제거하는 조건을 추가
			sql += " group by projnum";
			// 멤버변수 sql에 현재 쿼리를 저장
			this.sql = sql;

			// 정렬기준에 따라 쿼리에 조건을 추가
			if (order == null || order.equals("")) {
				sql += " order by projnum desc LIMIT ?, 10";
			} else if (order.equals("남은기간")) {
				sql += " order by remaindays asc LIMIT ?, 10";
			} else if (order.equals("등록일")) {
				sql += " order by projregisterdate desc LIMIT ?, 10";
			} else if (order.equals("시작일")) {
				sql += " order by projstartdate asc LIMIT ?, 10";
			} else if (order.equals("종료(예정)일")) {
				sql += " order by projenddate desc LIMIT ?, 10";
			}
			// 쿼리가 올바르게 작성되었는지 확인하기 위해서 콘솔창에 출력
			System.out.println("툴프레임부터 = " + sql);

		// 검색조건에 프로젝트명, 개발분야, 언어, 데이터베이스, 툴/프레임워크가 없고 기간이 있으면
		} else if ((projName == null || projName.equals("")) && devCount == null && langCount == null && dbCount == null
				&& tfwCount == null && time1 != null && !time1.equals("")) {
			// 기본 쿼리에 기간을 검색하는 조건을 추가하고 
			// 조회 결과를 프로젝트 번호를 기준으로 중복 제거하는 조건을 추가

			sql += " where projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d') group by projnum";

			// 정렬기준에 따라 쿼리에 조건을 추가
			if (order != null && order.equals("남은기간")) {
				sql += " order by remaindays asc LIMIT ?, 10";
			} else if (order.equals("등록일")) {
				sql += " order by projregisterdate desc LIMIT ?, 10";
			} else if (order.equals("시작일")) {
				sql += " order by projstartdate asc LIMIT ?, 10";
			} else if (order.equals("종료(예정)일")) {
				sql += " order by projenddate desc LIMIT ?, 10";
			} else if (order == null || order.equals("")) {
				sql += " order by projnum desc LIMIT ?, 10";
			}

			// 쿼리가 올바르게 작성되었는지 확인하기 위해 콘솔창에 출력
			System.out.println("기간부터 = " + sql);

		// 검색조건이 아무것도 없으면
		} else {
			// 쿼리에 검색결과를 프로젝트 번호를 기준으로 중복 제거하는 조건을 추가
			sql += " group by projnum";
			// 멤버변수 sql에 현재 쿼리를 저장
			this.sql = sql;
			
			// 정렬기준에 따라 쿼리에 조건을 추가
			if (order == null || order.equals("")) {
				sql += " order by projnum desc LIMIT ?, 10";
			} else if (order.equals("남은기간")) {
				sql += " order by remaindays asc LIMIT ?, 10";
			} else if (order.equals("등록일")) {
				sql += " order by projregisterdate desc LIMIT ?, 10";
			} else if (order.equals("시작일")) {
				sql += " order by projstartdate asc LIMIT ?, 10";
			} else if (order.equals("종료(예정)일")) {
				sql += " order by projenddate desc LIMIT ?, 10";
			}
		}

		// 최종 쿼리가 올바르게 작성되었는지 확인하기 위해 콘솔창에 출력
		System.out.println("최종쿼리 = " + sql);

		
		// 필요한 객체 생성
		List<ProjectView> list = new ArrayList<ProjectView>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProjectView pjv = null;

		try {
			// 커넥션 연결
			conn = DBManager.getConnection();
			
			// 작성된 쿼리 준비
			pstmt = conn.prepareStatement(sql);

			// ?의 개수에 따라 순차적으로 번호를 증가시키기 위한 변수 선언 및 초기화
			int cnt = 1;

			// 각 매개변수가 null이 아니면 변수들의 길이만큼 반복
			if (devCount != null) { 
				for (String devTemp : devCount) {
					pstmt.setString(cnt++, devTemp);
				}
			}
			if (langCount != null) {
				for (String langTemp : langCount) {
					pstmt.setString(cnt++, langTemp);
				}
			}
			if (dbCount != null) {
				for (String dbTemp : dbCount) {
					pstmt.setString(cnt++, dbTemp);
				}
			}
			if (tfwCount != null) {
				for (String tfwTemp : tfwCount) {
					pstmt.setString(cnt++, tfwTemp);
				}
			}
			if (time1 != null && !time1.equals("")) {
				pstmt.setString(cnt++, time1);
				pstmt.setString(cnt++, time2);
			}
			
			// 결과에서 몇번째 행 부터 보열줄 것이지 결정
			pstmt.setInt(cnt, (pageNum - 1) * 10);

			// 쿼리 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pjv = new ProjectView();
				pjv.setProjNum(rs.getInt("projnum"));
				pjv.setIsExtern(rs.getBoolean("isextern"));
				pjv.setProjDevelopSort(rs.getString("projdevelopsort"));
				pjv.setProjEndDate(rs.getString("projenddate"));
				pjv.setProjExpectedTime(rs.getInt("projexpectedtime"));
				pjv.setProjField(rs.getString("projfield"));
				pjv.setProjName(rs.getString("projname"));
				pjv.setProjPartner(rs.getString("projpartner"));
				pjv.setProjPlan(rs.getString("projplan"));
				pjv.setProjRecruitEndDate(rs.getString("projRecruitEndDate"));
				pjv.setProjRecruitStartDate(rs.getString("projRecruitStartDate"));
				pjv.setProjRegisterDate(rs.getString("projregisterdate"));
				pjv.setProjRegisterer(rs.getString("projRegisterer"));
				pjv.setProjReviseDate(rs.getString("projrevisedate"));
				pjv.setProjReviser(rs.getString("projreviser"));
				pjv.setProjStartDate(rs.getString("projstartdate"));
				pjv.setProjState(rs.getString("projstate"));
				pjv.setProjTarget(rs.getString("projtarget"));
				pjv.setDbNum(rs.getInt("dbnum"));
				pjv.setUsedDbName(rs.getString("dbname"));
				pjv.setExpectedEndDate(rs.getDate("expectedenddate"));
				pjv.setRemainDays(rs.getInt("remaindays"));
				pjv.setJoinFLCount(rs.getInt("joinfreecount"));

				list.add(pjv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 전체 정보를 검색하는 쿼리를 담은 멤버변수 sql을 이용하여 전체 결과가 몇개인지 count하여 페이징 하는 메소드
	 * 
	 * searchAllCheckedProject() 메소드와 함께 사용하기 위해서 같인 매개변수를 받음
	 * 
	 * @param projName
	 * @param devCount
	 * @param langCount
	 * @param dbCount
	 * @param tfwCount
	 * @param time1
	 * @param time2
	 * @param pageNum
	 * @return
	 */
	public ParamInt projectPaging(String projName, String[] devCount, String[] langCount, String[] dbCount,
			String[] tfwCount, String time1, String time2, int pageNum) {
		
		// 전체 컬럼의 수를 얻는 쿼리
		String sql = "select count(*) as rowcount from " + "(" + this.sql + ") as lastsql";

		// 쿼리가 올바르게 작성 되었는지 콘솔창에 출력하여 확인
		System.out.println("전체 컬럼수 계산 쿼리 = " + sql);

		// 페이징 처리를 위한 객체(ParamInt) 생성
		ParamInt paramInt = new ParamInt();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			int sum = 1;

			// 각 매개변수가 null이 아니면 변수들의 길이만큼 반복
			if (devCount != null) {
				for (String devTemp : devCount) {
					pstmt.setString(sum++, devTemp);
				}
			}
			if (langCount != null) {
				for (String langTemp : langCount) {
					pstmt.setString(sum++, langTemp);
				}
			}
			if (dbCount != null) {
				for (String dbTemp : dbCount) {
					pstmt.setString(sum++, dbTemp);
				}
			}
			if (tfwCount != null) {
				for (String tfwTemp : tfwCount) {
					pstmt.setString(sum++, tfwTemp);
				}
			}
			if (time1 != null && !time1.equals("")) {
				pstmt.setString(sum++, time1);
				pstmt.setString(sum, time2);
			}

			// 쿼리 실행
			rs = pstmt.executeQuery();

			// 실행 결과를 paramInt 객체의 rowCount에 저장
			while (rs.next()) {
				paramInt.setRowCount(rs.getInt("rowcount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		// paramInt의 rowCount 값을 받아서 전체 페이지 수를 구한 후 pageCount 변수에 저장
		paramInt.setPageCount(((paramInt.getRowCount() - 1) / 10) + 1);
		
		// 현재 페이지 번호로 첫번째 페이지를 구하고 paramInt의 firstPage 변수에 저장 
		paramInt.setFirstPage(((pageNum - 1) / 10) * 10 + 1);
		
		// paraInt의 firstPage에 9를 더한 값과 전체 페이지 수 중에서 작은 것을 lastPage에 저장  
		paramInt.setLastPage(Math.min(paramInt.getFirstPage() + 9, paramInt.getPageCount()));

		return paramInt;
	}

	/**
	 * 프로젝트 번호로 해당 프로젝트에 참여한 프리랜서의 정보를 얻는 메소드
	 * 
	 * JoinProj 클래스에는 프리랜서에 관련된 변수가 없으므로 Freelancer클래스를 사용하여 
	 * 프리랜서의 정보를 저장한다.
	 * 
	 * @param projNum
	 * @return
	 */
	public List<Freelancer> proJoinFreeListByProjNum(int projNum) {
		// 프로젝트 번호를 매개변수로 받아서 조회하는 쿼리
		String sql = "select * from projoinfree_view where projnum=?";

		List<Freelancer> list = new ArrayList<Freelancer>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Freelancer free = new Freelancer();

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				free.setFreeId(rs.getString("freeid"));
				free.setFreeName(rs.getString("freename"));
				free.setFreePhone(rs.getString("freephone"));
				free.setFreeEmail(rs.getString("freeemail"));

				list.add(free);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 프로젝트 번호로 해당 프로젝트의 참여 정보를 얻는 메소드
	 * 
	 * @param projNum
	 * @return
	 */
	public List<JoinProj> proJoinListByProjNum(int projNum) {
		// 프로젝트 번호를 매개변수로 받아서 조회하는 쿼리
		String sql = "select * from projoinfree_view where projnum=?";

		List<JoinProj> list = new ArrayList<JoinProj>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JoinProj pjoin = new JoinProj();

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pjoin.setFreeId(rs.getString("freeid"));
				pjoin.setProjNum(rs.getInt("projnum"));
				pjoin.setProjRole(rs.getString("projrole"));
				pjoin.setJoinProjDate(rs.getString("joinprojdate"));
				pjoin.setExitProjDate(rs.getString("exitprojdate"));
				pjoin.setFreeState(rs.getString("freestate"));

				list.add(pjoin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 프로젝트 번호로 검색할 때 그 결과를 페이징 하기위한 메소드
	 * @param pageNum
	 * @param projNum
	 * @return
	 */
	public ParamInt proJoinFreePaging(int pageNum, int projNum) {
		
		// 프로젝트번호를 매개변수로 받아 조회한 후 그 결과의 전체 컬럼 수를 구하는 쿼리
		String sql = "select count(*) as rowcount from projoinfree_view where projNum=?";
		System.out.println("전체 컬럼수 계산 쿼리 = " + sql);

		ParamInt paramInt = new ParamInt();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				paramInt.setRowCount(rs.getInt("rowcount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		paramInt.setPageCount(((paramInt.getRowCount() - 1) / 10) + 1);
		paramInt.setFirstPage(((pageNum - 1) / 10) * 10 + 1);
		paramInt.setLastPage(Math.min(paramInt.getFirstPage() + 9, paramInt.getPageCount()));

		return paramInt;
	}
	
	/**
	 * 새로운 프로젝트를 등록할 때 등록 화면에 새로운 프로젝트 번호를 출력하기 위해
	 * project 테이블에서 자동으로 생성된 최신 번호를 가져오는 메소드
	 * 
	 * @return
	 */
	public Project selctNewProjNum() {
		
		// project 테이블에서 최신 번호를 가져오는 쿼리
		String sql = "SELECT AUTO_INCREMENT as newprojnum FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'fms' AND TABLE_NAME = 'project'";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Project proj = new Project();

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				proj.setProjNum(rs.getInt("newprojnum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return proj;
	}

	/**
	 * 프로젝트를 등록 할 때 매개변수로 받아온 정보를 project, projlang, projframework, projrole 테이블에 각각 등록하는 메소드
	 * 
	 * @param proj
	 * @param langCount
	 * @param tfwCount
	 * @param roleName
	 * @param requirement
	 */
	public void insertProject(Project proj, String[] langCount, String[] tfwCount, String[] roleName,
			int[] requirement) {
		/*
		 *  프로젝트에 필요한 언어, 프레임워크, 역할을 등록하는 테이블은 각각 따로 존재한다.
		 *  
		 *  프로젝트 언어, 프레임워크, 역할 정보들을 등록 할 때 신규 프로젝트가 등록된 번호를 왜래키로 등록해야한다.
		 *  
		 *  테이블에 정보를 등록하려면 총 4개의 테이블에 정보를 등록하는 쿼리를 실행해야 한다.
		 *  
		 *  이를 각각 다른 메소드로 만든다면 한명의 사용자가 프로젝트를 등록하기 위해서 4개의 메소드가 실행되어야 하고 
		 *  4번의 커넥션이 열리고 닫힌다.
		 *  
		 *  하나의 화면에서 프로젝트 등록 작업을 마치려면 프로젝트가 등록된 뒤에 등록된 프로젝트의 번호를 바로 가져와서
		 *  다른 테이블의 외래키로 사용해야 한다. 
		 *  
		 *  그러나 정보가 등록될 때 프로젝트 번호가 생성되기 때문에 여러명의 사용자가 같은 시간에 프로젝트를 등록 했을 때 
		 *  어떤 프로젝트가 먼저 등록되는지 알 수 없고 프로젝트의 번호 또한 알 수 없다
		 *  
		 *  이 문제를 해결하기 위해서 가장 마지막에 성공적으로 수행된 INSERT 구문의 PK 값을 가져오는 SELECT last_insert_id()를 사용하는데
		 *  
		 *  last_insert_id 값은 커넥션마다 따로 관리되어 사용자 A와 B가 서로 다른 커넥션으로 DB에 접속하고 동시에 INSERT 구문을 실행한 후 
		 *  last_insert_id() 값을 조회해도 자신이 INSERT한 id값을 얻는다.
		 *  
		 *  만약 4개의 메소드가 각각 실행된다면 4번의 커넥션이 열리고 닫히면서 last_insert_id()는 쓸모가 없어지게 된다.
		 *  
		 *  이때문에 하나의 커넥션에서 모든 정보들을 등록할 수 있도록 하나의 메소드에서 모든 등록작업이 끝나게 해야한다.
		 */
		
		// 매개변수 proj로 받아온 프로젝트 정보를 project테이블에 등록하는 쿼리
		String projSql = "insert into project(isextern, projfield, projname, projstate, projregisterdate, projregisterer, projstartdate, projexpectedtime, projtarget, projpartner, projplan, projrecruitstartdate, projrecruitenddate, projdevelopsort, dbnum) "
				+ "values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// 가장 마지막에 등록된 id(프로젝트 번호)와 언어번호를 projlang 테이블에 등록하는 쿼리
		String pjlangSql = "insert into projlang (projnum, langnum) values((select last_insert_id()), ?)";
		
		// 가장 마지막에 등록된 id(프로젝트 번호)와 매개변수로 받은 프레임워크 번호를 projframework 테이블에 등록하는 쿼리
		/**
		 * 가장 마지막에 수행된 INSERT 구문은 pjlangSql이지만 projlang 테이블의 PK는 프로젝트 번호와 언어번호를 합친 합성키라서
		 * 둘 중 하나를 PK로 특정 할 수 없어 last_insert_id() 값이 변경되지 않는다.
		 */
		String pjframeSql = "insert into projframework (projnum, framenum) values((select last_insert_id()), ?)";
		
		// 마찬가지로 projframework의 PK도 프로젝트 번호와 프레임워크 번호를 합친 합성키이므로 둘 중 하나를 특정할 수 없어
		// last_insert_id() 값이 변경되지 않는다
		String pjroleSql1 = "insert into projrole(rolename, requirenum, projnum) values(?, ?, (select last_insert_id()))";
		
		// 프로젝트 번호를 찾기 위해 projrole 테이블에 마지막으로 등록된 행에서 프로젝트 번호를 조회하여 매개변수로 받은 다른 정보와 함께 등록하는 쿼리
		String pjroleSql2 = "insert into projrole(rolename, requirenum, projnum) values(?, ?, (select projnum from projrole prole where rolenum=(select last_insert_id())))";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 커넥션 연결
			conn = DBManager.getConnection();
			
			// 프로젝트를 등록하는 쿼리 준비
			pstmt = conn.prepareStatement(projSql);

			pstmt.setString(1, proj.getProjField());
			pstmt.setString(2, proj.getProjName());
			pstmt.setString(3, proj.getProjState());
			pstmt.setString(4, proj.getProjRegisterDate());
			pstmt.setString(5, proj.getProjRegisterer());
			pstmt.setString(6, proj.getProjStartDate());
			pstmt.setInt(7, proj.getProjExpectedTime());
			pstmt.setString(8, proj.getProjTarget());
			pstmt.setString(9, proj.getProjPartner());
			pstmt.setString(10, proj.getProjPlan());
			pstmt.setString(11, proj.getProjRecruitStartDate());
			pstmt.setString(12, proj.getProjRecruitEndDate());
			pstmt.setString(13, proj.getProjDevelopSort());
			pstmt.setInt(14, proj.getDbNum());

			// 쿼리 실행
			pstmt.executeUpdate();
			
			pstmt.close();

			// langCount의 길이만큼 쿼리를 반복하여 실행
			for (int i = 0; i < langCount.length; i++) {
				pstmt = conn.prepareStatement(pjlangSql);
				pstmt.setInt(1, Integer.parseInt(langCount[i]));
				pstmt.executeUpdate();
			}
			pstmt.close();

			// tfwCount의 길이만큼 쿼리를 반복하여 실행
			for (int i = 0; i < tfwCount.length; i++) {
				pstmt = conn.prepareStatement(pjframeSql);
				pstmt.setInt(1, Integer.parseInt(tfwCount[i]));
				pstmt.executeUpdate();
			}
			pstmt.close();

			// 첫번째 역할의 이름과 필요인원을 등록하는 쿼리를 준비하고 실행
			pstmt = conn.prepareStatement(pjroleSql1);
			pstmt.setString(1, roleName[0]);
			pstmt.setInt(2, requirement[0]);
			pstmt.executeUpdate();
			pstmt.close();

			// 첫번째 역할 다음 역할을 등록하는 쿼리를 반복하여 실행
			for (int i = 1; i < roleName.length; i++) {
				pstmt = conn.prepareStatement(pjroleSql2);
				pstmt.setString(1, roleName[i]);
				pstmt.setInt(2, requirement[i]);
				pstmt.executeUpdate();
			}
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			DBManager.close(conn, pstmt);
		}
	}

}

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

public class ProjectViewDAO {
	public static ProjectViewDAO instance = new ProjectViewDAO();

	public static ProjectViewDAO getInstance() {
		return instance;
	}

	public List<Project> selectProjectList() {
		String sql = "select * from project";

		List<Project> list = new ArrayList<Project>();

		Project pro = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				pro = new Project();
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

				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	public List<DBMS> selectProjectDBMSList() {
		String sql = "select distinct * from projdbmsview";

		List<DBMS> list = new ArrayList<DBMS>();

		DBMS db = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
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

	// 프로젝트에 사용된 언어
	public List<ProgLang> selectProjLangList() {
		String sql = "select distinct * from projlangview";

		List<ProgLang> list = new ArrayList<ProgLang>();

		ProgLang plang = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				plang = new ProgLang();
				/* plang.setLangNum(rs.getInt("langnum")); */
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

	// 프로젝트에 사용된 프레임워크 전체 출력
	public List<Framework> selectProjFameworkList() {
		String sql = "select distinct * from projframeview";

		List<Framework> list = new ArrayList<Framework>();

		Framework fwk = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				fwk = new Framework();
				/* fwk.setFrameNum(rs.getInt("framenum")); */
				fwk.setFrameName(rs.getString("framename"));
				/*
				 * fwk.setFrameDevelopField(rs.getString("framedevelopfield"));
				 */
				list.add(fwk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	public List<ProjectView> selectAllProjectInfo() {
		String sql = "select * from projoinfreecount_view";

		List<ProjectView> proViewList = new ArrayList<ProjectView>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ProjectView pjv = null;

		try {
			conn = DBManager.getConnection();

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

	public List<ProjectView> selectAllProjectInfoOderByRemains() {
		String sql = "select * from projoinfreecount_view order by remaindays asc";

		List<ProjectView> proViewList = new ArrayList<ProjectView>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ProjectView pjv = null;

		try {
			conn = DBManager.getConnection();

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

	public List<ProjectView> selectAllProjectInfoOderByRegiDate() {
		String sql = "select * from projoinfreecount_view order by projregisterdate desc";

		List<ProjectView> proViewList = new ArrayList<ProjectView>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ProjectView pjv = null;

		try {
			conn = DBManager.getConnection();

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

	public List<ProjectView> selectAllProjectInfoOderByStartDate() {
		String sql = "select * from projoinfreecount_view order by projstartdate asc";

		List<ProjectView> proViewList = new ArrayList<ProjectView>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ProjectView pjv = null;

		try {
			conn = DBManager.getConnection();

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

	public List<ProjectView> selectAllProjectInfoOderByEndDate() {
		String sql = "select * from projoinfreecount_view order by projenddate desc";

		List<ProjectView> proViewList = new ArrayList<ProjectView>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ProjectView pjv = null;

		try {
			conn = DBManager.getConnection();

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

	String sql;

	public List<ProjectView> searchAllCheckedProject(String projName, String[] devCount, String[] langCount,
			String[] dbCount, String[] tfwCount, String time1, String time2, String order, int pageNum) {

		String sql = "select * from projoinfreeframelang_view";

		System.out.println("dao 프로젝트명 = " + projName);
		System.out.println("dao 분야 = " + devCount);
		System.out.println("dao 언어 = " + langCount);
		System.out.println("dao 데이터베이스 = " + dbCount);
		System.out.println("dao 툴프레임 = " + tfwCount);
		System.out.println("dao 시간1 = " + time1);
		System.out.println("dao 시간2 = " + time2);

		if (projName != null && !projName.equals("")) {

			sql += " where projname like '%" + projName + "%'";

			if (devCount != null) {
				sql += " and projdevelopsort = ?";
				for (int i = 1; i < devCount.length; i++) {
					sql += " or projdevelopsort = ?";
				}
			}
			if (langCount != null) {
				sql += " and langname =?";
				for (int i = 1; i < langCount.length; i++) {
					sql += " or langname =?";
				}
			}
			if (dbCount != null) {
				sql += " and dbname=?";
				for (int i = 1; i < dbCount.length; i++) {
					sql += " or dbname=?";
				}
			}
			if (tfwCount != null) {
				sql += " and framename=?";
				for (int i = 1; i < tfwCount.length; i++) {
					sql += " or framename=?";
				}
			}
			if (time1 != null && !time1.equals("")) {
				sql += " and projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d')";
			}
			sql += " group by projnum";
			this.sql = sql;

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

			System.out.println("프로젝트명부터 = " + sql);

		} else if ((projName == null || projName.equals("")) && devCount != null) {

			sql += " where projdevelopsort =?";

			for (int i = 1; i < devCount.length; i++) {
				sql += " or projdevelopsort =?";
			}

			if (langCount != null) {
				sql += " and langname =?";
				for (int i = 1; i < langCount.length; i++) {
					sql += " or langname =?";
				}
			}
			if (dbCount != null) {
				sql += " and dbname=?";
				for (int i = 1; i < dbCount.length; i++) {
					sql += " or dbname=?";
				}
			}
			if (tfwCount != null) {
				sql += " and framename=?";
				for (int i = 1; i < tfwCount.length; i++) {
					sql += " or framename=?";
				}
			}
			if (time1 != null && !time1.equals("")) {
				sql += " and projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d')";
			}
			sql += " group by projnum";
			this.sql = sql;

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
			System.out.println("개발분야부터 = " + sql);

		} else if ((projName == null || projName.equals("")) && devCount == null && langCount != null) {
			sql += " where langname=?";

			for (int i = 1; i < langCount.length; i++) {
				sql += " or langname =?";
			}
			if (dbCount != null) {
				sql += " and dbname=?";
				for (int i = 1; i < dbCount.length; i++) {
					sql += " or dbname=?";
				}
			}
			if (tfwCount != null) {
				sql += " and framename=?";
				for (int i = 1; i < tfwCount.length; i++) {
					sql += " or framename=?";
				}
			}
			if (time1 != null && !time1.equals("")) {
				sql += " and projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d')";
			}
			sql += " group by projnum";
			this.sql = sql;

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
			System.out.println("언어부터 = " + sql);

		} else if ((projName == null || projName.equals("")) && devCount == null && langCount == null
				&& dbCount != null) {
			sql += " where dbname=?";

			for (int i = 1; i < dbCount.length; i++) {
				sql += " or dbname=?";
			}
			if (tfwCount != null) {
				sql += " and framename=?";
				for (int i = 1; i < tfwCount.length; i++) {
					sql += " or framename=?";
				}
			}
			if (time1 != null && !time1.equals("")) {
				sql += " and projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d')";
			}
			sql += " group by projnum";
			this.sql = sql;

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
			System.out.println("DBMS부터 = " + sql);

		} else if ((projName == null || projName.equals("")) && devCount == null && langCount == null && dbCount == null
				&& tfwCount != null) {
			sql += " where framename=?";

			for (int i = 1; i < tfwCount.length; i++) {
				sql += " or framename=?";
			}
			if (time1 != null && !time1.equals("")) {
				sql += " and projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d')";
			}
			sql += " group by projnum";
			this.sql = sql;

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
			System.out.println("툴프레임부터 = " + sql);

		} else if ((projName == null || projName.equals("")) && devCount == null && langCount == null && dbCount == null
				&& tfwCount == null && time1 != null && !time1.equals("")) {
			sql += " where projregisterdate between date_format(?, '%Y-%m-%d') and date_format(?, '%Y-%m-%d') group by projnum";

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

			System.out.println("기간부터 = " + sql);

		} else {
			sql += " group by projnum";
			this.sql = sql;

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

		System.out.println("최종쿼리 = " + sql);

		List<ProjectView> list = new ArrayList<ProjectView>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProjectView pjv = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			int sum = 1;

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
				pstmt.setString(sum++, time2);
			}
			pstmt.setInt(sum, (pageNum - 1) * 10);

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

	public ParamInt projectPaging(String projName, String[] devCount, String[] langCount, String[] dbCount,
			String[] tfwCount, String time1, String time2, int pageNum) {
		String sql = "select count(*) as rowcount from " + "(" + this.sql + ") as lastsql";

		System.out.println("전체 컬럼수 계산 쿼리 = " + sql);

		ParamInt paramInt = new ParamInt();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			int sum = 1;

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

	public List<Freelancer> proJoinFreeListByProjNum(int projNum) {
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

	public List<JoinProj> proJoinListByProjNum(int projNum) {
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

	public ParamInt proJoinFreePaging(int pageNum, int projNum) {
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

	public Project selctNewProjNum() {
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

	public void insertProject(Project proj, String[] langCount, String[] tfwCount, String[] roleName,
			int[] requirement) {
		String projSql = "insert into project(isextern, projfield, projname, projstate, projregisterdate, projregisterer, projstartdate, projexpectedtime, projtarget, projpartner, projplan, projrecruitstartdate, projrecruitenddate, projdevelopsort, dbnum) values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String pjlangSql = "insert into projlang (projnum, langnum) values((select last_insert_id()), ?);";
		String pjframeSql = "insert into projframework (projnum, framenum) values((select last_insert_id()), ?)";
		String pjroleSql1 = "insert into projrole(rolename, requirenum, projnum) values(?, ?, (select last_insert_id()))";
		String pjroleSql2 = "insert into projrole(rolename, requirenum, projnum) values(?, ?, (select projnum from projrole prole where rolenum=(select last_insert_id())))";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
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
			
			pstmt.executeUpdate();
			pstmt.close();

			for (int i = 0; i < langCount.length; i++) {
				pstmt = conn.prepareStatement(pjlangSql);
				pstmt.setInt(1, Integer.parseInt(langCount[i]));
				pstmt.executeUpdate();
			}
			pstmt.close();

			for (int i = 0; i < tfwCount.length; i++) {
				pstmt = conn.prepareStatement(pjframeSql);
				pstmt.setInt(1, Integer.parseInt(tfwCount[i]));
				pstmt.executeUpdate();
			}
			pstmt.close();

			pstmt = conn.prepareStatement(pjroleSql1);
			pstmt.setString(1, roleName[0]);
			pstmt.setInt(2, requirement[0]);
			pstmt.executeUpdate();
			pstmt.close();

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
			DBManager.close(conn, pstmt);
		}
	}
	
	/*public List<ProgLang> selectLangByProjNum(int projNum){
		String sql="select langname from projoinfreeframelang_view where projnum=? group by langname";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ProgLang> list = new ArrayList<ProgLang>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,projNum);
			rs = pstmt.executeQuery();
			
			ProgLang plang = new ProgLang();
			while(rs.next()) {
				plang.setLangName(rs.getString("langname"));
				
				list.add(plang);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<Framework> selectFrameByProjNum(int projNum){
		String sql="select framename from projoinfreeframelang_view where projnum=? group by framename";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Framework> list = new ArrayList<Framework>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();
			
			Framework fw = new Framework();
			while(rs.next()) {
				fw.setFrameName(rs.getString("framename"));
				
				list.add(fw);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}*/

}

package ccm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ccm.data.table.Project;
import ccm.util.DBManager;

public class ProjectDAO {
	public static ProjectDAO instance = new ProjectDAO();

	public static ProjectDAO getInstance() {
		return instance;
	}

	public List<Project> selectAllProject() {
		String sql = "select projnum,isextern,projfield,projname,projstate,PROJREGISTERDATE,PROJREGISTERER,PROJREVISEDATE,PROJREVISER,projstartdate,projenddate,projexpectedtime,projtarget,projpartner,projplan,projrecruitstartdate,projdevelopsort,dbnum from project";

		List<Project> list = new ArrayList<Project>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Project pVo = new Project();

				pVo.setProjNum(rs.getInt("projnum"));
				pVo.setIsExtern(rs.getBoolean("isextern"));
				pVo.setProjField(rs.getString("projfield"));
				pVo.setProjName(rs.getString("projname"));
				pVo.setProjState(rs.getString("projstate"));
				pVo.setProjRegisterDate(rs.getString("PROJREGISTERDATE"));
				pVo.setProjRegisterer(rs.getString("PROJREGISTERER"));
				pVo.setProjReviseDate(rs.getString("PROJREVISEDATE"));
				pVo.setProjReviser(rs.getString("PROJREVISER"));
				pVo.setProjStartDate(rs.getString("projstartdate"));
				pVo.setProjEndDate(rs.getString("projenddate"));
				pVo.setProjExpectedTime(rs.getInt("projexpectedtime"));
				pVo.setProjTarget(rs.getString("projtarget"));
				pVo.setProjPartner(rs.getString("projpartner"));
				pVo.setProjPlan(rs.getString("projplan"));
				pVo.setProjRecruitStartDate(rs.getString("projrecruitstartdate"));
				pVo.setProjRecruitEndDate(rs.getString("projrecruitstartdate"));
				pVo.setProjDevelopSort(rs.getString("projdevelopsort"));
				pVo.setDbNum(rs.getInt("dbnum"));

				list.add(pVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
}

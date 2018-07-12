package ccm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import ccm.data.AllOfEuclidProject;
import ccm.data.FreeIdFreeNameStartEndDate;
import ccm.data.FreelancerListToSearch;
import ccm.data.JoinAppliedFreelancer;
import ccm.data.Page;
import ccm.data.ParamInt;
import ccm.data.ProjJoinedFreelancer;
import ccm.data.ProjJoinedFreelancerForList;
import ccm.data.ProjListForSearch;
import ccm.data.ProjectRecruitState;
import ccm.data.RecommendedFreelancer;
import ccm.data.function.ProjUtil;
import ccm.data.table.Employee;
import ccm.data.table.Framework;
import ccm.data.table.ProgLang;
import ccm.data.table.ProjRole;
import ccm.util.DBManager;

public class ProjectAdministrationDAO
{
	private static ProjectAdministrationDAO instance = new ProjectAdministrationDAO();
	private ProjectAdministrationDAO() { super(); }
	public static ProjectAdministrationDAO getInstance() { return instance; }
	
	public int getMaxProjNum()
	{
		int projNum = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("SELECT MAX(PROJNUM) AS PROJNUM FROM PROJECT WHERE ISEXTERN = 0");
			rs = pstmt.executeQuery();
			while(rs.next())
				projNum = rs.getInt("PROJNUM");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		
		return projNum;
	}
	
	public AllOfEuclidProject getProjectDetail(int projNum) //프로젝트 디테일을 가져오는 메소드
	{
		AllOfEuclidProject projectDetail = null;
		
		String projectListSql = "SELECT * FROM PROJECT WHERE ISEXTERN = 0 AND PROJNUM = ?";
		String frameSql = "SELECT FRAMENUM, FRAMENAME, FRAMEDEVELOPFIELD"
				+ " FROM PROJFRAMENAME WHERE PROJNUM = ?";
		String langSql = "SELECT LANGNUM, LANGNAME"
				+ " FROM PROJLANGNAME WHERE PROJNUM = ?";
		String DBSql = "SELECT DBNUM, DBNAME FROM PROJDBMSNAME WHERE PROJNUM = ?";
		String regiSql = "SELECT EMPID, EMPPW, EMPDUTY, EMPNAME, EMPDEPT, EMPPICTURE,"
				+ " EMPPHONE, EMPEMAIL, EMPJOINDATE, EMPBIRTH,"
				+ " EMPSEX, EMPMARRIED, EMPFRONTADDR, EMPDROPDATE, EMPREARADDR, EMPBANK,"
				+ " EMPACCNAME, EMPACCOUNT, EMPAUTH FROM PROJREGISTERERNAME WHERE PROJNUM = ?";
		String reviSql = "SELECT EMPID, EMPPW, EMPDUTY, EMPNAME, EMPDEPT, EMPPICTURE,"
				+ " EMPPHONE, EMPEMAIL, EMPJOINDATE, EMPDROPDATE, EMPBIRTH,"
				+ " EMPSEX, EMPMARRIED, EMPFRONTADDR, EMPREARADDR, EMPBANK,"
				+ " EMPACCNAME, EMPACCOUNT, EMPAUTH FROM PROJREVISERNAME WHERE PROJNUM = ?";
		String roleSql = "SELECT PROJNUM, ROLENUM, ROLENAME, REQUIRENUM"
				+ " FROM PROJREQUIREROLENAME WHERE PROJNUM = ?";
		String joinedFreeSql = "SELECT PROJNUM, JOINNUM, FREEID, FREENAME, PROJROLE, JOINPROJDATE, "
				+ " EXITPROJDATE, FREEPHONE, FREEEMAIL FROM PROJJOINEDFREELANCER WHERE PROJNUM = ?";
		
		Connection conn = null;
		
		PreparedStatement projectListPstmt = null;
		PreparedStatement DBMSPstmt = null;
		PreparedStatement langPstmt = null;
		PreparedStatement framePstmt = null;
		PreparedStatement registererPstmt = null;
		PreparedStatement reviserPstmt = null;
		PreparedStatement rolePstmt = null;
		PreparedStatement joinedFreePstmt = null;
		ResultSet projectRs = null;
		ResultSet DBMSRs = null;
		ResultSet langRs = null;
		ResultSet frameRs = null;
		ResultSet registererRs = null;
		ResultSet reviserRs = null;
		ResultSet roleRs = null;
		ResultSet joinedFreeRs = null;
		
		try
		{
			conn = DBManager.getConnection();
			projectListPstmt = conn.prepareStatement(projectListSql);
			System.out.println(projectListPstmt);
			System.out.println(projNum);
			projectListPstmt.setInt(1, projNum);
			projectRs = projectListPstmt.executeQuery();
			
			while(projectRs.next())
			{
				projectDetail = new AllOfEuclidProject();
				projectDetail.setParams(projectRs);
				
				DBMSPstmt = conn.prepareStatement(DBSql);
				DBMSPstmt.setInt(1, projectDetail.getProjNum());
				DBMSRs = DBMSPstmt.executeQuery();
				while(DBMSRs.next())
					projectDetail.getProjDBMSInfo().setParams(DBMSRs);
				if(DBMSPstmt != null)
					DBMSPstmt.close();
				if(DBMSRs != null)
					DBMSRs.close();
				
				langPstmt = conn.prepareStatement(langSql);
				langPstmt.setInt(1, projectDetail.getProjNum());
				langRs = langPstmt.executeQuery();
				while(langRs.next())
				{
					ProgLang pl = new ProgLang();
					pl.setParams(langRs);
					projectDetail.getProjProgLangs().add(pl);
				}
				if(langPstmt != null)
					langPstmt.close();
				if(langRs != null)
					langRs.close();
				
				rolePstmt = conn.prepareStatement(roleSql);
				rolePstmt.setInt(1, projectDetail.getProjNum());
				roleRs = rolePstmt.executeQuery();
				while(roleRs.next())
				{
					ProjRole pr = new ProjRole();
					pr.setParams(roleRs);
					projectDetail.getProjRequireRoles().add(pr);
				}
				if(rolePstmt != null)
					rolePstmt.close();
				if(roleRs != null)
					roleRs.close();
				
				framePstmt = conn.prepareStatement(frameSql);
				framePstmt.setInt(1, projectDetail.getProjNum());
				frameRs = framePstmt.executeQuery();
				while(frameRs.next())
				{
					Framework fw = new Framework();
					fw.setParams(frameRs);
					projectDetail.getProjFrameworks().add(fw);
				}
				if(framePstmt != null)
					framePstmt.close();
				if(frameRs != null)
					frameRs.close();
				
				registererPstmt = conn.prepareStatement(regiSql);
				registererPstmt.setInt(1, projectDetail.getProjNum());
				registererRs = registererPstmt.executeQuery();
				while(registererRs.next())
				{
					Employee emp = new Employee();
					emp.setParams(registererRs);
					projectDetail.setProjRegistererInfo(emp);
				}
				if(registererPstmt != null)
					registererPstmt.close();
				if(registererRs != null)
					registererRs.close();
				
				reviserPstmt = conn.prepareStatement(reviSql);
				reviserPstmt.setInt(1, projectDetail.getProjNum());
				reviserRs = reviserPstmt.executeQuery();
				while(reviserRs.next())
				{
					Employee emp = new Employee();
					emp.setParams(reviserRs);
					projectDetail.setProjReviserInfo(emp);
				}
				if(reviserPstmt != null)
					reviserPstmt.close();
				if(reviserRs != null)
					reviserRs.close();
				
				joinedFreePstmt = conn.prepareStatement(joinedFreeSql);
				joinedFreePstmt.setInt(1, projNum);
				joinedFreeRs = joinedFreePstmt.executeQuery();
				while(joinedFreeRs.next())
				{
					ProjJoinedFreelancer jp = new ProjJoinedFreelancer();
					System.out.println("jp가 있다");
					jp.setParams(joinedFreeRs);
					projectDetail.getProjJoinedFree().add(jp);
				}
				if(joinedFreePstmt != null)
					joinedFreePstmt.close();
				if(joinedFreeRs != null)
					joinedFreeRs.close();
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, projectListPstmt, projectRs);
		}
		
		return projectDetail;
	}
	
	public ProjListForSearch[] getProjList(String projName, String[] projDevelopSort, String[] langNums,
			String[] frameNums, String[] DBMSNums, String startDate, String endDate,
			int pageNum, String sorting, ParamInt out_numOfRow)
	{
		ProjListForSearch[] list = new ProjListForSearch[10];
		HashSet<Integer> projNums = new HashSet<Integer>();
		
		Connection conn = null;
		PreparedStatement wholeRowCountPstmt = null;
		PreparedStatement projListPstmt = null;
		PreparedStatement namePstmt = null;
		PreparedStatement langPstmt = null;
		PreparedStatement framePstmt = null;
		PreparedStatement DBMSPstmt = null;
		PreparedStatement periodPstmt = null;
		
		String wholeRowCountSql = "SELECT COUNT(*) AS C FROM PROJLISTFORSEARCH";
		
		//프로젝트 리스트를 페이지로 출력하려고 함
		String projListSql = "SELECT PROJNUM, PROJRECRUITSTATE, PROJSTATE,"
				+ " PROJNAME, PROJDEVELOPSORT, PROJSTARTDATE, PROJENDDATE, PROJLEFTTIME,"
				+ " JOINNUM, REQUIRENUM FROM PROJLISTFORSEARCH";
		
		//프로젝트 이름, 언어, 프레임워크, DBMS로 검색
		String nameSql = "SELECT PROJNUM FROM PROJECT";
		String langSql = "SELECT PROJNUM FROM PROJLANGNAME";
		String frameSql = "SELECT PROJNUM FROM PROJFRAMENAME";
		String DBMSSql = "SELECT PROJNUM FROM PROJDBMSNAME";
		//프로젝트 기간 검색하는 쿼리
		String periodSql = "SELECT PROJNUM FROM PROJECT WHERE\r\n" + 
				"  STR_TO_DATE(DATE_FORMAT(PROJSTARTDATE, '%Y-%m-%d'), '%Y-%m-%d')" + 
				"  >= STR_TO_DATE(?, '%Y-%m-%d') AND" + 
				"  STR_TO_DATE(DATE_FORMAT(PROJENDDATE, '%Y-%m-%d'), '%Y-%m-%d')" + 
				"  <= STR_TO_DATE(?, '%Y-%m-%d')";
		
		ResultSet wholeRowCountRs = null;
		ResultSet projListRs = null;
		ResultSet nameRs = null;
		ResultSet projLangRs = null;
		ResultSet projFrameRs = null;
		ResultSet projDBMSRs = null;
		ResultSet periodRs = null;
		
		//각각의 기술 번호로 검색하려고함
		if(langNums != null)
		{
			langSql += " WHERE LANGNUM IN(";
			for(int i = 0; i < langNums.length; i++)
			{
				System.out.print ( langNums [ i ] + " " );
				langSql += (i != langNums.length - 1)? ("? ,") : ("? )");
			}
			System.out.println();
			System.out.println(langSql);
		}
		if(DBMSNums != null)
		{
			DBMSSql += " WHERE DBNUM IN(";
			for(int i = 0; i < DBMSNums.length; i++)
			{
				DBMSSql += (i != DBMSNums.length - 1)? ("? ,") : ("? )");
			}
		}
		if(frameNums != null)
		{
			frameSql += " WHERE FRAMENUM IN(";
			for(int i = 0; i < frameNums.length; i++)
			{
				frameSql += (i != frameNums.length - 1)? ("? ,") : ("? )");
			}
		}
		
		try
		{
			conn = DBManager.getConnection();
			
			//일단 프로젝트 테이블 전체 로우의 개수를 받는다 ()
			wholeRowCountPstmt = conn.prepareStatement(wholeRowCountSql);
			wholeRowCountRs = wholeRowCountPstmt.executeQuery();
			while(wholeRowCountRs.next()) out_numOfRow.setIntValue(wholeRowCountRs.getInt("C"));
			if(wholeRowCountRs != null) wholeRowCountRs.close();
			if(wholeRowCountPstmt != null) wholeRowCountPstmt.close();
			
			boolean doSearch = false;
			
			//프로젝트 이름 검사
			if(projName != null && !projName.trim ().equals(""))
			{
				projName = "%" + projName + "%";
				nameSql += " WHERE PROJNAME LIKE ?";
				namePstmt = conn.prepareStatement(nameSql);
				namePstmt.setString(1, projName);
				System.out.println(namePstmt.toString());
				nameRs = namePstmt.executeQuery();
				while(nameRs.next()) projNums.add(nameRs.getInt("PROJNUM"));
				if(namePstmt != null) namePstmt.close();
				if(nameRs != null) nameRs.close();
				doSearch = true;
			}
			//프로젝트 사용언어 검사
			if(langNums != null)
			{
				langPstmt = conn.prepareStatement(langSql);
				for(int i = 1; i <= langNums.length; i++) 
					langPstmt.setInt(i, Integer.parseInt(langNums[i - 1]));
				System.out.println(langPstmt.toString());
				projLangRs = langPstmt.executeQuery();
				while(projLangRs.next()) projNums.add(projLangRs.getInt("PROJNUM"));
				if(langPstmt != null) langPstmt.close();
				if(projLangRs != null) projLangRs.close();
				doSearch = true;
			}
			//사용 DBMS 검사
			if(DBMSNums != null)
			{
				DBMSPstmt = conn.prepareStatement(DBMSSql);
				for(int i = 1; i <= DBMSNums.length; i++)
					DBMSPstmt.setInt(i, Integer.parseInt(DBMSNums[i - 1]));
				System.out.println(DBMSPstmt);
				projDBMSRs = DBMSPstmt.executeQuery();
				while(projDBMSRs.next()) projNums.add(projDBMSRs.getInt("PROJNUM"));
				if(DBMSPstmt != null) DBMSPstmt.close();
				if(projDBMSRs != null) projDBMSRs.close();
				doSearch = true;
			}
			//사용 프레임워크 검사
			if(frameNums != null)
			{
				framePstmt = conn.prepareStatement(frameSql);
				for(int i = 1; i <= frameNums.length; i++)
					framePstmt.setInt(i, Integer.parseInt(frameNums[i - 1]));
				System.out.println(framePstmt.toString());
				projFrameRs = framePstmt.executeQuery();
				while(projFrameRs.next()) projNums.add(projFrameRs.getInt("PROJNUM"));
				if(framePstmt != null) framePstmt.close();
				if(projFrameRs != null) projFrameRs.close();
				doSearch = true;
			}
			if(( startDate != null && !startDate.trim().equals(""))
					&& ( endDate != null && !endDate.trim ().equals("")) )
			{
				periodPstmt = conn.prepareStatement(periodSql);
				periodPstmt.setString(1, startDate);
				periodPstmt.setString(2, endDate);
				System.out.println(periodPstmt.toString());
				periodRs = periodPstmt.executeQuery();
				while(periodRs.next()) projNums.add(periodRs.getInt("PROJNUM"));
				if(periodPstmt != null) periodPstmt.close();
				if(periodRs != null) periodRs.close();
				doSearch = true;
			}
			
			//여태까지 모은 프로젝트넘버를 모두 SQL에 갖다넣음
			if(projNums.size() != 0) //검색조건을 넣었으면
			{
				projListSql += " WHERE PROJNUM IN(";
				int count = 1;
				for(; count <= projNums.size(); count++)
				{
					projListSql += (count != projNums.size())? ("? ,") : ("? )");
				}
				projListSql += " ORDER BY "+ sorting +" DESC LIMIT ?, 10";
				System.out.println(projListSql);
				projListPstmt = conn.prepareStatement(projListSql);
				
				int i = 1;
				for(int projNum : projNums)
				{
					projListPstmt.setInt(i, projNum);
					i++;
				}
				
				projListPstmt.setInt(i++, (pageNum - 1) * 10);
				
				System.out.println(projListPstmt.toString());
				
				projListRs = projListPstmt.executeQuery();
				
				//마지막에 프로젝트 검색하면 끝
				i = 0;
				while(projListRs.next())
				{
					list[i] = new ProjListForSearch();
					list[i++].setParams(projListRs);
				}
				
				out_numOfRow.setIntValue(projNums.size());
			}
			else if (!doSearch) //검색조건을 안넣으면 모두 검색함
			{
				projListSql += " ORDER BY "+ sorting +" DESC LIMIT ?, 10";
				projListPstmt = conn.prepareStatement(projListSql);
				
				projListPstmt.setInt(1, (pageNum - 1) * 10);
				
				System.out.println(projListPstmt.toString());
				projListRs = projListPstmt.executeQuery();
				
				int i = 0;
				while(projListRs.next())
				{
					list[i] = new ProjListForSearch();
					list[i++].setParams(projListRs);
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, projListPstmt, projListRs);
		}
		
		return list;
	}
	
	public ProjJoinedFreelancerForList[] getJoinedFreelancerList(String listSortOption, 
			ParamInt numOfList, int pageNum, String searchFreelancer)
	{
		searchFreelancer = "%" + searchFreelancer + "%";
		ProjJoinedFreelancerForList[] list = null;
		String numOfListSql = "SELECT count(*) AS NUMOFLIST FROM PROJJOINEDFREELANCERLIST";
		String sql = "SELECT * FROM PROJJOINEDFREELANCERLIST "
				+ "WHERE FREEID LIKE ? OR FREENAME LIKE ? ORDER BY " + listSortOption + " LIMIT ?, 10";
		Connection conn = null;
		
		ResultSet numRs = null;
		ResultSet rs = null;
		PreparedStatement numPstmt = null;
		PreparedStatement pstmt = null;
		
		try
		{
			conn = DBManager.getConnection();
			
			numPstmt = conn.prepareStatement(numOfListSql);
			numRs = numPstmt.executeQuery();
			while(numRs.next()) numOfList.setIntValue(numRs.getInt("NUMOFLIST"));
			if(numRs != null) numRs.close();
			if(numPstmt != null) numPstmt.close();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchFreelancer);
			pstmt.setString(2, searchFreelancer);
			pstmt.setInt(3, (pageNum - 1) * 10);
			rs = pstmt.executeQuery();
			rs.last();
			list = new ProjJoinedFreelancerForList[rs.getRow()];
			rs.beforeFirst();
			while(rs.next())
			{
				ProjJoinedFreelancerForList p = new ProjJoinedFreelancerForList();
				p.setParams(rs);
				list[rs.getRow() - 1] = p;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public void projJoinAcceptOrRejectFrees(Integer[] joinNum, boolean acceptOrReject)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		if(acceptOrReject)
			sql = "UPDATE JOINPROJ SET FREESTATE = ? WHERE JOINNUM = ?";
		else
			sql = "DELETE FROM JOINPROJ WHERE JOINNUM = ?";
		
		try
		{
			conn = DBManager.getConnection();
			
			for(Integer i : joinNum)
			{
				pstmt = conn.prepareStatement(sql);
				if(acceptOrReject)
				{
					pstmt.setString(1, "접수완료");
					pstmt.setInt(2, i);
				}
				else pstmt.setInt(1, i);
				
				pstmt.executeUpdate();
				if(pstmt != null) pstmt.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(conn != null) conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<ProjectRecruitState> getRecruitState(int projNum)
	{
		ArrayList<ProjectRecruitState> list = new ArrayList<ProjectRecruitState>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM PROJECTRECRUITSTATE WHERE PROJNUM = ?";
		
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ProjectRecruitState newState = new ProjectRecruitState();
				newState.setParams(rs);
				list.add(newState);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public RecommendedFreelancer[] getRecommendedFreelancerList(String[] sortOption, int pageNum,
			ParamInt out_numOfRow, int curProjNum)
	{
		RecommendedFreelancer[] list = null;
		
		String sql = null;
		sql = "select *," + 
				"	CHECKSKILLSCORE(PRIMARYLANGS, LANGNAME, 2, LANGCOUNT) AS PROGLANGSCORE," + 
				"	CHECKSKILLSCORE(PRIMARYFRAMES, TOOLNAME, 2, TOOLCOUNT) AS FRAMEWORKSCORE" + 
				"	from recommendedfreelancer, RECOMMENDLANG, RECOMMENDTOOL" + 
				"	where RECOMMENDLANG.PROJNUM = RECOMMENDTOOL.PROJNUM" + 
				"	and RECOMMENDLANG.PROJNUM = ?" + 
				"	GROUP BY RECOMMENDEDFREELANCER.FREEID ORDER BY " +
				sortOption[0] + ", " + sortOption[1] + ", " + sortOption[2] +
				" LIMIT ?, 10";
				;
		/*sql = "SELECT * FROM RECOMMENDEDFREELANCER "//ORDER BY "
				//+ sortOption[0] + ", " + sortOption[1] + ", " + sortOption[2] + " "
				+ "LIMIT ?, 10";*/
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("SELECT * FROM RECOMMENDEDFREELANCER");
			rs = pstmt.executeQuery();
			rs.last();
			out_numOfRow.setIntValue(rs.getRow());
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, curProjNum);
			pstmt.setInt(2, (pageNum - 1) * 10);
			System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥" + pstmt);
			rs = pstmt.executeQuery();
			
			rs.last();
			list = new RecommendedFreelancer[rs.getRow()];
			rs.beforeFirst();
			
			while(rs.next())
			{
				RecommendedFreelancer newFree = new RecommendedFreelancer();
				newFree.setParams(rs);
				list[rs.getRow() - 1] = newFree; 
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public JoinAppliedFreelancer[] getAppliedFreelancer(int projNum, int pageNum, String sortOption, ParamInt out_numOfRow)
	{
		JoinAppliedFreelancer[] list = null;
		
		String sql = null;
		sql = "SELECT * FROM JOINAPPLIEDFREELANCER WHERE PROJNUM = ? "
				+ sortOption
				+ " LIMIT ?, 10";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("SELECT * FROM JOINAPPLIEDFREELANCER WHERE PROJNUM = ?");
			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();
			rs.last();
			out_numOfRow.setIntValue(rs.getRow());
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projNum);
			pstmt.setInt(2, (pageNum - 1) * 10);
			rs = pstmt.executeQuery();
			
			rs.last();
			list = new JoinAppliedFreelancer[rs.getRow()];
			rs.beforeFirst();
			
			while(rs.next())
			{
				JoinAppliedFreelancer newFree = new JoinAppliedFreelancer();
				newFree.setParams(rs);
				list[rs.getRow() - 1] = newFree; 
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public ArrayList<JoinAppliedFreelancer> getFreeListToPutIn(ArrayList<String> freeId, String sortOption)
	{
		ArrayList<JoinAppliedFreelancer> list = new ArrayList<JoinAppliedFreelancer>();
		
		String sql = null;
		sql = "SELECT * FROM FREELANCERLISTTOPUTIN ";//ORDER BY "
				//+ sortOption
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = DBManager.getConnection();
			
			if(freeId != null && freeId.size() != 0)
			{
				sql += "WHERE FREEID IN(";
				for(int i = 0; i < freeId.size(); i++)
				{
					if(i != freeId.size() - 1) sql += "?, ";
					else sql += "?)";
				}
			}
			
			pstmt = conn.prepareStatement(sql);
			if(freeId != null && freeId.size() != 0)
			{
				for(int i = 1; i <= freeId.size(); i++)
				{
					pstmt.setString(i, freeId.get(i - 1));
				}
			}
			System.out.println("피에스티엠티" + pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				JoinAppliedFreelancer newFree = new JoinAppliedFreelancer();
				newFree.setParams(rs);
				list.add(newFree);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public void sendMessageForPutInRequest(int projNum, String loginEmpId, String freeId, String freeName,
			String startDate, String exitDate)
	{
		String getProjNameSql = "SELECT PROJNAME FROM PROJECT WHERE PROJNUM = ?";
		String messageSql = "INSERT INTO MESSAGE(MSGNUM, PREVMSGNUM, FREEWRITER, EMPWRITER,"
				+ " FREERECEIVER, EMPRECEIVER, MSGTITLE, MSGCONTENT, MSGSENDDATE, MSGCHECKED,"
				+ " PROJNUM, MSGCHECKEDDATE) VALUES "
				+ " (null, null, null, ?, ?, null, ?, ?, CURRENT_TIMESTAMP, 0, ?, null)";
		Connection conn = null;
		PreparedStatement projNamePstmt = null;
		ResultSet projNameRs = null;
		PreparedStatement messagePstmt = null;
		
		String projName = null;
		String msgContent = null;
		
		try
		{
			conn = DBManager.getConnection();
			projNamePstmt = conn.prepareStatement(getProjNameSql);
			projNamePstmt.setInt(1, projNum);
			projNameRs = projNamePstmt.executeQuery();
			while(projNameRs.next()) projName = projNameRs.getString("PROJNAME");
			if(projNameRs != null) projNameRs.close();
			if(projNamePstmt != null) projNamePstmt.close();
			
			msgContent = freeName + "님 안녕하세요, " + projName + " 프로젝트를 "
					+ startDate + "부터 " + exitDate + "까지 참가할 것을 요청합니다";
			
			messagePstmt = conn.prepareStatement(messageSql);
			messagePstmt.setString(1, loginEmpId);
			messagePstmt.setString(2, freeId);
			messagePstmt.setString(3, projName + "에 대한 프로젝트 투입을 요청합니다");
			messagePstmt.setString(4, msgContent);
			messagePstmt.setInt(5, projNum);
			
			messagePstmt.executeUpdate();
			
			if(messagePstmt != null) messagePstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn);
		}
	}
	
	public void sendMessageAndPutIn(int projNum, String loginEmpId, String freeId, String freeName,
			String startDate, String exitDate)
	{
		String getProjNameSql = "SELECT PROJNAME FROM PROJECT WHERE PROJNUM = ?";
		String messageSql = "INSERT INTO MESSAGE(MSGNUM, PREVMSGNUM, FREEWRITER, EMPWRITER,"
				+ " FREERECEIVER, EMPRECEIVER, MSGTITLE, MSGCONTENT, MSGSENDDATE, MSGCHECKED,"
				+ " PROJNUM, MSGCHECKEDDATE) VALUES "
				+ " (null, null, null, ?, ?, null, ?, ?, CURRENT_TIMESTAMP, 0, ?, null)";
		String tooIpSql1 = "UPDATE JOINPROJ SET FREESTATE = '참여중', JOINACCEPTDATE = CURRENT_TIMESTAMP"
				+ " WHERE FREEID = ? AND PROJNUM = ?";
		String tooIpSql2 = "INSERT INTO JOINPROJ(JOINNUM, JOINPROJDATE, EXITPROJDATE, PROJROLE, FREESTATE,"
				+ " APPLICATIONDATE, JOINACCEPTDATE, PROJNUM, FREEID)"
				+ " VALUES(null, TIMESTAMP(?), TIMESTAMP(?),"
				+ " null, '참여중', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?)";
		Connection conn = null;
		PreparedStatement projNamePstmt = null;
		PreparedStatement duplicatedPstmt = null;
		ResultSet projNameRs = null;
		ResultSet duplicatedRs = null;
		PreparedStatement messagePstmt = null;
		PreparedStatement tooIpPstmt = null;
		
		boolean joined = false;
		
		String projName = null;
		String msgContent = null;
		
		try
		{
			conn = DBManager.getConnection();
			
			projNamePstmt = conn.prepareStatement(getProjNameSql);
			projNamePstmt.setInt(1, projNum);
			projNameRs = projNamePstmt.executeQuery();
			while(projNameRs.next()) projName = projNameRs.getString("PROJNAME");
			if(projNameRs != null) projNameRs.close();
			if(projNamePstmt != null) projNamePstmt.close();
			
			msgContent = freeName + "님 안녕하세요, " + projName + " 프로젝트에 투입되었습니다 "
					+ startDate + " ~ " + exitDate;
			
			messagePstmt = conn.prepareStatement(messageSql);
			messagePstmt.setString(1, loginEmpId);
			messagePstmt.setString(2, freeId);
			messagePstmt.setString(3, projName + " 프로젝트에 투입되었습니다");
			messagePstmt.setString(4, msgContent);
			messagePstmt.setInt(5, projNum);
			
			messagePstmt.executeUpdate();
			if(messagePstmt != null) messagePstmt.close();
			
			duplicatedPstmt = conn.prepareStatement("SELECT * FROM JOINPROJ WHERE FREEID = ? AND PROJNUM = ?");
			duplicatedPstmt.setString(1, freeId);
			duplicatedPstmt.setInt(2, projNum);
			duplicatedRs = duplicatedPstmt.executeQuery();
			if(duplicatedRs.next()) joined = true;
			if(duplicatedRs != null) duplicatedRs.close();
			if(duplicatedPstmt != null) duplicatedPstmt.close();
			
			if(joined)
			{
				tooIpPstmt = conn.prepareStatement(tooIpSql1);
				tooIpPstmt.setString(1, freeId);
				tooIpPstmt.setInt(2, projNum);
				
				tooIpPstmt.executeUpdate();
			}
			else
			{
				tooIpPstmt = conn.prepareStatement(tooIpSql2);
				
				ProjUtil pu = ProjUtil.getInstance();
				startDate = pu.removeCharAt(startDate, 4);
				System.out.println(startDate);
				startDate = pu.removeCharAt(startDate, 6);
				System.out.println(startDate);
				exitDate = pu.removeCharAt(exitDate, 4);
				System.out.println(exitDate);
				exitDate = pu.removeCharAt(exitDate, 6);
				System.out.println(exitDate);
				
				tooIpPstmt.setString(1, startDate);
				tooIpPstmt.setString(2, exitDate);
				tooIpPstmt.setInt(3, projNum);
				tooIpPstmt.setString(4, freeId);
				
				tooIpPstmt.executeUpdate();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, tooIpPstmt);
		}
	}
	
	public ArrayList<FreeIdFreeNameStartEndDate> getFreeIdAndFreeName(String[] freeIds)
	{
		ArrayList<FreeIdFreeNameStartEndDate> freeIdAndName = new ArrayList<FreeIdFreeNameStartEndDate>();
		String sql = "SELECT FREEID, FREENAME FROM FREELANCER ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = DBManager.getConnection();
			
			if(freeIds != null && freeIds.length != 0)
			{
				sql += "WHERE FREEID IN(";
				for(int i = 0; i < freeIds.length; i++)
				{
					if(i == freeIds.length - 1)
						sql += "?";
					else
						sql += "?, ";
				}
				sql += ")";
				
				pstmt = conn.prepareStatement(sql);
				
				for(int i = 1; i <= freeIds.length; i++)
				{
					pstmt.setString(i, freeIds[i - 1]);
				}
				
				System.out.println("쿼리쿼리쿼리" + pstmt);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					FreeIdFreeNameStartEndDate f = new FreeIdFreeNameStartEndDate();
					f.setFreeId(rs.getString("FREEID"));
					f.setFreeName(rs.getString("FREENAME"));
					freeIdAndName.add(f);
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		
		return freeIdAndName;
	}
	
	public ArrayList<FreelancerListToSearch> getFreelancerListToSearch(String freeName, String freePhone, String freeEmail,
			String[] devSorts, int[] langNums, int[] dbNums, int[] frameNums, String[] projRoles, String order, Page out_page)
	{
		ArrayList<FreelancerListToSearch> list = new ArrayList<FreelancerListToSearch>();
		
		Connection conn = null;
		
		String freeSql = "select f.JOBSTATE, f.FREEID, f.FREENAME, f.PRIMARYLANGS, f.AVAILABLEFRAMES, f.PROJCAREERYEARS, f.FREEKOSA, " + 
				" f.FREESCORE,LEFTJOINDAYS, f.FREEJOINDATE, f.FREEPHONE, f.FREEEMAIL from freelancerlisttosearch " + 
				"left join joinproj j on j.FREEID = f.FREEID " + 
				"left join primarylang pl on pl.FREEID = f.FREEID " + 
				"left join primaryframe pf on pf.FREEID = f.FREEID " + 
				"left join project p on p.PROJNUM = j.PROJNUM WHERE TRUE ";
		PreparedStatement freePstmt = null;
		ResultSet freeRs = null;
		
		String freeNameSql = null;
		String freeEmailSql = null;
		String freePhoneSql = null;
		String devSql = null;
		String langSql = null;
		String dbSql = null;
		String frameSql = null;
		String roleSql = null;
		
		if(freeName != null && !freeName.trim().equals(""))
		{
			freeNameSql = " AND f.FREENAME = ? ";
			freeSql += freeNameSql;
		}
		if(freeEmail != null && !freeEmail.trim().equals(""))
		{
			freeEmailSql = " AND f.FREEEMAIL = ? ";
			freeSql += freeEmailSql;
		}
		if(freePhone != null && !freePhone.trim().equals(""))
		{
			freePhoneSql = " AND f.FREEPHONE = ? ";
			freeSql += freePhoneSql;
		}
		if(devSorts != null && devSorts.length != 0)
		{
			for(int i = 0; i < devSorts.length; i++)
			{
				devSql = " AND p.PROJDEVELOPSORT in(";
				devSql += i < devSorts.length ? "?," : "?) ";
			}
			freeSql += devSql;
		}
		freeSql += devSql;
		if(langNums != null && langNums.length != 0)
		{
			for(int i = 0; i < langNums.length; i++)
			{
				langSql = " AND pl.LANGNUM IN(";
				langSql += i < langNums.length ? "?," : "?) ";
			}
			freeSql += langSql;
		}
		if(dbNums != null && dbNums.length != 0)
		{
			for(int i = 0; i < dbNums.length; i++)
			{
				dbSql = " AND p.DBNUM IN(";
				dbSql += i < dbNums.length ? "?," : "?) ";
			}
			freeSql += dbSql;
		}
		if(frameNums != null && frameNums.length != 0)
		{
			for(int i = 0; i < frameNums.length; i++)
			{
				langSql = " AND pf.FRAMENUM IN(";
				langSql += i < frameNums.length ? "?," : "?) ";
			}
			freeSql += frameSql;
		}
		if(projRoles != null && projRoles.length != 0)
		{
			for(int i = 0; i < projRoles.length; i++)
			{
				roleSql = " AND j.PROJROLE in(";
				roleSql += i < projRoles.length ? "?," : "?) ";
			}
			freeSql += roleSql;
		}
		
		freeSql += " GROUP BY F.FREEID " + order + " LIMIT ?, 10";
		
		try
		{
			conn = DBManager.getConnection();
			
			freePstmt = conn.prepareStatement(freeSql);
			
			int stmtSeq = 0;
			
			if(freeName != null && !freeName.trim().equals(""))
				freePstmt.setString(++stmtSeq, freeName);
			if(freeEmail != null && !freeEmail.trim().equals(""))
				freePstmt.setString(++stmtSeq, freeEmail);
			if(freePhone != null && !freePhone.trim().equals(""))
				freePstmt.setString(++stmtSeq, freePhone);
			if(devSorts != null && devSorts.length != 0)
				for(String devSort : devSorts)
					freePstmt.setString(++stmtSeq, devSort);
			if(langNums != null && langNums.length != 0)
				for(int langNum : langNums)
					freePstmt.setInt(++stmtSeq, langNum);
			if(dbNums != null && dbNums.length != 0)
				for(int dbNum : dbNums)
					freePstmt.setInt(++stmtSeq, dbNum);
			if(frameNums != null && frameNums.length != 0)
				for(int frameNum : frameNums)
					freePstmt.setInt(++stmtSeq, frameNum);
			if(projRoles != null && projRoles.length != 0)
				for(String projRole : projRoles)
					freePstmt.setString(++stmtSeq, projRole);
			freePstmt.setInt(++stmtSeq, (out_page.getPageNum() - 1) * 10);
			
			freeRs = freePstmt.executeQuery();
			
			freeRs.last();
			out_page.setNumOfRow(freeRs.getRow());
			freeRs.beforeFirst();
			
			while(freeRs.next())
			{
				FreelancerListToSearch newFree = new FreelancerListToSearch();
				newFree.setParams(freeRs);
				list.add(newFree);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, freePstmt, freeRs);
		}
		
		return list;
	}
}
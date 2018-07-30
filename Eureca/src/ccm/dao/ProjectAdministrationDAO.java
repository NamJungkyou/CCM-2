package ccm.dao;

import ccm.data.AllOfEuclidProject;
import ccm.data.CareerForProfile;
import ccm.data.EduForProfile;
import ccm.data.FreeIdFreeNameStartEndDate;
import ccm.data.FreeProfileOfSearch;
import ccm.data.FreelancerListToSearch;
import ccm.data.JoinAppliedFreelancer;
import ccm.data.JoinAppliedList;
import ccm.data.ListOfProjJoinedRecord;
import ccm.data.Page;
import ccm.data.ParamInt;
import ccm.data.ProjJoinedFreelancer;
import ccm.data.ProjJoinedFreelancerForList;
import ccm.data.ProjJoinedRecord;
import ccm.data.ProjListForSearch;
import ccm.data.ProjectRecruitState;
import ccm.data.RecommendedFreelancer;
import ccm.data.SkillInventorySearch;
import ccm.data.function.ProjUtil;
import ccm.data.table.Employee;
import ccm.data.table.Framework;
import ccm.data.table.ProgLang;
import ccm.util.DBManager;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * 프로젝트 관리에 관한 데이터를
 * 
 * 불러오는 DAO
 * 
 * 싱글턴 패턴으로 객체를 딱 한개만 생성함
 * 
 * @author 글로벌 IT 진재환
 *
 */

public class ProjectAdministrationDAO {
	private ProjectAdministrationDAO() {
	}

	private static ProjectAdministrationDAO instance = new ProjectAdministrationDAO();

	public static ProjectAdministrationDAO getInstance() {
		return instance;
	}

	/**
	 * 현재 가장 최신의 유클리드 자체 프로젝트 번호를 가져오는 메소드
	 * 
	 * @return 최신의 유클리드 프로젝트 번호
	 */
	public int getMaxProjNum() {
		// 반환할 프로젝트 번호를 0으로 초기화함
		int projNum = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			// SQL MAX() 함수로 최신 프로젝트 번호를 구함
			// 조건에 외부프로젝트가 아닌 프로젝트만 가져옴
			pstmt = conn.prepareStatement("SELECT MAX(PROJNUM) AS PROJNUM"
					+ "                      FROM PROJECT"
					+ "                     WHERE ISEXTERN = 0");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				projNum = rs.getInt("PROJNUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, new ResultSet[] { rs });
		}

		return projNum;
	}

	/**
	 * 유클리드 프로젝트 세부정보를 반환하는 메소드
	 * AllOfEuclid 클래스 객체를 생성후
	 * 쿼리를 여러번 실행하여
	 * 클래스 멤버들을 채워넣는 식의 작동방식
	 * @param projNum
	 * @return
	 */
	public AllOfEuclidProject getProjectDetail(int projNum) {
		AllOfEuclidProject projectDetail = null;

		
		// 외부프로젝트가 아닌 프로젝트만 가져오는 쿼리
		String projectListSql = "SELECT *"
				+ "                FROM PROJECT"
				+ "               WHERE ISEXTERN = 0"
				+ "                 AND PROJNUM = ?";
		
		// 프로젝트가 쓰는 프레임워크
		String frameSql = "SELECT FRAMENUM"
				+ "             , FRAMENAME"
				+ "             , FRAMEDEVELOPFIELD"
				+ "          FROM PROJFRAMENAME"
				+ "         WHERE PROJNUM = ?";

		// 프로그래밍 언어
		String langSql = "SELECT LANGNUM"
				+ "            , LANGNAME"
				+ "         FROM PROJLANGNAME"
				+ "        WHERE PROJNUM = ?";

		// DB
		String DBSql = "SELECT DBNUM"
				+ "          , DBNAME"
				+ "       FROM PROJDBMSNAME"
				+ "      WHERE PROJNUM = ?";
		
		// 등록자의 세부정보를 Freelancer 클래스 객체로 받아옴
		String regiSql = "SELECT EMPID"
				+ "            , EMPPW"
				+ "            , EMPDUTY"
				+ "            , EMPNAME"
				+ "            , EMPDEPT"
				+ "            , EMPPICTURE"
				+ "            , EMPPHONE"
				+ "            , EMPEMAIL"
				+ "            , EMPJOINDATE"
				+ "            , EMPBIRTH"
				+ "            , EMPSEX"
				+ "            , EMPMARRIED"
				+ "            , EMPFRONTADDR"
				+ "            , EMPDROPDATE"
				+ "            , EMPREARADDR"
				+ "            , EMPBANK"
				+ "            , EMPACCNAME"
				+ "            , EMPACCOUNT"
				+ "            , EMPAUTH"
				+ "         FROM PROJREGISTERERNAME"
				+ "        WHERE PROJNUM = ?";

		// 수정자
		String reviSql = "SELECT EMPID, EMPPW, EMPDUTY, EMPNAME, EMPDEPT, EMPPICTURE, EMPPHONE, EMPEMAIL, EMPJOINDATE, EMPDROPDATE, EMPBIRTH, EMPSEX, EMPMARRIED, EMPFRONTADDR, EMPREARADDR, EMPBANK, EMPACCNAME, EMPACCOUNT, EMPAUTH FROM PROJREVISERNAME WHERE PROJNUM = ?";

		// 역할별 채용현황
		String roleSql = "SELECT PROJNUM, ROLENUM, ROLENAME, REQUIRENUM FROM PROJREQUIREROLENAME WHERE PROJNUM = ?";

		// 참여한 프리랜서 목록
		String joinedFreeSql = "SELECT PROJNUM, JOINNUM, FREEID, FREENAME, PROJROLE, JOINPROJDATE,  EXITPROJDATE, FREEPHONE, FREEEMAIL FROM PROJJOINEDFREELANCER WHERE PROJNUM = ?";

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

		try {
			conn = DBManager.getConnection();
			projectListPstmt = conn.prepareStatement(projectListSql);
			System.out.println(projectListPstmt);
			System.out.println(projNum);
			projectListPstmt.setInt(1, projNum);
			projectRs = projectListPstmt.executeQuery();

			// 조건에 맞는 프로젝트를 찾으면 while 루프 한번 작동하는 동안에
			// 세부 쿼리들을 여러번 실행해서 AllOfEuclidProject 크래스
			// 필드의 값을 채워넣음
			while (projectRs.next()) {
				
				// 일단 객체를 생성
				projectDetail = new AllOfEuclidProject();
				// setParams 메소드로 쿼리 결과값을 필드에 자동으로 채움
				projectDetail.setParams(projectRs);
				
				// DB 쿼리 실행
				DBMSPstmt = conn.prepareStatement(DBSql);
				DBMSPstmt.setInt(1, projectDetail.getProjNum());
				DBMSRs = DBMSPstmt.executeQuery();
				while (DBMSRs.next())
					projectDetail.getProjDBMSInfo().setParams(DBMSRs);
				
				// 결과값을 처리하고 pstmt와 rs를 닫아줌
				if (DBMSPstmt != null)
					DBMSPstmt.close();
				if (DBMSRs != null) {
					DBMSRs.close();
				}
				
				// 프로그래밍 언어 쿼리 실행
				langPstmt = conn.prepareStatement(langSql);
				langPstmt.setInt(1, projectDetail.getProjNum());
				langRs = langPstmt.executeQuery();
				while (langRs.next()) {
					ProgLang pl = new ProgLang();
					pl.setParams(langRs);
					projectDetail.getProjProgLangs().add(pl);
				}
				if (langPstmt != null)
					langPstmt.close();
				if (langRs != null) {
					langRs.close();
				}
				
				// 역할별 채용현황 쿼리 실행
				rolePstmt = conn.prepareStatement(roleSql);
				rolePstmt.setInt(1, projectDetail.getProjNum());
				roleRs = rolePstmt.executeQuery();
				while (roleRs.next()) {
					ccm.data.table.ProjRole pr = new ccm.data.table.ProjRole();
					pr.setParams(roleRs);
					projectDetail.getProjRequireRoles().add(pr);
				}
				if (rolePstmt != null)
					rolePstmt.close();
				if (roleRs != null) {
					roleRs.close();
				}
				
				// 프레임워크 쿼리 실행
				framePstmt = conn.prepareStatement(frameSql);
				framePstmt.setInt(1, projectDetail.getProjNum());
				frameRs = framePstmt.executeQuery();
				while (frameRs.next()) {
					Framework fw = new Framework();
					fw.setParams(frameRs);
					projectDetail.getProjFrameworks().add(fw);
				}
				if (framePstmt != null)
					framePstmt.close();
				if (frameRs != null) {
					frameRs.close();
				}
				
				// 등록자의 세부정보 쿼리 실행
				registererPstmt = conn.prepareStatement(regiSql);
				registererPstmt.setInt(1, projectDetail.getProjNum());
				registererRs = registererPstmt.executeQuery();
				while (registererRs.next()) {
					Employee emp = new Employee();
					emp.setParams(registererRs);
					projectDetail.setProjRegistererInfo(emp);
				}
				if (registererPstmt != null)
					registererPstmt.close();
				if (registererRs != null) {
					registererRs.close();
				}
				
				// 수정자 쿼리 실행
				reviserPstmt = conn.prepareStatement(reviSql);
				reviserPstmt.setInt(1, projectDetail.getProjNum());
				reviserRs = reviserPstmt.executeQuery();
				while (reviserRs.next()) {
					Employee emp = new Employee();
					emp.setParams(reviserRs);
					projectDetail.setProjReviserInfo(emp);
				}
				if (reviserPstmt != null)
					reviserPstmt.close();
				if (reviserRs != null) {
					reviserRs.close();
				}
				
				// 프로젝트 참여자들은
				// ArrayList add 메소드로 여러명을 넣어줌
				joinedFreePstmt = conn.prepareStatement(joinedFreeSql);
				joinedFreePstmt.setInt(1, projNum);
				joinedFreeRs = joinedFreePstmt.executeQuery();
				while (joinedFreeRs.next()) {
					ProjJoinedFreelancer jp = new ProjJoinedFreelancer();
					jp.setParams(joinedFreeRs);
					projectDetail.getProjJoinedFree().add(jp);
				}
				if (joinedFreePstmt != null)
					joinedFreePstmt.close();
				if (joinedFreeRs != null) {
					joinedFreeRs.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, projectListPstmt, new ResultSet[] { projectRs });
		}

		return projectDetail;
	}
	
	/**
	 * 프로젝트에 참여한 프리랜서 목록을 반환하는 메소드
	 * 
	 * @param listSortOption 정렬옵션
	 * @param numOfList 쿼리에 LIMIT 옵션을 붙이지 않은 목록의 수
	 * @param pageNum 현재 페이지번호
	 * @param searchFreelancer 프리랜서 이름 검색옵션
	 * @return
	 */
	public ProjJoinedFreelancerForList[] getJoinedFreelancerList(String listSortOption, ParamInt numOfList, int pageNum,
			String searchFreelancer) {
		
		// 와일드카드 쿼리 검색을 위해 프리랜서 이름 앞뒤로 %를 붙여줌
		searchFreelancer = "%" + searchFreelancer + "%";
		
		// 프로젝트 참여한 프리랜서 배열 객체를 일단 널로 초기화해줌
		ProjJoinedFreelancerForList[] list = null;
		
		// 리스트의 전체 수를 구하는 쿼리
		String numOfListSql = "SELECT count(*) AS NUMOFLIST FROM PROJJOINEDFREELANCERLIST";
		
		// 메인 쿼리
		String sql = "SELECT * FROM PROJJOINEDFREELANCERLIST WHERE FREEID LIKE ? OR FREENAME LIKE ? ORDER BY "
				+ listSortOption + " LIMIT ?, 10";
		Connection conn = null;

		ResultSet numRs = null;
		ResultSet rs = null;
		PreparedStatement numPstmt = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			
			// 전체 행의 개수를 DB에서 받아옴
			numPstmt = conn.prepareStatement(numOfListSql);
			numRs = numPstmt.executeQuery();
			while (numRs.next())
				numOfList.setIntValue(numRs.getInt("NUMOFLIST"));
			if (numRs != null)
				numRs.close();
			if (numPstmt != null) {
				numPstmt.close();
			}
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchFreelancer);
			pstmt.setString(2, searchFreelancer);
			// 페이지번호는 (페이지번호 - 1) X 10 을 해서 딱 10개만
			// 받아오도록 스테이트먼트에 세팅함
			pstmt.setInt(3, (pageNum - 1) * 10);
			rs = pstmt.executeQuery();
			rs.last();
			list = new ProjJoinedFreelancerForList[rs.getRow()];
			rs.beforeFirst();
			while (rs.next()) {
				ProjJoinedFreelancerForList p = new ProjJoinedFreelancerForList();
				p.setParams(rs);
				list[(rs.getRow() - 1)] = p;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, new ResultSet[] { rs });
		}

		return list;
	}

	
	/**
	 * 프로젝트 참여신청 접수 상태로 업데이트해주거나
	 * 
	 * 참여신청 거절해서 DB에서 데이터 삭제하는 메소드
	 * 
	 * @param joinNum 프로젝트 참여 테이블 주키
	 * @param acceptOrReject true는 접수 false 는 거절
	 */
	public void projJoinAcceptOrRejectFrees(Integer[] joinNum, boolean acceptOrReject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		// 검사를 한번 한 후 sql 값을 결정함
		if (acceptOrReject) {
			// freestate를 바꾸는 update문의 sql
			sql = "UPDATE JOINPROJ SET FREESTATE = ? WHERE JOINNUM = ?";
		} else {
			// joinproj의 데이터를 아예 삭제해버리는 sql
			sql = "DELETE FROM JOINPROJ WHERE JOINNUM = ?";
		}
		try {
			conn = DBManager.getConnection();
			
			// 파라미터로 넘어온 joinNum 배열을
			// 계속 update로 실행함
			for (Integer i : joinNum) {
				pstmt = conn.prepareStatement(sql);
				if (acceptOrReject) {
					pstmt.setString(1, "접수완료");
					pstmt.setInt(2, i.intValue());
				} else {
					pstmt.setInt(1, i.intValue());
				}
				pstmt.executeUpdate();
				if (pstmt != null)
					pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			try {
				if (conn != null)
					conn.close();
			} catch (Exception exeption) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 채용 현황 리스트를 반환하는 메소드
	 * 
	 * @param projNum 해당 프로젝트의 채용현황을 받아서
	 * @return ArrayList 객체 리스트로 반환함
	 */
	public ArrayList<ProjectRecruitState> getRecruitState(int projNum) {
		
		ArrayList<ProjectRecruitState> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// PROJRECRUITSTATE 뷰에서 리스트를 받아오는 SQL문
		String sql = "SELECT * FROM PROJECTRECRUITSTATE WHERE PROJNUM = ?";

		try {
			// DB 커넥션
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			// pstmt에 projNum을 넘김
			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// ProjectRecruitState 객체를 생성해서
				// setParams 메소드로 DB에서 받은 값을 세팅함
				// 그러고 나서 list에 add()로 객체를 넣어줌
				ProjectRecruitState newState = new ProjectRecruitState();
				newState.setParams(rs);
				list.add(newState);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, new ResultSet[] { rs });
		}

		return list;
	}

	/**
	 * 추천프리랜서 리스트를 받아오는 메소드
	 * 
	 * @param sortOption 정렬 옵션
	 * @param pageNum 현재 페이지 번호
	 * @param out_numOfRow 행의 개수를 세팅하고
	 *                     함수 밖에서 다시 사용되는 변수
	 * @param curProjNum 현재 프로젝트 번호
	 * @return 추천프리랜서 리스트 배열
	 */
	public RecommendedFreelancer[] getRecommendedFreelancerList(String[] sortOption, int pageNum, ParamInt out_numOfRow,
			int curProjNum) {
		
		// 메소드가 끝나고 반환될 추천프리랜서 리스트를 null로 초기화
		RecommendedFreelancer[] list = null;

		String sql = null;
		
		// 추천 프리랜서 목록을 가져오는 쿼리문
		sql = "select *"
			+ " , CHECKSKILLSCORE(PRIMARYLANGS, LANGNAME, 2, LANGCOUNT) AS PROGLANGSCORE"
			+ " , CHECKSKILLSCORE(PRIMARYFRAMES, TOOLNAME, 2, TOOLCOUNT) AS FRAMEWORKSCORE"
			+ "  from recommendedfreelancer"
			+ "     , RECOMMENDLANG"
			+ "     , RECOMMENDTOOL"
			+ " where RECOMMENDLANG.PROJNUM = RECOMMENDTOOL.PROJNUM"
			+ "   and RECOMMENDLANG.PROJNUM = ?"
			+ " GROUP BY RECOMMENDEDFREELANCER.FREEID"
			+ " ORDER BY " + sortOption[0] // 정렬옵션 3개를 연달아서 줌
			+ "        , " + sortOption[1]
			+ "        , " + sortOption[2]
			+ " LIMIT ?, 10";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			
			// 단순히 데이터의 개수만 뽑아서 가져오는 쿼리를 먼저 날림
			pstmt = conn.prepareStatement("SELECT * FROM RECOMMENDEDFREELANCER");
			rs = pstmt.executeQuery();
			// rs를 라스트로 주고 행 개수를 뽑아냄
			rs.last();
			// out_numOfRow에 행 개수를 세팅함
			out_numOfRow.setIntValue(rs.getRow());
			if (rs != null)
				rs.close();
			if (pstmt != null) {
				pstmt.close();
			}
			
			// 두번째 쿼리를 준비함
			pstmt = conn.prepareStatement(sql);
			
			// 현재 프로젝트 번호를 세팅함
			pstmt.setInt(1, curProjNum);
			// LIMIT 구문의 첫번째 파라미터 세팅함
			pstmt.setInt(2, (pageNum - 1) * 10);
			rs = pstmt.executeQuery();
			
			// 행 개수를 구한 뒤
			rs.last();
			
			// 배열을 행 개수만큼 생성함
			list = new RecommendedFreelancer[rs.getRow()];
			
			// 다시 rs를 첫번째 데이터로 주고
			rs.beforeFirst();

			while (rs.next()) {
				// 추천프리랜서 객체를 새로 생성한 뒤
				RecommendedFreelancer newFree = new RecommendedFreelancer();
				
				// 객체 필드변수들을 rs에서 나온 값으로 세팅함
				newFree.setParams(rs);
				
				// 행의 순서대로 데이터를 집어넣음
				list[(rs.getRow() - 1)] = newFree;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, new ResultSet[] { rs });
		}

		return list;
	}

	/**
	 * 참여신청한 프리랜서 목록을 가져오는 메소드
	 * @param projNum 현재 프로젝트 번호
	 * @param pageNum 현재 페이지 번호
	 * @param sortOption 정렬 옵션
	 * @param out_numOfRow 행의 개수
	 * @return 참여신청한 프리랜서 배열
	 */
	public JoinAppliedFreelancer[] getAppliedFreelancer(int projNum, int pageNum, String sortOption,
			ParamInt out_numOfRow) {
		JoinAppliedFreelancer[] list = null;

		// 목록을 가져오는 쿼리문
		String sql = null;
		sql = "SELECT * FROM JOINAPPLIEDFREELANCER WHERE PROJNUM = ? " + sortOption + " LIMIT ?, 10";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			// 먼저 전체 헹의 개수만 뽑아옴
			pstmt = conn.prepareStatement("SELECT * FROM JOINAPPLIEDFREELANCER WHERE PROJNUM = ?");
			pstmt.setInt(1, projNum);
			rs = pstmt.executeQuery();
			rs.last();
			// out_numOfRow에 행 개수를 세팅함
			out_numOfRow.setIntValue(rs.getRow());
			if (rs != null)
				rs.close();
			if (pstmt != null) {
				pstmt.close();
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projNum);
			pstmt.setInt(2, (pageNum - 1) * 10);
			rs = pstmt.executeQuery();
			
			// 배열의 길이를 구하기 위해 rs를 먼저 마지막에 놓고
			rs.last();
			// rs 인덱스값으로 배열을 생성함
			list = new JoinAppliedFreelancer[rs.getRow()];
			rs.beforeFirst();

			while (rs.next()) {
				// JoinAppliedFreelancer 객체를 새로 생성함
				JoinAppliedFreelancer newFree = new JoinAppliedFreelancer();
				
				// setParams 메소드로 rs에서 나온 값들을 세팅함
				newFree.setParams(rs);
				
				// 배열의 인덱스마다 참여신청한 프리랜서 객체를 넣어줌
				list[(rs.getRow() - 1)] = newFree;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, new ResultSet[] { rs });
		}

		return list;
	}

	/**
	 * 투입할 프리랜서 목록을 가져오는 메소드
	 * 
	 * @param freeId 프리랜서 아이디 리스트
	 * @param sortOption 정렬옵션
	 * @return 투입할 프리랜서 목록을 저장하는 ArrayList 객체
	 */
	public ArrayList<JoinAppliedFreelancer> getFreeListToPutIn(ArrayList<String> freeId, String sortOption) {
		
		// 메소드가 끝나고 반환할 ArrayList 객체를 새로 생성함
		ArrayList<JoinAppliedFreelancer> list = new ArrayList<>();

		String sql = null;
		
		// 투입할 프리랜서 목록을 가져오는 기본 쿼리문
		sql = "SELECT * FROM FREELANCERLISTTOPUTIN ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();

			// 프리랜서 아이디 목록이 null 이 아니고
			// ArrayList 사이즈가 0이 아닐 경우에
			if ((freeId != null) && (freeId.size() != 0)) {
				// 기본 쿼리에 동적으로 WHERE 옵션을 추가함
				sql = sql + "WHERE FREEID IN(";
				
				// 프리랜서 아이디 개수만큼 물음표를 다시 추가함
				for (int i = 0; i < freeId.size(); i++) {
					if (i != freeId.size() - 1)
						sql = sql + "?, ";
					else {
						sql = sql + "?)";
					}
				}
			}
			
			// pstmt에 완성된 쿼리문을 넣어줌
			pstmt = conn.prepareStatement(sql);
			
			// 위와 마찬가지로 이번엔 물음표에 프리랜서 아이디를 세팅해줌
			if ((freeId != null) && (freeId.size() != 0)) {
				for (int i = 1; i <= freeId.size(); i++) {
					pstmt.setString(i, (String) freeId.get(i - 1));
				}
			}
			
			// 쿼리 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// JoinAppliedFreelancer 객체를 새로 생성함
				JoinAppliedFreelancer newFree = new JoinAppliedFreelancer();
				
				// 값을 세팅하고
				newFree.setParams(rs);
				
				// 리스트에 집어넣음
				list.add(newFree);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, new ResultSet[] { rs });
		}

		return list;
	}

	/**
	 * 프로젝트 투입 요청 메시지를 보내는 메소드
	 * 
	 * @param projNum 현재 프로젝트 번호
	 * @param loginEmpId 로그인한 직원 아이디
	 * @param freeId 메시지를 보낼 프리랜서 아이디
	 * @param freeName 프리랜서 이름
	 * @param startDate 투입일
	 * @param exitDate 철수일
	 */
	public void sendMessageForPutInRequest(int projNum, String loginEmpId, String freeId, String freeName,
			String startDate, String exitDate) {
		
		// 프로젝트 이름을 가져올 쿼리문
		String getProjNameSql = "SELECT PROJNAME FROM PROJECT WHERE PROJNUM = ?";
		
		// 메시지를 보내는 INSERT 문 쿼리
		String messageSql = "INSERT INTO MESSAGE (MSGNUM"
				+ "                             , PREVMSGNUM"
				+ "                             , FREEWRITER"
				+ "                             , EMPWRITER"
				+ "                             , FREERECEIVER"
				+ "                             , EMPRECEIVER"
				+ "                             , MSGTITLE"
				+ "                             , MSGCONTENT"
				+ "                             , MSGSENDDATE"
				+ "                             , MSGCHECKED"
				+ "                             , PROJNUM"
				+ "                             , MSGCHECKEDDATE)"
				+ "                       VALUES (null"
				+ "                             , null"
				+ "                             , null"
				+ "                             , ?"
				+ "                             , ?"
				+ "                             , null"
				+ "                             , ?"
				+ "                             , ?"
				+ "                             , CURRENT_TIMESTAMP"
				+ "                             , 0"
				+ "                             , ?"
				+ "                             , null)";

		Connection conn = null;
		PreparedStatement projNamePstmt = null;
		ResultSet projNameRs = null;
		PreparedStatement messagePstmt = null;

		// 해당 프로젝트 이름을 저장하는 문자열 변수
		String projName = null;
		
		// 메시지 내용을 저장하는 문자열 변수
		String msgContent = null;

		try {
			conn = DBManager.getConnection();
			
			// 먼저 프로젝트 이름을 가져옴
			projNamePstmt = conn.prepareStatement(getProjNameSql);
			
			// 물음표 값에 현재 프로젝트 이름을 넣어줌
			projNamePstmt.setInt(1, projNum);
			projNameRs = projNamePstmt.executeQuery();
			while (projNameRs.next())
				// 프로젝트 이름을 저장하는 변수에 쿼리문 결과를 넣어줌
				projName = projNameRs.getString("PROJNAME");
			if (projNameRs != null)
				projNameRs.close();
			if (projNamePstmt != null) {
				projNamePstmt.close();
			}
			
			// 메시지 내용을 프로젝트 이름 등의 변수와 함께
			// 동적으로 생성해줌
			msgContent = freeName + "님 안녕하세요, " + projName + " 프로젝트를 " + startDate + "부터 " + exitDate
					+ "까지 참가할 것을 요청합니다";

			messagePstmt = conn.prepareStatement(messageSql);
			messagePstmt.setString(1, loginEmpId);
			messagePstmt.setString(2, freeId);
			
			// 메시지 제목
			messagePstmt.setString(3, projName + "에 대한 프로젝트 투입을 요청합니다");
			
			// 메시지 내용
			messagePstmt.setString(4, msgContent);
			messagePstmt.setInt(5, projNum);
			
			// DB에 INSERT 문을 날려서 메시지 데이터를 넣어줌
			messagePstmt.executeUpdate();

			if (messagePstmt != null)
				messagePstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn);
		}
	}
	
	/**
	 * 프리랜서에게 투입 알림 메시지를 보내고 프로젝트 투입하는 메소드
	 * 
	 * @param projNum 프로젝트 번호
	 * @param loginEmpId 메시지 작성자(로그인한 직원번호)
	 * @param freeId 프리랜서 아이디
	 * @param freeName 프리랜서 이름
	 * @param startDate 프로젝트 투입일
	 * @param exitDate 프로젝트 철수일
	 */
	public void sendMessageAndPutIn(int projNum, String loginEmpId, String freeId, String freeName, String startDate,
			String exitDate) {
		
		// 프로젝트 이름을 구하는 쿼리문
		String getProjNameSql = "SELECT PROJNAME"
				+ "                FROM PROJECT"
				+ "               WHERE PROJNUM = ?";
		
		// 투입 알림 메시지를 보내는 INSERT 구문
		String messageSql = "INSERT INTO MESSAGE (MSGNUM"
				+ "                             , PREVMSGNUM"
				+ "                             , FREEWRITER"
				+ "                             , EMPWRITER"
				+ "                             , FREERECEIVER"
				+ "                             , EMPRECEIVER"
				+ "                             , MSGTITLE"
				+ "                             , MSGCONTENT"
				+ "                             , MSGSENDDATE"
				+ "                             , MSGCHECKED"
				+ "                             , PROJNUM"
				+ "                             , MSGCHECKEDDATE)"
				+ "                      VALUES  (null"
				+ "                             , null"
				+ "                             , null"
				+ "                             , ?"
				+ "                             , ?"
				+ "                             , null"
				+ "                             , ?"
				+ "                             , ?"
				+ "                             , CURRENT_TIMESTAMP"
				+ "                             , 0"
				+ "                             , ?"
				+ "                             , null)";

		// JOINPROJ 테이블에 있는
		// FREESTATE 의 데이터를 참여중으로 바꾸는
		// UPDATE 구문
		String tooIpSql1 = "UPDATE JOINPROJ"
				+ "            SET FREESTATE = '참여중'"
				+ "              , JOINACCEPTDATE = CURRENT_TIMESTAMP"
				+ "          WHERE FREEID = ?"
				+ "            AND PROJNUM = ?";

		
		String tooIpSql2 = "INSERT INTO JOINPROJ (JOINNUM"
				+ "                             , JOINPROJDATE"
				+ "                             , EXITPROJDATE"
				+ "                             , PROJROLE"
				+ "                             , FREESTATE"
				+ "                             , APPLICATIONDATE"
				+ "                             , JOINACCEPTDATE"
				+ "                             , PROJNUM"
				+ "                             , FREEID)"
				+ "                        VALUES(null"
				+ "                             , TIMESTAMP(?)"
				+ "                             , TIMESTAMP(?)"
				+ "                             , null"
				+ "                             , '참여중'"
				+ "                             , CURRENT_TIMESTAMP"
				+ "                             , CURRENT_TIMESTAMP"
				+ "                             , ?"
				+ "                             , ?)";

		Connection conn = null;
		PreparedStatement projNamePstmt = null;
		PreparedStatement duplicatedPstmt = null;
		ResultSet projNameRs = null;
		ResultSet duplicatedRs = null;
		PreparedStatement messagePstmt = null;
		PreparedStatement tooIpPstmt = null;

		// 이미 참여신청을 했는지 확인하는 불리언 변수
		boolean joined = false;

		String projName = null;
		String msgContent = null;

		try {
			conn = DBManager.getConnection();

			// 프로젝트 이름을 구하는 쿼리부터 준비함
			projNamePstmt = conn.prepareStatement(getProjNameSql);
			
			// 해당 프로젝트 번호를 세팅
			projNamePstmt.setInt(1, projNum);
			
			projNameRs = projNamePstmt.executeQuery();
			while (projNameRs.next())
				// 프로젝트 이름 변수 값을 넣음
				projName = projNameRs.getString("PROJNAME");
			if (projNameRs != null)
				projNameRs.close();
			if (projNamePstmt != null) {
				projNamePstmt.close();
			}
			
			// 프로젝트 이름을 구했으면 메시지 내용을 만듦
			msgContent = freeName + "님 안녕하세요, " + projName + " 프로젝트에 투입되었습니다 " + startDate + " ~ " + exitDate;

			// 투입 알림 메시지를 보내는 INSERT 문을 보냄 
			messagePstmt = conn.prepareStatement(messageSql);
			messagePstmt.setString(1, loginEmpId);
			messagePstmt.setString(2, freeId);
			messagePstmt.setString(3, projName + " 프로젝트에 투입되었습니다");
			messagePstmt.setString(4, msgContent);
			messagePstmt.setInt(5, projNum);

			messagePstmt.executeUpdate();
			if (messagePstmt != null) {
				messagePstmt.close();
			}
			
			// JOINPROJ 테이블에 이미 데이터가 존재하는지 확인해서
			// 프리랜서가 이미 참여신청을 했으면
			// joined 변수값을 true로 넣어주는 쿼리
			duplicatedPstmt = conn.prepareStatement("SELECT * FROM JOINPROJ WHERE FREEID = ? AND PROJNUM = ?");
			duplicatedPstmt.setString(1, freeId);
			duplicatedPstmt.setInt(2, projNum);
			duplicatedRs = duplicatedPstmt.executeQuery();
			
			// 데이터가 존재하면
			if (duplicatedRs.next())
				// joined 는 true
				joined = true;
			if (duplicatedRs != null)
				duplicatedRs.close();
			if (duplicatedPstmt != null) {
				duplicatedPstmt.close();
			}
			
			// joined 가 true 면 UPDATE
			// joined 가 false 면 INSERT 문을 날림
			if (joined) {
				tooIpPstmt = conn.prepareStatement(tooIpSql1);
				tooIpPstmt.setString(1, freeId);
				tooIpPstmt.setInt(2, projNum);

				tooIpPstmt.executeUpdate();
			} else {
				tooIpPstmt = conn.prepareStatement(tooIpSql2);

				// 프로젝트 유틸리티 객체를 가져옴
				ProjUtil pu = ProjUtil.getInstance();
				
				// 메소드의 파라미터로 넘어온 startDate의 시간값을 지움
				startDate = pu.removeCharAt(startDate, 4);
				System.out.println(startDate);
				startDate = pu.removeCharAt(startDate, 6);
				System.out.println(startDate);
				exitDate = pu.removeCharAt(exitDate, 4);
				System.out.println(exitDate);
				exitDate = pu.removeCharAt(exitDate, 6);
				System.out.println(exitDate);

				// 투입 UPDATE 문의 물음표값을 세팅해줌
				tooIpPstmt.setString(1, startDate);
				tooIpPstmt.setString(2, exitDate);
				tooIpPstmt.setInt(3, projNum);
				tooIpPstmt.setString(4, freeId);

				tooIpPstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, tooIpPstmt);
		}
	}

	/**
	 * 프리랜서 ID와 이름들을 가져오는 메소드
	 * 
	 * @param freeIds 프리랜서 ID들이 저장되어 있는 배열
	 * @return 프리랜서 이름과 ID를 저장하는 ArrayList 객체
	 */
	public ArrayList<FreeIdFreeNameStartEndDate> getFreeIdAndFreeName(String[] freeIds) {
		
		// FreeIdFreeNameStartEndDate 객체를 저장하는 ArrayList 객체를 새로 생성함
		ArrayList<FreeIdFreeNameStartEndDate> freeIdAndName = new ArrayList<>();
		
		// 프리랜서 아이디와 이름을 가져오는 쿼리문
		String sql = "SELECT FREEID"
				+ "        , FREENAME"
				+ "     FROM FREELANCER ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			
			// 프리랜서 아이디 배열 객체가 null 이 아니면
			if ((freeIds != null) && (freeIds.length != 0)) {
				// WHERE 조건을 동적으로 생성함
				sql = sql + "WHERE FREEID IN(";
				for (int i = 0; i < freeIds.length; i++) {
					if (i == freeIds.length - 1) {
						sql = sql + "?";
					} else
						sql = sql + "?, ";
				}
				sql = sql + ")";

				pstmt = conn.prepareStatement(sql);

				for (int i = 1; i <= freeIds.length; i++) {
					pstmt.setString(i, freeIds[(i - 1)]);
				}
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					// FreeIdFreeNameStartEndDate 객체를 생성하고
					FreeIdFreeNameStartEndDate f = new FreeIdFreeNameStartEndDate();
					
					// rs 에서 가져온 값들을 객체에 세팅함
					f.setFreeId(rs.getString("FREEID"));
					f.setFreeName(rs.getString("FREENAME"));
					
					// ArrayList 객체에 객체를 넣어줌
					freeIdAndName.add(f);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, new ResultSet[] { rs });
		}

		return freeIdAndName;
	}

	/**
	 * 프리랜서 검색에 필요한 파라미터에 따라
	 * 프리랜서를 검색해서 ArrayList를 반환하는 메소드
	 * 
	 * @param freeName 프리랜서 이름
	 * @param freePhone 프리랜서 전화번호
	 * @param freeEmail 이메일
	 * @param devSorts 개발 분야
	 * @param langNums 언어 번호들
	 * @param dbNums DBMS 번호들
	 * @param frameNums 프레임워크 번호들
	 * @param projRoles 역할들
	 * @param order 정렬 옵션
	 * @param out_page 페이지 객체
	 * @return 프리랜서 목록 검색 결과 ArrayList 객체
	 */
	public ArrayList<FreelancerListToSearch> getFreelancerListToSearch(String freeName, String freePhone,
			String freeEmail, String[] devSorts, int[] langNums, int[] dbNums, int[] frameNums, String[] projRoles,
			String order, Page out_page) {
		
		ArrayList<FreelancerListToSearch> list = new ArrayList<>();

		Connection conn = null;

		// 프리랜서 검색에 필요한 기본 쿼리문
		String freeSql = "select f.JOBSTATE"
				+ "            , f.FREEID"
				+ "            , f.FREENAME"
				+ "            , f.PRIMARYLANGS"
				+ "            , f.AVAILABLEFRAMES"
				+ "            , f.PROJCAREERYEARS"
				+ "            , f.FREEKOSA"
				+ "            , f.FREESCORE"
				+ "            , LEFTJOINDAYS"
				+ "            , f.FREEJOINDATE"
				+ "            , f.FREEPHONE"
				+ "            , f.FREEEMAIL"
				+ "         from freelancerlisttosearch f"
				+ "    left join joinproj j on j.FREEID = f.FREEID"
				+ "    left join primarylang pl on pl.FREEID = f.FREEID"
				+ "    left join primaryframe pf on pf.FREEID = f.FREEID"
				+ "    left join project p on p.PROJNUM = j.PROJNUM"
				+ "        WHERE TRUE ";

		PreparedStatement freePstmt = null;
		ResultSet freeRs = null;

		// 검색옵션 쿼리문
		// 검색옵션 파라미터가 null이 아닐 경우에
		// 쿼리문이 동적으로 추가됨
		String freeNameSql = null;
		String freeEmailSql = null;
		String freePhoneSql = null;
		String devSql = null;
		String langSql = null;
		String dbSql = null;
		String frameSql = null;
		String roleSql = null;

		// 검색옵션들이 null 이 아닐 경우
		if ((freeName != null) && (!freeName.trim().equals(""))) {
			// AND 로 조건이 추가되고
			// 물음표가 붙음
			freeNameSql = " AND f.FREENAME = ? ";
			freeSql = freeSql + freeNameSql;
		}
		if ((freeEmail != null) && (!freeEmail.trim().equals(""))) {
			freeEmailSql = " AND f.FREEEMAIL = ? ";
			freeSql = freeSql + freeEmailSql;
		}
		if ((freePhone != null) && (!freePhone.trim().equals(""))) {
			freePhoneSql = " AND f.FREEPHONE = ? ";
			freeSql = freeSql + freePhoneSql;
		}
		if ((devSorts != null) && (devSorts.length != 0)) {
			devSql = " AND p.PROJDEVELOPSORT in(";
			for (int i = 0; i < devSorts.length; i++) {
				devSql = devSql + (i + 1 < devSorts.length ? "?," : "?) ");
			}
			freeSql = freeSql + devSql;
		}
		if ((langNums != null) && (langNums.length != 0)) {
			langSql = " AND pl.LANGNUM IN(";
			for (int i = 0; i < langNums.length; i++) {
				if (i == langNums.length - 1)
					langSql = langSql + "?) ";
				else
					langSql = langSql + "?, ";
			}
			freeSql = freeSql + langSql;
		}
		if ((dbNums != null) && (dbNums.length != 0)) {
			dbSql = " AND p.DBNUM IN(";
			for (int i = 0; i < dbNums.length; i++) {
				dbSql = dbSql + (i + 1 < dbNums.length ? "?," : "?) ");
			}
			freeSql = freeSql + dbSql;
		}
		if ((frameNums != null) && (frameNums.length != 0)) {
			frameSql = " AND pf.FRAMENUM IN(";
			for (int i = 0; i < frameNums.length; i++) {

				frameSql = frameSql + (i + 1 < frameNums.length ? "?," : "?) ");
			}
			freeSql = freeSql + frameSql;
		}
		if ((projRoles != null) && (projRoles.length != 0)) {
			roleSql = " AND j.PROJROLE in(";
			for (int i = 0; i < projRoles.length; i++) {
				roleSql = roleSql + (i + 1 < projRoles.length ? "?," : "?) ");
			}
			freeSql = freeSql + roleSql;
		}

		// 검색옵션 동적 생성이 끝나면
		// 그룹화 쿼리가 다시 붙음
		freeSql = freeSql + " GROUP BY F.FREEID ";

		try {
			conn = DBManager.getConnection();

			freePstmt = conn.prepareStatement(freeSql);

			// 물음표에 들어갈 값들의 인덱스 변수
			int stmtSeq = 0;
			
			// 동적 생성된 쿼리문에 인덱스 번호가 올라가고
			// 검색 옵션을 물음표에 세팅해줌
			if ((freeName != null) && (!freeName.trim().equals("")))
				freePstmt.setString(++stmtSeq, freeName);
			if ((freeEmail != null) && (!freeEmail.trim().equals("")))
				freePstmt.setString(++stmtSeq, freeEmail);
			if ((freePhone != null) && (!freePhone.trim().equals("")))
				freePstmt.setString(++stmtSeq, freePhone);
			if ((devSorts != null) && (devSorts.length != 0))
				for (String devSort : devSorts)
					freePstmt.setString(++stmtSeq, devSort);
			if ((langNums != null) && (langNums.length != 0))
				for (int langNum : langNums)
					freePstmt.setInt(++stmtSeq, langNum);
			if ((dbNums != null) && (dbNums.length != 0))
				for (int dbNum : dbNums)
					freePstmt.setInt(++stmtSeq, dbNum);
			if ((frameNums != null) && (frameNums.length != 0))
				for (int frameNum : frameNums)
					freePstmt.setInt(++stmtSeq, frameNum);
			if ((projRoles != null) && (projRoles.length != 0)) {
				for (String projRole : projRoles)
					freePstmt.setString(++stmtSeq, projRole);
			}
			
			// 쿼리 실행
			freeRs = freePstmt.executeQuery();

			// 페이지번호를 메소드 밖으로 넘겨주기 위해
			// freeRs으 인덱스를 last로 바꾼 후
			freeRs.last();
			// numOfRow(데이터 행의 개수)에 행의 개수를 넣어줌
			out_page.setNumOfRow(freeRs.getRow());
			
			// 다시 인덱스를 처음으로 되돌리고 닫아줌
			freeRs.beforeFirst();

			if (freeRs != null)
				freeRs.close();
			if (freePstmt != null) {
				freePstmt.close();
			}
			
			// 이번에는 페이지 당 10개의 행을 받기 위해 LIMIT 구문을 추가함
			freeSql = freeSql + order + " LIMIT ?, 10";
			
			// 인덱스를 다시 0으로 초기화해주고
			stmtSeq = 0;

			freePstmt = conn.prepareStatement(freeSql);
			
			// 쿼리를 다시 세팅해주고
			if ((freeName != null) && (!freeName.trim().equals("")))
				freePstmt.setString(++stmtSeq, freeName);
			if ((freeEmail != null) && (!freeEmail.trim().equals("")))
				freePstmt.setString(++stmtSeq, freeEmail);
			if ((freePhone != null) && (!freePhone.trim().equals("")))
				freePstmt.setString(++stmtSeq, freePhone);
			if ((devSorts != null) && (devSorts.length != 0))
				for (String devSort : devSorts)
					freePstmt.setString(++stmtSeq, devSort);
			if ((langNums != null) && (langNums.length != 0))
				for (int langNum : langNums)
					freePstmt.setInt(++stmtSeq, langNum);
			if ((dbNums != null) && (dbNums.length != 0))
				for (int dbNum : dbNums)
					freePstmt.setInt(++stmtSeq, dbNum);
			if ((frameNums != null) && (frameNums.length != 0))
				for (int frameNum : frameNums)
					freePstmt.setInt(++stmtSeq, frameNum);
			if ((projRoles != null) && (projRoles.length != 0))
				for (String projRole : projRoles)
					freePstmt.setString(++stmtSeq, projRole);
			freePstmt.setInt(++stmtSeq, (out_page.getPageNum() - 1) * 10);
			
			// 쿼리를 다시 실행함
			freeRs = freePstmt.executeQuery();

			while (freeRs.next()) {
				// 프리랜서 객체를 생성하고
				FreelancerListToSearch newFree = new FreelancerListToSearch();
				
				// 객체의 데이터들을 넣어준뒤
				newFree.setParams(freeRs);
				
				// 리스트에 추가함
				list.add(newFree);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, freePstmt, new ResultSet[] { freeRs });
		}

		return list;
	}

	/**
	 * 프리랜서 검색 페이지에서
	 * 프리랜서 한명을 클릭했을 때 나오는
	 * 프리랜서 세부사항에서
	 * 참여신청한 프로젝트 목록을 받아오는 메소드
	 * 
	 * @param freeId 프리랜서 아이디
	 * @param out_page 페이지 객체
	 * @param sortOpt 정렬옵션
	 * @return 참여신청한 프로젝트를 저장하는 ArrayList 객체
	 */
	public ArrayList<JoinAppliedList> getJoinAppliedList(String freeId, Page out_page, String sortOpt) {
		
		// ArrayList 객체를 생성함
		ArrayList<JoinAppliedList> list = new ArrayList();

		// 참여신청한 프로젝트 목록을 가져오는 쿼리문
		String sql = "SELECT *"
				+ "     FROM JOINAPPLIEDLIST"
				+ "    WHERE FREEID = ? "
				+ sortOpt
				+ "    LIMIT ?, 10";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// pstmt에 프리랜서 아이디와 페이지번호를 세팅해줌
			pstmt.setString(1, freeId);
			pstmt.setInt(2, (out_page.getPageNum() - 1) * 10);

			rs = pstmt.executeQuery();
			rs.last();
			
			// 행의 개수를 넘겨줌
			out_page.setNumOfRow(rs.getRow());
			rs.beforeFirst();

			while (rs.next()) {
				// JoinAppliedList 객체를 생성하고
				JoinAppliedList j = new JoinAppliedList();
				
				// 객체 변수들을 초기화해준 뒤
				j.setParams(rs);
				
				// 리스트에 추가해줌
				list.add(j);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, new ResultSet[] { rs });
		}

		return list;
	}

	/**
	 * 프로젝트 참여 기록 목록을 가져오는 메소드
	 * 
	 * @param freeId 프리랜서 아이디
	 * @param sortOpt 정렬 옵션
	 * @return 프로젝트 참여기록 객체를 저장하는 List 객체
	 */
	public ArrayList<ProjJoinedRecord> getProjJoinedRecord(String freeId, String sortOpt) {
		
		// 먼저 ArrayList 객체를 새로 생성함
		ArrayList<ProjJoinedRecord> list = new ArrayList();

		// 데이터를 가져오는 쿼리문
		String sql = "SELECT *"
				+ "     FROM PROJJOINEDRECORD"
				+ "    WHERE FREEID = ? "
				+ sortOpt;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ProjJoinedRecord 객체를 생성하고
				ProjJoinedRecord p = new ProjJoinedRecord();
				
				// 객체 변수들의 값을 초기화 한 뒤
				p.setParams(rs);
				
				// 리스트에 추가해줌
				list.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, new ResultSet[] { rs });
		}

		return list;
	}

	/**
	 * 프리랜서 프로필 세부사항을 가져오는 메소드
	 * 
	 * @param freeId 선택된 프리랜서 아이디
	 * @param joinedRecordSortOption 프로젝트 참여기록 정렬옵션
	 * @param joinAppliedSortOption 참여신청한 프로젝트 정렬옵션
	 * @param joinAppliedPageNum 참여신청한 프로젝트 페이지 객체
	 * @param joinedRecordPageNum 프로젝트 참여기록 페이지 객체
	 * @return 프리랜서 프로필 세부사항 객체
	 */
	public FreeProfileOfSearch getFreeProfileOfSearch(String freeId, String joinedRecordSortOption,
			String joinAppliedSortOption, int joinAppliedPageNum, int joinedRecordPageNum) {
		FreeProfileOfSearch freeDetail = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		// 프리랜서 세부사항을 가져오는 쿼리문
		String freeDetailSql = "SELECT f.*"
				+ "               FROM freeprofileofsearch f"
				+ "              WHERE f.FREEID = ?";

		// 학력사항을 가져오는 쿼리
		String eduSql = "SELECT e.EDUSCHOOL"
				+ "           , e.EDUMAJOR"
				+ "           , DATE(e.SCHOOLJOINDATE) SCHOOLJOINDATE"
				+ "           , DATE(e.SCHOOLGRADUATEDDATE) SCHOOLGRADUATEDDATE"
				+ "        FROM education e"
				+ "       WHERE e.FREEID = ?"
				+ "       ORDER BY e.SCHOOLJOINDATE";

		// 근무경력을 가져오는 쿼리문
		String carSql = "SELECT c.CAREERCOMPANY"
				+ "           , CONCAT(CONCAT(DATE(c.COMPANYJOINDATE), ' ~ '), DATE(c.COMPANYDROPDATE)) AS CAREERTERM"
				+ "           , c.CAREERPOSITION"
				+ "           , c.CAREERJOB"
				+ "        FROM career c"
				+ "       WHERE c.FREEID = ?"
				+ "       ORDER BY c.COMPANYJOINDATE";

		
		// 스킬인벤토리 쿼리문
		String skillSql = "SELECT s.*"
				+ "          FROM SKILLINVENTORYSEARCH s"
				+ "         WHERE s.FREEID = ?"
				+ "         ORDER BY s.JOINTERM";

		// 프로젝트 참여기록 페이지롤 세팅해주는 쿼리
		String joinedRowSql = "SELECT l.*"
				+ "              FROM LISTOFPROJJOINEDRECORD l"
				+ "             WHERE l.FREEID = ?";

		// 참여신청한 프로젝트 페이지롤 세팅해주는 쿼리
		String appliedRowSql = "SELECT l.*"
				+ "               FROM LISTOFPROJJOINEDRECORD l"
				+ "              WHERE l.FREEID = ?";

		// 프로젝트 참여기록 쿼리
		String joinedRecordSql = "SELECT l.*"
				+ "                 FROM LISTOFPROJJOINEDRECORD l"
				+ "                WHERE l.FREEID = ?                 "
				+ joinedRecordSortOption
				+ "                LIMIT ?, 10";

		// 참여신청한 프로젝트 쿼리
		String joinAppliedSql = "SELECT l.*"
				+ "                FROM LISTOFJOINAPPLIEDLIST l"
				+ "               WHERE l.FREEID = ?                 "
				+ joinAppliedSortOption
				+ "               LIMIT ?, 10";

		// 행 개수를 담는 변수
		int rowCnt = 0;

		try {
			conn = DBManager.getConnection();

			// 프리랜서 인적사항 쿼리를 먼저 실행함
			pstmt = conn.prepareStatement(freeDetailSql);
			pstmt.setString(1, freeId);

			result = pstmt.executeQuery();

			while (result.next()) {
				// 프리랜서 프로필 객체 생성 후
				freeDetail = new FreeProfileOfSearch();
				
				// 필드를 세팅해줌
				freeDetail.setParams(result);
			}
			if (result != null)
				result.close();
			if (pstmt != null) {
				pstmt.close();
			}
			
			// 다음에 학력 쿼리를 실행함
			pstmt = conn.prepareStatement(eduSql);
			pstmt.setString(1, freeId);

			result = pstmt.executeQuery();

			while (result.next()) {
				// 학력 객체를 생성후
				EduForProfile newEdu = new EduForProfile();
				
				// 필드를 세팅하고
				newEdu.setParams(result);
				
				// 프리랜서 세부사항의 학력 리스트에 객체를 추가함
				freeDetail.getEdus().add(newEdu);
			}

			if (result != null)
				result.close();
			if (pstmt != null) {
				pstmt.close();
			}

			// 다음에 근무경력 쿼리를 실행함
			pstmt = conn.prepareStatement(carSql);
			pstmt.setString(1, freeId);

			result = pstmt.executeQuery();

			while (result.next()) {
				// 근무경력 객체를 새로 생성하고
				CareerForProfile newCar = new CareerForProfile();
				
				// 필드를 세팅해준 후
				newCar.setParams(result);
				
				// 프리랜서 세부사항의 경력 리스트에 객체를 추가함
				freeDetail.getCars().add(newCar);
			}

			if (result != null)
				result.close();
			if (pstmt != null) {
				pstmt.close();
			}

			
			// 스킬 인벤토리 쿼리를 실행하고
			pstmt = conn.prepareStatement(skillSql);
			pstmt.setString(1, freeId);

			result = pstmt.executeQuery();

			while (result.next()) {
				// 스킬인벤토리 객체를 생성한 후
				SkillInventorySearch newSkill = new SkillInventorySearch();
				
				// 필드를 세팅해주고
				newSkill.setParams(result);
				
				// 프리랜서 세부사항의 스킬인벤토리 리스트에 객체를 추가함
				freeDetail.getSkills().add(newSkill);
			}

			if (result != null)
				result.close();
			if (pstmt != null) {
				pstmt.close();
			}

			// 프로젝트 참여기록 행 개수를 구하는 쿼리를 실행함
			pstmt = conn.prepareStatement(joinedRowSql);
			pstmt.setString(1, freeId);
			result = pstmt.executeQuery();
			while (result.next())
				// 쿼리 결과값의 행 개수를 세어줌
				rowCnt++;
			
			// 프리랜서 세부사항에 있는 참여기록 페이지 객체의 값을 초기화해줌
			freeDetail.getJoinedRecordsPage().setNumOfRow(rowCnt); // 행의 개수
			freeDetail.getJoinedRecordsPage().setPageNum(joinedRecordPageNum); // 현재 페이지 번호
			if (result != null)
				result.close();
			if (pstmt != null) {
				pstmt.close();
			}
			
			// 프로젝트 참여기록 쿼리를 실행함
			pstmt = conn.prepareStatement(joinedRecordSql);
			pstmt.setString(1, freeId);
			// 현재 페이지 번호를 넣어줌
			pstmt.setInt(2, (joinedRecordPageNum - 1) * 10);

			result = pstmt.executeQuery();

			while (result.next()) {
				// 프로젝트 참여기록 객체를 생성후
				ListOfProjJoinedRecord newRecord = new ListOfProjJoinedRecord();
				
				// 필드의 값들을 결과값으로 세팅해주고
				newRecord.setParams(result);
				
				// 프리랜서 세부사항의 프로젝트 참여기록 리스트에 추가해줌
				freeDetail.getJoinedRecords().add(newRecord);
			}

			if (result != null)
				result.close();
			if (pstmt != null) {
				pstmt.close();
			}

			// 다시 행의 개수를 세는 변수를 0으로 초기화한 뒤
			rowCnt = 0;
			pstmt = conn.prepareStatement(appliedRowSql);
			pstmt.setString(1, freeId);
			result = pstmt.executeQuery();
			while (result.next())
				// 참여신청한 프로젝트 전체 행의 개수를 세어줌
				rowCnt++;
			
			// 프리랜서 세부사항의 참여신청한 프로젝트의 행 개수를 초기화한 뒤
			freeDetail.getJoinAppliedListPage().setNumOfRow(rowCnt);
			// 현재 페이지 번호도 초기화함
			freeDetail.getJoinAppliedListPage().setPageNum(joinAppliedPageNum);
			if (result != null)
				result.close();
			if (pstmt != null) {
				pstmt.close();
			}
			
			// 마지막으로 참여신청한 프로젝트 쿼리를 실행함
			pstmt = conn.prepareStatement(joinAppliedSql);
			pstmt.setString(1, freeId);
			pstmt.setInt(2, (joinAppliedPageNum - 1) * 10);

			result = pstmt.executeQuery();

			while (result.next()) {
				// 참여신청한 프로젝트 객체를 새로 생성후
				ccm.data.ListOfJoinAppliedList newList = new ccm.data.ListOfJoinAppliedList();
				
				// 필드를 초기화한 후
				newList.setParams(result);
				
				// 프리랜서 참여신청한 프로젝트 리스트에 객체를 추가함 
				freeDetail.getJoinAppliedList().add(newList);
			}

		} catch (SQLException e) {

			e.printStackTrace();

			try {
				if (result != null)
					result.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException localSQLException1) {
			}
		} finally {
			try {
				if (result != null)
					result.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException localSQLException2) {
			}
		}

		return freeDetail;
	}
}

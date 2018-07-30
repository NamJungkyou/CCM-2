package ccm.controller.action.project;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.dao.ProjectAdministrationDAO;
import ccm.data.FreeProfileOfSearch;
import ccm.data.FreelancerListToSearch;
import ccm.data.Page;
import ccm.data.function.ProjUtil;
import ccm.data.table.LangAndDBAndFrame;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 프리랜서 검색 페이지로 이동하는 액션
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class GoToPutInOfSearchFreelancerAction implements Action {
	
	// 검색하는 프리랜서 이름
	private String searchFreeName;
	// 검색하는 프리랜서 이메일
	private String searchFreeEmail;
	// 검색하는 프리랜서 핸드폰번호
	private String searchFreePhone;
	// 검색하는 개발분야
	private String[] searchDevSort;
	// 검색하는 언어 스킬
	private int[] searchLangSkill;
	// 검색하는 DBMS 스킬
	private int[] searchDBMSSkill;
	// 검색하는 프레임워크 스킬
	private int[] searchFrameworkSkill;
	// 검색하는 프리랜서 프로젝트 당시 역할
	private String[] searchProjRole;
	// 프리랜서 목록 정렬옵션
	private int listSortCode;
	
	// 프리랜서 목록 리스트객체
	private ArrayList<FreelancerListToSearch> freeList;
	// 프리랜서 목록 페이지객체
	private Page freeListPage;
	
	// 프리랜서 상세 프로필
	private FreeProfileOfSearch freeDetail;
	
	// 참여신청 프로젝트 목록 정렬 코드
	private int joinAppliedSortCode;
	// 프로젝트 참여기록 목록 정렬 코드
	private int joinedRecordSortCode;
	
	// 선택된 프리랜서 아이디
	private String selectedFreeId;
	
	// UI에 뿌려질 언어, 프레임워크, DBMS 목록
	private LangAndDBAndFrame ldf;
	// 프로젝트 역할 목록
	private String[] projRoleList = { "분석", "설계", "개발", "QA" };
	// 개발분야 목록
	private String[] devSortList = { "웹", "안드로이드", "응용프로그램", "엠베디드" };

	public GoToPutInOfSearchFreelancerAction() {
	}

	/**
	 * 
	 * 요청에서 데이터들을 가져오는 메소드
	 * 
	 * @param request
	 * @throws IOException
	 */
	private void receiveData(HttpServletRequest request) throws IOException {
		
		// 한글설정
		request.setCharacterEncoding("UTF-8");

		// 검색하는 프리랜서 이름, 이메일, 핸드폰번호를 모두 받아온 뒤
		// 다시 한글설정함
		searchFreeName = request.getParameter("searchFreeName");
		if (searchFreeName != null)
			searchFreeName = new String(searchFreeName.getBytes("8859_1"), "UTF-8");
		searchFreeEmail = request.getParameter("searchFreeEmail");
		if (searchFreeEmail != null)
			searchFreeEmail = new String(searchFreeEmail.getBytes("8859_1"), "UTF-8");
		searchFreePhone = request.getParameter("searchFreePhone");
		if (searchFreePhone != null)
			searchFreePhone = new String(searchFreePhone.getBytes("8859_1"), "UTF-8");

		// 프리랜서 개발분야를 받아옴
		searchDevSort = request.getParameterValues("searchDevSort");
		if (searchDevSort != null)
			for (int i = 0; i < searchDevSort.length; i++) {
				// 개발분야를 다시 한글설정 해줌
				searchDevSort[i] = new String(searchDevSort[i].getBytes("8859_1"), "UTF-8");
			}
		if (request.getParameterValues("searchLangSkill") != null) {
			String[] LSString = request.getParameterValues("searchLangSkill");
			searchLangSkill = new int[LSString.length];
			for (int i = 0; i < searchLangSkill.length; i++)
				searchLangSkill[i] = Integer.parseInt(LSString[i]);
		}
		if (request.getParameterValues("searchDBMSSkill") != null) {
			String[] DSString = request.getParameterValues("searchDBMSSkill");
			searchDBMSSkill = new int[DSString.length];
			for (int i = 0; i < searchDBMSSkill.length; i++)
				searchDBMSSkill[i] = Integer.parseInt(DSString[i]);
		}
		if (request.getParameterValues("searchFrameworkSkill") != null) {
			String[] FSString = request.getParameterValues("searchFrameworkSkill");
			searchFrameworkSkill = new int[FSString.length];
			for (int i = 0; i < searchFrameworkSkill.length; i++) {
				searchFrameworkSkill[i] = Integer.parseInt(FSString[i]);
			}
		}
		
		// 프로젝트 역할도 받아와서 다시 한글설정함
		searchProjRole = request.getParameterValues("searchProjRole");
		if (searchProjRole != null)
			for (int i = 0; i < searchProjRole.length; i++)
				searchProjRole[i] = new String(searchProjRole[i].getBytes("8859_1"), "UTF-8");
		
		// 프리랜서 리스트 정렬코드를 받아옴
		listSortCode = (request.getParameter("listSortCode") == null ? 1
				: Integer.parseInt(request.getParameter("listSortCode")));

		// 프리랜서 리스트 페이지번호를 받아온 후
		int freeListPageNum = request.getParameter("freeListPageNum") == null ? 1
				: Integer.parseInt(request.getParameter("freeListPageNum"));
		
		// 페이지 객체를 생성함
		freeListPage = new Page(freeListPageNum);

		// 프리랜서 리스트를 DAO에서 받아옴
		freeList = ProjectAdministrationDAO.getInstance().getFreelancerListToSearch(searchFreeName, searchFreePhone,
				searchFreeEmail, searchDevSort, searchLangSkill, searchDBMSSkill, searchFrameworkSkill, searchProjRole,
				// 정렬 코드를 SQL 구문으로 바꾼 뒤 파라미터에 넣어줌
				ProjUtil.getInstance().freelancerListToSearchCodeToOption(listSortCode), freeListPage);

		// 선택된 프리랜서 아이디를 받아옴
		selectedFreeId = request.getParameter("selectedFreeId");
		
		// 프리랜서가 선택 되었으면
		if ((selectedFreeId != null) && (!selectedFreeId.trim().equals(""))) {
			// 참여신청한 프로젝트 페이지번호를 받아오고 null이면 1로 맞춰줌
			int joinAppliedPageNum = request.getParameter("joinAppliedPageNum") == null ? 1
					: Integer.parseInt(request.getParameter("joinAppliedPageNum"));
			// 프로젝트 참여기록도 똑같이 세팅함
			int joinedRecordPageNum = request.getParameter("joinedRecordPageNum") == null ? 1
					: Integer.parseInt(request.getParameter("joinedRecordPageNum"));

			// 참여신청한 프로젝트 목록, 프로젝트 참여기록의 정렬코드도 받아옴
			joinAppliedSortCode = (request.getParameter("joinAppliedSortCode") == null ? 1
					: Integer.parseInt(request.getParameter("joinAppliedSortCode")));
			joinedRecordSortCode = (request.getParameter("joinedRecordSortCode") == null ? 1
					: Integer.parseInt(request.getParameter("joinedRecordSortCode")));

			// 프리랜서 세부정보를 DAO에서 받아옴
			freeDetail = ProjectAdministrationDAO.getInstance().getFreeProfileOfSearch(selectedFreeId,
					// 정렬코드를 SQL문으로 바꿔서 파라미터로 넘겨줌
					ProjUtil.getInstance().joinAppliedListSortCodeToOption(joinAppliedSortCode),
					ProjUtil.getInstance().joinedRecordSortCodeToOption(joinedRecordSortCode), joinAppliedPageNum,
					joinedRecordPageNum);
		}

		// 언어, 프레임워크, DBMS 목록을 DAO에서 받아옴
		ldf = CommonDAO.getInstance().getLangDBFrame();
	}

	/**
	 * 
	 * 요청, DAO에서 받은 모든 데이터를 UI로 넘겨줌
	 * 
	 * @param request
	 * @throws IOException
	 */
	private void sendData(HttpServletRequest request) throws IOException {
		request.setAttribute("searchFreeName", searchFreeName);
		request.setAttribute("searchFreeEmail", searchFreeEmail);
		request.setAttribute("searchFreePhone", searchFreePhone);
		request.setAttribute("searchDevSort", searchDevSort);
		request.setAttribute("searchLangSkill", searchLangSkill);
		request.setAttribute("searchDBMSSkill", searchDBMSSkill);
		request.setAttribute("searchFrameworkSkill", searchFrameworkSkill);
		request.setAttribute("searchProjRole", searchProjRole);

		request.setAttribute("listSortCode", Integer.valueOf(listSortCode));
		request.setAttribute("freeList", freeList);
		request.setAttribute("freeListPageNum", Integer.valueOf(freeListPage.getPageNum()));
		request.setAttribute("freeListFirstPage", Integer.valueOf(freeListPage.getFirstPage()));
		request.setAttribute("freeListLastPage", Integer.valueOf(freeListPage.getLastPage()));
		request.setAttribute("freeListNumOfPage", Integer.valueOf(freeListPage.getNumOfPage()));

		request.setAttribute("selectedFreeId", selectedFreeId);
		
		// 프리랜서 아이디가 선택되지 않았으면 세부정보에 필요한 데이터들은 넘겨주지 않음
		if ((selectedFreeId != null) && (!selectedFreeId.trim().equals(""))) {
			request.setAttribute("freeDetail", freeDetail);

			request.setAttribute("joinAppliedSortCode", Integer.valueOf(joinAppliedSortCode));
			request.setAttribute("joinAppliedPageNum",
					Integer.valueOf(freeDetail.getJoinAppliedListPage().getPageNum()));
			request.setAttribute("joinAppliedFirstPage",
					Integer.valueOf(freeDetail.getJoinAppliedListPage().getFirstPage()));
			request.setAttribute("joinAppliedLastPage",
					Integer.valueOf(freeDetail.getJoinAppliedListPage().getLastPage()));
			request.setAttribute("joinAppliedNumOfPage",
					Integer.valueOf(freeDetail.getJoinAppliedListPage().getNumOfPage()));
			request.setAttribute("joinedRecordSortCode", Integer.valueOf(joinedRecordSortCode));
			request.setAttribute("joinedRecordPageNum",
					Integer.valueOf(freeDetail.getJoinedRecordsPage().getPageNum()));
			request.setAttribute("joinedRecordFirstPage",
					Integer.valueOf(freeDetail.getJoinedRecordsPage().getFirstPage()));
			request.setAttribute("joinedRecordLastPage",
					Integer.valueOf(freeDetail.getJoinedRecordsPage().getLastPage()));
			request.setAttribute("joinedRecordNumOfPage",
					Integer.valueOf(freeDetail.getJoinedRecordsPage().getNumOfPage()));
		}

		request.setAttribute("ldf", ldf);
		request.setAttribute("projRoleList", projRoleList);
		request.setAttribute("devSortList", devSortList);
	}

	/**
	 * 
	 * UI로 이동하는 메소드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void forward(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("project/putinofsearchfreelancer.jsp").forward(request, response);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글설정
		request.setCharacterEncoding("UTF-8");

		// 데이터를 받고
		receiveData(request);
		
		// 가공된 데이터들을 넘겨줌
		sendData(request);
		// UI 페이지로 이동
		forward(request, response);
	}
}

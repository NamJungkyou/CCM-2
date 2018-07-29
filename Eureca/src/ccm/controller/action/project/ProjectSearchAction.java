package ccm.controller.action.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.dao.ProjectViewDAO;
import ccm.data.ParamInt;
import ccm.data.table.DBMS;
import ccm.data.table.Framework;
import ccm.data.table.Freelancer;
import ccm.data.table.JoinProj;
import ccm.data.table.ProgLang;
import ccm.data.table.ProjectView;

public class ProjectSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 페이지를 이동시킬 주소
		String url = "/project/projectSearch.jsp";

		ProjectViewDAO projectViewDao = ProjectViewDAO.getInstance();
		CommonDAO commonDao = CommonDAO.getInstance();

		// 프로젝트리스트 정렬 기준을 request에서 받음
		String order = request.getParameter("order");
		// 요청을 잘 받아왔는지 확인
		System.out.println("정렬기준 = " + order);

		// 모든 데이터베이스 정보를 가져오는 리스트
		List<DBMS> dbmsList = commonDao.DBMSList();
		// 모든 언어 정보를 가져오는 리스트
		List<ProgLang> langList = commonDao.ProgLangList();
		// 모든 프레임워크 정보를 가져오는 리스트
		List<Framework> frameList = commonDao.FameworkList();

		// 리스트들을 잘 가져왔는지 확인
		System.out.println("db리스트 =" + dbmsList.toString());
		System.out.println("언어 리스트 = " + langList.toString());
		System.out.println("프레임 리스트 = " + frameList.toString());

		
		// 각 리스트들을 request에 할당
		request.setAttribute("dbmsList", dbmsList);
		request.setAttribute("langList", langList);
		request.setAttribute("frameList", frameList);

		// request에서 프로젝트 정보들을 받아 각 변수들에 할당
		String projName = request.getParameter("projNameSearch");
		String[] devCount = request.getParameterValues("devFieldSearch");
		String[] langCount = request.getParameterValues("progLangSearch");
		String[] dbCount = request.getParameterValues("DBMSSearch");
		String[] tfwCount = request.getParameterValues("TOOLfwSearch");
		String time1 = request.getParameter("period1");
		String time2 = request.getParameter("period2");
		
		// 페이지번호
		int pageNum;

		if ((request.getParameter("pageNum") == null) || (request.getParameter("pageNum").equals(""))) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		// 페이지번호가 올바른지 확인
		System.out.println("페이지 번호 = " + pageNum);
		/*-----------------------------------------------검색--------------------------------------------------*/

		// request에서 각 변수들을 잘 받아왔는지 확인
		System.out.println("프로젝트 검색 액션 프로젝트명 = " + projName);
		System.out.println("프로젝트 검색 액션 개발분야 = " + devCount);
		System.out.println("프로젝트 검색 액션 언어 = " + langCount);
		System.out.println("프로젝트 검색 액션 DBMS = " + dbCount);
		System.out.println("프로젝트 검색 액션 툴프레임 = " + tfwCount);
		System.out.println("프로젝트 검색 액션 기간1 = " + time1);
		System.out.println("프로젝트 검색 액션 기간2 = " + time2);

		// 검색 결과를 얻기 위한 객체 생성 및 메소드 호출
		List<ProjectView> projViewList = projectViewDao.searchAllCheckedProject(projName, devCount, langCount, dbCount,
				tfwCount, time1, time2, order, pageNum);
		// 페이징을 하기위한 객체 생성 및 메소드 호출
		ParamInt paramInt = projectViewDao.projectPaging(projName, devCount, langCount, dbCount,
				tfwCount, time1, time2, pageNum);
		
		// 리스트 확인
		System.out.println("프로젝트 검색결과 리스트" + projViewList.toString());

		// request에 변수들을 저장
		request.setAttribute("projViewList", projViewList);
		/*------------------------------페이징을 위한 변수---------------------------------*/
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", paramInt.getPageCount());
		request.setAttribute("firstPage", paramInt.getFirstPage());
		request.setAttribute("lastPage", paramInt.getLastPage());
		/*---------------------------------검색옵션---------------------------------*/
		request.setAttribute("projName", projName);
		request.setAttribute("devCount", devCount);
		request.setAttribute("langCount", langCount);
		request.setAttribute("dbCount", dbCount);
		request.setAttribute("tfwCount", tfwCount);
		request.setAttribute("time1", time1);
		request.setAttribute("time2", time2);

		request.setAttribute("order", order);// 정렬기준

		/*----------------------------------------프로젝트 상세보기------------------------------------*/
		
		String projNum = request.getParameter("projNum");
		System.out.println("프로젝트 번호 = " + projNum);

		// 프로젝트 번호가 null이 아니면 그 프로젝트 번호로 검색한 결과를 각 리스트에 저장
		if (projNum != null && !projNum.equals("")) {
			ProjectView pSelectViewList = projectViewDao.selectAllProjectInfoByProjNum(Integer.parseInt(projNum));
			List<Framework> projFrameList = projectViewDao.selectProjFrameworkByProjNum(Integer.parseInt(projNum));
			List<ProgLang> projLangList = projectViewDao.selectProjProgLangByProjNum(Integer.parseInt(projNum));
			List<Freelancer> freelancerList = projectViewDao.proJoinFreeListByProjNum(Integer.parseInt(projNum));
			List<JoinProj> joinProjList = projectViewDao.proJoinListByProjNum(Integer.parseInt(projNum));
			
			// 프로젝트 검색결과 확인
			System.out.println("선택한 프로젝트 기본정보 = " + pSelectViewList.toString());
			System.out.println("선택한 프로젝트 프레임워크정보 = " + projFrameList.toString());
			System.out.println("선택한 프로젝트 언어정보 = " + projLangList.toString());
			System.out.println("선택한 프로젝트 참여자 정보 = " + freelancerList.toString());
			System.out.println("선택한 프로젝트 참여 정보 = " + joinProjList.toString());
			
			// 검색한 정보들을 담은 변수들를 request에 저장
			request.setAttribute("pSelectViewList", pSelectViewList);
			request.setAttribute("projFrameList", projFrameList);
			request.setAttribute("projLangList", projLangList);
			request.setAttribute("freelancerList", freelancerList);
			request.setAttribute("joinProjList", joinProjList);
		/*---------------------------------참여자목록 페이징-----------------------------------*/	
			
			// 참여자 목록을 페이징 처리하기위한 변수
			int joinFreePageNum;

			if ((request.getParameter("joinFreePageNum") == null) || (request.getParameter("joinFreePageNum").equals(""))) {
				joinFreePageNum = 1;
			} else {
				joinFreePageNum = Integer.parseInt(request.getParameter("joinFreePageNum"));// 페이지 번호
			}
			System.out.println("페이지 번호 = " + joinFreePageNum);
			
			ParamInt joinFreeParamInt = projectViewDao.proJoinFreePaging(pageNum, Integer.parseInt(projNum));
			
			// 참여자 목록의 페이징 처리를 위한 변수들을 request에 저장
			request.setAttribute("joinFreePageNum", joinFreePageNum);
			request.setAttribute("joinFreePageCount", joinFreeParamInt.getPageCount());
			request.setAttribute("joinFreeFirstPage", joinFreeParamInt.getFirstPage());
			request.setAttribute("joinFreeLastPage", joinFreeParamInt.getLastPage());

		}

		// url에 저장된 주소로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

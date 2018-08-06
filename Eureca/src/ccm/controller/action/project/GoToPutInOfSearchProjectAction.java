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

/**
 * 프로젝트 투입 화면에서 프로젝트 검색 버튼을 누르면
 * 
 * 팝업창이 열리면서 이 액션을 실행하고 프로젝트 검색 페이지로 이동
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class GoToPutInOfSearchProjectAction implements Action
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");

		String url = "/project/putinofsearchproject.jsp";

		ProjectViewDAO projectViewDao = ProjectViewDAO.getInstance();
		CommonDAO commonDao = CommonDAO.getInstance();

		String order = request.getParameter("order");// 프로젝트리스트 정렬 기준

		List<DBMS> dbmsList = commonDao.DBMSList();
		List<ProgLang> langList = commonDao.ProgLangList();
		List<Framework> frameList = commonDao.FameworkList();

		request.setAttribute("dbmsList", dbmsList);
		request.setAttribute("langList", langList);
		request.setAttribute("frameList", frameList);

		String projName = request.getParameter("projNameSearch");
		String[] devCount = request.getParameterValues("devFieldSearch");
		String[] langCount = request.getParameterValues("progLangSearch");
		String[] dbCount = request.getParameterValues("DBMSSearch");
		String[] tfwCount = request.getParameterValues("TOOLfwSearch");
		String time1 = request.getParameter("period1");
		String time2 = request.getParameter("period2");
		
		int pageNum;

		if ((request.getParameter("pageNum") == null) || (request.getParameter("pageNum").equals(""))) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));// 페이지 번호
		}
		/*-----------------------------------------------검색--------------------------------------------------*/

		List<ProjectView> projViewList = projectViewDao.searchAllCheckedProject(projName, devCount, langCount, dbCount,
				tfwCount, time1, time2, order, pageNum);
		ParamInt paramInt = projectViewDao.projectPaging(projName, devCount, langCount, dbCount,
				tfwCount, time1, time2, pageNum);
		

		request.setAttribute("projViewList", projViewList);

		/*------------------------------페이징변수-------------------------------------*/
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

		if (projNum != null && !projNum.equals("")) {
			ProjectView pSelectViewList = projectViewDao.selectAllProjectInfoByProjNum(Integer.parseInt(projNum));
			List<Framework> projFrameList = projectViewDao.selectProjFrameworkByProjNum(Integer.parseInt(projNum));
			List<ProgLang> projLangList = projectViewDao.selectProjProgLangByProjNum(Integer.parseInt(projNum));
			List<Freelancer> freelancerList = projectViewDao.proJoinFreeListByProjNum(Integer.parseInt(projNum));
			List<JoinProj> joinProjList = projectViewDao.proJoinListByProjNum(Integer.parseInt(projNum));
			
		/*---------------------------------참여자목록 페이징-----------------------------------*/	
			int joinFreePageNum;

			if ((request.getParameter("joinFreePageNum") == null) || (request.getParameter("joinFreePageNum").equals(""))) {
				joinFreePageNum = 1;
			} else {
				joinFreePageNum = Integer.parseInt(request.getParameter("joinFreePageNum"));// 페이지 번호
			}
			
			ParamInt joinFreeParamInt = projectViewDao.proJoinFreePaging(pageNum, Integer.parseInt(projNum));
			
			request.setAttribute("projNum", projNum);
			request.setAttribute("joinFreePageNum", joinFreePageNum);
			request.setAttribute("joinFreePageCount", joinFreeParamInt.getPageCount());
			request.setAttribute("joinFreeFirstPage", joinFreeParamInt.getFirstPage());
			request.setAttribute("joinFreeLastPage", joinFreeParamInt.getLastPage());

			request.setAttribute("pSelectViewList", pSelectViewList);
			request.setAttribute("projFrameList", projFrameList);
			request.setAttribute("projLangList", projLangList);
			request.setAttribute("freelancerList", freelancerList);
			request.setAttribute("joinProjList", joinProjList);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

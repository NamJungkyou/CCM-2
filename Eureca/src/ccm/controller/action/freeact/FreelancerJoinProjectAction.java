package ccm.controller.action.freeact;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.dao.FreelancerDAO;
import ccm.dao.ProjectViewDAO;
import ccm.data.ParamInt;
import ccm.data.table.DBMS;
import ccm.data.table.Framework;
import ccm.data.table.Freelancer;
import ccm.data.table.ProgLang;
import ccm.data.table.ProjectView;

public class FreelancerJoinProjectAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/freelancer/freelancerJoinProject.jsp";
		ProjectViewDAO projectViewDao = ProjectViewDAO.getInstance();
		FreelancerDAO freelancerDao = FreelancerDAO.getInstance();
		CommonDAO commonDao = CommonDAO.getInstance();

		String order = request.getParameter("order");// 프로젝트리스트 정렬 기준
		System.out.println("정렬기준 = " + order);

		List<DBMS> dbmsList = commonDao.DBMSList();
		List<ProgLang> langList = commonDao.ProgLangList();
		List<Framework> frameList = commonDao.FameworkList();

		System.out.println("db리스트 =" + dbmsList.toString());
		System.out.println("언어 리스트 = " + langList.toString());
		System.out.println("프레임 리스트 = " + frameList.toString());

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
		System.out.println("페이지 번호 = " + pageNum);
		/*-----------------------------------------------검색--------------------------------------------------*/

		System.out.println("프로젝트 검색 액션 프로젝트명 = " + projName);
		System.out.println("프로젝트 검색 액션 개발분야 = " + devCount);
		System.out.println("프로젝트 검색 액션 언어 = " + langCount);
		System.out.println("프로젝트 검색 액션 DBMS = " + dbCount);
		System.out.println("프로젝트 검색 액션 툴프레임 = " + tfwCount);
		System.out.println("프로젝트 검색 액션 기간1 = " + time1);
		System.out.println("프로젝트 검색 액션 기간2 = " + time2);

		String result = request.getParameter("result");

		if (result != null && result.equals("1")) {
			List<ProjectView> projViewList = projectViewDao.searchAllCheckedProject(projName, devCount, langCount,
					dbCount, tfwCount, time1, time2, order, pageNum);

			for (int i = 0; i < projViewList.size(); i++) {
				List<Framework> projFrameList = projectViewDao
						.selectProjFrameworkByProjNum(projViewList.get(i).getProjNum());
				List<ProgLang> projLangList = projectViewDao
						.selectProjProgLangByProjNum(projViewList.get(i).getProjNum());
				projViewList.get(i).setFrames(projFrameList);
				projViewList.get(i).setLangs(projLangList);
			}

			ParamInt paramInt = projectViewDao.projectPaging(projName, devCount, langCount, dbCount, tfwCount, time1,
					time2, pageNum);

			System.out.println("프로젝트 검색결과 리스트" + projViewList.toString());

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

			/*-----------------------------------프로젝트 참여신청----------------------------------*/
			String projNum = request.getParameter("projNum");
			System.out.println("프로젝트 번호 = " + projNum);

			if (projNum != null && !projNum.equals("")) {

				Freelancer loginfree = (Freelancer) request.getSession().getAttribute("loginfree");

				freelancerDao.freelancerJoinApply(loginfree.getFreeId(), Integer.parseInt(projNum));
			}
			request.setAttribute("result", result);
		}else {
			result="0";
			request.setAttribute("result", result);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}
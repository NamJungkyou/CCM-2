package ccm.controller.action.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;
import ccm.data.ParamInt;
import ccm.data.ProjJoinedFreelancerForList;
import ccm.data.function.ProjUtil;

public class GoToProjectJoinAcceptAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchFreelancerName = request.getParameter("searchFreelancerName");
		if (searchFreelancerName != null)
			searchFreelancerName = new String(searchFreelancerName.getBytes("8859_1"), "UTF-8");
		System.out.println("야 간지난다!!!!!!! " + searchFreelancerName);
		searchFreelancerName = searchFreelancerName == null ? "" : searchFreelancerName;
		String pageNumParam = request.getParameter("projJoinedFreePageNum"); // 페이지번호
		Integer pageNum = pageNumParam != null ? Integer.parseInt(pageNumParam) : 1;
		String listSortOptionParam = request.getParameter("listSortOption") == null ? "1"
				: request.getParameter("listSortOption");
		int listSortOption = Integer.parseInt(listSortOptionParam);
		String listSortOptionString = ProjUtil.getInstance().projJoinAcceptListCodeToOption(listSortOption);
		ParamInt numOfList = new ParamInt(0);
		ProjectAdministrationDAO pDao = ProjectAdministrationDAO.getInstance();

		ProjJoinedFreelancerForList[] list = null;
		list = pDao.getJoinedFreelancerList(listSortOptionString, numOfList, pageNum, new String(searchFreelancerName));

		int intNumOfPage = (int) Math.ceil(((double) numOfList.getIntValue() / 10.0f));

		int firstPage = (int) (Math.round((pageNum / 10.0f)) * 10) + 1;
		int lastPage = (int) Math.min(firstPage + 9, intNumOfPage);
		System.out.println("페이지넘 !!!!!!!!!!!!!!!!!!!! " + pageNum);
		request.setAttribute("searchFreelancerName", searchFreelancerName);
		request.setAttribute("numOfPage", intNumOfPage);
		request.setAttribute("projJoinedFreePageNum", pageNum);
		request.setAttribute("projJoinedFreeFirstPage", firstPage);
		request.setAttribute("projJoinedFreeLastPage", lastPage);
		request.setAttribute("projJoinedFreeList", list);
		request.setAttribute("listSortOption", listSortOption);
		request.getRequestDispatcher("/project/projectjoinaccept.jsp").forward(request, response);
	}
}

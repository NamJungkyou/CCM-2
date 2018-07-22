package ccm.controller.action.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.ProjectViewDAO;
import ccm.data.table.Framework;
import ccm.data.table.ProgLang;
import ccm.data.table.ProjectView;

public class ProjectSelectAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/project/projectSelectByProjNum.jsp";
		
		String projNum = request.getParameter("projNum");
		
		ProjectViewDAO projectViewDao = ProjectViewDAO.getInstance();
		
		ProjectView pSelectViewList = projectViewDao.selectAllProjectInfoByProjNum(Integer.parseInt(projNum));
		List<Framework> projFrameList = projectViewDao.selectProjFrameworkByProjNum(Integer.parseInt(projNum));
		List<ProgLang> projLangList = projectViewDao.selectProjProgLangByProjNum(Integer.parseInt(projNum));
		
		System.out.println(pSelectViewList.toString());
		System.out.println(projFrameList.toString());
		System.out.println(projLangList.toString());
		
		request.setAttribute("pSelectViewList", pSelectViewList);
		request.setAttribute("projFrameList", projFrameList);
		request.setAttribute("projLangList", projLangList);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
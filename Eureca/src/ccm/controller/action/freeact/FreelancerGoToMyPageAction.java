package ccm.controller.action.freeact;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;
import ccm.data.table.JoinProjectView;

public class FreelancerGoToMyPageAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = "/freelancer/myPage.jsp";
		Freelancer loginfree = (Freelancer) request.getSession().getAttribute("loginfree");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		List<JoinProjectView> jpViewList = fDao.selectJoinProjectViewList(loginfree.getFreeId());
		
		String freeState = fDao.getFreeState(loginfree.getFreeId());
		
		request.setAttribute("freeState", freeState);
		request.setAttribute("jpViewList", jpViewList);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

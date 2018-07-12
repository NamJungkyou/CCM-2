package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;

public class FreelancerGoToMyPageAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Freelancer loginfree = (Freelancer) request.getSession().getAttribute("loginfree");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		String freeState = fDao.getFreeState(loginfree.getFreeId());
		
		request.setAttribute("freeState", freeState);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("freelancer/myPage.jsp");
		dispatcher.forward(request, response);
	}

}

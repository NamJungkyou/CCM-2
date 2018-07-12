package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;

public class FreelancerUpdateProfileFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/freelancer/freelancerProfile.jsp";
		
		Freelancer loginfree = 
				(Freelancer)request.getSession().getAttribute("loginfree");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		fDao.updateProfile(loginfree);
		
		loginfree = fDao.showProfile(loginfree.getFreeId());
		
		request.getSession().setAttribute("loginfree", loginfree);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

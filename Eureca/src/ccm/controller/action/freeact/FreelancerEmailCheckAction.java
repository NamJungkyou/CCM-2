package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;

public class FreelancerEmailCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String freeEmail = request.getParameter("freeEmail");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		int result = fDao.confirmEmail(freeEmail);
		
		request.setAttribute("freeEmail", freeEmail);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("freelancer/emailCheck.jsp");
		dispatcher.forward(request, response);

	}

}

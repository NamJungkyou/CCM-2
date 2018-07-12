package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;

public class FreelancerPhoneCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String freePhone = request.getParameter("freePhone");
		
		FreelancerDAO eDao = FreelancerDAO.getInstance();
		
		int result = eDao.confirmPhone(freePhone);
		
		request.setAttribute("freePhone", freePhone);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("freelancer/phoneCheck.jsp");
		dispatcher.forward(request, response);


	}

}

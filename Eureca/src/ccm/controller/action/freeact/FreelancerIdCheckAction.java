package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;

public class FreelancerIdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String freeId = request.getParameter("freeId");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		int result = fDao.confirmID(freeId);
		
		request.setAttribute("freeId", freeId);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("freelancer/idCheck.jsp");
		dispatcher.forward(request, response);

	}

}

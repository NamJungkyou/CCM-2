package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;

public class EmployeeEmailCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String empEmail = request.getParameter("empEmail");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		int result = eDao.confirmEmail(empEmail);
		
		request.setAttribute("empEmail", empEmail);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee/emailCheck.jsp");
		dispatcher.forward(request, response);

	}

}

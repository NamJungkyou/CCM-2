package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;

public class EmployeeIdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empId = request.getParameter("empId");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		int result = eDao.confirmID(empId);
		
		request.setAttribute("empId", empId);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee/idCheck.jsp");
		dispatcher.forward(request, response);
		
		

	}

}

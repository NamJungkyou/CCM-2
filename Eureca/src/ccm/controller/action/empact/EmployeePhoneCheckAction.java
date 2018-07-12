package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;

public class EmployeePhoneCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String empPhone = request.getParameter("empPhone");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		int result = eDao.confirmPhone(empPhone);
		
		request.setAttribute("empPhone", empPhone);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee/phoneCheck.jsp");
		dispatcher.forward(request, response);

	}

}

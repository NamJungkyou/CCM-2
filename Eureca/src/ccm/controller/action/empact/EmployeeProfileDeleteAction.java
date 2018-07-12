package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;

public class EmployeeProfileDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*Freelancer loginfree = (Freelancer) request.getSession().getAttribute("loginfree");*/
		
		String empid = request.getParameter("empid");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		eDao.deleteEmployee(empid);
		
		new EmployeeProfileAction().execute(request, response); 

	}

}

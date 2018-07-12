package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Career;
import ccm.data.table.Education;
import ccm.data.table.Freelancer;

public class EmpFreelancerProfileUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/employee/empFreelancerProfileUpdate.jsp";
		String freeId = request.getParameter("freeId");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		Freelancer fVo = eDao.showFreelancerProfile(freeId);
		Freelancer fVo1 = fDao.showAccount(freeId);
		ArrayList<Education> eVo = fDao.showEducation(freeId);	
		ArrayList<Career> cVo = fDao.showCareer(freeId);
		
		request.setAttribute("Freelancer", fVo);
		request.setAttribute("Freelancer2", fVo1);
		request.setAttribute("Education", eVo);
		request.setAttribute("Career", cVo);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

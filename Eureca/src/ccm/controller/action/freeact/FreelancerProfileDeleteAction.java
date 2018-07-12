package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;

public class FreelancerProfileDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Freelancer loginfree = (Freelancer) request.getSession().getAttribute("loginfree");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		fDao.deleteFreelancer(loginfree.getFreeId());
		
		new FreelancerProfileAction().execute(request, response); 

	}

}

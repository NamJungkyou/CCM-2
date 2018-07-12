package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;

public class FreelancerBankUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Freelancer fVo = new Freelancer();
		
		fVo.setFreeBank(request.getParameter("freeBank"));
		fVo.setFreeAccName(request.getParameter("freeAccName"));
		fVo.setFreeAccount(request.getParameter("freeAccount"));
		fVo.setFreeId(request.getParameter("freeId"));

		FreelancerDAO fDao = FreelancerDAO.getInstance();
		fDao.updateAccount(fVo);
		
		System.out.println(request.getParameter("freeBank"));
		System.out.println(request.getParameter("freeAccName"));
		System.out.println(request.getParameter("freeAccount"));
		System.out.println(request.getParameter("freeId"));

		new FreelancerProfileAction().execute(request, response);

	}

}

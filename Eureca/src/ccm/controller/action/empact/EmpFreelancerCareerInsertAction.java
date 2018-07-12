package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Career;

public class EmpFreelancerCareerInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Career> cVo = new ArrayList<Career>();
		
		String[] careerNums = request.getParameterValues("careerNum");
		String[] careerCompanys = request.getParameterValues("careerCompany");
		String[] companyJoinDates = request.getParameterValues("companyJoinDate");
		String[] companyDropDates = request.getParameterValues("companyDropDate");
		String[] careerPositions = request.getParameterValues("careerPosition");
		String[] careerJobs = request.getParameterValues("careerJob");
		String freeId = request.getParameter("freeId");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		for(int i = 0; i < careerNums.length; i++) {
			Career c = new Career();
			
			c.setCareerNum(careerNums[i] == null || careerNums[i].equals("") ? fDao.getNewCareerNum() : Integer.parseInt(careerNums[i]));
			c.setCareerCompany(careerCompanys[i]);
			c.setCompanyJoinDate(companyJoinDates[i]);
			c.setCompanyDropDate(companyDropDates[i]);
			c.setCareerPosition(careerPositions[i]);
			c.setCareerJob(careerJobs[i]);
			c.setFreeId(freeId);
			
			cVo.add(c);		
		}
		
		fDao.updateCareer(cVo);
		
		new EmpFreelancerProfileAction().execute(request, response);

		}

	}


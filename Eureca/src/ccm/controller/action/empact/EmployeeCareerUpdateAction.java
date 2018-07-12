package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.data.table.Career;

public class EmployeeCareerUpdateAction implements Action {

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
		String empId = request.getParameter("empId");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		for(int i = 0; i < careerNums.length; i++) {
			Career c = new Career();
			
			c.setCareerNum(careerNums[i] == null || careerNums[i].equals("") ? eDao.getNewCareerNum() : Integer.parseInt(careerNums[i]));
			c.setCareerCompany(careerCompanys[i]);
			c.setCompanyJoinDate(companyJoinDates[i]);
			c.setCompanyDropDate(companyDropDates[i]);
			c.setCareerPosition(careerPositions[i]);
			c.setCareerJob(careerJobs[i]);
			c.setEmpId(empId);
			
			cVo.add(c);		
		}
		
		
/*		for(Career ed : cVo)
		{
			System.out.println("이거는 커리어업데이트액션입니다 : " + ed.getCareerNum()
			+ ed.getCareerCompany() + ed.getCompanyJoinDate() + ed.getCompanyDropDate()
			+ ed.getCareerPosition() + ed.getCareerJob());
		}
		
		System.out.println("empididididididid" + empId);*/
		
		
		eDao.updateCareer(cVo);
		
		new EmployeeProfileAction().execute(request, response);
		/*new EmployeeProfileInsertAction().execute(request, response);*/

	}

}

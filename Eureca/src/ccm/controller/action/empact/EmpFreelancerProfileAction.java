package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.data.table.Career;
import ccm.data.table.Education;
import ccm.data.table.Freelancer;

public class EmpFreelancerProfileAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/employee/empFreelancerProfileInsert.jsp";
		
		/*EmployeeDAO eDao = EmployeeDAO.getInstance();*/
		
		Freelancer fVo = new Freelancer();
		
		String freeId = request.getParameter("freeId");
		String freePw = request.getParameter("freePw");
		String freeName = request.getParameter("freeName");
		String freeBirth = request.getParameter("freeBirth");
		String freeSex = request.getParameter("freeSex");
		String freePhone = request.getParameter("freePhone");
		String freeEmail = request.getParameter("freeEmail");
		String freeMarried = request.getParameter("freeMarried");
		String freeFrontAddr = request.getParameter("freeFrontAddr");
		String freeRearAddr = request.getParameter("freeRearAddr");
		String freeBank = request.getParameter("freeBank");
		String freeAccName = request.getParameter("freeAccName");
		String freeAccount = request.getParameter("freeAccount");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		ArrayList<Education> edVo = new ArrayList<Education>();
		
		String[] eduNum = request.getParameterValues("eduNum");
		String[] eduSchool = request.getParameterValues("eduSchool");
		String[] eduMajor = request.getParameterValues("eduMajor");
		String[] eduDeploma = request.getParameterValues("eduDeploma");
		String[] schoolJoinDate = request.getParameterValues("schoolJoinDate");
		String[] schoolGraduatedDate = request.getParameterValues("schoolGraduatedDate");
		
		if(eduNum != null)
		{
			for(int i = 0; i < eduNum.length; i++)
			{
				Education edu = new Education();
				edu.setEduNum(eduNum[i] == null || eduNum[i].equals("") ? eDao.getNewEduNum() : Integer.parseInt(eduNum[i]));
				edu.setEduSchool(eduSchool[i]);
				edu.setEduMajor(eduMajor[i]);
				edu.setEduDeploma(eduDeploma[i]);
				edu.setSchoolJoinDate(schoolJoinDate[i]);
				edu.setSchoolGraduatedDate(schoolGraduatedDate[i]);
				edu.setEmpId(freeId);
				
				edVo.add(edu);
			}
		}
		
		
		ArrayList<Career> cVo = new ArrayList<Career>();
		
		String[] careerNum = request.getParameterValues("careerNum");
		String[] careerCompany = request.getParameterValues("careerCompany");
		String[] companyJoinDate = request.getParameterValues("companyJoinDate");
		String[] companyDropDate = request.getParameterValues("companyDropDate");
		String[] careerPosition = request.getParameterValues("careerPosition");
		String[] careerJob = request.getParameterValues("careerJob");
		
		if(careerNum != null)
		{
		for(int i = 0; i < careerNum.length; i++) {
			Career c = new Career();
			
			c.setCareerNum(careerNum[i] == null || careerNum[i].equals("") ? eDao.getNewCareerNum() : Integer.parseInt(careerNum[i]));
			c.setCareerCompany(careerCompany[i]);
			c.setCompanyJoinDate(companyJoinDate[i]);
			c.setCompanyDropDate(companyDropDate[i]);
			c.setCareerPosition(careerPosition[i]);
			c.setCareerJob(careerJob[i]);
			c.setEmpId(freeId);
			
			cVo.add(c);		
			}
		}
		
		request.setAttribute("Freelancer", fVo);
		
		request.setAttribute("freeId", freeId);
		request.setAttribute("freePw", freePw);
		request.setAttribute("freeName", freeName);
		request.setAttribute("freeBirth", freeBirth);
		request.setAttribute("freeSex", freeSex);
		request.setAttribute("freePhone", freePhone);
		request.setAttribute("freeEmail", freeEmail);
		request.setAttribute("freeMarried", freeMarried);
		request.setAttribute("freeFrontAddr", freeFrontAddr);
		request.setAttribute("freeRearAddr", freeRearAddr);
		request.setAttribute("freeBank", freeBank);
		request.setAttribute("freeAccName", freeAccName);
		request.setAttribute("freeAccount", freeAccount);
		
		
		request.setAttribute("Education", edVo);
		
		request.setAttribute("eduNum", eduNum);
		request.setAttribute("eduSchool", eduSchool);
		request.setAttribute("eduMajor", eduMajor);
		request.setAttribute("eduDeploma", eduDeploma);
		request.setAttribute("schoolJoinDate", schoolJoinDate);
		request.setAttribute("schoolGraduatedDate", schoolGraduatedDate);
		
		
		
		request.setAttribute("Career", cVo);
		
		request.setAttribute("careerNum", careerNum);
		request.setAttribute("careerCompany", careerCompany);
		request.setAttribute("companyJoinDate", companyJoinDate);
		request.setAttribute("companyDropDate", companyDropDate);
		request.setAttribute("careerPosition", careerPosition);
		request.setAttribute("careerJob", careerJob);
		
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}

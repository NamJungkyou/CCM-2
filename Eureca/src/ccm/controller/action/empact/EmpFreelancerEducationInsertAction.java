package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Education;

public class EmpFreelancerEducationInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Education> eVo = new ArrayList<Education>();
		
		String[] eduNums = request.getParameterValues("eduNum");
		for(String s : eduNums)System.out.println(s.toString());
		String[] eduSchools = request.getParameterValues("eduSchool");
		/*for(String s : eduSchools)System.out.println(s.toString());*/
		String[] eduMajors = request.getParameterValues("eduMajor");
		String[] eduDeplomas = request.getParameterValues("eduDeploma");
		String[] schoolJoinDates = request.getParameterValues("schoolJoinDate");
		String[] schoolGraduatedDates = request.getParameterValues("schoolGraduatedDate");
		String freeId = request.getParameter("freeId");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		for(int i = 0; i < eduNums.length; i++)
		{
			Education e = new Education();
			
			e.setEduNum(eduNums[i] == null || eduNums[i].equals("") ? fDao.getNewEduNum() : Integer.parseInt(eduNums[i]));
			e.setEduSchool(eduSchools[i]);
			e.setEduMajor(eduMajors[i]);
			e.setEduDeploma(eduDeplomas[i]);
			e.setSchoolJoinDate(schoolJoinDates[i]);
			e.setSchoolGraduatedDate(schoolGraduatedDates[i]);
			e.setFreeId(freeId);
			
			eVo.add(e);
		}
		
/*		for(Education ed : eVo)
		{
			System.out.println("**********edededed : " + ed.getSchoolGraduatedDateyy()
			+ ed.getSchoolGraduatedDatemm() + ed.getSchoolGraduatedDatedd());
		}*/
		
		fDao.updateEducation(eVo);
		
		new EmpFreelancerProfileAction().execute(request, response);

	}

}

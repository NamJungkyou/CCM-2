package ccm.controller.action.freeact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Education;

public class FreelancerEducationUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Education> eVo = new ArrayList<Education>();
		
		String[] eduNums = request.getParameterValues("eduNum");
//		for(String s : eduNums)System.out.println("+++++++" + s.toString());
		String[] eduSchools = request.getParameterValues("eduSchool");
		/*for(String s : eduSchools)System.out.println(s.toString());*/
		String[] eduMajors = request.getParameterValues("eduMajor");
		String[] eduDeplomas = request.getParameterValues("eduDeploma");
		String[] schoolJoinDates = request.getParameterValues("schoolJoinDate");
		String[] schoolGraduatedDates = request.getParameterValues("schoolGraduatedDate");
		String freeId = request.getParameter("freeId");
//		System.out.println("+++++++++++" + freeId);
		
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
		
		new FreelancerProfileAction().execute(request, response);
		
				
		/*eVo.setEduNum(Integer.parseInt(request.getParameter("eduNum")));
		eVo.setEduSchool(request.getParameter("eduSchool"));
		eVo.setEduMajor(request.getParameter("eduMajor"));
		eVo.setEduDeploma(request.getParameter("eduDeploma"));
		eVo.setSchoolJoinDateyy(request.getParameter("schoolJoinDateyy"));
		eVo.setSchoolJoinDatemm(request.getParameter("schoolJoinDatemm"));
		eVo.setSchoolJoinDatedd(request.getParameter("schoolJoinDatedd"));*/
		/*eVo.setSchoolJoinDate(Date.valueOf(request.getParameter("schoolJoinDate")));*/
		/*eVo.setSchoolGraduatedDateyy(request.getParameter("schoolGraduatedDateyy"));
		eVo.setSchoolGraduatedDatemm(request.getParameter("schoolGraduatedDatemm"));
		eVo.setSchoolGraduatedDatedd(request.getParameter("schoolGraduatedDatedd"));
		eVo.setFreeId(request.getParameter("freeId"));*/
		
/*		eVo.setEduNum(Integer.parseInt(request.getParameter("eduNum")));
		eVo.setEduSchool(request.getParameter("eduSchool"));
		eVo.setEduMajor(request.getParameter("eduMajor"));
		eVo.setEduDeploma(request.getParameter("eduDeploma"));
		eVo.setSchoolJoinDate(Date.valueOf(request.getParameter("schoolJoinDate")));
		eVo.setSchoolGraduatedDate(Date.valueOf(request.getParameter("schoolGraduatedDate")));
		eVo.setFreeId(request.getParameter("freeId"));*/
		
		
		/*FreelancerDAO fDao = FreelancerDAO.getInstance();
		fDao.updateEducation( eVo);
		
		System.out.println(request.getParameter("eduNum"));
		System.out.println(request.getParameter("eduSchool"));
		System.out.println(request.getParameter("eduMajor"));
		System.out.println(request.getParameter("eduDeploma"));
		System.out.println(request.getParameter("schoolJoinDate"));
		System.out.println(request.getParameter("schoolGraduatedDate"));
		
		new FreelancerProfileAction().execute(request, response);*/
		

	}

}

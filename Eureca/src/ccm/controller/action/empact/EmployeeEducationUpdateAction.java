package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.data.table.Education;

public class EmployeeEducationUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Education> eVo = new ArrayList<Education>();
		
		String[] eduNums = request.getParameterValues("eduNum");
		/*for(String s : eduNums)System.out.println("에듀넘이들어가나안들어가나!"+s.toString());*/
		String[] eduSchools = request.getParameterValues("eduSchool");
		/*for(String s : eduSchools)System.out.println(s.toString());*/
		String[] eduMajors = request.getParameterValues("eduMajor");
		String[] eduDeplomas = request.getParameterValues("eduDeploma");
		String[] schoolJoinDates = request.getParameterValues("schoolJoinDate");
		String[] schoolGraduatedDates = request.getParameterValues("schoolGraduatedDate");
		String empId = request.getParameter("empId");
		
		/*System.out.println("이엠피아이디가 들어가나 안들어가나 보자!" + empId);*/
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		for(int i = 0; i < eduNums.length; i++)
		{
			Education e = new Education();
			
			e.setEduNum(eduNums[i] == null || eduNums[i].equals("") ? eDao.getNewEduNum() : Integer.parseInt(eduNums[i]));
			e.setEduSchool(eduSchools[i]);
			e.setEduMajor(eduMajors[i]);
			e.setEduDeploma(eduDeplomas[i]);
			e.setSchoolJoinDate(schoolJoinDates[i]);
			e.setSchoolGraduatedDate(schoolGraduatedDates[i]);
			e.setEmpId(empId);
			
			eVo.add(e);
		}
		
		
		eDao.updateEducation(eVo);
		
		new EmployeeProfileAction().execute(request, response);
		/*new EmployeeProfileInsertAction().execute(request, response);*/
		
/*		for(Education ed : eVo)
		{
			System.out.println("**********edededed : " + ed.getSchoolGraduatedDateyy()
			+ ed.getSchoolGraduatedDatemm() + ed.getSchoolGraduatedDatedd());
		}*/
		/*System.out.println("empididididididid" + empId);*/
		
		
//		new EmployeeProfileInsertFormAction().execute(request, response);

	}

}

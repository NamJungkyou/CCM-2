package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.data.table.Education;
import ccm.data.table.Employee;

public class EmployeeProfileInsertFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/employee/employeeProfileInsert.jsp";
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		String empId = request.getParameter("empId");
		String empPw = request.getParameter("empPw");
		String empName = request.getParameter("empName");
		String empDept = request.getParameter("empDept");
		String empDuty = request.getParameter("empDuty");
		String empJoinDate = request.getParameter("empJoinDate");
		String empPicture = request.getParameter("empPicture");
		String empAuth = request.getParameter("empAuth");
		String empBirth = request.getParameter("empBirth");
		String empSex = request.getParameter("empSex");
		String empMarried = request.getParameter("empMarried");
		String empPhone = request.getParameter("empPhone");
		String empEmail = request.getParameter("empEmail");
		String empFrontAddr = request.getParameter("empFrontAddr");
		String empRearAddr = request.getParameter("empRearAddr");
		String empBank = request.getParameter("empBank");
		String empAccName = request.getParameter("empAccName");
		String empAccount = request.getParameter("empAccount");
		
		
		
		
		ArrayList<Education> edVo = new ArrayList<Education>();
		
		String[] eduNums = request.getParameterValues("eduNum");
		/*System.out.println(eduNums == null ? "에듀넘널" : "널아님");*/
		/*for(String s : eduNums)System.out.println(s.toString());*/
		String[] eduSchools = request.getParameterValues("eduSchool");
		/*for(String s : eduSchools)System.out.println(s.toString());*/
		String[] eduMajors = request.getParameterValues("eduMajor");
		String[] eduDeplomas = request.getParameterValues("eduDeploma");
		String[] schoolJoinDates = request.getParameterValues("schoolJoinDate");
		String[] schoolGraduatedDates = request.getParameterValues("schoolGraduatedDate");
		/*String empId = request.getParameter("empId");*/
		
	
//		Employee eVo = eDao.showProfile(url);
		
		
		Employee eVo = new Employee();
		
		eVo.setEmpId(request.getParameter("empId"));
		eVo.setEmpPw(request.getParameter("empPw"));
		eVo.setEmpName(request.getParameter("empName"));
		eVo.setEmpDept(request.getParameter("empDept"));
		eVo.setEmpDuty(request.getParameter("empDuty"));
		eVo.setEmpJoinDate(request.getParameter("empJoinDate"));
		eVo.setEmpPicture(request.getParameter("empPicture"));
		/*eVo.setEmpDropDate(request.getParameter("empDropDate"));*/
		eVo.setEmpAuth(Boolean.parseBoolean(request.getParameter("empAuth")));
		eVo.setEmpBirth(request.getParameter("empBirth"));
		eVo.setEmpSex(Boolean.parseBoolean(request.getParameter("empSex")));
		eVo.setEmpMarried(Boolean.parseBoolean(request.getParameter("empMarried")));
		eVo.setEmpPhone(request.getParameter("empPhone"));
		eVo.setEmpEmail(request.getParameter("empEmail"));
		eVo.setEmpFrontAddr(request.getParameter("empFrontAddr"));
		eVo.setEmpRearAddr(request.getParameter("empRearAddr"));
		eVo.setEmpBank(request.getParameter("empBank"));
		eVo.setEmpAccName(request.getParameter("empAccName"));
		eVo.setEmpAccount(request.getParameter("empAccount"));

		
		if(eduNums != null)
		{
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
				
				edVo.add(e);
			}		
		}
		
		
		
		eDao.insertProfile(eVo);
		
		eDao.updateEducation(edVo);
		

		
		/*EmployeeDAO eDao = EmployeeDAO.getInstance();*/
		
	
		
		request.setAttribute("Employee", eVo);
		
		request.setAttribute("empId", empId);
		request.setAttribute("empPw", empPw);
		request.setAttribute("empName", empName);
		request.setAttribute("empDept", empDept);
		request.setAttribute("empDuty", empDuty);
		request.setAttribute("empJoinDate", empJoinDate);
		request.setAttribute("empPicture", empPicture);
		request.setAttribute("empAuth", empAuth);
		request.setAttribute("empBirth", empBirth);
		request.setAttribute("empSex", empSex);
		request.setAttribute("empMarried", empMarried);
		request.setAttribute("empPhone", empPhone);
		request.setAttribute("empEmail", empEmail);
		request.setAttribute("empFrontAddr", empFrontAddr);
		request.setAttribute("empRearAddr", empRearAddr);
		request.setAttribute("empBank", empBank);
		request.setAttribute("empAccName", empAccName);
		request.setAttribute("empAccount", empAccount);
		
		
		request.setAttribute("Education", edVo);
		
		request.setAttribute("eduNums", eduNums);
		request.setAttribute("eduSchools", eduSchools);
		request.setAttribute("eduMajors", eduMajors);
		request.setAttribute("eduDeplomas", eduDeplomas);
		request.setAttribute("schoolJoinDates", schoolJoinDates);
		request.setAttribute("schoolGraduatedDates", schoolGraduatedDates);
		/*request.setAttribute("empId", empId);*/
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}

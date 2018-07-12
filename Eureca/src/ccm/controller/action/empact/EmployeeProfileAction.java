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
import ccm.data.table.Employee;

public class EmployeeProfileAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      
      String url = "/employee/employeeProfileInsert.jsp";
      
      /*EmployeeDAO eDao = EmployeeDAO.getInstance();*/
      
      Employee eVo = new Employee();
      
      String empId = request.getParameter("empId");
      String empPw = request.getParameter("empPw");
      String empName = request.getParameter("empName");
      String empDept = request.getParameter("empDept");
      String empDuty = request.getParameter("empDuty");
      String empJoinDate = request.getParameter("empJoinDate");
      String empPicture = request.getParameter("empPicture");
      String empFilePath = request.getParameter("empFilePath");
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
            edu.setEmpId(empId);
            
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
         c.setEmpId(empId);
         
         cVo.add(c);      
         }
      }
      
      request.setAttribute("Employee", eVo);
      
      request.setAttribute("empId", empId);
      request.setAttribute("empPw", empPw);
      request.setAttribute("empName", empName);
      request.setAttribute("empDept", empDept);
      request.setAttribute("empDuty", empDuty);
      request.setAttribute("empJoinDate", empJoinDate);
      request.setAttribute("empPicture", empPicture);
      request.setAttribute("empFilePath", empFilePath);
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
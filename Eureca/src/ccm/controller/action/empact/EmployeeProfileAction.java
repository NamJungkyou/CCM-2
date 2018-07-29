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
/**
 * 사원등록페이지에서 입력된 사원정보를 등록하는 액션(MultipartRequest로 Parameter값을 받지않고, request로 받을때)
 * 커맨드값 : employee_Profile
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmployeeProfileAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      
      String url = "/employee/employeeProfileInsert.jsp";
      
      // 사원 기본정보
      Employee eVo = new Employee();
      
      String empId = request.getParameter("empId"); // 아이디
      String empPw = request.getParameter("empPw"); // 비밀번호
      String empName = request.getParameter("empName"); // 이름
      String empDept = request.getParameter("empDept"); // 부서
      String empDuty = request.getParameter("empDuty"); // 직책
      String empJoinDate = request.getParameter("empJoinDate"); // 입사일
      String empPicture = request.getParameter("empPicture"); // 사진
      String empFilePath = request.getParameter("empFilePath");  // 사진저장경로
      String empAuth = request.getParameter("empAuth"); // 사이트내 권한
      String empBirth = request.getParameter("empBirth"); // 생년월일
      String empSex = request.getParameter("empSex"); // 성별
      String empMarried = request.getParameter("empMarried"); // 결혼여부
      String empPhone = request.getParameter("empPhone"); // 전화번호
      String empEmail = request.getParameter("empEmail"); // 이메일
      String empFrontAddr = request.getParameter("empFrontAddr"); // 주소
      String empRearAddr = request.getParameter("empRearAddr"); // 나머지주소
      String empBank = request.getParameter("empBank"); // 은행
      String empAccName = request.getParameter("empAccName"); // 계좌명의
      String empAccount = request.getParameter("empAccount"); // 계좌번호
      
      EmployeeDAO eDao = EmployeeDAO.getInstance();
      
      // 학력정보
      ArrayList<Education> edVo = new ArrayList<Education>();
      
      String[] eduNum = request.getParameterValues("eduNum"); // 학력번호(순번)
      String[] eduSchool = request.getParameterValues("eduSchool"); // 학교명
      String[] eduMajor = request.getParameterValues("eduMajor"); // 전공
      String[] eduDeploma = request.getParameterValues("eduDeploma"); // 학위
      String[] schoolJoinDate = request.getParameterValues("schoolJoinDate"); // 입학일
      String[] schoolGraduatedDate = request.getParameterValues("schoolGraduatedDate"); // 졸업일
      
      if(eduNum != null) // eduNum이 null값이 아닐 때
      {
         for(int i = 0; i < eduNum.length; i++)
         {
            Education edu = new Education();
            // eduNum이 null값일 때, eDao에서 getNewEduNum으로 새로운 학력번호를 받아옴
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
      
      // 경력정보
      ArrayList<Career> cVo = new ArrayList<Career>();
      
      String[] careerNum = request.getParameterValues("careerNum"); // 경력번호(순번)
      String[] careerCompany = request.getParameterValues("careerCompany"); // 회사명 
      String[] companyJoinDate = request.getParameterValues("companyJoinDate"); // 입사일
      String[] companyDropDate = request.getParameterValues("companyDropDate"); // 퇴사일
      String[] careerPosition = request.getParameterValues("careerPosition"); // 직책
      String[] careerJob = request.getParameterValues("careerJob"); // 회사내 역할
      
      if(careerNum != null) // careerNum이 null값이 아닐 때
      {
      for(int i = 0; i < careerNum.length; i++) {
         Career c = new Career();
         // careerNum이 null값일 때, eDao에서 getNewCareerNum으로 새로운 경력번호를 받아옴
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
      
      // 기본정보
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
      
      // 학력정보
      request.setAttribute("Education", edVo);
      
      request.setAttribute("eduNum", eduNum);
      request.setAttribute("eduSchool", eduSchool);
      request.setAttribute("eduMajor", eduMajor);
      request.setAttribute("eduDeploma", eduDeploma);
      request.setAttribute("schoolJoinDate", schoolJoinDate);
      request.setAttribute("schoolGraduatedDate", schoolGraduatedDate);
      
      // 경력정보
      request.setAttribute("Career", cVo);
      
      request.setAttribute("careerNum", careerNum);
      request.setAttribute("careerCompany", careerCompany);
      request.setAttribute("companyJoinDate", companyJoinDate);
      request.setAttribute("companyDropDate", companyDropDate);
      request.setAttribute("careerPosition", careerPosition);
      request.setAttribute("careerJob", careerJob);
      
      // String으로 선언한 url로 이동
      RequestDispatcher dispatcher = request.getRequestDispatcher(url);
      dispatcher.forward(request, response);

   }

}
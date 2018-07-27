package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.data.table.Career;
import ccm.data.table.Education;
import ccm.data.table.Employee;
/**
 * 사원등록페이지에서 입력된 사원정보를 등록하는 액션
 * 커맨드값 : employee_Profile_insert
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmployeeProfileInsertAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub

	   	// 사진이 업로드되는 경로를 upload라는 폴더로 지정
	   	String savePath = request.getServletContext().getRealPath("/upload");
	   	// 업로드 가능 사진용량을 15MB로 지정
        int sizeLimit = 1024*1024*15;
        // 사진 업로드를 위해 MultipartRequest를 선언
        MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
        
        // 기존의 request.getParameter로 값을 받을 수 없어, multi.getParameter로 값을 받음
        // jsp페이지에서 form태그안에 enctype을 multipart/form-data으로 선언
        String empId = multi.getParameter("empId"); // 아이디
        String empPw = multi.getParameter("empPw"); // 비밀번호
        String empName = multi.getParameter("empName"); // 이름
        String empDept = multi.getParameter("empDept"); // 소속부서
        String empDuty = multi.getParameter("empDuty"); // 회사직책
        String empJoinDate = multi.getParameter("empJoinDate"); // 회사입사일
        String empPicture = multi.getFilesystemName("empPicture"); // 증명사진
        String empAuth = multi.getParameter("empAuth"); // 사이트내 권한(2.관리자(true)/1.일반사원(false))
        String empBirth = multi.getParameter("empBirth"); // 생년월일
        String empSex = multi.getParameter("empSex"); // 성별
        String empMarried = multi.getParameter("empMarried"); // 결혼여부
        String empPhone = multi.getParameter("empPhone"); // 전화번호
        String empEmail = multi.getParameter("empEmail"); // 이메일주소
        String empFrontAddr = multi.getParameter("empFrontAddr"); // 주소
        String empRearAddr = multi.getParameter("empRearAddr"); // 나머지주쇼(상세주소)
        String empBank = multi.getParameter("empBank"); // 은행명
        String empAccName = multi.getParameter("empAccName"); // 계좌명의
        String empAccount = multi.getParameter("empAccount"); // 계좌번호
        
        String empFilePath = savePath + "/" + empPicture; // 증명사진 저장경로
        
      Employee eVo = new Employee();
      
      eVo.setEmpId(empId);
      eVo.setEmpPw(empPw);
      eVo.setEmpName(empName);
      eVo.setEmpDept(empDept);
      eVo.setEmpDuty(empDuty);
      eVo.setEmpJoinDate(empJoinDate);
      eVo.setEmpPicture(empPicture);
      eVo.setEmpFilePath(empFilePath);
      eVo.setEmpAuth(Boolean.parseBoolean(empAuth)); // Boolean으로 값을 설정하고 관리자권한은 2.관리자(true)/1.일반사원(false)
      eVo.setEmpBirth(empBirth);
      eVo.setEmpSex(Boolean.parseBoolean(empSex)); // Boolean으로 값을 설정하고 성별은 1.남자(true)/0.여자(false)
      eVo.setEmpMarried(Boolean.parseBoolean(empMarried)); // Boolean으로 값을 설정하고 결혼여부는 1. 기혼(true)/0.미혼(false)
      eVo.setEmpPhone(empPhone);
      
      eVo.setEmpEmail(empEmail);
      eVo.setEmpFrontAddr(empFrontAddr);
      eVo.setEmpRearAddr(empRearAddr);
      eVo.setEmpBank(empBank);
      eVo.setEmpAccName(empAccName);
      eVo.setEmpAccount(empAccount);
      

      EmployeeDAO eDao = EmployeeDAO.getInstance();
      eDao.insertProfile(eVo);
      
      // 학력등록은 배열로 처리
      ArrayList<Education> edVo = new ArrayList<Education>();
      
      String[] eduNum = multi.getParameterValues("eduNum"); // 학력번호(순번)
      String[] eduSchool = multi.getParameterValues("eduSchool"); // 학교
      String[] eduMajor = multi.getParameterValues("eduMajor"); // 전공
      String[] eduDeploma = multi.getParameterValues("eduDeploma"); // 학위
      String[] schoolJoinDate = multi.getParameterValues("schoolJoinDate"); // 입학일
      String[] schoolGraduatedDate = multi.getParameterValues("schoolGraduatedDate"); // 졸업일
      

         for(int i = 0; i < eduNum.length; i++)
         {
            Education edu = new Education();
            // eduNum이 null이면 eDao에서 선언한 getNewEduNum 메소드에서 기존에 있는 학력번호 중 최대값에 1을 더한 번호를 학력번호로 입력하게 됨
            edu.setEduNum(eduNum[i] == null || eduNum[i].equals("") ? eDao.getNewEduNum() : Integer.parseInt(eduNum[i]));
            edu.setEduSchool(eduSchool[i]);
            edu.setEduMajor(eduMajor[i]);
            edu.setEduDeploma(eduDeploma[i]);
            edu.setSchoolJoinDate(schoolJoinDate[i]);
            edu.setSchoolGraduatedDate(schoolGraduatedDate[i]);
            edu.setEmpId(empId); // 앞에서 입력한 아이디값을 받아와서 그 아이디에 학력정보를 넣음
            
            edVo.add(edu);
         }
         
         eDao.updateEducation(edVo);
     
      // 경력등록은 배열로 처리
      ArrayList<Career> cVo = new ArrayList<Career>();
      
      String[] careerNum = multi.getParameterValues("careerNum"); // 경력번호(순번)
      String[] careerCompany = multi.getParameterValues("careerCompany"); // 회사이름
      String[] companyJoinDate = multi.getParameterValues("companyJoinDate"); // 입사일
      String[] companyDropDate = multi.getParameterValues("companyDropDate"); // 퇴사일
      String[] careerPosition = multi.getParameterValues("careerPosition"); // 직책
      String[] careerJob = multi.getParameterValues("careerJob"); // 역할(디자이너/개발자 등)
      

      for(int i = 0; i < careerNum.length; i++) {
         Career c = new Career();
         // careerNum이 null이면 eDao에서 선언한 getNewCareerNum 메소드에서 기존에 있는 경력번호 중 최대값에 1을 더한 번호를 경력번호로 입력하게 됨
         c.setCareerNum(careerNum[i] == null || careerNum[i].equals("") ? eDao.getNewCareerNum() : Integer.parseInt(careerNum[i]));
         c.setCareerCompany(careerCompany[i]);
         c.setCompanyJoinDate(companyJoinDate[i]);
         c.setCompanyDropDate(companyDropDate[i]);
         c.setCareerPosition(careerPosition[i]);
         c.setCareerJob(careerJob[i]);
         c.setEmpId(empId); // 앞에서 입력한 아이디값을 받아와서 그 아이디에 경력정보를 넣음
         
         cVo.add(c);      
         }
      
      eDao.updateCareer(cVo);
      
      
      // 값이 처리되고 다시 새로운 사원등록페이지로 이동
      new EmployeeProfileInsertFormAction().execute(request, response);
      

   }

}
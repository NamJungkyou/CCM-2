package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.data.table.Education;
/**
 * 사원등록페이지에서 입력된 학력정보를 등록하는 액션
 * 현재 사원게정등록은 employeeProfileInsert.jsp에서 학력, 경력 등을 따로 등록하는 형식이 아닌, 모든 정보를 전체등록하는 형식으로 처리
 * 지금과 같은 액션은 각각 개별로 등록하는 형식일때 사용(employeeMyProfile.jsp에서 사용)
 * 이 액션에 관한 설명은 EmployeeProfileInsertAction.java에 기술되어있으므로 참조하면 됨, 여기서는 생략.
 * 커맨드값 : employee_Education_update
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmployeeEducationUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Education> eVo = new ArrayList<Education>();
		
		String[] eduNums = request.getParameterValues("eduNum");
		/*for(String s : eduNums)System.out.println("eduNum이 제대로 들어가고 있는지 테스트"+s.toString());*/
		String[] eduSchools = request.getParameterValues("eduSchool");
		/*for(String s : eduSchools)System.out.println(s.toString());*/
		String[] eduMajors = request.getParameterValues("eduMajor");
		String[] eduDeplomas = request.getParameterValues("eduDeploma");
		String[] schoolJoinDates = request.getParameterValues("schoolJoinDate");
		String[] schoolGraduatedDates = request.getParameterValues("schoolGraduatedDate");
		String empId = request.getParameter("empId");
		
		/*System.out.println("empId가 제대로 들어가고 있는지 테스트" + empId);*/
		
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

	}

}

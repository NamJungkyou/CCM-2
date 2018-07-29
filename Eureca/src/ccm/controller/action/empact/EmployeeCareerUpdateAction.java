package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.data.table.Career;
/**
 * 사원등록페이지에서 입력된 경력정보를 등록하는 액션
 * 마찬가지로 현재 사원게정등록은 employeeProfileInsert.jsp에서 학력, 경력 등을 따로 등록하는 형식이 아닌, 모든 정보를 전체등록하는 형식으로 처리
 * 지금과 같은 액션은 각각 개별로 등록하는 형식일때 사용(employeeMyProfile.jsp에서 사용)
 * 이 액션에 관한 설명은 EmployeeProfileInsertAction.java에 기술되어있으므로 참조하면 됨, 여기서는 생략.
 * 커맨드값 : employee_Career_update
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
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
			System.out.println("커리어 업데이트가 제대로 이루어지는지 테스트: " + ed.getCareerNum()
			+ ed.getCareerCompany() + ed.getCompanyJoinDate() + ed.getCompanyDropDate()
			+ ed.getCareerPosition() + ed.getCareerJob());
		}
		
		System.out.println("이엠피 아이디가 제대로 들어가고 있는지 테스트" + empId);*/
		
		
		eDao.updateCareer(cVo);
		
		new EmployeeProfileAction().execute(request, response);

	}

}

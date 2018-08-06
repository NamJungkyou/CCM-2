package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Career;
import ccm.data.table.Education;
import ccm.data.table.Freelancer;
/**
 * 등록된 프리랜서 계정 수정 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmpFreelancerProfileUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/employee/empFreelancerProfileUpdate.jsp";
		String freeId = request.getParameter("freeId");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		// EmployeeDAO에서 등록된 프리랜서 계정을 불러오는 showFreelancerProfile 메소드 호출
		Freelancer fVo = eDao.showFreelancerProfile(freeId); 
		// FreelancerDAO에서 등록된 프리랜서 계좌정보를 불러오는 showAccount 메소드 호출
		Freelancer fVo1 = fDao.showAccount(freeId);
		// FreelancerDAO에서 등록된 프리랜서 학력정보를 불러오는 showEducation 메소드 호출
		ArrayList<Education> eVo = fDao.showEducation(freeId);	
		// FreelancerDAO에서 등록된 프리랜서 경력정보를 불러오는 showCareer 메소드 호출
		ArrayList<Career> cVo = fDao.showCareer(freeId);
		
		request.setAttribute("Freelancer", fVo);
		request.setAttribute("Freelancer2", fVo1);
		request.setAttribute("Education", eVo);
		request.setAttribute("Career", cVo);
		
		// String으로 선언된 url 페이지로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

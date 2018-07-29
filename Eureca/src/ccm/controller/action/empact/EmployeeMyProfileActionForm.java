package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.dao.EmployeeDAO;
import ccm.data.table.Career;
import ccm.data.table.Education;
import ccm.data.table.Employee;
/**
 * 로그인한 사원계정의 프로필 정보를 불러오는 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmployeeMyProfileActionForm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// employeeMyProfile.jsp 페이지로 이동
		String url = "/employee/employeeMyProfile.jsp";
		// 로그인한 사원의 정보를 불러옴
		Employee loginemp = (Employee) request.getSession().getAttribute("loginemp");

		EmployeeDAO eDao = EmployeeDAO.getInstance();
		CommonDAO cDao = CommonDAO.getInstance();
		
		// 로그인한 사원 기본정보 출력
		Employee eVo = eDao.showProfile(loginemp.getEmpId());	
		/*System.out.println(fVo.toString());
		fVo.setFreeBirth(fVo.getFreeBirth().substring(0, 10));*/
/*		Education eVo = fDao.showEducation(loginfree.getFreeId());*/
		
		// 로그인한 사원 학력정보 출력
		ArrayList<Education> eduVo = eDao.showEducation(loginemp.getEmpId());
		// 로그인한 사원 경력정보 출력
		ArrayList<Career> cVo = eDao.showCareer(loginemp.getEmpId());
		
//		ArrayList<SkillInventory> proVo = eDao.showSkillInventoryPro(loginemp.getEmpId());
			
		request.setAttribute("employee", eVo);
		
		request.setAttribute("Education", eduVo);
		request.setAttribute("Career", cVo);
		
		/*request.setAttribute("SkillInventory", proVo);*/
		request.setAttribute("langDbFrame", cDao.getLangDBFrame());

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}

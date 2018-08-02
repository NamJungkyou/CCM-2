package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;

/**
 * 로그인한 프리랜서 기본정보 수정폼을 호출하는 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class FreelancerUpdateProfileFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/freelancer/freelancerProfile.jsp";

		// 로그인한 프리랜서의 정보를 불러오기위해 로그인 정보값이 저장된 세션을 불러옴
		Freelancer loginfree = (Freelancer) request.getSession().getAttribute("loginfree");

		FreelancerDAO fDao = FreelancerDAO.getInstance(); // FreelancerDao를 인스턴스값으로 불러옴

		fDao.updateProfile(loginfree); // 로그인한 프리랜서의 기본정보를 수정하는 메소드

		loginfree = fDao.showProfile(loginfree.getFreeId()); // 로그인한 프리랜서의 기본정보를 출력

		request.getSession().setAttribute("loginfree", loginfree); // 로그인한 프리랜서의 세션값을 불러옴

		// String으로 저장된 url 페이지로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

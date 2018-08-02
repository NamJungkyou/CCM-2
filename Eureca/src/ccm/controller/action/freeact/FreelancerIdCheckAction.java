package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
/**
 * 프리랜서 등록이나 회원가입이 이루어질때, 아이디 중복체크가 이루어지는 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class FreelancerIdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String freeId = request.getParameter("freeId");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		int result = fDao.confirmID(freeId); // FreelancerDAO에서 아이디 중복체크하는 confirmID 메소드 실행
		
		request.setAttribute("freeId", freeId);
		request.setAttribute("result", result); // 중복체크가 된 freeId 값을 받아옴
		
		// 아이디 중복체크를 위해 idCheck.jsp로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher("freelancer/idCheck.jsp");
		dispatcher.forward(request, response);

	}

}

package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
/**
 * 회원가입을 할 때 아이디 중복체크 url로 이동하는 액션 
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class IdCheckFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/common/idCheck.jsp";
		
		request.setAttribute("freeid", request.getParameter("freeid"));
		
		// String으로 선언한 아이디 중복체크 url로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}

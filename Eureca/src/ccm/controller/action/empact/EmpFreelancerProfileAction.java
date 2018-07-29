package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
/**
 * 프리랜서등록 페이지로 이동하는 액션
 * 커맨드값 : freelancer_Profile_insert
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmpFreelancerProfileAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/employee/empFreelancerProfileInsert.jsp";
		
		// String으로 선언한 url페이지로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}

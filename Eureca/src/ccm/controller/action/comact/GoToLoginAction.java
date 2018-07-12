package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;

/***************************
 * 
 * 
 * 로그인 폼으로 이동하는 액션이다
 * login.jsp로 이동한다
 * 커맨드값 : login
 * 
 * 작성자 : 
 * 
 * 수정자 : 
 * 
 * 수정일 : 
 *
 *
 ***************************/

public class GoToLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//login.jsp ㄱㄱㄱ
		request.getRequestDispatcher("/common/login.jsp").forward(request, response);
	}
}

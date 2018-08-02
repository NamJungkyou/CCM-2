package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ccm.controller.action.Action;

/***************************
 * 
 * 
 * 로그아웃 과정을 처리하는 액션
 * 
 * 작성자 : 진재환
 * 
 * 수정자 : 진재환
 * 
 * 수정일 : 2018-05-07
 *
 *
 ***************************/

public class LogoutProcessAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//세션에 로그인되어있는 애들에 null을 줘서 로그아웃시킴
		HttpSession ses = request.getSession();
		if(ses.getAttribute("loginemp") != null) ses.setAttribute("loginemp", null);
		if(ses.getAttribute("loginfree") != null)ses.setAttribute("loginfree", null);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("<script>");
		response.getWriter().println("alert('로그아웃되었습니다');");
		response.getWriter().println("location.href='/Eureca/CommonServ?command=main'");
		response.getWriter().println("</script>");
	}
}

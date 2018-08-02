package ccm.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***************************
 * 
 * 
 * 악숀 인터페이스
 * 이걸로 각 기능의 액션을 만든다
 * 
 * 작성자 : 
 * 
 * 수정자 : 
 * 
 * 수정일 : 
 *
 *
 ***************************/

public interface Action
{
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}

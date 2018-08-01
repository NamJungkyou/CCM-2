package ccm.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***************************
 * 
 * 
 * 액션 인터페이스
 * 이걸로 각 기능의 액션을 확장하여 뷰셀렉터를 만든다
 * 
 * 작성자 : 글로벌 IT 경영 진재환
 *
 *
 ***************************/

public interface Action
{
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}

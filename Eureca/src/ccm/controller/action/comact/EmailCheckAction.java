package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
/**
 * 프리랜서가 회원가입을 할 때, 이메일 중복체크가 이루어지는 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmailCheckAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String freeemail = request.getParameter("freeemail");
		
		CommonDAO cDao = CommonDAO.getInstance();
		
		// CommonDAO에서 이메일 중복체크가 이루어지는 confirmEmail 메소드 실행
		int result = cDao.confirmEmail(freeemail);
		
		request.setAttribute("freeemail", freeemail);
		request.setAttribute("result", result); // 중복체크가 끝난 freeemail값을 result에 넣음
		
		// 이메일 중복체크가 이루어지는 jsp 페이지 호출
		RequestDispatcher dispatcher = request.getRequestDispatcher("common/emailCheck.jsp");
		dispatcher.forward(request, response);
	}
}

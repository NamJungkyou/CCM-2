package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
/**
 * (휴대폰번호 중복체크와 동일하게)
 * 사원계정을 등록할때 DB상에서 empEmail이 unique타입으로 설정되어있어 만일 중복갑싱 존재하면 오류발생
 * 입력값 테스트를 진행할때 편리하게 진행하기 위해 이메일 중복체크 기능을 만들긴 했으나 실제로 프리랜서관리시스템(페이지)에 넣을 필요는 없음
 * 사용자가 아닌 개발자(시스템 보수자)를 위한 기능
 * 기능에 대한 자세한 사항은 아이디 중복체크와 거의 동일하므로 아이디 중복체크를 참고바람
 * 커맨드값 : email_Check
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmployeeEmailCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String empEmail = request.getParameter("empEmail");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		int result = eDao.confirmEmail(empEmail);
		
		request.setAttribute("empEmail", empEmail);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee/emailCheck.jsp");
		dispatcher.forward(request, response);

	}

}

package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
/**
 * 사원 추가 정보 수정 페이지로 이동
 * 커맨드값 : employee_Profile_update_form
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmployeeProfileUpdateActionForm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/employee/employeeProfileUpdate.jsp";
		
		// 선언된 url페이지로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}

package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
/**
 * 사원등록페이지에서 사원을 등록할때 입력된 사원 아이디가 기존의 사원 아이디와 중복이 되는지 체크하는 액션
 * 커맨드값 : id_Check
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */

public class EmployeeIdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empId = request.getParameter("empId");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		int result = eDao.confirmID(empId);
		
		request.setAttribute("empId", empId);
		request.setAttribute("result", result);
		
		// 페이지에서 아이디 중복체크를 누르면 아이디 값을 idCheck.jsp에서 넘겨받고 받은 id값이 중복되는지 안되는지 결과 출력 
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee/idCheck.jsp");
		dispatcher.forward(request, response);
		
		

	}

}

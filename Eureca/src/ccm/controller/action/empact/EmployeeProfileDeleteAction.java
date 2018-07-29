package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
/**
 * 등록된 사원계정을 삭제하는 액션
 * 커맨드값 : employee_Profile_delete
 * 
 * @작성자 김민현
 *
 */
public class EmployeeProfileDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String empid = request.getParameter("empid");
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		// empid를 기준으로 사원계쩡을 삭제
		eDao.deleteEmployee(empid);
		
		new EmployeeProfileAction().execute(request, response); 

	}

}

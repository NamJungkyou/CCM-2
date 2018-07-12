package ccm.controller.action.comact;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Employee;
import ccm.data.table.Freelancer;

public class SelectReceiverAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "common/msg/selectReceiver.jsp";
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		// 현재 사용자의 회원번호를 세션으로부터 가져오는 과정
		Freelancer freeTempVo = (Freelancer) request.getSession().getAttribute("loginfree");
		Employee empTempVo = (Employee) request.getSession().getAttribute("loginemp");
						
		System.out.println(freeTempVo);
		System.out.println(empTempVo);
		
		if(freeTempVo == null) {

			List<Freelancer> freelancer = fDao.selectAllFree();
			
			request.setAttribute("freeList", freelancer);
			
		} else if (empTempVo == null) {
			
			List<Employee> employee = eDao.selectAllEmp();
			
			request.setAttribute("empList", employee);
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

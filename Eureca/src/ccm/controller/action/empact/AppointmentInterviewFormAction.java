package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.dao.EmployeeDAO;
import ccm.data.table.JoinProj;

public class AppointmentInterviewFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/employee/sendInterviewMsg.jsp";
		String chk = request.getParameter("chk");
		System.out.println("받아온 chk 변수 : " + chk);
		//
		String[] joinNum = chk.split("/");
		
		for(int i=0;i<joinNum.length;i++) {
			System.out.println("받은 JoinNum : " + joinNum[i]);
		}
		
		List<JoinProj> join = new ArrayList<JoinProj>();
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		CommonDAO cDao = CommonDAO.getInstance();

		// 받아온 joinNum을 통해 해당 참여신청 정보를 가져온다. 
		for(int i=0; i<joinNum.length; i++) {
			join.add(cDao.getJoinProjByNo(joinNum[i]));
			System.out.println("join 프로젝트 : " + join);
			eDao.updateInterviewStateByJoinNum(joinNum[i]);
		}
		
		request.setAttribute("joinProjList", join);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

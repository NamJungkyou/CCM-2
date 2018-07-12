package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;

public class IdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String freeid = request.getParameter("freeid");
		/*String empid = request.getParameter("freeid");*/
		/*System.out.println(freeid);*/
/*		System.out.println("freeid-----" + freeid);
		System.out.println("empid------" + empid);*/
		CommonDAO cDao = CommonDAO.getInstance();
		
		int result = cDao.confirmID(freeid);
		
		request.setAttribute("freeid", freeid);
		/*request.setAttribute("empid", empid);*/
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("common/idCheck.jsp");
		dispatcher.forward(request, response);

	}

}

package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;

public class EmailCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String freeemail = request.getParameter("freeemail");
		
		CommonDAO cDao = CommonDAO.getInstance();
		
		int result = cDao.confirmEmail(freeemail);
		
		request.setAttribute("freeemail", freeemail);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("common/emailCheck.jsp");
		dispatcher.forward(request, response);

		

	}

}

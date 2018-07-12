package ccm.srvlt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ccm.controller.actfac.FreelancerAF;
import ccm.controller.action.Action;

/**
 * Servlet implementation class FreelancerServ
 */
@WebServlet("/FreelancerServ")
public class FreelancerServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreelancerServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		
		String command = request.getParameter("command");
		System.out.println("FreelancerSer에서 요청을 받음을 확인 : "+command);
		
		System.out.println(command);
		
		FreelancerAF ff = (FreelancerAF) FreelancerAF.getInstance();
		Action action = ff.getAction(command);
		
		if(action != null)
		{
			action.execute(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}

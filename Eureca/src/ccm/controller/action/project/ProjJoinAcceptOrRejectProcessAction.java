package ccm.controller.action.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;

public class ProjJoinAcceptOrRejectProcessAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		String searchFreelancerName = request.getParameter("searchFreelancerName");
		String listSortOption = request.getParameter("listSortOption") == null ? "APPLICATIONDATE"
				: request.getParameter("listSortOption");
		String[] joinNumParam = request.getParameterValues("acceptedJoin");
		String acceptOrRejectParam = request.getParameter("acceptOrReject");
		boolean acceptOrReject = acceptOrRejectParam.equals("true") ? true : false;
		Integer[] joinNum = null;
		
		if(joinNumParam != null)
		{
			joinNum = new Integer[joinNumParam.length];
			for(int i = 0; i < joinNum.length; i++)
			{
				joinNum[i] = Integer.parseInt(joinNumParam[i]);
			}
			ProjectAdministrationDAO p = ProjectAdministrationDAO.getInstance();
			p.projJoinAcceptOrRejectFrees(joinNum, acceptOrReject);
		}
		
		PrintWriter out = response.getWriter();
		
		if(acceptOrReject)
		{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String alertScript = 
					"<script charset=\"utf-8\">"
					+ "alert(\"참여신청이 거절되었습니다\");"
					+ "location.href="
					+ "\"/Eureca/ProjectServ?command=projectjoinaccept"
					+ "&searchFreelancerName=" + searchFreelancerName
					+ "&listSortOption=" + listSortOption
					+ "\";"
					+ "</script>";
			
			alertScript = new String(alertScript.getBytes("8859_1"), "UTF-8");
			out.println(alertScript);
		}
		
		//request.setAttribute("searchFreelancerName", searchFreelancerName);
		//request.setAttribute("listSortOption", listSortOption);
		
		//new GoToProjectJoinAcceptAction().execute(request, response);
	}

}

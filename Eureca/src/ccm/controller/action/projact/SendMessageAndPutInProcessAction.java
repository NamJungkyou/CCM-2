package ccm.controller.action.projact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;
import ccm.data.FreeIdFreeNameStartEndDate;

public class SendMessageAndPutInProcessAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String empId = request.getParameter("empId");
		System.out.println("이엠피아이디" + empId);
		int projNum = Integer.parseInt(request.getParameter("curProjNum"));
		String projName = request.getParameter("curProjName");
		
		String[] freeIds = new String[Integer.parseInt(request.getParameter("length"))];
		String[] freeNames = new String[Integer.parseInt(request.getParameter("length"))];
		String[] startDates = new String[Integer.parseInt(request.getParameter("length"))];
		String[] endDates = new String[Integer.parseInt(request.getParameter("length"))];
		
		for(int i = 0; i < Integer.parseInt(request.getParameter("length")); i++)
		{
			freeIds[i] = request.getParameter("putInFreeIds[" + i + "]");
			freeNames[i] = request.getParameter("putInFreeNames[" + i + "]");
			startDates[i] = request.getParameter("putInStartDate[" + i + "]");
			endDates[i] = request.getParameter("putInExitDate[" + i + "]");
		}
		
		for(int i = 0; i < freeIds.length; i++)
		{
			System.out.println("freeIds : " + freeIds[i] + ", freeNames : " + freeNames[i]
					+ ", startDates : " + startDates[i] + ", endDates : " + endDates[i]);
		}
		
		String freeId = freeIds[Integer.parseInt(request.getParameter("index"))];
		String freeName = freeNames[Integer.parseInt(request.getParameter("index"))];
		String startDate = startDates[Integer.parseInt(request.getParameter("index"))];
		String exitDate = endDates[Integer.parseInt(request.getParameter("index"))];
		
		ProjectAdministrationDAO.getInstance().sendMessageAndPutIn(projNum,
				empId, freeId, freeName, startDate, exitDate);
		
		ArrayList<FreeIdFreeNameStartEndDate> freeList = new ArrayList<FreeIdFreeNameStartEndDate>(); 
		
		for(int i = 0; i < freeIds.length; i++)
		{
			FreeIdFreeNameStartEndDate f = new FreeIdFreeNameStartEndDate();
			f.setFreeId(freeIds[i]);
			f.setFreeName(freeNames[i]);
			f.setStartDate(startDates[i]);
			f.setEndDate(endDates[i]);
			freeList.add(f);
		}
		
		request.setAttribute("freeList", freeList);
		request.setAttribute("projNum", projNum);
		request.setAttribute("projName", projName);
		
		request.getRequestDispatcher("/project/putRequestOrDelete.jsp").forward(request, response);
		
		/*response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("<html><head></head><body>");
		response.getWriter().println("<script>");
		response.getWriter().println("alert(\"투입되었습니다\");");
		response.getWriter().println("</script>");
		response.getWriter().println("</body></html>");*/
	}
}

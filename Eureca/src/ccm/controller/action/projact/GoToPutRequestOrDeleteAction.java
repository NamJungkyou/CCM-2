package ccm.controller.action.projact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;
import ccm.data.FreeIdFreeNameStartEndDate;

public class GoToPutRequestOrDeleteAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String[] freeIds = request.getParameterValues("freeIds");
		String[] freeNames = request.getParameterValues("freeNames");
		String[] startDates = request.getParameterValues("putInStartDate");
		String[] endDates = request.getParameterValues("putInExitDate");
		
		for(String f : freeIds)
		{
			System.out.println(f + "프리아이디 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
		}
		
		if(startDates != null)
		{
			for(String s : startDates)
			{
				System.out.println(s);
			}
		}
		
		int projNum = Integer.parseInt(request.getParameter("projNum"));
		String projName = request.getParameter("projName");
		
		ArrayList<FreeIdFreeNameStartEndDate> freeList = null;
		
		if(startDates == null || endDates == null)
		{
			freeList = ProjectAdministrationDAO.getInstance().getFreeIdAndFreeName(freeIds);
		}
		else
		{
			freeList = new ArrayList<FreeIdFreeNameStartEndDate>();
			for(int i = 0; i < freeIds.length; i++)
			{
				FreeIdFreeNameStartEndDate f = new FreeIdFreeNameStartEndDate();
				f.setFreeId(freeIds[i]);
				f.setFreeName(freeNames[i]);
				f.setStartDate(startDates[i]);
				f.setEndDate(endDates[i]);
				freeList.add(f);
			}
		}
		
		request.setAttribute("freeList", freeList);
		request.setAttribute("projNum", projNum);
		request.setAttribute("projName", projName);
		
		request.getRequestDispatcher("/project/putRequestOrDelete.jsp").forward(request, response);
	}
}

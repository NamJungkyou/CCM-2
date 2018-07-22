package ccm.controller.action.project;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;
import ccm.data.FreeIdToPutInAndProjNum;

public class AddToPutInFreelancerListAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String recommlistSortOption1st = request.getParameter("recommListSortOption1st");
		String recommlistSortOption2st = request.getParameter("recommListSortOption2st");
		String recommlistSortOption3st = request.getParameter("recommListSortOption3st");
		String appliedFreelistSortOption = request.getParameter("appliedFreeListSortOption");
		String freelancerListToPutInSortOption = request.getParameter("freelancerListToPutInSortOption");
		String curProjNumStr = request.getParameter("curProjNum");
		Integer curProjNum = curProjNumStr == null ?
				ProjectAdministrationDAO.getInstance().getMaxProjNum() : Integer.parseInt(curProjNumStr);
		
		String[] freeId = request.getParameterValues("putInFreeId");
		
		@SuppressWarnings("unchecked")
		ArrayList<FreeIdToPutInAndProjNum> freeIdListToPutIn = 
				(ArrayList<FreeIdToPutInAndProjNum>)request.getSession().getAttribute("freelancerIdListToPutIn");
		System.out.println(freeIdListToPutIn == null ? "장바구니 널" : "장바구니 널아님");
	
		if(freeIdListToPutIn == null) freeIdListToPutIn = new ArrayList<FreeIdToPutInAndProjNum>();
		if(freeIdListToPutIn != null)
		{
			if(freeId != null)
			{
				for(int i = 0; i < freeId.length; i++)
				{
					boolean duplicated = false;
					for(FreeIdToPutInAndProjNum f : freeIdListToPutIn)
					{
						//System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ" + f.getFreeId());
						if(f.getProjNum() == curProjNum)
						{
							if(f.getFreeId().equals(freeId[i]))
							{
								//System.out.println(f.getFreeId()+"가중복됨");
								duplicated = true;
							}
						}
					}
					if(duplicated == false)
						freeIdListToPutIn.add(new FreeIdToPutInAndProjNum(freeId[i], curProjNum));
				}
			}
		}
		request.getSession().setAttribute("freelancerIdListToPutIn", freeIdListToPutIn);
		
		@SuppressWarnings("unchecked")
		ArrayList<FreeIdToPutInAndProjNum> asdf = 
				(ArrayList<FreeIdToPutInAndProjNum>)request.getSession().getAttribute("freelancerIdListToPutIn");
		
		request.setAttribute("recommlistSortOption1st", recommlistSortOption1st);
		request.setAttribute("recommlistSortOption2st", recommlistSortOption2st);
		request.setAttribute("recommlistSortOption3st", recommlistSortOption3st);
		request.setAttribute("appliedFreelistSortOption", appliedFreelistSortOption);
		request.setAttribute("freelancerListToPutInSortOption", freelancerListToPutInSortOption);
		
		request.setAttribute("curProjNum", curProjNum);
		
		new GoToPutFreelancerAction().execute(request, response);
	}
}

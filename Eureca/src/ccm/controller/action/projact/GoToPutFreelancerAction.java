package ccm.controller.action.projact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;
import ccm.data.AllOfEuclidProject;
import ccm.data.FreeIdToPutInAndProjNum;
import ccm.data.JoinAppliedFreelancer;
import ccm.data.ParamInt;
import ccm.data.ProjectRecruitState;
import ccm.data.RecommendedFreelancer;
import ccm.data.function.ProjUtil;

public class GoToPutFreelancerAction implements Action
{
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String curRecommPageNumStr = request.getParameter("curRecommPageNum");
		Integer curRecommPageNum = curRecommPageNumStr == null ? 1 : Integer.parseInt(curRecommPageNumStr);
		String curAppliedFreePageNumStr = request.getParameter("curAppliedFreePageNum");
		Integer curAppliedFreePageNum = curAppliedFreePageNumStr == null ?
				1 : Integer.parseInt(curAppliedFreePageNumStr);
		
		String recommlistSortOption1st = request.getParameter("recommListSortOption1st");
			recommlistSortOption1st = recommlistSortOption1st != null ? recommlistSortOption1st : "5";
		String recommlistSortOption2st = request.getParameter("recommListSortOption2st");
			recommlistSortOption2st = recommlistSortOption2st != null ? recommlistSortOption2st : "3";
		String recommlistSortOption3st = request.getParameter("recommListSortOption3st");
			recommlistSortOption3st = recommlistSortOption3st != null ? recommlistSortOption3st : "2";
			
		System.out.println(recommlistSortOption1st + ", " + recommlistSortOption2st + ", " + recommlistSortOption3st
				+ " ???????????$$$$$$$$$############@@@@@@@@@@@@@@@@@@@@ ");
			
		String recommSortOption[] =
			{
				ProjUtil.getInstance().codeToRecommSortOption(Integer.parseInt(recommlistSortOption1st)),
				ProjUtil.getInstance().codeToRecommSortOption(Integer.parseInt(recommlistSortOption2st)),
				ProjUtil.getInstance().codeToRecommSortOption(Integer.parseInt(recommlistSortOption3st))
			};
		
		String appliedFreeListSortOption = request.getParameter("appliedFreeListSortOption");
		System.out.println("쏠트옵션 !!!!!!!!!!!!!!!!!!!!!!!!!!! " + appliedFreeListSortOption);
		appliedFreeListSortOption = appliedFreeListSortOption != null ? appliedFreeListSortOption : "1";
		appliedFreeListSortOption =
				ProjUtil.getInstance().codeToAppliedSortOption(Integer.parseInt(appliedFreeListSortOption));
		System.out.println(appliedFreeListSortOption + " 옵션!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		String freelancerListToPutInSortOption = request.getParameter("freelancerListToPutInSortOption");
		
		String curProjNumStr = request.getParameter("curProjNum");
		Integer curProjNum = curProjNumStr == null ?
				ProjectAdministrationDAO.getInstance().getMaxProjNum() : Integer.parseInt(curProjNumStr);
		
		HttpSession session = request.getSession();
		ArrayList<FreeIdToPutInAndProjNum> freeIdListToPutIn = 
				(ArrayList<FreeIdToPutInAndProjNum>) session.getAttribute("freelancerIdListToPutIn");
		ArrayList<String> freeIdToPutIn = new ArrayList<String>();
		
		if(freeIdListToPutIn != null)
		{
			for(int i = 0; i < freeIdListToPutIn.size(); i++)
			{
				if(freeIdListToPutIn.get(i).getProjNum() == curProjNum)
				{
					System.out.println(freeIdListToPutIn.get(i).getProjNum());
					freeIdToPutIn.add(freeIdListToPutIn.get(i).getFreeId());
				}
			}
		}
		
		ProjectAdministrationDAO p = ProjectAdministrationDAO.getInstance(); 
		AllOfEuclidProject curProject = p.getProjectDetail(curProjNum);
		ArrayList<ProjectRecruitState> projRecruitStateInfo = p.getRecruitState(curProjNum);
		ParamInt recommNumOfRow = new ParamInt(0);
		RecommendedFreelancer[] recommFreelancer = p.getRecommendedFreelancerList(recommSortOption,
				curRecommPageNum, recommNumOfRow, curProjNum);
		ParamInt appliedNumOfRow = new ParamInt(0);
		JoinAppliedFreelancer[] appliedFreelancer = p.getAppliedFreelancer(curProjNum,
				curAppliedFreePageNum, appliedFreeListSortOption, appliedNumOfRow);
		ArrayList<JoinAppliedFreelancer> freelancerListToPutIn =
				freeIdToPutIn == null || freeIdToPutIn.size() == 0 ? null :
				p.getFreeListToPutIn(freeIdToPutIn, freelancerListToPutInSortOption);
		
		System.out.println("쓰쓰아웃 " + recommNumOfRow.getIntValue());
		int recommFreeNumOfPage = (int)(Math.ceil((double)(recommNumOfRow.getIntValue()) / 10));
		int recommFreeFirstPage = (int)((curRecommPageNum / 10) * 10) + 1;
		int recommFreeLastPage = (int)Math.min(recommFreeFirstPage + 9, recommFreeNumOfPage);
		System.out.println("넘오브페이지 : " + recommFreeNumOfPage
				+ ", 퍼스트페이지 : " + recommFreeFirstPage + ", 라스트페이지 : " + recommFreeLastPage);
		int appliedFreeNumOfPage = (int)(Math.ceil((double)(appliedNumOfRow.getIntValue()) / 10));
		int appliedFreeFirstPage = (int)((curAppliedFreePageNum / 10) * 10) + 1;
		int appliedFreeLastPage = (int)Math.min(appliedFreeFirstPage + 9, appliedFreeNumOfPage);
		System.out.println(appliedFreeNumOfPage + "넘오브페이지");
		
		System.out.println(ProjUtil.getInstance().recommSortOptionToCode(recommSortOption[0]) + "♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println(ProjUtil.getInstance().recommSortOptionToCode(recommSortOption[1]) + "♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println(ProjUtil.getInstance().recommSortOptionToCode(recommSortOption[2]) + "♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		
		
		request.setAttribute("recommListSortOption1st",
				ProjUtil.getInstance().recommSortOptionToCode(recommSortOption[0]));
		request.setAttribute("recommListSortOption2st",
				ProjUtil.getInstance().recommSortOptionToCode(recommSortOption[1]));
		request.setAttribute("recommListSortOption3st",
				ProjUtil.getInstance().recommSortOptionToCode(recommSortOption[2]));
		request.setAttribute("appliedFreeListSortOption",
				ProjUtil.getInstance().appliedSortOptionToCode(appliedFreeListSortOption));
		System.out.println("쏠트옵션 나가는거 !!!!!!!!!!!!!!???????????????????? " + appliedFreeListSortOption);
		request.setAttribute("freelancerListToPutInSortOption", freelancerListToPutInSortOption);
		
		request.setAttribute("projRecruitStateInfo", projRecruitStateInfo);
		request.setAttribute("curProject", curProject);
		request.setAttribute("curProjNum", curProjNum);
		request.setAttribute("recommFreelancer", recommFreelancer);
		request.setAttribute("appliedFreelancer", appliedFreelancer);
		request.setAttribute("freelancerListToPutIn", freelancerListToPutIn);
		
		request.setAttribute("curRecommPageNum", curRecommPageNum);
		request.setAttribute("curAppliedFreePageNum", curAppliedFreePageNum);
		request.setAttribute("recommFreeNumOfPage", recommFreeNumOfPage);
		request.setAttribute("recommFreeFirstPage", recommFreeFirstPage);
		request.setAttribute("recommFreeLastPage", recommFreeLastPage);
		request.setAttribute("appliedFreeNumOfPage", appliedFreeNumOfPage);
		request.setAttribute("appliedFreeFirstPage", appliedFreeFirstPage);
		request.setAttribute("appliedFreeLastPage", appliedFreeLastPage);
		
		request.getRequestDispatcher("/project/putfreelancer.jsp").forward(request, response);
	}
}

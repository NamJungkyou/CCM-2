package ccm.controller.action.project;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;
import ccm.data.FreeIdToPutInAndProjNum;

/**
 * 프로젝트 투입 대상자 목록에 프리랜서를 추가하는 액션
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class AddToPutInFreelancerListAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 추천프리랜서 첫번째 정렬옵션
		String recommlistSortOption1st = request.getParameter("recommListSortOption1st");
		// 추천프리랜서 두번째 정렬옵션
		String recommlistSortOption2st = request.getParameter("recommListSortOption2st");
		// 추천프리랜서 세번째 정렬옵션
		String recommlistSortOption3st = request.getParameter("recommListSortOption3st");
		
		// 참여신청한 프리랜서목록 정렬 옵션
		String appliedFreelistSortOption = request.getParameter("appliedFreeListSortOption");
		// 프리랜서 투입목록 정렬옵션
		String freelancerListToPutInSortOption = request.getParameter("freelancerListToPutInSortOption");
		
		// 프로젝트 번호
		String curProjNumStr = request.getParameter("curProjNum");
		Integer curProjNum = curProjNumStr == null ?
				ProjectAdministrationDAO.getInstance().getMaxProjNum() : Integer.parseInt(curProjNumStr);
		
		// 프리랜서 아이디 목록
		String[] freeId = request.getParameterValues("putInFreeId");
		
		// 프로젝트 투입대상자 목록을 세션에서 가져옴
		@SuppressWarnings("unchecked")
		ArrayList<FreeIdToPutInAndProjNum> freeIdListToPutIn = 
				(ArrayList<FreeIdToPutInAndProjNum>)request.getSession().getAttribute("freelancerIdListToPutIn");
	
		// 세션에 투입대상자 목록 객체가 널이면 새로 객체를 생성함
		if(freeIdListToPutIn == null) freeIdListToPutIn = new ArrayList<FreeIdToPutInAndProjNum>();
		if(freeIdListToPutIn != null)
		{
			if(freeId != null)
			{
				for(int i = 0; i < freeId.length; i++)
				{
					// 투입대상자 목록에서 중복이 일어나는 지 검사하는 변수
					boolean duplicated = false;
					for(FreeIdToPutInAndProjNum f : freeIdListToPutIn)
					{
						if(f.getProjNum() == curProjNum)
						{
							// 프리랜서가 목록에서 중복이 일어나면 duplicated를 true로 바꿔줌
							if(f.getFreeId().equals(freeId[i]))
							{
								duplicated = true;
							}
						}
					}
					// 중복이 아니면
					if(duplicated == false)
						// 투입대상자 목록에 추가함
						freeIdListToPutIn.add(new FreeIdToPutInAndProjNum(freeId[i], curProjNum));
				}
			}
		}
		// 세션에 최신화된 리스트를 넣어줌
		request.getSession().setAttribute("freelancerIdListToPutIn", freeIdListToPutIn);
		
		// 변수들을 모두 요청값으로 넘김
		request.setAttribute("recommlistSortOption1st", recommlistSortOption1st);
		request.setAttribute("recommlistSortOption2st", recommlistSortOption2st);
		request.setAttribute("recommlistSortOption3st", recommlistSortOption3st);
		request.setAttribute("appliedFreelistSortOption", appliedFreelistSortOption);
		request.setAttribute("freelancerListToPutInSortOption", freelancerListToPutInSortOption);
		
		request.setAttribute("curProjNum", curProjNum);
		
		// 프로젝트 투입 페이지로 다시 이동함
		new GoToPutFreelancerAction().execute(request, response);
	}
}

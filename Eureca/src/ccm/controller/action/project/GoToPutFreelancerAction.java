package ccm.controller.action.project;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;
import ccm.data.AllOfEuclidProject;
import ccm.data.FreeIdToPutInAndProjNum;
import ccm.data.JoinAppliedFreelancer;
import ccm.data.ParamInt;
import ccm.data.ProjectRecruitState;
import ccm.data.RecommendedFreelancer;
import ccm.data.function.ProjUtil;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 프리랜서 투입 페이지로 이동하는 액션
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */
public class GoToPutFreelancerAction implements Action {
	public GoToPutFreelancerAction() {
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 현재 추천프리랜서 목록 페이지번호
		// null이면 1로 맞춰줌
		String curRecommPageNumStr = request.getParameter("curRecommPageNum");
		Integer curRecommPageNum = Integer
				.valueOf(curRecommPageNumStr == null ? 1 : Integer.parseInt(curRecommPageNumStr));
		
		// 현재 참여신청한 프리랜서 페이지번호
		// null이면 1로 맞춰줌
		String curAppliedFreePageNumStr = request.getParameter("curAppliedFreePageNum");
		Integer curAppliedFreePageNum = Integer
				.valueOf(curAppliedFreePageNumStr == null ? 1 : Integer.parseInt(curAppliedFreePageNumStr));

		// 첫번째 추천프리랜서 정렬옵션을 받아옴
		String recommlistSortOption1st = request.getParameter("recommListSortOption1st");
		recommlistSortOption1st = recommlistSortOption1st != null ? recommlistSortOption1st : "5";
		// 두번째 추천프리랜서 정렬옵션을 받아옴
		String recommlistSortOption2st = request.getParameter("recommListSortOption2st");
		recommlistSortOption2st = recommlistSortOption2st != null ? recommlistSortOption2st : "3";
		// 세번째 추천프리랜서 정렬옵션을 받아옴
		String recommlistSortOption3st = request.getParameter("recommListSortOption3st");
		recommlistSortOption3st = recommlistSortOption3st != null ? recommlistSortOption3st : "2";
		
		// 정렬옵션들을 배열 객체로 만들어줌
		String[] recommSortOption = {
				ProjUtil.getInstance().codeToRecommSortOption(Integer.parseInt(recommlistSortOption1st)),
				ProjUtil.getInstance().codeToRecommSortOption(Integer.parseInt(recommlistSortOption2st)),
				ProjUtil.getInstance().codeToRecommSortOption(Integer.parseInt(recommlistSortOption3st)) };

		// 참여신청한 프리랜서 목록의 정렬옵션
		String appliedFreeListSortOption = request.getParameter("appliedFreeListSortOption");
		
		// null 이면 기본 1로 맞춰줌
		appliedFreeListSortOption = appliedFreeListSortOption != null ? appliedFreeListSortOption : "1";
		
		// 참여신청한 프리랜서 정렬옵션 코드를 SQL구문으로 바꿔줌
		appliedFreeListSortOption = ProjUtil.getInstance()
				.codeToAppliedSortOption(Integer.parseInt(appliedFreeListSortOption));
		
		// 투입대상자 목록의 정렬옵션을 받아옴
		String freelancerListToPutInSortOption = request.getParameter("freelancerListToPutInSortOption");

		// 현재 선택된 프로젝트번호
		String curProjNumStr = request.getParameter("curProjNum");
		Integer curProjNum = Integer
				.valueOf(curProjNumStr == null ? ProjectAdministrationDAO.getInstance().getMaxProjNum()
						: Integer.parseInt(curProjNumStr));

		// 세션에서 투입대상자 객체를 받아옴
		HttpSession session = request.getSession();
		ArrayList<FreeIdToPutInAndProjNum> freeIdListToPutIn = (ArrayList) session
				.getAttribute("freelancerIdListToPutIn");
		
		// 프리랜서 아이디만 투입대상자 목록에서 뽑아와 저장하는 리스트
		ArrayList<String> freeIdToPutIn = new ArrayList();

		if (freeIdListToPutIn != null) {
			for (int i = 0; i < freeIdListToPutIn.size(); i++) {
				if (((FreeIdToPutInAndProjNum) freeIdListToPutIn.get(i)).getProjNum() == curProjNum.intValue()) {
					// 투입대상자 리스트에서 프리랜서 ID만 뽑아서 프리랜서 아이디 리스트에 저장함 
					freeIdToPutIn.add(((FreeIdToPutInAndProjNum) freeIdListToPutIn.get(i)).getFreeId());
				}
			}
		}

		// DAO 인스턴스를 가져옴
		ProjectAdministrationDAO p = ProjectAdministrationDAO.getInstance();
		
		// 프로젝트 세부정보를 받아옴
		AllOfEuclidProject curProject = p.getProjectDetail(curProjNum.intValue());
		
		// 현재 채용현황을 받아옴
		ArrayList<ProjectRecruitState> projRecruitStateInfo = p.getRecruitState(curProjNum.intValue());
		ParamInt recommNumOfRow = new ParamInt(0);
		
		// 추천프리랜서 목록을 받아옴
		RecommendedFreelancer[] recommFreelancer = p.getRecommendedFreelancerList(recommSortOption,
				curRecommPageNum.intValue(), recommNumOfRow, curProjNum.intValue());
		ParamInt appliedNumOfRow = new ParamInt(0);
		
		// 참여신청한 프리랜서 목록
		JoinAppliedFreelancer[] appliedFreelancer = p.getAppliedFreelancer(curProjNum.intValue(),
				curAppliedFreePageNum.intValue(), appliedFreeListSortOption, appliedNumOfRow);
		
		// 투입대상자 목록 객체가 널이 아니거나 사이즈가 0이 아니면 프리랜서 투입 대상자 목록을 DB에서 가져옴 
		ArrayList<JoinAppliedFreelancer> freelancerListToPutIn = (freeIdToPutIn == null) || (freeIdToPutIn.size() == 0)
				? null
				: p.getFreeListToPutIn(freeIdToPutIn, freelancerListToPutInSortOption);

		// 추천프리랜서 전체 페이지 개수
		int recommFreeNumOfPage = (int) Math.ceil(recommNumOfRow.getIntValue() / 10.0D);
		
		// 추천프리랜서 첫페이지 번호
		int recommFreeFirstPage = curRecommPageNum.intValue() / 10 * 10 + 1;
		
		// 추천프리랜서 마지막페이지 번호
		int recommFreeLastPage = Math.min(recommFreeFirstPage + 9, recommFreeNumOfPage);
		
		// 참여신청한 프리랜서도 똑같이 계산해줌
		int appliedFreeNumOfPage = (int) Math.ceil(appliedNumOfRow.getIntValue() / 10.0D);
		int appliedFreeFirstPage = curAppliedFreePageNum.intValue() / 10 * 10 + 1;
		int appliedFreeLastPage = Math.min(appliedFreeFirstPage + 9, appliedFreeNumOfPage);
		
		
		// 요청에 모든 변수들을 넘겨줌
		request.setAttribute("recommListSortOption1st",
				// 추천프리랜서 목록 정렬옵션 SQL 구문을 다시 코드로 변환함
				Integer.valueOf(ProjUtil.getInstance().recommSortOptionToCode(recommSortOption[0])));
		request.setAttribute("recommListSortOption2st",
				Integer.valueOf(ProjUtil.getInstance().recommSortOptionToCode(recommSortOption[1])));
		request.setAttribute("recommListSortOption3st",
				Integer.valueOf(ProjUtil.getInstance().recommSortOptionToCode(recommSortOption[2])));
		request.setAttribute("appliedFreeListSortOption",
				ProjUtil.getInstance().appliedSortOptionToCode(appliedFreeListSortOption));
		request.setAttribute("freelancerListToPutInSortOption", freelancerListToPutInSortOption);

		request.setAttribute("projRecruitStateInfo", projRecruitStateInfo);
		request.setAttribute("curProject", curProject);
		request.setAttribute("curProjNum", curProjNum);
		request.setAttribute("recommFreelancer", recommFreelancer);
		request.setAttribute("appliedFreelancer", appliedFreelancer);
		request.setAttribute("freelancerListToPutIn", freelancerListToPutIn);

		request.setAttribute("curRecommPageNum", curRecommPageNum);
		request.setAttribute("curAppliedFreePageNum", curAppliedFreePageNum);
		request.setAttribute("recommFreeNumOfPage", Integer.valueOf(recommFreeNumOfPage));
		request.setAttribute("recommFreeFirstPage", Integer.valueOf(recommFreeFirstPage));
		request.setAttribute("recommFreeLastPage", Integer.valueOf(recommFreeLastPage));
		request.setAttribute("appliedFreeNumOfPage", Integer.valueOf(appliedFreeNumOfPage));
		request.setAttribute("appliedFreeFirstPage", Integer.valueOf(appliedFreeFirstPage));
		request.setAttribute("appliedFreeLastPage", Integer.valueOf(appliedFreeLastPage));

		request.getRequestDispatcher("/project/putfreelancer.jsp").forward(request, response);
	}
}

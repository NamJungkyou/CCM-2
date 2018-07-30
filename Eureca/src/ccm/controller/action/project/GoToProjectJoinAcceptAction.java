package ccm.controller.action.project;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;
import ccm.data.ParamInt;
import ccm.data.function.ProjUtil;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 프로젝트 참여신청 접수 페이지로 이동하는 액션
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class GoToProjectJoinAcceptAction implements Action {
	public GoToProjectJoinAcceptAction() {
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 설정
		request.setCharacterEncoding("UTF-8");
		
		// 프리랜서 이름 검색옵션을 받아옴
		String searchFreelancerName = request.getParameter("searchFreelancerName");
		
		if (searchFreelancerName != null)
			// UTF-8 인코딩으로 다시 설정해줌
			searchFreelancerName = new String(searchFreelancerName.getBytes("8859_1"), "UTF-8");
		
		searchFreelancerName = searchFreelancerName == null ? "" : searchFreelancerName;
		
		// 현재 페이지 번호
		String pageNumParam = request.getParameter("projJoinedFreePageNum");
		
		// 페이지 번호가 null 이면 기본값 1로 맞춰줌
		Integer pageNum = Integer.valueOf(pageNumParam != null ? Integer.parseInt(pageNumParam) : 1);
		
		// 참여신청자 목록 정렬옵션이 null이어도 기본값 1로 맞춰줌
		String listSortOptionParam = request.getParameter("listSortOption") == null ? "1"
				: request.getParameter("listSortOption");
		
		
		int listSortOption = Integer.parseInt(listSortOptionParam);
		
		// 정렬옵션을 코드에서 쿼리구문으로 변경해줌
		String listSortOptionString = ProjUtil.getInstance().projJoinAcceptListCodeToOption(listSortOption);
		
		// 페이지번호를 저장하는 객체변수
		ParamInt numOfList = new ParamInt(0);
		
		// DAO 인스턴스를 받아옴
		ProjectAdministrationDAO pDao = ProjectAdministrationDAO.getInstance();

		// 투입대상자 목록을 null로 초기화하고
		ccm.data.ProjJoinedFreelancerForList[] list = null;
		
		// DB에서 목록을 받아옴
		list = pDao.getJoinedFreelancerList(listSortOptionString, numOfList, pageNum.intValue(),
				new String(searchFreelancerName));

		// 현재 모든 페이지의 개수를 계산함
		int intNumOfPage = (int) Math.ceil(numOfList.getIntValue() / 10.0D);

		// 첫페이지
		int firstPage = Math.round(pageNum.intValue() / 10.0F) * 10 + 1;
		
		// 마지막페이지
		int lastPage = Math.min(firstPage + 9, intNumOfPage);
		
		// 요청값에 모든 변수를 넘겨줌
		request.setAttribute("searchFreelancerName", searchFreelancerName);
		request.setAttribute("numOfPage", Integer.valueOf(intNumOfPage));
		request.setAttribute("projJoinedFreePageNum", pageNum);
		request.setAttribute("projJoinedFreeFirstPage", Integer.valueOf(firstPage));
		request.setAttribute("projJoinedFreeLastPage", Integer.valueOf(lastPage));
		request.setAttribute("projJoinedFreeList", list);
		request.setAttribute("listSortOption", Integer.valueOf(listSortOption));
		
		// 페이지로 이동
		request.getRequestDispatcher("/project/projectjoinaccept.jsp").forward(request, response);
	}
}

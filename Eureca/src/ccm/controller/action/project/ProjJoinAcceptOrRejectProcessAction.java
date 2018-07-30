package ccm.controller.action.project;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 프로젝트 참여신청을 접수하거나 거절하는 과정을 처리하는 액션
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */
public class ProjJoinAcceptOrRejectProcessAction implements Action {
	public ProjJoinAcceptOrRejectProcessAction() {
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 검색하는 프리랜서 아이디를 요청에서 받아옴
		String searchFreelancerName = request.getParameter("searchFreelancerName");
		
		// 다시 한글로 재처리해줌
		searchFreelancerName = new String(searchFreelancerName.getBytes("8859_1"), "UTF-8");
		
		// 리스트 정렬옵션
		String listSortOption = request.getParameter("listSortOption") == null ? "APPLICATIONDATE"
				: request.getParameter("listSortOption");
		
		// 참여번호들을 받아옴
		String[] joinNumParam = request.getParameterValues("acceptedJoin");
		
		// 참여신청 접수 또는 거절을 할 건지 판단하는 변수
		String acceptOrRejectParam = request.getParameter("acceptOrReject");		
		boolean acceptOrReject = acceptOrRejectParam.equals("true");
		Integer[] joinNum = null;

		// 참여번호를 Integer 자료형으로 바꿔줌
		if (joinNumParam != null) {
			joinNum = new Integer[joinNumParam.length];
			for (int i = 0; i < joinNum.length; i++) {
				joinNum[i] = Integer.valueOf(Integer.parseInt(joinNumParam[i]));
			}
			ProjectAdministrationDAO p = ProjectAdministrationDAO.getInstance();
			p.projJoinAcceptOrRejectFrees(joinNum, acceptOrReject);
		}

		PrintWriter out = response.getWriter();
		
		
		if (acceptOrReject) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String alertScript = "<script charset=\"utf-8\">alert(\"참여신청이 거절되었습니다\");location.href=\"ProjectServ?command=projectjoinaccept&searchFreelancerName="
					+

					searchFreelancerName + "&listSortOption=" + listSortOption + "\";" + "</script>";

			alertScript = new String(alertScript.getBytes("8859_1"), "UTF-8");
			out.println(alertScript);
		}
	}
}

package ccm.controller.action.project;

import ccm.controller.action.Action;
import ccm.dao.ProjectAdministrationDAO;
import ccm.data.FreeIdFreeNameStartEndDate;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 프로젝트 투입 요청메시지를 보내거나
 * 강제투입하는 페이지로 이동하는 액션
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */
public class GoToPutRequestOrDeleteAction implements Action {
	public GoToPutRequestOrDeleteAction() {
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		// 프리랜서 아이디 리스트
		String[] freeIds = request.getParameterValues("freeIds");
		
		// 프리랜서 이름 리스트
		String[] freeNames = request.getParameterValues("freeNames");
		
		// 참여시작일
		String[] startDates = request.getParameterValues("putInStartDate");
		
		// 철수일
		String[] endDates = request.getParameterValues("putInExitDate");

		
		// 프로젝트 번호를 받아옴
		int projNum = Integer.parseInt(request.getParameter("projNum"));
		
		// 프로젝트 이름을 받아옴
		String projName = request.getParameter("projName");

		// 프리랜서 리스트 객체변수를 만들어주고
		ArrayList freeList = null;

		// DAO에서 투입대상자 리스트를 가져옴
		if ((startDates == null) || (endDates == null)) {
			freeList = ProjectAdministrationDAO.getInstance().getFreeIdAndFreeName(freeIds);
		} else {
			// 
			freeList = new ArrayList();
			for (int i = 0; i < freeIds.length; i++) {
				FreeIdFreeNameStartEndDate f = new FreeIdFreeNameStartEndDate();
				f.setFreeId(freeIds[i]);
				f.setFreeName(freeNames[i]);
				f.setStartDate(startDates[i]);
				f.setEndDate(endDates[i]);
				((ArrayList) freeList).add(f);
			}
		}

		// UI로 데이터들을 모두 넘겨줌
		request.setAttribute("freeList", freeList);
		request.setAttribute("projNum", Integer.valueOf(projNum));
		request.setAttribute("projName", projName);

		// UI로 이동
		request.getRequestDispatcher("/project/putRequestOrDelete.jsp").forward(request, response);
	}
}

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
 * 프리랜서를 프로젝트 참여 상태로 바꾸고
 * 투입되었다는 메시지를 보내는 처리를 하는 액션
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class SendMessageForPutInProcessAction implements Action {
	public SendMessageForPutInProcessAction() {
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		// 로그인한 직원의 아이디를 받음 (메시지 작성자)
		String empId = request.getParameter("empId");
		
		// 현재 프로젝트 번호를 받아옴
		int projNum = Integer.parseInt(request.getParameter("curProjNum"));
		String projName = request.getParameter("curProjName");

		// 프리랜서 아이디, 이름, 투입일, 철수일을 받아옴
		String[] freeIds = new String[Integer.parseInt(request.getParameter("length"))];
		String[] freeNames = new String[Integer.parseInt(request.getParameter("length"))];
		String[] startDates = new String[Integer.parseInt(request.getParameter("length"))];
		String[] endDates = new String[Integer.parseInt(request.getParameter("length"))];
		for (int i = 0; i < Integer.parseInt(request.getParameter("length")); i++) {
			freeIds[i] = request.getParameter("putInFreeIds[" + i + "]");
			freeNames[i] = request.getParameter("putInFreeNames[" + i + "]");
			startDates[i] = request.getParameter("putInStartDate[" + i + "]");
			endDates[i] = request.getParameter("putInExitDate[" + i + "]");
		}

		// 인덱스들을 받아옴
		String freeId = freeIds[Integer.parseInt(request.getParameter("index"))];
		String freeName = freeNames[Integer.parseInt(request.getParameter("index"))];
		String startDate = startDates[Integer.parseInt(request.getParameter("index"))];
		String exitDate = endDates[Integer.parseInt(request.getParameter("index"))];

		// 투입상태로 바꾸고 메시지를 보내는 처리를 함
		ProjectAdministrationDAO.getInstance().sendMessageForPutInRequest(projNum, empId, freeId, freeName, startDate,
				exitDate);

		// 받아온 프리랜서 리스트를 리스트로 저장하기 위해 리스트 객체를 생성함
		ArrayList<FreeIdFreeNameStartEndDate> freeList = new ArrayList();

		// 리스트에 이름, 아이디, 투입일, 철수일을 저장하고
		for (int i = 0; i < freeIds.length; i++) {
			FreeIdFreeNameStartEndDate f = new FreeIdFreeNameStartEndDate();
			f.setFreeId(freeIds[i]);
			f.setFreeName(freeNames[i]);
			f.setStartDate(startDates[i]);
			f.setEndDate(endDates[i]);
			freeList.add(f);
		}

		// UI로 다시 보냄
		request.setAttribute("freeList", freeList);
		request.setAttribute("projNum", Integer.valueOf(projNum));
		request.setAttribute("projName", projName);
		request.setAttribute("msg", 1);

		request.getRequestDispatcher("/project/putRequestOrDelete.jsp").forward(request, response);
	}
}

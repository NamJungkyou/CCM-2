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
 * 투입알림 메시지를 보내고 투입상태로 바꾸는 처리를 하는 액션
 * 
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class SendMessageAndPutInProcessAction implements Action {
	public SendMessageAndPutInProcessAction() {
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		// 로그인한 직원 아이디를 받아옴
		String empId = request.getParameter("empId");
		
		// 현재 프로젝트 번호를 받아옴
		int projNum = Integer.parseInt(request.getParameter("curProjNum"));
		
		// 현재 프로젝트 이름을 받아옴
		String projName = request.getParameter("curProjName");

		// 투입대상자 프리랜서 아이디 목록
		String[] freeIds = new String[Integer.parseInt(request.getParameter("length"))];
		// 프리랜서 이름 목록
		String[] freeNames = new String[Integer.parseInt(request.getParameter("length"))];
		// 참여 시작일 목록
		String[] startDates = new String[Integer.parseInt(request.getParameter("length"))];
		// 철수일 목록
		String[] endDates = new String[Integer.parseInt(request.getParameter("length"))];

		
		// 프리랜서 투입을 철수일 아이디 이름을 받아와서 배열들을 초기화함
		for (int i = 0; i < Integer.parseInt(request.getParameter("length")); i++) {
			freeIds[i] = request.getParameter("putInFreeIds[" + i + "]");
			freeNames[i] = request.getParameter("putInFreeNames[" + i + "]");
			startDates[i] = request.getParameter("putInStartDate[" + i + "]");
			endDates[i] = request.getParameter("putInExitDate[" + i + "]");
		}

		// 진짜로 투입할 대상자의 아이디, 이름, 투입일, 철수일을 받음
		String freeId = freeIds[Integer.parseInt(request.getParameter("index"))];
		String freeName = freeNames[Integer.parseInt(request.getParameter("index"))];
		String startDate = startDates[Integer.parseInt(request.getParameter("index"))];
		String exitDate = endDates[Integer.parseInt(request.getParameter("index"))];

		// 메시지를 보내고 참여상태로 바꿈
		ProjectAdministrationDAO.getInstance().sendMessageAndPutIn(projNum, empId, freeId, freeName, startDate,
				exitDate);

		// 아이디 등의 정보를 하나의 객체로 목록화하는 변수를 생성함
		ArrayList<FreeIdFreeNameStartEndDate> freeList = new ArrayList();

		// 목록에 객체와 같이 담음
		for (int i = 0; i < freeIds.length; i++) {
			FreeIdFreeNameStartEndDate f = new FreeIdFreeNameStartEndDate();
			f.setFreeId(freeIds[i]);
			f.setFreeName(freeNames[i]);
			f.setStartDate(startDates[i]);
			f.setEndDate(endDates[i]);
			freeList.add(f);
		}

		// 다시 투입알림 메시지 또는 투입 UI로 데이터들을 보내고 이동함
		request.setAttribute("freeList", freeList);
		request.setAttribute("projNum", Integer.valueOf(projNum));
		request.setAttribute("projName", projName);

		request.getRequestDispatcher("/project/putRequestOrDelete.jsp").forward(request, response);
	}
}

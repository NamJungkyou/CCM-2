package ccm.controller.action.empact;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.data.table.JoinFreelancerInterview_view;
import ccm.data.table.JoinFreelancerSkillInventory_view;

public class ResultInterviewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "employee/interviewSchedule.jsp";
		
		// 각 이름으로 받아오는 파라메터 값을 String형 배열에 적용한다.
		String[] interviewReason = request.getParameterValues("interviewReason");
		String[] joinNum = request.getParameterValues("joinNum");
		String[] freeState = request.getParameterValues("freeState");
		String[] interviewNum = request.getParameterValues("interviewNum");
		
		for(int i=0; i< interviewNum.length; i++) {
			System.out.println("interviewReason[" + i + "] : " + interviewReason[i]);
			System.out.println("interviewNum[" + i + "] : " + interviewNum[i]);
			System.out.println("joinNum[" + i + "]: " + joinNum[i]);
			System.out.println("freeState[" + i + "] : " + freeState[i]);
		}
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		for(int i=0; i < interviewNum.length; i++) {
			if(freeState[i] == "" || freeState[i] == null) {
				continue;
			}
			if(interviewReason[i] == "" || interviewReason[i] == null) {
				continue;
			}
			
			eDao.updateInterviewReasonByinterviewNo(interviewNum[i], interviewReason[i]);
			
			switch(freeState[i]) {
			case "sucess":
				eDao.updateFreeStateByJoinNum(joinNum[i], "참여중");
				break;
			case "hold":
				eDao.updateFreeStateByJoinNum(joinNum[i], "보류");
				break;
			case "fail":
				eDao.updateFreeStateByJoinNum(joinNum[i], "불채용");
				break;
			}
		}
		
		// 이 아래의 작업은 면접일정/결과등록 목록을 보여주기 위한 작업입니다.
		List<JoinFreelancerSkillInventory_view> joinFreelancerSkillInventory_view = null;
		List<JoinFreelancerInterview_view> joinFreeInterview = null;
		
		// 참여신청을 한 프리랜서 신청목록 스킬인벤토리 리스트를 가져오는 메소드
		joinFreelancerSkillInventory_view = eDao.selectAllJoinFreeSkillInventory();
		System.out.println("참여신청한 프리랜서 스킬인벤토리 : " + joinFreelancerSkillInventory_view);

		// 참여신청한 프리랜서 인터뷰 리스트를 가져오는 메소드
		joinFreeInterview = eDao.selectJoinFreeInterview();
		System.out.println("참여신청한 프리랜서 인터뷰 : " + joinFreeInterview);
		
		
		// 저장된 객체를 화면에서 볼 수 있게 데이터를 보내주는 메소드
		request.setAttribute("joinFreeInterviewList", joinFreeInterview);
		request.setAttribute("joinFreeList", joinFreelancerSkillInventory_view);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

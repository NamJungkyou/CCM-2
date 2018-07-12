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

public class InterviewScheduleAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "employee/interviewSchedule.jsp";
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
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

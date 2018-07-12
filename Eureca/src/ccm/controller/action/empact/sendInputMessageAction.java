package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.dao.EmployeeDAO;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;
import ccm.data.table.Message;

public class sendInputMessageAction implements Action{

	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "Eu?e=input_free";		// 임시지정
		
		String empId = request.getParameter("emp");
		String projNum = request.getParameter("projNum");
		String projName = request.getParameter("projName");
		String freeId = request.getParameter("free");
		String joinDate = request.getParameter("join");
		String dropDate = request.getParameter("drop");
		String joinNum = request.getParameter("joinNm");
		String[] joinN = joinNum.split("/");
		String[] join = joinDate.split("/");
		String[] drop = dropDate.split("/");
		String[] free = freeId.split("/");
		
		Freelancer fVo = null;
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		CommonDAO cDao = CommonDAO.getInstance();
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		for(int i=0; i < free.length; i++) {
			
			fVo=fDao.getfVo(free[i]);
			
			Message mVo = new Message();
			
			mVo.setEmpWriter(empId);
			mVo.setFreeReceiver(free[i]);
			mVo.setMsgTitle("프로젝트 " + projName + "에 대한 투입요청 메시지입니다.");
			mVo.setMsgContent(fVo.getFreeName() + "님 " + projName + "에 참여확정 되었습니다. \n" + 
							join + "일 부터" + drop + "일 까지 일 하실 예정입니다. \n"
							+ "자세한 사항은 프로젝트 시작 전후로 설명드리겠습니다. ");
			mVo.setProjNum(projNum);
			cDao.insertMsg(mVo);
			
			eDao.updateFreeStateByJoinNum(joinN[i], "참여중");
			// 지금은 이정도
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}

package ccm.controller.action.comact;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.dao.EmployeeDAO;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Employee;
import ccm.data.table.Freelancer;
import ccm.data.table.Message;
import ccm.data.table.Project;

public class ShowSendMessageAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// showMsg/메시지 확인 페이지로 이동하는 액션
		String url = "common/msg/showSendMsg.jsp";
		
		// 보내진 no를 msgNo 변수에 적용한다.
		String msgNo = request.getParameter("no");
		String proj = request.getParameter("projNo");
		
		// 메세지 객체 생성
		Message message = null;
		
		// 프로젝트 객체 생성
		Project project = null;
		
		CommonDAO cDao = CommonDAO.getInstance();
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		// 메시지 번호를 통해 해당 메시지를 가져온다.
		message =  cDao.selectOneMsgByNo(msgNo);
		System.out.println("가져온 메시지 : " + message);
		
		// 프로젝트 번호를 통해 프로젝트를 찾아온다.
		project = cDao.selectProjectByNo(Integer.parseInt(proj));
		System.out.println("가져온 프로젝트 : " + project);
		
		// 현재 사용자의 회원번호를 세션으로부터 가져오는 과정
		Freelancer freeTempVo = (Freelancer) request.getSession().getAttribute("loginfree");
		Employee empTempVo = (Employee) request.getSession().getAttribute("loginemp");
				
		
		System.out.println("세션을 통해 가져온 프리랜서 정보 : " + freeTempVo);
		System.out.println("세션을 통해 가져온 사원 정보 : " + empTempVo);
		
		// 메시지 리스트를 생성한다
		List<Message> messageList = null;
		
		if( freeTempVo != null ) {
			// 세션을 통해 가져온 사용자의 아이디가 프리랜서일 때 해당 프리랜서가 받은 메시지 목록을 받아온다.
			messageList = cDao.selectAllMsgFree(freeTempVo.getFreeId());
			
			System.out.println("프리랜서 아이디를 통해 가져온 프리랜서가 받은 이전 메시지 : " + messageList);
		} else if( empTempVo != null ) {
			// 세션을 통해 가져온 사용자의 아이디가 사원(관리자)일 때 해당 사원(관리자)이 받은 메시지 목록을 받아온다.
			messageList = cDao.selectAllMsgEmp(empTempVo.getEmpId());
			
			System.out.println("사원 아이디를 통해 가져온 프리랜서가 받은 이전 메시지 : " + messageList);
		}

		System.out.println("메시지 : " + message);

		// 메시지의 작성자 아이디를 통해 작성자의 정보를 가져온다.
		if(empTempVo != null && msgNo != null) {
			// 메시지의 프리랜서 발신자를 통해 가져온 프리랜서 정보
			Freelancer free = fDao.getfVo(message.getFreeReceiver());
			
			System.out.println("작성자 프리랜서 : " + free);
			request.setAttribute("free", free);
		} else if (freeTempVo != null && msgNo != null) {
			// 메시지의 사원(관리자) 발신자를 통해 가져온 프리랜서 정보
			Employee emp = eDao.geteVo(message.getEmpReceiver());
			System.out.println("작성자 사원 : " + emp);
			request.setAttribute("emp", emp);
		}
		
		// 각 객체를 ""안의 이름으로 값들을 showSendMessage 페이지로 전달한다.
		request.setAttribute("messageList", messageList);
		request.setAttribute("message", message);
		request.setAttribute("project", project);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

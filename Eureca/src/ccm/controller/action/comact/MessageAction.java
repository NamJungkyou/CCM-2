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

public class MessageAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "common/msg/showMsg.jsp";
		
		String prevMsgNum = request.getParameter("prevMsgNum");
		String freeWriter = request.getParameter("freeWriter");
		String empWriter = request.getParameter("empWriter");
		String freeReceiver = request.getParameter("freeReceiver");
		String empReceiver = request.getParameter("empReceiver");
		String projNum = request.getParameter("projNum");
		String title = request.getParameter("msgTitle");
		String content = request.getParameter("msgContent");
		
		System.out.println("메시지 제목 : " + title);
		System.out.println("메시지 내용 : " + content);
		System.out.println("답장하는 이전 메시지 번호 : " + prevMsgNum);
		System.out.println("프리랜서 수신인 : " + freeReceiver);
		System.out.println("사원 수신인 : " + empReceiver);
		
		Message msgVo = new Message();
		
		CommonDAO cDao = CommonDAO.getInstance();
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		Freelancer free = fDao.getfVo(freeReceiver);
		Employee emp = eDao.geteVo(empReceiver);
		
		System.out.println("프리랜서 수신인 : " + free);
		System.out.println("사원 수신인 : " + emp);
		
		if(freeReceiver != null) {
			msgVo.setFreeReceiver(freeReceiver);
			System.out.println(freeReceiver);
		} 
		if (empReceiver != null) {
			msgVo.setEmpReceiver(empReceiver);
			System.out.println(empReceiver);
		}

		// 각각의 값을 지정함
		msgVo.setPrevMsgNum(prevMsgNum);
		msgVo.setFreeWriter(freeWriter);
		msgVo.setEmpWriter(empWriter);
		msgVo.setMsgTitle(title);
		msgVo.setMsgContent(content);
		msgVo.setProjNum(projNum);
		
		System.out.println("보낼 메시지 : " + msgVo);
		
		// DB에 삽입함/ 메시지를 보냄
		cDao.insertMsg(msgVo);

		// 현재 사용자의 회원번호를 세션으로부터 가져온다.
		Freelancer freeTempVo = (Freelancer) request.getSession().getAttribute("loginfree");
		Employee empTempVo = (Employee) request.getSession().getAttribute("loginemp");
		
		// 이전 메시지 목록을 불러올 리스트<메시지> 생성한다.
		List<Message> messageList = null;
		
		if( freeTempVo != null ) {
			// 프리랜서 아이디를 통해 프리랜서가 받은 이전 메시지를 가져온다.
			messageList = cDao.selectAllMsgFree(freeTempVo.getFreeId());
			
			System.out.println("프리랜서 정보를 통해 가져온 이전 메시지 목록 : " + messageList);
			
			request.setAttribute("freeTemp", freeTempVo);
		} else if( empTempVo != null ) {
			// 사원 아이디를 통해 프리랜서가 받은 이전 메시지를 가져온다.
			messageList = cDao.selectAllMsgEmp(empTempVo.getEmpId());
			
			System.out.println("사원정보를 통해 가져온 이전 메시지 목록 : " + messageList);
			
			request.setAttribute("empTemp", empTempVo);
		}
		
		request.setAttribute("messageList", messageList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

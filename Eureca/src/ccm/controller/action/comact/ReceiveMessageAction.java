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

public class ReceiveMessageAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "common/msg/showMsg.jsp";
		// 보내진 no를 msgNo 변수에 적용한다.
		String msgNo = request.getParameter("no");
		String msgProject = request.getParameter("projNum");
		String msgTitle = request.getParameter("msgTitle");
		String msgContent = request.getParameter("msgContent");
		String msgSendDate = request.getParameter("msgSendDate");
		
		// 보내진 작성자를 수신인으로 저장
		String freeReceiver = request.getParameter("freeWriter");
		String empReceiver = request.getParameter("empWriter");
		
		System.out.println("메시지 번호 : " + msgNo);
		System.out.println("보낸이(프리랜서 아이디) : " + freeReceiver);
		System.out.println("보낸인(사원 아이디) : " + empReceiver);
		System.out.println("관련 프로젝트 번호 : " + msgProject);
		System.out.println("답장하는 메시지의 내용 : " + msgContent);
		
		CommonDAO cDao = CommonDAO.getInstance();
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		// 수신인 정보를 가져온다.
		Freelancer free = fDao.getfVo(freeReceiver);
		Employee emp = eDao.geteVo(empReceiver);
		
		// 가져온 수신인 정보를 콘솔창에 출력한다.
		System.out.println("가져온 프리랜서 수신인 정보 : " + free);
		System.out.println("가져온 사원 수신인 정보 : " + emp);
		
		// 프로젝트 정보를 가져옴
		if(msgProject!=null && !msgProject.equals("")) {
		Project project = cDao.selectProjectByNo(Integer.parseInt(msgProject));
		System.out.println("가져온 프로젝트 : " + project);
		request.setAttribute("reProject", project);
		}
		// 답장할 메시지를 저장할 객체를 생성한다.
		Message reMsg = new Message();
		
		// 현재 사용자의 회원번호를 세션으로부터 가져온다.
		Freelancer freeTempVo = (Freelancer) request.getSession().getAttribute("loginfree");
		Employee empTempVo = (Employee) request.getSession().getAttribute("loginemp");
						
		// 세션으로부터 가져온 사용자의 회원정보를 출력한다.
		System.out.println("세션으로부터 가져온 프리랜서의 정보 : " + freeTempVo);
		System.out.println("세션으로부터 가져온 사원의 정보 : " + empTempVo);
				
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
		
		reMsg.setPrevMsgNum(msgNo);
		reMsg.setProjNum(msgProject);
		reMsg.setMsgTitle("Re:" + msgTitle);
		
		if( freeReceiver!= null ) {
			// 수신인이 프리랜서일 때 프리랜서
			reMsg.setFreeReceiver(free.getFreeName());
			String freeName = free.getFreeName();
			String freeId = free.getFreeId();
			
			// 발신자 정보를 내용(msgContent)애 넣는다.
			reMsg.setMsgContent( msgSendDate + "에 작성자 "+ freeName + " <" + freeId  + "> 님이 작성 :  \n\t" + msgContent
								+"\n------------------------------------------------------------------\n");
			System.out.println(reMsg);
		} 
		if( empReceiver != null ) {
			reMsg.setEmpReceiver(emp.getEmpId());
			String empId = emp.getEmpId();
			String empName = emp.getEmpName();

			// 발신자 정보를 내용(msgContent)애 넣는다.
			reMsg.setMsgContent( msgSendDate + "에 작성자 "+ empName + " <" + empId  + "> 님이 작성 :  \n\t" + msgContent
								+"\n------------------------------------------------------------------\n");

			System.out.println(reMsg);
		}

		System.out.println("답장하는 메시지 관련 정보 : " + reMsg);
		
		
		request.setAttribute("reFree", free);
		request.setAttribute("reEmp", emp);
		request.setAttribute("reMsg", reMsg);
		request.setAttribute("messageList", messageList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

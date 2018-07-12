package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.data.table.Message;

public class SendInputMessage implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/project/inputFreelancer.jsp";
		String content;
		String title;
		
		title = "프로젝트 " +request.getParameter("projNum") + "에 대해 투입을 요청합니다.";
		content = request.getParameter("joinDate") + "~" + request.getParameter("dropDate") +
				"까지 " + request.getParameter("");
		
		CommonDAO cDao = CommonDAO.getInstance();
		
		Message msgVo =  new Message();
		
		msgVo.setEmpWriter(request.getParameter("empWriter"));
		msgVo.setFreeReceiver(request.getParameter("freeReceiver"));
		
		msgVo.setMsgContent("content");
		msgVo.setProjNum(request.getParameter("projNum"));
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

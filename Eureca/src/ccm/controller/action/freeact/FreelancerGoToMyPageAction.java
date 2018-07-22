package ccm.controller.action.freeact;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;
import ccm.data.table.JoinProjectView;

public class FreelancerGoToMyPageAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("실행확인");
		String url = "/freelancer/myPage.jsp";
		
		Freelancer loginfree = (Freelancer) request.getSession().getAttribute("loginfree");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		System.out.println(loginfree.getFreeId());
		List<JoinProjectView> jpViewList = fDao.selectJoinProjectViewList(loginfree.getFreeId());
		for(JoinProjectView view : jpViewList) {
			System.out.println("조인프로젝트뷰"+view);
		}
		
		String freeState = fDao.getFreeState(loginfree.getFreeId());
		
		request.setAttribute("freeState", freeState);
		request.setAttribute("jpViewList", jpViewList);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

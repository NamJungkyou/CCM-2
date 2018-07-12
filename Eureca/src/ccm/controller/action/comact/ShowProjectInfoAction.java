package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.data.table.Employee;
import ccm.data.table.Freelancer;
import ccm.data.table.Project_Info_view;

public class ShowProjectInfoAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "project/showProjectInfo.jsp";
		
		CommonDAO cDao = CommonDAO.getInstance();
		
		// 현재 사용자의 회원번호를 세션으로부터 가져오는 과정
		Freelancer freeTempVo = (Freelancer) request.getSession().getAttribute("loginfree");
		Employee empTempVo = (Employee) request.getSession().getAttribute("loginemp");
		
		if(freeTempVo == null && empTempVo == null) {
			url = "common/login.jsp";
		}

		// 메인페이지에서 no로 보내온 프로젝트 번호를 문자열(String) no에 저장한다.
		String no = request.getParameter("no");
		
		
		// 프로젝트번호가 저장된 no을 사용하여 프로젝트 정보를 가져온다.
		Project_Info_view project_Info_view = cDao.selectOneProjByNo(no);
		
		request.setAttribute("project", project_Info_view);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

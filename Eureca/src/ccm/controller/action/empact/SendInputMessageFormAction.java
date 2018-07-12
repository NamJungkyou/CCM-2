package ccm.controller.action.empact;

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
import ccm.data.table.JoinProj;
import ccm.data.table.Project;

public class SendInputMessageFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "project/sendInputMsg.jsp";
		
		String[] no = request.getParameterValues("freeId");
		String[] joinNum = request.getParameterValues("joinNum");
		Project project = null;
		List<JoinProj> joinproj = null;
		List<Freelancer> free = null;
		JoinProj join = null;
		
		CommonDAO cDao = CommonDAO.getInstance();
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		
		// 메시지를 보낼, 로그인 되어있는 사원(관리자)를 받아온다.
		Employee empTempVo = (Employee) request.getSession().getAttribute("loginemp");
		
		// join에 참여프로젝트의 정보를 불러온다.
		join = cDao.getJoinProjByNo(joinNum[0]);

		// 프로젝트에 대한 상세 정보를 참여프로젝트의 프로젝트 번호로 가져온다.
		project=cDao.selectProjectByNo(join.getProjNum());
		
		for(int i=0; i<joinNum.length; i++) {
			
			// 프리랜서VO를 기초로하는 리스트 free를 받아온 no[]로 프리랜서의 정보를 찾아ㅗㄴ다. 
			free.add(fDao.getfVo(no[i]));
			
			// 참여프로젝트를 참여번호로 가져온다.
			joinproj.add(cDao.getJoinProjByNo(joinNum[i]));
			
		}
		
		request.setAttribute("emp", empTempVo);
		request.setAttribute("free", free);
		request.setAttribute("joinProjList", joinproj);
		request.setAttribute("project", project);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

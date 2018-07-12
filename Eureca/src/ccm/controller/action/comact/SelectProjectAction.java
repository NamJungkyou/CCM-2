
package ccm.controller.action.comact;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.data.table.Employee;
import ccm.data.table.Freelancer;
import ccm.data.table.JoinProject_view;

public class SelectProjectAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url ="common/msg/selectProject.jsp";
		String freeId = request.getParameter("freeId");
		List <JoinProject_view> joinproj = null;
		
		CommonDAO cDao = CommonDAO.getInstance();

		// 현재 사용자의 회원번호를 세션으로부터 가져오는 과정
		Freelancer freeTempVo = (Freelancer) request.getSession().getAttribute("loginfree");
		Employee empTempVo = (Employee) request.getSession().getAttribute("loginemp");
		
		System.out.println("프리랜서 세션을 가져옴 : " + freeTempVo);
		System.out.println("사원 세션을 가져옴 : " + empTempVo);
		
		if( freeTempVo != null ) {
			// 세션을 통해 가져온 사용자의 아이디가 프리랜서일 때 해당 프리랜서가 받은 메시지 목록을 받아온다.
			joinproj = cDao.selectJoinProjectByFreeId(freeTempVo.getFreeId());
			System.out.println("프리렌서 아이디로 joinproj를 가져옴 : " + joinproj);
			
		} else if( empTempVo != null ) {
			// 세션을 통해 가져온 사용자의 아이디가 사원일 때 해당 사원이 받은 메시지 목록을 받아온다.
			joinproj = cDao.selectJoinProjectByFreeId(freeId);
			System.out.println("수신받는 프리랜서 아이디로 joinproj를 가져옴 : " + joinproj);
		}
		
		System.out.println("joinProj__ : " + joinproj);
		
		request.setAttribute("joinProjList", joinproj);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

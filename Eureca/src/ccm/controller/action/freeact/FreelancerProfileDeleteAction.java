package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;
/**
 * 프리랜서 계정 탈퇴(삭제)액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class FreelancerProfileDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 로그인한 프리랜서의 정보를 불러오기위해 로그인 정보값이 저장된 세션을 불러옴
		Freelancer loginfree = (Freelancer) request.getSession().getAttribute("loginfree");
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		// FreelancerDAO에서 프리랜서 계정을 삭제하는 deleteFreelancer 메소드를 실행
		fDao.deleteFreelancer(loginfree.getFreeId());
		
		new FreelancerProfileAction().execute(request, response); 

	}

}

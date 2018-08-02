package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;
/**
 * 프리랜서 계좌정보 등록 및 수정 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class FreelancerBankUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Freelancer fVo = new Freelancer();
		
		fVo.setFreeBank(request.getParameter("freeBank")); // 은행명
		fVo.setFreeAccName(request.getParameter("freeAccName")); // 계좌명의 
		fVo.setFreeAccount(request.getParameter("freeAccount")); // 계좌번호
		fVo.setFreeId(request.getParameter("freeId")); // 아이디

		FreelancerDAO fDao = FreelancerDAO.getInstance();
		fDao.updateAccount(fVo); // 계좌정보 업데이트 메소드 실행
		
		// 계좌정보가 제대로 업데이트되고 있는지 테스트
		System.out.println(request.getParameter("freeBank"));
		System.out.println(request.getParameter("freeAccName"));
		System.out.println(request.getParameter("freeAccount"));
		System.out.println(request.getParameter("freeId"));

		new FreelancerProfileAction().execute(request, response);

	}

}

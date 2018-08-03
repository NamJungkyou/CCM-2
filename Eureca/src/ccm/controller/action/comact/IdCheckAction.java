package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
/**
 * 아이디중복체크가 이루어지는 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class IdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String freeid = request.getParameter("freeid");
		/*System.out.println("freeId값을 제대로 받아오는지 테스트" + freeid);*/
		CommonDAO cDao = CommonDAO.getInstance();
		
		// CommonDAO에서 아이디 중복체크 confirmID 메소드 실행
		int result = cDao.confirmID(freeid);
		
		request.setAttribute("freeid", freeid);
		request.setAttribute("result", result); // 중복체크가 끝난 freeid값을 result에 입력
		
		// 아이디 중복체크가 이루어지는 jsp페이지 출력
		RequestDispatcher dispatcher = request.getRequestDispatcher("common/idCheck.jsp");
		dispatcher.forward(request, response);

	}

}

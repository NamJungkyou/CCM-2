package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.data.table.Freelancer;
/**
 * 회원가입 페이지에서 입력된 정보가 등록되는 회원가입 액션
 * 커맨드값 : join
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String freeId = request.getParameter("freeId"); // 아이디
		String freePw = request.getParameter("freePw"); // 비밀번호
		String email_front = request.getParameter("email_front"); // 이메일 아이디
		String email_rear = request.getParameter("email_rear"); // 이메일 도메인주소

		System.out.println(freeId + ", " + freePw);
		
		// 이메일 입력값을 받을 때는 아이디와 도메인주소를 따로 입력받지만, 데이터베이스에 넘겨줄때는 아이디와 도메인주소를 한번에 받아서 넘겨줌
		String freeEmail = email_front + "@" + email_rear; 

		Freelancer fVo = new Freelancer();

		fVo.setFreeId(freeId);
		fVo.setFreeEmail(freeEmail);
		fVo.setFreePw(freePw);

		CommonDAO cDao = CommonDAO.getInstance();
		// CommonDAO에서 회원가입 insertMember 메소드 실행
		cDao.insertMember(fVo);

	}

}

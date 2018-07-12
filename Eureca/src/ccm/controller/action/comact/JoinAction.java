package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.data.table.Freelancer;
/*import test.ccm.dao.TestDAO;
import test.ccm.dto.FreeVO;*/

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String freeId = request.getParameter("freeId");
		String freePw = request.getParameter("freePw");
		String email_front = request.getParameter("email_front");
		String email_rear = request.getParameter("email_rear");

		System.out.println(freeId + ", " + freePw);

		String freeEmail = email_front + "@" + email_rear;

		Freelancer fVo = new Freelancer();

		fVo.setFreeId(freeId);
		fVo.setFreeEmail(freeEmail);
		fVo.setFreePw(freePw);

		CommonDAO cDao = CommonDAO.getInstance();
		cDao.insertMember(fVo);

	}

}

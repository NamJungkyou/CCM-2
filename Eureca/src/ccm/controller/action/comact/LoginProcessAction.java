package ccm.controller.action.comact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.dao.EmployeeDAO;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Employee;
import ccm.data.table.Freelancer;

/***************************
 * 
 * 
 * 로그인 과정을 처리하는 액션
 * 사용자가 로그인 폼에서 입력한것을 검사하고 로그인시켜주는 액션
 * 아이디 또는 비밀번호를 틀리게 입력하면 걍 얼럿이 뜸
 * 커맨드값 : logon
 * 
 * 작성자 : 진재환
 * 
 * 수정자 : 진재환
 * 
 * 수정일 : 2018-05-07
 *
 *
 ***************************/

public class LoginProcessAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		//로그인폼에서 사용자가 입력한 아이디와 비번을 가져옴
		String feid = request.getParameter("feid");
		String fepw = request.getParameter("fepw");
		
		Freelancer loginFree = new Freelancer();
		Employee loginEmp = new Employee();
		
		CommonDAO cd = CommonDAO.getInstance();
		FreelancerDAO freelancerDao = FreelancerDAO.getInstance();
		EmployeeDAO employeeDao = EmployeeDAO.getInstance();
		int res = cd.loginCheck(feid, fepw, loginFree, loginEmp); //로그인체크를 먼저 해줌
		
		//일단 세션값에 저장할 프리랜서 또는 직원 클래스를 null로 초기화한다
		
		System.out.println("로그인 확인 = "+res);
		
		switch(res) //res변수가 아이디비번 틀리면 -1, 프리랜서면 1, 직원이면 2를 토할것인데 각각의 처리를 해준다
		{
		case -1:
			//로그인 실패하면 history.go(-1);함수때문에 로그인폼으로 다시 이동한다 이거지
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<script>");
			response.getWriter().println("alert('로그인실패!'); history.go(-1);");
			response.getWriter().println("</script>");
			break;
		case 1: //res가 1이면 프리랜서 로그인
			System.out.println(loginFree == null ? "로그인프리 널" : "로그인프리 낫널");
			request.getSession().setAttribute("loginfree", loginFree);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<script>");
			response.getWriter().println("alert('로그인했습니다');");
			//response.getWriter().println("location.href='/Eureca/CommonServ?command=main'");
			response.getWriter().println("</script>");
			//RequestDispatcher dispatcher = request.getRequestDispatcher("common/idCheck.jsp");
			//dispatcher.forward(request, response);
			request.getSession().setAttribute("freeState", freelancerDao.getFreeState(loginFree.getFreeId()));
			request.getSession().setAttribute("newMessage", freelancerDao.getNewMassageCount(loginFree.getFreeId()));
			
			new GoToMainAction().execute(request, response);
			break;
		case 2: //res가 2면 직원로그인
			System.out.println(loginEmp == null ? "직원 널" : "직원 낫널");
			request.getSession().setAttribute("loginemp", loginEmp);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<script>");
			response.getWriter().println("alert('로그인했습니다');");
			//response.getWriter().println("location.href='/Eureca/CommonServ?command=main'");
			response.getWriter().println("</script>");
			request.getSession().setAttribute("doingProj", employeeDao.getDoingProjCount());
			request.getSession().setAttribute("waitingProj", employeeDao.getWaitingProjCount());
			request.getSession().setAttribute("empNewMessage", employeeDao.getNewMassageCount(loginEmp.getEmpId()));

			new GoToMainAction().execute(request, response);
			
			break;
		default:
			break;
		}
		
	}
}

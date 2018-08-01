package ccm.controller.action.comact;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.data.table.Project_Info_view;

/***************************
 * 
 * 
 * index.jsp에서 이 과정을 통해서 main.jsp로 이동한다
 * 유레카에서 가장먼저 실행되는 액션
 * 커맨드값 : main
 *
 ***************************/

public class GoToMainAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 커먼DAO 객체 인스턴스를 받아옴
		CommonDAO cDao = CommonDAO.getInstance();
		
		// 최신 프로젝트 정보를 받음
		Project_Info_view project_Info_view = cDao.selectLastRegistProject();
		
		// 프로젝트 리스트를 받음
		List<Project_Info_view> projectList = cDao.selectAllJoinableProject();
		
		
		request.setAttribute("projectList", projectList);
		request.setAttribute("project", project_Info_view);
		// 인덱스에서 메인으로 이동
		request.getRequestDispatcher("/common/main.jsp").forward(request, response);
	}
}

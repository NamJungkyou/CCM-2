package ccm.controller.action.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.dao.ProjectViewDAO;
import ccm.data.table.DBMS;
import ccm.data.table.Framework;
import ccm.data.table.ProgLang;
import ccm.data.table.Project;

public class ProjectInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String url = "/project/projectInsert.jsp";

		ProjectViewDAO projectViewDao = ProjectViewDAO.getInstance();
		CommonDAO commonDao = CommonDAO.getInstance();

		List<DBMS> dbmsList = commonDao.DBMSList();
		List<ProgLang> langList = commonDao.ProgLangList();
		List<Framework> frameList = commonDao.FameworkList();
		Project newProjNum = projectViewDao.selctNewProjNum();

		System.out.println("db리스트 =" + dbmsList.toString());
		System.out.println("언어 리스트 = " + langList.toString());
		System.out.println("프레임 리스트 = " + frameList.toString());

		request.setAttribute("dbmsList", dbmsList);
		request.setAttribute("langList", langList);
		request.setAttribute("frameList", frameList);
		request.setAttribute("newProjNum", newProjNum);

		String projField = request.getParameter("projField");
		if (projField != null)
			projField = new String(projField.getBytes("8859_1"), "UTF-8");
		String projRegisterer = request.getParameter("projRegisterer");
		String projRegisterDate = request.getParameter("projRegisterDate");
		String projState = request.getParameter("projState");
		if (projState != null)
			projState = new String(projState.getBytes("8859_1"), "UTF-8");
		String projName = request.getParameter("projName");
		if (projName != null)
			projName = new String(projName.getBytes("8859_1"), "UTF-8");
		String devFieldSearch = request.getParameter("devFieldSearch");
		if (devFieldSearch != null)
			devFieldSearch = new String(devFieldSearch.getBytes("8859_1"), "UTF-8");
		String[] langCount = request.getParameterValues("progLangSearch");
		String dbNum = request.getParameter("DBMSSearch");
		String[] tfwCount = request.getParameterValues("TOOLfwSearch");
		String startDate = request.getParameter("projStartDate");
		String projExpectedTime = request.getParameter("projExpectedTime");
		String recruitStartDate = request.getParameter("recruitStartDate");
		String recruitEndDate = request.getParameter("recruitEndDate");
		String projTarget = request.getParameter("projTarget");
		if (projTarget != null)
			projTarget = new String(projTarget.getBytes("8859_1"), "UTF-8");
		String projPartner = request.getParameter("projPartner");
		if (projPartner != null)
			projPartner = new String(projPartner.getBytes("8859_1"), "UTF-8");
		String projPlan = request.getParameter("projPlan");
		if (projPlan != null)
			projPlan = new String(projPlan.getBytes("8859_1"), "UTF-8");

		String[] roleName = new String[5];
		int[] requirement = new int[5];

		System.out.println("프로젝트 등록 액션 등록인 = " + projRegisterer);
		System.out.println("프로젝트 등록 액션 등록일 = " + projRegisterDate);
		System.out.println("프로젝트 등록 액션 상태 = " + projState);
		System.out.println("프로젝트 등록 액션 프로젝트명 = " + projName);
		System.out.println("프로젝트 등록 액션 개발분야 = " + devFieldSearch);
		System.out.println("프로젝트 등록 액션 언어 = " + langCount);
		System.out.println("프로젝트 등록 액션 DBMS = " + dbNum);
		System.out.println("프로젝트 등록 액션 툴프레임 = " + tfwCount);
		System.out.println("프로젝트 등록 액션 시작일 = " + startDate);
		System.out.println("프로젝트 등록 액션 종료일 = " + projExpectedTime);
		System.out.println("프로젝트 등록 액션 모집시작일 = " + recruitStartDate);
		System.out.println("프로젝트 등록 액션 모집종료일 = " + recruitEndDate);
		System.out.println("프로젝트 등록 액션 고객사 = " + projTarget);
		System.out.println("프로젝트 등록 액션 협력사 = " + projPartner);

		if (projName != null) {

			Project project = new Project();

			project.setProjField(projField);
			project.setProjName(projName);
			project.setProjState(projState);
			project.setProjRegisterDate(projRegisterDate);
			project.setProjRegisterer(projRegisterer);
			project.setProjStartDate(startDate);
			project.setProjExpectedTime(Integer.parseInt(projExpectedTime));
			project.setProjTarget(projTarget);
			project.setProjPartner(projPartner);
			project.setProjPlan(projPlan);
			project.setProjRecruitStartDate(recruitStartDate);
			project.setProjRecruitEndDate(recruitEndDate);
			project.setProjDevelopSort(devFieldSearch);
			project.setDbNum(Integer.parseInt(dbNum));

			roleName[0] = new String(request.getParameter("roleName1").getBytes("8859_1"), "UTF-8");
			roleName[1] = new String(request.getParameter("roleName2").getBytes("8859_1"), "UTF-8");
			roleName[2] = new String(request.getParameter("roleName3").getBytes("8859_1"), "UTF-8");
			roleName[3] = new String(request.getParameter("roleName4").getBytes("8859_1"), "UTF-8");
			roleName[4] = new String(request.getParameter("roleName5").getBytes("8859_1"), "UTF-8");
			for (int i = 0; i < roleName.length; i++)
				System.out.println("역할역할 " + roleName[i]);
			requirement[0] = Integer.parseInt(request.getParameter("requiredPerson1"));
			requirement[1] = Integer.parseInt(request.getParameter("requiredPerson2"));
			requirement[2] = Integer.parseInt(request.getParameter("requiredPerson3"));
			requirement[3] = Integer.parseInt(request.getParameter("requiredPerson4"));
			requirement[4] = Integer.parseInt(request.getParameter("requiredPerson5"));

			projectViewDao.insertProject(project, langCount, tfwCount, roleName, requirement);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

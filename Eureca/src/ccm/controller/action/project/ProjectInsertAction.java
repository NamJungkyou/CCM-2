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

import java.net.URLEncoder;

/**
 * 프로젝트를 등록하는 클래스
 * 
 * @author 글로벌IT경영 남정규
 *
 */
public class ProjectInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 이동할 주소를 담을 변수
		String url = "/project/projectInsert.jsp";

		ProjectViewDAO projectViewDao = ProjectViewDAO.getInstance();
		CommonDAO commonDao = CommonDAO.getInstance();

		// 데이터베이스, 언어, 프레임워크의 모든 정보를 담은 리스트를 각 객체에 생성하고 저장
		List<DBMS> dbmsList = commonDao.DBMSList();
		List<ProgLang> langList = commonDao.ProgLangList();
		List<Framework> frameList = commonDao.FameworkList();
		
		// 새로 등록할 프로젝트 번호
		Project newProjNum = projectViewDao.selctNewProjNum();

		// 각 리스트들을 제대로 받아왔는지 확인
		System.out.println("db리스트 =" + dbmsList.toString());
		System.out.println("언어 리스트 = " + langList.toString());
		System.out.println("프레임 리스트 = " + frameList.toString());

		//각 리스트들과 신규 프로젝트 번호를 request에 저장
		request.setAttribute("dbmsList", dbmsList);
		request.setAttribute("langList", langList);
		request.setAttribute("frameList", frameList);
		request.setAttribute("newProjNum", newProjNum);

		String projField = request.getParameter("projField");
		if (projField != null)
			projField = new String(projField.getBytes("UTF-8"));
		
		String projRegisterer = request.getParameter("projRegisterer");
		String projRegisterDate = request.getParameter("projRegisterDate");
		String projState = request.getParameter("projState");
		if (projState != null)
			projState = new String(projState.getBytes("UTF-8"));
		String projName = request.getParameter("projName");
		if (projName != null)
			projName = new String(projName.getBytes("UTF-8"));
		String devFieldSearch = request.getParameter("devFieldSearch");
		if (devFieldSearch != null)
			devFieldSearch = new String(devFieldSearch.getBytes("UTF-8"));
		String[] langCount = request.getParameterValues("progLangSearch");
		String dbNum = request.getParameter("DBMSSearch");
		String[] tfwCount = request.getParameterValues("TOOLfwSearch");
		String startDate = request.getParameter("projStartDate");
		String projExpectedTime = request.getParameter("projExpectedTime");
		String recruitStartDate = request.getParameter("recruitStartDate");
		String recruitEndDate = request.getParameter("recruitEndDate");
		String projTarget = request.getParameter("projTarget");
		if (projTarget != null)
			projTarget = new String(projTarget.getBytes("UTF-8"));
		String projPartner = request.getParameter("projPartner");
		if (projPartner != null)
			projPartner = new String(projPartner.getBytes("UTF-8"));
		String projPlan = request.getParameter("projPlan");
		if (projPlan != null)
			projPlan = new String(projPlan.getBytes("UTF-8"));

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

		// Project 객체를 생성하고 객체의 각 변수에 맞는 정보를 저장
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

			roleName[0] = new String(request.getParameter("roleName1").getBytes("UTF-8"));
			roleName[1] = new String(request.getParameter("roleName2").getBytes("UTF-8"));
			roleName[2] = new String(request.getParameter("roleName3").getBytes("UTF-8"));
			roleName[3] = new String(request.getParameter("roleName4").getBytes("UTF-8"));
			roleName[4] = new String(request.getParameter("roleName5").getBytes("UTF-8"));
			for (int i = 0; i < roleName.length; i++)
				System.out.println("역할 " + roleName[i]);
			requirement[0] = Integer.parseInt(request.getParameter("requiredPerson1"));
			requirement[1] = Integer.parseInt(request.getParameter("requiredPerson2"));
			requirement[2] = Integer.parseInt(request.getParameter("requiredPerson3"));
			requirement[3] = Integer.parseInt(request.getParameter("requiredPerson4"));
			requirement[4] = Integer.parseInt(request.getParameter("requiredPerson5"));

			// 프로젝트를 등록하는 메소드 호출
			projectViewDao.insertProject(project, langCount, tfwCount, roleName, requirement);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

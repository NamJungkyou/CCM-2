package ccm.controller.action.freeact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.SkillInventory;

public class FreelancerSkillInventoryUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "FreelancerServ?command=freelancer_Profile";
		
		FreelancerDAO freelancerDao = FreelancerDAO.getInstance();

		List<SkillInventory> skillInvList = new ArrayList<SkillInventory>();

		String[] isExtern = request.getParameterValues("isExtern");
		String[] projNum = request.getParameterValues("projNum");
	/*	String[] projCount = request.getParameterValues("projCount");*/
		String[] projNames = request.getParameterValues("projName");
		String[] joinProjDates = request.getParameterValues("joinProjDate");
		String[] exitProjDates = request.getParameterValues("exitProjDate");
		String[] projTargets = request.getParameterValues("projTarget");
		String[] projCompanys = request.getParameterValues("projCompany");
		String[] projRoles = request.getParameterValues("projRole");
		String[] dbNum = request.getParameterValues("dbNum");
		String freeId = request.getParameter("freeId");

		int rowCount = Integer.parseInt(request.getParameter("rowCount")) - 1;
		ArrayList<String[]> langNumList = new ArrayList<String[]>();
		ArrayList<String[]> frameNumList = new ArrayList<String[]>();

		for (int i = 0; i < rowCount; i++) {
			String langNum = "langNum" + i;

			System.out.println(langNum);
			String langNumArray[] = request.getParameterValues(langNum);

			langNumList.add(langNumArray);
		}

		for (int i = 0; i < rowCount; i++) {
			String frameNum = "frameNum" + i;
			
			System.out.println(frameNum);
			String frameNumArray[] = request.getParameterValues(frameNum);
			
			frameNumList.add(frameNumArray);
		}

	/*	----------------------------테스트 출력--------------------------*/
		for(String temp: isExtern) {
			System.out.println("외부프로젝트 여부 : "+temp);
		}
		for(String temp: projNum) {
			System.out.println("프로젝트 번호 : "+temp);
		}
		for(String temp : projNames) {
			System.out.println("프로젝트명 : "+temp);
		}
		
		System.out.println("언어 배열 = " + langNumList);

		for (int i = 0; i < langNumList.size(); i++) {
			
			if(langNumList.get(i)!=null) {
			for (String temp : langNumList.get(i)) {
				System.out.println("받아온 언어"+i+" 값 = " + temp);
			}}
		}
		
		System.out.println("프레임워크 배열 : " + frameNumList);

		for (int i = 0; i < frameNumList.size(); i++) {
			
			if(frameNumList.get(i)!=null) {
			for (String temp : frameNumList.get(i)) {
				System.out.println("받아온 프레임워크"+i+" 값 : " + temp);
			}}
		}
		/*	----------------------------테스트 출력 끝--------------------------*/	
		
		for (int i = 0; i < rowCount; i++) {
			SkillInventory skillInv = new SkillInventory();
			
			skillInv.setIsExtern(Integer.parseInt(isExtern[i]));
			skillInv.setProjNum(Integer.parseInt(projNum[i]));
			skillInv.setProjName(projNames[i]);
			skillInv.setJoinProjDate(joinProjDates[i]);
			skillInv.setExitProjDate(exitProjDates[i]);
			skillInv.setProjTarget(projTargets[i]);
			skillInv.setProjCompany(projCompanys[i]);
			skillInv.setProjRole(projRoles[i]);
			skillInv.setDbNum(Integer.parseInt(dbNum[i]));
			skillInv.setFreeId(freeId);

			skillInvList.add(skillInv);
		}
		
		freelancerDao.insertSkillInventory(skillInvList, langNumList, frameNumList);

/*		new FreelancerProfileAction().execute(request, response);*/
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
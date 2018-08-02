package ccm.controller.action.freeact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Education;
/**
 * 프리랜서 학력정보 등록 및 수정 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class FreelancerEducationUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Education> eVo = new ArrayList<Education>(); // 학력정보 저장을 위해 배열로 선언
		
		String[] eduNums = request.getParameterValues("eduNum"); // 학력번호
//		for(String s : eduNums)System.out.println("학력번호가 제대로 들어가고 있는지 테스트" + s.toString());
		String[] eduSchools = request.getParameterValues("eduSchool"); // 학교명
		/*for(String s : eduSchools)System.out.println("학교명이 제대로 들어가고 있는지 테스트" + s.toString());*/
		String[] eduMajors = request.getParameterValues("eduMajor"); // 전공
		String[] eduDeplomas = request.getParameterValues("eduDeploma"); // 학위
		String[] schoolJoinDates = request.getParameterValues("schoolJoinDate"); // 입학일
		String[] schoolGraduatedDates = request.getParameterValues("schoolGraduatedDate"); // 졸업일
		String freeId = request.getParameter("freeId"); // 아이디
//		System.out.println("프리랜서 아이디 값을 제대로 받아오는지 테스트" + freeId);
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		for(int i = 0; i < eduNums.length; i++)
		{
			Education e = new Education();
			
			// 학력번호가 null이면 새로운 학력번호를 받아옴
			e.setEduNum(eduNums[i] == null || eduNums[i].equals("") ? fDao.getNewEduNum() : Integer.parseInt(eduNums[i]));
			e.setEduSchool(eduSchools[i]);
			e.setEduMajor(eduMajors[i]);
			e.setEduDeploma(eduDeplomas[i]);
			e.setSchoolJoinDate(schoolJoinDates[i]);
			e.setSchoolGraduatedDate(schoolGraduatedDates[i]);
			e.setFreeId(freeId);
			
			eVo.add(e);
		}
			
		fDao.updateEducation(eVo); // 학력정보 업데이트 메소드실행
		
		new FreelancerProfileAction().execute(request, response);
		
	}
}

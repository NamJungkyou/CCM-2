package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Career;
import ccm.data.table.Education;
import ccm.data.table.Freelancer;
import ccm.data.table.SkillInventory;

/**
 * 프리랜서 계정등록 액션, 등록이 따로따로 이루어지는게 아니라 한번에 전체등록이 이루어짐
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmpFreelancerProfileInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String savePath = request.getServletContext().getRealPath("/upload"); // 사진경로지정
		int sizeLimit = 1024 * 1024 * 15; // 업로드 가능 사진용량을 15MB로 지정

		// 사진 업로드를 위해 MultipartRequest를 선언
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8",
				new DefaultFileRenamePolicy());

		// 기존의 request.getParameter로 값을 받을 수 없어, multi.getParameter로 값을 받음
		// jsp페이지에서 form태그안에 enctype을 multipart/form-data으로 선언
		String freeEmail = multi.getParameter("freeEmail"); // 이메일
		String freePw = multi.getParameter("freePw"); // 비밀번호
		String freeName = multi.getParameter("freeName"); // 이름
		String freeBirth = multi.getParameter("freeBirth"); // 생년월일
		String freeSex = multi.getParameter("freeSex"); // 성별
		String freeMarried = multi.getParameter("freeMarried"); // 결혼여부
		String freePic = multi.getFilesystemName("freePic"); // 사진
		String freePhone = multi.getParameter("freePhone"); // 휴대폰번호
		String freeFrontAddr = multi.getParameter("freeFrontAddr"); // 주소
		String freeRearAddr = multi.getParameter("freeRearAddr"); // 나머지주소
		String freeBank = multi.getParameter("freeBank"); // 은행명
		String freeAccName = multi.getParameter("freeAccName"); // 계좌명의
		String freeAccount = multi.getParameter("freeAccount"); // 계좌번호
		String freeId = multi.getParameter("freeId"); // 아이디

		String freeFilePath = savePath + "/" + freePic; // 사진저장경로

		Freelancer fVo = new Freelancer();

		fVo.setFreeEmail(freeEmail);
		fVo.setFreePw(freePw);
		fVo.setFreeName(freeName);
		fVo.setFreeBirth(freeBirth);
		fVo.setFreeSex(Boolean.parseBoolean(freeSex)); // Boolean으로 값을 설정하고 성별은 1.남자(true)/0.여자(false)
		fVo.setFreeMarried(Boolean.parseBoolean(freeMarried)); // Boolean으로 값을 설정하고 결혼여부는 1.기혼(true)/0.미혼(false)
		fVo.setFreePhone(freePhone);
		fVo.setFreeFrontAddr(freeFrontAddr);
		fVo.setFreeRearAddr(freeRearAddr);
		fVo.setFreeBank(freeBank);
		fVo.setFreeAccName(freeAccName);
		fVo.setFreeAccount(freeAccount);
		fVo.setFreeId(freeId);
		fVo.setFreePic(freePic);
		fVo.setfreeFilePath(freeFilePath);

		EmployeeDAO eDao = EmployeeDAO.getInstance();
		eDao.insertFreelancerProfile(fVo); // employeeDAO에서 프리랜서 기본정보 등록 insertFreelancerProfile 메소드 실행

		// 학력정보등록 부분
		ArrayList<Education> eVo = new ArrayList<Education>(); // 학력정보 저장을 위해 배열로 선언

		String[] eduNums = multi.getParameterValues("eduNum"); // 학력번호
		// for(String s : eduNums)System.out.println("학력번호가 제대로 들어가는지 테스트" + s.toString());
		String[] eduSchools = multi.getParameterValues("eduSchool"); // 학교명
		// for(String s : eduSchools)System.out.println("학교명이 제대로 들어가는지 테스트" + s.toString());
		String[] eduMajors = multi.getParameterValues("eduMajor"); // 전공
		String[] eduDeplomas = multi.getParameterValues("eduDeploma"); // 학위
		String[] schoolJoinDates = multi.getParameterValues("schoolJoinDate"); // 입학일
		String[] schoolGraduatedDates = multi.getParameterValues("schoolGraduatedDate"); // 졸업일
		// String freeId = request.getParameter("freeId");

		FreelancerDAO fDao = FreelancerDAO.getInstance();

		for (int i = 0; i < eduNums.length; i++) {
			Education e = new Education();
			
			// 학력번호가 null이면 freeDAO에서 새로운 학력번호를 생성하는 getNewEduNUm 메소드에서 학력번호를 받아옴 
			e.setEduNum(eduNums[i] == null || eduNums[i].equals("") ? fDao.getNewEduNum() : Integer.parseInt(eduNums[i]));
			e.setEduSchool(eduSchools[i]);
			e.setEduMajor(eduMajors[i]);
			e.setEduDeploma(eduDeplomas[i]);
			e.setSchoolJoinDate(schoolJoinDates[i]);
			e.setSchoolGraduatedDate(schoolGraduatedDates[i]);
			e.setFreeId(freeId);

			eVo.add(e);
		}

		fDao.updateEducation(eVo); // freelancerDAO에서 학력정보 업데이트 updateEducation 메소드실행

		// 경력정보등록 부분
		ArrayList<Career> cVo = new ArrayList<Career>(); // 경력정보 저장을 위해 배열로 선언

		String[] careerNums = multi.getParameterValues("careerNum"); // 경력번호
		String[] careerCompanys = multi.getParameterValues("careerCompany"); // 회사명
		String[] companyJoinDates = multi.getParameterValues("companyJoinDate"); // 입사일
		String[] companyDropDates = multi.getParameterValues("companyDropDate"); // 퇴사일
		String[] careerPositions = multi.getParameterValues("careerPosition"); // 직책
		String[] careerJobs = multi.getParameterValues("careerJob"); // 회사내 역할
		// String freeId = request.getParameter("freeId");

		// FreelancerDAO fDao = FreelancerDAO.getInstance();

		for (int i = 0; i < careerNums.length; i++) {
			Career c = new Career();
			
			// 경력번호가 null이면 freeDAO에서 새로운 경력번호를 생성하는 getNewCareerNUm 메소드에서 경력번호를 받아옴 
			c.setCareerNum(careerNums[i] == null || careerNums[i].equals("") ? fDao.getNewCareerNum() : Integer.parseInt(careerNums[i]));
			c.setCareerCompany(careerCompanys[i]);
			c.setCompanyJoinDate(companyJoinDates[i]);
			c.setCompanyDropDate(companyDropDates[i]);
			c.setCareerPosition(careerPositions[i]);
			c.setCareerJob(careerJobs[i]);
			c.setFreeId(freeId);

			cVo.add(c);
		}

		fDao.updateCareer(cVo); // freelancerDAO에서 경력정보 업데이트 updateCareer 메소드실행
		
		// 스킬인벤토리 등록 부분
		List<SkillInventory> skillInvList = new ArrayList<SkillInventory>();

		String[] isExtern = request.getParameterValues("isExtern");
		String[] projNum = request.getParameterValues("projNum");
		/* String[] projCount = request.getParameterValues("projCount"); */
		String[] projNames = request.getParameterValues("projName");
		String[] joinProjDates = request.getParameterValues("joinProjDate");
		String[] exitProjDates = request.getParameterValues("exitProjDate");
		String[] projTargets = request.getParameterValues("projTarget");
		String[] projCompanys = request.getParameterValues("projCompany");
		String[] projRoles = request.getParameterValues("projRole");
		String[] dbNum = request.getParameterValues("dbNum");

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

		/* ----------------------------테스트 출력-------------------------- */
		for (String temp : isExtern) {
			System.out.println("외부프로젝트 여부 : " + temp);
		}
		for (String temp : projNum) {
			System.out.println("프로젝트 번호 : " + temp);
		}
		for (String temp : projNames) {
			System.out.println("프로젝트명 : " + temp);
		}

		System.out.println("언어 배열 = " + langNumList);

		for (int i = 0; i < langNumList.size(); i++) {

			if (langNumList.get(i) != null) {
				for (String temp : langNumList.get(i)) {
					System.out.println("받아온 언어" + i + " 값 = " + temp);
				}
			}
		}

		System.out.println("프레임워크 배열 : " + frameNumList);

		for (int i = 0; i < frameNumList.size(); i++) {

			if (frameNumList.get(i) != null) {
				for (String temp : frameNumList.get(i)) {
					System.out.println("받아온 프레임워크" + i + " 값 : " + temp);
				}
			}
		}
		/* ----------------------------테스트 출력 끝-------------------------- */

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

		fDao.insertSkillInventory(skillInvList, langNumList, frameNumList);

		new EmpFreelancerProfileAction().execute(request, response);
	}

}

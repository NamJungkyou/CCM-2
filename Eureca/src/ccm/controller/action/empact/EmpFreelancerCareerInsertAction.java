package ccm.controller.action.empact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Career;
/**
 * 프리랜서 계정 경력정보 등록 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmpFreelancerCareerInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Career> cVo = new ArrayList<Career>();
		
		String[] careerNums = request.getParameterValues("careerNum"); // 경력번호
		String[] careerCompanys = request.getParameterValues("careerCompany"); // 회사명
		String[] companyJoinDates = request.getParameterValues("companyJoinDate"); // 입사일
		String[] companyDropDates = request.getParameterValues("companyDropDate"); // 퇴사일
		String[] careerPositions = request.getParameterValues("careerPosition"); // 직책
		String[] careerJobs = request.getParameterValues("careerJob"); // 회사내 역할
		String freeId = request.getParameter("freeId"); // 아이디
		
		FreelancerDAO fDao = FreelancerDAO.getInstance();
		
		for(int i = 0; i < careerNums.length; i++) {
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
		
		fDao.updateCareer(cVo); // 경력정보 업데이트 메소드실행
		
		new EmpFreelancerProfileAction().execute(request, response);

		}

	}


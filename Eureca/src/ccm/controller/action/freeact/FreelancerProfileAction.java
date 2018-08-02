package ccm.controller.action.freeact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.action.Action;
import ccm.dao.CommonDAO;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Career;
import ccm.data.table.Education;
import ccm.data.table.Freelancer;
import ccm.data.table.ProgLang;
import ccm.data.table.SkillInventory;
/**
 * 로그인한 프리랜서의 마이페이지를 불러오는 액션
 * 
 * @작성자 글로벌IT꼉영 김민현
 *
 */
public class FreelancerProfileAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String url = "/freelancer/freelancerProfile.jsp";

		// String freeid = request.getParameter("freeid");
		
		// 로그인한 프리랜서의 정보를 불러오기위해 로그인 정보값이 저장된 세션을 불러옴
		Freelancer loginfree = (Freelancer) request.getSession().getAttribute("loginfree");

		FreelancerDAO fDao = FreelancerDAO.getInstance(); // FreelancerDao를 인스턴스값으로 불러옴
		CommonDAO cDao = CommonDAO.getInstance();
		Freelancer fVo = fDao.showProfile(loginfree.getFreeId()); // 이름, 나이 등 기본적인 프리랜서 정보 출력
		Freelancer fVo1 = fDao.showAccount(loginfree.getFreeId()); // 계좌정보 출력
		System.out.println("프리랜서 기본 정보를 정확히 불러오고 있는지 테스트" + fVo.toString());
		fVo.setFreeBirth(fVo.getFreeBirth().substring(0, 10)); // 생년월일 중 년월일을 제외한 시간정보는 생략
		/* Education eVo = fDao.showEducation(loginfree.getFreeId()); */
		ArrayList<Education> eVo = fDao.showEducation(loginfree.getFreeId()); // 학력정보 출력

		ArrayList<Career> cVo = fDao.showCareer(loginfree.getFreeId()); // 경력정보 출력

		ArrayList<SkillInventory> proVo = fDao.showSkillInventoryPro(loginfree.getFreeId()); // 스킬인벤토리 출력

		System.out.println(proVo.size() >= 0 ? "안비었음" : "비었음"); // 스킬인벤토리가 비었는지 안비었는지 테스트

		request.setAttribute("freelancer", fVo); // 프리랜서 기본정보
		request.setAttribute("freelancer2", fVo1); // 계좌정보

		request.setAttribute("Education", eVo); // 학력정보
		request.setAttribute("Career", cVo); // 경력정보

		request.setAttribute("SkillInventory", proVo); // 스킬인벤토리
		System.out.println("사이즈" + proVo.size());
		request.setAttribute("langDbFrame", cDao.getLangDBFrame());
		for(ProgLang temp: cDao.getLangDBFrame().getLang()) {
			System.out.println("언어 리스트 = " +temp.getLangName());
		}
		for(SkillInventory temp: proVo)
		{
			for(ProgLang lang : temp.getProjlangs())
				System.out.println("스킬인벤토리 언어 리스트 = " +lang.getLangName());
		}
		
		// String으로 선언된 url 페이지로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}

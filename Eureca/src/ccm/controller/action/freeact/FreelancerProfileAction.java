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

public class FreelancerProfileAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String url = "/freelancer/freelancerProfile.jsp";

		// String freeid = request.getParameter("freeid");

		Freelancer loginfree = (Freelancer) request.getSession().getAttribute("loginfree");

		FreelancerDAO fDao = FreelancerDAO.getInstance();
		CommonDAO cDao = CommonDAO.getInstance();
		Freelancer fVo = fDao.showProfile(loginfree.getFreeId());
		Freelancer fVo1 = fDao.showAccount(loginfree.getFreeId());
		System.out.println(fVo.toString());
		fVo.setFreeBirth(fVo.getFreeBirth().substring(0, 10));
		/* Education eVo = fDao.showEducation(loginfree.getFreeId()); */
		ArrayList<Education> eVo = fDao.showEducation(loginfree.getFreeId());

		ArrayList<Career> cVo = fDao.showCareer(loginfree.getFreeId());

		ArrayList<SkillInventory> proVo = fDao.showSkillInventoryPro(loginfree.getFreeId());

		System.out.println(proVo.size() >= 0 ? "안비었음" : "비었음");

		request.setAttribute("freelancer", fVo);
		request.setAttribute("freelancer2", fVo1);

		request.setAttribute("Education", eVo);
		request.setAttribute("Career", cVo);

		request.setAttribute("SkillInventory", proVo);
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}

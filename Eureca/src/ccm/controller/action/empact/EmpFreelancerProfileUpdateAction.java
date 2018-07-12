package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ccm.controller.action.Action;
import ccm.controller.action.freeact.FreelancerProfileAction;
import ccm.dao.EmployeeDAO;
import ccm.data.table.Freelancer;

public class EmpFreelancerProfileUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        String savePath = request.getServletContext().getRealPath("/upload");
        int sizeLimit = 1024*1024*15;
         
        MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

        String freeEmail = multi.getParameter("freeEmail");
        String freePw = multi.getParameter("freePw");
        String freeName = multi.getParameter("freeName");
        String freeBirth = multi.getParameter("freeBirth");
        String freeSex = multi.getParameter("freeSex");
        String freeMarried = multi.getParameter("freeMarried");
        
        String freeKosa = multi.getParameter("freeKosa");
        String freeScore = multi.getParameter("freeScore");
        
        String freePic = multi.getFilesystemName("freePic");
        String freePhone = multi.getParameter("freePhone");
        String freeFrontAddr = multi.getParameter("freeFrontAddr");
        String freeRearAddr = multi.getParameter("freeRearAddr");
        String freeBank = multi.getParameter("freeBank");
        String freeAccName = multi.getParameter("freeAccName");
        String freeAccount = multi.getParameter("freeAccount");
        String freeId = multi.getParameter("freeId");
        
        String freeFilePath = savePath + "/" + freePic;
		
			
		Freelancer fVo = new Freelancer();
		
        fVo.setFreeEmail(freeEmail);
        fVo.setFreePw(freePw);
        fVo.setFreeName(freeName);
        fVo.setFreeBirth(freeBirth);
        fVo.setFreeSex(Boolean.parseBoolean(freeSex));
        fVo.setFreeMarried(Boolean.parseBoolean(freeMarried));
        fVo.setFreeKosa(Integer.parseInt(freeKosa));
        fVo.setFreeScore(Double.parseDouble(freeScore));
        
        fVo.setFreePhone(freePhone);
        fVo.setFreeFrontAddr(freeFrontAddr);
        fVo.setFreeRearAddr(freeRearAddr);
        fVo.setFreeId(freeId);
        fVo.setFreePic(freePic);
        fVo.setfreeFilePath(freeFilePath);
        fVo.setFreeBank(freeBank);
        fVo.setFreeAccName(freeAccName);
        fVo.setFreeAccount(freeAccount);
        
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		eDao.updateFreelancerProfile(fVo);
		
		new FreelancerProfileAction().execute(request, response);

	}

}

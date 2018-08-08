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
/**
 * 등록된 프리랜서 계정 정보 추가 등록 및 수정 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmpFreelancerProfileUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 사진이 업로드되는 경로를 upload라는 폴더로 지정
        String savePath = request.getServletContext().getRealPath("/upload");
        // 업로드 가능 사진용량을 15MB로 지정
        int sizeLimit = 1024*1024*15;
        // 사진 업로드를 위해 MultipartRequest를 선언
        MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
       
        // 기존의 request.getParameter로 값을 받을 수 없어, multi.getParameter로 값을 받음
        // jsp페이지에서 form태그안에 enctype을 multipart/form-data로 선언
        String freeEmail = multi.getParameter("freeEmail"); // 이메일
        String freePw = multi.getParameter("freePw"); // 비밀번호
        String freeName = multi.getParameter("freeName"); // 이름
        String freeBirth = multi.getParameter("freeBirth"); // 생년월일
        String freeSex = multi.getParameter("freeSex"); // 성별
        String freeMarried = multi.getParameter("freeMarried"); // 결혼여부
        
        String freeKosa = multi.getParameter("freeKosa"); // 프리랜서등급
        String freeScore = multi.getParameter("freeScore"); // 평점
        
        String freePic = multi.getFilesystemName("freePic"); // 사진
        String freePhone = multi.getParameter("freePhone"); // 전화번호
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
        fVo.setFreeMarried(Boolean.parseBoolean(freeMarried)); // Boolean으로 값을 설정하고 결혼여부는 1. 기혼(true)/0.미혼(false)
        fVo.setFreeKosa(Integer.parseInt(freeKosa));
        fVo.setFreeScore(Double.parseDouble(freeScore)); // 평점을 더 자세히 소수점까지 부여하기위해 double값으로 설정       
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
		// EmployeeDAO에서 프리랜서 계정 업데이트 updateFreelancerProfile 메소드 호출
		eDao.updateFreelancerProfile(fVo);
		
		new FreelancerProfileAction().execute(request, response);

	}

}

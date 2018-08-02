package ccm.controller.action.freeact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ccm.controller.action.Action;
import ccm.dao.FreelancerDAO;
import ccm.data.table.Freelancer;
/**
 * 프리랜서 기본정보를 추가등록하고 수정하는 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class FreelancerProfileUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String savePath = request.getServletContext().getRealPath("/upload"); // 사진저장경로 지정
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
		String freeId = multi.getParameter("freeId"); // 아이디

		String freeFilePath = savePath + "/" + freePic; // 사진저장경로

		Freelancer fVo = new Freelancer();

		fVo.setFreeEmail(freeEmail);
		fVo.setFreePw(freePw);
		fVo.setFreeName(freeName);
		fVo.setFreeBirth(freeBirth);
		fVo.setFreeSex(Boolean.parseBoolean(freeSex)); // Boolean으로 값을 설정하고 성별은 1.남자(true)/0.여자(false)
		fVo.setFreeMarried(Boolean.parseBoolean(freeMarried)); // Boolean으로 값을 설정하고 결혼여부는 1. 기혼(true)/0.미혼(false)
		fVo.setFreePhone(freePhone);
		fVo.setFreeFrontAddr(freeFrontAddr);
		fVo.setFreeRearAddr(freeRearAddr);
		fVo.setFreeId(freeId);
		fVo.setFreePic(freePic);
		fVo.setfreeFilePath(freeFilePath);

		FreelancerDAO fDao = FreelancerDAO.getInstance();
		fDao.updateProfile(fVo); // FreelancerDAO에서 기본정보 수정 메소드를 실행

		new FreelancerProfileAction().execute(request, response); // 수정된 기본정보 값을 다시 불러오는 액션 실행
	}

}
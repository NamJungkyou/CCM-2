package ccm.controller.action.empact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ccm.controller.action.Action;
import ccm.dao.EmployeeDAO;
import ccm.data.table.Employee;
/**
 * 로그인한 사원계정의 추가정보를 등록하거나 수정하는 액션
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmployeeMyProfileUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        String savePath = request.getServletContext().getRealPath("/upload");
        int sizeLimit = 1024*1024*15;
         
        MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

        String empName = multi.getParameter("empName"); // 이름
        String empPw = multi.getParameter("empPw"); // 비밀번호
        String empDept = multi.getParameter("empDept"); // 부서
        String empDuty = multi.getParameter("empDuty"); // 직책
        String empJoinDate = multi.getParameter("empJoinDate"); // 입사일
        String empDropDate = multi.getParameter("empDropDate"); // 퇴사일
        String empPicture = multi.getFilesystemName("empPicture"); // 사진(FilesystemName으로 처리)
        String empAuth = multi.getParameter("empAuth"); // 관리권한
        String empBirth = multi.getParameter("empBirth"); // 생년월일
        String empSex = multi.getParameter("empSex"); // 성별
        String empMarried = multi.getParameter("empMarried"); // 결혼여부
        String empPhone = multi.getParameter("empPhone"); // 전화번호
        String empEmail = multi.getParameter("empEmail"); // 이메일
        String empFrontAddr = multi.getParameter("empFrontAddr"); // 주소
        String empRearAddr = multi.getParameter("empRearAddr"); // 나머지주소
        String empBank = multi.getParameter("empBank"); // 은행명
        String empAccName = multi.getParameter("empAccName"); // 계좌명의
        String empAccount = multi.getParameter("empAccount"); // 계좌번호
        String empId = multi.getParameter("empId"); // 아이디
        
        
        String empFilePath = savePath + "/" + empPicture; // 사진저장경로
		
		
		Employee eVo = new Employee();
		
		eVo.setEmpName(empName);
		eVo.setEmpPw(empPw);
		eVo.setEmpDept(empDept);
		eVo.setEmpDuty(empDuty);
		eVo.setEmpJoinDate(empJoinDate);	
		eVo.setEmpDropDate(empDropDate);	
		eVo.setEmpPicture(empPicture);
		eVo.setEmpAuth(Boolean.parseBoolean(empAuth));
		eVo.setEmpBirth(empBirth);
		eVo.setEmpSex(Boolean.parseBoolean(empSex));
		eVo.setEmpMarried(Boolean.parseBoolean(empMarried));
		eVo.setEmpPhone(empPhone);
		eVo.setEmpEmail(empEmail);
		eVo.setEmpFrontAddr(empFrontAddr);
		eVo.setEmpRearAddr(empRearAddr);
		eVo.setEmpBank(empBank);
		eVo.setEmpAccName(empAccName);
		eVo.setEmpAccount(empAccount);
		eVo.setEmpId(empId);
		eVo.setEmpFilePath(empFilePath);
		
		EmployeeDAO eDao = EmployeeDAO.getInstance();
		eDao.updateEmployee(eVo);
		
		// employeeMyProfile.jsp 페이지로 이동
		new EmployeeMyProfileActionForm().execute(request, response);

	}

}

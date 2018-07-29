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
 * 사원등록페이지에서 입력된 사원 추가 기본정보를 등록하는 액션(MultipartRequest로 Parameter값을 받을 때)
 * EmployeeProfileAction.java참조
 * 커맨드값 : employee_Profile_update
 * 
 * @작성자 글로벌IT경영 김민현
 *
 */
public class EmployeeProfileUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String savePath = request.getServletContext().getRealPath("/upload");
        int sizeLimit = 1024*1024*15;
         
        MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

        String empName = multi.getParameter("empName");
        String empPw = multi.getParameter("empPw");
        String empDept = multi.getParameter("empDept");
        String empDuty = multi.getParameter("empDuty");
        String empJoinDate = multi.getParameter("empJoinDate");
        String empDropDate = multi.getParameter("empDropDate");
        String empPicture = multi.getFilesystemName("empPicture");
        String empAuth = multi.getParameter("empAuth");
        String empBirth = multi.getParameter("empBirth");
        String empSex = multi.getParameter("empSex");
        String empMarried = multi.getParameter("empMarried");
        String empPhone = multi.getParameter("empPhone");
        String empEmail = multi.getParameter("empEmail");
        String empFrontAddr = multi.getParameter("empFrontAddr");
        String empRearAddr = multi.getParameter("empRearAddr");
        String empBank = multi.getParameter("empBank");
        String empAccName = multi.getParameter("empAccName");
        String empAccount = multi.getParameter("empAccount");
        String empId = multi.getParameter("empId");
        
        
        String empFilePath = savePath + "/" + empPicture;
		
		
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
		
		new EmployeeMyProfileActionForm().execute(request, response);

	}

}

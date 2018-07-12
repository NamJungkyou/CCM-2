package ccm.controller.actfac;

import ccm.controller.action.Action;
import ccm.controller.action.empact.AppointmentInterviewAction;
import ccm.controller.action.empact.AppointmentInterviewFormAction;
import ccm.controller.action.empact.EmpFreelancerProfileAction;
import ccm.controller.action.empact.EmployeeCareerUpdateAction;
import ccm.controller.action.empact.EmployeeEducationUpdateAction;
import ccm.controller.action.empact.EmployeeEmailCheckAction;
import ccm.controller.action.empact.EmployeeIdCheckAction;
import ccm.controller.action.empact.EmployeeMainAction;
import ccm.controller.action.empact.EmployeeMyProfileActionForm;
import ccm.controller.action.empact.EmployeeMyProfileUpdateAction;
import ccm.controller.action.empact.EmployeePhoneCheckAction;
import ccm.controller.action.empact.EmployeeProfileAction;
import ccm.controller.action.empact.EmployeeProfileDeleteAction;
import ccm.controller.action.empact.EmployeeProfileInsertAction;
import ccm.controller.action.empact.EmployeeProfileInsertFormAction;
import ccm.controller.action.empact.EmployeeProfileUpdateAction;
import ccm.controller.action.empact.EmployeeProfileUpdateActionForm;
import ccm.controller.action.empact.EmployeeViewAction;
import ccm.controller.action.empact.InterviewScheduleAction;
import ccm.controller.action.empact.ResultInterviewAction;
import ccm.controller.action.empact.SendInputMessageFormAction;
import ccm.controller.action.empact.sendInputMessageAction;

public class EmployeeAF {
	private static EmployeeAF instance = new EmployeeAF();

	private EmployeeAF() {
		super();
	}

	public static EmployeeAF getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("EmployeeAF :" + command);

		// 커맨드를 넣는 코드 http://localhost:8181/FMS/Eu?c=login 이런식
		if (command.equals("employee_Profile")) {
			action = new EmployeeProfileAction();
		} else if (command.equals("employee_Profile_insert_form")) {
			action = new EmployeeProfileInsertFormAction();
		} else if (command.equals("employee_Profile_insert")) {
			action = new EmployeeProfileInsertAction();
		} else if (command.equals("id_Check")) {
			action = new EmployeeIdCheckAction();
		} else if (command.equals("employee_Education_update")) {
			action = new EmployeeEducationUpdateAction();
		} else if (command.equals("phone_Check")) {
			action = new EmployeePhoneCheckAction();
		} else if (command.equals("employee_Career_update")) {
			action = new EmployeeCareerUpdateAction();
		} else if (command.equals("freelancer_Profile_insert")) {
			action = new EmpFreelancerProfileAction();
		} else if (command.equals("email_Check")) {
			action = new EmployeeEmailCheckAction();
		} else if (command.equals("employee_Profile_update")) {
			action = new EmployeeProfileUpdateAction();
		} else if (command.equals("employee_Profile_update_form")) {
			action = new EmployeeProfileUpdateActionForm();
		} else if (command.equals("employee_Profile_delete")) {
			action = new EmployeeProfileDeleteAction();
		} else if (command.equals("employee_MyProfile")) {
			action = new EmployeeMyProfileActionForm();
		} else if (command.equals("employee_MyProfile_update")) {
			action = new EmployeeMyProfileUpdateAction();
		}
		/*--------------------------------박태근------------------------------------*/
		else if (command.equals("emp_main")) {
			// 사원의 메인페이지로 이동하는 액션
			action = new EmployeeMainAction();
		} else if (command.equals("emp_view")) {
			// 사원정보 상세보기 페이지로 이동하는 액션
			action = new EmployeeViewAction();
		} else if (command.equals("result_interview")) {
			// 면접 결과를 등록하는 액션
			action = new ResultInterviewAction();
		} else if (command.equals("appointment_interview")) {
			// 면접일 지정한 후 지정된 면접일과 기타 정보들을 전달해주는 액션
			action = new AppointmentInterviewAction();
		} else if (command.equals("appointment_interview_form")) {
			// 면접일을 지정하기 위한 페이지로 이동하는 액션
			action = new AppointmentInterviewFormAction();
		} else if (command.equals("interview_schedule")) {
			// 면접일정을 확인하고 결과를 등록하기 위한 페이지로 이동하는 액션
			action = new InterviewScheduleAction();
		} else if (command.equals("send_input_message")) {
			// 투입메시지를 전송하는 액션
			action = new sendInputMessageAction();
		} else if (command.equals("send_input_message_form")) {
			// 투입메시지를 전송하는 액션
			action = new SendInputMessageFormAction();
		}
		return action;
	}
}

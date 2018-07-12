package ccm.controller.actfac;

import ccm.controller.action.Action;
import ccm.controller.action.comact.EmailCheckAction;
import ccm.controller.action.comact.GoToLoginAction;
import ccm.controller.action.comact.GoToMainAction;
import ccm.controller.action.comact.IdCheckAction;
import ccm.controller.action.comact.JoinAction;
import ccm.controller.action.comact.JoinFormAction;
import ccm.controller.action.comact.LoginProcessAction;
import ccm.controller.action.comact.LogoutProcessAction;
import ccm.controller.action.comact.MessageAction;
import ccm.controller.action.comact.ReceiveMessageAction;
import ccm.controller.action.comact.SelectProjectAction;
import ccm.controller.action.comact.SelectReceiverAction;
import ccm.controller.action.comact.ShowMessageAction;
import ccm.controller.action.comact.ShowProjectInfoAction;
import ccm.controller.action.comact.ShowSendMessageAction;

/***************************
 * 
 * 
 * 악숀 인터페이스 이걸로 각 기능의 액션을 만든다
 * 
 * 작성자 :
 * 
 * 수정자 :
 * 
 * 수정일 :
 *
 *
 ***************************/

public class CommonAF {
	private static CommonAF instance = new CommonAF();

	private CommonAF() {
		super();
	}

	public static CommonAF getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("CommonAF :" + command);

		// 커맨드를 넣는 코드 http://localhost:8181/FMS/Eu?c=login 이런식
		if (command.equals("main")) {
			action = new GoToMainAction();
		} // 메인페이지로 이동
		else if (command.equals("login")) {
			action = new GoToLoginAction();
		} // 로그인페이지로 이동
		else if (command.equals("loginproc")) {
			action = new LoginProcessAction();
		} // 로그인 처리
		else if (command.equals("logoutproc")) {
			action = new LogoutProcessAction();
		} // 로그아웃 처리
		else if (command.equals("join_form")) {
			action = new JoinFormAction();
		} // 회원가입 페이지로 이동
		else if (command.equals("join")) {
			action = new JoinAction();
		} else if (command.equals("idCheck")) {
			action = new IdCheckAction();
		} else if (command.equals("emailCheck")) {
			action = new EmailCheckAction();
		}

		/*---------------------------메세지-----------------------------------*/
		else if (command.equals("message")) {
			// 메시지를 보내는 액션
			action = new MessageAction();
		} else if (command.equals("receive_message")) {
			// 메시지를 보내는 액션
			action = new ReceiveMessageAction();
		} else if (command.equals("show_message")) {
			// 메세지를 확인하는 액션
			action = new ShowMessageAction();
		} else if (command.equals("show_send_message")) {
			// 메세지를 확인하는 액션
			action = new ShowSendMessageAction();
		} else if (command.equals("msg_select_project")) {
			// 메세지와 연관된 프로젝트 번호를 프로젝트에 넣어주는 액션
			action = new SelectProjectAction();
		} else if (command.equals("msg_select_receiver")) {
			// 메세지와 연관된 수신인을 찾는 액션
			action = new SelectReceiverAction();
		} else if (command.equals("show_project_info")) {
			action = new ShowProjectInfoAction();
		}

		return action;
	}
}

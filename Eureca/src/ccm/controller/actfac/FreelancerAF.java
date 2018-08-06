package ccm.controller.actfac;

import ccm.controller.action.Action;
import ccm.controller.action.empact.EmpFreelancerCareerInsertAction;
import ccm.controller.action.empact.EmpFreelancerEducationInsertAction;
import ccm.controller.action.empact.EmpFreelancerProfileInsertAction;
import ccm.controller.action.freeact.FreelancerBankUpdateAction;
import ccm.controller.action.freeact.FreelancerCareerUpdateAction;
import ccm.controller.action.freeact.FreelancerEducationUpdateAction;
import ccm.controller.action.freeact.FreelancerEmailCheckAction;
import ccm.controller.action.freeact.FreelancerGoToMyPageAction;
import ccm.controller.action.freeact.FreelancerIdCheckAction;
import ccm.controller.action.freeact.FreelancerJoinProjectAction;
import ccm.controller.action.freeact.FreelancerPhoneCheckAction;
import ccm.controller.action.freeact.FreelancerProfileAction;
import ccm.controller.action.freeact.FreelancerProfileDeleteAction;
import ccm.controller.action.freeact.FreelancerProfileUpdateAction;
import ccm.controller.action.freeact.FreelancerSkillInventoryUpdateAction;
import ccm.controller.action.freeact.FreelancerUpdateProfileFormAction;
/**
 * 프리랜서 ActionFactory
 * 
 * @작성자 : 글로벌IT경영 김민현
 *
 */

public class FreelancerAF {
	private static FreelancerAF instance = new FreelancerAF();

	private FreelancerAF() {
		super();
	}

	public static FreelancerAF getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("FreelancerAF :" + command); // 어떤 FreelancerAF 커맨드가 실행되는지 출력

		// 커맨드를 넣는 코드 http://localhost:8181/FMS/Eu?c=login 이런식
		if (command.equals("freelancer_Profile")) {
			// 로그인한 프리랜서의 마이페이지로 이동
			action = new FreelancerProfileAction();
		} else if (command.equals("freelancer_Profile_update_form")) {
			// 로그인한 프리랜서 기본정보 수정폼 호출
			action = new FreelancerUpdateProfileFormAction();
		} else if (command.equals("freelancer_Profile_update")) {
			// 프리랜서 기본정보 수정
			action = new FreelancerProfileUpdateAction();
		} else if (command.equals("freelnacer_Profile_delete")) {
			// 프리랜서 계쩡탈퇴(삭제)
			action = new FreelancerProfileDeleteAction();
		} else if (command.equals("freelancer_Bank_update")) {
			// 프리랜서 계좌정보 등록 및 수정
			action = new FreelancerBankUpdateAction();
		} else if (command.equals("freelancer_Education_update")) {
			// 프리랜서 학력정보 등록 및 수정
			action = new FreelancerEducationUpdateAction();
		} else if (command.equals("freelancer_Career_update")) {
			// 프리랜서 경력정보 등록 및 수정
			action = new FreelancerCareerUpdateAction();
		} else if (command.equals("freelancer_SkillInventory_update")) {
			action = new FreelancerSkillInventoryUpdateAction();
		} else if (command.equals("id_Check")) {
			// 아이디 중복체크
			action = new FreelancerIdCheckAction();
		} else if (command.equals("email_Check")) {
			// 이메일 중복체크
			action = new FreelancerEmailCheckAction();
		} else if (command.equals("freelancer_join_project")) {
			action = new FreelancerJoinProjectAction();
		} else if (command.equals("emp_freelancer_insert")) {
			// 프리랜서 계정등록
			action = new EmpFreelancerProfileInsertAction();
		} else if (command.equals("phone_Check")) {
			// 휴대폰번호 중복체크
			action = new FreelancerPhoneCheckAction();
		} else if (command.equals("emp_Free_Education_insert")) {
			// 프리랜서 계정 학력정보등록
			action = new EmpFreelancerEducationInsertAction();
		} else if (command.equals("emp_Free_Career_insert")) {
			// 프리랜서 계정 경력정보등록
			action = new EmpFreelancerCareerInsertAction();
		} else if (command.equals("freelancer_myPage")) {
			action = new FreelancerGoToMyPageAction();
		}
		return action;
	}
}

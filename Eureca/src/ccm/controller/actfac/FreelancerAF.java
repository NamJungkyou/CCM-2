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
		System.out.println("FreelancerAF :" + command);

		// 커맨드를 넣는 코드 http://localhost:8181/FMS/Eu?c=login 이런식
		if (command.equals("freelancer_Profile")) {
			action = new FreelancerProfileAction();
		} else if (command.equals("freelancer_Profile_update_form")) {
			action = new FreelancerUpdateProfileFormAction();
		} else if (command.equals("freelancer_Profile_update")) {
			action = new FreelancerProfileUpdateAction();
		} else if (command.equals("freelnacer_Profile_delete")) {
			action = new FreelancerProfileDeleteAction();
		} else if (command.equals("freelancer_Bank_update")) {
			action = new FreelancerBankUpdateAction();
		} else if (command.equals("freelancer_Education_update")) {
			action = new FreelancerEducationUpdateAction();
		} else if (command.equals("freelancer_Career_update")) {
			action = new FreelancerCareerUpdateAction();
		} else if (command.equals("freelancer_SkillInventory_update")) {
			action = new FreelancerSkillInventoryUpdateAction();
		} else if (command.equals("id_Check")) {
			action = new FreelancerIdCheckAction();
		} else if (command.equals("email_Check")) {
			action = new FreelancerEmailCheckAction();
		} else if (command.equals("freelancer_join_project")) {
			action = new FreelancerJoinProjectAction();
		} else if (command.equals("emp_freelancer_insert")) {
			action = new EmpFreelancerProfileInsertAction();
		} else if (command.equals("phone_Check")) {
			action = new FreelancerPhoneCheckAction();
		} else if (command.equals("emp_Free_Education_insert")) {
			action = new EmpFreelancerEducationInsertAction();
		} else if (command.equals("emp_Free_Career_insert")) {
			action = new EmpFreelancerCareerInsertAction();
		} else if (command.equals("freelancer_myPage")) {
			action = new FreelancerGoToMyPageAction();
		}
		return action;
	}
}

package ccm.controller.actfac;

import ccm.controller.action.Action;

/**
 * 프로젝트 관련된 액션들을 처리하는 액션팩토리
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ProjectAF {
	private static ProjectAF instance = new ProjectAF();

	private ProjectAF() {
	}

	public static ProjectAF getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("ProjectAF : " + command);

		if (command.equals("project_list")) {
			action = new ccm.controller.action.project.ProjectSearchAction();
		} else if (command.equals("project_select")) {
			action = new ccm.controller.action.project.ProjectSelectAction();
		} else if (command.equals("project_insert")) {
			action = new ccm.controller.action.project.ProjectInsertAction();
		} else if (command.equals("projectjoinaccept")) {
			action = new ccm.controller.action.project.GoToProjectJoinAcceptAction();
		} else if (command.equals("acceptorrejectproc")) {
			action = new ccm.controller.action.project.ProjJoinAcceptOrRejectProcessAction();
		} else if (command.equals("putfreelancer")) {
			action = new ccm.controller.action.project.GoToPutFreelancerAction();
		} else if (command.equals("addtoputinfreelancerlist")) {
			action = new ccm.controller.action.project.AddToPutInFreelancerListAction();
		} else if (command.equals("gotoputinofsearchproject")) {
			action = new ccm.controller.action.project.GoToPutInOfSearchProjectAction();
		} else if (command.equals("putrequestordelete")) {
			action = new ccm.controller.action.project.GoToPutRequestOrDeleteAction();
		} else if (command.equals("sendmessageforputinrequestproc")) {
			action = new ccm.controller.action.project.SendMessageForPutInProcessAction();
		} else if (command.equals("sendmessageandputinproc")) {
			action = new ccm.controller.action.project.SendMessageAndPutInProcessAction();
		} else if (command.equals("gotoputinofsearchfreelancer")) {
			action = new ccm.controller.action.project.GoToPutInOfSearchFreelancerAction();
		}

		return action;
	}
}
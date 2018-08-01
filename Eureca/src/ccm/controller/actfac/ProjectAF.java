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
		}

		if (command.equals("projectjoinaccept"))
			action = new ccm.controller.action.project.GoToProjectJoinAcceptAction();
		if (command.equals("acceptorrejectproc"))
			action = new ccm.controller.action.project.ProjJoinAcceptOrRejectProcessAction();
		if (command.equals("putfreelancer"))
			action = new ccm.controller.action.project.GoToPutFreelancerAction();
		if (command.equals("addtoputinfreelancerlist"))
			action = new ccm.controller.action.project.AddToPutInFreelancerListAction();
		if (command.equals("gotoputinofsearchproject"))
			action = new ccm.controller.action.project.GoToPutInOfSearchProjectAction();
		if (command.equals("putrequestordelete"))
			action = new ccm.controller.action.project.GoToPutRequestOrDeleteAction();
		if (command.equals("sendmessageforputinrequestproc"))
			action = new ccm.controller.action.project.SendMessageForPutInProcessAction();
		if (command.equals("sendmessageandputinproc"))
			action = new ccm.controller.action.project.SendMessageAndPutInProcessAction();
		if (command.equals("gotoputinofsearchfreelancer")) {
			action = new ccm.controller.action.project.GoToPutInOfSearchFreelancerAction();

			if (command.equals("project_list")) {
				action = new ccm.controller.action.project.ProjectSearchAction(); // 프로젝트 리스트
			} else if (command.equals("project_select")) {
				action = new ccm.controller.action.project.ProjectSelectAction(); // 프로젝트 선택
			} else if (command.equals("project_insert")) {
				action = new ccm.controller.action.project.ProjectInsertAction(); // 프로젝트 등록 페이지로 이동
			}

			if (command.equals("projectjoinaccept"))
				action = new ccm.controller.action.project.GoToProjectJoinAcceptAction(); // 참여신청한 프리랜서 페이지로 이동
			if (command.equals("acceptorrejectproc"))
				action = new ccm.controller.action.project.ProjJoinAcceptOrRejectProcessAction(); // 참여신청 접수
			if (command.equals("putfreelancer"))
				action = new ccm.controller.action.project.GoToPutFreelancerAction(); // 프로젝트 투입 페이지로 이동
			if (command.equals("addtoputinfreelancerlist"))
				action = new ccm.controller.action.project.AddToPutInFreelancerListAction(); // 프리랜서 투입 목록에 추가
			if (command.equals("gotoputinofsearchproject"))
				action = new ccm.controller.action.project.GoToPutInOfSearchProjectAction(); // 프로젝트 투입의 프로젝트 검색
			if (command.equals("putrequestordelete"))
				action = new ccm.controller.action.project.GoToPutRequestOrDeleteAction(); // 투입메시지, 강제투입 페이지
			if (command.equals("sendmessageforputinrequestproc"))
				action = new ccm.controller.action.project.SendMessageForPutInProcessAction(); // 투입요청 메시지 전송
			if (command.equals("sendmessageandputinproc"))
				action = new ccm.controller.action.project.SendMessageAndPutInProcessAction(); // 강제투입
			if (command.equals("gotoputinofsearchfreelancer")) {
				action = new ccm.controller.action.project.GoToPutInOfSearchFreelancerAction(); // 프로젝트 투입의 프리랜서 검색
			}
		}
		return action;
	}
}
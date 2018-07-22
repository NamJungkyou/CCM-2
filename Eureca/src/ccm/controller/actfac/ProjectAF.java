package ccm.controller.actfac;

import ccm.controller.action.Action;
import ccm.controller.action.project.*;
public class ProjectAF {
	
	private static ProjectAF instance = new ProjectAF();
	
	private ProjectAF() {
		super();
	}
	
	public static ProjectAF getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		System.out.println("ProjectAF : " + command);
		
		if (command.equals("project_list")) {
			action = new ProjectSearchAction();
		} else if (command.equals("project_select")) {
			action = new ProjectSelectAction();
		}
		else if (command.equals("project_insert")) {
			action = new ProjectInsertAction();
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////
		if (command.equals("projectjoinaccept")) { action = new GoToProjectJoinAcceptAction(); }
		if (command.equals("acceptorrejectproc")) { action = new ProjJoinAcceptOrRejectProcessAction(); }
		if (command.equals("putfreelancer")) { action = new GoToPutFreelancerAction(); }
		if (command.equals("addtoputinfreelancerlist")) { action = new AddToPutInFreelancerListAction(); }
		if (command.equals("gotoputinofsearchproject")) { action = new GoToPutInOfSearchProjectAction(); }
		if (command.equals("putrequestordelete")) { action = new GoToPutRequestOrDeleteAction(); }
		if (command.equals("sendmessageforputinrequestproc")) { action = new SendMessageForPutInProcessAction(); }
		if (command.equals("sendmessageandputinproc")) { action = new SendMessageAndPutInProcessAction(); }
		////////////////////////////////////////////////////////////////////////////////////////////////////		
		return action;
	}

}

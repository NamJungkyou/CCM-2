function selectProject(){
	// 메시지 보내기 폼에서 프로젝트 검색을 누르면 수신인  검색창이 팝업창 형태로 나타내는 기능
	if(document.sendMsgFrm.receiver.value == ""){
		alert("수신인을 입력한 후에 검색을 눌러주세요");
		sendMsgFrm.receiver.focus();
		return false;
	}
	var url = "CommonServ?command=msg_select_project&freeId="+document.sendMsgFrm.freeReceiver.value;
	window.open(url, "_blank_1",
					"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=900, height=200");
}

function selectReceiver(){
	// 메시지 보내기 폼에서 수신인 검색을 누르면 수신인  검색창이 팝업창 형태로 나타내는 기능
	var url = "CommonServ?command=msg_select_receiver";
	window.open(url, "_blank_1",
					"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

function chooseReceiverFree(receiverId, receiverName){
	// 발신자가 사원인 경우 수신인검색에서 수신인(프리랜서)의 이름을 누르면 수신인의 정보가 수신인의 부모폼으로 들어가는 기능
	/*alert("수신인(프리랜서) 아이디 : " + receiverId + "<br>" + 
			"수신인(프리랜서) 이름 : " + receiverName);*/
	
	opener.document.getElementById("receiver").value = receiverName;
	opener.document.getElementById("freeReceiver").value = receiverId;
	
	opener.document.getElementById("freeReceiver").style.display = "";
	opener.document.getElementById("empReceiver").style.display = "none";
	
	self.close();
}

function chooseReceiverEmp(receiverId, receiverName){
	// 발신자가 프리랜서인 경우 수신인검색에서 수신인(사원)의 이름을 누르면 수신인의 정보가 수신인의 부모폼으로 들어가는 기능 
	/*alert("수신인(사원) 아이디 : " + receiverId + "<br>" + 
			"수신인(사원) 이름 : " + receiverName);*/
	
	opener.document.getElementById("receiver").value = receiverName;
	opener.document.getElementById("empReceiver").value = receiverId;
	
	opener.document.getElementById("freeReceiver").style.display = "none";
	opener.document.getElementById("empReceiver").style.display = "";
	
	self.close();
}

function chooseProject(projectId, projectName){
	// 프로젝트 검색에서 프로젝트 명을 누르면 부모 폼으로 들어가는 기능
	/*alert("프로젝트 아이디 : " + projectId + "\n" + 
			"프로젝트 이름 : " + projectName);*/
	
	opener.document.getElementById("projName").value = projectName;
	opener.document.getElementById("projNum").value = projectId;
	
	self.close();
}

function sendCheck(){
	// 메시지 보내기 버튼을 눌렀을 때 메시지 보낼 필수 정보가 누락되지 않았는지 확인하는 메시지
	if(document.sendMsgFrm.receiver.value==""){
		alert("수신인은 반드시 입력해야 합니다.");
		sendMsgFrm.receiver.focus();
		return false;
	}
	if(document.sendMsgFrm.title.value==""){
		alert("제목을 입력해 주세요.");
		sendMsgFrm.title.focus();
		return false;
	}
	if(document.sendMsgFrm.content.value==""){
		alert("내용을 입력해 주세요.");
		sendMsgFrm.content.focus();
		return false;
	}
}

function sendInputMsg(){
	if(document.getElementsByName('joinDate') == null || document.getElementsByName('joinDate') == "" ){
		alert("투입일을 확인해 주세요");
		return false;
	}
	if(document.getElementsByName('dropDate') == null || document.getElementsByName('dropDate') == "" ){
		alert("투입종료일을 확인해 주세요");
		return false;
	}
	
	var empId = document.getElementsByName('empWriter').value;
	var projNum = document.getElementsByName('projNum').value;
	var ProjName = document.getElementByName('projName').value;
	var freeId = document.getElementsByName('free');
	var joinNum = document.getElementByName('joinNum');
	var joinDate = document.getElementsByName('joinDate');
	var dropDate = document.getElementsByName('dropDate');
	var join = "";
	var drop = "";
	var free = "";
	var joinN = "";
	
	for( i=0 ; i<free.length; i++){
		free += freeId + "/";
		join += joinDate + "/";
		drop += dropDate + "/";
		joinN += joinNum + "/";
	}
	
	var url = "EmployeeServ?command=send_input_message&emp=" + empId + "&projNum=" + projNum + "&joinNum" + joinN 
			"&projName=" + projName + "&join=" + join + "&drop=" + drop + "&free=" + free;
	
	window.opener.location.href=url;
	
	self.close();

}
function state(num, state){

	// 번호를 확인하는 알림창
	// alert(num);
	
	var choice = document.getElementsByName("choice" + num )[0].value;
	
	// 상태를 확인하는 알림창 상태는
	// s = sucess, h = hold, f = fail 이다.
	// alert(state);
	
	if( state == "s" ){
	
		document.getElementById("fState" + num ).value = "sucess";
		// alert("sucess");
		
	} else if ( state == "h" ) {
		
		document.getElementById("fState" + num ).value = "hold";
		// alert("hold");
		
	} else if ( state == "f") {
		
		document.getElementById("fState" + num ).value = "fail";
		// alert("fail");
		
	} else {
	
		alert("아무 값도 안들어옴");
		
	}
	
}

function sendInterviewMsg(){
	// 면접일 지정을 위한 메시지창을 팝업창으로 띄워주는 메소드
	
	// checks 변수를 선언한 후 부모화면에서 이름이 checks인 체크박스의 value를 받아온다.
	var checks = document.getElementsByName("checks");
	
	var chk = "";
	
	for(i=0; i<checks.length; i++){
		// checks가 체크가 되었는지 확인한다.
		if(checks[i].checked === true){
			
			// 체크가 되었으면 chk에 해당 checks의 value를 '/'로 구분하여 저장한다.
			chk += checks[i].value + "/";
		}
	}
	
	if(chk == ""){
		// 선택된 체크박스가 없으면 실행이 선택된 프리랜서가 없다고 알림창이 뜬다.
		alert("면접을 볼 프리랜서를 선택해 주세요");
		return false;
	}
	
	/*alert(chk);*/
	
	
	// 펼쳐질 팝업창의 주소
	var url = "EmployeeServ?command=appointment_interview_form&chk=" + chk;
	
	// 팝업창을 펼친다.
	window.open(url, "_blank_1",
	"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=600, height=300");
	
}

function sendAppointmentMsg(){
	/*체크해야 할 것들
	date(면접일), time(면접시간), location(면접 장소), content(면접관련 알림말)*/
	if(document.frm.date.value == null || document.frm.date.value==""){
		alert("면접일을 지정해주세요");
		return false;
	}
	if(document.frm.time.value == null || document.frm.time.value == ""){
		alert("면접 시간을 지정해주세요");
		return false;
	}
	if(document.frm.location.value == null || document.frm.location.value == ""){
		alert("면접 장소를 적어주세요");
		return false;
	}
	
	var inputs = document.getElementsByName("joinNum");
	var location = document.frm.location.value;
	var time = document.frm.time.value;
	var date = document.frm.date.value;
	var content = document.frm.content.value;
	
	var no = "";
	
	for(i = 0; i < inputs.length; i++){
		no += inputs[i].value + "/";
	}
	
	alert("프리랜서 아이디 : " +  no + "\n location : " + location + "\n time : " + time 
			+ "\n date : " + date + "\n content : " + content);
	
	var url = "EmployeeServ?command=appointment_interview&no=" + no + "&location=" + location+ 
	"&interviewDate=" + date + "&interviewTime=" + time + "&content=" + content;
	
	window.opener.location.href = url;
	
	self.close();
}

function sendResultMsg(){
	// 유효성 검사를 하기 위해 freeState와 interviewReason을 받아온다.
	var freeState = document.getElementsByName("freeState");
	var interviewReason = document.getElementsByName("interviewReason");
	
	var state = "" , reason = "", diff = "";
	
	
	
	for( i = 0; i < freeState.length; i++){
		
		if(	freeState[i].value != null || 
			freeState[i].value.trim() != "" ){

			state += freeState[i].value;
		}
		if(	interviewReason[i].value != null || 
			interviewReason[i].value.trim() != "" ){
			
			reason += interviewReason[i].value;
		}
		if( 
			(	freeState[i].value == null || 
				freeState[i].value.trim() == "" )
				&&
			(	interviewReason[i].value != null || 
				interviewReason[i].value.trim() != "")){
			diff += "+";
		} else if (
			(	freeState[i].value != null || 
				freeState[i].value.trim() != "" )
				&&
			(	interviewReason[i].value == null || 
				interviewReason[i].value.trim() == "")
		) {
			diff += "+";
		}
	}
	
	if(state.trim() == ""){
		alert("채용여부를 선택하지 않으셨습니다.");
		return false;
	}
	
	if(reason.trim() == ""){
		alert("사유를 적지 않으셨습니다.");
		return false;
	}
	if(diff != ""){
		alert("채용여부와 사유의 선택이 맞지 않습니다.");
		return false;
	}
}
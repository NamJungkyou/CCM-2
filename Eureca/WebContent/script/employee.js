/**
 * employee와 관련된 유효성 검사와 등록이 이루어지는 javascript
 * 
 * @작성자 : 글로벌IT경영 김민현
 */

// 이메일 중복체크
function emailCheck() {
	// 이메일을 입력하지 않으면 안내창이 뜨고 empEmail칸으로 커서 이동
	if (document.frm.empEmail.value == "") {
		alert('이메일주소를 입력하세요');
		document.frm.empEmail.focus();
		return;
	}
	// 이메일 중복체크를 위한 커맨드 실행
	var url = "EmployeeServ?command=email_Check&empEmail=" + document.frm.empEmail.value;
	// 이메일 중복체크를 위한 팝업창 출력 및 팝업창 크기 설정
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");	
}

// 이메일 중복체크 완료
function emailok() {
	// 중복체크가 완료되면 팝업창이 닫히고 기존의 페이지에 empEmail값이 넘어감
	opener.frm.empEmail.value=document.frm.empEmail.value;
	self.close();
}

// 휴대폰번호 중복체크
function phoneCheck() {
	// 휴대폰번호를 입력하지 않으면 안내창이 뜨고 empPhone칸으로 커서 이동
	if (document.frm.empPhone.value == "") {
		alert('휴대폰번호를 입력하세요');
		document.frm.empPhone.focus();
		return;
	}	
	// 휴대폰번호 중복체크를 위한 커맨드 실행
	var url = "EmployeeServ?command=phone_Check&empPhone=" + document.frm.empPhone.value;
	// 휴대폰번호 중복체크를 위한 팝업창 출력 및 팝업창 크기 설정
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");	
}

// 휴대폰번호 중복체크 완료
function phoneok() {
	// 중복체크가 완료되면 팝업창이 닫히고 기존의 페이지에 empPhone값이 넘어감
	opener.frm.empPhone.value=document.frm.empPhone.value;
	self.close();
}

// 아이디 중복체크
function idCheck() {
	// empId란에 아이디를 입력하지 않고 등록을 누르면 다음으로 넘어가지않고 아이디를 입력하세요 라는 안내창이 뜨고 empId란에 커서가 이동
	if (document.frm.empId.value == "") {
		alert('아이디를 입력하세요');
		document.frm.empId.focus();
		return;
	}
	// 아이디 중복케츠를 위한 커맨드 실행
	var url = "EmployeeServ?command=id_Check&empId=" + document.frm.empId.value;
	// 아이디 중복체크를 위한 jsp 팝업창 크기와 설정값을 설정
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");	
}

// 아이디 중복체크 완료
function idok() {
	// 아이디 중복체크가 끝나고 확인버튼을 누르면 팝업창에 입력한 empId값이 기존 jsp페이지에 있는 empId칸에 입력됨
	opener.frm.empId.value=document.frm.empId.value;
	self.close();
}

// 사원계쩡등록
function ProfileUpdate() {
	// 비밀번호 입력칸에 값이 없다면 비밀번호를 입력해주세요 라는 안내창이 뜨고 비밀번호 입력칸으로 커서가 이동
	if (document.frm.empPw.value.length == 0) {
		alert("비밀번호를 입력해주세요");
		document.frm.empPw.focus();
		return false;
	}
	// 비밀번호 칸에 입력된 값과 비밀번호 확인칸에 입력된 값이 서로 일치하지 않으면 비밀번호가 일치하지 않습니다 라는 안내창이 뜨고 비밀번호 확인칸에 커서가 이동
	if (document.frm.empPw.value != document.frm.empPw2.value) {
		alert("비밀번호가 일치하지 않습니다");
		document.frm.empPw2.focus();
		return false;
	}
	// 생년월일 입력칸에 YYYY-MM-DD 형식으로 날짜를 입력하지 않으면, 아래와 같은 안내창이 뜨고 YYYY-MM-DD형식으로 입력하여야 등록이 가능
	var str1 = document.frm.empBirth.value;
	 var pattern = /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
	 if(pattern.test(str1)) {
	// 다른 형식으로 empBirth값을 입력하면 안내창이 뜨고 empBirth 칸으로 커서 이동
	 } else {
	     alert("2018-01-01 형식으로 날짜를 입력해주세요");
	     document.frm.empBirth.focus();
	     return false;
	 }
	// 휴대폰 입력칸에 -와 같은 특수문자를 입력하면 아래와 같은 안내창이 뜨고 특수문자를 제외한 숫자만을 입력해야 등록가능
	var str = document.frm.empPhone.value;
	 var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
	 if(regExp.test(str)) {
	  //특수문자 제거
	  alert("휴대폰 번호는 -를 제외한 숫자만 입력해주세요")
	  document.frm.empPhone.focus();
	  return false;
	 }
	// 모든 유효성 검사를 거치고 등록이 됨
	alert("등록되었습니다.");
	// 사원계정 등록을 위한 커맨드 실행
	document.frm.action = "EmployeeServ?command=employee_Profile_insert&empId="
			+ document.frm.empId.value;
	document.frm.method = "post";
	document.frm.submit();
}

// 사원계정정보 수정
function EmployeeProfileUpdate() {
	// 생년월일 입력칸에 YYYY-MM-DD 형식으로 날짜를 입력하지 않으면, 아래와 같은 안내창이 뜨고 YYYY-MM-DD형식으로 입력하여야 등록이 가능
	var str1 = document.frm.empBirth.value;
	 var pattern = /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
	 if(pattern.test(str1)) {
	// 다른 형식으로 empBirth값을 입력하면 안내창이 뜨고 empBirth 칸으로 커서 이동
	 } else {
	     alert("2018-01-01 형식으로 날짜를 입력해주세요");
	     document.frm.empBirth.focus();
	     return false;
	 }
	// 휴대폰 입력칸에 -와 같은 특수문자를 입력하면 아래와 같은 안내창이 뜨고 특수문자를 제외한 숫자만을 입력해야 등록가능
	var str = document.frm.empPhone.value;
	 var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
	 if(regExp.test(str)) {
	  //특수문자 제거
	  alert("휴대폰 번호는 -를 제외한 숫자만 입력해주세요")
	  document.frm.empPhone.focus();
	  return false;
	 }
	// 모든 유효성 검사를 거치면 안내창이 뜨고 등록이 됨
	alert("등록되었습니다.");
	// 사원계정 수정을 위한 커맨드 실행
	document.frm.action = "EmployeeServ?command=employee_MyProfile_update&empId="
			+ document.frm.empId.value;
	document.frm.method = "post";
	document.frm.submit();
}

// 로그인한 사원이 자신의 계정을 수정하거나 추가등록
function MyProfileUpdate() {
	// 수정을 할때 비밀번호를 입력하지 않으면 안내창이 뜨고 empPw로 커서이동
	if (document.frm.empPw.value.length == 0) {
		alert("비밀번호를 입력해주세요");
		document.frm.empPw.focus();
		return false;
	}
	// 수정을 할때 비밀번호칸과 비밀번호확인 칸의 값이 일치하지 않으면 안내창이 뜨고 empPw2로 커서이동
	if (document.frm.empPw.value != document.frm.empPw2.value) {
		alert("비밀번호가 일치하지 않습니다");
		document.frm.empPw2.focus();
		return false;
	} 
	// 생년월일 입력칸에 YYYY-MM-DD 형식으로 날짜를 입력하지 않으면, 아래와 같은 안내창이 뜨고 YYYY-MM-DD형식으로 입력하여야 등록이 가능
	var str1 = document.frm.empBirth.value;
	 var pattern = /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
	 if(pattern.test(str1)) {
	// 다른 형식으로 empBirth값을 입력하면 안내창이 뜨고 empBirth 칸으로 커서 이동 
	 } else {
	     alert("2018-01-01 형식으로 날짜를 입력해주세요");
	     document.frm.empBirth.focus();
	     return false;
	 }
	// 휴대폰 입력칸에 -와 같은 특수문자를 입력하면 아래와 같은 안내창이 뜨고 특수문자를 제외한 숫자만을 입력해야 등록가능
	var str = document.frm.empPhone.value;
	 var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
	 if(regExp.test(str)) {
	  //특수문자 제거
	  alert("휴대폰 번호는 -를 제외한 숫자만 입력해주세요")
	  document.frm.empPhone.focus();
	  return false;
	 }
	// 모든 유효성 검사를 거치면 안내창이 뜨고 등록이 됨
	alert("등록되었습니다.");
	// 수정을 위한 커맨드 실행
	document.frm.action = "EmployeeServ?command=employee_MyProfile_update&empId="
			+ document.frm.empId.value;
	document.frm.method = "post";
	document.frm.submit();
}

// 사원 학력 등록
function EducationUpdate(empId) {
	alert("등록되었습니다.");
	// 학력등록 커맨드 실행
	document.frm2.action = "EmployeeServ?command=employee_Education_update&empId="
			+ document.frm.empId.value;
	document.frm2.method = "post";
	document.frm2.submit();
}

// 학력, 경력 등록부분에서 행추가 기능
function add_row() {
	// my-tbody라는 이름을 jsp페이지에서 t-body id값을 my-tbody라고 입력
	var my_tbody = document.getElementById('my-tbody');
	// var row = my_tbody.insertRow(0); // 상단에 추가
	var row = my_tbody.insertRow(my_tbody.rows.length); // 하단에 추가
	// 셀 갯수를 0~4 총 5개로 설정
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	// eduNum은 따로 사용자가 입력하는 값이 아니라 자동으로 들어가는 값이므로 type을 hidden으로 줌
	cell1.innerHTML = '<input type="hidden" name="eduNum"><input type="text" name="eduSchool">';
	cell2.innerHTML = '<input type="text" name="eduMajor">';
	cell3.innerHTML = '<input type="text" name="eduDeploma">';
	cell4.innerHTML = '<input type="text" name="schoolJoinDate">';
	cell5.innerHTML = '<input type="text" name="schoolGraduatedDate">';
}

// 추가된 행 삭제
function delete_row() {
	var my_tbody = document.getElementById('my-tbody');
	if (my_tbody.rows.length < 1)
		return;
	// my_tbody.deleteRow(0); // 상단부터 삭제
	my_tbody.deleteRow(my_tbody.rows.length - 1); // 하단부터 삭제
}

// 사원 경력 등록
function CareerUpdate(empId) {
	alert("등록되었습니다.");
	// 경력등록 커맨드 실행
	document.frm2.action = "EmployeeServ?command=employee_Career_update&empId="
			+ document.frm.empId.value;
	document.frm2.method = "post";
	document.frm2.submit();
}

// (경력테이블)행추가 기능
function add_row2() {
	// CareerTable라는 이름을 jsp페이지에서 t-body id값을 CareerTable라고 입력
	var CareerTable = document.getElementById('CareerTable');
	// var row = my_tbody.insertRow(0); // 상단에 추가
	var row = CareerTable.insertRow(CareerTable.rows.length); // 하단에 추가
	// 셀 갯수를 0~3 총 4개로 설정
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	// careerNum은 따로 사용자가 입력하는 값이 아니라 자동으로 들어가는 값이므로 type을 hidden으로 줌
	cell1.innerHTML = '<input type="hidden" name="careerNum"><input type="text" name="careerCompany">';
	cell2.innerHTML = '<input type="text" name="companyJoinDate"> ㅡ <input type="text" name="companyDropDate">';
	cell3.innerHTML = '<input type="text" name="careerPosition">';
	cell4.innerHTML = '<input type="text" name="careerJob">';
}

// 추가된 행 삭제
function delete_row2() {
	var CareerTable = document.getElementById('CareerTable');
	if (CareerTable.rows.length < 1)
		return;
	// my_tbody.deleteRow(0); // 상단부터 삭제
	CareerTable.deleteRow(CareerTable.rows.length - 1); // 하단부터 삭제
}

// 사원계정등록(전체등록)
function TotalUpdate() {
	alert("등록되었습니다.");
	// 사원계쩡 커맨드 실행
	document.frm.action = "EmployeeServ?command=employee_Profile_insert&empId="
			+ document.frm.empId.value;
	document.frm.method = "post";
	document.frm.submit();
}

// 주소검색 openAPI
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/FMS/freelancer/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}

// 팝업페이지에서 입력된 주소 정보를 받아 현 페이지로 정보를 가져옴
function jusoCallBack(roadAddrPart1,addrDetail){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.frm.roadAddrPart1.value = roadAddrPart1;
		document.frm.addrDetail.value = addrDetail;	
}

// 계정삭제
function ProfileDelete() {
	alert("탈퇴되었습니다.");
	// 계정삭제 커맨드 실행
	document.frm.action = "EmployeeServ?command=employee_Profile_delete&empId="
			+ document.frm.empId.value;
	document.frm.method = "post";
	document.frm.submit();
}
function emailCheck() {
	if (document.frm.empEmail.value == "") {
		alert('이메일주소를 입력하세요');
		document.frm.empEmail.focus();
		return;
	}	
	var url = "EmployeeServ?command=email_Check&empEmail=" + document.frm.empEmail.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");	
}

function emailok() {
	opener.frm.empEmail.value=document.frm.empEmail.value;
	self.close();
}

function phoneCheck() {
	if (document.frm.empPhone.value == "") {
		alert('휴대폰번호를 입력하세요');
		document.frm.empPhone.focus();
		return;
	}	
	var url = "EmployeeServ?command=phone_Check&empPhone=" + document.frm.empPhone.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");	
}

function phoneok() {
	opener.frm.empPhone.value=document.frm.empPhone.value;
	self.close();
}

function idCheck() {
	if (document.frm.empId.value == "") {
		alert('아이디를 입력하세요');
		document.frm.empId.focus();
		return;
	}	
	var url = "EmployeeServ?command=id_Check&empId=" + document.frm.empId.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");	
}

function idok() {
	opener.frm.empId.value=document.frm.empId.value;
	self.close();
}

function ProfileUpdate() {
	
	if (document.frm.empPw.value.length == 0) {
		alert("비밀번호를 입력해주세요");
		document.frm.empPw.focus();
		return false;
	}
	
	if (document.frm.empPw.value != document.frm.empPw2.value) {
		alert("비밀번호가 일치하지 않습니다");
		document.frm.empPw2.focus();
		return false;
	}
	
	var str1 = document.frm.empBirth.value;
	 var pattern = /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
	 if(pattern.test(str1)) {
		 
	 } else {
	     alert("2018-01-01 형식으로 날짜를 입력해주세요");
	     document.frm.empBirth.focus();
	     return false;
	 }
	
	var str = document.frm.empPhone.value;
	 var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
	 if(regExp.test(str)) {
	  //특수문자 제거
	  alert("휴대폰 번호는 -를 제외한 숫자만 입력해주세요")
	  document.frm.empPhone.focus();
	  return false;
	 }
	alert("등록되었습니다.");
	document.frm.action = "EmployeeServ?command=employee_Profile_insert&empId="
			+ document.frm.empId.value;
	document.frm.method = "post";
	document.frm.submit();
}

function EmployeeProfileUpdate() {

	var str1 = document.frm.empBirth.value;
	 var pattern = /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
	 if(pattern.test(str1)) {
		 
	 } else {
	     alert("2018-01-01 형식으로 날짜를 입력해주세요");
	     document.frm.empBirth.focus();
	     return false;
	 }
	
	var str = document.frm.empPhone.value;
	 var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
	 if(regExp.test(str)) {
	  //특수문자 제거
	  alert("휴대폰 번호는 -를 제외한 숫자만 입력해주세요")
	  document.frm.empPhone.focus();
	  return false;
	 }
	alert("등록되었습니다.");
	document.frm.action = "EmployeeServ?command=employee_MyProfile_update&empId="
			+ document.frm.empId.value;
	document.frm.method = "post";
	document.frm.submit();
}

function MyProfileUpdate() {
	
	if (document.frm.empPw.value.length == 0) {
		alert("비밀번호를 입력해주세요");
		document.frm.empPw.focus();
		return false;
	}
	
	if (document.frm.empPw.value != document.frm.empPw2.value) {
		alert("비밀번호가 일치하지 않습니다");
		document.frm.empPw2.focus();
		return false;
	} 

	var str1 = document.frm.empBirth.value;
	 var pattern = /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
	 if(pattern.test(str1)) {
		 
	 } else {
	     alert("2018-01-01 형식으로 날짜를 입력해주세요");
	     document.frm.empBirth.focus();
	     return false;
	 }
	
	var str = document.frm.empPhone.value;
	 var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
	 if(regExp.test(str)) {
	  //특수문자 제거
	  alert("휴대폰 번호는 -를 제외한 숫자만 입력해주세요")
	  document.frm.empPhone.focus();
	  return false;
	 }
	alert("등록되었습니다.");
	document.frm.action = "EmployeeServ?command=employee_MyProfile_update&empId="
			+ document.frm.empId.value;
	document.frm.method = "post";
	document.frm.submit();
}

function EducationUpdate(empId) {
	alert("등록되었습니다.");
	document.frm2.action = "EmployeeServ?command=employee_Education_update&empId="
			+ document.frm.empId.value;
	document.frm2.method = "post";
	document.frm2.submit();
}

function add_row() {
	var my_tbody = document.getElementById('my-tbody');
	// var row = my_tbody.insertRow(0); // 상단에 추가
	var row = my_tbody.insertRow(my_tbody.rows.length); // 하단에 추가
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);

	cell1.innerHTML = '<input type="hidden" name="eduNum"><input type="text" name="eduSchool">';
	cell2.innerHTML = '<input type="text" name="eduMajor">';
	cell3.innerHTML = '<input type="text" name="eduDeploma">';
	cell4.innerHTML = '<input type="text" name="schoolJoinDate">';
	cell5.innerHTML = '<input type="text" name="schoolGraduatedDate">';
}

function delete_row() {
	var my_tbody = document.getElementById('my-tbody');
	if (my_tbody.rows.length < 1)
		return;
	// my_tbody.deleteRow(0); // 상단부터 삭제
	my_tbody.deleteRow(my_tbody.rows.length - 1); // 하단부터 삭제
}

function CareerUpdate(empId) {
	alert("등록되었습니다.");
	document.frm2.action = "EmployeeServ?command=employee_Career_update&empId="
			+ document.frm.empId.value;
	document.frm2.method = "post";
	document.frm2.submit();
}

function add_row2() {
	var CareerTable = document.getElementById('CareerTable');
	// var row = my_tbody.insertRow(0); // 상단에 추가
	var row = CareerTable.insertRow(CareerTable.rows.length); // 하단에 추가
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);

	cell1.innerHTML = '<input type="hidden" name="careerNum"><input type="text" name="careerCompany">';
	cell2.innerHTML = '<input type="text" name="companyJoinDate"> ㅡ <input type="text" name="companyDropDate">';
	cell3.innerHTML = '<input type="text" name="careerPosition">';
	cell4.innerHTML = '<input type="text" name="careerJob">';
}

function delete_row2() {
	var CareerTable = document.getElementById('CareerTable');
	if (CareerTable.rows.length < 1)
		return;
	// my_tbody.deleteRow(0); // 상단부터 삭제
	CareerTable.deleteRow(CareerTable.rows.length - 1); // 하단부터 삭제
}

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/FMS/freelancer/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}

function jusoCallBack(roadAddrPart1,addrDetail){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.frm.roadAddrPart1.value = roadAddrPart1;
		document.frm.addrDetail.value = addrDetail;	
}

function ProfileDelete() {
	alert("탈퇴되었습니다.");
	document.frm.action = "EmployeeServ?command=employee_Profile_delete&empId="
			+ document.frm.empId.value;
	document.frm.method = "post";
	document.frm.submit();
}
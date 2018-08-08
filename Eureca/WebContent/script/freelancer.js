/**
 * freelancer와 관련된 유효성 검사와 등록이 이루어지는 javascript
 * 
 * @작성자 : 글로벌IT경영 김민현
 */

// 아이디 중복체크
function idCheck() {
	// 아이디를 입력하지 않으면 안내창이 뜨고 freeId칸으로 커서 이동
	if (document.frm.freeId.value == "") {
		alert('아이디를 입력하세요');
		document.frm.freeId.focus();
		return;
	}
	// 아이디 중복체크를 위한 커맨드 실행
	var url = "FreelancerServ?command=id_Check&freeId=" + document.frm.freeId.value;
	// 아이디 중복체크를 위한 팝업창 출력 및 팝업창 크기 설정
	window.open(url, "_blank_1",
					"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

// 아이디 중복체크 완료
function idok() {
	// 중복체크가 완료되면 팝업창이 닫히고 기존의 페이지에 freeId값이 넘어감
	opener.frm.freeId.value = document.frm.freeId.value;
	self.close();
}

// 이메일 중복체크
function emailCheck() {
	// 이메일을 입력하지 않으면 안내창이 뜨고 freeEmail칸으로 커서 이동
	if (document.frm.freeEmail.value == "") {
		alert('이메일주소를 입력하세요');
		document.frm.freeEmail.focus();
		return;
	}
	// 이메일 중복체크를 위한 커맨드 실행
	var url = "FreelancerServ?command=email_Check&freeEmail=" + document.frm.freeEmail.value;
	// 이메일 중복체크를 위한 팝업창 출력 및 팝업창 크기 설정
	window.open(url, "_blank_1",
					"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

// 이메일 중복체크 완료
function emailok() {
	// 중복체크가 완료되면 팝업창이 닫히고 기존의 페이지에 freeEmail값이 넘어감
	opener.frm.freeEmail.value = document.frm.freeEmail.value;
	self.close();
}

// 휴대폰번호 중복체크
function phoneCheck() {
	// 휴대폰번호를 입력하지 않으면 안내창이 뜨고 freePhone칸으로 커서 이동
	if (document.frm.freePhone.value == "") {
		alert('휴대폰번호를 입력하세요');
		document.frm.freePhone.focus();
		return;
	}
	// 휴대폰번호 중복체크를 위한 커맨드 실행
	var url = "FreelancerServ?command=phone_Check&freePhone="
			+ document.frm.freePhone.value;
	// 휴대폰번호 중복체크를 위한 팝업창 출력 및 팝업창 크기 설정
	window.open(url, "_blank_1",
					"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

// 휴대폰번호 중복체크 완료
function phoneok() {
	// 중복체크가 완료되면 팝업창이 닫히고 기존의 페이지에 freePhone값이 넘어감
	opener.frm.freePhone.value = document.frm.freePhone.value;
	self.close();
}

/*
 * function ProfileUpdate() {
 * 
 * if (document.frm.freePw.value.length == 0) { alert("비밀번호를 입력해주세요");
 * document.frm.freePw.focus(); return false; }
 * 
 * if (document.frm.freePw.value != document.frm.freePw2.value) { alert("비밀번호가
 * 일치하지 않습니다"); document.frm.freePw2.focus(); return false; }
 * 
 * var str1 = document.frm.freeBirth.value; var pattern =
 * /[0-9]{4}-[0-9]{2}-[0-9]{2}/; if(pattern.test(str1)) {
 *  } else { alert("2018-01-01 형식으로 날짜를 입력해주세요");
 * document.frm.freeBirth.focus(); return false; }
 * 
 * var str = document.frm.freePhone.value; var regExp =
 * /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi if(regExp.test(str)) {
 * alert("휴대폰 번호는 -를 제외한 숫자만 입력해주세요") document.frm.freePhone.focus(); return
 * false; }
 * 
 * alert("수정되었습니다."); document.frm.action =
 * "FreelancerServ?command=freelancer_Profile_update&freeId=" +
 * document.frm.freeId.value; document.frm.method = "post";
 * document.frm.submit(); }
 */

// 프리랜서 정보 추가등록 및 수정
function ProfileUpdate() {
	// 수정이 완료되면 뜨는 안내창
	alert("수정 되었습니다.");
	// 프리랜서 정보수정 커맨드 실행
	// 아이디값을 기준으로 freeId값에 맞는 정보가 수정
	document.frm.action = "FreelancerServ?command=freelancer_Profile_update&freeId="
			+ document.frm.freeId.value;
	document.frm.method = "post";
	document.frm.submit();
}

// 프리랜서 계정 탈퇴(삭제)
function ProfileDelete() {
	// 탈퇴가 완료되면 뜨는 안내창
	alert("탈퇴 되었습니다.");
	// 프리랜서 계정탈퇴 커맨드 실행
	// 아이디값을 기준으로 freeId값에 맞는 계정 삭제
	document.frm.action = "FreelancerServ?command=freelnacer_Profile_delete&freeId="
			+ document.frm.freeId.value;
	document.frm.method = "post";
	document.frm.submit();
}

// 프리랜서 계좌정보 등록 및 수정
function BankUpdate() {
	// 수정이 완료되면 뜨는 안내창
	alert("등록되었습니다.");
	// 프리랜서 계좌정보 수정 커맨드 실행
	// 아이디값 기준으로 freeId값에 맞는 계정의 계좌정보 수정
	document.frm2.action = "FreelancerServ?command=freelancer_Bank_update&freeId="
			+ document.frm.freeId.value;
	document.frm2.method = "post";
	document.frm2.submit();
}

// 프리랜서 학력정보 등록 및 수정
function EducationUpdate(freeId) {
	// 수정이 완료되면 뜨는 안내창
	alert("등록되었습니다.");
	// 프리랜서 학력정보 수정 커맨드 실행
	// 아이디값 기준 freeId값에 맞는 계정 학력정보 수정
	document.frm2.action = "FreelancerServ?command=freelancer_Education_update&freeId="
			+ document.frm.freeId.value;
	document.frm2.method = "post";
	document.frm2.submit();
}

// 학력, 경력 등록부분에서 테이블 행추가 기능
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

// 프리랜서 경력정보 등록 및 수정
function CareerUpdate(freeId) {
	// 수정이 완료되면 뜨는 안내창
	alert("등록되었습니다.");
	// 프리랜서 경력정보 수정 커맨드 실행
	// 아이디값 기준으로 freeId값에 맞는 계정의 경력정보 수정
	document.frm2.action = "FreelancerServ?command=freelancer_Career_update&freeId="
			+ document.frm.freeId.value;
	document.frm2.method = "post";
	document.frm2.submit();
}

// (경력테이블)행 추가 기능
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

function SkillInventoryUpdate() {

	var rowCount;
	$(document).ready(function() {
		rowCount = $('#skillInv tr').length;
	});

	alert("등록되었습니다.");
	document.frm2.action = "FreelancerServ?command=freelancer_SkillInventory_update&freeId="
			+ document.frm.freeId.value + "&rowCount=" + rowCount;
	document.frm2.method = "post";
	document.frm2.submit();
}

function add_row3() {

	var SkillTable = document.getElementById('SkillTable');
	// var row = my_tbody.insertRow(0); // 상단에 추가
	var row = SkillTable.insertRow(SkillTable.rows.length); // 하단에 추가
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);
	var cell7 = row.insertCell(6);
	var cell8 = row.insertCell(7);

	var rowCount;
	$(document).ready(function() {
		if ($('#skillInv tr').length == 0) {
			rowCount = 0;
		} else {
			rowCount = $('#skillInv tr').length - 2;
		}
	});

	cell1.innerHTML = '<input type="hidden" name="isExtern" value="1"><input type="hidden" name="projNum" value="0"><input type="text" name="projName">';
	cell2.innerHTML = '<input type="text" name="joinProjDate"> ~ <input type="text" name="exitProjDate">';
	cell3.innerHTML = '<input type="text" name="projTarget">';
	cell4.innerHTML = '<input type="text" name="projCompany">';
	cell5.innerHTML = '<input type="text" name="projRole">';
	cell6.innerHTML = '<div class="checkbox-dropdown">선택<ul class="checkbox-dropdown-list"><li><label><input type="checkbox" value="1" name="langNum'
			+ rowCount
			+ '" />C</label></li><li><label><input type="checkbox" value="2" name="langNum'
			+ rowCount
			+ '" />C++</label></li><li><label><input type="checkbox" value="3" name="langNum'
			+ rowCount
			+ '" />C#</label></li><li><label><input type="checkbox" value="4" name="langNum'
			+ rowCount
			+ '" />Java</label></li><li><label><input type="checkbox" value="5" name="langNum'
			+ rowCount
			+ '" />Python</label></li><li><label><input type="checkbox" value="6" name="langNum'
			+ rowCount
			+ '" />Ruby</label></li><li><label><input type="checkbox" value="7" name="langNum'
			+ rowCount
			+ '" />Delphi</label></li><li><label><input type="checkbox" value="8" name="langNum'
			+ rowCount
			+ '" />Cobol</label></li><li><label><input type="checkbox" value="9" name="langNum'
			+ rowCount
			+ '" />R</label></li><li><label><input type="checkbox" value="10" name="langNum'
			+ rowCount + '" />javascript</label></li></ul></div>';
	cell7.innerHTML = '<select name="dbNum"><option value="1">Oracle 11g</option><option value="2">Oracle 12c</option><option value="3">MySQL</option><option value="4">MariaDB</option><option value="5">PostgreSQL</option><option value="6">SQL Server</option></select>';
	cell8.innerHTML = '<div class="checkbox-dropdown">선택<ul class="checkbox-dropdown-list"><li><label><input type="checkbox" value="1" name="frameNum'
			+ rowCount
			+ '" />WinForm</label></li><li><label><input type="checkbox" value="2" name="frameNum'
			+ rowCount
			+ '" />QT</label></li><li><label><input type="checkbox" value="3" name="frameNum'
			+ rowCount
			+ '" />MFC</label></li><li><label><input type="checkbox" value="4" name="frameNum'
			+ rowCount
			+ '" />JavaFX</label></li><li><label><input type="checkbox" value="5" name="frameNum'
			+ rowCount
			+ '" />Swing</label></li><li><label><input type="checkbox" value="6" name="frameNum'
			+ rowCount
			+ '" />Spring</label></li><li><label><input type="checkbox" value="7" name="frameNum'
			+ rowCount
			+ '" />OpenGL</label></li><li><label><input type="checkbox" value="8" name="frameNum'
			+ rowCount
			+ '" />DirectX</label></li><li><label><input type="checkbox" value="9" name="frameNum'
			+ rowCount
			+ '" />OpenCL</label></li><li><label><input type="checkbox" value="10" name="frameNum'
			+ rowCount
			+ '" />JSP</label></li><li><label><input type="checkbox" value="11" name="frameNum'
			+ rowCount
			+ '" />ASP.NET</label></li><li><label><input type="checkbox" value="12" name="frameNum'
			+ rowCount
			+ '" />JQuery</label></li><li><label><input type="checkbox" value="13" name="frameNum'
			+ rowCount
			+ '" />Xamarin</label></li><li><label><input type="checkbox" value="14" name="frameNum'
			+ rowCount
			+ '" />전자정부프레임워크</label></li><li><label><input type="checkbox" value="15" name="frameNum'
			+ rowCount + '" />Django</label></li></ul></div>';

	$(".checkbox-dropdown").click(function() {
		$(this).toggleClass("is-active");
	});

	$(".checkbox-dropdown ul").click(function(e) {
		e.stopPropagation();
	});
}

function delete_row3() {
	var SkillTable = document.getElementById('SkillTable');
	if (SkillTable.rows.length < 1)
		return;
	// my_tbody.deleteRow(0); // 상단부터 삭제
	SkillTable.deleteRow(SkillTable.rows.length - 1); // 하단부터 삭제
}

// 프리랜서 계쩡등록(관리자가 프리랜서 계정을 추가할때)
function empFreeInsert() {
	// 휴대폰 입력칸에 -와 같은 특수문자를 입력하면 아래와 같은 안내창이 뜨고 특수문자를 제외한 숫자만을 입력해야 등록가능
	var str = document.frm.freePhone.value;
	var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
	if (regExp.test(str)) {
		// 특수문자 제거
		alert("휴대폰 번호는 -를 제외한 숫자만 입력해주세요")
		document.frm.freePhone.focus();
		return false;
	}
	// 모든 유효성 검사를 거치면 안내창이 뜨고 등록이 됨 
	alert("등록되었습니다.");
	// 프리랜서 계정등록을 위한 커맨드 실행
	document.frm.action = "FreelancerServ?command=emp_freelancer_insert&freeId="
			+ document.frm.freeId.value;
	document.frm.method = "post";
	document.frm.submit();
}

// 프리랜서 계정 학력등록(관리자가 프리랜서 계정을 추가할떄)
function empFreeEducationInsert(freeId) {
	// 등록이 완료되면 뜨는 안내창
	alert("등록되었습니다.");
	// 프리랜서 계정 학력정보 등록 커맨드 실행
	document.frm2.action = "FreelancerServ?command=emp_Free_Education_insert&freeId="
			+ document.frm.freeId.value;
	document.frm2.method = "post";
	document.frm2.submit();
}

// 프리랜서 계정등록에서 학력, 경력 등을 따로 등록하지않고 한번에 등록(관리자가 프리랜서 계정을 추가할떄), 테스트
function empFreeTestInsert(freeId) {
	// 등록이 완료되면 뜨는 안내창
	alert("등록되었습니다.");
	// 프리랜서 계정등록 커맨드 실행
	document.frm.action = "FreelancerServ?command=emp_freelancer_insert&freeId="
			+ document.frm.freeId.value;
	document.frm.method = "post";
	document.frm.submit();
}

// 프리랜서 계정등록에서 학력테이블 행 추가기능
function add_Edu_row() {
	// empFreeEducationTable라는 이름을 jsp페이지에서 t-body id값을 empFreeEducationTable라고 입력
	var my_tbody = document.getElementById('empFreeEducationTable');
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
function delete_Edu_row() {
	var my_tbody = document.getElementById('empFreeEducationTable');
	if (my_tbody.rows.length < 1)
		return;
	// my_tbody.deleteRow(0); // 상단부터 삭제
	my_tbody.deleteRow(my_tbody.rows.length - 1); // 하단부터 삭제
}

// 프리랜서 계정 경력등록(관리자가 프리랜서 계정을 추가할떄)
function empFreeCareerUpdate(freeId) {
	// 등록이 완료되면 뜨는 안내창
	alert("등록되었습니다.");
	// 프리랜서 계정 경력정보 등록 커맨드 실행
	document.frm2.action = "FreelancerServ?command=emp_Free_Career_insert&freeId="
			+ document.frm.freeId.value;
	document.frm2.method = "post";
	document.frm2.submit();
}

// 프리랜서 계정등록에서 경력테이블 행 추가기능
function add_Career_row() {
	// empFreeCareerTable라는 이름을 jsp페이지에서 t-body id값을 empFreeCareerTable라고 입력
	var CareerTable = document.getElementById('empFreeCareerTable');
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
function delete_Career_row() {
	var CareerTable = document.getElementById('empFreeCareerTable');
	if (CareerTable.rows.length < 1)
		return;
	// my_tbody.deleteRow(0); // 상단부터 삭제
	CareerTable.deleteRow(CareerTable.rows.length - 1); // 하단부터 삭제
}

// 주소찾기 팝업 호출
function goPopup() {
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제
	// 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/FMS/freelancer/jusoPopup.jsp", "pop",
			"width=570,height=420, scrollbars=yes, resizable=yes");

	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제
	// 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	// var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes,
	// resizable=yes");
}

// 검색된 주소입력값 현페이지로 등록
function jusoCallBack(roadAddrPart1, addrDetail) {
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	document.frm.roadAddrPart1.value = roadAddrPart1;
	document.frm.addrDetail.value = addrDetail;

}

// 특수문자 체크
function checkStringFormat(string) {
	var stringRegx = /[~!@\#$%<>^&*\()\-=+_\’]/gi;
	var isValid = true;
	if (stringRegx.test(string)) {
		isValid = false;
	}
	return isValid;
}

// 특수문자 제거
function regExp() {
	// 특수문자 검증 start
	var str = document.frm.freePhone.value;
	var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
	if (regExp.test(str)) {
		// 특수문자 제거
		var t = str.replace(regExp, "")
		alert("특수문자를 제거했습니다. ==>" + t)
	} else {
		alert("정상적인 문자입니다. ==>" + str)
	}
	// 특수문자 검증 end
}

function search() {
	document.frm.action = "FreelancerServ?command=freelancer_join_project&result=1";
	document.frm.method = "post";
	document.frm.submit();
}

function onSubmit() {
	document.frm.submit();
}

function apply(projNum, pageNum) {
	document.frm.action = "FreelancerServ?command=freelancer_join_project&projNum="
			+ projNum + "&pageNum=" + pageNum;
	document.frm.method = "post";
	document.frm.submit();
}

function projPageSelect(pageNum) {
	document.frm.action = "FreelancerServ?command=freelancer_join_project&pageNum="
			+ pageNum;
	document.frm.method = "post";
	document.frm.submit();
}

function projPrePageSelect(pageNum) {
	if (pageNum = 1) {
		alert("첫번째 페이지입니다.");
		return false;
	} else {
		document.frm.action = "FreelancerServ?command=freelancer_join_project&pageNum="
				+ (Number(pageNum) - 1);
		document.frm.method = "post";
		document.frm.submit();
	}
}

function projNextPageSelect(pageNum, lastPage) {
	if (Number(pageNum) == Number(lastPage)) {
		alert("마지막 페이지입니다.")
		return false;
	} else {
		document.frm.action = "FreelancerServ?command=freelancer_join_project&pageNum="
				+ (Number(pageNum) + 1);
		document.frm.method = "post";
		document.frm.submit();
	}
}

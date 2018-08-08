/**
 * 회원가입에 필요한 유효성 검사가 이루어지는 javascript
 * 
 * @작성자 : 글로벌IT경영 김민현
 */

// 회원가입 체크
function joinCheck() {
	// 아이디 입력란에 아무것도 입력하지 않으면 안내창이 뜨고 아이디 입력칸으로 커서가 이동
	if (document.frm.freeid.value.length == 0) {
		alert("아이디를 입력하세요");
		document.frm.freeid.focus();
		return false;		
	} // 이메일 입력란에 아무것도 입력하지 않으면 안내창이 뜨고 이메일 입력칸으로 커서가 이동
	if (document.frm.freeemail.value.length == 0) {
		alert("이메일을 입력하세요");
		document.frm.freeemail.focus();
		return false;
	} // 비밀번호 입력란에 아무것도 입력하지 않으면 안내창이 뜨고 비밀번호 입력칸으로 커서가 이동
	if (document.frm.freepw.value.length == 0) {
		alert("비밀번호를 입력하세요");
		document.frm.freepw.focus();
		return false;
	} // 비밀번호 입력란과 비밀번호확인 입력란에 입력된 값이 일치하지 않으면 안내창이 뜨고 비밀번호확인 입력칸으로 커서가 이동
	if (document.frm.freepw.value != document.frm.freepw2.value) {
		alert("비밀번호가 일치하지 않습니다");
		document.frm.freepw2.focus();
		return false;
	} return true;
}

// 아이디 중복체크
function idCheck() {
	// 아이디 입력란에 입력된 값이 없으면 안내창이 뜨고 아이디 입력칸으로 커서 이동
	if (document.frm.freeid.value == "") {
		alert('아이디를 입력하세요');
		document.frm.freeid.focus();
		return;
	} // 아이디 중복체크를 위한 팝업창 출력	
	var url = "CommonServ?command=idCheck&freeid=" + document.frm.freeid.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");	
}

// 아이디 중복체크, 중복체크가 끝나면 팝업창에서 입력된 아이디값이 기존 jsp페이지의 아이디 값으로 입력됨
function idok() {
	opener.frm.freeid.value=document.frm.freeid.value;
	self.close();
}

// 이메일 중복체크
function emailCheck() {
	if (document.frm.freeemail.value == "") {
		alert('이메일을 입력하세요');
		document.frm.freeemail.focus();
		return;
	} // 이메일 중복체크를 위한 팝업창 출력
	var url = "CommonServ?command=emailCheck&freeemail=" + document.frm.freeemail.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");	
}

// 이메일 중복체크가 완료되면 jsp페이지에 이메일 값 입력
function emailok() {
	opener.frm.freeemail.value=document.frm.freeemail.value;
	self.close();
}

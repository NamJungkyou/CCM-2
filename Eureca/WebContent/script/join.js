function joinCheck() {
	
	if (document.frm.freeid.value.length == 0) {
		alert("아이디를 입력하세요");
		document.frm.freeid.focus();
		return false;		
	}
	if (document.frm.freeemail.value.length == 0) {
		alert("이메일을 입력하세요");
		document.frm.freeemail.focus();
		return false;
	}
	if (document.frm.freepw.value.length == 0) {
		alert("비밀번호를 입력하세요");
		document.frm.freepw.focus();
		return false;
	}
	if (document.frm.freepw.value != document.frm.freepw2.value) {
		alert("비밀번호가 일치하지 않습니다");
		document.frm.freepw2.focus();
		return false;
	} return true;
}

function idCheck() {
	if (document.frm.freeid.value == "") {
		alert('아이디를 입력하세요');
		document.frm.freeid.focus();
		return;
	}	
	var url = "CommonServ?command=idCheck&freeid=" + document.frm.freeid.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");	
}

function idok() {
	opener.frm.freeid.value=document.frm.freeid.value;
	self.close();
}

function emailCheck() {
	if (document.frm.freeemail.value == "") {
		alert('이메일을 입력하세요');
		document.frm.freeemail.focus();
		return;
	}	
	var url = "CommonServ?command=emailCheck&freeemail=" + document.frm.freeemail.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");	
}

function emailok() {
	opener.frm.freeemail.value=document.frm.freeemail.value;
	self.close();
}

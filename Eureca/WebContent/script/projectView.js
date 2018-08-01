/**
 * 프로젝트 관리 기능에서 사용할 자바스크립트를 정의해 놓았다.
 * 
 * 작성자 : 남정규
 */

// ProjSelectAcion에 프로젝트 번호를 함께 보냄.
function projectViewSelectByProjNum() {
	if (document.frm.projNum.value == "") {
		alert("번호오류");
		document.frm.projNum.focus();
		return;
	}
	var url = "ProjectServ?command=project_select&projNum="
			+ document.frm.projNum.value;
	/*
	 * window .open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes,
	 * resizable=no, width=1000, height=600");
	 */
	location.href = url;

	/* "location.href='HECE_SystemServlet?command=estmate_appform_appnum_select_form&appNum=${appformList.appnum}'" */
}

// 정렬 기준을 선택하면 
function orderSelect() {
	var url = "ProjectServ?command=project_list&order="
			+ document.frm[1].order.value + "&devCount="
			+ document.frm[0].devFieldSearch.value + "&langCount="
			+ document.frm[0].progLangSearch.value + "&dbCount="
			+ document.frm[0].DBMSSearch.value + "&tfwCount="
			+ document.frm[0].TOOLfwSearch.value;

	location.href = url;
}

function numSelect() {
	var url = "ProjectServ?command=project_list&order="
			+ document.frm[0].order.value + "&devCount="
			+ document.frm[0].devFieldSearch.value + "&langCount="
			+ document.frm[0].progLangSearch.value + "&dbCount="
			+ document.frm[0].DBMSSearch.value + "&tfwCount="
			+ document.frm.TOOLfwSearch.value + "&projNum="
			+ document.frm[1].projNum.value;

	location.href = url;
}

function onSubmit() {
	document.frm.submit();
}

function projNumSelect(projNum, pageNum) {
	document.frm.action = "ProjectServ?command=project_list&projNum=" + projNum
			+ "&pageNum=" + pageNum;
	document.frm.method = "post";
	document.frm.submit();
}

function projPageSelect(pageNum) {
	document.frm.action = "ProjectServ?command=project_list&pageNum=" + pageNum;
	document.frm.method = "post";
	document.frm.submit();
}

// 이전 페이지로 이동
function projPrePageSelect(pageNum) {
	if (pageNum = 1) {
		alert("첫번째 페이지입니다.");
		return false;
	} else {
		document.frm.action = "ProjectServ?command=project_list&pageNum="
				+ (Number(pageNum) - 1);
		document.frm.method = "post";
		document.frm.submit();
	}
}

// 다음 페이지로 이동
function projNextPageSelect(pageNum, lastPage) {
	if(Number(pageNum) == Number(lastPage)){
		alert("마지막 페이지입니다.")
		return false;
	}else{
	document.frm.action = "ProjectServ?command=project_list&pageNum="
			+ (Number(pageNum) + 1);
	document.frm.method = "post";
	document.frm.submit();
	}
}

// 
function joinFreePageSelect(joinFreePageNum) {
	document.frm.action = "ProjectServ?command=project_list&joinFreePageNum="
			+ joinFreePageNum;
	document.frm.method = "post";
	document.frm.submit();
}

// 검색창 팝업
function projectSearch(){
	var url = "ProjectServ?command=project_list";
window
		.open(url, "_blank_1",
				"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=800,height=600");
}

// 검색 팝업에서 확인을 누르면 부모 창으로 프로젝트 정보를 전송
function projectOk(projNum, projName, projState, projStartDate, projExpectedTime, projEndDate, joinFLCount, projTarget, projPartner, projPlan) {

	opener.frm.projNum.value = projNum;
	opener.frm.projName.value = projName;
	opener.frm.projState.value = projState;
	opener.frm.projStartDate.value = projStartDate;
	opener.frm.projExpectedTime.value = projExpectedTime;
	opener.frm.projEndDate.value = projEndDate;
	opener.frm.joinFLCount.value = joinFLCount;
	opener.frm.projTarget.value = projTarget;
	opener.frm.projPartner.value = projPartner;
	opener.frm.projPlan.value = projPlan;
	
	
	self.close();
}


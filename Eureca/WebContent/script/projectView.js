/**
 * 
 */

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

/*
 * function orderSelect(){ var url =
 * "ProjectServ?command=project_list&order="+document.frm2.order.value
 * +"&search" + document.frm2.cusnum.value + "&cusname=" +
 * document.frm2.cusname.value + "&cusphone=" + document.frm2.cusphone.value +
 * "&cusemail=" + document.frm2.cusemail.value + "&cusaddr=" +
 * document.frm2.cusaddr.value + "&ceoname=" + document.frm2.ceoname.value;
 * 
 * location.href = url; }
 */

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

/*
 * function numSelect(){ var url =
 * "ProjectServ?command=project_list&order="+document.frm2.order.value +
 * "&dvCount=" + document.frm.devFieldSearch.value + "&langCount=" +
 * document.frm.progLangSearch.value + "&dbCount=" +
 * document.frm.DBMSSearch.value + "&tfwCount=" +
 * document.frm.TOOLfwSearch.value;
 * 
 * location.href = url; }
 */

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

function joinFreePageSelect(joinFreePageNum) {
	document.frm.action = "ProjectServ?command=project_list&joinFreePageNum="
			+ joinFreePageNum;
	document.frm.method = "post";
	document.frm.submit();
}

function projectSearch(){
	var url = "ProjectServ?command=project_list";
window
		.open(url, "_blank_1",
				"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=800,height=600");
}

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


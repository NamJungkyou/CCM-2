<%--

프리랜서 강제투입, 투입요청 메시지 전송 페이지

작성자 : 글로벌 IT 진재환

--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>투입 메시지 전송</title>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> 
<script>
// 데이트픽커로 투입일 또는 종료일 버튼을 누르면
// 달력이 동적으로 생성되도록 함
$(function() {
  $( ".datepicker" ).datepicker({
    dateFormat: 'yy-mm-dd'
  });
});

// 현재 날짜를 DB에 맞게 정제해서 반환하는 함수
function getCurDate()
{
	// 현재 날짜를 동적으로 생성
    var currentdate = new Date();
	// 현재 날짜 변수에서 달만 따로 빼서
	// 현재달이 9보다 크면 날짜 앞에 0을 붙여줌
    var month = (currentdate.getMonth() + 1) < 10 ? ("0" + (currentdate.getMonth() + 1)) : (currentdate.getMonth() + 1);
    // 일수도 똑같이 검사해서 0을 붙여줌
    var days = (currentdate.getDate()) < 10 ? ("0" + (currentdate.getDate())) : (currentdate.getDate());
    
    // 정제된 월일을 다시 연도 앞에 붙임
    var datetime = currentdate.getFullYear() + "-"
            + month + "-"
            + days;
    
    return datetime;
}

// 투입 요청 메시지를 보내는 함수
// 파라미터 넘버는 ArrayList 객체로 넘어온 프리랜서 목록
// 에 있는 인덱스고 msgOrPutIn은
// 메시지를 보낼 건지
// 강제투입을 할 건지 결정하는 불리언변수
function submitMessageRequest(number, msgOrPutIn)
{
	// 폼 객체를 새로 생성함
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("id", "submitForm");
	form.setAttribute("action", "ProjectServ");
	
	// 바디에 새로 생성한 폼을 달아줌
 	document.body.appendChild(form);
	
	// 커맨드 엘리먼트를 생성함
	var command = document.createElement("input");
	command.setAttribute("type","hidden");
	command.setAttribute("name","command");
	
	// 투입요청 메시지는 true, 강제투입은 false
	// 검사를 한 후 커맨드 변수에 애트리뷰트를 각각에 맞는 값으로 생성해줌
	if(msgOrPutIn) command.setAttribute("value", "sendmessageandputinproc");
	else command.setAttribute("value", "sendmessageforputinrequestproc");
	
	// 메시지 작성자는 로그인된 직원의 아이디
	var empIdInput = document.createElement("input");
	empIdInput.setAttribute("type", "hidden");
	empIdInput.setAttribute("name", "empId");
	empIdInput.setAttribute("value", "${loginemp.empId}");
	
	// 이 창으로 넘어온 프로젝트 번호를 변수로 생성함 
	var projNumInput = document.createElement("input");
	projNumInput.setAttribute("type", "hidden");
	projNumInput.setAttribute("name", "curProjNum");
	projNumInput.setAttribute("value", "${projNum}");
	
	// 프로젝트 이름
	var projNameInput = document.createElement("input");
	projNameInput.setAttribute("type", "hidden");
	projNameInput.setAttribute("name", "curProjName");
	projNameInput.setAttribute("value", "${projName}");
	
	// length는 프리랜서 목록에 있는 프리랜서의 수
	var length = document.createElement("input");
	length.setAttribute("type","hidden");
	length.setAttribute("name","length");
	length.setAttribute("value", document.getElementsByName("putInFreeIdReal").length);
	
	// 인덱스는 함수 파라미터로 넘겨진 넘버
	var index = document.createElement("input");
	index.setAttribute("type", "hidden");
	index.setAttribute("name", "index");
	index.setAttribute("value", "" + number);
	
	// 새로 생성한 폼에 생성한 변수들을 자식으로 달아줌
	form.appendChild(command);
	form.appendChild(empIdInput);
	form.appendChild(projNumInput);
	form.appendChild(projNameInput);
	form.appendChild(length);
	form.appendChild(index);
	
	// 다수의 프리랜서 정보를 넘겨야 하기 때문에
	// 애트리뷰트 이름 뒤에 인덱스를 붙여주고 폼에 달아줌 
	for(var i = 0; i < document.getElementsByName("putInFreeIdReal").length; i++)
	{
		var a = document.createElement("input");
		a.setAttribute("type", "hidden");
		a.setAttribute("name", "putInFreeIds[" + i + "]");
		a.setAttribute("value", "" + document.getElementsByName("putInFreeIdReal")[i].value);
		form.appendChild(a);
	}
	for(var i = 0; i < document.getElementsByName("putInFreeIdReal").length; i++)
	{
		var b = document.createElement("input");
		b.setAttribute("type", "hidden");
		b.setAttribute("name", "putInFreeNames[" + i + "]");
		b.setAttribute("value", "" + document.getElementsByName("freeNames")[i].value);
		form.appendChild(b);
	}
	for(var i = 0; i < document.getElementsByName("putInFreeIdReal").length; i++)
	{
		var c = document.createElement("input");
		c.setAttribute("type", "hidden");
		c.setAttribute("name", "putInStartDate[" + i + "]");
		c.setAttribute("value", "" + document.getElementsByName("putInStartDate")[i].value);
		form.appendChild(c);
	}
	for(var i = 0; i < document.getElementsByName("putInFreeIdReal").length; i++)
	{
		var d = document.createElement("input");
		d.setAttribute("type", "hidden");
		d.setAttribute("name", "putInExitDate[" + i + "]");
		d.setAttribute("value", "" + document.getElementsByName("putInExitDate")[i].value);
		form.appendChild(d);
	}
	
	// 파라미터 값에 따라 얼럿 메시지를 다르게 줌
	if(msgOrPutIn) alert("투입되었습니다");
	else alert("투입 요청 메시지를 보냈습니다");
	
	form.submit();
}
</script>
</head>
<body>
	<form>
		<table border>
			<tr>
				<td>발신인</td>
				<td>${loginemp.empName}</td>
				<td>발신일</td>
				<td><script>document.write(getCurDate())</script></td>
			</tr>
			<tr>
				<td>프로젝트명</td>
				<td colspan="3">${projName}</td>
			</tr>
			<tr>
				<td>수신인</td>
				<td>투입일</td>
				<td colspan="2"></td>
			</tr>
			<%-- 이 페이지로 넘어온 투입 대상자들을 목록화해서 페이지에 뿌려줌 --%>
			<c:forEach var="list" items="${freeList}" varStatus="status">
				<tr>
					<td>
						${list.freeName}
						<input type="hidden" name="putInFreeIdReal" value="${list.freeId}">
						<input type="hidden" name="freeNames" value="${list.freeName}">
					</td>
					<td>
						<input type="text" class="datepicker" name="putInStartDate" value="${list.startDate}">
						~
						<input type="text" class="datepicker" name="putInExitDate" value="${list.endDate}">
					</td>
					<td colspan="2">
						<input type="button" value="요청전송" onclick="submitMessageRequest(${status.index}, false)">
						<input type="button" value="투입" onclick="submitMessageRequest(${status.index}, true)">
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4">
					<input type="button" value="확인" onclick="window.close()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
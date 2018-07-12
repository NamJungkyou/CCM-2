<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>투입 메시지 전송</title>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> 
<script>
$(function() {
  $( ".datepicker" ).datepicker({
    dateFormat: 'yy-mm-dd'
  });
});

function getCurDate()
{
    var currentdate = new Date();
    var month = (currentdate.getMonth() + 1) < 10 ? ("0" + (currentdate.getMonth() + 1)) : (currentdate.getMonth() + 1);
    var days = (currentdate.getDate()) < 10 ? ("0" + (currentdate.getDate())) : (currentdate.getDate());
    var datetime = currentdate.getFullYear() + "-"
            + month + "-"
            + days;
    return datetime;
}

function submitMessageRequest(number, msgOrPutIn)
{
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("id", "submitForm");
	form.setAttribute("action", "/Eureca/ProjectServ");
	
 	document.body.appendChild(form);
	
	var command = document.createElement("input");
	command.setAttribute("type","hidden");
	command.setAttribute("name","command");
	
	if(msgOrPutIn) command.setAttribute("value", "sendmessageandputinproc");
	else command.setAttribute("value", "sendmessageforputinrequestproc");
	
	var empIdInput = document.createElement("input");
	empIdInput.setAttribute("type", "hidden");
	empIdInput.setAttribute("name", "empId");
	empIdInput.setAttribute("value", "${loginemp.empId}");
	
	var projNumInput = document.createElement("input");
	projNumInput.setAttribute("type", "hidden");
	projNumInput.setAttribute("name", "curProjNum");
	projNumInput.setAttribute("value", "${projNum}");
	
	var projNameInput = document.createElement("input");
	projNameInput.setAttribute("type", "hidden");
	projNameInput.setAttribute("name", "curProjName");
	projNameInput.setAttribute("value", "${projName}");
	
	var length = document.createElement("input");
	length.setAttribute("type","hidden");
	length.setAttribute("name","length");
	length.setAttribute("value", document.getElementsByName("putInFreeIdReal").length);
	
	var index = document.createElement("input");
	index.setAttribute("type", "hidden");
	index.setAttribute("name", "index");
	index.setAttribute("value", "" + number);
	
	form.appendChild(command);
	form.appendChild(empIdInput);
	form.appendChild(projNumInput);
	form.appendChild(projNameInput);
	form.appendChild(length);
	form.appendChild(index);
	
	
	
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
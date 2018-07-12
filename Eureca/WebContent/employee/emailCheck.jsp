<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이메일 중복 체크</title>
<script type="text/javascript" src="script/employee.js"></script>
</head>
<body>
	<h2> 이메일 중복 확인 </h2>
	<form action="EmployeeServ" method="get" name="frm">
	<input type="hidden" name="command" value="email_Check">
	이메일주소 <input type="text" name="empEmail" value="${empEmail}">
	<input type="submit" value="중복 체크">
	<br>
	<c:if test="${result == 1}">
	<script type="text/javascript">
	opener.document.frm.empEmail.value="";
	</script>
	${empEmail}는 이미 사용 중인 이메일주소입니다.
	</c:if>
	<c:if test="${result==-1}">
	${empEmail}는 사용 가능한 이메일주소입니다.
	<input type="button" value="사용" onclick="emailok()">
	</c:if>
	</form>
</body>
</html>
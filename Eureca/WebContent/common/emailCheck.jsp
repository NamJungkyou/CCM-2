<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이메일 중복 체크</title>
<script type="text/javascript" src="script/join.js"></script>
</head>
<body>
	<h2> 이메일 중복 확인 </h2>
	<form action="CommonServ" method="get" name="frm">
	<input type="hidden" name="command" value="emailCheck">
	이메일 <input type="text" name="freeemail" value="${freeemail}">
	<input type="submit" value="중복 체크">
	<br>
	<c:if test="${result == 1}">
	<script type="text/javascript">
	opener.document.frm.freeemail.value="";
	</script>
	${freeemail}는 이미 사용 중인 아이디입니다.
	</c:if>
	<c:if test="${result==-1}">
	${freeemail}는 사용 가능한 아이디입니다.
	<input type="button" value="사용" onclick="emailok()">
	</c:if>
	</form>
</body>
</html>
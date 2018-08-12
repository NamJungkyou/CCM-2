<%--

	프리랜서가 회원가입을 할 때, 아이디 중복체크가 이루어지는 jsp
	
	작성자 : 글로벌IT경영 김민현
	
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 중복 체크</title>
<script type="text/javascript" src="script/join.js"></script>
</head>
<body>
	<h2> 아이디 중복 확인 </h2>
	<form action="CommonServ" method="get" name="frm">
	<input type="hidden" name="command" value="idCheck">
	아이디 <input type="text" name="freeid" value="${freeid}">
	<input type="submit" value="중복 체크">
	<br>
	<!-- 중복된 아이디가 있을때 -->
	<c:if test="${result == 1}">
	<script type="text/javascript">
	opener.document.frm.freeid.value="";
	</script>
	${freeid}는 이미 사용 중인 아이디입니다.
	</c:if>
	<!-- 중복된 아이디가 없을때 -->
	<c:if test="${result==-1}">
	${freeid}는 사용 가능한 아이디입니다.
	<!-- 중복검사가 끝난 freeid값은 idCheck.jsp 페이지가 닫히고 기존에 있던 jsp페이지에 freeid값이 넘어감 -->
	<input type="button" value="사용" onclick="idok()">
	</c:if>
	</form>
</body>
</html>
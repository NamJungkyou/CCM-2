<%--

	관리자가 프리랜서 계쩡등록을 할 때, 아이디 중복체크가 이루어지는 jsp
	
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
<script type="text/javascript" src="script/employee.js"></script>
</head>
<body>
	<h2> 아이디 중복 확인 </h2>
	<form action="EmployeeServ" method="get" name="frm">
	<input type="hidden" name="command" value="id_Check">
	아이디 <input type="text" name="empId" value="${empId}">
	<input type="submit" value="중복 체크">
	<br>
	<!-- 중복된 아이디가 있을때 -->
	<c:if test="${result == 1}">
	<script type="text/javascript">
	opener.document.frm.empId.value="";
	</script>
	${empId}는 이미 사용 중인 아이디입니다.
	</c:if>
	<!-- 중복된 아이디가 없을때 -->
	<c:if test="${result==-1}">
	${empId}는 사용 가능한 아이디입니다.
	<!-- 중복검사가 끝난 empId값은 idCheck.jsp 페이지가 닫히고 기존에 있던 jsp페이지에 empId값이 넘어감 -->
	<input type="button" value="사용" onclick="idok()">
	</c:if>
	</form>
</body>
</html>
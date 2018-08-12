<%--

	관리자가 프리랜서 계정등록을 할 때, 휴대폰 중복체크가 이루어지는 jsp
	
	작성자 : 글로벌IT경영 김민현
	
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>휴대폰 중복 체크</title>
<script type="text/javascript" src="script/freelancer.js"></script>
</head>
<body>
	<h2> 휴대폰 중복 확인 </h2>
	<form action="FreelancerServ" method="get" name="frm">
	<input type="hidden" name="command" value="phone_Check">
	휴대폰번호 <input type="text" name="freePhone" value="${freePhone}">
	<input type="submit" value="중복 체크">
	<br>
	<!-- 중복된 휴대폰이 있을때 -->
	<c:if test="${result == 1}">
	<script type="text/javascript">
	opener.document.frm.freePhone.value="";
	</script>
	${freePhone}는 이미 사용 중인 휴대폰번호입니다.
	</c:if>
	<!-- 중복된 휴대폰이 없을때 -->
	<c:if test="${result==-1}">
	${freePhone}는 사용 가능한 휴대폰번호입니다.
	<!-- 중복검사가 끝난 freePhone값은 idCheck.jsp 페이지가 닫히고 기존에 있던 jsp페이지에 freePhone값이 넘어감 -->
	<input type="button" value="사용" onclick="phoneok()">
	</c:if>
	</form>
</body>
</html>
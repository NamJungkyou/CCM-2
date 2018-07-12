<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지 메인</title>
</head>
<body>
	<jsp:include page="/frame/header.jsp" />
	<div>
		<!-- 계정목록 -->
		<table align="center" border="1">
			<c:forEach var="emp" items="${empList}">
				<tr class="record">
					<th>아이디</th>
					<td>${emp.eid}</td>
					<th>이름</th>
					<td><a href="Eu?c=emp_view&cno=${emp.eid}">
							${emp.ename} </a></td>
					<th>부서</th>
					<td>${emp.edept}</td>
					<th>직책</th>
					<td>${emp.eclass}</td>
				</tr>
				<tr class="record">
					<th>입사일</th>
					<td>${emp.joindate}</td>
					<th>전화번호</th>
					<td>${emp.phone}</td>
					<th>이메일</th>
					<td>${emp.email}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="/frame/footer.jsp" />
</body>
</html>
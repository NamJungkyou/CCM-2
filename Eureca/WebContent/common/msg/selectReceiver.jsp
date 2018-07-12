<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script/message.js?ver=2"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수신자 검색</title>
</head>
<body>
	<form name="selectReceiverFrm" method="post">
		<table align="center" border="1">
			<c:choose>
				<c:when test="${freeList != null}">
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>Kosa등급</th>
						<th>회원등급</th>
					</tr>
					<c:forEach var="free" items="${freeList}">
						<tr>
							<td>${free.freeId}</td>
							<td>
								<a href="#" onclick="chooseReceiverFree('${free.freeId}', '${free.freeName}')">
									${free.freeName}
								</a>
									<input type="hidden" id="freeReceiver" value="${free.freeName}">
									<input type="hidden" id="freeReceiverId" value="${free.freeId}">
							</td>
							<td>${free.freeKosa}</td>
							<td>${free.freeClass}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:when test="${empList != null}">
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>부서</th>
						<th>직책</th>
					</tr>
					<c:forEach var="emp" items="${empList}" varStatus="status">
						<tr>
							<td>${emp.empId}</td>
							<td>
								<a href="#" onclick="chooseReceiverEmp('${emp.empId}', '${emp.empName}')">
									${emp.empName}
								</a>
									<input type="hidden" id="empReceiver" value="${emp.empName}">
									<input type="hidden" id="empReceiverId" value="${emp.empId}">
							</td>
							<td>${emp.empDept}</td>
							<td>${emp.empDuty}</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
	</form>
</body>
</html>
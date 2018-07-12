<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>투입 메시지</title>
<script type="text/javascript" src="script/message.js" charset="UTF-8"></script>
</head>
<body>
<c:set var="now" value="<%= new java.util.Date() %>" />
	<div>
		<form method="post" name="frm">
			<table align="center" border="1">
				<tr>
					<th colspan="4">
						<img class="logo" src="common/img/eureka.PNG"/>
						투입요청보내기
					</th>
				</tr>
				<tr>
					<th>발신인</th>
					<td>
						<input type="text" name="empName" readonly="readonly" value="${emp.empName}">
						<input type="hidden" name="empWriter" value="${emp.empId}">	
					</td>
					<th>발신일</th>
					<td><fmt:formatDate value="${now}" pattern="yyyy. MM. dd"/></td>
				</tr>
				<tr>
					<th>프로젝트명</th>
					<td colspan="3">
						<input type="text" name ="projName" value="${project.projName}">
						<input type="hidden" name="projNum" value="${project.projNum}">
					</td>
				</tr>
				<tr>
					<th>수신인</th>
					<th colspan="3">투입일</th>
				</tr>
				<c:forEach var="joinproj" items="${joinProjList}" varStatus="status">
					<tr>
						<td>
							<input type="hidden" name="joinNum" value="${joinproj.joinNum}">
							<input type="text" value="${free[status.index].freeName}">
							<input type="hidden" name="free" value="${free[status.index].freeId}">
						</td>
						<td colspan="2">
							<input type="date" name = "joinDate" value="${project.projStartDate}">
							&nbsp;~&nbsp;
							<input type="date" name = "dropDate" value="${project.projEndDate}">
						</td>
						<!-- <td>
							<input type="button" name="" value="요청전송" onclick="">
							<input type="button" name="input" value="투입" onclick="">
						</td> -->
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" align="center">
						<input type="submit" value="투입하기" onclick="sendInputMsg(); return false;">
						<input type="button" value="취소" onclick="self.close();">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
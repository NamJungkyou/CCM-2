<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 상세보기</title>
</head>
<body>
<c:set var="now" value="<%= new java.util.Date() %>" />
	<table align="center" border="1">
		<tr>
			<td colspan="7">
				${project.projName}
				(등록일 : <fmt:formatDate value="${project.projRegisterDate}" pattern="yyyy. MM. dd"/>)
			</td>
			<td>
				<c:choose>
					<c:when test="${project.projStartDate > now}"><label>대기중</label></c:when>
					<c:when test="${project.projRecruitEndDate <= now}"><label>진행중</label></c:when>
				</c:choose>
				<c:choose>
					<c:when test="${project.projRecruitEndDate > now}"><label>모집중</label></c:when>
					<c:when test="${project.projRecruitEndDate == now}"><label>당일마감</label></c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
				<th>개발분야</th>
				<td>${project.projDevelopSort}</td>
				<th>시작일</th>
				<td><fmt:formatDate value="${project.projStartDate}" pattern="yyyy. MM. dd"/></td>
				<th>예상기간</th>
				<td>${project.projExpectedTime}일</td>
				<th>종료예정일</th>
				<td><fmt:formatDate value="${project.projEndDate}" pattern="yyyy. MM. dd"/></td>
			</tr>
			<tr>
				<th>모집인원</th>
				<td>${project.requirePeople}명</td>
				<th>고객사</th>
				<td colspan="5">${project.projTarget}</td>
			</tr>
			<tr>
				<th>DBMS</th>
				<td>${project.dbName}</td>
				<th>언어</th>
				<td colspan="2">${project.languages}</td>
				<th>TOOL/<br>FRAMEWORK</th>
				<td colspan="2">${project.frames}</td>
			</tr>
			<tr>
				<td colspan="8">
					<textarea rows="15" cols="105" readonly="readonly">${project.projPlan}</textarea>
				</td>
			</tr>
			<tr>
				<td align="right" colspan="8">
					<input type="button" value="참여신청" onclick="">
				</td>
			</tr>
	</table>
</body>
</html>
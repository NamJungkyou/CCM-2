<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="script/message.js?ver=1"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 선택</title>
<style type="text/css">
td{
	text-align: center;
}
</style>
</head>
<body>
		<table align="center" border="1">
			<tr>
				<th>번호</th>
				<th>프로젝트 이름</th>
				<th>프로젝트 상태</th>
				<th>프로젝트 유형</th>
				<th>개발 유형</th>
				<th>고객사</th>
				<th>프로젝트 시작일</th>
				<th>프로젝트 종료일</th>
			</tr>
		<c:forEach var="joinProj" items="${joinProjList}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>
					<a href="#" onclick="chooseProject('${joinProj.projNum}','${joinProj.projName}')">
						<input type="hidden" id="projNum" value="${joinProj.projNum}">
						<input type="hidden" id="projName" value="${joinProj.projName}">
						${joinProj.projName}
					</a>
				</td>
				<td>${joinProj.projState}</td>
				<td>${joinProj.projField}</td>
				<td>${joinProj.projDevelopSort}</td>
				<td>${joinProj.projTarget}</td>
				<td>${joinProj.projStartDate}</td>
				<td>${joinProj.projEndDate}</td>
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>
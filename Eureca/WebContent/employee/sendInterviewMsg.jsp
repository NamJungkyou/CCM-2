<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="script/interview.js?ver=1"
	charset="UTF-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="common/css/style.css">
<title>면접일 지정</title>
</head>
<body>
	<jsp:include page="/frame/popup_header.jsp" />
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<section id="container" class="sidebar-closed">
		<section id="main-content">
				<form method="post" name="frm">
					<section class="panel">
						<header class="panel-heading no-border"> 면접일 지정 </header>
						<table class="table table-bordered">
							<tr>
								<th>날짜</th>
								<td><input type="date" name="interviewDate" id="date"></td>
								<th>시간</th>
								<td><input type="time" name="interviewTime" id="time"></td>
							</tr>
							<tr>
								<th>장소</th>
								<td colspan="3"><input type="text" name="location"
									id="location"></td>
							</tr>
							<tr>
								<th>알림말</th>
								<td colspan="3"><textarea name="content" rows="10"
										cols="50" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan="4" align="center"><c:forEach var="join"
										items="${joinProjList}">
										<input type="hidden" name="joinNum" value="${join.joinNum}">
									</c:forEach> <input type="submit" value="확인"
									onclick="sendAppointmentMsg(); return false;"> <input
									type="button" value="취소" onclick="self.close();"></td>
							</tr>
						</table>
					</section>
				</form>
		</section>
	</section>
	<jsp:include page="/frame/popup_footer.jsp" />
</body>
</html>
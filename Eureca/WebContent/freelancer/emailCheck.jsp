테스트
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이메일 중복 체크</title>
<script type="text/javascript" src="script/freelancer.js"></script>
</head>
<body>
	<jsp:include page="/frame/popup_header.jsp" />
	<section id="container" class="sidebar-closed">
		<section id="main-content">
			<form action="FreelancerServ" method="get" name="frm">
				<section class="panel">
					<header class="panel-heading no-border"> 이메일 중복 확인 </header>
					<table class="table table-bordered">
						<tr>
							<th><input type="hidden" name="command" value="email_Check">
								이메일주소</th>
							<td><input type="text" name="freeEmail" value="${freeEmail}">
								<input type="submit" value="중복 체크"></td>
							<c:if test="${result == 1}">
								<tr>
									<td colspan="2"><script type="text/javascript">
										opener.document.frm.freeEmail.value = "";
									</script> ${freeEmail}는 이미 사용 중인 이메일주소입니다.</td>
								</tr>
							</c:if>
							<c:if test="${result==-1}">
								<tr>
									<td colspan="2">${freeEmail}는 사용 가능한 이메일주소입니다. <input
										type="button" value="사용" onclick="emailok()">
									</td>
								</tr>
							</c:if>
					</table>
				</section>
			</form>
		</section>
	</section>
	<jsp:include page="/frame/popup_footer.jsp" />
</body>
</html>
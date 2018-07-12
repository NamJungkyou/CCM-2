<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>휴대폰 중복 체크</title>
<script type="text/javascript" src="script/employee.js"></script>
</head>
<body>
	<jsp:include page="/frame/popup_header.jsp" />
	<section id="container" class="sidebar-closed">
		<section id="main-content">
			<form action="EmployeeServ" method="get" name="frm">
				<input type="hidden" name="command" value="phone_Check">
				<section class="panel">
					<header class="panel-heading no-border"> 휴대폰 중복 확인 </header>
					<table class="table table-bordered">
						<tr>
							<th>휴대폰번호</th>
							<td><input type="text" name="empPhone" value="${empPhone}">
								<input type="submit" value="중복 체크"></td>
							<c:if test="${result == 1}">
								<tr>
									<td colspan="2"><script type="text/javascript">
										opener.document.frm.empPhone.value = "";
									</script> ${empPhone}는 이미 사용 중인 휴대폰번호입니다.</td>
								</tr>
							</c:if>
							<c:if test="${result==-1}">
								<tr>
									<td colspan="2">${empPhone}는 사용 가능한 휴대폰번호입니다. <input
										type="button" value="사용" onclick="phoneok()">
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
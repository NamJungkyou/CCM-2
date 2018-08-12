<%--

	관리자가 프리랜서 계정등록을 할 때, 휴대폰 중복체크가 이루어지는 jsp
	
	회원가입이 아닌 프리랜서 추가정보를 등록하거나 수정할때, 따로 중복체크를 해야하는건 아니지만 휴대폰 값이 unique로 설정되어있어 입력값 테스트를 위해 편의상 만들어놓은 jsp
	
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
							<!-- 중복된 휴대폰이 있을때 -->
							<c:if test="${result == 1}">
								<tr>
									<td colspan="2"><script type="text/javascript">
										opener.document.frm.empPhone.value = "";
									</script> ${empPhone}는 이미 사용 중인 휴대폰번호입니다.</td>
								</tr>
							</c:if>
							<!-- 중복된 휴대폰이 없을때 -->
							<c:if test="${result==-1}">
								<tr>
									<td colspan="2">${empPhone}는 사용 가능한 휴대폰번호입니다. 
									<!-- 중복검사가 끝난 empPhone값은 idCheck.jsp 페이지가 닫히고 기존에 있던 jsp페이지에 empPhone값이 넘어감 -->
									<input type="button" value="사용" onclick="phoneok()">
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
<%--

	프리랜서가 추가정보를 등록하고 수정할 때, 이메일 중복체크가 이루어지는 jsp
	
	회원가입이 아닌 프리랜서 추가정보를 등록하거나 수정할때, 따로 중복체크를 해야하는건 아니지만 이메일 값이 unique로 설정되어있어 입력값 테스트를 위해 편의상 만들어놓은 jsp
	
	작성자 : 글로벌IT경영 김민현
	
 --%>
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
							<!-- 중복된 이메일이 있을때 -->
							<c:if test="${result == 1}">
								<tr>
									<td colspan="2"><script type="text/javascript">
										opener.document.frm.freeEmail.value = "";
									</script> ${freeEmail}는 이미 사용 중인 이메일주소입니다.</td>
								</tr>
							</c:if>
							<!-- 중복된 이메일이 없을때 -->
							<c:if test="${result==-1}">
								<tr>
								<td colspan="2">${freeEmail}는 사용 가능한 이메일주소입니다. 
								<!-- 중복검사가 끝난 freeemail값은 idCheck.jsp 페이지가 닫히고 기존에 있던 jsp페이지에 freeemail값이 넘어감 -->
								<input type="button" value="사용" onclick="emailok()">
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
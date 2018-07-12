<%-- 

	로그인 페이지
	
	작성자 : 

	수정일 : 
	
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body class="login-img3-body">
	<jsp:include page="/frame/header.jsp"></jsp:include>

<div class="banner-dott">
	<div class="container">
		<form method="post" action="CommonServ" class="login-form">
			<input type="hidden" name="command" value="loginproc">
			<div class="login-wrap">
				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" class="form-control" name="feid" placeholder="아이디" autofocus>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" class="form-control" name="fepw" placeholder="비밀번호">
				</div>
				<span class="pull-right">
						<a href="#"> 아이디/비밀번호 찾기</a>
				</span>
				
				<button class="btn btn-primary btn-lg btn-block" type="submit">로그인</button>
				<input class="btn btn-info btn-lg btn-block" type="button"
					value="회원가입" onclick="location.href='CommonServ?command=join_form'">
			</div>
		</form>
		</div>
	</div>

	<jsp:include page="/frame/footer.jsp"></jsp:include>
</body>
</html>
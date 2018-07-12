<%-- 

	회원가입 페이지
	
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
<title>회원가입</title>
<script type="text/javascript" src="/Eureca/script/join.js"></script>
</head>
<body class="join-body">
	<jsp:include page="/frame/header.jsp"></jsp:include>

	<!-- 회원가입
	<form name="frm" method="post" action="CommonServ">
	<input type="hidden" name="command" value="join">
		<table>
			<tr>
				<td>
					아이디
				</td>
				<td>
					<input type="text" name="freeId">
				</td>
			</tr>
			<tr>
				<td>
					이메일
				</td>
				<td>
					<input type="text" name="email_front">
					@
					<input type="text" name="email_rear">
				</td>
			</tr>
			<tr>
				<td>
					비밀번호
				</td>
				<td>
					<input type="password" name="freePw">
				</td>
			</tr>
			<tr>
				<td>
					비밀번호 확인
				</td>
				<td>
					<input type="password" name="freePw2">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" name="agree">
					이용약관 및 개인정보 이용방침에 동의합니다 시발
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="CommonServ?command=freelancer_Profile">등록가자잇!</a>
		 <input type="submit" value="회원가입" onclick="return joinCheck()">
	   
	    <input type="button" value="회원가입2" onclick="location.href='/FMS/JoinServ?command=join'">
				</td>
			</tr>
		</table>
	</form> -->

	<!-- <div class="container">
		<div class="register">
			<form name="frm" method="post" action="CommonServ" class="join-form">
				<input type="hidden" name="command" value="join">
				<div class="register-top-grid">
					<h3>회원가입</h3>
					<div>
						<span>아이디<label>*</label></span> <input type="text">
					</div>
					<div>
						<span>Email<label>*</label></span> <input type="text">
					</div>
					<div>
						<span>비밀번호<label>*</label></span> <input type="text">
					</div>
					<div>
						<span>비밀번호 확인<label>*</label></span> <input type="text">
					</div>
					<div class="clearfix"></div>
				</div>

				<div class="clearfix"></div>
				<div class="register-but">
					<input type="submit" value="가입">
					<div class="clearfix"></div>
				</div>
			</form>
		</div>
	</div> -->

	<div class="center-container">
		<div class="banner-dott">
			<div class="main">
				<h1 class="w3layouts_head">회원가입</h1>
				<div class="w3layouts_main_grid">
					<form name="frm" method="post" action="CommonServ"
						class="w3_form_post">
						<input type="hidden" name="command" value="join">
						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid"> <label>아이디</label> <input
								type="text" name="freeId" placeholder="ID" required="">
							</span>
						</div>
						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid"> <label>이메일</label> <input
								type="text" name="email_front" placeholder="Email" required="">
							</span>
						</div>
						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid"> <label>비밀번호</label> <input
								type="password" name="freePw" placeholder="Password" required="">
							</span>
						</div>
						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid"> <label>비밀번호 확인</label> <input
								type="password" name="freePw2" placeholder="Password Check"
								required="">
							</span>
						</div>
						<div class="w3_main_grid">
							<div class="w3_main_grid_right">
								<input type="submit" value="가입" onclick="return joinCheck()">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/frame/footer.jsp"></jsp:include>
</body>
</html>
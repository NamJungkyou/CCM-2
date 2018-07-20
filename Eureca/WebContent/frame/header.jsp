<%-- 

	헤더
	
	작성자 : 남정규

	수정일 : 2018.05.24
	
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">

<title>헤더</title>

<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!-- full calendar css-->
<link href="assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css"
	rel="stylesheet" />
<link href="assets/fullcalendar/fullcalendar/fullcalendar.css"
	rel="stylesheet" />
<!-- easy pie chart-->
<link href="assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
<!-- owl carousel -->
<link rel="stylesheet" href="css/owl.carousel.css" type="text/css">
<link href="css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
<!-- Custom styles -->
<link rel="stylesheet" href="css/fullcalendar.css">
<link href="css/widgets.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />
<link href="css/xcharts.min.css" rel=" stylesheet">
<link href="css/jquery-ui-1.10.4.min.css" rel="stylesheet">
<!-- =======================================================
    Theme Name: NiceAdmin
    Theme URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
    Author: BootstrapMade
    Author URL: https://bootstrapmade.com
  ======================================================= -->
</head>

<body>
	<!-- container section start -->
	<section id="container" class="">
		<header class="header white-bg">
			<div class="toggle-nav">
				<div class="icon-reorder tooltips"
					data-original-title="Toggle Navigation" data-placement="bottom">
					<i class="icon_menu"></i>
				</div>
			</div>
			<div id="top-line"></div>
			<!--logo start-->
			<a href="index.jsp" class="logo"><img src="./img/logo.png"></a>
			<!--logo end-->
			<c:choose>
				<c:when test="${loginemp != null}">
					<div class="top-nav menu-row">
						<ul class="nav puul-right top-menu">
							<li><a href="ProjectServ?command=project_insert">프로젝트 관리</a></li>
							<li><a href="EmployeeServ?command=freelancer_Profile_insert">프리랜서 관리</a></li>
							<li><a href="EmployeeServ?command=employee_Profile_insert_form">계정관리</a></li>
							<li><a href="CommonServ?command=show_message">메세지 작성</a></li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<div class="top-nav menu-row">
						<ul class="nav puul-right top-menu">
								<li><a href="FreelancerServ?command=freelancer_Profile">프로필 등록</a></li>
							<!-- <li><a href="ProjectServ?command=project_list">프로젝트 관리</a></li> -->
							<li><a href="FreelancerServ?command=freelancer_join_project">프로젝트 참여신청</a></li>
							<li><a href="FreelancerServ?command=freelancer_myPage">My페이지</a></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>

			<div class="top-nav notification-row">
				<ul style="margin-top: 7px; margin-right: 20px;">
					<li><c:choose>
							<%--로그인 상태가 아니면 --%>
							<c:when test="${loginfree == null && loginemp == null}">
								<a href="CommonServ?command=login">로그인</a> / <a
									href="CommonServ?command=join_form">회원가입</a>
							</c:when>
							<c:otherwise>
								<a href="CommonServ?command=logoutproc">로그아웃</a>
							</c:otherwise>
						</c:choose></li>
				</ul>
			</div>
		</header>
		<!--header end-->
		<%-- 로그인 상태면 미니프로필 표시 --%>
		<c:choose>
			<c:when test="${loginfree == null && loginemp == null}">
				<%-- 로그인상태 검사 --%>
				<%-- 로그인상태가 아니면 프로필 출력안됨 --%>
			</c:when>
			<c:otherwise>
				<%-- 로그인상태면 미니프로필 받아오면된다 --%>
				<jsp:include page="miniprof.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
	</section>

	
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>내 프로필</title>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="css/dropdown.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/frame/header.jsp"></jsp:include>

	<section id="container" class="">
		<section id="main-content">
			<section class="wrapper">
				<form name="frm" method="post" action="FreelancerServ">
					<input type="hidden" name="command" value="freelancer_myPage">
					<section class="panel">
						<header class="panel-heading no-border"> 참여중인 프로젝트 목록 </header>
						<table class="table table-bordered">
							<c:forEach var="jpViewList" items="${jpViewList}" varStatus="status">
								<tr>
									<th colspan="6">${jpViewList.projName}  ${jpViewList.projState}</th>
								</tr>
								<tr>
									<th>시작일</th>
									<td>${jpViewList.projStartDate}</td>
									<th>투입일</th>
									<td>${jpViewList.joinProjDate}</td>
									<th>참여인원</th>
									<td>${jpViewList.joinFLCount}/
										${jpViewList.requireCount}</td>
								</tr>
								<tr>
									<th>내용</th>
									<td>${jpViewList.projPlan}</td>
								</tr>
							</c:forEach>
						</table>
					</section>
				</form>
			</section>
		</section>
	</section>

	<jsp:include page="/frame/footer.jsp"></jsp:include>
	<script type="text/javascript" src="/Eureca/script/dropdown.js"></script>
	<script type="text/javascript" src="/Eureca/script/freelancer.js"></script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 찾기</title>
</head>
<body>
	<%-- <jsp:include page="/frame/header.jsp"></jsp:include> --%>
	<!-- 헤더 아래쪽부터 감싸는 컨테이너 -->
	<section id="container" class="">
		<section id="main-content">
			<section class="wrapper">

				<form method="post" action="ProjectServ" id="frm" name="frm">
					<input type="hidden" name="command"
						value="gotoputinofsearchproject">
					<div class="col-lg-8">
						<section class="panel">
							<header class="panel-heading no-border"> 프로젝트 검색 </header>
							<table class="table table-bordered align-middle">
								<tr>
									<th>프로젝트 명</th>
									<td><input type="text" name="projNameSearch"
										class="col-lg-8" value="${projName}"></td>
								</tr>
								<tr>
									<th>개발분야</th>
									<td><ul class="table-custom col-lg-12">
											<c:set var="devCheck" value="" />
											<c:forEach var="devName" items="${devCount}">
												<c:choose>
													<c:when test="${devName eq '웹'}">
														<c:set var="devCheck1" value="checked" />
													</c:when>
													<c:when test="${devName eq '안드로이드'}">
														<c:set var="devCheck2" value="checked" />
													</c:when>
													<c:when test="${devName eq '엠베디드'}">
														<c:set var="devCheck3" value="checked" />
													</c:when>
													<c:when test="${devName eq '응용프로그램'}">
														<c:set var="devCheck4" value="checked" />
													</c:when>
													<c:when test="${devName eq '안드로이드2'}">
														<c:set var="devCheck5" value="checked" />
													</c:when>
												</c:choose>
											</c:forEach>
											<li><input type="checkbox" name="devFieldSearch"
												value="웹" ${devCheck1}><label for="웹">웹</label></li>
											<li><input type="checkbox" name="devFieldSearch"
												value="안드로이드" ${devCheck2}><label for="안드로이드">안드로이드</label></li>
											<li><input type="checkbox" name="devFieldSearch"
												value="엠베디드" ${devCheck3}><label for="엠베디드">엠베디드</label></li>
											<li><input type="checkbox" name="devFieldSearch"
												value="응용프로그램" ${devCheck4}><label for="응용프로그램">응용프로그램</label></li>
											<li><input type="checkbox" name="devFieldSearch"
												value="안드로이드2" ${devCheck5}><label for="안드로이드2">안드로이드2</label></li>
										</ul>
								</tr>
								<tr>
									<th>사용언어</th>
									<td>
										<ul class="table-custom col-lg-12">
											<!-- 언어 리스트 출력 -->
											<c:forEach var="langIter" items="${langList}">
												<!-- 검색 후 선택했던 체크박스가 해제 되지 않도록 하기 위해 체크박스 값을 담는 변수-->
												<c:set var="langCheck" value="" />
												<!-- 선택했던 검색옵션(langCount)을 다시 받아와 
													langList의 언어명과 langCount의 언어명이 같으면 checked  -->
												<c:forEach var="langName" items="${langCount}">
													<c:if test="${langName eq langIter.langName}">
														<c:set var="langCheck" value="checked" />
													</c:if>
												</c:forEach>
												<li><input type="checkbox" name="progLangSearch"
													value="${langIter.langName}" ${langCheck}
													id="${langIter.langName}"><label
													for="${langIter.langName}">${langIter.langName}</label> <c:set
														var="langCheck" value="" /></li>
											</c:forEach>
										</ul>
									</td>
								</tr>
								<tr>
									<th>DBMS</th>
									<td>
										<ul class="table-custom col-lg-12">
											<!-- 데이터베이스 리스트 출력 -->
											<c:forEach var="DBMSIter" items="${dbmsList}">
												<!-- 검색 후 선택했던 체크박스가 해제 되지 않도록 하기 위해 체크박스 값을 담는 변수-->
												<c:set var="langCheck" value="" />
												<!-- 선택했던 검색옵션(dbCount)을 다시 받아와 
													dbmsList의 DB명과 dbCount의 DB명이 같으면 checked   -->
												<c:forEach var="dbName" items="${dbCount}">
													<c:if test="${dbName eq DBMSIter.dbName}">
														<c:set var="langCheck" value="checked" />
													</c:if>
												</c:forEach>
												<li><input type="checkbox" name="DBMSSearch"
													value="${DBMSIter.dbName}" ${langCheck}
													id="${DBMSIter.dbName}"><label
													for="${DBMSIter.dbName}">${DBMSIter.dbName}</label> <c:set
														var="langCheck" value="" /></li>
											</c:forEach>

										</ul>
									</td>
								</tr>
								<tr>
									<th>TOOL/framework</th>
									<td><ul class="table-custom col-lg-12">
									<!-- 프레임워크 리스트 출력 -->
											<c:forEach var="tfwIter" items="${frameList}">
											<!-- 검색 후 선택했던 체크박스가 해제 되지 않도록 하기 위해 체크박스 값을 담는 변수-->
												<c:set var="langCheck" value="" />
												<!-- 선택했던 검색옵션(tfwCount)을 다시 받아와 
													frameList의 DB명과 tfwCount의 DB명이 같으면 checked   -->
												<c:forEach var="frameName" items="${tfwCount}">
													<c:if test="${frameName eq tfwIter.frameName}">
														<c:set var="langCheck" value="checked" />
													</c:if>
												</c:forEach>
												<li><input type="checkbox" name="TOOLfwSearch"
													value="${tfwIter.frameName}" ${langCheck}
													id="${tfwIter.frameName}"><label
													for="${tfwIter.frameName}">${tfwIter.frameName}</label> <c:set
														var="langCheck" value="" /></li>
											</c:forEach>
										</ul></td>
								</tr>
								<tr>
									<th>기간</th>
									<td><input type="text" class="datepicker" name="period1"
										value="${time1}"> - <input type="text"
										class="datepicker" name="period2" value="${time2}"></td>
								<tr>
									<td colspan="2" class="align-right"><input type="submit"
										value="검색"></td>
								</tr>
							</table>
						</section>
					</div>
					<div class="clear"></div>
					<div class="col-lg-8" id="projectSearch">
						<section class="panel">
							<header class="panel-heading">
								프로젝트 목록 <select name="order" onchange="onSubmit()">
								<!-- 정렬기준 선택 후에 선택했던 옵션이 초기화 되지 않도록 
										order을 다시 받아와 각 기준과 비교하고 selected 설정 -->
									<option value="" ${order eq "" ? "selected" :""}>선택하세요</option>
									<option value="등록일" ${order eq "등록일" ? "selected" :""}>등록일</option>
									<option value="시작일" ${order eq "시작일" ? "selected" :""}>시작일</option>
									<option value="종료(예정)일" ${order eq "종료(예정)일" ? "selected" :""}>종료(예정)일</option>
									<option value="남은기간" ${order eq "남은기간" ? "selected" :""}>남은기간</option>
								</select>
							</header>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>상태</th>
										<th>프로젝트번호</th>
										<th>프로젝트명</th>
										<th>개발분야</th>
										<th>참여인원</th>
										<th>시작일</th>
										<th>종료(예정)일</th>
										<th>남은기간</th>
										<th>이동</th>
									</tr>
								</thead>
								<!-- 검색결과 출력 -->
								<c:forEach var='projViewList' items="${projViewList}"
									varStatus="status">
									<tr style="cursor: pointer;">
										<td
											onclick="projNumSelect('${projViewList.projNum}','${pageNum}')">${projViewList.projState}<input
											type="hidden" name="projState"
											value="${projViewList.projState}"></td>
										<td
											onclick="projNumSelect('${projViewList.projNum}','${pageNum}')">${projViewList.projNum}<input
											type="hidden" name="projNum" id="projNum"
											value="${projViewList.projNum}" disabled></td>
										<td
											onclick="projNumSelect('${projViewList.projNum}','${pageNum}')">${projViewList.projName}<input
											type="hidden" name="projName"
											value="${projViewList.projName}"></td>
										<td
											onclick="projNumSelect('${projViewList.projNum}','${pageNum}')">${projViewList.projField}<input
											type="hidden" name="projField"
											value="${projViewList.projField}"></td>
										<td
											onclick="projNumSelect('${projViewList.projNum}','${pageNum}')">${projViewList.joinFLCount}<input
											type="hidden" name="joinFLCount"
											value="${projViewList.joinFLCount}"></td>
										<td
											onclick="projNumSelect('${projViewList.projNum}','${pageNum}')">${projViewList.projStartDate}<input
											type="hidden" name="projStartDate"
											value="${projViewList.projStartDate}"></td>
										<td
											onclick="projNumSelect('${projViewList.projNum}','${pageNum}')">${projViewList.expectedEndDate}<input
											type="hidden" name="expectedEndDate"
											value="${projViewList.expectedEndDate}"></td>
										<td
											onclick="projNumSelect('${projViewList.projNum}','${pageNum}')">${projViewList.remainDays}<input
											type="hidden" name="remainDays"
											value="${projViewList.remainDays}"></td>
											<!-- 셀을 클릭하면 프리랜서 투입리스트로 프로젝트 정보와 같이 이동  -->
										<td><input type="button" value="이동"
											onclick="selectProject(${projViewList.projNum})"></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="8"></td>
								</tr>
								<tr>
									<td colspan="8" class="align-center"><a href="#"
										onclick="projPrePageSelect('${pagenNum}')">이전</a>
										<!-- 페이지 번호를 첫 페이지부터 끝 페이지까지 순차적으로 출력 -->
										 <c:forEach
											var="pageNumIter" begin="${firstPage}" end="${lastPage}">
											<input type="hidden" name="pageNum" value="${pageNumIter}">
											<a href="#"
												onclick="projPageSelect('${projNum}', '${pageNumIter}')">
												${pageNumIter} </a>
										</c:forEach> <a href="#" onclick="projNextPageSelect('${pageNum}')">다음</a></td>
								</tr>
							</table>
						</section>
					</div>

					<div class="clear"></div>
					<div class="col-lg-8">
						<section class="panel details-background">
							<header class="panel-heading no-border">
								상세보기
								<!-- 선택한 프로젝트의 정보를 출력 --> 
								<span>등록인 : ${pSelectViewList.projRegisterer}<input
									type="hidden" name="r_projRegisterer2"
									value="${pSelectViewList.projRegisterer}">
								</span> <span>등록일 : ${pSelectViewList.projRegisterDate}<input
									type="hidden" name="r_projNum"
									value="${pSelectViewList.projRegisterDate}"></span> <span>
									<input type="button" value="이동"
									onclick="selectProject(${pSelectViewList.projNum})"
									style="float: right;">
								</span>
							</header>
							<table class="table table-bordered details-background">
								<tr>
									<th>번호</th>
									<td>${pSelectViewList.projNum}<input type="hidden"
										name="r_projNum" value="${pSelectViewList.projNum}"></td>
									<th>등록인</th>
									<td>${pSelectViewList.projRegisterer}<input type="hidden"
										name="r_projRegisterer2"
										value="${pSelectViewList.projRegisterer}"></td>
									<th>등록일</th>
									<td colspan="2">${pSelectViewList.projRegisterDate}<input
										type="hidden" name="r_projRegisterDate"
										value="${pSelectViewList.projRegisterDate}">
									</td>
								</tr>
								<tr>
									<th>프로젝트명</th>
									<td colspan="3">${pSelectViewList.projName}<input
										type="hidden" name="r_projName"
										value="${pSelectViewList.projName}"></td>
									<th>상태</th>
									<td colspan="2">${pSelectViewList.projState}<input
										type="hidden" name="r_projState"
										value="${pSelectViewList.projState}"></td>
								</tr>
								<tr>
									<th>시작일</th>
									<td>${pSelectViewList.projStartDate}<input type="hidden"
										name="r_projStartDate"
										value="${pSelectViewList.projStartDate}"></td>
									<th>예상기간</th>
									<td>${pSelectViewList.projExpectedTime}<input
										type="hidden" name="r_projExpectedTime"
										value="${pSelectViewList.projExpectedTime}"></td>
									<th>종료일</th>
									<td colspan="2">${pSelectViewList.projEndDate}<input
										type="hidden" name="r_projEndDate"
										value="${pSelectViewList.projEndDate}"></td>
								</tr>
								<tr>
									<th>필요인원</th>
									<td></td>
									<th>참여인원</th>
									<td colspan="3">${pSelectViewList.joinFLCount}<input
										type="hidden" name="r_joinFLCount"
										value="${pSelectViewList.joinFLCount}"></td>
								</tr>
								<tr>
									<th>고객사</th>
									<td colspan="3">${pSelectViewList.projTarget}<input
										type="hidden" name="r_projTarget"
										value="${pSelectViewList.projTarget}"></td>
									<th>협력사</th>
									<td colspan="2">${pSelectViewList.projPartner}<input
										type="hidden" name="r_projPartner"
										value="${pSelectViewList.projPartner}"></td>
								</tr>
								<tr>
									<th>개발분야</th>
									<td colspan="5">${pSelectViewList.projDevelopSort}<input
										type="hidden" name="r_projDevelopSort"
										value="${pSelectViewList.projDevelopSort}"></td>
								</tr>
								<tr>
									<th>사용언어</th>
									<td colspan="5">
									<!-- 언어들을 '/'로 구분하여 한줄로 출력 --> 
									<c:forEach var='projLangList'
											items="${projLangList}" varStatus="status">
			${projLangList.langName}/<input type="hidden" name="r_langName"
												value="${projLangList.langName}">
										</c:forEach></td>
								</tr>
								<tr>
									<th>DBMS</th>
									<td colspan="5">${pSelectViewList.usedDbName}<input
										type="hidden" name="r_usedDbName"
										value="${pSelectViewList.usedDbName}"></td>
								</tr>
								<tr>
									<th>TOOL/<br>framework
									</th>
									<td colspan="5"><c:forEach var='projFrameList'
											items="${projFrameList}" varStatus="status">
											<!-- 프레임워크들을 '/'로 구분하여 한줄로 출력 --> 
			${projFrameList.frameName}/<input type="hidden" name="r_frameName"
												value="${projFrameList.frameName}">
										</c:forEach></td>
								</tr>
								<tr>
									<th>세부내용</th>
									<td colspan="5"><textarea rows="10" cols="80"
											name="r_projPlan">${pSelectViewList.projPlan}</textarea>
								</tr>

							</table>
						</section>
					</div>
					<div class="clear"></div>
					<div class="col-lg-8">
						<section class="panel">
							<header class="panel-heading no-border">참여자목록</header>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>아이디</th>
										<th>이름</th>
										<th>역할</th>
										<th>투입일</th>
										<th>철수일</th>
										<th>전화번호</th>
										<th>이메일</th>
									</tr>
								</thead>
								<!-- 참여자목록 출력 -->
								<c:forEach var='freelancerList' items="${freelancerList}"
									varStatus="status">
									<tr>
										<td>${freelancerList.freeId}<input type="hidden"
											name="r_freeId" value="${freelancerList.freeId}"></td>
										<td>${freelancerList.freeName}<input type="hidden"
											name="r_freeName" value="${freelancerList.freeName}"></td>
										<td>${joinProjList[status.index].projRole}<input
											type="hidden" name="r_projRole"
											value="${joinProjList[status.index].projRole}"></td>
										<td>${joinProjList[status.index].joinProjDate}<input
											type="hidden" name="r_joinProjDate"
											value="${joinProjList[status.index].joinProjDate}"></td>
										<td>${joinProjList[status.index].exitProjDate}<input
											type="hidden" name="r_exitProjDate"
											value="${joinProjList[status.index].exitProjDate}"></td>
										<td>${freelancerList.freePhone}<input type="hidden"
											name="r_freePhone" value="${freelancerList.freePhone}"></td>
										<td>${freelancerList.freeEmail}<input type="hidden"
											name="r_freeEmail" value="${freelancerList.freeEmail}"></td>
									</tr>
								</c:forEach>
								<tr>
								<!-- 페이징처리 -->
									<td colspan="7" class="align-center"><a href="#"
										onclick="projPrePageSelect('${joinFreePageNumIter}')">이전</a> <c:forEach
											var="joinFreePageNumIter" begin="${joinFreeFirstPage}"
											end="${joinFreeLastPage}">
											<input type="hidden" name="joinFreePageNum"
												value="${joinFreePageNumIter}">
											<a href="#"
												onclick="joinFreePageSelect'${joinFreePageNumIter}')">
												${joinFreePageNumIter} </a>
										</c:forEach> <a href="#"
										onclick="projNextPageSelect('${joinFreePageNumIter}')">다음</a></td>
								</tr>
							</table>
						</section>
					</div>
				</form>
			</section>
		</section>
	</section>

	<%-- <jsp:include page="/frame/footer.jsp"></jsp:include> --%>
	<!-- javascripts -->
	<script src="script/projectViewOfPutIn.js"></script>
	<script>

// 부모창의 선택된 프로젝트번호를 바꿔주는 함수 
function selectProject(projNum)
{
	// 현재 검색창에서 파라미터로 프로젝트번호를 넘기면
	// 부모창인 프로젝트 투입 페이지에서 프로젝트 번호를 받아와
	// 해당 프로젝트 번호로 DB에서 검색을 한 후
	// 프로젝트 정보를 가져옴
	opener.parent.refreshWithProjectNum(projNum);
	window.close();
}
</script>
</body>
</html>
<%--
	프리랜서 검색 페이지
	
	작성자 : 글로벌 IT 진재환
--%>
 

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
<!-- =======================================================
    Theme Name: NiceAdmin
    Theme URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
    Author: BootstrapMade
    Author URL: https://bootstrapmade.com
  ======================================================= -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프리랜서 찾기</title>
</head>
<body>

	<%--

아래쪽의 프리랜서 상세정보를 계속 표시하기 위해
선택된 프리랜서 아이디를 바깥에 심어둠

--%>
	<input type="hidden" name="selectedFreeId" value="${selectedFreeId}">


	<section id="container" class="">
		<section id="main-content">
			<section class="wrapper">
				<div class="col-lg-8">
					<section class="panel">
						<header class="panel-heading no-border"> 프리랜서 검색 </header>

						<%-- 검색옵션을 선택하는 폼 --%>
						<form name="searchForm" method="get" action="ProjectServ">
							<%-- 검색옵션들과 같이 제출되는 커맨드 --%>
							<input type="hidden" name="command"
								value="gotoputinofsearchfreelancer">
								
							<table class="table table-bordered align-middle">
								<tr>
									<th>이름</th>
									<td><input type="text" name="searchFreeName"
										class="col-lg-8" value="${searchFreeName}"></td>
									<th>이메일</th>
									<td><input type="text" name="searchFreeEmail"
										class="col-lg-8" value="${searchFreeEmail}"></td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td><input type="text" name="searchFreePhone"
										class="col-lg-8" value="${searchFreePhone}"></td>
								</tr>
								<tr>
									<th>개발분야</th>
									<td colspan="3"><ul class="table-custom col-lg-12">
											<c:set var="devCheck" value="" />
											<c:forEach var="devName" items="${searchDevSort}">
												<c:choose>
													<%-- 검색후 체크상태를 유지하기 위해 개발분야를 검사함  --%>
													<c:when test="${devName eq '웹'}">
														<%-- 체크 옵션을 삽입하기 위해 만든 변수 --%>
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
												</c:choose>
											</c:forEach>
											<%-- 체크옵션 변수를 인풋태그에 삽입함 --%>
											<li><input type="checkbox" name="searchDevSort"
												value="웹" ${devCheck1}><label for="웹">웹</label></li>
											<li><input type="checkbox" name="searchDevSort"
												value="안드로이드" ${devCheck2}><label for="안드로이드">안드로이드</label></li>
											<li><input type="checkbox" name="searchDevSort"
												value="엠베디드" ${devCheck3}><label for="엠베디드">엠베디드</label></li>
											<li><input type="checkbox" name="searchDevSort"
												value="응용프로그램" ${devCheck4}><label for="응용프로그램">응용프로그램</label></li>
											<li><input type="checkbox" name="searchDevSort"
												value="안드로이드2" ${devCheck5}><label for="안드로이드2">안드로이드2</label></li>
										</ul>
								</tr>
								<tr>
									<th>사용언어</th>
									<td colspan="3">
										<ul class="table-custom col-lg-12">
											<%-- DB에서 가져온 언어를 반복문으로 검사함 --%>
											<c:forEach var="langIter" items="${ldf.lang}">
												<%-- 체크옵션을 위해 설정한 변수 --%>
												<c:set var="langCheck" value="" />
												<%-- 이전 요청에서 체크되었던 언어번호들을 가져옴 --%>
												<c:forEach var="langNum" items="${searchLangSkill}">
													<%-- DB에서 가져온 언어번호와 이전 요청의 언어번호를 비교해서 --%>
													<%-- 일치하면 langCheck 변수의 값을 checked로 바꿔줌 --%>
													<c:if test="${langNum == langIter.langNum}">
														<c:set var="langCheck" value="checked" />
													</c:if>
												</c:forEach>
												<%-- 인풋태그에 체크옵션 변수를 삽입해줌 --%>
												<li><input type="checkbox" name="searchLangSkill"
													value="${langIter.langNum}" ${langCheck}
													id="${langIter.langNum}"><label
													for="${langIter.langNum}">${langIter.langName}</label> <c:set
														var="langCheck" value="" /></li>
											</c:forEach>
										</ul>
									</td>
								</tr>
								<tr>
									<th>DBMS</th>
									<td colspan="3">
										<ul class="table-custom col-lg-12">
											<c:forEach var="DBMSIter" items="${ldf.dbms}">
												<c:set var="langCheck" value="" />
												<c:forEach var="dbNum" items="${searchDBMSSkill}">
													<c:if test="${dbNum == DBMSIter.dbNum}">
														<c:set var="langCheck" value="checked" />
													</c:if>
												</c:forEach>
												<li><input type="checkbox" name="searchDBMSSkill"
													value="${DBMSIter.dbNum}" ${langCheck}
													id="${DBMSIter.dbNum}"><label
													for="${DBMSIter.dbNum}">${DBMSIter.dbName}</label> <c:set
														var="langCheck" value="" /></li>
											</c:forEach>
										</ul>
									</td>
								</tr>
								<tr>
									<th>TOOL/framework</th>
									<td colspan="3"><ul class="table-custom col-lg-12">
											<c:forEach var="frameIter" items="${ldf.frame}">
												<c:set var="langCheck" value="" />
												<c:forEach var="frameNum" items="${searchFrameworkSkill}">
													<c:if test="${frameNum == frameIter.frameNum}">
														<c:set var="langCheck" value="checked" />
													</c:if>
												</c:forEach>
												<li><input type="checkbox" name="searchFrameworkSkill"
													value="${frameIter.frameNum}" ${langCheck}
													id="${frameIter.frameNum}"><label
													for="${frameIter.frameNum}">${frameIter.frameName}</label>
													<c:set var="langCheck" value="" /></li>
											</c:forEach>
										</ul></td>
								</tr>
								<tr>
									<th>역할</th>
									<td colspan="3">
										<ul class="table-custom col-lg-12">
											<c:set var="roleCheck" value="" />
											<c:forEach var="roleName" items="${searchProjRole}">
												<c:choose>
													<c:when test="${roleName eq '분석'}">
														<c:set var="roleCheck1" value="checked" />
													</c:when>
													<c:when test="${roleName eq '설계'}">
														<c:set var="roleCheck2" value="checked" />
													</c:when>
													<c:when test="${roleName eq '개발'}">
														<c:set var="roleCheck3" value="checked" />
													</c:when>
													<c:when test="${roleName eq '보안'}">
														<c:set var="roleCheck4" value="checked" />
													</c:when>
													<c:when test="${roleName eq 'QA'}">
														<c:set var="roleCheck5" value="checked" />
													</c:when>
												</c:choose>
											</c:forEach>
											<li><input type="checkbox" name="searchProjRole"
												value="분석" ${roleCheck1}><label for="분석">분석</label></li>
											<li><input type="checkbox" name="searchProjRole"
												value="설계" ${roleCheck2}><label for="설계">설계</label></li>
											<li><input type="checkbox" name="searchProjRole"
												value="개발" ${roleCheck3}><label for="개발">개발</label></li>
											<li><input type="checkbox" name="searchProjRole"
												value="보안" ${roleCheck4}><label for="보안">보안</label></li>
											<li><input type="checkbox" name="searchProjRole"
												value="QA" ${roleCheck5}><label for="QA">QA</label></li>
										</ul>
									</td>
								<tr>
									<td colspan="4" class="align-right">
										
										<%----- 검색 버튼 ----%>
										<%-- 서브밋 인풋으로 검색옵션을 다시 요청 액션으로 보냄 --%>
										<input type="submit" value="검색">
										<%--------------------%>
									</td>
								</tr>
							</table>
						</form>
					</section>
				</div>
				<div class="clear"></div>
				<div class="col-lg-8" id="projectSearch">
					<section class="panel">
						<form name="freeListForm" method="get" action="ProjectServ">
							<input type="hidden" name="listSortCode" value="${listSortCode}">
							<input type="hidden" name="command"
								value="gotoputinofsearchfreelancer">
							<header class="panel-heading">
								프리랜서 목록
								<div style="float: right;">
									정렬 기준 <select name="listSortCode"
										onchange="searchFreelancers(this.value)">
										<%-- 이전 액션에서 선택한 정렬 옵션을 현재 페이지에도 적용시켜줌 --%>
										<c:choose>
											<c:when test="${listSortCode == 1}">
												<option value="1" selected="selected">최신순</option>
											</c:when>
											<c:otherwise>
												<option value="1">최신순</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${listSortCode == 2}">
												<option value="2" selected="selected">참여자</option>
											</c:when>
											<c:otherwise>
												<option value="2">참여자</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${listSortCode == 3}">
												<option value="3" selected="selected">대기자</option>
											</c:when>
											<c:otherwise>
												<option value="3">대기자</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</header>
							<table class="table table-bordered align-middle">
								<tr>
									<th>상태</th>
									<th>이름</th>
									<th>주 언어</th>
									<th>가용 프레임워크</th>
									<th>경력</th>
									<th>KOSA등급</th>
									<th>평점</th>
									<th>남은기간</th>
									<th>가입일</th>
									<th></th>
								</tr>
								<c:forEach var="freeListIter" items="${freeList}">
									<tr style="cursor: pointer;">
										<td onclick="selectFreelancer('${freeListIter.freeId}')">${freeListIter.jobState}</td>
										<td onclick="selectFreelancer('${freeListIter.freeId}')">${freeListIter.freeName}</td>
										<td onclick="selectFreelancer('${freeListIter.freeId}')">${freeListIter.primaryLangs}</td>
										<td onclick="selectFreelancer('${freeListIter.freeId}')">${freeListIter.availableFrames}</td>
										<td onclick="selectFreelancer('${freeListIter.freeId}')">${freeListIter.projCareerYears}년</td>
										<td onclick="selectFreelancer('${freeListIter.freeId}')">${freeListIter.freeKosa}</td>
										<td onclick="selectFreelancer('${freeListIter.freeId}')">${freeListIter.freeScore}</td>
										<td onclick="selectFreelancer('${freeListIter.freeId}')">${freeListIter.leftJoinDays}</td>
										<td onclick="selectFreelancer('${freeListIter.freeId}')">${freeListIter.freeJoinDate}</td>
										<td><input type="button"
											onclick="selectFree('${freeListIter.freeId}')" value="선택"></td>
									</tr>
								</c:forEach>
								<tr>
									<%-- 페이징 --%>
									<td colspan="9"><c:choose>
											<%-- 현재 페이지번호 - 1이 0보다 작으면 이전버튼이 비활성화됨 --%>
											<c:when test="${freeListPageNum - 1 < 0}">이전</c:when>
											<%-- 현재 페이지번호 - 1이 0보다 크면 이전버튼이 활성화됨 --%>
											<c:otherwise>
												<a href="#" onclick="moveFreeListPage(${freeListPageNum - 1})">이전</a>
											</c:otherwise>
										</c:choose> <c:forEach var="freePageNumIter" begin="${freeListFirstPage}"
											end="${freeListLastPage}" step="1">
											<c:choose>
												<c:when test="${freePageNumIter == freeListPageNum}">${freePageNumIter}</c:when>
												<c:otherwise>
													<a href="#" onclick="moveFreeListPage(${freePageNumIter})">${freePageNumIter}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach> <c:choose>
											<c:when test="${freeListPageNum + 1 > freeListNumOfPage}">다음</c:when>
											<c:otherwise>
												<a href="#"
													onclick="moveFreeListPage(${freeListPageNum + 1})">다음</a>
											</c:otherwise>
										</c:choose></td>
								</tr>
							</table>
						</form>
					</section>
					
					
					<%-- 선택된 프리랜서 ID 가 null이 아니면 프리랜서 상세정보를 표시해줌 --%>
					<c:if test="${selectedFreeId != null}">
					
					<%-- 프리랜서 상세정보 --%>
						<form name="freeDetailForm" method="get" action="ProjectServ">
							<input type="hidden" name="command"
								value="gotoputinofsearchfreelancer">
							<section class="panel">
								<header class="panel-heading no-border">상세보기</header>
								<table class="table table-bordered">
									<tr>
										<td rowspan="7">
											<%-- ${freeDetail.freePic} --%>
										</td>
										<td>아이디</td>
										<td>${freeDetail.freeId}</td>
										<td>상태</td>
										<td>${freeDetail.freeState}</td>
										<td>가입일</td>
										<td>${freeDetail.freeJoinDate}</td>
									</tr>
									<tr>
										<td>이름</td>
										<td>${freeDetail.freeName}</td>
										<td>생년월일</td>
										<td>${freeDetail.freeBirth}</td>
										<td>등급</td>
										<td>${freeDetail.freeKosa}</td>
									</tr>
									<tr>
										<td>성별</td>
										<td>${freeDetail.freeSex}</td>
										<td>결혼여부</td>
										<td>${freeDetail.freeMarried}</td>
										<td>평점</td>
										<td>${freeDetail.freeScore}</td>
									</tr>
									<tr>
										<td>전화번호</td>
										<td>${freeDetail.freePhone}</td>
										<td>이메일</td>
										<td colspan="3">${freeDetail.freeEmail}</td>
									</tr>
									<tr>
										<td rowspan="2">주소</td>
										<td colspan="5">${freeDetail.freeFrontAddr}</td>
									</tr>
									<tr>
										<td colspan="5">${freeDetail.freeRearAddr}</td>
									</tr>
									<tr>
										<td><input type="button" value="선택" onclick=""></td>
									</tr>
								</table>
							</section>
							<section class="panel">
							
							<%-- 계좌정보 --%>
								<header class="panel-heading no-border">상세보기</header>
								<table class="table table-bordered">
									<tr>
										<td>은행</td>
										<td>${freeDetail.freeBank}</td>
										<td>계좌명의</td>
										<td>${freeDetail.freeAccName}</td>
									</tr>
									<tr>
										<td>계좌번호</td>
										<td colspan="3">${freeDetail.freeAccount}</td>
									</tr>
								</table>
							</section>
							
							<%-- 학력사항 목록 --%>
							<section class="panel">
								<header class="panel-heading no-border">학력</header>
								<table class="table table-bordered">
									<tr>
										<th>학교</th>
										<th>학과</th>
										<th>입학일</th>
										<th>졸업일</th>
									</tr>
									
									<%-- 모든 학력사항 목록을 표시함 --%>
									<c:forEach var="edu" items="${freeDetail.edus}">
										<tr>
											<td>${edu.eduSchool}</td>
											<td>${edu.eduMajor}</td>
											<td>${edu.schoolJoinDate}</td>
											<td>${edu.schoolGraduatedDate}</td>
										</tr>
									</c:forEach>
								</table>
							</section>
							
							<%-- 경력사항 목록 --%>
							<section class="panel">
								<header class="panel-heading no-border">경력</header>
								<table class="table table-bordered">
									<tr>
										<th>회사명</th>
										<th>기간</th>
										<th>직위</th>
										<th>담당업무</th>
									</tr>
									<c:forEach var="car" items="${freeDetail.cars}">
										<tr>
											<td>${car.careerCompany}</td>
											<td>${car.careerTerm}</td>
											<td>${car.careerPosition}</td>
											<td>${car.careerJob}</td>
										</tr>
									</c:forEach>
								</table>
							</section>
							
							<%-- 스킬 인벤토리 --%>
							<section class="panel">
								<header class="panel-heading no-border">SKILL INVENTORY</header>
								<table class="table table-bordered">
									<tr>
										<th>프로젝트명</th>
										<th>참여기간</th>
										<th>고객사</th>
										<th>근무회사</th>
										<th>역할</th>
										<th>언어</th>
										<th>DBMS</th>
										<th>TOOL/Framework</th>
									</tr>
									<c:forEach var="inven" items="${freeDetail.skills}">
										<tr>
											<td>${inven.projName}</td>
											<td>${inven.joinTerm}</td>
											<td>${inven.projPartner}</td>
											<td>${inven.projCompany}</td>
											<td>${inven.projRole}</td>
											<td>${inven.langSkills}</td>
											<td>${inven.frameSkills}</td>
											<td>${inven.dbName}</td>
										</tr>
									</c:forEach>
								</table>
							</section>
						</form>
						
						<%-- 프로젝트 참여 기록 --%>
						<form name="joinedRecordForm" method="get" action="ProjectServ">
							<input type="hidden" name="command"
								value="gotoputinofsearchfreelancer"> <input
								type="hidden" name="joinedRecordPageNum"
								value="${joinedRecordPageNum}"> <input type="hidden"
								name="joinedRecordSortCode" value="${joinedRecordSortCode}">
							<section class="panel">
								<header class="panel-heading no-border">
									프로젝트 참여 기록
									<div style="float: right;">
										정렬 기준
										
										<%-- 참여기록 정렬옵션 코드를 검사해서 체크상태를 유지함 --%> 
										<select name="joinedRecordSortCode"
											onchange="changeJoinedRecordSortCode(this.value)">
											<c:choose>
												<c:when test="${joinedRecordSortCode == 1}">
													<option value="1" selected="selected">최신순</option>
												</c:when>
												<c:otherwise>
													<option value="1">최신순</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${joinedRecordSortCode == 2}">
													<option value="2" selected="selected">참여인원</option>
												</c:when>
												<c:otherwise>
													<option value="2">참여인원</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${joinedRecordSortCode == 3}">
													<option value="3" selected="selected">신청마감일순</option>
												</c:when>
												<c:otherwise>
													<option value="3">신청마감일순</option>
												</c:otherwise>
											</c:choose>
										</select>
									</div>
								</header>
								<table class="table table-bordered">
									<tr>
										<th>상태</th>
										<th>프로젝트명</th>
										<th>역할</th>
										<th>참여인원</th>
										<th>투입일</th>
										<th>철수일</th>
										<th>참여기간</th>
									</tr>
									<c:forEach var="joinedRecord"
										items="${freeDetail.joinedRecords}">
										<tr>
											<td>${joinedRecord.recruitState}
												${joinedRecord.projState}</td>
											<td>${joinedRecord.projName}</td>
											<td>${joinedRecord.projRole}</td>
											<td>${joinedRecord.requireState}</td>
											<td>${joinedRecord.joinProjDate}</td>
											<td>${joinedRecord.exitProjDate}</td>
											<td>${joinedRecord.joinTerm}일</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="9">
											<%-- 프로젝트 참여기록 페이징 --%>
											<c:choose>
												<c:when test="${joinedRecordPageNum - 1 < 0}">이전</c:when>
												<c:otherwise>
													<a href="#"
														onclick="moveFreeListPage(${joinedRecordPageNum - 1})">이전</a>
												</c:otherwise>
											</c:choose> <c:forEach var="recordPageNumIter"
												begin="${joinedRecordFirstPage}"
												end="${joinedRecordLastPage}" step="1">
												<c:choose>
													<c:when test="${recordPageNumIter == joinedRecordPageNum}">${recordPageNumIter}</c:when>
													<c:otherwise>
														<a href="#"
															onclick="moveFreeListPage(${recordPageNumIter})">${recordPageNumIter}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach> <c:choose>
												<c:when
													test="${joinedRecordPageNum + 1 > joinedRecordNumOfPage}">다음</c:when>
												<c:otherwise>
													<a href="#"
														onclick="moveFreeListPage(${joinedRecordPageNum + 1})">다음</a>
												</c:otherwise>
											</c:choose></td>
									</tr>
								</table>
							</section>
						</form>
						
						<%-- 참여신청한 프로젝트 목록 --%>
						<form name="joinAppliedForm" method="get" action="ProjectServ">
							<input type="hidden" name="command"
								value="gotoputinofsearchfreelancer"> <input
								type="hidden" name="joinAppliedPageNum"
								value="${joinAppliedPageNum}"> <input type="hidden"
								name="joinAppliedSortCode" value="${joinAppliedSortCode}">
							<section class="panel">
								<header class="panel-heading no-border">
									참여신청한 프로젝트
									<div style="float: right;">
										정렬 기준 <select name="joinAppliedSortCode"
											onchange="changeJoinAppliedSortCode(this.value)">
											<%-- 참여신청한 프로젝트 목록 정렬옵션 코드를 계속 유지함 --%>
											<c:choose>
												<c:when test="${joinAppliedSortCode == 1}">
													<option value="1" selected="selected">최신순</option>
												</c:when>
												<c:otherwise>
													<option value="1">최신순</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${joinAppliedSortCode == 2}">
													<option value="2" selected="selected">참여인원</option>
												</c:when>
												<c:otherwise>
													<option value="2">참여인원</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${joinAppliedSortCode == 3}">
													<option value="3" selected="selected">신청마감일순</option>
												</c:when>
												<c:otherwise>
													<option value="3">신청마감일순</option>
												</c:otherwise>
											</c:choose>
										</select>
									</div>
								</header>
								<table class="table table-bordered">
									<tr>
										<th>상태</th>
										<th>프로젝트명</th>
										<th>개발분야</th>
										<th>참여인원</th>
										<th>시작일</th>
										<th>종료일</th>
										<th>남은기간</th>
									</tr>
									<c:forEach var="joinAppliedList"
										items="${freeDetail.joinAppliedList}">
										<tr>
											<td>${joinAppliedList.recruitState}
												${joinAppliedList.projState}</td>
											<td>${joinAppliedList.projName}</td>
											<td>${joinAppliedList.projDevSort}</td>
											<td>${joinAppliedList.requireState}</td>
											<td>${joinAppliedList.projStartDate}</td>
											<td>${joinAppliedList.projEndDate}</td>
											<td>${joinAppliedList.projTerm}일</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="9">
											<c:choose>
												<%-- 참여신청한 프로젝트 목록 페이징 --%>	
												<c:when test="${joinAppliedPageNum - 1 < 0}">이전</c:when>
												<c:otherwise>
													<a href="#"
														onclick="moveFreeListPage(${joinAppliedPageNum - 1})">이전</a>
												</c:otherwise>
											</c:choose> <c:forEach var="joinAppliedPageNumIter"
												begin="${joinAppliedFirstPage}" end="${joinAppliedLastPage}"
												step="1">
												<c:choose>
													<c:when
														test="${joinAppliedPageNumIter == joinAppliedPageNum}">${joinAppliedPageNumIter}</c:when>
													<c:otherwise>
														<a href="#"
															onclick="moveFreeListPage(${joinAppliedPageNumIter})">${joinAppliedPageNumIter}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach> <c:choose>
												<c:when test="${joinAppliedPageNum + 1 > freeListNumOfPage}">다음</c:when>
												<c:otherwise>
													<a href="#"
														onclick="moveFreeListPage(${joinAppliedPageNum + 1})">다음</a>
												</c:otherwise>
											</c:choose></td>
									</tr>
								</table>
							</section>
						</form>
					</c:if>
				</div>
			</section>
		</section>
	</section>

	<%-- 자바스크립트 --%>
	<script>
	
		// 프리랜서 검색결과 리스트의 페이지번호 이동 함수
		function moveFreeListPage(pageNum)
		{
			// 검색폼만 가져옴
			var frm = document.getElementsByName("searchForm")[0];
			
			// freeListPageNum 파라미터를 새로 생성함
			var nextPageNum = document.createElement("input");
			nextPageNum.setAttribute("type", "hidden");
			nextPageNum.setAttribute("name", "freeListPageNum");
			// 밸류에는 함수의 파라미터 pageNum을 줌
			nextPageNum.setAttribute("value", pageNum);

			frm.appendChild(nextPageNum);
			
			// 폼 제출
			frm.submit();
		}
		
		// 프리랜서 검색 함수
		function searchFreelancers(sortOption)
		{
			// 검색폼을 가져옴
			var frm = document.getElementsByName("searchForm")[0];
			
			// 정렬옵션을 가져오고
			document.getElementsByName("listSortCode")[0].value = sortOption;
			// 정렬옵션이 0이 아니면
			if (sortOption != 0)
			{
				// listSortCode 파라미터를 새로 생성함
				var sortCode = document.createElement("input");
				sortCode.setAttribute("type", "hidden");
				sortCode.setAttribute("name", "listSortCode");
				sortCode.setAttribute("value", sortOption);
				
				frm.appendChild(sortCode);
			}
			
			// 폼 제출
			frm.submit();
		}
		
		
		function selectFreelancer(freeId)
		{
			var frm = document.getElementsByName("searchForm")[0];
			
			if (freeId != "")
			{
				var selectedFreeId = document.createElement("input");
				selectedFreeId.setAttribute("type", "hidden");
				selectedFreeId.setAttribute("name", "selectedFreeId");
				selectedFreeId.setAttribute("value", freeId);
				
				frm.appendChild(selectedFreeId);
			}
			
			frm.submit();
		}
		
		// 참여신청한 프로젝트 목록의 페이지 번호를 이동하는 함수
		function moveJoinAppliedPage(freePageNum, joinAppliedPageNum, joinedRecordPageNum)
		{
			// 검색옵션 폼을 가져옴
			var frm1 = document.getElementsByName("searchForm")[0];
			
			// 프리랜서 검색 결과 목록의 페이지 번호를
			// 새로 생성한 파라미터에 값으로 넣음
			var nextPageNum = document.createElement("input");
			nextPageNum.setAttribute("type", "hidden");
			nextPageNum.setAttribute("name", "freeListPageNum");
			nextPageNum.setAttribute("value", freePageNum);
			frm1.appendChild(nextPageNum);
			
			// 프리랜서 상세정보 폼을 가져옴
			var frm2 = document.getElementsByName("freeDetailForm")[0];
			// 프로젝트 참여기록 폼을 가져옴
			var frm3 = ducument.getElementsByName("joinedRecordForm")[0];
			
			// 참여신청한 프로젝트 목록의 페이지번호 파라미터를 새로 생성함
			var nextPageNum2 = document.createElement("input");
			nextPageNum2.setAttribute("type", "hidden");
			nextPageNum2.setAttribute("name", "joinedRecordPageNum");
			nextPageNum2.setAttribute("value", joinedRecordPageNum);
			frm3.appendChild(nextPageNum2);
			
			// 참여신청한 프로젝트 폼
			var frm4 = ducument.getElementsByName("joinAppliedForm")[0];
			
			// 참여신청한 프로젝트 목록 페이지번호를 가져옴
			var nextPageNum3 = document.createElement("input");
			nextPageNum3.setAttribute("type", "hidden");
			nextPageNum3.setAttribute("name", "joinAppliedPageNum");
			nextPageNum3.setAttribute("value", joinAppliedPageNum);
			frm4.appendChild(nextPageNum3);
			
			frm1.submit();
			frm2.submit();
			frm3.submit();
			frm4.submit();
		}
		
		// 참여 기록 목록의 페이지 번호를 변경해줌
		// 현재 프리랜서 목록과 참여신청목록의 페이지 번호를 유지해야 하기 때문에
		// 세개의 파라미터를 넘김
		function moveJoinedRecordPage(freePageNum, joinedRecordPageNum, joinAppliedPageNum)
		{
			// 검색폼을 유지해야 하기 때문에 검색폼을 가져옴
			var frm1 = document.getElementsByName("searchForm")[0];
			
			// 다음 페이지 번호 인풋을 새로 생성함
			var nextPageNum = document.createElement("input");
			nextPageNum.setAttribute("type", "hidden");
			nextPageNum.setAttribute("name", "freeListPageNum");
			nextPageNum.setAttribute("value", pageNum);
			
			// 검색폼에 자식으로 달아줌
			frm1.appendChild(nextPageNum);
			
			frm1.submit();
			
			var frm2 = document.getElementsByName("freeDetailForm")[0];
			var frm3 = ducument.getElementsByName("joinedRecordForm")[0];
			var frm4 = ducument.getElementsByName("joinAppliedForm")[0];
		}
		
		// 프로젝트 참여기록 정렬옵션을 바꿔주는 함수
		function changeJoinedRecordSortCode(sortCode)
		{
			// 참여기록의 정렬옵션을 이 함수의 파라미터 값으로 바꿔줌
			document.getElementsByName("joinedRecordSortCode")[0].value = sortCode;
			
			// 현재 이 페이지에 있는 모든 폼을 불러옴
			var frm1 = document.getElementsByName("searchForm")[0];
			var frm2 = document.getElementsByName("freeDetailForm")[0];
			var frm3 = document.getElementsByName("freeListForm")[0];
			var frm4 = document.getElementsByName("joinedRecordForm")[0];
			var frm5 = document.getElementsByName("joinAppliedForm")[0];
			
			// 새로운 폼을 생성하고 현재 페이지로 재요청하기 위해
			// 커맨드를 이 페이지로 이동하도록 생성해줌
			var frm = document.createElement("form");
			frm.setAttribute('method',"get");
			frm.setAttribute('action',"ProjectServ");
			
			var command = document.createElement("input");
			command.setAttribute("type", "hidden");
			command.setAttribute("name", "command");
			command.setAttribute("value", "gotoputinofsearchfreelancer");
			
			// 새로 생성한 폼에 새로 생성한 커맨드를 자식으로 달아줌
			frm.appendChild(command);
			
			// 현재 이 페이지에 있는 모든 폼의
			// 엘리먼트들을 새로 생성한 폼에 자식으로 달아줌
			for (var i = 0; i < frm1.elements.length; i++)
			{
				frm.appendChild(frm1.elements[i]);
			}
			for (var i = 0; i < frm2.elements.length; i++)
			{
				frm.appendChild(frm2.elements[i]);
			}
			for (var i = 0; i < frm3.elements.length; i++)
			{
				frm.appendChild(frm3.elements[i]);
			}
			for (var i = 0; i < frm4.elements.length; i++)
			{
				frm.appendChild(frm4.elements[i]);
			}
			for (var i = 0; i < frm5.elements.length; i++)
			{
				frm.appendChild(frm5.elements[i]);
			}
			
			// 선택된 프리랜서 아이디도 계속 유지해야되기 때문에
			// 엘리먼트를 새로 생성해줌
			var selectedFreeId = document.createElement("input");
			selectedFreeId.setAttribute("type", "hidden");
			selectedFreeId.setAttribute("name", "selectedFreeId");
			selectedFreeId.setAttribute("value", "${selectedFreeId}");
				
			// 새로 생성한 폼에 선택된 프리아이디 엘리먼트를 달아줌
			frm.appendChild(selectedFreeId);
			
			// 페이지 바디부분에 새로 생성한 폼을 자시으로 달아줌
			document.getElementsByTagName('body')[0].appendChild(frm);
			frm.submit();
		}
		
		// 참여신청한 프로젝트 목록 정렬옵션을 바꿔주는 함수
		// 참여기록 정렬옵션 바꾸는 함수와 비슷하게 작동함
		function changeJoinAppliedSortCode(sortCode)
		{
			document.getElementsByName("joinAppliedSortCode")[0].value = sortCode;
			
			var frm1 = document.getElementsByName("searchForm")[0];
			var frm2 = document.getElementsByName("freeDetailForm")[0];
			var frm3 = document.getElementsByName("freeListForm")[0];
			var frm4 = document.getElementsByName("joinedRecordForm")[0];
			var frm5 = document.getElementsByName("joinAppliedForm")[0];
			
			var frm = document.createElement("form");
			frm.setAttribute('method',"get");
			frm.setAttribute('action',"ProjectServ");
			
			var command = document.createElement("input");
			command.setAttribute("type", "hidden");
			command.setAttribute("name", "command");
			command.setAttribute("value", "gotoputinofsearchfreelancer");
			
			frm.appendChild(command);
			
			for (var i = 0; i < frm1.elements.length; i++)
			{
				frm.appendChild(frm1.elements[i]);
			}
			for (var i = 0; i < frm2.elements.length; i++)
			{
				frm.appendChild(frm2.elements[i]);
			}
			for (var i = 0; i < frm3.elements.length; i++)
			{
				frm.appendChild(frm3.elements[i]);
			}
			for (var i = 0; i < frm4.elements.length; i++)
			{
				frm.appendChild(frm4.elements[i]);
			}
			for (var i = 0; i < frm5.elements.length; i++)
			{
				frm.appendChild(frm5.elements[i]);
			}
			
			var selectedFreeId = document.createElement("input");
			selectedFreeId.setAttribute("type", "hidden");
			selectedFreeId.setAttribute("name", "selectedFreeId");
			selectedFreeId.setAttribute("value", "${selectedFreeId}");
				
			frm.appendChild(selectedFreeId);
			
			document.getElementsByTagName('body')[0].appendChild(frm);
			frm.submit();
		}
		
		// 부모창인 프리랜서 투입의 투입대기자 목록에
		// 선택된 프리랜서 아이디로 보내서 부모창의
		// 투입대기자 목록에 추가하는 함수
		function selectFree(freeId)
		{
			opener.parent.addToPutInFreelancerListFromChild(freeId);
			
			alert("추가되었습니다");
		}
	</script>
</body>
</html>
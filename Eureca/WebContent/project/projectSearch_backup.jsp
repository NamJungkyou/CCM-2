<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 찾기</title>
</head>
<body>
	<jsp:include page="/frame/header.jsp"></jsp:include>
	<section id="container" class=""> <section id="main-content">
	<section class="wrapper">
<c:choose>
							<%--로그인 상태가 아니면 --%>
							<c:when test="${loginfree == null && loginemp != null}">
	<form method="post" action="ProjectServ" id="frm" name="frm">
		<input type="hidden" name="command" value="project_list">
		<div class="col-lg-8">
			<section class="panel"> <header
				class="panel-heading no-border"> 프로젝트 검색 </header>
			<table class="table table-bordered align-middle">
				<tr>
					<th>프로젝트 명</th>
					<td><input type="text" name="projNameSearch" class="col-lg-8"
						value="${projName}"></td>
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
							<li><input type="checkbox" name="devFieldSearch" value="웹"
								${devCheck1} id="web"><label for="web">웹</label></li>
							<li><input type="checkbox" name="devFieldSearch"
								value="안드로이드" ${devCheck2} id="android"><label for="android">안드로이드</label></li>
							<li><input type="checkbox" name="devFieldSearch"
								value="엠베디드" ${devCheck3} id="embedded"><label for="embedded">엠베디드</label></li>
							<li><input type="checkbox" name="devFieldSearch"
								value="응용프로그램" ${devCheck4} id="application"><label for="application">응용프로그램</label></li>
							<li><input type="checkbox" name="devFieldSearch"
								value="안드로이드2" ${devCheck5} id="android2"><label for="android2">안드로이드2</label></li>
						</ul>
				</tr>
				<tr>
					<th>사용언어</th>
					<td>
						<ul class="table-custom col-lg-12">
							<c:forEach var="langIter" items="${langList}">
								<c:set var="langCheck" value="" />
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
							<c:forEach var="DBMSIter" items="${dbmsList}">
								<c:set var="langCheck" value="" />
								<c:forEach var="dbName" items="${dbCount}">
									<c:if test="${dbName eq DBMSIter.dbName}">
										<c:set var="langCheck" value="checked" />
									</c:if>
								</c:forEach>
								<li><input type="checkbox" name="DBMSSearch"
									value="${DBMSIter.dbName}" ${langCheck} id="${DBMSIter.dbName}"><label
									for="${DBMSIter.dbName}">${DBMSIter.dbName}</label> <c:set
										var="langCheck" value="" /></li>
							</c:forEach>

						</ul>
					</td>
				</tr>
				<tr>
					<th>TOOL/framework</th>
					<td><ul class="table-custom col-lg-12">
							<c:forEach var="tfwIter" items="${frameList}">
								<c:set var="langCheck" value="" />
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
						value="${time1}"> - <input type="text" class="datepicker"
						name="period2" value="${time2}"></td>

					<tr>
					<td colspan="2" class="align-right"><input type="submit"
						value="검색"></td>
				</tr>
			</table>
			</section>
		</div>
		<div class="clear"></div>
		<div class="col-lg-8" id="projectSearch">
			<section class="panel"> <header class="panel-heading">
			프로젝트 목록 <select name="order" onchange="onSubmit()">
				<option value="" ${order eq "" ? "selected" :""}>선택하세요</option>
				<option value="등록일" ${order eq "등록일" ? "selected" :""}>등록일</option>
				<option value="시작일" ${order eq "시작일" ? "selected" :""}>시작일</option>
				<option value="종료(예정)일" ${order eq "종료(예정)일" ? "selected" :""}>종료(예정)일</option>
				<option value="남은기간" ${order eq "남은기간" ? "selected" :""}>남은기간</option>
			</select> </header>
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
					</tr>
				</thead>
				<c:forEach var='projViewList' items="${projViewList}"
					varStatus="status">
					<tr onclick="projNumSelect('${projViewList.projNum}','${pageNum}')"
						style="cursor: pointer;">
						<td>${projViewList.projState}<input type="hidden"
							name="projState" value="${projViewList.projState}"></td>
						<td>${projViewList.projNum}<input type="hidden"
							name="projNum" id="projNum" value="${projViewList.projNum}"
							disabled></td>
						<td>${projViewList.projName}<input type="hidden"
							name="projName" value="${projViewList.projName}"></td>
						<td>${projViewList.projField}<input type="hidden"
							name="projField" value="${projViewList.projField}"></td>
						<td>${projViewList.joinFLCount}<input type="hidden"
							name="joinFLCount" value="${projViewList.joinFLCount}"></td>
						<td>${projViewList.projStartDate}<input type="hidden"
							name="projStartDate" value="${projViewList.projStartDate}"></td>
						<td>${projViewList.expectedEndDate}<input type="hidden"
							name="expectedEndDate" value="${projViewList.expectedEndDate}"></td>
						<td>${projViewList.remainDays}<input type="hidden"
							name="remainDays" value="${projViewList.remainDays}"></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="7"></td>
				</tr>
				<tr>
					<td colspan="7" class="align-center"><a href="#"
						onclick="projPrePageSelect('${pagenNum}')">이전</a> <c:forEach
							var="pageNumIter" begin="${firstPage}" end="${lastPage}">
							<input type="hidden" name="first" value="${firstPage}">
							<input type="hidden" name="last" value="${lastPage}">
							<input type="hidden" name="pageNum" value="${pageNumIter}">
							<a href="#" onclick="projPageSelect('${pageNumIter}')">
								${pageNumIter} </a>
						</c:forEach> <a href="#" onclick="projNextPageSelect('${pageNum}','${lastPage}')">다음</a></td>
				</tr>
			</table>
		</section>
		</div>

		<div class="clear"></div>
		<div class="col-lg-8">
			<section class="panel details-background"> <header
				class="panel-heading no-border">상세보기</header>
			<table class="table table-bordered details-background">
				<tr>
					<th>번호</th>
					<td>${pSelectViewList.projNum}<input type="hidden"
						name="r_projNum" value="${pSelectViewList.projNum}"></td>
					<th>등록인</th>
					<td>${pSelectViewList.projRegisterer}<input type="hidden"
						name="r_projRegisterer2" value="${pSelectViewList.projRegisterer}"></td>
					<th>등록일</th>
					<td colspan="2">${pSelectViewList.projRegisterDate}<input
						type="hidden" name="r_projRegisterDate"
						value="${pSelectViewList.projRegisterDate}"></td>
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
						name="r_projStartDate" value="${pSelectViewList.projStartDate}"></td>
					<th>예상기간</th>
					<td>${pSelectViewList.projExpectedTime}<input type="hidden"
						name="r_projExpectedTime"
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
					<td colspan="5"><c:forEach var='projLangList'
							items="${projLangList}" varStatus="status">
			${projLangList.langName}<c:if test="${not status.last}">/</c:if><input type="hidden" name="r_langName"
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
			${projFrameList.frameName}<c:if test="${not status.last}">/</c:if><input type="hidden" name="r_frameName"
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
			<section class="panel"> <header
				class="panel-heading no-border">참여자목록</header>
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
				<c:forEach var='freelancerList' items="${freelancerList}"
					varStatus="status">
					<tr>
						<td>${freelancerList.freeId}<input type="hidden"
							name="rfreeId" value="${freelancerList.freeId}"></td>
						<td>${freelancerList.freeName}<input type="hidden"
							name="rfreeName" value="${freelancerList.freeName}"></td>
						<td>${joinProjList[status.index].projRole}<input
							type="hidden" name="rprojRole"
							value="${joinProjList[status.index].projRole}"></td>
						<td>${joinProjList[status.index].joinProjDate}<input
							type="hidden" name="rjoinProjDate"
							value="${joinProjList[status.index].joinProjDate}"></td>
						<td>${joinProjList[status.index].exitProjDate}<input
							type="hidden" name="rexitProjDate"
							value="${joinProjList[status.index].exitProjDate}"></td>
						<td>${freelancerList.freePhone}<input type="hidden"
							name="rfreePhone" value="${freelancerList.freePhone}"></td>
						<td>${freelancerList.freeEmail}<input type="hidden"
							name="rfreeEmail" value="${freelancerList.freeEmail}"></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="7" class="align-center"><a href="#"
						onclick="projPrePageSelect('${joinFreePageNumIter}')">이전</a> <c:forEach
							var="joinFreePageNumIter" begin="${joinFreeFirstPage}"
							end="${joinFreeLastPage}">
							<input type="hidden" name="joinFreePageNum"
								value="${joinFreePageNumIter}">
							<a href="#" onclick="joinFreePageSelect('${joinFreePageNumIter}')">
								${joinFreePageNumIter} </a>
						</c:forEach> <a href="#"
						onclick="projNextPageSelect('${joinFreePageNumIter}')">다음</a></td>
				</tr>
			</table>
			</section>
		</div>
	</form>
	</c:when>
	<c:otherwise>
	로그인하세요
	</c:otherwise>
	</c:choose>
	</section> </section> </section>

	<jsp:include page="/frame/footer.jsp"></jsp:include>
	<!-- javascripts -->
<script type="text/javascript" src="script/projectView.js"></script>
</body>
</html>
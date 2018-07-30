<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 등록</title>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/frame/header.jsp"></jsp:include>
	<!-- 헤더 끝 -->
	<section id="container" class="">
		<section id="main-content">
			<section class="wrapper">

				<form method="post" action="ProjectServ" id="frm" name="frm">
					<input type="hidden" name="command" value="project_insert">
					<div class="col-lg-10">
						<section class="panel">
							<header class="panel-heading no-border">
								프로젝트 등록 <input type="button" value="검색"
									onclick="projectSearch()">
							</header>
							<table class="table table-bordered">
								<tr>
									<th>번호</th>
									<td><input type="text" name="projNum"
										value="${newProjNum.projNum}" readonly></td>
									<th>등록인</th>
									<td><input type="hidden" name="projRegisterer"
										value="${loginemp.empId}"> <input type="text"
										name="projRegistererName" value="${loginemp.empName}" readonly></td>
									<th>등록일</th>
									<td colspan=""><input type="text" name="projRegisterDate"
										readonly></td>
								</tr>
								<tr>
									<th>프로젝트명</th>
									<td colspan=""><input type="text" name="projName"></td>
									<th>프로젝트 유형</th>
									<td colspan=""><select name="projField">
											<option value="선택">선택</option>
											<option value="SI">SI</option>
											<option value="SM">SM</option>
									</select></td>
									<th>상태</th>
									<td colspan=""><select name="projState">
											<option value="선택">선택</option>
											<option value="대기중">대기중</option>
											<option value="참여중">참여중</option>
											<option value="종료">종료</option>
									</select></td>
								</tr>
								<tr>
									<th>시작일</th>
									<td><input type="text" name="projStartDate"
										class="datepicker" value="${startDate}"
										oninput="calcExpectedTimeToEndDate()"></td>
									<th>예상기간</th>
									<td><input type="text" name="projExpectedTime"
										oninput="calcExpectedTimeToEndDate()" size="5">일</td>
									<th>종료(예상)일</th>
									<td colspan=""><input type="text" name="projEndDate"
										class="datepicker" value="${endDate}" disabled></td>
								</tr>
								<tr>
									<th>필요인원</th>
									<td colspan="3"><input type="hidden" name="roleName1"
										value="분석">분석&nbsp;<input type="text"
										name="requiredPerson1" style="width: 10%;"
										oninput="calcTotalNumOfRequire()"> <input
										type="hidden" name="roleName2" value="설계">&nbsp;설계&nbsp;<input
										type="text" name="requiredPerson2" style="width: 10%;"
										oninput="calcTotalNumOfRequire()"> <input
										type="hidden" name="roleName3" value="개발">&nbsp;개발&nbsp;<input
										type="text" name="requiredPerson3" style="width: 10%;"
										oninput="calcTotalNumOfRequire()"> <input
										type="hidden" name="roleName4" value="보안">&nbsp;보안&nbsp;<input
										type="text" name="requiredPerson4" style="width: 10%;"
										oninput="calcTotalNumOfRequire()"> <input
										type="hidden" name="roleName5" value="QA">&nbsp;QA&nbsp;<input
										type="text" name="requiredPerson5" style="width: 10%;"
										oninput="calcTotalNumOfRequire()"></td>
									<th>총 필요인원</th>
									<td colspan=""><input type="text" name="joinFLCount"
										value="${pSelectViewList.joinFLCount}" disabled oninput=""></td>
								</tr>
								<tr>
									<th>고객사</th>
									<td colspan=""><input type="text" name="projTarget"></td>
									<th>협력사</th>
									<td colspan="2"><input type="text" name="projPartner"></td>
								</tr>
								<tr>
									<th>모집시작일</th>
									<td colspan=""><input type="text" name="recruitStartDate"
										class="datepicker" value="${recruitStartDate}"></td>
									<th>모집종료일</th>
									<td colspan="2"><input type="text" name="recruitEndDate"
										class="datepicker" value="${recruitEndDate}"></td>
								</tr>
								<tr>
									<th>개발분야</th>
									<td colspan="5"><ul class="table-custom">
											<li><input type="radio" name="devFieldSearch" value="웹"><label
												for="웹">웹</label></li>
											<li><input type="radio" name="devFieldSearch"
												value="안드로이드" id="android"><label for="android">안드로이드</label></li>
											<li><input type="radio" name="devFieldSearch"
												value="엠베디드"><label for="엠베디드" id="embedded">엠베디드</label></li>
											<li><input type="radio" name="devFieldSearch"
												value="응용프로그램" id="application"><label
												for="application">응용프로그램</label></li>
											<li><input type="radio" name="devFieldSearch"
												value="안드로이드2" id="android2"><label for="android2">안드로이드2</label></li>
										</ul>
								</tr>
								<tr>
									<th>사용언어</th>
									<td colspan="5">
										<ul class="table-custom">
											<!-- proglang 테이블에 등록되어있는 언어들을 체크박스로 출력 -->
											<c:forEach var="langIter" items="${langList}">
												<li><input type="checkbox" name="progLangSearch"
													value="${langIter.langNum}" id="${langIter.langName}"><label
													for="${langIter.langName}">${langIter.langName}</label></li>
											</c:forEach>
										</ul>
									</td>
								</tr>
								<tr>
									<th>DBMS</th>
									<td colspan="5">
										<ul class="table-custom">
											<!-- DBMS 테이블에 등록되어있는 데이터베이스들을 체크박스로 출력 -->
											<c:forEach var="DBMSIter" items="${dbmsList}">
												<li><input type="radio" name="DBMSSearch"
													value="${DBMSIter.dbNum}" id="${DBMSIter.dbName}"><label
													for="${DBMSIter.dbName}">${DBMSIter.dbName}</label></li>
											</c:forEach>

										</ul>
									</td>
								</tr>
								<tr>
									<th>TOOL/framework</th>
									<td colspan="5"><ul class="table-custom">
											<!-- framework 테이블에 등록되어있는 프레임워크들을 체크박스로 출력 -->
											<c:forEach var="tfwIter" items="${frameList}">
												<li><input type="checkbox" name="TOOLfwSearch"
													value="${tfwIter.frameNum}" id="${tfwIter.frameName}"><label
													for="${tfwIter.frameName}">${tfwIter.frameName}</label></li>
											</c:forEach>
										</ul></td>
								</tr>
								<tr>
									<th>세부내용</th>
									<td colspan="5"><textarea rows="10" cols="80"
											name="projPlan"></textarea></td>
								</tr>
								<tr>
									<td colspan="6" class="align-right"><input type="submit"
										value="등록"></td>
								</tr>
							</table>
						</section>
					</div>
				</form>
			</section>
		</section>
	</section>
	<!-- 푸터 -->
	<jsp:include page="/frame/footer.jsp"></jsp:include>
	<!-- 푸터 끝 -->

	<!-- javascripts -->
	<script src="script/projectView.js"></script>
	<script type="text/javascript">
	/* 시간을 실시간으로 출력하기 위한 자바스크립트 */
		setInterval("dpTime()", 1000);
		function dpTime() {
			var now = new Date();
			hours = now.getHours();
			minutes = now.getMinutes();
			seconds = now.getSeconds();
			year = now.getFullYear();
			month = now.getMonth() + 1;
			date = now.getDate();
			if (month < 10) {
				month = "0" + month;
			}
			if (date < 10) {
				date = "0" + date;
			}
			if (hours < 10) {
				hours = "0" + hours;
			}
			if (minutes < 10) {
				minutes = "0" + minutes;
			}
			if (seconds < 10) {
				seconds = "0" + seconds;
			}
			var target = document.getElementsByName("projRegisterDate")[0];
			target.value = year + "-" + month + "-" + date + " " + hours + ":"
					+ minutes + ":" + seconds;
		}

		// 예상기간을 입력하면 프로젝트 종료일이 자동으로 출력되는 함수
		function calcExpectedTimeToEndDate() {
			// 예상기간 인풋태그 객체를 불러옴
			var expectedTime = document.getElementsByName("projExpectedTime")[0].value;
			
			// 시작일 인풋태그 객체를 불러옴
			var startDate = new Date(document
					.getElementsByName("projStartDate")[0].value);
			
			// 종료일을 우선 시작일로 참조함
			var endDate = startDate;
			
			// 종료일에 예상기간을 더함
			endDate.setDate(endDate.getDate() + parseInt(expectedTime));

			// 달을 계산
			var month = (endDate.getMonth() + 1) < 10 ? ("0" + (endDate
					.getMonth() + 1)) : (endDate.getMonth() + 1);
			
			// 일을 계산
			var days = (endDate.getDate()) < 10 ? ("0" + (endDate.getDate()))
					: (endDate.getDate());
			
			// 종료일을 다시 포맷팅함
			var datetime = endDate.getFullYear() + "-" + month + "-" + days;

			if (datetime == "NaN-NaN-NaN" || expectedTime == "")
				datetime = "";
			
			// 종료일 태그의 값을 최신화함
			document.getElementsByName("projEndDate")[0].value = datetime;
		}

		// 역할별 필요인원을 합산하는 함수
		function calcTotalNumOfRequire() {
			
			// 분석 필요인원을 계산
			var analyst = parseInt(document
					.getElementsByName("requiredPerson1")[0].value);
			
			// 디자이너 필요인원
			var designer = parseInt(document
					.getElementsByName("requiredPerson2")[0].value);
			
			// 개발 필요인원
			var developer = parseInt(document
					.getElementsByName("requiredPerson3")[0].value);
			
			// 보안 필요인원
			var security = parseInt(document
					.getElementsByName("requiredPerson4")[0].value);
			
			// QA 필요인원
			var QA = parseInt(document.getElementsByName("requiredPerson5")[0].value);

			// 값이 비어있으면 0으로 처리
			if (analyst == null || isNaN(analyst))
				analyst = 0;
			if (designer == null || isNaN(designer))
				designer = 0;
			if (developer == null || isNaN(developer))
				developer = 0;
			if (security == null || isNaN(security))
				security = 0;
			if (QA == null || isNaN(QA))
				QA = 0;
			
			// 역할별 필요인원들을 합산함
			var total = analyst + designer + developer + security + QA;

			// 총 필요인원을 최신화함
			document.getElementsByName("joinFLCount")[0].value = total
		}
	</script>

</body>
</html>
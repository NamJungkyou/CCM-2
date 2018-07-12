<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="script/interview.js?ver=1.218"
	charset="UTF-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>면접일정/결과 등록</title>
</head>
<body>
	<jsp:include page="/frame/header.jsp" />
	<section id="container" class="">
		<section id="main-content">
			<section class="wrapper">

				<form name="scheduleFrm" method="post">
					<section class="panel">
						<header class="panel-heading no-border">
							신청 접수한 프리랜서 <select>
								<option>정렬기준</option>
								<option>종합</option>
								<option>평점순</option>
								<option>언어순</option>
								<option>경력순</option>
								<option>등급순</option>
								<option>최신순</option>
								<option>대기자</option>
								<option>참여신청자</option>
							</select>
						</header>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>상태</th>
									<th>이름</th>
									<th>주언어</th>
									<th>가용 TOOL / framework</th>
									<th>경력</th>
									<th>프로젝트 참여횟수</th>
									<th>KOSA등급</th>
									<th>평점</th>
									<th>선택</th>
								</tr>
							</thead>
							<!-- 조인을 이용하여 보여주기 경력, 프리랜서, 언어, 툴/프레임워크 -->
							<c:forEach var="joinFree" items="${joinFreeList}" end="9">
								<tr>
									<td>${joinFree.freeState}</td>
									<td>${joinFree.freeName}</td>
									<td>${joinFree.languages}</td>
									<td>${joinFree.frameworks}</td>
									<td>${joinFree.careerYear}</td>
									<td>${joinFree.joinProjTime}</td>
									<td>${joinFree.freeKosa}</td>
									<td>${joinFree.freeScore}</td>
									<td><input type="checkbox" value="${joinFree.joinNum}"
										name="checks"></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="9" align="right"><input type="submit"
									value="면접일 지정" onclick="sendInterviewMsg(); return false; ">
								</td>
							</tr>
						</table>
					</section>
				</form>
				<br>
				<form action="Eu?e=result_interview" method="post"
					name="resultInterviewFrm">
					<section class="panel">
						<header class="panel-heading no-border">
							면접 결과 등록 <select>
								<option>정렬기준</option>
								<option>상태순</option>
								<option>날짜순</option>
							</select>
						</header>
						<table class="table table-bordered">

							<thead>
								<tr>
									<th>상태</th>
									<th>이름</th>
									<th>면접일</th>
									<th>장소</th>
									<th>채용여부</th>
									<th colspan="2">사유</th>
								</tr>
							</thead>
							<c:forEach var="interview" items="${joinFreeInterviewList}"
								end="9" varStatus="status">
								<tr>
									<td><c:choose>
											<c:when test="${interview.interviewState == 0}">
												<input type="text" name="interviewState" value="면접대기"
													readonly="readonly">
											</c:when>
											<c:when test="${interview.interviewState == 1}">
												<input type="text" name="interviewState" value="면접완료"
													readonly="readonly">
											</c:when>
											<c:when test="${interview.interviewState == 2}">
												<input type="text" name="interviewState" value="보류"
													readonly="readonly">
											</c:when>
										</c:choose></td>
									<td><input type="text" name="freeName"
										value="${interview.freeName}" readonly="readonly"> <input
										type="hidden" name="interviewNum"
										value="${interview.interviewNum}"> <input
										type="hidden" name="joinNum" value="${interview.joinNum}"></td>
									<td><input type="text" name="interviewDate"
										value="${interview.interviewDate}" readonly="readonly"></td>
									<td><input type="text" name="interviewLocation"
										value="${interview.interviewLocation}" readonly="readonly">
									</td>
									<td><input type="radio" name="choice${status.index}"
										id="sucess${status.index}" value="sucess"
										onchange="return state(${status.index}, 's');" /> <label
										for="sucess${status.index}">채용</label> <input type="radio"
										name="choice${status.index}" id="hold${status.index}"
										value="hold" onchange="return state(${status.index}, 'h');" />
										<label for="hold${status.index}">보류</label> <input
										type="radio" name="choice${status.index}"
										id="fail${status.index}" value="fail"
										onchange="return state(${status.index}, 'f');" /> <label
										for="fail${status.index}">불채용</label> <input type="hidden"
										name="freeState" id="fState${status.index}"></td>
									<td colspan="2"><input type="text" name="interviewReason"
										id="interviewReason${status.index}"></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="7" align="right"><input type="submit"
									value="결과등록" onclick="return sendResultMsg();"></td>
							</tr>
						</table>
					</section>
				</form>
			</section>
		</section>
	</section>
	<jsp:include page="/frame/footer.jsp" />
</body>
</html>
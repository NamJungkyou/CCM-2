<%--
 
 프로젝트 참여신청 접수 페이지
 
 작성자 : 진재환
 
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 참여신청 접수</title>

<%-- 제이쿼리 임포트 --%>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

	<%-- 헤더 추가 --%>
	<jsp:include page="/frame/header.jsp"></jsp:include>


	<section id="container" class="">
		<section id="main-content">
			<section class="wrapper">

				<%-- 참여신청 접수 폼 --%>
				<form id="projectjoinaccept" action="ProjectServ" method="get">
					<input type="hidden" name="projJoinedFreePageNum"
						value="${projJoinedFreePageNum}">

					<section class="panel">
						<header class="panel-heading">프리랜서 참여신청 접수</header>
						<header class="panel-heading">
							참여신청한 프리랜서 <input type="text" name="searchFreelancerName"
								value="${searchFreelancerName}"> <input type="hidden"
								name="command" value="projectjoinaccept" /> <input
								type="submit" value="검색" /> <select name="listSortOption"
								onchange="document.getElementById('projectjoinaccept').submit()">

								<%-- 참여신청 목록 정렬 옵션 --%>
								<c:choose>
									<c:when test="${listSortOption == 1}">
										<option value="1" selected="selected">최신순</option>
									</c:when>
									<c:otherwise>
										<option value="1">최신순</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${listSortOption == 2}">
										<option value="2" selected="selected">신청마감일순</option>
									</c:when>
									<c:otherwise>
										<option value="2">신청마감일순</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${listSortOption == 3}">
										<option value="3" selected="selected">프로젝트명</option>
									</c:when>
									<c:otherwise>
										<option value="3">프로젝트명</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${listSortOption == 4}">
										<option value="4" selected="selected">이름</option>
									</c:when>
									<c:otherwise>
										<option value="4">이름</option>
									</c:otherwise>
								</c:choose>
							</select>
						</header>

						<%-- 참여신청 목록 --%>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>상태</th>
									<th>이름</th>
									<th>아이디</th>
									<th>참여신청한 프로젝트</th>
									<th>신청일</th>
									<th>모집마감일</th>
									<th>선택</th>
								</tr>
							</thead>
							<c:forEach var="projJoinedFreelancer"
								items="${projJoinedFreeList}">
								<tr>
									<td>${projJoinedFreelancer.freeState}</td>
									<td><a href="">${projJoinedFreelancer.freeName}</a></td>
									<td>${projJoinedFreelancer.freeId}</td>
									<td><a href="">${projJoinedFreelancer.projName}</a></td>
									<td>${projJoinedFreelancer.applicationDate}</td>
									<td>${projJoinedFreelancer.projRecruitEndDate}</td>

									<%-- 참여신청 접수인원 체크 --%>
									<td><input name="acceptedJoin" type="checkbox"
										value="${projJoinedFreelancer.joinNum}"></td>
								</tr>
							</c:forEach>
							
							<%-- 페이징 처리 --%>
							<tr>
								<td colspan="7"><c:choose>
										<%-- 페이지번호가 0보다 클 때 이전버튼 활성화되도록 함 --%>
										<c:when test="${(projJoinedFreePageNum - 1) > 0}">
											<a href="#" onclick="goToPrevPostPage(true)">이전</a>
										</c:when>
										<c:otherwise>이전</c:otherwise>
									</c:choose>
									<%-- 페이지 번호 뿌려줌 --%>
									<c:forEach var="pageNumIter"
										begin="${projJoinedFreeFirstPage}"
										end="${projJoinedFreeLastPage}">
										<c:choose>
											<c:when test="${pageNumIter == projJoinedFreePageNum}">${pageNumIter}</c:when>
											<c:otherwise>
												<a href="#" onclick="movePage(${pageNumIter})">${pageNumIter}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<%-- 페이지 수보다 페이지번호가 작으면 다음버튼 활성화 --%>
									<c:choose>
										<c:when test="${(projJoinedFreePageNum + 1) <= numOfPage}">
											<a href="#" onclick="goToPrevPostPage(false)">다음</a>
										</c:when>
										<c:otherwise>다음</c:otherwise>
									</c:choose> <input type="button" onclick="getCheckedJoinNum(true)"
									value="접수"> <input type="button"
									onclick="getCheckedJoinNum(false)'" value="거절"></td>
							</tr>
						</table>
					</section>
				</form>
			</section>
		</section>
	</section>
	
	<%-- 푸터 --%>
	<jsp:include page="/frame/footer.jsp"></jsp:include>

	<script>
	
	// 참여신청 목록에서 체크된 참여자들의 참여번호만 모아서 접수 액션으로 전송함
	function getCheckedJoinNum(state) // state는 접수인지 거절인지 확인하는 변수
	{
		var acceptCheckBox = document.getElementsByName("acceptedJoin");
		var acceptOrReject = state;
		
		var url = "ProjectServ?command=acceptorrejectproc"
			+ "&acceptOrReject=true"
			+ "&searchFreelancerName=" + "${searchFreelancer}"
			+ "&listSortOption=" + "${listSortOption}";
		
		// 체크된 참여번호를 링크에 붙임 
		for(var i = 0; i < acceptCheckBox.length; i++)
			if(acceptCheckBox[i].checked) url += "&acceptedJoin=" + acceptCheckBox[i].value;
		
		document.location.href=url;
	}
	
	// 이전이나 다음 페이지로 이동하는 함수
	// prevPost : true - 이전으로 이동, false - 다음으로 이동
	function goToPrevPostPage(prevPost)
	{
		var url = "ProjectServ?command=projectjoinaccept"
				+ "&searchFreelancerName=" + "${searchFreelancerName}"
				+ "&listSortOption=" + "${listSortOption}";
		if(prevPost == true)
		{
			url += "&projJoinedFreePageNum=" + ${projJoinedFreePageNum - 1};
		}
		else
		{
			url += "&projJoinedFreePageNum=" + ${projJoinedFreePageNum + 1};
		}
		
		document.location.href=url;
	}
	
	// 특정 페이지 번호로 이동시키는 함수
	function movePage(pageNum)
	{
		var url = "ProjectServ?command=projectjoinaccept"
			+ "&searchFreelancerName=" + "${searchFreelancerName}"
			+ "&listSortOption=" + "${listSortOption}"
			+ "&projJoinedFreePageNum=" + pageNum;
		
		document.location.href = url;
	}
</script>
</body>
</html>
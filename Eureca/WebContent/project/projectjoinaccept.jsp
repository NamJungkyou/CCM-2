<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 참여신청 접수</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<jsp:include page="/frame/header.jsp"></jsp:include>
	<section id="container" class="">
		<section id="main-content">
			<section class="wrapper">
				<form id="projectjoinaccept" action="ProjectServ" method="get">
					<input type="hidden" name="projJoinedFreePageNum" value="${projJoinedFreePageNum}">
					
					<section class="panel">
						<header class="panel-heading">프리랜서 참여신청 접수</header>
						<header class="panel-heading">
							참여신청한 프리랜서 <input type="text" name="searchFreelancerName"
								value="${searchFreelancerName}"> <input type="hidden"
								name="command" value="projectjoinaccept" /> <input type="submit"
								value="검색" /> <select name="listSortOption"
								onchange="document.getElementById('projectjoinaccept').submit()">
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
								<td><input name="acceptedJoin" type="checkbox"
									value="${projJoinedFreelancer.joinNum}"></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="7">
								<c:choose>
									<c:when test="${(projJoinedFreePageNum - 1) > 0}"><a href="#" onclick="goToPrevPostPage(true)">이전</a></c:when>
									<c:otherwise>이전</c:otherwise>
								</c:choose>
								<c:forEach
									var="pageNumIter" begin="${projJoinedFreeFirstPage}"
									end="${projJoinedFreeLastPage}">
									<c:choose>
										<c:when test="${pageNumIter == projJoinedFreePageNum}">${pageNumIter}</c:when>
										<c:otherwise><a href="#" onclick="movePage(${pageNumIter})">${pageNumIter}</a></c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(projJoinedFreePageNum + 1) <= numOfPage}"><a href="#" onclick="goToPrevPostPage(false)">다음</a></c:when>
									<c:otherwise>다음</c:otherwise>
								</c:choose>
								<input type="button"
								onclick="getCheckedJoinNum(true)" value="접수"> <input
								type="button" onclick="getCheckedJoinNum(false)'" value="거절">
							</td>
						</tr>
					</table>
					</section>
				</form>
			</section>
		</section>
	</section>
	<jsp:include page="/frame/footer.jsp"></jsp:include>

	<script>
	function getCheckedJoinNum(state)
	{
		var acceptCheckBox = document.getElementsByName("acceptedJoin");
		var acceptOrReject = state;
		
		var url = "ProjectServ?command=acceptorrejectproc"
			+ "&acceptOrReject=true"
			+ "&searchFreelancerName=" + "${searchFreelancer}"
			+ "&listSortOption=" + "${listSortOption}";
		for(var i = 0; i < acceptCheckBox.length; i++)
			if(acceptCheckBox[i].checked) url += "&acceptedJoin=" + acceptCheckBox[i].value;
		
		//alert(url);
		
		document.location.href=url;
	}
	
	function goToPrevPostPage(prevPost)
	{
		//alert(prevPost);
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
		
		//alert(url);
		
		document.location.href=url;
	}
	
	function movePage(pageNum)
	{
		var url = "ProjectServ?command=projectjoinaccept"
			+ "&searchFreelancerName=" + "${searchFreelancerName}"
			+ "&listSortOption=" + "${listSortOption}"
			+ "&projJoinedFreePageNum=" + pageNum;
		
			
		//alert(pageNum)
		
		document.location.href = url;
	}
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프리랜서 투입</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body>
	<jsp:include page="/frame/header.jsp"></jsp:include>
	<section id="container" class="">
		<section id="main-content">
			<section class="wrapper">
				<input type="button" value="프로젝트 검색"
					onclick="location.href='/Eureca/ProjectServ?command=gotoputinofsearchproject'">
				<form action="">
					<input type="hidden" name="curProjNum" value="">
					<section class="panel">
						<header class="panel-heading no-border">
							${curProject.projName}(등록일 :
							${curProject.projRegisterDate})${curProject.projState} </header>
						<table class="table table-bordered">
							<tr>
								<th>개발분야</th>
								<td>${curProject.projDevelopSort}</td>
								<th>시작일</th>
								<td>${curProject.projStartDate}</td>
								<th>예상기간</th>
								<td>${curProject.projExpectedTime}</td>
								<th>종료일</th>
								<td><c:if test="${curProject.projEndDate != null}">
						${curProject.projEndDate}
					</c:if></td>
							</tr>
							<tr>
								<th>참여인원</th>
								<td><c:set var="numOfProjJoinedFree" value="${0}"></c:set>
									<c:forEach var="joinedFree"
										items="${curProject.projJoinedFree}" varStatus="status">
										<c:set var="numOfProjJoinedFree"
											value="${numOfProjJoinedFree + status.count}"></c:set>
									</c:forEach> ${numOfProjJoinedFree}</td>
								<th>모집일</th>
								<td colspan="5">${curProject.projRecruitStartDate}~
									${curProject.projRecruitEndDate}</td>
							</tr>
							<tr>
								<th>언어</th>
								<td><c:forEach var="langs"
										items="${curProject.projProgLangs}" varStatus="status">
										<c:choose>
											<c:when test="${status.last}">
								${langs.langName}							
							</c:when>
											<c:otherwise>
								${langs.langName}&nbsp;/&nbsp;
							</c:otherwise>
										</c:choose>
									</c:forEach></td>
								<td>DBMS</td>
								<td>${curProject.projDBMSInfo.dbName}</td>
								<td>TOOL/framework</td>
								<td><c:forEach var="frames"
										items="${curProject.projFrameworks}" varStatus="status">
										<c:choose>
											<c:when test="${status.last}">
								${frames.frameName}							
							</c:when>
											<c:otherwise>
								${frames.frameName}&nbsp;/&nbsp;
							</c:otherwise>
										</c:choose>
									</c:forEach></td>
							</tr>
						</table>
					</section>
					<section class="panel">
						<header class="panel-heading no-border">채용 현황</header>
						<table class="table table-bordered">
							<tr>
								<th>모집분야</th>
								<th>모집인원</th>
								<th>현재인원</th>
								<th>부족인원</th>
								<th>추천인</th>
							</tr>
							<c:forEach var="recruit" items="${projRecruitStateInfo}"
								varStatus="status">
								<tr>
									<td>${recruit.roleName}</td>
									<td>${recruit.requireNum}</td>
									<td>${recruit.numOfJoined}</td>
									<td>${recruit.numOfLack}</td>
									<td><input type="button" value="검색" onclick=""></td>
								</tr>
							</c:forEach>
							<tr>
								<th>총계</th>
								<td><c:set var="requireNumSum" value="${0}"></c:set> <c:forEach
										var="pRequireNum" items="${projRecruitStateInfo}"
										varStatus="status">
										<c:set var="requireNumSum"
											value="${requireNumSum + pRequireNum.requireNum}"></c:set>
									</c:forEach> ${requireNumSum}</td>
								<td><c:set var="numOfJoinedSum" value="${0}"></c:set> <c:forEach
										var="pNumOfJoined" items="${projRecruitStateInfo}"
										varStatus="status">
										<c:set var="numOfJoinedSum"
											value="${numOfJoinedSum + pNumOfJoined.numOfJoined}"></c:set>
									</c:forEach> ${numOfJoinedSum}</td>
								<td><c:set var="numOfLackSum" value="${0}"></c:set> <c:forEach
										var="pNumOfLack" items="${projRecruitStateInfo}"
										varStatus="status">
										<c:set var="numOfLackSum"
											value="${numOfLackSum + pNumOfLack.numOfLack}"></c:set>
									</c:forEach> ${numOfLackSum}</td>
								<td><input type="button" value="검색" onclick=""></td>
							</tr>
						</table>
					</section>

					<section class="panel">
						<header class="panel-heading no-border">추천 프리랜서</header>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>기준</th>
									<th colspan="8">1순위 <select name="recommListSortOption1st"
										onchange="changeRecommFreeSortOption1st(this.value)">
											<c:choose>
												<c:when test="${recommListSortOption1st == 1}">
													<option value="1" selected="selected">등급</option>
												</c:when>
												<c:otherwise>
													<option value="1">등급</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption1st == 2}">
													<option value="2" selected="selected">평점</option>
												</c:when>
												<c:otherwise>
													<option value="2">평점</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption1st == 3}">
													<option value="3" selected="selected">언어</option>
												</c:when>
												<c:otherwise>
													<option value="3">언어</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption1st == 4}">
													<option value="4" selected="selected">프레임워크</option>
												</c:when>
												<c:otherwise>
													<option value="4">프레임워크</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption1st == 5}">
													<option value="5" selected="selected">학력</option>
												</c:when>
												<c:otherwise>
													<option value="5">학력</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption1st == 6}">
													<option value="6" selected="selected">경력</option>
												</c:when>
												<c:otherwise>
													<option value="6">경력</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption1st == 7}">
													<option value="7" selected="selected">남은기간</option>
												</c:when>
												<c:otherwise>
													<option value="7">남은기간</option>
												</c:otherwise>
											</c:choose>
									</select> 2순위 <select name="recommListSortOption2st"
										onchange="changeRecommFreeSortOption2st(this.value)">
											<c:choose>
												<c:when test="${recommListSortOption2st == 1}">
													<option value="1" selected="selected">등급</option>
												</c:when>
												<c:otherwise>
													<option value="1">등급</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption2st == 2}">
													<option value="2" selected="selected">평점</option>
												</c:when>
												<c:otherwise>
													<option value="2">평점</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption2st == 3}">
													<option value="3" selected="selected">언어</option>
												</c:when>
												<c:otherwise>
													<option value="3">언어</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption2st == 4}">
													<option value="4" selected="selected">프레임워크</option>
												</c:when>
												<c:otherwise>
													<option value="4">프레임워크</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption2st == 5}">
													<option value="5" selected="selected">학력</option>
												</c:when>
												<c:otherwise>
													<option value="5">학력</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption2st == 6}">
													<option value="6" selected="selected">경력</option>
												</c:when>
												<c:otherwise>
													<option value="6">경력</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption2st == 7}">
													<option value="7" selected="selected">남은기간</option>
												</c:when>
												<c:otherwise>
													<option value="7">남은기간</option>
												</c:otherwise>
											</c:choose>
									</select> 3순위 <select name="recommListSortOption3st"
										onchange="changeRecommFreeSortOption3st(this.value)">
											<c:choose>
												<c:when test="${recommListSortOption3st == 1}">
													<option value="1" selected="selected">등급</option>
												</c:when>
												<c:otherwise>
													<option value="1">등급</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption3st == 2}">
													<option value="2" selected="selected">평점</option>
												</c:when>
												<c:otherwise>
													<option value="2">평점</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption3st == 3}">
													<option value="3" selected="selected">언어</option>
												</c:when>
												<c:otherwise>
													<option value="3">언어</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption3st == 4}">
													<option value="4" selected="selected">프레임워크</option>
												</c:when>
												<c:otherwise>
													<option value="4">프레임워크</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption3st == 5}">
													<option value="5" selected="selected">학력</option>
												</c:when>
												<c:otherwise>
													<option value="5">학력</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption3st == 6}">
													<option value="6" selected="selected">경력</option>
												</c:when>
												<c:otherwise>
													<option value="6">경력</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${recommListSortOption3st == 7}">
													<option value="7" selected="selected">남은기간</option>
												</c:when>
												<c:otherwise>
													<option value="7">남은기간</option>
												</c:otherwise>
											</c:choose>
									</select>
									</th>
								</tr>
							</thead>
							<tr>
								<th>상태</th>
								<th>이름</th>
								<th>주언어</th>
								<th>가용 Tool / Framework</th>
								<th>경력</th>
								<th>남은 기간</th>
								<th>KOSA 등급</th>
								<th>평점</th>
								<th>선택</th>
							</tr>
							<c:forEach var="recommFree" items="${recommFreelancer}"
								varStatus="status">
								<tr>
									<td>${recommFree.freeState}</td>
									<td>${recommFree.freeName}</td>
									<td>${recommFree.primaryLangs}</td>
									<td>${recommFree.primaryFrames}</td>
									<td>${recommFree.projCareerYears}년</td>
									<td>${recommFree.leftJoinDays}</td>
									<td>${recommFree.freeKosa}</td>
									<td>${recommFree.freeScore}</td>
									<td><input type="checkbox" name="putInFreeId"
										value="${recommFree.freeId}"></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="9"><c:choose>
										<c:when test="${curRecommPageNum == 1}">
							이전
						</c:when>
										<c:otherwise>
											<span onclick="changePageNum(${curRecommPageNum - 1}, true)"
												style="cursor: pointer">이전</span>
										</c:otherwise>
									</c:choose> <c:forEach var="pageNumIter" begin="${recommFreeFirstPage}"
										end="${recommFreeLastPage}">
										<span onclick="changePageNum(${pageNumIter}, true)"
											style="cursor: pointer">${pageNumIter}</span>
									</c:forEach> <c:choose>
										<c:when test="${curRecommPageNum == recommFreeNumOfPage}">
							다음
						</c:when>
										<c:otherwise>
											<span onclick="changePageNum(${curRecommPageNum + 1}, true)"
												style="cursor: pointer">다음</span>
										</c:otherwise>
									</c:choose> <input type="button" onclick="addToPutInFreelacerList()"
									value="투입목록에 추가"></td>
							</tr>
						</table>
					</section>

					<section class="panel">
						<header class="panel-heading no-border">
							참여 신청한 프리랜서 정렬기준 <select name="appliedFreeListSortOption"
								onchange="changeAppliedFreeSortOption(this.value)">
								<!-- <select name="appliedFreeListSortOption" onchange="alert(this.value)"> -->
								<c:choose>
									<c:when test="${appliedFreeListSortOption == 1}">
										<option value="1" selected="selected">최신순</option>
									</c:when>
									<c:otherwise>
										<option value="1">최신순</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${appliedFreeListSortOption == 2}">
										<option value="2" selected="selected">참여자</option>
									</c:when>
									<c:otherwise>
										<option value="2">참여자</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${appliedFreeListSortOption == 3}">
										<option value="3" selected="selected">대기자</option>
									</c:when>
									<c:otherwise>
										<option value="3">대기자</option>
									</c:otherwise>
								</c:choose>
							</select>
						</header>
						<table class="table table-bordered">
							<tr>
								<th>상태</th>
								<th>이름</th>
								<th>주언어</th>
								<th>가용 Tool / framework</th>
								<th>경력</th>
								<th>남은기간</th>
								<th>KOSA등급</th>
								<th>평점</th>
								<th>선택</th>
							</tr>
							<c:forEach var="apply" items="${appliedFreelancer}">
								<tr>
									<td>${apply.freeState}</td>
									<td>${apply.freeName}</td>
									<td>${apply.primaryLangs}</td>
									<td>${apply.primaryFrames}</td>
									<td>${apply.projCareerYears}년</td>
									<td>${apply.leftJoinDays}</td>
									<td>${apply.freeKosa}</td>
									<td>${apply.freeScore}</td>
									<td><input type="checkbox" name="putInFreeId"
										value="${apply.freeId}"></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="9"><c:choose>
										<c:when test="${curAppliedFreePageNum == 1}">
							이전
						</c:when>
										<c:otherwise>
											<span
												onclick="changePageNum(${curAppliedPageNum - 1}, false)"
												style="cursor: pointer">이전</span>
										</c:otherwise>
									</c:choose> <c:forEach var="pageNumIter" begin="${appliedFreeFirstPage}"
										end="${appliedFreeLastPage}">
										<span onclick="changePageNum(${pageNumIter}, false)"
											style="cursor: pointer">${pageNumIter}</span>
									</c:forEach> <c:choose>
										<c:when
											test="${curAppliedFreePageNum == appliedFreeNumOfPage}">
							다음
						</c:when>
										<c:otherwise>
											<span
												onclick="changePageNum(${curAppliedFreePageNum + 1}, false)"
												style="cursor: pointer">다음</span>
										</c:otherwise>
									</c:choose> <input type="button" onclick="addToPutInFreelacerList()"
									value="투입목록에 추가"></td>
							</tr>
						</table>
					</section>

					<input type="button" value="프리랜서 검색" onclick="">
					<c:if test="${freelancerListToPutIn != null}">
						<section class="panel">
							<header class="panel-heading no-border">투입할 프리랜서 목록</header>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>상태</th>
										<th>이름</th>
										<th>주언어</th>
										<th>가용 Tool / framework</th>
										<th>경력</th>
										<th>남은기간</th>
										<th>KOSA등급</th>
										<th>평점</th>
										<th>선택</th>
									</tr>
								</thead>
								<c:forEach var="putIn" items="${freelancerListToPutIn}">
									<tr>
										<td>${putIn.freeState}</td>
										<td>${putIn.freeName}</td>
										<td>${putIn.primaryLangs}</td>
										<td>${putIn.primaryFrames}</td>
										<td>${putIn.projCareerYears}</td>
										<td>${putIn.leftJoinDays}</td>
										<td>${putIn.freeKosa}</td>
										<td>${putIn.freeScore}</td>
										<td><input type="checkbox" name="putInFreeIdReal"
											value="${putIn.freeId}"></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="9"><input type="button" value="투입"
										onclick="putInFreelancerPopUp()"></td>
								</tr>
							</table>
						</section>
					</c:if>
				</form>
			</section>
		</section>
	</section>

	<jsp:include page="/frame/footer.jsp"></jsp:include>

	<script>
	function addToPutInFreelacerList()
	{
		var freeIdCheckBox = document.getElementsByName("putInFreeId");
		//alert("아아앙");
		
		var url = "/Eureca/ProjectServ?command=addtoputinfreelancerlist"
			+ "&curProjNum=${curProjNum}"
			+ "&recommListSortOption1st=${recommListSortOption1st}"
			+ "&recommListSortOption2st=${recommListSortOption2st}"
			+ "&recommListSortOption3st=${recommListSortOption3st}"
			+ "&appliedFreeListSortOption=${appliedFreeListSortOption}"
			+ "&freelancerListToPutInSortOption=${freelancerListToPutInSortOption}"
			+ "&curRecommPageNum=${curRecommPageNum}"
			+ "&curAppliedFreePageNum=${curAppliedFreePageNum}";
		for(var i = 0; i < freeIdCheckBox.length; i++)
			if(freeIdCheckBox[i].checked) url += "&putInFreeId=" + freeIdCheckBox[i].value;
			
		document.location.href=url;
	}
	
	function changePageNum(pageNum, isRecommPage)
	{
		var freeIdCheckBox = document.getElementsByName("putInFreeId");
		//alert("아아앙");
		
		var url = "/Eureca/ProjectServ?command=putfreelancer"
			+ "&curProjNum=${curProjNum}"
			+ "&recommListSortOption1st=${recommListSortOption1st}"
			+ "&recommListSortOption2st=${recommListSortOption2st}"
			+ "&recommListSortOption3st=${recommListSortOption3st}"
			+ "&appliedFreeListSortOption=${appliedFreeListSortOption}"
			+ "&freelancerListToPutInSortOption=${freelancerListToPutInSortOption}";
			if(isRecommPage)
				url += "&curRecommPageNum=" + pageNum
				+ "&curAppliedFreePageNum=${curAppliedFreePageNum}";
			else
				url += "&curRecommPageNum=${curRecommPageNum}"
				+ "&curAppliedFreePageNum=" + pageNum;
				
		for(var i = 0; i < freeIdCheckBox.length; i++)
			if(freeIdCheckBox[i].checked) url += "&putInFreeId=" + freeIdCheckBox[i].value;
		
		document.location.href=url;
	}
	
	function changeRecommFreeSortOption1st(option)
	{
		alert(option);
		var freeIdCheckBox = document.getElementsByName("putInFreeId");
		
		var url = "/Eureca/ProjectServ?command=addtoputinfreelancerlist"
			+ "&curProjNum=${curProjNum}"
			+ "&recommListSortOption1st=" + option
			+ "&recommListSortOption2st=${recommListSortOption2st}"
			+ "&recommListSortOption3st=${recommListSortOption3st}"
			+ "&appliedFreeListSortOption=${appliedFreeListSortOption}"
			+ "&freelancerListToPutInSortOption=${freelancerListToPutInSortOption}"
			+ "&curRecommPageNum=${curRecommPageNum}"
			+ "&curAppliedFreePageNum=${curAppliedFreePageNum}";
		for(var i = 0; i < freeIdCheckBox.length; i++)
			if(freeIdCheckBox[i].checked) url += "&putInFreeId=" + freeIdCheckBox[i].value;
			
		document.location.href=url;
	}
	
	function changeRecommFreeSortOption2st(option)
	{
		alert(option);
		var freeIdCheckBox = document.getElementsByName("putInFreeId");
		
		var url = "/Eureca/ProjectServ?command=addtoputinfreelancerlist"
			+ "&curProjNum=${curProjNum}"
			+ "&recommListSortOption1st=${recommListSortOption1st}"
			+ "&recommListSortOption2st=" + option
			+ "&recommListSortOption3st=${recommListSortOption3st}"
			+ "&appliedFreeListSortOption=${appliedFreeListSortOption}"
			+ "&freelancerListToPutInSortOption=${freelancerListToPutInSortOption}"
			+ "&curRecommPageNum=${curRecommPageNum}"
			+ "&curAppliedFreePageNum=${curAppliedFreePageNum}";
		for(var i = 0; i < freeIdCheckBox.length; i++)
			if(freeIdCheckBox[i].checked) url += "&putInFreeId=" + freeIdCheckBox[i].value;
			
		document.location.href=url;
	}
	
	function changeRecommFreeSortOption3st(option)
	{
		alert(option);
		var freeIdCheckBox = document.getElementsByName("putInFreeId");
		
		var url = "/Eureca/ProjectServ?command=addtoputinfreelancerlist"
			+ "&curProjNum=${curProjNum}"
			+ "&recommListSortOption1st=${recommListSortOption1st}"
			+ "&recommListSortOption2st=${recommListSortOption2st}"
			+ "&recommListSortOption3st=" + option
			+ "&appliedFreeListSortOption=${appliedFreeListSortOption}"
			+ "&freelancerListToPutInSortOption=${freelancerListToPutInSortOption}"
			+ "&curRecommPageNum=${curRecommPageNum}"
			+ "&curAppliedFreePageNum=${curAppliedFreePageNum}";
		for(var i = 0; i < freeIdCheckBox.length; i++)
			if(freeIdCheckBox[i].checked) url += "&putInFreeId=" + freeIdCheckBox[i].value;
			
		document.location.href=url;
	}
	
	function changeAppliedFreeSortOption(option)
	{
		alert(option);
		var freeIdCheckBox = document.getElementsByName("putInFreeId");
		
		var url = "/Eureca/ProjectServ?command=addtoputinfreelancerlist"
			+ "&curProjNum=${curProjNum}"
			+ "&recommListSortOption1st=${recommListSortOption1st}"
			+ "&recommListSortOption2st=${recommListSortOption2st}"
			+ "&recommListSortOption3st=${recommListSortOption3st}"
			+ "&appliedFreeListSortOption=" + option
			+ "&freelancerListToPutInSortOption=${freelancerListToPutInSortOption}"
			+ "&curRecommPageNum=${curRecommPageNum}"
			+ "&curAppliedFreePageNum=${curAppliedFreePageNum}";
		for(var i = 0; i < freeIdCheckBox.length; i++)
			if(freeIdCheckBox[i].checked) url += "&putInFreeId=" + freeIdCheckBox[i].value;
			
		document.location.href=url;
	}
	
	function putInOrDelete()
	{
		var freeIdCheckBox = document.getElementsByName("putInFreeId");
		//alert("아아앙");
		
		var url = "/Eureca/ProjectServ?command=addtoputinfreelancerlist"
			+ "&curProjNum=${curProjNum}"
			+ "&recommListSortOption1st=${recommListSortOption1st}"
			+ "&recommListSortOption2st=${recommListSortOption2st}"
			+ "&recommListSortOption3st=${recommListSortOption3st}"
			+ "&appliedFreeListSortOption=${appliedFreeListSortOption}"
			+ "&freelancerListToPutInSortOption=${freelancerListToPutInSortOption}";
			+ "&curRecommPageNum=${curRecommPageNum}"
			+ "&curAppliedFreePageNum=${curAppliedFreePageNum}"
		for(var i = 0; i < freeIdCheckBox.length; i++)
			if(freeIdCheckBox[i].checked) url += "&putInFreeId=" + freeIdCheckBox[i].value;
			
		document.location.href=url;
	}
	
	function putInFreelancerPopUp()
	{
		var freeIdCheckBox = document.getElementsByName("putInFreeIdReal");
		var projNum = ${curProject.projNum};
		var projName = "${curProject.projName}";
		
		var url = "ProjectServ?command=putrequestordelete" + "&projNum=" + projNum + "&projName=" + projName;
		
		for(var i = 0; i < freeIdCheckBox.length; i++)
			if(freeIdCheckBox[i].checked) url += "&freeIds=" + freeIdCheckBox[i].value;
		
		//alert(projNum + ", " + url);
		
		window.open(url, "", "width=400, height=300, left=500, top=400");
	}
</script>
</body>
</html>
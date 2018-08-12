<%--

	프리랜서 계정등록 jsp

	작성자 : 글로벌IT경영 김민현
	
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프리랜서 등록</title>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="css/dropdown.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="/frame/header.jsp"></jsp:include>

	<section id="container" class="">
		<section id="main-content">
			<section class="wrapper">
			<!-- 사진 업로드를 위해 form태그안에 enctype을 multipart/form-data로 설정 -->
			<!-- 기존의 request로 Parameter값을 받지 못하고 multi로 값을 받아야함 자세한건 Action참조 -->
				<form name="frm" method="post" enctype="multipart/form-data">
					<!-- <input type="hidden" name="command" value="freelancer_Profile_update"> -->
					<section class="panel">
						<header class="panel-heading no-border"> 프리랜서 등록 </header>
						<table class="table table-bordered">
							<tr>
								<th>아이디</th>
								<td colspan="2"><input type="text" name="freeId"
									value="${freeId}">
									<!-- 아이디 중복체크버튼 -->
								<input type="button" value="중복체크" onclick="idCheck()"></td>
								<th>이메일</th>
								<td colspan="3"><input type="text" name="freeEmail"
									value="${freeEmail}"> 
								<!-- 이메일 중복체크버튼(있어도되고 없어도되고) --> 
								<input type="button" value="중복체크" onclick="emailCheck()"></td>
							</tr>

							<tr>
								<th>비밀번호</th>
								<td colspan="2"><input type="password" name="freePw"
									value="${freePw}"></td>
								<th>비밀번호 확인</th>
								<td colspan="3"><input type="password" name="freePw2" value="${freePw}"></td>
							</tr>

							<tr>
								<th rowspan="5">사진</th>
								<td rowspan="5" colspan="2"><%-- <img
									src="/Eureca/upload/${freelancer.freePic}"> --%> <input
									type="file" name="freePic" value="${freelancer.freePic}">
								</td>
								<th>이름</th>
								<td><input type="text" name="freeName" value="${freeName}"></td>
								<th>생년월일</th>
								<td><input type="text" name="freeBirth" class="datepicker"
									value="${freeBirth}"></td>
							</tr>

							<tr>
								<th>성별</th>
								<td><c:if test="${freeSex == true}">
										<c:set var="sexCheck" value="checked" />
									</c:if> <input type="radio" name="freeSex" value="true" ${sexCheck} id="man">
									<c:set var="sexCheck" value="" /><label for="man">남자</label> <c:if
										test="${freeSex == false}">
										<c:set var="sexCheck2" value="checked" />
									</c:if> <input type="radio" name="freeSex" value="false" ${sexCheck2} id="woman">
									<c:set var="sexCheck2" value="" /><label for="woman">여자</label></td>

								<th>결혼여부</th>
								<td><c:if test="${freeMarried == false}">
										<c:set var="marriedCheck" value="checked" />
									</c:if> <input type="radio" name="freeMarried" value="false"
									${marriedCheck} id="single"> <c:set var="marriedCheck" value="" />
									<label for="single">미혼</label><c:if test="${freeMarried == true}">
										<c:set var="marriedCheck2" value="checked" />
									</c:if> <input type="radio" name="freeMarried" value="true"
									${marriedCheck2} id="married"> <c:set var="marriedCheck2" value="" />
									<label for="married">기혼</label></td>
							</tr>

							<tr>
								<th>연락처</th>
								<td><input type="text" name="freePhone"
									value="${freePhone}"> <input type="button" value="중복체크"
									onclick="phoneCheck()"></td>
							</tr>

							<tr>
								<th rowspan="2">주소</th>
								<td colspan="3"><input type="text" id="roadAddrPart1"
									name="freeFrontAddr" value="${freeFrontAddr}" size="20">
								<!-- 주소검색 API호출 --> 
								<input type="button" onClick="goPopup();" value="주소찾기" /></td>
							</tr>

							<tr>
								<!-- FrontAddr은 API에서 검색한 값을 바탕으로 자동입력되고 addrDetail값은 관리자가 직접 입력해야함 -->
								<td colspan="3"><input type="text" id="addrDetail"
									name="freeRearAddr" value="${freeRearAddr}"></td>
							</tr>

						</table>

					</section>

				    <!-- 계좌정보등록 -->
					<section class="panel">
						<header class="panel-heading no-border"> 계좌등록 </header>
						<table class="table table-bordered">
							<tr>
								<th>은행</th>
								<td><select id="freeBank" name="freeBank" size="1">
										<option value="${freeBank}">${freeBank}</option>
										<option value="KB국민">KB국민</option>
										<option value="하나은행">하나은행</option>
										<option value="기업은행">기업은행</option>
										<option value="NH농협">NH농협</option>
										<option value="신한은행">신한은행</option>
										<option value="신협">신협</option>
										<option value="우리은행">우리은행</option>
										<option value="카카오뱅크">카카오뱅크</option>
										<option value="대구은행">대구은행</option>
										<option value="부산은행">부산은행</option>
								</select></td>

								<th>계좌 명의</th>
								<td><input type="text" name="freeAccName"
									value="${freeAccName}"></td>
							</tr>

							<tr>
								<th>계좌번호</th>
								<td><input type="text" name="freeAccount"
									value="${freeAccount}"></td>
							</tr>
						</table>
					</section>
				</form>

				<form name="frm2" method="post">
					<!-- <input type="button" value="등록" onclick="return empFreeInsert()"> -->
					<section class="panel">
						<header class="panel-heading no-border"> 학력 </header>
						<table class="table table-bordered">

							<thead>
								<tr>
									<th>학교</th>
									<th>학과</th>
									<th>학위</th>
									<th>입학일</th>
									<th>졸업일</th>
								</tr>
							</thead>
							<c:forEach var="Education" items="${Education}">
								<tr>
								<!-- eduNum은 따로 등록하거나 수정하는게 아니라 자동으로 설정되는 값이므로 hidden타입으로 설정 -->
									<td><input type="hidden" name="eduNum"
										value="${Education.eduNum}"> <input type="text"
										name="eduSchool" value="${Education.eduSchool}"></td>
									<td><input type="text" name="eduMajor"
										value="${Education.eduMajor}"></td>
									<td><input type="text" name="eduDeploma"
										value="${Education.eduDeploma}"></td>
									<td><input type="text" name="schoolJoinDate"
										value="${Education.schoolJoinDate}"></td>
									<td><input type="text" name="schoolGraduatedDate"
										value="${Education.schoolGraduatedDate}"></td>
								</tr>
							</c:forEach>
							<!-- javascript에서 행추가 및 행삭제 기능을 실행하기 위해 tbody id를 empFreeEducationTable로 설정 -->
							<tbody id="empFreeEducationTable">
							</tbody>
						</table>
						<input type="button" onclick="add_Edu_row()" value="행추가">
						<input type="button" onclick="delete_Edu_row()" value="행삭제">
						<%-- <input type="button" value="등록"
							onclick="return empFreeEducationInsert('${freeId}')"> --%>
					</section>
					<section class="panel">
						<header class="panel-heading no-border"> 경력 </header>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>회사명</th>
									<th>근무기간</th>
									<th>직위</th>
									<th>담당업무</th>
								</tr>
							</thead>

							<c:forEach var="Career" items="${Career}">
								<tr>
								<!-- careerNum은 따로 등록하거나 수정하는게 아니라 자동으로 설정되는 값이므로 hidden타입으로 설정 -->
									<td><input type="hidden" name="careerNum"
										value="${Career.careerNum}"> <input type="text"
										name="careerCompany" value="${Career.careerCompany}"></td>
									<td><input type="text" name="companyJoinDate"
										value="${Career.companyJoinDate}"> ㅡ <input
										type="text" name="companyDropDate"
										value="${Career.companyDropDate}"></td>

									<td><input type="text" name="careerPosition"
										value="${Career.careerPosition}"></td>
									<td><input type="text" name="careerJob"
										value="${Career.careerJob}"></td>
								</tr>
							</c:forEach>
							<!-- javascript에서 행추가 및 행삭제 기능을 실행하기 위해 tbody id를 empFreeCareerTable로 설정 -->
							<tbody id="empFreeCareerTable">
							</tbody>
						</table>

						<input type="button" onclick="add_Career_row()" value="행추가">
						<input type="button" onclick="delete_Career_row()" value="행삭제">
						<!-- 한번에 전체등록 -->
						<input type="button" value="등록"
                    	 onclick="return empFreeTestInsert()">
					</section>
					<section class="panel">
						<header class="panel-heading no-border"> Skill Inventory
						</header>
						<table class="table table-bordered" id="skillInv">
							<thead>
								<tr>
									<th>프로젝트명</th>
									<th>참여기간</th>
									<th>고객사</th>
									<th>근무회사</th>
									<th>역할</th>
									<th>언어</th>
									<th>DBMS</th>
									<th>TOOL/framework</th>
								</tr>
							</thead>
							<c:forEach var="skillInventoryList" items="${SkillInventory}"
								varStatus="skill">
								<tr>
									<c:choose>
										<c:when test="${skillInventoryList.isExtern == 1}">
											<td><input type="text"
												value="${skillInventoryList.projName}"></td>
											<td><input type="text"
												value="${skillInventoryList.joinProjDate}"> ~ <input
												type="text" value="${skillInventoryList.exitProjDate}"></td>
											<td><input type="text"
												value="${skillInventoryList.projTarget}"></td>
											<td><input type="text"
												value="${skillInventoryList.projCompany}"></td>
											<td><input type="text"
												value="${skillInventoryList.projRole}"></td>
										</c:when>
										<c:otherwise>
											<td>${skillInventoryList.projName}</td>
											<td>${skillInventoryList.joinProjDate}~
												${skillInventoryList.exitProjDate}</td>
											<td>${skillInventoryList.projTarget}</td>
											<td>${skillInventoryList.projCompany}</td>
											<td>${skillInventoryList.projRole}</td>
										</c:otherwise>
									</c:choose>
									<td><select multiple="multiple">
											<c:set var="selected" value=""></c:set>
											<c:forEach var="projSkillLangs" items="${langDbFrame.lang}">
												<c:forEach var="skillLangs"
													items="${skillInventoryList.projlangs}">
													<c:choose>
														<c:when
															test="${skillLangs.langNum == projSkillLangs.langNum}">
															<c:set var="selected" value="${\"selected\"}"></c:set>
														</c:when>
														<c:otherwise>
															<c:set var="selected" value=""></c:set>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<option value="${projSkillLangs.langNum}" ${selected}>
													${projSkillLangs.langName}</option>
												<c:set var="selected" value=""></c:set>
											</c:forEach>
									</select></td>
									<td><c:choose>
											<c:when test="${skillInventoryList.isExtern == 1}">
												<select name="dbNum">
													<c:forEach var="projSkillDbmss" items="${langDbFrame.dbms}">
														<c:choose>
															<c:when
																test="${skillInventoryList.db.dbNum == projSkillDbmss.dbNum}">
																<option value="${projSkillDbmss.dbNum}"
																	selected="selected">${projSkillDbmss.dbName}</option>
															</c:when>
															<c:otherwise>
																<option value="${projSkillDbmss.dbNum}">
																	${projSkillDbmss.dbName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</c:when>
											<c:otherwise>
								${skillInventoryList.db.dbName}
							</c:otherwise>
										</c:choose></td>
									<td></td>
								</tr>
							</c:forEach>
							<tbody id="SkillTable">
							</tbody>
						</table>
						<input type="button" onclick="add_row3()" value="행추가"> <input
							type="button" onclick="delete_row3()" value="행삭제"> <input
							type="button" value="등록" onclick="return SkillInventoryUpdate()">
					</section>
				</form>
			</section>
		</section>
	</section>
	<jsp:include page="/frame/footer.jsp"></jsp:include>
	<!-- <script type="text/javascript" src="/Eureca/script/freelancer.js"></script> -->
	<script type="text/javascript" src="script/freelancer.js"></script>
</body>
</html>
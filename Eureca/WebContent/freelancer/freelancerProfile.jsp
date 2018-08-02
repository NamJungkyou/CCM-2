<%--
	로그인한 프리랜서의 프로필을 출력하는 화면
	
	SkillIventory 작성 : 남정규
	
 --%>

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
				<!-- <form name="frm" method="post" action="FreelancerServ"> -->
				<form name="frm" method="post" enctype="multipart/form-data">
					<!-- <input type="hidden" name="command" value="freelancer_Profile_update"> -->
					<section class="panel">
						<header class="panel-heading no-border">내 프로필</header>
						<table class="table table-bordered">
							<tr>
								<th>아이디</th>
								<td><input type="hidden" name="freeId"
									value="${freelancer.freeId}">${freelancer.freeId}</td>
								<th>이메일</th>
								<td colspan="3"><input type="text" name="freeEmail"
									value="${freelancer.freeEmail}"><input type="button"
									value="중복체크" onclick="emailCheck()"></td>
							</tr>

							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="freePw"
									value="${freelancer.freePw}"></td>
								<th>비밀번호 확인</th>
								<td colspan="3"><input type="password" name="freePw2"
									value="${freelancer.freePw}"></td>
							</tr>

							<tr>
								<td rowspan="3" colspan="2" class="img-align-center"><div class="insert-picture">
										<img src="/Eureca/upload/${freelancer.freePic}">
									</div>
									<input type="file" name="freePic" value="${freelancer.freePic}">
								</td>
								<th>이름</th>
								<td><input type="text" name="freeName"
									value="${freelancer.freeName}"></td>
								<th>생년월일</th>
								<td><input type="text" name="freeBirth"
									value="${freelancer.freeBirth}"></td>
							</tr>

							<tr>
								<th>성별</th>
								<td><c:if test="${freelancer.freeSex == true}">
										<c:set var="sexCheck" value="checked" />
									</c:if> <input type="radio" name="freeSex"
									value="${freelancer.freeSex}" ${sexCheck}> <c:set
										var="sexCheck" value="" /> 남자 <c:if
										test="${freelancer.freeSex == false}">
										<c:set var="sexCheck2" value="checked" />
									</c:if> <input type="radio" name="freeSex"
									value="${freelancer.freeSex}" ${sexCheck2}> <c:set
										var="sexCheck2" value="" /> 여자</td>

								<th>결혼여부</th>
								<td><c:if test="${freelancer.freeMarried == false}">
										<c:set var="marriedCheck" value="checked" />
									</c:if> <input type="radio" name="freeMarried"
									value="${freelancer.freeMarried}" ${marriedCheck}> <c:set
										var="marriedCheck" value="" /> 미혼<c:if
										test="${freelancer.freeMarried == true}">
										<c:set var="marriedCheck2" value="checked" />
									</c:if> <input type="radio" name="freeMarried"
									value="${freelancer.freeMarried}" ${marriedCheck2}> <c:set
										var="marriedCheck2" value="" /> 기혼</td>
							</tr>

							<tr>
								<th>연락처</th>
								<td colspan="3"><input type="text" name="freePhone"
									value="${freelancer.freePhone}"></td>
							</tr>

							<tr>
								<th rowspan="2">주소</th>
								<td colspan="5"><input type="text" id="roadAddrPart1"
									name="freeFrontAddr" value="${freelancer.freeFrontAddr}"
									size="20"><input type="button"
									onClick="goPopup();" value="주소찾기" /></td>
							</tr>

							<tr>
								<td colspan="5"><input type="text" id="addrDetail"
									name="freeRearAddr" value="${freelancer.freeRearAddr}"></td>
							</tr>

						</table>
						<input type="button" value="수정" onclick="return ProfileUpdate()">
						<input type="button" value="탈퇴" onclick="return ProfileDelete()">
					</section>
				</form>

				<form name="frm2" method="post">
					<section class="panel">
						<header class="panel-heading no-border">계좌등록</header>
						<table class="table table-bordered">
							<tr>
								<th>은행</th>
								<td><select id="freeBank" name="freeBank" size="1">
										<option value="${freelancer2.freeBank}">${freelancer2.freeBank}</option>
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
									value="${freelancer2.freeAccName}"></td>
							</tr>

							<tr>
								<th>계좌번호</th>
								<td colspan="3"><input type="text" name="freeAccount"
									value="${freelancer2.freeAccount}"></td>
							</tr>
						</table>

						<input type="button" value="등록"
							onclick="return BankUpdate('${freeId}')">
					</section>

					<section class="panel">
						<header class="panel-heading no-border">학력</header>
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
							<tbody id="my-tbody">
							</tbody>
						</table>
						<input type="button" onclick="add_row()" value="행추가"> <input
							type="button" onclick="delete_row()" value="행삭제"> <input
							type="button" value="등록"
							onclick="return EducationUpdate('${freeId}')">
					</section>

					<section class="panel">
						<header class="panel-heading no-border">경력</header>
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
							<tbody id="CareerTable">
							</tbody>
						</table>

						<input type="button" onclick="add_row2()" value="행추가"> <input
							type="button" onclick="delete_row2()" value="행삭제"> <input
							type="button" value="등록"
							onclick="return CareerUpdate('${freeId}')">
					</section>
					
					<!-- 스킬인벤토리 시작 -->
					<section class="panel" style="overflow: scroll;">
						<header class="panel-heading no-border">Skill Inventory</header>
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
							<tbody id="SkillTable">
								<c:forEach var="skillInventoryList" items="${SkillInventory}"
									varStatus="skill">
									<input type="hidden" name="isExtern"
										value="${skillInventoryList.isExtern}">
									<tr>
										<c:choose>
											<!-- 외부 프로젝트면 수정이 가능하도록 input박스에 출력 -->
											<c:when test="${skillInventoryList.isExtern == 1}">
												<input type="hidden" name="projNum"
													value="${skillInventoryList.projNum}">
												<td><input type="text" name="projName"
													value="${skillInventoryList.projName}"></td>
												<td><input type="text" name="joinProjDate"
													value="${skillInventoryList.joinProjDate}"> ~ <input
													type="text" name="exitProjDate"
													value="${skillInventoryList.exitProjDate}"></td>
												<td><input type="text" name="projTarget"
													value="${skillInventoryList.projTarget}"></td>
												<td><input type="text" name="projCompany"
													value="${skillInventoryList.projCompany}"></td>
												<td><input type="text" name="projRole"
													value="${skillInventoryList.projRole}"></td>
											</c:when>
											<!-- 내부 프로젝트면 수정이 불가능하도록 그냥 출력 -->
											<c:otherwise>
												<input type="hidden" name="projNum"
													value="${skillInventoryList.projNum}">
												<td><input type="hidden" name="projName"
													value="${skillInventoryList.projName}">${skillInventoryList.projName}</td>
												<td><input type="hidden" name="joinProjDate"
													value="${skillInventoryList.joinProjDate}">${skillInventoryList.joinProjDate}~
													<input type="hidden" name="exitProjDate"
													value="${skillInventoryList.exitProjDate}">${skillInventoryList.exitProjDate}</td>
												<td><input type="hidden" name="projTarget"
													value="${skillInventoryList.projTarget}">${skillInventoryList.projTarget}</td>
												<td><input type="hidden" name="projCompany"
													value="${skillInventoryList.projCompany}">${skillInventoryList.projCompany}</td>
												<td><input type="hidden" name="projRole"
													value="${skillInventoryList.projRole}">${skillInventoryList.projRole}</td>
											</c:otherwise>
										</c:choose>
										<td><c:choose>
												<!-- 외부 프로젝트면 수정 가능하도록 출력  -->
												<c:when test="${skillInventoryList.isExtern == 1}">
													<!-- 드롭다운체크박스로 여러개의 언어를 선택할 수 있게 함 -->
													<div class="checkbox-dropdown">
														선택
														<ul class="checkbox-dropdown-list">
															<!-- progLang에 등록되어있는 모든 언어를 출력 -->
															<c:forEach var="projSkillLangs"
																items="${langDbFrame.lang}">
																<c:set var="langCheck" value="" />
																<c:forEach var="skillProjLang"
																	items="${skillInventoryList.projlangs}">
																	<!-- 이 프로젝트에서 사용된 언어가 있으면 checked -->
																	<c:if
																		test="${skillProjLang.langName eq projSkillLangs.langName}">
																		<c:set var="langCheck" value="checked" />
																	</c:if>
																</c:forEach>
																<!-- 몇번째 프로젝트의 체크박스인지 식별할 수 있도록 langNum에 skilInventoryList의 인데스값을 합침 -->
																<li><label><input type="checkbox"
																		name="langNum${skill.index}"
																		value="${projSkillLangs.langNum}" ${langCheck} />
																		${projSkillLangs.langName}</label> <c:set var="langCheck"
																		value="" /></li>
															</c:forEach>
														</ul>
													</div>
												</c:when>
												<c:otherwise>
													<input type="hidden" name="langNum${skill.index}" value="0">
													<!-- 내부프로젝트이면 수정이 불가능하도록 '/'로 구분하여 한줄로 출력 -->
													<c:forEach var="skillProjLang"
														items="${skillInventoryList.projlangs}" varStatus="status">
														${skillProjLang.langName}<c:if test="${not status.last}">/</c:if>
													</c:forEach>
												</c:otherwise>
											</c:choose></td>
										<td><c:choose>
												<c:when test="${skillInventoryList.isExtern == 1}">
													<select name="dbNum">
														<c:forEach var="projSkillDbmss"
															items="${langDbFrame.dbms}">
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
													<input type="hidden" name="dbNum"
														value="${skillInventoryList.db.dbNum}"> ${skillInventoryList.db.dbName}
                     </c:otherwise>
											</c:choose></td>
										<td><c:choose>
											<!-- 프레임워크도 언어와 같은 방식으로 출력 -->
												<c:when test="${skillInventoryList.isExtern == 1}">
													<div class="checkbox-dropdown">
														선택
														<ul class="checkbox-dropdown-list">
															<c:forEach var="projSkillFrame"
																items="${langDbFrame.frame}">
																<c:set var="langCheck" value="" />
																<c:forEach var="skillProjFrame"
																	items="${skillInventoryList.frames}">
																	<c:if
																		test="${skillProjFrame.frameName eq projSkillFrame.frameName}">
																		<c:set var="langCheck" value="checked" />
																	</c:if>
																</c:forEach>
																<li><label><input type="checkbox"
																		name="frameNum${skill.index}"
																		value="${projSkillFrame.frameNum}" ${langCheck} />
																		${projSkillFrame.frameName}</label> <c:set var="langCheck"
																		value="" /></li>
															</c:forEach>
														</ul>
													</div>
												</c:when>
												<c:otherwise>
													<input type="hidden" name="frameNum${skill.index}"
														value="0">
													<c:forEach var="skillProjFrame"
														items="${skillInventoryList.frames}" varStatus="status">
														${skillProjFrame.frameName}<c:if test="${not status.last}">/</c:if>
													</c:forEach>
												</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<input type="button" onclick="add_row3()" value="행추가"> <input
							type="button" onclick="delete_row3()" value="행삭제"> <input
							type="button" value="등록" onclick="return SkillInventoryUpdate()">
					</section>
					<!-- 스킬인벤토리 끝 -->
				</form>

			</section>
		</section>
	</section>

	<!-- 푸터 -->
	<jsp:include page="/frame/footer.jsp"></jsp:include>
	
	<!-- 자바스크립트 - 다른 js파일들과 충돌하지 않도록 마지막에 선언 -->
	<script type="text/javascript" src="/Eureca/script/dropdown.js"></script>
	<script type="text/javascript" src="/Eureca/script/freelancer.js"></script>

</body>
</html>
<%-- 

	사원계정등록 페이지
	
	@작성자 : 글로벌IT경영 김민현
	
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 계정 등록</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="css/dropdown.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/frame/header.jsp"></jsp:include>
	<section id="container" class="">
		<section id="main-content">
			<section class="wrapper">
				<!-- 사진업로드 기능을 위해 enctype을  multipart/form-data로 설정-->
				<!-- form태그안에 enctype을 multipart/form-data로 설정하게되면 설정한 form안의 모든 값들은 request가 아닌 multi로 parameter값을 받아야함 -->
				<form name="frm" method="post" enctype="multipart/form-data">
					<section class="panel">
						<header class="panel-heading no-border"> 사원 계정 등록 </header>
						<table class="table table-bordered">
							<tr>
								<th>아이디</th>
								<td colspan="2"><input type="text" name="empId"
									value="${empId}"> 
								<!-- 아이디 중복체크 -->	
								<input type="button" value="중복체크" onclick="idCheck()"></td>

								<th>비밀번호</th>
								<td colspan=""><input type="password" name="empPw"
									value="${empPw}"></td>
								<th>비밀번호 확인</th>
								<td colspan="3"><input type="password" name="empPw2"
									value="${empPw}"></td>
							</tr>

							<tr>
								<th rowspan="4">사진</th>
								<td rowspan="4" colspan="2">
							<%-- 	<img src="/Eureca/upload/${empPicture}">  --%>
								<input type="file" name="empPicture" value="${empPicture}"></td>

								<th>이름</th>
								<td><input type="text" name="empName" value="${empName}"></td>
								<th>부서</th>
								<td><select id="empDept" name="empDept" size="1">
										<option value="${empDept}">${empDept}</option>
										<option value="SI사업팀">SI사업팀</option>
										<option value="SM사업팀">SM사업팀</option>
										<option value="총무/회계팀">총무/회계팀</option>
										<option value="사업기획팀">사업기획팀</option>
										<option value="파이프라인개발팀">파이프라인개발팀</option>
										<option value="Hybrid모델개발팀">Hybrid모델개발팀</option>
								</select></td>
								<th>직책</th>
								<td><input type="text" name="empDuty" value="${empDuty}"></td>
							</tr>

							<tr>
								<th>입사일</th>
								<td><input type="text" class="datepicker"
									name="empJoinDate" value="${empJoinDate}"></td>
								<th>퇴사일</th>
								<td><input type="text" class="datepicker"
									name="empDropDate" value="${empDropDate}"></td>
								<th>권한</th>
								<td><c:if test="${empAuth == true}">
										<c:set var="authCheck" value="checked" />
									</c:if> <input type="radio" name="empAuth" value="true" ${authCheck}
									id="admin"> <c:set var="authCheck" value="" /> <label
									for="admin">관리자</label> <c:if test="${empAuth == false}">
										<c:set var="authCheck2" value="checked" />
									</c:if> <input type="radio" name="empAuth" value="false" ${authCheck2}
									id="employee"> <c:set var="authCheck2" value="" /><label
									for="employee">사원</label></td>
							</tr>

							<tr>
								<th>생년월일</th>
								<td><input type="text" name=empBirth value="${empBirth}"></td>
								<th>성별</th>
								<td><c:if test="${empSex == true}">
										<c:set var="sexCheck" value="checked" />
									</c:if> <input type="radio" name="empSex" value="true" ${sexCheck}
									id="man"> <c:set var="sexCheck" value="" /><label
									for="man">남자</label> <c:if test="${empSex == false}">
										<c:set var="sexCheck2" value="checked" />
									</c:if> <input type="radio" name="empSex" value="false" ${sexCheck2}
									id="woman"> <c:set var="sexCheck2" value="" /><label
									for="woman">여자</label></td>

								<th>결혼여부</th>
								<td><c:if test="${empMarried == false}">
										<c:set var="marriedCheck" value="checked" />
									</c:if> <input type="radio" name="empMarried" value="false"
									${marriedCheck} id="single"> <c:set var="marriedCheck"
										value="" /> <label for="single">미혼</label> <c:if
										test="${empMarried == true}">
										<c:set var="marriedCheck2" value="checked" />
									</c:if> <input type="radio" name="empMarried" value="true"
									${marriedCheck2} id="married"> <c:set
										var="marriedCheck2" value="" /> <label for="married">기혼</label></td>
							</tr>

							<tr>
								<th>전화번호</th>
								<td><input type="text" name="empPhone" value="${empPhone}"><input
									type="button" value="중복체크" onclick="phoneCheck()"></td>
								<th>이메일</th>
								<td colspan="3"><input type="text" name="empEmail"
									value="${empEmail}" size="20" style="width: 97%;"><input
									type="button" value="중복체크" onclick="emailCheck()"></td>
							</tr>

							<tr>
								<th rowspan="2">주소</th>
								<td colspan="8"><input type="text" id="roadAddrPart1"
									name="empFrontAddr" value="${empFrontAddr}" size="20">
									<input type="button" onClick="goPopup();" value="주소찾기" /></td>
							</tr>
							<tr>
								<td colspan="8"><input type="text" id="addrDetail"
									name="empRearAddr" value="${empRearAddr}"></td>
							</tr>

							<tr>
								<th>계좌</th>
								<td colspan="2"><select id="empBank" name="empBank"
									size="1">
										<option value="${empBank}">${empBank}</option>
										<option value="KB국민">KB국민</option>
										<option value="하나은행">하나은행</option>
										<option value="기업은행">기업은행</option>
										<option value="NH농협">NH농협</option>
										<option value="신한은행">신한은행</option>
										<option value="신협">신협</option>
										<option value="우리은행">우리은행</option>
										<option value="카카오뱅크">카카오뱅크</option>
										<option value="대구은행">대구은행</option>
								</select></td>
								<th>계좌명의</th>
								<td colspan="6"><input type="text" name=empAccName
									value="${empAccName}"></td>
							</tr>

							<tr>
								<th>계좌번호</th>
								<td colspan="8"><input type="text" name=empAccount
									value="${empAccount}" size="20" style="width: 97%;"></td>
							</tr>
							<tr>
								<!-- <td><input type="button" value="등록"
									onclick="return ProfileUpdate()"></td> -->
							</tr>
						</table>
					</section>
					<!-- 			</div> -->
				
				<div class="clear"></div>
		
						<!-- 학력 등록 -->
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

							<c:forEach var="educationList" items="${Education}">
								<tr>
								<!-- eduNum은 따로 등록하거나 수정하는게 아니라 자동으로 설정되는 값이므로 hidden타입으로 설정 -->
									<td><input type="hidden" name="eduNum"
										value="${educationList.eduNum}"> <input type="text"
										name="eduSchool" value="${educationList.eduSchool}"></td>
									<td><input type="text" name="eduMajor"
										value="${educationList.eduMajor}"></td>
									<td><input type="text" name="eduDeploma"
										value="${educationList.eduDeploma}"></td>
									<td><input type="text" name="schoolJoinDate"
										value="${educationList.schoolJoinDate}"></td>
									<td><input type="text" name="schoolGraduatedDate"
										value="${educationList.schoolGraduatedDate}"></td>
								</tr>
							</c:forEach>
							<!-- javascript에서 행추가 및 행삭제 기능을 실행하기 위해 tbody id를 my-tbody로 설정 -->
							<tbody id="my-tbody">
							</tbody>
						</table>


						<input type="button" onclick="add_row()" value="행추가"> <input
							type="button" onclick="delete_row()" value="행삭제"> <%-- <input
							type="button" value="등록"
							onclick="return EducationUpdate('${empId}')"> --%>
	
						<!-- 경력 등록 -->
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

							<c:forEach var="CareerList" items="${Career}">
								<tr>
								<!-- careerNum은 따로 등록하거나 수정하는게 아니라 자동으로 설정되는 값이므로 hidden타입으로 설정 -->
									<td><input type="hidden" name="careerNum"
										value="${CareerList.careerNum}"> <input type="text"
										name="careerCompany" value="${CareerList.careerCompany}">
									</td>
									<td><input type="text" name="companyJoinDate"
										value="${CareerList.companyJoinDate}"> ㅡ <input
										type="text" name="companyDropDate"
										value="${CareerList.companyDropDate}"></td>

									<td><input type="text" name="careerPosition"
										value="${CareerList.careerPosition}"></td>
									<td><input type="text" name="careerJob"
										value="${CareerList.careerJob}"></td>
								</tr>
							</c:forEach>
							<!-- javascript에서 행추가 및 행삭제 기능을 실행하기 위해 tbody id를 CareerTable로 설정 -->
							<tbody id="CareerTable">
							</tbody>
						</table>

						<input type="button" onclick="add_row2()" value="행추가"> <input
							type="button" onclick="delete_row2()" value="행삭제"> <input
							type="button" value="등록"
							onclick="return TotalUpdate()">
				</form>
				
				<form name="frm2" method="post">
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

							<c:forEach var="SkillInventory" items="${SkillInventory}">
								<tr>
									<td><input type="hidden" name="projNum"
										value="${SkillInventory.projNum}"> <input type="text"
										name="projName" value="${SkillInventory.projName}"></td>
									<td><input type="text" name="joinProjDate"
										value="${SkillInventory.joinProjDate}"> ㅡ <input
										type="text" name="exitProjDate"
										value="${SkillInventory.exitProjDate}"></td>

									<td><input type="text" name="projTarget"
										value="${SkillInventory.projTarget}"></td>
									<td><input type="text" name="projCompany"
										value="${SkillInventory.projCompany}"></td>
									<td><input type="text" name="projRole"
										value="${SkillInventory.projRole}"></td>
									<td><input type="text" name="langName"
										value="${SkillInventory.langName}"></td>
									<td><input type="text" name="dbName"
										value="${SkillInventory.dbName}"></td>
									<td><input type="text" name="frameName"
										value="${SkillInventory.frameName}"></td>

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
	<script type="text/javascript" src="script/employee.js"></script>
</body>
</html>
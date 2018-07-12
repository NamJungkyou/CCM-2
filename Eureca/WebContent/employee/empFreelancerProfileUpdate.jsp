<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프리랜서 정보수정</title>
<style>
table {
	/* width: 100%; */
	border: 1px solid #444444;
}

th, td {
	border: 1px solid #444444;
}
</style>
<script type="text/javascript" src="/Eureca/script/freelancer.js">
	
</script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$(".datepicker").datepicker();
	});
</script>
</head>

<body>
	<h1>프리랜서 정보수정</h1>
	<!-- <form name="frm" method="post" action="FreelancerServ"> -->
	<form name="frm" method="post">
		<!-- <input type="hidden" name="command" value="freelancer_Profile_update"> -->
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="hidden" name="freeId"
					value="${freelancer.freeId}">${freelancer.freeId}</td>
				<th>상태</th>
				<td><select id="freeState" name="freeState" size="1">
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
				<th>작성일</th>
				<td><input type="hidden" name="freeReviseDate">${freelancer.freeReviseDate}</td>
			</tr>

			<tr>
				<th>이름</th>
				<td><input type="text" name="freeName"
					value="${freelancer.freeName}"></td>
				<th>생년월일</th>
				<td><input type="text" name="freeBirth"
					value="${freelancer.freeBirth}"></td>
				<th>등급</th>
				<td><select id="freeKosa" name="freeKosa" size="1">
						<option value="${freelancer.freeKosa}">${freelancer.freeKosa}</option>
						<option value="초급">초급</option>
						<option value="중급">중급</option>
						<option value="고급">고급</option>
				</select></td>
			</tr>

			<tr>
				<th>성별</th>
				<td><c:if test="${freeSex == true}">
						<c:set var="sexCheck" value="checked" />
					</c:if> <input type="radio" name="freeSex" value="true" ${sexCheck}>
					<c:set var="sexCheck" value="" /> 남자 <c:if
						test="${freeSex == false}">
						<c:set var="sexCheck2" value="checked" />
					</c:if> <input type="radio" name="freeSex" value="false" ${sexCheck2}>
					<c:set var="sexCheck2" value="" /> 여자</td>

				<th>결혼여부</th>
				<td><c:if test="${freeMarried == false}">
						<c:set var="marriedCheck" value="checked" />
					</c:if> <input type="radio" name="freeMarried" value="false"
					${marriedCheck}> <c:set var="marriedCheck" value="" /> 미혼<c:if
						test="${freeMarried == true}">
						<c:set var="marriedCheck2" value="checked" />
					</c:if> <input type="radio" name="freeMarried" value="true"
					${marriedCheck2}> <c:set var="marriedCheck2" value="" />
					기혼</td>

				<th>등급</th>
				<td><select id="freeState" name="freeState" size="1">
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

				<th>평점</th>
				<td><input type="text" name="freeScore"
					value="${freelancer.freeScore}"></td>
			</tr>

			<tr>
				<th>전화번호</th>
				<td><input type="text" name="freePhone"
					value="${freelancer.freePhone}"></td>
				<td><input type="button" value="중복체크" onclick="phoneCheck()"></td>
				<th>이메일</th>
				<td><input type="text" name="freeEmail"
					value="${freelancer.freeEmail}"></td>
				<td><input type="button" value="중복체크" onclick="emailCheck()"></td>
			</tr>

			<tr>
				<th rowspan="2">주소</th>
				<td colspan="2"><input type="text" id="roadAddrPart1"
					name="freeFrontAddr" value="${freeFrontAddr}" size="20"
					style="width: 97%;"></td>
				<td><input type="button" onClick="goPopup();" value="주소찾기" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="text" id="addrDetail"
					name="freeRearAddr" value="${freeRearAddr}"></td>
			</tr>

		</table>

		<input type="button" value="수정" onclick="return ProfileUpdate()">

		<h1>계좌정보</h1>
		<table>
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
				<td><input type="text" name="freeAccount"
					value="${freelancer2.freeAccount}"></td>
			</tr>
		</table>

		<input type="button" value="등록" onclick="return BankUpdate()">

		<h1>학력</h1>
		<table>
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
			<tbody id="empFreeEducationTable">
			</tbody>
		</table>

		<input type="button" onclick="add_Edu_row()" value="행추가"> <input
			type="button" onclick="delete_Edu_row()" value="행삭제"> <input
			type="button" value="등록"
			onclick="return empFreeEducationInsert('${freeId}')">


		<h1>경력</h1>
		<table>
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
						value="${Career.companyJoinDate}"> ㅡ <input type="text"
						name="companyDropDate" value="${Career.companyDropDate}"></td>

					<td><input type="text" name="careerPosition"
						value="${Career.careerPosition}"></td>
					<td><input type="text" name="careerJob"
						value="${Career.careerJob}"></td>
				</tr>
			</c:forEach>
			<tbody id="empFreeCareerTable">
			</tbody>
		</table>

		<input type="button" onclick="add_Career_row()" value="행추가"> <input
			type="button" onclick="delete_Career_row()" value="행삭제"> <input
			type="button" value="등록"
			onclick="return empFreeCareerUpdate('${freeId}')">

		<h1>Skill Inventory</h1>
		<table>
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
										<c:when test="${skillLangs.langNum == projSkillLangs.langNum}">
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
												<option value="${projSkillDbmss.dbNum}" selected="selected">
													${projSkillDbmss.dbName}</option>
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
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 선택</title>
</head>
<body>
<form method="post" action="CommonServ" name="frm">
		<input type="hidden" name="command" value="project_list">
	</form>
	<table>
		<tr>
			<th>번호</th>
			<th>등록인</th>
			<th>등록일</th>
			<th>프로젝트명</th>
			<th>상태</th>
			<th>시작일</th>
			<th>예상기간</th>
			<th>종료일</th>
			<th>필요인원</th>
			<th>참여인원</th>
			<th>고객사</th>
			<th>협력사</th>
			<th>사용언어</th>
			<th>DBMS</th>
			<th>TOOL/framework</th>
			<th>세부내용</th>
		</tr>
			<tr>
			<td>${pSelectViewList.projNum}<input type="hidden"
						name="r_projNum" value="${pSelectViewList.projNum}"></td>
			<td>${pSelectViewList.projRegisterer}<input type="hidden"
						name="r_projRegisterer2" value="${pSelectViewList.projRegisterer}"></td>
			<td>${pSelectViewList.projRegisterDate}<input type="hidden"
						name="r_projRegisterDate" value="${pSelectViewList.projRegisterDate}"></td>
			<td>${pSelectViewList.projName}<input type="hidden"
						name="r_projName" value="${pSelectViewList.projName}"></td>
			<td>${pSelectViewList.projState}<input type="hidden"
						name="r_projState" value="${pSelectViewList.projState}"></td>
			<td>${pSelectViewList.projStartDate}<input type="hidden"
						name="r_projStartDate" value="${pSelectViewList.projStartDate}"></td>
			<td>${pSelectViewList.projExpectedTime}<input type="hidden"
						name="r_projExpectedTime" value="${pSelectViewList.projExpectedTime}"></td>
			<td>${pSelectViewList.projEndDate}<input type="hidden"
						name="r_projEndDate" value="${pSelectViewList.projEndDate}"></td>
			<td></td>
			<td>${pSelectViewList.joinFLCount}<input type="hidden"
						name="r_joinFLCount" value="${pSelectViewList.joinFLCount}"></td>
			<td>${pSelectViewList.projTarget}<input type="hidden"
						name="r_projTarget" value="${pSelectViewList.projTarget}"></td>
			<td>${pSelectViewList.projPartner}<input type="hidden"
						name="r_projPartner" value="${pSelectViewList.projPartner}"></td>
			<td>
					<c:forEach var='projLangList' items="${projLangList}"
			varStatus="status">
			${projLangList.langName}<input type="hidden"
						name="r_langName" value="${projLangList.langName}">
						</c:forEach></td>
			<td>${pSelectViewList.usedDbName}<input type="hidden"
						name="r_usedDbName" value="${pSelectViewList.usedDbName}"></td>
			<td>
			<c:forEach var='projFrameList' items="${projFrameList}"
			varStatus="status">
			${projFrameList.frameName}<input type="hidden"
						name="r_frameName" value="${projFrameList.frameName}">
						</c:forEach></td>
			</tr>

	</table>
</body>
</html>
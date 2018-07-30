<%-- 

	로그인하면 왼쪽에 뜨는 미니프로필이다
	
	작성자 : 글로벌 IT 경영 진재환

	수정일 : 
	
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<c:choose>
	<c:when test="${loginemp == null}">
		<%-- 직원이 null이니까 프리랜서 프로필 출력하면됨 --%>

		<section id="container" class="">
			<!--sidebar start-->
			<aside>
				<div id="sidebar" class="nav-collapse ">
					<div class="miniprof-top">
						<div class="miniprof-picture">
							<!-- img src="${loginfree.freePic}" -->
							<c:choose>
								<c:when test="${loginfree.freePic != null}">
									<img src="/Eureca/upload/${loginfree.freePic}">
								</c:when>
								<c:otherwise>
									<img src="/Eureca/upload/man.png">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="miniprof">
							<div class="miniprof-name">
								${loginfree.freeName}(${loginfree.freeId})</div>
							<div>
								<table>
									<tr>
										<td colspan="2"><span class="icon-span"><i
												class="icon_phone"></i></span>${loginfree.freePhone}</td>
									</tr>
									<tr>
										<td colspan="2"><span class="icon-span"><i
												class="icon_mail"></i></span>${ loginfree.freeEmail }</td>
									</tr>
								</table>
							</div>
							<div class="simple-state">
								<div class="state float-left">
									<table>
										<tr>
											<td class="align-center font-size">상태</td>
										</tr>
										<tr>
											<td class="align-center">${freeState}</td>
										</tr>
									</table>
								</div>
								<div class="grade float-left">
									<table>
										<tr>
											<td class="align-center font-size">등급</td>
										</tr>
										<tr>
											<td class="align-center">${loginfree.freeClass}</td>
										</tr>
									</table>
								</div>
								<div class="clear"></div>
								<div class="newMessage float-left">
									<table>
										<tr>
											<td class="align-center">${newMessage}</td>
										</tr>
										<tr>
											<td class="align-center font-size">신규메세지</td>
										</tr>
									</table>
								</div>
								<div class="">
									
								</div>
								<div class="clear"></div>
								<!-- <table>
									<tr>
										<td class="align-center font-size">상태</td>
										<td class="align-center font-size">등급</td>
									</tr>
									<tr>
										<td class="align-center">2</td>
										<td class="align-center">1</td>
									</tr>
									<tr>
										<td class="align-center">1</td>
										<td></td>
									</tr>
									<tr>
										<td class="align-center font-size">신규 메세지</td>
										<td></td>
									</tr>
								</table> -->
							</div>
						</div>
					</div>
					<!-- sidebar menu start-->
					<ul class="active sidebar-menu">
						<li class="sub-menu"><a href="javascript:;" class=""> <i
								class="icon_document_alt"></i> <span>프로필 등록</span>
						</a> <!-- <ul class="sub">
						<li><a class="" href="form_component.html">프로젝트 등록</a></li>
						<li><a class="" href="form_validation.html">프로젝트 참여신청 접수</a></li>
						<li><a class="" href="form_validation.html">프리랜서 투입</a></li>
					</ul> --></li>
						<li class="sub-menu"><a
							href="FreelancerServ?command=freelancer_join_project" class="">
								<i class="icon_desktop"></i> <span>프로젝트 참여신청</span>
						</a> <!-- <ul class="sub">
						<li><a class="" href="general.html">프리랜서 등록</a></li>
						<li><a class="" href="buttons.html">프리랜서 정보 수정</a></li>
						<li><a class="" href="grids.html">면접 일정/결과 등록</a></li>
					</ul> --></li>
						<li class="sub-menu"><a href="javascript:;" class=""> <i
								class="icon_table"></i> <span>My페이지</span> <span
								class="menu-arrow arrow_carrot-right"></span>
						</a>
							<ul class="sub">
								<li><a class="" href="basic_table.html">메세지 확인</a></li>
							</ul></li>

						<!-- <li class="sub-menu"><a href="javascript:;" class=""> <i
						class="icon_documents_alt"></i> <span>메세지 작성</span> <span
						class="menu-arrow arrow_carrot-right"></span>
				</a>
					<ul class="sub">
						<li><a class="" href="profile.html">Profile</a></li>
						<li><a class="" href="login.html"><span>Login Page</span></a></li>
						<li><a class="" href="blank.html">Blank Page</a></li>
						<li><a class="" href="404.html">404 Error</a></li>
					</ul></li> -->
					</ul>
					<!-- sidebar menu end-->
				</div>
			</aside>
			<!--sidebar end-->
		</section>
	</c:when>

	<c:otherwise>
		<%-- otherwise니까 당연히 직원프로필을 출력해야겠지 --%>
		<%-- 여기도 비슷하게 만들면됨 --%>

		<section id="container" class="">
			<!--sidebar start-->
			<aside>
				<div id="sidebar" class="nav-collapse ">
					<div class="miniprof-top">
						<div class="miniprof-picture">
							<c:choose>
								<c:when test="${loginemp.empPicture != null}">
									<img src="/Eureca/upload/${loginemp.empPicture}">
								</c:when>
								<c:otherwise>
									<img src="/Eureca/img/man.png">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="miniprof">
							<div class="miniprof-name">${ loginemp.empName }&nbsp;${ loginemp.empDuty }
							</div>
							<div>
								<table>
									<tr>
										<td><span class="icon-span"><i class="icon_phone"></i></span>${ loginemp.empPhone }</td>
									</tr>
									<tr>
										<td><span class="icon-span"><i class="icon_mail"></i></span>${ loginemp.empEmail }</td>
									</tr>
								</table>
							</div>
							<div class="simple-state">
								<div class="state float-left">
									<table>
										<tr>
											<td class="align-center font-size">접수대기</td>
										</tr>
										<tr>
											<td class="align-center">${waitingProj}</td>
										</tr>
									</table>
								</div>
								<div class="grade float-left">
									<table>
										<tr>
											<td class="align-center font-size">진행중</td>
										</tr>
										<tr>
											<td class="align-center">${doingProj}</td>
										</tr>
									</table>
								</div>
								<div class="clear"></div>
								<div class="newMessage float-left">
									<table>
										<tr>
											<td class="align-center">${empNewMessage}</td>
										</tr>
										<tr>
											<td class="align-center font-size">신규메세지</td>
										</tr>
									</table>
								</div>
								<div class="">
									
								</div>
								<div class="clear"></div>
						</div>
					</div>
					<!-- sidebar menu start-->
					<ul class="sidebar-menu">
						<li class="sub-menu active"><a href="javascript:;" class=""> <i
								class="icon_document_alt"></i> <span>프로젝트 관리</span> <span
								class="menu-arrow arrow_carrot-right"></span>
						</a>
							<ul class="sub">
								<li><a class="" href="ProjectServ?command=project_insert">프로젝트
										등록</a></li>
								<li><a class=""
									href="ProjectServ?command=projectjoinaccept">프로젝트 참여신청 접수</a></li>
								<li><a class="" href="ProjectServ?command=putfreelancer">프리랜서
										투입</a></li>
							</ul></li>
						<li class="sub-menu"><a href="javascript:;" class=""> <i
								class="icon_desktop"></i> <span>프리랜서 관리</span> <span
								class="menu-arrow arrow_carrot-right"></span>
						</a>
							<ul class="sub">
								<li><a class=""
									href="EmployeeServ?command=freelancer_Profile_insert">프리랜서
										등록</a></li>
								<li><a class="" href="">프리랜서 정보 수정</a></li>
								<li><a class=""
									href="EmployeeServ?command=interview_schedule">면접 일정/결과 등록</a></li>
							</ul></li>
						<li class="sub-menu"><a href="javascript:;" class=""> <i
								class="icon_table"></i> <span>계정관리</span> <span
								class="menu-arrow arrow_carrot-right"></span>
						</a>
							<ul class="sub">
								<li><a class=""
									href="EmployeeServ?command=employee_Profile_insert_form">사원계정
										등록</a></li>
								<li><a class="" href="">사원계정 수정</a></li>
							</ul></li>

						<li class="sub-menu"><a
							href="CommonServ?command=show_message" class=""> <i
								class="icon_documents_alt"></i> <span>메세지 작성</span> <span
								class="menu-arrow arrow_carrot-right"></span>
						</a> <!-- <ul class="sub">
								<li><a class="" href="profile.html">Profile</a></li>
								<li><a class="" href="login.html"><span>Login
											Page</span></a></li>
								<li><a class="" href="blank.html">Blank Page</a></li>
								<li><a class="" href="404.html">404 Error</a></li>
							</ul> --></li>
					</ul>
					<!-- sidebar menu end-->
				</div>
			</aside>
			<!--sidebar end-->
		</section>
	</c:otherwise>
</c:choose>
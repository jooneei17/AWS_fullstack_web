<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="common/head.jsp" %>
</head>
<body>
   <%@ include file="common/header.jsp" %>
    <main class="container py-3 pb-5">
        <div class="row">
            <!-- 가장 작을 때에는 col-12비율로 했다가(col-12 생략가능) lg사이즈로 오게 되면 9비율로 변경 -->
            <section class="col-lg-9 ">
                <h3>본문 시작 구간</h3>
                <article>공간1</article>
                <article>공간2</article>
                <article>공간3</article>
                <article>공간4</article>
            </section>
            <aside class="col">
            	<c:if test="${empty member}">
                <div class="bg-white p-3 d-grid">
                  <p class="text-muted small">사이트를 더 안전하고 편리하게 이용하세요</p>
                  <a href="member/login" class="btn btn-success btn-block mb-3"><span class="fs-3">TJ</span><span class="ml-3">로그인</span></a>
                  <div>
                      <a href="#" class="text-decoration-none small">아이디</a>
                      <a href="#" class="text-decoration-none small">비밀번호 찾기</a>
                      <a href="#" class="text-decoration-none small float-end">회원가입</a>
                  </div>
                </div>
                </c:if>
                <c:if test="${not empty member}">
                	<div class="text-muted">
	                	<p>${member.name}님 환영합니다</p>
                	</div>
                	<div>
                		<a href="#" class="text-decoration-none small">마이페이지</a>
                        <a href="member/logout" class="btn btn-danger btn-small">로그아웃</a>
                	</div>
                </c:if>
            </aside>
        </div>
    </main>
    <%@ include file="common/footer.jsp" %>
</body>
</html>
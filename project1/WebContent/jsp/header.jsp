<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	String userid = (String)session.getAttribute("userid");
%>    
		<div style="text-align: right">
			<ul class = "head">
			  <li>ユーザID:<%= userid %></li>
			  <li><a href="/project1/logout"><img src="/project1/img/logout.png" alt="ログアウト"></a></li>
			</ul>
		</div>
		
		<ul class = "menu">
		  <li><a href="${pageContext.request.contextPath}/top">TOP</a></li>
		  <li><a href="${pageContext.request.contextPath}/companyreist">新規登録</a></li>
		  <li><a href="${pageContext.request.contextPath}/loginHistory">ログイン履歴</a></li>
		  <li><a href="#">-</a></li>
		  <li><a href="#">-</a></li>
		</ul>
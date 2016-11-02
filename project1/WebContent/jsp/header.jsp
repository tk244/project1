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
		  <li><a href="#">HOME</a></li>
		  <li><a href="#">page1</a></li>
		  <li><a href="#">page2</a></li>
		  <li><a href="#">page3</a></li>
		  <li><a href="#">page4</a></li>
		</ul>
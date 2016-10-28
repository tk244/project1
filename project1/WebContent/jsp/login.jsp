<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	String login = (String)session.getAttribute("login");
	if (login != null && login.equals("OK")){
	    pageContext.forward("/jsp/top.jsp");
	}
%>

<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<title>ログイン</title>
</head>
<body>
    <form action="/project1/login" method="post">
        <div id="form">
            <p class="form-title">ログイン</p>
			<p style="color: red">
	         	${errorMessage}
	     	</p>
            
            <p>ユーザID</p>           
            <input type="text" name="userid">
            <br><br>
            <p>パスワード</p>
            <input type="password" name="pass">
            <br>
            <div Align="right"><a class = "reisue12" href="controller/reissue.jsp">ユーザID・パスワードをお忘れですか？</a></div>
            <p class="Login_submit"><input type="submit" value="ログイン"></p>
            <br>
            <div Align="center"><a href="${pageContext.request.contextPath}/jsp/newregist.jsp">はじめてご利用の方（新規会員登録）</a></div>
        </div>
    </form>
</body>
</html>
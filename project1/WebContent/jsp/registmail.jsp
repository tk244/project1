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
<title>会員登録 メールアドレス登録</title>
</head>
<body>
    <form action="/project1/mailsend" method="post">
        <div id="form">
            <p class="form-title">会員登録 メールアドレス登録</p>
			<p style="color: red">
	         	${errorMessage}
	     	</p>            
            <p>メールアドレス</p>           
            <input type="text" name="mailadress">
            <br><br>            
            <p class="Login_submit"><input type="submit" value="送信"></p>            
        </div>
    </form>
</body>
</html>
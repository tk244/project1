<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
<title>ログイン</title>
</head>
<body>
	<div id="form">	
		<form action="${pageContext.request.contextPath}/login" method="post">
			<p class="form-title">ログイン</p>
			<p style="color: red">
				${errorMessage}
			</p>
			<p>メールアドレス</p>
			<input type="text" name="userid">
			<br><br>
			<p>パスワード</p>
			<input type="password" name="pass">
			<br>
			<div Align="right"><a class = "reisue12" href="${pageContext.request.contextPath}/jsp/reissue.jsp">ユーザID・パスワードをお忘れですか？</a></div>
			<p class="Login_submit"><input type="submit" value="ログイン"></p>
			<br>
			<div Align="center"><a href="${pageContext.request.contextPath}/mailsend">はじめてご利用の方（新規会員登録）</a></div>
		</form>
	</div>
</body>
</html>
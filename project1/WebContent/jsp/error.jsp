<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>エラー</title>
</head>
<body>
	${errorMessage}
	<br>
	<div Align="left"><a href="${pageContext.request.contextPath}/login">ログイン</a></div>

</body>
</html>
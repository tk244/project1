<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sentcompleted.css">
<title>会員登録 確認メール送信</title>
</head>
<body>
	<div id="form">
		<p class="form-title">会員登録 確認メール送信</p>
		<p>会員登録 確認メール送信</p>	
		<p>ご登録のメールアドレスに確認メールを送信しました。</p>
		<p>登録の手続きはまだ完了していません。</p>	
		<p>確認メールに記載のURLから登録手続きを行ってください。</p>
		<p><a href=${pageContext.request.contextPath}${Message}>URL</a></p>	
		<p>※ URLの有効期限は配信後30分です。 </p>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registmail.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/registmail.js"></script>
<title>会員再登録</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/mailsend" method="post" name="form1" onSubmit="return check()">
		<div id="form">
			<p class="form-title">会員再登録</p>
			<div Align="right"><a href="javascript:history.back();">戻る</a></div>
			<p style="color: red">
				${errorMessage}
			</p>
			<input type="hidden" name="param" value="reissue"}>
			<p>メールアドレス</p>
			<input type="text" name="mailadress">
			<div id="error_mailadress">
				<span id="errormsg_mailadress" class="err"></span>
			</div>
			<br>
			<br>
			<p class="Mailsend_submit"><input type="submit" value="送信"></p>
		</div>
	</form>
</body>
</html>
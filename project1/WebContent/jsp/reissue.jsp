<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/newregist.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reissue.js"></script>
<title>パスワード設定</title>
</head>
<body>
	<div id="form">
		<p class="form-title">パスワード設定</p>
		<p style="color: red; text-align: center;">
			${errorMessage}
		</p>
		<form name="form1" action="${pageContext.request.contextPath}/reissue" method="post">
			<input type="hidden" name="userid" value=${mailadress}>
			<div id="error_chk">
				<span id="errormsg_chk" class="err"></span>
			</div>
			<table>
				<tr>
					<td class="col">パスワード<span class="str">&nbsp;(必須)</span></td>
					<td><input type="password" name="pass" >
						<% if(request.getAttribute("errorPass") != null){ %>
							<br><span class="err">${errorPass}</span>
						<% } %>
						<div id="error_pass">
							<span id="errormsg_pass" class="err"></span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="col">パスワード(確認用)<span class="str">&nbsp;(必須)</span></td>
					<td><input type="password" name="passchk" >
						<% if(request.getAttribute("errorPasschk") != null){ %>
							<br><span class="err">${errorPasschk}</span>
						<% } %>
						<div id="error_passchk">
							<span id="errormsg_passchk" class="err"></span>
						</div>
					</td>
				</tr>
			</table>
			<div Align="right">
				<span class="submit">
					<input type="submit" value="登録" onclick="return mySubmit();">
	 			</span>
			</div>
		</form>
	</div>
</body>
</html>
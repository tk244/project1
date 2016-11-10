<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/newregist.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/newregist.js"></script>
<title>会員登録</title>
</head>
<body>
	<div id="form">
		<p class="form-title">会員登録</p>
		<p style="color: red; text-align: center;">
			${errorMessage}
		</p>
		<form name="form1" action="${pageContext.request.contextPath}/newregist" method="post">
			<table>
				<tr>
					<td class="col">メールアドレス<span class="str">&nbsp;(必須)</span></td>
					<td><input type="text" readonly="readonly" name="userid" value=${mailadress}/>
						<% if(request.getAttribute("errorUserid") != null){ %>
							<br><span class="err">${errorUserid}</span>
						<% } %>
						<div id="error_userid">
							<span id="errormsg_userid" class="err"></span>
						</div>
					</td>
				</tr>
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
					<td class="col">名前<span class="str">&nbsp;(必須)</span></td>
					<td><input type="text" name="username" >
						<% if(request.getAttribute("errorUsername") != null){ %>
							<br><span class="err">${errorUsername}</span>
						<% } %>
						<div id="error_username">
							<span id="errormsg_username" class="err"></span>
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
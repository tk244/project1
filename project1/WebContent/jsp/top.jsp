<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	String login = (String)session.getAttribute("login");
	if (login == null || !login.equals("OK")){
	    pageContext.forward("/jsp/login.jsp");
	}
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/top.css">
<title>トップページ</title>
</head>
<body>

	<div id="form">
		<jsp:include page="/jsp/header.jsp"></jsp:include>
    </div>
    
</body>
</html>
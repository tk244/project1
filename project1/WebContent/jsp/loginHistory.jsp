<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/top.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/top.js"></script>
<title>ログイン履歴</title>
</head>
<body>
	<div id="form">
		<form name=MyForm action="${pageContext.request.contextPath}/companylist" method="post"> 
			<jsp:include page="/jsp/header.jsp"></jsp:include>

			<div class="scr">
				<table class="tbl">
					<thead class="scrollHead">
						<tr>
							<th class="chk"></th>
							<th class="no">No.</th>
							<th class="userid">ユーザ名</th>
							<th class="logindate">ログイン日時</th>
						</tr>
					</thead>
					<tbody class="scrollBody">
						<% int cnt=0; %>
						<c:forEach var="item" items="${loginHistoryList}" >
							<% cnt = cnt + 1; %>
							<tr>
								<td class="chk"><input type="checkbox" name="deletechks"  /></td>
								<td class="no"><%= cnt %></td>
								<td class="userid"><c:out value="${item.userid}" /></td>
								<td class="logindate"><c:out value="${item.logindate}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
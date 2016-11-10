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
<title>トップページ</title>
</head>
<body>
	<div id="form">
		<form name=MyForm action="${pageContext.request.contextPath}/companylist" method="post"> 
			<jsp:include page="/jsp/header.jsp"></jsp:include>
			<ul class = "sub">
				<li></li>
				<li><p style="color: #708090; text-align:right;">${companyLists.size()}件</p></li>
			</ul>
			<div id="error_chk">
				<span id="errormsg_chk" class="err"></span>
			</div>
			<div class="scr">
				<table class="tbl">
					<thead class="scrollHead">
						<tr>
							<th class="chk"></th>
							<th class="no">No.</th>
							<th class="company_id">会社ID</th>
							<th class="company_name">会社名</th>
							<th class="area">地域</th>
							<th class="business">業種</th>
						</tr>
					</thead>
					<tbody class="scrollBody">
						<% int cnt=0; %>
						<c:forEach var="item" items="${companyLists}" >
							<% cnt = cnt + 1; %>
							<tr>
								<td class="chk"><input type="checkbox" name="deletechks" value=${item.company_id} /></td>
								<td class="no"><%= cnt %></td>
								<td class="company_id"><c:out value="${item.company_id}" /></td>
								<td class="company_name">
									<a href="${pageContext.request.contextPath}/companydetail?company_id=${item.company_id}">
										<c:out value="${item.company_name}" />
									</a>
								</td>
								<td class="area"><c:out value="${item.area}" /></td>
								<td class="business"><c:out value="${item.business}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<ul class = "sub">
				<li><span class="submit"><input type="submit" value="削除" onClick="return func('Delete');"></span></li>
				<li><span class="submit"><input type="submit" value="検索" onClick="return func('Search');"></span></li>
			</ul>
			<input type=hidden name=MySubmit> 
		</form>
	</div>
</body>
</html>
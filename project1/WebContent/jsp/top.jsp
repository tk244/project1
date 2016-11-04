<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
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
		
		<div align="right"><a href="${pageContext.request.contextPath}/companyreist">新規登録</a></div>
		
		<div class="scr">
			<table class="tbl">
			  <thead class="scrollHead">
			    <tr>
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
	  </div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/companydetail.css">
<title>企業詳細</title>
</head>
<body>
	<div id="form">
		<jsp:include page="/jsp/header.jsp"></jsp:include>		
		<div Align="right"><a href="javascript:history.back();">戻る</a></div>
		<table>
			<tr>
				<td class="col">会社名<span class="str"></span></td>
				<td>${companyLists[0].company_name}</td>
			</tr>
			<tr>
				<td class="col">都道府県</td>
				<td>${companyLists[0].area}</td>
			</tr>
			<tr>	
				<td class="col">業種</td>
				<td>${companyLists[0].business}</td>
			</tr>
			<tr>
				<td class="col">特徴</td>
				<td>
					<c:forEach var="item" items="${characteristicList}" >
						${item.characteristic_name} <br/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="col">時間帯</td>
				<td>
					<c:forEach var="item" items="${timezoneList}" >
						${item.timezone_name} <br/>	
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="col">期間</td>
 				<td>
					<c:forEach var="item" items="${periodList}" >
						${item.period_name}  <br/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="col">雇用形態</td>
				<td>
					<c:forEach var="item" items="${employmentList}" >
						${item.employment_name}  <br/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="col">備考</td>
				<td>${companyLists[0].remarks}</td>
			</tr> 
		</table>
		<div Align="right">
			<span class="submit">
				<input type="submit" value="更新" onClick="location.href='${pageContext.request.contextPath}/companyedit?company_id=${companyLists[0].company_id}'">
			</span>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/companyreist.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/companyreist.js"></script>
<title>企業登録</title>
</head>
<body>
	<div id="form">
		<p class="form-title">企業登録</p>
		<div Align="right"><a href="javascript:history.back();">戻る</a></div>
		<p style="color: red; text-align: center;">
			${errorMessage}
		</p>
		<form name="form1" action="${pageContext.request.contextPath}/companyreist" method="post">
			<table>
				<tr>
					<td class="col">会社名<span class="str">&nbsp;(必須)</span></td>
					<td><input type="text" name="company_name" />
						<div id="error_company_name">
							<span id="errormsg_company_name" class="err"></span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="col">都道府県<span class="str">&nbsp;(必須)</span></td>
					<td>
						<select name="area">
							<c:forEach var="item" items="${areaList}" >	
    							<option value="${item.area_id}">${item.area_name}</option>  
							</c:forEach>
						</select>
						<div id="error_area">
							<span id="errormsg_area" class="err"></span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="col">業種<span class="str">&nbsp;(必須)</span></td>
					<td>
						<select name="business">
							<c:forEach var="item" items="${businessList}" >
								<option value="${item.business_id}">${item.business_name}</option>  
							</c:forEach>
						</select>
						<div id="error_business">
							<span id="errormsg_business" class="err"></span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="col">特徴<span class="str">&nbsp;(必須)</span></td>
					<td>
						<c:forEach var="item" items="${characteristicList}" >
							<input type="checkbox" name="characteristics" value="${item.characteristic_id}" >${item.characteristic_name}
						</c:forEach>
 						<div id="error_characteristic">
							<span id="errormsg_characteristic" class="err"></span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="col">時間帯<span class="str">&nbsp;(必須)</span></td>
					<td>
						<c:forEach var="item" items="${timezoneList}" >
							<input type="checkbox" name="timezones" value="${item.timezone_id}" >${item.timezone_name}						  
						</c:forEach>
						<div id="error_timezone">
							<span id="errormsg_timezone" class="err"></span>
						</div>
					</td>
				</tr>
				<tr>	      		
					<td class="col">期間<span class="str">&nbsp;(必須)</span></td>
					<td>
						<c:forEach var="item" items="${periodList}" >
							<input type="checkbox" name="periods" value="${item.period_id}" >${item.period_name}	  
						</c:forEach>
						<div id="error_period">
							<span id="errormsg_period" class="err"></span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="col">雇用形態<span class="str">&nbsp;(必須)</span></td>
					<td>
						<c:forEach var="item" items="${employmentList}" >
							<input type="checkbox" name="employments" value="${item.employment_id}" >${item.employment_name}
						</c:forEach>
						<div id="error_employment">
							<span id="errormsg_employment" class="err"></span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="col">給料<span class="str">&nbsp;(必須)</span></td>
					<td><input type="text" name="salary" />
						<div id="error_salary">
							<span id="errormsg_salary" class="err"></span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="col">備考</td>
					<td>
						<textarea name="remarks" rows="4" cols="40"></textarea>
						<div id="error_remarks">
							<span id="errormsg_remarks" class="err"></span>
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
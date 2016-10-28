<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/newregist.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/newregist.js"></script>
<title>新規登録</title>

</head>
<body>

	<div id="form">
	
	   	<p class="form-title">新規登録</p>
	   	<p style="color: red; text-align: center;">
			${errorMessage}
		</p>
	
	    <form name="form1" action="/project1/newregist" method="post">

			<table>
	      		<tr>
	       			<td class="col">ユーザID<span class="str">&nbsp;(必須)</span></td>
	          		<td><input type="text" name="userid" >
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
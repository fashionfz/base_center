<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%> 
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.context.SecurityContextImpl" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
Object msg = auth.getPrincipal();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<style>
a{ text-decoration:none;color:#555555;}

html { overflow-y:hidden;overflow-x:hidden; }

</style>
<script type="text/javascript">
function logout(){
	parent.window.location="<%=basePath %>j_spring_security_logout";
}

function getName(){
	return $("#username").val();
}

</script>
</head>
<body leftmargin="0" topmargin="0" style="background-color: #ffffff">
<table cellpadding="0" cellspacing="0" width="100%" border="0px">
	<tr>
	    <td width="100%">
	    <%
	    if("anonymousUser".equals(msg.toString())){
			response.sendRedirect("login.jsp");
		}else{
	    %>
	        <table height="90px" border="0" bgcolor="#ffffff">
	           <tr>
	               <td width="120px">&nbsp;</td>
	               <td width="400px" valign="middle">目录服务器平台管理</td>
	               <td width="45%">&nbsp;</td>
                   <td width="280px" valign="bottom">
	                   <font style="font-size:13px;margin-bottom:10px" face="Verdana,Helvetica" color="#555555">
	                     <img title="您好：<security:authentication property='principal.username'/>" style="{vertical-align:bottom;}" src="images/user.png"/>&nbsp;您好：   <security:authentication property="principal.authorities"></security:authentication>
	                     <security:authentication property="principal.username"></security:authentication>
	                     &nbsp;&nbsp;
	                     <a title="注  销" onclick="logout()" href="#" >注&nbsp;销</a>
	                  </font>
	                  	<input type="hidden" id="username" value="<security:authentication property="principal.username"></security:authentication>"/>
                   </td>
               </tr>
            </table>
        <%
		}
        %>	    
	    </td>
	</tr>
</table>
</body>
</html>
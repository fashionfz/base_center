<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%> 
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.context.SecurityContextImpl" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
SecurityContextImpl security = (SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
if(security!=null){
	String userName = security.getAuthentication().getName();
	if(userName != null && !"".equals(userName)){
		response.sendRedirect("index.html");
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" media="screen" href="css/form.css">
<script type="text/javascript">
function init(){
	var url = ""+parent.window.location;
	var name = url.substring(url.lastIndexOf("/")+1,url.length);
	if(name=="index.html"){
		parent.window.location="login.jsp";
	}
}
</script>
<title>login</title>
</head>
<body onload="init()">
<div id="login">

<form id="login" action="j_spring_security_check" method="post">
    <h1>用户登录</h1>
    <fieldset id="inputs">
        <input type="text" required="" autofocus="" placeholder="Username" id="j_username" name="j_username">   
        <input type="password" required="" placeholder="Password" id="j_password" name="j_password">
    </fieldset>
    <fieldset id="actions">
        <input type="submit" value="登      录" id="submit">
        <!-- 
        自动登录<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>
     -->
    </fieldset>
    <fieldset id="note" style="text-align:center">
	 <%if(session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)!=null){ %>
	  <span style="color:red">用户名或密码错误</span>
	  <%} %>
    </fieldset>
</form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <base href="<%=basePath%>">
        <meta charset="UTF-8">
        <title>知识点：</title>
    </head>
    <body>
    	<h2>登录</h2>
    	<form action="sys/login" method="post">
    		<input type="text" name="account" 
    			value="${requestScope.account }"
    			autofocus="autofocus"
    			placeholder="请输入账号"
    		>
    		<input type="text" name="password"
    			placeholder="请输入密码"
    		>
    		<button>登录</button>
    	</form>
    	
    	<span style="color: red;">${requestScope.message }</span>
    </body>
</html>
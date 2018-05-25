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
    	<h2>用户表单</h2>
    	<form action="sys/user/add" method="post">
    		<input type="text" name="account">
    		<input type="text" name="user_name">
    		<select name="sex">
    			<option value="男">男</option>
    			<option value="女">女</option>
    		</select>
    		<button>添加用户</button>
    	</form>
    	<span style="color: red;">${message }</span>
    </body>
</html>
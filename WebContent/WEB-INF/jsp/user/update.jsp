<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
    	<form action="sys/user/update" method="post">
    		<input type="text" name="account" value="${user.account }">
    		<input type="text" name="user_name" value="${user.user_name }">
    		<select name="sex">
    			<option value="男"
    			<c:if test="${user.sex eq '男' }">selected="selected"</c:if>
    			>男</option>
    			<option value="女"
    			<c:if test="${user.sex eq '女' }">selected="selected"</c:if>
    			>女</option>
    		</select>
    		<button>更新用户</button>
    		
    		<input type="hidden" name="user_id" value="${user.user_id }">
    	</form>
    	<span style="color: red;">${message }</span>
    </body>
</html>
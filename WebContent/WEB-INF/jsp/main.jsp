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
    	<h2>XXX管理系统</h2>
    	<div>
    	<a href="sys/user/list" target="contentIframe">系统用户管理</a>
    	</div>
    	<div>
    	<iframe name="contentIframe" style="width: 600px;height: 600px;border: 1px solid red;"></iframe>
    	</div>
    </body>
</html>
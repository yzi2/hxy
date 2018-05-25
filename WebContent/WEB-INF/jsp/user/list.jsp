<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
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
    	<h2>系统用户列表</h2>
    	<a href="sys/user/add">添加用户</a>
    	<table border="1" cellspacing="0" style="width: 600px;">
    		<thead>
    			<tr>
    				<th>序号</th>
    				<th>账号</th>
    				<th>性别</th>
    				<th>姓名</th>
    				<th>创建日期</th>
    				<th>操作</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach items="${requestScope.userList }" var="user" varStatus="vs">
    			<tr>
    				<td>${vs.count }</td>
    				<td>${user.account }</td>
    				<td>${user.sex }</td>
    				<td>${user.user_name }</td>
    				<td>
    				<fmt:formatDate value="${user.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/>
    				</td>
    				<td>
    				<a href="sys/user/update?user_id=${user.user_id }">修改</a>
    					  | 
    				<a href="sys/user/delete?user_id=${user.user_id }">删除</a>
    				</td>
    			</tr>
    			</c:forEach>
    		</tbody>
    	</table>
    </body>
</html>
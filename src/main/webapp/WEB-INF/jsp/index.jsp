<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>

<%@include file="./common/base.jsp"%>

</head>
<body>
	<h1>SpringMVC+Spring4.3.6+mybatis3.4.2</h1>
		${basePath }
	<hr />

	<a href="${basePath}/user/gotoAdd">跳转添加测试</a>
	<br />
	<br />
	<a href="${basePath}/user/getList">跳转查询测试</a>
	<br />
	<br />
	<a href="${basePath}/user/gotoJson">跳转json测试</a>
	<br />
	<br />
	<a href="${basePath}/user/gotoUpload">跳转上传文件测试</a>
	<br />
	<br />
	
	
	
</body>
</html>
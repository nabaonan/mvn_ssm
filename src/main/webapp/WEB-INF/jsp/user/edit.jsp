<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit user</title>
</head>
<body>

	<c:url var="saveUrl" value="${basePath}/user/update" />
	
	<!--下面action用的变量是上边定义的saveUrl，
	modelAttribute：这个是用来读取后台对象的名字，跟提交没有关系
	
	-->
	<form:form modelAttribute="userForEdit" action="${saveUrl}">
		<table>
			<tr>
				<td>ID:</td>
				<td><form:input path="id" readonly="true" /></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Nice name:</td>
				<td><form:input path="niceName" /></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><form:input path="age"/></td>
			</tr>
			
			<tr>
				<td>兴趣</td>
				<td>
					 <form:checkbox path="hobbies" value="火影"/>看火影
	               	 <form:checkbox path="hobbies" value="龙珠"/>看龙珠
	                 <form:checkbox path="hobbies" value="海贼王"/> 看海贼
				</td>
			</tr>
			
			<tr>
				<td>性别</td>
				<td>
					 <form:radiobutton path="sex" value="男"/>男<br/>
	               	 <form:radiobutton  path="sex" value="女"/>女<br/>
	               	 <form:radiobutton  path="sex" value="中偏男"/>中偏男 <br/>
	                 <form:radiobutton  path="sex" value="中偏女"/>中偏女 <br/>
	               	 <form:radiobutton  path="sex" value="中"/>中 <br/>
	                 <form:radiobutton   path="sex" value="无"/>无 <br/>
	                 <form:radiobutton  path="sex" value="双"/>双 <br/>
				</td>
			</tr>
			
		</table>
		<input type="submit" value="保存">
	</form:form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
</head>
<body>
	<a href="gotoAdd">Add</a>
	<table>
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>NiceName</td>
			<td>Age</td>
		</tr>
		<c:forEach var="user" items="${userList }">
			<tr>
				<td>${user.id }</td>
				<td>${user.name }</td>
				<td>${user.niceName }</td>
				<td>${user.age }</td>
				<td><a href="${basePath }/user/get/${user.id }" target="_blank">详细</a></td>
				<td><a href="${basePath }/user/willEdit/${user.id }">编辑</a></td>
				<td>
					<form:form method="delete" action="${basePath }/user/delete/${user.id }">
						<input type="submit" value="删除"/>
					</form:form>
					<%-- <a  href="${basePath }/user/delete/${user.id }">删除</a> --%>
				
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

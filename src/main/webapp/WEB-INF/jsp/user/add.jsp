<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
<script type="text/javascript" src="${basePath}/static/js/jquery.js"></script>
</head>
<body>
	<form action="${basePath}/user/add" method="post">

		Name:<input name="name" type="text" /> <br> Nice Name:<input
			name="niceName" type="text" /> <br> Age:<input name="age"
			type="text" /><br> <input type="submit" value="提交">
	</form>

	<hr />
	<!-- 对象传参 ，多个不同对象接受参数 -->
	<form action="${basePath}/user/add" method="post">

		<h1>对象传参 ，多个不同对象接受参数</h1>
			Name:<input name="user.name" type="text" /> <br> 
			Nice Name: <input name="user.niceName" type="text" /> <br> 
			Age:<input name="user.age" type="text" /><br> 
			otherUsername:<input name="otherUser.name" type="text" /><br> 
			otherUser niceName:<input name="otherUser.niceName" type="text" /><br> 
		
			<input type="submit" value="提交">
	</form>

	<hr />
	
	<button id="addUser">发送ajax数据到后台</button>(*经过测试可以支持一次上传多个对象到后台，在ajax的data中需要指定类似"user.name":"ddd"这样指定，后台参数通过配置requestBody注解就可以接受json了)
	<br/><br/>
	<button id="addAllUser">一次提交多个用户</button>(*经过测试ajax上传的时候，属性必须配置contentType:"application/json;charset=utf-8"，)<br/>
	后台接收参数必须添加@requestBody注解  list 接收
	<script>
		$(function(){
			
			$("#addUser").click(function(){
				alert("asdfasdf");
				$.ajax({
					url:"${basePath}/user/add",
					method:"post",
					data:{
						"user.name":"nbn1",
						"user.age":"11",
						"user.niceName":"eeee",
						"user.father.name":"fatherName",//对于复杂对象仍然可以使用多个点的方式进行注入
						"otherUser.name":"other",
						"otherUser.niceName":"otherNIceName"
					},
					success:function(result){
						alert("提交成功了");
					},
					failure:function(){
						alert("上传失败了")
					}
				});
			});
			
			var users=[{
				"name":"nbn1",
				"age":"11",
				"niceName":"eeee"
			},{
				"name":"nbn2",
				"age":"22",
				"niceName":"eeee"
			}];
			
			$("#addAllUser").click(function(){
				alert("开始提交");
				$.ajax({
						type:"post",
						contentType:"application/json;charset=utf-8", //设置请求头信息,这个属性必须配，不然会报415错误
						url:"${basePath}/user/addAll",
						dataType:"json",
						data:JSON.stringify(users),
						success:function(result){
							alert("提交成功了");
						},
						failure:function(){
							alert("上传失败了")
						}
				});
			});
			
		});
	
		
	
	
	</script>




</body>
</html>

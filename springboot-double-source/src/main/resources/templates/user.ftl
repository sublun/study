<html>
<head>
	<title>用户列表</title>
	<script type="text/javascript" src="/js/spring-first.js"></script>
</head>
<body>
	<table border="1">
		<tr>
			<th>用户id</th>
			<th>用户名</th>
			<th>密码</th>
			<th>手机号</th>
			<th>邮箱</th>
			<th>创建日期</th>
			<th>修改日期</th>
		</tr>
		<#list userList as user>
		<tr>
			<td>${user.id}</td>
			<td>${user.username}</td>
			<td>${user.password}</td>
			<td>${user.phone!}</td>
			<td>${user.email!}</td>
			<td>${user.created?string("yyyy-MM-dd HH:mm:ss")}</td>
			<td>${user.updated?string("yyyy-MM-dd HH:mm:ss")}</td>
		</tr>
		</#list>
	</table>
	<img src="/images/mm.jpg">
</body>
</html>
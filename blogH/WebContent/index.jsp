<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	/**
	*	
	*	time: 2017年9月25日 下午4:44:18
	*/
	String path = request.getContextPath(); //得到项目名
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

		<%
			response.sendRedirect("main");
		%>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>BLOGH</title>
	</head>
	<body>
		<h1>BlogH</h1>
		<hr>
	</body>
</html>
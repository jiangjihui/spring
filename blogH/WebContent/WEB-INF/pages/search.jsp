<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	/**
	*	
	*	time: 2017年9月26日 上午8:31:04
	*/
	String path = request.getContextPath(); //得到项目名
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>标题</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link rel="icon" href="../../favicon.ico">
	    <link rel="stylesheet" href="css/bootstrap.min.css">
	    <link rel="stylesheet" href="css/main.css">
	    
		<script src="js/jquery-3.2.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
		
		<%@ include file="header.jsp" %>
	
	    <div class="container">
	
	      <div class="blog-header">
	        <h1 class="blog-title">The Bootstrap Blog</h1>
	        <p class="lead blog-description">The official example template of creating a blog with Bootstrap.</p>
	      </div>
	
	      <div class="row">
	
	        <div class="col-sm-8 blog-main">
	
			<%@ include file="notelist.jsp" %>
			
			 <nav>
			  <ul class="pager">
			    <li><a <c:if test="${pageInfo.hasPreviousPage }">href="search?condition=${condition }&page=${pageInfo.pageNum - 1 }"</c:if> >上一页</a></li>
			    <li><a <c:if test="${pageInfo.hasNextPage }">href="search?condition=${condition }&page=${pageInfo.pageNum +1 }"</c:if> >下一页</a></li>
			  </ul>
			</nav>
	
	        </div><!-- /.blog-main -->
	
	        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<%@ include file="sidebar.jsp" %>
	        </div><!-- /.blog-sidebar -->
	
	      </div><!-- /.row -->
	
	    </div><!-- /.container -->
	
	    <%@ include file="footer.jsp" %>
		
	</body>
</html>
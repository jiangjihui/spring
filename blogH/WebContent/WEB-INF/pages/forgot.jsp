<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	/**
	*	管理员登录
	*	time: 2017年8月19日 下午4:26:27
	*/
	String path = request.getContextPath(); //得到项目名
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html class="no-js">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
		<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
		
		 	<!-- Facebook and Twitter integration -->
		<meta property="og:title" content=""/>
		<meta property="og:image" content=""/>
		<meta property="og:url" content=""/>
		<meta property="og:site_name" content=""/>
		<meta property="og:description" content=""/>
		<meta name="twitter:title" content="" />
		<meta name="twitter:image" content="" />
		<meta name="twitter:url" content="" />
		<meta name="twitter:card" content="" />
		
		<title>忘记密码|BlogH</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/login-animate.css">
		<link rel="stylesheet" href="css/login-style.css">
		
		<script src="js/jquery-3.2.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/sha1.js"></script>
		<script src="js/login.js"></script>
		<script src="js/modernizr-2.6.2.min.js"></script>
		
		<!-- Placeholder -->
		<script src="js/jquery.placeholder.min.js"></script>
		<!-- Waypoints -->
		<script src="js/jquery.waypoints.min.js"></script>
		<!-- Main JS -->
		<script src="js/login.js"></script>
			
		
		
	</head>
	<body class="style-2">

		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<ul class="menu">
						<!-- <li><a href="index.html">Style 1</a></li>
						<li><a href="index2.html">Style 2</a></li>
						<li class="active"><a href="index3.html">Style 3</a></li> -->
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<!-- Start Sign In Form -->
					<form action="#" class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
						<h2>忘记密码</h2>
						<div class="form-group">
							<div class="alert alert-success" role="alert">Your email has been sent.</div>
						</div>
						<div class="form-group">
							<label for="email" class="sr-only">邮箱</label>
							<input type="email" class="form-control" id="email" placeholder="Email" autocomplete="off">
						</div>
						<div class="form-group">
							<p><a href="login">登录</a> or <a href="signup">注册</a></p>
						</div>
						<div class="form-group">
							<input type="submit" value="Send Email" class="btn btn-primary">
						</div>
					</form>
					<!-- END Sign In Form -->

				</div>
			</div>
			<div class="row" style="padding-top: 60px; clear: both;">
				<div class="col-md-12 text-center"><p><small>&copy; All Rights Reserved. xqlsrjjjh. <a href="#">BlogH</a></small></p></div>
			</div>
		</div>
	
	</body>
	
</html>
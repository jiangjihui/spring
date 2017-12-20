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
	    <link rel="icon" href="img/favicon.ico">
	    <link rel="stylesheet" href="css/bootstrap.min.css">
	    <link rel="stylesheet" href="css/main.css">
	    
		<script src="js/jquery-3.2.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/bootbox.js"></script>
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
	        
				<c:if test="${friend==null }"><button id="specialcare" type="button" class="btn btn-info navbar-right">关注</button></c:if>
				<c:if test="${friend!=null }"><button id="cancelspecialcare" type="button" class="btn btn-info navbar-right">取消关注</button></c:if>
	        
			    <img alt="头像" src="${cust.imgpath }">
			    <address class="info-address">Philippines, Afela inc.<br> Talay st. 65, PO Box 6200 </address>
			    <p>Phone: ${cust.phone }</p>
			    <p>Email: <a href="mailto:${cust.email }">${cust.email }</a></p>
			    <p>生日: ${cust.birthday }</p>
			
			    <a href="#" id="sidenav-close">
			      <i class="ti-close"></i>
			    </a>
	
	        </div><!-- /.blog-main -->
	
	        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<%@ include file="sidebar.jsp" %>
	        </div><!-- /.blog-sidebar -->
	
	      </div><!-- /.row -->
	
	    </div><!-- /.container -->
	
	    <%@ include file="footer.jsp" %>
		
	</body>
	<script>
	$("#specialcare").click(function() {
		bootbox.confirm({
			message:'确认关注该博主吗？<br/><br/>博主名称：<strong>${cust.loginname}</strong>',
	  			callback:function (result){
	   			if(result){
		    		$.ajax({  
	                    type : "POST",  //提交方式  
	                    url : "specialcare",//路径  
	                    data : {  
	                        "id" : ${cust.id }  
	                    },//数据，这里使用的是Json格式进行传输  
	                    success : function(result) {//返回数据根据结果进行相应的处理  
	                        if ( result.success) {  
	                            bootbox.alert(result.success,function (){
	                            	location.reload();
	                            });
	                        } else {  
	                        	bootbox.alert(result.error);
	                        }  
	                    }  
	                });
	   			}
	  			},
	  			title:"确认"
	  		});
	});
	$("#cancelspecialcare").click(function() {
		bootbox.confirm({
				message:'确认取消关注该博主吗？<br/><br/>博主名称：<strong>${cust.loginname}</strong>',
	  			callback:function (result){
	   			if(result){
		    		$.ajax({  
	                    type : "POST",  //提交方式  
	                    url : "cancelspecialcare",//路径  
	                    data : {  
	                        "id" : ${cust.id }  
	                    },//数据，这里使用的是Json格式进行传输  
	                    success : function(result) {//返回数据根据结果进行相应的处理  
	                        if ( result.success) {  
	                            bootbox.alert(result.success,function (){
	                            	location.reload();
	                            });
	                        } else {  
	                        	bootbox.alert(result.error);
	                        }  
	                    }  
	                });
	   			}
	  			},
	  			title:"确认"
	  		});
	});
	</script>
</html>
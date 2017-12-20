<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- jsp页面加入快捷键开发者模式 -->
<link href="css/toastr.min.css" media="all" rel="stylesheet" type="text/css"/>		<!-- 引入toastr弹框 -->
<script src="js/toastr.min.js" type="text/javascript"></script>
<script>
	
	function HotKey(e) {
				var theEvent = window.event || e;			//兼容火狐浏览器
		        var a = theEvent.keyCode;
		        if ((a == 49) && (theEvent.altKey)) {
		            //window.open("http://www.baidu.com", '_blank')
		            var jspName = "<%=request.getServletPath()%>";			// 获取jsp文件路径		
		            
		            //bootbox.setLocale("zh_CN");		//使用bootbox弹框，设置弹框语言为中文
		            //bootbox.alert(jspName);
		            
		            //初始化toastr弹框，可以不做此操作
		            toastr.options = {
					  "closeButton": true,		//是否配置关闭按钮
					  "debug": false,
					  "positionClass": "toast-top-right",		//消息框的显示位置
					  "onclick": null,
					  "showDuration": "100",
					  "hideDuration": "1000",
					  "timeOut": "5000",
					  "extendedTimeOut": "1000",
					  "showEasing": "swing",
					  "hideEasing": "linear",
					  "showMethod": "fadeIn",
					  "hideMethod": "fadeOut"
					}
		            toastr.info('JSP信息', jspName);		//使用toastr弹框
		    	}
		    }
	
		    // 初始化加载
		    $(document).ready(function () {
		        document.onkeydown = HotKey;
	
		    });
	
</script>
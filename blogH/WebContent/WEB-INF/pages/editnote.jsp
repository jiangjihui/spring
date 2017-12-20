<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<form class="form-horizontal required-validate" action="savenote" enctype="multipart/form-data" method="post" onsubmit="return noteSubmit(this)">
 	<div class="form-group">
        <div class="col-lg-10">
          <input type="text" name="title" placeholder="" data-required="true" class="form-control" value="标题">
        </div>
        <div class="col-lg-2 navbar-right">
			<button type="submit" class="btn btn-primary">保存</button>
		</div>
 	</div>
	<input type="text" name="note" hidden="true">
	 <div class="form-group">
		<hr>
	   	<div id="sumEdit" class="summernote" name="note" placeholder="请进行详细的描述，使更多的人了解" action="upnotefile">${note}</div>
	</div>
</form>
	    
<script type="text/javascript">

	if(!checkLogin()){
		$('#sumEdit').summernote('disable');
	}

	//检查用户是否登录
	function checkLogin() {
    	var canSubmit=false;
		//检查用户是否登录
   		$.ajax({  
               type : "POST",  //提交方式  
               url : "checkLogin",//路径  
               data : {  
                   //"serialno" : serialno  
               },//数据，这里使用的是Json格式进行传输  
               async:false,		//ajax设置为同步请求
               success : function(result) {//返回数据根据结果进行相应的处理  
                   if ( result.success) {  
                	   canSubmit=true;
                   } else {  
                	   bootbox.alert(result.error);
                   }  
               }  
           });
		return canSubmit;
	}

    function noteSubmit(form) {
        if (!$("#sumEdit").summernote('isEmpty') && checkLogin()) {
            $("input[name='note']").val(Base64.encode($("#sumEdit").summernote('code')));
            return true;
        } else {
            return false;
        }
	}
    
	function sumShow() {
		var markupStr = $('#sumEdit').summernote('code');		//获取summernote填写的信息
		var afterEncode = Base64.encode(markupStr);
		var afterDecode = Base64.decode(afterEncode);
		alert(afterEncode+"\n"+afterDecode);
		return;
	}
	//初始化富文本编辑器
    $('div.summernote').each(function() {
        var $this = $(this);
        var placeholder = $this.attr("placeholder") || '';
        var url = $this.attr("action") || '';
        $this.summernote({
            lang : 'zh-CN',		//指定语言为中文简体
            placeholder : placeholder,		//summernote初始化显示的内容
            minHeight : 300,
            dialogsFade : true,// 增加summernote上弹出窗口滑进滑出的动态效果
            dialogsInBody : true,// 这个属性也很关键，默认为false，字面上的意思是summernote的弹出框是否在body中（in嘛），设置为false时，dialog的式样会继承其上一级外部（如上文中的form-horizontal）容器式样，那么显示的效果就很别扭，这里也不再上图；那么设置为true时，就不会继承上一级外部div的属性啦，从属于body嘛
            // summernote.
            disableDragAndDrop : false,// 设置为false吧，有的时候拖拽会出点问题，你可实践
            // and drop
            callbacks : {
            	// onImageUpload的参数为files，summernote支持选择多张图片
                onImageUpload : function(files) {
                    var $files = $(files);

                    // 通过each方法遍历每一个file
                    $files.each(function() {
                        var file = this;
                        // FormData，新的form表单封装，具体可百度，但其实用法很简单，如下
                        var data = new FormData();

                        // 将文件加入到file中，后端可获得到参数名为“file”
                        data.append("file-zh[]", file);

                        // ajax上传
                        $.ajax({
                            data : data,
                            type : "POST",
                            url : url,// div上的action
                            cache : false,
                            contentType : false,
                            processData : false,

		                    success : function(result) {//返回数据根据结果进行相应的处理  
		                        if ( result.success) {  
                                    // 获取后台数据保存的图片完整路径
                                    var imageUrl =result.success;
                                    // 插入到summernote
                                    $this.summernote('insertImage', imageUrl, function ($image) {
                                    	  $image.css('width', $image.width() / 2);
                                    	});
		                        } else {  
		                        	alert(result.error);
		                        }  
		                    },  
                            // ajax请求失败时处理
                            error : console.log("ajax请求失败时处理")
                        });
                    });
                }
            }
        });
    });
  </script>

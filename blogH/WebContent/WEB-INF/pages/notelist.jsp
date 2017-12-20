<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:forEach items="${pageInfo.list }" var="note">
<div class="blog-post">
	<div>
	<hr>
	  <h3 class="blog-post-title">${note.title }</h3>
	  <p  class="blog-post-meta">${note.createtime }<%-- <fmt:formatDate type='date' value='${note.createtime }' pattern=' yyyy/MM/dd hh:mm:ss' /> --%> by 
		<a href="customerlist?customerid=${note.customerid }" style="font-weight: 900;">${note.customername }</a>
		<c:if test="${customer.id==note.customerid }"><a class="navbar-right  del-btn" style="margin-right: 10px;cursor:pointer;font-size:14px;" noteid="${note.id }" notetitle="${note.title }" >删除</a></c:if>
	  </p>
	</div>
  <div>${note.note }</div>
  </div><!-- /.blog-post -->
</c:forEach>

<script src="js/bootbox.js"></script>
<script>
/*给每个删除按钮绑定删除事件*/
$(".del-btn").click(function() {
	var $this = $(this);
	var noteid = $this.attr("noteid");
	var notetitle = $this.attr("notetitle");
	//删除blog
	bootbox.confirm({
  			message:'确认删除该blog吗？<br/><br/>标题：<strong>'+notetitle+'</strong>',
  			callback:function (result){
   			if(result){
	    		$.ajax({  
                    type : "POST",  //提交方式  
                    url : "delnote",//路径  
                    data : {  
                        "id" : noteid  
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
  			title:"确认删除"
  		});
});
</script>
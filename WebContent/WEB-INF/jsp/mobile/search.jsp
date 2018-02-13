<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
		
		<script src="<%=basePath %>resources/scripts/jquery-form.js"></script>

		<style>
			.ban{
				font-size:0.5em;
			}
		</style>
	</head>
	<body>
	
		<div data-role="page" id="home" data-title="小说搜索">
			
			<div data-role="content" >
				<form action="">
			    	<div data-role="fieldcontain">
				        <input type="text" name="fullname" id="bookname" placeholder="请输入书名" style="display:block;">
				        <a data-role="button" id="search"  data-icon="search">搜索</a>
			    	</div>
			    </form>
			    
			    <div id="result">
			    	<ul data-role="listview" data-inset="true">
						
			    	</ul>
			    </div>
			</div>
			
		</div>
		
	<script>
 		$(function(){
 			$("#search").click(function(){
 				var bookname = $("#bookname").val();
 				var url = '<%=basePath %>mobile/search';
 				var options = {
 					url: url,
 					type : 'POST',
 					data : {
 						bookname : bookname
 					},
 					success: function (data) {
 						if(data.length > 0){
 							updateresult(data);
 						}else{
 							alert("没有查到相关书籍！");
 						}
 						
 					}
 				}; 
 				//这是用ajax的方式提交表单，不用刷新页面，而且这个插件提交表单可以
 				//带上文件，比较实用
 				$("form").ajaxSubmit(options);
 			});
		}) 
		
		//根据查询结果刷新result div
		function updateresult(data){
 			var html = "";
 			
 			//更新查询结果的时候将以前的查询结果清空
 			$('#result>ul').empty();
 			
 			for(var i=0; i<data.length; i++){
 				var item = data[i];
 				
 				html += "<li>";
 				
 				html += '<a data-ajax="false" href="<%=basePath %>mobile/test?url=' + item.bookUrl + '" >' + 
						 	'<img src="' + item.bookPicUrl + '">' + 
						 	item.bookName + 
						 	'<br/>' +
						 	'<span class="ban">'+ item.type +'</span>' +
						 	'<br/>' +
						 	'<span class="ban">'+ item.auth +'</span>' +
						 	'<br/>' +
							'</a>'; 
 				html += "</li>";
 			}
 			$("#result>ul").append(html); 
 			$("#result>ul").listview("refresh");            
 		}
	</script>	
	</body>
</html>
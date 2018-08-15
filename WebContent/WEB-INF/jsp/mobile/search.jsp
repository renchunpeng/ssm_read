<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="/WEB-INF/jsp/common/jqueryMobile.jsp" %>
		<script src="<%=basePath %>resources/scripts/jquery-form.js"></script>

		<link rel="stylesheet" href="<%=basePath %>resources/css/commonCss.css" type="text/css">
		<script src="<%=basePath %>resources/scripts/commonJs.js"></script>
		<style>
			.ban{
				font-size:0.5em;
			}

		</style>
	</head>
	<body>
		<div id="mask" class="mask"></div>
		<div data-role="page" id="home" data-title="小说搜索">
			<div data-role="header" data-position="fixed">
				<h1>小说搜索</h1>
				<a href="<%=basePath %>mobile/bookList" data-ajax="false" data-role="button" class="ui-btn-left" data-icon="back">返回</a>
			</div>
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
				showMask();
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
 						hideMask();
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
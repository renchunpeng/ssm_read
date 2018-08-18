<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld" %>
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

		<style>
			a{
				background:rgb(241,229,201);
			}
		</style>
	</head>
	<body>
	
		<div data-role="page" id="home" data-title="${bookName }">
			<div data-role="header" data-position="fixed">
				<c:if test="${isNewList }">
					<a href="<%=basePath %>mobile/test?url=${bookUrl}" data-ajax="false" data-role="button" class="ui-btn-left" data-icon="star">所有章节</a>
					<h1>最新章节</h1>
				</c:if> 
				
				<c:if test="${isAll }">
					<a href="<%=basePath %>mobile/test?url=${bookUrl}&isNewList='true'" data-ajax="false" data-role="button" class="ui-btn-left" data-icon="star">最新章节</a>
					<h1>所有章节</h1>
				</c:if> 
			
				<a href="<%=basePath %>mobile/bookList" data-ajax="false" data-role="button" class="ui-btn-right" data-icon="home">首页</a>
			</div>
			
			<div data-role="content" >
				<c:if test="${display}">
					<button onclick="addBook()" id="addBook" >添加到书架</button>
				</c:if>

				<ul data-role="listview" data-inset="true" >
					<c:forEach items="${lists}" var="item"> 
						<li><a url="${item.url}"><c:out value="${item.title}"></c:out></a></li>
				    </c:forEach>  
				</ul>
			</div>
		</div>
		
	<script>
 		$(function(){
			$("div[data-role='content']").find("a").click(function(){
				var pageUrl = $(this).attr("url");
				if("${isNewList }"){
					window.location.href = "<%=basePath%>mobile/getContent?url=" + pageUrl + "&isNewList=" + "true";
				}else{
					window.location.href = "<%=basePath%>mobile/getContent?url="+pageUrl;
				}
				
			});
			
/*			//判断加入书架的按钮是否需要显示
			var bookList = "${sessionScope.bookList}";
			if(bookList.indexOf("${bookUrl}") > -1){
				$("#addBook").parent().hide();  
			}*/
			
		}) 
		
		//添加书籍到书架
		function addBook(){
 			var bookName = "${bookName}";
 			var bookUrl = "${bookUrl}";
 			var picUrl = "${picUrl}";
 			
 			$.ajax({
 				url:"<%=basePath %>mobile/addBook",
 				data:{
 					bookName:bookName,
 					bookUrl:bookUrl,
 					picUrl:picUrl
 				},
 				success:function(result){
 		        	alert("书籍已成功添加到书架！");
 		        	$("#addBook").parent().hide();  
 		    	}
 			});
 		}
	</script>	
	</body>
</html>
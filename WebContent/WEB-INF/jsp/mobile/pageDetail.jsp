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
		
		<style>
 			#content{
				<%-- background: url(<%=basePath%>images/timg.jpg) repeat; --%>
				background:rgb(241,229,201);
			} 
		</style>
	</head>
	<body>
		<!--首页-->
		<div data-role="page" id="page_detail" data-title="${title }">
			<div data-role="content" id="content">
				${content }
			</div>
			
			<div data-role="footer"  data-position="fixed">
				<div data-role="navbar">
					<ul>
						<li>
							<a url="${pre }" id="pre" data-icon="arrow-l">上一章</a>
						</li>
						<li>
							<a url="<%=basePath %>mobile/test" id="list">返回目录</a>
						</li>
						<li>
							<a id="bookmark">加入书签</a>
						</li>
						<li>
							<a url="${next }" id="next" data-icon="arrow-r">下一章</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	
	<script>
		$(function(){
			$("a:not(#list,#bookmark)").click(function(){
				var pageUrl = $(this).attr("url");
				if(pageUrl.indexOf(".html") <= 0 ) {
				    alert("已经没有了!");
				    return;
				}

				window.location.href = "<%=basePath%>mobile/getContent?url="+pageUrl;
			});
			
			$("#list").click(function(){
				var bookUrl = $("#pre").attr("url");
				var value = bookUrl.substring(0, bookUrl.lastIndexOf('/'))
				var pageUrl = $(this).attr("url");
				if("${isNewList }"){
					window.location.href = pageUrl + "?url=" + value + "&isNewList=" + "true";
				}else{
					window.location.href = pageUrl + "?url=" + value;
				}
			});

			$("#bookmark").click(function(){
				$.ajax({
					url :"<%=basePath%>mobile/saveBookMark",
					data :{"bookMark":"${thisUrl }"},
					type : "post",
					dataType:"json",
					success: function (result) {
						if (result.success) {
							alert("书签保存成功！");
						} else {
							alert("书签保存失败！");
						}
					}
				});
			});
		}) 


	</script>
	</body>
</html>
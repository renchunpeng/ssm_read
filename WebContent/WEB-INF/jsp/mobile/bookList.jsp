<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
		
<%-- 		<link rel="stylesheet" href="<%=basePath %>themes/skyd.min.css" />
		<link rel="stylesheet" href="<%=basePath %>themes/jquery.mobile.icons.min.css" /> --%>
		
		<style>
			span{
				font-size:10px;
			}
			
			#updateText{
				font-size:0.5em;
				color:red;
				position: relative;
				bottom:5px;
			}
			
		</style>
	</head>
	<body>
	
		<div data-role="page" id="home" data-title="追书神器" >
			<div data-role="header" data-position="fixed" >
				<div data-role="navbar" >
			      <ul>
			        <li><a href="<%=basePath %>mobile/bookList" data-ajax="false" data-icon="home" class="ui-btn-active">我的书架</a></li>
			        <li><a href="<%=basePath %>mobile/tosearch" data-ajax="false" data-icon="search">书城</a></li>
			      </ul>
			    </div>
			</div>
			
			<div data-role="content" >
				<ul data-role="listview" data-inset="true">
					<c:forEach items="${lists}" var="item"> 
						<li>
							<a url="<%=basePath %>mobile/test?url=${item.bookUrl}" >
								<img src="${item.imgUrl }">${item.name } 
 								<c:if test="${item.update }">
								   <span id="updateText">更新</span>
								</c:if> 
								<br/>
								<span>${item.lastUpdateDate }<br/>${item.lastPageName }</span>
							</a>
						</li>
				    </c:forEach>  
			    </ul>
			</div>
		</div>
		
	<script>
		$(function(){
			$("div[data-role='content']").find("a").click(function(){
				var pageUrl = $(this).attr("url");
				window.location.href = pageUrl + "&isNewList=" + "true";
			});
		}) 
	</script>	
	</body>
</html>
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
			#divImg{
				display: flex;
  				justify-content: center;
  				align-items: center;
			}
		</style>
	</head>
	<body>
	
		<div data-role="page">
			<div data-role="content">
				<div id="divImg">
					<img style="text-ali" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516878959632&di=9e25e868cb0a0b74c6454327418615d6&imgtype=0&src=http%3A%2F%2Fwww.pc6.com%2Fup%2F2013-7%2F20137259258.png" />
				</div>
				<form action="#" method="post">
					<input type="text" name="zhanghao" id="zhanghao" placeholder="账号:" />
					<input type="password" name="mima" id="mima" placeholder="密码:" />
					<a data-role="button" data-theme="b">登录</a>
				</form>
			</div>
		</div>
		
	<script>
 		$(function(){
  			$("a").click(function(){
 				var name = $("#zhanghao").val();
 				var pwd = $("#mima").val();
 				
 				var url = '<%=basePath %>login/doLogin';
 				var options = {
 					url: url,
 					type : 'POST',
 					data : {
 						name : name,
 						pwd : pwd
 					},
 					success: function (data) {
 						
 						if(data == "success"){
 							window.location.href = "<%=basePath %>mobile/bookList";
 						}else{
 							alert(data);
 						}
 						
 					}
 				}; 
 				//这是用ajax的方式提交表单，不用刷新页面，而且这个插件提交表单可以
 				//带上文件，比较实用
 				$("form").ajaxSubmit(options);
 			}); 
		}) 
	</script>	
	</body>
</html>
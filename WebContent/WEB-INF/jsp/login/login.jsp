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
				background-image:url("<%=basePath %>images/login2.jpg");
				background-repeat: no-repeat;
			}
		</style>
	</head>
	<body>
		<div data-role="page">
			<div data-role="content">
				<form action="#" method="post">
					<input type="text" name="zhanghao" id="zhanghao" placeholder="账号:" />
					<input type="number" name="mima" id="mima" placeholder="密码:" />
					<div class="ui-grid-a">
						<div class="ui-block-a">
							<a data-role="button" data-theme="b" id="login">登录</a>
						</div>

						<div class="ui-block-b">
							<a data-role="button" data-theme="b" href="<%=basePath %>login/goRegister" data-ajax="false">注册</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		
	<script>
 		$(function(){
  			$("#login").click(function(){
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
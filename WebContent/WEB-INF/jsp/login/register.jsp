<%--
  Created by IntelliJ IDEA.
  User: rcp
  Date: 2018/8/14
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<html>
<head>
    <title>用户注册</title>
    <%@include file="/WEB-INF/jsp/common/jqueryMobile.jsp" %>

    <link rel="stylesheet" href="<%=basePath %>resources/css/commonCss.css" type="text/css">
    <script src="<%=basePath %>resources/scripts/commonJs.js"></script>
</head>
<body>
<div data-role="page">
    <div id="mask" class="mask"></div>
    <div data-role="header">
        <a href="<%=basePath %>login/goLogin" data-ajax="false" data-role="button">返回登录</a>
        <h1>用户注册</h1>
    </div>
    <div data-role="content">
        <form id="form1">
            <label for="userNumber">账号：</label>
            <input type="text" name="name" id="userNumber" placeholder="请输入账号"/>
            <label for="userPw">密码：</label>
            <input type="text" name="password" id="userPw" placeholder="请输入密码"/>
            <label for="userName">姓名：</label>
            <input type="text" name="userName" id="userName" placeholder="请输入姓名"/>
            <input type="button" value="免费注册" data-theme="b" onclick="check()"/>
        </form>
    </div>
    <div data-role="footer" data-position="fixed">
        <h1>版权所有：春哥</h1>
    </div>
</div>
</body>
<script language="javascript">
    function check() {
        var userNumber = $("#userNumber").val();
        var userPw = $("#userPw").val();
        var userName = $("#userName").val();
        if (userNumber == null || userNumber == "") {
            $("#info").html("请输入账号!");
            return false;
        }
        if (userPw == null || userPw == "") {
            $("#info").html("请输入密码!");
            return false;
        }
        if (userName == null || userName == "") {
            $("#info").html("请输入姓名!");
            return false;
        }
        showMask();
        $.ajax({
            url:"<%=basePath%>login/register",
            type:"POST",
            data:{
                name:userNumber,
                password:userPw,
                realname:userName
            },
            success:function(result){
                alert(result);
                hideMask();
                if(result.success){
                    alert("用户注册成功！");
                    window.location.href = "<%=basePath%>login/goLogin";
                }
            }
        });
    }
</script>
</html>

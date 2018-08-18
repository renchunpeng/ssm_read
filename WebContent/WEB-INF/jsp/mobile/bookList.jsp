<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/jsp/common/jqueryMobile.jsp" %>

    <link rel="stylesheet" href="<%=basePath %>resources/css/commonCss.css" type="text/css">
    <script src="<%=basePath %>resources/scripts/commonJs.js"></script>
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
    <div id="mask" class="mask"></div>
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
                    <a url="<%=basePath %>mobile/test?url=${item.bookUrl}" bookMark="${item.bookMark}">
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

    <a href="#myPopup" data-rel="popup" class="ui-btn ui-btn-inline ui-corner-all" style="display: none">显示弹窗</a>

    <div data-role="popup" id="myPopup" class="ui-content">
        <div data-role="header">
            <h1>信息确认</h1>
        </div>

        <div data-role="main" class="ui-content">
            <h3>确定将本书从书籍列表中移除吗?</h3>
            <div class="ui-grid-a">
                <div class="ui-block-a">
                    <button onclick="removeBook()" class="ui-btn ui-icon-check ui-btn-icon-left">确定</button>
                </div>

                <div class="ui-block-b">
                    <button onclick="closeDialog()" class="ui-btn ui-icon-refresh ui-btn-icon-right">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function(){
        $("div[data-role='content']").find("a").click(function(){
            var bookMark = $(this).attr("bookMark");
            if(bookMark){
                window.location.href = "<%=basePath %>mobile/getContent?url=" + bookMark;
            }else{
                var pageUrl = $(this).attr("url");
                window.location.href = pageUrl + "&isNewList=" + "true";
            }
        });

        /*长按删除书籍*/
        $("li a").on("taphold",function(){
            removeBookUrl = $(this).attr("url");
            $("#myPopup").popup("open");
        });
    })

    function removeBook() {
        closeDialog();
        showMask();
        var x = removeBookUrl.indexOf("?");
        var bookUrl = removeBookUrl.substring(x+5,removeBookUrl.length)
        $.ajax({
            url:"<%=basePath %>mobile/removeBookList",
            data:{
                bookUrl:bookUrl
            },
            success:function(result){
                alert("已成功从书籍列表移除！");
                location.reload(true);
            }
        });
    }

    function closeDialog() {
        $("#myPopup").popup("close");
    }
</script>
</body>
</html>
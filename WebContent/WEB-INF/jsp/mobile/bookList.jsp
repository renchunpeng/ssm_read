<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/jsp/common/jqueryMobile.jsp" %>
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
    })
</script>
</body>
</html>
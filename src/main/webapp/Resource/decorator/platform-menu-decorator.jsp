<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/Resource/include/feep-global-header.jsp" %>
    <link href="${contextPath}/Resource/css/responsive-nav.css" rel="stylesheet">
</head>
<body>
<div class="header index-header-img">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-24">
                <h2>
                    <strong>Future Element Empolder Platform</strong>
                </h2>
            </div>
            <div class="col-md-24">
                <p>只需专注业务，其他的交给平台来处理吧！</p>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-default navbar-static-top indexNavbar">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand index-navbar-brand" href="javascript:0">FEEP</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="nav-active"><a href="#">数据管理</a></li>
                <li><a href="#">系统管理</a></li>
                <li><a href="#">资源管理</a></li>
                <li><a href="#">任务管理</a></li>
                <li><a href="#">流程管理</a></li>
                <li><a href="#">版本管理</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                        aria-expanded="false">报表中心 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">数据表</a></li>
                        <li><a href="#">数据模型</a></li>
                        <li><a href="#">数据字典</a></li>
                        <li class="divider"></li>
                        <li><a href="#">工作流</a></li>
                        <li class="divider"></li>
                        <li><a href="#">定时任务</a></li>
                    </ul>
                </li>
            </ul>
            <div class="navbar-form navbar-right">
                <button type="button" class="btn btn-primary" aria-label="Left Align" onclick="logout();">
                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;登出
                </button>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown"><a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button"
                                        aria-expanded="false">开发手册<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">服务端</a></li>
                        <li class="divider"></li>
                        <li><a href="#">客户端</a></li>
                        <li class="divider"></li>
                        <li><a href="#">工作流</a></li>
                    </ul>
                </li>
                <li><a href="#">JSON 视图</a></li>
                <li><a href="#">正则测试</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid index-background">
    <div class="row">
        <div class="col-md-3">
            <div role="navigation" id="index-menu-left" class="nav-collapse">
                <ul>
                    <li class="active"><a href="#">数据表</a></li>
                    <li><a href="#">数据视图</a></li>
                    <li><a href="#">数据模型</a></li>
                    <li><a href="#">数据字典</a></li>
                    <li><a href="#">大数据</a></li>
                    <li><a href="#">缓存管理</a></li>
                </ul>
            </div>
        </div>
        <div class="index-content col-md-21">
            <div class="panel panel-default content-panel">
                <div class="panel-body">
                    <sitemesh:write property='feep'/>
                </div>
            </div>
        </div>
        <div class="col-md-3 navbar-fixed-bottom">
            <ul class="pager indexTopBtn">
                <li>
                    <a href="javascript:Feep.scroll2Top()">
                        <span class="glyphicon glyphicon-eject" aria-hidden="true"></span>&nbsp;Top
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="container-fluid index-footer">
    <p class="text-center">©2015&nbsp;未来元素信息技术有限公司&nbsp;<br>&nbsp;FEEP&nbsp;version&nbsp;1.0</p>
</div>
<%@ include file="/Resource/include/feep-js-lib.jsp" %>
<script>
    $(function () {
        setNavTop();
        $(window).resize(resize);
        resize();
    });

    function resize() {
        $(".index-background").css("min-height", $(window).height() - $(".index-header-img").height() - $(".indexNavbar").height());
        $('#index-menu-left').width($('#index-menu-left').parent().width())
    }
    function logout() {
        FUI.confirm("确定退出系统吗？", function (arg) {
            if (arg) {
                Feep.request("feep_logout", null, function () {
                    Feep.pageTo.login();
                }, function () {
                    FUI.alert(Feep.errorMsg);
                });
            }
        });

    }
    function setNavTop() {
        $(".indexNavbar").pin();
        Feep.scroll(function (clientHeight, scrollTop, scrollLeft) {
            if (scrollTop > 120) {
                $(".indexTopBtn").show("fast");
            } else {
                $(".indexTopBtn").hide("fast");
            }
        });
    }
</script>
</body>
</html>
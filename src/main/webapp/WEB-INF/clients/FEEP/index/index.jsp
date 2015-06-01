<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/Resource/include/feep-global-header.jsp" %>
<link href="${contextPath}/Resource/css/responsive-nav.css" rel="stylesheet">
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
        <div class="collapse navbar-collapse" style="font-weight: bold;">
                <ul class="nav navbar-nav">
                <li class="active"><a href="#">数据管理</a></li>
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
        <div class="col-md-21" style="padding: 10px 10px 10px 0;">
            <div style="height: 100%; width: 100%;">
                <div class="panel panel-info" style="margin: 0 0 5px 0;">
                    <div class="panel-body" style="padding: 15px 15px 5px 15px">
                        <div>
                            <form class="form-inline">
                                <div class="form-group" style="margin-left: 15px;">
                                    <label for="dbname">表名：</label>
                                    <input type="text" class="form-control" id="dbname">
                                </div>
                                <div class="form-group" style="margin-left: 15px;">
                                    <label for="showname">显示名：</label>
                                    <input type="email" class="form-control" id="showname">
                                </div>
                                <div class="form-group" style="margin-left: 15px;">
                                    <label for="sytem">所属系统：</label>
                                    <input type="email" class="form-control" id="sytem">
                                </div>
                                <div class="form-group" style="margin-left: 15px;">
                                    <label for="type">类型：</label>
                                    <input type="email" class="form-control" id="type">
                                </div>
                            </form>
                        </div>
                        <div style="border-top: 1px solid #e5e5e5;margin-top:15px;padding-top: 5px;text-align: center">
                            <button type="button" class="btn btn-success" style="min-width: 85px;margin-left: 15px;">
                                添加
                            </button>
                            <button type="button" class="btn btn-primary" style="min-width: 85px;margin-left: 15px;">
                                查询
                            </button>
                            <button type="button" class="btn btn-danger" style="min-width: 85px;margin-left: 15px;">重置
                            </button>
                        </div>
                    </div>
                </div>
                <div style="background-color: #FFFFFF">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr style="background-color: #666699;color:#FFFFFF;">
                            <th width="50px" class="text-center">序号</th>
                            <th width="15%">表名</th>
                            <th width="15%">显示名</th>
                            <th width="15%">所属系统</th>
                            <th width="15%">类型</th>
                            <th width="auto">备注</th>
                            <th width="200px" class="text-center">操作</th>
                        </tr>
                        </thead>
                        <c:forEach var="a" items="<%=new String[31]%>" varStatus="status">
                            <tr>
                                <td class="text-center">${status.index+1}</td>
                                <td>2</td>
                                <td>3</td>
                                <td>4</td>
                                <td>5</td>
                                <td>6</td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-link" style="padding : 0px 10px;">查看</button>
                                    <button type="button" class="btn btn-link" style="padding : 0px 10px;">修改</button>
                                    <button type="button" class="btn btn-link" style="padding : 0px 10px;">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
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
</body>
<%@ include file="/Resource/include/feep-js-lib.jsp" %>
<script>
    var index_scrollTop;
    function main() {
        setNavTop();
    }
    function onSizeChange(w, h) {
        $(".index-background").css("min-height", h - $(".index-header-img").height() - $(".indexNavbar").height());
        $('#index-menu-left').width($('#index-menu-left').parent().width())
    }
    function logout() {
        var ret = Feep.request("feep_logout");
        if (ret) {
            Feep.pageTo.login();
        }
    }
    function setNavTop() {
        Feep.scroll(function (clientHeight, scrollTop, scrollLeft) {
            if (scrollTop >= 120) {
                $(".indexNavbar").removeClass("navbar-static-top");
                $(".indexNavbar").addClass("navbar-fixed-top");
                $(".indexTopBtn").show("fast");
            } else {
                $(".indexNavbar").removeClass("navbar-fixed-top");
                $(".indexNavbar").addClass("navbar-static-top");
                $(".indexTopBtn").hide("fast");
            }
        });
    }
</script>
</html>

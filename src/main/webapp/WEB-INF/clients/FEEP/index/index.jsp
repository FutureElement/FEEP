<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
<meta charset="UTF-8">
<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>FEEP 开发平台</title>
<link href="${contextPath}/Resource/thirdLib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/Resource/css/feep.css" rel="stylesheet">
</head>
<style>
.index-header-img {
	background-repeat: no-repeat;
	background-position: center 0;
	-webkit-background-size: cover;
	background-size: cover;
	position: relative;
	height: 120px;
	padding-top: 43px;
	text-align: center;
}

</style>
<body>
	<div class="header index-header-img" style="background-image: url(/FEEP/Resource/img/index.jpg)">
		<!-- <div class="logoimg">
	  <a href="http://expo.bootcss.com"><img src="http://static.bootcss.com/expo/img/d/f5/ab31f6c55403cfa55ccb32bc7f29b.png" alt="Bootstrap 优站精选" width="78"></a>
	</div> -->
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h2 style="color: #FFFFFF; margin-bottom: 2px;">
						<strong>Future Element Empolder Platform</strong>
					</h2>
				</div>
				<div class="col-md-12">
					<p style="color: #FFFFFF; width: 100%;">只需专注业务，其他的交给平台来处理吧！</p>
				</div>
			</div>
		</div>
	</div>
	<nav id="indexNavbar" class="navbar navbar-default navbar-static-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand " href="#" style="color: #337ab7; font-weight: bold">FEEP</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
						</ul></li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="搜索项目">
					</div>
					<button type="submit" class="btn btn-default">查询</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button"
						aria-expanded="false">开发手册<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">服务端</a></li>
							<li class="divider"></li>
							<li><a href="#">客户端</a></li>
							<li class="divider"></li>
							<li><a href="#">工作流</a></li>
						</ul></li>
					<li><a href="#">JSON 视图</a></li>
					<li><a href="#">正则测试</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>
	<div class="container-fluid" id="indexContent" style="background-color: #CCCCCC; margin-top: -20px; height: 1000px;">
		<div class="row">
			<div class="col-md-3" style="padding-top: 20px;" data-spy="scroll" data-target="#indexMenu">
				<nav class="affix-top" data-spy="affix" data-offset-top="220" id="indexMenu" data-offset-bottom="20"
					style="top: 70px; width: 200px;">
					<ul class="list-group">
						<li class="list-group-item active">数据表</li>
						<li class="list-group-item">数据视图</li>
						<li class="list-group-item">数据模型</li>
						<li class="list-group-item">数据字典</li>
					</ul>
				</nav>
			</div>
			<div class="col-md-9"></div>
		</div>
	</div>
	<div class="container-fluid" id="indexFooter" style="background-color: #00AA88; height: 150px;"></div>
</body>
<script src="${contextPath}/Resource/thirdLib/jquery/jquery.js"></script>
<script src="${contextPath}/Resource/thirdLib/angularJs/angular.js"></script>
<script src="${contextPath}/Resource/thirdLib/bootstrap/js/bootstrap.min.js"></script>
<script src="${contextPath}/Resource/thirdLib/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="${contextPath}/Resource/js/feep.js"></script>
<script>
	function main() {
		$("#indexContent").css("min-height", $(window).height() - 200);
		setNavTop();
	}
	function setNavTop() {
		$(window).scroll(function() {
			//var htmlHeight=document.body.scrollHeight||document.documentElement.scrollHeight;
			//var clientHeight=document.body.clientHeight||document.documentElement.clientHeight;
			var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
			if (scrollTop >= 200) {
				$("#indexNavbar").removeClass("navbar-static-top");
				$("#indexNavbar").addClass("navbar-fixed-top");
			} else {
				$("#indexNavbar").removeClass("navbar-fixed-top");
				$("#indexNavbar").addClass("navbar-static-top");
			}
		});
	}
	function logout() {
		var ret = Feep.request("feep_logout");
		if (ret) {
			Feep.pageTo.login();
		}
	}
</script>
</html>

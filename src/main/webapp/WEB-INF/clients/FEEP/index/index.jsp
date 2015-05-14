<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Resource/include/feep-global-header.jsp"%>
<body>
	<div class="header index-header-img">
		<div class="container-fluid">
			<div class="row" style="padding-top: 43px;">
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
			<div class="collapse navbar-collapse" id="indexNavbar">
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
				<div class="navbar-form navbar-right">
					<button type="button" class="btn btn-default" aria-label="Left Align" onclick="logout();">
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
						</ul></li>
					<li><a href="#">JSON 视图</a></li>
					<li><a href="#">正则测试</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid" id="indexContent" style="background-color: #CCCCCC; margin-top: -20px;">
		<div class="row">
			<div class="col-md-2" style="padding: 0">
				<div class="list-group index-menu-left" id="indexMenu">
					<a class="list-group-item active">数据表</a> <a class="list-group-item">数据视图</a> <a class="list-group-item">数据模型</a> <a
						class="list-group-item">数据字典</a>
				</div>
			</div>
			<div id="indexContentRight" class="col-md-10" style="height: 1000px;border-left: 1px solid #999999;"></div>
		</div>
	</div>
	<div class="container-fluid" id="indexFooter" style="background-color: #484d4f; height: 100px;"></div>
</body>
<%@ include file="/Resource/include/feep-js-lib.jsp"%>
<script>
	function main() {
		setNavTop();
	}
	function onSizeChange(w, h) {
		$("#indexContent").css("min-height", h - 170);
		$('#indexMenu').css("width", w - $("#indexContentRight").width() - 55);
	}
	function setNavTop() {
		$(window).scroll(function() {
			//var htmlHeight=document.body.scrollHeight||document.documentElement.scrollHeight;
			//var clientHeight=document.body.clientHeight||document.documentElement.clientHeight;
			var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
			if (scrollTop >= 120) {
				$("#indexNavbar").removeClass("navbar-static-top");
				$("#indexNavbar").addClass("navbar-fixed-top");
				$('#indexMenu').removeClass("navbar-static-top");
				$('#indexMenu').addClass("navbar-fixed-top");
				$('#indexMenu').css("margin-top", "70px");
			} else {
				$("#indexNavbar").removeClass("navbar-fixed-top");
				$("#indexNavbar").addClass("navbar-static-top");
				$('#indexMenu').addClass("navbar-static-top");
				$('#indexMenu').removeClass("navbar-fixed-top");
				$('#indexMenu').css("margin-top", "20px");
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

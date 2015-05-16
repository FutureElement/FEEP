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
						</ul></li>
					<li><a href="#">JSON 视图</a></li>
					<li><a href="#">正则测试</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid index-background index-content">
		<div class="row">
			<div class="col-md-2">
				<ul class="list-group index-menu-left" id="indexMenuLeft">
					<li class="list-group-item active">数据表</li>
					<li class="list-group-item">数据视图</li>
					<li class="list-group-item">数据模型</li>
					<li class="list-group-item">数据字典</li>
					<li class="list-group-item">大数据</li>
				</ul>
			</div>
			<div class="col-md-10" style="padding: 10px 10px 10px 0;">
				<div id="indexContent_inner" style="height: 100%;width: 100%;">
					<div class="panel panel-primary" style="margin: 0">
					  <div class="panel-heading">数据表-列表</div>
					  <div class="panel-body">
					    <table class="table table-bordered table-hover table-striped">
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						  <tr class="info"><td>1</td><td>2</td><td>3</td><td>4</td></tr>
						</table>
					  </div>
					</div>
					
				</div>
			</div>
			<div class="col-md-2 navbar-fixed-bottom">
				<ul class="pager indexTopBtn">
				  <li>
				  	<a href="javascript:goTop()">
				  		<span class="glyphicon glyphicon-eject" aria-hidden="true"></span>
				  		&nbsp;Top
				  	</a>
				  </li>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="container-fluid index-footer">
		<p class="text-center index-footer-text">©2015&nbsp;未来元素信息技术有限公司&nbsp;|&nbsp;FEEP&nbsp;Version&nbsp;1.0</p>
	</div>
</body>
<%@ include file="/Resource/include/feep-js-lib.jsp"%>
<script>
	var index_scrollTop;
	function main() {
		setNavTop();
	}
	function goTop(){
		$("body,html").animate({scrollTop:0}, 500);
	}
	function logout() {
		var ret = Feep.request("feep_logout");
		if (ret) {
			Feep.pageTo.login();
		}
	}
	
	function setNavTop() {
		$(window).scroll(function() {
			//var htmlHeight=document.body.scrollHeight||document.documentElement.scrollHeight;
			//var clientHeight=document.body.clientHeight||document.documentElement.clientHeight;
			index_scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
			if (index_scrollTop >= 120) {
				$("#indexNavbar").removeClass("navbar-static-top");
				$("#indexNavbar").addClass("navbar-fixed-top");
				$(".indexTopBtn").show("fast");
			} else {
				$("#indexNavbar").removeClass("navbar-fixed-top");
				$("#indexNavbar").addClass("navbar-static-top");
				$(".indexTopBtn").hide("fast");
			}
		});
	}
</script>
</html>

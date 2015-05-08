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
<link href="${contextPath}/Resource/thirdLib/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet">
<link href="${contextPath}/Resource/css/feep.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid" style="background-color: black;">
		<div style="padding-top: 200px; text-align: center; color: white;">
			<h1>
				<strong>Hello World!</strong>
			</h1>
		</div>
	</div>
	<div class="container-fluid">
		<div style="text-align: center;">
			<h2>
				<strong>Working...</strong>
			</h2>
			<address>
				<a href="javascript:logout()">退出系统</a>
			</address>
		</div>
	</div>
</body>
<script src="${contextPath}/Resource/thirdLib/jquery/jquery.js"></script>
<script src="${contextPath}/Resource/thirdLib/angularJs/angular.js"></script>
<script src="${contextPath}/Resource/thirdLib/bootstrap/js/bootstrap.min.js"></script>
<script src="${contextPath}/Resource/thirdLib/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="${contextPath}/Resource/js/feep.js"></script>
<script type="text/javascript">
	function logout() {
		var ret = Feep.request("feep_logout");
		if (ret) {
			Feep.pageTo.login();
		}
	}
</script>
</html>
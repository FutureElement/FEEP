<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
<meta charset="UTF-8">
<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>FEEP 404</title>
<link href="${contextPath}/Resource/thirdLib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/Resource/thirdLib/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet">
<link href="${contextPath}/Resource/css/feep.css" rel="stylesheet">
</head>
<body class="page404-body">
	<div class="page404-container">
		<img src="${contextPath}/Resource/img/page404/404.png" />
		<img src="${contextPath}/Resource/img/page404/404_msg.png" />
		<p>
			<a href="#">
				<button onclick="toHmoePage()" type="button" class="btn btn-danger">返&nbsp;回&nbsp;首&nbsp;页&nbsp;(&nbsp;<span id="leftSeconds">3</span>&nbsp;)</button>
			</a>
		</p>
	</div>
	<div class="page404-cloud"></div>
</body>
<script src="${contextPath}/Resource/thirdLib/jquery/jquery.js"></script>
<script src="${contextPath}/Resource/thirdLib/angularJs/angular.js"></script>
<script src="${contextPath}/Resource/thirdLib/bootstrap/js/bootstrap.min.js"></script>
<script src="${contextPath}/Resource/thirdLib/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="${contextPath}/Resource/js/feep.js"></script>
<script type="text/javascript">
	var jumpTaskId;
	function main() {
		jumpTaskId = Feep.runTask(setLeftSeconds,1000,4000,this);
	}
	function setLeftSeconds(){
		var s = Number($("#leftSeconds").text());
		s -= 1;		
		$("#leftSeconds").text(s);			
		if(s == 0){
			toHmoePage();
		}
	}
	function toHmoePage(){
		Feep.stopTask(jumpTaskId,this);
		Feep.pageTo.home();
	}
</script>
</html>
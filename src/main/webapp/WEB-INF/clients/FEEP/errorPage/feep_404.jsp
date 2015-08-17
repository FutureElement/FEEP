<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Resource/include/feep-global-header.jsp"%>
<body class="page404-body">
	<div class="page404-container">
		<img src="${contextPath}/Resource/img/large/404.png" />
		<img src="${contextPath}/Resource/img/middle/404_msg.png" />
		<div class="row">
			<div class="col-md-12">
				<button onclick="toHomePage()" type="button" class="btn btn-success page404-btn">去&nbsp;首&nbsp;页</button>
			</div>
			<div class="col-md-12">
				<button onclick="goBack()" type="button" class="btn btn-danger page404-btn">返&nbsp;回(&nbsp;<span id="leftSeconds">4</span>&nbsp;)</button>
			</div>
		</div>
	</div>
	<div class="page404-cloud"></div>
</body>
<%@ include file="/Resource/include/feep-js-lib.jsp"%>
<script type="text/javascript">
	var jumpTaskId;
	function main() {
		jumpTaskId = Feep.runTask(setLeftSeconds,1000,5000,this);
	}
	function setLeftSeconds(){
		var s = Number($("#leftSeconds").text());
		s -= 1;		
		$("#leftSeconds").text(s);			
		if(s == 0){
			goBack();
		}
	}
	function toHomePage(){
		Feep.stopTask(jumpTaskId,this);
		Feep.pageTo.home();
	}
	function goBack(){
		Feep.stopTask(jumpTaskId,this);
		Feep.pageTo.back();
	}
	
</script>
</html>
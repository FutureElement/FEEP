<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Resource/include/feep-global-header.jsp"%>
<body class="page404-body">
	<div class="page404-container">
		<img src="${contextPath}/Resource/img/large/404.png" />
		<img src="${contextPath}/Resource/img/middle/404_msg.png" />
		<p>
			<a href="#">
				<button onclick="toHomePage()" type="button" class="btn btn-danger">返&nbsp;回&nbsp;首&nbsp;页&nbsp;(&nbsp;<span id="leftSeconds">3</span>&nbsp;)</button>
			</a>
		</p>
	</div>
	<div class="page404-cloud"></div>
</body>
<%@ include file="/Resource/include/feep-js-lib.jsp"%>
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
            toHomePage();
		}
	}
	function toHomePage(){
		Feep.stopTask(jumpTaskId,this);
		Feep.pageTo.home();
	}
</script>
</html>
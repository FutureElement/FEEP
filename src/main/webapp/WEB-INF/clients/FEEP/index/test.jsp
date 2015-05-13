<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Resource/include/feep-global-header.jsp"%>
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
<%@ include file="/Resource/include/feep-js-lib.jsp"%>
<script type="text/javascript">
	function logout() {
		var ret = Feep.request("feep_logout");
		if (ret) {
			Feep.pageTo.login();
		}
	}
</script>
</html>
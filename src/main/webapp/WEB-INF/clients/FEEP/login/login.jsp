<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Resource/include/feep-global-header.jsp"%>
<body>
	<div class="container-fluid tableStyle-table login-background">
		<div class="row tableStyle-cell">
			<div class="col-md-24 col-ms-24">
				<p class="login-text-header"><strong>FutureElement 开发平台</strong></p>
			</div>
			<div class="col-md-24 col-ms-24">
				<div role="dialog" aria-labelledby="loginModalLabel" aria-hidden="false">
					<div class="modal-dialog login-dialog-width">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="loginModalLabel">
									<strong>用户登陆</strong> <span id="login_message" class="text-danger login-message pull-right"></span>
								</h4>
							</div>
							<div class="modal-body" style="padding-bottom: 0;">
								<form>
									<div class="form-group">
										<label for="username" class="sr-only">用户名:</label>
										<div class="input-group">
											<div class="input-group-addon" style="background-color: inherit;">
												<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
											</div>
											<input type="text" class="form-control" id="username" placeholder="用户名">
										</div>
									</div>
									<div class="form-group">
										<label for="password" class="sr-only">密码:</label>
										<div class="input-group">
											<div class="input-group-addon" style="background-color: inherit;">
												<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
											</div>
											<input type="password" class="form-control" id="password" placeholder="密码">
										</div>
									</div>
									<div style="text-align: center;">
										<button id="loginButton" type="button" class="btn btn-primary login-submit"
											onclick="submitLogin();">登&nbsp;陆</button>
									</div>
								</form>
							</div>
							<div class="modal-footer" style="padding-top: 0;">
								<div class="checkbox">
									<label style="float: left; padding-left: 0px;"> <input id="isSaveUsername" type="checkbox"
										data-size="mini" data-label-width="20" data-on-text="记住用户名" data-off-text="不记住用户名">
									</label> <a style="float: right; cursor: pointer; padding-top: 2px;" onclick="forgotPassword();">忘记密码？</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="navbar-fixed-bottom">
		<div class="row">
			<div class="col-md-24 col-ms-24">
				<div class="login-version login-dialog-width">
					<p>版权所有：未来元素信息技术有限公司</p>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/Resource/include/feep-js-lib.jsp"%>
<script>
	var FEEP_KEY_REMEMBER_USERNAME = "feep_key_remember_username";
	
	function main() {
		initLoginForm();
	}
	function onSizeChange(w,h){
		$("body").height(h);
	}
	function initLoginForm() {
		var username = Feep.cookie.get(FEEP_KEY_REMEMBER_USERNAME);
		if (username) {
			$("#username").val(username);
			$("#isSaveUsername").bootstrapSwitch('state', true);
		} else {
			$("#username").val();
			$("#isSaveUsername").bootstrapSwitch('state', false);
		}
		$(".form-control").keydown(function(event) {
			switch (event.keyCode) {
				case 13:
					$("#loginButton").click();
					break;
				default:
					break;
			}
		});
	}
	
	function submitLogin() {
		$("#login_message").html("登陆中...");
		rememberUsername();
		var ret = Feep.request("feep_login", $("#username").val(), $("#password").val());
		if (ret == true) {
			$(".form-group").removeClass("has-error");
			$("#login_message").html("登陆成功！");
			Feep.asyn(Feep.pageTo.home, this);
		} else if (ret == false) {
			$(".form-group").addClass("has-error");
			$("#login_message").html("用户名或密码错误!");
		} else {
			$(".form-group").addClass("has-error");
			$("#login_message").html("系统异常,请稍后再试！");
		}
	}
	function forgotPassword() {
		alert("lost password");
	}
	function rememberUsername() {
		var isSave = $("#isSaveUsername")[0].checked;
		if (isSave) {
			Feep.cookie.add(FEEP_KEY_REMEMBER_USERNAME, $("#username").val());
		} else {
			Feep.cookie.remove(FEEP_KEY_REMEMBER_USERNAME);
		}
	}
</script>
</html>
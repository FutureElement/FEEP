/**
 * Created by ZhangGang on 2015/4/4.
 */
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
	rememberUsername();
	var ret = Feep.request("feep_login", $("#username").val(), $("#password").val());
	if (ret == true) {
		$(".form-group").removeClass("has-error");
		$("#login_message").html("登陆中...");
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

$(function(){
	$("#submit").click(function(){
		login();
	});
	
});

function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	if ("" == username) {
		asyncbox.tips('请输入用户名');
		document.getElementById('username').focus() ;
		return;
	}
	if ("" == password) {
		asyncbox.tips('请输入密码');
		document.getElementById('password').focus();
		return;
	}
	/** 登录验证 * */
	$.post("loginAction.htm?method=login", {
		"account" : username,
		"password" : password
	}, function(data) {
		var s = eval("("+data+")");
		switch (s.code) {
		case 1:
			window.location.href = "u/adminAction.htm?method=admin";
			break;
		case 2: 
			window.location.href = "u/adminAction.htm?method=forwardUserMain";
		case -1:
			asyncbox.tips(s.msg);
			document.getElementById('username').focus() ;
			$("#username").val("") ;
			$("#password").val("") ;
		    break;
		default:
			break;
		}

	});
}
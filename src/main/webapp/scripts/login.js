//Log_in.html主处理
$(function() {
	// 页面加载完毕
	// 给登陆按钮绑定单击事件
	$("#login").click(checkLogin)
})
// 登录处理
function checkLogin() {
	// 1.获取请求参数值?name,passowrd
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	// 2.检测参数格式
	$("#count_span").html("");
	$("#password_span").html("");
	var ok = true;
	if (name == "") {
		ok = false;
		$("#count_span").html("用户名不能为空");
	}
	if (password == "") {
		ok = false;
		$("#password_span").html("密码不能为空");
	}
	// 3.发送Ajax
	if (ok) {
		$.ajax({
			url : base_path + "/user/login.do",
			type : "post",
			data : {
				"name" : name,
				"password" : password
			},
			dataType : "json",
			success : function(result) {
				// result就是服务器返回的结果
				if (result.status == 0) {// 登录成功
					var user = result.data;// 获取返回的user信息
					// 写入cookie,2小时过期
					addCookie("uid", user.cn_user_id, 2);
					addCookie("uname", user.cn_user_name, 2);
					// 成功登录后跳转到主页面
					window.location.href = "edit.html";
				} else if (result.status == 1) {// 用户不存在
					$("#count_span").html(result.msg);
				} else if (result.status == 2) {
					$("#password_span").html(result.msg);
				}
			},
			error : function() {
				alert("登录异常");
			}
		});
	}
}
//Log_in.html主处理
$(function() {
	// 页面加载完毕
	// 给登陆按钮绑定单击事件
	$("#login").click(checkLogin);
	$("#regist_button").click(registUser);
	//修改密码
	$("#changePassword").click(changepwd);
	//关闭修改密码页面
	$("#back").click(back);
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
				} else if (result.status == 2) {// 密码错误
					$("#password_span").html(result.msg);
				}
			},
			error : function() {
				alert("登录异常");
			}
		});
	}
}

// 注册处理
function registUser() {
	// 1.获取参数
	var name = $("#regist_username").val().trim();
	var nick = $("#nickname").val().trim();
	var password = $("#regist_password").val().trim();
	var f_password = $("#final_password").val().trim();
	// 2.参数格式检查
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	var ok = true;
	if (name == "") {
		ok = false;
		$("#warning_1").show();
		$("#warning_1 span").html("用户名为空");
	}
	if (password == "") {
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("密码为空");
	} else if (password.length < 6) {
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("密码长度过短");
	}
	if (f_password == "") {
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("确认密码不能为空");
	} else if (f_password != password) {
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("密码输入不一致");
	}
	// 3.发送Ajax
	if (ok) {
		$.ajax({
			url : base_path + "/user/add.do",
			type : "post",
			data : {
				"cn_user_name" : name,
				"cn_user_nick" : nick,
				"cn_user_password" : password
			},
			dataType : "json",
			success : function(result) {
				// 对回调函数判断
				if (result.status == 0) {// 注册成功
					// 注册成功后返回登录页面
					$("#back").click();
				} else if (result.status == 1) {// 用户名已存在
					$("#warning_1").show();
					$("#warning_1 span").html(result.msg);
				}
			},
			error : function() {
				alert("注册异常");
			}
		});
	}
}

//退出登录
function logout(){
	delCookie("uid");
	delCookie("uname");
	window.location.href = "log_in.html";
}

//修改密码
function changepwd(){
	//获取参数
	var userId = getCookie("uid");
	var last_password = $("#last_password").val().trim();
	var new_password = $("#new_password").val().trim();
	var final_password = $("#final_password").val().trim();
	//检测参数格式
	$("#warning_1").html("");
	$("#warning_2").html("");
	$("#warning_3").html("");
	
	var ok = true;
	if(userId == null){
		ok = false;
		window.location.href = "log_in.html";
	}
	
	if(last_password == ""){
		ok = false;
		$("#warning_1").html("密码不能为空");
		$("#warning_1").css("display","block");
	}
	
	if(new_password == ""){
		ok = false;
		$("#warning_2").html("新密码不能为空");
		$("#warning_2").css("display","block");
	}else if(new_password < 6){
		ok = false;
		$("#warning_2").html("新密码长度过短");
		$("#warning_2").css("display","block");
	}
	if(new_password != final_password){
		ok = false;
		$("#warning_3").html("密码输入不一致");
		$("#warning_3").css("display","block");
	}
	//发送Ajax
	if(ok){
		$.ajax({
			url:base_path+"/user/change.do",
			type:"post",
			data:{
				"userId":userId,
				"last_password":last_password,
				"new_password":new_password
			},
			dataType:"json",
			success:function(result){
				if(result.status == 0){
					window.location.href = "log_in.html";
				}else if(result.status == 1){
					$("#warning_1").html("原始密码错误");
				}
			},
			error:function(){
				alert("修改密码异常");
			}
		});
	}
}
//关闭修改密码页面
function back(){
	window.location.href = "edit.html";
}
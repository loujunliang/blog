// 加载用户笔记本
function loadUserBooks() {
	// 获取用户id
	var uid = getCookie("uid");
	// 检测id格式
	if (uid == null) {
		window.location.href = "log_in.html";
	}
	// 发送Ajax
	$.ajax({
		url : base_path + "/book/loadbooks.do",
		type : "post",
		data : {
			"uid" : uid
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				$("#book_ul").empty();
				// 获取返回的笔记本集合
				var books = result.data;
				// 循环生成列表元素
				for (var i = 0; i < books.length; i++) {
					// 获取bookName
					var bookName = books[i].cn_notebook_name;
					// 获取bookId
					var bookId = books[i].cn_notebook_id;
					// 循环创建li列表元素
					createBookLi(bookName, bookId);
				}
				// $("#book_ul li").click(loadNotes);
			}
		},
		error : function() {
			alert("加载笔记本列表异常");
		}
	});
}
// 创建笔记本列表li元素
function createBookLi(bookName, bookId) {
	// 构建li元素
	var sli = "";
	sli += '<li class="online"><a><i class="fa fa-book" title="online" rel="tooltip-bottom">'
			+ bookName + '</i></a></li>';
	// 将bookId绑定到li元素上
	var $li = $(sli);
	$li.data("bookId", bookId);
	// 将li对象追加到ul列表中
	$("#book_ul").append($li);
}

// 添加笔记本
function addBook() {
	// 获取参数
	var bookName = $("#input_notebook").val().trim();
	var userId = getCookie("uid");
	// 检测参数格式
	if (userId == null) {
		window.location.href = "log_in.html";
	} else if (bookName == "") {
		$("#notebook_span").html("笔记本名称不能为空");
	} else {
		// 发送Ajax
		$.ajax({
			url : base_path + "/book/add.do",
			type : "post",
			data : {
				"cn_notebook_name" : bookName,
				"cn_user_id" : userId
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 0) {
					var book = result.data;
					var bookId = book.cn_notebook_id;
					createBookLi(bookName, bookId);
					alert(result.msg);
				} else if (result.status == 1) {
					$("#notebook_span").html("笔记本添加失败");
				}
			},
			error : function() {
				alert("网络异常");
			}
		});
	}
}
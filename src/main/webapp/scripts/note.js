//加载用户笔记本中的笔记列表
function loadNotes() {
	// 获取笔记本id
	var bookId = $(this).data("bookId");
	// 设置笔记本选中效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 检测id格式
	if (bookId == null) {
		return;
	}
	// 发送Ajax
	$.ajax({
		url : base_path + "/note/loadnotes.do",
		type : "post",
		data : {
			"bookId" : bookId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				var notes = result.data;
				$("#note_ul").empty();
				for (var i = 0; i < notes.length; i++) {
					var noteTitle = notes[i].cn_note_title;
					var noteId = notes[i].cn_note_id;
					createNoteLi(noteTitle, noteId);
				}
			}
		},
		error : function() {
			alert("笔记获取异常")
		}
	});
}
// 创建笔记列表li元素
function createNoteLi(noteTitle, noteId) {
	// 构建li元素
	var sli = "";
	sli += '<li class="online"><a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'
			+ noteTitle
			+ '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button></a>'
			+ '<div class="note_menu" tabindex="-1"><dl><dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt><dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'
			+ '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt></dl></div></li>';
	// 将li元素追加到ul列表中
	var $li = $(sli);
	$li.data("noteId", noteId);
	$("#note_ul").append($li);
}

// 加载笔记内容
function loadNote() {
	// 获取noteId
	var noteId = $(this).data("noteId");
	// 检测id格式
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 发送Ajax
	$.ajax({
		url : base_path + "/note/load.do",
		type : "post",
		data : {
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				var note = result.data;
				$("#input_note_title").val(note.cn_note_title);
				// 使用UEditor副文本编辑器设置笔记内容
				um.setContent(note.cn_note_body);
				// $("#myEditor").html(note.cn_note_body);
			}
		},
		error : function() {
			alert("内容获取异常");
		}
	});
}

// 修改笔记内容
function updateNote() {
	$("#note_title_span").html("");
	// 获取前台参数
	var title = $("#input_note_title").val().trim();
	var body = um.getContent();
	//获取选中的笔记li元素
	var $li = $("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	// 检测参数格式
	var ok = true;
	if(noteId == null){
		alert("请选择要保存的笔记");
	}else if (title == "") {
		ok = false;
		$("#note_title_span").html("<font color='red'>标题不能为空</font>");
	}
	// 发送Ajax
	if (ok) {
		$.ajax({
			url : base_path + "/note/update.do",
			type : "post",
			data : {
				"cn_note_title" : title,
				"cn_note_body" : body,
				"cn_note_id" : noteId
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 0) {// 修改成功
					//<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> 使用Java操作符<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>
					//更新列表li中标题
					var sli = "";
					sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					//将选中的li元素a内容替换
					$li.find("a").html(sli);
					//提示成功
					alert(result.msg);
					
				} else if (result.status == 1) {// 保存失败
					$("#note_title_span").html(result.msg);
				}
			},
			error : function() {
				alert("内容存储异常");
			}
		});
	}
}
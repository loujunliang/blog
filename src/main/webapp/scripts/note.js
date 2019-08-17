//加载用户笔记本中的笔记列表
function loadNote() {
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
	$li = $(sli);
	$li.data("noteId", noteId);
	$("#note_ul").append($li);
}
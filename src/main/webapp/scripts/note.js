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
	// 获取选中的笔记li元素
	var $li = $("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	// 检测参数格式
	var ok = true;
	if (noteId == null) {
		alert("请选择要保存的笔记");
	} else if (title == "") {
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
					// 更新列表li中标题
					var sli = "";
					sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'
					+ title
					+ '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					// 将选中的li元素a内容替换
					$li.find("a").html(sli);
					// 提示成功
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

// 添加笔记
function addNote() {
	// 获取参数
	var noteTitle = $("#input_note").val().trim();
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	var userId = getCookie("uid");
	// 检测参数格式
	if (userId == null) {
		window.location.href = "log_in.html";
	}else if(noteTitle == ""){
		$("#note_span").html("笔记标题不能为空");
	}else {
		// 发送Ajax
		$.ajax({
			url : base_path + "/note/add.do",
			type : "post",
			data : {
				"cn_notebook_id" : bookId,
				"cn_note_title" : noteTitle,
				"cn_user_id" : userId
			},
			dataType : "json",
			success : function(result) {
				if(result.status == 0){
					var note = result.data;
					var noteId = note.cn_note_id;
					createNoteLi(noteTitle, noteId);
					alert(result.msg);
				}else{
					$("#note_span").html("笔记添加失败");
				}
			},
			error : function() {
				alert("创建笔记异常");
			}
		});
	}
}

//操作笔记弹窗
function alertNote(){
	//隐藏笔记菜单
	$("#note_ul div").hide();
	//显示笔记菜单
	$(this).parent().next().slideDown(100);
	//先清除note选中状态
	$("#note_ul a").removeClass("checked");
	//设置选中状态
	$(this).parent().addClass("checked");
	return false;
}
//隐藏笔记菜单
function hideNoteMenu(){
	//隐藏笔记菜单
	$("#note_ul div").hide();
}

//移动笔记
function moveNote(){
	//获取参数
	var bookId = $("#moveSelect").val();
	var $li = $("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	//检测参数格式
	if(bookId == "none"){
		$("#moveSelect_span").html("请选择笔记本");
	}else{
		//发送Ajax
		$.ajax({
			url:base_path+"/note/move.do",
			type:"post",
			data:{
				"cn_notebook_id":bookId,
				"cn_note_id":noteId
			},
			dataType:"json",
			success:function(result){
				if(result.status == 0){
					$("#note_ul a.checked").parent().remove();
					alert(result.msg);
				}else{
					alert(result.msg);
				}
			},
			error:function(){
				alert("移动笔记异常");
			}
		});
	}
}

//删除笔记(放至回收站)
function deleteNote(){
	//获取参数
	var noteId = $("#note_ul a.checked").parent().data("noteId");
	//检测参数格式
	//发送Ajax
	$.ajax({
		url:base_path+"/note/delete.do",
		type:"post",
		data:{
			"noteId":noteId
		},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				$("#note_ul a.checked").parent().remove();
				alert(result.msg);
			}else if(result.status == 1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除笔记异常");
		}
	});
}

//打开回收站
function rollBack(){
	$("#pc_part_4")[0].style.display='block';
	$("#rollBackNote_ul").empty();
	var userId = getCookie("uid");
	//获取参数
	//检测参数格式
	//发送Ajax
	$.ajax({
		url:base_path+"/note/rollback.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var notes = result.data;
				for (var i = 0; i < notes.length; i++) {
					var noteTitle = notes[i].cn_note_title;
					var noteId = notes[i].cn_note_id;
					createRollBackNote(noteTitle,noteId);
				}
			}else if(result.status == 1){
				window.location.href = "edit.html";
				alert("数据获取失败");
			}
		},
		error:function(){
			alert("回收站数据获取异常");
		}
	});
}
//创建回收站笔记列表
function createRollBackNote(noteTitle,noteId){
	var sli = "";
	sli += '<li class="disable"><a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button></a></li>';
	var $li = $(sli);
	$li.data("noteId",noteId);
	$("#rollBackNote_ul").append($li);
}

//恢复回收站笔记
function replayNote(){
	//获取参数
	var noteId = $("#replaySelect").data("noteId");
	//var noteId = $("#rollBackNote_ul a.checked").parent().data("noteId");
	var bookId = $("#replaySelect").val();
	alert(noteId);
	//检测参数格式
	if(bookId == null){
		$("#replaySelect_span").html("请选择笔记本");
	}else{
		//发送Ajax
		$.ajax({
			url:base_path+"/note/replay.do",
			type:"post",
			data:{
				"noteId":noteId,
				"bookId":bookId
			},
			dataType:"json",
			success:function(result){
				if(result.status == 0){
					var roll=$("#rollBackNote_ul li");
					for (var i = 0; i < roll.length; i++) {
						var id=$(roll[i]).data("noteId");
						if (noteId==id) {
							$(roll[i]).remove();
							break;
						}
					}
					alert(result.msg);
				}
			},
			error:function(){
				alert("恢复笔记异常");
			}
		});
	}
}
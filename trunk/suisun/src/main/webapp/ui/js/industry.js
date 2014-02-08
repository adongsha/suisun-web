var page;

$(function() {
	$(".active").removeClass("active");
	$(".ico4").parent().addClass("active");

	var currentPage = $("#current").val();
	var pageAll = $("#pageAll").val();
	var tips = $(".validateTips");
	

	page = new pageUtil('u/industryAction.htm?method=page', currentPage, pageAll);

	$("#dialog-form").dialog({
		autoOpen : false,
		height : 300,
		width : 500,
		modal : true,
		buttons : {
			"添加" : function() {
				var bValid = true;
				var oThis = this;
				$(".validateTips").hide();
				var industryName = $("#industryName").val();
				var note = $("#note").val();
				if (industryName == '') {
					updateTips("行业不能为空.");
					bValid = false;
				}

				if (bValid) {
					$.post("u/industryAction.htm?method=add", {
						"industryName" : industryName,
						"note" : note
					}, function(data) {
						var d = eval("(" + data + ")");
						switch (d.code) {
						case 1:
							$(oThis).dialog("close");
							var currentPage = $("#current").val();
							window.location.href="u/industryAction.htm?method=page&currentPage="+currentPage;
							break;
						case -1:
							updateTips("添加不成功.");
							break;
						default:
							break;
						}
					});
				}

			}
		},
		Cancel : function() {
			$(this).dialog("close");
		},

		close : function() {
			$(this).dialog("close");
		}
	});

	function updateTips(t) {
		tips.text(t).addClass("ui-state-error");
		$(".validateTips").show();
		setTimeout(function() {
			tips.removeClass("ui-state-highlight", 1500);
		}, 500);
	}

	$("#addIndustry").click(function() {
		$("#dialog-form").dialog("open");
	});
	
});


function edit(id,name,note){
	
	$("#industryName").attr("value",name);
	$("#note").val(note);
	$("#dialog-form").dialog("open");
	
	$("#dialog-form").dialog({
		autoOpen : false,
		height : 300,
		width : 500,
		modal : true,
		buttons : {
			"修改" : function() {
				var bValid = true;
				var oThis = this;
				$(".validateTips").hide();
				
				if (name == '') {
					updateTips("行业不能为空.");
					bValid = false;
				}
                
				if (bValid) {
					var tempName = $("#industryName").val();
					var tempNote = $("#note").val();
					$.post("u/industryAction.htm?method=update", {
						"industryName" : tempName,
						"note" : tempNote,
						"id":id
					}, function(data) {
						var d = eval("(" + data + ")");
						switch (d.code) {
						case 1:
							var currentPage = $("#current").val();
							$(oThis).dialog("close");
							window.location.href="u/industryAction.htm?method=page&currentPage="+currentPage;
							break;
						default:
							updateTips("修改不成功.");
							break;
						}
					});
				}

			}
		},
		Cancel : function() {
			$(this).dialog("close");
		},

		close : function() {
			$(this).dialog("close");
		}
	});
}

function deleteInds(id,name,note){
	
	$.post("u/industryAction.htm?method=delete",{
		"industryName" : name,
		"note" : note,
		"id":id
	},function(data){
		var d = eval("("+data+")");
		switch (d.code) {
		case 1:
			var currentPage = $("#current").val();
			window.location.href="u/industryAction.htm?method=page&currentPage="+currentPage;
			break;

		default:
			alert("删除失败");
			break;
		}
	});
}



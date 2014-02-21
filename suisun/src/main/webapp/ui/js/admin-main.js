var page;
$(function() {

	var currentPage = $("#current").val();
	var pageAll = $("#pageAll").val();
	var search = $("#search").val();
	var power = $("#power").val();
	page = new pageUtil('u/adminAction.htm?method=page', currentPage, pageAll,
			power, search);

	$("#power")
			.change(
					function() {
						var p = $("#power").val();
						window.location.href = "u/adminAction.htm?method=page&currentPage=1&power="
								+ p + "&search=";
					});

	$("#searchBtn")
			.click(
					function() {
						var s = $("#search").val();
						var p = $("#power").val();
						
						if(p == undefined){
							window.location.href = "u/adminAction.htm?method=page&currentPage=1&power="
								+ 2 + "&search=" + encodeURI(encodeURI(s));

						} else {
							window.location.href = "u/adminAction.htm?method=page&currentPage=1&power="
								+ p + "&search=" + s;
							 
						}
					});

});

function edit(id) {
	window.location.href="u/adminAction.htm?method=forwardeditUser&id="+id;
}

function deleteUser(id) {
	if (confirm("确定删除吗?")) {
		var currentPage = $("#current").val();
		var pageAll = $("#pageAll").val();
		var search = $("#search").val();
		var power = $("#power").val();
		$.post("u/adminAction.htm?method=delUser", {
			"uid" : id
		},function(data){
			var d = eval("("+data+")");
			switch (d.code) {
			case -1:
				alert(d.msg);
				break;
			case 1:
				$("#"+id).fadeOut("slow");
				break;
			default:
				break;
			}
		});
	}
}
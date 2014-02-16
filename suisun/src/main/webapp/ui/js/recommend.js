
$(function() {
	$(".active").removeClass("active");
	$(".ico7").parent().addClass("active");

	$("#submit").click(function(){
		var s = $("select option:selected");
		var rid = "";
		for(var i=0; s && i<s.length; i++){
			rid+=$(s[i]).val()+",";
		}
		
		$.post("u/recommendAction.htm?method=addRecommend",
				{"rid":rid},function(data){
					window.location.href="u/recommendAction.htm?method=forwardTip&code="+data;
				});
	});
});





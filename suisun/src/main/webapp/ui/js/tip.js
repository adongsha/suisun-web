
$(function() {
	var code = $("#code").val();
	console.log("code:"+code);
	if(code ==1 || code == -1){
		$(".active").removeClass("active");
		$(".ico1").parent().addClass("active");
	}

	if(code ==2 || code == -2){
		$(".active").removeClass("active");
		$(".ico6").parent().addClass("active");
	}
	
	if(code ==3 || code == -3){
		$(".active").removeClass("active");
		$(".ico7").parent().addClass("active");
	}



});





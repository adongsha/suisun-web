//var page;

$(function() {
	$(".active").removeClass("active");
	$(".ico5").parent().addClass("active");

	var currentPage = $("#current").val();
	var pageAll = $("#pageAll").val();
	var albumType = $("#albumType").val();
	var album = $("#album").val();
	var orderBy = $("#orderBy").val();

	page = new pageUtil('u/customerStatAction.htm?method=page', currentPage,
			pageAll, albumType, album, orderBy);
	
	$("#searchBtn").click(function(){
		var albumType = $("#albumType").val();
		var album = $("#album").val();
		var orderBy = $("#orderBy").val();
		
		window.location.href = "u/customerStatAction.htm?method=page&currentPage=1&albumType="+albumType
		+"&album="+album+"&orderBy="+orderBy;
	});
});
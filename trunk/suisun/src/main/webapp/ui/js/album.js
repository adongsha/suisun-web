$(function() {
	// 菜单选中
	$(".active").removeClass("active");
	$(".ico2").parent().addClass("active");
	
	// 选择企业名称
	$("#enterprises").change(function(){
		searchAlbum() ;
	}) ;
	
	// 画册搜索
	$("#searchBtn").click(function(){
		searchAlbum() ;
	}) ;
});

// 画册搜索
function searchAlbum(){
	var url = "u/albumsAction.htm?method=showAlbums&albumName="+ $("#albumName").val() +"&enterpriseName=" + $("#enterprises").val() ;
	window.location.href = url ;
}
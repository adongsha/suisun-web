$(function() {
	// �˵�ѡ��
	$(".active").removeClass("active");
	$(".ico2").parent().addClass("active");
	
	// ѡ����ҵ����
	$("#enterprises").change(function(){
		searchAlbum() ;
	}) ;
	
	// ��������
	$("#searchBtn").click(function(){
		searchAlbum() ;
	}) ;
});

// ��������
function searchAlbum(){
	var url = "u/albumsAction.htm?method=showAlbums&albumName="+ $("#albumName").val() +"&enterpriseName=" + $("#enterprises").val() ;
	window.location.href = url ;
}
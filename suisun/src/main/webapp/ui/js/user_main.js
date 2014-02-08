$(function() {
	$(".active").removeClass("active");
	$(".ico6").parent().addClass("active");
	
});

function edit(id){
	window.location.href="u/adminAction.htm?method=editUser&id="+id;
}
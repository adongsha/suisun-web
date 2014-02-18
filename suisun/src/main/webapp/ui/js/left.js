jQuery(function(){
	
	
	jQuery("#ico0").click(function(){
		window.location.href = "u/albumsAction.htm?method=showAuditInfo&albumName=";
	});
	
	jQuery("#ico1").click(function(){
		window.location.href = "u/adminAction.htm?method=admin";
	});
	
	jQuery("#ico2").click(function(){
		window.location.href = "u/albumsAction.htm?method=forwardAlbums";
	});
	
	jQuery("#ico3").click(function(){
		window.location.href = "u/adminAction.htm?method=forwardAddUser";
	});
	
	jQuery("#ico4").click(function(){
		window.location.href = "u/industryAction.htm?method=industry";
	});
	
	jQuery("#ico5").click(function(){
		window.location.href = "u/customerStatAction.htm?method=forwardCusStat";
	});
	
	jQuery("#ico6").click(function(){
		window.location.href = "u/adminAction.htm?method=forwardUserMain";
	});
	
	jQuery("#ico7").click(function(){
		window.location.href = "u/recommendAction.htm?method=forwardRecommend";
	});
	
	jQuery("#ico8").click(function(){
		window.location.href = "u/appAction.htm?method=forwardApp";
	});
	
});


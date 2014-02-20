function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    projectName = "/suisun";
    return(localhostPaht+projectName);
};

$(function() {
	// 当前菜单选中
	$(".active").removeClass("active");
	$(".ico2").parent().addClass("active");
	
	// 画册搜索
	$("#enterprises").change(function(){
		searchAlbum() ;
	}) ;
	
	// 画册搜索
	$("#searchBtn").click(function(){
		searchAlbum() ;
	}) ;
	
	// 跳转至新建画册
	$("#addAlbum").click(function(){
		var url = "u/albumsAction.htm?method=forwardAddAlbum" ;
		window.open(url,'新建画册','height=450,width=400,top=160,left=510,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
	}) ;
	
	// 照片上传
	uploadify();
});

// 照片上传
function uploadify(){
	var session_id = $("#sessionid").val() ;
	// 上传配置
    $("#uploadifyFile").uploadify({  
        'height'        : 20,   
        'width'         : 90,    
        'buttonText'    : '选择上传照片',  
        'swf'           : getRootPath() + '/ui/js/uploadify/uploadify.swf?ver=' + Math.random(),  
        'uploader'      : getRootPath() + '/u/uploadAction.htm;jsessionid=' + session_id,  
        'auto'          : true,
        'fileSizeLimit' : '153072KB', 
        'fileTypeExts'  : '*.jpg; *.png', 
        'formData' :{
        	"method" : "uploadAlbum"
        },
        'onUploadStart' : function(file) {
        },  
        'onUploadSuccess':function(file, data, response){
        	var strs = data.split("/");
        	var picName = strs[strs.length - 1] ;
        	// 设置照片地址
        	$("#albumCover").val(picName) ;
        	$("#albumImg").attr("src",getRootPath() + "/" + data) ;
        	asyncbox.tips("照片上传成功");
        },  
        'onUploadComplete':function(file,swfuploadifyQueue){  
        	
        },
        onError: function(event, queueID, fileObj) {  
            asyncbox.tips("照片名称:" + fileObj.name + "上传失败");
        }
    });
}

// 上传照片
function startUpload(){  
    $('#uploadifyFile').uploadify('upload','*');  
}

// 画册搜索
function searchAlbum(){
	var url = "u/albumsAction.htm?method=showAlbums&albumName="+ $("#albumName").val() +"&enterpriseName=" + $("#enterprises").val() ;
	alert(url) ;
	window.location.href = url ;
}

// 跳转至新建画册
function addAlbum(){
	var uri = "u/albumsAction.htm?method=forwardAddAlbum" ;
	window.open(uri,'新建画册','height=580,width=620,top=50,left=310,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
}

// 跳转至画册目录
function forwardDirectory(uuid){
	window.location.href = "u/albumsAction.htm?method=forwardDirectory&albumId=" + uuid ;
}

// 跳转至画册修改界面
function editAlbum(uuid){
	var uri = "u/albumsAction.htm?method=forwardUpdateAlbum&uuid=" + uuid ;
	window.open(uri,'画册信息','height=560,width=420,top=50,left=510,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
}

// 保存画册信息
function saveAlbum(){
	if($("#albumName").val() == ""){
		asyncbox.tips("请输入画册名称");
		return ;
	}else if($("#albumEnglish").val() == ""){
		asyncbox.tips("请输入英文名称");
		return ;
	}else if($("#albumAlias").val() == ""){
		asyncbox.tips("请输入画册别名");
		return ;
	}else if($("#albumCover").val() == ""){
		asyncbox.tips("请上传画册封面");
		return ;
	}
	// 提交表单
	$("#albumForm").submit() ;
}

// 删除画册
function deleteAlbum(uuid){
	if(confirm("是否删除画册?")){
		$.post("u/albumsAction.htm?method=deleteAlbum", {
			"uuid" : uuid
		}, function(data) {
			window.location.reload() ;
		});
	}
}
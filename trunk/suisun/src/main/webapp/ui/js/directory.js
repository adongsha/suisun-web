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

$(function(){
	// 当前菜单选中
	$(".active").removeClass("active");
	$(".ico2").parent().addClass("active");
	
	// 照片上传
	uploadify();
}) ;

//照片上传
function uploadify(){
	var session_id = $("#sessionid").val() ;
	// 上传配置
    $("#uploadifyFile").uploadify({  
        'height'        : 20,   
        'width'         : 90,    
        'buttonText'    : '选择上传照片',  
        'swf'           : getRootPath() + '/ui/js/uploadify/uploadify.swf?ver=' + Math.random(),  
        'uploader'      : getRootPath() + '/u/uploadAction.htm;jsessionid=' + session_id ,  
        'auto'          : true,
        'fileSizeLimit' : '153072KB', 
        'fileTypeExts'  : '*.jpg; *.png', 
        'formData' : {
        	"method" : "uploadAlbumPic"
        },
        'onUploadStart' : function(file) {
        },  
        'onUploadSuccess':function(file, data, response){
        	// 设置照片地址
        	var strs = data.split("/");
        	var picName = strs[strs.length - 1] ;
        	$("#picUrl").val(picName) ;
        	$("#pictureImg").attr("src",getRootPath() + "/" + data) ;
        	asyncbox.tips("照片上传成功");
        },  
        'onUploadComplete':function(file,swfuploadifyQueue){  
        	
        },
        onError: function(event, queueID, fileObj) {  
            asyncbox.tips("照片名称:" + fileObj.name + "上传失败");
        }
    });
}

// 保存画册目录信息
function saveDirectory(){
	if($("#directoryName").val() == ""){
		asyncbox.tips("请输入目录名称");
		return ;
	}else if($("#directoryEnglish").val() == ""){
		asyncbox.tips("请输入英文名称");
		return ;
	}else if($("#picNum").val() == ""){
		asyncbox.tips("请输入画册容量");
		return ;
	}
	// 提交表单
	$("#directoryForm").submit() ;
}

// 跳转至新建画册目录
function forwardAddDirectory(){
	var uri = "u/albumsAction.htm?method=forwardAddDirectory&albumId=" + $("#albumId").val() ;
	window.open(uri,'新建画册目录','height=480,width=420,top=150,left=510,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
}

// 跳转至修改画册目录
function forwardUpdateDirectory(uuid){
	var uri = "u/albumsAction.htm?method=forwardUpdateDirectory&uuid=" + uuid + "&albumId=" + $("#albumId").val() ;
	window.open(uri,'画册目录','height=480,width=420,top=150,left=510,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
}

// 删除画册目录
function deleteDirectory(uuid){
	if(confirm("是否删除画册目录?")){
		$.post("u/albumsAction.htm?method=deleteDirectory", {
			"uuid" : uuid
		}, function(data) {
			window.location.reload() ;
		});
	}
}

// 跳转至新增照片
function forwardAddPicture(directoryId){
	if(directoryId == ""){
		asyncbox.tips("请选择画册目录");
		return ;
	}
	var uri = "u/albumsAction.htm?method=forwardAddPicture&directoryId=" + directoryId ;
	window.open(uri,'画册图片','height=480,width=420,top=150,left=510,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
}

// 保存照片信息
function savePicture(){
	if($("#picName").val() == ""){
		asyncbox.tips("请输入图片名称");
		return ;
	}else if($("#englishName").val() == ""){
		asyncbox.tips("请输入英文名称");
		return ;
	}else if($("#indexPic").val() == ""){
		asyncbox.tips("请输入图片序号");
		return ;
	}
	// 提交表单
	$("#pictureForm").submit() ;
}

// 显示照片信息
function showPicture(directoryId){
	window.location.href = "u/albumsAction.htm?method=showPicture&albumId=" + $("#albumId").val() + "&directoryId=" + directoryId ;
}

// 跳转至修改照片信息
function editPicture(uuid){
	var uri = "u/albumsAction.htm?method=forwardUpdatePicture&uuid=" + uuid ;
	window.open(uri,'画册照片','height=480,width=420,top=150,left=510,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
}

//  跳转至发布更新
function forwardPublishInfo(){
	var uri = "u/albumsAction.htm?method=forwardPublishInfo&albumId=" + $("#albumId").val() ;
	window.open(uri,'画册发布更新','height=480,width=420,top=150,left=510,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
}

// 跳转二维码
function forwardErWeiMa(){
	var uri = "u/albumsAction.htm?method=forwardErWeiMa&albumId=" + $("#albumId").val() ;
	window.open(uri,'画册二维码','height=480,width=420,top=150,left=510,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
}

// 删除图片
function deletePicture(uuid){
	if(confirm("是否删除图片?")){
		$.post("u/albumsAction.htm?method=deleltePicture", {
			"uuid" : uuid
		}, function(data) {
			window.location.reload() ;
		});
	}
}
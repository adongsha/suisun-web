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
	$(".active").removeClass("active");
	$(".ico8").parent().addClass("active");
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
        'buttonText'    : '选择上传文件',  
        'swf'           : getRootPath() + '/ui/js/uploadify/uploadify.swf?ver=' + Math.random(),  
        'uploader'      : getRootPath() + '/u/uploadAction.htm;jsessionid=' + session_id,  
        'auto'          : true,
        'formData' :{
        	"method" : "uploadApp"
        },
        'onUploadStart' : function(file) {
        },  
        'onUploadSuccess':function(file, data, response){
        	// 设置地址
        	var strs = data.split("/");
        	var picName = strs[strs.length - 1] ;
        	
        	$("#downloadUrl").val(picName) ;
        	asyncbox.tips("文件上传成功");
        },  
        'onUploadComplete':function(file,swfuploadifyQueue){  
        	
        },
        onError: function(event, queueID, fileObj) {  
            asyncbox.tips("文件名称:" + fileObj.name + "上传失败");
        }
    });
}

// 跳转至新增APP信息
function forwardAddApp(){
	var uri = "u/appAction.htm?method=forwardAddApp" ;
	window.open(uri,'新增版本信息','height=480,width=420,top=150,left=510,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
}

// 跳转至编辑APP信息
function editApp(uuid){
	var uri = "u/appAction.htm?method=forwardUpdateApp&uuid=" + uuid ;
	window.open(uri,'编辑版本信息','height=480,width=420,top=150,left=510,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,depended=no,alwaysRaised=yes');
}

//保存APP信息
function saveApp(){
	if($("#versionCode").val() == ""){
		asyncbox.tips("请输入版本号");
		return ;
	}else if($("#appPlatform").val() == ""){
		asyncbox.tips("请输入平台名称");
		return ;
	}else if($("#updateContent").val() == ""){
		asyncbox.tips("请输入更新内容");
		return ;
	}else if($("#downloadUrl").val() == ""){
		asyncbox.tips("请上传文件");
		return ;
	}
	// 提交表单
	$("#appForm").submit() ;
}

// 删除信息
function deleteApp(uuid){
	if(confirm("是否删除版本信息?")){
		$.post("u/appAction.htm?method=deleteApp", {
			"uuid" : uuid
		}, function(data) {
			window.location.reload() ;
		});
	}
}

// 下载APP
function downloadApp(url,version){
	window.location.href = "u/appAction.htm?method=download&url=" + url + "&version=" + version ;
}
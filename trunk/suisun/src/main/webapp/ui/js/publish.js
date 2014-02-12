// 保存发布信息
function saveInfo(){
	alert("") ;
	if($("#updateContent").val() == ""){
		asyncbox.tips("请填写更新内容");
		return ;
	}
	$("#publishForm").submit() ;
}
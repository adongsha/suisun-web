<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<meta charset="utf-8">
		<title>随商管理后台</title>
		<link rel="stylesheet" href="ui/js/jquery/css/bootstrap.min.css">
		<link rel="stylesheet" href="ui/js/uploadify/uploadify.css"></link>
		<link href="ui/js/asyncbox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="ui/js/uploadify/jquery.uploadify-3.1.min.js"></script>
		<script type="text/javascript" src="ui/js/asyncbox/AsyncBox.v1.4.5.js" charset="UTF-8"></script>
		<script type="text/javascript" src="ui/js/directory.js" charset="UTF-8"></script>
		<script type="text/javascript">
			var flag = '${flag}' ;
			if(flag == "success"){
				alert("保存成功") ;
				window.opener.window.location.reload() ;
				window.close() ;
			}
		</script>
	</head>
	
	<body onload="document.getElementById('picName').focus()">
		<form:form id="pictureForm" action="u/albumsAction.htm?method=updatePicture" method="post" commandName="picture">
			<form:hidden path="uuid"/>
			<form:hidden path="albumDirectoryId"/>
			<form:hidden path="audit"/>
			<table style="width: 100%;height: auto;border: 0px;">
				<tr>
					<td style="padding-left: 50px;padding-top: 20px;">
						图片名称 <form:input path="picName"/>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 20px;padding-left: 50px;">
						英文名称 <form:input path="englishName"/>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 20px;padding-left: 50px;">
						图片序号 <form:input path="indexPic"/>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 20px;padding-left: 50px;">
						<table>
							<tr>
								<td style="font-size: 12">
									<form:hidden path="picUrl" />
									<input type="file" id="uploadifyFile" name="uploadifyFile" />
									请按A4纸比例来制作图片，选择要上传的文件后，请点击“上传”按钮
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 20px;padding-left: 50px;">
						<img id="pictureImg" src="<%=basePath%>${picture.picUrl}" width="300" height="200">
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center" style="padding-top: 20px;">
						<input type="button" value="修改" class="btn btn-primary" onclick="savePicture()"/>
						<input type="reset" value="重置" class="btn"/>
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>
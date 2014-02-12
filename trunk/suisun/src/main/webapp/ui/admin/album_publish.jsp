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
		<title>画册发布更新</title>
		<link rel="stylesheet" href="ui/js/jquery/css/bootstrap.min.css">
		<link rel="stylesheet" href="ui/js/uploadify/uploadify.css"></link>
		<link href="ui/js/asyncbox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="ui/js/uploadify/jquery.uploadify-3.1.min.js"></script>
		<script type="text/javascript" src="ui/js/asyncbox/AsyncBox.v1.4.5.js" charset="UTF-8"></script>
		<script type="text/javascript" src="ui/js/publish.js" charset="UTF-8"></script>
		<script type="text/javascript">
			var flag = '${flag}' ;
			if(flag == "success"){
				alert("保存成功") ;
				window.opener.window.location.reload() ;
				window.close() ;
			}
		</script>
	</head>
	
	<body onload="document.getElementById('updateContent').focus()">
		<form:form id="publishForm" action="u/albumsAction.htm?method=addPublishInfo" method="post" commandName="publish">
			<form:hidden path="albumId"/>
			<table style="width: 100%;height: auto;border: 0px;">
				<tr>
					<td style="padding-left: 50px;padding-top: 20px;font-size: 15">
						画册更新内容 <form:textarea path="updateContent" rows="6" cols="30"/>
					</td>
				</tr>
				
				<tr>
					<td style="padding-left: 50px;padding-top: 5px;font-size: 12;padding-right: 50px;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						每次更新画册，更新的说明内容不能超过80个汉字，请简要说明即可，避免影响客户体验，降低客户印象
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center" style="padding-top: 20px;">
						<input type="button" value="发布" class="btn btn-primary" onclick="saveInfo()"/>
						<input type="reset" value="重置" class="btn"/>
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>
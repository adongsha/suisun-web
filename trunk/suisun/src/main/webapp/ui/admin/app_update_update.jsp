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
		<script type="text/javascript" src="ui/js/app.js" charset="UTF-8"></script>
		<script type="text/javascript">
			var flag = '${flag}' ;
			if(flag == "success"){
				alert("保存成功") ;
				window.opener.window.location.reload() ;
				window.close() ;
			}
		</script>
	</head>
	
	<body onload="document.getElementById('versionCode').focus()">
		<input type="hidden" id="sessionid" value="${pageContext.session.id}"/>
		<form:form id="appForm" action="u/appAction.htm?method=updateApp" method="post" commandName="AppUpdate">
			<form:hidden path="uuid"/>
			<form:hidden path="createTime"/>
			<table style="width: 100%;height: auto;border: 0px;">
				<tr>
					<td style="padding-left: 50px;padding-top: 20px;">
						版&nbsp;&nbsp;本&nbsp;&nbsp;号 <form:input path="versionCode"/>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 20px;padding-left: 50px;">
						软件平台 <form:select path="appPlatform">
									<form:option value="">-----请选择-----</form:option>
									<c:forEach items="${platforms}" var="plat">
										<form:option value="${plat}">${plat}</form:option>
									</c:forEach>
								</form:select>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 20px;padding-left: 50px;" valign="top">
						更新内容 <form:textarea path="updateContent" rows="10" cols="10"/>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 20px;padding-left: 50px;">
						<table>
							<tr>
								<td style="font-size: 12">
									<form:hidden path="downloadUrl" />
									<input type="file" id="uploadifyFile" name="uploadifyFile" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center" style="padding-top: 20px;">
						<input type="button" value="修改" class="btn btn-primary" onclick="saveApp()"/>
						<input type="reset" value="重置" class="btn"/>
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>
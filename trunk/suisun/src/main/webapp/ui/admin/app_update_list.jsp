<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
<base href="<%=basePath%>" />
<meta charset="utf-8">
<title>随商管理后台</title>
<link media="all" rel="stylesheet" type="text/css"
	href="ui/css/main.css" />
<link media="all" rel="stylesheet" type="text/css"
	href="ui/css/table.css" />
<link href="ui/js/asyncbox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="ui/js/asyncbox/AsyncBox.v1.4.5.js" charset="UTF-8"></script>
<script type="text/javascript" src="ui/js/app.js" charset="UTF-8"></script>
<script type="text/javascript">
	function changeStatus(uuid,audit){
		$.post("u/albumsAction.htm?method=changeStatus", {
			"uuid" : uuid,
			"audit" : audit
		}, function(data) {
			alert("审批完成") ;
			window.location.reload() ;
		});
	}
	
</script>
</head>
<body>
	<div >
		<div id="content" style="margin: 0;padding: 0;">
			<div class="c1" style="margin: 0;padding: 0;">
				<div id="right_content" style="margin: 0;padding: 0;">
					<h2>
						<label>系统版本管理</label> <label
							style="margin-left: 100px; float: right;"> 
							<input type="button" value="新建" onclick="forwardAddApp()" style="margin-right: 10px;"/>
						</label>
					</h2>
					<table id="rounded-corner" style="table-layout:fixed;">
						<thead>
							<tr>
								<th align="center" width="100">ID</th>
								<th align="center" width="100">平台</th>
								<th align="center" width="100">版本号</th>
								<th align="center" width="100">升级日志</th>
								<th align="center" width="100">更新时间</th>
								<th align="center" width="100">文件</th>
								<th width="50" align="center">操作</th>
							</tr>
						</thead>
						<tfoot>
							
						</tfoot>
						<tbody>
							<c:forEach items="${apps}" var="p" varStatus="status">
								<tr
									<c:if test="${status.count%2 == 1}">
								   		class="even"
									</c:if>
									<c:if test="${status.count%2 == 0}">
										class="odd"
									</c:if>>
									<td align="center" style="font-size: 12">${p.uuid}</td>
									<td align="center" style="font-size: 12">${p.appPlatform}</td>
									<td align="center" style="font-size: 12">${p.versionCode}</td>
									<td align="center" style="font-size: 12">${p.updateContent}</td>
									<td align="center" style="font-size: 12">${p.createTime}</td>
									<td align="center" style="font-size: 12">
										<a href="javascript:void(0);" onclick="downloadApp('${p.downloadUrl}','${p.versionCode}')">
											下载
										</a>
									</td>
									<td align="center" style="font-size: 12">
										<a href="javascript:void(0);" id="edit" onclick="editApp('${p.uuid}')">
											<img src="ui/images/edit.png" alt="" title="" border="0" /> </a>
										<a href="javascript:void(0);" id="trash" onclick="deleteApp('${p.uuid}')">
											<img src="ui/images/trash.gif" alt="" title="" border="0" /> </a>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
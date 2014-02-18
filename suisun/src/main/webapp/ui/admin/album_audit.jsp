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
	
	function showAuditInfo(){
		window.location.href = "u/albumsAction.htm?method=showAuditInfo&albumName=" + $("#albumName").val() ;
	}
</script>

</head>
<body>
	<div id="wrapper">
		<div id="content">
			<div class="c1">

				<%@include file="common/head.jsp"%>

				<div id="right_content">
					<h2>
						<label>画册审批</label> <label
							style="margin-left: 100px; float: right;"> <input
							type="text" placeholder="画册名称" id="albumName"/> <a href="javascript:void(0);"
							class="button greens" onclick="showAuditInfo()">搜索</a> </label>
					</h2>
					<table id="rounded-corner">
						<thead>
							<tr>
								<th align="center" width="100">企业名称</th>
								<th align="center" width="100">画册名称</th>
								<th align="center" width="100">英文名称</th>
								<th align="center" width="100">发布时间</th>
								<th align="center" width="100">更新内容</th>
								<th width="50" align="center">审批</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${publishList}" var="p" varStatus="status">
								<tr
									<c:if test="${status.count%2 == 1}">
								   		class="even"
									</c:if>
									<c:if test="${status.count%2 == 0}">
										class="odd"
									</c:if>>
									<td align="center" style="font-size: 12">${p.userName}</td>
									<td align="center" style="font-size: 12">${p.albumName}</td>
									<td align="center" style="font-size: 12">${p.englisthName}</td>
									<td align="center" style="font-size: 12">${p.createTime}</td>
									<td align="center" style="font-size: 12">${p.updateContent}</td>
									<td align="center" style="font-size: 12">
										<a href="javascript:void(0);" id="edit"
										onclick="changeStatus('${p.uuid}','1')"> 通过 </a> <a
										href="javascript:void(0);" id="trash"
										onclick="changeStatus('${p.uuid}','-1')"> 拒绝 </a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<%@include file="common/left.jsp"%>
	</div>
</body>
</html>
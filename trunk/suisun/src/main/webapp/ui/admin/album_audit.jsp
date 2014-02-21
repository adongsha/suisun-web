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
   $(function(){
       	$(".active").removeClass("active");
	$(".ico6").parent().addClass("active");
   });
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
	
	// 推送所有
	function pushAllInfo(){
		var inputs = document.getElementsByTagName("input");//获取所有的input标签对象
		
		for(var i=0;i<inputs.length;i++){
			var obj = inputs[i];
			
			if(obj.type=='checkbox'){
				if(obj.checked){
					obj.checked = false ;
				}else{
					obj.checked = true ;
				}
			}
		}
	}
	
	// 推送选中
	function pushInfo(){
		var inputs = document.getElementsByTagName("input");//获取所有的input标签对象
		var ids = "" ;
		
		for(var i=0;i<inputs.length;i++){
		  var obj = inputs[i];
		  if(obj.type=='checkbox' && obj.checked){
			  ids += obj.value + "," ;
		  }
		}
		
		// 截取ID
		ids = ids.substring(0, ids.length-1) ;
		
		if(ids != ""){
			alert(ids) ;
			return ;
			$.post("u/albumsAction.htm?method=deleteAllPictrue", {
				"id" : ids
			}, function(data) {
				window.location.reload() ;
			});
		}else{
			asyncbox.tips("请选择审批信息");
		}
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
						<label>画册审批</label>
							<label style="float: right;margin-right: 10;">
								<input type="button" name="pushAll" value="推送选中" onclick="pushInfo()">
							</label>
							 <!-- <label
							style="margin-left: 100px; float: right;"> <input
							type="text" placeholder="画册名称" id="albumName"/> <a href="javascript:void(0);"
							class="button greens" onclick="showAuditInfo()">搜索</a> </label> -->
					</h2>
					<table id="rounded-corner">
						<thead>
							<tr>
								<th align="center" width="80"><input type="button" value="全选" onclick="pushAllInfo()"/></th>
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
									<td align="center" style="font-size: 12">
										<input type="checkbox" value="${p.uuid}" />
									</td>
									<td align="center" style="font-size: 12">${p.userName}</td>
									<td align="center" style="font-size: 12">${p.albumName}</td>
									<td align="center" style="font-size: 12">${p.englisthName}</td>
									<td align="center" style="font-size: 12">${p.createTime}</td>
									<td align="center" style="font-size: 12">${p.updateContent}</td>
									<td align="center" style="font-size: 12">
										<c:if test="${p.audit == 0}">
										<a href="javascript:void(0);" id="edit"
										onclick="changeStatus('${p.uuid}','1')"> 通过 </a> <a
										href="javascript:void(0);" id="trash"
										onclick="changeStatus('${p.uuid}','-1')"> 拒绝 </a>
										</c:if>
										<c:if test="${p.audit == 1}">
											<font color="green">通过</font>
										</c:if>
										<c:if test="${p.audit == -1}">
											<font color="red">拒绝</font>
										</c:if>
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
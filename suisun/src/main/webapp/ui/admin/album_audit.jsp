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
<link href="ui/js/asyncbox/skins/ZCMS/asyncbox.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="ui/js/asyncbox/AsyncBox.v1.4.5.js"
	charset="UTF-8"></script>
<script type="text/javascript">
	$(function() {
		$(".active").removeClass("active");
		$(".ico6").parent().addClass("active");
	});
	function changeStatus(uuid, audit) {
		$.post("u/albumsAction.htm?method=changeStatus", {
			"uuid" : uuid,
			"audit" : audit
		}, function(data) {
			alert("审批完成");
			window.location.reload();
		});
	}

	function showAuditInfo() {
		window.location.href = "u/albumsAction.htm?method=showAuditInfo&albumName="
				+ $("#albumName").val();
	}

	function push(id) {
		$.post("u/albumsAction.htm?method=push", {
			"tag" : id
		}, function(data) {
			if (data == 1) {
				asyncbox.tips("推送成功..");
			} else {
				asyncbox.tips("推送失败..");
			}
		});
	}

	// 全选按钮
	function pushAllInfo() {
		var inputs = document.getElementsByTagName("input");//获取所有的input标签对象

		for ( var i = 0; i < inputs.length; i++) {
			var obj = inputs[i];

			if (obj.type == 'checkbox') {

				obj.checked = true;
			}
		}
	}

	// 推送选中
	function pushInfo() {
		var inputs = document.getElementsByTagName("input");//获取所有的input标签对象
		var ids = "";

		for ( var i = 0; i < inputs.length; i++) {
			var obj = inputs[i];
			if (obj.type == 'checkbox' && obj.checked) {
				ids += obj.value + ",";
			}
		}

		// 截取ID
		ids = ids.substring(0, ids.length - 1);

		if (ids != "") {
			alert(ids);
			$.post("u/albumsAction.htm?method=push", {
				"tag" : ids
			}, function(data) {
				if (data == 1) {

					asyncbox.tips("推送成功..");
				} else {
					asyncbox.tips("推送失败..");
				}
			});

			return;
			/* 	$.post("u/albumsAction.htm?method=deleteAllPictrue", {
					"id" : ids
				}, function(data) {
					window.location.reload();
				}); */
		} else {
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

						<!-- <label
							style="margin-left: 100px; float: right;"> <input
							type="text" placeholder="画册名称" id="albumName"/> <a href="javascript:void(0);"
							class="button greens" onclick="showAuditInfo()">搜索</a> </label> -->
					</h2>
					<table id="rounded-corner">
						<thead>
							<tr>
								<th align="center" width="40">
									<!-- <input type="button"
									value="全选" onclick="pushAllInfo()" /> -->
								</th>
								<th align="center" width="100">企业名称</th>
								<th align="center" width="100">画册名称</th>
								<th align="center" width="100">英文名称</th>
								<th align="center" width="100">发布时间</th>
								<th align="center" width="100">更新内容</th>
								<th align="center" width="100">查看画册</th>
								<th width="100" align="center">审批</th>
								<th align="center" width="100">推送</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="12">
									<div class="form_sub_buttons" style="margin: 0px;padding: 0px;">
										<a class="button green" style="float: left;"
											onclick="pushAllInfo()">全选</a> <a onclick="pushInfo();"
											class="button red" style="float: left;">推送选中的</a>
									</div></td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${publishList}" var="p" varStatus="status">
								<tr
									<c:if test="${status.count%2 == 1}">
								   		class="even"
									</c:if>
									<c:if test="${status.count%2 == 0}">
										class="odd"
									</c:if>>
									<td align="center" style="font-size: 12"><c:if
											test="${p.audit == 1}">
											<input type="checkbox" value="${p.uuid}" />

										</c:if></td>
									<td align="center" style="font-size: 12">${p.userName}</td>
									<td align="center" style="font-size: 12">${p.albumName}</td>
									<td align="center" style="font-size: 12">${p.englisthName}</td>
									<td align="center" style="font-size: 12">${p.createTime}</td>
									<td align="center" style="font-size: 12">${p.updateContent}</td>
									<td align="center" style="font-size: 12"><a
										href="u/albumsAction.htm?method=forwardDirectory&albumId=${p.uuid}">[查看画册]</a>
									</td>

									<td align="center" style="font-size: 12"><c:if
											test="${p.audit == 0}">
											<a href="javascript:void(0);" id="edit"
												onclick="changeStatus('${p.uuid}','1')"> [通过] </a>
											<a href="javascript:void(0);" id="trash"
												onclick="changeStatus('${p.uuid}','-1')"> [不通过] </a>
										</c:if> <c:if test="${p.audit == 1}">
											<font color="green">[通过]</font>
										</c:if> <c:if test="${p.audit == -1}">
											<font color="red">[不通过]</font>
										</c:if></td>
									<td align="center" style="font-size: 12"><c:if
											test="${p.audit == 1}">

											<a class="button green" onclick="push('${p.uuid}')">推 送</a>

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
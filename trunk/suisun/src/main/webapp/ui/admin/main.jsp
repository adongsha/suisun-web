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
<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="ui/js/pageUser.js"></script>
<script type="text/javascript" src="ui/js/admin-main.js"></script>

<!--[if lt IE 9]><link rel="stylesheet" type="text/css" href="css/ie.css" /><![endif]-->
</head>
<body>
	<div id="wrapper">
		<div id="content">
			<div class="c1">

				<%@include file="common/head.jsp"%>

				<div id="right_content">
					<h2>
						<label>用户管理</label> <label
							style="margin-left: 100px; float: right;"><input
							type="text" id="search" placeholder="用户名、企业名称"
							<c:if test="${!empty search}">value="${search}" </c:if> /><a
							href="javascript:void(0);" class="button greens" id="searchBtn">搜索</a>
						</label>
						<c:if test="${user.power == 0}">
							<label style="float: right;">查看:<select id="power">
									<option value="3"
										<c:if test="${power == 3 }"> selected="selected"</c:if>>全部</option>
									<option value="2"
										<c:if test="${power == 2 }"> selected="selected"</c:if>>普通用户</option>
									<option value="1"
										<c:if test="${power == 1 }"> selected="selected"</c:if>>管理员</option>

							</select> </label>
						</c:if>
					</h2>
					<table id="rounded-corner">
						<thead>
							<tr>
								<th></th>
								<th>用户名</th>
								<th>企业名称</th>
								<th>地址</th>
								<th>负责人</th>
								<th>电话</th>
								<th>最近登录时间</th>
								<th width="5%">编辑</th>
								<th width="5%">删除</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="12">
									<div class="form_sub_buttons">
										<a href="#" class="button green" style="float: left;">全选</a> <a
											href="#" class="button red" style="float: left;">删除选中的</a>
									</div>


									<div style="width: 400px;margin-top: 10px;float: right;">
										<input type="hidden" value="${currentPage}" id="current">
										<input type="hidden" value="${pageAll}" id="pageAll">
										当前第${currentPage}页&nbsp;&nbsp;一共${pageAll}页&nbsp;&nbsp; <input
											type="button" value="上一页" onclick="page.per();" />&nbsp;&nbsp;
										<input type="button" value="下一页" onclick="page.next()" />&nbsp;&nbsp;
										<input type="text" style="width: 30px;"
											value="${currentPage }" name="currentPage" />&nbsp;&nbsp; <input
											type="button" value="确认" onclick="page.jump();" />&nbsp;&nbsp;
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${userList}" var="u" varStatus="status">
								<tr id="${u.uuid}"
									<c:if test="${status.count%2 == 1}">
								   class="even"
								</c:if>
									<c:if test="${status.count%2 == 0}">
									class="odd"
								</c:if>>
									<td><input type="checkbox" name="" /></td>
									<td>${u.account}</td>
									<td>${u.enterpriseName}</td>
									<td>${u.address}</td>
									<td>${u.linkman}</td>
									<td>${u.phone}</td>
									<td>${u.lastTime}</td>
									<td><a href="javascript:void(0);" id="edit"
										onclick="edit('${u.uuid}')"><img src="ui/images/edit.png"
											alt="" title="" border="0" /> </a></td>
									<td><a href="javascript:void(0);" id="trash"
										onclick="deleteUser('${u.uuid}')"><img
											src="ui/images/trash.gif" alt="" title="" border="0" /> </a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
					
					<iframe src="<%=basePath%>u/albumsAction.htm?method=showAuditInfo&albumName=" width="100%" height="500" style="margin-top: 20px;" frameborder=”no” border=”0″></iframe>
					
				</div>


			</div>
		</div>
		<%@include file="common/left.jsp"%>


	</div>
</body>
</html>
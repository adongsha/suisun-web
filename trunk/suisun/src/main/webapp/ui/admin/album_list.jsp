<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.suisun.beans.Album"%>
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
		<title>画册管理</title>
		<link media="all" rel="stylesheet" type="text/css" href="ui/css/main.css" />
		<link media="all" rel="stylesheet" type="text/css" href="ui/css/table.css" />
		<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="ui/js/pageUser.js"></script>
		<script type="text/javascript" src="ui/js/admin-main.js"></script>
	</head>
<body>
	<div id="wrapper">
		<div id="content">
			<div class="c1">

				<%@include file="common/head.jsp"%>

				<div id="right_content">
					<h2>
						<label>画册管理</label> <label
							style="margin-left: 100px; float: right;">
								<input type="text" id="search" size="14" placeholder="用户名、企业名称"
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
									</div></td>
							</tr>
						</tfoot>
						<tbody>
							<% 
								List<Album> albums = (List<Album>)request.getAttribute("albums") ;
								// 总记录数
								int size = albums.size() ;
								// 总行数
								int row = 0 ;
								
								if(size%4 == 0){
									row = size / 4 ;
								}else{
									row = size / 4 + 1 ;
								}
								
								for(int i = 0; i < row; i++){
							%>
								<tr>				
							<%
									for(int j = i*4; j < (i+1)*4; j++){
										if(j >= size){
											break ;
										}
										if(albums.get(j) != null){
							%>
											<td>
							<% 
												Album album = albums.get(j) ;
							%>
												<img alt="" src="<%=basePath%>upload/bastketball.jpg" width="250" height="200"/>
							<%
							%>
											</td>
							<%
										}
									}
							%>
								</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<%@include file="common/left.jsp"%>
	</div>
</body>
</html>
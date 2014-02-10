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
		<script type="text/javascript" src="ui/js/album.js" charset="UTF-8"></script>
	</head>
<body>
	<div id="wrapper">
		<div id="content">
			<div class="c1">

				<%@include file="common/head.jsp"%>

				<div id="right_content">
					<h2>
						<label>画册管理</label> 
						<label style="margin-left: 100px; float: right;">
								<input type="text" id="albumName" size="14" placeholder="相册名称" value="<%=request.getAttribute("albumName")%>"/>
								<a href="javascript:void(0);" class="button greens" id="searchBtn">搜索</a>
								<input type="button" value="新建画册" id="addAlbum"/>
						</label>
						<c:if test="${not empty enterprises}">
							<label style="float: right;">企业名称:
								<select id="enterprises">
									<option value="">
										全部
									</option>
									<c:choose>
										<c:when test="${not empty enterprises}">
											<c:forEach items="${enterprises}" var="e">
												<option value="${e}" <c:if test="${enterpriseName == e}">selected</c:if> >									
													${e}
												</option>
											</c:forEach>
										</c:when>
									</c:choose>
								</select>
							</label>
						</c:if>
					</h2>
					<table id="rounded-corner">
						<tfoot>
							<tr>
								<td colspan="12">
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
											<td align="center">
							<% 
												Album album = albums.get(j) ;
							%>
												<div style="background-color: white;">
													<img alt="" src="<%=basePath%>upload/bastketball.jpg" style="margin: 4px" width="250" height="200"/>
													<table style="width: 100%;">
														<tr>
															<td width="70%" style="padding-left: 10px">
																<a href="javascript:void(0);" onclick="">
																	<font size="3px" color="black">测试专用</font>
																</a>
															</td>
															
															<td width="30%" align="right" style="padding-right: 10px">
																<a href="javascript:void(0);" id="edit" onclick="">
																	<img src="ui/images/edit.png" border="0" />
																</a>
															</td>
														</tr>
													</table>
												</div>
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
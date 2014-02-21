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
		<script type="text/javascript" src="ui/js/uploadify/jquery.uploadify-3.1.min.js"></script>
		<script type="text/javascript" src="ui/js/album.js" charset="UTF-8"></script>
		
	</head>
<body onload="document.getElementById('albumName').focus();">
	<input type="hidden" id="sessionid" value="${pageContext.session.id}"/>
	<div id="wrapper">
		<div id="content">
			<div class="c1">

				<%@include file="common/head.jsp"%>

				<div id="right_content">
					<h2>
						<label>画册管理</label>
						<label style="margin-left: 10px; float: right;">
								<input type="text" id="albumName" size="14" placeholder="相册名称" value="<%=request.getAttribute("albumName")%>"/>
								<a href="javascript:void(0);" class="button greens" id="searchBtn">搜索</a>
						</label>
						<label style="margin-left: 10px; float: right;">
							<c:if test="${not empty enterprises}">
									企业名称:
										<select id="enterprises" style="margin-right: 20">
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
								</c:if>
						</label>
						
						<label style="margin-left: 10px; float: right;"> 
							<input type="button" value="新建画册" id="addAlbum" style="margin-right: 10;"/>
						</label>
					</h2>
					<table>
						
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
											<td align="left">
							<% 
												Album album = albums.get(j) ;
							%>
												<div style="background-color: white; width: 280;height: 240;text-align: center;">
													<input type="hidden" id="uuid" value="<%=album.getUuid()%>"/>
													<a href="javascript:void(0);" onclick="forwardDirectory('<%=album.getUuid()%>')">
														<img alt="" src="<%=album.getAlbumCover()%>" style="margin: 4px" width="250" height="200"/>
													</a>
													<table style="width: 100%;">
														<tr>
															<td width="70%" style="padding-left: 10px">
																<a href="javascript:void(0);" onclick="">
																	<font style="font-size: 14" color="black"><%=album.getAlbumName()%></font>
																</a>
															</td>
															
															<td  align="right" style="padding-right: 10px" valign="top">
																<a href="javascript:void(0);" id="edit" onclick="editAlbum('<%=album.getUuid()%>')">
																	<font style="font-size: 12">编辑</font>
																</a>
																<font style="font-size: 12">|</font>
																<a href="javascript:void(0);" id="edit" onclick="deleteAlbum('<%=album.getUuid()%>')">
																	<font style="font-size: 12">删除</font>
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
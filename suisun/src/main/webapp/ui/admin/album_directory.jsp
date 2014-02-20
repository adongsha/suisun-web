<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.suisun.beans.Album"%>
<%@ page import="cn.suisun.beans.AlbumPic"%>
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
		<link href="ui/js/asyncbox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" href="ui/js/jquery/css/bootstrap.min.css">
		<link media="all" rel="stylesheet" type="text/css" href="ui/css/main.css" />
		<link media="all" rel="stylesheet" type="text/css" href="ui/css/table.css" />
		<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="ui/js/uploadify/jquery.uploadify-3.1.min.js"></script>
		<script type="text/javascript" src="ui/js/asyncbox/AsyncBox.v1.4.5.js" charset="UTF-8"></script>
		<script type="text/javascript" src="ui/js/directory.js" charset="UTF-8"></script>
		<script type="text/javascript">
			function chnageColor(obj,col){
				obj.bgColor = col ;
			}
		</script>
	</head>
<body>
	<input type="hidden" id="sessionid" value="${pageContext.session.id}"/>
	<input id="albumId" type="hidden" value="${albumId}"/>
	<div id="wrapper" style="background-color: #EBEBEB;">
		<div id="content">
			<div class="c1">

				<%@include file="common/head.jsp"%>

				<div id="right_content">
					<h2>
						<label>
							画册目录
						</label>
						<label style="text-align: right;margin-right: 10px;">
							<input type="button" value="查看画册二维码" class="btn" onclick="forwardErWeiMa()"/>
							<input type="button" value="上传图片" onclick="forwardAddPicture('${directoryId}')" class="btn"/>
							<!-- <input type="button" value="编辑名片" id="editCard" class="btn"/> -->
							<c:if test="${sessionScope.user.power == 0 || sessionScope.user.power == 1}">
								<input type="button" value="发布更新" class="btn" onclick="forwardPublishInfo()"/>
							</c:if>
							
						</label>
					</h2>
					<table>
						<tbody >
							<tr>
								<td valign="top" style="padding-top: 20px;">
									<table style="width: 220;">
										<tr>
											<td valign="top">
												画册目录
											</td>
											<td align="right" valign="top">
												<a href="javascript:void(0);" onclick="forwardAddDirectory()">新建</a>
											</td>
										</tr>
										<tr>
											<c:choose>
												<c:when test="${not empty directorys }">
													<c:forEach items="${directorys }" var="d">
														<tr class="tdBody tr_over" >
															<td style="font-size: 12px;" style="cursor: hand" bgcolor="${d.uuid == directoryId ? '#A4D3EE' : ''}">
																<a href="javascript:void(0);" onclick="showPicture('${d.uuid}')">
																	${d.directoryName}
																</a>
															</td>
															<td style="text-align: right;" width="50">
																<a href="javascript:void(0);" onclick="forwardUpdateDirectory('${d.uuid}')">
																	<img style="margin-right: 5px;" alt="编辑" src="ui/images/edit.png" border="0" align="middle"/>
																</a>
																<a href="javascript:void(0);" onclick="deleteDirectory('${d.uuid}')">
																	<img style="margin-right: 5px;" onclick="" alt="删除" src="ui/images/trash.gif" border="0" align="middle"/>
																</a>
															</td>
														</tr>
													</c:forEach>
												</c:when>
											</c:choose>
										</tr>
									</table>
								</td>
								<td>
									<table >
											<% 
												List<AlbumPic> pic = (List<AlbumPic>)request.getAttribute("picList") ;
												// 总记录数
												int size = pic.size() ;
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
														if(pic.get(j) != null){
											%>
															<td align="center">
											<% 
																AlbumPic p = pic.get(j) ;
											%>
																<div style="background-color: white;margin-top: 10px;">
																	<a href="javascript:void(0);" onclick="">
																		<img alt="" src="<%=p.getPicUrl()%>" style="margin: 10px" width="300" height="200"/>
																	</a>
																	<table style="width: 100%;">
																		<tr>
																			<td width="70%" style="padding-left: 10px;padding-bottom: 10px;">
																				<a href="javascript:void(0);" onclick="">
																					<font size="3px" color="black"><%=p.getPicName()%></font>
																				</a>
																			</td>
																			
																			<td width="30%" align="right" style="padding-right: 10px;padding-bottom: 10px;">
																				<a href="javascript:void(0);" id="edit" onclick="editPicture('<%=p.getUuid()%>')">
																					<img src="ui/images/edit.png" border="0" />
																				</a>
																				<a style="padding-left: 5px;" href="javascript:void(0);" onclick="deletePicture('<%=p.getUuid()%>')">
																					<img style="margin-right: 5px;" onclick="" alt="删除" src="ui/images/trash.gif" border="0" align="middle"/>
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
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<%@include file="common/left.jsp"%>
	</div>
</body>
</html>
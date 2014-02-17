
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

<link rel="stylesheet" href="ui/css/recommend/common.css"
	type="text/css" />
<link type="text/css" rel="stylesheet"
	href="ui/js/jquery/css/jquery-ui.css" />
<link type="text/css" href="ui/css/recommend/ui.multiselect.css"
	rel="stylesheet" />

<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="ui/js/jquery/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="ui/js/jquery/jquery.localisation-min.js"></script>
<script type="text/javascript" src="ui/js/jquery/jquery.tmpl.1.1.1.js"></script>
<script type="text/javascript" src="ui/js/recommend/ui.multiselect.js"></script>
<script type="text/javascript" src="ui/js/recommend.js"></script>

<script type="text/javascript">
	$(function() {
		$.localise('ui.multiselect', {/*language: 'es',/* */
			path : 'js/locale/'
		});

		// local
		$("#countries").multiselect();

	});
</script>
<!--[if lt IE 9]><link rel="stylesheet" type="text/css" href="css/ie.css" /><![endif]-->
</head>
<body>
	<div id="wrapper">
		<div id="content">
			<div class="c1">

				<%@include file="common/head.jsp"%>

				<div id="right_content">
					<h2>
						<label>推荐画册</label>
					</h2>

					<form target="submitFrame" style="border: none;">
						<dl>
							<dd>
								<select id="countries" class="multiselect" multiple="multiple"
									name="countries[]" style="height:70%; width: 50%">

									<c:forEach items="${aList}" var="a">
										<option value="${a.uuid}">${a.albumName}</option>
									</c:forEach>
									
                                    <c:forEach items="${rList}" var="b">
                                        <option value="${b.uuid}"
									          selected="selected"
									     >${b.albumName}</option>
                                    </c:forEach>
								</select>
							</dd>
						</dl>
					</form>
					<div>
						<a href="javascript:void(0);" id="submit" class="button green"
							style="float: right;margin-right:400px;">提交</a>
					</div>


				</div>

			</div>
		</div>
		<%@include file="common/left.jsp"%>


	</div>
</body>
</html>
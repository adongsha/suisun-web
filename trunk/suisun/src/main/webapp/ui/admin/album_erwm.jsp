<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>随商管理后台</title>
<link rel="stylesheet" href="ui/js/jquery/css/bootstrap.min.css">
<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="ui/js/jquery/jquery.qrcode.min.js"></script>
<script type="text/javascript">
    $(function(){

	$('#qrcodeCanvas').qrcode("http://www.suisun.cn");
	});
</script>
</head>

<body>
	<div id="qrcodeCanvas" align="center" style="margin-top: 50px;"></div>
</body>
</html>
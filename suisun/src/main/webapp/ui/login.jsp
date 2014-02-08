<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>随商后台管理系统</title>
<link rel="stylesheet" type="text/css" href="ui/css/login.css" />
<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
<link href="ui/js/asyncbox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="ui/js/asyncbox/AsyncBox.v1.4.5.js" charset="UTF-8"></script>
<script type="text/javascript" src="ui/js/login.js"></script>
</head>
<body onload="document.getElementById('username').focus()">
<div id="loginpanelwrap">
  	
	<div class="loginheader">
    <div class="logintitle">随商后台管理系统</div>
    </div>

     
    <div class="loginform">
        
        <div class="loginform_row">
        <label style="font-size: 14px;">用户名:</label>
        <input type="text" class="loginform_input" name="" id="username" />
        </div>
        <div class="loginform_row">
        <label style="font-size: 14px;">密码:</label>
        <input type="password" class="loginform_input" name="" id="password" />
        </div>
        <div class="loginform_row">
        <input type="submit" class="loginform_submit" value="登录" id="submit"/>
        </div> 
        <div class="clear"></div>
    </div>
 

</div>

    	
</body>
</html>
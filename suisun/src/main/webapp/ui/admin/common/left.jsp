<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<head>
<meta charset="utf-8">
<title>随商管理后台</title>
<script type="text/javascript" src="ui/js/left.js"></script>
<!--[if lt IE 9]><link rel="stylesheet" type="text/css" href="css/ie.css" /><![endif]-->
</head>
<body>
	<aside id="sidebar">
		<strong class="logo"></strong>
		<ul class="tabset buttons">



			<c:if test="${user.power == 2 }">
				<li id="ico6"><a href="javascript:void(0);" class="ico6"> <span>用户首页</span><em></em>
				</a> <!-- <span class="num">11</span> --> <span class="tooltip"><span>用户首页</span>
				</span>
				</li>
			</c:if>

			<c:if test="${user.power == 0 || user.power == 1 }">
				<li class="active" id="ico1"><a href="javascript:void(0);"
					class="ico1"><span>用户管理</span><em></em> </a> <!-- <span class="num">用户管理</span> -->
					<span class="tooltip"><span>用户管理</span> </span>
				</li>
			</c:if>

			<li id="ico2"><a href="javascript:void(0);" class="ico2"><span>画册管理</span><em></em>
			</a> <!--  <span class="num">画册管理</span> --> <span class="tooltip"><span>画册管理</span>
			</span>
			</li>

			<c:if test="${user.power == 0 || user.power == 1 }">
				<li id="ico0"><a href="javascript:void(0);" class="ico6"> <span>画册审批</span><em></em>
				</a> <!-- <span class="num">11</span> --> <span class="tooltip"><span>画册审批</span>
				</span>
				</li>
			
			<li id="ico7"><a href="javascript:void(0);" class="ico7"><span>推荐画册</span><em></em>
			</a> <span class="tooltip"><span>推荐画册</span> </span>
			</li>
			</c:if>
			<c:if test="${user.power == 0 || user.power == 1 }">
				<li id="ico3"><a href="javascript:void(0);" class="ico3"><span>添加用户</span><em></em>
				</a> <!-- <span class="num">添加用户</span> --> <span class="tooltip"><span>添加用户</span>
				</span>
				</li>

				<li id="ico4"><a href="javascript:void(0);" class="ico4"><span>行业管理</span><em></em>
				</a> <!-- <span class="num">行业管理</span> --> <span class="tooltip"><span>行业管理</span>
				</span>
				</li>

			</c:if>

			<li id="ico5"><a href="javascript:void(0);" class="ico5"><span>客户管理</span><em></em>
			</a> <!-- <span class="num">客户管理</span> --> <span class="tooltip"><span>客户管理</span>
			</span>
			</li>

			<c:if test="${user.power == 0 || user.power == 1 }">
				<li id="ico8"><a href="javascript:void(0);" class="ico8"><span>版本管理</span><em></em>
				</a> <!-- <span class="num">客户管理</span> --> <span class="tooltip"><span>版本管理</span>
				</span>
				</li>
			</c:if>




			<!--
			<li><a href="#tab-8" class="ico8"><span>Settings</span><em></em>
			</a> <span class="tooltip"><span>Settings</span> </span></li> -->
		</ul>
		<span class="shadow"></span>
	</aside>
</body>
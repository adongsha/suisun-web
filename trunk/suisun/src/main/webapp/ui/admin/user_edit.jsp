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
<link rel="stylesheet" href="ui/js/jquery/css/jquery-ui.css">
<link rel="stylesheet" href="ui/js/jquery/css/bootstrap.min.css">
<script type="text/javascript" src="ui/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="ui/js/jquery/jquery-validate.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<div id="content">
			<div class="c1">

				<%@include file="common/head.jsp"%>

				<div id="right_content"
					style="padding:0px; margin: 0px; width: 100%">
					<header class="container"
						style="margin-bottom: 20px; margin-top: 20px; width: 80%">
						<h2 style="font-size: 30px;">修改用户</h2>
					</header>

					<div style="width: 80%" class="container">
						<form id="form" method="post"
							action="u/adminAction.htm?method=editUser"
							enctype="multipart/form-data">
							<input type="hidden" name="uuid" id="uuid" value="${u.uuid}" />

							<div style="float: left; width: 50%; margin: 0px; padding: 0px;">
								<div id="account-description"></div>
								<div class="control-group input-append">
									<input type="text" name="account" id="account"
										data-required="true" data-describedby="account-description"
										data-description="account" style="height: 30px;"
										value="${u.account}" disabled="disabled" /> <label
										for="account" class="add-on"><span
										class="icon-asterisk"></span> 账号</label>
								</div>


								<div id="oldpassword-description"></div>
								<div class="control-group input-append">
									<input type="password" name="oldpassword" id="oldpassword"
										data-describedby="oldpassword-description"
										data-conditional="oldpassword"
										data-description="oldpassword" style="height: 30px;" /> <label
										for="oldpassword" class="add-on"><span
										class="icon-asterisk"></span> 旧密码</label>
								</div>

								<div id="password-description"></div>
								<div class="control-group input-append">
									<input type="password" name="password" id="password"
										data-describedby="password-description"
										data-description="password" style="height: 30px;" /> <label
										for="password" class="add-on"><span
										class="icon-asterisk"></span> 新密码(如果为空,密码不做修改)</label>
								</div>

								<div id="confirm-description"></div>
								<div class="control-group input-append">
									<input type="password" name="confirm" id="confirm"
										data-required="true" data-describedby="confirm-description"
										data-conditional="confirm" data-description="confirm"
										style="height: 30px;" /> <label for="confirm" class="add-on"><span
										class="icon-asterisk"></span>确认密码</label>
								</div>

								<c:if test="${user.power == 0}">
									<div id="power-description"></div>
									<div class="control-group input-append">
										<select name="power" id="power" data-required
											data-describedby="power-description" data-conditional="power"
											data-description="power" style="height: 30px; width: 205px;">
											<option value="2"
												<c:if test="${u.power==2}">selected="selected"</c:if>>普通用户</option>
											<option value="1"
												<c:if test="${u.power==1}">selected="selected"</c:if>>管理员</option>
										</select> <label for="confirm" class="add-on"><span
											class="icon-asterisk"></span>用户类型</label>
									</div>
								</c:if>

								<div id="industry-description"></div>
								<div class="control-group input-append">
									<select name="industry" id="industry" data-required
										data-describedby="industry-description"
										data-conditional="industry" data-description="industry"
										style="height: 30px; width: 205px;">
										<c:forEach items="${inList}" var="in">
											<option value="${in.uuid}"
												<c:if test="${in.uuid==u.industryId}">selected="selected"</c:if>>${in.industryName}</option>
										</c:forEach>
									</select> <label for="confirm" class="add-on"><span
										class="icon-asterisk"></span>行业</label>
								</div>

								<div id="enterpriseName-description"></div>
								<div class="control-group input-append">
									<input type="text" name="enterpriseName" id="enterpriseName"
										data-describedby="enterpriseName-description"
										data-description="enterpriseName" style="height: 30px;"
										value="${u.enterpriseName}" /> <label for="enterpriseName"
										class="add-on"><span class="icon-asterisk"></span>
										企业名称</label>
								</div>

								<div id="enterpriseEnglish-description"></div>
								<div class="control-group input-append">
									<input type="text" name="enterpriseEnglish"
										id="enterpriseEnglish"
										data-describedby="enterpriseEnglish-description"
										data-description="enterpriseEnglish" style="height: 30px;"
										value="${u.enterpriseEnglish}" /> <label
										for="enterpriseEnglish" class="add-on"><span
										class="icon-asterisk"></span> 英文名称</label>
								</div>

								<div id="shortName-description"></div>
								<div class="control-group input-append">
									<input type="text" name=shortName id="shortName"
										data-describedby="shortName-description"
										data-description="shortName" style="height: 30px;"
										value="${u.shortName}" /> <label for="shortName"
										class="add-on"><span class="icon-asterisk"></span>
										企业简称</label>
								</div>

								<div id="shortEnglish-description"></div>
								<div class="control-group input-append">
									<input type="text" name=shortEnglish id="shortEnglish"
										data-describedby="shortEnglish-description"
										data-description="shortEnglish" style="height: 30px;"
										value="${u.shortEnglish}" /> <label for="shortEnglish"
										class="add-on"><span class="icon-asterisk"></span>
										英文简称</label>
								</div>

								<div id="telephone-description"></div>
								<div class="control-group input-append">
									<input type="text" name="telephone" id="telephone"
										data-describedby="telephone-description"
										data-description="telephone" style="height: 30px;"
										value="${u.telephone}" /> <label for="telephone"
										class="add-on"><span class="icon-asterisk"></span> 固话</label>
								</div>

								<div id="email-description"></div>
								<div class="control-group input-append">
									<input type="text" name="email" id="email"
										data-describedby="email-description" data-description="email"
										style="height: 30px;" value="${u.email}" /> <label
										for="email" class="add-on"><span class="icon-asterisk"></span>
										邮箱</label>
								</div>

							</div>


							<div style="float: right; width: 50%;margin: 0px; padding: 0px;">
								<div id="fax-description"></div>
								<div class="control-group input-append">
									<input type="text" name="fax" id="fax"
										data-describedby="fax-description" data-description="fax"
										style="height: 30px;" value="${u.fax}" /> <label for="fax"
										class="add-on"><span class="icon-asterisk"></span> 传真</label>
								</div>

								<div id="address-description"></div>
								<div class="control-group input-append">
									<input type="text" name="address" id="address"
										data-describedby="address-description"
										data-description="address" style="height: 30px;"
										value="${u.address}" /> <label for="address" class="add-on"><span
										class="icon-asterisk"></span> 地址</label>
								</div>

								<div id="website-description"></div>
								<div class="control-group input-append">
									<input type="text" name="website" id="website"
										data-describedby="website-description"
										data-description="website" style="height: 30px;"
										value="${u.website}" /> <label for="website" class="add-on"><span
										class="icon-asterisk"></span> 网址</label>
								</div>

								<div id="linkman-description"></div>
								<div class="control-group input-append">
									<input type="text" name="linkman" id="linkman"
										data-describedby="linkman-description"
										data-description="linkman" style="height: 30px;"
										value="${u.linkman}" /> <label for="linkman" class="add-on"><span
										class="icon-asterisk"></span> 联系人</label>
								</div>

								<div id="englishLinkMan-description"></div>
								<div class="control-group input-append">
									<input type="text" name="englishLinkMan" id="englishLinkMan"
										data-describedby="englishLinkMan-description"
										data-description="englishLinkMan" style="height: 30px;"
										value="${u.englishLinkMan}" /> <label for="englishLinkMan"
										class="add-on"><span class="icon-asterisk"></span>
										联系人英文名</label>
								</div>

								<div id="phone-description"></div>
								<div class="control-group input-append">
									<input type="text" name="phone" id="phone"
										data-describedby="phone-description" data-description="phone"
										style="height: 30px;" value="${u.phone}" /> <label
										for="phone" class="add-on"><span class="icon-asterisk"></span>
										手机号码</label>
								</div>

								<div id="logo-description"></div>
								<div class="control-group input-append">
									<input type="file" name="logo" id="logo"
										data-describedby="logo-description" data-description="logo"
										style="height: 30px; width: 205px;" /> <label for="logo"
										class="add-on"><span class="icon-asterisk"></span>
										企业logo</label>
								</div>
								<div>
								<c:if test="${!empty u.logoUrl}">
									<img alt="" src="${u.logoUrl}" width="100px" height="100px" />								
								</c:if>
								</div>

								<div id="enterpriseInfo-description"></div>
								<div class="control-group input-append">
									<textarea name="enterpriseInfo" id="enterpriseInfo"
										data-describedby="enterpriseInfo-description"
										data-description="enterpriseInfo" rows="5">${u.enterpriseInfo}</textarea>
									<label for="enterpriseInfo" class="add-on"><span
										class="icon-asterisk"></span> 企业信息</label>
								</div>
							</div>
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">修改</button>

								<button type="reset" class="btn">重置</button>
							</div>
						</form>
					</div>

				</div>

			</div>
		</div>
		<%@include file="common/left.jsp"%>

	</div>





	<script>
		$('form')
				.validate(
						{
							onKeyup : true,
							sendForm : true,
							eachValidField : function() {

								$(this).closest('div').removeClass('error')
										.addClass('success');
							},
							eachInvalidField : function() {

								$(this).closest('div').removeClass('success')
										.addClass('error');
							},
							conditional : {
								confirm : function() {

									return $(this).val() == $('#password')
											.val();
								},
								oldpassword : function() {
									var a = $(this).val();
									var flag = $
											.ajax({
												url : "u/adminAction.htm?method=getPassword",
												async : false,
												data: "password="+a,
												type : "POST"
											}).responseText;
									if (flag == "false") {
										return false;
									}
									if (flag == "true") {
										return true;
									}
								}

							},
							description : {
								account : {
									required : '<div class="alert alert-error" style="margin-right: 50%;margin-bottom: 5px; padding-top: 2px;padding-bottom: 2px;">必填</div>',
								},
								username : {
									required : '<div class="alert alert-error">Required</div>',
									pattern : '<div class="alert alert-error">Pattern</div>',
									conditional : '<div class="alert alert-error">Conditional</div>',
									valid : '<div class="alert alert-success">Valid</div>'
								},
								password : {
									conditional : '<div class="alert alert-error" style="margin-right: 30%;">密码不一致</div>',
								},
								oldpassword : {
									conditional : '<div class="alert alert-error" style="margin-right: 30%;">旧密码不正确</div>',
								}
							}
						});
	</script>
</body>
</html>
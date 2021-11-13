﻿<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>看板登录</title>
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/scanboardLogin.css" />
<link rel="stylesheet" type="text/css" href="css/animsition.css" />
</head>
<body>
	<div class="wp animsition">
		<div class="boardLogin">
			<a href="#" class="logo">
				<img src="images/loginLogo.png">
			</a>
			<form>
				<div class="inpGroup">
					<span class="loginIco1"></span>
					<input type="text" name="userName" placeholder="请输入您的用户名">
				</div>
				<div class="prompt">
					<p class="error">用户名错误或不存在</p>
				</div>
				
				<div class="inpGroup">
					<span class="loginIco2"></span>
					<input type="password" name="password" placeholder="请输入您的密码">
				</div>
				<div class="prompt">
					<p class="success">输入正确</p>
				</div>
				
				<!-- <button class="submit">登录</button> -->
				<a href="scanboard.html" class="animsition-link submit">登录</a>
			</form>
		</div>
	</div>
	<div id="particles-js"></div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.animsition.js"></script>
<script src="js/particles.min.js"></script>
<script src="js/app.js"></script>
<script type="text/javascript">
	$(".animsition").animsition({
	    inClass               :   'fade-in',
	    outClass              :   'fade-out',
	    inDuration            :    800,
	    outDuration           :    1000,
	    linkElement           :   '.animsition-link',

	    loading               :    false,
	    loadingParentElement  :   'body',
	    loadingClass          :   'animsition-loading',
	    unSupportCss          : [ 'animation-duration',
	                              '-webkit-animation-duration',
	                              '-o-animation-duration'
	                            ],
	

	    overlay               :   false,
	    
	    overlayClass          :   'animsition-overlay-slide',
	    overlayParentElement  :   'body'
  	});
</script>
</html>

<%-- 
    Document   : login
    Created on : 2016-8-12, 15:24:52
    Author     : hatanococoro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="pages/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="pages/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="pages/lib/icheck/icheck.css" />
<!--[if lt IE 9]>
<link href="static/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户登录</title>
<meta name="keywords" content="登陆">
</head>
<body>
<section class="container">
	<div class="line"></div>
	<form action="" method="post" class="form form-horizontal responsive">
		<div class="row cl">
		<label class="form-label col-xs-2">账户：</label>
		<div class="formControls col-xs-5">
                   <input type="text" class="input-text" autocomplete="off" value="admin" placeholder="用户名" name="username" id="username" nullmsg="账户不能为空">
		</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-2">密码：</label>
		<div class="formControls col-xs-5">
                    <input type="password" class="input-text" autocomplete="off" value="admin" placeholder="密码" name="password" id="password" nullmsg="请输入密码！" >          
		</div>
                <p class="c-error text-l">${message}</p>
		</div>
                
                <div class="row cl">  
                   <label class="form-label col-xs-2" >验证码:</label> 
                   <div class="formControls col-xs-5">
                   <input class="input-text" name="kaptcha" type="text" id="kaptcha" maxlength="4">
                   <img src="<%=basePath%>/login/captcha-image" id="kaptchaImage"  style="margin-bottom: -3px"/>       
                   <a onclick="changeCode()">看不清?换一张</a>  
		   </div>
                   <p class="c-error text-l">${message_k}</p>
                </div>
        <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input type="submit" class="btn btn-success radius size-L" value="登陆">
          <input type="reset" class="btn btn-default radius size-L" value="取消">
        </div>
      </div>
	</form>
</section>

<script type="text/javascript" src="pages/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="pages/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="pages/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="pages/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="pages/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="pages/lib/bootstrap-Switch/bootstrapSwitch.js"></script> 
<script type="text/javascript" src="pages/lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="pages/lib/Validform/5.3.2/passwordStrength-min.js"></script>
<script type="text/javascript" src="pages/static/h-ui/js/H-ui.js"></script>
<script>
//var navigation = responsiveNav("Hui-navbar", {customToggle: ".nav-toggle"});


    $(function(){  //生成验证码         
        $('#kaptchaImage').click(function () {  
        $(this).hide().attr('src', '<%=basePath%>/login/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); });      
    });   
  		  
    function changeCode() {  //刷新
        $('#kaptchaImage').hide().attr('src', '<%=basePath%>/login/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();  
        event.cancelBubble=true;  
    } 
    
    window.onbeforeunload = function(){  
    //关闭窗口时自动退出  
    if(event.clientX>360&&event.clientY<0||event.altKey){     
        alert(parent.document.location);  
    }  
  }; 
</script>
</body>
</html>
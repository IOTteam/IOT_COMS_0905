<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/static/h-ui.admin/skin/default/skin.css" id="skin" />

<!--[if lt IE 9]>
<link href="static/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>客户信息</title>
<meta name="keywords" content="表格">
</head>
<body>
            
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs">客户订单管理系统</a>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li>欢迎登陆,</li>
					<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">${user.userName}</a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="<%=basePath%>login/userInfo">个人信息</a></li>
							<li><a href="<%=basePath%>login/editPassword">修改密码</a></li>
							<li><a href="<%=basePath%>/login">退出</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</header>
            
<aside class="Hui-aside">
	<div class="menu_dropdown">
	    <ul>
                <li>
                    <a href="<%=basePath%>CustInfo/CustQuery">客户信息</a>
                </li>
            </ul>
            <ul>
                <li>
                    <a href="<%=basePath%>orderList/queryList">订单列表</a>
                </li>
            </ul>
              <ul>
                <li>
                    <a href="<%=basePath%>productMaster/loadProductMaster">商品信息</a>
                </li>
            </ul>
              <ul>
                <li>
                    <a href="<%=basePath%>CustPrice/queryCustPrice">客户产品单价</a>
                </li>
            </ul>
	</div>
</aside>
    
    <div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
      <section class="Hui-article-box">
	<div id="iframe_box" class="Hui-article">

	</div>
      </section>

<script type="text/javascript" src="<%=basePath%>pages/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script>

</script>
</body>
</html>

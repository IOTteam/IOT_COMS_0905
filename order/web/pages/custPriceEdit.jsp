<%-- 
    Document   : cusPriceEdit
    Created on : 2016-8-29, 14:01:11
    Author     : hatanococoro
--%>

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
<!--[if lt IE 9]>
<link href="static/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>新增客户信息</title>       
</head>
<body>
<section class="container">
        
        <form action="CusPriceEdit" method="post">
		<h3 align="center">客户产品单价表修改</h3>
 
                客户名字：<input type="text" name="customerPriceId"  class="input-text radius" hidden="true" readonly="true" value="${CustomerPriceId.customerPriceId}"></input>
                客户名字：<input type="text" name="customerName"  class="input-text radius" readonly="true" value="${CustomerPriceId.customerMasterId.customerName}" style="width:100px"></input>
                产品名字：<input type="text" name="productName"  class="input-text radius" readonly="true" value="${CustomerPriceId.productMasterId.productName}" style="width:100px"></input>          
                数量起量：<input type="text" name="rangeMin"  class="input-text radius" readonly="true" value="${CustomerPriceId.ranges}" style="width:100px"></input>
                数量终量：<input type="text" name="rangeMax"  class="input-text radius" readonly="true" value="${rangeMax}" style="width:100px"></input>
                目前价格：<input type="text" name="presentPrice"  class="input-text radius" readonly="true" value="${CustomerPriceId.rangePrice}" style="width:100px"></input>
                修改价格：<input type="text"  class="input-text radius" name="editPrice" style="width:100px"></input>
                <input type="submit" class="btn btn-primary radius"  value="提交修改"></input>
                
        </form>
</body>
</html>

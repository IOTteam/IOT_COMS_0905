<%-- 
    Document   : CustInfo
    Created on : 2016-8-10, 15:04:43
    Author     : lxp
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/static/h-ui.admin/skin/default/skin.css" id="skin" />
<!--[if lt IE 9]>
<link href="static/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>订单头档</title>
<meta name="keywords" content="订单头档">
</head>

<body>
    
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a href="<%=basePath%>index" class="logo navbar-logo f-l mr-10 hidden-xs">客户订单管理系统</a>
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
        <div class="page-container">
            <form action="orderQuery" method="post">
		    <p><h3 align="center">订单信息列表</h3>
                        订单编码：<input type="text" name="customerId" class="input-text radius" style="width:100px" />
                       下单日期：<input type="text" name="firstOrderDate" class="input-text radius" style="width:100px" />
                       下单日期：<input type="text" name="lastOrderDate" class="input-text radius" style="width:100px" />
                       <input class="btn btn-primary radius"  type="submit" value="查询"/>
                       <input class="btn btn-primary radius" type="button" value="新增" onclick="add()"/>
                    </p>
            </form>
        <table  class="table table-border table-bordered table-striped" >
		<tr>
		<th style="width:100px">订单编码</th> <th style="width:100px">下单日期</th>  <th style="width:100px">下单客户</th> <th style="width:100px">操作</th> 
		</tr>
                <c:forEach items="${orderList}" var ="order">
                    <tr class="orderInfo">
                    <td style="width:100px"><c:out value="${order.orderId}"></c:out></td>
                    <td style="width:100px"><c:out value="${order.orderDate}"></c:out></td> 
                    <td style="width:100px"><c:out value="${order.customerName}"></c:out></td>
                    <td>
                     <input class="btn btn-primary radius"  type="button" value="详细"/>                   
                    <input class="btn btn-primary radius"  type="button" value="删除"/>
                    </td>
                    
                </tr>
                 </c:forEach> 
        </table>
	</div>
    <div class="footer">IOT TEAM</div>
   </section>
    

<script type="text/javascript" src="<%=basePath%>pages/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/bootstrap-Switch/bootstrapSwitch.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/Validform/5.3.2/passwordStrength-min.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/static/h-ui/js/H-ui.js"></script>
<script>
    
    $(function (){
        
        var session = "<%=session.getAttribute("user")%>";
        console.dir(session);
        if(session === null){
             window.location = "<%=basePath%>login";
        }
        
    });
    
    function add(){
                window.location = "<%=basePath%>orderList/orderAdd";

            }
      
      $(function (){
          
        $(".orderInfo").each(function (){
            
            var tem = $(this).children().eq(3);
            var button = tem.children();
            console.dir("真是够了");

            button.bind("click",function(){ 
               var val = button.parent().parent().children("td").get(0).innerHTML;
               console.dir(val);
               if($(this)[0].value === "详细"){
                   window.location = "<%=basePath%>orderList/detailQuery?orderId="+val+"";
               }
               
               if($(this)[0].value === "删除"){
                   window.location = "<%=basePath%>orderList/orderDelete?orderId="+val+"";
               }
            });
            
      });  
    })
    
</script>
</body>
</html>
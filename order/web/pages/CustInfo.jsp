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
<title>客户信息</title>
        
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
							<li><a data-toggle="modal" href="#userInfo">个人信息</a></li>
                                                        <li><a data-toggle="modal" href="#passwordEdit">修改密码</a></li>
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
            <form action="CustQuery" method="post">
                <p><h3 align="center">客户信息列表</h3>
                    客户编码：<input type="text" name="customerId" class="input-text radius" style="width:100px" />
                    客户名称：<input type="text" name="customerName" class="input-text radius" style="width:100px" />
                    <input class="btn btn-primary radius"  type="submit" value="查询"/>
                    <input class="btn btn-primary radius" type="button" value="新增" onclick="add()"/>
                </p>
            </form>


        <table  class="table table-border table-bordered table-striped" >
            <tr>
                <th style="width:100px">客户编号</th><th style="width:100px">客户姓名</th> <th style="width:100px">客户邮箱</th>  <th style="width:100px">客户电话</th>   <th style="width:100px">操作</th> 
            </tr>
            <c:forEach items="${cmList}" var ="customer">
                <tr class="custInfo">
                     <td style="width:100px"><c:out value="${customer.customerId}"></c:out></td>
                    <td style="width:100px"><c:out value="${customer.customerName}"></c:out></td>
                    <td style="width:100px"><c:out value="${customer.customerMail}"></c:out></td> 
                    <td style="width:100px"><c:out value="${customer.customerPhone}"></c:out></td>
                    <td>
                    <input class="btn btn-primary radius" type="submit" value="修改"/>
                    <input class="btn btn-primary radius" type="submit" value="删除"/>
                    </td>
                    </tr>
            </c:forEach> 
        </table>
       </div>
      <div class="footer">IOT TEAM</div>
      </section>
    
    <div id="userInfo" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 <div class="modal-header">
    <h3 id="myModalLabel">用户信息</h3><a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
 </div>
  <div class="modal-body">
    	<form action="" class="form form-horizontal responsive">
            	<div class="row cl">
		<label class="form-label col-xs-3">用户编号：</label>
		<div class="formControls col-xs-5">
                   <input type="text" class="input-text" autocomplete="off" value="${user.userId}" name="username" />
		</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-3">用户姓名：</label>
		<div class="formControls col-xs-5">
                    <input type="text" class="input-text" autocomplete="off"  value="${user.userName}" name="password" />
		</div>
		</div>
        </form>
 </div>
  <div class="modal-footer">
 <button class="btn" data-dismiss="modal" aria-hidden="true">确定</button>
 </div>
</div>
                
<div id="passwordEdit" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 <div class="modal-header">
    <h3 id="myModalLabel">修改密码</h3><a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
 </div>
  <div class="modal-body">
    	<form action="login/editPassword" method="post" class="form form-horizontal responsive">
            	<div class="row cl">
		<label class="form-label col-xs-3">原密码：</label>
		<div class="formControls col-xs-5">
                   <input type="password" class="input-text" autocomplete="off" name="passwordOld" />
		</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-3">新密码：</label>
		<div class="formControls col-xs-5">
                    <input type="password" class="input-text" autocomplete="off" name="passwordNew" />
		</div>
		</div>
                <div class="row cl">
		<label class="form-label col-xs-3">确认新密码：</label>
		<div class="formControls col-xs-5">
                    <input type="password" class="input-text" autocomplete="off"  name="passwordConfirm" />
                     <p class="c-error text-l">${message}</p>
		</div>
		</div>
                
                <div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
		    <input class="btn btn-primary radius" type="submit" value="保存" >
                    <input type="button" class="btn btn-primary radius" value="取消" data-dismiss="modal" aria-hidden="true">
		</div>
                
        </form>
 </div>
</div>

<script type="text/javascript" src="<%=basePath%>pages/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/lib/bootstrap-modal/2.2.4/bootstrap-modalmanager.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/lib/bootstrap-modal/2.2.4/bootstrap-modal.js"></script>
<script>
    
   $(function (){
        
        var session = "<%=session.getAttribute("user")%>";
        if(session === null){
             window.location = "<%=basePath%>login";
        }
        
    });
      
    function add() {
                window.location = "<%=basePath%>CustInfo/CustAdd";

    }

     $(function (){
        $(".custInfo").each(function (){
            
            var tem = $(this).children().eq(4);
            var button = tem.children();

            button.bind("click",function(){ 
               var val = button.parent().parent().children("td").get(0).innerHTML;
               
               if($(this)[0].value === "修改"){
                   window.location = "<%=basePath%>CustInfo/CustEdit?customerId="+val+"";
               }
               
               if($(this)[0].value === "删除"){
                   window.location = "<%=basePath%>CustInfo/CustDel?customerId="+val+"";
               }
            });
            
      });  
    })
            
         
</script>
</body>
</html>
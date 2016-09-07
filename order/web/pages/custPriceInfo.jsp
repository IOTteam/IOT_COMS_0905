<%-- 
    Document   : custPriceInfo
    Created on : 2016-8-26, 13:44:02
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/static/h-ui.admin/skin/default/skin.css" id="skin" />
<!--[if lt IE 9]>
<link href="static/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>客户产品单价信息</title>
        
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
            <form action="CustQuery" method="post">
            <p>
                <h3 align="center">客户产品单价信息列表</h3>
                   客户名称：<input type="text" name="customerName" value="${queryCondition.CustomerName}" class="input-text radius" style="width:100px" />
                   产品名称：<input type="text" name="productName" value="${queryCondition.ProductName}" class="input-text radius" style="width:100px" />
                   价格起价：<input type="text" name="priceMin" value="${queryCondition.PriceMin}" class="input-text radius" style="width:100px" />
                   价格终价：<input type="text" name="priceMax" value="${queryCondition.PriceMax}" class="input-text radius" style="width:100px" />
                   数量起量：<input type="text" name="rangesMin" value="${queryCondition.RangesMin}" class="input-text radius" style="width:100px" />
                   数量终量：<input type="text" name="rangesMax" value="${queryCondition.RangesMax}" class="input-text radius" style="width:100px" />
                    <input class="btn btn-primary radius"  type="submit" value="查询"/>
                </p>
    </form>
    <table  class="table table-border table-bordered table-bg" id="CusPriceForm" >
            <tr>
                <th style="width:100px">客户编号</th>  <th style="width:100px">客户姓名</th> 
                <th style="width:100px">产品编号</th>  <th style="width:100px">产品名称</th>   
                <th style="width:100px">数量级</th>    <th style="width:100px">价格</th>
                <th style="width:100px">修改</th> 
            </tr>
            <c:forEach items="${custPriceList}" var ="custPrice">
                <tr style="height: 50px" class="CusPrice" >
                    <td hidden="true" style="width:100px"><c:out value="${custPrice.customerPriceId}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice.customerMasterId.customerId}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice.customerMasterId.customerName}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice.productMasterId.productId}"></c:out></td> 
                    <td style="width:100px"><c:out value="${custPrice.productMasterId.productName}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice.ranges}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice.rangePrice}"></c:out></td>
                    <td>
                    <input class="btn btn-primary radius" type="button" name="edit" value="修改" style="display: inline-block;" />
                    </td>
                    </tr>
            </c:forEach> 
        </table>
    
    <div align="right">
        <p> <input class="btn btn-primary radius" type="button" value="上一页" onclick="pre()"/>
            <input class="input-text radius" type="text" id="pageNo" value="${pageNo}" readonly="true" style="width:30px" />
            /
            <input class="input-text radius" type="text" id="totalPage" value="${totalPage}" readonly="true" style="width:30px" />
         <input class="btn btn-primary radius" type="button" value="下一页" onclick="next()"/></p>
    </div>
    </div>
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
        if(session === null){
             window.location = "<%=basePath%>login";
        }
        
    });
    
    $(function (){
        $(".CusPrice").each(function (){
            
            var tem = $(this).children().eq(7);
            var button = tem.children();

            button.bind("click",function(){ 
               var val = button.parent().parent().children("td").get(0).innerHTML;
               window.location = "<%=basePath%>CustPrice/CustPriceEdit?customerPriceId="+val+"";
            });
      });  
    })
    
    
    function next(){
        
        var pageNo = parseInt($("#pageNo")[0].value);
        var totalPage = parseInt($("#totalPage")[0].value);
        
        if(pageNo >= totalPage){
            return false;
        }else{
            $("#pageNo")[0].value = pageNo + 1; 
        }
        
        $.ajax({  
                url : "queryNext",  
                type : "get",  
                datatype:"json",  
                data : {pageNo:pageNo},  
                success : function(data, stats) {  
                    if (stats === "success") {  
                       
                        var i = -1;
                        $("#CusPriceForm").find("tr").each(function(){

                            var j = 0;
                            $(this).find("td").each(function(){

                                if($(this)[0].innerHTML.length === 148){                                   
                                    $(this).children().each(function (){
                                           $(this).show();
                                       });
                                    
                                    if(data[i].toString() === ",,,,,,"){
                                       $(this).children().each(function (){
                                           $(this).hide();
                                       });
                                    }
                                    return false;
                                }else{
                                    $(this)[0].innerHTML = data[i][j];
                                     j++;
                                } 
                            });
                            i++;
                        });
                    }  
                },  
                error : function(data) {  
                    alert("失败");  
                }  
            });
        }
            
    function pre(){
        
        var pageNo = parseInt($("#pageNo")[0].value);
        if(pageNo <= 1){
            return false;
        }else{
            $("#pageNo")[0].value = pageNo - 1; 
        }
        
         $.ajax({  
                url : "queryPre",  
                type : "get",  
                datatype:"json",  
                data : {pageNo:pageNo},  
                success : function(data, stats) {  
                    if (stats === "success") {  
                        
                        var i = -1;
                        $("#CusPriceForm").find("tr").each(function(){

                            var j = 0;
                            $(this).find("td").each(function(){
                                
                                if($(this)[0].innerHTML.length === 148){
                                    
                                    $(this).children().each(function (){
                                        console.dir($(this));
                                        $(this).show();
                                       });
                                    
                                    if(data[i].toString() === ",,,,,,"){
                                       $(this).children().each(function (){
                                           $(this).hide();
                                       });
                                    }
                                    
                                    return false;
                                }else if($(this)[0].innerHTML.length === 140){
                                        $(this).children().each(function (){
                                        $(this).show();
                                       });
                                }else{
                                    $(this)[0].innerHTML = data[i][j];
                                     j++;
                                }
                            });
                            i++;
                        });
                    }  
                },  
                error : function(data) {  
                    alert("失败");  
                }  
            });
    }

    

         
</script>
</body>
</html>
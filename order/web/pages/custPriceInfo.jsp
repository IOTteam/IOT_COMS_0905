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
<section class="container">

        <div class="">
            <form action="CustQuery" method="post">
                <h3 align="center">客户产品单价信息列表</h3>
                <p>客户编码：<input type="text" name="customerId" class="input-text radius" style="width:100px" />
                   客户名称：<input type="text" name="customerName" class="input-text radius" style="width:100px" />
                   产品编码：<input type="text" name="ProductId" class="input-text radius" style="width:100px" />
                   产品名称：<input type="text" name="Product" class="input-text radius" style="width:100px" />

                    <input class="btn btn-primary radius"  type="submit" value="查询"/>
                </p>
            </form>
        </div>
   
    <table  class="table table-border table-bordered table-striped" >
            <tr>
                <th style="width:100px">客户编号</th>  <th style="width:100px">客户姓名</th> 
                <th style="width:100px">产品编号</th>  <th style="width:100px">产品名称</th>   
                <th style="width:100px">数量级</th>    <th style="width:100px">价格</th>
                <th style="width:100px">修改</th> 
            </tr>
            <c:forEach items="${custPriceList}" var ="custPrice">
                <tr>
                    <td style="width:100px"><c:out value="${custPrice.customerMasterId.customerId}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice.customerMasterId.customerName}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice.productMasterId.productId}"></c:out></td> 
                    <td style="width:100px"><c:out value="${custPrice.productMasterId.productName}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice.ranges}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice.rangePrice}"></c:out></td>
                    <td>
                    <input class="btn btn-primary radius" type="submit" value="修改" onclick="update()"/>
                    </td>
                    </tr>
            </c:forEach> 
        </table>
    
    <div align="right">
        <p> <input class="btn btn-primary radius" type="button" value="上一页" onclick="pre()"/>
            ${index}
         <input class="btn btn-primary radius" type="button" value="下一页" onclick="next()"/></p>
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
    
        $("tr").click(function(){


        var str = [];
        var num = 0;
        $(this).find("td").each(function(i){

           var txt = $(this).text();
           str[num] = txt;
           num++;
           });

        //window.location = "<%=basePath%>CustPrice/CustEdit?customerId="+str[0]+"";
    });
    
    function next(){
        
        window.location = "<%=basePath%>CustPrice/queryNext";

    }
    
    function pre(){
        
        window.location = "<%=basePath%>CustPrice/queryPre";

    }
         
</script>
</body>
</html>
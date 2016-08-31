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
                <p>
                   客户名称：<input type="text" name="customerName" value="${queryCondition.CustomerName}" class="input-text radius" style="width:100px" />
                   产品名称：<input type="text" name="productName" value="${queryCondition.ProductName}" class="input-text radius" style="width:100px" />
                   价格起价：<input type="text" name="priceMin" value="${queryCondition.PriceMin}" class="input-text radius" style="width:100px" />
                   价格终价：<input type="text" name="priceMax" value="${queryCondition.PriceMax}" class="input-text radius" style="width:100px" />
                   数量起量：<input type="text" name="rangesMin" value="${queryCondition.RangesMin}" class="input-text radius" style="width:100px" />
                   数量终量：<input type="text" name="rangesMax" value="${queryCondition.RangesMax}" class="input-text radius" style="width:100px" />
                   

                    <input class="btn btn-primary radius"  type="submit" value="查询"/>
                </p>
            </form>
        </div>
   
    <table  class="table table-border table-bordered table-striped" id="CusPriceForm" >
            <tr>
                <th style="width:100px">客户编号</th>  <th style="width:100px">客户姓名</th> 
                <th style="width:100px">产品编号</th>  <th style="width:100px">产品名称</th>   
                <th style="width:100px">数量级</th>    <th style="width:100px">价格</th>
                <th style="width:100px">修改</th> 
            </tr>
            <c:forEach items="${custPriceList}" var ="custPrice">
                <tr style="height: 50px" >
                    <td hidden="true" style="width:100px"><c:out value="${custPrice[0]}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice[1]}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice[2]}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice[3]}"></c:out></td> 
                    <td style="width:100px"><c:out value="${custPrice[4]}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice[5]}"></c:out></td>
                    <td style="width:100px"><c:out value="${custPrice[6]}"></c:out></td>
                    <td>
                    <input class="btn btn-primary radius" type="button" name="edit" value="修改" onclick="update()" style="display: inline-block;" />
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

        //window.location = "<%=basePath%>CustPrice/CustPriceEdit?customerPriceId="+str[0]+"";
    });
    
    function next(){
        
        if($("#pageNo")[0].value >= $("#totalPage")[0].value){
            return false;
        }else{
            var pageNo_old = parseInt($("#pageNo")[0].value);
            $("#pageNo")[0].value = pageNo_old + 1; 
        }
        
        $.ajax({  
                url : "queryNext",  
                type : "get",  
                datatype:"json",  
                data : {pageNo:pageNo_old},  
                success : function(data, stats) {  
                    if (stats === "success") {  
                        
                        var i = -1;
                        $("#CusPriceForm").find("tr").each(function(){

                            var j = 0;
                            $(this).find("td").each(function(){
                                
                                console.dir($(this)[0].innerHTML.length);
                                if($(this)[0].innerHTML.length === 167){
                                    
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
        
        if($("#pageNo")[0].value <= 1){
            return false;
        }else{
            var pageNo_old = parseInt($("#pageNo")[0].value);
            $("#pageNo")[0].value = pageNo_old - 1; 
        }
        
         $.ajax({  
                url : "queryPre",  
                type : "get",  
                datatype:"json",  
                data : {pageNo:pageNo_old},  
                success : function(data, stats) {  
                    if (stats === "success") {  
                        
                        var i = -1;
                        $("#CusPriceForm").find("tr").each(function(){

                            var j = 0;
                            $(this).find("td").each(function(){
                                
                                if($(this)[0].innerHTML.length === 167){
                                    
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
                                }else if($(this)[0].innerHTML.length === 159){
                                        $(this).children().each(function (){
                                        console.dir($(this));
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
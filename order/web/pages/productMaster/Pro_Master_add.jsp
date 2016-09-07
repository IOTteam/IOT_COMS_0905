<%-- 
    Document   : Pro_Master_add
    Created on : 2016-8-18, 15:17:24
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>pages/static/h-ui/css/H-ui.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>pages/lib/Hui-iconfont/1.0.7/iconfont.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>pages/lib/icheck/icheck.css" />
        <title>产品管理</title>
    </head>
    <body>
    <section class="container">
        <form action="addProductMaster" method="post">
		<h3 align="center">产品新增管理</h3>
                <div id="productTable">
                    产品编码：<input type="text" name="productId" id="productId" value="${productMaster.productId}" readonly="readonly" style="height:20px;"/>
                    产品名称：<input type="text" name="productName" id="productName" style="height:20px;"/>
                    产品规格：<input type="text" name="productSpec" id="productSpec" style="height:20px;"/>                  
                    产品规格：<input type="text" name="productPrice" id="productPrice" style="height:20px;"/>
                    优惠选择：<select class="select" style="width:150px;" size="1" name="status" id="status" placeholder="此产品是否优惠">
                                <option value="2" selected>否</option>
                                    <option value="1">是</option> 
                                </select>
                    <input type="hidden" name="productMasterId" id="productMasterId" value="${productMaster.productMasterId}" />
                </div>
                <div class="formControls col-xs-11">
                    <h1><small>新增客户产品单价信息：</small></h1>
                </div>
                <div class="formControls col-xs-2">
                   <span class="select-box">
                        <select class="select" size="1" style="width:150px;" name="customerMasterId" id="customerMasterId" placeholder="输入客户编号">
                        <option value="" selected>请选择客户编号</option>
                            <c:forEach items="${customerMasterList}" var = "customerMaster">
                                <option value="${customerMaster.customerMasterId}">${customerMaster.customerMasterId}</option>
                            </c:forEach>
                        </select>
                    </span>
                </div>
                <div class="formControls col-xs-2" id="inpt">
                    <input readonly="readonly"  name="proname" id="proname" style="width:150px;height:28px;" />
                </div>
                <div class="formControls col-xs-2">
                    <span class="select-box">
                        <select class="select" size="1" name="ranges" id="ranges" placeholder="输入产品数量" >
                        <option value="" selected>产品数量</option>
                            <option value="6000">1</option> 
                            <option value="1000">1000</option>
                            <option value="2000">2000</option>
                            <option value="4000">4000</option>  
                        </select>
                    </span> 
                    </div>
                <div class="formControls col-xs-2">
                    <span class="select-box">
                        <select class="select" size="1" name="rangePrice" id="rangePrice" placeholder="输入折扣信息">
                        <option value="" selected>请选择折扣</option>
                            <option value="1">1</option>
                            <option value="0.9">0.9</option>
                            <option value="0.8">0.8</option>
                            <option value="0.7">0.7</option>
                            <option value="0.6">0.6</option>
                            <option value="0.5">0.5</option>
                            <option value="0.4">0.4</option>
                            <option value="0.3">0.3</option>  
                            <option value="0.2">0.2</option>
                            <option value="0.1">0.1</option> 
                        </select>
                    </span>
                    </div>
                <div class="formControls col-xs-1">
                    <input type="button" value="+" class="btn btn-primary radius" onclick="change()" />
                </div>
                <div class="formControls col-xs-8">
                    <table id="priceTable" hidden="true" class="table table-border table-bordered table-striped">
                        <tr><td>客户姓名</td><td>产品编号</td><td>产品数量</td><td>优惠额度</td></tr>
                    </table>
                </div>
                <div class="formControls col-xs-5">
                    <input type="submit" value="新增" class="btn btn-primary radius"/>
                </div>
            </form>
        <script type="text/javascript" src="<%=basePath%>pages/lib/jquery/jquery-3.1.0.min.js"></script> 
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
    productName = document.getElementById("productTable");
    productMaster = productName.getElementsByTagName("input");
    productMaster[1].onblur=function(){
    customerPrice = document.getElementById("inpt").getElementsByTagName("input");
    customerPrice[0].value = productMaster[1].value; 
    };
    
    function change(){
        $info1 = $("select#status option:selected").val();
        if($info1==1){addPreferential();}
        else{alert("您不能进行此项操作");}
    }
    
    function addPreferential(){

        var customerMasterId =  $("#customerMasterId")[0].value;
        var proname = $("#proname")[0].value;
        var ranges =  $("#ranges")[0].value;
        var rangePrice = $("#rangePrice")[0].value; 
        
        $.ajax({  
                url : "setCustomerPrice",  
                type : "Post",  
                datatype:"json",  
                data : {customerMasterId:""+ customerMasterId +"",ranges:""+ ranges +"", rangePrice:""+ rangePrice +"",
                    proname:"" + proname + ""},  
                success : function(data, stats) {  
                    if (stats === "success") {  
                     
//                var i = data.length - 1;
//		info1 = data[i].customerName;
//		info2 = data[i].proname;
//		info3 = data[i].ranges;
//		info4 = data[i].rangePrice;
		
		//table中新建行列
                $("#priceTable");
		tb = document.getElementById("priceTable");
                tb.hidden = false;
		new_row = tb.insertRow();
		new_cell1 = new_row.insertCell();
		new_cell2 = new_row.insertCell();
		new_cell3 = new_row.insertCell();
		new_cell4 = new_row.insertCell();
		//新建行列中插入信息
		new_cell1.innerHTML = customerMasterId;
		new_cell2.innerHTML = proname;
		new_cell3.innerHTML = ranges;
		new_cell4.innerHTML = rangePrice;
                     
                    }  
                },  
                error : function(data) {  
                    alert("你为什么失败了");  
                }  
            });
        
    }
//    function addPreferential(){
//		//table中新建行列
//                $("#priceTable");
//		tb = document.getElementById("priceTable");
//                tb.hidden = false;
//                save = document.getElementById("inpt");
//                inpt = save.getElementsByTagName("input");
//                
//                info1 =inpt[0].value;
//                info2 = inpt[1].value;
//                info3 = inpt[2].value;
//                info4 = inpt[3].value;
//        
//		new_row = tb.insertRow();
//		new_cell1 = new_row.insertCell();
//		new_cell2 = new_row.insertCell();
//		new_cell3 = new_row.insertCell();
//		new_cell4 = new_row.insertCell();
//		//新建行列中插入信息
//		new_cell1.innerHTML = info1 ;
//		new_cell2.innerHTML = info2;
//		new_cell3.innerHTML = info3;
//		new_cell4.innerHTML = info4;
//        }   
</script>
</section>
    </body>
</html>

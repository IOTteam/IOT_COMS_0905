<%-- 
    Document   : Pro_Master
    Created on : 2016-8-18, 11:37:11
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<!--        <script type="text/javascript">
            function add(){
                window.location = "<%=basePath%>productMaster/addProductMasterInit";
            }
            function update(){
                window.location = "<%=basePath%>productMaster/modifyProductMasterInit";
            }
        </script>-->
    </head>
    <body>
        <form action="loadProductMaster" method="post">
        <div class="">
            <!--<form action="loadProductMasterInit" method="post">-->
		<h3 align="center">产品信息列表</h3>
		    <p>产品编码：<input type="text" name="productId" id="productId"/>
                       产品名称：<input type="text" name="productName" id="productName"/>
                       产品规格：<input type="text" name="productSpec" id="productSpec"/>
                       标准售价：<input type="text" name="priceMin" id="priceMin"/> - <input type="text" name="priceMax" id="priceMax"/>
                       <input type="submit" value="查询"/>
                    </p>
            <!--</form>-->
        </div>

        <table id="d1" border="1px" cellspacing="0px" class="" style="height:300px; width:100%; border-collapse:collapse" >
		<tr>
		    <td style="width:100px">产品编号</td> 
                    <td style="width:100px">产品名称</td>  
                    <td style="width:100px">产品规格</td> 
                    <td style="width:100px">标准售价</td> 
                    <td style="width:50px">操作</td> 
		</tr>
                <c:forEach items="${productMasterList}" var ="productMaster">
                    <!--<form action="modifyProductMasterInit" method="get">-->
                <tr>
                    <td style="width:100px">${productMaster.productId}</td>
                    <td style="width:100px">${productMaster.productName}</td> 
                    <td style="width:100px">${productMaster.productSpec}</td>
                    <td style="width:100px">${productMaster.productPrice}</td>
                    <td style="width:50px">
                        <a href="<%=basePath%>productMaster/modifyProductMasterInit?productId=${productMaster.productId}">修改</a>
                        <a href="<%=basePath%>productMaster/removeProductMaster?productId=${productMaster.productId}">删除</a>
                        <!--<input type="submit" value="修改" onclick="update()"/>-->
                        <!--<input type="submit" value="删除" onclick="delete()"/>-->
                    </td>
                </tr>
                <!--</form>-->
                </c:forEach>
                <div align="right">
                    <a href="<%=basePath%>productMaster/addProductMasterInit">增加</a>
                    <!--<input type="submit" value="新增" onclick="add()"/>-->
                </div>
        </table>
        </form>
    </body>
</html>

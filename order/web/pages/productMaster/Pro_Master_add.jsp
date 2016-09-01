<%-- 
    Document   : Pro_Master_add
    Created on : 2016-8-18, 15:17:24
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="addProductMaster" method="post">
		<h3 align="center">产品信息列表</h3>
                <table  border="1px" cellspacing="0px" class="" style="height:25px; width:100%; border-collapse:collapse">
                    <tr>
                        <td>
                            产品编码：<input type="text" name="productId" id="productId" value="${productMaster.productId}" readonly="readonly"/>
                        </td>
                        <td>
                            产品名称：<input type="text" name="productName" id="productName"/>
                        </td>
                        <td>
                            产品规格：<input type="text" name="productSpec" id="productSpec"/>
                        </td>
                        <td>                        
                            标准售价：<input type="text" name="productPrice" id="productPrice"/>
                        </td>
                        <td><input type="hidden" name="productMasterId" id="productMasterId" value="${productMaster.productMasterId}" /></td>
                    </tr>
                    <c:forEach items="${customerMasterList}" var = "customerMaster">
                            <tr>
                                <td>
                                    <span>客户姓名</span>
                                    <input type="text" name="customerName" id="customerName"  value="${customerMaster.customerName}" readonly="readonly" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品数量</span>
                                    <input type="text" name="ranges" id="ranges"  value="1000" readonly="readonly" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品名称</span>
                                    <input type="text" name="" id="" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品价格</span>
                                    <input type="text" name="rangePrice" id="rangePrice" style="width:100px"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>客户姓名</span>
                                    <input type="text" name="customerName" id="customerName"  value="${customerMaster.customerName}" readonly="readonly" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品数量</span>
                                    <input type="text" name="ranges" id="ranges"  value="2000" readonly="readonly" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品名称</span>
                                    <input type="text" name="" id="" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品价格</span>
                                    <input type="text" name="rangePrice" id="rangePrice" style="width:100px"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>客户姓名</span>
                                    <input type="text" name="customerName" id="customerName"  value="${customerMaster.customerName}" readonly="readonly" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品数量</span>
                                    <input type="text" name="ranges" id="ranges"  value="3000" readonly="readonly" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品名称</span>
                                    <input type="text" name="" id="" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品价格</span>
                                    <input type="text" name="rangePrice" id="rangePrice" style="width:100px"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>客户姓名</span>
                                    <input type="text" name="customerName" id="customerName"  value="${customerMaster.customerName}" readonly="readonly" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品数量</span>
                                    <input type="text" name="ranges" id="ranges"  value="4000" readonly="readonly" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品名称</span>
                                    <input type="text" name="" id="" style="width:100px"/>
                                </td>
                                <td>
                                    <span>产品价格</span>
                                    <input type="text" name="rangePrice" id="rangePrice"  style="width:100px"/>
                                </td>
                            </tr>
                    </c:forEach>
                </table>
                <input type="submit" value="确定"/>
            </form>
    </body>
</html>

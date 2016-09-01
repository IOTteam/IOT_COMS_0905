<%-- 
    Document   : Pro_Master_modify
    Created on : 2016-8-19, 13:20:39
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
    </head>
    <body>
        <form action="modifyProductMaster" method="post" >
        <input type="hidden" name="pId" value="${productMaster.productId}">
		<h3 align="center">产品信息列表</h3>
                <table  border="1px" cellspacing="0px" class="" style="height:25px; width:100%; border-collapse:collapse">
                    <%--<c:forEach items="${pm}" var="productMaster">--%>
                    <tr>
                        <td>
                            <span>产品编码：</span>
                            <input type="text" name="productId" id="productId"  value="${productMaster.productId}" readonly="readonly" style="width:100px"/>
                        </td>
                        <td>
                            <span>产品名称：</span>
                            <input type="text" name="productName" id="productName" value="${productMaster.productName}" readonly="readonly" style="width:100px"/>
                        </td>
                        <td>
                            <span>产品规格：</span>
                            <input type="text" name="productSpec" id="productSpec" value="${productMaster.productSpec}" readonly="readonly" style="width:100px"/>
                        </td>
                        <td>
                            <span>标准售价：</span>
                            <textarea name="productPrice" id="productPrice" style="height: 16px;width:100px">${productMaster.productPrice }</textarea>
                        </td>
                        <td>
                            <input type="hidden" name="productMasterId" id="productMasterId" value="${productMaster.productMasterId}">
                            <input type="hidden" name="status" id="status" value="${productMaster.status}">
                            <input type="submit" value="确定"/>
                        </td>
                        <%--</c:forEach>--%>
                    </tr> 
                </table>
            </form>
    </body>
</html>

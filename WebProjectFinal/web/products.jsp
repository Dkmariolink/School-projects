

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="commonStyles.css">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Product Management</title>
    </head>
    <body>        
         <c:choose>
                <c:when test = "${user.firstName != null}">
                    <p>You are currently logged in as: <c:out value="${user.lastName}"/></p>
                    <a href="membership?action=logout">User Logout</a>
                </c:when>
                
                <c:otherwise>
                    <c:redirect url="/login.jsp"/>
                </c:otherwise>
        </c:choose>
                               
        <h2>Products</h2>

        <table>
            <tr>
                <th>Code</th>
                <th>Description</th>
                <th class="right">Price</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>

            <c:forEach var="element" items="${products}">
                <tr>
                    <td><c:out value="${element.code}"/></td>
                    <td><c:out value="${element.description}"/></td>
                    <td><c:out value="${element.priceCurrencyFormat}"/></td>
                    <td>
                        <a href="productManagement?action=updateProduct&productCode=${element.code}">Edit</a>
                    </td>
                    <td>
                        <a href="productManagement?action=deleteProduct&productCode=${element.code}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        
        <form action="productManagement" method="get">
            <input type="hidden" name="action" value="addProduct">
            <p><input type="submit" value="Add Product"></p>
        </form>
    </body>
</html>

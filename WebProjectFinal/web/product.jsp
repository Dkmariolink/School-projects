
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

        <h2>Product</h2>

        <form action="productManagement?action=addProduct" method="post">
            <p>   
                <label class="leftHeading">Code</label>
                <input type="text" name="code"  value="${sessionScope.productToUpdate.code}">
            </p>

            <p>
                <label class="leftHeading">Description</label>
                <textarea rows="4" cols="25" name="desc">${sessionScope.productToUpdate.description}</textarea>
            </p>

            <p>
                <label class="leftHeading">Price</label>
                <input type="text" name="price" value="${sessionScope.productToUpdate.price}">
            </p>

            <div class="rightButton">
                <input type="hidden" name="action" value="addProduct">          
                <input type="submit" value="Add Product">

            </div>
        </form>

        <form action="productManagement?action=displayProducts" method="get">
            <input type="hidden" name="action" value="displayProducts">
            <input type="submit" value ="View Products">
        </form>
    </body>
</html>

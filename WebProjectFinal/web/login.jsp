
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="commonStyles.css">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Login</title>
    </head>
    <body>
        
        <h3>Sorry, you have to be logged in to do that!</h3>
        <h2>Login</h2>
        <form action="membership?action=login" method="post">
            <p>    
                <label class="leftHeading">Username</label>
                <input type="text" name="username">
            </p>

            <p>
                <label class="leftHeading">Password</label>
                <input type="password" name="password">
            </p>

            <div class="rightButton">
                <input type="submit" value="Submit">
            </div>
        </form>

        <p>
            <a href="signup.jsp">New user? Click here to register</a>
        </p>

    </body>
</html>

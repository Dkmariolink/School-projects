

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="commonStyles.css">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Sign Up</title>
    </head>
    <body>
        <h2>Sign-up Form</h2>

        <form action="membership?action=signup" method="post">
            <p>    
                <label class="leftHeading">First Name</label>
                <input type="text" name="firstName">
            </p>

            <p>
                <label class="leftHeading">Last Name</label>
                <input type="text" name="lastName">
            </p>

            <p>
                <label class="leftHeading">Email</label>
                <input type="email" name="e-mail">
            </p>

            <p>
                <label class="leftHeading">Username</label>
                <input type="text" name="username">
            </p>

            <p>
                <label class="leftHeading">Password</label>
                <input type="password" name="password">
            </p>

            <div class="rightButton">
                <input type="submit" value="Sign Up">
            </div>
        </form>

    </body>
</html>

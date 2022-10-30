<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yungshun
  Date: 2022-12-15
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Custom Login Page</title>
</head>
<body>
    <h3>Custom Login Page</h3>

    <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">

        <!-- Check for login error -->
        <c:if test="${param.error != null}">
            <i class="failed">Sorry! You enter invalid username/password.</i>
        </c:if>

        <!-- Check for logout -->
        <c:if test="${param.logout != null}">
            <i>You have been logged out.</i>
        </c:if>

        <p>Username: <input type="text" name="username"></p>
        <p>Password: <input type="password" name="password"></p>

        <input type="submit" value="Login" />

    </form:form>
</body>
</html>

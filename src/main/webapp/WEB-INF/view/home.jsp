<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <title>Spring Security LMS</title>
</head>

<body>
<h2>Spring Security LMS Home Page</h2>
<hr>

Welcome to the Spring Security LMS home page!

<!-- Display username and role -->
<p>
    User: <security:authentication property="principal.username" />
    <br><br>
    Role(s): <security:authentication property="principal.authorities" />
</p>

<!-- Add a link to point to /admin -->
<security:authorize access="hasRole('ADMIN')">
<p>
    <a href="${pageContext.request.contextPath}/admin">Admin</a>
    (Only for Admin)
</p>
</security:authorize>

<!-- Add a link to point to /staff -->
<security:authorize access="hasRole('STAFF')">
<p>
    <a href="${pageContext.request.contextPath}/staff">Staff</a>
    (Only for Staff)
</p>
</security:authorize>

<!-- Add a logout button -->
<form:form action="${pageContext.request.contextPath}/logout" method="POST">

    <input type="submit" value="Logout" />

</form:form>

</body>

</html>
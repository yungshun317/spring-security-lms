<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>Spring Security LMS</title>
</head>

<body>
<h2>Spring Security LMS Home Page</h2>
<hr>

Welcome to the Spring Security LMS home page!

<!-- Add a logout button -->
<form:form action="${pageContext.request.contextPath}/logout" method="POST">

    <input type="submit" value="Logout" />

</form:form>

</body>

</html>
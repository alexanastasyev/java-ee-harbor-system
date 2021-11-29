<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign in page</title>
</head>
<body>
    <form name="LoginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login"/>
        <label>
            Login: <input name="login" type="text"/>
        </label>
        <br/>
        <label>
            Password: <input name="password" type="password"/>
        </label>
        <br/>
        <c:if test="${errorMessage != null}">
            <p style="color: red">${errorMessage}</p>
            <c:remove var="errorMessage" scope="session" />
        </c:if>
        <input type="submit" value="Sign In">
    </form>
</body>
</html>

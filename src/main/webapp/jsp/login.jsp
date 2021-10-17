<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <br/>
            ${errorLoginPassMessage}
        <br/>
            ${wrongAction}
        <br/>
            ${nullPage}
        <input type="submit" value="Sign In">
    </form>
</body>
</html>

<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome</h1>
    <hr/>
            ${user}, hello!
    <hr/>
    <a href="controller?command=logout">Logout</a>
</body>
</html>

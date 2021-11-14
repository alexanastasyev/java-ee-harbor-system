<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>
    <form name="CreateUserForm" method="POST" action="controller">
        <input type="hidden" name="command" value="create_user"/>
        <label>
            Login: <input name="login" type="text"/>
        </label>
        <br/>
        <label>
            Password: <input name="password" type="text"/>
        </label>
        <label>
            Role:
            <select name="roles">
                <c:forEach var="role" items="${roles}">
                    <option value="${role.getId()}">
                            ${role.getTitle()}
                    </option>
                </c:forEach>
            </select>
        </label>
        <br/>
        <input type="submit" value="Create">
    </form>
</body>
</html>

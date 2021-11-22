<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
    <form name="EditUserForm" method="POST" action="controller">
        <input type="hidden" name="command" value="edit_user"/>
        <input type="hidden" name="id" value="${user.getId()}"/>
        <label>
            Login: <input name="login" type="text" value="${user.getLogin()}"/>
        </label>
        <br/>
        <label>
            Password: <input name="password" type="text" value="${user.getPassword()}"/>
        </label>
        <label>
            Role:
            <select name="roles">
                <c:forEach var="role" items="${roles}">
                    <option value="${role.getId()}" <c:if test="${user.getRole().getTitle().equals(role.getTitle())}"> selected </c:if>>
                            ${role.getTitle()}
                    </option>
                </c:forEach>
            </select>
        </label>
        <label>
            Status:
            <select name="statuses">
                <c:forEach var="status" items="${statuses}">
                    <option value="${status.getId()}" <c:if test="${user.getStatus().getTitle().equals(status.getTitle())}"> selected</c:if>>
                            ${status.getTitle()}
                    </option>
                </c:forEach>
            </select>
        </label>
        <br/>
        <input type="submit" value="Edit">
    </form>
    <a href="controller?command=show_admin_page">Back</a>
</body>
</html>

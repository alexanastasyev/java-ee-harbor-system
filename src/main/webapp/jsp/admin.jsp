<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome, Admin</title>
</head>
<body>
    <h1>Welcome, Admin</h1>
    <hr/>
        <table>
            <tr>
                <th>No</th>
                <th>Login</th>
                <th>Password</th>
                <th>Role</th>
                <th>Status</th>
            </tr>
            <c:forEach var="user" items="${users}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}" /></td>
                    <td><c:out value="${user.getLogin()}" /></td>
                    <td><c:out value="${user.getPassword()}" /></td>
                    <td><c:out value="${user.getRole().getTitle()}" /></td>
                    <td><c:out value="${user.getStatus().getTitle()}" /></td>
                </tr>
            </c:forEach>
        </table>
    <hr />
    <a href="controller?command=create_user">Add user</a>
    <hr />
    <a href="controller?command=logout">Logout</a>
</body>
</html>

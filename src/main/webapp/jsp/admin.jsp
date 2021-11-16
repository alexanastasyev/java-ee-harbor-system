<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome, Admin</title>
</head>
<body>
    <h1>Welcome, Admin</h1>
    ${errorMessage}
    <hr/>
        <table>
            <tr>
                <th>No</th>
                <th>Login</th>
                <th>Password</th>
                <th>Role</th>
                <th>Status</th>
            </tr>
            <c:forEach var="report" items="${users}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}" /></td>
                    <td><c:out value="${report.getLogin()}" /></td>
                    <td><c:out value="${report.getPassword()}" /></td>
                    <td><c:out value="${report.getRole().getTitle()}" /></td>
                    <td><c:out value="${report.getStatus().getTitle()}" /></td>
                    <td><a href="controller?command=show_edit_user_page&id=${report.getId()}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
    <hr />
    <a href="controller?command=show_create_user_page">Add user</a>
    <hr />
    <a href="controller?command=logout">Logout</a>
</body>
</html>

<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome, moderator</title>
</head>
<body>
<h1>Welcome, moderator</h1>
${errorMessage}
<hr/>
<a href="controller?command=show_moderator_reports_page">Reports</a>
<hr/>
<table>
    <tr>
        <th>No</th>
        <th>Login</th>
        <th>Role</th>
        <th>Status</th>
    </tr>
    <c:forEach var="user" items="${users}" varStatus="status">
        <tr>
            <td><c:out value="${status.count}"/></td>
            <td><c:out value="${user.getLogin()}"/></td>
            <td><c:out value="${user.getRole().getTitle()}"/></td>
            <td><c:out value="${user.getStatus().getTitle()}"/></td>
            <td>
                <form method="POST" action="controller">
                    <input type="hidden" name="command" value="handle_user_blocking">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="hidden" name="nextPage" value="/controller?command=show_moderator_users_page">

                    <input type="submit" value=
                    <c:choose>
                    <c:when test="${user.getStatus().getTitle().equals('blocked')}">
                        <c:out value="Unblock"/>
                    </c:when>
                        <c:otherwise>
                            <c:out value="Block"/>
                        </c:otherwise>
                    </c:choose>
                    >

                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<hr/>
<a href="controller?command=logout">Logout</a>
</body>
</html>

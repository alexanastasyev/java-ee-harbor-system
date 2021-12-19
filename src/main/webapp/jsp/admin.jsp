<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome, Admin</title>

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <%--    Bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>
    <div class="content-container">
        <h1>Welcome!</h1>
        <c:if test="${errorMessage != null}">
            <hr/>
            <p style="color: red">${errorMessage}</p>
            <c:remove var="errorMessage" scope="session" />
        </c:if>
        <hr/>
        <div class="align-content-opposite">
            <div class="width-wrap-content">
                <a href="controller?command=show_admin_piers_page"><button class="btn btn-primary">Piers</button></a>
                <a href="controller?command=show_create_user_page"><button class="btn btn-outline-primary">Add user</button></a>
            </div>
            <div class="width-wrap-content">
                <a href="controller?command=logout"><button class="btn btn-danger">Logout</button></a>
            </div>
        </div>
        <hr/>
        <h2>Users</h2>
        <table class="table table-hover margin-top-medium">
            <tr>
                <th>No</th>
                <th>Login</th>
                <th>Password</th>
                <th>Role</th>
                <th>Status</th>
                <th>Online</th>
                <th></th>
            </tr>
            <c:forEach var="user" items="${users}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}" /></td>
                    <td><c:out value="${user.getLogin()}" /></td>
                    <td><c:out value="${user.getPassword()}" /></td>
                    <td><c:out value="${user.getRole().getTitle()}" /></td>
                    <td><c:out value="${user.getStatus().getTitle()}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${user.isOnline()}">Online</c:when>
                            <c:otherwise>Offline</c:otherwise>
                        </c:choose>
                    </td>
                    <td><a href="controller?command=show_edit_user_page&id=${user.getId()}"><button class="btn btn-outline-info btn-sm">Edit</button></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>

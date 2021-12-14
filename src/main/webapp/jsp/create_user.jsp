<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create user</title>

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <%--    Bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>
    <div class="content-container">
        <h2>Create new user</h2>
        <c:if test="${errorMessage != null}">
            <p style="color: red">${errorMessage}</p>
            <c:remove var="errorMessage" scope="session" />
            <hr/>
        </c:if>
        <form name="CreateUserForm" method="POST" action="controller" class="margin-top-medium">
            <div class="form-group">
                <input type="hidden" name="command" value="create_user"/>
                <label>
                    Login: <br/>
                    <input name="login" type="text" class="form-control"/>
                </label>
                <br/>
                <label class="margin-top-small">
                    Password: <br/>
                    <input name="password" type="text" class="form-control"/>
                </label>
                <br/>
                <label class="margin-top-small">
                    Role: <br/>
                    <select name="roles" class="form-control">
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.getId()}">
                                    ${role.getTitle()}
                            </option>
                        </c:forEach>
                    </select>
                </label>
                <br/>
                <button type="submit" class="btn btn-primary margin-top-medium">Create</button>
            </div>
        </form>
        <hr/>
        <a href="controller?command=show_admin_page"><button class="btn btn-secondary">Back</button></a>
    </div>
</body>
</html>

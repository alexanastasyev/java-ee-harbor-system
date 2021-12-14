<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign in page</title>

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <%--    Bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
    <div class="content-container align-content-center">
        <h1>Harbour Anastashkin</h1>
        <form name="LoginForm" method="POST" action="controller" class="margin-top-big">
            <div class="form-group">
                <input type="hidden" name="command" value="login"/>
                <label class="align-content-left form-check-label">
                    Login: <br/>
                    <input name="login" type="text" class="form-control"/>
                </label>
                <br/>
                <label class="align-content-left form-check-label margin-top-small">
                    Password: <br/>
                    <input name="password" type="password" class="form-control"/>
                </label>
                <br/>
                <c:if test="${errorMessage != null}">
                    <p style="color: red">${errorMessage}</p>
                    <c:remove var="errorMessage" scope="session" />
                </c:if>
                <button type="submit" class="btn btn-primary margin-top-medium">Sign in</button>
            </div>
        </form>
    </div>
</body>
</html>

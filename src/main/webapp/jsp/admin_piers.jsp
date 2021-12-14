<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Piers</title>

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <%--    Bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>
    <div class="content-container">
        <h1>Welcome!</h1>
        ${errorMessage}
        <hr/>
        <div class="align-content-opposite">
            <div class="width-wrap-content">
                <a href="controller?command=show_main_page"><button class="btn btn-primary">Users</button></a>
                <form method="post" action="controller" class="width-wrap-content margin-bottom-empty">
                    <input type="hidden" name="command" value="create_pier">
                    <button type="submit" class="btn btn-outline-primary">Add pier</button>
                </form>
            </div>
            <div class="width-wrap-content">
                <a href="controller?command=logout"><button class="btn btn-danger">Logout</button></a>
            </div>
        </div>
        <hr/>
        <h2>Piers</h2>
        <table class="table table-hover margin-top-medium">
            <tr>
                <th>No</th>
                <th>Available</th>
                <th></th>
            </tr>
            <c:forEach var="pier" items="${piers}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:choose>
                        <c:when test="${availabilities.get(status.index)}">Yes</c:when>
                        <c:otherwise>No</c:otherwise>
                    </c:choose></td>
                    <td class="align-content-right">
                        <c:if test="${availabilities.get(status.index)}">
                                <form method="post" action="controller" class="width-wrap-content margin-bottom-empty">
                                    <input type="hidden" name="command" value="delete_pier"/>
                                    <input type="hidden" name="id" value="${pier.getId()}"/>
                                    <button class="btn btn-outline-danger btn-sm" type="submit">Delete</button>
                                </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>

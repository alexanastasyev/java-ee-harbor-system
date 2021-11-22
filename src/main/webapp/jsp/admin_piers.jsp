<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Piers</title>
</head>
<body>
<h1>Welcome, Admin</h1>
${errorMessage}
<hr/>
<form method="post" action="controller">
    <input type="hidden" name="command" value="create_pier">
    <input type="submit" value="Create">
</form>
<hr/>
<table>
    <tr>
        <th>No</th>
        <th>Available</th>
    </tr>
    <c:forEach var="pier" items="${piers}" varStatus="status">
        <tr>
            <td><c:out value="${status.count}"/></td>
            <td><c:choose>
                <c:when test="${availabilities.get(status.index)}">Yes</c:when>
                <c:otherwise>No</c:otherwise>
            </c:choose></td>
            <c:if test="${availabilities.get(status.index)}">
                <td>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="delete_pier"/>
                        <input type="hidden" name="id" value="${pier.getId()}"/>
                        <input type="submit" value="Delete"/>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<hr />
<a href="controller?command=show_main_page">Back</a>
</body>
</html>

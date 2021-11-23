<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome, Captain</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js" ></script>
</head>
    <body onload="noBack()">
    <h1>Welcome, Captain</h1>
    <hr />
    <c:choose>
        <c:when test="${pierAssignment == null}">
            <h2>Welcome to Pearl Harbor, captain</h2>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="request_arrival"/>
                <input type="submit" value="Request arrival"/>
            </form>
        </c:when>
        <c:when test="${pierAssignment.getRequestStatus().getTitle().equals('requested_arrival')}">
            <h2>Your arrival request number: ${pierAssignment.getId()}, captain</h2>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="cancel_arrival_request"/>
                <input type="submit" value="Cancel request"/>
            </form>
        </c:when>
        <c:when test="${pierAssignment.requestStatus.getTitle().equals('approved_arrival')}">
            <h2>Your arrival request number: ${pierAssignment.getId()}  has been approved, captain</h2>
            <p>Arrive to pier №${pierAssignment.getPier().getId()}</p>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="arrive_pier"/>
                <input type="submit" value="Arrive to pier"/>
            </form>
        </c:when>
        <c:when test="${pierAssignment.requestStatus.getTitle().equals('locked')}">
            <h2>You are in Pearl Harbor pier №${pierAssignment.getPier().getId()}. Enjoy your visiting, captain</h2>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="show_upload_page"/>
                <input type="submit" value="Upload"/>
            </form>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="show_unload_page"/>
                <input type="submit" value="Unload"/>
            </form>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="request_department"/>
                <input type="submit" value="Request department"/>
            </form>
        </c:when>
        <c:when test="${pierAssignment.getRequestStatus().getTitle().equals('requested_department')}">
            <h2>Your department request number: ${pierAssignment.getId()}, captain</h2>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="cancel_department_request"/>
                <input type="submit" value="Cancel request"/>
            </form>
        </c:when>
        <c:when test="${pierAssignment.requestStatus.getTitle().equals('approved_department')}">
            <h2>Your department request number: ${pierAssignment.getId()} has been approved, captain</h2>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="depart_pier"/>
                <input type="submit" value="Depart from pier"/>
            </form>
        </c:when>
    </c:choose>
    <a href="controller?command=logout">Logout</a>
    </body>
</html>

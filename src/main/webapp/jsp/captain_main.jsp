<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome, Captain</title>
</head>
    <c:if test="${errorMessage != null}">
        <hr/>
        <p style="color: red">${errorMessage}</p>
        <c:remove var="errorMessage" scope="session" />
    </c:if>
    <body>
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
                <c:if test="${!isFreePiers}">
                    <h2>There are no free piers at the moment</h2>
                </c:if>
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
            <a href="controller?command=show_upload_page">Upload</a>
            <a href="controller?command=show_unload_page">Unload</a>
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
    <hr/>
    <a href="controller?command=show_create_report_page">Report</a>
    <hr/>
    <a href="controller?command=logout">Logout</a>
    </body>
</html>

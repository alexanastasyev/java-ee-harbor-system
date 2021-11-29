<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Report</title>
</head>
    <c:if test="${errorMessage != null}">
        <hr/>
        <p style="color: red">${errorMessage}</p>
        <c:remove var="errorMessage" scope="session" />
    </c:if>
    <body>
        <a href="controller?command=show_moderator_reports_page">Back</a>
        <hr/>
        <table>
            <tr>
                <td><b>From: </b></td>
                <td>${report.getFromUser().getLogin()}</td>
            </tr>
            <tr>
                <td><b>To: </b></td>
                <td>${report.getToUser().getLogin()}</td>
            </tr>
            <tr>
                <td><b>Text: </b></td>
                <td>${report.getText()}</td>
            </tr>
        </table>
        <hr/>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="handle_user_blocking">
            <input type="hidden" name="id" value="${report.getToUser().getId()}">
            <input type="hidden" name="nextPage" value="/controller?command=show_report_page&id=${report.getId()}">

            <input type="submit" value=
                <c:choose>
                    <c:when test="${report.getToUser().getStatus().getTitle().equals('blocked')}">
                        <c:out value="Unblock"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="Block"/>
                    </c:otherwise>
                </c:choose>
            >
        </form>

        <form method="POST" action="controller">
            <input type="hidden" name="command" value="delete_report">
            <input type="hidden" name="id" value="${report.getId()}">

            <input type="submit" value="Delete report">
        </form>

    </body>
</html>

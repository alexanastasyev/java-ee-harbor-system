<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Report</title>

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <%--    Bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
    <body>
        <div class="content-container">
            <c:if test="${errorMessage != null}">
                <hr/>
                <p style="color: red">${errorMessage}</p>
                <c:remove var="errorMessage" scope="session" />
            </c:if>
            <h1>View complaint</h1>
            <hr/>
                <a href="controller?command=show_moderator_reports_page"><button class="btn btn-secondary">Back</button></a>
            <hr/>
            <table class="table table-borderless">
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
            <div class="width-wrap-content">
                <form method="POST" action="controller" class="width-wrap-content margin-bottom-empty">
                    <input type="hidden" name="command" value="handle_user_blocking">
                    <input type="hidden" name="id" value="${report.getToUser().getId()}">
                    <input type="hidden" name="nextPage" value="/controller?command=show_report_page&id=${report.getId()}">

                    <button type="submit" class="btn btn-warning">
                        <c:choose>
                        <c:when test="${report.getToUser().getStatus().getTitle().equals('blocked')}">
                            <c:out value="Unblock user"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="Block user"/>
                        </c:otherwise>
                        </c:choose>
                    </button>
                </form>

                <form method="POST" action="controller" class="width-wrap-content margin-bottom-empty">
                    <input type="hidden" name="command" value="delete_report">
                    <input type="hidden" name="id" value="${report.getId()}">

                    <button type="submit" class="btn btn-danger">Delete complaint</button>
                </form>
            </div>
        </div>
    </body>
</html>

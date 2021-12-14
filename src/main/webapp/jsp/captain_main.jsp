<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome, Captain</title>

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
        <h1>Welcome!</h1>
        <hr/>
        <div class="align-content-opposite">
            <div class="width-wrap-content">
                <a href="controller?command=show_create_report_page"><button class="btn btn-outline-primary">Complain</button></a>
            </div>
            <div class="width-wrap-content">
                <a href="controller?command=logout"><button class="btn btn-danger">Logout</button></a>
            </div>
        </div>
        <hr/>
        <c:choose>
            <c:when test="${pierAssignment == null}">
                <h2 class="margin-top-big">Welcome to Harbour Anastashkin!</h2>
                <form method="post" action="controller" class="margin-top-big">
                    <input type="hidden" name="command" value="request_arrival"/>
                    <button type="submit" class="btn btn-primary btn-lg">Request arrival</button>
                </form>
            </c:when>
            <c:when test="${pierAssignment.getRequestStatus().getTitle().equals('requested_arrival')}">
                <h2 class="margin-top-big">Your arrival request id: <b>${pierAssignment.getId()}</b></h2>
                <c:if test="${!isFreePiers}">
                    <h2 class="margin-top-big">There are no free piers at the moment</h2>
                </c:if>
                <form method="post" action="controller" class="margin-top-big">
                    <input type="hidden" name="command" value="cancel_arrival_request"/>
                    <button type="submit" class="btn btn-primary btn-lg">Cancel request</button>
                </form>
            </c:when>
            <c:when test="${pierAssignment.requestStatus.getTitle().equals('approved_arrival')}">
                <h2 class="margin-top-big">Your arrival request id: <b>${pierAssignment.getId()}</b>  has been approved.</h2>
                <h2 class="margin-top-big">Arrive to pier <b>№${pierAssignment.getPier().getId()}</b></h2>
                <form method="post" action="controller" class="margin-top-big">
                    <input type="hidden" name="command" value="arrive_pier"/>
                    <button type="submit" class="btn btn-primary btn-lg">Arrive to pier</button>
                </form>
            </c:when>
            <c:when test="${pierAssignment.requestStatus.getTitle().equals('locked')}">
                <h2 class="margin-top-big">You are in pier <b>№${pierAssignment.getPier().getId()}</b>.</h2>
                <h2>Enjoy your visiting!</h2>
                <div class="width-wrap-content margin-top-big">
                    <a href="controller?command=show_upload_page"><button class="btn btn-success">Upload</button></a>
                    <a href="controller?command=show_unload_page"><button class="btn btn-warning">Unload</button></a>
                </div>
                <hr/>
                <form method="post" action="controller" class="margin-top-big">
                    <input type="hidden" name="command" value="request_department"/>
                    <button type="submit" class="btn btn-primary btn-lg">Request department</button>
                </form>
            </c:when>
            <c:when test="${pierAssignment.getRequestStatus().getTitle().equals('requested_department')}">
                <h2 class="margin-top-big">Your department request id: <b>${pierAssignment.getId()}</b>.</h2>
                <form method="post" action="controller" class="margin-top-big">
                    <input type="hidden" name="command" value="cancel_department_request"/>
                    <button type="submit" class="btn btn-primary btn-lg">Cancel department</button>
                </form>
            </c:when>
            <c:when test="${pierAssignment.requestStatus.getTitle().equals('approved_department')}">
                <h2 class="margin-top-big">Your department request id: <b>${pierAssignment.getId()}</b> has been approved.</h2>
                <form method="post" action="controller" class="margin-top-big">
                    <input type="hidden" name="command" value="depart_pier"/>
                    <button type="submit" class="btn btn-primary btn-lg">Depart from pier</button>
                </form>
            </c:when>
        </c:choose>
    </div>
</body>
</html>

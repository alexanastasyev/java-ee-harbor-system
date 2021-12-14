<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome, Dispatcher</title>

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
                    <a href="controller?command=show_products_info_page"><button class="btn btn-primary">Products</button></a>
                    <a href="controller?command=show_create_report_page"><button class="btn btn-outline-primary">Complain</button></a>
                </div>
                <div class="width-wrap-content">
                    <a href="controller?command=logout"><button class="btn btn-danger">Logout</button></a>
                </div>
            </div>
        <hr/>
        <table class="table table-borderless">
            <tr>
                <th>Requests</th>
                <th>Piers</th>
            </tr>
            <tr>
                <td>
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>No</th>
                            <th>Captain</th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                        <c:forEach var="pierAssignment" items="${pierAssignments}" varStatus="status">
                            <c:if test="${pierAssignment.getRequestStatus().getTitle().equals('requested_arrival') || pierAssignment.getRequestStatus().getTitle().equals('requested_department')}">
                                <tr>
                                    <td>${pierAssignment.getId()}</td>
                                    <td>${pierAssignment.getCaptain().getLogin()}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${pierAssignment.getRequestStatus().getTitle().equals('requested_arrival')}">
                                                Arrival request
                                            </c:when>
                                            <c:otherwise>
                                                Department request
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <form method="post" action="controller">
                                            <div class="form-group">
                                                <input type="hidden" name="pierAssignmentId" value="${pierAssignment.getId()}">
                                                <c:choose>
                                                    <c:when test="${pierAssignment.getRequestStatus().getTitle().equals('requested_arrival')}">
                                                        <input type="hidden" name="command" value="approve_arrival_request"/>
                                                        <div class="layout-flex">
                                                            <label class="layout-flex width-wrap-content">
                                                                Pier: &emsp;
                                                                <select name="chosenPier" class="form-control width-wrap-content">
                                                                    <c:forEach var="pierWithAssignment" items="${piersWithAssignments}">
                                                                        <c:if test="${pierWithAssignment.getValue() == null}">
                                                                            <option value="${pierWithAssignment.getKey().getId()}">${pierWithAssignment.getKey().getId()}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </select>
                                                            </label>
                                                            &emsp;
                                                            <button type="submit" class="btn btn-outline-primary">Approve</button>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="hidden" name="command" value="approve_department_request"/>
                                                        <button type="submit" class="btn btn-outline-primary">Approve</button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </td>
                <td>
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>No</th>
                            <th>Captain</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach var="pierWithAssignment" items="${piersWithAssignments}" varStatus="status">
                            <tr>
                                <td>${pierWithAssignment.getKey().getId()}</td>
                                <td>${pierWithAssignment.getValue().getCaptain().getLogin()}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${pierWithAssignment.getValue() == null}">
                                            Free
                                        </c:when>
                                        <c:when test="${pierWithAssignment.getValue().getRequestStatus().getTitle().equals('approved_arrival')}">
                                            Engaged
                                        </c:when>
                                        <c:when test="${pierWithAssignment.getValue().getRequestStatus().getTitle().equals('locked')}">
                                            Locked
                                        </c:when>
                                        <c:when test="${pierWithAssignment.getValue().getRequestStatus().getTitle().equals('requested_department')}">
                                            Release requested
                                        </c:when>
                                        <c:otherwise>
                                            Vacated
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>

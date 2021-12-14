<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product</title>

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <%--    Bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>
    <div class="content-container">
        <h1>Welcome!</h1>
        <hr/>
        <div class="align-content-opposite">
            <div class="width-wrap-content">
                <a href="controller?command=show_dispatcher_main_page"><button class="btn btn-primary">Requests</button></a>
            </div>
            <div class="width-wrap-content">
                <a href="controller?command=logout"><button class="btn btn-danger">Logout</button></a>
            </div>
        </div>
        <hr/>
        <table class="table table-borderless">
            <tr>
                <th>In harbour</th>
                <th>Departed</th>
            </tr>
            <tr>
                <td>
                    <table class="table table-bordered table-hover">
                        <tr>
                            <th>No</th>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>From</th>
                            <th>PierNo</th>
                            <th>Arrival date</th>
                        </tr>
                        <c:forEach var="product" items="${products}" varStatus="status">
                            <c:if test="${product.getDepartureDate() == null}">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${product.getTitle()}</td>
                                    <td>${product.getQuantity()}</td>
                                    <td>${product.getCaptain().getLogin()}</td>
                                    <td>${product.getPier().getId()}</td>
                                    <td>${product.getArrivalDate()}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </td>
                <td>
                    <table class="table table-bordered table-hover">
                        <tr>
                            <th>No</th>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Arrival date</th>
                            <th>Departure date</th>
                        </tr>
                        <c:forEach var="product" items="${products}" varStatus="status">
                            <c:if test="${product.getDepartureDate() != null}">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${product.getTitle()}</td>
                                    <td>${product.getQuantity()}</td>
                                    <td>${product.getArrivalDate()}</td>
                                    <td>${product.getDepartureDate()}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>

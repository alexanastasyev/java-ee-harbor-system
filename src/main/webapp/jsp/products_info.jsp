<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
    <table>
        <tr>
            <td>
                <table>
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
                <table>
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
</body>
</html>

<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/upload.js"></script>
</head>
<body>
    <table id="table-products">
        <thead>
            <tr>
                <th>No</th>
                <th>Title</th>
                <th>Quantity</th>
                <th>Arrival date</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${product.getTitle()}"/></td>
                    <td><c:out value="${product.getQuantity()}"/></td>
                    <td><c:out value="${product.getArrivalDate().toString()}"/></td>
                    <td><input type="checkbox" name="uploadProduct"></td>
                    <td><input type="hidden" value="${product.getId()}"></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form method="post" action="controller">
        <input type="hidden" name="command" value="upload"/>
        <input id="input-products" type="hidden" name="productsIds">
        <input type="submit" value="Upload" onclick="handleUploadClick()"/>
    </form>
</body>
</html>

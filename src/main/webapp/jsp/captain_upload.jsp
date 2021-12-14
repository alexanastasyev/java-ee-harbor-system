<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/upload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/back.js"></script>

    <%--    Bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>
    <div class="content-container">
        <h1>Upload products</h1>
        <hr/>
        <form method="post" action="controller" class="margin-bottom-empty width-wrap-content">
            <input type="hidden" name="command" value="upload"/>
            <input id="input-products" type="hidden" name="productsIds">
            <button type="submit" class="btn btn-success" onclick="handleUploadClick()">Upload selected products</button>
        </form>
        <button class="btn btn-secondary" onclick="back()">Back</button>
        <hr/>
        <table id="table-products" class="table table-hover">
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
    </div>
</body>
</html>

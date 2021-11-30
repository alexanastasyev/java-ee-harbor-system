<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
    <script type="text/javascript">
        function handleUploadClick() {
            const products = [];

            const table = document.getElementById('table-products');

            const children = table.lastElementChild.children;
            for (let i = 0; i < children.length; i++) {
                const id = children[i].lastElementChild.firstElementChild.value;
                const shouldUpload = children[i].children[children[i].children.length - 2].firstElementChild.checked;
                if (shouldUpload) {
                    products.push(id);
                }
            }
            document.getElementById('input-products').setAttribute('value', JSON.stringify(products));
        }
    </script>
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

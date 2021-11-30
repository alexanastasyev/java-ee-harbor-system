<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Unload</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/unload.js"></script>
</head>
<body>
    <div id="dynamic-div">
    </div>

    <button onclick="addItem()">Add product</button>

    <form method="post" action="controller">
        <input type="hidden" name="command" value="unload">
        <input id="input-products" type="hidden" name="products">
        <input type="submit" onclick="handleUnloadClick()" value="Unload">
    </form>
</body>
</html>

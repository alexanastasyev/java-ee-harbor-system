<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Unload</title>

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/unload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/back.js"></script>

    <%--    Bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>
    <div class="content-container">
        <h1>Unload products</h1>
        <hr/>
        <form method="post" action="controller" class="margin-bottom-empty width-wrap-content">
            <input type="hidden" name="command" value="unload">
            <input id="input-products" type="hidden" name="products">
            <button type="submit" class="btn btn-warning" onclick="handleUnloadClick()">Unload selected products</button>
        </form>
        <button class="btn btn-secondary" onclick="back()">Back</button>
        <hr/>
        <div id="dynamic-div">
        </div>
        <hr/>
        <button class="btn btn-primary" onclick="addItem()">Add product</button>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Unload</title>
    <script type="text/javascript">
        function addItem() {
            const divMain = document.getElementById('dynamic-div');

            const div = document.createElement('div');

            const labelTitle = document.createElement('label');
            labelTitle.append('Title: ');
            const inputTitle = document.createElement('input');
            inputTitle.setAttribute('type', 'text');
            inputTitle.setAttribute('name', 'productTitle');
            labelTitle.appendChild(inputTitle);
            div.appendChild(labelTitle);

            const labelQuantity = document.createElement('label');
            labelQuantity.append(' Quantity: ');
            const inputQuantity = document.createElement('input');
            inputQuantity.setAttribute('type', 'text');
            inputQuantity.setAttribute('name', 'productQuantity');
            labelQuantity.appendChild(inputQuantity);
            div.appendChild(labelQuantity);

            divMain.appendChild(div);
        }

        function handleUnloadClick() {
            const products = []

            const divMain = document.getElementById('dynamic-div');
            const children = divMain.children;
            for (let i = 0; i < children.length; i++) {
                const title = children[i].firstElementChild.firstElementChild.value;
                const quantity = children[i].lastElementChild.firstElementChild.value;

                products.push({
                   'title': title,
                   'quantity': quantity
                });
            }

            document.getElementById('input-products').setAttribute('value', JSON.stringify(products));
        }

    </script>
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

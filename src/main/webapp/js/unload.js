function addItem() {
    const divMain = document.getElementById('dynamic-div');

    const div = document.createElement('div');
    div.setAttribute('class', 'margin-top-small form-group');

    const labelTitle = document.createElement('label');
    labelTitle.append('Title:')
    const inputTitle = document.createElement('input');
    inputTitle.setAttribute('type', 'text');
    inputTitle.setAttribute('name', 'productTitle');
    inputTitle.setAttribute('class', 'form-control');
    labelTitle.appendChild(inputTitle);
    div.appendChild(labelTitle);

    const labelQuantity = document.createElement('label');
    labelQuantity.append('Quantity:')
    labelQuantity.setAttribute('class', 'margin-left-small');
    const inputQuantity = document.createElement('input');
    inputQuantity.setAttribute('type', 'text');
    inputQuantity.setAttribute('name', 'productQuantity');
    inputQuantity.setAttribute('class', 'form-control');
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
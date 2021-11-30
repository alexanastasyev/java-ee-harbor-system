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
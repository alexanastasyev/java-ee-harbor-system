function addItem() {
    const form = document.getElementById('dynamic-form');
    const labelTitle = document.createElement('label');

    const inputTitle = document.createElement('input');
    inputTitle.setAttribute('type', 'text');
    inputTitle.setAttribute('name', 'productTitle');

    labelTitle.appendChild(inputTitle);
    form.appendChild(labelTitle);
}
function noBack() {
    history.pushState(null, document.title, location.href);

    // !!! Never delete 'event' field - function is not defined
    // noinspection JSUnusedLocalSymbols
    window.addEventListener('popstate', function (event) {
        history.pushState(null, document.title, location.href);
    });
}
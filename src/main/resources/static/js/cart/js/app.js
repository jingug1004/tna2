require.config({
    urlArgs: function(id, url) {
        var body = window.document.body;
        var deployVersion;
        if (navigator.appVersion.indexOf("MSIE 7.") != -1) {
            deployVersion = (body.getAttributeNode("data-deployversion"))? body.getAttributeNode("data-deployversion").value : '';
        } else {
            deployVersion = (body.getAttribute("data-deployversion"))? body.getAttribute("data-deployversion") : '';
        }

        return (url.indexOf('?') === -1 ? '?v=' : '&') + deployVersion;
    }
});

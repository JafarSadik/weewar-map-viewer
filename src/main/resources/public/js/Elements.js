var MapViewer = MapViewer || {};
MapViewer.Elements = (function () {
    return {
        mapBox: (map) =>
            `<div id="${map.id}" class="map-box">
                <img src="/images/maps/thumbnails/${map.id}.png"/>
            </div>`
    }
})();
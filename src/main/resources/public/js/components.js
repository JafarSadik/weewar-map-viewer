var MapViewer = MapViewer || {};
MapViewer.Components = (function () {
    return {
        mapBox: (map) =>
            `<div id="${map.id}" class="map-box">
                <img src="/images/maps/thumbnails/${map.id}.png"/>
            </div>`
    }
})();
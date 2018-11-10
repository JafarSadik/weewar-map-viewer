var MapViewer = MapViewer || {};
MapViewer.Elements = (function () {
    return {
        mapBox: (map) =>
            `<div id="map_${map.id}" class="map-box">
                <a href="/map/${map.id}"><img src="/images/maps/thumbnails/${map.id}.png"/></a>
            </div>`
    }
})();
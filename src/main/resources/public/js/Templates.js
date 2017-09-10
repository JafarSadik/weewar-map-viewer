var MapViewer = MapViewer || {};
MapViewer.Templates = (function () {
    return {
        renderMapBox: function (map) {
            return `
            <div id="map_${map.mapId}" class="map-box">
                <a href="/map/${map.mapId}/${map.revision}/${map.urlEncodedMapName}"><img src="/images/maps/thumbnails/${map.mapId}.png"/></a>
            </div>
            `;
        }
    };
})();
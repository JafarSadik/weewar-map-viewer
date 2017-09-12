var MapViewer = MapViewer || {};
MapViewer.Elements = (function () {
    let elements = {};
    elements.mapBox = (map) => `
            <div id="map_${map.mapId}" class="map-box">
                <a href="/map/${map.mapId}/${map.revision}/${map.urlEncodedMapName}"><img src="/images/maps/thumbnails/${map.mapId}.png"/></a>
            </div>
            `;
    return elements;
})();
var MapViewer = MapViewer || {};
MapViewer.Elements = (function () {
    let elements = {};
    elements.mapBox = (map) => `
            <div id="map_${map.id}" class="map-box">
                <a href="/map/${map.id}/${map.revision}"><img src="/images/maps/thumbnails/${map.id}.png"/></a>
            </div>
            `;
    return elements;
})();
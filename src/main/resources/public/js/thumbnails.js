var MapViewer = MapViewer || {};
MapViewer.Thumbnails = (function () {
    const thumbnail = (map) =>
        `<div id="${map.id}" class="map-box">
            <img src="/images/maps/thumbnails/${map.id}.png" alt="${map.id}"/>
        </div>`;

    return {
        append: (maps) => {
            const $container = $("#map-box-container");
            maps.forEach(map => $container.append(thumbnail(map)));
        }
    }
})();

var MapViewer = MapViewer || {};
MapViewer.MapContainer = function () {
    if (!(this instanceof MapViewer.MapContainer)) {
        throw new Error("Constructor called without new!");
    }

    $(() => this.$container = $("#map-box-container"))
};

MapViewer.MapContainer.prototype.append = function (mapBox) {
    this.$container.append(mapBox);
};
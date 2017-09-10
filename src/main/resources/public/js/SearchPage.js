var MapViewer = MapViewer || {};
(function () {
    let mapContainer = new MapViewer.MapContainer();
    let firstMapIndex = 0, pageSize = 100;

    $(function () {
        loadInitialMaps();
        pageScrolledToBottom(() => loadMoreMaps(firstMapIndex, pageSize));
    });

    function loadInitialMaps() {
        loadMoreMaps(0, maxItemsOnPage());
    }

    function loadMoreMaps(_firstMapIndex, _pageSize) {
        _firstMapIndex = _firstMapIndex || firstMapIndex;
        _pageSize = _pageSize || pageSize;
        getMaps(_firstMapIndex, _pageSize,
            function (maps) {
                $.each(maps, function (index, map) {
                    let mapBox = MapViewer.Templates.renderMapBox(map);
                    mapContainer.append(mapBox);
                });
                firstMapIndex = _firstMapIndex + _pageSize
            })
    }

    function pageScrolledToBottom(callback) {
        window.onscroll = function (event) {
            if ((window.innerHeight + window.scrollY) >= document.documentElement.offsetHeight) {
                callback(event);
            }
        };
    }

    function getMaps(first, count, callback) {
        $.get(`/api/maps?first=${first}&count=${count}`, callback);
    }

    function maxItemsOnPage() {
        let mapBoxWidth = 180, mapBoxHeight = 150;
        let maxColumns = screen.width / mapBoxWidth;
        let maxRows = screen.height / mapBoxHeight;
        return Math.round(maxColumns * maxRows);
    }
})();

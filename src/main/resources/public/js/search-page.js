var MapViewer = MapViewer || {};
(function () {
    let container = new MapViewer.MapContainer();
    let firstMapIndex = 0, pageSize = 100;

    $(function () {
        loadInitialMaps();
        pageScrolledToBottom(() => loadMoreMaps(firstMapIndex, pageSize));
        initDialogBox()
    });

    function loadInitialMaps() {
        loadMoreMaps(0, maxItemsOnPage());
    }

    function loadMoreMaps(_firstMapIndex, _pageSize) {
        getMaps(_firstMapIndex, _pageSize, (maps) => {
            maps.forEach(map => container.append(MapViewer.Components.mapBox(map)));
            firstMapIndex = _firstMapIndex + _pageSize
        });
    }

    function pageScrolledToBottom(callback) {
        window.onscroll = function (event) {
            if ((window.innerHeight + window.scrollY) >= document.documentElement.offsetHeight) {
                callback(event);
            }
        };
    }

    function initDialogBox() {
        let $modal = $('.modal');

        $("#map-box-container").on("click", ".map-box", function () {
            let active = $(this);
            let mapId = $(active).attr('id');
            let $modalContent = $modal.find('.modal-content');

            $modalContent.html(`
                <a href="/map/${mapId}" target="_blank">
                    <img src="/images/maps/${mapId}.png"/>
                </a>
            `);
            $modal.show();
        });

        $modal.click(() => $modal.hide())
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

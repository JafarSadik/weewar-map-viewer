var MapViewer = MapViewer || {};
(function () {
    let container = new MapViewer.MapContainer();
    let components = MapViewer.Components;
    let firstMapIndex = 0, pageSize = 100;

    $(function () {
        loadInitialMaps();
        pageScrolledToBottom(() => loadMoreMaps(firstMapIndex, pageSize));
        initDialogBox()
    });

    function loadInitialMaps() {
        loadMoreMaps(0, maxItemsOnPage());
    }

    function loadMoreMaps(first, count) {
        getMaps(first, count).then((maps) => {
            for (let map of maps) {
                container.append(components.mapBox(map));
            }
            firstMapIndex = first + count
        })
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
            getMapInfo(mapId).then((map) => {
                $modal.html(`                   
                    <div class="modal-content">
                        <div class="title">
                            <div>${map.name}, version ${map.revision} by ${map.creator || 'unknown'}</div>
                            <div>${map.players} players | Income: ${map.perBaseCredits} | Start credits: ${map.initialCredits} | Map size: ${map.width}x${map.height}</div>
                        </div>
                        <a href="/map/${mapId}" target="_blank">
                            <img src="/images/maps/${mapId}.png"/>
                        </a>
                    </div>
                `);
                $modal.show();
            });
        });

        $modal.click(() => $modal.hide())
    }

    function getMaps(first, count) {
        return new Promise((resolve) =>
            $.get(`/api/maps?first=${first}&count=${count}`, (maps) => resolve(maps))
        );
    }

    function getMapInfo(mapId) {
        return new Promise((resolve) =>
            $.get(`/api/maps/${mapId}`, (mapInfo) => resolve(JSON.parse(mapInfo)))
        );
    }

    function maxItemsOnPage() {
        let mapBoxWidth = 180, mapBoxHeight = 150;
        let maxColumns = screen.width / mapBoxWidth;
        let maxRows = screen.height / mapBoxHeight;
        return Math.round(maxColumns * maxRows);
    }
})();

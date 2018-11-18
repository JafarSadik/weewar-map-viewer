var MapViewer = MapViewer || {};
(function () {
    const thumbnails = MapViewer.Thumbnails;
    const page = {index: 0, size: maxItemsOnPage()};

    $(function () {
        loadInitialMaps();
        whenPageScrolledToBottom(() => loadMoreMaps());
    });

    function loadInitialMaps() {
        loadMoreMaps();
    }

    function loadMoreMaps() {
        getMaps(page.index, page.size).then((maps) => {
            thumbnails.append(maps);
            page.index = page.index + page.size;
        })
    }

    function whenPageScrolledToBottom(callback) {
        window.onscroll = function (event) {
            if ((window.innerHeight + window.scrollY) >= document.documentElement.offsetHeight) {
                callback(event);
            }
        };
    }

    function getMaps(first, count) {
        return new Promise((resolve) =>
            $.get(`/api/maps?first=${first}&count=${count}`, (maps) => resolve(maps))
        );
    }

    function maxItemsOnPage() {
        let mapBoxWidth = 180, mapBoxHeight = 150;
        let maxColumns = window.innerWidth / mapBoxWidth;
        let maxRows = window.innerHeight / mapBoxHeight;
        return Math.round(maxColumns * maxRows);
    }
})();

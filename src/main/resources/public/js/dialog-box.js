(function () {
    $(function () {
        $modal = $('.modal');
        showModalOnClick();
        hideModalOnClick();
    });

    function showModalOnClick() {
        $("#map-box-container").on("click", ".map-box", function () {
            const mapId = $(this).attr('id');

            Promise.all([preloadImage(mapImagePath(mapId)), getMapInfo(mapId)])
                .then(([image, mapInfo]) => {
                    $modal.html(dialogBox(mapInfo));
                    $modal.show();
                });
        });
    }

    function hideModalOnClick() {
        $modal.click(() => $modal.hide());
    }

    let $modal;

    let mapImagePath = (mapId) => `/images/maps/${mapId}.png`;

    let dialogBox = (map) =>
        `<div class="modal-content">
            <div class="title">
                <div>${map.name}, version ${map.revision} by ${map.creator || 'unknown author'}</div>
                <div>${map.players} players | Income: ${map.perBaseCredits} | Start credits: ${map.initialCredits} | Map size: ${map.width}x${map.height}</div>
            </div>
            <a href="/map/${map.id}" target="_blank">
                <img src="${mapImagePath(map.id)}" alt="${map.id}"/>
            </a>
         </div>`;

    function getMapInfo(mapId) {
        return new Promise((resolve) =>
            $.get(`/api/maps/${mapId}`, (mapInfo) => resolve(JSON.parse(mapInfo)))
        );
    }

    function preloadImage(src) {
        return new Promise((resolve, reject) => {
                let image = new Image();
                image.onload = () => resolve(image);
                image.onerror = () => reject(`failed to load image: ${src}`);
                image.src = src;
            }
        );
    }
})();

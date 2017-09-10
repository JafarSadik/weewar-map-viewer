$(document).ready(function () {
    var template = $("#map-box-template").text();
    var $container = $("#map-box-container");
    $.get("/api/maps?first=0&count=13000", function(maps) {
        $.each(maps, function(index, map) {
            var mapBoxDiv = Mustache.render(template, map);
            $container.append(mapBoxDiv);
        });
    });
});
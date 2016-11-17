$(document).ready(function () {
    $('#menu-button').on('click', function (e) {
        $('#menu').toggleClass('overlay-menu');
        e.preventDefault();
    });
});

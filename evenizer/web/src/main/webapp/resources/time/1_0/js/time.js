/**
 *
 */

function start() {

}

function rotate() {

}

/*
 var rotation = 0;

 $(document).ready(function() {
 $("#clockLong").click(function() {
 rotation += 10;
 $("#clockLong").rotate(rotation);
 alert("test");
 })
 });
 */

$(document).ready(function () {

    $(".clock").click(function () {
        $(this).hide();
    });

    $(".button").click(function () {
        $(".clock > .arrowLong").css("animation-direction", "reverse");
        $(".clock > .arrowShort").css("animation-direction", "reverse");
        $(".spiral").css("animation-play-state", "running")
    });

    /*$("#slider").slider();*/


});

var interval = setInterval(tick, 1000);

function tick() {
    var date = new Date();
    date.getYear();
    $(".year").text(date.getFullYear());
    $(".month").text(months[date.getMonth()]);
    $(".day").text(date.getDay());
    $(".hours").text(date.getHours());
    $(".minutes").text(date.getMinutes());
    $(".seconds").text(date.getSeconds());
}

var months = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];

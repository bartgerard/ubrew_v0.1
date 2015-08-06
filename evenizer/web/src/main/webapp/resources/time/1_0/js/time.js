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
        $(".spiral").css("animation-play-state", "running");

        offsetPresent = offsetDestination;
        offsetLast = offsetDestination;

        offsetDestination = {
            year: 0,
            month: 0,
            day: 0,
            date: null
        };
        offsetLast.date = new Date();

        tick();
    });

    /*$("#slider").slider();*/

    var timeIndex = 0;

    var times = [".time1", ".time2", ".time3", ".time4", ".time5", ".time6"];

    $(".timeCover").click(function () {

        timeIndex = (timeIndex + 1) % 6;

        for (i = 0; i < times.length; i++) {
            if (i == timeIndex) {
                $(times[i]).css("z-index", 1);
            } else {
                $(times[i]).css("z-index", 0);
            }
        }
    })


});

var interval = setInterval(tick, 1000);

var offsetDestination = {
    year: -20000,
    month: 20,
    day: 0,
    date: new Date()
};

var offsetPresent = {
    year: 0,
    month: 0,
    day: 0,
    date: null
};

var offsetLast = {
    year: 0,
    month: 0,
    day: 0,
    date: null
};

function tick() {
    var date = new Date();
    tickA('.displayDestination > ', offsetDestination.date, offsetDestination.year, offsetDestination.month);
    tickA('.displayPresent > ', date, offsetPresent.year, offsetPresent.month);
    tickA('.displayLast > ', offsetLast.date, offsetLast.year, offsetLast.month);

    var dayPercentage = date.getTime() % dayInMilliseconds / dayInMilliseconds;
    $('.dayAndNight').css('transform', 'rotate(' + dayPercentage * 360 + 'deg)');
}

function tickA(element, date, offsetYear, offsetMonth) {
    if (date != null) {
        $(element + '.year').text(date.getFullYear() + offsetYear);
        $(element + '.month').text(months[(date.getMonth() + offsetMonth) % 12]);
        $(element + '.day').text(date.getDay());
        $(element + '.hours').text(date.getHours());
        $(element + '.minutes').text(date.getMinutes());
        $(element + '.seconds').text(date.getSeconds());
    } else {
        $(element + '.year').text('----');
        $(element + '.month').text('---');
        $(element + '.day').text('--');
        $(element + '.hours').text('--');
        $(element + '.minutes').text('--');
        $(element + '.seconds').text('--');
    }
}

var dayInMilliseconds = 1000 * 60 * 60 * 24;

var months = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];

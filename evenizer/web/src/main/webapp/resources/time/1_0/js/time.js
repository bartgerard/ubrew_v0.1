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

    tick();

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

    $(".timeCover").click(function () {

        timeIndex = (timeIndex + 1) % 6;

        for (i = 0; i < times.length; i++) {
            if (i == timeIndex) {
                $(times[i]).css("z-index", 1);
                offsetDestination = timeOffsets[i];
                tick();
            } else {
                $(times[i]).css("z-index", 0);
            }
        }
    });


});

var timeIndex = 0;

var times = [".time1", ".time2", ".time3", ".time4", ".time5", ".time6"];

var now = new Date();

var timeOffsets = [
    {
        year: -10000 - now.getFullYear(),
        month: (6 - now.getMonth()) % 12,
        day: 0,
        date: now
    },
    {
        year: -3500 - now.getFullYear(),
        month: (5 - now.getMonth()) % 12,
        day: 0,
        date: now
    },
    {
        year: 0 - now.getFullYear(),
        month: (4 - now.getMonth()) % 12,
        day: 0,
        date: now
    },
    {
        year: 800 - now.getFullYear(),
        month: (3 - now.getMonth()) % 12,
        day: 0,
        date: now
    },
    {
        year: 1492 - now.getFullYear(),
        month: (2 - now.getMonth()) % 12,
        day: 0,
        date: now
    },
    {
        year: 42596 - now.getFullYear(),
        month: (1 - now.getMonth()) % 12,
        day: 0,
        date: now
    }
];

var interval = setInterval(tick, 1000);

var offsetDestination = timeOffsets[0];

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

    clock('#actualTime > ', date, offsetPresent.year);
    clock('#currentTime > ', date, offsetPresent.year);
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

function clock(element, date, offsetYear) {
    $(element + '.clockHours').css({transform: 'rotate(' + (date.getHours() * 360 / 24) + 'deg)'});
    $(element + '.clockMinutes').css({transform: 'rotate(' + (date.getMinutes() * 360 / 60) + 'deg)'});
    $(element + '.clockSeconds').css({transform: 'rotate(' + (date.getSeconds() * 360 / 60) + 'deg)'});
}

var dayInMilliseconds = 1000 * 60 * 60 * 24;

var months = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];

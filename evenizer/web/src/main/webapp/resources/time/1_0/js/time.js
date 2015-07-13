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

    $("#slider").slider();

});
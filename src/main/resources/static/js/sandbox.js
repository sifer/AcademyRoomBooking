console.log("JavaScript test.")

function changedRoom() {
    $("#roomsize").val($("#roomname").val());
}
function changedSize() {
    $("#roomname").val($("#roomsize").val());
}

var $newDiv = $("<div id='test'></div>");
$("#testdiv").append($newDiv);
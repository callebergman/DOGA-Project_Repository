$(document).ready(function()
{
    $("#expertise").hide();
    $("#periods").hide();
    $("#preview").hide();
    $("#basicInfo").show();
    
    $("#opt1").click(function() {
        $("#expertise").hide();
        $("#periods").hide();
        $("#preview").hide();
        $("#basicInfo").show();
        
        $.cookie("opt1", true);
        $.cookie("opt2", false);
        $.cookie("opt3", false);
        $.cookie("opt4", false);
    });

    $("#opt2").click(function() {
        $("#periods").hide();
        $("#basicInfo").hide();
        $("#preview").hide();
        $("#expertise").show();
        $.cookie("opt1", false);
        $.cookie("opt2", true);
        $.cookie("opt3", false);
        $.cookie("opt4", false);
        document.getElementById('formA').submit();
    });

    $("#opt3").click(function() {

        $("#basicInfo").hide();
        $("#expertise").hide();
        $("#preview").hide();
        $("#periods").show();
        $.cookie("opt1", false);
        $.cookie("opt2", false);
        $.cookie("opt3", true);
        $.cookie("opt4", false);
        document.getElementById('formA').submit();
    });

    $("#opt4").click(function() {
        
        $("#basicInfo").hide();
        $("#expertise").hide();
        $("#periods").hide();
        $("#preview").show();
        $.cookie("opt1", false);
        $.cookie("opt2", false);
        $.cookie("opt3", false);
        $.cookie("opt4", true);
        document.getElementById('formA').submit();
    });
    if (getCookie("opt1") === "true") {
         
        $("#expertise").hide();
        $("#periods").hide();
        $("#preview").hide();
         $("#basicInfo").show();
    }
    else if (getCookie("opt2") === "true") {
        
        $("#periods").hide();
        $("#basicInfo").hide();
        $("#preview").hide();
        $("#expertise").show();
    }
    else if (getCookie("opt3") === "true") {
         
        $("#basicInfo").hide();
        $("#expertise").hide();
        $("#preview").hide();
        $("#periods").show();
    }
    else if (getCookie("opt4") === "true") {
      
        $("#periods").hide();
        $("#basicInfo").hide();
        $("#expertise").hide();
        $("#preview").show();
    }
});

function setCookie(c_name, value, exdays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var c_value = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
    document.cookie = c_name;
}
function getCookie(c_name) {
    var i, x, y, ARRcookies = document.cookie.split(";");
    for (i = 0; i < ARRcookies.length; i++) {
        x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
        y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
        x = x.replace(/^\s+|\s+$/g, "");
        if (x === c_name) {
            return unescape(y);
        }
    }

}


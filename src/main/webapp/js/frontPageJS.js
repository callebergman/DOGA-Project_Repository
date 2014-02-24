$(document).ready(function()
{
    $("#opt1").click(function() {
        $("#expertise").hide();
        $("#periods").hide();
        $("#preview").hide();
        $("#basicInfo").show();

        setCookie("opt1", true, 365);
        setCookie("opt2", false, 365);
        setCookie("opt3", false, 365);
        setCookie("opt4", false, 365);




    });


    $("#opt2").click(function() {

        $("#periods").hide();
        $("#basicInfo").hide();
        $("#preview").hide();
        $("#expertise").show();
        setCookie("opt1", false, 365);
        setCookie("opt2", true, 365);
        setCookie("opt3", false, 365);
        setCookie("opt4", false, 365);
    });

    $("#opt3").click(function() {

        $("#basicInfo").hide();
        $("#expertise").hide();
        $("#preview").hide();
        $("#periods").show();
        setCookie("opt1", false, 365);
        setCookie("opt2", false, 365);
        setCookie("opt3", true, 365);
        setCookie("opt4", false, 365);
    });

    $("#opt4").click(function() {
        
        $("#basicInfo").hide();
        $("#expertise").hide();
        $("#periods").hide();
        $("#preview").show();
        setCookie("opt1", false, 365);
        setCookie("opt2", false, 365);
        setCookie("opt3", false, 365);
        setCookie("opt4", true, 365);
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
    document.cookie = c_name + "=" + c_value;
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


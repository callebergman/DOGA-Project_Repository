            $( document ).ready(function() {
                $("#expertise").hide();
                $("#periods").hide();
                
                
                $("#opt1").click(function(){
                    $("#basicInfo").show();
                    $("#expertise").hide();
                    $("#periods").hide();
                    setCookie("opt1", true, 365);
                    setCookie("opt2", false, 365);
                    setCookie("opt3", false, 365);
                });
                
                $("#opt2").click(function(){
                    $("#expertise").show();
                    $("#periods").hide();
                    $("#basicInfo").hide();
                    setCookie("opt1", false, 365);
                    setCookie("opt2", true, 365);
                    setCookie("opt3", false, 365);
                });
                
                $("#opt3").click(function(){
                    $("#periods").show();
                    $("#basicInfo").hide();
                    $("#expertise").hide();
                    setCookie("opt1", false, 365);
                    setCookie("opt2", false, 365);
                    setCookie("opt3", true, 365);
                });
         
                
                if(getCookie("opt2") == "true"){
                    $("#expertise").show();
                    $("#periods").hide();
                    $("#basicInfo").hide();
                }
                else if(getCookie("opt3") == "true"){
                    $("#periods").show();
                    $("#basicInfo").hide();
                    $("#expertise").hide();
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
                    if (x == c_name) {
                        return unescape(y);
                    }
                }
            }
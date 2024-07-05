$(document).ready(function () {
	
	/* var loaderParm = document.getElementById('loading');
		
if (loaderParm == null) {
var loader = $('<div id="loading"><div class="image" align="center"><img alt="Loading..." src="/images/loading.gif"/></div><div class="load"> </div></div>')
 //alert (" cek ");
 //.css({position: "relative", top: "1em", left: "1em"})

.hide()
                          .appendTo("body");

                        $(document).ajaxStart(function() {
                                    loader.show();
                        }).ajaxStop(function() {
                                    loader.hide();
                        });                      
            } */
 });

 

var please_wait = null;

var xmlHttp = null;

 

try {

    // Firefox, Opera 8.0+, Safari

    xmlHttp=new XMLHttpRequest();

} catch (e) {

    // Internet Explorer

    try {

        xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");

    } catch (e) {

        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");

    }

}

 

function open_url(url, target) {

            if ( ! document.getElementById) {

                        return false;

            }

 

            if (please_wait != null) {

                        document.getElementById(target).innerHTML = please_wait;

            }

 

            if (window.ActiveXObject) {

                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
						

            } else if (window.XMLHttpRequest) {

                        xmlHttp = new XMLHttpRequest();

            }

 

            if (xmlHttp == undefined) {

                        return false;

            }

            xmlHttp.onreadystatechange = function() { response(url, target); }

            xmlHttp.open("GET", url, true);

            xmlHttp.send(null);
			

}

 

function response(url, target) {

            if (xmlHttp.readyState == 4) {

                        document.getElementById(target).innerHTML = (xmlHttp.status == 200) ? xmlHttp.responseText : "Ooops!! A broken link! Please contact the webmaster of this website ASAP and give him the fallowing errorcode: " + xmlHttp.status;

            }

}

 

function set_loading_message(msg) {

            please_wait = msg;

}

 

function validateForm(idform, actionform, target) {

    var form = document.getElementById(idform);

            form.action = actionform;

            form.submit();

            open_url(actionform, target);

}


function open_url_to_div(pageAction, scriptAction) {
	$.get(pageAction,function(data) {

                        $("#tableview").html(data);

//Loading waktu klik
        $.getScript(scriptAction,function (data) {
//alert (pageAction, scriptAction);
//alert (pageAction);

//var $xxx=pageAction;
//alert (scriptAction);
        });
		
		

   });       

}

function open_url_to_div2(pageAction, scriptAction, scriptAction2) {

   var opt = { md:scriptAction2 }

   $.get(pageAction, opt, function(data) {

                        $("#tableview").html(data);

        $.getScript(scriptAction,function (data) {

        });

   });        

 

}


/* Modified by CDR, 03/03/2014 */
function gantinewPage2(modul,fungsi,currentPage,param1,param2,param3,param4,divId)
{
	var opt = {currentPage : currentPage, param1 : param1, param2 : param2, param3 : param3 , param4 : param4};
	jQuery.get(modul,opt,function(data) {
		$("#"+divId).html(data);	
       if(fungsi)
			{
				//alert(fungsi);
				jQuery.getScript(fungsi);
			}		
	});
}

function jumpto2(handler,totalPages,fungsi,param1,param2,param3,param4, divId){
	var jump_page = window.prompt("Masukkan no halaman yang ingin Anda tuju:");
	totalPages = totalPages*1;
	var currentPage = jump_page*1;
	
	if (jump_page !== null && !isNaN(jump_page) && jump_page > 0 && (currentPage <= totalPages))
	{
		var opt = {currentPage : jump_page , jumpPage : 1, param1 : param1, param2 : param2, param3 : param3 , param4 : param4, paging :'paging'};

		jQuery.get(handler,opt,function(data) {
			$("#"+divId).html(data);	
			//$("a[@class=newPage]").unbind("click");			
			//$("a[@class=newPage]").bind("click",fungsi);
			if(fungsi)
			{
				//alert(fungsi);
				jQuery.getScript(fungsi);
			}
		});
	}
	else
	{		
		alert("halamanTerakhir : "+totalPages);
	}
	
}

function gantinewPage3(modul,fungsi,currentPage,param1,param2,param3,param4,param5,param6,divId)
{
//alert(param1+' | '+param2);	
	var opt = {currentPage : currentPage, param1 : param1, param2 : param2, param3 : param3 , param4 : param4, param5 : param5 , param6 : param6};
	jQuery.get(modul,opt,function(data) {
		$("#"+divId).html(data);

		if (divId == 'popupview'){
			centerPopup();
			loadPopup();
			document.location.href="#topbar";
		}
       if(fungsi)
		{
			jQuery.getScript(fungsi);
		}	
		
	});	
	
}

function gantinewPage5(modul,fungsi,currentPage,param1,param2,param3,param4,param5,param6)
{
//alert(param1+' | '+param2);	
	var opt = {currentPage : currentPage, param1 : param1, param2 : param2, param3 : param3 , param4 : param4, param5 : param5 , param6 : param6};
	jQuery.get(modul,opt,function(data) {
		$("#tableview").html(data);	
       if(fungsi)
			{
				//alert(fungsi);
				jQuery.getScript(fungsi);
			}		
	});
	
}

function gantinewPage10(modul,fungsi,currentPage,param1,param2,param3,param4,param5,param6,param7,param8,param9,param10,param11)
{
//alert(param1+' | '+param2);	
	var opt = {currentPage : currentPage, param1 : param1, param2 : param2, param3 : param3 , param4 : param4, param5 : param5 , param6 : param6, param7 : param7, param8 : param8, param9 : param9, param10 : param10, param11 : param11};
	jQuery.get(modul,opt,function(data) {
		$("#tableview").html(data);	
       if(fungsi)
			{
				//alert(fungsi);
				jQuery.getScript(fungsi);
			}		
	});
	
}

function jumpto3(handler,totalPages,fungsi,param1,param2,param3,param4,param5,param6, divId){
	var jump_page = window.prompt("Masukkan no halaman yang ingin Anda tuju:");
	totalPages = totalPages*1;
	var currentPage = jump_page*1;
	
	if (jump_page !== null && !isNaN(jump_page) && jump_page > 0 && (currentPage <= totalPages))
	{
		var opt = {currentPage : jump_page , jumpPage : 1, param1 : param1, param2 : param2, param3 : param3 , param4 : param4, param5 : param5 , param6 : param6, paging :'paging'};

		jQuery.get(handler,opt,function(data) {
			$("#"+divId).html(data);	
			//$("a[@class=newPage]").unbind("click");			
			//$("a[@class=newPage]").bind("click",fungsi);
			if(fungsi)
			{
				//alert(fungsi);
				jQuery.getScript(fungsi);
			}
		});
	}
	else
	{		
		alert("halamanTerakhir : "+totalPages);
	}
	
}

function jumpto5(handler,totalPages,fungsi,param1,param2,param3,param4,param5,param6){
	var jump_page = window.prompt("Masukkan no halaman yang ingin Anda tuju:");
	totalPages = totalPages*1;
	var currentPage = jump_page*1;
	
	if (jump_page !== null && !isNaN(jump_page) && jump_page > 0 && (currentPage <= totalPages))
	{
		var opt = {currentPage : jump_page , jumpPage : 1, param1 : param1, param2 : param2, param3 : param3 , param4 : param4, param5 : param5 , param6 : param6, paging :'paging'};

		jQuery.get(handler,opt,function(data) {
			$("#tableview").html(data);	
			//$("a[@class=newPage]").unbind("click");			
			//$("a[@class=newPage]").bind("click",fungsi);
			if(fungsi)
			{
				//alert(fungsi);
				jQuery.getScript(fungsi);
			}
		});
	}
	else
	{		
		alert("halamanTerakhir : "+totalPages);
	}
	
}

function jumpto10(handler,totalPages,fungsi,param1,param2,param3,param4,param5,param6,param7,param8,param9,param10,param11){
	var jump_page = window.prompt("Masukkan no halaman yang ingin Anda tuju:");
	totalPages = totalPages*1;
	var currentPage = jump_page*1;
	
	if (jump_page !== null && !isNaN(jump_page) && jump_page > 0 && (currentPage <= totalPages))
	{
		var opt = {currentPage : jump_page , jumpPage : 1, param1 : param1, param2 : param2, param3 : param3 , param4 : param4, param5 : param5 , param6 : param6, param7 : param7, param8 : param8 , param9 : param9, param10 : param0 , param11 : param11, paging :'paging'};

		jQuery.get(handler,opt,function(data) {
			$("#tableview").html(data);	
			//$("a[@class=newPage]").unbind("click");			
			//$("a[@class=newPage]").bind("click",fungsi);
			if(fungsi)
			{
				//alert(fungsi);
				jQuery.getScript(fungsi);
			}
		});
	}
	else
	{		
		alert("halamanTerakhir : "+totalPages);
	}
	
}

function windowOpens(url){
	var w=0;
	var h=0;
	w=screen.availWidth;
	h=screen.availHeight;
	var popW=800,popH=500;
	var leftc=(w-popW)/2;
	var topc=(h-popH)/2;
	var selectWindow=window.open(url,'Selection','left='+leftc+',top='+topc+', width='+popW+',height='+popH+',resizable=0,scrollbars=yes')
}
/* end Modified by CDR, 03/03/2014 */

/* Modified by CDR, 03/10/2014 */
function doCount(countdown) {
	document.getElementById("confirm").style.display="block";
	
	if (countdown > 0) {
        countdown=countdown-1;
		window.status=countdown + " seconds left to view this page.";
		setTimeout('doCount()',5000); 
    }
    else {
        document.getElementById("confirm").style.display="none";
    } 


}

 function compareDate(dstrt,dfins){ //(DD-MM-YYYY)
	/* dstrt=dstrt.replace("/","");
	dstrt=dstrt.replace("-","");
	dfins=dfins.replace("/","");
	dfins=dfins.replace("-",""); */
	
	//alert(dstrt);
	var dstrtArr = dstrt.split('-');
	var dd1 = dstrtArr[0];
	var mm1 = dstrtArr[1] - 1;
	var yy1 = dstrtArr[2];
	
	var dfinsArr = dfins.split('-');
	var dd2 = dfinsArr[0];
	var mm2 = dfinsArr[1] - 1;
	var yy2 = dfinsArr[2];
	
	var D1 = new Date(yy1, mm1,dd1);
	var D2 = new Date(yy2, mm2,dd2 );
	
	//alert(dd1+" | "+mm1+" | "+yy1+" | tgl 2 "+dd2+" | "+mm2+" | "+yy2);
	//alert(D1+" | "+D2);
	
	if (D1>D2){
		alert ("Tanggal Awal lebih besar dari Tanggal Akhir.");
		
		/* if(document.getElementById('finishdate')){
			document.getElementById('finishdate').focus();
		} */
		return false;
	}else{
		return true;
	}
}

function toUpper(divID){
	var str1 = document.getElementById(divID).value;
	var strUpper = str1.toUpperCase(); 
	
	document.getElementById(divID).value = strUpper;
}

function isNumber(id){
	var my_string=document.getElementById(id).value;
	
	if(isNaN(my_string)){
		alert('Isikan dengan angka.');
		document.getElementById(id).value='';
		document.getElementById(id).focus();
		
	}
}

function numberFormat(divID){
	var dataAwal = '';
	var dataAwal = document.getElementById(divID).value.split('.').join('');//replace(/./g,'');
	//var RE = /^-{0,1}\d*\.{0,1}\d+$/;
	var RE = /[0-9,]+$/;
	
	if(!RE.test(dataAwal)){
		alert('Isikan dengan angka.');
		document.getElementById(divID).focus();
		document.getElementById(divID).value = '';
	} else {
		document.getElementById(divID).value = FormatNumberBy3(dataAwal, ',', '.');	
	}
}

function FormatNumberBy3(num, decpoint, sep) {
  // check for missing parameters and use defaults if so
  if (arguments.length == 2) {
    sep = ",";
  }
  if (arguments.length == 1) {
    sep = ",";
    decpoint = ".";
  }
  // need a string for operations
  num = num.toString();
  // separate the whole number and the fraction if possible
  a = num.split(decpoint);
  x = a[0]; // decimal
  y = a[1]; // fraction
  z = "";

  
  if (typeof(x) != "undefined") {
    // reverse the digits. regexp works from left to right.
    for (i=x.length-1;i>=0;i--)
      z += x.charAt(i);
    // add seperators. but undo the trailing one, if there
    z = z.replace(/(\d{3})/g, "$1" + sep);
    if (z.slice(-sep.length) == sep)
      z = z.slice(0, -sep.length);
    x = "";
    // reverse again to get back the number
    for (i=z.length-1;i>=0;i--)
      x += z.charAt(i);
    // add the fraction back in, if it was there

    if (typeof(y) != "undefined" )//&& y.length > 0)
      x += decpoint + y;
  }
  
  return x;
}

function compareDate(dstrt,dfins){ //(DD-MM-YYYY)
	/* dstrt=dstrt.replace("/","");
	dstrt=dstrt.replace("-","");
	dfins=dfins.replace("/","");
	dfins=dfins.replace("-",""); */
	
	//alert(dstrt);
	var dstrtArr = dstrt.split('-');
	var dd1 = dstrtArr[0];
	var mm1 = dstrtArr[1] - 1;
	var yy1 = dstrtArr[2];
	
	var dfinsArr = dfins.split('-');
	var dd2 = dfinsArr[0];
	var mm2 = dfinsArr[1] - 1;
	var yy2 = dfinsArr[2];
	
	var D1 = new Date(yy1, mm1,dd1);
	var D2 = new Date(yy2, mm2,dd2 );
	
	//alert(dd1+" | "+mm1+" | "+yy1+" | tgl 2 "+dd2+" | "+mm2+" | "+yy2);
	//alert(D1+" | "+D2);
	
	if (D1>D2){
		alert ("Tanggal Awal lebih besar dari Tanggal Akhir.");
		exit();
		//document.getElementById('finishdate').focus();
		return false;
	}else{
		return true;
	}
}

 

function moneyFormat(divID){
	var dataAwal = document.getElementById(divID).value;
	if(!isNaN(dataAwal)){
		var number = numeral(dataAwal);
		number.format();
		numeral.defaultFormat('0.0,00');
		
		document.getElementById(divID).value =  number.format();
	}
} 
/* end Modified by CDR, 03/10/2014  */

/* print report */
function PrintElem(elem)
    {
        Popup($(elem).html());
    }

    function Popup(data) 
    {
        var mywindow = window.open('', 'my div', 'height=600,width=900');
        mywindow.document.write("<html><head><link rel='stylesheet' href='css/layout.css' type='text/css' /><title>Cetak</title>");
        /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
        mywindow.document.write("</head><body ><div class=''wrapper col3'><div id='homecontent'><div class='panel'>");
		mywindow.document.write(data);
        mywindow.document.write('</div></div></div></body></html>');

        mywindow.document.close(); // necessary for IE >= 10
        mywindow.focus(); // necessary for IE >= 10

       mywindow.print();
       mywindow.close();

        return true;
    }

function zeroPad(num, places) {
  var zero = places - num.toString().length + 1;
  return Array(+(zero > 0 && zero)).join("0") + num;
}	

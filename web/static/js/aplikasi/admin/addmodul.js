$(document).ready(function() {
});

    $(function(){
   $("input[type='checkbox']").change(function() { 
      if($("#ceksuperadmin").is(':checked')){$("#modulSuperAdmin").val('1')}
      else{$("#modulSuperAdmin").val('0')}
      if($("#cekadmin").is(':checked')){$("#modulAdmin").val('1')}
      else{$("#modulAdmin").val('0')}
      if($("#ceksudin").is(':checked')){$("#modulSudin").val('1')}
      else{$("#modulSudin").val('0')}
      if($("#cekpa").is(':checked')){$("#modulPA").val('1')}
      else{$("#modulPA").val('0')}
      if($("#cekpk").is(':checked')){$("#modulPK").val('1')}
      else{$("#modulPK").val('0')}
   });
    });
    

function formatnomodul(nilai) {
    var noModul = nilai;
    var noModul1, noModul2, noModul3;
    var splited = noModul.split(".");
    if (splited.length == 1) {
        if (splited[0].length == 1) {
            $("#noModul").val("0" + splited[0]);
        }
    }
    else if (splited.length == 2) {
        noModul1 = splited[0];
        noModul2 = splited[1];
        if (splited[0].length == 1) {
            noModul1 = "0" + splited[0];
        }
        if (splited[1].length == 1) {
            noModul2 = "0" + splited[1]
        }
        $("#noModul").val(noModul1 + "." + noModul2);
    }
    else if (splited.length == 3) {
        noModul1 = splited[0];
        noModul2 = splited[1];
        noModul3 = splited[2];
        if (splited[0].length == 1) {
            noModul1 = "0" + splited[0];
        }
        if (splited[1].length == 1) {
            noModul2 = "0" + splited[1];
        }
        if (splited[2].length == 1) {
            noModul3 = "0" + splited[2]
        }
        $("#noModul").val(noModul1 + "." + noModul2 + "." + noModul3);
    }
}


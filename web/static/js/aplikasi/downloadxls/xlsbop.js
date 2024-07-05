/*
 *
 *
 *
 */

$(document).ready(function() {

});


function cetakjurnalxls() {
    window.location.href = getbasepath() + "/rkasbkus/xls/bkuxls?tahun=" + $("#tahun").val()
            + "&npsn=" + $('#npsn').val();
}


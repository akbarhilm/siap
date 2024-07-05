$(document).ready(function() {
    $("#btnCetak").hide();
});

function getPengguna() {
    var nrk = $("#nrk").val();
    $.getJSON(getbasepath() + "/reset/json/getPengguna", {nrk: nrk},
    function(result) {
        if (result.aData != null) {
            var id = result.aData['idPengguna'];
            var sekolah = result.aData['namaSekolahPendek'];
            var namapengguna = result.aData['namaPengguna'];
            $("#idpengguna").val(id);
            $("#namapengguna").val(namapengguna);
            $("#sekolah").val(sekolah);
        } else {
            bootbox.alert("Data NRK tidak ada");
        }
    });
}

function hapuspassword() {
    $("#idpengguna").val("");
    $("#namapengguna").val("");
    $("#sekolah").val("");
    $("#passwordBaru").val("");
    $("#btnCetak").hide();
    $("#btnSimpan").show();
}

function generatePassword() {
    $.getJSON(getbasepath() + "/reset/json/generatePassword", {},
            function(result) {
                var newPassword = result.aData;
                $("#passwordBaru").val(newPassword);
            });
}

function simpan() {
    if ($("#passwordBaru").val() != '' && $("#passwordBaru").val() != null && $("#idpengguna").val() != '' && $("#idpengguna").val() != null) {
        var varurl = getbasepath() + "/reset/json/simpanpassword";
        var dataac = [];
        var data = {idpengguna: $("#idpengguna").val(), sandi: $("#passwordBaru").val()}
        dataac = data;
        $.ajax({
            type: "POST",
            url: varurl,
            contentType: "text/plain; charset=utf-8",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'},
            data: JSON.stringify(dataac)}).always(function() {
            bootbox.alert("Password baru berhasil disimpan");
            $("#btnCetak").show();
            $("#btnSimpan").hide();
        });
    } else {
        bootbox.alert("Lengkapi data terlebih dahulu");
    }
}

function cetak() {
    var idpengguna = $('#idpengguna').val(); 
    var nrk = $('#nrk').val(); 
    
    if (idpengguna == "") {
        bootbox.alert("Data Tidak Tersedia"); 
    } else { 
        window.location.href = getbasepath() + "/reset/json/prosescetak?idpengguna=" + idpengguna + "&nrk=" + nrk;
    }
}
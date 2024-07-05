$(document).ready(function() {

});
function cekSimpan(result) {
    var str = $("#passwordBaru").val();
    var panjang = str.length;

    if ($("#passwordLama").val() !== "" && $("#passwordBaru").val() !== "" && $("#passwordBaruVerify").val() !== "") {
        if ($("#passwordLama").val() !== $("#passpengguna").val()) {
            bootbox.alert("Password Lama Tidak Sesuai");
        } else if ($("#passwordBaru").val() !== $("#passwordBaruVerify").val()) {
            bootbox.alert("Password Baru Tidak Sesuai");
        } else if (result != 1) {
            bootbox.alert("Password Tidak Boleh Sama Dengan Password Saat Ini dan 5 Password Terakhir");
        } else if (panjang < 5 || panjang > 16) {
            bootbox.alert("Panjang Karakter : 5-16 Karakter");
        } else {
            simpan();
        }
    } else {
        bootbox.alert("Pengisian Data Harus Lengkap");
    }


}

function simpan() {

    var varurl = getbasepath() + "/ubahpass/json/prosesubah";
    var dataac = [];

    var datajour = {
        user: $("#pengguna").val(),
        pass: $("#passwordBaru").val()
    };

    dataac = datajour;

    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {
        bootbox.alert("Password Berhasil Diubah");
    });
}
function checkpassword() {
    $.getJSON(getbasepath() + "/ubahpass/json/checkpassword", {user: $("#pengguna").val(), pass: $("#passwordBaru").val()},
    function(result) {
        cekSimpan(result)
    });
}

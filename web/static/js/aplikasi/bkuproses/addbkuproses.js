$(document).ready(function() {
    getSkpd();
});

function getSkpd() {
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bkuproses/json/getSkpd", {idskpd: idskpd},
    function(result) {

        var kode = result.aData['kodeSkpd'];
        var nama = result.aData['namaSkpd'];

        $("#skpd").val(kode + "/" + nama);

    });
}

function setmaxtgl(tgl) {
    if (parseInt(tgl) > 31) {
        bootbox.alert("Tanggal Tidak Boleh Lebih Besar dari 31");
        $("#tanggal").val("");
    }
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function cekSimpan() {
    if ($("#tanggal").val() == "") {
        bootbox.alert("Isi Tanggal Terlebih Dulu");
    } else if ($("#triwulan").val() == "-") {
        bootbox.alert("Pilih Triwulan Terlebih Dulu");
    } else if ($("#bulan").val() == "-") {
        bootbox.alert("Pilih Bulan Terlebih Dulu");
    } else {
        simpan();
    }
}

function simpan() {
    var varurl = getbasepath() + "/bkuproses/json/prosessimpan";
    var dataac = [];
    var bataswaktu;
    var tgl;

    if ($("#tanggal").val().length < 2) {
        tgl = "0" + $("#tanggal").val();
    } else {
        tgl = $("#tanggal").val();
    }

    bataswaktu = tgl + "-" + $("#bulan").val();

    var datajour = {
        idskpd: $("#idskpd").val(),
        triwulan: $("#triwulan").val(),
        sumbdana: $("#kodeSumbdana").val(),
        bataswaktu: bataswaktu

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


        bootbox.alert("Data Batas Waktu Pengajuan Belanja Sekolah Berhasil Disimpan");
        $('#btnSimpan').attr("disabled", true);
    });
}
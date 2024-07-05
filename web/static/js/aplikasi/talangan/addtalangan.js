$(document).ready(function() {
    getMcb();
});



function getMcb() {
    var idsekolah = $('#idsekolah').val();
    console.log("M " + idsekolah);
    $.getJSON(getbasepath() + "/danatalangan/json/getMcb", {idsekolah: idsekolah},
    function(result) {
        var banyak, kode, ket;
        var opt = '<option value="0">--Pilih Mcb--</option>'; // untuk tampilan awal combo

        banyak = result.aData.length;
        console.log("n " + banyak);
        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                console.log("i " + i);
                opt += '<option value="' + result.aData[i]['id'] + '" >' + result.aData[i]['idMcb'] + ' / ' + result.aData[i]['namaMcb'] + '</option>';
            }
        }

        $("#cbmcb").html(opt);
    });
}

function getData() {
    var nrk = $("#nrkPptk").val();
    console.log(nrk + " cuy");
    var user = 'sipkdprod';
    var pass = 'sipkdprod!!';
//    var user = 'sipkddev';
//    var pass = 'sipkddev';

    if ((nrk != "")) {
//        $.getJSON("http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD", {nrk: nrk},
//        function(data) {
//            if (data.results[0] != null) {
//                $("#namaPptk").val(data.results[0]['NAMA']);
//                $("#nipPptk").val(data.results[0]['NIP18']);
//            } else {
//                bootbox.alert("Data tidak ada");
//            }
//        }
//        );

        var varurl = getbasepath() + "/bkubos/json/getpegawai";
//        var varurl = "https://cors-anywhere.herokuapp.com/http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegSIPKD";
//        var varurl = "https://cors-anywhere.herokuapp.com/http://soadki.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD";
//        var varurl = "http://" + user + ":" + pass + "@soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegSIPKD";
//        var varurl = "http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegSIPKD";
//        var varurl = "http://soadki.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD";
        var dataac = [];
        console.log("NRK: " + nrk);
        var datajour = {
            nrk: nrk
        };
        dataac = datajour;
//        console.log("DATAAC: " + JSON.stringify(dataac));
//
        $.ajax({
            url: varurl,
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(dataac),
            contentType: 'text/plain; charset=utf-8',
            headers: {
                'Accept': 'application/json, text/javascript',
                'Content-Type': 'application/json',
            },
            success: function(result) {
                if (result != null) {
                    $("#namaPptk").val(result['NAMA']);
                    $("#nipPptk").val(result['NIP18']);
                } else {
                    bootbox.alert("Data tidak ada");
                }
            },
            error: function() {
                bootbox.alert('boo!');
            },
        });
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

function setformatpengeluaran(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");

    $('#nilai').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));

}


function cleardata() {

    $('#tglDok').val("");
    $('#nilai').val("");
    $('#uraian').val("");

}
function print() {
    console.log("idsekolah " + $("#idsekolah").val());
    console.log("idskpd " + $("#idskpd").val());
}
function check() {
    $('#btnSimpan').attr("disabled", true);
//    var tglDok = $("#tglDok").val();
    var nrk = $("#nrkPptk").val();
    var namaMcb = $("#namaMcb").val();
    var idMcb = $("#idMcb").val();
    var nippptk = $("#nipPptk").val();
    var namapptk = $("#namaPptk").val();
//    var tahunTglDok = tglDok.substr(6, 4);

    var nilai = accounting.unformat($("#nilai").val(), ",");


    var table3 = document.getElementById('spjtablebody');
    var rows3 = table3.rows;
    var jum3 = rows3.length;
    var bank = $("#namaBank").val();
    var norek = $("#rekeningBank").val();
    var namarekan = $("#namarekanan").val();


    if (nilai <= 0) {
        bootbox.alert("Pengisian Data Harus Lengkap");
        $('#btnSimpan').attr("disabled", false);
    } else {
        simpan();
    }
}

function simpan() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var idsekolah = $("#idsekolah").val();
    var kodesumbdana = $("#jenis").val();
    var triwulan = $("#triwulan").val();
    console.log("OU " + triwulan);
    console.log("id " + idsekolah + "  " + idskpd);
    var nilai = accounting.unformat($("#nilai").val(), ",");
    console.log("bulan " + $("#bulan").val());
    var varurl = getbasepath() + "/danatalangan/json/prosessimpantalangan";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        bulan: $("#bulan").val(),
        idsekolah: idsekolah,
        idskpd: idskpd,
        triwulan: triwulan,
//        tgldok: $("#tglDok").val(),
        kodesumbdana: kodesumbdana,
        idmcb: $("#cbmcb").val(),
        nrk: $("#nrkPptk").val(),
//        namapptk: $("#namaPptk").val(),
        namapptk: '12345',
//        nippptk: $("#nipPptk").val(),
        nippptk: '12345',
        uraian: $("#uraian").val(),
        nilai: nilai
    };
    dataac = datajour;

    return   $.ajax({
        type: "POST",
        url: varurl, contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {

        cleardata();

        bootbox.alert("Data Talangan Berhasil Disimpan");
        $('#btnSimpan').attr("disabled", false);
    });
}
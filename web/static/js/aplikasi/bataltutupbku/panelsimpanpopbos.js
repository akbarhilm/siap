/*
 *
 *
 *
 *
 */

$(document).ready(function() {
    id_value = window.localStorage.getItem("idsekolahval");
    bln_value = window.localStorage.getItem("triwulanval");
    bton_value = window.localStorage.getItem("kodebutton");
    console.log("bton_value == " + bton_value);
    $('#triwulan').val(bln_value);
    $('#idsekolah').val(id_value);

    setKeteranganTriwulan(bln_value);

    //$("#tanggal").datepicker("setDate", new Date()); // set sysdate untuk tanggal
    //setTanggalTutup();
    getBendahara();
    getNilaiKas();
    getNilaiSaldo();


});

//global variable
var id_value, bln_value, bton_value;

function getNilaiSaldo() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();
    //var kodewilproses = window.localStorage.getItem("wilayahsp2dproses_val");

    $.getJSON(getbasepath() + "/bataltutupbkubos/json/getNilaiSaldo", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan}, //, kodewilproses: kodewilproses},
    function(result) {

        var saldotunai = result.aData['saldoTunai'];
        var saldobank = result.aData['saldoBank'];
        var saldopanjar = result.aData['saldoLain'];


        $('#saldoTunai').val(accounting.formatNumber(saldotunai));
        $('#saldoBank').val(accounting.formatNumber(saldobank));
        $('#saldoLain').val(accounting.formatNumber(saldopanjar));


        //console.log("Kas Saat Ini"+kassaatini);
    });
}

function getNilaiKas() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();
    //var kodewilproses = window.localStorage.getItem("wilayahsp2dproses_val");

    //console.log("bulan == " + bulan);

    $.getJSON(getbasepath() + "/bataltutupbkubos/json/getNilaiKas", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan}, //, kodewilproses: kodewilproses},
    function(result) {

        var kasterimalalu = result.aData['kasTerimaLalu'];
        var kaskeluarlalu = result.aData['kasKeluarLalu'];
        var kasterima = result.aData['kasTerima'];
        var kaskeluar = result.aData['kasKeluar'];
        var kassaatini = result.aData['kasSaatIni'];

        $('#kasTerimaLalu').val(accounting.formatNumber(kasterimalalu));
        $('#kasKeluarLalu').val(accounting.formatNumber(kaskeluarlalu));
        $('#kasTerima').val(accounting.formatNumber(kasterima));
        $('#kasKeluar').val(accounting.formatNumber(kaskeluar));
        $('#kasSaatIni').val(accounting.formatNumber(kassaatini));

        //console.log("Kas Saat Ini"+kassaatini);
    });
}

function getBendahara() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/bataltutupbkubos/json/getBendahara", {tahun: tahun, idsekolah: idsekolah},
    function(result) {

        $('#nipPA').val(result.aData['nipPA']);
        $('#nrkPA').val(result.aData['nrkPA']);
        $('#namaPA').val(result.aData['namaPA']);
        $('#nipBendahara').val(result.aData['nipBendahara']);
        $('#nrkBendahara').val(result.aData['nrkBendahara']);
        $('#namaBendahara').val(result.aData['namaBendahara']);


        //console.log("Kas Saat Ini"+kassaatini);
    });
}
/*
 function setTanggalTutup() {

 var now = new Date();
 var day = ("0" + now.getDate()).slice(-2);
 var month = ("0" + (now.getMonth() + 1)).slice(-2);
 var tanggal = now.getFullYear() + month + day;
 var tanggalview = day + "/" + month + "/" + now.getFullYear();

 $('#tanggal').val(tanggalview);

 // console.log("tanggal tutup = "+tanggal);

 $('#tglPenutupan').val(tanggal);

 }
 */
function setKeteranganTriwulan(triwulan) {
    var kettriwulan;

    if (triwulan == "1") {
        kettriwulan = "Triwulan 1";

    } else if (triwulan == "2") {
        kettriwulan = "Triwulan 2";

    } else if (triwulan == "3") {
        kettriwulan = "Triwulan 3";

    } else if (triwulan == "4") {
        kettriwulan = "Triwulan 4";

    } else {
        kettriwulan = "SALAH";
    }

    $('#ketTriwulan').val(kettriwulan);
}

function cekSaldoMinus() {
    var cek = false;
    var tunai = accounting.unformat($("#saldoTunai").val());
    var bank = accounting.unformat($("#saldoBank").val());
    var lain = accounting.unformat($("#saldoLain").val());
    //if (bton_value == 1) {
    if ((tunai < 0) || (bank < 0) || (lain < 0)) {
        cek = true;
    }
    //}
    return cek;
}

function cekSaldoNol() {
    var cek = false;
    var saldo = accounting.unformat($("#kasSaatIni").val());

    //if (bton_value == 1) {
    if (saldo == 0) {
        cek = true;
    }
    //}
    return cek;
}

function simpan() {
    //var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var tahun = $("#tahun").val();
    var idsekolah = $("#idsekolah").val();
    //var kodewilproses = window.localStorage.getItem("wilayahsp2dproses_val");

    //var varurl = getbasepath() + "/bataltutupbkubos/json/simpan";
    var dataac = [];
    var kodeTombol = bton_value;

    var datajour = {
        //kodewilproses: kodewilproses,
        kodetombol: kodeTombol,
        tahun: tahun,
        idsekolah: idsekolah,
        triwulan: $("#triwulan").val(),
        //tglpenutupan: $("#tglPenutupan").val(),
        nrkpa: $("#nrkPA").val(),
        nippa: $("#nipPA").val(),
        namapa: $("#namaPA").val(),
        nrkbend: $("#nrkBendahara").val(),
        nipbend: $("#nipBendahara").val(),
        namabend: $("#namaBendahara").val(),
        kasterimalalu: accounting.unformat($("#kasTerimaLalu").val()),
        kaskeluarlalu: accounting.unformat($("#kasKeluarLalu").val()),
        kasterima: accounting.unformat($("#kasTerima").val()),
        kaskeluar: accounting.unformat($("#kasKeluar").val()),
        kassaatini: accounting.unformat($("#kasSaatIni").val()),
        saldotunai: accounting.unformat($("#saldoTunai").val()),
        saldobank: accounting.unformat($("#saldoBank").val()),
        saldolain: accounting.unformat($("#saldoLain").val())
    }
    dataac = datajour;

    /*if (kodeTombol == 2) { // jika saldo minus dan proses tutup buku, maka pesan error dan tidak bisa simpan tutup BKU
        if (cekSaldoMinus()) {
            bootbox.alert("Ada Saldo Minus, Anda Tidak Bisa Melanjutkan Proses Tutup BKU, Silahkan Cek Lagi.");
        }
//        else if (!cekSaldoNol()) {
//            bootbox.alert("Nilai Saldo Kas Tidak Nol, Anda Tidak Bisa Melanjutkan Proses Tutup BKU, Silahkan Cek Lagi.");
//        }
        else {
            ajaxsimpan(dataac);
        }
    }
    else if (kodeTombol == 1) { // jika saldo minus dan proses draft, maka pesan error dan draft bisa diproses
        if (cekSaldoMinus()) {
            bootbox.alert("Ada Saldo Minus, Silahkan Cek Lagi Data BKU Anda.", function(result) {
                ajaxsimpan(dataac);
            });
        }
//        else if (!cekSaldoNol()) {
//            bootbox.alert("Nilai Saldo Kas Tidak Nol, Silahkan Cek Lagi Data BKU Anda.", function(result) {
//                ajaxsimpan(dataac);
//            });
//        }
        else {
            ajaxsimpan(dataac);
        }
    }
    else { // jika kode tombol belum terdefinisi
        bootbox.alert("Kode Tombol Belum Terdefinisi.");
    }
*/
            ajaxsimpan(dataac);

}

function ajaxsimpan(dataproses) {
    var varurl = getbasepath() + "/bataltutupbkubos/json/simpan";
    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataproses)
    }).always(function(data) {
        setbulantutup();
        bootbox.alert("Data Batal Tutup BKU Pengeluaran Berhasil Disimpan", function(result) {
            parent.$.fancybox.close();
        });
    });
}

function setbulantutup() {
    $('#triwulan', window.parent.document).val("").change();
    $('#updatetgl', window.parent.document).val("update").change();
}

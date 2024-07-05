$(document).ready(function() {

    setCurrentDate();
    cekDataSekolah();
    gridbku();
    getSaldoKas();
    cekStatusKodeProses();
    // set awal data WP di hide
    if ($('#kodeBilling').val() == "0") {
        document.getElementById("labelnpwppenyetor").style.display = "none";
        document.getElementById("labelnamapenyetor").style.display = "none";
        document.getElementById("labelalamat").style.display = "none";
        document.getElementById("labelkota").style.display = "none";
        document.getElementById("panelhasilinquiry").style.display = "none";
    }

});
var nrk;
var banyakpajak;
var idpajakpn = new Array();
var kodepajakpn = new Array();
var selisih;
var kodeproses;
var nosk = "000000000000000"; // default diisi 0 sebanyak 15
var nop = "000000000000000000"; // default diisi 0 sebanyak 18
var noidentitas = "0000000000000000"; // default diisi 0 sebanyak 16


function cekStatusKodeProses() {
    document.getElementById("btnSimpan").style.visibility = "visible";
    if ($('#kodeBilling').val() == "0") {
        kodeproses = "1" // create billing
        document.getElementById('btnSimpan').innerHTML = 'Proses Permohonan Kode Billing';
    } else {
        if ($('#tglBayar').val() == "" || $('#tglBayar').val() == null) {
            inquirybilling();
            kodeproses = "2"; // payment billing
            document.getElementById('btnSimpan').innerHTML = 'Proses Pembayaran Pajak';
        } else {
            kodeproses = "3"; // re-inquiry billing
            document.getElementById('btnSimpan').innerHTML = 'Proses Re-Inquiry Billing';
            document.getElementById("panelhasilinquiry").style.display = "none";
        }
    }
}

function setCurrentDate() {

    $.getJSON(getbasepath() + "/token/json/getDate", {},
            function(result) {
                var date = result.aData['dMohon'];
                $('#tglPosting').val(date.substr(0, 10));
            });
}

function cekDataSekolah() {
    if ($('#norekBank').val() == "" || $('#namaPengirimBank').val() == "") {
        bootbox.alert("Data Nomor Rekening BOP Sekolah Belum Tersedia, Lengkapi Data Referensi Terlebih Dulu");
        $('#btnSimpan').attr("disabled", true);
    }

    if ($('#npwpSekolah').val() == "" || $('#namaWpSekolah').val() == "" || $('#alamatWpSekolah').val() == "" || $('#kotaWpSekolah').val() == "") {
        bootbox.alert("Data NPWP Sekolah Belum Lengkap, Silahkan Lengkapi Data Referensi Terlebih Dulu");
        $('#btnSimpan').attr("disabled", true);
    }

}

function gridbku() {

    if (typeof myTable == 'undefined') {
        myTable = $('#bkustable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[50, 100, 500, -1], [50, 100, 500, "All"]],
            "iDisplayLength": 500,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/pajaktf/json/listbkubop",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idbku", value: $("#idBku").val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var nilai = "<input type='text' name='nilai" + index + "' id='nilai" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true' />";
                var kodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var kodekomponen = "<input type='hidden' id='kodekomponen" + index + "' value='" + aData['kodeKomponen'] + "' />";
                $('td:eq(0)', nRow).html(index + kodeakun + kodekomponen);
                $('td:eq(4)', nRow).html(nilai);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "6%"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center", "sWidth": "15%"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "center", "sWidth": "31%"},
                {"mDataProp": "namaKomponen", "bSortable": false, sClass: "center", "sWidth": "30%"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "right", "sWidth": "18%"}
            ]
        });
    } else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function setButton(id) {
    $('#idbutton').val(id);
}


function getSaldoKas() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();
    $.getJSON(getbasepath() + "/pajaktf/json/getSaldoKasBop", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan},
    function(result) {

        var saldo = result.aData['saldoKas'];
        //console.log("ini saldo " + saldo);
        $('#saldo').val(accounting.formatNumber(saldo, 2, '.', ","));
    });
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function cekbayar() {

    var saldo = accounting.unformat($("#saldo").val(), ",");
    var nilaibku = accounting.unformat($("#nilaiPajak").val(), ",");
    if ($("#tglPosting").val() == "" || $("#norekBank").val() == "" || $("#namaPengirimBank").val() == "") {
        bootbox.alert("Pengisian Data Harus Lengkap");
    } else if (saldo < nilaibku) {
        bootbox.alert("Saldo Kas Tidak Mencukupi untuk Melakukan Pembayaran Pajak");
    } else if ($('#npwpSekolah').val() == "" || $('#namaWpSekolah').val() == "" || $('#alamatWpSekolah').val() == "" || $('#kotaWpSekolah').val() == "") {
        bootbox.alert("Data NPWP Sekolah Belum Lengkap, Silahkan Lengkapi Data Referensi Terlebih Dulu");
    } else if ($('#npwpSekolah').val() == "" || $('#namaWpSekolah').val() == "" || $('#alamatWpSekolah').val() == "" || $('#kotaWpSekolah').val() == "") {
        bootbox.alert("Data NPWP Sekolah Belum Lengkap, Silahkan Lengkapi Data Referensi Terlebih Dulu");
    } else {
        cekstatus();
    }

}

function cekstatus() {
    document.getElementById("btnSimpan").style.visibility = "hidden";
    var kodetrx = $('#kodeTransaksi').val();
    $('#prosestransfer').attr("disabled", true);
    var npwp, namawp, alamatwp, kotawp, npwppenyetor, namapenyetor;
    if (kodetrx == "P2" || kodetrx == "P5" || (kodetrx == "P4" && $('#persenPot').val() == "0.5")) { // jika pajak apa npwp nya beda ??? P2 -> PPh Ps P2; PPN : P5; P4 : Pph Ps 4 Ayat 2 KHUSUS 0.5%
//console.log("masuk if beda npwp");
        npwp = $('#npwpRekanan').val();
        namawp = $('#namaWp').val();
        alamatwp = $('#alamatWp').val();
        kotawp = $('#kotaWp').val();
        npwppenyetor = $('#npwpSekolah').val();
        namapenyetor = $('#namaWpSekolah').val();
    } else { // npwp penyetor dan pemungut sama (Sekolah)
//console.log("masuk if sama npwp");

        npwp = $('#npwpSekolah').val();
        namawp = $('#namaWpSekolah').val();
        alamatwp = $('#alamatWpSekolah').val();
        kotawp = $('#kotaWpSekolah').val();
        npwppenyetor = $('#npwpSekolah').val();
        namapenyetor = $('#namaWpSekolah').val();
    }

// set ulang data WP
    $('#npwpRekanan').val(npwp);
    $('#namaWp').val(namawp);
    $('#alamatWp').val(alamatwp);
    $('#kotaWp').val(kotawp);
    var norek = $('#norekBank').val().substr(0, 12);
    var npwprekanan = npwp.substr(0, 15);
    namawp = namawp.substr(0, 30);
    alamatwp = alamatwp.substr(0, 50);
    kotawp = kotawp.substr(0, 30);
    npwppenyetor = npwppenyetor.substr(0, 15);
    namapenyetor = namapenyetor.substr(0, 50);
    var masapajak = $('#masaPajak').val().toString() + $('#tahunPajak').val().toString();
    var t = document.getElementById("kodeTransaksi");
    var deskpajak = t.options[t.selectedIndex].text;
    var uraian = deskpajak + "/" + $('#noBkuMohon').val() + "/" + $('#npsn').val();

    if (npwp == "" || namawp == "" || alamatwp == "" || kotawp == "" || npwppenyetor == "" || namapenyetor == "") {
        bootbox.alert("Data Wajib Pajak Penyetor Tidak Lengkap");
        document.getElementById("btnSimpan").style.visibility = "visible";
    } else {
        if (kodeproses == "1") { // create billing
            simpanCreate(npwprekanan, namawp, alamatwp, kotawp, npwppenyetor, namapenyetor, norek, masapajak, uraian);
        } else if (kodeproses == "2") { // cek dulu dengan data DJP
            var nilaipajak = accounting.unformat($("#nilaiPajak").val(), ",");
            var nilaidjp = accounting.unformat($("#djpnilai").val(), ",");

            if ($('#kodeBilling').val().trim() !== $('#djpkodebill').val().trim()) {
                bootbox.alert("Data Kode Billing Tidak Sama dengan Data Pajak DJP");
                document.getElementById("btnSimpan").style.visibility = "visible";
            } /* else if ($('#npwpRekanan').val() !== $('#djpnpwp').val()) {
             bootbox.alert("Data NPWP Penyetor Tidak Sama dengan Data Pajak DJP");
             } else if ($('#namaWp').val().substr(0, 30) !== $('#djpnamawp').val()) {
             bootbox.alert("Data Nama Wajib Pajak Tidak Sama dengan Data Pajak DJP");
             } else if ($('#alamatWp').val().substr(0, 50) !== $('#djpalamat').val()) {
             bootbox.alert("Data Alamat Wajib Pajak Tidak Sama dengan Data Pajak DJP");
             } */ else if (nilaipajak !== nilaidjp) {
                bootbox.alert("Data Nilai Pajak Tidak Sama dengan Data Pajak DJP");
                document.getElementById("btnSimpan").style.visibility = "visible";
            } else { // payment billing
                simpanPayBilling(npwppenyetor, namapenyetor, norek, uraian);
            }
        } else { // re-inquiry status bpn
            simpanReinquiry();
        }
    }

}

function simpanCreate(npwprekanan, namawp, alamatwp, kotawp, npwppenyetor, namapenyetor, norek, masapajak, uraian) {

    var varurl = getbasepath() + "/pajaktf/json/simpandjpcreate";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idbku: $('#idBku').val(),
        kodetrx: $('#kodeTransaksi').val(),
        idsekolah: $('#idsekolah').val(),
        norek: norek,
        npwprekanan: npwprekanan,
        namawp: namawp,
        alamatwp: alamatwp,
        kotawp: kotawp,
        kjs: $('#kodeJenisSetor').val(),
        akupajak: $('#akunPajak').val(),
        masapajak: masapajak,
        tahunpajak: $('#tahunPajak').val(),
        nosk: nosk,
        nop: nop,
        noidentitas: noidentitas,
        nilaipajak: accounting.unformat($("#nilaiPajak").val(), ",").toString(),
        npwppenyetor: npwppenyetor,
        namapenyetor: namapenyetor,
        uraian: uraian,
        koderequest: kodeproses,
        kodeapp: "003" // 003 untuk SIAP-BOP
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
        $('#idrequest').val(data.idrequest);
        $('#bulkid').val(data.bulkid);
        transferPaymentSsp(npwprekanan, namawp, alamatwp, kotawp, npwppenyetor, namapenyetor, norek, masapajak, uraian); // create billing code

    });
}

function transferPaymentSsp(npwprekanan, namawp, alamatwp, kotawp, npwppenyetor, namapenyetor, norek, masapajak, uraian) {

    var varurl = getbasepath() + "/pajaktf/json/create"; // create kode billing
    var dataac = [];
    var datapajak = {
        bulkid: $('#bulkid').val(),
        idrequest: $('#idrequest').val(),
        norek: norek,
        npwprekanan: npwprekanan,
        namawp: namawp,
        alamatwp: alamatwp,
        kotawp: kotawp,
        akupajak: $('#akunPajak').val(),
        kjs: $('#kodeJenisSetor').val(),
        masapajak: masapajak,
        tahunpajak: $('#tahunPajak').val(),
        nosk: nosk,
        nop: nop,
        noidentitas: noidentitas,
        nilaipajak: accounting.unformat($("#nilaiPajak").val(), ",").toString(),
        npwppenyetor: npwppenyetor,
        namapenyetor: namapenyetor,
        uraian: uraian,
        idapp: $('#idApp').val(),
        kodeapp: "003" // BOP

    };
    dataac = datapajak;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {

            // DATA RESPON SELAIN DATA YG DIKIRIM
            var idrequest = data['idrequest'];
            var idresponse = data['idresponse'];
            var norek = data['debitaccountno'];
            var bulkidreq = data['bulkidrequest'];
            var namakjs = data['subtypeoftaxdescription'];
            var namaakunpajak = data['typeoftaxdescription'];
            var kodeproses = data['processingcode'];
            var koderesponse = data['responsecode'];
            var uraianresponse = data['responsecodedescription'];
            var kodebilling = data['billingcode'];
            $("#kodeBilling").val(data['billingcode']);
            var tglbillexp = data['expirydate'];
            var kodestan = data['stan'];
            var masapajak = data['taxperiod'];
            if (data['ntpnnumber'] != null && data['ntpnnumber'] != "-" && data['ntpnnumber'] != "") {
                var tglbayar = data['datetimeofpayment'];
                var tgltransmisi = data['datetimeoftransmission'];
                var tglbuku = data['settlementdate'];
                var ntb = data['ntbnumber'];
                var ntpn = data['ntpnnumber'];
                var statusbpn = data['bpnstatus'];

            } else {
                var tglbayar = "";
                var tgltransmisi = "";
                var tglbuku = "";
                var ntb = "";
                var ntpn = "";
                var statusbpn = "";

            }
            if (tglbillexp == "-") {
                tglbillexp = "";
            }
            // set awal data WP di hide
            document.getElementById("labelnpwppenyetor").style.display = "block";
            document.getElementById("labelnamapenyetor").style.display = "block";
            document.getElementById("labelalamat").style.display = "block";
            document.getElementById("labelkota").style.display = "block";
            document.getElementById("panelhasilinquiry").style.display = "block";
            updatePajakCreate(bulkidreq, idrequest, idresponse, norek, namakjs, namaakunpajak, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn, masapajak);
        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "PROSES PEMBAYARAN PAJAK GAGAL KARENA KONEKSI TERPUTUS, SILAHKAN REFRESH PANEL"//xhr.responseText
            });
            console.error(xhr);
        }
    }).always(function(data) {
//$('#prosestransfer').attr("disabled", false);
    });
}

function updatePajakCreate(bulkidreq, idrequest, idresponse, norek, namakjs, namaakunpajak, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn, masapajak) {

    var varurl = getbasepath() + "/pajaktf/json/updatedjpcreate";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idsekolah: $('#idsekolah').val(),
        idresponse: idresponse,
        namakjs: namakjs,
        namamap: namaakunpajak,
        kodeproses: kodeproses,
        koderesponse: koderesponse,
        uraianresponse: uraianresponse,
        kodebilling: kodebilling,
        tglbillexp: tglbillexp,
        kodestan: kodestan,
        tglbayar: tglbayar,
        tgltransmisi: tgltransmisi,
        tglbuku: tglbuku,
        ntb: ntb,
        ntpn: ntpn,
        statusbpn: statusbpn,
        bulkidreq: bulkidreq,
        idbku: $('#idBku').val(),
        norek: norek,
        masapajak: masapajak

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
        var deskripsi = "";
        if (uraianresponse !== "-") {
            deskripsi = " - " + uraianresponse;
        }
        var kodereq = "1"; // create kode billing
        if (koderesponse == null || koderesponse == "") {
            bootbox.alert("Koneksi Terputus");
        } else if (ntpn != null && ntpn != "-" && ntpn != "") {
            updateRinciPotPajak("3", bulkidreq, idrequest, kodebilling, tglbillexp, tglbuku, statusbpn, tglbayar, ntb, ntpn, masapajak, kodestan);
            $('#ntb').val(ntb);
            $('#ntpn').val(ntpn);

            bootbox.alert("Data Payment Sudah Ada Di Bank DKI, Data Akan Diupdate");

        } else if (kodeproses == "400100" || kodeproses == "301100") { //create billing success
            simpanRinciPotPajak(bulkidreq, idrequest, kodebilling, tglbillexp);
            cekStatusKodeProses();
            bootbox.alert("Proses Pembuatan Kode Billing DJP Berhasil");
        } else { //
            bootbox.alert("Proses Pembuatan Kode Billing DJP Gagal - RC" + koderesponse + ". Silahkan Hubungi Admin dan Cantumkan Kode RC.");
            console.log("RC : " + koderesponse + deskripsi);
        }

    });
}

function simpanRinciPotPajak(bulkidreq, idrequest, kodebilling, tglbillexp) {

    var varurl = getbasepath() + "/pajaktf/json/simpanpotrincibop";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idbku: $('#idBku').val(),
        idsekolah: $('#idsekolah').val(),
        bulkidreq: bulkidreq,
        idrequest: idrequest,
        kodebilling: kodebilling,
        tglexp: tglbillexp,
        kjs: $('#kodeJenisSetor').val(),
        kodemap: $('#akunPajak').val()
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
        inquirybilling();
    });
}

function updateRinciPotPajak(kodereq, bulkidreq, idrequest, kodebilling, tglbillexp, tglbuku, statusbpn, tglbayar, ntb, ntpn, masapajak, kodestan) {

    var varurl = getbasepath() + "/pajaktf/json/updatepotrincibop";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idbku: $('#idBku').val(),
        bulkidreq: bulkidreq,
        idrequest: idrequest,
        kodebilling: kodebilling,
        tglexp: tglbillexp,
        tglbuku: tglbuku,
        statusbpn: statusbpn,
        tglbayar: tglbayar,
        ntb: ntb,
        ntpn: ntpn,
        kodereq: kodereq,
        masapajak: masapajak,
        kodestan: kodestan
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

    });
}

function inquirybilling() {
    var varurl = getbasepath() + "/pajaktf/json/inquirybilling";
    var dataac = [];
    var datapajak = {
        billingcode: $("#kodeBilling").val(),
        idapp: $("#idApp").val(),
        kodeapp: "003" // BOP
    };
    dataac = datapajak;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {
            var npwprekanan = data['taxid'];
            var namawp = data['taxpayername'];
            var alamatwp = data['taxpayeraddress'];
            var nilaipajak = data['paymentamount'];
            var koderesponse = data['responsecode'];
            var uraianresponse = data['responsecodedescription'];
            var kodebilling = data['billingcode'];
            $('#djpnpwp').val(npwprekanan);
            $('#djpnamawp').val(namawp);
            $('#djpalamat').val(alamatwp);
            $('#djpnilai').val(accounting.formatNumber(nilaipajak, 2, '.', ","));
            $('#djpkodebill').val(kodebilling);

            if (koderesponse == null || koderesponse == "") {
                bootbox.alert("Koneksi Terputus")
            } else if (koderesponse == "EC") {
                var kodetrx = $('#kodeTransaksi').val();
                var npwp, namawp, alamatwp, kotawp, npwppenyetor, namapenyetor;
                if (kodetrx == "P2" || kodetrx == "P5" || (kodetrx == "P4" && $('#persenPot').val() == "0.5")) { // jika pajak apa npwp nya beda ??? P2 -> PPh Ps P2; PPN : P5; P4 : Pph Ps 4 Ayat 2 KHUSUS 0.5%
                    kotawp = $('#kotaWp').val();
                    npwppenyetor = $('#npwpSekolah').val();
                    namapenyetor = $('#namaWpSekolah').val();
                } else {
                    kotawp = $('#kotaWpSekolah').val();
                    npwppenyetor = $('#npwpSekolah').val();
                    namapenyetor = $('#namaWpSekolah').val();
                }
                var norek = $('#norekBank').val().substr(0, 12);
                kotawp = kotawp.substr(0, 30);
                npwppenyetor = npwppenyetor.substr(0, 15);
                namapenyetor = namapenyetor.substr(0, 50);
                var masapajak = $('#masaPajak').val().toString() + $('#tahunPajak').val().toString();
                var t = document.getElementById("kodeTransaksi");
                var deskpajak = t.options[t.selectedIndex].text;
                var uraian = deskpajak + "/" + $('#noBkuMohon').val() + "/" + $('#npsn').val();
                simpanCreate(npwprekanan, namawp, alamatwp, kotawp, npwppenyetor, namapenyetor, norek, masapajak, uraian);
            } else if (koderesponse != "00") {
                bootbox.alert(uraianresponse);
            } else {
                simpanInquiry(npwprekanan, namawp, alamatwp, nilaipajak, kodebilling);
            }
        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        }
    }).always(function(data) {

    });
}
function simpanInquiry(npwprekanan, namawp, alamatwp, nilaipajak, kodebilling) {
    var varurl = getbasepath() + "/pajaktf/json/simpaninquiry";
    var dataac = [];
    var datajour = {
        idbku: $('#idBku').val(),
        npwprekanan: npwprekanan,
        namawp: namawp,
        alamatwp: alamatwp,
        nilaipajak: nilaipajak,
        kodebilling: $('#kodeBilling').val(),
        kodeapp: "003" // 003 untuk SIAP-BOP
    };
    dataac = datajour;
    return   $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {

        }
    });
}
function simpanPayBilling(npwppenyetor, namapenyetor, norek, uraian) {
    var varurl = getbasepath() + "/pajaktf/json/simpandjpbilling";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idbku: $('#idBku').val(),
        kodetrx: $('#kodeTransaksi').val(),
        idsekolah: $('#idsekolah').val(),
        norek: norek,
        koderequest: kodeproses,
        kodeapp: "003", // 003 untuk SIAP-BOP
        tahunpajak: $('#tahunPajak').val(),
        npwppenyetor: npwppenyetor,
        namapenyetor: namapenyetor,
        uraian: uraian,
        kodebilling: $('#kodeBilling').val()

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
        $('#idrequest').val(data.idrequest);
        $('#bulkid').val(data.bulkid);
        transferPaymentBilling(npwppenyetor, namapenyetor, norek, uraian); // payment billing

    });
}

function transferPaymentBilling(npwppenyetor, namapenyetor, norek, uraian) {

    var varurl = getbasepath() + "/pajaktf/json/billing";
    var dataac = [];
    var datapajak = {
        bulkid: $('#bulkid').val(),
        idrequest: $('#idrequest').val(),
        norek: norek,
        kodebilling: $('#kodeBilling').val(),
        npwppenyetor: npwppenyetor,
        namapenyetor: namapenyetor,
        uraian: uraian,
        idapp: $('#idApp').val(),
        kodeapp: "003" // BOP
    };
    dataac = datapajak;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {

            // DATA RESPON SELAIN DATA YG DIKIRIM
            var bulkidreq = data['bulkidrequest'];
            var idrequest = data['idrequest'];
            var idresponse = data['idresponse'];
            var npwprekanan = data['taxid'];
            var namawp = data['taxpayername'];
            var alamatwp = data['taxpayeraddress'];
            var kjs = data['subtypeoftax'];
            var namakjs = data['subtypeoftaxdescription'];
            var akupajak = data['typeoftax'];
            var namaakunpajak = data['typeoftaxdescription'];
            var masapajak = data['taxperiod'];
            var nosk = data['numberofprovisionletter'];
            var nop = data['numberoftaxobject'];
            var nilaipajak = data['paymentamount'];
            var npwppenyetor = data['taxpayerid'];
            var namapenyetor = data['taxpayeridname'];
            var uraian = data['paymentdescription'];
            var kodeproses = data['processingcode'];
            var koderesponse = data['responsecode'];
            var uraianresponse = data['responsecodedescription'];
            var kodebilling = data['billingcode'];
            var kodestan = data['stan'];
            var tglbayar = data['datetimeofpayment'];
            var tgltransmisi = data['datetimeoftransmission'];
            var tglbuku = data['settlementdate'];
            var ntb = data['ntbnumber'];
            var ntpn = data['ntpnnumber'];
            var statusbpn = data['bpnstatus'];
            var kotawp = "-";
            var noidentitas = "-";
            var tglbillexp = "";
            if (nilaipajak == "-") {
                nilaipajak = "0";
            }

            if (uraianresponse == null) {
                bootbox.alert("Konensi Terputus");
            } else {
                var deskripsi = "";
                if (uraianresponse !== "-") {
                    deskripsi = " - " + uraianresponse;
                }

                if (kodeproses == "400100") {
                    bootbox.alert("Proses Pembuatan Kode Billing DJP Berhasil");
                } else if (kodeproses == "301100") {
                    //bootbox.alert("Pembuatan Kode Billing Sudah Berhasil dan Valid");
                    bootbox.alert("Proses Pembayaran Pajak Gagal - RC" + koderesponse + ". Silahkan Hubungi Admin dan Cantumkan Kode RC.");
                    console.log("RC : " + koderesponse + deskripsi);
//                    bootbox.alert("Proses Pembayaran Pajak Gagal" + deskripsi);
                } else if (kodeproses == "501011") {
                    if (koderesponse == "00") {
                        bootbox.alert("Proses Pembayaran Pajak Berhasil Namun Belum Mendapat NTPN");
                        updateRinciPotPajak("2", bulkidreq, idrequest, kodebilling, tglbillexp, tglbuku, statusbpn, tglbayar, ntb, ntpn, masapajak, kodestan);

                    } else {
                        bootbox.alert("Proses Pembayaran Pajak Gagal - RC" + koderesponse + ". Silahkan Hubungi Admin dan Cantumkan Kode RC.");
                        console.log("RC : " + koderesponse + deskripsi);
                    }
                } else if (kodeproses == "500100") {
                    if (statusbpn == 3) {
                        bootbox.alert("Proses Pembayaran Pajak Berhasil Namun Status BPN Belum Ada");
                    } else if (statusbpn == 2) {
                        bootbox.alert("Proses Pembayaran Pajak Berhasil Namun Status BPN Masih Sementara");
                    } else {
                        $('#ntb').val(ntb);
                        $('#ntpn').val(ntpn);

                        bootbox.alert("Proses Pengesahan MPN dan NTPN Berhasil");
                        updateStatusTransferBku();
                    }
                    updateRinciPotPajak("2", bulkidreq, idrequest, kodebilling, tglbillexp, tglbuku, statusbpn, tglbayar, ntb, ntpn, masapajak, kodestan);

                }

                updatePajakBilling(npwprekanan, namawp, alamatwp, kjs, akupajak, masapajak, nosk, nop, noidentitas, nilaipajak, tglbillexp, kotawp, bulkidreq, idrequest, idresponse, namakjs, namaakunpajak, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn);
                // bootbox.alert(uraianresponse);
            }
        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        }
    }).always(function(data) {

    });
}

function updatePajakBilling(npwprekanan, namawp, alamatwp, kjs, akupajak, masapajak, nosk, nop, noidentitas, nilaipajak, tglexp, kotawp, bulkidreq, idrequest, idresponse, namakjs, namaakunpajak, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn) {

    var varurl = getbasepath() + "/pajaktf/json/updatedjpbilling";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idsekolah: $('#idsekolah').val(),
        idresponse: idresponse,
        npwprekanan: npwprekanan,
        namawp: namawp,
        alamatwp: alamatwp,
        kotawp: kotawp,
        kjs: kjs,
        akupajak: akupajak,
        namakjs: namakjs,
        namamap: namaakunpajak,
        masapajak: masapajak,
        nosk: nosk,
        nop: nop,
        noidentitas: noidentitas,
        nilaipajak: nilaipajak.toString(),
        kodeproses: kodeproses,
        koderesponse: koderesponse,
        uraianresponse: uraianresponse,
        tglexp: tglexp,
        kodestan: kodestan,
        tglbayar: tglbayar,
        tgltransmisi: tgltransmisi,
        tglbuku: tglbuku,
        ntb: ntb,
        ntpn: ntpn,
        statusbpn: statusbpn,
        bulkidreq: bulkidreq,
        idbku: $('#idBku').val()

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

    });
}

function simpanReinquiry() {
    var varurl = getbasepath() + "/pajaktf/json/simpandjpreinquiry";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idbku: $('#idBku').val(),
        kodetrx: $('#kodeTransaksi').val(),
        idsekolah: $('#idsekolah').val(),
        koderequest: kodeproses,
        kodeapp: "003", // 003 untuk SIAP-BOP
        ntb: $('#ntb').val(),
        kodebilling: $('#kodeBilling').val(),
        norekbank: $('#norekBank').val(),
        tahunpajak: $('#tahunPajak').val()

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
        $('#idrequest').val(data.idrequest);
        $('#bulkid').val(data.bulkid);
        reInquiryBilling(); // re-inquiry billing

    });
}

function reInquiryBilling() {

    var varurl = getbasepath() + "/pajaktf/json/reinquirybilling";
    var dataac = [];
    var datapajak = {
        bulkid: $('#bulkid').val(),
        idrequest: $('#idrequest').val(),
        ntb: $('#ntb').val().substr(0, 12),
        kodebilling: $('#kodeBilling').val(),
        idapp: $('#idApp').val(),
        kodeapp: "003" // BOP
    };
    dataac = datapajak;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {

            // DATA RESPON SELAIN DATA YG DIKIRIM

            var bulkidreq = data['bulkidrequest'];
            var idrequest = data['idrequest'];
            var idchannel = data['idchannel'];
            var idresponse = data['idresponse'];
            var norek = "-";//data['debitaccountno'];
            var npwprekanan = data['taxid'];
            var namawp = data['taxpayername'];
            var alamatwp = data['taxpayeraddress'];
            var kjs = data['subtypeoftax'];
            var namakjs = data['subtypeoftaxdescription'];
            var akupajak = data['typeoftax'];
            var namaakunpajak = data['typeoftaxdescription'];
            var masapajak = data['taxperiod'];
            var nosk = "-";//data['numberofprovisionletter'];
            var nop = data['numberoftaxobject'];
            var nilaipajak = data['paymentamount'];
            var npwppenyetor = data['taxpayerid'];
            var namapenyetor = data['taxpayeridname'];
            var uraian = data['paymentdescription'];
            var kodeproses = "-"; //data['processingcode'];
            var koderesponse = data['responsecode'];
            var uraianresponse = data['responsecodedescription'];
            var kodebilling = data['billingcode'];
            var kodestan = data['stan'];
            var tglbayar = data['datetimeofpayment'];
            var tgltransmisi = data['datetimeoftransmission'];
            var tglbuku = data['settlementdate'];
            var ntb = data['ntbnumber'];
            var ntpn = data['ntpnnumber'];
            var statusbpn = data['bpnstatus'];
            var kotawp = "-";
            var noidentitas = "0000000000000000";
            var tglbillexp = "";

            if (nilaipajak == "-") {
                nilaipajak = "0";
            }

            if (uraianresponse == null) {
                bootbox.alert("Konensi Terputus");
            } else {

                if (koderesponse == "00") {
                    $('#ntpn').val(ntpn);
                    bootbox.alert("Proses Pengesahan MPN dan NTPN Berhasil");
                    updateStatusTransferBku();
                    updateRinciPotPajak("3", bulkidreq, idrequest, kodebilling, tglbillexp, tglbuku, statusbpn, tglbayar, ntb, ntpn, masapajak, kodestan);
                } else {
                    bootbox.alert("Status BPN Masih Sementara");
                }

                updatePajakReinquiry(npwprekanan, namawp, alamatwp, npwppenyetor, namapenyetor, uraian, kjs, akupajak, masapajak, nosk, nop, noidentitas, nilaipajak, tglbillexp, bulkidreq, idresponse, kodeproses, koderesponse, uraianresponse, kodestan, tglbayar, tgltransmisi, tglbuku, ntpn, statusbpn)
            }
        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        }
    }).always(function(data) {

    });
}


function updateStatusTransferBku() {
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;
    console.log("tanggal posting : " + tanggal);

    var varurl = getbasepath() + "/pajaktf/json/updateBkuByIdBop";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        tglposting: tanggal,
        idsekolah: $('#idsekolah').val(),
        idbku: $('#idBku').val()
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

    });
}

function updatePajakReinquiry(npwprekanan, namawp, alamatwp, npwppenyetor, namapenyetor, uraian, kjs, akupajak, masapajak, nosk, nop, noidentitas, nilaipajak, tglexp, bulkidreq, idresponse, kodeproses, koderesponse, uraianresponse, kodestan, tglbayar, tgltransmisi, tglbuku, ntpn, statusbpn) {

    var varurl = getbasepath() + "/pajaktf/json/updatedjpreinquiry";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idsekolah: $('#idsekolah').val(),
        idresponse: idresponse,
        npwprekanan: npwprekanan,
        namawp: namawp,
        alamatwp: alamatwp,
        kjs: kjs,
        akupajak: akupajak,
        masapajak: masapajak,
        nosk: nosk,
        nop: nop,
        noidentitas: noidentitas,
        nilaipajak: nilaipajak.toString(),
        npwppenyetor: npwppenyetor,
        namapenyetor: namapenyetor,
        uraian: uraian,
        kodeproses: kodeproses,
        koderesponse: koderesponse,
        uraianresponse: uraianresponse,
        tglexp: tglexp,
        kodestan: kodestan,
        tglbayar: tglbayar,
        tgltransmisi: tgltransmisi,
        tglbuku: tglbuku,
        ntpn: ntpn,
        statusbpn: statusbpn,
        bulkidreq: bulkidreq,
        idbku: $('#idBku').val()

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

    });
}
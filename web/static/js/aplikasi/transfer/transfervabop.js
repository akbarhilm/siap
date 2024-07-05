$(document).ready(function() {
    getListPajakPn();
    getSaldoKas();
    gridbku();

    if ($('#kodeVA').val() == "1") {
        document.getElementById("cbVA").checked = true;
    }

});
var banyakpajak;
var idpajakpn = new Array();
var kodepajakpn = new Array();
function gridbku() {

    if (typeof myTable == 'undefined') {
        myTable = $('#spjtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 100,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkutf/json/listbkubop",
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

                //var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";

                var nilai = "<input type='text' name='nilai" + index + "' id='nilai" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true' />";

                $('td:eq(0)', nRow).html(index);
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
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}


function getSaldoKas() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();

    $.getJSON(getbasepath() + "/bkutf/json/getSaldoKasBop", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan},
    function(result) {

        var saldo = result.aData['saldoKas'];

        $('#saldo').val(accounting.formatNumber(saldo, 2, '.', ","));

    });

}
function getToken() {
    var idsekolah = $('#idsekolah').val();
    var tahun = $('#tahun').val();
    $.getJSON(getbasepath() + "/token/json/getToken", {idsekolah: idsekolah, tahun: tahun},
    function(result) {
        var banyak, kode, ket;
        var opt = "<option>--Pilih Token--</option>"; // untuk tampilan awal combo

        banyak = result.aData.length;
        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                //console.log("i " + i);
                opt += '<option value="' + result.aData[i]['token'] + '" >' + result.aData[i]['token'] + '</option>';
            }
        }

        $("#cbtoken").html(opt);
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

    var saldo = (accounting.unformat($("#saldo").val(), ","));
    var nilaibku = (accounting.unformat($("#nilaiBku").val(), ","));

    if (saldo < nilaibku) {
        bootbox.alert("Saldo Kas Tidak Mencukupi untuk Melakukan Pembayaran SPJ");
    } else if ($("#tglPosting").val() == "" || $("#norekBank").val() == "" || $("#namaRekan").val() == "") {
        bootbox.alert("Pengisian Data Harus Lengkap");
    } else {
        bayar();
    }
}

function bayar() {
     
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;


    var varurl = getbasepath() + "/bkutf/json/bayarbkubop";
    var dataac = [];
    var listpajak = [];
    if (banyakpajak > 0) {
        for (var i = 0; i < banyakpajak; i++) { // list
            var pararr = {
                idbku: idpajakpn[i],
                kodetrx: kodepajakpn[i]
            };

            listpajak[i] = pararr;

        }
    }
    var datajour = {
        tahun: $("#tahun").val(),
        idsekolah: $('#idsekolah').val(),
        kodetransaksi: $('#kodeTransaksi').val(),
        tglposting: tanggal,
        namarekan: $("#namaRekan").val(),
        norek: $("#norekBank").val(),
        kodebank: $("#kodeBank").val(),
        kodebanktf: $("#kodeBankTf").val(),
        namabank: $("#namaBank").val(),
        idbku: $("#idBku").val(),
        banyakpajak: banyakpajak.toString(),
        listpajak: listpajak

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
        bootbox.alert("Konfirmasi Pembayaran Virtual Account Berhasil Disimpan");
        document.getElementById("btnSimpan").style.visibility = "hidden";
    });


}

function getListPajakPn() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var nobkuref = $('#noBkuMohon').val();

    $.getJSON(getbasepath() + "/bkutf/json/getListPajakPnBop", {tahun: tahun, idsekolah: idsekolah, nobkuref: nobkuref},
    function(result) {
        banyakpajak = result.aData.length;
        
        try {
            if (banyakpajak > 0) {
                for (var i = 0; i < banyakpajak; i++) {
                    idpajakpn[i] = result.aData[i]['idBku'];
                    kodepajakpn[i] = result.aData[i]['kodeTransaksi'];
                }
            }
        } catch (e) {
            console.log(e);
        }
    });

}
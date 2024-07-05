$(document).ready(function() {
    //setBulan();
    check();
});

function check() {
    gridbku();
    getTotal();
}

function gridbku() {

    if (typeof myTable == 'undefined') {

        myTable = $('#monjourtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, 200, 5000], [25, 50, 100, 200, "All"]],
            "iDisplayLength": 50,
            "sAjaxSource": getbasepath() + "/monbos/json/listindexmontransfer",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idSekolah").val()},
                {name: "triwulan", value: $("#triwulan").val()}
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
                var tahun = $("#tahun").val();
                var tglDok, akun;
                var thn, mm, dd;

                if (index == 1) {
                    tglDok = "";
                } else {
                    var tanggalDok = aData['tglDok'];

                    thn = tanggalDok.substr(0, 4);
                    mm = tanggalDok.substr(5, 2);
                    dd = tanggalDok.substr(8, 2);
                    tglDok = dd + "/" + mm + "/" + thn;

                }

                var kodetrans = aData['kodeTransaksi'];
                var nilaim = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                var nilaik = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");
                var nilais = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + nilaim + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + nilaik + "' readOnly='true'/>";
                var saldo = "<input type='text' name='saldo" + index + "' id='saldo" + index + "'  class='inputmoney'  value='" + nilais + "' readOnly='true'/>";
                var idtrans = "<input type='hidden' id='kodetransaksi" + index + "' value='" + aData['idTransaksi'] + "' />";
                var uraian = "<textarea id='uraianbukti" + index + "'readonly style='border:none;margin:0;width:280px;'>" + aData['uraianBukti'] + "</textarea>";
                var kegiatan = "<textarea id='kegiatan" + index + "'readonly style='border:none;margin:0;width:180px;'>" + aData['ketKegiatan'] + "</textarea>";




                if (index == 1) {
                    akun = "";
                } else if (kodetrans.substr(0, 1) == "P" || kodetrans == "JJ") {
                    akun = aData['kodeakun'];
                } else {
                    akun = "";
                }

                if (aData['ketKegiatan'] == null) {
                    kegiatan = "";
                }



                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(tglDok);
                $('td:eq(4)', nRow).html(akun);
                $('td:eq(5)', nRow).html(uraian);
                $('td:eq(6)', nRow).html(kegiatan);
                $('td:eq(7)', nRow).html(nilaikeluar);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "tglDok", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBkuMohon", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "uraianBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "ketKegiatan", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "right"},
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();

    }

}

function cetak() {
    //alert("cetak mon bku bop har");
    var idsekolah = $('#idSekolah').val();
    var tglPosting = $("#tglPosting").val();
    var tahun = $("#tahun").val();
    if (idsekolah == "") {
        bootbox.alert("Data Tidak Tersedia");
    } else {
        //window.location.href = getbasepath() + "/bku/json/prosescetakmonbkubophar?tahun=" + tahun + "&idsekolah=" + idsekolah + "&tglPosting=" + tglPosting + "&jenislaporan=" + jenislaporan + "&saldo=" + saldo + "&akun=" + akun + "&wilayah=" + wilayah;
        window.location.href = getbasepath() + "/bku/json/prosescetakmonbkuboshar?tahun=" + tahun + "&idsekolah=" + idsekolah + "&tglPosting=" + tglPosting;
    }
}


function getTotal() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();


    $.getJSON(getbasepath() + "/monbos/json/getTotal", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan},
    function(result) {

        var tm = result.aData['nilaiMasuk'];
        var tk = result.aData['nilaiKeluar'];
        var ts = result.aData['nilaiSisa'];

        $("#totkeluar").val(accounting.formatNumber(tk, 2, '.', ","));
        $("#totbayar").val(accounting.formatNumber(tk, 2, '.', ","));

        getSaldo(tk);

    });
}
function getSaldo(tk) {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();


    $.getJSON(getbasepath() + "/monbos/json/getSaldo", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan},
    function(result) {

        var tm = result.aData['nilaiMasuk'];

        $("#saldokas").val(accounting.formatNumber(tm, 2, '.', ","));
        $("#sisakas").val(accounting.formatNumber(tm - tk, 2, '.', ","));

    });
}

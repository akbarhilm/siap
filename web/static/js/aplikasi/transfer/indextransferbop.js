$(document).ready(function() {
    getMaxTriwulan();

});

// variable global

function getMaxTriwulan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/bkubop/json/getMaxTriwulan", {tahun: tahun, idsekolah: idsekolah},
    function(result) {

        $('#triwulan').val(result);

        gridtransfer();

    });

}


function gridtransfer() {

    if (typeof myTable == 'undefined') {

        myTable = $('#jourtable').dataTable({
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
            "sAjaxSource": getbasepath() + "/bkutf/json/listindexbop",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "triwulan", value: $("#triwulan").val()},
                {name: "nobku", value: $("#nobkufilter").val()},
                {name: "nobukti", value: $("#nobuktifilter").val()}
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
                var transaksi, va;

                var kodetrans = aData['kodeTransaksi'];

                if (kodetrans == "JJ") {
                    transaksi = "SPJ (PENGAJUAN BELANJA)";
                } else if (kodetrans == "P1") {
                    transaksi = "PPH PS 21";
                } else if (kodetrans == "P2") {
                    transaksi = "PPH PS 22";
                } else if (kodetrans == "P3") {
                    transaksi = "PPH PS 23 JASA I";
                } else if (kodetrans == "P5") {
                    transaksi = "PPN";
                } else if (kodetrans == "ST") {
                    transaksi = "SETOR SISA KAS";
                } else if (kodetrans == "JG") {
                    transaksi = "JASA GIRO";
                } else if (kodetrans == "RT") {
                    transaksi = "PENDAPATAN LAIN-LAIN";
                }

                var nilai = accounting.formatNumber(aData['nilaiTransfer'], 2, '.', ",");
                var nilaitf = "<input type='text' name='nilaitf" + index + "' id='nilaitf" + index + "'  class='inputmoney'  value='" + nilai + "' readOnly='true' />";
                var transfer = "";

                if (aData['kodeVA'] == "1") { // JIKA VIRTUAL ACCOUNT
                    transfer = "Bayar Manual untuk VA";
                    va = "<a href='" + getbasepath() + "/bkutf/tfbopva/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "&idskpd=" + aData['idskpd'] + "' class='icon-edit' ></a>";

                } else if (kodetrans.substr(0, 1) == "P") { // untuk pajak
                    transfer = "<a href='" + getbasepath() + "/pajaktf/tfpajakbop/" + "?idbku=" + aData['idBku'] + "&kodetrx=" + kodetrans + "&idsekolah=" + $('#idsekolah').val() + "' class='icon-edit' ></a>";
                    va = "";
                } else {
                    transfer = "<a href='" + getbasepath() + "/bkutf/tfbop/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "&idskpd=" + aData['idskpd'] + "' class='icon-edit' ></a>";
                    va = "";
                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(transaksi);
                $('td:eq(5)', nRow).html(nilaitf);
                $('td:eq(6)', nRow).html(transfer);
                $('td:eq(7)', nRow).html(va);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBku", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noBkuMohon", "bSortable": true, sClass: "center", "sWidth": "10%"},
                {"mDataProp": "kodeTransaksi", "bSortable": true, sClass: "center", "sWidth": "23%"},
                {"mDataProp": "noDok", "bSortable": false, sClass: "left", "sWidth": "14%"},
                {"mDataProp": "token", "bSortable": false, sClass: "center", "sWidth": "10%"},
                {"mDataProp": "nilaiTransfer", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "idsekolah", "bSortable": false, sClass: "center", "sWidth": "12%"},
                {"mDataProp": "idsekolah", "bSortable": false, sClass: "center", "sWidth": "12%"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();

    }

}


function carinobku() {

    $("#nobkufilter").keyup(function() {
        gridtransfer();
    });
}

function carinobukti() {

    $("#nobuktifilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridtransfer();
        }
    });
}


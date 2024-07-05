$(document).ready(function() {
    clearGrid();
});

function cetakXls() {
    var kode = $('#modul').val();
    if (kode == "cdpl") {
        cetakDuplikatXls();
    } else if (kode == "crnc") {
        cetakRinciXls();
    } else if (kode == "cakb") {
        cetakAkbXls();
    }
}

function clearGrid() {
    $('#tabelDuplikat').hide();
    $('#tabelRinci').hide();
    $('#tabelAkb').hide();
    $("#cetak").prop('disabled', true);
}

function setGrid() {
    var kode = $('#modul').val();

    console.log("kode combo == " + kode);
    if (kode == "cdpl") {
        $('#tabelDuplikat').show();
        $('#tabelRinci').hide();
        $('#tabelAkb').hide();
        gridDuplikat();
        console.log("ROWS = " + $('#tabelDuplikat').size());
        $("#cetak").prop('disabled', false);

    } else if (kode == "crnc") {
        $('#tabelDuplikat').hide();
        $('#tabelRinci').show();
        $('#tabelAkb').hide();
        gridRinci();
        $("#cetak").prop('disabled', false);

    } else if (kode == "cakb") {
        $('#tabelDuplikat').hide();
        $('#tabelRinci').hide();
        $('#tabelAkb').show();
        gridAkb();
        $("#cetak").prop('disabled', false);
    } else {
        clearGrid();
    }
}
function gridDuplikat() {
    var tahun = $("#tahun").val();

    var urljson = getbasepath() + "/bku/indexcekrkas/json/listduplikat";
    myTableDuplikat = $('#dpltable').dataTable({
        "bPaginate": true,
        "sPaginationType": "bootstrap",
        "bJQueryUI": false,
        "bProcessing": true,
        "bServerSide": true,
        "bInfo": true,
        "bScrollCollapse": true,
        //"sScrollY": ($(window).height() * 108 / 100),
        "bFilter": false,
        "sAjaxSource": urljson,
        "bDestroy": true,
        "aaSorting": [[1, "asc"]],
        "fnServerParams": function(aoData) {
            aoData.push(
                    {name: "tahun", value: tahun}
            );
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

            $('td:eq(0)', nRow).html(index);
            $('td:eq(7)', nRow).html(accounting.formatNumber(aData['pagu'], 2, '.', ","));

            return nRow;
        },
        "aoColumns": [
            {"mDataProp": "tahun", "bSortable": false, sClass: "center"},
            {"mDataProp": "tahun", "bSortable": false, sClass: "center"},
            {"mDataProp": "kodeUnit", "bSortable": true, sClass: "center"},
            {"mDataProp": "idGiat", "bSortable": true, sClass: "center"},
            {"mDataProp": "kodeBidang", "bSortable": true, sClass: "center"},
            {"mDataProp": "kodeGiat", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaGiat", "bSortable": true, sClass: "center"},
            {"mDataProp": "pagu", "bSortable": true, sClass: "center"}
        ]
    });

}

function gridRinci() {
    var tahun = $("#tahun").val();

    var urljson = getbasepath() + "/bku/indexcekrkas/json/listrinci";
    myTableRinci = $('#krincitable').dataTable({
        "bPaginate": true,
        "sPaginationType": "bootstrap",
        "bJQueryUI": false,
        "bProcessing": true,
        "bServerSide": true,
        "bInfo": true,
        "bScrollCollapse": true,
        //"sScrollY": ($(window).height() * 108 / 100),
        "bFilter": false,
        "sAjaxSource": urljson,
        "bDestroy": true,
        "aaSorting": [[1, "asc"]],
        "fnServerParams": function(aoData) {
            aoData.push(
                    {name: "tahun", value: tahun}
            );
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

            $('td:eq(0)', nRow).html(index);
            $('td:eq(4)', nRow).html(accounting.formatNumber(aData['paguGiat'], 2, '.', ","));
            $('td:eq(5)', nRow).html(accounting.formatNumber(aData['sumGiatRinci'], 2, '.', ","));
            $('td:eq(6)', nRow).html(accounting.formatNumber(aData['selisih'], 2, '.', ","));
            return nRow;
        },
        "aoColumns": [
            {"mDataProp": "tahun", "bSortable": false, sClass: "center"},
            {"mDataProp": "tahun", "bSortable": false, sClass: "center"},
            {"mDataProp": "kodeUnit", "bSortable": true, sClass: "center"},
            {"mDataProp": "idGiat", "bSortable": true, sClass: "center"},
            {"mDataProp": "paguGiat", "bSortable": true, sClass: "right"},
            {"mDataProp": "sumGiatRinci", "bSortable": true, sClass: "right"},
            {"mDataProp": "selisih", "bSortable": true, sClass: "right"}
        ]
    });

}

function gridAkb() {
    var tahun = $("#tahun").val();

    var urljson = getbasepath() + "/bku/indexcekrkas/json/listakb";
    myTableAkb = $('#kakbtable').dataTable({
        "bPaginate": true,
        "sPaginationType": "bootstrap",
        "bJQueryUI": false,
        "bProcessing": true,
        "bServerSide": true,
        "bInfo": true,
        "bScrollCollapse": true,
        //"sScrollY": ($(window).height() * 108 / 100),
        "bFilter": false,
        "sAjaxSource": urljson,
        "bDestroy": true,
        "aaSorting": [[1, "asc"]],
        "fnServerParams": function(aoData) {
            aoData.push(
                    {name: "tahun", value: tahun}
            );
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

            $('td:eq(0)', nRow).html(index);
            $('td:eq(4)', nRow).html(accounting.formatNumber(aData['paguGiat'], 2, '.', ","));
            $('td:eq(5)', nRow).html(accounting.formatNumber(aData['sumGiatAkb'], 2, '.', ","));
            $('td:eq(6)', nRow).html(accounting.formatNumber(aData['selisih'], 2, '.', ","));
            return nRow;
        },
        "aoColumns": [
            {"mDataProp": "tahun", "bSortable": false, sClass: "center"},
            {"mDataProp": "tahun", "bSortable": false, sClass: "center"},
            {"mDataProp": "kodeUnit", "bSortable": true, sClass: "center"},
            {"mDataProp": "idGiat", "bSortable": true, sClass: "center"},
            {"mDataProp": "paguGiat", "bSortable": true, sClass: "right"},
            {"mDataProp": "sumGiatAkb", "bSortable": true, sClass: "right"},
            {"mDataProp": "selisih", "bSortable": true, sClass: "right"}
        ]
    });

}

function cetakDuplikatXls() {
    window.location.href = getbasepath() + "/bku/indexcekrkas/xls/xlsduplikat?tahun=" + $("#tahun").val();
}
function cetakRinciXls() {
    window.location.href = getbasepath() + "/bku/indexcekrkas/xls/xlsrinci?tahun=" + $("#tahun").val();
}
function cetakAkbXls() {
    window.location.href = getbasepath() + "/bku/indexcekrkas/xls/xlsakb?tahun=" + $("#tahun").val();
}
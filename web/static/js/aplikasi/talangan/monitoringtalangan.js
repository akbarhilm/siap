$(document).ready(function() {
    check();
});

function check() {
    getMaxTriwulan();
}

function setGrid() {
    grid();
    getTotalWS();
}
function grid() {
    var kodeotoritas = $("#kodeotoritas").val();
    var idskpd = $("#idskpd").val();
    var npsn = $("#npsn").val();
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
            "sAjaxSource": getbasepath() + "/danatalangan/json/listindexmonitoring",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "triwulan", value: $("#triwulan").val()},
                {name: "kodeotoritas", value: kodeotoritas},
                {name: "idskpd", value: idskpd},
                {name: "npsn", value: npsn},
                {name: "tipe", value: $("#jenis").val()}
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
                var tanggal1 = aData['tanggalTerima'];
                var tanggal2 = aData['tanggalBayar'];
                var tanggalTerima, tanggalBayar, thn, mm, dd;
                console.aData;
                thn = tanggal1.substr(0, 4);
                mm = tanggal1.substr(5, 2);
                dd = tanggal1.substr(8, 2);
                tanggalTerima = dd + "/" + mm + "/" + thn;
                thn = tanggal2.substr(0, 4);
                mm = tanggal2.substr(5, 2);
                dd = tanggal2.substr(8, 2);
                tanggalBayar = dd + "/" + mm + "/" + thn;
                var nilai = accounting.formatNumber(aData['nilaiBayar'], 2, '.', ",");
                var namaSekolah = aData['npsn'] + " / " + aData['namaSekolah'];
                var nilai = "<input type='text' name='nilai" + index + "' id='nilai" + index + "'  class='inputmoney'  value='" + nilai + "' readOnly='true'/>";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(tanggalTerima);
                $('td:eq(2)', nRow).html(namaSekolah);
                $('td:eq(5)', nRow).html(tanggalBayar);
                $('td:eq(7)', nRow).html(nilai);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "tanggalTerima", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaSekolah", "bSortable": true, sClass: "center"},
                {"mDataProp": "iMcb", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaMcb", "bSortable": false, sClass: "left"},
                {"mDataProp": "bulanTagihan", "bSortable": false, sClass: "left"},
                {"mDataProp": "tanggalBayar", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeBayar", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiBayar", "bSortable": false, sClass: "right"},
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();

    }

}

function getTotalWS() {
    var tahun = $('#tahun').val();
    var triwulan = $('#triwulan').val();
    var kodeotoritas = $('#kodeotoritas').val();
    var idkpsd = $('#idkpsd').val();
    var npsn = $('#npsn').val();

    $.getJSON(getbasepath() + "/danatalangan/json/getTotalWS", {tahun: tahun,
        tipe: $('#jenis').val(), triwulan: triwulan, kodeotoritas: kodeotoritas, idskpd: idkpsd, npsn: npsn},
    function(result) {

        var nilai = result;
        console.log("NILAI " + nilai);

        $("#totdana").val(accounting.formatNumber(nilai, 2, '.', ","));
        $("#totaldana").val(accounting.formatNumber(nilai, 2, '.', ","));

    });
}

function getMaxTriwulan() {
    var tahun = $('#tahun').val();
    var kodeotoritas = $('#kodeotoritas').val();
    var idkpsd = $('#idkpsd').val();
    var npsn = $('#npsn').val();

    $.getJSON(getbasepath() + "/danatalangan/json/getMaxTriwulan", {tahun: tahun,
        tipe: $('#jenis').val(), kodeotoritas: kodeotoritas, idskpd: idkpsd, npsn: npsn},
    function(result) {
        console.log(result + " A")
        $('#max').val(result);

        $('#triwulan').val(result);
        setGrid();
    });

}

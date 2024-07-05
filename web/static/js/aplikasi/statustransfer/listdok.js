$(document).ready(function() {

});

var triwulan, idsekolah, kodeTransaksi, kodesumb;

function grid() {

    $("#skpdtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/statustransfer/json/listkegiatan",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "nomohon", value: $('#nobku').val()},
                {name: "triwulan", value: triwulan},
                {name: "kodetrans", value: kodeTransaksi},
                {name: "kodesumb", value: kodesumb},
                {name: "idsekolah", value: idsekolah}
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
                var nilai;

                if (aData['kodeTransaksi'] == "JJ") {
                    nilai = accounting.formatNumber(aData['nilaiSpjNetto'], 2, '.', ",");
                } else {
                    nilai = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");
                }

                var nobku = "<input type='hidden' id='nobku" + index + "' value='" + aData['noBkuMohon'] + "' />";
                var idbku = "<input type='hidden' id='idbku" + index + "' value='" + aData['idBku'] + "' />";
                var nodok = "<input type='hidden' id='nodok" + index + "' value='" + aData['noDok'] + "' />";
                var uraian = "<textarea id='uraian" + index + "'readonly style='border:none;margin:0;width:500px;'>" + aData['uraianBukti'] + "</textarea>";

                $('td:eq(0)', nRow).html(index + nobku + idbku);
                $('td:eq(3)', nRow).html(uraian + nodok);
                $('td:eq(4)', nRow).html(nilai);
                $('td:eq(5)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambildata(" + index + ")'></span>");

                return nRow;

            },
            "aoColumns": [
                {"mDataProp": "idBku", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center", "sWidth": "12%"},
                {"mDataProp": "noDok", "bSortable": false, sClass: "left", "sWidth": "16%"},
                {"mDataProp": "uraianBukti", "bSortable": true, sClass: "left", "sWidth": "25%"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "right", "sWidth": "13%"},
                {"mDataProp": "idBku", "bSortable": false, sClass: "center", "sWidth": "4%"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

$(document).ready(function() {

});

var triwulan, idsekolah;

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
            "sAjaxSource": getbasepath() + "/koreksinilaispjbop/json/listspj",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "nobku", value: $('#nobku').val()},
                {name: "nobukti", value: $('#nobukti').val()},
                {name: "triwulan", value: triwulan},
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

                var nilai = accounting.formatNumber(aData['nilaiSpj'], 2, '.', ",");
                var nobku = "<input type='hidden' id='nobku" + index + "' value='" + aData['noBkuMohon'] + "' />";
                var idbku = "<input type='hidden' id='idbku" + index + "' value='" + aData['idBku'] + "' />";
                var idkeg = "<input type='hidden' id='idkeg" + index + "' value='" + aData['idKegiatan'] + "' />";
                var kodekeg = "<input type='hidden' id='kodekeg" + index + "' value='" + aData['kodeKeg'] + "' />";
                var nobukti = "<input type='hidden' id='nobukti" + index + "' value='" + aData['noBuktiDok'] + "' />";
                var kegiatan = "<textarea id='ketkeg" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['kodeKeg'] + "/" + aData['namaKeg'] + "</textarea>";
                var uraian = "<textarea id='ketkeg" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['uraianBukti'] + "</textarea>";
                var nilaispj = "<input type='text' id='nilai" + index + "'  class='inputmoney'  value='" + nilai + "' readOnly='true' />";

                $('td:eq(0)', nRow).html(index + nobku + idbku);
                $('td:eq(1)', nRow).html(kegiatan + idkeg + kodekeg);
                $('td:eq(4)', nRow).html(uraian);
                $('td:eq(5)', nRow).html(nilaispj);
                $('td:eq(6)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambildata(" + index + ")'></span>");

                return nRow;

            },
            "aoColumns": [
                {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "namaKeg", "bSortable": false, sClass: "left", "sWidth": "25%"},
                {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center", "sWidth": "12%"},
                {"mDataProp": "noBuktiDok", "bSortable": false, sClass: "left", "sWidth": "16%"},
                {"mDataProp": "uraianBukti", "bSortable": true, sClass: "left", "sWidth": "25%"},
                {"mDataProp": "nilaiSpj", "bSortable": false, sClass: "right", "sWidth": "13%"},
                {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center", "sWidth": "4%"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

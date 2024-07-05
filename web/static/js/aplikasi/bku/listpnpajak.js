$(document).ready(function() {

});

var jenistrans, nobkuedit, ketaddedit;

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
            "sAjaxSource": getbasepath() + "/bku/json/listkegpjpn",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "nobku", value: nobkuedit},
                {name: "jenistrans", value: jenistrans},
                {name: "idsekolah", value: $('#idsekolah').val()}
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
                //noBKU, noBukti, nilaiBku, , , 
                var nilai = accounting.formatNumber(aData['nilaiBku'], 2, '.', ",");
                var textnobku = "<input type='hidden' id='nobku" + index + "' value='" + aData['noBKU'] + "' />";
                var textidkeg = "<input type='hidden' id='idkeg" + index + "' value='" + aData['idKegiatan'] + "' />";
                var textkodekeg = "<input type='hidden' id='kodekeg" + index + "' value='" + aData['kodeKeg'] + "' />";
                var textnamakeg = "<input type='hidden' id='namakeg" + index + "' value='" + aData['namaKeg'] + "' />";
                var nilaiterima = "<input type='text' id='nilaiterima" + index + "'  class='inputmoney'  value='" + nilai + "' readOnly='true' />";
                var ketkeg = "<textarea id='ketkeg" + index + "'readonly style='border:none;margin:0;width:800px;'>" + aData['kodeKeg'] + "/" + aData['namaKeg'] + "</textarea>";

                $('td:eq(0)', nRow).html(index + textnobku);
                $('td:eq(3)', nRow).html(ketkeg + textidkeg);
                $('td:eq(4)', nRow).html(nilaiterima + textkodekeg + textnamakeg);
                $('td:eq(5)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiBku", "bSortable": true, sClass: "right"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

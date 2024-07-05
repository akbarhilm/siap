$(document).ready(function() {

});

var kodetrans, nobkumohon, triwulan;

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
            "sAjaxSource": getbasepath() + "/bkubop/json/getListPnJg",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "triwulan", value: triwulan},
                {name: "nobkumohon", value: nobkumohon},
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

                var nilai = accounting.formatNumber(aData['nilaiBku'], 2, '.', ",");
                
                
                //idBku, noBkuMohon, noBukti, uraian, nilaiBku
                
                var nomohon = "<input type='hidden' id='nomohon" + index + "' value='" + aData['noBkuMohon'] + "' />";
                var nilai = "<input type='text' id='nilai" + index + "'  class='inputmoney'  value='" + nilai + "' readOnly='true' />";
                var uraian = "<textarea id='uraian" + index + "'readonly style='border:none;margin:0;width:170px;'>" + aData['uraian'] + "</textarea>";
               
                $('td:eq(0)', nRow).html(index + nomohon);
                $('td:eq(4)', nRow).html(nilai);
                $('td:eq(5)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambildata(" + index + ")'></span>");

                return nRow;

            },
            "aoColumns": [
                {"mDataProp": "idBku", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center"},
                {"mDataProp": "uraian", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiBku", "bSortable": true, sClass: "right"},
                {"mDataProp": "idBku", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

$(document).ready(function() {

});

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
            "sAjaxSource": getbasepath() + "/bank/json/listbankpfk",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "nama", value: $('#nama').val()}
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
                
                var textnama = "<input type='hidden' id='namatf" + index + "' value='" + aData['namaBankTf'] + "' />";
                var textkode = "<input type='hidden' id='kodebank" + index + "' value='" + aData['kodeBank'] + "' />";
                var textkodetf = "<input type='hidden' id='kodetf" + index + "' value='" + aData['kodeBankTf'] + "' />";
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textkodetf + textkode + textnama + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "kodeBank", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeBankTf", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaBankTf", "bSortable": true, sClass: "left"},
                {"mDataProp": "kodeBank", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

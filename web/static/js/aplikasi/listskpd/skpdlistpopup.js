function grid( ) {
    $("#skpdtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
            "bPaginate": false,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath()+"/skpdpopup/json/listskpdjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                  {name: "namaskpd", value: $('#namaskpd').val()},
                  {name: "kodeskpd", value: $('#kodeSkpd').val()}
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
                var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idSkpd'] + "' value='" + aData['kodeSkpd']+"/"+aData['namaSkpd'] + "' />"
                var textkodeskpd = "<input type='hidden' id='kodeSkpd" + aData['idSkpd'] + "' value='" + aData['kodeSkpd']+ "' />"
                var textnskpd = "<input type='hidden' id='namaSkpd" + aData['idSkpd'] + "' value='" + aData['namaSkpd']+ "' />"
               
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textkodeskpd+textnskpd+textnamaskpd + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idSkpd'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSkpd", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "kodeSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idSkpd", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}



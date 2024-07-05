function grid( ) {
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
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath()+"/penggunapopup/json/listpenggunajson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                  {name: "namapengguna", value: $('#namapengguna').val()},
                  {name: "nrk", value: $('#nrk').val()}
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
                var textnamaskpd = "<input type='hidden' id='namaPengguna" + aData['idPengguna'] + "' value='" + aData['idNrk']+"/"+aData['namaPengguna'] + "' />"
                var textkodeskpd = "<input type='hidden' id='idPengguna" + aData['idPengguna'] + "' value='" + aData['idPengguna']+ "' />"
                var kodeotor = "<input type='hidden' id='kodeOtoritas" + aData['idPengguna'] + "' value='" + aData['kodeOtoritas']+ "' />"
                var nrk = "<input type='hidden' id='nrk" + aData['idPengguna'] + "' value='" + aData['idNrk']+ "' />"
                //var textnskpd = "<input type='hidden' id='namaSekolah" + aData['idSekolah'] + "' value='" + aData['namaSekolahPendek']+ "' />"
               
                $('td:eq(0)', nRow).html(index);
                $('td:eq(4)', nRow).html(textkodeskpd+textnamaskpd+kodeotor + nrk+"<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idPengguna'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idPengguna", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "idNrk", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaPengguna", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSekolahPendek", "bSortable": false, sClass: "center"},
                 {"mDataProp": "idPengguna", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}



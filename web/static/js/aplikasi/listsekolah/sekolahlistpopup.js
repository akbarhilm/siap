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
            "sAjaxSource": getbasepath() + "/sekolahpopup/json/listsekolahjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "namasekolah", value: $('#namasekolah').val()},
                {name: "npsn", value: $('#npsn').val()}
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
                var textnamaskpd = "<input type='hidden' id='namaSekolahPendek" + aData['idSekolah'] + "' value='" + aData['npsn'] + "/" + aData['namaSekolahPendek'] + "' />"
                var textkodeskpd = "<input type='hidden' id='npsn" + aData['idSekolah'] + "' value='" + aData['npsn'] + "' />"
                var textnskpd = "<input type='hidden' id='namaSekolah" + aData['idSekolah'] + "' value='" + aData['namaSekolahPendek'] + "' />"

                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textkodeskpd + textnskpd + textnamaskpd + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idSekolah'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSekolah", "bSortable": false, sClass: "center"},
                {"mDataProp": "npsn", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSekolahPendek", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idSekolah", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function gridbyidskpd(kodesumbdana) {  /* idns , 21-12-2017 */
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
            "sAjaxSource": getbasepath() + "/sekolahpopup/json/listsekolahbyidskpdjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kodesumbdana", value: kodesumbdana},
                {name: "namasekolah", value: $('#namasekolah').val()},
                {name: "npsn", value: $('#npsn').val()}
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
                var textnamaskpd = "<input type='hidden' id='namaSekolahPendek" + aData['idSekolah'] + "' value='" + aData['npsn'] + "/" + aData['namaSekolahPendek'] + "' />"
                var textkodeskpd = "<input type='hidden' id='npsn" + aData['idSekolah'] + "' value='" + aData['npsn'] + "' />"
                var textnskpd = "<input type='hidden' id='namaSekolah" + aData['idSekolah'] + "' value='" + aData['namaSekolahPendek'] + "' />"
                var nobos = "<input type='hidden' id='nobos" + aData['idSekolah'] + "' value='" + aData['noBOS'] + "' />"
                var nobop = "<input type='hidden' id='nobop" + aData['idSekolah'] + "' value='" + aData['noBOP'] + "' />"
                var namabos = "<input type='hidden' id='namabos" + aData['idSekolah'] + "' value='" + aData['namaBOS'] + "' />"
                var namabop = "<input type='hidden' id='namabop" + aData['idSekolah'] + "' value='" + aData['namaBOP'] + "' />"
                
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textkodeskpd + textnskpd + textnamaskpd +nobos+nobop+namabos+namabop+ "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idSekolah'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSekolah", "bSortable": false, sClass: "center"},
                {"mDataProp": "npsn", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSekolahPendek", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idSekolah", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}



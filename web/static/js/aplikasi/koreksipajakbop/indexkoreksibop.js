$(document).ready(function() {

});

// variable global

function setGrid() {
    console.log("cektw = " + $("#triwulan").val());
    if ($("#triwulan").val() == "-") {
        clearrow();
    } else {
        gridindex();
    }
}

function gridindex() {
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
            "sAjaxSource": getbasepath() + "/koreksiPajakBop/json/listindex",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "triwulan", value: $("#triwulan").val()}
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

                //idBku, noBkuMohon, kodePajak, noDok, tglDok, namaKeg, noBuktiDok, nilaiPajak 
                var tutup = aData['kodeTutup'];
                var pilihan;

                var nilai = accounting.formatNumber(aData['nilaiPajak'], 2, '.', ",");
                var idbku = "<input type='hidden' id='idbku" + index + "' value='" + aData['idBku'] + "' />";
                var nobkuref = "<input type='hidden' id='nobkuref" + index + "' value='" + aData['noBkuRef'] + "' />";
                var kegiatan = "<textarea id='kegiatan" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['namaKeg'] + "</textarea>";

                
                if (aData['kodePajak'] == "SPJ") {
                    pilihan = "";
                } else {
                    if (tutup == "2") { // jika sudah ditutup
                        pilihan = "<a href='" + getbasepath() + "/koreksiPajakBop/arsipkoreksi/" + aData['idBku'] + "?idsekolah=" + $("#idsekolah").val() + "' class='icon-file-text' ></a>";
                    } else {
                        pilihan = "<a href='" + getbasepath() + "/koreksiPajakBop/arsipkoreksi/" + aData['idBku'] + "?idsekolah=" + $("#idsekolah").val() + "' class='icon-file-text' ></a>-<a href='" + getbasepath() + "/koreksiPajakBop/hapuskoreksi/" + aData['idBku'] + "?idsekolah=" + $("#idsekolah").val() + "' class='icon-remove' ></a>";
                    }
                }


                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(kegiatan);
                $('td:eq(6)', nRow).html(nilai);
                $('td:eq(7)', nRow).html(pilihan + idbku);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBku", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBkuMohon", "bSortable": true, sClass: "center"},
                {"mDataProp": "kodePajak", "bSortable": true, sClass: "left"},
                {"mDataProp": "namaKeg", "bSortable": false, sClass: "left"},
                {"mDataProp": "noDok", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBuktiDok", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiPajak", "bSortable": false, sClass: "right"},
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

function clearrow() {
    var i;
    var table;

    table = document.getElementById('jourtablebody');
    var rows = table.rows;
    var jum = rows.length;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}
$(document).ready(function() {

});

var ketval, ketcarabayar;

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
            "sAjaxSource": getbasepath() + "/bkuspjpajak/json/listkegiatanjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "nama", value: $('#nama').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "keterangan", value: "SPJ"},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "carabayar", value: ketcarabayar}
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
                /*
                 var textnama = "<input type='hidden' id='nama" + aData['idKegiatan'] + "' value='" + aData['namaKeg']+"' />";
                 var textkode = "<input type='hidden' id='kode" + aData['idKegiatan'] + "' value='" + aData['kodeKeg']+"' />";
                 var textket = "<input type='hidden' id='ket" + aData['idKegiatan'] + "' value='" + aData['kodeKeg']+"/"+aData['namaKeg'] + "' />";
                 var textbeban = "<input type='hidden' id='beban" + aData['idKegiatan'] + "' value='" + aData['beban']+ "' />";
                 */

                var textnama = "<input type='hidden' id='nama" + index + "' value='" + aData['namaKeg'] + "' />";
                var textkode = "<input type='hidden' id='kode" + index + "' value='" + aData['kodeKeg'] + "' />";
                var tnama = "<textarea id='txtnama" + index + "'readonly style='border:none;margin:0;width:960px;'>" + aData['namaKeg'] + "</textarea>";
                var textket = "<input type='hidden' id='ket" + index + "' value='" + aData['kodeKeg'] + "/" + aData['namaKeg'] + "' />";
                var textbeban = "<input type='hidden' id='beban" + index + "' value='" + aData['beban'] + "' />";
                var textidkeg = "<input type='hidden' id='idkeg" + index + "' value='" + aData['idKegiatan'] + "' />";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(tnama);
                $('td:eq(4)', nRow).html(textidkeg+textbeban + textket + textkode + textnama + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"},
                {"mDataProp": "beban", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeKeg", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaKeg", "bSortable": true, sClass: "left"},
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

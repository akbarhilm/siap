$(document).ready(function() {
    grid();
    getSkpd();
});


function grid() {
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
            "sAjaxSource": getbasepath() + "/bkuproses/json/listindex",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $("#idskpd").val()}
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
                var sumbdana, tw;

                //var idtrans = "<input type='hidden' id='id" + index + "' value='" + aData['id'] + "' />";

                var edit = "<a href='" + getbasepath() + "/bkuproses/edit/" + "?id=" + aData['id'] + "' class='icon-edit' ></a>";

                if (aData['kodeSumbdana'] == "1") {
                    sumbdana = "BOS";
                } else {
                    sumbdana = "BOP";
                }

                if (aData['triwulan'] == "1") {
                    tw = "Triwulan I";
                } else if (aData['triwulan'] == "2") {
                    tw = "Triwulan II";
                } else if (aData['triwulan'] == "3") {
                    tw = "Triwulan III";
                } else if (aData['triwulan'] == "4") {
                    tw = "Triwulan IV";
                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(sumbdana);
                $('td:eq(2)', nRow).html(tw);
                $('td:eq(4)', nRow).html(edit);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "kodeSumbdana", "bSortable": false, sClass: "center"},
                {"mDataProp": "triwulan", "bSortable": false, sClass: "center"},
                {"mDataProp": "batasWaktu", "bSortable": false, sClass: "center"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();

    }

}

function getSkpd() {
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bkuproses/json/getSkpd", {idskpd: idskpd},
    function(result) {

        var kode = result.aData['kodeSkpd']
        var nama = result.aData['namaSkpd']

        $("#skpd").val(kode + "/" + nama);

    });
}
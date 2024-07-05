$(document).ready(function () {
    grid();
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
            "sAjaxSource": getbasepath() + "/cutoff/json/listindex",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function () {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()}
                );
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {

            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var tahun = $("#tahun").val();
                var edit = "<a href='" + getbasepath() + "/cutoff/editlibur/" + "?id=" + aData['id'] + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/cutoff/hapuslibur/" + "?id=" + aData['id'] + "' class='icon-remove' ></a>";
                var uraian = "<textarea id='uraian" + index + "'readonly style='border:none;margin:0;width:180px;'>" + aData['keterangan'] + "</textarea>";
                var tanggal;

                $('td:eq(0)', nRow).html(index);
//                $('td:eq(2)', nRow).html(tanggal);
                $('td:eq(3)', nRow).html(uraian);
                $('td:eq(4)', nRow).html(edit);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "tahun", "bSortable": true, sClass: "center"},
                {"mDataProp": "waktu", "bSortable": true, sClass: "center"},
                {"mDataProp": "keterangan", "bSortable": false, sClass: "center"},
                {"mDataProp": "id", "bSortable": false, "sWidth": "5%", sClass: "center"}
            ]
        });
    } else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();

    }

}
$(document).ready(function() {
    grid();
});

function caritahun() {
    $("#tahun").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 3) { // || panjang == 0
            grid();
        }
    });
}

function cariket() {
    $("#ketfilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            grid();
        }
    });
}
function carikoderek() {
    $("#koderekfilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            grid();
        }
    });
}

function grid( ) {
    //$("#usertable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#usertable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            //"sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            "iDisplayLength": 10,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listbastrx",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "ketfilter", value: $('#ketfilter').val()},
                {name: "koderekfilter", value: $('#koderekfilter').val()}
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
                var isceked = aData['kodeAktif'] == '1' ? 'checked' : '';
                var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />"

                $('td:eq(0)', nRow).html(index);
                $('td:eq(6)', nRow).html(cekaktif);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "kodeRek", "bSortable": false, sWidth: '5%',sClass: "center"},
                {"mDataProp": "keterangan", "bSortable": true, sDefaultContent: "-", sClass: "left", sWidth: '15%'},
                {"mDataProp": "kodeRek", "bSortable": true, sDefaultContent: "-", sClass: "left", sWidth: '15%'},
                {"mDataProp": "uraian", "bSortable": true, sDefaultContent: "-", sClass: "left"} 
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function cetak() {
    var aaa = $("#tahun").val();
    var eee = aaa + "-" + "ADM-PENGGUNA.pdf";

    window.location.href = getbasepath() + "/useradm/json/prosescetak?tahun=" + aaa + "&namafile=" + eee;


}
$(document).ready(function() {
    isTutupBuku();
});

function gridbapkas() {

    var urljson = getbasepath() + "/bapkasbos/json/getlistbapkas";
    if (typeof myTable == 'undefined') {
        myTable = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, 200, 5000], [25, 50, 100, 200, "All"]],
            "iDisplayLength": 25,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idsekolah", value: $('#idsekolah').val()},
                {name: "tahun", value: $('#tahun').val()}
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

                var tp = $('#tglBkuPros').val();
                var tutupbuku = $('#tutupbuku').val();
                var tgl = aData['tglBkuBa'];
                var bln = aData['blnBkuBa'];
                var idb = aData['idSekolahBAPKas'];
                var is = aData['sekolah']['idSekolah'];
                var tw;

                if (aData['triwulan'] == "1") {
                    tw = "Triwulan I";
                } else if (aData['triwulan'] == "2") {
                    tw = "Triwulan II";
                } else if (aData['triwulan'] == "3") {
                    tw = "Triwulan III";
                } else if (aData['triwulan'] == "4") {
                    tw = "Triwulan IV";
                }

                var edit = "-";
//                if (tp == 0) {
                if (aData['statusBkuBa'] == '0') {
                    document.getElementById('kolom5').innerHTML = 'Edit  -  Hapus  -  Unduh';
                    edit = "<a href='" + getbasepath() + "/bapkasbos/editbapkas/" + aData['idSekolahBAPKas'] + "/" + is + "' class='icon-edit' ></a>  -  <a href='" + getbasepath() + "/bapkasbos/deletebapkas/" + aData['idSekolahBAPKas'] + "/" + is + "' class='icon-remove' ></a>  -  <a href='#' onclick='cetak(" + "" + aData['triwulan'].toString() + ")' class='icon-book' ></a>";
                } else {
                    document.getElementById('kolom5').innerHTML = 'Arsip  -  Unduh';
                    edit = "<a href='" + getbasepath() + "/bapkasbos/arsipbapkas/" + aData['idSekolahBAPKas'] + "/" + is + "' class='icon-edit' ></a>  -  <a href='#' onclick='cetak(" + "" + aData['triwulan'].toString() + ")' class='icon-book' ></a>";
                }
//                } else {
//                    edit = "<a href='" + getbasepath() + "/bapkasbos/viewbapkas/" + aData['idSekolahBAPKas'] + "/" + is + "' class='icon-print' ></a>  -  <a href='#' onclick='cetak(" + "" + aData['triwulan'].toString() + ")' class='icon-book' ></a>";
//                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(tgl);
                $('td:eq(2)', nRow).html(tw);
                $('td:eq(4)', nRow).html(edit);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "tahun", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglBkuBa", "bSortable": true, sClass: "center"},
                {"mDataProp": "triwulan", "bSortable": true, sClass: "center"},
                {"mDataProp": "ketBkuBa", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglBkuBa", "bSortable": true, sClass: "center"}

            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}


function pindahhalaman() {
    window.location.replace(getbasepath() + "/bapkasbos/addbapkas");
}

function cetak(triwulan) {
    var idsekolah = $("#idsekolah").val();
    var tahun = $("#tahun").val();

    window.location.href = getbasepath() + "/bapkasbos/json/prosescetak?idsekolah=" + idsekolah + "&triwulan=" + triwulan + "&tahun=" + tahun;
    ;
}

function isTutupBuku() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/bapkasbos/json/istutupbuku", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        $('#tutupbuku').val(result);
        gridbapkas();

    });
}

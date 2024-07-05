$(document).ready(function() {
    grid();
});


function grid( ) {
    $("#modultable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#modultable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/indexadmmodul/json/listmodul",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "namaModul", value: $('#namaModul').val()}
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
                //var noinduk = aData['idInduk'];
                var link = aData['namaModulLink'];
                var jumlahSubModul = aData['jumlahSubModul'];
                var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />"
                var pilih = "<a class='icon-edit' href='" + getbasepath() + "/bku/indexadmmodul/ubahmodul/" + aData['id'] + "'  ></a> "
                var pilihDelete = "<a class='icon-edit' href='" + getbasepath() + "/bku/indexadmmodul/ubahmodul/" + aData['id'] + "'  ></a> - <a class='icon-remove' href='" + getbasepath() + "/bku/indexadmmodul/delmodul/" + aData['id'] + "' ></a>"
                //var pilihplus = "<a class='icon-edit' href='" + getbasepath() + "/bku/indexadmmodul/ubahmodul/" + aData['id'] + "'  ></a> - <a class='icon-remove' href='" + getbasepath() + "/bku/indexadmmodul/delmodul/" + aData['id'] + "' ></a> - <a class='icon-plus' href='" + getbasepath() + "/bku/indexadmmodul/tambahmodul/" + aData['id'] + "' ></a>"
                var tambahmodul = "<a class='icon-plus' href='" + getbasepath() + "/bku/indexadmmodul/tambahmodul/" + aData['id'] +"/" + aData['noModul'] + "' ></a>"
                $('td:eq(0)', nRow).html(index);
                $('td:eq(8)', nRow).html(cekaktif);
                if (link == null || link == "")
                    $('td:eq(4)', nRow).html(tambahmodul);
                else
                    $('td:eq(4)', nRow).html("");
                if (jumlahSubModul > 0)
                    $('td:eq(9)', nRow).html(pilih);
                else
                    $('td:eq(9)', nRow).html(pilihDelete);


                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "id", "bSortable": false, sDefaultContent: "", sClass: "center", sWidth: '8%'},
                {"mDataProp": "idInduk", "bSortable": false, sDefaultContent: "", sClass: "center", sWidth: '8%'},
                {"mDataProp": "noModul", "bSortable": false, sDefaultContent: ""},
                {"mDataProp": "idInduk", "bSortable": false, sDefaultContent: "", sClass: "left", sWidth: '2%'},
                {"mDataProp": "namaModul", "bSortable": false, sDefaultContent: ""},
                {"mDataProp": "namaModulLink", "bSortable": false, sDefaultContent: ""},
                {"mDataProp": "keterangan", "bSortable": false, sDefaultContent: ""},
                {"mDataProp": "kodeAktif", "bSortable": false, sDefaultContent: "", sClass: "center"},
                {"mDataProp": "id", "bSortable": false, sDefaultContent: "", sClass: "left"}
            ]
        });
        //$("div.top").html("<a  href='" + getbasepath() + "/bku/indexadmmodul/tambahmodul' class='btn blue' style='float: right'>Tambah Data</a>");
        $("div.top").html(" ");

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}
/* tutup , idns
 function cetak() {
 var aaa = $("#tahun").val();
 var eee = aaa+"-"+"ADM-PENGGUNA.pdf";

 window.location.href= getbasepath() + "/useradm/json/prosescetak?tahun="+aaa+"&namafile="+eee;


 }*/


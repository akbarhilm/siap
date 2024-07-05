$(document).ready(function() {
    //grid();
});
var t = true;
function togle() {

    if (t == true) {
        $("input[name=isaktif]").prop('disabled', false);
        $("#simpan").prop('disabled', false);
        $("#tgl").text("Cancel");
        t = false;
    } else {
        t = true;
        grid();
        $("#tgl").text("Tambah/Edit");
        $("#simpan").prop('disabled', true);
    }

}
function cek() {
    if ($("#idPengguna").val() == "") {
        bootbox.alert("Pilih Pengguna Terlebih Dahulu!");
    } else {
        grid();
        $("#tgl").prop("disabled", false);
    }
}
function grid() {


    //console.log('aaaaaaaaaaaa')
    //$("#usertable").show();
//    if (typeof myTable == 'undefined') {
//        myTable =
    $('#usertable').dataTable({
        "bPaginate": false,
        "sPaginationType": "bootstrap",
        "bJQueryUI": true,
        "bProcessing": true,
        "bServerSide": false,
        // "bInfo": true,
        "bDestroy": true,
        "aLengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
        "iDisplayLength": 10,
        // "sDom": '<"top"i>irt<"bottom"flp><"clear">',
        "bScrollCollapse": true,
        "bFilter": false,
        "sAjaxSource": getbasepath() + "/modulpengguna/json/listmodul",
        //"aaSorting": [[1, "asc"]],
        "fnServerParams": function(aoData) {
            aoData.push(
                    {name: "idpengguna", value: $('#idPengguna').val()},
            {name: "kodeotor", value: $('#kodeotor').val()}
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
            var isceked = aData['kodeOtoritas'] == '1' ? 'checked' : '';
            var cekaktif = "<input type='hidden' id='idmodul" + index + "' value=" + aData['idModul'] + "> <input type='checkbox' name='isaktif' class='cekaktif' id='isaktif" + index + "' disabled='true'  " + isceked + " />"
            var pilih = "<a class='icon-edit' href='" + getbasepath() + "/useradm/ubahpengguna/" + aData['idPengguna'] + "'  ></a> - <a class='icon-remove' href='" + getbasepath() + "/useradm/delpengguna/" + aData['idPengguna'] + "' ></a>"


            if (aData['kodeDetail'] == 1) {
                $('td:eq(0)', nRow).css('padding-left', '30px');
                $('td:eq(1)', nRow).css('padding-left', '30px');
                $('td:eq(2)', nRow).css('padding-left', '30px');
                $('td:eq(3)', nRow).html(cekaktif);
            } else {
                $('td:eq(0)', nRow).css('font-weight', 'bold');
                $('td:eq(1)', nRow).css('font-weight', 'bold');
                $('td:eq(2)', nRow).css('font-weight', 'bold');
                $('td:eq(3)', nRow).html("");
            }
            //$('td:eq(9)', nRow).html(pilih);


            return nRow;
        },
        "aoColumns": [
            {"mDataProp": "noModul", "bSortable": false, sDefaultContent: "-", sWidth: "10%"},
            {"mDataProp": "namaModul", "bSortable": false, sDefaultContent: "-"},
            {"mDataProp": "uraianModul", "bSortable": false, sDefaultContent: "-"},
            {"mDataProp": "kodeOtoritas", "bSortable": false, sClass: "center"}

        ]
    });
//        var a = " ";
//
//        $("div.top").html(a);

//    }
//    else
//    {
//        myTable.fnClearTable(0);
//        myTable.fnDraw();
//    }
}
function simpan() {
    var varurl = getbasepath() + "/modulpengguna/json/simpanpenggunamodul";
    var dataac = [];
    $(".cekaktif:checked").each(function() {
        var a = $(this).attr('id');
        a = a.substr(a.length - (a.length - 7));
        var datapeg = {
            idPengguna: $("#idPengguna").val(),
            idModul: $("#idmodul" + a).val(),
            //idskpd:idskpdval
        }
        dataac.push(datapeg);
    });
    //console.log("datakirim == "+dataac);
    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {
        grid();
        togle();
        bootbox.alert(data.responseText);
    });

}
function cetak() {
    var aaa = $("#tahun").val();
    var eee = aaa + "-" + "ADM-PENGGUNA.pdf";

    window.location.href = getbasepath() + "/useradm/json/prosescetak?tahun=" + aaa + "&namafile=" + eee;


}
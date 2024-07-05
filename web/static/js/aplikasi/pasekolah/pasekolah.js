$(document).ready(function() {
    //grid();
});

//function ws(){
//
//
//      $.getJSON("http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD",{nrk:$("#nrk").val()},
//                function(data) {
//                   $("#nama").val(data.results[0]['NAMA']);
//                   $("#nip").val(data.results[0]['NIP18'])
//                }
//         );
//
//}
function tampil( ) {
    var pilih;
    var idpgun = $("#idPengguna").val();
    //$("#usertable").show();
//    if (typeof myTable == 'undefined') {
//        myTable =
    $('#usertable').dataTable({
        "bPaginate": false,
        "sPaginationType": "bootstrap",
        "bJQueryUI": true,
        "bProcessing": true,
        "bServerSide": false,
        "bInfo": false,
        "bDestroy": true,
        //"aLengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
        //"iDisplayLength": 10,
        "sDom": '<"top"i>irt<"bottom"flp><"clear">',
        "bScrollCollapse": true,
        "bFilter": false,
        "sAjaxSource": getbasepath() + "/pasekolah/json/listpasekolah",
        "aaSorting": [[1, "asc"]],
        "fnServerParams": function(aoData) {
            aoData.push(
                    {name: "idpengguna", value: $('#idPengguna').val()}
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
            if(aData['c_asal']=='1'){
             pilih = "";
        }else{
          pilih = "<a class='icon-remove' href='" + getbasepath() + "/pasekolah/nonaktif/" + aData['idSekolah'] + "/"+idpgun+"'  ></a>";
        }
            $('td:eq(0)', nRow).html(index);
           // $('td:eq(8)', nRow).html(cekaktif);
                $('td:eq(4)', nRow).html(pilih);


            return nRow;
        },
        "aoColumns": [
            {"mDataProp": "npsn", "bSortable": false, sClass: "center"},
            {"mDataProp": "npsn", "bSortable": false, sClass: "center"},
            {"mDataProp": "namaSekolah", "bSortable": true, sDefaultContent: "-", sClass: "center"},
            {"mDataProp": "alamatSekolah", "bSortable": true, sDefaultContent: "-"},
               {"mDataProp": "npsn", "bSortable": false, sClass: "center"}


        ]
    });
    $("div.top").html("<a class='fancybox fancybox.iframe btn blue' href='" + getbasepath() + "/sekolahpopup/listsekolahpa?target=_top'  style='float: right'>Tambah Data</a>");

}
//    else
//    {
//        myTable.fnDestroy();
//        myTable.fnDraw();
//    }
//}

function cetak() {
    var aaa = $("#tahun").val();
    var eee = aaa + "-" + "ADM-PENGGUNA.pdf";

    window.location.href = getbasepath() + "/useradm/json/prosescetak?tahun=" + aaa + "&namafile=" + eee;


}

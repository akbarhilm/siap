$(document).ready(function() {
    grid();
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
function grid( ) {


    if (typeof myTable == 'undefined') {
        myTable = $('#usertable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": true,
            "bProcessing": true,
            "bServerSide": false,
            "bInfo": true,
            //"aLengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
            "iDisplayLength": 10,
            //"sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": false,
            "bFilter": true,
            "sAjaxSource": getbasepath() + "/sekolah/json/getmcb",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                       
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
            "fnRowCallback": function(nRow, aaData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
               // var isceked = aData['kodeAktif'] == '1' ? 'checked' : '';
                //var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />"
                var pilih = "<a class='icon-edit' href='" + getbasepath() + "/sekolah/editmcb/" + aaData['idMcb'] + "'  ></a> - <a class='icon-remove' href='javascript:confirm("+aaData["idMcb"]+")' ></a>"
                
                $('td:eq(0)', nRow).html(index);
               
                $('td:eq(4)', nRow).html(pilih);


                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idMcb", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaSekolah", "bSortable": true, sDefaultContent: "-", },
                {"mDataProp": "noMcb", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaMcb", "bSortable": true, sDefaultContent: "-"},
                
                {"mDataProp": "idMcb", "bSortable": true, sDefaultContent: "-", sClass: "center"}
            ]
        });
        //$("div.bottom").html("<a  href='" + getbasepath() + "/useradm/tambahpengguna' class='btn blue' style='float: left'>Tambah Data</a>");

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function confirm(id){
    var url= getbasepath()+"/sekolah/deletemcb/"+id;
    bootbox.confirm("Hapus data ?", function(result){
        if(result){
        window.location.href=url; 
    }
    
});

}

function cetak() {
    var aaa = $("#tahun").val();
    var eee = aaa + "-" + "ADM-PENGGUNA.pdf";

    window.location.href = getbasepath() + "/useradm/json/prosescetak?tahun=" + aaa + "&namafile=" + eee;


}

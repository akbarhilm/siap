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
    console.log("username " + $('#username').val());
    console.log("kodeotoritas " + $('#kodeotoritas').val());
    $("#usertable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#usertable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": true,
            "bProcessing": true,
            "bServerSide": false,
            "bInfo": false,
            "aLengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
            "iDisplayLength": 10,
            //"sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": true,
            "sAjaxSource": getbasepath() + "/useradm/json/listpengguna",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "userName", value: $('#username').val()},
                {name: "kodeotoritas", value: $('#kodeotoritas').val()},
                {name: "idpengguna", value: $('#idpengguna').val()}
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
                var pilih = "<a class='icon-edit' href='" + getbasepath() + "/useradm/ubahpengguna/" + aData['idPengguna'] + "'  ></a> - <a class='icon-remove' href='" + getbasepath() + "/useradm/delpengguna/" + aData['idPengguna'] + "' ></a>"
                var grup="";
                var otoritas="";
                switch (aData['kodeGroup']) {
                    case "1":
                        grup = "BOS";
                        break;
                    case "2":
                        grup = "BOP";
                        break;
                    case "3":
                        grup = "BOS DAN BOP";
                        break;
                }
                
                switch (aData['kodeOtoritas']) {
                    case "0":
                        otoritas = "Sudin";
                        break;
                    case "1":
                        otoritas = "Kepala Sekolah (PA)";
                        break;
                    case "2":
                        otoritas = "Bendahara (PK)";
                        break;
                    case "8":
                        otoritas = "Admin";
                        break;
                    case "9":
                        otoritas = "Super Admin";
                        break;
                }
                console.log(grup);
                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(grup);
                $('td:eq(2)', nRow).html(otoritas);
                $('td:eq(8)', nRow).html(cekaktif);
                $('td:eq(9)', nRow).html(pilih);


                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idPengguna", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeGroup", "bSortable": true, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "kodeOtoritas", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idNrk", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaPengguna", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "jabatanPengguna", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "nipPengguna", "bSortable": true, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "namaEmail", "bSortable": true, sDefaultContent: "-", sClass: "left"},
                {"mDataProp": "kodeAktif", "bSortable": true, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "idPengguna", "bSortable": true, sDefaultContent: "-", sClass: "center"}
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

function cetak() {
    var aaa = $("#tahun").val();
    var eee = aaa + "-" + "ADM-PENGGUNA.pdf";

    window.location.href = getbasepath() + "/useradm/json/prosescetak?tahun=" + aaa + "&namafile=" + eee;


}

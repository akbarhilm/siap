$(document).ready(function() {
    
});

// variabel global
var maxTriwulan;

function cekSekolah() {
    var a = document.getElementById("btnTambah");
    
    if ($('#idsekolah').val() == ""){
        bootbox.alert("Pilih Sekolah Terlebih Dulu");
    } else {
        a.href = getbasepath() + "/koreksiakunbos/addkoreksi/"+$('#idsekolah').val();
    }
}

function getMaxTriwulan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/koreksiakunbos/json/getMaxTriwulan", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        
        maxTriwulan = result;
        
    });
}

function setTw() {
    $('#triwulan').val("-");
    grid();
}

function grid() {
    var urljson = getbasepath() + "/koreksiakunbos/json/listindex";
    
    if (typeof myTable == 'undefined') {
        myTable = $('#bkutable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            //"sDom": '<"top"i>lrt<"bottom"i>p<"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".checkbox").hide();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "idsekolah", value: $('#idsekolah').val()},
                {name: "triwulan", value: $('#triwulan').val()}
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
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {///cetakspp
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                //idBku, noBkuMohon, tglDok, noBuktiDok, uraianBukti, nilaiSpj
                var nilai = accounting.formatNumber(aData['nilaiSpj'], 2, '.', ",");
                var pilihan = "";
                var triwulan = $('#triwulan').val();
                
                var edit = "<a href='" + getbasepath() + "/koreksiakunbos/editkoreksi/" + "?idbku=" + aData['idBku'] + "' class='icon-edit' ></a>";
                var arsip = "<a href='" + getbasepath() + "/koreksiakunbos/arsipkoreksi/" + "?idbku=" + aData['idBku'] + "&idsekolah=" + "' class='icon-file-text' ></a>";
                
                if (parseInt(maxTriwulan)< parseInt(triwulan)){ // jika tw yg dipilih belum tutup
                    pilihan = edit;
                } else {
                    pilihan = arsip;
                }
                var uraian = "<textarea id='uraian" + index + "'readonly style='border:none;margin:0;width:400px;'>" + aData['uraianBukti'] + "</textarea>";
                var nilaispj = "<input type='text' id='nilai" + index + "'  class='inputmoney'  value='" + nilai + "' readOnly='true' />";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(4)', nRow).html(uraian); // uraian pake textarea
                $('td:eq(5)', nRow).html(nilaispj); // nilai diformat
                $('td:eq(6)', nRow).html(pilihan); // pilihan
               
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBku", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "tglDok", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "noBuktiDok", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "uraianBukti", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "nilaiSpj", "bSortable": false, sDefaultContent: "-", sClass: "right"},
                {"mDataProp": "idBku", "bSortable": false, sClass: "center"}
            ]
        });
        
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

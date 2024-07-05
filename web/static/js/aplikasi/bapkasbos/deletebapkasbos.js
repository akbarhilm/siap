$(document).ready(function() {
    gridbapkasrinci();
});

function setBulanEdit(tahun,idskpd) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    
    
    $.getJSON(getbasepath() + "/bapkasbos/json/setBulanEdit", {tahun: tahun,idskpd: idskpd},
    function(result) {
        //console.log(result);
      
        var banyak, bulan,ketbulan;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
             for (var i = 0; i < banyak; i++) {
                 bulan = result.aData[i]['BLNBKUBA'];
                 ketbulan = result.aData[i]['KETBUL'];
                 //alert(bulan+ketbulan);
                 opt += '<option value="'+ bulan + '">' + ketbulan + '</option>';
            
            }
        }
       
        $("#blnBkuBa1").html(opt);
       
        
    });
    
}

    function pindahhalamanadepan() {
        window.location.replace(getbasepath() + "/bapkasbos/indexbapkas");
    }

function countRow() {
    var rowCount = document.getElementById('bapkasrincitablebody').rows.length;
    var a, idrow, countidrow, banyakrinci;
    for (var a = 1; a <= idrow; a++) {
        countidrow += 1;
        console.log("cek count idrow = " + idrow);
        banyakrinci += countidrow;
    }
    console.log("total banyakrinci == " + banyakrinci);
    console.log("total rowCount == " + rowCount);
    //$('#banyakrinci').val(banyakrinci);
}


function gridbapkasrinci() { // untuk nampilin grid awal
    //document.getElementById("btnSimpan").style.visibility = "hidden";
    //console.log("CUMA TEST grid skpd nihil");
    $("#btnSimpan").hide();
    var urljson = getbasepath() + "/bapkasbos/json/getlistbapkasrinci";
    //console.log("idskpdval == "+$('#idskpd').val());
    //jumbarisawal=0;
    var index = 0;
    
    
     $("#bapkasrincitable").show();
     $("#bapkasrincitablefoot2").empty();
     
    if (typeof myTable == 'undefined') {
        myTable = $('#bapkasrincitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 10000, // buat nampilin semmua tanpa page
            //"sDom": '<"top"i>rt<"bottom"flp><"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idSkpdBAPKas", value: $('#idSkpdBAPKas').val()} // parsing parameter ke action "/lampasetttp/json/getlistasetttp"
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
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) { //nampilin table footer total
                var totalba = 0;
                for (var i = iStart; i < iEnd; i++) {
                }
                $("#totalnilaiba").val(accounting.formatNumber(totalba, 2, '.', ","));

            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) { //format untuk tampilan body tabel
                var numStart = this.fnPagingInfo().iStart;
                index = numStart + iDisplayIndexFull + 1;
                var idSkpdBAPKas = "<input type='hidden' id='idSkpdBAPKas" + index + "'   name='idSkpdBAPKas" + index + "'  value='" + aData['idSkpdBAPKas'] + "'  />";

                var idSkpdBAPKasRinci = "<input type='hidden' id='idSkpdBAPKasRinci" + index + "' name='idSkpdBAPKasRinci" + index + "' value='" + aData['idSkpdBAPKasRinci'] + "' />";
                //var namaBapKas = "<textarea type='text' id='namaBapKas" + index + "' name='namaBapKas" + index + "' value='" + aData['namaBapKas'] + "' readonly='true' style='margin: 0px; width: 200px; height: 42px;' ></textarea>";
                var namaBapKas = "<input type='text' name='namaBapKas" + index + "' id='namaBapKas" + index + "' value = '" + aData['namaBapKas'] + "'  readonly='true' />";
                var nilaiBapKas = "<input type='text' name='nilaiBapKas" + index + "' id='nilaiBapKas" + index + "' class='inputmoney' value = '" + aData['nilaiBapKas'] + "'  readonly='true' />";
                var pilih = "<a class='icon-edit' href='" + getbasepath() + "/bapkasbos/updatebapkas/" + aData['idBapKas'] + "'  ></a> - <a class='icon-remove' href='#' onclick='hapusrinci(" + index + ")' ></a>";
                var isceked = aData['nilaiBapKas'] >= 0 ? 'checked' : '';
                //var isceked = aData['nilaiBapKas'] !== 0 ? 'checked' : '';
                var readonly = 'readonly';
                var inputcek;
                if (isceked === 'checked') {
                    readonly = '';
                }
                inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";
           
                idrow = index;
                if (index === null || index === "" || index === 0) {
                    nomorgrid = 0;
                    idrowawal = 0;
                }
                else {
                    nomorgrid = parseInt(aData['idSkpdBAPKasRinci']);
                    idrowawal = idrow;
                }
                $('td:eq(0)', nRow).html(index + idSkpdBAPKasRinci + idSkpdBAPKas);
                $('td:eq(1)', nRow).html(namaBapKas);
                $('td:eq(2)', nRow).html(nilaiBapKas);
                $('td:eq(3)', nRow).html(pilih + idSkpdBAPKasRinci);
                $('td:eq(4)', nRow).html(inputcek);
                return nRow;
            },
            "aoColumns": [//kolom nya sesuaikan nama di model
                {"mDataProp": "idSkpdBAPKasRinci", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaBapKas", "bSortable": true, sClass: "left"},
                {"mDataProp": "nilaiBapKas", "bSortable": true, sClass: "center"},
                {"mDataProp": "idSkpdBAPKasRinci", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSkpdBAPKasRinci", "bSortable": false, sClass: "center"}
            ]
        });
        //console.log("sesese "+idrow);
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    getbanyakrincibapkas();
}

function getbanyakrincibapkas( ) {
    $.getJSON(getbasepath() + "/bapkasbos/json/getbanyakrincibapkas", {idSkpdBAPKas: $("#idSkpdBAPKas").val()},
    function (result) {
        console.log("total banyakrinci == " + result);
        $('#banyakrinci').val(result);
    });

}

function countRow() {
    var rowCount = document.getElementById('bapkasrincitablebody').rows.length;
    var a, idrow, countidrow, banyakrinci;
    for (var a = 1; a <= idrow; a++) {
        countidrow += 1;
        console.log("cek count idrow = " + idrow);
        banyakrinci += countidrow;
    }
    console.log("total banyakrinci == " + banyakrinci);
    console.log("total rowCount == " + rowCount);
    //$('#banyakrinci').val(banyakrinci);
    
}

function grid( ) {
    $("#skpdtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath()+"/sekolahpopup/json/listsekolahjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                  {name: "namasekolah", value: $('#namasekolah').val()},
                  {name: "npsn", value: $('#npsn').val()}
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
                var textnamaskpd = "<input type='hidden' id='namaSekolahPendek" + aData['idSekolah'] + "' value='" + aData['npsn']+"/"+aData['namaSekolahPendek'] + "' />"
                var textkodeskpd = "<input type='hidden' id='npsn" + aData['idSekolah'] + "' value='" + aData['npsn']+ "' />"
                var textnskpd = "<input type='hidden' id='namaSekolah" + aData['idSekolah'] + "' value='" + aData['namaSekolahPendek']+ "' />"
               
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='insert(" + aData['idSekolah'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSekolah", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "npsn", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSekolahPendek", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idSekolah", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}
function insert(id){
    var ipgun = $("#idPengguna",window.parent.document).val();
    var idsekolah = id;
    
   
    var varurl = getbasepath() + "/pasekolah/json/prosessimpan";
    var dataac = []; 
    //kodeGrup userName  userPassword idSkpd kodeWilayahProses ipAddress userEmail userNipPeg userNrkPeg userNamaPeg kodeAktif
    
    var datajour = {
        idpengguna: ipgun,
        idsekolah: idsekolah
       
    }

    dataac = datajour;
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
        bootbox.alert("Data User Berhasil Disimpan");
        parent.tampil();
         parent.$.fancybox.close();// ke halaman depan
        
    });
    
}
function gridbyidskpd( ) {  /* idns , 21-12-2017 */
    $("#skpdtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath()+"/sekolahpopup/json/listsekolahbyidskpdjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                  {name: "namasekolah", value: $('#namasekolah').val()},
                  {name: "npsn", value: $('#npsn').val()}
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
                var textnamaskpd = "<input type='hidden' id='namaSekolahPendek" + aData['idSekolah'] + "' value='" + aData['npsn']+"/"+aData['namaSekolahPendek'] + "' />"
                var textkodeskpd = "<input type='hidden' id='npsn" + aData['idSekolah'] + "' value='" + aData['npsn']+ "' />"
                var textnskpd = "<input type='hidden' id='namaSekolah" + aData['idSekolah'] + "' value='" + aData['namaSekolahPendek']+ "' />"
               
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textkodeskpd+textnskpd+textnamaskpd + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idSekolah'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSekolah", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "npsn", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSekolahPendek", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idSekolah", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}



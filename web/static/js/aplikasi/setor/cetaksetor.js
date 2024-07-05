$(document).ready(function() {
    grid();
});
function grid() {
    var urljson = getbasepath() + "/setor/json/listcetaksetor";
    if (typeof myTable == 'undefined') {
        myTable = $('#cetaksetortable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sDom": '<"top"i>lrt<"bottom"i>p<"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".checkbox").hide();
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
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {///cetakspp
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var idsekolah = $('#idsekolah').val();
                var tahun = $('#tahun').val();

                var nrkpa = "<input type='hidden' id='nrkpa" + aData['idSetor'] + "' value='" + aData['nrkPA'] + "'  />";
                var nippa = "<input type='hidden' id='nippa" + aData['idSetor'] + "' value='" + aData['nipPA'] + "'  />";
                var namapa = "<input type='hidden' id='namapa" + aData['idSetor'] + "' value='" + aData['namaPA'] + "'  />";
                var jabpa = "<input type='hidden' id='jabpa" + aData['idSetor'] + "' value='" + aData['pangkatPA'] + "'  />";
                var nrkpk = "<input type='hidden' id='nrkpk" + aData['idSetor'] + "' value='" + aData['nrkPK'] + "'  />";
                var nippk = "<input type='hidden' id='nippk" + aData['idSetor'] + "' value='" + aData['nipPK'] + "'  />";
                var namapk = "<input type='hidden' id='namapk" + aData['idSetor'] + "' value='" + aData['namaPK'] + "'  />";
                var jabpk = "<input type='hidden' id='jabpk" + aData['idSetor'] + "' value='" + aData['pangkatPK'] + "'  />";
                var donlod = "-";
                var batal = "-";
                var cekprint = "-";

                var stat = aData['statusCetak'];
                if (stat == 'PENGAJUAN') {
                    cekprint = "<input type='checkbox' name='cek" + aData['idSetor'] + "'  id='cek" + aData['idSetor'] + "' value='" + aData['idSetor'] + "' class='checkbox' />";
                } else if (stat == 'CETAK') {
                    donlod = "<a class='icon-book' href='" + getbasepath() + "/setor/cetaksetor/" + tahun + "/" + idsekolah + "/" + aData['noSetor'] + "/" + aData['ctrx'] + "' ></a>";
                    batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalcetak(" + aData['idSetor'] + "," + aData['noSetor'] + ") ></a>";
                } else if (stat == 'INPUTBKU') {
                    donlod = "<a class='icon-book' href='" + getbasepath() + "/setor/cetaksetor/" + tahun + "/" + idsekolah + "/" + aData['noSetor'] + "/" + aData['ctrx'] + "' ></a>";
                }

                $('td:eq(0)', nRow).html(index + nrkpa + nippa + namapa + jabpa + nrkpk + nippk + namapk + jabpk);
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiSetor']));
                $('td:eq(7)', nRow).html(cekprint);
                $('td:eq(8)', nRow).html(donlod);
                $('td:eq(9)', nRow).html(batal);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "tahunAnggaran", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSetor", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeTransaksi", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "nilaiSetor", "bSortable": false, sClass: "kanan", sDefaultContent: "-"},
                {"mDataProp": "statusCetak", "bSortable": false, sDefaultContent: "-", sClass: "left"},
                {"mDataProp": "namaPA", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"}
            ]
        });
        $("div.top").html("<span onclick=trigercetak()  class='Button btn dark' style='float: right'>Proses Cetak</span>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function trigercetak() {
    var baseurl = getbasepath();
    var selected = [];
    var idsekolah = $("#idsekolah").val();
    var idpengguna = $("#idpengguna").val();
    //var statuscek = 0;
    $('.checkbox:checked').each(function() {
        var id = $(this).val();
        var nrkpa = $("#nrkpa" + id).val() == "null" || $("#nrkpa" + id).val() == "" ? "-" : $("#nrkpa" + id).val();
        var nippa = $("#nippa" + id).val() == "null" || $("#nippa" + id).val() == "" ? "-" : $("#nippa" + id).val();
        var namapa = $("#namapa" + id).val() == "null" || $("#namapa" + id).val() == "" ? "-" : $("#namapa" + id).val();
        var jabpa = $("#jabpa" + id).val() == "null" || $("#jabpa" + id).val() == "" ? "-" : $("#jabpa" + id).val();
        var nrkpk = $("#nrkpk" + id).val() == "null" || $("#nrkpk" + id).val() == "" ? "-" : $("#nrkpk" + id).val();
        /*
         var nrkpk;
         if($("#nrkpk" + id).val() == "null"){
         console.log("nrkpk === null");
         nrkpk = "-";
         } else {
         console.log("nrkpk === else null");
         nrkpk = $("#nrkpk" + id).val();
         }
         */

        var nippk = $("#nippk" + id).val() == "null" || $("#nippk" + id).val() == "" ? "-" : $("#nippk" + id).val();
        var namapk = $("#namapk" + id).val() == "null" || $("#namapk" + id).val() == "" ? "-" : $("#namapk" + id).val();
        var jabpk = $("#jabpk" + id).val() == "null" || $("#jabpk" + id).val() == "" ? "-" : $("#jabpk" + id).val();
        console.log("data pk0 === " + nrkpk + " ; " + nippk + " ; " + namapk);
        console.log("data pk1 === " + $("#nrkpk" + id).val() + " ; " + $("#nippk" + id).val() + " ; " + $("#namapk" + id).val());
        //if (!(namaBendahara == null || namaBendahara == '' || namaBendahara == 'undefined' || $.trim(namaBendahara).length < 1) || !(namaPptk == null || namaPptk == '' || namaPptk == 'undefined' || $.trim(namaPptk).length < 1)) {

        var map = {"id": id,
            "nrkpa": nrkpa,
            "nippa": nippa,
            "namapa": namapa,
            "jabpa": jabpa,
            "nrkpk": nrkpk,
            "nippk": nippk,
            "namapk": namapk,
            "jabpk": jabpk,
            "idsekolah": idsekolah,
            "idpengguna": idpengguna
        };
        selected.push(map);
        //} else {
        //    statuscek++;
        //}

    });
    //console.log("data cetak === ");

    //if (statuscek == 0) {
    $.ajax({
        type: "POST",
        url: baseurl + "/setor/json/insertsetorcetak",
        contentType: "text/plain; charset=utf-8",
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(selected)
    }).always(function(data) {
        grid();
        bootbox.alert(data.responseText);
    });
    //} else {
    //    bootbox.alert("Pejabat penandatangan wajib dipilih sebelum cetak!");
    //}

}


function batalcetak(idsetor, nosetor) {
    var urlhapuscetaksetor = getbasepath() + "/setor/json/hapussetorcetak";
    bootbox.confirm("Anda akan membatalkan cetak data setor dengan nomor " + nosetor + " di " + $("#sekolah").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapuscetaksetor,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idsetor": idsetor, "nosetor": nosetor})
            }).always(function(data) {
                grid();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}

/*function batalspd(idspd, nospp,namafile) {

 var urlhapusspd = getbasepath() + "/cetakspp/json/hapussppcetak";
 bootbox.confirm("Anda akan membatalkan cetak data SPP dengan nomor " + nospp + " di" + $("#skpd").val() + ",  lanjutkan ? ", function (result) {
 if (result) {
 return   $.ajax({
 type: "POST",
 url: urlhapusspd,
 contentType: "text/plain; charset=utf-8",
 headers: {
 'Accept': 'application/json',
 'Content-Type': 'application/json'
 },
 data: JSON.stringify({"idspd": idspd, "nospp": nospp,"namafile":namafile})
 }).always(function (data) {
 gridsppup();
 bootbox.alert(data.responseText);
 });
 } else {
 bootbox.hideAll();
 return result;
 }
 });


 }*/

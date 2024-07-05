$(document).ready(function() {
    grid();
});
function grid() {
    var urljson = getbasepath() + "/setor/json/listsetorapprove";
    var tahun = $('#tahun').val();
    if (typeof myTable == 'undefined') {
        myTable = $('#approvesetortable').dataTable({
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
                        {name: "tahun", value: $('#tahun').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "sekolah", value: $('#textsekolah').val()}
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
                //var idsekolah = $('#idsekolah').val();
                //var tahun = $('#tahun').val();
                //console.debug("status === " + aData['statusCetak']);
                var sekolah = aData['sekolah']['npsn'] + " / " + aData['sekolah']['namaSekolah'];

                var pilihan = "-";
                var ceklist = "-";
                var batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalapprove(" + aData['idSetor'] + ") title='Batal Setuju'></a>";
                var donlod = "<a class='icon-print' href='" + getbasepath() + "/setor/cetaksetor/" + tahun + "/" + aData['sekolah']['idSekolah'] + "/" + aData['noSetor'] + "/" + aData['ctrx'] + "' ></a>";
                var rinci = "<a class='icon-file-text fancybox fancybox.iframe' href='" + getbasepath() + "/setor/viewrincisetor/" + aData['sekolah']['idSekolah'] + "/" + aData['idSetor'] + "?target=_top' title='Rincian per Kode Rekening'></a>";

                var stat = aData['statusCetak'];
                var transaksi = aData['kodeTransaksi'].substr(0, 5);
                if (stat == "CETAK") {
                    if (transaksi == "SETOR") {
                        pilihan = rinci;
                    }
                    ceklist = "<input type='checkbox' name='cek" + aData['idSetor'] + "'  id='cek" + aData['idSetor'] + "' value='" + aData['idSetor'] + "' class='checkbox' />";
                } else if (stat == "APPROVED") {
                    if (transaksi == "SETOR") {
                        pilihan = rinci + " . " + batal;
                    } else if (transaksi == "PENDA") {
                        pilihan = batal;
                    } else {
                        pilihan = ".";
                    }
                } else {
                    if (transaksi == "SETOR") {
                        pilihan = rinci + " . " + donlod;
                    } else if (transaksi == "PENDA") {
                        pilihan = donlod;
                    } else {
                        pilihan = ".";
                    }
                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(sekolah);
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiSetor']));
                $('td:eq(6)', nRow).html(ceklist);
                $('td:eq(7)', nRow).html(pilihan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "noSetor", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeTransaksi", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "nilaiSetor", "bSortable": false, sClass: "kanan", sDefaultContent: "-"},
                {"mDataProp": "statusCetak", "bSortable": false, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"}
            ]
        });
        $("div.top").html("<span onclick=trigerapprove()  class='Button btn dark' style='float: right'>Proses Persetujuan</span>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function trigerapprove() {
    var baseurl = getbasepath();
    var selected = [];
    //var idsekolah = $("#idsekolah").val();
    //var idpengguna = $("#idpengguna").val();
    var nrkpengguna = $("#nrkpengguna").val();
    console.log("OOOII = " + nrkpengguna);
    console.log("OOOII = " + $("#nrkpengguna").val());
    var cek = 0;
    $('.checkbox:checked').each(function() {
        var id = $(this).val();
        /*
         var nrkpa = $("#nrkpa" + id).val() == "null" || $("#nrkpa" + id).val() == "" ? "-" : $("#nrkpa" + id).val();
         var nippa = $("#nippa" + id).val() == "null" || $("#nippa" + id).val() == "" ? "-" : $("#nippa" + id).val();
         var namapa = $("#namapa" + id).val() == "null" || $("#namapa" + id).val() == "" ? "-" : $("#namapa" + id).val();
         var jabpa = $("#jabpa" + id).val() == "null" || $("#jabpa" + id).val() == "" ? "-" : $("#jabpa" + id).val();
         var nrkpk = $("#nrkpk" + id).val() == "null" || $("#nrkpk" + id).val() == "" ? "-" : $("#nrkpk" + id).val();

         var nippk = $("#nippk" + id).val() == "null" || $("#nippk" + id).val() == "" ? "-" : $("#nippk" + id).val();
         var namapk = $("#namapk" + id).val() == "null" || $("#namapk" + id).val() == "" ? "-" : $("#namapk" + id).val();
         var jabpk = $("#jabpk" + id).val() == "null" || $("#jabpk" + id).val() == "" ? "-" : $("#jabpk" + id).val();
         */
        //console.log("data pk0 === "+ nrkpk +" ; "+ nippk +" ; "+ namapk);
        //console.log("data pk1 === "+ $("#nrkpk" + id).val() +" ; "+ $("#nippk" + id).val() +" ; "+ $("#namapk" + id).val());
        //if (!(namaBendahara == null || namaBendahara == '' || namaBendahara == 'undefined' || $.trim(namaBendahara).length < 1) || !(namaPptk == null || namaPptk == '' || namaPptk == 'undefined' || $.trim(namaPptk).length < 1)) {

        var map = {"id": id,
            "nrkpengguna": nrkpengguna
                    /*,
                     "nrkpa": nrkpa,
                     "nippa": nippa,
                     "namapa": namapa,
                     "jabpa": jabpa,
                     "nrkpk": nrkpk,
                     "nippk": nippk,
                     "namapk": namapk,
                     "jabpk": jabpk,
                     "idsekolah": idsekolah,
                     "idpengguna": idpengguna*/
        };
        selected.push(map);
        //} else {
        //    statuscek++;
        //}
        cek++;
    });
    //console.log("data cetak === ");

    if (cek != 0) {
        $.ajax({
            type: "POST",
            url: baseurl + "/setor/json/insertsetorapprove",
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
    } else {
        bootbox.alert("Data tidak ada yang di check.");
    }

}


function batalapprove(idsetor) {
    var urlhapuscetaksetor = getbasepath() + "/setor/json/hapussetorapprove";
    bootbox.confirm("Anda akan membatalkan Persetujuan Setoran,  lanjutkan ? ", function(result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapuscetaksetor,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idsetor": idsetor})
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

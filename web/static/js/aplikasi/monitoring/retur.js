$(document).ready(function() {
    //setBulan();

});

function tampil() {

    if (typeof myTable == 'undefined') {
        
        myTable = $('#usertable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, 200, 5000], [25, 50, 100, 200, "All"]],
            "iDisplayLength": 50,
            "sAjaxSource": getbasepath() + "/monretur/json/listretur",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                        {name: "idsekolah", value: $("#idsekolah").val()},
                        {name: "tw", value: $("#tw").val()},
                        {name: "tipe", value: $("#tiperek").val()}
                                
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
               
                var nilai = accounting.formatNumber(aData['nilaiSpj'], 2, '.', ",");
               
                var nilaii = "<label>"+nilai+"</label>";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(nilaii);
               
                return nRow;
            },            
            "aoColumns": [
                {"mDataProp": "idBku", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "tglTrf", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center"},
                {"mDataProp": "rekeningTujuan", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaTujuan", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiSpj", "bSortable": false, sClass: "right"},
                {"mDataProp": "ketBank", "bSortable": false, sClass: "left"},
                
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();        
    }   
}

function cetak() {
    //alert("cetak mon bku bop har");
    var idsekolah = $('#idSekolah').val();
    var tglPosting = $("#tglPosting").val();
    var tahun = $("#tahun").val();
    if (idsekolah == "") {
        bootbox.alert("Data Tidak Tersedia");
    } else {
        //window.location.href = getbasepath() + "/bku/json/prosescetakmonbkubophar?tahun=" + tahun + "&idsekolah=" + idsekolah + "&tglPosting=" + tglPosting + "&jenislaporan=" + jenislaporan + "&saldo=" + saldo + "&akun=" + akun + "&wilayah=" + wilayah;
        window.location.href = getbasepath() + "/bku/json/prosescetakmonbkubophar?tahun=" + tahun + "&idsekolah=" + idsekolah + "&tglPosting=" + tglPosting;
    }
}

/*
function getTotal() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var bulan = $('#bulan').val();

    $.getJSON(getbasepath() + "/bkubop/json/getTotal", {tahun: tahun, idsekolah: idsekolah, bulan: bulan},
    function(result) {

        var tm = result.aData['nilaiMasuk'];
        var tk = result.aData['nilaiKeluar'];
        var ts = result.aData['nilaiSisa'];

        $("#totmasuk").val(accounting.formatNumber(tm, 2, '.', ","));
        $("#totkeluar").val(accounting.formatNumber(tk, 2, '.', ","));
        $("#totsaldokas").val(accounting.formatNumber(ts, 2, '.', ","));
        $("#saldokas").val(accounting.formatNumber(ts, 2, '.', ","));

    });
}
*/
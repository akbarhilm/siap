$(document).ready(function() {
    //setBulan();

});

function gridmon() {
console.log("tess masuk grid gridmon");
    if (typeof myTable == 'undefined') {
        
        myTable = $('#monjourtable').dataTable({
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
            "sAjaxSource": getbasepath() + "/bku/json/listIndexMonitoringBkuBopHarian",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                        {name: "idsekolah", value: $("#idSekolah").val()},
                        {name: "tglPosting", value: $("#tglPosting").val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                var nilaitotm = accounting.formatNumber($("#totmasuk").val(), 2, '.', ",");
                var nilaitotk = accounting.formatNumber($("#totkeluar").val(), 2, '.', ",");                
                var saldokas = accounting.formatNumber($("#totsaldokas").val(), 2, '.', ",");
                $("#totmasuk").val(nilaitotm);
                $("#totkeluar").val(nilaitotk);
                $("#totsaldokas").val(saldokas);
            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $("#totmasuk").val(0);  // init
                $("#totkeluar").val(0); // init
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {                
                var totmasuk = parseFloat($("#totmasuk").val()) + parseFloat(aData['perimaan']) ;
                $("#totmasuk").val(totmasuk); 
                var totkeluar = parseFloat($("#totkeluar").val()) + parseFloat(aData['pengeluaran']);
                $("#totkeluar").val(totkeluar);             
                var saldokas = parseFloat(aData['saldo']);
                $("#totsaldokas").val(saldokas);             
                var nilaim = accounting.formatNumber(aData['perimaan'], 2, '.', ",");
                var nilaik = accounting.formatNumber(aData['pengeluaran'], 2, '.', ",");
                var nilais = accounting.formatNumber(aData['saldo'], 2, '.', ",");                
                var nilaimasuk = "<label>"+nilaim+"</label>";
                var nilaikeluar = "<label>"+nilaik+"</label>";
                var saldo = "<label>"+nilais+"</label>";
                $('td:eq(6)', nRow).html(nilaimasuk);
                $('td:eq(7)', nRow).html(nilaikeluar);
                $('td:eq(8)', nRow).html(saldo);
                return nRow;
            },            
            "aoColumns": [
                {"mDataProp": "noUrut", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "noBku", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "namaTrx", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBuktiDoc", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglBuktiDoc", "bSortable": false, sClass: "left"},
                {"mDataProp": "keterangan", "bSortable": false, sClass: "center"},
                {"mDataProp": "perimaan", "bSortable": false, sClass: "right"},
                {"mDataProp": "pengeluaran", "bSortable": false, sClass: "right"},
                {"mDataProp": "saldo", "bSortable": false, sClass: "right"},
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
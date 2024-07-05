$(document).ready(function() {
    //setBulan();

});

function gridmon() {
console.log("tess masuk grid gridmon");
    if (typeof myTable == 'undefined') {
        
        myTable = $('#montriwlnjourtable').dataTable({
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
            "sAjaxSource": getbasepath() + "/bku/json/listIndexMonitoringAkbBkuPerTriwulan",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                        {name: "idsekolah", value: $("#idSekolah").val()},
                        {name: "triwulan", value: $("#triwulan").val()},
                        {name: "kodeKegiatanFilter", value: $("#kodeKegiatanFilter").val()},
                        {name: "namaKegiatanFilter", value: $("#namaKegiatanFilter").val()},
                        {name: "kodeAkunFilter", value: $("#kodeAkunFilter").val()},
                        {name: "namaAkunFilter", value: $("#namaAkunFilter").val()},
                        {name: "selisihFilter", value: $("#selisihFilter").val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                
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

                var nilaiakb = accounting.formatNumber(aData['nilaiAkb'], 2, '.', ",");
                var nilaipengbku = accounting.formatNumber(aData['nilaiPengajuanBku'], 2, '.', ",");
                var nilaiselisih = accounting.formatNumber(aData['selisih'], 2, '.', ",");

                var selisih = parseFloat(aData['selisih']);
                var tanda = "<b class='icon-ok-sign' style='color:green;font-size:16pt;'></b>";
                if(selisih < 0)
                    tanda = "<b class='icon-minus-sign'  style='color:red;font-size:16pt;'></b>";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(nilaiakb);
                $('td:eq(6)', nRow).html(nilaipengbku);
                $('td:eq(7)', nRow).html(nilaiselisih);
                $('td:eq(8)', nRow).html(tanda);
                return nRow;
            },            
            "aoColumns": [
                {"mDataProp": "noUrut", "bSortable": false, "sWidth": "3%", sClass: "center"},
                {"mDataProp": "kodeKegiatan", "bSortable": false, "sWidth": "13%", sClass: "center"},
                {"mDataProp": "namaKegiatan", "bSortable": false, "sWidth": "13%", sClass: "left"},
                {"mDataProp": "kodeAkun", "bSortable": false, "sWidth": "13%", sClass: "center"},
                {"mDataProp": "namaAkun", "bSortable": false, "sWidth": "13%", sClass: "left"},
                {"mDataProp": "nilaiAkb", "bSortable": false, "sWidth": "13%", sClass: "right"},
                {"mDataProp": "nilaiPengajuanBku", "bSortable": false, "sWidth": "13%", sClass: "right"},
                {"mDataProp": "selisih", "bSortable": false, "sWidth": "13%", sClass: "right"},
                {"mDataProp": "tanda", "bSortable": false, "sWidth": "6%", sClass: "right"}                                
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
    var tahun = $("#tahun").val();
    var idsekolah = $('#idSekolah').val();
    var triwulan = $("#triwulan").val();

    var kodeKegiatanFilter = $("#kodeKegiatanFilter").val();
    var namaKegiatanFilter = $("#namaKegiatanFilter").val();
    var kodeAkunFilter = $("#kodeAkunFilter").val();
    var namaAkunFilter = $("#namaAkunFilter").val();
    var selisihFilter = $("#selisihFilter").val();
      
    if (idsekolah == "") {
        bootbox.alert("Data Tidak Tersedia");
    } else {
        window.location.href = getbasepath() + "/bku/json/prosescetakakbbkutriwulan?tahun=" + tahun + "&idsekolah=" + idsekolah + "&triwulan=" + triwulan 
                + "&kodeKegiatanFilter=" + kodeKegiatanFilter+ "&namaKegiatanFilter=" + namaKegiatanFilter +"&kodeAkunFilter=" + kodeAkunFilter
                +"&namaAkunFilter=" + namaAkunFilter+"&selisihFilter=" + selisihFilter;
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
function cariKodeKegiatan() {
    $("#kodeKegiatanFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridmon();
            $("#kodeKegiatanFilter").focus();
        }
    });
}
function cariNamaKegiatan() {
    $("#namaKegiatanFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridmon();
            $("#namaKegiatanFilter").focus();
        }
    });
}
function cariKodeAkun() {
    $("#kodeAkunFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridmon();
            $("#kodeAkunFilter").focus();
        }
    });
}

function cariNamaAkun() {
    $("#namaAkunFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridmon();
            $("#namaAkunFilter").focus();
        }
    });
}

function cariSelisihxx() {
    alert('cariSelisih..'+$("#selisihFilter").val());
    $("#selisihFilter").change(function() {
        alert('selisih chngae chngae..'+$("#selisihFilter").val());
        gridmon();
        $("#selisihFilter").focus();
    });
}

function cariSelisih() {
    gridmon();    
    $("#selisihFilter").focus();
}


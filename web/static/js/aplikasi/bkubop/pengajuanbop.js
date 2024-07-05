$(document).ready(function() {
    getTutupBuku();
});

var banyak = 0;

function checkAll(object) {
    var length = document.getElementById('mytablebody').rows.length;
    var checked = object.checked;
    var ada = false;
    var noMohon;
    /*
     for (var i = 1; i <= length; i++) {
     noMohon = $("#noMohon" + i).val();
     checkNilai(noMohon, function(value) {
     if (value) {
     ada = value
     }
     });
     }
     */
    if (banyak > 0) {
        $('#isapproved').prop('checked', false);
        bootbox.alert("Ada SPJ yang pajaknya belum terakumulasi, Silahkan periksa kembali pajak penerimaan.");
    } else {
        for (var i = 1; i <= length; i++) {
            if (checked == true) {
                $('#isapproved' + i).prop('checked', true);
            } else {
                $('#isapproved' + i).prop('checked', false);
            }
        }
    }
}

function checkNilai(noBkuMohon, callback) {
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();
    $.getJSON(getbasepath() + "/statusbop/json/validateNilai", {idSekolah: idSekolah,
        triwulan: triwulan, noBkuMohon: noBkuMohon},
    function(result) {
        if (result != 0) {
            callback(true);
        } else {
            callback(false);
        }
    });
}

function checkNilai2(noBkuMohon) {
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();
    $.getJSON(getbasepath() + "/statusbop/json/validateNilai", {idSekolah: idSekolah,
        triwulan: triwulan, noBkuMohon: noBkuMohon},
    function(result) {
        banyak += result;
    });
}

function checkBox(object) {
    var length = document.getElementById('mytablebody').rows.length;
    var idCheckbox = $(object).attr("id")
    var index = idCheckbox.substr(10, idCheckbox.length);
    var noMohon = $("#noMohon" + index).val();
    var checked = object.checked;
    var count = 0;
    console.log(checked + " ");
    checkNilai(noMohon, function(value) {
        console.log($("#noMohon" + index).val() + " " + noMohon);
        if (value) {
            $('#isapproved' + index).prop('checked', false);
            bootbox.alert("Silahkan periksa kembali pajak penerimaan untuk SPJ ini.");
        } else {
            for (var i = 1; i <= length; i++) {
                if ($("#noMohon" + i).val() == noMohon && i != index) {
                    console.log("masuk sini " + i);
                    if (checked == true) {
                        console.log(i + " ini i")
                        $('#isapproved' + i).prop('checked', true);
                    } else {
                        $('#isapproved' + i).prop('checked', false);
                    }
                }
                if ($('#isapproved' + i).is(':checked')) {
                    count++
                }
            }
        }
    });


    if (count == length) {
        $('#isapproved').prop('checked', true);
    } else {
        $('#isapproved').prop('checked', false);
    }
}
function setCombo() {
    var opt = '<option value="-" selected="true">-- Pilih --</option>'
    console.log("T1 " + $("#tutup1").val());


    if ($("#tutup1").val() != 0) {
        opt = opt + '<option value="1">Triwulan I</option>'
    } else if ($("#tutup2").val() != 0) {
        opt = opt + '<option value="2">Triwulan II</option>'
    } else if ($("#tutup3").val() != 0) {
        opt = opt + '<option value="3">Triwulan III</option>'
    } else if ($("#tutup4").val() != 0) {
        opt = opt + '<option value="4">Triwulan IV</option>'
    }
    console.log(opt);
    $("#triwulan").html(opt);
    cek();
}

function cek() {
    $("#ajukan").prop('disabled', true);
    $("#undo").prop('disabled', true);
    if ($("#triwulan").val() == '-') {
        $("#sudah").val('');
        $("#belum").val('');
        $("#approve").val('');
        $("#sudin").val('');
        $('#tabelpengajuan').hide();
    } else {
        getBanyak();
        getTotal1();

    }
}
function buttonAjukan() {
    if ($('#isapproved').checked) {
        prosesAjukanAll();
    } else {
        prosesAjukan();
    }
}
function buttonBatalkan() {
    if ($('#isapproved').checked) {
        prosesBatalkanAll();
    } else {
        prosesBatalkan();
    }
}
function prosesAjukanAll() {
    var varurl = getbasepath() + "/statusbop/json/prosesupdate";
    var dataac = [];
    var data = {tahun: $("#tahun").val(), idSekolah: $("#idSekolah").val(),
        triwulan: $("#triwulan").val(), statusSebelum: '0', statusSesudah: '1', kodeTrx: 'JJ'}
    dataac = data;
    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {
        bootbox.alert("Berhasil diajukan");
        cek();
    });
}

function prosesBatalkanAll() {
    var varurl = getbasepath() + "/statusbop/json/prosesupdate";
    var dataac = [];
    var data = {tahun: $("#tahun").val(), idSekolah: $("#idSekolah").val(),
        triwulan: $("#triwulan").val(), statusSebelum: '1', statusSesudah: '0', kodeTrx: 'JJ'}
    dataac = data;
    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {
        bootbox.alert("Berhasil dibatalkan");
        cek();
    });
}

function prosesAjukan() {
    var varurl = getbasepath() + "/statusbop/json/prosesupdatemohon";
    var dataac = [];
    var statusSesudah = '1';

    $(".ischecked:checked").each(function() {
        var id = $(this).attr('id');
        id = id.substr(10, id.length);
        var noMohon = $("#noMohon" + id).val();

        var data = {tahun: $("#tahun").val(), idSekolah: $("#idSekolah").val(),
            triwulan: $("#triwulan").val(), statusSebelum: '0', statusSesudah: statusSesudah, kodeTrx: 'JJ', noMohon: noMohon}
        dataac.push(data);
    });

    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {
        bootbox.alert("Berhasil diajukan");
        cek();
    });
}
function prosesBatalkan() {
    var varurl = getbasepath() + "/statusbop/json/prosesupdatemohon";
    var dataac = [];
    var statusSesudah = '0';

    $(".ischecked:checked").each(function() {
        var id = $(this).attr('id');
        id = id.substr(10, id.length);
        var noMohon = $("#noMohon" + id).val();

        var data = {tahun: $("#tahun").val(), idSekolah: $("#idSekolah").val(),
            triwulan: $("#triwulan").val(), statusSebelum: '1', statusSesudah: statusSesudah, kodeTrx: 'JJ', noMohon: noMohon}
        dataac.push(data);
    });

    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {
        bootbox.alert("Berhasil dibatalkan");
        cek();
    });
}
function showGridPengajuan() {
    var total = parseInt($('#sudah').val()) + parseInt($('#belum').val());
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();
    banyak = 0;

    var urljson = getbasepath() + "/statusbop/json/listpengajuan";
    myTable = $('#pnjtable').dataTable({
        "bPaginate": true,
        "sPaginationType": "bootstrap",
        "bJQueryUI": false,
        "bProcessing": true,
        "aLengthMenu": [[total], ["Semua"]],
        "iDisplayLength": total,
        "bServerSide": true,
        "bInfo": true,
        "bScrollCollapse": true,
        //"sScrollY": ($(window).height() * 108 / 100),
        "bFilter": false,
        "sAjaxSource": urljson,
        "bDestroy": true,
        "aaSorting": [[1, "asc"]],
        "fnServerParams": function(aoData) {
            aoData.push(
                    {name: "tahun", value: tahun},
            {name: "idSekolah", value: idSekolah},
            {name: "triwulan", value: triwulan},
            {name: "kodeTrx", value: 'JJ'},
            {name: "dari", value: '0'},
            {name: "hingga", value: '1'},
            {name: "kodeKegiatanFilter", value: $("#kodeKegiatanFilter").val()},
            {name: "namaKegiatanFilter", value: $("#namaKegiatanFilter").val()},
            {name: "kodeAkunFilter", value: $("#kodeAkunFilter").val()},
            {name: "namaAkunFilter", value: $("#namaAkunFilter").val()}
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
            var isChecked = aData['status'] == '1' ? 'checked' : '';
//            var cekStatus = "<input type='checkbox' name='isajui" + index + "' id='isajui" + index + "' disabled  " + isChecked + " />"

            var cekStatus = "<input type='hidden' id='noMohon" + index + "' value=" + aData['noMohon'] + "><input type='checkbox' onclick='checkBox(this)' class='ischecked' name='isapproved" + index + "' id='isapproved" + index + "'  " + isChecked + " />";
            checkNilai2(aData['noMohon']);
            $('td:eq(0)', nRow).html(index);
            $('td:eq(1)', nRow).html(cekStatus);
            $('td:eq(9)', nRow).html(accounting.formatNumber(aData['kasKeluar'], 2, '.', ","));

            return nRow;
        },
        "aoColumns": [
            {"mDataProp": "noMohon", "bSortable": false, sClass: "center"},
            {"mDataProp": "noMohon", "bSortable": false, sClass: "center"},
            {"mDataProp": "noMohon", "bSortable": true, sClass: "center"},
            {"mDataProp": "kodeKegiatan", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaKegiatan", "bSortable": true, sClass: "left"},
            {"mDataProp": "kodeAkun", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaAkun", "bSortable": true, sClass: "left"},
            {"mDataProp": "kodeKomponen", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaKomponen", "bSortable": true, sClass: "left"},
            {"mDataProp": "kasKeluar", "bSortable": true, sClass: "right"},
        ]
    });
}

function getTotal1() {
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();

    $.getJSON(getbasepath() + "/statusbop/json/getTotalDari", {tahun: tahun,
        idSekolah: idSekolah, triwulan: triwulan, kodeTrx: 'JJ', dari: '0', hingga: '1'},
    function(result) {

        var total = result.aData;
        console.log("TOTAL 1 " + total);
        $("#totkeluar1").val(accounting.formatNumber(total, 2, '.', ","));
    });
}

function getBanyak() {
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();

    $.getJSON(getbasepath() + "/statusbop/json/getBanyak", {tahun: tahun,
        idSekolah: idSekolah, triwulan: triwulan, kodeTrx: 'JJ'},
    function(result) {

        var belum = result.aData[0];
        var sudah = result.aData[1];
        var approve = result.aData[2];
        var sudin = result.aData[3];

        $("#belum").val(belum);
        $("#sudah").val(sudah);
        $("#approve").val(approve);
        $("#sudin").val(sudin);
        if (belum != '0') {
            console.log("ABC " + belum);
            $("#ajukan").prop('disabled', false);
        } /*else {
         $('#isapproved').prop('checked', true);
         }*/
        if (sudah != '0') {
            console.log("ABC " + sudah);
            $("#undo").prop('disabled', false);
        } /*else {
         $('#isapproved').prop('checked', true);
         }*/

        $('#tabelpengajuan').show();
        showGridPengajuan();
    });
}
function getTutupBuku() {
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();

    $.getJSON(getbasepath() + "/statusbop/json/getTutupBuku", {tahun: tahun,
        idSekolah: idSekolah, kodeTrx: 'JJ'},
    function(result) {

        console.log("ABCD1 " + result.aData[0]);
        console.log("ABCD2 " + result.aData[1]);
        console.log("ABCD3 " + result.aData[2]);
        console.log("ABCD4 " + result.aData[3]);
        $('#tutup1').val(result.aData[0]);
        $('#tutup2').val(result.aData[1]);
        $('#tutup3').val(result.aData[2]);
        $('#tutup4').val(result.aData[3]);
        setCombo();
    });
}
function cariKodeKegiatan() {
    $("#kodeKegiatanFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            showGrid();
            $("#kodeKegiatanFilter").focus();
        }
    });
}
function cariNamaKegiatan() {
    $("#namaKegiatanFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            showGrid();
            $("#namaKegiatanFilter").focus();
        }
    });
}
function cariKodeAkun() {
    $("#kodeAkunFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            showGrid();
            $("#kodeAkunFilter").focus();
        }
    });
}
function cariNamaAkun() {
    $("#namaAkunFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            showGrid();
            $("#namaAkunFilter").focus();
        }
    });
}
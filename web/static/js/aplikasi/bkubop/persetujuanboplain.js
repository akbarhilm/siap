$(document).ready(function() {
    getTutupBuku();
});

function setCombo() {
    var opt = '<option value="-" selected="true">-- Pilih --</option>'
//    console.log("T1 " + $("#tutup1").val());
    if ($("#trx").val() != '-') {
        if ($("#tutup1").val() != 0) {
            opt = opt + '<option value="1">Triwulan I</option>'
        } else if ($("#tutup2").val() != 0) {
            opt = opt + '<option value="2">Triwulan II</option>'
        } else if ($("#tutup3").val() != 0) {
            opt = opt + '<option value="3">Triwulan III</option>'
        } else if ($("#tutup4").val() != 0) {
            opt = opt + '<option value="4">Triwulan IV</option>'
        }
    }
    console.log(opt);
    $("#triwulan").html(opt);
    cek();
}

function getTutupBuku() {
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var trx = $('#trx').val();
    $.getJSON(getbasepath() + "/statusbop/json/getTutupBuku", {tahun: tahun,
        idSekolah: idSekolah, kodeTrx: trx},
    function(result) {
        console.log("Trx " + trx);
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
function checkAll(object) {
    var length = document.getElementById('mytablebody').rows.length;
    var checked = object.checked;
    for (var i = 1; i <= length; i++) {
        if (checked == true) {
            $('#isapproved' + i).prop('checked', true);
        } else {
            $('#isapproved' + i).prop('checked', false);
        }
    }
}
function checkBox(object) {
    var length = document.getElementById('mytablebody').rows.length;
    var idCheckbox = $(object).attr("id")
    var index = idCheckbox.substr(10, idCheckbox.length);
    var noMohon = $("#noMohon" + index).val();
    var checked = object.checked;
    var count = 0;
    console.log(checked + " ");
    for (var i = 1; i <= length; i++) {

        console.log($("#noMohon" + i).val() + " " + noMohon);
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
    if (count == length) {
        $('#isapproved').prop('checked', true);
    } else {
        $('#isapproved').prop('checked', false);
    }
}
function cek() {
    $("#setujui").prop('disabled', true);
    $("#undo").prop('disabled', true);
    if ($("#triwulan").val() == '-' || $("#trx").val() == '-') {
        $("#sudah").val('');
        $("#belum").val('');
        $("#approve").val('');
        $("#sudin").val('');
        $('#tabel').hide();
    } else {
        $('#tabel').show();
        getBanyak();
        getTotal();
    }
}

function prosesSetujuiAll() {
    var trx = $("#trx").val();
    var varurl = getbasepath() + "/statusbop/json/prosesupdate";
    var dataac = [];
    var statusSesudah;
    if (trx == 'JJ') {
        statusSesudah = '3';
    } else {
        statusSesudah = '3';
    }
    var data = {tahun: $("#tahun").val(), idSekolah: $("#idSekolah").val(),
        triwulan: $("#triwulan").val(), statusSebelum: '1', statusSesudah: statusSesudah, kodeTrx: trx}
    dataac = data;
    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {
        bootbox.alert("Berhasil disetujui");
        cek();
    });
}

function prosesSetujui() {
    var varurl = getbasepath() + "/statusbop/json/prosesupdatemohon";
    var dataac = [];
    var statusSesudah = '3';

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
        bootbox.alert("Berhasil disetujui");
        cek();
    });
}
function prosesBatalkan() {
    var varurl = getbasepath() + "/statusbop/json/prosesupdatemohon";
    var dataac = [];
    var statusSesudah = '1';

    $(".ischecked:checked").each(function() {
        var id = $(this).attr('id');
        id = id.substr(10, id.length);
        var noMohon = $("#noMohon" + id).val();

        var data = {tahun: $("#tahun").val(), idSekolah: $("#idSekolah").val(),
            triwulan: $("#triwulan").val(), statusSebelum: '3', statusSesudah: statusSesudah, kodeTrx: 'JJ', noMohon: noMohon}
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
        hapusToken();
    });
}

function prosesBatalkanAll() {
    var trx = $("#trx").val();
    var varurl = getbasepath() + "/statusbop/json/prosesupdate";
    var dataac = [];
    var statusSebelum;
    if (trx == 'JJ') {
        statusSebelum = '3';
    } else {
        statusSebelum = '3';
    }
    var data = {tahun: $("#tahun").val(), idSekolah: $("#idSekolah").val(),
        triwulan: $("#triwulan").val(), statusSebelum: statusSebelum, statusSesudah: '1', kodeTrx: trx}
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
        hapusTokenSemua();
        $('#isapproved').prop('checked', true);
    });
}
function buttonSetujui() {
    if (($('#isapproved').checked && $('#trx').val() == 'JJ') || $('#trx').val() != 'JJ') {
        prosesSetujuiAll();
    } else {
        prosesSetujui();
    }
}
function buttonBatalkan() {
    if (($('#isapproved').checked && $('#trx').val() == 'JJ') || $('#trx').val() != 'JJ') {
        prosesBatalkanAll();
    } else {
        prosesBatalkan();
    }
}
function hapusToken() {
    var varurl = getbasepath() + "/token/json/deletetokenmohon";
    var dataac = [];

    $(".ischecked:checked").each(function() {
        var id = $(this).attr('id');
        id = id.substr(10, id.length);
        var noMohon = $("#noMohon" + id).val();

        var data = {tahun: $("#tahun").val(), idSekolah: $("#idSekolah").val(),
            kodesumbdana: '2', noMohon: noMohon}
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
        cek();
    });
}
function hapusTokenSemua() {
    var varurl = getbasepath() + "/token/json/deletetokensemua";
    var dataac = [];

    var data = {tahun: $("#tahun").val(), idSekolah: $("#idSekolah").val(),
        kodesumbdana: '2'}
    dataac = data;

    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {
        cek();
    });
}
function showGrid() {
    var total = parseInt($('#sudah').val()) + parseInt($('#sudin').val());
    var trx = $("#trx").val();
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();
    var hingga;
    if (trx == 'JJ') {
        hingga = '3';
    } else {
        hingga = '3';
    }
    var urljson = getbasepath() + "/statusbop/json/listpersetujuan";
    myTable = $('#mytable').dataTable({
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
            {name: "kodeTrx", value: trx},
            {name: "dari", value: '1'},
            {name: "hingga", value: hingga},
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
            var isChecked = aData['status'] == '3' ? 'checked' : '';
            var cekStatus;
            if ($("#trx").val() == 'JJ' && $("#sudin").val() == 0) {
                isChecked = 'checked';
            } else if ($("#trx").val() == 'JJ' && $("#sudah").val() == 0) {
                isChecked = '';
            }
            if ($("#trx").val() == 'JJ') {
                cekStatus = "<input type='hidden' id='noMohon" + index + "' value=" + aData['noMohon'] + "><input type='checkbox' onclick='checkBox(this)' class='ischecked' name='isapproved" + index + "' id='isapproved" + index + "'  " + isChecked + " />"

            } else {
                cekStatus = "<input type='hidden' id='noMohon" + index + "' value=" + aData['noMohon'] + "><input type='checkbox' onclick='checkBox(this)' class='ischecked' name='isapproved" + index + "' id='isapproved" + index + "' disabled " + isChecked + " />"
            }
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

function getTotal() {
    var trx = $("#trx").val();
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();
    var hingga;
    if (trx == 'JJ') {
        hingga = '3';
    } else {
        hingga = '3';
    }
    $.getJSON(getbasepath() + "/statusbop/json/getTotalDari", {tahun: tahun,
        idSekolah: idSekolah, triwulan: triwulan, kodeTrx: trx, dari: '1', hingga: hingga},
    function(result) {

        var total = result.aData;

        $("#totkeluar").val(accounting.formatNumber(total, 2, '.', ","));

    });
}

function getBanyak() {
    var trx = $("#trx").val();
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();

    $.getJSON(getbasepath() + "/statusbop/json/getBanyak", {tahun: tahun,
        idSekolah: idSekolah, triwulan: triwulan, kodeTrx: trx},
    function(result) {

        var belum = result.aData[0];
        var sudah = result.aData[1];
        var approve = result.aData[2];
        var sudin = result.aData[3];
        $("#belum").val(belum);
        $("#sudah").val(sudah);
        $("#approve").val(approve);
        $("#sudin").val(sudin);

        if (sudah != '0') {
            console.log("ABC " + sudah);
            $("#setujui").prop('disabled', false);
        } else {
            $('#isapproved').prop('checked', true);
        }
        if (sudin != '0') {
            console.log("ABC " + sudin);
            $("#undo").prop('disabled', false);
        } else {
            $('#isapproved').prop('checked', true);
        }
        showGrid();

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
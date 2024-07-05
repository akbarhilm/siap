$(document).ready(function() {
    setCombo();
    console.log($('#kodeOtoritas').val());
    if ($('#kodeOtoritas').val() == '0' || $('#kodeOtoritas').val() == '8') {
        document.getElementById('proses').innerHTML = 'Setujui & Batalkan';
        $('#batal').hide();
    } else if ($('#kodeOtoritas').val() == '1') {
        document.getElementById('proses').innerHTML = 'Setujui';
        $('#batal').show();
    } else if ($('#kodeOtoritas').val() == '2') {
        document.getElementById('proses').innerHTML = 'Ajukan';
        $('#batal').show();
    }
    clearGrid();
});

function setIndex(index) {
    $('#index').val(index);
}
function clearGrid() {
    $('#tabelbatal').hide();
    $('#tabelbatalpajak').hide();
    $("#proses").prop('disabled', true);
    $("#batal").prop('disabled', true);
}

function setGrid() {
    var kodetransaksi = $('#kodetransaksi').val();

    if (kodetransaksi == "JJ" || kodetransaksi == "ST" || kodetransaksi == "RT") {
        $('#tabelbatal').show();
        gridSPJ();

    } else if (kodetransaksi.substr(0, 1) == "P") {
        $('#tabelbatalpajak').show();
        gridPajak();
    } else {
        clearGrid();
    }
}
function cek() {
    $("#proses").prop('disabled', true);
    $("#batal").prop('disabled', true);
    $('#tabelbatal').hide();
    $('#tabelbatalpajak').hide();
    if ($("#triwulan").val() == '-') {
        $("#jumlah").val('');
    } else {
        getJumlah();
        getTotal();
    }
}
function cekPajakPengeluaran(noBkuMohon, callback) {
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();
    $.getJSON(getbasepath() + "/btltf/json/getPajakPengeluaran", {idSekolah: idSekolah,
        triwulan: triwulan, noBkuMohon: noBkuMohon, kodesumberdana: '1'},
    function(result) {
        if (result != 0) {
            callback(true);
        } else {
            callback(false);
        }
    });
}
var isTrue;

//function checkAll(object) {
//    var kodetransaksi = $('#kodetransaksi').val();
//    var pajak = '';
//    if (kodetransaksi == 'JJ') {
//        pajak = '';
//    } else {
//        pajak = 'pajak';
//    }
//    var length = document.getElementById('mytablebody' + pajak).rows.length;
//    var checked = object.checked;
//    isTrue = false;
//    if (kodetransaksi == 'JJ') {
//        for (var i = 1; i <= length; i++) {
//            cekPajakPengeluaran($("#noBkuMohon" + i).val(), function(value) {
//                isTrue = value;
//            });
//            if (isTrue) {
//                break;
//            }
//        }
//    }
//    if (isTrue) {
//        $('#isapproved' + pajak).prop('checked', false);
//        bootbox.alert("SPJ ini sudah memiliki pajak pengeluaran.");
//    } else {
//        for (var i = 1; i <= length; i++) {
//            if (checked == true) {
//                $('#isapproved' + pajak + i).prop('checked', true);
//            } else {
//                $('#isapproved' + pajak + i).prop('checked', false);
//            }
//        }
//    }
//}
function checkBox(object) {
    var kodetransaksi = $('#kodetransaksi').val();
    var pajak = '';
    var potong;
    if (kodetransaksi == 'JJ' || kodetransaksi == 'ST' || kodetransaksi == "RT") {
        pajak = '';
        potong = 10;
    } else {
        pajak = 'pajak';
        potong = 15;
    }
    var idCheckbox = $(object).attr("id");
    var index = idCheckbox.substr(potong, idCheckbox.length);
    var noBkuMohon = $("#noBkuMohon" + pajak + index).val();
    var checked = object.checked;
    console.log(checked + " ");
    cekPajakPengeluaran(noBkuMohon, function(value) {
        if (value && kodetransaksi == 'JJ') {
            $('#isapproved' + pajak + index).prop('checked', false);
            bootbox.alert("SPJ ini sudah memiliki pajak pengeluaran.");
        }
    });


}
function setCombo() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idSekolah').val();

    $.getJSON(getbasepath() + "/bkubos/json/getTriwulanByRekap", {tahun: tahun, idsekolah: idsekolah},
    function(result) {

        var opt = "";

        if (result == 1) {
            opt = '<option value="1">Triwulan I</option>';
        } else if (result == 2) {
            opt = '<option value="2">Triwulan II</option>';
        } else if (result == 3) {
            opt = '<option value="3">Triwulan III</option>';
        } else if (result == 4) {
            opt = '<option value="4">Triwulan IV</option>';
        } else {
            opt = '<option value="-">-----------</option>';
        }

        $("#triwulan").html(opt);
        cek();
        console.log("TW " + $("#triwulan").val());
    });
}


function buttonProses() {
//    if ((document.getElementById("isapproved")).checked) {
//        console.log(1);
//        prosesAll();
//    } else {
//        console.log(2);
    if ($("#kodetransaksi").val() == 'JJ' || $("#kodetransaksi").val() == 'ST' || $("#kodetransaksi").val() == "RT") {
        proses();
    } else {
        prosesPajak();
    }
    //    }
}

function buttonBatal() {
//    if ((document.getElementById("isapproved")).checked) {
//        batalAll();
//    } else {
    if ($("#kodetransaksi").val() == 'JJ' || $("#kodetransaksi").val() == 'ST' || $("#kodetransaksi").val() == "RT") {
        batal();
    } else {
        batalPajak();
    }
    //    }
}

function prosesPajak() {
    var varurl = getbasepath() + "/btltf/json/prosesbatalpajak";
    var dataac = [];
    var count = 0;
    var countsudah = 0;
    $(".ischeckedpajak:checked").each(function() {
        var id = $(this).attr('id');
        id = id.substr(15, id.length);
        if ($('#idbapajak' + id).val() > 0) {
            var noBkuMohon = $("#noBkuMohonpajak" + id).val();
            var data = {idba: $('#idbapajak' + id).val(), idSekolah: $("#idSekolah").val(), triwulan: $("#triwulan").val(),
                kodeotor: $("#kodeOtoritas").val(), kodesumberdana: '1', noBkuMohon: noBkuMohon, batal: '1'};
            dataac.push(data);
            count++;
            if (($("#kodeOtoritas").val() == '2' && $('#kodereturpajak' + id).val() == '1') || ($("#kodeOtoritas").val() == '1' && $('#kodereturpajak' + id).val() == '2')) {
                countsudah++;
            }
        }
    });
    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {
        if ($("#kodeOtoritas").val() == '2') {
            if (count == 0) {
                bootbox.alert("Silahkan membuat BA terlebih dahulu");
            } else if (countsudah > 0) {
                bootbox.alert("Pajak sudah diajukan sebelumnya");
            } else {
                bootbox.alert("Pembatalan pajak berhasil diajukan");
            }
        } else if ($("#kodeOtoritas").val() == '1') {
            if (countsudah > 0) {
                bootbox.alert("Pajak sudah disetujui sebelumnya");
            } else {
                bootbox.alert("Pembatalan pajak berhasil disetujui");
            }
        } else if ($("#kodeOtoritas").val() == '0' || $("#kodeOtoritas").val() == '8') {
            bootbox.alert("Pajak berhasil dibatalkan");
        }
        cek();
    });
}
//function prosesAll() {
//    var varurl = getbasepath() + "/btltf/json/prosesbatalsemua";
//    var dataac = [];
//    var data = {idSekolah: $("#idSekolah").val(), triwulan: $("#triwulan").val(),
//        kodeotor: $("#kodeOtoritas").val(), kodesumberdana: '1', batal: '1'};
//    dataac = data;
//    $.ajax({
//        type: "POST",
//        url: varurl,
//        contentType: "text/plain; charset=utf-8",
//        headers: {
//            'Accept': 'application/json',
//            'Content-Type': 'application/json'},
//        data: JSON.stringify(dataac)}).always(function() {
//        bootbox.alert("Pengajuan berhasil diajukan");
//        cek();
//    });
//}


function proses() {
    var varurl = getbasepath() + "/btltf/json/prosesbatal";
    var dataac = [];
    var count = 0;
    var countsudah = 0;
    $(".ischecked:checked").each(function() {
        var id = $(this).attr('id');
        id = id.substr(10, id.length);
        if ($('#idba' + id).val() > 0) {
            var noBkuMohon = $("#noBkuMohon" + id).val();

            var data = {idba: $('#idba' + id).val(), idSekolah: $("#idSekolah").val(), triwulan: $("#triwulan").val(),
                kodeotor: $("#kodeOtoritas").val(), kodesumberdana: '1', noBkuMohon: noBkuMohon, batal: '1', kodetransaksi: $("#kodetransaksi").val()};
            dataac.push(data);
            count++;
            if (($("#kodeOtoritas").val() == '2' && $('#koderetur' + id).val() == '1') || ($("#kodeOtoritas").val() == '1' && $('#koderetur' + id).val() == '2')) {
                countsudah++;
            }
        }
    });

    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {
        if ($("#kodeOtoritas").val() == '2') {
            if (count == 0) {
                bootbox.alert("Silahkan membuat BA terlebih dahulu");
            } else {
                bootbox.alert("Pembatalan " + $("#kodetransaksi option:selected").text() + " berhasil diajukan");
            }
        } else if ($("#kodeOtoritas").val() == '1') {
            if (countsudah > 0) {
                bootbox.alert($("#kodetransaksi option:selected").text() + " sudah disetujui sebelumnya");
            } else {
                bootbox.alert("Pembatalan " + $("#kodetransaksi option:selected").text() + " berhasil disetujui");
            }
        } else if ($("#kodeOtoritas").val() == '0' || $("#kodeOtoritas").val() == '8') {
            bootbox.alert($("#kodetransaksi option:selected").text() + " berhasil dibatalkan");
        }
        cek();
    });
}
function batalPajak() {
    var varurl = getbasepath() + "/btltf/json/prosesbatalpajak";
    var dataac = [];
    var count = 0;
    var countsudah = 0;
    $(".ischeckedpajak:checked").each(function() {
        var id = $(this).attr('id');
        id = id.substr(15, id.length);
        if ($('#idbapajak' + id).val() > 0) {
            var noBkuMohon = $("#noBkuMohonpajak" + id).val();
            var data = {idSekolah: $("#idSekolah").val(), triwulan: $("#triwulan").val(),
                kodeotor: $("#kodeOtoritas").val(), kodesumberdana: '1', noBkuMohon: noBkuMohon, batal: '0'};
            dataac.push(data);
            count++;
            if (($("#kodeOtoritas").val() == '2' && $('#kodereturpajak' + id).val() == '0') || ($("#kodeOtoritas").val() == '1' && $('#kodereturpajak' + id).val() == '1')) {
                countsudah++;
            }
        }
    });

    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {
        if ($("#kodeOtoritas").val() == '2') {
            if (count == 0) {
                bootbox.alert("Silahkan membuat BA terlebih dahulu");
            } else {
                bootbox.alert("Pembatalan pajak berhasil diajukan");
            }
        } else if ($("#kodeOtoritas").val() == '1') {
            bootbox.alert("Pembatalan pajak berhasil disetujui");
        } else if ($("#kodeOtoritas").val() == '0' || $("#kodeOtoritas").val() == '8') {
            if (countsudah > 0) {
                bootbox.alert("Pajak belum disetujui");
            } else {
                bootbox.alert("Persetujuan pajak berhasil dibatalkan");
            }
        }
        cek();
    });
}
//function batalAll() {
//    var varurl = getbasepath() + "/btltf/json/prosesbatalsemua";
//    var dataac = [];
//    var data = {idSekolah: $("#idSekolah").val(), triwulan: $("#triwulan").val(),
//        kodeotor: $("#kodeOtoritas").val(), kodesumberdana: '1', batal: '0'};
//    dataac = data;
//    $.ajax({
//        type: "POST",
//        url: varurl,
//        contentType: "text/plain; charset=utf-8",
//        headers: {
//            'Accept': 'application/json',
//            'Content-Type': 'application/json'},
//        data: JSON.stringify(dataac)}).always(function() {
//        bootbox.alert("Pengajuan berhasil dibatalkan");
//        cek();
//    });
//}


function batal() {
    var varurl = getbasepath() + "/btltf/json/prosesbatal";
    var dataac = [];
    var count = 0;
    var countsudah = 0;
    $(".ischecked:checked").each(function() {
        var id = $(this).attr('id');
        id = id.substr(10, id.length);
        if ($('#idba' + id).val() > 0) {
            var noBkuMohon = $("#noBkuMohon" + id).val();

            var data = {idSekolah: $("#idSekolah").val(), triwulan: $("#triwulan").val(),
                kodeotor: $("#kodeOtoritas").val(), kodesumberdana: '1', noBkuMohon: noBkuMohon, batal: '0', kodetransaksi: $("#kodetransaksi").val()};
            dataac.push(data);
            count++
            if (($("#kodeOtoritas").val() == '2' && $('#koderetur' + id).val() == '0') || ($("#kodeOtoritas").val() == '1' && $('#koderetur' + id).val() == '1')) {
                countsudah++;
            }
        }
    });

    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {
        if ($("#kodeOtoritas").val() == '2') {
            bootbox.alert("Pengajuan " + $("#kodetransaksi option:selected").text() + " berhasil dibatalkan");
        } else if ($("#kodeOtoritas").val() == '1') {
            if (countsudah > 0) {
                bootbox.alert($("#kodetransaksi option:selected").text() + " belum disetujui");
            } else {
                bootbox.alert("Pengajuan " + $("#kodetransaksi option:selected").text() + " berhasil dibatalkan");
            }
        }
        cek();
    });
}
function gridSPJ() {
    var total = parseInt($('#jumlah').val());
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();
    var kodeotor = $('#kodeOtoritas').val();
    var kodetransaksi = $('#kodetransaksi').val();
    var urljson = getbasepath() + "/btltf/json/listpembatalan";
    myTable = $('#btltable').dataTable({
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
                    {name: "idSekolah", value: idSekolah},
            {name: "triwulan", value: triwulan},
            {name: "kodeotor", value: kodeotor},
            {name: "kodesumberdana", value: '1'},
            {name: "kodetransaksi", value: kodetransaksi},
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
            var ba = aData['idBaBatal'];
            var button;
            var edithref = getbasepath() + "/babtl/listeditba?idsekolah=" + $('#idSekolah').val() + "&nomohon=" + aData['noBkuMohon'] + "&tahun=" + tahun + "&kodesumbdana=" + $('#kodesumbdana').val() + "&target='_top'";
            var addhref = getbasepath() + "/babtl/listtambahba?target='_top'";
            var arsiphref = getbasepath() + "/babtl/listarsipba" + "?idsekolah=" + $('#idSekolah').val() + "&nomohon=" + aData['noBkuMohon'] + "&tahun=" + tahun + "&kodesumbdana=" + $('#kodesumbdana').val() + "&target='_top'";
            var hapushref = getbasepath() + "/babtl/listhapusba" + "?idsekolah=" + $('#idSekolah').val() + "&nomohon=" + aData['noBkuMohon'] + "&tahun=" + tahun + "&kodesumbdana=" + $('#kodesumbdana').val() + "&target='_top'";
            var edit = "<a onclick='setIndex(" + index + ")' href=" + edithref + " class='fancybox fancybox.iframe' ><i class='icon-edit'></i></a>-<a onclick='setIndex(" + index + ")' href=" + hapushref + " class='fancybox fancybox.iframe' ><i class='icon-remove'></i></a>";
            var edit2 = "<a onclick='setIndex(" + index + ")' href=" + edithref + " class='fancybox fancybox.iframe' ><i class='icon-edit'></i></a>";
            var arsip = "<a onclick='setIndex(" + index + ")' href=" + arsiphref + " class='fancybox fancybox.iframe' ><i class='icon-file-text'></i></a>";
            var add = "<a onclick='setIndex(" + index + ")' href=" + addhref + " class='fancybox fancybox.iframe' ><i class='icon-plus'></i></a>";

            if (ba != 0) {
                if (kodeotor != '2') {
                    console.log("masuk arsip");
                    button = arsip;
                } else {
                    if (aData['kodeRetur'] == '1') {
                        console.log("masuk edit2");
                        button = edit2;
                    } else {
                        console.log("masuk edit");
                        button = edit;
                    }
                }
            } else {
                button = add;
            }

//            var cekStatus = "<input type='checkbox' name='isajui" + index + "' id='isajui" + index + "' disabled  " + isChecked + " />"
            var cekStatus = "<input type='hidden' id='koderetur" + index + "' value='" + aData['kodeRetur'] + "'><input type='hidden' id='nilai" + index + "' value='" + aData['nilaiSpjNetto'] + "'><input type='hidden' id='idba" + index + "' value='" + ba + "'><input type='hidden' id='noBkuMohon" + index + "' value=" + aData['noBkuMohon'] + "><input type='radio' onclick='checkBox(this)' class='ischecked' name='isapproved' id='isapproved" + index + "'/>";
            var nilaikeluar = "<input type='hidden' id='nilaikeluar" + index + "' value='" + aData['nilaiKeluar'] + "'>";
            $('td:eq(0)', nRow).html(index);
            $('td:eq(1)', nRow).html(cekStatus + nilaikeluar);
            $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ","));
            $('td:eq(6)', nRow).html(accounting.formatNumber(aData['nilaiPajak'], 2, '.', ","));
            $('td:eq(7)', nRow).html(accounting.formatNumber(aData['nilaiSpjNetto'], 2, '.', ","));
            $('td:eq(8)', nRow).html(button);

            return nRow;
        },
        "aoColumns": [
            {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center"},
            {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center"},
            {"mDataProp": "noBkuMohon", "bSortable": true, sClass: "center"},
            {"mDataProp": "kodeKeg", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaKeg", "bSortable": true, sClass: "left"},
            {"mDataProp": "nilaiKeluar", "bSortable": true, sClass: "right"},
            {"mDataProp": "nilaiPajak", "bSortable": true, sClass: "right"},
            {"mDataProp": "nilaiSpjNetto", "bSortable": true, sClass: "right"},
            {"mDataProp": "noBkuMohon", "bSortable": true, sClass: "center"},
        ]
    });
}
function gridPajak() {
    var total = parseInt($('#jumlah').val());
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();
    var kodeotor = $('#kodeOtoritas').val();
    var kodetransaksi = $('#kodetransaksi').val();
    var urljson = getbasepath() + "/btltf/json/listpembatalan";
    myTable = $('#btltablepajak').dataTable({
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
                    {name: "idSekolah", value: idSekolah},
            {name: "triwulan", value: triwulan},
            {name: "kodeotor", value: kodeotor},
            {name: "kodesumberdana", value: '1'},
            {name: "kodetransaksi", value: kodetransaksi},
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
            var ba = aData['idBaBatal'];
            var button;
            var edithref = getbasepath() + "/babtl/listeditba?idsekolah=" + $('#idSekolah').val() + "&nomohon=" + aData['noBkuMohon'] + "&tahun=" + tahun + "&kodesumbdana=" + $('#kodesumbdana').val() + "&target='_top'";
            var addhref = getbasepath() + "/babtl/listtambahba?target='_top'";
            var arsiphref = getbasepath() + "/babtl/listarsipba" + "?idsekolah=" + $('#idSekolah').val() + "&nomohon=" + aData['noBkuMohon'] + "&tahun=" + tahun + "&kodesumbdana=" + $('#kodesumbdana').val() + "&target='_top'";
            var hapushref = getbasepath() + "/babtl/listhapusba" + "?idsekolah=" + $('#idSekolah').val() + "&nomohon=" + aData['noBkuMohon'] + "&tahun=" + tahun + "&kodesumbdana=" + $('#kodesumbdana').val() + "&target='_top'";
            var edit = "<a onclick='setIndex(" + index + ")' href=" + edithref + " class='fancybox fancybox.iframe' ><i class='icon-edit'></i></a>-<a onclick='setIndex(" + index + ")' href=" + hapushref + " class='fancybox fancybox.iframe' ><i class='icon-remove'></i></a>";
            var edit2 = "<a onclick='setIndex(" + index + ")' href=" + edithref + " class='fancybox fancybox.iframe' ><i class='icon-edit'></i></a>";
            var arsip = "<a onclick='setIndex(" + index + ")' href=" + arsiphref + " class='fancybox fancybox.iframe' ><i class='icon-file-text'></i></a>";
            var add = "<a onclick='setIndex(" + index + ")' href=" + addhref + " class='fancybox fancybox.iframe' ><i class='icon-plus'></i></a>";

            if (ba != 0) {
                if (kodeotor != '2') {
                    button = arsip;

                } else {
                    if (aData['kodeRetur'] == '1') {
                        button = edit2;
                    } else {
                        button = edit;
                    }
                }
            } else {
                button = add;
            }

//            var cekStatus = "<input type='checkbox' name='isajui" + index + "' id='isajui" + index + "' disabled  " + isChecked + " />"
            var cekStatus = "<input type='hidden' id='kodereturpajak" + index + "' value='" + aData['kodeRetur'] + "'><input type='hidden' id='nilaipajak" + index + "' value='" + aData['nilaiKeluar'] + "'><input type='hidden' id='idbapajak" + index + "' value='" + ba + "'><input type='hidden' id='noBkuMohon" + "pajak" + index + "' value=" + aData['noBkuMohon'] + "><input type='radio' onclick='checkBox(this)' class='ischecked" + "pajak" + "' name='isapproved' id='isapproved" + 'pajak' + index + "'  />";
            
            var uraian = "<textarea id='uraianbukti" + "pajak" + index + "'readonly style='border:none;margin:0;width:180px;'>" + aData['uraianBukti'] + "</textarea>";

            $('td:eq(0)', nRow).html(index);
            $('td:eq(1)', nRow).html(cekStatus);
            $('td:eq(8)', nRow).html(uraian);
            $('td:eq(9)', nRow).html(accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ","));
            $('td:eq(10)', nRow).html(button);

            return nRow;
        },
        "aoColumns": [
            {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center"},
            {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center"},
            {"mDataProp": "noBkuMohon", "bSortable": true, sClass: "center"},
            {"mDataProp": "noBkuRef", "bSortable": true, sClass: "center"},
            {"mDataProp": "noBkuSpj", "bSortable": true, sClass: "center"},
            {"mDataProp": "noBukti", "bSortable": true, sClass: "center"},
            {"mDataProp": "kodeKeg", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaKeg", "bSortable": true, sClass: "left"},
            {"mDataProp": "uraianBukti", "bSortable": true, sClass: "left"},
            {"mDataProp": "nilaiKeluar", "bSortable": true, sClass: "right"},
            {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center"},
        ]
    });
}

function getTotal() {
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();
    var kodeotor = $('#kodeOtoritas').val();
    var kodetransaksi = $('#kodetransaksi').val();


    $.getJSON(getbasepath() + "/btltf/json/getTotal", {idSekolah: idSekolah, triwulan: triwulan,
        kodeotor: kodeotor, kodesumberdana: '1', kodetransaksi: kodetransaksi},
    function(result) {
        if ($('#kodetransaksi').val() == 'JJ' || $('#kodetransaksi').val() == 'RT') {
            var totalk = result.aData['nilaiKeluar'];
            var totalp = result.aData['nilaiPajak'];
            var totaln = result.aData['nilaiSpjNetto'];
            $("#totkeluar").val(accounting.formatNumber(totalk, 2, '.', ","));
            $("#totpajak").val(accounting.formatNumber(totalp, 2, '.', ","));
            $("#totnetto").val(accounting.formatNumber(totaln, 2, '.', ","));
        } else {
            var totalk = result.aData['nilaiKeluar'];
            $("#totkeluarpajak").val(accounting.formatNumber(totalk, 2, '.', ","));
        }
    });
}
function getJumlah() {
    var tahun = $('#tahun').val();
    var idSekolah = $('#idSekolah').val();
    var triwulan = $('#triwulan').val();
    var kodeotor = $('#kodeOtoritas').val();
    var kodetransaksi = $('#kodetransaksi').val();

    $.getJSON(getbasepath() + "/btltf/json/getJumlah", {idSekolah: idSekolah,
        triwulan: triwulan, kodeotor: kodeotor, kodesumberdana: '1', kodetransaksi: kodetransaksi},
    function(result) {

        var jumlah = result;
        $("#jumlah").val(jumlah);
        if (jumlah != 0) {
            $("#proses").prop('disabled', false);
            $("#batal").prop('disabled', false);
        }
        setGrid();
        console.log("jumlah " + jumlah);
    });
}
function cariKodeKegiatan() {
    if ($("#kodetransaksi").val() == 'JJ' || $("#kodetransaksi").val() == 'ST' || $('#kodetransaksi').val() == 'RT') {
        $("#kodeKegiatanFilter").keyup(function() {
            var panjang = $(this).val().length;
            if (panjang > 2 || panjang == 0) {
                gridSPJ();
                $("#kodeKegiatanFilter").focus();
            }
        });
    } else {
        $("#kodeKegiatanPFilter").keyup(function() {
            var panjang = $(this).val().length;
            if (panjang > 2 || panjang == 0) {
                gridPajak()();
                $("#kodeKegiatanPFilter").focus();
            }
        });
    }
}
function cariNamaKegiatan() {
    if ($("#kodetransaksi").val() == 'JJ' || $("#kodetransaksi").val() == 'ST' || $('#kodetransaksi').val() == 'RT') {
        $("#namaKegiatanFilter").keyup(function() {
            var panjang = $(this).val().length;
            if (panjang > 2 || panjang == 0) {
                gridSPJ();
                $("#namaKegiatanFilter").focus();
            }
        });
    } else {
        $("#namaKegiatanPFilter").keyup(function() {
            var panjang = $(this).val().length;
            if (panjang > 2 || panjang == 0) {
                gridPajak()();
                $("#namaKegiatanPFilter").focus();
            }
        });
    }

}
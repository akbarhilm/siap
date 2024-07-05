$(document).ready(function() {
    setTriwulan();
    checkButton();
});

var idrow = 0;
var idrowbaru = 0;
var banyaktampil = 0;
var noNR = 0;
var banyakdata;
var kosong = 0;
var banyakdataawal;
var banyakrinciawal = 0;

var nomorgrid = 0;

var jumhapus = 0;
var idbuton;

function prosesSetujui() {
    var kodeOtoritas = $("#kodeOtoritas").val();
    var dari, ke;
    if (kodeOtoritas == '1') {
        ke = 1;
    } else {
        ke = 2;
    }
    var varurl = getbasepath() + "/bapkasbop/json/prosesupdate";
    var dataac = [];
    var data = {id: $("#idSekolahBAPKas").val(), ke: ke}

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
        $('#statusBkuBa').val(ke);
        checkButton();
    });

}

function prosesBatal() {
    var kodeOtoritas = $("#kodeOtoritas").val();
    var dari, ke;
    if (kodeOtoritas == '1') {
        ke = 0;
    } else {
        ke = 1;
    }
    var varurl = getbasepath() + "/bapkasbop/json/prosesupdate";
    var dataac = [];
    var data = {id: $("#idSekolahBAPKas").val(), ke: ke}

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
        $('#statusBkuBa').val(ke);
        checkButton();
    });
}

function checkButton() {
    var kodeOtoritas = $('#kodeOtoritas').val();
    var statusBkuBa = $('#statusBkuBa').val();

    if (statusBkuBa == '0' && kodeOtoritas == '1') {
        $("#setujui").prop('disabled', false);
        $("#batal").prop('disabled', true);
    } else if (statusBkuBa == '1' && kodeOtoritas == '1') {
        $("#setujui").prop('disabled', true);
        $("#batal").prop('disabled', false);
    } else if (statusBkuBa == '1' && kodeOtoritas == '0') {
        $("#setujui").prop('disabled', false);
        $("#batal").prop('disabled', true);
    } else if (statusBkuBa == '2' && kodeOtoritas == '0') {
        $("#setujui").prop('disabled', true);
        $("#batal").prop('disabled', false);
    } else {
        $("#setujui").prop('disabled', true);
        $("#batal").prop('disabled', true);
    }
}
function pindahhalamanadepan() {
    window.location.replace(getbasepath() + "/bapkasbop/indexbapkas");
}

function setTriwulan() {
    var opt = '<option value="-" selected="true">-- Pilih --</option>'
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/bapkasbop/json/getTriwulan", {tahun: tahun,
        idsekolah: idsekolah},
    function(result) {
        console.log(result.aData);
        for (var i = 0; i <= result.aData.length; i++) {
            if (result.aData[i] == '1') {
                opt = opt + '<option value="1">Triwulan I</option>'
            } else if (result.aData[i] == '2') {
                opt = opt + '<option value="2">Triwulan II</option>'
            } else if (result.aData[i] == '3') {
                opt = opt + '<option value="3">Triwulan III</option>'
            } else if (result.aData[i] == '4') {
                opt = opt + '<option value="4">Triwulan IV</option>'
            }
        }

        $("#triwulan").html(opt);
    });
}

function setData() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();
    if (triwulan != '-') {
        $.getJSON(getbasepath() + "/bapkasbop/json/getbapkas", {tahun: tahun,
            idsekolah: idsekolah, triwulan: triwulan},
        function(result) {
            if (result != null) {
                $('#idSekolahBAPKas').val(result.aData['idSekolahBAPKas'])
                var tanggal, d, m, y;
                tanggal = result.aData['tglBkuBaformat'];
                d = tanggal.substr(7);
                m = tanggal.substr(5, 7);
                y = tanggal.substr(0, 5);
                var mm, dd, yyyy;
                var mm = tanggal.substr(4, 2);
                var dd = tanggal.substr(6, 2);
                var yyyy = tanggal.substr(0, 4);

                //$('#tglBkuBa').val(d + "/" + m + "/" + y);
                $('#tglBkuBa').val(dd + "/" + mm + "/" + yyyy);
                $('#namaHari').val(result.aData['namaHari']);
                $('#nrkPa').val(result.aData['nrkPa']);
                $('#nipPa').val(result.aData['nipPa']);
                $('#namaPa').val(result.aData['namaPa']);
                $('#jabatanPa').val(result.aData['jabatanPa']);
                $('#nrkBend').val(result.aData['nrkBend']);
                $('#nipBend').val(result.aData['nipBend']);
                $('#namaBend').val(result.aData['namaBend']);
                $('#jabatanBend').val(result.aData['jabatanBend']);
                $('#noSkGub').val(result.aData['noSkGub']);
                $('#tglSkGub').val(result.aData['tglSkGub']);
                $('#nilaiUangKertas1').val(accounting.formatNumber(result.aData['nilaiUangKertas'], 2, '.', ","));
                $('#nilaiUangLogam1').val(accounting.formatNumber(result.aData['nilaiUangLogam'], 2, '.', ","));
                $('#nilaiUangSp2d1').val(accounting.formatNumber(result.aData['nilaiUangSp2d'], 2, '.', ","));
                $('#nilaiUangSaldoBank1').val(accounting.formatNumber(result.aData['nilaiUangSaldoBank'], 2, '.', ","));
                $('#nilaiUangSuratBerharga1').val(accounting.formatNumber(result.aData['nilaiUangSuratBerharga'], 2, '.', ","));
                $('#nilaiUangTotalBkuBa1').val(accounting.formatNumber(result.aData['nilaiUangTotalBkuBa'], 2, '.', ","));
                $('#nilaiUangSaldoBkuBa').val(accounting.formatNumber(result.aData['nilaiUangSaldoBkuBa'], 2, '.', ","));
                $('#nilaiUangSelisihBkuBa').val(accounting.formatNumber(result.aData['nilaiUangSelisihBkuBa'], 2, '.', ","));
                $('#ketBkuBa').val(result.aData['ketBkuBa']);
                $('#statusBkuBa').val(result.aData['statusBkuBa']);
                gridbapkasrinci();
                hitungnilaiBapKas();
                checkButton();
            } else {
                bootbox.alert("Data tidak ada");
            }
        });
    } else {
        $('#idSekolahBAPKas').val("")
        $('#tglBkuBa').val("");
        $('#namaHari').val("");
        $('#nrkPa').val("");
        $('#nipPa').val("");
        $('#namaPa').val("");
        $('#jabatanPa').val("");
        $('#nrkBend').val("");
        $('#nipBend').val("");
        $('#namaBend').val("");
        $('#jabatanBend').val("");
        $('#noSkGub').val("");
        $('#tglSkGub').val("");
        $('#nilaiUangKertas1').val("");
        $('#nilaiUangLogam1').val("");
        $('#nilaiUangSp2d1').val("");
        $('#nilaiUangSaldoBank1').val("");
        $('#nilaiUangSuratBerharga1').val("");
        $('#nilaiUangTotalBkuBa1').val("");
        $('#nilaiUangSaldoBkuBa').val("");
        $('#nilaiUangSelisihBkuBa').val("");
        $('#ketBkuBa').val("");
        $('#statusBkuBa').val("");
    }
}
function getNilaiTotalBAPKas() {
    var kertas, logam, sp2d, sb, saldoBank, total, kr;
    total = 0;
    if ($('#nilaiUangKertas1').val() == "") {
        kertas = 0;
    } else {
        kertas = parseFloat(accounting.unformat($('#nilaiUangKertas1').val(), ","));//(accounting.unformat($('#nilaiUangKertas1').val(), ","));
    }

    if ($('#nilaiUangLogam1').val() == "") {
        logam = 0;
    } else {
        logam = parseFloat(accounting.unformat($('#nilaiUangLogam1').val(), ","));
    }

    if ($('#nilaiUangSp2d1').val() == "") {
        sp2d = 0;
    } else {
        sp2d = parseFloat(accounting.unformat($('#nilaiUangSp2d1').val(), ","));
    }
    if ($('#nilaiUangSaldoBank1').val() == "") {
        saldoBank = 0;
    } else {
        saldoBank = parseFloat(accounting.unformat($('#nilaiUangSaldoBank1').val(), ","));
    }
    if ($('#nilaiUangSuratBerharga1').val() == "") {
        sb = 0;
    } else {
        sb = parseFloat(accounting.unformat($('#nilaiUangSuratBerharga1').val(), ","));
    }

    total = kertas + logam + sp2d + saldoBank + sb;

    $("#nilaiUangTotalBkuBa1").val(accounting.formatNumber(total, 2, '.', ","));

    $("#nilaiUangTotalBkuBa1").val(accounting.formatNumber(total, 2, '.', ","));
    /*$("#nilaiUangKertas1").val(accounting.formatNumber(kr, 2, '.', ","));
     $("##nilaiUangLogam1").val(accounting.formatNumber(logam, 2, '.', ","));
     $("#nilaiUangSp2d1").val(accounting.formatNumber(sp2d, 2, '.', ","));
     $("#nilaiUangSaldoBank1").val(accounting.formatNumber(saldoBank, 2, '.', ","));
     $("#nilaiUangSuratBerharga1").val(accounting.formatNumber(sb, 2, '.', ","));*/

}

function gridbapkasrinci() { // untuk nampilin grid awal
    $("#btnSimpan").hide();
    var urljson = getbasepath() + "/bapkasbop/json/getlistbapkasrinci";
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
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idSekolahBAPKas", value: $('#idSekolahBAPKas').val()}
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

                try {
                    var BapKastotal = 0;

                    for (var i = iStart; i < iEnd; i++) {
                        BapKastotal += parseFloat(aaData[i]['nilaiBapKas']);

                    }
                    $("#totalBapKas").val(accounting.formatNumber(BapKastotal, 2, '.', ","));
                    $("#totalbk").val(accounting.formatNumber(BapKastotal, 2, '.', ","));

                } catch (e) {
                    console.log(e);
                }
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) { //format untuk tampilan body tabel
                var numStart = this.fnPagingInfo().iStart;
                index = numStart + iDisplayIndexFull + 1;


                var idSkpdBAPKas = "<input type='hidden' id='idSkpdBAPKas" + index + "'   name='idSkpdBAPKas" + index + "'  value='" + aData['idSekolahBAPKas'] + "'  />";
                var idSkpdBAPKasRinci = "<input type='hidden' id='idSkpdBAPKasRinci" + index + "' name='idSkpdBAPKasRinci" + index + "' value='" + aData['idSekolahBAPKasRinci'] + "' />";
                var namaBapKas = "<input type='text' name='namaBapKas" + index + "' id='namaBapKas" + index + "' value = '" + aData['namaBapKas'] + "'  readonly='true' style='width: 900px;' />";

                var inputBapKas = "<input type='text'  readonly='true' id='nilaiBapKas" + index + "'    name='nilaiBapKas" + index + "'   value='" + aData['nilaiBapKas'] + "'  class='inputmoney '   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaiBapKas()'  readonly='true'   />";
                var nilaiBapKashidden = "<input type='hidden' readonly='true' name='nilaiBapKasorg" + index + "'     id='nilaiBapKasorg" + index + "' value='" + aData['nilaiBapKas'] + "' />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + " ' name='cekpilih" + index + "' onchange='enablerow(this," + index + ")'  />";

                idrow = index;
                if (index === null || index === "" || index === 0) {
                    nomorgrid = 0;
                    idrowawal = 0;
                }
                else {
                    nomorgrid = parseInt(aData['idSekolahBAPKasRinci']);
                    idrowawal = idrow;
                }
                $('td:eq(0)', nRow).html(index + idSkpdBAPKasRinci + idSkpdBAPKas);
                $('td:eq(1)', nRow).html(namaBapKas);
                $('td:eq(2)', nRow).html(inputBapKas + nilaiBapKashidden);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSekolahBAPKasRinci", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaBapKas", "bSortable": true, sClass: "left"},
                {"mDataProp": "nilaiBapKas", "bSortable": true, sClass: "center"},
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    getbanyakrincibapkas();
}

function getbanyakrincibapkas() {
    $.getJSON(getbasepath() + "/bapkasbop/json/getbanyakrincibapkas", {idSekolahBAPKas: $("#idSekolahBAPKas").val()},
    function(result) {
        $('#banyakrinci').val(result);
        banyakrinciawal = result;
    });
}

function tambahRow() {
    idrow += 1;
    nomorgrid += 1;
    idrowbaru += 1;

    var rowCount = document.getElementById('bapkasrincitablebody').rows.length;
    var table = document.getElementById('bapkasrincitablefoot2');

    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    cell1.innerHTML = "<td class= 'center'  >" + idrow + " </td>";
    cell2.innerHTML = "<td> <input type='text' name='namaBapKas" + idrow + "' id='namaBapKas" + idrow + "' /> </td>";
    cell3.innerHTML = "<td> <input type='text' name='nilaiBapKas" + idrow + "' id='nilaiBapKas" + idrow + "'  class='inputmoney'  value = '0' onkeyup='getTotal(" + idrow + "),cektotalbk(" + idrow + ")'  /> </td>";
    cell4.innerHTML = "<td class= 'center' ><a class='center icon-remove' onclick='hapusBapKasRinci(" + idrow + ")' ></a> </td>";

    formatnumberonkeyup();
    $('#banyakrinci').val(idrow);
    $('#idrowbaru').val(idrowbaru);

}

function getTotal(id) { //untuk menghitung nilai total ketika menambahkan data baru
    var i, totalBapKas, nilaiBapKas, totalBap;
    totalBapKas = 0;
    nilaiBapKas = 0;
    totalBap = 0;

    for (i = 1; i <= id; i++) {
        if ($('#nilaiBapKas' + i).val() == "") {
            totalBapKas = 0;
        } else {
            totalBapKas = parseFloat(accounting.unformat($('#nilaiBapKas' + i).val(), ","));
        }
        totalBap += totalBapKas;
    }

    $('#totalbk').val(accounting.formatNumber(totalBap, 2, '.', ","));
}

function enablerow(obj, idSkpdBAPKasRinci) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
        $("#penanda" + idSkpdBAPKasRinci).val("1");
    } else {
        $("#penanda" + idSkpdBAPKasRinci).val("0");
    }
    setdisabled(param, idSkpdBAPKasRinci);
}

function setdisabled(param, idSkpdBAPKasRinci) {
    $("#nilaiBapKas" + idSkpdBAPKasRinci).attr("readonly", param);
    $("#namaBapKas" + idSkpdBAPKasRinci).attr("readonly", param);

    hitungnilaiBapKas();
}

function hitungnilaiBapKas() {
    //untuk menghitung nilai total ketika menambahkan data baru
    var i, totalBapKas, nilaiBapKas, totalBap;
    totalBapKas = 0;
    nilaiBapKas = 0;
    totalBap = 0;

    for (i = 1; i <= idrow; i++) {
        if ($('#nilaiBapKas' + i).val() == "") {
            totalBapKas = 0;
        } else {
            totalBapKas = parseFloat(accounting.unformat($('#nilaiBapKas' + i).val(), ","));
        }
        totalBap += totalBapKas;
    }
    $('#totalbk').val(accounting.formatNumber(totalBap, 2, '.', ","));
}

function getNilaiTotalSelisihBAPKas() {
    var kertas1, logam1, sp2d1, sb1, saldoBank1, total1, kr1, tbku1, totalselisih;
    totalselisih = 0;
    if ($('#nilaiUangKertas1').val() == "") {
        kertas1 = 0;
    } else {
        kertas1 = parseFloat(accounting.unformat($('#nilaiUangKertas1').val(), ","));//(accounting.unformat($('#nilaiUangKertas1').val(), ","));
    }

    if ($('#nilaiUangLogam1').val() == "") {
        logam1 = 0;
    } else {
        logam1 = parseFloat(accounting.unformat($('#nilaiUangLogam1').val(), ","));
    }

    if ($('#nilaiUangSp2d1').val() == "") {
        sp2d1 = 0;
    } else {
        sp2d1 = parseFloat(accounting.unformat($('#nilaiUangSp2d1').val(), ","));
    }
    if ($('#nilaiUangSaldoBank1').val() == "") {
        saldoBank1 = 0;
    } else {
        saldoBank1 = parseFloat(accounting.unformat($('#nilaiUangSaldoBank1').val(), ","));
    }
    if ($('#nilaiUangSuratBerharga1').val() == "") {
        sb1 = 0;
    } else {
        sb1 = parseFloat(accounting.unformat($('#nilaiUangSuratBerharga1').val(), ","));
    }
    if ($('#nilaiUangSaldoBkuBa').val() == "") {
        tbku1 = 0;
    } else {
        tbku1 = parseFloat(accounting.unformat($('#nilaiUangSaldoBkuBa').val(), ","));
    }

    totalselisih = (kertas1 + logam1 + sp2d1 + saldoBank1 + sb1) - tbku1;

    $('#nilaiUangSelisihBkuBa').val(accounting.formatNumber(totalselisih, 2, '.', ","));
}

function cektotalbk(id) {
    var totalbk1 = accounting.unformat($("#totalbk").val(), ",");
    var nilaiUangSaldoBkuBa1 = accounting.unformat($("#nilaiUangSaldoBkuBa").val(), ",");
    var totalBap = 0;
    var n = id;

    if (totalbk1 > nilaiUangSaldoBkuBa1) { //jika total rinci > total bapkas
        bootbox.alert("Nilai total rinci harus <= Nilai Saldo BAP KAS");
        //$('#nilaiBapKas' + n).val(accounting.formatNumber(totalBap, 2, '.', ","));
        $('#nilaiBapKas' + n).autoNumeric('set', 0);
        getTotal(id);
    }

}

function pasangvalidatebesarperfield(idSkpdBAPKasRinci) {
    var nilaiUangSaldoBkuBa1 = accounting.unformat($("#nilaiUangSaldoBkuBa").val(), ",");// $("nilaiSisaPaguSpp").val();
    var totalBapKas = accounting.unformat($("#totalbk").val(), ",");
    var totalBap = 0;

    var i, totalBapKas;
    totalBapKas = 0;

    for (i = 1; i <= idrow; i++) {
        if ($('#nilaiBapKas' + i).val() == "") {
            totalBapKas = 0;
        } else {
            totalBapKas = parseFloat(accounting.unformat($('#nilaiBapKas' + i).val(), ","));
        }
        totalBap += totalBapKas;
    }

    var status = totalBap <= nilaiUangSaldoBkuBa1;
    if (!status) {
        bootbox.alert("Nilai BAP KAS Rinci tidak boleh lebih besar dari nilai saldo BKU BA", function() {
            $("#nilaiBapKas" + idSkpdBAPKasRinci).autoNumeric('set', 0);
            hitungnilaiBapKas();
        });

    } else {
        return true;
    }
}

function numbersonly(myfield, e, dec) {
    var key;
    var keychar;

    if (window.event)
        key = window.event.keyCode;
    else if (e)
        key = e.which;
    else
        return true;
    keychar = String.fromCharCode(key);

// control keys
    if ((key == null) || (key == 0) || (key == 8) ||
            (key == 9) || (key == 13) || (key == 27) || (key == 46) || (key == 45))
        return true;

// numbers
    else if ((("0123456789").indexOf(keychar) > -1))
        return true;

// decimal point jump
    else if (dec && (keychar == "."))
    {
        myfield.form.elements[dec].focus();
        return false;
    }
    else
        return false;
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function setformatKertas(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangKertas1').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}

function setformatLogam(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangLogam1').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}

function setformatSp2d(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangSp2d1').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}

function setformatSaldoBank(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangSaldoBank1').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}

function setformatSuratBerharga(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangSuratBerharga1').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}

function setformatSaldoBkuBa(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangSaldoBkuBa').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}

function hapusBapKasRinci(id) {
    var table = document.getElementById('bapkasrincitablefoot2');
    var id2 = id - idrowbaru + 1 - jumhapus;

    table.deleteRow(id2);

    jumhapus += 1;

    getTotal();

}


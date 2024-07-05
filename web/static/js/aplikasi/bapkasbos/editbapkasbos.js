
$(document).ready(function() {
    setTw();
    gridbapkasrinci();
    hitungnilaiBapKas();
    getNilaiTotalSelisihBAPKas();
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
function pindahhalamanadepan() {
    window.location.replace(getbasepath() + "/bapkasbos/indexbapkas");
}

function setTw() {
    var tw;

    if ($('#triwulan').val() == "1") {
        tw = "Triwulan I";
    } else if ($('#triwulan').val() == "2") {
        tw = "Triwulan II";
    } else if ($('#triwulan').val() == "3") {
        tw = "Triwulan III";
    } else if ($('#triwulan').val() == "4") {
        tw = "Triwulan IV";
    }

    $('#tw').val(tw);
    getNilaiKas();
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
    var urljson = getbasepath() + "/bapkasbos/json/getlistbapkasrinci";
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
                var namaBapKas = "<input type='text' name='namaBapKas" + index + "' id='namaBapKas" + index + "' value = '" + aData['namaBapKas'] + "'  readonly='true'  />";

                var inputBapKas = "<input type='text'   id='nilaiBapKas" + index + "'    name='nilaiBapKas" + index + "'   value='" + aData['nilaiBapKas'] + "'  class='inputmoney '   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaiBapKas()'  readonly='true'   />";
                var nilaiBapKashidden = "<input type='hidden' name='nilaiBapKasorg" + index + "'     id='nilaiBapKasorg" + index + "' value='" + aData['nilaiBapKas'] + "' />";
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
                $('td:eq(3)', nRow).html(inputcek);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSekolahBAPKasRinci", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaBapKas", "bSortable": true, sClass: "left"},
                {"mDataProp": "nilaiBapKas", "bSortable": true, sClass: "center"},
                {"mDataProp": "idSekolahBAPKasRinci", "bSortable": false, sClass: "center"}
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
    $.getJSON(getbasepath() + "/bapkasbos/json/getbanyakrincibapkas", {idSekolahBAPKas: $("#idSekolahBAPKas").val()},
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
    //var nilaiUangSaldoBkuBa1 = accounting.unformat($("#nilaiUangSaldoBkuBa").val(), ",");
    if (totalbk1 < 0) { //maka minus
        totalbk1a = 0 - totalbk1;
    }
    else {
        totalbk1a = totalbk1;
    }
    var nilaiUangSaldoBkuBa1 = accounting.unformat($("#nilaiUangSelisihBkuBa").val(), ",");
    if (nilaiUangSaldoBkuBa1 < 0) { //maka minus
        nilaiUangSaldoBkuBa1a = 0 - nilaiUangSaldoBkuBa1;
    }
    else {
        nilaiUangSaldoBkuBa1a = nilaiUangSaldoBkuBa1;
    }
    var totalBap = 0;
    var n = id;

    if (totalbk1a > nilaiUangSaldoBkuBa1a) { //jika total rinci > total bapkas
        //bootbox.alert("Nilai total rinci harus <= Nilai Saldo BAP KAS");
        bootbox.alert("Nilai Total rinci Harus Sama dengan Nilai Selisih BAP Kas");
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

function simpan() {
    var totalrinci = accounting.unformat($("#totalbk").val(), ",");
    var selisih = accounting.unformat($("#nilaiUangSelisihBkuBa").val(), ",");

    if (totalrinci == selisih) {
        return true;
    } else {
        bootbox.alert("Nilai Total rinci Harus Sama dengan Nilai Selisih BAP Kas");
        return false;
    }
}
function getNilaiKas() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();

    $.getJSON(getbasepath() + "/bapkasbos/json/getNilaiKas", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan},
    function(result) {

        var nilai = result.aData['nilaiUangSaldoBkuBa'];
        $("#nilaiUangSaldoBkuBa").val(accounting.formatNumber(nilai, 2, '.', ","));
        if ($("#arsip").val() != '1') {
            getNilaiTotalSelisihBAPKas2(nilai);
        }
    });
}

function getNilaiTotalSelisihBAPKas2(nilai) {
    var nilaix = nilai;
    var nilai, kertas1, logam1, sp2d1, sb1, saldoBank1, total1, kr1, tbku1, totalselisih;
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

    totalselisih = (kertas1 + logam1 + sp2d1 + saldoBank1 + sb1) - nilaix;

    $('#nilaiUangSelisihBkuBa').val(accounting.formatNumber(totalselisih, 2, '.', ","));
}
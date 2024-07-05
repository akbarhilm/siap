
$(document).ready(function() {
    setTw();
    getNilaiTotalBAPKas(); // ditambah agar jika nilainya 0 tidak error jika langsung disimpan (Nilai Total BAP Kas tidak null)
    getNilaiTotalSelisihBAPKas();
    getRekKor();
});

var idrow = 0;
var banyaktampil = 0;
var noNR = 0;
var nomorgrid = 0;
var banyakdata;
var kosong = 0;
var totaldebetNR;
var totalkreditNR;
var totaldebet;
var totalkredit;
var grandtotalD = 0;
var grandtotalK = 0;
var idbas = new Array();
var idbaslist = new Array();
var idpilih;
var unitawal;

var banyakdataawal;


var jumhapus = 0;
var idbuton;

function pindahhalamanadepan() {
    window.location.replace(getbasepath() + "/bapkasbos/indexbapkas");
}

function setTw() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/bapkasbos/json/setTriwulan", {tahun: tahun, idsekolah: idsekolah},
    function(result) {

        var banyak, tw, kettw;
        var opt = "";

        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                tw = result.aData[i]['TRIWULAN'];
                kettw = result.aData[i]['KETTW'];
                //alert(bulan+ketbulan);
                opt += '<option value="' + tw + '">' + kettw + '</option>';
            }
        }
        $("#triwulan").html(opt);
        getNilaiKas();
    });

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

}

function getTotal() {
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

function tambahRow() {
    idrow += 1;
    nomorgrid += 1;

    var table = document.getElementById('bapkasrincitablefoot2');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    cell1.innerHTML = "<td class= 'center'  >" + idrow + " </td>";
    cell2.innerHTML = "<td> <input type='text' name='namaBapKas" + idrow + "' id='namaBapKas" + idrow + "' style='width: 900px;' /> </td>";
    cell3.innerHTML = "<td> <input type='text' name='nilaiBapKas" + idrow + "' id='nilaiBapKas" + idrow + "'  class='inputmoney'  value = '0' onkeyup='getTotal(),cektotalbk(" + idrow + ")'  /> </td>";
    cell4.innerHTML = "<td class= 'center' ><a class='center icon-remove' onclick='hapusBapKasRinci(" + idrow + "); getbutton(" + idrow + ")' ></a> </td>";

    formatnumberonkeyup();
    $('#banyakrinci').val(idrow);

}

function hapusBapKasRinci(id) {

    var table = document.getElementById('bapkasrincitablefoot2');
    var id2 = id - idrow + 1 - jumhapus;

    table.deleteRow(id2);
    jumhapus += 1;

    var banyak = $('#banyakrinci').val();
    banyak -= 1;
    $('#banyakrinci').val(banyak);

}

function getbutton(id) {
    idbuton = id;
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
    $("#nilaiUangSelisihBkuBa").val(accounting.formatNumber(totalselisih, 2, '.', ","));
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
        $('#nilaiBapKas' + n).autoNumeric('set', 0);
        getTotal();
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
        getNilaiTotalSelisihBAPKas();
    });
}

function getRekKor() {
    var rek = $("#rekeningBOS").val();
    var varurl = getbasepath() + "/bkustrans/json/rekeningkoran";
    var dataac = [];
    var datajour = {
        account: rek

    };
    dataac = datajour;

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {
            //console.log(data);
            getsaldo();

        },
        error: function(xhr) {
            console.error(xhr);
            bootbox.alert("Sambungan Bank DKI Terputus");
            //$('#btnSimpan').attr("disabled", true);
        }
    }).always(function(data) {
    });
}


function getsaldo() {
    var url = getbasepath() + "/bkustrans/json/saldoakhir"
    var rekbos = $("#rekeningBOS").val();
    var tipe = "BOS";
    var rekbop = "";

    $.getJSON(url, {tipe: tipe, rekeningbos: rekbos, rekeningbop: rekbop},
    function(data) {
        //console.log(data.aData[0])
        $("#nilaiUangSaldoBank1").val(accounting.formatNumber(data.aData[0]['V_TRX_SALDOAKHIR'], 2, '.', ","));
        // $("#saldokeluar").val(accounting.formatNumber(data.aData[0]['V_TRX_SALDOAKHIR'], 2, '.', ","));
        getNilaiTotalSelisihBAPKas();
    }
    );
}
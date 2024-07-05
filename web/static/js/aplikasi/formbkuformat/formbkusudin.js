$(document).ready(function() {
    $('#labeltanggal').hide();
    $('#bulan1').hide();
    setPanelAkun();
    setPanelAkundinas();
    setIdSkpd();
});

function cekkodeOtor() {
    var idskpd = $("#idskpd").val();
    console.log("cek idskpd=" + idskpd);
    if (idskpd === "2" || idskpd === "12782") {
        console.log("DINAS");
        $('#jenislapdinas').show();
        $('#jenislap').hide();
        $('#formsudin').hide();
        $('#formdinas').show();
        setPanelAkundinas();
    } else {
        console.log("SUDIN");
        $('#jenislapdinas').hide();
        $('#jenislap').show();
        $('#formsudin').show();
        $('#formdinas').hide();
        setPanelAkun();
    }
}

function setDisplayWilayah() {
    if ($("#idskpd").val() == 761) {
        document.getElementById("labelwilayah").style.display = "block";
    } else {
        document.getElementById("labelwilayah").style.display = "none";
    }
}

function cetak() {
    var idsekolah = $('#idsekolah').val();
    var triwulan = $("#triwulan").val();
    //var bulan = $("#bulan").val();
    var tahun = $("#tahun").val();
    var jenislaporan = $("#jenislap").val();
    var kodewil = $("#kecamatan").val();
    var idskpd = $("#idskpd").val();
    var nipkasi = $("#nipSisdik").val();
    var namakasi = $("#namaSisdik").val();
    //var unformat = $("#tanggal").val();
    //var semester = $("#semester").val();
    //var tanggal = unformat.substr(6, 4) + unformat.substr(3, 2) + unformat.substr(0, 2);

    if (idsekolah === "") {
        bootbox.alert("Data Tidak Tersedia");
    } else {
        if (jenislaporan === '2') {
            if (nipkasi == "" || namakasi == "") {
                bootbox.alert("Nama dan NIP Kasie Disdik masih kosong"); // butuh panel insert atau mau dijadiin satu panel

            }
            else {
                window.location.href = getbasepath() + "/formbkuformat/json/prosescetaksudin?tahun="
                        + tahun + "&triwulan=" + triwulan + "&jenislaporan=" + jenislaporan + "&kodewil=" + kodewil + "&idskpd=" + idskpd + "&nipkasi=" + nipkasi + "&namakasi=" + namakasi;
            }
        }
        else {
            window.location.href = getbasepath() + "/formbkuformat/json/prosescetaksudin?tahun="
                    + tahun + "&triwulan=" + triwulan + "&jenislaporan=" + jenislaporan + "&idskpd=" + idskpd;
        }
    }
}

function setIdskpd(id) {
    $('#idskpd').val(id);
    console.log("idskpd=" + idskpd);
    setSaldoAwal();
}

function setSaldoAwal() {
    var bulan = $('#bulan').val()
    var idsekolah = $('#idsekolah').val();
    var jenis = $('#jenislap').val();

    if (jenis == "7" || jenis == "8" || jenis == "9" || jenis == "P1" || jenis == "P2" || jenis == "P3" || jenis == "P4" || jenis == "P5" || jenis == "P6") {
        $.getJSON(getbasepath() + "/formbkuformat/json/getSaldoAwal", {jenis: jenis, bulan: bulan, idsekolah: idsekolah},
        function(result) {
            var saldo = result.aData[0]['saldoawal'];
            console.log("saldo = " + saldo);
            $("#saldo").val(saldo);
            document.getElementById("saldo").readOnly = true;
        });
    } else {
        document.getElementById("saldo").readOnly = false;
        $("#saldo").val("0");
    }
}

function setAkunBelanja() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/formbkuformat/json/getAkunBelanja", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = result.aData.length;
        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['kodeakun'];
                ket = result.aData[i]['namaakun'];
                opt += '<option value="' + kode + '" >' + ket + '</option>';
            }
            $("#akunbelanja").html(opt);
        }
    });
}

function setPanelAkun() {
    if ($('#jenislap').val() === '2') {
        $('#labeltriwulan').hide();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $("#btncetak").show();
        $("#btnUpdSisdik").show();
        setKecamatan();
        $('#labelkecamatan').show();
        document.getElementById("labelkecamatan").style.display = "block";
        setSisdik();
        $('#labelNipSisdik').show();
        $('#labelNamaSisdik').show();
    } else if ($('#jenislap').val() === '3') {
        $('#labeltriwulan').hide();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelsemester').hide();
        $("#btncetak").show();
        $("#btnUpdSisdik").hide();
        $('#labelkecamatan').hide();
        $('#labelNipSisdik').hide();
        $('#labelNamaSisdik').hide();
        document.getElementById("labelkecamatan").style.display = "none";
    } else {
        $('#labeltriwulan').hide();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $("#btncetak").hide();
        $("#btnUpdSisdik").hide();
        $('#labelkecamatan').hide();
        document.getElementById("labelkecamatan").style.display = "none";
        $('#labelNipSisdik').hide();
        $('#labelNamaSisdik').hide();
    }
}

function setPanelAkundinas() {
    var jenislap = $('#jenislapdinas').val();
    console.log("jenislapdinas=" + jenislap);
    if ($('#jenislapdinas').val() === '4') {
        $('#labeltriwulan').hide();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $("#btncetak").show();
        $("#btnUpdSisdik").hide();
        $('#labelkecamatan').hide();
        document.getElementById("labelkecamatan").style.display = "none";
        $('#labelNipSisdik').hide();
        $('#labelNamaSisdik').hide();
    } else {
        $('#labeltriwulan').hide();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $("#btncetak").hide();
        $("#btnUpdSisdik").hide();
        $('#labelkecamatan').hide();
        document.getElementById("labelkecamatan").style.display = "none";
        $('#labelNipSisdik').hide();
        $('#labelNamaSisdik').hide();
    }
}

function setWilayah() {
    $.getJSON(getbasepath() + "/formbkuformat/json/getWilayah", {},
            function(result) {
                var banyak, kode, ket;
                var opt = "";
                banyak = result.aData.length;
                for (var i = 0; i < banyak; i++) {
                    kode = result.aData[i]['kodeWilayah'];
                    ket = result.aData[i]['ketWilayah'];
                    opt += '<option value="' + kode + '" >' + ket + '</option>';
                }
                $("#wilayah").html(opt);
            });
}

function setTriwulan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/formbkuformat/json/setTriwulan", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        //console.log(result);
        var banyak, triwulan;
        var opt = "";
        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                triwulan = result.aData[i]['triwulan'];
                opt += '<option value="' + triwulan + '"> Triwulan ' + triwulan + ' </option>';
            }
            $('#btncetak').attr("disabled", false);
        } else {
            $('#btncetak').attr("disabled", true);
        }
        $("#triwulan").html(opt);
    });
}

function cekRekap() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/formbkuformat/json/cekRekap", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        if (result <= 0) {
            //console.log(" banyak <=0");
            bootbox.alert("Anda belum melakukan draft tutup bku. Silahkan melakukan draft tutup bku terlebih dahulu.");
        }
        else {
            //console.log(" banyak >0");
        }
    });
}

function setIdSkpd() {
    var nrkPptk = $('#nrkPptk').val();
    $.getJSON(getbasepath() + "/formbkuformat/json/getIdSkpd", {nrkPptk: nrkPptk},
    function(result) {
        var banyak, idskpd;
        banyak = result.aData.length;
        idskpd = result.aData[0]['idskpd'];
        document.getElementById("idskpd").readOnly = true;
        $("#idskpd").val(idskpd);
        cekkodeOtor();
    });
}

function setKecamatan() {
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/formbkuformat/json/getKecamatan", {idskpd: idskpd},
    function(result) {
        var banyak, idwil, kodewil, ket;
        var opt = "";
        banyak = result.aData.length;
        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                idwil = result.aData[i]['idWilayah'];
                kodewil = result.aData[i]['kodeWilayah'];
                ket = result.aData[i]['namaWilayah'];
                opt += '<option value="' + kodewil + '" >' + ket + '</option>';
            }
            $("#kecamatan").html(opt);
        }
    });
}

function setSisdik() {
    var kecamatan = $('#kecamatan').val();
    console.log("cek kecamatan Sisdik=" + kecamatan);
    $.getJSON(getbasepath() + "/formbkuformat/json/getSisdik", {kecamatan: kecamatan},
    function(result) {
        var nipSisdik1, namaSisdik1;
        var nipSisdik1 = result.aData[0]['nipSisdik'];
        var namaSisdik1 = result.aData[0]['namaSisdik'];
        $('#nipSisdik').val(nipSisdik1);
        $('#namaSisdik').val(namaSisdik1);
        console.log("cek kecamatan nipSisdik=" + nipSisdik1);
        console.log("cek kecamatan namaSisdik=" + namaSisdik1);
    });
}

function updateSisdik() {
    if ($("#nipSisdik").val() != '' && $("#nipSisdik").val() != null && $("#namaSisdik").val() != '' && $("#namaSisdik").val() != null) {
        var varurl = getbasepath() + "/formbkuformat/json/updateSisdik";
        var dataac = [];
        var data = {nipSisdik: $("#nipSisdik").val(), namaSisdik: $("#namaSisdik").val(), kecamatan: $("#kecamatan").val()}
        dataac = data;
        $.ajax({
            type: "POST",
            url: varurl,
            contentType: "text/plain; charset=utf-8",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'},
            data: JSON.stringify(dataac)}).always(function() {
            bootbox.alert("Data Sisdik Berhasil Diperbarui");
            $("#btnCetak").show();
            $("#btnSimpan").hide();
        });
    } else {
        bootbox.alert("Lengkapi data terlebih dahulu");
    }
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
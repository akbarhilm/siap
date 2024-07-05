$(document).ready(function() {
    $('#labelkegiatan').hide();
    $('#labeltanggal').hide();
    setKegiatan();
    setTriwulan();
    //setBulan();
    //setSemester();
    cekRekap()
    $('#bulan1').hide();
    setPanelAkun();
});

function cetak() {
    var idsekolah = $('#idsekolah').val();
    var triwulan = $("#triwulan").val();
    var tahun = $("#tahun").val();
    var jenislaporan = $("#jenislap").val();
    var akun = $("#akunbelanja").val();
    var idkegiatan = $("#kegiatan").val();
    var bulan = $("#bulan").val();
    var semester = $("#semester").val();
    var nosurat = $("#nosurat").val();
    var unformat = $("#tanggal").val();
    var tanggal = unformat.substr(6, 4) + unformat.substr(3, 2) + unformat.substr(0, 2);

    if (idsekolah === "") {
        bootbox.alert("Data Tidak Tersedia");
    }
    else {
        if (jenislaporan === 'SPT') {
            if (tanggal == "") {
                console.log("tanggalnya NULL");
                bootbox.alert("Data Tidak Lengkap");
            }
            else {
                window.location.href = getbasepath() + "/formbkubos/json/prosescetak?tahun="
                        + tahun + "&idsekolah=" + idsekolah + "&triwulan=" + triwulan + "&jenislaporan=" + jenislaporan + "&akun=" + akun + "&idkegiatan=" + idkegiatan + "&tanggal=" + tanggal + "&bulan=" + bulan + "&semester=" + semester;
            }
        }
        else {
            window.location.href = getbasepath() + "/formbkubos/json/prosescetak?tahun="
                    + tahun + "&idsekolah=" + idsekolah + "&triwulan=" + triwulan + "&jenislaporan=" + jenislaporan + "&akun=" + akun + "&idkegiatan=" + idkegiatan + "&tanggal=" + tanggal + "&bulan=" + bulan + "&semester=" + semester;
        }
    }
}

function setIdskpd(id) {
    $('#idskpd').val(id);
    setSaldoAwal();
}

function setSaldoAwal() {
    var bulan = $('#bulan').val()
    var idsekolah = $('#idsekolah').val();
    var jenis = $('#jenislap').val();
    if (jenis == "7" || jenis == "8" || jenis == "9" || jenis == "P1" || jenis == "P2" || jenis == "P3" || jenis == "P4" || jenis == "P5" || jenis == "P6") {
        $.getJSON(getbasepath() + "/formbkubos/json/getSaldoAwal", {jenis: jenis, bulan: bulan, idsekolah: idsekolah},
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
    $.getJSON(getbasepath() + "/formbkubos/json/getAkunBelanja", {tahun: tahun, idsekolah: idsekolah},
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

function setKegiatan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/formbkubos/json/getKegiatan", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        var banyak, id, nama;
        var opt = "";
        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                id = result.aData[i]['idKegiatan'];
                nama = result.aData[i]['namaKegiatan'];
                opt += '<option value="' + id + '" >' + nama + '</option>';
            }
            $("#kegiatan").html(opt);
        }
    });
}

function setPanelAkun() {
    if ($('#jenislap').val() == '27') {
        $('#labeltriwulan').hide();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').show();
        document.getElementById("labelakunbelanja").style.display = "none";
        $("#btncetak").show();
        setAkunBelanja();
    } else if ($('#jenislap').val() == 'KK') {
        $('#labeltriwulan').show();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').show();
        document.getElementById("labelakunbelanja").style.display = "none";
        $("#btncetak").show();
        setKegiatan();
    } else if ($('#jenislap').val() == 'KKK') {
        $('#labeltriwulan').show();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').show();
        document.getElementById("labelakunbelanja").style.display = "none";
        $("#btncetak").show();
        setKegiatan();
    } else if ($('#jenislap').val() == 'BKUBOS') {
        $('#labeltriwulan').hide();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').show();
        $("#btncetak").show();
        document.getElementById("labelakunbelanja").style.display = "none";
        document.getElementById("labelkegiatan").style.display = "none";
        setKegiatan();
    } else if ($('#jenislap').val() == 'RD') {
        $('#labeltriwulan').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').show();
        $('#labelsemester').hide();
        $("#btncetak").show();
        document.getElementById("labelakunbelanja").style.display = "none";
        document.getElementById("labelkegiatan").style.display = "none";
        setKegiatan();
    } else if ($('#jenislap').val() == 'TJ') {
        $('#labeltriwulan').hide();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
        $("#btncetak").show();
        setKegiatan();
    } else if ($('#jenislap').val() == 'P1' || $('#jenislap').val() == 'P2' || $('#jenislap').val() == 'P3' || $('#jenislap').val() == 'P4' || $('#jenislap').val() == 'P5' || $('#jenislap').val() == 'P6') {
        $('#labeltriwulan').show();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
        $("#btncetak").show();
    }
    else if ($('#jenislap').val() == 'SPT') {
        $('#labeltriwulan').show();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').show();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').hide();
        $("#btncetak").show();
        document.getElementById("labelakunbelanja").style.display = "none";
        document.getElementById("labelkegiatan").style.display = "none";
    }
    else if ($('#jenislap').val() == 'MT') {
        $('#labeltriwulan').show();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').show();
        $("#btncetak").show();
        document.getElementById("labelakunbelanja").style.display = "none";
        document.getElementById("labelkegiatan").style.display = "none";
    }
    else if ($('#jenislap').val() == '8') {
        $('#labeltriwulan').show();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').hide();
        $("#btncetak").show();
        document.getElementById("labelakunbelanja").style.display = "none";
        document.getElementById("labelkegiatan").style.display = "none";
    }
    else if ($('#jenislap').val() == '22') {
        $('#labeltriwulan').show();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').hide();
        $("#btncetak").show();
        document.getElementById("labelakunbelanja").style.display = "none";
        document.getElementById("labelkegiatan").style.display = "none";
    }
    else if ($('#jenislap').val() == 'PJK') {
        $('#labeltriwulan').show();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').hide();
        $("#btncetak").show();
        document.getElementById("labelakunbelanja").style.display = "none";
        document.getElementById("labelkegiatan").style.display = "none";
    } else if ($('#jenislap').val() == 'MT9') {
        $('#labeltriwulan').show();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').hide();
        $("#btncetak").show();
        document.getElementById("labelakunbelanja").style.display = "none";
        document.getElementById("labelkegiatan").style.display = "none";
    }
    else {
        $('#labeltriwulan').hide();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        $('#labeltanggal').hide();
        $('#labelakunbelanja').hide();
        $('#labelkegiatan').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
        document.getElementById("labelkegiatan").style.display = "none";
        $("#btncetak").hide();
    }
}

function setWilayah() {
    $.getJSON(getbasepath() + "/formbkubos/json/getWilayah", {},
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

    $.getJSON(getbasepath() + "/formbkubos/json/setTriwulan", {tahun: tahun, idsekolah: idsekolah},
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
/*
 * tidak dihapus, ada kemungkinan akan digunakan untuk report baru
 function setBulan() {
 var tahun = $('#tahun').val();
 var idsekolah = $('#idsekolah').val();
 $.getJSON(getbasepath() + "/formbkubos/json/setBulan", {tahun: tahun, idsekolah: idsekolah},
 function(result) {
 console.log("cek anitaikan result bulan=", +result);
 var banyak, bulan;
 var opt = "";
 banyak = result.aData.length;
 console.log("cek anitaikan banyak=", +banyak);
 if (banyak > 0) {
 for (var i = 0; i < banyak; i++) {
 bulan = result.aData[i]['bulan'];
 opt += '<option value="' + bulan + '"> Bulan ' + bulan + ' </option>';
 }
 $('#btncetak').attr("disabled", false);
 } else {
 $('#btncetak').attr("disabled", true);
 }
 $("#bulan").html(opt);
 });
 }

 function setSemester() {
 var tahun = $('#tahun').val();
 var idsekolah = $('#idsekolah').val();

 $.getJSON(getbasepath() + "/formbkubos/json/setSemester", {tahun: tahun, idsekolah: idsekolah},
 function(result) {
 console.log("cek anitaikan result semester=", +result);
 var banyak, semester;
 var opt = "";
 banyak = result.aData.length;
 console.log("cek anitaikan banyak=", +banyak);
 if (banyak > 0) {
 for (var i = 0; i < banyak; i++) {
 semester = result.aData[i]['semester'];
 opt += '<option value="' + semester + '"> Semester ' + semester + ' </option>';
 }
 $('#btncetak').attr("disabled", false);
 } else {
 $('#btncetak').attr("disabled", true);
 }
 $("#semester").html(opt);
 });
 }
 */
function cekRekap() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/formbkubos/json/cekRekap", {tahun: tahun, idsekolah: idsekolah},
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

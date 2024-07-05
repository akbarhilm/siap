$(document).ready(function() {
    //document.getElementById("labelakunbelanja").style.display = "none";
    //$('#labelkegiatan').hide();
    //$('#labeltanggal').hide();
    //setKegiatan();
    setTriwulan();
    $('#bulan1').hide();
    //setPanelAkun();
});

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
    //var akun = $("#akunbelanja").val();
    //var idkegiatan = $("#kegiatan").val();
    //var unformat = $("#tanggal").val();
    //var semester = $("#semester").val();
    //var tanggal = unformat.substr(6, 4) + unformat.substr(3, 2) + unformat.substr(0, 2);

    if (idsekolah === "") {
        bootbox.alert("Data Tidak Tersedia");
    } else {
        //if (jenislap === '7') {
        //    window.location.href = getbasepath() + "/formbkuformat/json/prosescetak?tahun="
        //            + tahun + "&idsekolah=" + idsekolah + "&triwulan=" + triwulan + "&jenislaporan=" + jenislaporan + "&akun=" + akun;
        //}
        //else {
        window.location.href = getbasepath() + "/formbkuformat/json/prosescetak?tahun="
                + tahun + "&idsekolah=" + idsekolah + "&triwulan=" + triwulan + "&jenislaporan=" + jenislaporan;
        //}
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
/*
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

 function setKegiatan() {
 var tahun = $('#tahun').val();
 var idsekolah = $('#idsekolah').val();
 $.getJSON(getbasepath() + "/formbkuformat/json/getKegiatan", {tahun: tahun, idsekolah: idsekolah},
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
 */
function setPanelAkun() {
    if ($('#jenislap').val() === '1') {
        $('#labeltriwulan').show();
        //$('#labelsemester').hide();
        $('#bulan1').hide();
        //$('#labeltanggal').hide();
        //$('#labelakunbelanja').hide();
        //$('#labelkegiatan').hide();
        //document.getElementById("labelakunbelanja").style.display = "none";
        //setAkunBelanja();
        $("#btncetak").show();
    } else if ($('#jenislap').val() === '2') {
        $('#labeltriwulan').show();
        $('#labelsemester').hide();
        $('#bulan1').hide();
        //$('#labeltanggal').hide();
        //$('#labelakunbelanja').hide();
        //$('#labelkegiatan').hide();
        //document.getElementById("labelakunbelanja").style.display = "none";
        //setKegiatan();
        $("#btncetak").show();
    } else if ($('#jenislap').val() === '3') {
        $('#labeltriwulan').show();
        //$('#labelsemester').hide();
        $('#bulan1').hide();
        //$('#labeltanggal').hide();
        //$('#labelakunbelanja').hide();
        //$('#labelkegiatan').hide();
        //document.getElementById("labelakunbelanja").style.display = "none";
        $("#btncetak").show();
    } else if ($('#jenislap').val() === '4') {
        $('#labeltriwulan').show();
        //$('#labelsemester').hide();
        $('#bulan1').hide();
        //$('#labeltanggal').hide();
        //$('#labelakunbelanja').hide();
        //$('#labelkegiatan').hide();
        //document.getElementById("labelakunbelanja").style.display = "none";
        $("#btncetak").show();
    } else if ($('#jenislap').val() === '5') {
        $('#labeltriwulan').show();
        //$('#labelsemester').hide();
        $('#bulan1').hide();
        //$('#labeltanggal').hide();
        //$('#labelakunbelanja').hide();
        //$('#labelkegiatan').hide();
        //document.getElementById("labelakunbelanja").style.display = "none";
        $("#btncetak").show();
    } else {
        $('#labeltriwulan').hide();
        //$('#labelsemester').hide();
        $('#bulan1').hide();
        //$('#labeltanggal').hide();
        //$('#labelakunbelanja').hide();
        //$('#labelkegiatan').hide();
        //document.getElementById("labelakunbelanja").style.display = "none";
        $("#btncetak").hide();
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

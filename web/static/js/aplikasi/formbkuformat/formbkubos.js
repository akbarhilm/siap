$(document).ready(function() {
    setTriwulan();
    $('#bulan1').hide();
    setPanelAkun();
    setComboBulan();
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
    var bulan = $("#bulan").val();
    var tahun = $("#tahun").val();
    var jenislaporan = $("#jenislap").val();
    var akun = $("#akunbelanja").val();
    var nosurat = $("#nosurat").val();
    var kastunai = $("#kastunai").val();
    console.log("akun=" + akun);

    if (idsekolah === "") {
        bootbox.alert("Data Tidak Tersedia");
    } else {
        if (jenislaporan === '1' || jenislaporan === '2' || jenislaporan === '3' || jenislaporan === '4') {
            window.location.href = getbasepath() + "/formbkubosformat/json/prosescetak?tahun="
                    + tahun + "&idsekolah=" + idsekolah + "&bulan=" + bulan + "&jenislaporan=" + jenislaporan;
        } else if (jenislaporan === '6' || jenislaporan === '8') {
            window.location.href = getbasepath() + "/formbkubosformat/json/prosescetak?tahun="
                    + tahun + "&idsekolah=" + idsekolah + "&triwulan=" + triwulan + "&jenislaporan=" + jenislaporan;
        } else if (jenislaporan === '5') {
            window.location.href = getbasepath() + "/formbkubosformat/json/prosescetak?tahun="
                    + tahun + "&idsekolah=" + idsekolah + "&bulan=" + bulan + "&jenislaporan=" + jenislaporan + "&akun=" + akun;
        } else { //jenis laporan = 7 (
            window.location.href = getbasepath() + "/formbkubosformat/json/prosescetak2?tahun="
                    + tahun + "&idsekolah=" + idsekolah + "&triwulan=" + triwulan + "&jenislaporan=" + jenislaporan + "&nosurat=" + nosurat + "&kastunai=" + kastunai;
        }
    }
}

function setPanelAkun() {
    if ($('#jenislap').val() === '1') {
        $('#labeltriwulan').hide();
        setComboBulan();
        $('#bulan1').show();
        setAkunBelanja();
        $("#btncetak").show();
        $("#btnproses").show();
        $("#labelNosurat").hide();
        $("#labelKastunai").hide();
        $('#labelakunbelanja').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
    } else if ($('#jenislap').val() === '2') {
        $('#labeltriwulan').hide();
        setComboBulan();
        $('#bulan1').show();
        setKegiatan();
        $("#btncetak").show();
        $("#btnproses").show();
        $("#labelNosurat").hide();
        $("#labelKastunai").hide();
        $('#labelakunbelanja').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
    } else if ($('#jenislap').val() === '3') {
        $('#labeltriwulan').hide();
        setComboBulan();
        $('#bulan1').show();
        $("#btncetak").show();
        $("#btnproses").show();
        $("#labelNosurat").hide();
        $("#labelKastunai").hide();
        $('#labelakunbelanja').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
    } else if ($('#jenislap').val() === '4') {
        $('#labeltriwulan').hide();
        setComboBulan();
        $('#bulan1').show();
        $("#btncetak").show();
        $("#btnproses").show();
        $("#labelNosurat").hide();
        $("#labelKastunai").hide();
        $('#labelakunbelanja').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
    } else if ($('#jenislap').val() === '5') {
        $('#labeltriwulan').hide();
        setComboBulan();
        $('#bulan1').show();
        $("#btncetak").show();
        $("#btnproses").show();
        $("#labelNosurat").hide();
        $("#labelKastunai").hide();
        setAkunBelanja();
        $('#labelakunbelanja').show();
        document.getElementById("labelakunbelanja").style.display = "block";
    } else if ($('#jenislap').val() === '6') {
        $('#labeltriwulan').show();
        $('#bulan1').hide();
        $("#btncetak").show();
        $("#btnproses").show();
        $("#labelNosurat").hide();
        $("#labelKastunai").hide();
        $('#labelakunbelanja').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
    } else if ($('#jenislap').val() === '7') {
        $('#labeltriwulan').show();
        $('#bulan1').hide();
        $("#btncetak").show();
        $("#btnproses").show();
        $("#labelNosurat").show();
        $("#labelKastunai").show();
        $('#labelakunbelanja').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
    } else if ($('#jenislap').val() === '8') {
        $('#labeltriwulan').show();
        setComboBulan();
        $('#bulan1').hide();
        $("#btncetak").show();
        $("#btnproses").show();
        $("#labelNosurat").hide();
        $("#labelKastunai").hide();
        $('#labelakunbelanja').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
    } else {
        $('#labeltriwulan').hide();
        $('#bulan1').hide();
        $("#btncetak").hide();
        $("#btnproses").hide();
        $("#labelNosurat").hide();
        $("#labelKastunai").hide();
        $('#labelakunbelanja').hide();
        document.getElementById("labelakunbelanja").style.display = "none";
    }
}

function setWilayah() {
    $.getJSON(getbasepath() + "/formbkubosformat/json/getWilayah", {},
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

    $.getJSON(getbasepath() + "/formbkubosformat/json/setTriwulan", {tahun: tahun, idsekolah: idsekolah},
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

function setAkunBelanja() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/formbkubosformat/json/getAkunBelanja", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        var banyak, idakun, kode, ket;
        var opt = "";
        banyak = result.aData.length;
        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                idakun = result.aData[i]['idakun'];
                kode = result.aData[i]['kodeakun'];
                ket = result.aData[i]['namaakun'];
                opt += '<option value="' + idakun + '" >' + ket + '</option>';
            }
            $("#akunbelanja").html(opt);
        }
    });
}

function setComboBulan() {
    var idsekolah = $('#idsekolah').val();
    var tahun = $('#tahun').val();
    $.getJSON(getbasepath() + "/formbkubosformat/json/setComboBulan", {idsekolah: idsekolah, tahun: tahun},
    function(result) {
        var banyak, kode, ket;
        var opt = "<option>--Pilih Bulan--</option>"; // untuk tampilan awal combo bulan
        banyak = result.aData.length;
        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['kodeBulan'];
                ket = result.aData[i]['uraian'];
                opt += '<option value="' + kode + '" >' + ket + '</option>';
            }
        }
        $("#bulan").html(opt);
    });
}

function cekProsesBkuBulan() {
    var index = refcetak.bulan.selectedIndex;
    console.log("banyak list bulan = " + index);
    if (index >= 1) { // untuk item pertama ga usah di cek // Last Edited : untuk item index ke 2, karena ketambahan "--Pilih Bulan--" di index 1
        var indexSebelum = index - 1; // diinisialisasi setelah cek index paling awal agar sebelum <> -1

        var teksSebelum = document.getElementById("bulan").options.item(indexSebelum).text;
        var teksLenSebelum = String(teksSebelum).length;
        var keteranganSebelum = String(teksSebelum).substring(teksLenSebelum, teksLenSebelum - 14); //14 = length "BELUM DIPROSES"

        var teksSaatIni = document.getElementById("bulan").options.item(index).text;
        var teksLenSaatIni = String(teksSaatIni).length;
        var keteranganSaatIni = String(teksSaatIni).substring(teksLenSaatIni, teksLenSaatIni - 14);
        //console.log("teksSebelum Sebelum = " + teksSebelum);
        //console.log("teksLenSebelum Sebelum = " + teksLenSebelum);
        //console.log("keterangan Sebelum = " + keteranganSebelum);
        //console.log("keterangan keteranganSaatIni = " + keteranganSaatIni);

        if (keteranganSaatIni === "BELUM DIPROSES") {
            // bila bisa diproses dan keterangan bulannya "BELUM DIPROSES", maka hanya tombol draft yang aktif
            //kalo belum proses maka cetak disable dan proses enable
            $('#btnproses').attr("disabled", false);
            $('#btncetak').attr("disabled", true);
        }
        else if (keteranganSaatIni === "SUDAH DIPROSES") {
            // bila bisa diproses dan keterangan bulannya "SUDAH DIPROSES", maka yg aktif semua tombol
            $('#btnproses').attr("disabled", false);
            $('#btncetak').attr("disabled", false);
        }
        else {
            // bila bisa diproses dan keterangan bulannya "DRAFT DIPROSES", maka yg aktif tombol draft dan proses tutup
            $('#btnproses').attr("disabled", true);
            $('#btncetak').attr("disabled", true);
        }
    } else {
        $('#btnproses').attr("disabled", true);
        $('#btncetak').attr("disabled", true);
    }
}

function simpan() { // update status transfer di tabel master transaksi
    var varurl = getbasepath() + "/formbkubosformat/json/prosesupdateTIBulan";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        idsekolah: $("#idsekolah").val(),
        bulan: $("#bulan").val()
    };
    dataac = datajour;
    return   $.ajax({
        type: "POST",
        url: varurl, contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {
        bootbox.alert("Data Bku berhasil diproses");
        setComboBulan();
        cekProsesBkuBulan();
        $('#btncetak').attr("disabled", false);
    });
}

function reset() {
    document.getElementById("refcetak").reset();
}
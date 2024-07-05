$(document).ready(function() {
    setAwal();
});
// global variable
var banyakTutupMax, kodeTutupMax, bulanTutupMax;

function setAwal() {
    var masuk = $("#nilaiMasuk").val();
    var keluar = $("#nilaiKeluar").val();

    if (masuk > 0) {
        $("#nilaijg").val(masuk);
        $("#jenisPembayaran").val("PN");
        document.getElementById('nilaijg').readOnly = false;
        document.getElementById("pilihjg").style.visibility = "hidden";

    } else if (keluar > 0) {
        $("#nilaijg").val(keluar);
        $("#jenisPembayaran").val("PG");
        document.getElementById('nilaijg').readOnly = true;
        document.getElementById("pilihjg").style.visibility = "visible";
    }
}


function simpan() {

    var tglPost = $("#tglPosting").val();
    var nobukti = $("#noBuktiDok").val();
    var tglDok = $("#tglDok").val();
    var filling = $("#inboxFile").val();
    var bulanTglPost = tglPost.substr(3, 2);
    var tahunTglPost = tglPost.substr(6, 4);
    var bulan = $("#bulan").val();
    var nippptk = $("#nipPptk").val();
    var namapptk = $("#namaPptk").val();

    var nobkuref = accounting.unformat($("#noBkuRef").val(), ",");
    var jenis = accounting.unformat($("#jenisPembayaran").val(), ",");

    if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || nippptk == "" || namapptk == "") {
        bootbox.alert("Pengisian Data Harus Lengkap");
    } else if (bulanTglPost !== bulan) {
        bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
    } else if (tahunTglPost !== $("#tahun").val()) {
        bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
    } else if (jenis == "PG" && nobkuref == "") {
        bootbox.alert("Pilih Penerimaan Jasa Giro Terlebih Dulu");
    } else {
        update();
    }


}

function update() {
    var tahun = $("#tahun").val();
    var idsekolah = $("#idsekolah").val();
    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#inboxFile").val();
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;

    var nilaijg = accounting.unformat($("#nilaijg").val(), ",");
    var jenis = $("#jenisPembayaran").val();
    var nilaimasuk, nilaikeluar, nobkuref;

    if (jenis == "PN") {
        nilaimasuk = nilaijg.toString();
        nilaikeluar = "0";
        nobkuref = "";

    } else {
        nilaimasuk = "0";
        nilaikeluar = nilaijg.toString();
        nobkuref = $("#noBkuRef").val();
    }

    var varurl = getbasepath() + "/bku/json/prosesupdatebkujg";
    var dataac = [];

    var datajour = {
        tahun: tahun,
        idsekolah: idsekolah,
        nobku: $("#noBKU").val(),
        tglposting: tanggal,
        nobukti: $("#noBukti").val(),
        tgldok: $("#tglDok").val(),
        fileinbox: fileinbox,
        uraian: $("#uraian").val(),
        carabayar: $("#kodePembayaran").val(),
        nilaikeluar: nilaikeluar,
        nilaimasuk: nilaimasuk,
        nippptk: $("#nipPptk").val(),
        namapptk: $("#namaPptk").val(),
        nobkuref: nobkuref,
        kodesumbdana: $("#kodeSumbdana").val(),
        kodetransaksi: $("#kodeTransaksi").val(),
        jenispembayaran : jenis
    };
    dataac = datajour;
    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {
        bootbox.alert("Data Buku Kas Umum Berhasil Diubah");

    });

}

function setformatpengeluaran(nilai) {

    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaijg').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));

}

function getSisaKas() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var sumbdana = $('#kodeSumbdana').val();
    var nobku = $('#noBKU').val();

    $.getJSON(getbasepath() + "/bku/json/getSisaKas", {tahun: tahun, idsekolah: idsekolah, sumbdana: sumbdana, nobku: nobku},
    function(result) {

        var nilai = result.aData['saldoKas'];
        $('#sisakas').val(accounting.formatNumber(nilai, 2, '.', ","));

    });

}

function getKodeTutupMax() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/bku/json/getKodeTutupMax", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan, tgltutup, nextkodebulan, nextbulan;
        var opt;
        banyakTutupMax = result.aData.length;
        if (banyak > 0) {
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];
            tgltutup = result.aData[0]['kodeTglTutup'];
            if (kodebulan == "01") {
                nextkodebulan = "02";
                nextbulan = "02 - Februari";
            } else if (kodebulan == "02") {
                nextkodebulan = "03";
                nextbulan = "03 - Maret";
            } else if (kodebulan == "03") {
                nextkodebulan = "04";
                nextbulan = "04 - April";
            } else if (kodebulan == "04") {
                nextkodebulan = "05";
                nextbulan = "05 - Mei";
            } else if (kodebulan == "05") {
                nextkodebulan = "06";
                nextbulan = "06 - Juni";
            } else if (kodebulan == "06") {
                nextkodebulan = "07";
                nextbulan = "07 - Juli";
            } else if (kodebulan == "07") {
                nextkodebulan = "08";
                nextbulan = "08 - Agustus";
            } else if (kodebulan == "08") {
                nextkodebulan = "09";
                nextbulan = "09 - September";
            } else if (kodebulan == "09") {
                nextkodebulan = "10";
                nextbulan = "10 - Oktober";
            } else if (kodebulan == "10") {
                nextkodebulan = "11";
                nextbulan = "11 - November";
            } else if (kodebulan == "11") {
                nextkodebulan = "12";
                nextbulan = "12 - Desember";
            } else if (kodebulan == "12") {
                nextkodebulan = "-";
                nextbulan = "Sudah Tutup Buku";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            // menentukan bulan by tgl tutup
            if (tgltutup == "null" || tgltutup == null) {
                opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';
                kodeTutupMax = kodebulan;
                bulanTutupMax = bulan;
            } else {
                opt = '<option value="' + nextkodebulan + '" >' + nextbulan + '</option>';
                kodeTutupMax = nextkodebulan;
                bulanTutupMax = nextbulan;
            }

        } else {
            opt = '<option value="01" >01 - Januari</option>';
        }

        $("#bulan").html(opt);
    });
}

function getKodeTutup() {

    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/bku/json/getKodeTutup", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan, tgltutup, nextkodebulan, nextbulan;
        var opt;
        if (banyak > 0) {
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];
            tgltutup = result.aData[0]['kodeTglTutup'];
            if (kodebulan == "01") {
                nextkodebulan = "02";
                nextbulan = "02 - Februari";
            } else if (kodebulan == "02") {
                nextkodebulan = "03";
                nextbulan = "03 - Maret";
            } else if (kodebulan == "03") {
                nextkodebulan = "04";
                nextbulan = "04 - April";
            } else if (kodebulan == "04") {
                nextkodebulan = "05";
                nextbulan = "05 - Mei";
            } else if (kodebulan == "05") {
                nextkodebulan = "06";
                nextbulan = "06 - Juni";
            } else if (kodebulan == "06") {
                nextkodebulan = "07";
                nextbulan = "07 - Juli";
            } else if (kodebulan == "07") {
                nextkodebulan = "08";
                nextbulan = "08 - Agustus";
            } else if (kodebulan == "08") {
                nextkodebulan = "09";
                nextbulan = "09 - September";
            } else if (kodebulan == "09") {
                nextkodebulan = "10";
                nextbulan = "10 - Oktober";
            } else if (kodebulan == "10") {
                nextkodebulan = "11";
                nextbulan = "11 - November";
            } else if (kodebulan == "11") {
                nextkodebulan = "12";
                nextbulan = "12 - Desember";
            } else if (kodebulan == "12") {
                nextkodebulan = "-";
                nextbulan = "Sudah Tutup Buku";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            // menentukan bulan by tgl tutup
            if (tgltutup == "null" || tgltutup == null) {
                opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';
            } else {
                opt = '<option value="' + nextkodebulan + '" >' + nextbulan + '</option>';
            }

            $("#bulan").html(opt);
        } else { // jika awal tahun, belum ada yang insert data
            /*if (banyakTutupMax > 0) {
             opt = '<option value="' + kodeTutupMax + '" >' + bulanTutupMax + '</option>';
             } else {
             opt = '<option value="01" >01 - Januari</option>';
             }*/
            getKodeTutupMax();
        }

        // $("#bulan").html(opt);
    });
}

function getBulanByRekap() {

    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/bku/json/getBulanByRekap", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan;
        var opt;
        if (banyak > 0) {
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];
            if (kodebulan == "13") {
                kodebulan = "";
                bulan = "Sudah Tutup Buku Akhir Tahun";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';
            $("#bulan").html(opt);
        } else { // jika awal tahun, belum ada yang insert data
            opt = '<option value="01" >01 - Januari</option>';
            $("#bulan").html(opt);
        }

    });
}

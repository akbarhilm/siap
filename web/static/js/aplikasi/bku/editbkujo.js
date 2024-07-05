$(document).ready(function() {
    $("#saldokas").val($("#nilaiMasuk").val());
    getSaldoKasJO();

});
// global variable
var banyakTutupMax, kodeTutupMax, bulanTutupMax;


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

    var sisakas = accounting.unformat($("#kassebelum").val(), ",");
    var keluar = accounting.unformat($("#totalkeluar").val(), ",");
    var kas = accounting.unformat($("#saldokas").val(), ",");

    var totalkas = sisakas + kas;

    if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || nippptk == "" || namapptk == "") {
        bootbox.alert("Pengisian Data Harus Lengkap");
    } else if (bulanTglPost !== bulan) {
        bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
    } else if (tahunTglPost !== $("#tahun").val()) {
        bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
    } else if (totalkas < keluar) {
        bootbox.alert("Total Kas (1+3) Tidak Boleh Lebih Kecil dari Total Pengeluaran (2)");
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
    
    var kas = accounting.unformat($("#saldokas").val(), ",");
    
    var varurl = getbasepath() + "/bku/json/prosesupdatebku";
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
        nilaikeluar: "0",
        nilaimasuk: kas.toString(),
        nippptk: $("#nipPptk").val(),
        namapptk: $("#namaPptk").val(),
        nobkuref: "",
        kodesumbdana: $("#kodeSumbdana").val(),
        kodetransaksi: $("#kodeTransaksi").val()
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
    $('#saldokas').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));

}

function getSaldoKasJO() {

    var nobku = $("#noBKU").val();
    var idsekolah = $("#idsekolah").val();
    var sumbdana = $("#kodeSumbdana").val();

    $.getJSON(getbasepath() + "/bku/json/getSaldoKasJO", {nobku: nobku, idsekolah: idsekolah, sumbdana: sumbdana},
    function(result) {
        var saldo = result.aData['saldoKas'];
        var keluar = result.aData['nilaiKeluar'];

        $('#kassebelum').val(accounting.formatNumber(saldo, 2, '.', ","));
        $('#totalkeluar').val(accounting.formatNumber(keluar, 2, '.', ","));

    });
}

function cekGantiKode(kode) {
    var sisakas = accounting.unformat($("#kassebelum").val(), ",");
    var keluar = accounting.unformat($("#totalkeluar").val(), ",");
    var opt;

    if (sisakas < keluar) {
        bootbox.alert("Sumber Dana Tidak Bisa Diubah Karena Sisa Kas Lebih Kecil Dari Total Pengeluaran");
        if (kode == "2") { // kondisi yang diambil nilai sesuadah dipilih, jadi kebalikan
            opt = '<option value="1" selected>1 - BOS</option>';
        } else {
            opt = '<option value="2" selected>2 - BOP</option>';
        }

        $("#kodeSumbdana").html(opt);
    }

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

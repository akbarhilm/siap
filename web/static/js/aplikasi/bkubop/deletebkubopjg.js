$(document).ready(function() {
    document.getElementById('nilaijg').readOnly = true;

    setAwal();
    //setTriwulan();
});

function setTriwulan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/bkubop/json/getTriwulanByRekap", {tahun: tahun, idsekolah: idsekolah},
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

    });
}

function setAwal() {
    var masuk = $("#nilaiMasuk").val();
    var keluar = $("#nilaiKeluar").val();

    if (masuk > 0) {
        $("#nilaijg").val(masuk);
        $("#jenisPembayaran").val("PN");

    } else if (keluar > 0) {
        $("#nilaijg").val(keluar);
        $("#jenisPembayaran").val("PG");

    }
}


function simpan() {

    var nobukti = $("#noBuktiDok").val();
    var tglDok = $("#tglDok").val();
    var bulanTglDok = tglDok.substr(3, 2);
    var tahunTglDok = tglDok.substr(6, 4);
    var filling = $("#inboxFile").val();
    var nrkPptk = $("#nrkPptk").val();
    var nippptk = $("#nipPptk").val();
    var namapptk = $("#namaPptk").val();

    var jenis = accounting.unformat($("#jenisPembayaran").val(), ",");

    if (nrkPptk == "" || nobukti == "" || tglDok == "" || filling == "" || nippptk == "" || namapptk == "") {
        bootbox.alert("Pengisian Data Harus Lengkap");
    } else if (tahunTglDok !== $("#tahun").val()) {
        bootbox.alert("Tahun Dokumen Harus Sesuai Tahun yang Login");
    } else {
        update();
    }


}

function update() {
    var tahun = $("#tahun").val();
    var idsekolah = $("#idsekolah").val();
    var fileinbox = $("#inboxFile").val();

    var nilaijg = accounting.unformat($("#nilaijg").val(), ",");
    var jenis = $("#jenisPembayaran").val();
    var nilaimasuk, nilaikeluar;

    if (jenis == "PN") {
        nilaimasuk = nilaijg.toString();
        nilaikeluar = "0";

    } else {
        nilaimasuk = "0";
        nilaikeluar = nilaijg.toString();

    }

    var varurl = getbasepath() + "/bkubop/json/prosesupdatebku";
    var dataac = [];

    var datajour = {
        idbku: $("#idBku").val(),
        idrinci: $("#idRinci").val(),
        tahun: tahun,
        idsekolah: idsekolah,
        nobkumohon: $("#noBkuMohon").val(),
        nobukti: $("#noBukti").val(),
        tgldok: $("#tglDok").val(),
        fileinbox: fileinbox,
        uraian: $("#uraian").val(),
        carabayar: $("#kodePembayaran").val(),
        nilaikeluar: nilaikeluar,
        nilaimasuk: nilaimasuk,
        nrk: $("#nrkPptk").val(),
        nippptk: $("#nipPptk").val(),
        namapptk: $("#namaPptk").val(),
        kodetransaksi: $("#kodeTransaksi").val(),
        jenispembayaran: jenis
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


function hapus() {
    var tahun = $("#tahun").val();

    bootbox.confirm("Anda akan menghapus data BKU ini, lanjutkan ? ", function(result) {
        if (result) {
            var varurl = getbasepath() + "/bkubop/json/prosesdeletebyid";
            var dataac = [];

            var datajour = {
                idbku: $("#idBku").val(),
                idrinci: $("#idRinci").val(),
                tahun: tahun,
                kodetransaksi: $("#kodeTransaksi").val(),
                idsekolah: $("#idsekolah").val(),
                nobkuref: "0"//$("#noBkuMohon").val()
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
                bootbox.alert("Data Buku Kas Umum Berhasil Dihapus");
                window.location.href = getbasepath() + "/bkubop/indexbkubop"; // ke form index
            });

        } else {
            bootbox.hideAll();
            return result;
        }
    });


}
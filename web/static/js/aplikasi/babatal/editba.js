$(document).ready(function() {
    $('#sekolah').val($('#sekolah', window.parent.document).val());
    $('#idsekolah').val($('#idSekolah', window.parent.document).val());
    $('#npsn').val($('#namaSkpd', window.parent.document).val());
    $('#kodesumbdana').val($('#kodesumbdana', window.parent.document).val());
    var triwulan = $('#triwulan', window.parent.document).val();
    $('#triwulan').val("Triwulan " + triwulan);
    var kodetransaksi = $('#kodetransaksi', window.parent.document).val();
    var transaksi;
    switch (kodetransaksi.substr(0, 1)) {
        case "J":
            transaksi = "SPJ";
            break;
        case "P":
            transaksi = "Pajak";
            break;
        case "S":
            transaksi = "Setoran Kas";
            break;
        case "R":
            transaksi = "Pendapatan Lain-lain";
            break;
        default:
            transaksi = "Jenis Transaksi Salah"
            break;
    }
    $('#kodetransaksi').val(kodetransaksi);
    $('#transaksi').val(transaksi);
    var index = $('#index', window.parent.document).val();
    switch (kodetransaksi.substr(0, 1)) {
        case "J":
        case "R":
        case "S":
            $('#nomohon').val($('#noBkuMohon' + index, window.parent.document).val());
            var nilai = $('#nilai' + index, window.parent.document).val();
            break;
        case "P":
            $('#nomohon').val($('#noBkuMohonpajak' + index, window.parent.document).val());
            var nilai = $('#nilaipajak' + index, window.parent.document).val()
            break;
        default:
            break;
    }
    $('#nilaiparent').val(nilai);
    setNoBa();
    var tanggal = $('#tanggal').val();
    var dd = tanggal.substr(0, 2);
    var mm = tanggal.substr(3, 2);
    var yyyy = tanggal.substr(6, 4);
    $('#tanggal').val(dd + "/" + mm + "/" + yyyy);
});
function setNoBa() {
    if ($('#tanggal').val() != '' && $('#tanggal').val() != null) {
        getFormattedNoBa();
    } else {
        bootbox.alert("Isi Tanggal Berita Acara Terlebih Dahulu");
    }
}
function setformatpengeluaran(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilai').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function check() {
    var tanggal = $("#tanggal").val();
    var uraian = $("#uraian").val();

    if (tanggal == "" || uraian == "") {
        bootbox.alert("Pengisian Data Harus Lengkap");
    } else {
        simpan();
    }
}


function getFormattedNoBa() {
    var tanggal = $("#tanggal").val();
    var kodesumbdana = $("#kodesumbdana").val();
    var idsekolah = $("#idsekolah").val();
    var npsn = $("#npsn").val();
    var noBeritaAcara = '000000' + $("#noBa").val();
    var hasil = noBeritaAcara.substr(noBeritaAcara.length - 6) + '/' + (kodesumbdana == '1' ? 'BOS' : 'BOP') + '/' + npsn + '/' + getRomawiFromBulanCoy(tanggal.substr(3, 2)) + '/' + tanggal.substr(6, 4)
    $('#noBeritaAcaraFormatted').val(hasil);
    console.log("INI TANGGAL " + $("#tanggal").val());
}
function simpan() {
    var idBa = $("#idBa").val();
    var tanggal = $("#tanggal").val();
    var uraian = $("#uraian").val();
    var noBeritaAcaraFormatted = $("#noBeritaAcaraFormatted").val();

    var varurl = getbasepath() + "/babtl/json/prosesedit";
    var dataac = [];
    var datajour = {
        tanggal: tanggal,
        idba: idBa,
        uraian: uraian,
        noBeritaAcaraFormatted: noBeritaAcaraFormatted
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
        bootbox.alert("Data Berita Acara Berhasil Diubah");
        $('#divButton').hide();
        parent.cek();
    });
}
function hapus() {
    var idBa = $("#idBa").val();

    bootbox.confirm("Anda akan menghapus data BA ini, lanjutkan ? ", function(result) {
        if (result) {
            var varurl = getbasepath() + "/babtl/json/proseshapus";
            var dataac = [];
            var datajour = {
                idba: idBa
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
                $('#divButton').hide();
                parent.cek();
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}
function close() {
    parent.$.fancybox.close();
}
function getRomawiFromBulanCoy(datamentah) {
    switch (datamentah) {
        case '01':
            return "I";
        case '02':
            return "II";
        case '03':
            return "III";
        case '04':
            return "IV";
        case '05':
            return "V";
        case '06':
            return "VI";
        case '07':
            return "VII";
        case '08':
            return "VIII";
        case '09':
            return "IX";
        case '10':
            return "X";
        case '11':
            return "XI";
        case '12':
            return "XII";
        default :
            return null
    }
}

function cetak() {
    var idsekolah = $('#idsekolah').val();
    var npsn = $('#npsn').val();
    var idba = $("#idBa").val();
    var kodesumbdana = $('#kodesumbdana').val();

    window.location.href = getbasepath() + "/babtl/json/prosescetak?idsekolah=" + idsekolah + "&npsn=" + npsn + "&idba=" + idba + "&kodesumbdana=" + kodesumbdana;
}
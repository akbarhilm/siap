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
            if (kodetransaksi == 'JJ') {
                $('#nomohon').val($('#noBkuMohon' + index, window.parent.document).val());
                var nilai = $('#nilai' + index, window.parent.document).val();
                break;
            }
        case "R":
        case "S":
            $('#nomohon').val($('#noBkuMohon' + index, window.parent.document).val());
            var nilai = $('#nilaikeluar' + index, window.parent.document).val();
            break;
        case "P":
            $('#nomohon').val($('#noBkuMohonpajak' + index, window.parent.document).val());
            var nilai = $('#nilaipajak' + index, window.parent.document).val()
            break;
        default:
            break;
    }
    $('#nilaiparent').val(nilai);
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
    var noBeritaAcara = $("#noBeritaAcara").val();
    var nilaiparent = $("#nilaiparent").val();
    var uraian = $("#uraian").val();
    var nilaiunformat = accounting.unformat($("#nilai").val(), ",");

    //console.log("nilaiparent = " + nilaiparent);
    //console.log("nilaiunformat = " + nilaiunformat);
    if (tanggal == "" || noBeritaAcara == "" || $("#nilai").val() == "" || uraian == "") {
        bootbox.alert("Pengisian Data Harus Lengkap");
    } else if (nilaiparent != nilaiunformat) {
        bootbox.alert("Nilai yang dimasukan harus sama dengan nilai yang akan dibatalkan");
    } else {
        simpan();
    }
}


function getFormattedNoBa() {
    var tanggal = $("#tanggal").val();
    var kodesumbdana = $("#kodesumbdana").val();
    var idsekolah = $("#idsekolah").val();
    var npsn = $("#npsn").val();
    $.getJSON(getbasepath() + "/babtl/json/getformattednoba", {tanggal: tanggal,
        kodesumbdana: kodesumbdana, idsekolah: idsekolah},
    function(result) {
        $('#noBeritaAcara').val(result);
        var unformatted = '000000' + result;
        var hasil = unformatted.substr(unformatted.length - 6) + '/' + (kodesumbdana == '1' ? 'BOS' : 'BOP') + '/' + npsn + '/' + getRomawiFromBulanCoy(tanggal.substr(3, 2)) + '/' + tanggal.substr(6, 4)
        $('#noBeritaAcaraFormatted').val(hasil);
    });
}
function simpan() {
    var tanggal = $("#tanggal").val();
    var tahun = $("#tahun").val();
    var triwulan = $("#triwulan").val().substr(9, 1);
    var nomohon = $("#nomohon").val();
    var kodesumbdana = $("#kodesumbdana").val();
    var kodetransaksi = $("#kodetransaksi").val();
    var idsekolah = $("#idsekolah").val();
    var noBeritaAcara = $("#noBeritaAcara").val();
    var noBeritaAcaraFormatted = $("#noBeritaAcaraFormatted").val();
    var uraian = $("#uraian").val();
    var nilaiunformat = accounting.unformat($("#nilai").val(), ",");

    var varurl = getbasepath() + "/babtl/json/prosessimpan";
    var dataac = [];
    var datajour = {
        tahun: tahun,
        idsekolah: idsekolah,
        kodetransaksi: kodetransaksi,
        tanggal: tanggal,
        nomohon: nomohon,
        noBeritaAcara: noBeritaAcara,
        noBeritaAcaraFormatted: noBeritaAcaraFormatted,
        nilai: nilaiunformat.toString(),
        uraian: uraian,
        triwulan: triwulan,
        kodesumbdana: kodesumbdana
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
        bootbox.alert("Data Berita Acara Berhasil Disimpan");
        $('#divButton').hide();
        parent.cek();
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
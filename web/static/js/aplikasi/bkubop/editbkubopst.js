
$(document).ready(function() {

    setAwal();
    //setTriwulan();

});

function setAwal() {
    var masuk = $("#nilaiMasuk").val();
    var keluar = $("#nilaiKeluar").val();

    $("#nilaijg").val(keluar);

}

function getData() {
    var nrk = $("#nrkPptk").val().substr(0, 6);
    var user = 'sipkdprod';
    var pass = 'sipkdprod!!';
//    var user = 'sipkddev';
//    var pass = 'sipkddev';

    if ((nrk != "")) {
//        $.getJSON("http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD", {nrk: nrk},
//        function(data) {
//            if (data.results[0] != null) {
//                $("#namaPptk").val(data.results[0]['NAMA']);
//                $("#nipPptk").val(data.results[0]['NIP18']);
//            } else {
//                bootbox.alert("Data tidak ada");
//            }
//        }
//        );

        var varurl = getbasepath() + "/bkubos/json/getpegawai";
//        var varurl = "https://cors-anywhere.herokuapp.com/http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegSIPKD";
//        var varurl = "https://cors-anywhere.herokuapp.com/http://soadki.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD";
//        var varurl = "http://" + user + ":" + pass + "@soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegSIPKD";
//        var varurl = "http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegSIPKD";
//        var varurl = "http://soadki.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD";
        var dataac = [];
        console.log("NRK: " + nrk);
        var datajour = {
            nrk: nrk
        };
        dataac = datajour;
//        console.log("DATAAC: " + JSON.stringify(dataac));
//
        $.ajax({
            url: varurl,
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(dataac),
            contentType: 'text/plain; charset=utf-8',
            headers: {
                'Accept': 'application/json, text/javascript',
                'Content-Type': 'application/json',
            },
            success: function(result) {
                if (result != null) {
                    $("#namaPptk").val(result['NAMA']);
                    $("#nipPptk").val(result['NIP18']);
                } else {
                    bootbox.alert("Data tidak ada");
                }
            },
            error: function() {
                bootbox.alert('boo!');
            },
        });
    }
}
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

function simpan() {

    var nobukti = $("#noBuktiDok").val();
    var tglDok = $("#tglDok").val();
    var bulanTglDok = tglDok.substr(3, 2);
    var tahunTglDok = tglDok.substr(6, 4);
    var filling = $("#inboxFile").val();
    var nrkPptk = $("#nrkPptk").val();
    var nippptk = $("#nipPptk").val();
    var namapptk = $("#namaPptk").val();

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
    var nilaimasuk, nilaikeluar;

    nilaimasuk = "0";
    nilaikeluar = nilaijg.toString();

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
        triwulan: $("#triwulan").val(),
        nobkuref: "0"
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
                hapusba();
                bootbox.alert("Data Buku Kas Umum Berhasil Dihapus");
                window.location.href = getbasepath() + "/bkubop/indexbkubop"; // ke form index
            });

        } else {
            bootbox.hideAll();
            return result;
        }
    });


}
function hapusba() {
    var tahun = $("#tahun").val();
    var idsekolah = $("#idsekolah").val();
    var nomohon = $('#noBkuMohon').val();
    var triwulan = $('#triwulan').val();

    var varurl = getbasepath() + "/babtl/json/proseshapusbymohon";
    var dataac = [];
    var datajour = {
        tahun: tahun,
        idsekolah: idsekolah,
        nomohon: nomohon,
        triwulan: triwulan,
        sumbdana: '2'
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
    });
}
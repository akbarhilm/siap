$(document).ready(function () {
    convertTanggal();
});
function cekSimpan(jenis) {

    if ($("#tglLibur").val() != "" && $("#uraian").val() != "") {
        if (jenis == 1) {
            simpan();
        } else {
            ubah();
        }
    } else {
        bootbox.alert("Pengisian Data Harus Lengkap");
    }
}

function convertTanggal() {
    if ($("#edit").val() == "true") {
        var dd, mm, yy;
        yy = $("#waktu").val().substr(0, 4);
        mm = $("#waktu").val().substr(4, 2);
        dd = $("#waktu").val().substr(6, 2);
        $("#tglLibur").val(dd + "/" + mm + "/" + yy);
    }
}
function formatTanggal(tanggal) {
    console.log(tanggal); // 04/10/2018
    var dd, mm, yyyy;
    yyyy = tanggal.substr(6, 4);
    mm = tanggal.substr(3, 2);
    dd = tanggal.substr(0, 2);
    return yyyy + mm + dd;
}

function simpan() {
    var varurl = getbasepath() + "/cutoff/json/prosestambahlibur";
    var dataac = [];

    var datajour = {
        tanggal: formatTanggal($("#tglLibur").val()),
        uraian: $("#uraian").val()
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
    }).always(function (data) {
        bootbox.alert("Hari Libur Berhasil Ditambah");
        window.location.href = getbasepath() + "/cutoff/index"; // ke form index

    });
}
function hapus() {
    bootbox.confirm("Anda akan menghapus hari libur ini, lanjutkan ? ", function (result) {
        if (result) {

            var varurl = getbasepath() + "/cutoff/json/proseshapuslibur";
            var dataac = [];

            var datajour = {
                tanggal: formatTanggal($("#tglLibur").val()),
                uraian: $("#keterangan").val(),
                id: $("#id").val()
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
            }).always(function (data) {
                bootbox.alert("Hari Libur Berhasil Dihapus");
                window.location.href = getbasepath() + "/cutoff/index"; // ke form index
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });
}
function ubah() {
    console.log()
    var varurl = getbasepath() + "/cutoff/json/prosesubahlibur";
    var dataac = [];

    var datajour = {
        tanggal: formatTanggal($("#tglLibur").val()),
        uraian: $("#keterangan").val(),
        id: $("#id").val()
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
    }).always(function (data) {
        bootbox.alert("Hari Libur Berhasil Ditambah");
        window.location.href = getbasepath() + "/cutoff/index"; // ke form index
    });

}
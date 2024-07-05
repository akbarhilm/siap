$(document).ready(function() {
    $("#proses").prop('disabled', true);
    cek();
});

function cek() {
    cekDuplikat();
    cekGiat();
    cekGiatRinci();
//    cekDuplikat2();
//    cekGiat2();
//    cekGiatRinci2();
}
function getBudget() {
    if ($("#modul").val() == '') {
        $("#rcdcountbudget").val('');
        $("#totalbudget").val('');
        $("#rcdcountkonversi").val('');
        $("#totalkonversi").val('');
        $("#proses").prop('disabled', true);
        console.log("Disable button");
    }

    var modul = $("#modul").val();

    $.getJSON(getbasepath() + "/bku/indexkonversirkas/json/getBudget", {kode: modul},
    function(result) {
        var count1, vsum1, count2, vsum2;
        var dupe = $("#dupe").val();
        var giat = $("#giat").val();
        var rinci1 = $("#giatr1").val();
        var rinci2 = $("#giatr2").val();

        count1 = result.aData[0]['banyak'];
        vsum1 = result.aData[0]['pagu'];
        count2 = result.aData[1]['banyak'];
        vsum2 = result.aData[1]['pagu'];

        if (count1 > 0) {
            if (modul == "kgt") {
                if (dupe != 0) {
                    bootbox.alert("Ada data yang duplikat sebanyak " + dupe + " data");
                } else {
                    $("#proses").prop('disabled', false);
                    console.log("Enable button");
                }
            } else if (modul == "kgtk") {
//                console.log("ABCDE " + kegiatan1);
//                console.log("ABCDE " + kegiatan2);
                if (giat > 0) {
//                if (kegiatan2 == 0) {
                    $("#proses").prop('disabled', false);
                    console.log("Enable button");
                } else {
                    bootbox.alert("Lakukan proses konversi kegiatan terlebih dahulu");
                }
            } else if (modul == "kgta") {
//                console.log("ABCDE " + rinci1);
//                console.log("ABCDE " + rinci2);
                if (rinci1 > 0 && rinci2 > 0) {
//                if (rinci2 == 0) {
                    $("#proses").prop('disabled', false);
                    console.log("Enable button");
                } else {
                    bootbox.alert("Lakukan proses konversi kegiatan rinci terlebih dahulu");
                }
            }
        }
        $("#rcdcountbudget").val(count1);
        $("#totalbudget").val(accounting.formatNumber(vsum1));
        $("#rcdcountkonversi").val(count2);
        $("#totalkonversi").val(accounting.formatNumber(vsum2));
    });
}

function cekDuplikat() {
    var tahun = $("tahun").val();
    $.getJSON(getbasepath() + "/bku/indexkonversirkas/json/cekDuplicate", {tahun: tahun},
    function(result) {
        var count = result.aData;
        console.log("Ngambil data duplikat " + count);
        $("#dupe").val(count);
    });
}

function cekGiat() {
    var tahun = $("tahun").val();
    $.getJSON(getbasepath() + "/bku/indexkonversirkas/json/cekGiat", {tahun: tahun},
    function(result) {
        var count = result.aData;
        console.log("Ngambil data giat " + count);

        $("#giat").val(count);
    });
}

function cekGiatRinci() {
    var tahun = $("tahun").val();
    $.getJSON(getbasepath() + "/bku/indexkonversirkas/json/cekGiatRinci", {tahun: tahun},
    function(result) {
        var count1 = result.aData['banyak1'];
        var count2 = result.aData['banyak2'];
        console.log("Ngambil data giat rinci " + count1 + " " + count2);

        $("#giatr1").val(count1);
        $("#giatr2").val(count2);
    });
}

function prosesKonversi() {
    var varurl = getbasepath() + "/bku/indexkonversirkas/json/prosesKonversi";
    var dataac = [];
    var data = {kode: $("#modul").val()}
    dataac = data;
    $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        data: JSON.stringify(dataac)}).always(function() {

        bootbox.alert("Berhasil");
        cek();
        getBudget();
    });
}
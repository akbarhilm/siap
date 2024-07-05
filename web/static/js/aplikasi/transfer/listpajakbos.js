$(document).ready(function() {
    getDataWP();

});

var ketbaris, idrinci, nilaispj, nobkuref, idblrinci, namasubkeg, ketrinci, tglpost;
var idbas, kodeakun, idkomponen, nodok, nrk, nama, nip, idkeg, kodekeg, triwulan, idspj;
var fileinbox;
var rowpajak = 0;


function ceksimpan() {
    var total = accounting.unformat($("#sumpajak").val(), ",");

    var mp1 = $("#masapajak1").val();
    var mp2 = $("#masapajak2").val();
    var npwp = $("#npwp").val();
    var namawp = $("#namarekanan").val();


    if (total > nilaispj) {
        bootbox.alert("Total Pajak Tidak Boleh Lebih Besar Dari Nilai SPJ");
    } else if (mp1 == "-" || mp2 == "-") {
        bootbox.alert("Pilih Masa Pajak Terlebih Dulu");
    } else if (npwp == "" || namawp == "") {
        bootbox.alert("Data NPWP dan Wajib Pajak Tidak Boleh Kosong");
    } else {
        simpanPajak();
    }

}

function grid() {
    rowpajak = 0
    $("#spjtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#spjtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkutf/json/listpajakspjbos",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
                //formatnumberonkeyup();
            }, "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idsekolah", value: $('#idsekolah').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "nobkuref", value: nobkuref},
                {name: "idblrinci", value: idblrinci}
                );

            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;

                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiPajakSpj'] + "' onkeyup='hitungtotal()' />";
                var hitung, keterangan;

                var kodetrans = "<input type='hidden' id='kodetrans" + index + "' value='" + aData['keterangan'] + "' />";

                if (aData['keterangan'] == "P1") { // PPh 21
                    hitung = "";
                    keterangan = "PPH PS 21";

                } else if (aData['keterangan'] == "P2") { // PPh 22
                    hitung = "<select id='ppnP2' name='ppnP2' onchange=hitungP2(this.value," + index + ") ><option value='-'>Pilih</option><option value='0'>NON PPN</option><option value='1'>PPN</option></select>";
                    keterangan = "PPH PS 22";

                } else if (aData['keterangan'] == "P3") { // PPh 23
                    hitung = "<select id='ppnP3' name='ppnP3' onchange=hitungP3(" + index + ") ><option value='-'>Pilih</option><option value='0'>NON PPN</option><option value='1'>PPN</option></select> &nbsp <select id='persenP3' name='persenP3' onchange=hitungP3(" + index + ") ><option value='-'>Pilih</option><option value='2'>2%</option></select>";
                    keterangan = "PPH PS 23 JASA I";

                } else if (aData['keterangan'] == "P5") { // PPN
                    hitung = "<select id='persenP5' name='persenP5' onchange=hitungP5(this.value," + index + ")><option value='-'>Pilih</option><option value='1'>1%</option><option value='10'>10%</option></select>";
                    keterangan = "PPN";

                }

                rowpajak = rowpajak + 1;
                $('td:eq(0)', nRow).html(index + kodetrans);
                $('td:eq(1)', nRow).html(keterangan);
                $('td:eq(2)', nRow).html(hitung);
                $('td:eq(3)', nRow).html(nilaiinput);
                // $('td:eq(4)', nRow).html(textidkeg+textbeban + textket + textkode + textnama + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBku", "bSortable": false, sClass: "center"},
                {"mDataProp": "keterangan", "bSortable": false, sClass: "left"},
                {"mDataProp": "idBkuRinci", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiPajakSpj", "bSortable": true, sClass: "right"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    //hitungtotal();

}

function hitungtotal() {

    var total = 0;

    for (var a = 1; a <= rowpajak; a++) {
        total += parseFloat(accounting.unformat($("#nilaiinput" + a).val(), ","));
    }

    $("#sumpajak").val(accounting.formatNumber(total, 2, '.', ","));

}

function hitungP2(ppn, index) {
    var hasil;

    if (ppn == 0) { // NON PPN
        hasil = (nilaispj) * (1.5 / 100);
    } else if (ppn == 1) {
        hasil = (nilaispj / 1.1) * (1.5 / 100);
    } else {
        hasil = 0;
    }

    $('#nilaiinput' + index).val(accounting.formatNumber(hasil, 2, '.', ","));
    hitungtotal();

}

function hitungP3(index) {
    var hasil;
    var persen = $('#persenP3').val();
    var ppn = $('#ppnP3').val();

    if (ppn == "-" || persen == "-") {
        hasil = 0;

    } else {
        var pengali = ppn == 0 ? 1 : 1.1;
        //console.log("pengali = "+pengali);
        hasil = (nilaispj / pengali) * (persen / 100);
    }

    $('#nilaiinput' + index).val(accounting.formatNumber(hasil, 2, '.', ","));
    hitungtotal();

}

function hitungP4(index) {
    var hasil;
    var persen = $('#persenP4').val();
    var ppn = $('#ppnP4').val();

    if (ppn == "-" || persen == "-") {
        hasil = 0;

    } else {
        var pengali = ppn == 0 ? 1 : 1.1;
        //console.log("pengali = "+pengali);
        hasil = (nilaispj / pengali) * (persen / 100);
    }

    $('#nilaiinput' + index).val(accounting.formatNumber(hasil, 2, '.', ","));
    hitungtotal();

}

function hitungP5(persen, index) {
    var hasil;

    if (persen == 1) { // 1%
        hasil = (nilaispj / 1.01) * (1 / 100);
    } else if (persen == 10) { //10%
        hasil = (nilaispj / 1.1) * (10 / 100);
    } else {
        hasil = 0;
    }

    $('#nilaiinput' + index).val(accounting.formatNumber(hasil, 2, '.', ","));
    hitungtotal();

}

function simpanPajak() {
    var tahun = $("#tahun").val();
    var bkututup, bkustatus;

    var masapajak = $("#masapajak1").val().toString() + $("#masapajak2").val().toString();
    bkututup = "1";
    bkustatus = "3";

    var dd, mm, yy, tanggal;
    dd = tglpost.substr(0, 2);
    mm = tglpost.substr(3, 2);
    yy = tglpost.substring(6);
    tanggal = yy + mm + dd;

    var varurl = getbasepath() + "/bkutf/json/prosessimpanpajakbos";
    var dataac = [];
    var nilailist = [];
    var i;
    var banyak = 0;

    for (i = 0; i < 4; i++) { // list
        var id = i + 1;
        var nilai = Math.round(parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ","))); // pembulatan ke atas
        var uraian;
        console.log("INI TEST KE " + id + " NILAINYA ADALAH " + nilai)
        if (nilai > 0) {
            if ($('#kodetrans' + id).val() == "P1") {
                uraian = "Diterima Pajak PPH PS 21 atas SPJ " + nodok;
            } else if ($('#kodetrans' + id).val() == "P2") {
                uraian = "Diterima Pajak PPH PS 22 atas SPJ " + nodok;
            } else if ($('#kodetrans' + id).val() == "P3") {
                uraian = "Diterima Pajak PPH PS 23 JASA I atas SPJ " + nodok;
            } else if ($('#kodetrans' + id).val() == "P5") {
                uraian = "Diterima Pajak PPN atas SPJ " + nodok;
            }

            var pararr = {
                nilaimasuk: nilai,
                nilaikeluar: "0",
                kodetransaksi: $('#kodetrans' + id).val(),
                uraian: uraian
            };

            nilailist[banyak] = pararr;
            banyak = banyak + 1;
        }

    }

    var datajour = {
        idspj: idspj,
        tahun: $("#tahun").val(),
        idsekolah: $("#idsekolah").val(),
        idbas: idbas,
        kodeakun: kodeakun,
        idkomponen: idkomponen,
        idblrinci: idblrinci,
        nobukti: nodok,
        tgldok: $("#tglDok").val(),
        jenis: "3",
        beban: "TU",
        fileinbox: fileinbox,
        nrk: nrk,
        namapptk: nama,
        nippptk: nip,
        carabayar: "2",
        idkegiatan: idkeg,
        kodekeg: kodekeg,
        namarekan: $("#namarekanan").val(), // nama WP
        npwp: $("#npwp").val(),
        masapajak: masapajak,
        tahunpajak: $("#tahunpajak").val(),
        triwulan: triwulan,
        nobkuref: nobkuref,
        bkututup: bkututup,
        bkustatus: bkustatus,
        tglposting: tanggal,
        nilailist: nilailist

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

        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        $('#triger', window.parent.document).val($("#sumpajak").val()).change();
    });


}

function getDataWP() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/bkubos/json/getDataWP", {tahun: tahun, idsekolah: idsekolah},
    function(result) {

        if (result.aData !== null) { // jika ada datanya
            var npwp = result.aData['npwp'];
            var nama = result.aData['namaRekan'];

            $('#npwp').val(npwp);
            $('#namarekanan').val(nama);

        } else {
            bootbox.alert("Data Referensi NPWP Sekolah Tidak Tersedia, Silahkan Isi Data Referensi Terlebih Dulu.");
        }

    });

}

function setMasaPajak2() {
    var bulanmp1 = $('#masapajak1').val();

    $('#masapajak2').val(bulanmp1);
}

function getDataPajakSpj(idblrinci, nobkuref) {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/bkutf/json/getDataPajakSpjBos", {tahun: tahun, idsekolah: idsekolah, nobkuref: nobkuref, idblrinci: idblrinci},
    function(result) {

        var saldo = result.aData['nilaiPajak'];
        var masapajak = result.aData['masaPajak'];

        //$('#sumpajak').val(accounting.formatNumber(saldo, 2, '.', ","));
        $('#sumpajak').val(saldo);

        $('#tahunpajak').val(result.aData['tahunPajak']);

        var mp1 = masapajak.substr(0, 2);
        var mp2 = masapajak.substr(2, 2);

        $('#masapajak1').val(mp1);
        $('#masapajak2').val(mp2);

    });

}

function cek() {
    $('#triger', window.parent.document).val($("#sumpajak").val()).change();
}
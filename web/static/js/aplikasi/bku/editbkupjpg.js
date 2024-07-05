$(document).ready(function() {
    gridpajak();
});
// global variable
var jumbarispajak = 0;
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
    var idkeg = $("#idKegiatan").val();


    if ((jumbarispajak > 0) && ($("#namaakun1").val() !== "")) {
        if (tglPost == "" || nobukti == "" || tglDok == "" || idkeg == "" || filling == "" || nippptk == "" || namapptk == "") {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else if (bulanTglPost !== bulan) {
            bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
        } else if (tahunTglPost !== $("#tahun").val()) {
            bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
        } else {
            update();
        }
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

    var varurl = getbasepath() + "/bku/json/prosesupdatebkupjpg";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < jumbarispajak; i++) { // list spj
        var id = i + 1;

        var pararr = {
            nilaimasuk: "0",
            nilaikeluar: $("#nilaiPajak" + id).val(),
            idbas: $("#idbas" + id).val(),
            kodeakun: $("#kodeakun" + id).val()
        };
        nilailist[i] = pararr;
    }

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
        nipPPTK: $("#nipPptk").val(),
        namaPPTK: $("#namaPptk").val(),
        idkegiatan : $("#idKegiatan").val(),
        kodekegiatan : $("#kodeKeg").val(),
        nobkuref: $("#noBkuRef").val(),
        kodetransaksi: $("#kodeTransaksi").val(),
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
        bootbox.alert("Data Buku Kas Umum Berhasil Diubah");

    });

}

function setKegiatan() {

    $("#ketKegiatan").val($("#keteranganKegPop").val());
    $("#idKegiatan").val($("#idKegpop").val());
    $("#kodeKeg").val($("#kodeKegpop").val());

    gridpajak();
}

function gridpajak() {
    jumbarispajak = 0;
    if (typeof myTablePj == 'undefined') {
        myTablePj = $('#pajaktable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 100,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listpnpajak",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "nobku", value: $("#noBkuRef").val()},
                {name: "jenistrans", value: $("#kodeTransaksi").val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

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
                jumbarispajak = jumbarispajak + 1;

                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var nilaipajak = "<input type='text' name='nilaiPajak" + index + "' id='nilaiPajak" + index + "'  class='inputmoney'  value='" + aData['nilaiPajak'] + "' readOnly='true' />";
                var namaakun = "<textarea id='akunnama" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['namaakun'] + "</textarea>";
                var textnamaakun = "<input type='hidden' id='namaakun" + index + "' value='" + aData['namaakun'] + "' />";
                var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";

                //noBKU, kodeakun, namaakun, nilaiPajak 
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(namaakun + textnamaakun + textkodeakun + idbas);
                $('td:eq(4)', nRow).html(nilaipajak);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiPajak", "bSortable": false, sClass: "right"}
            ]
        });
    }
    else
    {
        myTablePj.fnClearTable(0);
        myTablePj.fnDraw();
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

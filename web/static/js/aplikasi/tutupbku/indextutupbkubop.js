/*
 *
 *
 *
 */

$(document).ready(function() {
    //setpanel(1);
    setComboTriwulan();
    //document.getElementById("panelTombol").style.display = "block"; //menampilkan panel tombol
    cekProsesTutup();
});

function setTanggalAkhir() {

    var tgl = $("#triwulan").find(":selected").text();
    console.log("combo bulan isinya == " + tgl);
    var index = tgl.indexOf("BELUM DIPROSES");
    var index2 = tgl.indexOf("DRAFT DIPROSES");
    //var belum = tgl.substring(5);
    var dd, mm, yy, tanggal;

    dd = tgl.substr(5, 2);
    mm = tgl.substr(8, 2);
    yy = tgl.substr(11, 4);

    tanggal = yy + mm + dd;

    //$('#tgl2').val(tanggal);

    if (refcetak.triwulan.selectedIndex == 0 || tgl == null || tgl == "") { // untuk tampilan awal atau saat memilih "--Pilih Bulan--" semua tombol disable
        console.log("masuk if 1 " + refcetak.triwulan.selectedIndex + " == " + tgl);
        $('#btndraft').attr("disabled", true);
        $('#btnproses').attr("disabled", true);
        $('#btncetak').attr("disabled", true);
        $('#btncetakxls').attr("disabled", true);
    } else if (index != -1) { //jika belum diproses
        ///bootbox.alert("belum proses = "+tgl+belum);
        //$("#bulan").val(15); // tidak ada data
        console.log("masuk if 2 ");
        $('#btndraft').attr("disabled", false);
        $('#btnproses').attr("disabled", true);
        $('#btncetak').attr("disabled", true);
        $('#btncetakxls').attr("disabled", true);

    } else if (index2 != -1) {
        console.log("masuk if 3 ");
        $('#btndraft').attr("disabled", false);
        $('#btnproses').attr("disabled", false);
        $('#btncetak').attr("disabled", false);
        $('#btncetakxls').attr("disabled", false);
    }
    else {
        console.log("masuk else ");
        //bootbox.alert("sudah proses = "+index+" = "+tgl+belum);
        $('#btndraft').attr("disabled", true);
        $('#btnproses').attr("disabled", true);
        $('#btncetak').attr("disabled", false);
        $('#btncetakxls').attr("disabled", false);
    }

    gridbkupengeluaran();
}

function setComboTriwulan() {
    var idsekolah = $('#idsekolah').val();
    var tahun = $('#tahun').val();

    $.getJSON(getbasepath() + "/tutupbkubop/json/setComboTriwulan", {idsekolah: idsekolah, tahun: tahun},
    function(result) {
        var banyak, kode, ket;
        var opt = "<option>--Pilih Bulan--</option>"; // untuk tampilan awal combo bulan

        banyak = result.aData.length;

        if (banyak > 0) {
            if (result.aData[0]['triwulan'] > '1') {
                opt += '<option value="1" >Triwulan 1 : BELUM DIPROSES</option>';
            }
            else if (result.aData[0]['triwulan'] > '2') {
                opt += '<option value="2" >Triwulan 2 : BELUM DIPROSES</option>';
            }
            else if (result.aData[0]['triwulan'] > '3') {
                opt += '<option value="3" >Triwulan 3 : BELUM DIPROSES</option>';
            }
            else if (result.aData[0]['triwulan'] > '4') {
                opt += '<option value="4" >Triwulan 4 : BELUM DIPROSES</option>';
            }

            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['triwulan'];
                ket = result.aData[i]['uraian'];

                opt += '<option value="' + kode + '" >' + ket + '</option>';
            }
        }

        $("#triwulan").html(opt);
    });
}

function cetakjurnalxls() {
    window.location.href = getbasepath() + "/tutupbkubop/xls/bkuxls?tahun=" + $("#tahun").val()
            + "&idsekolah=" + $('#idsekolah').val() + "&triwulan="
            + $('#triwulan').val();
}

function gridbkupengeluaran() {
    var urljson = getbasepath() + "/tutupbkubop/json/gridbku";
    if (typeof myTable == 'undefined') {
        myTable = $('#bkutable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "triwulan", value: $('#triwulan').val()},
                {name: "idsekolah", value: $('#idsekolah').val()}
                /*,
                 {name: "idkegiatan", value: $('#idKegiatan').val()},
                 {name: "jenislaporan", value: $('#jenislaporan').val()}*/
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
                var nilaimasuk = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                var nilaikeluar = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");
                var nilaikas = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");

                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(nilaimasuk);
                $('td:eq(6)', nRow).html(nilaikeluar);
                $('td:eq(7)', nRow).html(nilaikas);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "tglPosting", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglPosting", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": true, sClass: "left"},
                {"mDataProp": "uraianBukti", "bSortable": true, sClass: "left"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "kanan"}

            ]
        });

    }
    else {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


function cetakbkukeluar() {

    var tahun = $("#tahun").val();

    //var ccc = $("#tgl1").val();
    //var ddd = $("#tgl2").val();
    //var kegiatan = "";
    //var kodlap = "01";
    var idsekolah = $("#idsekolah").val();
    //var nama = $("#sekolah").val();
    var triwulan = $("#triwulan").val();
    //var qqq = ccc.split("/").join("-");
    //console.log("tahun = "+aaa);  console.log("tgl awal = "+ccc);  console.log("tgl akhir = "+ddd);  console.log("id kegiatan = "+fff);  console.log("jenis = "+ggg); console.log("idskpd = "+hhh);
    //var eee = aaa + "-" + "REALISASI_BKU_PERKEGIATAN" + ".pdf";

    window.location.href = getbasepath() + "/tutupbkubop/json/prosescetak?tahun=" + tahun + "&triwulan=" + triwulan + "&idsekolah=" + idsekolah;

}



function cekProsesTutup() {

    var index = refcetak.triwulan.selectedIndex;
    console.log("banyak list triwulan = " + index);
    if (index > 1) { // untuk item pertama ga usah di cek // Last Edited : untuk item index ke 2, karena ketambahan "--Pilih Bulan--" di index 1
        var indexSebelum = index - 1; // diinisialisasi setelah cek index paling awal agar sebelum <> -1

        var teksSebelum = document.getElementById("triwulan").options.item(indexSebelum).text;
        var teksLenSebelum = String(teksSebelum).length;
        var keteranganSebelum = String(teksSebelum).substring(teksLenSebelum, teksLenSebelum - 14); //14 = length "BELUM DIPROSES"

        var teksSaatIni = document.getElementById("triwulan").options.item(index).text;
        var teksLenSaatIni = String(teksSaatIni).length;
        var keteranganSaatIni = String(teksSaatIni).substring(teksLenSaatIni, teksLenSaatIni - 14);
        //console.log("teksSebelum Sebelum = " + teksSebelum);
        //console.log("teksLenSebelum Sebelum = " + teksLenSebelum);
        //console.log("keterangan Sebelum = " + keteranganSebelum);

        if (keteranganSebelum == "BELUM DIPROSES" || keteranganSebelum == "DRAFT DIPROSES") { // jika bulan sebelumnya belum diproses, maka bulan ini ga bisa proses // Last Edited : menambahkan kondisi keteranganSebelum == "DRAFT DIPROSES"
            /*
             * Last Edited : semua tombol disable bila bulan sebelumnya belum diproses
             */
		console.log("masuk 1");

            $('#btnproses').attr("disabled", true);
            $('#btndraft').attr("disabled", true);
            $('#btncetak').attr("disabled", true);
            $('#btncetakxls').attr("disabled", true);

        } else {
            if (keteranganSaatIni == "BELUM DIPROSES") {
		console.log("masuk 2");

                // bila bisa diproses dan keterangan bulannya "BELUM DIPROSES", maka hanya tombol draft yang aktif
                $('#btnproses').attr("disabled", true);
                $('#btndraft').attr("disabled", false);
                $('#btncetak').attr("disabled", true);
                $('#btncetakxls').attr("disabled", true);
            }
            else if (keteranganSaatIni.match("PROSES") == "PROSES") {
		console.log("masuk 3");
                // bila bisa diproses dan keterangan bulannya "DRAFT DIPROSES", maka yg aktif semua tombol
                $('#btnproses').attr("disabled", false);
                $('#btndraft').attr("disabled", false);
                $('#btncetak').attr("disabled", false);
                $('#btncetakxls').attr("disabled", false);
            }
            else {
		console.log("masuk 4");

                // bila bisa diproses dan keterangan bulannya "DRAFT DIPROSES", maka yg aktif tombol draft dan proses tutup
                $('#btnproses').attr("disabled", true);
                $('#btndraft').attr("disabled", true);
                $('#btncetak').attr("disabled", false);
                $('#btncetakxls').attr("disabled", false);
            }
        }

        gridbkupengeluaran(); // refresh tampilan grid
    } else {
        //console.log("else");
        setTanggalAkhir(); // mengecek lagi aturan dasar nya
    }

}


function cekBapKasValidasi() {
    console.log("tes baspkas validasi");
    var triwulan = $('#triwulan').val();
    var idsekolah = $('#idsekolah').val();
    var tahun = $('#tahun').val();

    $.getJSON(getbasepath() + "/tutupbkubop/json/getBapKasValidasi", {idsekolah: idsekolah, tahun: tahun, triwulan: triwulan},
    function(result) {
        //var ket = "";

        //var banyak = result.aData.length;
        if (result.aData == null) { // jika belum melakukan bapkas
            bootbox.alert("Anda Belum Melakukan BAP KAS, silahkan lakukan BAP KAS terlebih dulu.", function(result) {
                $.fancybox.close();
            });
        } else {
            var status = result.aData['statusBA'];
            if (status == '0') { // jika status bapkas 0, input bapkas
                bootbox.alert("Anda Telah Input BAP KAS, namun BELUM disetujui PA, silahkan PA melakukan persetujuan BAP KAS terlebih dulu.", function(result) {
                    $.fancybox.close();
                });
            } else if (status == '1') { // jika status bapkas 1, input bapkas, approve PA
                bootbox.alert("Anda Telah Input BAP KAS dan sudah disetujui PA, namun BELUM disetujui oleh SUDIN, silahkan melakukan persetujuan BAP KAS oleh SUDIN terlebih dulu.", function(result) {
                    $.fancybox.close();
                });
            } else { // jika status bapkas 2, input bapkas, approve PA, approve SUDIN
                console.log("status = " + result.aData['statusBA']);
                $('#nilaibapkas').val(result.aData['saldoBA']);
//                window.localStorage.setItem("nilaibapkas", result.aData['saldoBA']);

            }
        }
    });
}

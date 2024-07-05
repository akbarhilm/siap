$(document).ready(function() {
    setPanel();

});

// global variable
var jumbarisspj = 0;
var jumbarispajak = 0;
var idrow = 0;

function simpan() {
    console.log("jum baris spj = " + jumbarisspj);
    var jenistransaksi = $('#kodeTransaksi').val();
    var jenisbayar = $('#jenisPembayaran').val();
    var tglPost = $("#tglPosting").val();
    var filling = $('#fileInbox').val();
    var bulanTglPost = tglPost.substr(3, 2);
    var tahunTglPost = tglPost.substr(6, 4);
    var nobukti = $("#noBuktiDok").val();
    var tglDok = $("#tglDok").val();
    var bulan = $("#bulan").val();
    var datalengkap = true;
    var nippptk = $("#nipPptk").val();
    var namapptk = $("#namaPptk").val();
    var sisakas = accounting.unformat($("#sisakas").val(), ",");

    var idKegiatanSpj = $("#idKegiatanSpj").val();
    var nilaitotalspj = $("#totalspjhidden").val();
    var nilaitotalpajak = $("#totalpajakhidden").val();
    var nilaispjnetto = nilaitotalspj - nilaitotalpajak;

    var nilaibku = accounting.unformat($("#nilaibku").val(), ",");
    var nilaijg = accounting.unformat($("#nilaijg").val(), ",");

    var idKegiatanPj = $("#idKegiatanPj").val();

    var table3 = document.getElementById('spjtablebody');
    var rows3 = table3.rows;
    var jum3 = rows3.length;

    var banyakcek = 0;


    if (jenistransaksi == "JJ") {

        if ((jum3 > 0) && ($("#akunnama1").val() !== "")) {
            for (var a = 1; a <= jum3; a++) {
                if ($('#penanda' + a).val() == 1) {
                    banyakcek = banyakcek + 1;
                }
            }

            if (banyakcek > 0) {
                for (var a = 1; a <= jum3; a++) {
                    var nilai = parseFloat(accounting.unformat($('#nilaiinput' + a).val(), ","));

                    if ($('#penanda' + a).val() == 1 && nilai <= 0) { // validasinya ganti, nilai boleh 0 (KALO EDIT BOLEH, KL SIMPAN BARU GA BOLEH)
                        datalengkap = false;
                    }
                }
            } else if (banyakcek == 0) {
                datalengkap = false;
            }

            if (tglPost == "" || nobukti == "" || tglDok == "" || idKegiatanSpj == "" || filling == "" || nippptk == "" || namapptk == "" || datalengkap == false) {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else if (bulanTglPost !== bulan) {
                bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
            } else if (tahunTglPost !== $("#tahun").val()) {
                bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
            } else if (nilaitotalspj > sisakas) {
                bootbox.alert("Total SPJ Tidak Boleh Lebih Besar dari Sisa Kas");
            } else {
                simpanSPJ();
            }

        }

    } else if (jenistransaksi.substr(0, 1) == "P") {
        
        if ((jumbarispajak > 0) && ($("#namaakun1").val() !== "")) {
            if (tglPost == "" || nobukti == "" || tglDok == "" || idKegiatanPj == "" || filling == "" || nippptk == "" || namapptk == "") {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else if (bulanTglPost !== bulan) {
                bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
            } else if (tahunTglPost !== $("#tahun").val()) {
                bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
            } else {
                simpanPajak();
            }
        }

    } else if (jenistransaksi == "JO") {
        if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || nippptk == "" || namapptk == "" || nilaibku <= 0) {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else if (bulanTglPost !== bulan) {
            bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
        } else if (tahunTglPost !== $("#tahun").val()) {
            bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
        } else {
            simpanBku();
        }

    } else if (jenistransaksi == "ST") {
        if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || nippptk == "" || namapptk == "" || nilaibku <= 0) {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else if (bulanTglPost !== bulan) {
            bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
        } else if (tahunTglPost !== $("#tahun").val()) {
            bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
        } else if (nilaibku > sisakas) {
            bootbox.alert("Nilai Setoran Tidak Boleh Lebih Besar dari Sisa Kas");
        } else {
            simpanBku();
        }

    } else if (jenistransaksi == "JG") {
        if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || nippptk == "" || namapptk == "" || nilaijg <= 0) {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else if (bulanTglPost !== bulan) {
            bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
        } else if (tahunTglPost !== $("#tahun").val()) {
            bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
        } else {
            simpanBku();
        }
    }
}

function simpanSPJ() {
    var tahun = $("#tahun").val();
    var idsekolah = $("#idsekolah").val();
    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;

    var sumbdana = $('#sumbdana').val().substr(0, 3);
    var kode;

    if (sumbdana == "BOS") {
        kode = "1";
    } else if (sumbdana == "BOP") {
        kode = "2";
    }

    if (jumbarisspj > 0) {

        var varurl = getbasepath() + "/bku/json/prosessimpanspj";
        var dataac = [];
        var nilailist = [];
        var nilaipajak = [];
        var i, j;
        var uraianbukti;

        for (i = 0; i < jumbarisspj; i++) { // list spj
            var id = i + 1;
            uraianbukti = "Dibayar " + $('#keteranganKegPop').val() + " " + $("#namaakun" + id).val() + " - " + $("#uraian").val();

            var totalpajak = Math.round($("#pajakunformat" + id).val());
            var nilaibruto = parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ","));
            var nilainetto = nilaibruto - totalpajak;

            var pararr = {
                kodetransaksi: "JJ",
                nilaimasuk: "0",
                nilaikeluar: nilaibruto,
                idbas: $("#idbas" + id).val(),
                uraianbukti: uraianbukti.substr(0, 400),
                penanda: $("#penanda" + id).val(),
                kodeakun: $("#kodeakun" + id).val(),
                nilainetto: nilainetto,
                nilipajak: totalpajak
            };
            console.log("list pajak - i : " + i);
            nilailist[i] = pararr;
        }

        for (j = 0; j < jumbarisspj; j++) { // list pajak
            var id = j + 1;

            for (var k = 1; k <= 6; k++) {
                var row = k.toString() + id.toString();
                var nilaipajak = Math.round($('#nilaip' + row).val());//parseFloat(accounting.unformat($('#nilaip' + row).val(), ","));
                var jenistrx = "P" + k;
                //console.log("Jenis Pajak"+jenistrx);
                if (nilaipajak > 0) {
                    var kettrx = "";

                    if (jenistrx == "P1") {
                        kettrx = "Pajak Penghasilan Pasal 21 atas kegiatan ";
                    } else if (jenistrx == "P2") {
                        kettrx = "Pajak Penghasilan Pasal 22 atas kegiatan ";
                    } else if (jenistrx == "P3") {
                        kettrx = "Pajak Penghasilan Pasal 23 atas kegiatan ";
                    } else if (jenistrx == "P4") {
                        kettrx = "Pajak Penghasilan Pasal 4 ayat (2) atas kegiatan ";
                    } else if (jenistrx == "P5") {
                        kettrx = "Pajak Pertambahan Nilai atas kegiatan ";
                    } else if (jenistrx == "P6") {
                        kettrx = "Pajak Penghasilan Pasal 26 atas kegiatan ";
                    }

                    uraianbukti = "Diterima " + kettrx + $('#keteranganKegPop').val() + " " + $("#namaakun" + id).val();

                    var pararr = {
                        kodetransaksi: jenistrx,
                        nilaimasuk: nilaipajak,
                        nilaikeluar: "0",
                        idbas: $("#idbas" + id).val(),
                        uraianbukti: uraianbukti, //$("#uraian").val(),
                        penanda: "1",
                        kodeakun: $("#kodeakun" + id).val(),
                        nilainetto: "0",
                        nilipajak: "0"
                    };
                    //console.log("list pajak - i : " + i);
                    nilailist[i] = pararr;
                    i = i + 1;

                }
            }
        }

        var datajour = {
            tahun: tahun,
            idsekolah: idsekolah,
            tglposting: tanggal,
            nobukti: $("#noBuktiDok").val(),
            tgldok: $("#tglDok").val(),
            jenis: "3",
            beban: "TU",
            idkegiatan: $('#idKegiatanSpj').val(),
            kodekegiatan: $('#kodeKegSpj').val(),
            fileinbox: fileinbox,
            uraianinput: $("#uraian").val(),
            carabayar: $("#kodePembayaran").val(),
            nipPPTK: $("#nipPptk").val(),
            namaPPTK: $("#namaPptk").val(),
            kodesumbdana: kode,
            nilailist: nilailist

        }
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
            cleardata();
            clearrow();
            bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");

        });

    }
}

function simpanPajak() {
    var tahun = $("#tahun").val();
    var idsekolah = $("#idsekolah").val();
    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;

    
    if (jumbarispajak > 0) {

        var varurl = getbasepath() + "/bku/json/prosessimpanpajak";
        var dataac = [];
        var nilailist = [];
        var i, j;
        
        for (i = 0; i < jumbarispajak; i++) { // list spj
            var id = i + 1;
                
            var pararr = {
                nilaimasuk: "0",
                nilaikeluar: $("#nilaiPajak" + id).val(),
                idbas: $("#idbas" + id).val(),
                penanda: $("#penanda" + id).val(),
                kodeakun: $("#kodeakun" + id).val()
            };
            nilailist[i] = pararr;
        }

        var datajour = {
            tahun: tahun,
            idsekolah: idsekolah,
            tglposting: tanggal,
            kodetransaksi: $('#kodeTransaksi').val(),
            nobukti: $("#noBuktiDok").val(),
            tgldok: $("#tglDok").val(),
            jenis: "3",
            beban: "TU",
            idkegiatan: $('#idKegiatanPj').val(),
            kodekegiatan: $('#kodeKegPj').val(),
            fileinbox: fileinbox,
            uraianinput: $("#uraian").val(),
            carabayar: $("#kodePembayaran").val(),
            nipPPTK: $("#nipPptk").val(),
            namaPPTK: $("#namaPptk").val(),
            nobkuref: $("#nobkuref").val(),
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
            cleardata();
            clearrow();
            bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");

        });

    }
}

function simpanBku() {
    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var jenisbayar = $("#jenisPembayaran").val();
    var dd, mm, yy, tanggal;
    var uraian, nobkuref;
    var nilaimasuk, nilaikeluar;
    var nilaibku = (accounting.unformat($("#nilaibku").val(), ",")).toString();
    var nilaijg = (accounting.unformat($("#nilaijg").val(), ",")).toString();
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)

    var sumbdana = $('#cbsumberdana').val();

    uraian = $("#uraian").val();
    nobkuref = "";

    if ($('#kodeTransaksi').val() == "JG") {
        sumbdana = "";

        if (jenisbayar == "PN") {
            nilaimasuk = nilaijg;
            nilaikeluar = "0";

        } else if (jenisbayar == "PG") {
            nilaimasuk = "0";
            nilaikeluar = nilaijg;
            nobkuref = $("#nobkuref").val();
        }

    } else if ($('#kodeTransaksi').val() == "JO") {
        nilaimasuk = nilaibku;
        nilaikeluar = "0";

    } else if ($('#kodeTransaksi').val() == "ST") {
        nilaimasuk = "0";
        nilaikeluar = nilaibku;
    }

    var varurl = getbasepath() + "/bku/json/prosessimpanbku";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        idsekolah: $("#idsekolah").val(),
        tglposting: tanggal,
        kodetransaksi: $('#kodeTransaksi').val(),
        nobukti: $("#noBuktiDok").val(),
        tgldok: $("#tglDok").val(),
        jenis: "3",
        beban: "TU",
        nilaikeluar: nilaikeluar,
        nilaimasuk: nilaimasuk,
        fileinbox: fileinbox,
        namapptk: $("#namaPptk").val(),
        nippptk: $("#nipPptk").val(),
        uraian: uraian,
        carabayar: $("#kodePembayaran").val(),
        kodesumbdana: sumbdana,
        nobkuref: nobkuref
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
        cleardata();
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");

    });

}

function cleardata() {

    $('#noBuktiDok').val("");
    $('#fileInbox').val("");
    $('#idKegpop').val("");
    $('#kodeKegpop').val("");
    $('#namaKegpop').val("");
    $('#keteranganKegPop').val("");
    $('#keteranganKegSpj').val("");
    $('#idKegiatanSpj').val("");
    $('#kodeKegSpj').val("");
    $('#keteranganKegPj').val("");
    $('#idKegiatanPj').val("");
    $('#kodeKegPj').val("");
    $('#bidang').val("");
    $('#snp').val("");
    $('#sumbdana').val("");
    $('#sisakas').val("");
    $('#nilaibku').val("");
    $('#nobkuref').val("");
    $('#nilaijg').val("");
    $('#nipPptk').val("");
    $('#namaPptk').val("");
    $('#uraian').val("");
    $("#sumspj").val(0);
    $("#sumpajak").val(0);
    $("#totalspjhidden").val(0);
    $("#totalpajakhidden").val(0);

    if ($('#kodeTransaksi').val() == "ST") {
        getSisaKas();
    }
}

function clearrow() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var i;
    var table;

    if (jenistransaksi == "JJ") {
        table = document.getElementById('spjtablebody');

    } else if (jenistransaksi.substr(0, 1) == "P") {
        table = document.getElementById('pajaktablebody');
    }

    var rows = table.rows;
    var jum = rows.length;

    idrow = 0;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}

function setPanel() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var ketpajak;

    setGrid();
    cleardata();

    document.getElementById("labelcbsumberdana").style.display = "none";
    document.getElementById("labelkegiatanspj").style.display = "none";
    document.getElementById("labelkegiatanpajak").style.display = "none";
    document.getElementById("labelbidang").style.display = "none";
    document.getElementById("labelsnp").style.display = "none";
    document.getElementById("labelsumbdanaspj").style.display = "none";
    document.getElementById("labelsisakas").style.display = "none";
    document.getElementById("labelnilaibku").style.display = "none";
    document.getElementById("labelnilaijg").style.display = "none";
    document.getElementById("labeljenisbayar").style.display = "none";

    document.getElementById("btnSimpan").style.visibility = "visible";

    if (jenistransaksi == "JJ") {
        document.getElementById("labelkegiatanspj").style.display = "block";
        document.getElementById("labelbidang").style.display = "block";
        document.getElementById("labelsnp").style.display = "block";
        document.getElementById("labelsumbdanaspj").style.display = "block";
        document.getElementById("labelsisakas").style.display = "block";

    } else if (jenistransaksi.substr(0, 1) == "P") {
        document.getElementById("labelkegiatanpajak").style.display = "block";

        if (jenistransaksi == "P1") {
            ketpajak = "PPH PS 21";
        } else if (jenistransaksi == "P2") {
            ketpajak = "PPH PS 22 ";
        } else if (jenistransaksi == "P3") {
            ketpajak = "PPH PS 23 JASA I";
        } else if (jenistransaksi == "P4") {
            ketpajak = "PPH PS 4 Ayat 2";
        } else if (jenistransaksi == "P5") {
            ketpajak = "PPN";
        } else if (jenistransaksi == "P6") {
            ketpajak = "PPH Pasal 26";
        }

        $('#uraian').val("Disetor Pajak " + ketpajak + " atas ");

    } else if (jenistransaksi == "JO") {
        document.getElementById("labelcbsumberdana").style.display = "block";
        document.getElementById('labelnilaibkutext').innerHTML = 'Nilai Penerimaan Kas :';
        document.getElementById("labelnilaibku").style.display = "block";

    } else if (jenistransaksi == "ST") {
        document.getElementById("labelcbsumberdana").style.display = "block";
        document.getElementById('labelnilaibkutext').innerHTML = 'Nilai Setoran :';
        document.getElementById("labelsisakas").style.display = "block";
        document.getElementById("labelnilaibku").style.display = "block";
        getSisaKas();

    } else if (jenistransaksi == "JG") {
        document.getElementById("labeljenisbayar").style.display = "block";
        document.getElementById("labelnilaijg").style.display = "block";
        document.getElementById("pilihjg").style.visibility = "hidden";
        setJasaGiro();

    } else if (jenistransaksi == "-") {
        document.getElementById("btnSimpan").style.visibility = "hidden";
    }


}

function setGrid() {
    var jenistransaksi = $('#kodeTransaksi').val();

    $('#tabelPajak').hide();
    $('#tabelSPJ').hide();

    if (jenistransaksi == "JJ") {
        $('#tabelSPJ').show();
    } else if (jenistransaksi.substr(0, 1) == "P") {
        $('#tabelPajak').show();
    }
}

function getKegiatan() {
    var jenistrans = $('#kodeTransaksi').val();

    if (jenistrans == "JJ") {
        $('#idKegiatanSpj').val($('#idKegpop').val());
        $('#kodeKegSpj').val($('#kodeKegpop').val());
        $('#keteranganKegSpj').val($('#keteranganKegPop').val());

        gridspj();

    } else if (jenistrans.substr(0, 1) == "P") {
        $('#idKegiatanPj').val($('#idKegpop').val());
        $('#kodeKegPj').val($('#kodeKegpop').val());
        $('#keteranganKegPj').val($('#keteranganKegPop').val());

        gridpajak();
    }

}

function gridspj() {
    jumbarisspj = 0;
    if (typeof myTable == 'undefined') {
        myTable = $('#spjtable').dataTable({
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
            "sAjaxSource": getbasepath() + "/bku/json/listspj",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "nobku", value: $("#noBKU").val()},
                {name: "idkegiatan", value: $("#idKegiatanSpj").val()}
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
                jumbarisspj = jumbarisspj + 1;

                var penanda = "<input type='hidden' id='penanda" + index + "' value='" + 0 + "' />";
                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var nilaiAngg = "<input type='text' name='nilaiAnggaran" + index + "' id='nilaiAnggaran" + index + "'  class='inputmoney'  value='" + aData['nilaiAnggaran'] + "' readOnly='true' />";
                var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSebelum'] + "' readOnly='true' />";
                var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['nilaiSisa'] + "' readOnly='true' />";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiSpj'] + "' readOnly='true' onkeyup='pasangvalidatebesarperfield(" + index + ")' onchange='pasangvalidatebesarperfield(" + index + "), validasipajak(" + index + ")' />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") />";
                var namaakun = "<textarea id='akunnama" + index + "'readonly style='border:none;margin:0;width:170px;'>" + aData['namaakun'] + "</textarea>";
                var textnamaakun = "<input type='hidden' id='namaakun" + index + "' value='" + aData['namaakun'] + "' />";
                var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var pajakunformat = "<input type='hidden' id='pajakunformat" + index + "' value='0' />";
                var nilaip1 = "<input type='hidden' id='nilaip1" + index + "' value='0' />";
                var nilaip2 = "<input type='hidden' id='nilaip2" + index + "' value='0' />";
                var nilaip3 = "<input type='hidden' id='nilaip3" + index + "' value='0' />";
                var nilaip4 = "<input type='hidden' id='nilaip4" + index + "' value='0' />";
                var nilaip5 = "<input type='hidden' id='nilaip5" + index + "' value='0' />";
                var nilaip6 = "<input type='hidden' id='nilaip6" + index + "' value='0' />";
                var nilaipajak = "<input type='text' name='totalpajak" + index + "' id='totalpajak" + index + "'  class='inputmoney' value='0' readOnly='true' onchange='hitungtoralpajak()' onclick='setnilaipajak()' />";

                var pilihpajak = "<a id='inputpajak" + index + "' >";

                //var pilihpajak = "<a id='inputpajak" + index + "' onclick='setketpajak(" + index + ","+ aData['idBas'] +")' class='fancybox fancybox.iframe' href='" + getbasepath() + "/bkuspjpajak/listpajak?target='_top'' title='Input Pajak SPJ'  ><i class='icon-edit'>";

                //jumbarisspj = jumbarisspj + 1;

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(namaakun);
                $('td:eq(3)', nRow).html(nilaiAngg);
                $('td:eq(4)', nRow).html(nilaisebelum);
                $('td:eq(5)', nRow).html(nilaisisa);
                $('td:eq(6)', nRow).html(nilaiinput);
                $('td:eq(7)', nRow).html(pilihpajak);
                $('td:eq(8)', nRow).html(nilaipajak + nilaip1 + nilaip2 + nilaip3 + nilaip4 + nilaip5 + nilaip6 + pajakunformat);
                $('td:eq(9)', nRow).html(inputcek + idbas + textnamaakun + textkodeakun + penanda);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "left", "sWidth": "8%"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "left", "sWidth": "15%"},
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right", "sWidth": "11%"},
                {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right", "sWidth": "11%"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right", "sWidth": "11%"},
                {"mDataProp": "nilaiSpj", "bSortable": false, sClass: "right", "sWidth": "11%"},
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "idBas", "bSortable": false, sClass: "right", "sWidth": "11%"},
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "3%"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    //getBanyakListSPJ();
    getSisaKas();

    $("#sumspj").val(0);
    $("#sumpajak").val(0);
    $("#totalspjhidden").val(0);
    $("#totalpajakhidden").val(0);

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
                {name: "nobku", value: $("#nobkuref").val()},
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

function setketpajak(index, idbas) {
    $("#idbastemp").val(idbas);
    $("#indextemp").val(index);
}

function enablerow(obj, index) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
        $("#penanda" + index).val(1);
    } else {
        $("#penanda" + index).val(0);
    }
    setdisabled(param, index);
    hitungnilaispj();
    setpajak(index, param);
}

function setpajak(index, param) {
    var text;
    if (param == "readonly") {
        text = "<a id='inputpajak" + index + "' >";
        $("#nilaip1" + index).val(0);
        $("#nilaip2" + index).val(0);
        $("#nilaip3" + index).val(0);
        $("#nilaip4" + index).val(0);
        $("#nilaip5" + index).val(0);
        $("#nilaip6" + index).val(0);
        $("#totalpajak" + index).val(0);
        $("#pajakunformat" + index).val(0);

    } else {
        text = "<a id='inputpajak" + index + "' onclick='setketpajak(" + index + "," + $("#idbas" + index).val() + ")' class='fancybox fancybox.iframe' href='" + getbasepath() + "/bku/listpajakspj?target='_top'' title='Input Pajak SPJ'  ><i class='icon-edit'>";
    }
    $("#inputpajak" + index).html(text);
    hitungtoralpajak();
}

function hitungtoralpajak() {

    var total = 0;

    for (var a = 1; a <= jumbarisspj; a++) {
        total += parseFloat(accounting.unformat($("#totalpajak" + a).val(), ","));
        //total += $("#pajakunformat" + a).val();
    }

    $("#sumpajak").val(accounting.formatNumber(total, 2, '.', ","));
    $("#totalpajakhidden").val(total);
}

function setnilaipajak() {

    for (var a = 1; a <= jumbarisspj; a++) {
        var nilai = $("#pajakunformat" + a).val();

        $("#totalpajak" + a).val(accounting.formatNumber(nilai, 2, '.', ","));
    }

}


function setdisabled(param, index) {
    $("#nilaiinput" + index).attr("readonly", param);

}

function validasipajak(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaipajak = $("#pajakunformat" + index).val();
    //var total = 0;

    if (nilaiinput < nilaipajak) {
        bootbox.alert("Nilai SPJ tidak boleh lebih kecil dari Nilai Pajak.");
        //$('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
        $('#nilaiinput' + index).autoNumeric('set', nilaipajak); // nilaisisa harus yang di unformat :)
    }

    hitungnilaispj();
}

function pasangvalidatebesarperfield(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaisisa = accounting.unformat($("#nilaisisa" + index).val(), ",");
    //var total = 0;

    if (nilaiinput > nilaisisa) {
        bootbox.alert("Nilai SPJ tidak boleh lebih besar dari Sisa Anggaran.");
        //$('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
        $('#nilaiinput' + index).autoNumeric('set', nilaisisa); // nilaisisa harus yang di unformat :)
    }

    hitungnilaispj();
}

function hitungnilaispj() {
    var total = 0;

    var searchIDs = $("#spjtable input:checkbox:checked").map(function() {
        return $(this).val();
    }).get();

    //console.log("searchIDs = "+ searchIDs);
    $.each(searchIDs, function(idx, data) {
        total += parseFloat(accounting.unformat($("#nilaiinput" + data).val(), ","));
    })
    $("#sumspj").val(accounting.formatNumber(total, 2, '.', ","));
    $("#totalspjhidden").val(total);
}

function getSisaKas() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var cbsumberdana = $('#cbsumberdana').val();
    var sumbdana = $('#sumbdana').val().substr(0, 3);
    var nobku = $('#noBKU').val();
    var kode;

    if (jenistransaksi == "JJ") {
        if (sumbdana == "BOS") {
            kode = "1";
        } else if (sumbdana == "BOP") {
            kode = "2";
        }

    } else {
        kode = cbsumberdana;
    }


    $.getJSON(getbasepath() + "/bku/json/getSisaKas", {tahun: tahun, idsekolah: idsekolah, sumbdana: kode, nobku: nobku},
    function(result) {

        var nilai = result.aData['saldoKas'];
        $('#sisakas').val(accounting.formatNumber(nilai, 2, '.', ","));

    });

}

function setformatpengeluaran(nilai) {
    var jenistransaksi = $('#kodeTransaksi').val();

    var nilaiunformat = accounting.unformat(nilai, ",");

    if (jenistransaksi == "JG") {
        $('#nilaijg').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
    } else {
        $('#nilaibku').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
    }

}

function setJasaGiro() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var kodebayar = $('#jenisPembayaran').val();

    if (jenistransaksi == "JG") {
        if (kodebayar == "PN") {
            document.getElementById('nilaijg').readOnly = false;
            document.getElementById("pilihjg").style.visibility = "hidden";
        } else {
            document.getElementById('nilaijg').readOnly = true;
            document.getElementById("pilihjg").style.visibility = "visible";
        }
    }
}

 function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
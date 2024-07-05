$(document).ready(function() {
//var kata = "1234567891011"
//console.log("kata == "+ kata.substr(0, 5));
    $("#nrkPptk").val($("#nrkPptk").val().substr(0, 6));
    $("#kodesk").hide();
    $("#golongan").hide();
    setTriwulan();
    //getPkBlj();
    getData();
    getMcb();

});
// global variable
var jumbarisspj = 0;
var jumbarispjpn = 0;
var jumbarispjpg = 0;
var idrow = 0;
var namapkblj, nrkpkblj, nippkblj;
var statusspj = 0;
function getMcb() {
    var idsekolah = $('#idsekolah').val();
    console.log("M " + idsekolah);
    $.getJSON(getbasepath() + "/danatalangan/json/getMcb", {idsekolah: idsekolah},
    function(result) {
        var banyak, kode, ket;
        var opt = '<option value="-">--Pilih Mcb--</option>'; // untuk tampilan awal combo

        banyak = result.aData.length;
        console.log("n " + banyak);
        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                console.log("i " + i);
                opt += '<option value="' + result.aData[i]['noMcb'] + '" >' + result.aData[i]['noMcb'] + ' / ' + result.aData[i]['namaMcb'] + '</option>';
            }
        }

        $("#cbmcb").html(opt);
    });
}
function getWpPjPg() {
    var idbku = $('#idbku').val();
    $.getJSON(getbasepath() + "/bkubop/json/getWpPjPg", {idbku: idbku},
    function(result) {
        if (result != null) {
            var npwp = result;
            $('#npwp').val(npwp);
            getDataNPWP();
        }
    });
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
            document.getElementById("btnSimpan").style.display = "none";
            $("#kodeTransaksi").attr('disabled', true);
        }

        $("#triwulan").html(opt);
        setSpj();
    });
}

function getDataNPWP() {
    var varurl = getbasepath() + "/bkubop/json/inquirynpwp";
    var dataac = [];
    var datajour = {
        npwp: $('#npwp').val()
    };
    dataac = datajour;
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
            if (result['error'] == null) {
                $("#namanpwp").val(result['NAMA']);
                $("#alamatnpwp").val(result['ALAMAT']);
                $("#kotanpwp").val(result['KABKOT']);
                if (result['STATUS_PKP'] == 'NON PKP') {
                    document.getElementById("cmbpkp").checked = false;
                } else {
                    document.getElementById("cmbpkp").checked = true;
                }
            } else {
                bootbox.alert(result['error']);
            }
        },
        error: function() {
            bootbox.alert("Sambungan DJP Terputus");
        }
    });
}

function getData() {
    var nrk = $("#nrkPptk").val().substr(0, 6);
    var nrkdepag = $("#nrkPptk").val().substr(0, 3);
    console.log(nrk + " cuy");
    var user = 'sipkdprod';
    var pass = 'sipkdprod!!';
//    var user = 'sipkddev';
//    var pass = 'sipkddev';

    if ((nrk != "")) {
        if (nrkdepag == "NRK") {
            document.getElementById("namaPptk").readOnly = false;
            document.getElementById("nipPptk").readOnly = false;
            getPkBlj();
        } else {

            document.getElementById("namaPptk").readOnly = true;
            document.getElementById("nipPptk").readOnly = true;
            var varurl = getbasepath() + "/bkubop/json/getpegawai";
            var dataac = [];
            console.log("NRK: " + nrk);
            var datajour = {
                nrk: nrk
            };
            dataac = datajour;
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
                    console.log(result);
                    if (result != null) {
                        $("#namaPptk").val(result['NAMA']);
                        $("#nipPptk").val(result['NIP18']);
                    } else {
                        bootbox.alert("Data NRK tidak ada");
                    }
                },
                error: function() {
                    //bootbox.alert('boo!');
                }
            });
        }

    }
}

function setSpj() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();
    $.getJSON(getbasepath() + "/bkubop/json/getStatusSpj", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan},
    function(result) {

        var opt = "";
//        if (result > 0) { // SPJ bisa input tapi akan cek, jika sudah pernah mengajukan/sudah diapprove maka v_spj_mohon tidak diisi
        opt = '<option value="-" selected> --------------------- PILIH ---------------------</option>';
        opt += '<option value="JO">PENGISIAN KAS</option>';
        opt += '<option value="JJ">SPJ (PENGAJUAN BELANJA)</option>';
        opt += '<option value="P1">PPH PS 21</option>';
        opt += '<option value="P2">PPH PS 22</option>';
        opt += '<option value="P3">PPH PS 23 JASA I</option>';
        opt += '<option value="P4">PPH PS 4 AYAT 2</option>';
        opt += '<option value="P5">PPN</option>';
        /* opt += '<option value="C1">PENCAIRAN CEK</option>';
         opt += '<option value="C2">SETOR TUNAI</option>'; */
        opt += '<option value="ST">SETOR SISA KAS </option>';
        opt += '<option value="JG">JASA GIRO</option>';
        opt += '<option value="RT">PENDAPATAN LAIN-LAIN</option>';
        statusspj = 0; // input spj sesudah approve pa
        getSisaKasSpjTambahan();
//        } else {
//            opt = '<option value="-" selected> --------------------- PILIH ---------------------</option>';
//            opt += '<option value="JO">PENGISIAN KAS</option>';
//            opt += '<option value="JJ">SPJ (PENGAJUAN BELANJA)</option>';
//            opt += '<option value="P1">PPH PS 21</option>';
//            opt += '<option value="P2">PPH PS 22</option>';
//            opt += '<option value="P3">PPH PS 23 JASA I</option>';
//            opt += '<option value="P5">PPN</option>';
//            opt += '<option value="C1">PENCAIRAN CEK</option>';
//            opt += '<option value="C2">SETOR TUNAI</option>';
//            opt += '<option value="ST">SETOR SISA KAS </option>';
//            opt += '<option value="JG">JASA GIRO</option>';
//            statusspj = 0; // input spj sebelum ada yg approve
//        }
//        if (triwulan == '1') { // SPJ bisa input tapi akan cek, jika sudah pernah mengajukan maka v_spj_mohon tidak diisi
//            opt = '<option value="-" selected> --------------------- PILIH ---------------------</option>';
//            opt += '<option value="JO">PENGISIAN KAS</option>';
//            opt += '<option value="JJ">SPJ (PENGAJUAN BELANJA)</option>';
//            opt += '<option value="P1">PPH PS 21</option>';
//            opt += '<option value="P2">PPH PS 22</option>';
//            opt += '<option value="P3">PPH PS 23 JASA I</option>';
//            opt += '<option value="P5">PPN</option>';
//            opt += '<option value="C1">PENCAIRAN CEK</option>';
//            opt += '<option value="C2">SETOR TUNAI</option>';
//            opt += '<option value="ST">SETOR SISA KAS </option>';
//            opt += '<option value="JG">JASA GIRO</option>';
//            statusspj = 1; // input spj sesudah diajukan ke sudin
//            getSisaKasSpjTambahan();
//
//        }
        $("#kodeTransaksi").html(opt);
    });
    setPanel();
}


function setVA() {
    if (document.getElementById("cbVA").checked == true) {
        $('#kodeVA').val(1);
    } else {
        $('#kodeVA').val(0);
    }
}

function setTalangan() {
    if (document.getElementById("cbTalangan").checked == true) {
        $('#kodeTalangan').val(1);
        document.getElementById("labelbulan").style.display = "block";
        document.getElementById("labelmcb").style.display = "block";
        document.getElementById("labelva").style.display = "none";
        $('#rekeningBank').val("91691160131"); // BARU
        $('#namarekanan').val("REKENING IMPERSONAL 916");
        document.getElementById("rekeningBank").readOnly = true;
        document.getElementById("namarekanan").readOnly = true;
        $('#pilihBank').attr("disabled", true);
        $('#kodeBank').val("1110012");
        $('#kodeBankTf').val("111");
        $('#namaBank').val("BANK DKI");
        $('#uraian').val("Pembayaran Listrik atas Dana Talangan Bank DKI untuk Triwulan " + $('#triwulan').val());
    } else {
        $('#kodeTalangan').val(0);
        $('#rekeningBank').val("");
        $('#namarekanan').val("");
        document.getElementById("labelva").style.display = "block";
        document.getElementById("labelbulan").style.display = "none";
        document.getElementById("labelmcb").style.display = "none";
        document.getElementById("rekeningBank").readOnly = false;
        document.getElementById("namarekanan").readOnly = false;
        $('#pilihBank').attr("disabled", false);
        $('#kodeBank').val("");
        $('#kodeBankTf').val("");
        $('#namaBank').val("");
        $('#uraian').val("");
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

function setformatpengeluaran(nilai) {
    var jenistransaksi = $('#kodeTransaksi').val();
    var nilaiunformat = accounting.unformat(nilai, ",");
    if (jenistransaksi == "JG" || jenistransaksi.substr(0, 1) == "C") {
        $('#nilaijg').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
    } else {
        $('#nilaibku').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
    }

}

function setPanel() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var jenisbayar = $('#jenisPembayaran').val();
    var ketpajak;
    setGrid();
    cleardata();
    setKodeBayar();
    $('#kodeVA').val(0); // set ulang kode VA
    $('#kodeTalangan').val(0); // set ulang kode Talangan

    document.getElementById("labelkegiatanspj").style.display = "none";
    document.getElementById("labeltalangan").style.display = "none";
    document.getElementById("labelmcb").style.display = "none";
    document.getElementById("labelbulan").style.display = "none";
    document.getElementById("labelkegiatanpajak").style.display = "none";
    document.getElementById("labelbidang").style.display = "none";
    document.getElementById("labelsnp").style.display = "none";
    document.getElementById("labelsumbdanaspj").style.display = "none";
    document.getElementById("labelsisakas").style.display = "none";
    document.getElementById("labelnilaibku").style.display = "none";
    document.getElementById("labelnilaijg").style.display = "none";
    document.getElementById("labeljenisbayar").style.display = "none";
    document.getElementById("labelcarabayar").style.display = "block";
    document.getElementById("labelkegiatansts").style.display = "none";
    document.getElementById("labelnpwp").style.display = "none";
    document.getElementById("labelnamanpwp").style.display = "none";
    document.getElementById("labelalamatnpwp").style.display = "none";
    document.getElementById("labelkotanpwp").style.display = "none";
    document.getElementById("labelpkp").style.display = "none";
    document.getElementById("labelptkp").style.display = "none";
    document.getElementById("labelkjs").style.display = "none";
    document.getElementById("labelnamabank").style.display = "none";
    document.getElementById("labelrekbank").style.display = "none";
    document.getElementById("labelva").style.display = "none";
    document.getElementById("labelpaguakb").style.display = "none";
    document.getElementById("labelnamarekanan").style.display = "none";
    document.getElementById("labelmasapajak").style.display = "none";
    document.getElementById("labeltahunpajak").style.display = "none";
    document.getElementById("labeltgldokjo").style.display = "none";
    document.getElementById("labeltgldok").style.display = "none";
    document.getElementById("labeltgl").style.display = "none";
    document.getElementById("labeltglbukti").style.display = "none";
    document.getElementById("labelsisaspj").style.display = "none";
    document.getElementById("labelrumuspajakp1").style.display = "none";
    document.getElementById("labelrumuspajakp2").style.display = "none";
    document.getElementById("labelrumuspajakp3").style.display = "none";
    document.getElementById("labelrumuspajakp4").style.display = "none";
    document.getElementById("labelrumuspajakp5").style.display = "none";
    document.getElementById("labelbayarpajak").style.display = "none";
    document.getElementById("labelpns").style.display = "none";
    document.getElementById("labelsk").style.display = "none";
    document.getElementById("labelbelanja").style.display = "none";
    document.getElementById("labelpegawai").style.display = "none";
    document.getElementById("labelperiode").style.display = "none";
    document.getElementById("btnSimpan").style.visibility = "visible";
    document.getElementById("divButton").style.display = "block";
    $("#tglDok").attr('disabled', false);
    document.getElementById('noBuktiDok').readOnly = false;
    document.getElementById("namarekanan").readOnly = false;

    if (jenistransaksi == "JO") {
        document.getElementById('labelnilaibkutext').innerHTML = 'Nilai Penerimaan Kas :';
        document.getElementById("labelnilaibku").style.display = "block";
        document.getElementById("tglDok").className = "required date-picker entrytanggal valid";
        document.getElementById("labeltgldokjo").style.display = "block";
        $('#tglDokJo').val("");

    } else if (jenistransaksi == "JG" || jenistransaksi == "RT") {
        if (jenistransaksi == "RT") {
            document.getElementById('labelpengeluarantext').innerHTML = 'Nilai :';
        } else if (jenistransaksi == "JG") {
            document.getElementById('labelpengeluarantext').innerHTML = 'Nilai Jasa Giro :';
        }

        document.getElementById("labeljenisbayar").style.display = "block";
        document.getElementById("labelnilaijg").style.display = "block";
        document.getElementById("pilihjg").style.visibility = "hidden";
        document.getElementById("labeltgldok").style.display = "block";
        document.getElementById("labeltgl").style.display = "block";
        setJasaGiro();

    } else if (jenistransaksi == "ST") {
        document.getElementById('labelpengeluarantext').innerHTML = 'Nilai Setoran :';
        document.getElementById("labelnilaijg").style.display = "block";
        document.getElementById('nilaijg').readOnly = true;
        document.getElementById("pilihjg").style.visibility = "visible";
        document.getElementById('noBuktiDok').readOnly = true;
        document.getElementById("labeltgldok").style.display = "block";
        document.getElementById("labeltgl").style.display = "block";
        $('#tglDok').attr("disabled", true);

    } else if (jenistransaksi == "JJ") {
        document.getElementById("labelsisaspj").style.display = "block";
        document.getElementById("labelkegiatanspj").style.display = "block";
        document.getElementById("labelbidang").style.display = "block";
        document.getElementById("labelsnp").style.display = "block";
        document.getElementById("labelsumbdanaspj").style.display = "block";
        document.getElementById("labelsisakas").style.display = "block";
        document.getElementById("labeltgldok").style.display = "block";
        document.getElementById("labelnamabank").style.display = "block";
        document.getElementById("labelrekbank").style.display = "block";
        document.getElementById("labelva").style.display = "block";
        document.getElementById("labelpaguakb").style.display = "block";
        document.getElementById('textnamarekan').innerHTML = 'Nama Rekanan/PFK :';
        document.getElementById("labelnamarekanan").style.display = "block";
        document.getElementById("labeltglbukti").style.display = "block";
        document.getElementById("labelnpwp").style.display = "block";
        document.getElementById("labelnamanpwp").style.display = "block";
        document.getElementById("labelkotanpwp").style.display = "block";
        document.getElementById("labelalamatnpwp").style.display = "block";
        document.getElementById("labelpkp").style.display = "block";
        document.getElementById("labelptkp").style.display = "block";
        document.getElementById("labelbelanja").style.display = "block";
        document.getElementById("labelsk").style.display = "block";
        document.getElementById('textnpwp').innerHTML = 'NPWP Rekanan/PFK :';
        document.getElementById('textnamanpwp').innerHTML = 'Nama NPWP Rekanan/PFK :';
        document.getElementById('textalamatnpwp').innerHTML = 'Alamat NPWP Rekanan/PFK :';
        document.getElementById('textkotanpwp').innerHTML = 'Kota NPWP Rekanan/PFK :';
        clearrow();
        getSisaKasSpjTambahan();

    } else if (jenistransaksi.substr(0, 1) == "P") {
        document.getElementById("labelkegiatanpajak").style.display = "block";
        document.getElementById('textnamarekan').innerHTML = 'Nama Penyetor :';
        document.getElementById("labelnamarekanan").style.display = "block";
        document.getElementById("labelnpwp").style.display = "block";
        document.getElementById("labelnamanpwp").style.display = "block";
        document.getElementById("labelkotanpwp").style.display = "block";
        document.getElementById("labelalamatnpwp").style.display = "block";
        document.getElementById("labelmasapajak").style.display = "block";
        document.getElementById("labeltahunpajak").style.display = "block";
        document.getElementById("labeljenisbayar").style.display = "block";
        document.getElementById("labeltgldok").style.display = "block";
        document.getElementById("labeltgl").style.display = "block";
        //document.getElementById("namarekanan").readOnly = true;
        document.getElementById("labelbayarpajak").style.display = "block";
        document.getElementById("labelpkp").style.display = "block";
        document.getElementById("labelkjs").style.display = "block";
        var opt = "";
        opt = '<option value="-">Pilih</option>';
        opt += '<option value="PN">Penerimaan</option>';
        opt += '<option value="PG">Pengeluaran</option>';
        $("#jenisPembayaran").html(opt);
        console.log("SINI");
        setKegiatanPajak();
        //getDataWP();

        var optkjs;

        if (jenistransaksi == "P1") {
            $("#kodemap").val('411121');
            optkjs = '<option value="402">402</option>';
            optkjs += '<option value="100">100</option>';
            ketpajak = "PPH PS 21";
        } else if (jenistransaksi == "P2") {
            $("#kodemap").val('411122');
            optkjs = '<option value="920">920</option>';
            ketpajak = "PPH PS 22 ";
        } else if (jenistransaksi == "P3") {
            $("#kodemap").val('411124');
            optkjs = '<option value="100">100</option>';
            optkjs += '<option value="104">104</option>';
            ketpajak = "PPH PS 23 JASA I";
        } else if (jenistransaksi == "P4") {
            $("#kodemap").val('411128');
            optkjs = '<option value="420">420</option>';
            ketpajak = "PPH PS 4 Ayat 2";
        } else if (jenistransaksi == "P5") {
            $("#kodemap").val('411211');
            optkjs = '<option value="920">920</option>';
            ketpajak = "PPN";
        } else if (jenistransaksi == "P6") {
            ketpajak = "PPH Pasal 26";
        }
        $("#kodekjs").html(optkjs);

        if (jenisbayar == "PN") {
            $('#uraian').val("Diterima Pajak " + ketpajak + " atas SPJ ");
        } else {
            $('#uraian').val("Disetor Pajak " + ketpajak + " atas Penerimaan Pajak ");
        }

        clearrow();

    } else if (jenistransaksi == "-") {
        document.getElementById("divButton").style.display = "none";
        //document.getElementById("btnSimpan").style.visibility = "hidden";

    } else if (jenistransaksi == "C1" || jenistransaksi == "C2") {
        document.getElementById('labelpengeluarantext').innerHTML = 'Nilai :';
        document.getElementById("labelnilaijg").style.display = "block";
        document.getElementById("pilihjg").style.visibility = "hidden";
        document.getElementById("labeltgldokjo").style.display = "block";
        document.getElementById("labelcarabayar").style.display = "none";
        $('#tglDokJo').val("");

    }

    if (jenistransaksi == "JJ" || jenistransaksi.substr(0, 1) == "P") {
        document.getElementById("divButton").setAttribute("align", "right");
    } else {
        document.getElementById("divButton").setAttribute("align", "left");
    }

}
function inputSK() {
    if ($('#cmbsk').val() == '1') {
        $('#kodesk').show();
    } else {
        $('#kodesk').val("");
        $('#kodesk').hide();

    }
}
function pilihBelanja() {
    $('#pns').val('-');
    $('#golongan').val('-');
    $('#golongan').hide();
    if ($('#belanja').val() == '1') {
        document.getElementById("labelpns").style.display = "block";
        document.getElementById("labelptkp").style.display = "block";
        document.getElementById("labelsk").style.display = "none";
    } else {
        document.getElementById("labelpns").style.display = "none";
        document.getElementById("labelptkp").style.display = "none";
        document.getElementById("labelperiode").style.display = "none";
        document.getElementById("labelpegawai").style.display = "none";
        document.getElementById("labelsk").style.display = "block";
    }
}
function pilihGolongan() {
    if ($('#pns').val() == '1') {
        $('#golongan').show();
        document.getElementById("labelpegawai").style.display = "none";
        $('#pegawai').val("-");
    } else if ($('#pns').val() == '0') {
        $('#golongan').val('-');
        $('#golongan').hide();
        $('#pegawai').val('-');
        document.getElementById("labelpegawai").style.display = "block";
    } else {
        document.getElementById("labelgolongan").style.display = "none";
    }
}
function pilihPegawai() {
    if ($('#pegawai').val() == '1') {
        document.getElementById("labelperiode").style.display = "block";
    } else {
        $('#periode').val('-');
        document.getElementById("labelperiode").style.display = "none";
    }
}
function setKodeBayar() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var opt = "";
    if (jenistransaksi == "JO" || jenistransaksi == "RT") {
        opt = '<option value="2" select>2 - Bank/Transfer/Cek</option>';
    } else {
        //opt = '<option value="1" select>1 - Tunai</option>';
        opt = '<option value="2" selected>2 - Bank/Transfer/Cek</option>';
    }

    $("#kodePembayaran").html(opt);
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
    $('#paguakb').val("");
    $('#nilaibku').val("");
    $('#nobkuref').val("");
    $('#nilaijg').val("");
    //$('#nipPptk').val("");
    //$('#namaPptk').val("");

    $('#uraian').val("");
    $("#sumspj").val(0);
    $("#totalspjhidden").val(0);
    $('#keteranganKegSts').val("");
    $('#idKegiatanSts').val("");
    $('#kodeKegSts').val("");
    $('#npwp').val("");
    $('#namanpwp').val("");
    $('#alamatnpwp').val("");
    $('#kotanpwp').val("");
    $('#idBank').val("");
    $('#kodeBank').val("");
    $('#kodeBankTf').val("");
    $('#namaBank').val("");
    $('#rekeningBank').val("");
    $('#kodeVA').val("");
    $('#namarekanan').val("");
    $('#nobkuref').val("");
    $('#idrinci').val("");
    $('#ketmasapajak').val("");
    $('#pns').val("-");
    $('#golongan').val("-");
    $('#belanja').val("-");
    $('#pegawai').val("-");
    $('#periode').val("-");
    $('#ptkp').val("-");
    $('#cmbsk').val("-");
    $('#kodesk').val("");
    document.getElementById("cbVA").checked = false;
    document.getElementById("cbTalangan").checked = false;
    document.getElementById("cmbpkp").readOnly = false;
    document.getElementById("rekeningBank").readOnly = false;
    document.getElementById("namarekanan").readOnly = false;
    $('#pilihBank').attr("disabled", false);
}

function clearrow() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var jenisbayar = $('#jenisPembayaran').val();
    var i;
    var table;
    if (jenistransaksi == "JJ") {
        table = document.getElementById('spjtablebody');
    } else if (jenistransaksi.substr(0, 1) == "P") {

        if (jenisbayar == "PN") {
            $('#sumpjpn').autoNumeric('set', 0);
            table = document.getElementById('pajaktablebody');
        } else {
            table = document.getElementById('pajakpgtablebody');
        }

    }

    var rows = table.rows;
    var jum = rows.length;
    idrow = 0;
    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}


function setGrid() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var jenisbayar = $('#jenisPembayaran').val();
    $('#tabelPajak').hide();
    $('#tabelSPJ').hide();
    $('#tabelPajakPg').hide();
    if (jenistransaksi == "JJ") {
        $('#tabelSPJ').show();
    } else if (jenistransaksi.substr(0, 1) == "P") {
        if (jenisbayar == "PN") {
            $('#tabelPajak').show();
        } else if (jenisbayar == "PG") {
            $('#tabelPajakPg').show();
        } else {
            $('#tabelPajak').hide();
            $('#tabelPajakPg').hide();
        }
    }
}

function enableBtnSimpan() {
    $('#btnSimpan').attr("disabled", false);
}

function setJenisBayar() {
    var jenistransaksi = $('#kodeTransaksi').val();
    if (jenistransaksi == "JG" || jenistransaksi == "RT") {
        setJasaGiro();
    } else if (jenistransaksi.substr(0, 1) == "P") {
        console.log("SINI 2");
        setKegiatanPajak();
    }

}

function setJasaGiro() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var kodebayar = $('#jenisPembayaran').val();

    if (jenistransaksi == "JG" || jenistransaksi == "RT") {
        if (kodebayar == "PN") {
            document.getElementById('nilaijg').readOnly = false;
            document.getElementById("pilihjg").style.visibility = "hidden";
            document.getElementById('noBuktiDok').readOnly = false;
            //$('#tglDok').attr("disabled", false);

        } else if (kodebayar == "PG") {
            document.getElementById('nilaijg').readOnly = true;
            document.getElementById("pilihjg").style.visibility = "visible";
            document.getElementById('noBuktiDok').readOnly = true;
            //$('#tglDok').attr("disabled", true);

        } else {
            document.getElementById('nilaijg').readOnly = true;
            document.getElementById("pilihjg").style.visibility = "hidden";
            document.getElementById('noBuktiDok').readOnly = false;
        }

    } else {
        document.getElementById('noBuktiDok').readOnly = false;
        $('#tglDok').attr("disabled", false);
    }
}

function setKegiatanPajak() {
    var jenis = $("#jenisPembayaran").val();
    var jenistransaksi = $("#kodeTransaksi").val();
    var ketpajak = "";
    //getDataWP();

    var a = document.getElementById("pilihKegPj");
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

    document.getElementById("labelbayarpajak").style.display = "none";
    if (jenis == "PN") {
        document.getElementById('textnamarekan').innerHTML = 'Nama Sekolah :';
        document.getElementById('textnpwp').innerHTML = 'NPWP Sekolah :';
        document.getElementById('textnamanpwp').innerHTML = 'Nama NPWP Sekolah :';
        document.getElementById('textalamatnpwp').innerHTML = 'Alamat NPWP Sekolah :';
        document.getElementById('textkotanpwp').innerHTML = 'Kota NPWP Sekolah :';
        if (jenistransaksi == "P1") {
//            document.getElementById("labelrumuspajakp1").style.display = "block";
//            $('#rumuspajakp1').val("-");
        } else if (jenistransaksi == "P2") {
            document.getElementById("labelrumuspajakp2").style.display = "block";
            $('#rumuspajakp2').val("-");
        } else if (jenistransaksi == "P3") {
            document.getElementById("labelrumuspajakp3").style.display = "block";
            $('#rumuspajakp3').val("-");
        } else if (jenistransaksi == "P4") {
            document.getElementById("labelrumuspajakp4").style.display = "block";
            $('#rumuspajakp4').val("-");
        } else if (jenistransaksi == "P5") {
            document.getElementById("labelrumuspajakp5").style.display = "block";
            $('#rumuspajakp5').val("-");
        }
        document.getElementById("labelbayarpajak").style.display = "block";
        a.href = getbasepath() + "/bkubop/listpjpn?target='_top'";

        $('#uraian').val("Diterima Pajak " + ketpajak + " atas SPJ ");
        console.log("SINI 3");
        getDataWP();
    } else if (jenis == "PG") {
        document.getElementById('textnamarekan').innerHTML = 'Nama Penyetor :';
        document.getElementById('textnpwp').innerHTML = 'NPWP Penyetor :';
        document.getElementById('textnamanpwp').innerHTML = 'Nama NPWP Penyetor :';
        document.getElementById('textalamatnpwp').innerHTML = 'Alamat NPWP Penyetor :';
        document.getElementById('textkotanpwp').innerHTML = 'Kota NPWP Penyetor :';
        document.getElementById("labelrumuspajakp1").style.display = "none";
        document.getElementById("labelrumuspajakp2").style.display = "none";
        document.getElementById("labelrumuspajakp3").style.display = "none";
        document.getElementById("labelrumuspajakp4").style.display = "none";
        document.getElementById("labelrumuspajakp5").style.display = "none";
        a.href = getbasepath() + "/bkubop/listpjpg?target='_top'";
        $('#uraian').val("Disetor Pajak " + ketpajak + " atas Penerimaan Pajak ");
    } /*else {
     //a.href ="#";
     document.getElementById("labelrumuspajakp1").style.display = "none";
     document.getElementById("labelrumuspajakp2").style.display = "none";
     document.getElementById("labelrumuspajakp3").style.display = "none";
     document.getElementById("labelrumuspajakp5").style.display = "none";

     $('#uraian').val("");
     } */

    $('#idKegpop').val("");
    $('#kodeKegpop').val("");
    $('#namaKegpop').val("");
    $('#keteranganKegPop').val("");
    $('#keteranganKegPj').val("");
    $('#idKegiatanPj').val("");
    $('#nobkuref').val("");
    $('#idrinci').val("");
    $('#ketmasapajak').val("");
    setGrid();
    clearrow();
}

function getParamPajak() {
    var idbku = $('#idbku').val();

    console.log(idbku + " idbku");
    $.getJSON(getbasepath() + "/bkubop/json/getDataParam", {idbku: idbku},
    function(result) {
        if (result == null) {
            $('#cnpwp').val("-");
            $('#idparam').val("-");
            $('#cpkp').val("-");
            $('#cpns').val("-");
            $('#csk').val("-");
            $('#cbelanja').val("-");
            $('#cpegawai').val("-");
            $('#cperiode').val("-");
            $('#cptkp').val("-");
        } else {
            var npwp = result['npwp'];
            var idparam = result['idParam'];
            var pkp = result['kodePKP'];
            var pns = result['kodePNS'];
            var sk = result['kodeSK'];
            var belanja = result['kodeBelanja'];
            var pegawai = result['kodePegawai'];
            var periode = result['kodePeriode'];
            var ptkp = result['kodePTKP'];
            $('#cnpwp').val(npwp);
            $('#idparam').val(idparam);
            $('#cpkp').val(pkp);
            $('#cpns').val(pns);
            $('#csk').val(sk);
            $('#cbelanja').val(belanja);
            $('#cpegawai').val(pegawai);
            $('#cperiode').val(periode);
            $('#cptkp').val(ptkp);
        }
        validateP2P3();
    });
}
function validateP2P3() {
    var idbku = $('#idbku').val();
    var idsekolah = $('#idsekolah').val();
    console.log(idbku + " idbku");
    $.getJSON(getbasepath() + "/bkubop/json/validateP2P3", {idbku: idbku, idsekolah: idsekolah},
    function(result) {
        var p2 = result['P2'];
        var p3 = result['P3'];
        $('#p2').val(p2);
        $('#p3').val(p3);
        cekParam();
    });
}
function cekParam() {
    var jenistransaksi = $("#kodeTransaksi").val();
    var valid = true;
    if (jenistransaksi == 'P1') {
        if ($('#cbelanja').val() != '1') {
            valid = false;
            bootbox.alert("Jenis belanja selain pegawai tidak boleh dikenakan pajak PPH Pasal 21");
        } else {
            setPnP1();
        }
    } else if (jenistransaksi == 'P2') {
        if ($('#cbelanja').val() != '2') {
            valid = false;
            bootbox.alert("Jenis belanja selain barang tidak boleh dikenakan pajak PPH Pasal 22");
        } else if ($('#p3').val() != '0') {
            valid = false;
            bootbox.alert("Jika sudah dikenakan pajak PPH Pasal 23 maka tidak dapat dikenakan pajak PPH Pasal 22");
        } else if ($('#csk').val() == '1') {
            valid = false;
            bootbox.alert("Rekanan yang memiliki Surat Keterangan Pajak tidak boleh dipotong pajak PPH Pasal 22 atau PPH Pasal 23 tetapi dipotong PPH Pasal 4 Ayat 2");
        }

    } else if (jenistransaksi == 'P3') {
        if ($('#cbelanja').val() != '3') {
            valid = false;
            bootbox.alert("Jenis belanja selain jasa tidak boleh dikenakan pajak PPH Pasal 23");
        } else if ($('#p2').val() != '0') {
            valid = false;
            bootbox.alert("Jika sudah dikenakan pajak PPH Pasal 22 maka tidak dapat dikenakan pajak PPH Pasal 23");
        } else if ($('#csk').val() == '1') {
            valid = false;
            bootbox.alert("Rekanan yang memiliki Surat Keterangan Pajak tidak boleh dipotong pajak PPH Pasal 22 atau PPH Pasal 23 tetapi dipotong PPH Pasal 4 Ayat 2");
        }
    } else if (jenistransaksi == 'P4') {

    } else if (jenistransaksi == 'P5') {
        if ($('#cpkp').val() != '1') {
            valid = false;
            bootbox.alert("Rekanan yang memiliki Status PKP Aktif tidak boleh dipotong pajak PPN");
        }
    }
    if (!valid) {
        $('#btnSimpan').hide();
    }
}
function setOptPnPg() {
    var jenis = $("#jenisPembayaran").val();
    var opt = "";
    if (jenis !== "-") {
        opt = '<option value="PN">Penerimaan</option>';
        opt += '<option value="PG">Pengeluaran</option>';
        $("#jenisPembayaran").html(opt);
        $("#jenisPembayaran").val(jenis);
    }

}

function cekTW() {
    var tw = $("#triwulan").val();
    var a = document.getElementById("pilihKegSpj");
    var jenistransaksi = $('#kodeTransaksi').val();
    if (jenistransaksi == "JJ") {
// set button pilih kegiatan jika TW = "-"
        if (tw == "-") {
            a.href = "#";
            document.getElementById("pilihKegSpj").className = "btn green";
            bootbox.alert("Pilih Triwulan Terlebih Dulu");
        } else {
            document.getElementById("pilihKegSpj").className = "fancybox fancybox.iframe btn green";
            a.href = getbasepath() + "/common/listkegiatanbop?target='_top'";
        }

// clear data dan gridspj
        clearrow();
        $('#idKegpop').val("");
        $('#kodeKegpop').val("");
        $('#namaKegpop').val("");
        $('#keteranganKegPop').val("");
        $('#keteranganKegSpj').val("");
        $('#idKegiatanSpj').val("");
        $('#kodeKegSpj').val("");
        $('#bidang').val("");
        $('#snp').val("");
        $('#sumbdana').val("");
        $('#sisakas').val("");
        $('#paguakb').val("");
        $("#sumspj").val(0);
        $("#totalspjhidden").val(0);
    }
}

function getKegiatan() {
    var jenistrans = $('#kodeTransaksi').val();
    var jenisbayar = $('#jenisPembayaran').val();
    if (jenistrans == "JJ") {
        $('#idKegiatanSpj').val($('#idKegpop').val());
        $('#kodeKegSpj').val($('#kodeKegpop').val());
        $('#keteranganKegSpj').val($('#keteranganKegPop').val());
        getSisaKasJJ();
        gridspj();
    } else if (jenistrans.substr(0, 1) == "P") {
        $('#idKegiatanPj').val($('#idKegpop').val());
        $('#kodeKegPj').val($('#kodeKegpop').val());
        $('#keteranganKegPj').val($('#keteranganKegPop').val());
        if (jenisbayar == "PN") {
            gridpjpn();
        } else {

            var mp1 = $('#ketmasapajak').val().substr(0, 2);
            var mp2 = $('#ketmasapajak').val().substr(2, 2);
            $('#masapajak1').val(mp1);
            $('#masapajak2').val(mp2);
            gridpjpg();
        }

    }

}

function getSisaKasJJ() {
    var idkegiatan = $('#idKegiatanSpj').val();
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var nobku = $('#noBkuMohon').val();
    var triwulan = $('#triwulan').val();
    // get sisa kas pagu per-kegiatan
    $.getJSON(getbasepath() + "/bkubop/json/getSisaKas", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan, nobku: nobku, idkegiatan: idkegiatan},
    function(result) {

        var saldo = result.aData['saldoKas'];
        var pagu = result.aData['paguAkb'];
        $('#sisakas').val(accounting.formatNumber(saldo, 2, '.', ","));
        $('#paguakb').val(accounting.formatNumber(pagu, 2, '.', ","));
    });
}

function simpan() {
    var talangan = $('#kodeTalangan').val();
    $('#btnSimpan').attr("disabled", true);
    var jenistransaksi = $('#kodeTransaksi').val();
    var TW = $('#triwulan').val();
    var tglDok = $("#tglDok").val();
    var tglDokJo = $("#tglDokJo").val();
    var filling = $('#fileInbox').val();
    var bulanTglDok = tglDok.substr(3, 2);
    var tahunTglDok = tglDok.substr(6, 4);
    var bulanTglDokJo = tglDokJo.substr(3, 2);
    var tahunTglDokJo = tglDokJo.substr(6, 4);
    var nobukti = $("#noBuktiDok").val();
    var datalengkap = true;
    var nrk = $("#nrkPptk").val();
    var nippptk = $("#nipPptk").val();
    var namapptk = $("#namaPptk").val();
    var idKegiatanSpj = $("#idKegiatanSpj").val();
    var nilaitotalspj = $("#totalspjhidden").val();
    var nilaibku = accounting.unformat($("#nilaibku").val(), ",");
    var nilaijg = accounting.unformat($("#nilaijg").val(), ",");
    var idKegiatanPj = $("#idKegiatanPj").val();
    var npwp = $("#npwp").val();
    var namanpwp = $("#namanpwp").val();
    var table3 = document.getElementById('spjtablebody');
    var rows3 = table3.rows;
    var jum3 = rows3.length;
    var bank = $("#namaBank").val();
    var norek = $("#rekeningBank").val();
    var namarekan = $("#namarekanan").val();
    if (jenistransaksi == "JO") {
        if (tglDokJo == "" || nobukti == "" || filling == "" || nippptk == "" || namapptk == "" || nrk == "" || nilaibku <= 0) {
            bootbox.alert("Pengisian Data Harus Lengkap");
            $('#btnSimpan').attr("disabled", false);
        } else if (TW == "-") {
            bootbox.alert("Pilih Triwulan Terlebih Dulu");
            $('#btnSimpan').attr("disabled", false);
        } else if (tahunTglDokJo !== $("#tahun").val()) {
            bootbox.alert("Tahun Dokumen Harus Sesuai Tahun yang Login");
            $('#btnSimpan').attr("disabled", false);
        } else {
            simpanJO();
        }

    } else if (jenistransaksi == "JG" || jenistransaksi == "RT") {
        if (nrk == "" || nobukti == "" || tglDok == "" || filling == "" || nippptk == "" || namapptk == "" || nilaijg <= 0) {
            bootbox.alert("Pengisian Data Harus Lengkap");
            $('#btnSimpan').attr("disabled", false);
        } else if (TW == "-") {
            bootbox.alert("Pilih Triwulan Terlebih Dulu");
            $('#btnSimpan').attr("disabled", false);
        } else if (tahunTglDok !== $("#tahun").val()) {
            bootbox.alert("Tahun Dokumen Harus Sesuai Tahun yang Login");
            $('#btnSimpan').attr("disabled", false);
        } else {
            simpanJO();
        }
    } else if (jenistransaksi == "ST") {
        if (nrk == "" || nobukti == "" || tglDok == "" || filling == "" || nippptk == "" || namapptk == "" || nilaijg <= 0) {
            bootbox.alert("Pengisian Data Harus Lengkap");
            $('#btnSimpan').attr("disabled", false);
        } else if (TW == "-") {
            bootbox.alert("Pilih Triwulan Terlebih Dulu");
            $('#btnSimpan').attr("disabled", false);
        } else if (tahunTglDok !== $("#tahun").val()) {
            bootbox.alert("Tahun Dokumen Harus Sesuai Tahun yang Login");
            $('#btnSimpan').attr("disabled", false);
        } else {
            simpanJO();
        }
    } else if (jenistransaksi == "C1" || jenistransaksi == "C2") {
        if (nrk == "" || nobukti == "" || tglDokJo == "" || filling == "" || nippptk == "" || namapptk == "" || nilaijg <= 0) {
            bootbox.alert("Pengisian Data Harus Lengkap");
            $('#btnSimpan').attr("disabled", false);
        } else if (TW == "-") {
            bootbox.alert("Pilih Triwulan Terlebih Dulu");
            $('#btnSimpan').attr("disabled", false);
        } else if (tahunTglDokJo !== $("#tahun").val()) {
            bootbox.alert("Tahun Dokumen Harus Sesuai Tahun yang Login");
            $('#btnSimpan').attr("disabled", false);
        } else {
            simpanC12();
        }
    }
    if (jenistransaksi == "JJ") {
        var cekvolume = true;
        var sisakas = accounting.unformat($("#sisakas").val(), ","); // sisa pagu AKB TW per Kegiatan
        var paguakb = accounting.unformat($("#paguakb").val(), ",");
        var sisakasspj = accounting.unformat($("#sisakasspj").val(), ",");
        var banyak = 0;
        var nilaivalidasi, uraianvalidasi;
        var panjangnorek = ($("#rekeningBank").val()).length;
        var kodebank = $("#kodeBankTf").val();
        var kodeva = $("#kodeVA").val();
        var pns = $("#pns").val();
        var periode = $("#periode").val();
        var golongan = $("#golongan").val();
        var ptkp = $("#ptkp").val();
        var sk = $("#cmbsk").val();
        var kodesk = $("#kodesk").val();
        var belanja = $("#belanja").val();
        var pegawai = $("#pegawai").val();

        if (sisakas < sisakasspj) {
            nilaivalidasi = sisakas;
            uraianvalidasi = "Sisa Pagu AKB Kegiatan";
        } else {
            nilaivalidasi = sisakasspj;
            uraianvalidasi = "Sisa Kas";
        }

        if ((jum3 > 0) && ($("#akunnama1").val() !== "")) {

            for (var a = 1; a <= jum3; a++) {
                var nilai = parseFloat(accounting.unformat($('#nilaiinput' + a).val(), ","));
                var volume = parseInt($('#volume' + a).val());
                var kodeakun = $('#kodeakun' + a).val().substr(0, 5);
                if (nilai <= 0) { // validasinya ganti, nilai ga boleh 0 (KALO EDIT BOLEH, KL SIMPAN BARU GA BOLEH)
                    datalengkap = false;
                } else {
                    banyak = banyak + 1;
                }

                if ((kodeakun == "5.2.3") && (volume <= 0) && (nilai > 0)) {
                    cekvolume = false;
                }
            }


            if ((sk == "1" && kodesk == "") || nrk == "" || nobukti == "" || tglDok == "" || filling == "" || idKegiatanSpj == "" || nippptk == "" || namapptk == "" || namarekan == "" || bank == "" || norek == "" || banyak == 0 || (npwp != "" && namanpwp == "")) {
                bootbox.alert("Pengisian Data Harus Lengkap");
                $('#btnSimpan').attr("disabled", false);
            } else if (TW == "-") {
                bootbox.alert("Pilih Triwulan Terlebih Dulu");
                $('#btnSimpan').attr("disabled", false);
            } else if (tahunTglDok !== $("#tahun").val()) {
                bootbox.alert("Tahun Dokumen Harus Sesuai Tahun yang Login");
                $('#btnSimpan').attr("disabled", false);
            } else if (belanja == "-") {
                bootbox.alert("Pilih Jenis Belanja Terlabih Dahulu");
                $('#btnSimpan').attr("disabled", false);
            } else if (belanja == "1" && pns == "-") {
                bootbox.alert("Pilih Status PNS Terlabih Dahulu");
                $('#btnSimpan').attr("disabled", false);
            } else if (pns == "1" && golongan == '-') {
                bootbox.alert("Pilih Golongan PNS Terlabih Dahulu");
                $('#btnSimpan').attr("disabled", false);
            } else if (pns == "0" && pegawai == "-") {
                bootbox.alert("Pilih Jenis Pegawai Terlabih Dahulu");
                $('#btnSimpan').attr("disabled", false);
            } else if (pegawai == "1" && periode == "-") {
                bootbox.alert("Pilih Jenis Periode Terlabih Dahulu");
                $('#btnSimpan').attr("disabled", false);
            } else if (belanja == "1" && ptkp == "-") {
                bootbox.alert("Pilih Jenis PTKP Terlabih Dahulu");
                $('#btnSimpan').attr("disabled", false);
            } else if (belanja != "1" && sk == "-") {
                bootbox.alert("Pilih Status Surat Keterangan Pajak Terlebih Dahulu");
                $('#btnSimpan').attr("disabled", false);
            } else if (nilaitotalspj > nilaivalidasi) {
                bootbox.alert("Total SPJ Tidak Boleh Lebih Besar dari " + uraianvalidasi);
                $('#btnSimpan').attr("disabled", false);
            } else if ($('#cbmcb').val() == '-' && $('#kodeTalangan').val() == '1') {
                bootbox.alert("Pilih MCB Terlebih Dahulu");
                $('#btnSimpan').attr("disabled", false);
            } else if (cekvolume == false) {
                bootbox.alert("Untuk Belanja Modal (Kode Rekening 5.2.3.*) Volume Harus Diisi");
                $('#btnSimpan').attr("disabled", false);
            } else if (kodebank == "111" && panjangnorek !== 11 && kodeva == "0") {
                bootbox.alert("Panjang No Rekening Tujuan Bank DKI Harus 11 Karakter");
                $('#btnSimpan').attr("disabled", false);
            } else if (panjangnorek > 20 && kodeva == "0") {
                bootbox.alert("Panjang No Rekening Tujuan Tidak Boleh Lebih dari 20 Karakter");
                $('#btnSimpan').attr("disabled", false);
            } else {
                simpanSPJ();
            }
        }
    } else if (jenistransaksi.substr(0, 1) == "P") {
        var jumlah, i;
        if ($("#jenisPembayaran").val() == "PN") {
            jumlah = jumbarispjpn;
        } else {
            jumlah = jumbarispjpg;
        }

        for (i = 0; i < jumlah; i++) {
            var nilai = parseFloat(accounting.unformat($('#nilaiinput' + i).val(), ","));
            //console.log("nilai pajak = "+nilai);
            if (nilai < 0) { // nilai ga boleh 0 dan minus
                datalengkap = false;
                //console.log("masuk nilai < 0");
            }
        }

        if (nrk == "" || nobukti == "" || tglDok == "" || filling == "" || idKegiatanPj == "" || nippptk == "" || namapptk == "" || namarekan == "" || ($("#jenisPembayaran").val() == "PN" && npwp == "") || $('#tahunpajak').val() == "" || (npwp != "" && namanpwp == "")) {
            bootbox.alert("Pengisian Data Harus Lengkap");
            $('#btnSimpan').attr("disabled", false);
        } else if (TW == "-") {
            bootbox.alert("Pilih Triwulan Terlebih Dulu");
            $('#btnSimpan').attr("disabled", false);
        } else if (tahunTglDok !== $("#tahun").val()) {
            bootbox.alert("Tahun Dokumen Harus Sesuai Tahun yang Login");
            $('#btnSimpan').attr("disabled", false);
        } else if (parseInt($("#masapajak1").val()) > parseInt($("#masapajak2").val())) {
            bootbox.alert("Bulan Masa Pajak Pertama Tidak Boleh Lebih Besar dari Bulan Masa Pajak Kedua");
            $('#btnSimpan').attr("disabled", false);
        } else if (datalengkap == false) {
            bootbox.alert("Nilai Pajak Tidak Boleh Minus");
            $('#btnSimpan').attr("disabled", false);
        } else {
            simpanPajak();
        }

    }

}
function simpanC12() {
    var fileinbox = $("#fileInbox").val();
    var kodetrx = $('#kodeTransaksi').val();
    var uraian = $("#uraian").val();
    var jenisbayar = $("#jenisPembayaran").val();
    var nilaimasuk, nilaikeluar;
    var nilaibku = (accounting.unformat($("#nilaibku").val(), ",")).toString();
    var nilai = (accounting.unformat($("#nilaijg").val(), ",")).toString();
    var idbas1, idbas2, kodeakun1, kodeakun2, bkututup, bkustatus, tgldok;
    tgldok = $("#tglDokJo").val();
    idbas1 = "3899";
    idbas2 = "3900";
    kodeakun1 = "1.1.01.03.01.003.01";
    kodeakun2 = "1.1.01.03.01.003.02";
    bkututup = "0";
    bkustatus = "1";
    var varurl = getbasepath() + "/bkubop/json/prosessimpanc12";
    var dataac = [];
    var datajour = {
        tahun: $("#tahun").val(),
        idsekolah: $("#idsekolah").val(),
        kodetransaksi: $('#kodeTransaksi').val(),
        nobukti: $("#noBuktiDok").val(),
        tgldok: tgldok,
        jenis: "3",
        beban: "TU",
        nilai: nilai,
        fileinbox: fileinbox,
        nrk: $("#nrkPptk").val(),
        namapptk: $("#namaPptk").val(),
        nippptk: $("#nipPptk").val(),
        uraian: uraian,
        idbas1: idbas1,
        idbas2: idbas2,
        kodeakun1: kodeakun1,
        kodeakun2: kodeakun2,
        triwulan: $("#triwulan").val(),
        bkututup: bkututup,
        bkustatus: bkustatus
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
        $('#btnSimpan').attr("disabled", false);
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        window.location.href = getbasepath() + "/bkubop/indexbkubop"; // ke form index
    });
}
function simpanJO() {
    var fileinbox = $("#fileInbox").val();
    var kodetrx = $('#kodeTransaksi').val();
    var uraian = $("#uraian").val();
    var jenisbayar = $("#jenisPembayaran").val();
    var nilaimasuk, nilaikeluar;
    var nilaibku = (accounting.unformat($("#nilaibku").val(), ",")).toString();
    var nilaijg = (accounting.unformat($("#nilaijg").val(), ",")).toString();
    var idbas, kodeakun, bkututup, bkustatus, tgldok;
    if (kodetrx == "JO") {
        tgldok = $("#tglDokJo").val();
    } else {
        tgldok = $("#tglDok").val();
    }

    if (kodetrx == "JO") {
        nilaimasuk = nilaibku;
        nilaikeluar = "0";
        idbas = "12780";
        kodeakun = "1.1.01.03.05.001.02";
        bkututup = "1";
        bkustatus = "3";
    } else if (kodetrx == "JG") {
        idbas = "12781";
        kodeakun = "1.1.01.03.05.001.03";
        if (jenisbayar == "PN") { // PENERIMAAN JASA GIRO
            nilaimasuk = nilaijg;
            nilaikeluar = "0";
            bkututup = "1";
            bkustatus = "3";
        } else { // PENGELUARAN JASA GIRO
            nilaimasuk = "0";
            nilaikeluar = nilaijg;
            bkututup = "0";
            bkustatus = "1"
        }

    } else if (kodetrx == "RT") {
        idbas = "12780";
        kodeakun = "1.1.01.03.05.001.02";
        if (jenisbayar == "PN") { // PENERIMAAN RT
            nilaimasuk = nilaijg;
            nilaikeluar = "0";
            bkututup = "1";
            bkustatus = "3";
        } else { // PENGELUARAN RT
            nilaimasuk = "0";
            nilaikeluar = nilaijg;
            bkututup = "0";
            bkustatus = "1"
        }

    } else if (kodetrx == "ST") {
        nilaimasuk = "0";
        nilaikeluar = nilaijg;
        idbas = "12780";
        kodeakun = "1.1.01.03.05.001.02";
        bkututup = "0";
        bkustatus = "1";
    } else if (kodetrx == "RT") {
        nilaimasuk = nilaibku;
        nilaikeluar = "0";
        idbas = "12780";
        kodeakun = "1.1.01.03.05.001.02";
        bkututup = "1";
        bkustatus = "3";
    }


    var varurl = getbasepath() + "/bkubop/json/prosessimpanjo";
    var dataac = [];
    var datajour = {
        tahun: $("#tahun").val(),
        idsekolah: $("#idsekolah").val(),
        kodetransaksi: $('#kodeTransaksi').val(), nobukti: $("#noBuktiDok").val(),
        tgldok: tgldok, jenis: "3",
        beban: "TU",
        nilaikeluar: nilaikeluar, nilaimasuk: nilaimasuk,
        fileinbox: fileinbox,
        nrk: $("#nrkPptk").val(),
        namapptk: $("#namaPptk").val(),
        nippptk: $("#nipPptk").val(),
        uraian: uraian,
        carabayar: $("#kodePembayaran").val(),
        idbas: idbas,
        kodeakun: kodeakun,
        triwulan: $("#triwulan").val(),
        nobkuref: $("#nobkuref").val(),
        bkututup: bkututup,
        bkustatus: bkustatus,
        jenisbayar: jenisbayar,
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
        $('#btnSimpan').attr("disabled", false);
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        window.location.href = getbasepath() + "/bkubop/indexbkubop"; // ke form index
    });
}

function simpanPajak() {
    var tahun = $("#tahun").val();
    var fileinbox = $("#fileInbox").val();
    var jenisbayar = $("#jenisPembayaran").val();
    var kodekjs = $("#kodekjs").val();
    var kodemap = $("#kodemap").val();
    var persen = '0';
    var jumlah, bkututup, bkustatus;
    if ($('#kodeTransaksi').val() == 'P4' && $('#rumuspersenp4').val() == '0.5') {
        persen = $('#rumuspersenp4').val();
    }
    var masapajak = $("#masapajak1").val().toString() + $("#masapajak2").val().toString();
    var tahunpajak = $("#tahunpajak").val();

    if ($('#kodeTransaksi').val() == 'P1') {
        masapajak = konversimasapajak($("#masapajak1").val().toString() + $("#masapajak2").val().toString());
        if ($("#masapajak1").val().toString() + $("#masapajak2").val().toString() == '0101') {
            tahunpajak = $("#tahunpajak").val() - 1;
        }
    }

    if (jenisbayar == "PN") {
        jumlah = jumbarispjpn;
        //idblrinci = $("#idrinci").val();
        bkututup = "1";
        bkustatus = "3";
    } else {
        jumlah = jumbarispjpg;
        //idblrinci = $("#idblrinci1").val(); // dari grid
        bkututup = "0";
        bkustatus = "1";
    }


    if (jumlah > 0) {

        var varurl = getbasepath() + "/bkubop/json/prosessimpanpajak";
        var dataac = [];
        var nilailist = [];
        var i;
        var banyak = 0;
        var nilaimasuk, nilaikeluar, jenisbayarpajak;
        for (i = 0; i < jumlah; i++) { // list
            var id = i + 1;
            var nilai = parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ","));
            if (jenisbayar == "PN") {
                nilaimasuk = nilai.toString();
                nilaikeluar = "0";
                jenisbayarpajak = $("#jenisBayarPajak").val();
            } else {
                nilaimasuk = "0";
                nilaikeluar = nilai.toString();
                jenisbayarpajak = "1";
            }

            if (nilai > 0) {

                var pararr = {
                    nilaimasuk: nilaimasuk,
                    nilaikeluar: nilaikeluar,
                    idbas: $("#idbas" + id).val(),
                    kodeakun: $("#kodeakun" + id).val(),
                    idbkurinci: "0",
                    idkomponen: $("#idkomponen" + id).val(),
                    idblrinci: $("#idblrinci" + id).val()

                };
                nilailist[banyak] = pararr;
                banyak = banyak + 1;
            }

        }

        var datajour = {
            tahun: $("#tahun").val(),
            idsekolah: $("#idsekolah").val(),
            kodetransaksi: $('#kodeTransaksi').val(),
            nobukti: $("#noBuktiDok").val(),
            tgldok: $("#tglDok").val(),
            jenis: "3",
            beban: "TU",
            fileinbox: fileinbox,
            nrk: $("#nrkPptk").val(),
            namapptk: $("#namaPptk").val(),
            nippptk: $("#nipPptk").val(),
            uraian: $("#uraian").val(),
            carabayar: $("#kodePembayaran").val(),
            idkegiatan: $("#idKegiatanPj").val(),
            kodekeg: $("#kodeKegPj").val(),
            namarekan: $("#namarekanan").val(),
            npwp: $("#npwp").val(),
            namanpwp: $("#namanpwp").val(),
            alamatnpwp: $("#alamatnpwp").val(),
            kotanpwp: $("#kotanpwp").val(),
            masapajak: masapajak,
            tahunpajak: tahunpajak,
            triwulan: $("#triwulan").val(),
            nobkuref: $("#nobkuref").val(),
            idbkuref: $("#idbku").val(),
            jenisbayarpajak: jenisbayarpajak,
            bkututup: bkututup,
            bkustatus: bkustatus,
            jenisbayar: jenisbayar,
            kodemap: kodemap,
            kodekjs: kodekjs,
            persen: persen,
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
            console.log("status procedure" + data);
            cleardata();
            clearrow();
            $('#sumpjpn').autoNumeric('set', 0);
            bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
            $('#btnSimpan').attr("disabled", false);
            //window.location.href = getbasepath() + "/bkubop/indexbkubop"; // ke form index
        });
    } else {
        $('#btnSimpan').attr("disabled", false);
    }
}

function simpanSPJ() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var fileinbox = $("#fileInbox").val();
    var bulan, imcb;
    var sk = $("#cmbsk").val();
    var kodesk = "-";
    if (sk == 1) {
        kodesk = $("#kodesk").val();
    }
    var pns = "-";
    var golongan = "-";

    var belanja = $('#belanja').val();
    var pegawai = "-";
    var periode = "-";
    if (belanja == "1") {
        pns = $('#pns').val();
        golongan = "0";
        if (pns == "1") {
            golongan = $('#golongan').val();
        } else {
            pegawai = $('#pegawai').val();
            if (pegawai == "1") {
                periode = $('#periode').val()
            }
        }
    }
    var pkp = '0';
    var checked = $('#cmbpkp').val();
    if (checked == "on") {
        pkp = '1';
    }
    var ptkp = '-';
    if ($('#ptkp').val() != null && $('#ptkp').val() != '-') {
        ptkp = $('#ptkp').val();
    }
    var npwp = $('#npwp').val();
    var namanpwp = $('#namanpwp').val();
    var alamatnpwp = $('#alamatnpwp').val();
    var kotanpwp = $('#kotanpwp').val();

    if ($("#kodeTalangan").val() == "1") {
        bulan = $("#bulan").val();
        imcb = $("#cbmcb").val();
    } else {
        bulan = "-";
        imcb = "-";
    }

    if (jumbarisspj > 0) {
        var varurl = getbasepath() + "/bkubop/json/prosessimpanspj";
        var dataac = [];
        var nilailist = [];
        var i;
        var banyak = 0;
        if ($('#kodeTransaksi').val() !== "JJ") {
            statusspj = 0;
        }

        for (i = 0; i < jumbarisspj; i++) { // list
            var id = i + 1;
            var nilai = parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ","));
            var kodeakun = $('#kodeakun' + id).val().substr(0, 5);
            var volume;
            if (kodeakun == "5.2.3") {
                volume = $("#volume" + id).val();
            } else {
                volume = "0";
            }

            if (nilai > 0) {

                var pararr = {
                    nilaimasuk: "0",
                    nilaikeluar: parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ",")).toString(),
                    idbas: $("#idbas" + id).val(),
                    kodeakun: $("#kodeakun" + id).val(),
                    idkomponen: $("#idkomponen" + id).val(),
                    idblrinci: $("#idblrinci" + id).val(),
                    nourut: $("#nourut" + id).val(),
                    namasubkeg: $("#namasubkeg" + id).val(),
                    komponenpajak: $("#komponenpajak" + id).val(),
                    volume: volume,
                    hargasatuan: $("#hargasatuan" + id).val(),
                    ketrinci: $("#ketrinci" + id).val()

                };
                nilailist[banyak] = pararr;
                banyak = banyak + 1;
            }


        }

        var datajour = {
            tahun: $("#tahun").val(),
            idsekolah: $("#idsekolah").val(),
            kodetransaksi: $('#kodeTransaksi').val(),
            nobukti: $("#noBuktiDok").val(),
            tgldok: $("#tglDok").val(),
            jenis: "3",
            beban: "TU",
            fileinbox: fileinbox,
            nrk: $("#nrkPptk").val(),
            namapptk: $("#namaPptk").val(), nippptk: $("#nipPptk").val(),
            uraian: $("#uraian").val(),
            carabayar: $("#kodePembayaran").val(),
            idkegiatan: $("#idKegiatanSpj").val(),
            kodekeg: $("#kodeKegSpj").val(),
            norek: $("#rekeningBank").val().trim(),
            namarekan: $("#namarekanan").val(),
            kodebank: $("#kodeBank").val(),
            kodebanktf: $("#kodeBankTf").val(),
            namabanktf: $("#namaBank").val(),
            kodeva: $('#kodeVA').val(),
            triwulan: $("#triwulan").val(),
            kodetalangan: $("#kodeTalangan").val(),
            bulan: bulan,
            imcb: imcb,
            statusspj: statusspj.toString(),
            kodesk: kodesk,
            pns: golongan,
            belanja: belanja,
            pegawai: pegawai,
            pkp: pkp,
            ptkp: ptkp,
            npwp: npwp,
            namanpwp: namanpwp,
            alamatnpwp: alamatnpwp,
            kotanpwp: kotanpwp,
            periode: periode,
            nilailist: nilailist
        };
        dataac = datajour;
        return   $.ajax({
            type: "POST",
            url: varurl, contentType: "text/plain; charset=utf-8",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(dataac)
        }).always(function(data) {

            cleardata();
            clearrow();
            getSisaKasSpjTambahan();
            bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
            $('#btnSimpan').attr("disabled", false);
//            window.location.href = getbasepath() + "/bkubop/indexbkubop"; // ke form index

        });
    } else {
        $('#btnSimpan').attr("disabled", false);
    }
}


function gridspj() {
    jumbarisspj = 0;
    document.getElementById("labeltalangan").style.display = "none";
    document.getElementById("labelmcb").style.display = "none";
    document.getElementById("labelbulan").style.display = "none";
    if (typeof myTable == 'undefined') {
        myTable = $('#spjtable').dataTable({"bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[500, 1000, 2000, -1], [500, 1000, 2000, "All"]],
            "iDisplayLength": 2000,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkubop/json/listspj",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            }, "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "nobkumohon", value: $("#noBkuMohon").val()},
                {name: "triwulan", value: $("#triwulan").val()},
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
                var ketrincival, namasub;
                if (aData['keteranganRinci'] == null) {
                    ketrincival = "";
                } else {
                    ketrincival = aData['keteranganRinci'];
                }

                if (aData['namaSubKegiatan'] == null) {
                    namasub = "";
                } else {
                    namasub = aData['namaSubKegiatan'];
                }

                var nilaisebelumtw = "<input type='hidden' id='nilaisebelumtw" + index + "' value='" + aData['nilaiBkuSebelumTw'] + "' />";
                var paguakun = "<input type='hidden' id='paguakun" + index + "' value='" + aData['paguAkun'] + "' />";
                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var idbku = "<input type='hidden' id='idbku" + index + "' value='" + aData['idBku'] + "' />";
                var idkomponen = "<input type='hidden' id='idkomponen" + index + "' value='" + aData['idKomponen'] + "' />";
                var idblrinci = "<input type='hidden' id='idblrinci" + index + "' value='" + aData['idBlRinci'] + "' />";
                var nourut = "<input type='hidden' id='nourut" + index + "' value='" + aData['noUrut'] + "' />";
                var namasubkeg = "<input type='hidden' id='namasubkeg" + index + "' value='" + namasub + "' />";
                var ketrinci = "<input type='hidden' id='ketrinci" + index + "' value='" + ketrincival + "' />";
                var komponenpajak = "<input type='hidden' id='komponenpajak" + index + "' value='" + aData['komponenPajak'] + "' />";
                //var volume = "<input type='text' size='5' onkeypress='return isNumber(event)' maxlength='10' id='volume" + index + "' value='" + aData['volume'] + "' />";
                var hargasatuan = "<input type='hidden' id='hargasatuan" + index + "' value='" + aData['hargaSatuan'] + "' />";
                var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";
                var komponen = "<textarea id='komponen" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['namaKomponen'] + "</textarea>"; // aData['kodeKomponen'] + "/" +
                var ketrincitext = "<textarea id='ketrincitext" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['spekKomponen'] + " / " + namasub + " / " + ketrincival + "</textarea>";
                var nilaiAngg = "<input type='text' name='nilaiAnggaran" + index + "' id='nilaiAnggaran" + index + "'  class='inputmoney'  value='" + aData['nilaiAnggaran'] + "' readOnly='true' />";
                var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSebelum'] + "' readOnly='true' />";
                var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['nilaiSisa'] + "' readOnly='true' />";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' onkeyup='pasangvalidatebesarperfield(" + index + ")' onchange='pasangvalidatebesarperfield(" + index + ")' />";
                if (aData['kodeakun'] == '5.2.2.03.03') {
                    document.getElementById("labeltalangan").style.display = "block";
                }

                var volume = "";
                if (aData['kodeakun'].substr(0, 5) == '5.2.3') {
                    volume = "<input type='text' size='5' onkeypress='return isNumber(event)' maxlength='10' id='volume" + index + "' value='" + aData['volume'] + "' />";
                }

                $('td:eq(0)', nRow).html(index + idbku + idblrinci + paguakun + nilaisebelumtw);
                $('td:eq(1)', nRow).html(akun + idbas + textkodeakun);
                $('td:eq(2)', nRow).html(komponen + idkomponen);
                $('td:eq(3)', nRow).html(ketrincitext + ketrinci + nourut + namasubkeg + komponenpajak + hargasatuan);
                $('td:eq(4)', nRow).html(nilaiAngg);
                $('td:eq(5)', nRow).html(nilaisebelum);
                $('td:eq(6)', nRow).html(nilaisisa);
                $('td:eq(7)', nRow).html(volume);
                $('td:eq(8)', nRow).html(nilaiinput);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center", "sWidth": "17%"},
                {"mDataProp": "kodeKomponen", "bSortable": false, sClass: "center", "sWidth": "16%"},
                {"mDataProp": "keteranganRinci", "bSortable": false, sClass: "center", "sWidth": "18%"},
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right", "sWidth": "10%"},
                {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right", "sWidth": "10%"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right", "sWidth": "10%"},
                {"mDataProp": "volume", "bSortable": false, sClass: "right", "sWidth": "5%"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right", "sWidth": "10%"}
            ]
        });
    } else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

    $("#sumspj").val(0);
    $("#totalspjhidden").val(0);
}


function pasangvalidatebesarperfield(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaisisa = accounting.unformat($("#nilaisisa" + index).val(), ",");
    var paguakun = accounting.unformat($("#paguakun" + index).val(), ",");
    var totalakun = 0;
    var totalsebelum = 0;
    var sisaakun = 0;

    for (var a = 1; a <= jumbarisspj; a++) {
        if ($("#idbas" + index).val() == $("#idbas" + a).val()) {
            totalakun += accounting.unformat($("#nilaiinput" + a).val(), ","); // nilai inputan untuk akun tsb
            totalsebelum += accounting.unformat($("#nilaisebelumtw" + a).val(), ","); // nilai yang pernah dikeluarkan untuk akun tsb dan untuk tw tsb
        }
    }

    sisaakun = paguakun - totalsebelum; // pagu akun (tw) dikurangi total spj untuk akun tsb untuk tw tsb

    if (totalakun > sisaakun) {
        bootbox.alert("Nilai SPJ tidak boleh lebih besar dari Sisa Anggaran Per-Kode Rekening Triwulan Ini.");
        $('#nilaiinput' + index).autoNumeric('set', 0); // nilaisisa harus yang di unformat :)
    }

    if (nilaiinput > nilaisisa) {
        bootbox.alert("Nilai SPJ tidak boleh lebih besar dari Sisa Anggaran.");
        //$('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
        $('#nilaiinput' + index).autoNumeric('set', nilaisisa); // nilaisisa harus yang di unformat :)
    }

    hitungnilaispj();
}

function hitungnilaispj() {
    var total = 0;
    for (var a = 1; a <= jumbarisspj; a++) {
        total += parseFloat(accounting.unformat($("#nilaiinput" + a).val(), ","));
    }

    $("#sumspj").val(accounting.formatNumber(total, 2, '.', ","));
    $("#totalspjhidden").val(total);
}

function setMasaPajak2() {
    var bulanmp1 = $('#masapajak1').val();
    $('#masapajak2').val(bulanmp1);
}

function gridpjpn() {
    jumbarispjpn = 0;
    if (typeof myTablePajak == 'undefined') {
        myTablePajak = $('#pajaktable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap", "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[50, 100, 500, -1], [50, 100, 500, "All"]],
            "iDisplayLength": 500,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkubop/json/listpajakspj",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push({name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "triwulan", value: $("#triwulan").val()},
                {name: "nobkumohon", value: $("#noBkuMohon").val()},
                {name: "nobkuref", value: $("#nobkuref").val()},
                {name: "idbku", value: $("#idbku").val()},
                {name: "jenispajak", value: $("#kodeTransaksi").val()}
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
                var rumus = "";
                var jenispajak = $("#kodeTransaksi").val();
                jumbarispjpn = jumbarispjpn + 1;
                if (jenispajak == "P1") {
//                    rumus = "<select id='ppnP1" + index + "' name='ppnP1" + index + "' onchange=hitungP1(" + index + ") ><option value='-'>Pilih</option><option value='1'>NPWP</option><option value='2'>NON NPWP</option></select> &nbsp <select id='persenP1" + index + "' name='persenP1" + index + "' onchange=hitungP1(" + index + ") ><option value='-'>Pilih</option></select>";
                } else if (jenispajak == "P2") {
                    rumus = "<select id='ppnP2" + index + "' name='ppnP2" + index + "' onchange=hitungP2(this.value," + index + ") ><option value='-'>Pilih</option><option value='0'>TIDAK ADA PPN</option><option value='1'>ADA PPN</option></select>";
                } else if (jenispajak == "P3") {
                    rumus = "<select id='ppnP3" + index + "' name='ppnP3" + index + "' onchange=hitungP3(" + index + ") ><option value='-'>Pilih</option><option value='0'>TIDAK ADA PPN</option><option value='1'>ADA PPN</option></select> &nbsp <select id='persenP3" + index + "' name='persenP3" + index + "' onchange=hitungP3(" + index + ") ><option value='-'>Pilih</option><option value='2'>2%</option></select>";
                } else if (jenispajak == "P4") {
                    rumus = "<select id='ppnP4" + index + "' name='ppnP4" + index + "' onchange=hitungP4(" + index + ") ><option value='-'>Pilih</option><option value='0'>TIDAK ADA PPN</option><option value='1'>ADA PPN</option></select> &nbsp <select id='persenP4" + index + "' name='persenP4" + index + "' onchange=hitungP4(" + index + ") ><option value='-'>Pilih</option><option value='0.5'>0.5%</option></select>";
                } else if (jenispajak == "P5") {
                    rumus = "<select id='persenP5" + index + "' name='persenP5" + index + "' onchange=hitungP5(this.value," + index + ")><option value='-'>Pilih</option><option value='1'>1%</option><option value='10'>10%</option></select>";
                }

                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var idblrinci = "<input type='hidden' id='idblrinci" + index + "' value='" + aData['idBlRinci'] + "' />";
                var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var idkomponen = "<input type='hidden' id='idkomponen" + index + "' value='" + aData['idKomponen'] + "' />";
                var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:350px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";
                var komponen = "<textarea id='komponen" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['namaKomponen'] + "</textarea>";
                var nilaispj = "<input type='text' name='nilaispj" + index + "' id='nilaispj" + index + "'  class='inputmoney'  value='" + aData['nilaiSpj'] + "' readOnly='true' />";
                var nilainetto = "<input type='text' name='nilainetto" + index + "' id='nilainetto" + index + "'  class='inputmoney'  value='" + aData['nilaiNettoSpj'] + "' readOnly='true' />";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiPajakSpj'] + "' onkeyup='pasangvalidasinilaipjpn(" + index + "); hitungTotalPjPn()' onchange='pasangvalidasinilaipjpn(" + index + "); hitungTotalPjPn()' />";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(akun + idbas + textkodeakun);
                $('td:eq(2)', nRow).html(komponen + idkomponen + idblrinci);
                $('td:eq(3)', nRow).html(nilaispj);
                $('td:eq(4)', nRow).html(nilainetto);
                $('td:eq(5)', nRow).html(rumus);
                $('td:eq(6)', nRow).html(nilaiinput);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "right", "sWidth": "25%"},
                {"mDataProp": "namaKomponen", "bSortable": false, sClass: "right", "sWidth": "25%"},
                {"mDataProp": "nilaiSpj", "bSortable": false, sClass: "right", "sWidth": "13%"},
                {"mDataProp": "idKomponen", "bSortable": false, sClass: "center", "sWidth": "13%"},
                {"mDataProp": "nilaiPajakSpj", "bSortable": false, sClass: "center", "sWidth": "7%"},
                {"mDataProp": "idBkuRinci", "bSortable": false, sClass: "right", "sWidth": "13%"}
            ]
        });
    } else
    {
        myTablePajak.fnClearTable(0);
        myTablePajak.fnDraw();
    }
}

function setPersenP1(rumus) {
    var opt = "";
    if ($("#keteranganKegPj").val() == "") {
        $("#rumuspajakp1").val("-");
        bootbox.alert("Pilih Kegiatan Terlebih Dahulu");
    } else {
        if (rumus == "1") {
            opt = '<option value="-">Pilih</option>';
            opt += '<option value="5">5%</option>';
            opt += '<option value="10">10%</option>';
            opt += '<option value="15">15%</option>';
        } else if (rumus == "2") {
            opt = '<option value="-">Pilih</option>';
            opt += '<option value="6">6%</option>';
            opt += '<option value="12">12%</option>';
            opt += '<option value="18">18%</option>';
        } else {
            opt = "";
        }

        $("#rumuspersenp1").html(opt);
    }

}

function setPnP1() {
    var opt1 = "";
    var opt2 = "";
    if ($("#keteranganKegPj").val() == "") {
        $("#rumuspajakp1").val("-");
        bootbox.alert("Pilih Kegiatan Terlebih Dahulu");
    } else {
        for (var i = 1; i <= jumbarispjpn; i++) {
            hitungP1(i);
        }
    }

}

function setPnP2(persen) {
    if ($("#keteranganKegPj").val() == "") {
        $("#rumuspajakp2").val("-");
        bootbox.alert("Pilih Kegiatan Terlebih Dahulu");
    } else {
        for (var i = 1; i <= jumbarispjpn; i++) {
            $("#ppnP2" + i).val(persen);
            hitungP2(persen, i);
        }
    }
}

function setPnP3() {

    if ($("#keteranganKegPj").val() == "") {
        $("#rumuspajakp3").val("-");
        bootbox.alert("Pilih Kegiatan Terlebih Dahulu");
    } else {
        if ($("#rumuspajakp3").val() !== "-" && $("#rumuspersenp3").val() !== "-") {
            for (var i = 1; i <= jumbarispjpn; i++) {
                $("#ppnP3" + i).val($("#rumuspajakp3").val());
                $("#persenP3" + i).val($("#rumuspersenp3").val());
                hitungP3(i);
            }
        }

    }
}
function setPnP4() {

    if ($("#keteranganKegPj").val() == "") {
        $("#rumuspajakp4").val("-");
        bootbox.alert("Pilih Kegiatan Terlebih Dahulu");
    } else {
        if ($("#rumuspajakp4").val() !== "-" && $("#rumuspersenp4").val() !== "-") {
            for (var i = 1; i <= jumbarispjpn; i++) {
                $("#ppnP4" + i).val($("#rumuspajakp4").val());
                $("#persenP4" + i).val($("#rumuspersenp4").val());
                hitungP4(i);
            }
        }

    }
}

function setPnP5(persen) {

    if ($("#keteranganKegPj").val() == "") {
        $("#rumuspajakp5").val("-");
        bootbox.alert("Pilih Kegiatan Terlebih Dahulu");
    } else {
        for (var i = 1; i <= jumbarispjpn; i++) {
            $("#persenP5" + i).val(persen);
            hitungP5(persen, i);
        }
    }

}

function hitungNetto(index) {
    var nilaispj = accounting.unformat($("#nilaispj" + index).val(), ",");
    var pajak = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var netto = nilaispj - pajak;
    $("#nilainetto" + index).val(accounting.formatNumber(Math.round(netto), 2, '.', ","));
}

function hitungP1(index) {
    var hasil, netto;
    var nilai = accounting.unformat($("#nilaispj" + index).val(), ",");
    var kodepns = $("#cpns").val(), kodepegawai = $("#cpegawai").val();
    var pengali;
    if ($("#cnpwp").val() == null || $("#cnpwp").val() == '') {
        pengali = 1.2;
    } else {
        pengali = 1.0;
    }
    var varurl = "/PAJAK/hitungpajak/json/pph21";
    var dataac = [];
    var datajour = {
        nilai: nilai,
        kodepns: kodepns,
        kodepegawai: kodepegawai,
        pengali: pengali
    };
    dataac = datajour;
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
            hasil = result;
            netto = nilai - hasil;
            $('#nilaiinput' + index).autoNumeric('set', Math.round(hasil));
            pasangvalidasinilaipjpn(index);
        },
        error: function() {
            //bootbox.alert('boo!');
        }
    });
}

function hitungP2(ppn, index) {
    var hasil, netto;
    var nilai = accounting.unformat($("#nilaispj" + index).val(), ",");
    var persen, pengali, pengalinpwp;
    persen = 1.5;
    if (ppn != '-') {
        if (ppn == '1') {
            pengali = 1.1;
        } else {
            pengali = 1.0;
        }
        if ($("#cnpwp").val() != null && $("#cnpwp").val() != '') {
            pengalinpwp = 1.0;
        } else {
            pengalinpwp = 2.0;
        }

        var varurl = "/PAJAK/hitungpajak/json/pph22";
        var dataac = [];
        var datajour = {
            nilai: nilai,
            persen: persen,
            pengali: pengali,
            pengalinpwp: pengalinpwp
        };
        dataac = datajour;
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
                hasil = result;
                netto = nilai - hasil;
                $('#nilaiinput' + index).autoNumeric('set', Math.round(hasil));
                pasangvalidasinilaipjpn(index);
            },
            error: function() {
                //bootbox.alert('boo!');
            }
        });
    } else {
        hasil = 0;
        netto = nilai - hasil;
        pasangvalidasinilaipjpn(index);
    }

    //$('#nilaiinput' + index).val(accounting.formatNumber(Math.round(hasil), 2, '.', ","));
    //$("#nilainetto" + index).val(accounting.formatNumber(Math.round(netto), 2, '.', ","));
    //hitungTotalPjPn();
}

function hitungP3(index) {
    var hasil, netto;
    var persen = parseFloat($('#persenP3' + index).val());
    var ppn = $('#ppnP3' + index).val();
    var pengali;
    var pengalinpwp;
    var nilai = accounting.unformat($("#nilaispj" + index).val(), ",");
    if (ppn == "-" || persen == "-") {
        hasil = 0;
        netto = nilai - hasil;
        $('#nilaiinput' + index).autoNumeric('set', Math.round(hasil));
        pasangvalidasinilaipjpn(index);
    } else {
        if (ppn == '1') {
            pengali = 1.1;
        } else {
            pengali = 1.0;
        }
        if ($("#cnpwp").val() != null && $("#cnpwp").val() != '') {
            pengalinpwp = 1.0;
        } else {
            pengalinpwp = 2.0;
        }

        var varurl = "/PAJAK/hitungpajak/json/pph23";
        var dataac = [];
        var datajour = {
            nilai: nilai,
            persen: persen,
            pengali: pengali,
            pengalinpwp: pengalinpwp
        };
        dataac = datajour;
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
                hasil = result;
                netto = nilai - hasil;
                $('#nilaiinput' + index).autoNumeric('set', Math.round(hasil));
                pasangvalidasinilaipjpn(index);
            },
            error: function() {
                //bootbox.alert('boo!');
            }
        });


        //console.log("pengali = "+pengali);
    }

    //$("#nilainetto" + index).val(accounting.formatNumber(Math.round(netto), 2, '.', ","));
    //$('#nilaiinput' + index).val(accounting.formatNumber(Math.round(hasil), 2, '.', ","));
    //hitungTotalPjPn();
}
function hitungP4(index) {
    var hasil, netto;
    var persen = parseFloat($('#persenP4' + index).val());
    var ppn = $('#ppnP4' + index).val();
    var pengali;
    var nilai = accounting.unformat($("#nilaispj" + index).val(), ",");
    if (ppn == "-" || persen == "-") {
        hasil = 0;
        netto = nilai - hasil;
        $('#nilaiinput' + index).autoNumeric('set', Math.round(hasil));
        pasangvalidasinilaipjpn(index);
    } else {
        if (ppn == '1') {
            pengali = 1.1;
        } else {
            pengali = 1.0;
        }

        var varurl = "/PAJAK/hitungpajak/json/pph24";
        var dataac = [];
        var datajour = {
            nilai: nilai,
            persen: persen,
            pengali: pengali
        };
        dataac = datajour;
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
                hasil = result;
                netto = nilai - hasil;
                $('#nilaiinput' + index).autoNumeric('set', Math.round(hasil));
                pasangvalidasinilaipjpn(index);
            },
            error: function() {
                //bootbox.alert('boo!');
            }
        });


        //console.log("pengali = "+pengali);
    }
}

function hitungP5(ppn, index) {
    //
    var hasil, netto;

    var pengali;
    var persen;
    var nilai = accounting.unformat($("#nilaispj" + index).val(), ",");
    if (ppn != '-') {
        if (ppn == '1') {
            pengali = 1.01;
            persen = 1;
        } else {
            pengali = 1.1;
            persen = 10;
        }

        var varurl = "/PAJAK/hitungpajak/json/ppn";
        var dataac = [];
        var datajour = {
            nilai: nilai,
            persen: persen,
            pengali: pengali
        };
        dataac = datajour;
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
                hasil = result;
                netto = nilai - hasil;
                $('#nilaiinput' + index).autoNumeric('set', Math.round(hasil));
                pasangvalidasinilaipjpn(index);
            },
            error: function() {
                //bootbox.alert('boo!');
            }
        });
    } else {
        hasil = 0;
        netto = nilai - hasil;
        $('#nilaiinput' + index).autoNumeric('set', Math.round(hasil));
        pasangvalidasinilaipjpn(index);
    }

}

function pasangvalidasinilaipjpn(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    //var nilaisisa = accounting.unformat($("#nilaispj" + index).val(), ",");
    var nilaisisa = accounting.unformat($("#nilainetto" + index).val(), ",");
    //var total = 0;

    if (nilaiinput > nilaisisa) {
        bootbox.alert("Nilai Pajak tidak boleh lebih besar dari Nilai Sisa SPJ.");
        //$('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));

        if (nilaisisa < 0) {
            $('#nilaiinput' + index).autoNumeric('set', 0);
        } else {
            $('#nilaiinput' + index).autoNumeric('set', nilaisisa); // nilaisisa harus yang di unformat :)
        }

    }

    hitungTotalPjPn();
}

function hitungTotalPjPn() {
    var total = 0;
    for (var a = 1; a <= jumbarispjpn; a++) {
        total += parseFloat(accounting.unformat($("#nilaiinput" + a).val(), ","));
    }

//$("#sumpjpn").val(accounting.formatNumber(total, 2, '.', ","));
    $('#sumpjpn').autoNumeric('set', total);
}

function getDataWP() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/bkubop/json/getDataWP", {tahun: tahun, idsekolah: idsekolah},
    function(result) {

        if (result.aData !== null) { // jika ada datanya
            var npwp = result.aData['npwp'];
            var nama = result.aData['namaRekan'];
            $('#npwp').val(npwp);
            $('#namarekanan').val(nama);
            getDataNPWP();
        } else {
            bootbox.alert("Data Referensi NPWP Sekolah Tidak Tersedia, Silahkan Isi Data Referensi Terlebih Dulu.");
        }

    });
}

function gridpjpg() {
    jumbarispjpg = 0;
    if (typeof myTablePajak2 == 'undefined') {
        myTablePajak2 = $('#pajakpgtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[50, 100, 200, -1], [50, 100, 200, "All"]],
            "iDisplayLength": 200,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkubop/json/listpajakpg",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "nobkumohon", value: $("#nobkuref").val()},
                {name: "jenispajak", value: $("#kodeTransaksi").val()}
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
                jumbarispjpg = jumbarispjpg + 1;
                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var idkomponen = "<input type='hidden' id='idkomponen" + index + "' value='" + aData['idKomponen'] + "' />";
                var idbl = "<input type='hidden' id='idblrinci" + index + "' value='" + aData['idBlRinci'] + "' />";
                var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:500px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";
                var komponen = "<textarea id='komponen" + index + "'readonly style='border:none;margin:0;width:450px;'>" + aData['namaKomponen'] + "</textarea>";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiPajakSpj'] + "' readOnly='true' />";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(akun + idbas + textkodeakun);
                $('td:eq(2)', nRow).html(komponen + idkomponen + idbl);
                $('td:eq(3)', nRow).html(nilaiinput);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaKomponen", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiPajakSpj", "bSortable": false, sClass: "center"}
            ]
        });
    } else
    {
        myTablePajak2.fnClearTable(0);
        myTablePajak2.fnDraw();
    }

}

function getPkBlj() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/bkubop/json/getPkBlj", {tahun: tahun, idsekolah: idsekolah},
    function(result) {

        if (result.aData !== null) { // jika ada datanya
            namapkblj = result.aData['namaPptk'];
            ('#namaPptk').val(result.aData['namaPptk']);
            nrkpkblj = result.aData['nrkPptk'];
            nippkblj = result.aData['nipPptk'];
        }
    });
}

function setJgSt() {
    var a = document.getElementById("pilihjg");
    var kodetrans = $("#kodeTransaksi").val();
    if (kodetrans == "ST") {
        a.href = getbasepath() + "/bkubop/listsetoran?target='_top'";
    } else if (kodetrans == "JG") {
        a.href = getbasepath() + "/bkubop/listjgpn?target='_top'";
    }

}

function getSisaKasSpjTambahan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var nobku = $('#noBkuMohon').val();
    var triwulan = $('#triwulan').val();
    // get sisa kas pagu per-kegiatan
    $.getJSON(getbasepath() + "/bkubop/json/getSisaKasSpj", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan, nobku: nobku},
    function(result) {

        var sisakas = result.aData['nilaiBku'];
        $('#sisakasspj').val(accounting.formatNumber(sisakas, 2, '.', ","));
    });
}


function setRumusPajak() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var jenisbayar = $('#jenisPembayaran').val();
    if (jenistransaksi.substr(0, 1) == "P") {

    }
}

function isNamaRekanan(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57) && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) && charCode !== 44 && charCode !== 46 && charCode !== 47 && charCode !== 32) {
        return false;
    }
    return true;
}

function konversimasapajak(masapajak) {
    var masapajakpph21 = '0000';

    switch (masapajak) {
        case '0101':
            masapajakpph21 = '1212';
            break;
        case '0202':
            masapajakpph21 = '0101';
            break;
        case '0303':
            masapajakpph21 = '0202';
            break;
        case '0404':
            masapajakpph21 = '0303';
            break;
        case '0505':
            masapajakpph21 = '0404';
            break;
        case '0606':
            masapajakpph21 = '0505';
            break;
        case '0707':
            masapajakpph21 = '0606';
            break;
        case '0808':
            masapajakpph21 = '0707';
            break;
        case '0909':
            masapajakpph21 = '0808';
            break;
        case '1010':
            masapajakpph21 = '0909';
            break;
        case '1111':
            masapajakpph21 = '1010';
            break;
        case '1212':
            masapajakpph21 = '1111';
    }

    return masapajakpph21;
}
$(document).ready(function() {
    setTglBayar();
    getListPajakPn();
    setCurrentDate();
    setPanel();
    setTujuan();
    getDataBankBOS();
    getToken();
    if ($('#kodeTalangan').val() == "1") {
        document.getElementById("cbTalangan").checked = true;
    } else {
        document.getElementById("labeltalangan").style.display = "none";
        document.getElementById("labelmcb").style.display = "none";
        document.getElementById("labelbulan").style.display = "none";
    }
});

var jumrowspj, kodestan, noreff, rbit4, rbit11, rbit12, rbit13, rbit37, update12, update13, update39;
var nrk;
var banyakpajak;
var idpajakpn = new Array();
var kodepajakpn = new Array();
var response76 = "00";
var count = 0;

function setTglBayar() {
    if ($('#kodeTransaksi').val().substr(0, 1) == "P") {
        $('#tglPosting').attr("disabled", false);
    } else {
        $('#tglPosting').attr("disabled", true);
    }
}


function setTalangan() {
    if (document.getElementById("cbTalangan").checked == true) {
        $('#kodeTalangan').val(1);
    } else {
        $('#kodeTalangan').val(0);
    }
}
var selisih;
function getToken() {
    var idsekolah = $('#idsekolah').val();
    var noBkuMohon = $('#noBkuMohon').val();
    $.getJSON(getbasepath() + "/token/json/getToken", {idsekolah: idsekolah, kodesumbdana: '1', noBkuMohon: noBkuMohon},
    function(result) {
        var data, kode, ket;
        data = result.aData;
        if (data != null) {
            $('#token').val(result.aData['token'])
            nrk = result.aData['nrk'];
            var waktudki = result.aData['dMohonDki'];
            var waktu = result.aData['dMohon'];
            var h, m, s;
            var hdki, mdki, sdki;
            h = parseInt(waktu.substr(11, 2)) * 3600000;
            m = parseInt(waktu.substr(14, 2)) * 60000;
            s = parseInt(waktu.substr(17, 2)) * 1000;
            var totalwaktu = h + m + s;
            hdki = parseInt(waktudki.substr(11, 2)) * 3600000;
            mdki = parseInt(waktudki.substr(14, 2)) * 60000;
            sdki = parseInt(waktudki.substr(17, 2)) * 1000;
            var totalwaktudki = hdki + mdki + sdki;

            selisih = totalwaktu - totalwaktudki;
            console.log("SELISIH " + selisih);
        }
    });
}
function validateTime() {
    $.getJSON(getbasepath() + "/token/json/getTimeLimit", {kodesumbdana: 1, nomohon: $('#noBkuMohon').val(), idsekolah: $('#idsekolah').val(), tahun: $('#tahun').val()},
    function(result) {
        var limit = result['dPayment'];
        console.log("ini limit " + limit)
        getDateDki(limit);
    });
}
function getDateDki(limit) {
    document.getElementById("btnSimpan").style.visibility = "hidden";
    var limithh, limitmm;
    limithh = parseInt(limit.substr(0, 2));
    limitmm = parseInt(limit.substr(3, 2));
    if (limitmm + 30 > 60) {
        limitmm -= 30;
        if (limithh + 1 <= 24) {
            limithh + 1;
        } else {
            limithh = 1;
        }
        limithh -= 1;
    } else {
        limitmm += 30;
    }
    limitmm -= 1;
    $.getJSON(getbasepath() + "/token/json/getDate", {},
            function(result) {
                var waktumentah = result.aData['dMohon'];
                var dd, mm, yyyy, h, m, s;
                dd = parseInt(waktumentah.substr(0, 2));
                mm = parseInt(waktumentah.substr(3, 2));
                yyyy = parseInt(waktumentah.substr(6, 4));
                var tanggal = new Date(yyyy, mm - 1, dd);
                h = parseInt(waktumentah.substr(11, 2)) * 3600000;
                m = parseInt(waktumentah.substr(14, 2)) * 60000;
                s = parseInt(waktumentah.substr(17, 2)) * 1000;
                var totalwaktu = h + m + s;

                var dkidate = totalwaktu - selisih;
                var deadline = (limithh * 3600000) + (limitmm * 60000) + (59 * 1000)
                console.log("deadline " + deadline);
                console.log("dki " + dkidate);
                console.log("waktuserver " + totalwaktu);
                if (dkidate > deadline || tanggal.getDay() == 0 || tanggal.getDay() == 6) {
                    console.log(true);
                    $('#validatetime').val(1);
                } else {
                    console.log(false);
                    $('#validatetime').val(0);
                }
                checkLibur();
            });
}
function setCurrentDate() {
    /*
     var today = new Date();
     var dd = today.getDate();
     var mm = today.getMonth() + 1; //January is 0!
     var yyyy = today.getFullYear();

     if (dd < 10) {
     dd = '0' + dd;
     }

     if (mm < 10) {
     mm = '0' + mm;
     }

     today = dd + '/' + mm + '/' + yyyy;
     $('#tglPosting').val(today);
     */

    $.getJSON(getbasepath() + "/token/json/getDate", {},
            function(result) {
                var date = result.aData['dMohon'];
                $('#tglPosting').val(date.substr(0, 10));
            });
}

function cekDataBankDki() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var kodebank = $('#kodeBankTf').val();
    var norek = $('#norekBank').val().replace(/\D/g, '');
    var kodeva = $('#kodeVA').val();

    $('#dkinamabank').val("");
    $('#dkikodebank').val("");
    $('#dkinorek').val("");
    $('#dkinama').val("");

    if (jenistransaksi.substr(0, 1) !== "P") {
        if ((norek !== "")) { // && (kodeva == "0")
            getDataBankDki();
        }
    }
}

function getDataBankDki() {
    var kodebank = $('#kodeBankTf').val();
    var norek = $('#norekBank').val().replace(/\D/g, '');

    var varurl = getbasepath() + "/postnorek/json/ceknorek";

    var dataac = [];

    var datajour = {
        kodebank: kodebank,
        norek: norek
    };
    dataac = datajour;

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {
            //console.log(data);
            if ($('#kodeBankTf').val() == '111') {
                $('#dkinamabank').val(data["namabank"]);
                $('#dkikodebank').val(data["kodebank"]);
                $('#dkinorek').val(data["norek"]);
                $('#dkinama').val(data["nama"].trim());
            } else {
                $('#dkinamabank').val(data["bank"]);
                $('#dkikodebank').val(data["code"]);
                $('#dkinorek').val(data["account"]);
                $('#dkinama').val(data["name"].trim());
                response76 = data["response"];
            }

        },
        error: function(xhr) {
            console.error(xhr);
            bootbox.alert("Sambungan Bank DKI Terputus");
            //$('#btnSimpan').attr("disabled", true);
        }
    }).always(function(data) {

    });

}

function setPanel() {
    var jenistransaksi = $('#kodeTransaksi').val();

    setGrid();

    document.getElementById("labelkegiatan").style.display = "none";
    document.getElementById("labelva").style.display = "none";
    document.getElementById("labelmasapajak").style.display = "none";
    document.getElementById("labeltahunpajak").style.display = "none";
    document.getElementById("labelnpwp").style.display = "none";
    document.getElementById("labelnamapenyetor").style.display = "none";
    document.getElementById("labelalamat").style.display = "none";
    document.getElementById("labelkota").style.display = "none";
    document.getElementById("labelsaldokas").style.display = "none";
    document.getElementById("pilihBank").style.visibility = "hidden";

    if (jenistransaksi == "JJ") {
        document.getElementById("labelkegiatan").style.display = "block";
        document.getElementById("labelva").style.display = "block";
        document.getElementById("pilihBank").style.visibility = "visible";
        document.getElementById("labelsaldokas").style.display = "block";
        document.getElementById("labeltoken").style.display = "block";
        document.getElementById("databank").style.display = "block";
        getSaldoKas();

    } else if (jenistransaksi.substr(0, 1) == "P") {
        document.getElementById("labelkegiatan").style.display = "block";
        document.getElementById("labelmasapajak").style.display = "block";
        document.getElementById("labeltahunpajak").style.display = "block";
        document.getElementById("labelnpwp").style.display = "block";
        document.getElementById("labelnamapenyetor").style.display = "block";
        document.getElementById("labelalamat").style.display = "block";
        document.getElementById("labelkota").style.display = "block";

        document.getElementById("labelnamabank").style.display = "none";
        document.getElementById("labelrekbank").style.display = "none";
        document.getElementById("labelnamarekanan").style.display = "none";
        document.getElementById("labeltoken").style.display = "none";
        document.getElementById("databank").style.display = "none";

        getDataNpwp();

        var mp1 = $('#masaPajak').val().substr(0, 2);
        var mp2 = $('#masaPajak').val().substr(2, 2);

        $('#masapajak1').val(mp1);
        $('#masapajak2').val(mp2);

    }

}

function setGrid() {
    var jenistransaksi = $('#kodeTransaksi').val();

    if (jenistransaksi == "JJ") {
        $('#tabelSPJ').show();
        $('#tabelBkus').hide();
        gridspj();

    } else if (jenistransaksi.substr(0, 1) == "P") {
        $('#tabelSPJ').hide();
        $('#tabelBkus').show();
        gridbku();

    } else {
        $('#tabelSPJ').hide();
        $('#tabelBkus').hide();
    }
}

function setTujuan() {
    var jenistransaksi = $('#kodeTransaksi').val();
    var idskpd = "12782"; // default Dinas Pendidikan untuk BOS

    if (jenistransaksi == "ST" || jenistransaksi == "JG" || jenistransaksi == "RT") {
        $.getJSON(getbasepath() + "/bkutf/json/getDataBankTujuan", {idskpd: idskpd},
        function(result) {

            var kodebank = result.aData['kodeBank'];
            var namabank = result.aData['namaBank'];
            var namatujuan = result.aData['namaTujuan'];
            var norek = result.aData['norekTujuan'];

            $('#kodeBankTf').val(kodebank);
            $('#namaBank').val(namabank);
            $('#norekBank').val(norek);
            $('#namaRekan').val(namatujuan);

            $("#kodeBankTf").attr('readonly', 'readonly');
            $("#namaBank").attr('readonly', 'readonly');
            $("#norekBank").attr('readonly', 'readonly');
            $("#namaRekan").attr('readonly', 'readonly');

            cekDataBankDki();

        });
    } else {
        cekDataBankDki();
    }

}

function gridbku() {

    if (typeof myTable == 'undefined') {
        myTable = $('#bkustable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[50, 100, 500, -1], [50, 100, 500, "All"]],
            "iDisplayLength": 500,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkutf/json/listbkubos",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idbku", value: $("#idBku").val()}
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

                //var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";

                var nilai = "<input type='text' name='nilai" + index + "' id='nilai" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true' />";
                var kodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var kodekomponen = "<input type='hidden' id='kodekomponen" + index + "' value='" + aData['kodeKomponen'] + "' />";

                $('td:eq(0)', nRow).html(index + kodeakun + kodekomponen);
                $('td:eq(4)', nRow).html(nilai);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "6%"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center", "sWidth": "15%"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "center", "sWidth": "31%"},
                {"mDataProp": "namaKomponen", "bSortable": false, sClass: "center", "sWidth": "30%"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "right", "sWidth": "18%"}
            ]
        });
    } else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function gridspj() {
    jumrowspj = 0;
    var sumspj = 0;
    var sumpajak = 0;
    var sumtf = 0;
    if (typeof myTable2 == 'undefined') {
        myTable2 = $('#spjtable').dataTable({
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
            "sAjaxSource": getbasepath() + "/bkutf/json/listbkubos",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idbku", value: $("#idBku").val()}
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

                //var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";
                jumrowspj = jumrowspj + 1;
                var nilai = "<input type='text' name='nilaispj" + index + "' id='nilaispj" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true' />";
                var nilaipajak = "<input type='text' name='nilaipajak" + index + "' id='nilaipajak" + index + "'  class='inputmoney'  value='" + aData['nilaiPajakSpj'] + "' readOnly='true' />";
                var nilainetto = "<input type='text' name='nilainetto" + index + "' id='nilainetto" + index + "'  class='inputmoney'  value='" + aData['nilaiNettoSpj'] + "' readOnly='true' />";
                var idbkurinci = "<input type='hidden' id='idrinci" + index + "' value='" + aData['idBkuRinci'] + "' />";

                var idblrinci = "<input type='hidden' id='idblrinci" + index + "' value='" + aData['idBlRinci'] + "' />";
                var namasubkeg = "<input type='hidden' id='namasubkeg" + index + "' value='" + aData['namaSubKegiatan'] + "' />";
                var ketrinci = "<input type='hidden' id='ketrinci" + index + "' value='" + aData['keteranganRinci'] + "' />";
                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var kodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var idkomponen = "<input type='hidden' id='idkomponen" + index + "' value='" + aData['idKomponen'] + "' />";
                var kodekomponen = "<input type='hidden' id='kodekomponen" + index + "' value='" + aData['kodeKomponen'] + "' />";

                var pilihpajak = "<a id='inputpajak" + index + "' onclick='setButton(" + index + ")' class='fancybox fancybox.iframe' href='" + getbasepath() + "/bkutf/listpajakbos?target='_top'' title='Input Pajak SPJ'  ><i class='icon-edit'>";

                sumspj = sumspj + aData['nilaiKeluar'];
                sumpajak = sumpajak + aData['nilaiPajakSpj'];
                sumtf = sumtf + aData['nilaiNettoSpj'];

                $('#sumspj').autoNumeric('set', sumspj);
                $('#sumpajak').autoNumeric('set', sumpajak);
                $('#sumtf').autoNumeric('set', sumtf);

                $('td:eq(0)', nRow).html(index + idbkurinci + idblrinci + namasubkeg + ketrinci);
                $('td:eq(4)', nRow).html(nilai + idbas + kodeakun + idkomponen + kodekomponen);
//                $('td:eq(5)', nRow).html(pilihpajak);
                $('td:eq(5)', nRow).html(nilaipajak);
                $('td:eq(6)', nRow).html(nilainetto);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "3%"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center", "sWidth": "8%"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "center", "sWidth": "28%"},
                {"mDataProp": "namaKomponen", "bSortable": false, sClass: "center", "sWidth": "25%"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "right", "sWidth": "11%"},
//                {"mDataProp": "idBkuRinci", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "nilaiPajakSpj", "bSortable": false, sClass: "center", "sWidth": "11%"},
                {"mDataProp": "nilaiNettoSpj", "bSortable": false, sClass: "center", "sWidth": "11%"}
            ]
        });
    } else
    {
        myTable2.fnClearTable(0);
        myTable2.fnDraw();
    }

}

function setButton(id) {
    $('#idbutton').val(id);
}

function getDataBankBOS() {

    $.getJSON(getbasepath() + "/bkutf/json/getDataBankBOS", {idsekolah: $('#idsekolah').val()},
    function(result) {

        if (result.aData !== null) {
            var norek = result.aData['norekPengirim'];
            var nama = result.aData['namaPengirim'];
            var wilayah = result.aData['wilayah'];

            $('#norekpengirim').val(norek);
            $('#namapengirim').val(nama);
            $('#wilayah').val(wilayah);

            if ($('#norekpengirim').val() == "" || $('#namapengirim').val() == "") {
                bootbox.alert("Data Nomor Rekening BOS Sekolah Belum Tersedia, Lengkapi Data Referensi Terlebih Dulu");
                $('#btnSimpan').attr("disabled", true);
            }

        } else {
            bootbox.alert("Data Nomor Rekening BOS Sekolah Belum Tersedia, Lengkapi Data Referensi Terlebih Dulu");
            $('#btnSimpan').attr("disabled", true);
        }

    });

}

function getDataNpwp() {

    $.getJSON(getbasepath() + "/bkutf/json/getDataNpwp", {idsekolah: $('#idsekolah').val()},
    function(result) {

        var npwp = result.aData['npwp'];
        var nama = result.aData['namaWp'];
        var alamat = result.aData['alamatWp'];
        var kota = result.aData['kotaWp'];

        $('#namapenyetor').val(nama);
        $('#npwp').val(npwp);
        $('#alamatpenyetor').val(alamat);
        $('#kotapenyetor').val(kota);

    });

}

function getSaldoKas() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();

    $.getJSON(getbasepath() + "/bkutf/json/getSaldoKasBos", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan},
    function(result) {

        var saldo = result.aData['saldoKas'];
        console.log("ini saldo " + saldo);
        $('#saldo').val(accounting.formatNumber(saldo, 2, '.', ","));

    });

}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
function checkLibur() {
    $.getJSON(getbasepath() + "/cutoff/json/checklibur", {},
            function(result) {
                if (result) {
                    bootbox.alert("Transfer tidak dapat dilakukan pada hari libur atau telah melewati batas waktu sudah ditentukan.");
                } else {
                    cekbayar();
                }
            });
}
function cekbayar() {

    var jenistransaksi = $('#kodeTransaksi').val();
    var norek = $('#norekBank').val().replace(/\D/g, '');
    var nama = $('#namaRekan').val();
    var kodebank = $('#kodeBankTf').val();
    var norekdki = $('#dkinorek').val();
    var namadki = $('#dkinama').val();
    var saldo = accounting.unformat($("#saldo").val(), ",");
    var nilaibku = accounting.unformat($("#nilaiBku").val(), ",");
    var panjangnorek = ($("#norekBank").val()).length;

    if (jenistransaksi.substr(0, 1) !== "P") { // untuk testing di bandung, validasi dilepas dl
        if ($("#validatetime").val() == '1') {
            bootbox.alert("Transfer hanya dapat dilakukan pada hari Senin - Jumat sebelum pukul 16:00 WIB (Selain Bank DKI) dan 21:00 WIB (Untuk Bank DKI). Silahkan  melakukan transfer kembali dihari kerja berikutnya.");
        } else if (norek !== norekdki) { // KHUSUS BANK DKI
            bootbox.alert("Nomor Rekening Harus Sama dengan Data Bank");
        } else if (nama !== namadki.trim()) { //trim karena offus ada spasi belakang nya
            bootbox.alert("Nama Tujuan Harus Sama dengan Data Nama Bank");
        } else if ($("#tglPosting").val() == "" || $("#norekBank").val() == "" || $("#namaRekan").val() == "") {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else if (saldo < nilaibku && jenistransaksi == "JJ") {
            bootbox.alert("Saldo Kas Tidak Mencukupi untuk Melakukan Pembayaran SPJ");
        } else if (nilaibku <= 5000 && kodebank !== "111") {
            bootbox.alert("Nominal Transfer Minimal selain Bank DKI adalah Sebesar Rp 5.001");
        } else if ($("#token").val() == "") {
            bootbox.alert("Ajukan Token Terlebih Dahulu");
        } else if (kodebank == "111" && panjangnorek !== 11) {
            bootbox.alert("Panjang No Rekening Tujuan Bank DKI Harus 11 Karakter");
            $('#btnSimpan').attr("disabled", false);
        } else if (panjangnorek > 20) {
            bootbox.alert("Panjang No Rekening Tujuan Tidak Boleh Lebih dari 20 Karakter");
            $('#btnSimpan').attr("disabled", false);
        } else if (response76 !== "00") { // untuk data offus yang ridak terdaftar/ respon error, response = 00 berhasil, else error
            bootbox.alert("Data Nasabah Tidak Terdaftar");
            $('#btnSimpan').attr("disabled", false);
        } /*else if ($('#norekpengirim').val() == "") {
         bootbox.alert("Data Nomor Rekening BOS Sekolah Belum Tersedia, Lengkapi Data Referensi Terlebih Dulu");
         } */ else {
            submittf(jenistransaksi);
        }
    } else {
        if (saldo < nilaibku && jenistransaksi == "JJ") {
            bootbox.alert("Saldo Kas Tidak Mencukupi untuk Melakukan Pembayaran SPJ");
        } else if ($("#token").val() == "" && jenistransaksi.substr(0, 1) != "P") {
            bootbox.alert("Ajukan Token Terlebih Dahulu");
        } else if (jenistransaksi.substr(0, 1) == "P") {
            if ($("#tglPosting").val() == "") {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else {
                bayarpajak();
            }
        } else {
            submittf(jenistransaksi);
        }
    }
}

function submittf(jenistransaksi) {

    bootbox.confirm("Anda akan melakukan transfer BKU dengan nomor " + $("#noBukti").val() + " senilai " + $("#nilaiBku").val() + ",  lanjutkan ? ", function(result) {
        console.log("kodestan = " + kodestan);
        if (result) {
            count++;
            if (count = 1) {
                //document.getElementById("btnSimpan").style.visibility = "hidden";
                var sysdate;

                // get kode stan dan sysdate dulu
                $.getJSON(getbasepath() + "/bkutf/json/getKodeStan", {},
                        function(result) {
                            kodestan = result.aData['kodeStan'];
                            noreff = result.aData['nomorRef'];

                            sysdate = result.aData['tglProses']; // ddmmyy hh24miss
                            //console.log("sysdate = " + sysdate);
                            var dd = sysdate.substr(0, 2);
                            var mm = sysdate.substr(2, 2);
                            var yy = sysdate.substr(4, 2);
                            var h = sysdate.substr(7, 2);
                            var m = sysdate.substr(9, 2);
                            var s = sysdate.substr(11, 2);

                            var kodebank = $("#kodeBankTf").val();
                            var norektujuan = $("#norekBank").val().toString().trim();
                            var namatujuan = $("#namaRekan").val();
                            var nilaibayar = accounting.unformat($("#nilaiBku").val(), ",");
                            var norekpengirim = $("#norekpengirim").val().trim();
                            var namapengirim = $("#namapengirim").val();
                            var idbku = $("#idBku").val();
                            var tahun = $("#tahun").val();
                            var kodewil = $("#wilayah").val();
                            var npsn = $("#npsn").val();
                            var uraian = $("#uraian").val().substr(0, 100);
                            var namasekolah = $("#namasekolah").val();

                            var nilaibku = accounting.unformat($("#nilaiBku").val(), ",");
                            var pad = "0000000000000";
                            var nilaiamountbalance = (pad + nilaibku).slice(-pad.length);
                            var kodeTalangan = $("#kodeTalangan").val();
                            var bulanTagihan = $("#kodeBulan").val();
                            var idMcb = $("#idMcb").val();

                            var kodeakun;

                            if (jenistransaksi == "JJ") {
                                if ($("#kodeakun1").val() == "" || $("#kodeakun1").val() == null || $("#kodeakun1").val() == "null") {
                                    kodeakun = "-";
                                } else {
                                    kodeakun = $("#kodeakun1").val();
                                }

                            } else {
                                kodeakun = "-";
                            }
                            if (jenistransaksi == "JJ") {
                                var kodekomponen = $("#kodekomponen1").val();
                            } else {
                                var kodekomponen = "-";
                            }

                            //tglkirim = dd + "/" + mm + "/" + yy + " " + "" + h + ":" + m + ":" + s;
                            // set var untuk rekon
                            rbit4 = nilaiamountbalance;
                            rbit11 = kodestan;
                            rbit12 = h + m + s; // hhmmss
                            rbit13 = yy + mm + dd; // MMDD
                            rbit37 = noreff;

                            var var4 = "000000000000"; // amount
                            var var7 = mm + dd + h + m + s; // MMDDhhmmss
                            var var11 = kodestan;
                            var var12 = h + m + s; // hhmmss
                            var var13 = mm + dd; // MMDD
                            var var14 = yy + mm; // YYMM
                            var var15 = mm + dd; // MMDD
                            var var32 = "000" + kodebank;
                            var var37 = noreff;
                            var var48 = nrk + "|" + $("#token").val();
                            var kodeauthor = tahun + var7; // YYYYMMDDhhmmss
                            var sumberdana = "BOS";

                            //SET FIX LENGTH NOREK (20) UNTUK FIELD 120
                            var panjangnorek = norektujuan.toString().length;
                            var pspasi = 20 - panjangnorek;
                            var spasi = "";
                            var norek20 = "";

                            for (var i = 0; i < pspasi; i++) {
                                spasi = spasi + " ";
                            }
                            //norek20 = (norektujuan.toString() + spasi).toString();
                            norek20 = (norektujuan.trim() + spasi).toString();

                            // set var untuk kirim
                            var var120 = kodebank + "|" + norek20 + "|" + namatujuan + "|" + nilaibayar + "|" + norekpengirim + "|" + namapengirim + "|" + idbku + "|" + tahun + "|" + kodewil + "|" + uraian + "|" + npsn + "|" + namasekolah + "|" + kodeakun + "|" + kodekomponen + "|" + sumberdana + "|" + nilaiamountbalance + "|" + kodeTalangan + "|" + idMcb + "|" + bulanTagihan;
                            var var62 = tahun.toString() + "2" + $("#kodeBatalTf").val() + idbku.toString(); // yyyy+ kode sumb + kode retur + idbku // kode app diganti untuk SP2D jadi 2 (asalnya 1) agar sama dengan urutan kode app di ws pajak (pertanggal 15 jan 19)
                            var var102 = norekpengirim; // no rek pengirim
                            var var103 = norektujuan; // no rek penerima

                            simpanTMBank(var4, var7, var11, var12, var13, var14, var15, var32, var37, var48, var62, var102, var103, var120, tahun, idbku, nilaibayar, kodeauthor);
                            //transferBankDki(var4, var7, var11, var12, var13, var14, var15, var32, var37, var48, var62, var102, var103, var120, tahun, idbku, nilaibayar, kodeauthor);
                        });
            }
        } else {
            bootbox.hideAll();
            document.getElementById("btnSimpan").style.visibility = "visible";

            return result;
        }
    });
}

function simpanTMBank(var4, var7, var11, var12, var13, var14, var15, var32, var37, var48, var62, var102, var103, var120, tahun, idbku, nilaibayar, kodeauthor) {
    var kodeakun;
    if ($('#kodeTransaksi').val() == "JJ") {
        //kodeakun = $("#kodeakun1").val();
        if ($("#kodeakun1").val() == "" || $("#kodeakun1").val() == null || $("#kodeakun1").val() == "null") {
            kodeakun = "-";
        } else {
            kodeakun = $("#kodeakun1").val();
        }
    } else {
        kodeakun = "-";
    }

    if ($('#kodeTransaksi').val() == "JJ") {
        var kodekomponen = $("#kodekomponen1").val();
    } else {
        var kodekomponen = "-";
    }
    var varurl = getbasepath() + "/bkutf/json/simpanbkubank";
    var dataac = [];


    var datajour = {
        tahun: tahun,
        idbku: idbku,
        nilaibayar: nilaibayar.toString(),
        msgkirim: var120,
        bit4: rbit4,
        bit11: rbit11,
        bit12: rbit12,
        bit13: rbit13,
        bit37: rbit37,
        kodebank: $("#kodeBankTf").val(),
        norektujuan: $("#norekBank").val(),
        namatujuan: $("#namaRekan").val(),
        norekpengirim: $("#norekpengirim").val(),
        namapengirim: $("#namapengirim").val(),
        kodewil: $("#wilayah").val(),
        keterangan: $("#uraian").val().substr(0, 100), //$("#uraian").val(),
        idsekolah: $("#idsekolah").val(),
        npsn: $("#npsn").val(),
        namasekolah: $("#namasekolah").val(),
        kodeakun: kodeakun,
        kodekomponen: kodekomponen,
        token: $("#token").val(),
        kodesumbdana: '1',
        bit62: var62

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
        var kode = data.kode;
        var pesan = data.pesan;
        console.log("error = " + pesan);
        hapustoken(); // hapus data token, berhasil atau gagal token tidak bisa dipakai 2x

        if (kode.toString() == "1") {
            transferBankDki(var4, var7, var11, var12, var13, var14, var15, var32, var37, var48, var62, var102, var103, var120, tahun, idbku, nilaibayar, kodeauthor);
        } else if (kode.toString() == "9") {
            bootbox.alert("Data Transfer Gagal Diproses");
        }

    });
}


function transferBankDki(var4, var7, var11, var12, var13, var14, var15, var32, var37, var48, var62, var102, var103, var120, tahun, idbku, nilaibayar, kodeauthor) {
    var var0 = "0200"; // ??
    var var2 = "000000000000000000"; //
    var var3 = "500005"; //

    var var18 = "7012"; //
    var var22 = "001"; // ???
    var var33 = "111"; //
    var var41 = "EBKUSKLH"; //
    var var49 = "360"; // For Indonesia use “360”
    // var var52 = "1234567890123456"; // ???? ga usah
    var var54 = var4; //"000000000000";//nilaiamountbalance; // Amount Balance

    var msg, status;
    var varurl = getbasepath() + "/bkutf/json/transfersp2d";
    var dataac = [];
    var datasp2d = {
//        idbku: idbku,
//        kodesumbdana: '1',
        0: var0,
        2: var2,
        3: var3,
        4: var4,
        7: var7,
        11: var11,
        12: var12,
        13: var13,
        14: var14,
        15: var15,
        18: var18,
        22: var22,
        32: var32,
        33: var33,
        37: var37,
        41: var41,
        48: var48,
        49: var49,
        //52: var0,
        54: var54,
        62: var62,
        102: var102,
        103: var103,
        120: var120

    };
    dataac = datasp2d;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {

            var data39 = data[39];
            if (data39 == "00") {
                status = 1;
                console.log("MASUK A");
                msg = "Transaksi BKUS Berhasil";
            } else {
                status = 9;
                if (data39 == "76") {
                    msg = "Transaksi Gagal - No Rekening Tidak Terdaftar";
                } else if (data39 == "63") {
                    msg = "Transaksi Gagal - Data Sudah Berhasil Transaksi, Tidak Perlu Ditransaksikan Kembali";
                } else if (data39 == "68") {
                    msg = "Transaksi Gagal - Koneksi Bank DKI Time Out";
                } else if (data39 == "12") {
                    msg = "Transaksi Tidak Berlaku";
                } else if (data39 == "13") {
                    msg = "Transaksi Gagal - Jumlah Tidak Valid";
                } else if (data39 == "18") {
                    msg = "Transaksi Gagal - Sudah Dibayar / No Bill";
                } else if (data39 == "20") {
                    msg = "Transaksi Gagal - Posting Core Gagal";
                } else if (data39 == "30") {
                    msg = "Transaksi Gagal - Pesan Format Kesalahan";
                } else if (data39 == "51") {
                    msg = "Transaksi Gagal - Saldo tidak cukup untuk melakukan transaksi";
                } else if (data39 == "53") {
                    msg = "Transaksi Gagal - Tidak ada Tabungan";
                } else if (data39 == "57") {
                    msg = "Transaksi Gagal - Transaksi tidak diizinkan untuk Pemegang Kartu / Penerbit";
                } else if (data39 == "78") {
                    msg = "Transaksi Gagal - Rekening Ditutup";
                } else if (data39 == "90") {
                    msg = "Transaksi Gagal - Terjadi Kesalahan";
                } else if (data39 == "91") {
                    msg = "Transaksi Gagal - Server melakukan transaksi lainnya, coba lagi nanti";
                } else if (data39 == "94") {
                    msg = "Transaksi Gagal - Waktu koneksi habis";
                } else {
                    msg = "Transaksi Gagal";
                }
                console.log("MASUK B " + msg);

            }

            var cek = "FIELD 37 : " + data[37] + " <br/> FIELD 39 : " + data[39] + " <br/> FIELD 120 : " + data[120];
            /*bootbox.alert({
             size: "small",
             title: "DATA RESPONSE",
             message: cek
             });*/

            bootbox.alert(msg);
            console.log(data);
            update12 = data[12];
            update13 = data[13];
            update39 = data[39];

            //simpanBkuBank(tahun, idbku, nilaibayar, var120, kodeauthor, update12, update13, update39, status, msg, var4);

            var pesan = update39 + " - " + msg;
            var mm = update13.substr(0, 2);
            var dd = update13.substr(2, 2);
            var tglproses = dd + mm + tahun + " " + update12;

            updateBkuBank(idbku, status, "-", tglproses, nilaibayar, pesan); // update data di tmbkusbank sesuai status transfer
            if (status == 1) { // jika berhasil maka update data d_posting transaksi
                console.log("bayar");
                bayar();
            }

        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        }
    }).always(function(data) {

    });

}

function simpanBkuBank(tahun, idbku, nilaibayar, msgkirim, kodeauthor, update12, update13, update39, status, msg, var4) {

    var mm = update13.substr(0, 2);
    var dd = update13.substr(2, 2);
    //var tahun = $("#tahun").val();
    var tglproses = dd + mm + tahun + " " + update12;
    var pesan = update39 + " - " + msg;


    var varurl = getbasepath() + "/bkutf/json/simpanbkubank";
    var dataac = [];


    var datajour = {
        tahun: tahun,
        idbku: idbku,
        nilaibayar: nilaibayar.toString(),
        msgkirim: msgkirim,
        bit4: rbit4,
        bit11: rbit11,
        bit12: rbit12,
        bit13: rbit13,
        bit37: rbit37,
        kodebank: $("#kodeBankTf").val(),
        norektujuan: $("#norekBank").val(),
        namatujuan: $("#namaRekan").val(),
        norekpengirim: $("#norekpengirim").val(),
        namapengirim: $("#namapengirim").val(),
        kodewil: $("#wilayah").val(),
        keterangan: $("#uraian").val().substr(0, 100),
        idsekolah: $("#idsekolah").val(),
        npsn: $("#npsn").val(),
        namasekolah: $("#namasekolah").val(),
        kodeakun: $("#kodeakun1").val(),
        kodekomponen: $("#kodekomponen1").val(),
        token: $("#token").val(),
        kodesumbdana: '1'

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
        console.log("simpan data berhasil");
        updateBkuBank(idbku, status, "-", tglproses, nilaibayar, pesan);

    });
}

function updateBkuBank(idbku, status, trxterima, tglproses, nilai, msg) {

    var varurl = getbasepath() + "/bkutf/json/updatebkubankbos";
    var dataac = [];
    var tahun = $('#tahun').val();
    var datajour = {
        idsekolah: $("#idsekolah").val(),
        bit11: rbit11,
        kodesumbdana: "2",
        tahun: tahun,
        idbku: idbku,
        statusbank: status.toString(),
        trxterimabank: trxterima,
        tglprosesbank: tglproses,
        nilaibayarbank: nilai.toString(),
        msgterimabank: msg

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
        console.log("update data berhasil");
        update12 = "";
        update13 = "";
        update39 = "";
    });
}

function bayarpajak() {
    var kodetransaksi = $('#kodeTransaksi').val();
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substr(6, 4);
    tanggal = yy + mm + dd;
    var masapajak = $("#masapajak1").val() + $("#masapajak2").val();
    var tahunpajak = $("#tahunPajak").val();

    var varurl = getbasepath() + "/bkutf/json/bayarbkubos";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        idsekolah: $('#idsekolah').val(),
        kodetransaksi: kodetransaksi,
        tglposting: tanggal,
        namarekan: $("#namaRekan").val(),
        norek: $("#norekBank").val(),
        kodebank: $("#kodeBank").val(),
        kodebanktf: $("#kodeBankTf").val(),
        namabank: $("#namaBank").val(),
        idbku: $("#idBku").val(),
        masapajak: masapajak,
        tahunpajak: tahunpajak

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
        bootbox.alert("Transaksi BKU Berhasil Dibayarkan");
        document.getElementById("btnSimpan").style.visibility = "hidden";
        //setPajakJJ();

    });

}

function bayar() {
    var kodetransaksi = $('#kodeTransaksi').val();
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;


    var varurl = getbasepath() + "/bkutf/json/bayarbkubos";
    var dataac = [];
    var listpajak = [];
    console.log("INI JUMLAH PAJAK " + banyakpajak);
    if (banyakpajak > 0) {
        for (var i = 0; i < banyakpajak; i++) { // list
            var pararr = {
                idbku: idpajakpn[i],
                kodetrx: kodepajakpn[i]
            };

            listpajak[i] = pararr;

        }
    }

    var datajour = {
        tahun: $("#tahun").val(),
        idsekolah: $('#idsekolah').val(),
        kodetransaksi: kodetransaksi,
        tglposting: tanggal,
        namarekan: $("#namaRekan").val(),
        norek: $("#norekBank").val(),
        kodebank: $("#kodeBank").val(),
        kodebanktf: $("#kodeBankTf").val(),
        namabank: $("#namaBank").val(),
        idbku: $("#idBku").val(),
        banyakpajak: banyakpajak.toString(),
        listpajak: listpajak

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
        //bootbox.alert("Transaksi BKU Berhasil Dibayarkan");
        document.getElementById("btnSimpan").style.visibility = "hidden";
        //setPajakJJ();

    });

}
function setPajakJJ() {
    var i;

    for (i = 1; i <= jumrowspj; i++) {
        document.getElementById("inputpajak" + i).style.visibility = "hidden";

    }

}

function setMasaPajak2() {
    var bulanmp1 = $('#masapajak1').val();

    $('#masapajak2').val(bulanmp1);
}

function getNilaiSpjNetto() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var idbku = $('#idBku').val();

    $.getJSON(getbasepath() + "/bkutf/json/getNilaiSpjNettoBos", {tahun: tahun, idsekolah: idsekolah, idbku: idbku},
    function(result) {

        var nilai = result.aData['nilaiSpjNetto'];

        $('#nilaiBku').val(accounting.formatNumber(nilai, 2, '.', ","));

    });
}

function hapustoken() {
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;

    var varurl = getbasepath() + "/token/json/deletetoken";
    var dataac = [];

    var datajour = {
        idsekolah: $("#idsekolah").val(),
        token: $("#token").val(),
        dposting: tanggal,
        noMohon: $('#noBkuMohon').val(),
        kodesumbdana: '1'
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

function trigerPajakSpj() {
    getNilaiSpjNetto();
    gridspj();
}

function getListPajakPn() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var nobkuref = $('#noBkuMohon').val();

    $.getJSON(getbasepath() + "/bkutf/json/getListPajakPnBos", {tahun: tahun, idsekolah: idsekolah, nobkuref: nobkuref},
    function(result) {
        banyakpajak = result.aData.length;

        try {
            if (banyakpajak > 0) {
                for (var i = 0; i < banyakpajak; i++) {
                    idpajakpn[i] = result.aData[i]['idBku'];
                    kodepajakpn[i] = result.aData[i]['kodeTransaksi'];
                }
            }
        } catch (e) {
            console.log(e);
        }
    });

}

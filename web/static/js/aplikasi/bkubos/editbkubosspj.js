$(document).ready(function() {
    getSisaKasJJ();
    //setTriwulan();
    getDataKegiatan();
    gridspj();

    adjustParam();

});
function adjustParam() {
    // kode VA
    if ($('#kodeVA').val() == "1") {
        document.getElementById("cbVA").checked = true;
    }
    // kode Talangan
    if ($('#kodeTalangan').val() == "1") {
        $('#pilihBank').attr("disabled", true);

        document.getElementById("cbTalangan").checked = true;
        document.getElementById("norekBank").readOnly = true;
        document.getElementById("namaRekan").readOnly = true;
        document.getElementById("labelva").style.display = "none";

        getMcb();

    } else {
        $('#pilihBank').attr("disabled", false);
        document.getElementById("labeltalangan").style.display = "none";
        document.getElementById("labelmcb").style.display = "none";
        document.getElementById("labelbulan").style.display = "none";
    }

    // Param pajak
    $('#npwp').val($('#datanpwp').val());
    getDataNPWP();
    $('#belanja').val($('#kodeBelanja').val());
    if ($('#kodeBelanja').val() == '1') {
        document.getElementById("labelpns").style.display = "block";
        if ($('#kodePNS').val() != '0') {
            $('#golongan').show();
            $('#pns').val('1');
            $('#golongan').val($('#kodePNS').val());
            document.getElementById("labelpegawai").style.display = "none";
            document.getElementById("labelperiode").style.display = "none";
        } else {
            $('#pns').val('0');
            $('#golongan').hide();
            document.getElementById("labelpegawai").style.display = "block";
            $('#pegawai').val($('#kodePegawai').val());
            if ($('#kodePegawai').val() == '1') {
                document.getElementById("labelperiode").style.display = "block";
                $('#periode').val($('#kodePeriode').val());
            } else {
                $('#periode').val($('#kodePeriode').val());
                document.getElementById("labelperiode").style.display = "none";
            }
        }

        document.getElementById("labelptkp").style.display = "block";
        document.getElementById("labelsk").style.display = "none";
    } else {
        document.getElementById("labelperiode").style.display = "none";
        document.getElementById("labelpegawai").style.display = "none";
        document.getElementById("labelpns").style.display = "none";

        document.getElementById("labelptkp").style.display = "none";
        document.getElementById("labelsk").style.display = "block";
    }
    $('#ptkp').val($('#kodePTKP').val());
    if ($('#kodeSK').val() != '-') {
        $('#cmbsk').val('1');
        $('#kodesk').show();
        $('#kodesk').val($('#kodeSK').val());

    } else {
        $('#cmbsk').val('0');
        $('#kodesk').hide();
    }
    if ($('#kodePKP').val() == '1') {
        document.getElementById("cmbpkp").checked = true;
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
    document.getElementById("labelperiode").style.display = "none";
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
// global variable
var jumbarisspj = 0;
var totalawal = 0;
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
        $("#cbmcb").val($("#idMcb").val());
        $("#bulan").val($("#bulanTagihan").val());
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

    $.getJSON(getbasepath() + "/bkubos/json/getTriwulanByRekap", {tahun: tahun, idsekolah: idsekolah},
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
        getSisaKasJJ();

    });
}

function cekTW() {

    // clear data dan gridspj
    clearrow();
    $('#idKegiatan').val("");
    $('#kodeKeg').val("");
    $('#namaKegpop').val("");
    $('#ketKegiatan').val("");
    $('#bidang').val("");
    $('#snp').val("");
    $('#sumbdana').val("");
    $('#sisakas').val("");
    $('#paguakb').val("");
    $("#sumspj").val(0);
    $("#totalspjhidden").val(0);

}

function clearrow() {
    var i;
    var table;
    table = document.getElementById('spjtablebody');

    var rows = table.rows;
    var jum = rows.length;

    idrow = 0;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
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

        $('#norekBank').val("91691160131");
        $('#namaRekan').val("REKENING IMPERSONAL 916");
        document.getElementById("norekBank").readOnly = true;
        document.getElementById("namaRekan").readOnly = true;
        document.getElementById("labelva").style.display = "none";

        $('#pilihBank').attr("disabled", true);

        $('#kodeBank').val("1110012");
        $('#kodeBankTf').val("111");
        $('#namaBank').val("BANK DKI");
        $('#uraian').val("Pembayaran Listrik atas Dana Talangan Bank DKI untuk Triwulan " + $('#triwulan').val());
        getMcb();


    } else {
        $('#kodeTalangan').val(0);
        $('#norekBank').val("");
        $('#namaRekan').val("");
        document.getElementById("norekBank").readOnly = false;
        document.getElementById("namaRekan").readOnly = false;
        document.getElementById("labelva").style.display = "block";

        $('#kodeBank').val("");
        $('#kodeBankTf').val("");
        $('#namaBank').val("");
        $('#uraian').val("");
        $('#pilihBank').attr("disabled", false);

    }
}

function getKegiatan() {
    getSisaKasJJ();
    gridspj();
}

function getSisaKasJJ() {
    var idkegiatan = $('#idKegiatan').val();
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var nobku = $('#noBkuMohon').val();
    var triwulan = $('#triwulan').val();

    $.getJSON(getbasepath() + "/bkubos/json/getSisaKas", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan, nobku: nobku, idkegiatan: idkegiatan},
    function(result) {

        var saldo = result.aData['saldoKas'];
        var pagu = result.aData['paguAkb'];

        $('#sisakas').val(accounting.formatNumber(saldo, 2, '.', ","));
        $('#paguakb').val(accounting.formatNumber(pagu, 2, '.', ","));

    });

}

function getDataKegiatan() {
    var idkegiatan = $('#idKegiatan').val();
    var tahun = $('#tahun').val();

    $.getJSON(getbasepath() + "/bkubos/json/getDataKegiatan", {tahun: tahun, idkegiatan: idkegiatan},
    function(result) {

        var snp = result.aData['ketSnp'];
        var bidang = result.aData['ketBidang'];
        var sumbdana = result.aData['ketSumbdana'];

        $('#snp').val(snp);
        $('#bidang').val(bidang);
        $('#sumbdana').val(sumbdana);

    });

}


function gridspj() {
    jumbarisspj = 0;
    var bukti = $('#bukti').val();

    if (typeof myTable == 'undefined') {
        myTable = $('#spjtable').dataTable({
            "bPaginate": true,
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
            "sAjaxSource": getbasepath() + "/bkubos/json/listspjedit",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "nobkumohon", value: $("#noBkuMohon").val()},
                {name: "triwulan", value: $("#triwulan").val()},
                {name: "idkegiatan", value: $("#idKegiatan").val()}
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
                    ketrincival = ""
                } else {
                    ketrincival = aData['keteranganRinci'];
                }

                if (aData['namaSubKegiatan'] == null) {
                    namasub = ""
                } else {
                    namasub = aData['namaSubKegiatan'];
                }

                totalawal = totalawal + aData['nilaiBkuInput'];
                $("#sumspj").val(accounting.formatNumber(totalawal, 2, '.', ","));
                $("#totalspjhidden").val(totalawal);

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
                //var volume = "<input type='hidden' id='volume" + index + "' value='" + aData['volume'] + "' />";
                //var volume = "<input type='text' size='5' onkeypress='return isNumber(event)' maxlength='10' id='volume" + index + "' value='" + aData['volume'] + "' />";
                var hargasatuan = "<input type='hidden' id='hargasatuan" + index + "' value='" + aData['hargaSatuan'] + "' />";

                var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:170px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";
                var komponen = "<textarea id='komponen" + index + "'readonly style='border:none;margin:0;width:180px;'>" + aData['namaKomponen'] + "</textarea>"; // aData['kodeKomponen'] + "/" +
                var ketrincitext = "<textarea id='ketrincitext" + index + "'readonly style='border:none;margin:0;width:190px;'>" + aData['spekKomponen'] + " / " + namasub + " / " + ketrincival + "</textarea>";
                var nilaiAngg = "<input type='text' name='nilaiAnggaran" + index + "' id='nilaiAnggaran" + index + "'  class='inputmoney'  value='" + aData['nilaiAnggaran'] + "' readOnly='true' />";
                var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSebelum'] + "' readOnly='true' />";
                var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['nilaiSisa'] + "' readOnly='true' />";
                var nilaipajak = "<input type='text' name='nilaipajak" + index + "' id='nilaipajak" + index + "'  class='inputmoney'  value='" + aData['nilaiPajakSpj'] + "' readOnly='true' />";

                if (bukti == '1') {
                    var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "' readonly='true' class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' onkeyup='pasangvalidatebesarperfield(" + index + ")' onchange='pasangvalidatebesarperfield(" + index + ");validasiNilaiPajak(" + index + ")' />";
                    //console.log("masuk sini");
                } else {
                    var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' onkeyup='pasangvalidatebesarperfield(" + index + ")' onchange='pasangvalidatebesarperfield(" + index + ");validasiNilaiPajak(" + index + ")' />";
                    //console.log("masuk sini deng");
                }

                var volume = "";
                if (aData['kodeakun'].substr(0, 5) == '5.2.3') {
                    volume = "<input type='text' size='5' onkeypress='return isNumber(event)' maxlength='10' id='volume" + index + "' value='" + aData['volume'] + "' />";
                }

                $('td:eq(0)', nRow).html(index + idbku + idblrinci);
                $('td:eq(1)', nRow).html(akun + idbas + textkodeakun + paguakun);
                $('td:eq(2)', nRow).html(komponen + idkomponen);
                $('td:eq(3)', nRow).html(ketrincitext + ketrinci + nourut + namasubkeg + komponenpajak + hargasatuan);
                $('td:eq(4)', nRow).html(nilaiAngg);
                $('td:eq(5)', nRow).html(nilaisebelum);
                $('td:eq(6)', nRow).html(nilaisisa);
                $('td:eq(7)', nRow).html(volume);
                $('td:eq(8)', nRow).html(nilaiinput);
                $('td:eq(9)', nRow).html(nilaipajak);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center", "sWidth": "14%"},
                {"mDataProp": "kodeKomponen", "bSortable": false, sClass: "center", "sWidth": "14%"},
                {"mDataProp": "keteranganRinci", "bSortable": false, sClass: "center", "sWidth": "15%"},
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right", "sWidth": "10%"},
                {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right", "sWidth": "10%"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right", "sWidth": "10%"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "center", "sWidth": "5%"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right", "sWidth": "10%"},
                {"mDataProp": "nilaiPajakSpj", "bSortable": false, sClass: "right", "sWidth": "9%"}
            ]
        });
    } else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

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
            totalsebelum += accounting.unformat($("#nilaisebelum" + a).val(), ","); // nilai yang pernah dikeluarkan untuk akun tsb
        }
    }

    sisaakun = paguakun - totalsebelum; // pagu akun (tw) dikurangi total spj untuk akun tsb

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

function validasiNilaiPajak(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaipajak = accounting.unformat($("#nilaipajak" + index).val(), ",");
    //var total = 0;


    if (nilaiinput < nilaipajak) {
        bootbox.alert("Nilai SPJ tidak boleh lebih kecil dari Nilai Pajak.");
        //$('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
        $('#nilaiinput' + index).autoNumeric('set', nilaipajak); // nilaisisa harus yang di unformat :)
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

function simpan() {

    var nobukti = $("#noBukti").val();
    var tglDok = $("#tglDok").val();
    var bulanTglDok = tglDok.substr(3, 2);
    var tahunTglDok = tglDok.substr(6, 4);
    var filling = $("#inboxFile").val();
    var nrkPptk = $("#nrkPptk").val();
    var nippptk = $("#nipPptk").val();
    var namapptk = $("#namaPptk").val();
    var idKegiatan = $("#idKegiatan").val();
    var namarekan = $("#namaRekan").val();
    var bank = $("#namaBank").val();
    var norek = $("#norekBank").val();
    var pns = $("#pns").val();
    var periode = $("#periode").val();
    var golongan = $("#golongan").val();
    var ptkp = $("#ptkp").val();
    var sk = $("#cmbsk").val();
    var kodesk = $("#kodesk").val();
    var belanja = $("#belanja").val();
    var npwp = $("#npwp").val();
    var pegawai = $("#pegawai").val();
    var namanpwp = $("#namanpwp").val();

    var sisakas = accounting.unformat($("#sisakas").val(), ","); // SISA PAGU AKB PER-KEGIATAN
    var paguakb = accounting.unformat($("#paguakb").val(), ",");
    var sisakasspj = accounting.unformat($("#saldoawal").val(), ","); // SISA KAS
    var banyak = 0;
    var nilaivalidasi, uraianvalidasi;

    if (sisakas < sisakasspj) {
        nilaivalidasi = sisakas;
        uraianvalidasi = "Sisa Pagu AKB Kegiatan";

    } else {
        nilaivalidasi = sisakasspj;
        uraianvalidasi = "Sisa Kas";
    }

    var table3 = document.getElementById('spjtablebody');
    var rows3 = table3.rows;
    var jum3 = rows3.length;

    var nilaitotalspj = $("#totalspjhidden").val();
    var cekvolume = true;

    if ((jum3 > 0) && ($("#akunnama1").val() !== "")) {

        for (var a = 1; a <= jum3; a++) {
            var nilai = parseFloat(accounting.unformat($('#nilaiinput' + a).val(), ","));
            var volume = parseInt($('#volume' + a).val());
            var kodeakun = $('#kodeakun' + a).val().substr(0, 5);

            if (nilai < 0) { //  EDIT BOLEH MENJADI NOL, TIDAK BOLEH MINUS
                banyak = banyak + 1;
            }

            if ((kodeakun == "5.2.3") && (volume <= 0) && (nilai > 0)) {
                cekvolume = false;
            }
        }

        if ((sk == "1" && kodesk == "") || nrkPptk == "" || nobukti == "" || (npwp != "" && namanpwp == "") || tglDok == "" || filling == "" || idKegiatan == "" || nippptk == "" || namapptk == "" || namarekan == "" || bank == "" || norek == "") {
            bootbox.alert("Pengisian Data Harus Lengkap");
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
        } else if (sk == "-") {
            bootbox.alert("Pilih Status Surat Keterangan Pajak Terlebih Dahulu");
            $('#btnSimpan').attr("disabled", false);
        } else if (banyak > 0) {
            bootbox.alert("Nilai SPJ Tidak Boleh Minus");
        } else if (tahunTglDok !== $("#tahun").val()) {
            bootbox.alert("Tahun Dokumen Harus Sesuai Tahun yang Login");
        } else if (nilaitotalspj > nilaivalidasi) {
            bootbox.alert("Total SPJ Tidak Boleh Lebih Besar dari " + uraianvalidasi);
        } else if ($('#cbmcb').val() == '-' && $('#kodeTalangan').val() == '1') {
            bootbox.alert("Pilih MCB Terlebih Dahulu");
        } else if (cekvolume == false) {
            bootbox.alert("Umtuk Belanja Modal (Kode Rekening 5.2.3.*) Volume Harus Diisi");
        } else {
            update();
        }
    }

}

function update() {
    if (jumbarisspj > 0) {
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

        var bulan, imcb;
        if ($("#kodeTalangan").val() == "1") {
            bulan = $("#bulan").val();
            imcb = $("#cbmcb").val();
        } else {
            bulan = "-";
            imcb = "-";
        }
        var varurl = getbasepath() + "/bkubos/json/prosesupdatebkuspj";
        var dataac = [];
        var nilailist = [];
        var i;
        var banyak = 0;

        for (i = 0; i < jumbarisspj; i++) { // list
            var id = i + 1;
            var nilai = parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ","));
            var idrinci = $("#idbku" + id).val();

            var kodeakun = $('#kodeakun' + id).val().substr(0, 5);
            var volume;

            if (kodeakun == "5.2.3") {
                volume = $("#volume" + id).val();
            } else {
                volume = "0";
            }

            if (nilai > 0 || idrinci > 0) {

                var pararr = {
                    nilaimasuk: "0",
                    nilaikeluar: parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ",")).toString(),
                    nilaipajak: parseFloat(accounting.unformat($('#nilaipajak' + id).val(), ",")).toString(),
                    idrinci: $("#idbku" + id).val(),
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
            idbku: $("#idBku").val(),
            idparam: $("#idParam").val(),
            tahun: $("#tahun").val(),
            idsekolah: $("#idsekolah").val(),
            nobkumohon: $("#noBkuMohon").val(),
            kodetransaksi: $('#kodeTransaksi').val(),
            nobukti: $("#noBukti").val(),
            tgldok: $("#tglDok").val(),
            jenis: "3",
            beban: "TU",
            fileinbox: $("#inboxFile").val(),
            nrk: $("#nrkPptk").val(),
            namapptk: $("#namaPptk").val(),
            nippptk: $("#nipPptk").val(),
            uraian: $("#uraian").val(),
            carabayar: $("#kodePembayaran").val(),
            idkegiatan: $("#idKegiatan").val(),
            kodekeg: $("#kodeKeg").val(),
            norek: $("#norekBank").val().trim(),
            namarekan: $("#namaRekan").val(),
            kodebank: $("#kodeBank").val(),
            kodebanktf: $("#kodeBankTf").val(),
            namabanktf: $("#namaBank").val(),
            kodeva: $('#kodeVA').val(),
            kodetalangan: $('#kodeTalangan').val(),
            bulan: bulan,
            imcb: imcb,
            nobkuref: "0",
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
            url: varurl,
            contentType: "text/plain; charset=utf-8",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(dataac)
        }).always(function(data) {
            bootbox.alert("Data Buku Kas Umum Berhasil Diubah");
            totalawal = 0;
            gridspj();
        });

    }

}
function updatebukti() {
    if (jumbarisspj > 0) {

        var varurl = getbasepath() + "/bkubos/json/prosesupdatebkuspjbukti";
        var dataac = [];

        var datajour = {
            idbku: $("#idBku").val(),
            tahun: $("#tahun").val(),
            idsekolah: $("#idsekolah").val(),
            kodetransaksi: $('#kodeTransaksi').val(),
            nobukti: $("#noBukti").val()
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

}
function hapus() {
    var tahun = $("#tahun").val();

    bootbox.confirm("Anda akan menghapus data BKU ini, lanjutkan ? ", function(result) {
        if (result) {
            var varurl = getbasepath() + "/bkubos/json/prosesdeletebyid";
            var dataac = [];

            var datajour = {
                idbku: $("#idBku").val(),
                idparam: $("#idParam").val(),
                idrinci: $("#idRinci").val(),
                tahun: tahun,
                kodetransaksi: $("#kodeTransaksi").val(),
                idsekolah: $("#idsekolah").val(),
                nobkuref: $("#noBkuMohon").val()
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
                hapusba()
                bootbox.alert("Data Buku Kas Umum Berhasil Dihapus");
                window.location.href = getbasepath() + "/bkubos/indexbkubos"; // ke form index
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
        sumbdana: '1'
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
function cek() {
    var i;
    var banyak = 0;

    for (i = 0; i < jumbarisspj; i++) { // list
        var id = i + 1;
        var nilai = parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ","));
        var idrinci = $("#idblrinci" + id).val();

        console.log("nilai = " + nilai);
        console.log("idrinci = " + idrinci);


        if (nilai > 0 || idrinci > 0) {

            console.log("masukin");
        }

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
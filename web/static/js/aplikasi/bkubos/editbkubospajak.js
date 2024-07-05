$(document).ready(function() {
    setTriwulan();
    setRumusPajak();
    getDataNPWP();
    setKJS();
    // set masa pajak
    var mp1 = $('#masaPajak').val().substr(0, 2);
    var mp2 = $('#masaPajak').val().substr(2, 2);

    $('#masapajak1').val(mp1);
    $('#masapajak2').val(mp2);

//    if ($('#jenisPembayaran').val() == "PN") {
//        document.getElementById('textnamarekan').innerHTML = 'Nama Pemungut :';
//        document.getElementById('textnpwp').innerHTML = 'NPWP Pemungut :';
//        document.getElementById('textnamanpwp').innerHTML = 'Nama NPWP Pemungut :';
//        document.getElementById('textalamatnpwp').innerHTML = 'Alamat NPWP Pemungut :';
//        document.getElementById('textkotanpwp').innerHTML = 'Kota NPWP Pemungut :';
//        $('#tabelPajak').show();
//        $('#tabelPajakPg').hide();
//        gridpjpn();
//
//    } else {
//        document.getElementById('textnamarekan').innerHTML = 'Nama Penyetor :';
//        document.getElementById('textnpwp').innerHTML = 'NPWP Penyetor :';
//        document.getElementById('textnamanpwp').innerHTML = 'Nama NPWP Penyetor :';
//        document.getElementById('textalamatnpwp').innerHTML = 'Alamat NPWP Penyetor :';
//        document.getElementById('textkotanpwp').innerHTML = 'Kota NPWP Penyetor :';
//        $('#tabelPajak').hide();
//        $('#tabelPajakPg').show();
//        gridpjpg();
//    }

});

// global variable
var jumbarispjpn = 0;
var jumbarispjpg = 0;

function getParamPajak() {
    var idbku = $('#idBkuRef').val();
    console.log(idbku + " idbku");
    $.getJSON(getbasepath() + "/bkubos/json/getDataParam", {idbku: idbku},
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
    });
}
function setKJS() {
    var optkjs;
    var jenistransaksi = $('#kodeTransaksi').val();

    if (jenistransaksi == "P1") {
        optkjs = '<option value="100">100</option>';
        optkjs += '<option value="402">402</option>';
    } else if (jenistransaksi == "P2") {
        optkjs = '<option value="920">920</option>';
    } else if (jenistransaksi == "P3") {
        optkjs = '<option value="100">100</option>';
        optkjs += '<option value="104">104</option>';
    } else if (jenistransaksi == "P4") {
        optkjs = '<option value="420">420</option>';
    } else if (jenistransaksi == "P5") {
        optkjs = '<option value="920">920</option>';
    } else if (jenistransaksi == "P6") {
    }
    $("#kodekjs").html(optkjs);
    var kodeKjs = $('#kodeKjs').val();
    var kodeMap = $('#kodeMap').val();
    $('#kodekjs').val(kodeKjs);

}
function getDataNPWP() {
    var varurl = getbasepath() + "/bkubos/json/inquirynpwp";
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
function setRumusPajak() {
    document.getElementById("labelrumuspajakp1").style.display = "none";
    document.getElementById("labelrumuspajakp2").style.display = "none";
    document.getElementById("labelrumuspajakp3").style.display = "none";
    document.getElementById("labelrumuspajakp4").style.display = "none";
    document.getElementById("labelrumuspajakp5").style.display = "none";
    document.getElementById("labelbayarpajak").style.display = "none";

    if ($('#jenisPembayaran').val() == "PN") {
        getParamPajak();
        document.getElementById("labelbayarpajak").style.display = "block";

        if ($('#kodeTransaksi').val() == "P1") {
//            document.getElementById("labelrumuspajakp1").style.display = "block";

        } else if ($('#kodeTransaksi').val() == "P2") {
            document.getElementById("labelrumuspajakp2").style.display = "block";

        } else if ($('#kodeTransaksi').val() == "P3") {
            document.getElementById("labelrumuspajakp3").style.display = "block";
        } else if ($('#kodeTransaksi').val() == "P4") {
            document.getElementById("labelrumuspajakp4").style.display = "block";

        } else if ($('#kodeTransaksi').val() == "P5") {
            document.getElementById("labelrumuspajakp5").style.display = "block";

        }
    }
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
// global variable
var jumbarispjpn = 0;
var jumbarispjpg = 0;

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

        //$("#triwulan").html(opt); ; TW sesuai tabel (hasil query ger data)
        if ($('#jenisPembayaran').val() == "PN") {
            document.getElementById('textnamarekan').innerHTML = 'Nama Pemungut :';
            document.getElementById('textnpwp').innerHTML = 'NPWP Pemungut :';
            document.getElementById('textnamanpwp').innerHTML = 'Nama NPWP Pemungut :';
            document.getElementById('textalamatnpwp').innerHTML = 'Alamat NPWP Pemungut :';
            document.getElementById('textkotanpwp').innerHTML = 'Kota NPWP Pemungut :';
            $('#tabelPajak').show();
            $('#tabelPajakPg').hide();
            gridpjpn();

        } else {
            document.getElementById('textnamarekan').innerHTML = 'Nama Penyetor :';
            document.getElementById('textnpwp').innerHTML = 'NPWP Penyetor :';
            document.getElementById('textnamanpwp').innerHTML = 'Nama NPWP Penyetor :';
            document.getElementById('textalamatnpwp').innerHTML = 'Alamat NPWP Penyetor :';
            document.getElementById('textkotanpwp').innerHTML = 'Kota NPWP Penyetor :';
            $('#tabelPajak').hide();
            $('#tabelPajakPg').show();
            gridpjpg();
        }
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

function setMasaPajak2() {
    var bulanmp1 = $('#masapajak1').val();

    $('#masapajak2').val(bulanmp1);
}


function gridpjpn() {
    jumbarispjpn = 0;
    var total = 0;
    if (typeof myTablePajak == 'undefined') {
        myTablePajak = $('#pajaktable').dataTable({
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
            "sAjaxSource": getbasepath() + "/bkubos/json/listpajakspj",
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
                {name: "nobkuref", value: $("#noBkuRef").val()},
                {name: "idbku", value: $("#idBkuRef").val()},
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
                    rumus = "<select id='ppnP2" + index + "' name='ppnP2" + index + "' onchange=hitungP2(this.value," + index + ") ><option value='-'>Pilih</option><option value='0'>NON PPN</option><option value='1'>PPN</option></select>";

                } else if (jenispajak == "P3") {
                    rumus = "<select id='ppnP3" + index + "' name='ppnP3" + index + "' onchange=hitungP3(" + index + ") ><option value='-'>Pilih</option><option value='0'>NON PPN</option><option value='1'>PPN</option></select> &nbsp <select id='persenP3" + index + "' name='persenP3" + index + "' onchange=hitungP3(" + index + ") ><option value='-'>Pilih</option><option value='2'>2%</option></select>";
                } else if (jenispajak == "P4") {
                    rumus = "<select id='ppnP4" + index + "' name='ppnP4" + index + "' onchange=hitungP4(" + index + ") ><option value='-'>Pilih</option><option value='0'>NON PPN</option><option value='1'>PPN</option></select> &nbsp <select id='persenP4" + index + "' name='persenP4" + index + "' onchange=hitungP4(" + index + ") ><option value='-'>Pilih</option><option value='0.5'>0.5%</option></select>";
                } else if (jenispajak == "P5") {
                    rumus = "<select id='persenP5" + index + "' name='persenP5" + index + "' onchange=hitungP5(this.value," + index + ")><option value='-'>Pilih</option><option value='1'>1%</option><option value='10'>10%</option></select>";
                }

                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var idblrinci = "<input type='hidden' id='idblrinci" + index + "' value='" + aData['idBlRinci'] + "' />";
                var idbkurinci = "<input type='hidden' id='idbkurinci" + index + "' value='" + aData['idBkuRinci'] + "' />";
                var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var idkomponen = "<input type='hidden' id='idkomponen" + index + "' value='" + aData['idKomponen'] + "' />";
                var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:350px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";
                var komponen = "<textarea id='komponen" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['namaKomponen'] + "</textarea>";

                var nilaispj = "<input type='text' name='nilaispj" + index + "' id='nilaispj" + index + "'  class='inputmoney'  value='" + aData['nilaiSpj'] + "' readOnly='true' />";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiPajakSpj'] + "' onkeyup='pasangvalidasinilaipjpn(" + index + ");hitungTotalPjPn()' onchange='pasangvalidasinilaipjpn(" + index + ");hitungTotalPjPn()' />";
                var nilainetto = "<input type='text' name='nilainetto" + index + "' id='nilainetto" + index + "'  class='inputmoney'  value='" + aData['nilaiNettoSpj'] + "' readOnly='true' />";
                var nilaiawal = "<input type='hidden' id='nilaiawal" + index + "' value='" + aData['nilaiPajakSpj'] + "' />";

                total = total + aData['nilaiPajakSpj'];
                $('#sumpjpn').autoNumeric('set', total);

                $('td:eq(0)', nRow).html(index + idbkurinci + nilaiawal);
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
            var rumus = $("#rumuspajakp1").val();

            // id = '" + index + "'  id = '" + index + "' name = 'persenP1" + index + "' onchange = hitungP1(" + index + ") > < option value = '-' > Pilih < /option></select > ";
            if (rumus == "1") {
                opt1 = '<option value="1">NPWP</option>';
                opt2 = '<option value="5">5%</option>';
                opt2 += '<option value="10">10%</option>';
                opt2 += '<option value="15">15%</option>';

            } else if (rumus == "2") {
                opt1 = '<option value="2">NON NPWP</option>';
                opt2 = '<option value="6">6%</option>';
                opt2 += '<option value="12">12%</option>';
                opt2 += '<option value="18">18%</option>';

            } else {
                opt1 = '<option value="-">Pilih</option>';
                opt2 = "";
            }

            $("#ppnP1" + i).html(opt1);
            $("#persenP1" + i).html(opt2);
            $("#persenP1" + i).val($("#rumuspersenp1").val());

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

function hitungP1(index) {
    var hasil, netto;
    var nilai = accounting.unformat($("#nilaispj" + index).val(), ",");
    var kodepns = $("#cpns").val(), kodepegawai = $("#cpegawai").val();
    var pengali;
    if ($("#cnpwp").val() != null && $("#cnpwp").val() != '') {
        pengali = 1.0;
    } else {
        pengali = 1.2;
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
        $('#nilaiinput' + index).autoNumeric('set', nilaisisa); // nilaisisa harus yang di unformat :)
    }

}

function hitungTotalPjPn() {
    var total = 0;

    for (var a = 1; a <= jumbarispjpn; a++) {
        total += parseFloat(accounting.unformat($("#nilaiinput" + a).val(), ","));
    }

    //$("#sumpjpn").val(accounting.formatNumber(total, 2, '.', ","));
    $('#sumpjpn').autoNumeric('set', total);
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
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 100,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkubos/json/listpajakpg",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "nobkumohon", value: $("#noBkuRef").val()},
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

                var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:500px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";
                var komponen = "<textarea id='komponen" + index + "'readonly style='border:none;margin:0;width:450px;'>" + aData['namaKomponen'] + "</textarea>";

                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiPajakSpj'] + "' readOnly='true' />";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(akun + idbas + textkodeakun);
                $('td:eq(2)', nRow).html(komponen + idkomponen);
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


function simpan() {

    var nobukti = $("#noBukti").val();
    var tglDok = $("#tglDok").val();
    var bulanTglDok = tglDok.substr(3, 2);
    var tahunTglDok = tglDok.substr(6, 4);
    var filling = $("#inboxFile").val();
    var nrk = $("#nrkPptk").val();
    var nippptk = $("#nipPptk").val();
    var namanpwp = $("#namanpwp").val();

    var namapptk = $("#namaPptk").val();
    var idKegiatan = $("#idKegiatan").val();
    var namarekan = $("#namaRekan").val();
    var npwp = $("#npwp").val();
    var i, jumlah;
    var datalengkap = true;

    if ($("#jenisPembayaran").val() == "PN") {
        jumlah = jumbarispjpn;
    } else {
        jumlah = jumbarispjpg;
    }

    for (i = 0; i < jumlah; i++) {
        var nilai = parseFloat(accounting.unformat($('#nilaiinput' + i).val(), ","));

        if (nilai < 0) { // validasinya ganti, nilai ga boleh 0 (KALO EDIT BOLEH, KL SIMPAN BARU GA BOLEH)
            datalengkap = false;
        }
    }

    if (nrk == "" || nobukti == "" || tglDok == "" || filling == "" || idKegiatan == "" || nippptk == "" || namapptk == "" || namarekan == "" || ($("#jenisPembayaran").val() == "PN" && npwp == "") || (npwp != "" && namanpwp == "") || $('#tahunPajak').val() == "") {
        bootbox.alert("Pengisian Data Harus Lengkap");
    } else if (tahunTglDok !== $("#tahun").val()) {
        bootbox.alert("Tahun Dokumen Harus Sesuai Tahun yang Login");
    } else if (parseInt($("#masapajak1").val()) > parseInt($("#masapajak2").val())) {
        bootbox.alert("Bulan Masa Pajak Pertama Tidak Boleh Lebih Besar dari Bulan Masa Pajak Kedua");
    } else if (datalengkap == false) {
        bootbox.alert("Nilai Pajak Tidak Boleh Minus");
    } else {
        update();
    }

}

function update() {
    var jenisbayar = $("#jenisPembayaran").val();
    var jumlah, idblrinci;
    var nilaimasuk, nilaikeluar;
    var persen = '0';
    if ($('#kodeTransaksi').val() == 'P4' && $('#rumuspersenp4').val() == '0.5') {
        persen = $('#rumuspersenp4').val();
    }
    var masapajak = $("#masapajak1").val().toString() + $("#masapajak2").val().toString();

    if (jenisbayar == "PN") {
        jumlah = jumbarispjpn;
    } else {
        jumlah = jumbarispjpg;
    }

    if (jumlah > 0) {
        var varurl = getbasepath() + "/bkubos/json/prosesupdatepajak";
        var dataac = [];
        var nilailist = [];
        var i;
        var banyak = 0;

        for (i = 0; i < jumlah; i++) { // list
            var id = i + 1;
            var nilaiawal = 0;
            var nilai = parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ","));
            var idrinci = $("#idbkurinci" + id).val();

            if (jenisbayar == "PN") {
                nilaiawal = parseFloat(accounting.unformat($('#nilaiawal' + id).val(), ","));
                nilaimasuk = nilai.toString();
                nilaikeluar = "0";
            } else {
                nilaimasuk = "0";
                nilaikeluar = nilai.toString();
            }

            if (nilai > 0 || idrinci > 0) {
                var pararr = {
                    idbkurinci: idrinci,
                    idbas: $("#idbas" + id).val(),
                    kodeakun: $("#kodeakun" + id).val(),
                    idkomponen: $("#idkomponen" + id).val(),
                    idblrinci: $("#idblrinci" + id).val(),
                    nilaipajakspj: (nilaiawal - nilai),
                    nilaimasuk: nilaimasuk,
                    nilaikeluar: nilaikeluar
                };

                nilailist[banyak] = pararr;
                banyak = banyak + 1;
            }

        }

        var datajour = {
            idbku: $("#idBku").val(),
            tahun: $("#tahun").val(),
            idsekolah: $("#idsekolah").val(),
            nobkumohon: $("#noBkuMohon").val(),
            kodetransaksi: $('#kodeTransaksi').val(),
            nobukti: $("#noBukti").val(),
            tgldok: $("#tglDok").val(),
            fileinbox: $("#inboxFile").val(),
            nrk: $("#nrkPptk").val(),
            namapptk: $("#namaPptk").val(),
            nippptk: $("#nipPptk").val(),
            uraian: $("#uraian").val(),
            carabayar: $("#kodePembayaran").val(),
            idkegiatan: $("#idKegiatan").val(),
            kodekeg: $("#kodeKeg").val(),
            npwp: $("#npwp").val(),
            namanpwp: $("#namanpwp").val(),
            alamatnpwp: $("#alamatnpwp").val(),
            kotanpwp: $("#kotanpwp").val(),
            namarekan: $("#namaRekan").val(),
            masapajak: masapajak,
            idbkuref: $("#idBkuRef").val(),
            tahunpajak: $("#tahunPajak").val(),
            nobkuref: $("#noBkuRef").val(),
            jenispembayaran: $("#jenisPembayaran").val(),
            jenisbayarpajak: $("#kodePajak").val(),
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
            bootbox.alert("Data Buku Kas Umum Berhasil Diubah");
            if ($('#jenisPembayaran').val() == "PN") {
                gridpjpn();
            } else {
                gridpjpg();
            }
        });
    }

}

function cekhapus() {
    var jenisbayar = $("#jenisPembayaran").val();
    if (jenisbayar == "PN") {
        hapus();
    } else {
        hapusPg();
    }
}

function hapus() {
    var tahun = $("#tahun").val();
    var jenisbayar = $("#jenisPembayaran").val();

    bootbox.confirm("Anda akan menghapus data BKU ini, lanjutkan ? ", function(result) {
        if (result) {
            var varurl = getbasepath() + "/bkubos/json/prosesdeletepjpn";
            var dataac = [];
            var nilailist = [];
            var jumlah, i;

            for (i = 0; i < jumbarispjpn; i++) {
                var id = i + 1;
                var nilaiawal = parseFloat(accounting.unformat($('#nilaiawal' + id).val(), ","));

                var pararr = {
                    idblrinci: $("#idblrinci" + id).val(),
                    nilaipajakspj: nilaiawal
                };

                nilailist[i] = pararr;

            }
            var datajour = {
                idbku: $("#idBku").val(),
                tahun: tahun,
                kodetransaksi: $("#kodeTransaksi").val(),
                idbkuref: $("#idBkuRef").val(),
                nobkuref: $("#noBkuRef").val(),
                jenisbayarpajak: $("#kodePajak").val(),
                jenispembayaran: $("#jenisPembayaran").val(),
                idsekolah: $("#idsekolah").val(),
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
                bootbox.alert("Data Buku Kas Umum Berhasil Dihapus");
                window.location.href = getbasepath() + "/bkubos/indexbkubos"; // ke form index
            });

        } else {
            bootbox.hideAll();
            return result;
        }
    });

}

function hapusPg() {
    var tahun = $("#tahun").val();

    bootbox.confirm("Anda akan menghapus data BKU ini, lanjutkan ? ", function(result) {
        if (result) {
            var varurl = getbasepath() + "/bkubos/json/prosesdeletebyid";
            var dataac = [];

            var datajour = {
                idbku: $("#idBku").val(),
                tahun: tahun,
                kodetransaksi: $("#kodeTransaksi").val(),
                idsekolah: $("#idsekolah").val(),
                jenisbayarpajak: $("#kodePajak").val(),
                jenispembayaran: $("#jenisPembayaran").val(),
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
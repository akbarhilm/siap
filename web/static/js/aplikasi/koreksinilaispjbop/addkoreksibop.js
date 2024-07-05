$(document).ready(function() {
    setTriwulan();
    setTriwulanKoreksi();
    getDataPegawai();
});
// variabel global
var maxTriwulan;
var jumbarisspj;
function getDataPegawai() {
    var nrk = $("#nrkPptk").val().substr(0, 6);
    var nrkdepag = $("#nrkPptk").val().substr(0, 3);
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
            //console.log("NRK: " + nrk);
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

function setTriwulan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/koreksinilaispjbop/json/getListTriwulanByRekap", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        var banyak, kode, ket;
        var opt = ""; // untuk tampilan awal combo

        banyak = result.aData.length;
        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                if (result.aData[i]['triwulan'] == "1") {
                    opt += '<option value="1">Triwulan I</option>';
                } else if (result.aData[i]['triwulan'] == "2") {
                    opt += '<option value="2">Triwulan II</option>';
                } else if (result.aData[i]['triwulan'] == "3") {
                    opt += '<option value="3">Triwulan III</option>';
                } else if (result.aData[i]['triwulan'] == "4") {
                    opt += '<option value="4">Triwulan IV</option>';
                }

            }
        }

        $("#triwulan").html(opt);
    });
}

function gridspj() {
    jumbarisspj = 0;
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
            "sAjaxSource": getbasepath() + "/koreksinilaispjbop/json/listspjkoreksi",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            }, "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "nobkumohon", value: "-99"}, // add default -99
                {name: "nomohonref", value: $("#noBkuRef").val()},
                {name: "triwulan", value: $("#triwulan").val()},
                {name: "idkegiatan", value: $("#idKegpop").val()}
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

                //idBku, idBas, idKomponen, idBlRinci, noUrut, namaSubKegiatan,      keteranganRinci, kodeKomponen, namaKomponen, komponenPajak,
                //        hargaSatuan, kodeakun, namaakun, nilaiBkuInput, spekKomponen

                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var idbku = "<input type='hidden' id='idbku" + index + "' value='" + aData['idBku'] + "' />";
                var idkomponen = "<input type='hidden' id='idkomponen" + index + "' value='" + aData['idKomponen'] + "' />";
                var idblrinci = "<input type='hidden' id='idblrinci" + index + "' value='" + aData['idBlRinci'] + "' />";
                var nourut = "<input type='hidden' id='nourut" + index + "' value='" + aData['noUrut'] + "' />";
                var namasubkeg = "<input type='hidden' id='namasubkeg" + index + "' value='" + namasub + "' />";
                var ketrinci = "<input type='hidden' id='ketrinci" + index + "' value='" + ketrincival + "' />";
                var komponenpajak = "<input type='hidden' id='komponenpajak" + index + "' value='" + aData['komponenPajak'] + "' />";
                var hargasatuan = "<input type='hidden' id='hargasatuan" + index + "' value='" + aData['hargaSatuan'] + "' />";
                var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";
                var komponen = "<textarea id='komponen" + index + "'readonly style='border:none;margin:0;width:250px;'>" + aData['namaKomponen'] + "</textarea>"; // aData['kodeKomponen'] + "/" +
                var ketrincitext = "<textarea id='ketrincitext" + index + "'readonly style='border:none;margin:0;width:250px;'>" + aData['spekKomponen'] + " / " + namasub + " / " + ketrincival + "</textarea>";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' onkeyup='pasangvalidatebesarperfield(" + index + ")' onchange='pasangvalidatebesarperfield(" + index + ")' />";
                var nilaiAngg = "<input type='text' name='nilaiAnggaran" + index + "' id='nilaiAnggaran" + index + "'  class='inputmoney'  value='" + aData['nilaiAnggaran'] + "' readOnly='true' />";
                $('td:eq(0)', nRow).html(index + idbku + idblrinci);
                $('td:eq(1)', nRow).html(akun + idbas + textkodeakun);
                $('td:eq(2)', nRow).html(komponen + idkomponen);
                $('td:eq(3)', nRow).html(ketrincitext + ketrinci + nourut + namasubkeg + komponenpajak + hargasatuan);
                $('td:eq(4)', nRow).html(nilaiAngg);
                $('td:eq(5)', nRow).html(nilaiinput);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center", "sWidth": "20%"},
                {"mDataProp": "kodeKomponen", "bSortable": false, sClass: "center", "sWidth": "20%"},
                {"mDataProp": "keteranganRinci", "bSortable": false, sClass: "center", "sWidth": "26%"},
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right", "sWidth": "15%"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function pasangvalidatebesarperfield(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaiangg = accounting.unformat($("#nilaiAnggaran" + index).val(), ",");
    if (nilaiinput > nilaiangg) {
        bootbox.alert("Nilai SPJ tidak boleh lebih besar dari Nilai Anggaran.");
        $('#nilaiinput' + index).autoNumeric('set', nilaiangg); // nilaisisa harus yang di unformat :)
    }

    hitungnilaispj();
}

function hitungnilaispj() {
    var total = 0;
    for (var a = 1; a <= jumbarisspj; a++) {
        total += parseFloat(accounting.unformat($("#nilaiinput" + a).val(), ","));
    }

    $("#sumspj").val(accounting.formatNumber(total, 2, '.', ","));
    $("#nilaiSpj").val(total);
}

function simpan() {
    var table3 = document.getElementById('spjtablebody');
    var rows3 = table3.rows;
    var jum3 = rows3.length;
    var datalengkap = true;
    var nrk = $("#nrkPptk").val();
    var nippptk = $("#nipPptk").val();
    var namapptk = $("#namaPptk").val();
    var idKegiatanSpj = $("#idKegpop").val();
    var nilaitotalspj = $("#nilaiSpj").val();
    var pagukoreksi = accounting.unformat($("#pagukoreksi").val(), ",");
    var tglDok = $("#tglDok").val();
    var tahunTglDok = tglDok.substr(6, 4);
    var banyak = 0;
    var nobukti = $("#noBuktiDok").val();

    if ($("#kodeTransaksi").val() == "JJ") {

        if ((jum3 > 0) && ($("#akunnama1").val() !== "")) {

            for (var a = 1; a <= jum3; a++) {
                var nilai = parseFloat(accounting.unformat($('#nilaiinput' + a).val(), ","));
                if (nilai < 0) {
                    datalengkap = false;
                } else {
                    banyak = banyak + 1;
                }
            }

            if (nrk == "" || nobukti == "" || tglDok == "" || idKegiatanSpj == "" || nippptk == "" || namapptk == "" || banyak == 0) {
                bootbox.alert("Pengisian Data Harus Lengkap");
                $('#btnSimpan').attr("disabled", false);
            } else if ($('#triwulan').val() == "-") {
                bootbox.alert("Pilih Triwulan Terlebih Dulu");
                $('#btnSimpan').attr("disabled", false);
            } else if (tahunTglDok !== $("#tahun").val()) {
                bootbox.alert("Tahun Dokumen Harus Sesuai Tahun yang Login");
                $('#btnSimpan').attr("disabled", false);
            } else if (parseInt(nilaitotalspj) > parseInt(pagukoreksi)) {
                bootbox.alert("Total Koreksi Tidak Boleh Lebih Besar dari Pagu SPJ Koreksi");
                $('#btnSimpan').attr("disabled", false);
            } else {
                simpanKoreksi();
            }
        }
    }

}

function simpanKoreksi() {

    if (jumbarisspj > 0) {
        var varurl = getbasepath() + "/koreksinilaispjbop/json/prosessimpankoreksi";
        var dataac = [];
        var nilailist = [];
        var i;
        var banyak = 0;

        var tglPost = $("#tglPost").val();
        var dd, mm, yy, tanggal;
        dd = tglPost.substr(0, 2);
        mm = tglPost.substr(3, 2);
        yy = tglPost.substr(6, 4);
        tanggal = yy + mm + dd;


        for (i = 0; i < jumbarisspj; i++) { // list
            var id = i + 1;
            var nilai = parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ","));
            var idrinci = $("#idbku" + id).val();
            if (nilai > 0 || idrinci > 0) {

                var pararr = {
                    nilaimasuk: parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ",")).toString(),
                    nilaikeluar: "0",
                    nilaipajak: "0",
                    idrinci: $("#idbku" + id).val(),
                    idbas: $("#idbas" + id).val(),
                    kodeakun: $("#kodeakun" + id).val(),
                    idkomponen: $("#idkomponen" + id).val(),
                    idblrinci: $("#idblrinci" + id).val(),
                    nourut: $("#nourut" + id).val(),
                    namasubkeg: $("#namasubkeg" + id).val(),
                    komponenpajak: $("#komponenpajak" + id).val(),
                    volume: "0",
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
            tglposting: tanggal,
            tgldok: $("#tglDok").val(),
            nrk: $("#nrkPptk").val(),
            uraian: $("#uraian").val(),
            idbku: $("#idBku").val(),
            namapptk: $("#namaPptk").val(),
            nippptk: $("#nipPptk").val(),
            nobkuref: $("#noBkuRef").val(),
            idkegiatan: $("#idKegpop").val(),
            kodekeg: $("#kodeKegpop").val(),
            triwulan: $("#triwulan").val(),
            triwulankoreksi: $("#triwulanKoreksi").val(),
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

            bootbox.alert("Data Koreksi Akun Belanja Berhasil Disimpan");
            $('#btnSimpan').attr("disabled", true);
            //window.location.href = getbasepath() + "/koreksinilaispjbop/index"; // ke form index

        });
    } else {
        $('#btnSimpan').attr("disabled", false);
    }
}

function setTriwulanKoreksi() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/koreksiakunbop/json/getTriwulanByRekap", {tahun: tahun, idsekolah: idsekolah},
    function(result) {

        $("#triwulanKoreksi").val(result);

    });
}
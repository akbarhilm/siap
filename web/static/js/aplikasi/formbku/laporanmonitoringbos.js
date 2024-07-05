$(document).ready(function() {
    document.getElementById("labelakunbelanja").style.display = "none";
    $('#labeltriwulan').hide();
    $('#labeltanggal').hide();
    $('#bulan1').hide();
    $('#formcetaksetortablelabel').hide();
    setAkunBelanja();
    //setTriwulan();
    //setBulan();
    setPanelAkun();
//    cekRekap();

});

function cetak() {
    var idsekolah = $('#idsekolah').val();
    var triwulan = $("#triwulan").val();
    var unformat = $("#tanggal").val();
    //var wilayah = $("#wilayah").val();
    var tanggal = unformat.substr(6, 4) + unformat.substr(3, 2) + unformat.substr(0, 2);
    var tahun = $("#tahun").val();
    var bulan = $("#bulan").val();
    var akunbelanja = $("#akunbelanja").val();
    var jenislaporan = $("#jenislap").val();
    var jenjang = $("#jenjang").val();
    var idskpd = $("#idSkpd").val();
    var jenislap = $('#jenislap').val();
    console.log("idskpd = " + idskpd);
    /*
     if ((idsekolah == "") || (idskpd == "")) {
     bootbox.alert("Data Tidak Lengkap");
     console.log("idsekolah kosong = " + idsekolah);
     } else {
     console.log("idsekolah ada isinya = " + idsekolah);
     window.location.href = getbasepath() + "/formbkubos/json/prosescetakmon?tahun="
     + tahun + "&idsekolah=" + idsekolah + "&triwulan=" + triwulan + "&tanggal=" + tanggal + "&jenislaporan=" + jenislaporan + "&bulan=" + bulan + "&akunbelanja=" + akunbelanja + "&jenjang=" + jenjang + "&idskpd=" + idskpd;
     } */
    console.log("jenislap = " + jenislap);
    if (idskpd == "") {
        if (idsekolah == "") {
            bootbox.alert("Data Tidak Lengkap");
            console.log("idsekolah kosong = " + idsekolah);
        } else {
            bootbox.alert("Data Tidak Lengkap");
            console.log("idsekolah ada isinya tapi idskpd null = " + idsekolah);
        }
    }
    else {
        console.log("----masuk else----");
        if (idsekolah == "") {
            if ((jenislap == '3') || (jenislap == '4') || (jenislap == '5') || (jenislap == '7') || (jenislap == '10')) {
                window.location.href = getbasepath() + "/formbkubos/json/prosescetakmon?tahun="
                        + tahun + "&idsekolah=" + idsekolah + "&triwulan=" + triwulan + "&tanggal=" + tanggal + "&jenislaporan=" + jenislaporan + "&bulan=" + bulan + "&akunbelanja=" + akunbelanja + "&jenjang=" + jenjang + "&idskpd=" + idskpd;
            }
            else {
                bootbox.alert("Data Tidak Lengkap");
            }
        } else {
            if ((jenislap == '1') || (jenislap == '2') || (jenislap == '6') || (jenislap == '8') || (jenislap == '9')) {
                window.location.href = getbasepath() + "/formbkubos/json/prosescetakmon?tahun="
                        + tahun + "&idsekolah=" + idsekolah + "&triwulan=" + triwulan + "&tanggal=" + tanggal + "&jenislaporan=" + jenislaporan + "&bulan=" + bulan + "&akunbelanja=" + akunbelanja + "&jenjang=" + jenjang + "&idskpd=" + idskpd;
            }
            else {
                bootbox.alert("Data Tidak Lengkap");
            }
        }
    }
}
function setAkunBelanja() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/formbkubos/json/getAkunBelanja", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['kodeakun'];
                ket = result.aData[i]['namaakun'];

                opt += '<option value="' + kode + '" >' + ket + '</option>';

            }
            $("#akunbelanja").html(opt);

        }

    });
}
function setPanelAkun() {

    if ($('#jenislap').val() == '1') { //Rincian Pengajuan Pembayaran Anggaran BOs
        $('#labeltriwulan').hide();
        $('#labeltanggal').show();
        $('#bulan1').hide();
        $('#labelakunbelanja').hide();
        $('#jenjang1').hide();
        $('#labelsekolah').show();
        $("#btncetak").show();
        $('#formcetaksetortablelabel').hide();
        $('#idsekolah').val('');
        $('#sekolah').val('');
    } else if ($('#jenislap').val() == '2') { //buku pembantu rincian objek
        $('#labeltriwulan').hide();
        $('#labeltanggal').hide();
        $('#bulan1').show();
        $('#labelakunbelanja').show();
        setAkunBelanja();
        $('#jenjang1').hide();
        $('#labelsekolah').show();
        $("#btncetak").show();
        $('#formcetaksetortablelabel').hide();
        $('#idsekolah').val('');
        $('#sekolah').val('');
    } else if ($('#jenislap').val() == '3') {
        $('#labeltriwulan').hide();
        $('#labeltanggal').hide();
        $('#bulan1').hide();
        $('#labelakunbelanja').hide();
        $('#jenjang1').show();
        $('#labelsekolah').hide();
        $("#btncetak").show();
        $('#formcetaksetortablelabel').hide();
        $('#idsekolah').val('');
        $('#sekolah').val('');
    } else if ($('#jenislap').val() == '4') {
        $('#labeltriwulan').hide();
        $('#labeltanggal').show();
        $('#bulan1').hide();
        $('#labelakunbelanja').hide();
        $('#jenjang1').hide();
        $('#labelsekolah').hide();
        $("#btncetak").show();
        $('#formcetaksetortablelabel').hide();
        $('#idsekolah').val('');
        $('#sekolah').val('');
    } else if ($('#jenislap').val() == '5') {
        $('#labeltriwulan').hide();
        $('#labeltanggal').show();
        $('#bulan1').hide();
        $('#labelakunbelanja').hide();
        $('#jenjang1').hide();
        $('#labelsekolah').hide();
        $("#btncetak").show();
        $('#formcetaksetortablelabel').hide();
        $('#idsekolah').val('');
        $('#sekolah').val('');
    } else if ($('#jenislap').val() == '6') { //Report_MonitoringTransfer-BOS
        $('#labeltriwulan').show();
        $('#labeltanggal').show();
        $('#bulan1').hide();
        $('#labelakunbelanja').hide();
        $('#jenjang1').hide();
        $('#labelsekolah').show();
        $("#btncetak").show();
        $('#formcetaksetortablelabel').hide();
        $('#idsekolah').val('');
        $('#sekolah').val('');
    } else if ($('#jenislap').val() == '7') {
        $('#labeltriwulan').show();
        $('#labeltanggal').hide();
        $('#bulan1').hide();
        $('#labelakunbelanja').hide();
        $('#jenjang1').hide();
        $('#labelsekolah').hide();
        $("#btncetak").show();
        $('#formcetaksetortablelabel').hide();
        $('#idsekolah').val('');
        $('#sekolah').val('');
    } else if ($('#jenislap').val() == '8') {
        $('#labeltriwulan').hide();
        $('#labeltanggal').hide();
        $('#bulan1').hide();
        $('#labelakunbelanja').hide();
        $('#jenjang1').hide();
        $('#labelsekolah').show();
        $("#btncetak").hide();
        $('#formcetaksetortablelabel').show();
        grid();
        $('#idsekolah').val('');
        $('#sekolah').val('');
    } else if ($('#jenislap').val() == '9') {
        $('#labeltriwulan').show();
        $('#labeltanggal').hide();
        $('#bulan1').hide();
        $('#labelakunbelanja').hide();
        $('#jenjang1').hide();
        $('#labelsekolah').show();
        $("#btncetak").show();
        $('#formcetaksetortablelabel').hide();
        $('#idsekolah').val('');
        $('#sekolah').val('');
    } else if ($('#jenislap').val() == '10') { //ditambahkan report jenis baru pada 190104 -- report RealisasiKegiatan_SKPD-BOS
        $('#labeltriwulan').show();
        $('#labeltanggal').hide();
        $('#bulan1').hide();
        $('#labelakunbelanja').hide();
        $('#jenjang1').hide();
        $('#labelsekolah').hide();
        $("#btncetak").show();
        $('#formcetaksetortablelabel').hide();
        $('#idsekolah').val('');
        $('#sekolah').val('');
    } else {
        $('#labeltriwulan').hide();
        $('#labeltanggal').hide();
        $('#bulan1').hide();
        $('#labelakunbelanja').hide();
        $('#jenjang1').hide();
        $('#labelsekolah').hide();
        $("#btncetak").hide();
        $('#idsekolah').val('');
        $('#sekolah').val('');
        $('#formcetaksetortablelabel').hide();
    }

}

function setTriwulan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/formbkubos/json/setTriwulan", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        //console.log(result);

        var banyak, triwulan;
        var opt = "";

        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                triwulan = result.aData[i]['triwulan'];

                opt += '<option value="' + triwulan + '"> Triwulan ' + triwulan + ' </option>';
            }
            $('#btncetak').attr("disabled", false);
        } else {
            $('#btncetak').attr("disabled", true);
        }

        $("#triwulan").html(opt);

    });
}
function setBulan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/formbkubos/json/setBulan", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        console.log("cek anitaikan result bulan=", +result);
        var banyak, bulan;
        var opt = "";
        banyak = result.aData.length;
        console.log("cek anitaikan banyak=", +banyak);
        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                bulan = result.aData[i]['bulan'];
                opt += '<option value="' + bulan + '"> Bulan ' + bulan + ' </option>';
            }
            $('#btncetak').attr("disabled", false);
        } else {
            $('#btncetak').attr("disabled", true);
        }
        $("#bulan").html(opt);
    });
}
function cekRekap() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    $.getJSON(getbasepath() + "/formbkubos/json/cekRekap", {tahun: tahun, idsekolah: idsekolah},
    function(result) {
        if (result <= 0) {
            //console.log(" banyak <=0");
            bootbox.alert("Anda belum melakukan draft tutup bku. Silahkan melakukan draft tutup bku terlebih dahulu.");
        }
        else {
            //console.log(" banyak >0");
        }
    });
}


function grid() {
    var urljson = getbasepath() + "/formbkubos/json/listcetaksetor";
    if (typeof myTable == 'undefined') {
        myTable = $('#cetaksetortable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            //"sDom": '<"top"i>lrt<"bottom"i>p<"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".checkbox").hide();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idsekolah", value: $('#idsekolah').val()},
                {name: "tahun", value: $('#tahun').val()}
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
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {///cetakspp
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var idsekolah = $('#idsekolah').val();
                var tahun = $('#tahun').val();

                var nrkpa = "<input type='hidden' id='nrkpa" + aData['idSetor'] + "' value='" + aData['nrkPA'] + "'  />";
                var nippa = "<input type='hidden' id='nippa" + aData['idSetor'] + "' value='" + aData['nipPA'] + "'  />";
                var namapa = "<input type='hidden' id='namapa" + aData['idSetor'] + "' value='" + aData['namaPA'] + "'  />";
                var jabpa = "<input type='hidden' id='jabpa" + aData['idSetor'] + "' value='" + aData['pangkatPA'] + "'  />";
                var nrkpk = "<input type='hidden' id='nrkpk" + aData['idSetor'] + "' value='" + aData['nrkPK'] + "'  />";
                var nippk = "<input type='hidden' id='nippk" + aData['idSetor'] + "' value='" + aData['nipPK'] + "'  />";
                var namapk = "<input type='hidden' id='namapk" + aData['idSetor'] + "' value='" + aData['namaPK'] + "'  />";
                var jabpk = "<input type='hidden' id='jabpk" + aData['idSetor'] + "' value='" + aData['pangkatPK'] + "'  />";
                var donlod = "-";
                var batal = "-";
                var cekprint = "-";

                var stat = aData['statusCetak'];
                if (stat == 'PENGAJUAN') {
                    cekprint = "<input type='checkbox' name='cek" + aData['idSetor'] + "'  id='cek" + aData['idSetor'] + "' value='" + aData['idSetor'] + "' class='checkbox' />";
                } else if (stat == 'CETAK') {
                    donlod = "<a class='icon-book' href='" + getbasepath() + "/setor/cetaksetor/" + tahun + "/" + idsekolah + "/" + aData['noSetor'] + "/" + aData['ctrx'] + "' ></a>";
                    batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalcetak(" + aData['idSetor'] + "," + aData['noSetor'] + ") ></a>";
                } else if (stat == 'INPUTBKU') {
                    donlod = "<a class='icon-book' href='" + getbasepath() + "/setor/cetaksetor/" + tahun + "/" + idsekolah + "/" + aData['noSetor'] + "/" + aData['ctrx'] + "' ></a>";
                }

                $('td:eq(0)', nRow).html(index + nrkpa + nippa + namapa + jabpa + nrkpk + nippk + namapk + jabpk);
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiSetor']));
                $('td:eq(7)', nRow).html(cekprint);
                $('td:eq(8)', nRow).html(donlod);
                $('td:eq(9)', nRow).html(batal);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "tahunAnggaran", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSetor", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeTransaksi", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "nilaiSetor", "bSortable": false, sClass: "kanan", sDefaultContent: "-"},
                {"mDataProp": "statusCetak", "bSortable": false, sDefaultContent: "-", sClass: "left"},
                {"mDataProp": "namaPA", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"}
            ]
        });
        //$("div.top").html("<span onclick=trigercetak()  class='Button btn dark' style='float: right'>Proses Cetak</span>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
$(document).ready(function() {
    getMaxStatus();
    getMaxTriwulan();
});

// variable global
var kodesetor = new Array();
var kodetrxid = new Array();
var max;
function getMaxStatus() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();

    $.getJSON(getbasepath() + "/bkubop/json/getMaxStatus", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan},
    function(result) {

        max = result.aData;

    });
}
function gridbku() {
    if (typeof myTable == 'undefined') {

        myTable = $('#jourtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, 200, 5000], [25, 50, 100, 200, "All"]],
            "iDisplayLength": 50,
            "sAjaxSource": getbasepath() + "/bkubop/json/listindex",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "triwulan", value: $("#triwulan").val()},
                {name: "tipe", value: $("#cbbku").val()}
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
                var kodetrans = aData['kodeTransaksi'];
                var tanda = '';
                if ((aData['statusBank'] == '1' && kodetrans == 'JJ' && aData['kodeVA'] != 1) ||
                        (aData['statusBank'] != '1' && aData['statusBank'] != '9' && kodetrans != 'JJ' && aData['tglPosting'] != "" && aData['tglPosting'] != null)
                        || (aData['tglPosting'] != "" && aData['tglPosting'] != null && kodetrans == 'JJ' && aData['kodeVA'] == 1)) {
                    tanda = "<span class='glyphicon glyphicon-ok-sign' style='color:green'></span>";
                } else if (aData['saldoKas'] == '9') {
                    tanda = "<span class='glyphicon glyphicon-remove-sign' style='color:red'></span>";
                }
                var tahun = $("#tahun").val();
                var idsekolah = $("#idsekolah").val();
                var tglDok, akun;
                var thn, mm, dd;

                console.log("cek idsek=" + idsekolah);

                if (index == 1) {
                    tglDok = "";
                } else {
                    var tanggalDok = aData['tglDok'];

                    thn = tanggalDok.substr(0, 4);
                    mm = tanggalDok.substr(5, 2);
                    dd = tanggalDok.substr(8, 2);
                    tglDok = dd + "/" + mm + "/" + thn;

                }


                var nilaim = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                var nilaik = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");
                var nilais = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + nilaim + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + nilaik + "' readOnly='true'/>";
                var saldo = "<input type='text' name='saldo" + index + "' id='saldo" + index + "'  class='inputmoney'  value='" + nilais + "' readOnly='true'/>";
                var idtrans = "<input type='hidden' id='kodetransaksi" + index + "' value='" + aData['idTransaksi'] + "' />";
                var uraian = "<textarea id='uraianbukti" + index + "'readonly style='border:none;margin:0;width:180px;'>" + aData['uraianBukti'] + "</textarea>";
                var kegiatan = "<textarea id='kegiatan" + index + "'readonly style='border:none;margin:0;width:180px;'>" + aData['ketKegiatan'] + "</textarea>";

                var editjo = "<a href='" + getbasepath() + "/bkubop/editbkujo/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubop/hapusbkujo/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editjg = "<a href='" + getbasepath() + "/bkubop/editbkujg/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubop/hapusbkujg/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editsetor = "<a href='" + getbasepath() + "/bkubop/editbkusetor/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubop/hapusbkusetor/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editspj = "<a href='" + getbasepath() + "/bkubop/editbkuspj/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubop/hapusbkuspj/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editspjsusulan = "<a href='" + getbasepath() + "/bkubop/editbkuspj/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>";
                var editspjbukti = "<a href='" + getbasepath() + "/bkubop/editbkuspjbukti/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>";
                var editspjbukticetak = "<a href='" + getbasepath() + "/bkubop/editbkuspjbukti/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/bkubop/json/prosescetakbku/" + "?idbku=" + aData['idBku'] + "&idsekolah=" + idsekolah + "' class='icon-print' ></a>";
                var editpajak = "<a href='" + getbasepath() + "/bkubop/editbkupajak/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubop/hapusbkupajak/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editc12 = "<a href='" + getbasepath() + "/bkubop/editbkuc12/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubop/hapusbkuc12/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";


                // arsip bku - hanya bisa lihat transaksi
                var arsipjo = "<a href='" + getbasepath() + "/bkubop/arsipbkujo/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsipjg = "<a href='" + getbasepath() + "/bkubop/arsipbkujg/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsippajak = "<a href='" + getbasepath() + "/bkubop/arsipbkupajak/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsipspj = "<a href='" + getbasepath() + "/bkubop/arsipbkuspj/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsipst = "<a href='" + getbasepath() + "/bkubop/arsipbkust/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsipc12 = "<a href='" + getbasepath() + "/bkubop/arsipbkuc12/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";


                var editvalid = "";

                if (index == 1) {
                    akun = "";
                } else if (kodetrans.substr(0, 1) == "P" || kodetrans == "JJ"
                        || kodetrans.substr(0, 1) == "C") {
                    akun = aData['kodeakun'];
                } else {
                    akun = "";
                }

                if (aData['ketKegiatan'] == null) {
                    kegiatan = "";
                }

                if (index == 1) {
                    editvalid = "";

                } else {
                    if (kodetrans == "JJ") {
                        editvalid = arsipspj;
                    } else if (kodetrans == "JO") {
                        editvalid = arsipjo;
                    } else if (kodetrans == "ST") {
                        editvalid = arsipst;
                    } else if (kodetrans == "JG") {
                        editvalid = arsipjg;
                    } else if (kodetrans.substr(0, 1) == "P") {
                        editvalid = arsippajak;
                    } else if (kodetrans.substr(0, 1) == "C") {
                        editvalid = arsipc12;
                    }
                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(tglDok);
                $('td:eq(4)', nRow).html(akun);
                $('td:eq(5)', nRow).html(uraian);
                $('td:eq(6)', nRow).html(kegiatan);
                $('td:eq(7)', nRow).html(nilaimasuk + idtrans);
                $('td:eq(8)', nRow).html(nilaikeluar);
                $('td:eq(9)', nRow).html(saldo);
                $('td:eq(10)', nRow).html(tanda);
                $('td:eq(11)', nRow).html(editvalid);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "tglDok", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBkuMohon", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "uraianBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "ketKegiatan", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "right"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "right"},
                {"mDataProp": "idBas", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "idBas", "bSortable": false, "sWidth": "5%", sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();

    }

}
function setGrid() {
    if ($('#cbbku').val() != '0') {
        getKodeSetor();
        getTotal();
    }
}
function getTotal() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();
    var kodetotal = $('#cbbku').val();

    $.getJSON(getbasepath() + "/bkubop/json/getTotal", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan, kodetotal: kodetotal},
    function(result) {

        var tm = result.aData['nilaiMasuk'];
        var tk = result.aData['nilaiKeluar'];
        var ts = result.aData['nilaiSisa'];

        $("#totmasuk").val(accounting.formatNumber(tm, 2, '.', ","));
        $("#totkeluar").val(accounting.formatNumber(tk, 2, '.', ","));
        $("#totsaldokas").val(accounting.formatNumber(ts, 2, '.', ","));
        $("#saldokas").val(accounting.formatNumber(ts, 2, '.', ","));

    });
}


function getKodeSetor() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var triwulan = $('#triwulan').val();

    $.getJSON(getbasepath() + "/bkubop/json/getKodeSetor", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan},
    function(result) {

        var banyak = result.aData.length;
        var id, kode, trx, nobkumohon;

        if (banyak > 0) {

            for (var i = 0; i < banyak; i++) {
                id = result.aData[i]['idBku'];
                kode = result.aData[i]['kodeSetor'];
                trx = result.aData[i]['kodeTransaksi'];
                // nobkumohon = result.aData['noBkuMohon'];

                kodesetor[id] = kode;
                kodetrxid[id] = trx;
            }
        }

        gridbku();

    });

}

function getMaxTriwulan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();

    $.getJSON(getbasepath() + "/bkubop/json/getMaxTriwulan", {tahun: tahun, idsekolah: idsekolah},
    function(result) {

        $('#triwulan').val(result);

        getTotal();
        getKodeSetor();

    });

}

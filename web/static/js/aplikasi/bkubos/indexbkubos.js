$(document).ready(function() {
    getMaxTriwulan();
});
// variable global
var kodesetor = new Array();
var kodetrxid = new Array();
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
            "sAjaxSource": getbasepath() + "/bkubos/json/listindex",
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
                if ((aData['statusBank'] == '1' && (kodetrans == 'JJ' || kodetrans == 'ST' || kodetrans == 'JG' || kodetrans == 'RT') && aData['kodeVA'] != 1) ||
                        (aData['statusBank'] != '1' && aData['statusBank'] != '9' && (kodetrans != 'JJ' && kodetrans != 'ST') && aData['tglPosting'] != "" && aData['tglPosting'] != null)
                        || (aData['tglPosting'] != "" && aData['tglPosting'] != null && kodetrans == 'JJ' && aData['kodeVA'] == 1)
                        || (aData['tglPosting'] != "" && aData['tglPosting'] != null && kodetrans.substr(0, 1) == "P" && parseInt(aData['nilaiKeluar']) > 0 && aData['statusBpn'] == '2')) {
                    tanda = "<span class='glyphicon glyphicon-ok-sign' style='color:green'></span>";
                } else if ((kodetrans != 'JJ' && kodetrans != 'ST' && kodetrans != 'JG' && kodetrans != 'RT' && aData['kodeRetur'] == 3) || aData['statusBank'] == '9') {
                    tanda = "<span class='glyphicon glyphicon-remove-sign' style='color:red'></span>";
                }

                var tahun = $("#tahun").val();
                var idsekolah = $("#idsekolah").val();

                console.log("cek idsek=" + idsekolah);

                var tglDok, akun;
                var thn, mm, dd;
                if (index == 1) {
                    tglDok = "";
                } else {
                    var tanggalDok = aData['tglDok'];
                    thn = tanggalDok.substr(0, 4);
                    mm = tanggalDok.substr(5, 2);
                    dd = tanggalDok.substr(8, 2);
                    tglDok = dd + "/" + mm + "/" + thn;
                }

                var editspjbukti = "<a href='" + getbasepath() + "/bkubos/editbkuspjbukti/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>";
                var editspjbukticetak = "<a href='" + getbasepath() + "/bkubos/editbkuspjbukti/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/bkubos/json/prosescetakbku/" + "?idbku=" + aData['idBku'] + "&idsekolah=" + idsekolah + "' class='icon-print' ></a>";
                var nilaim = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                var nilaik = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");
                var nilais = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");
                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + nilaim + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + nilaik + "' readOnly='true'/>";
                var saldo = "<input type='text' name='saldo" + index + "' id='saldo" + index + "'  class='inputmoney'  value='" + nilais + "' readOnly='true'/>";
                var idtrans = "<input type='hidden' id='kodetransaksi" + index + "' value='" + aData['idTransaksi'] + "' />";
                var uraian = "<textarea id='uraianbukti" + index + "'readonly style='border:none;margin:0;width:180px;'>" + aData['uraianBukti'] + "</textarea>";
                var kegiatan = "<textarea id='kegiatan" + index + "'readonly style='border:none;margin:0;width:180px;'>" + aData['ketKegiatan'] + "</textarea>";
                var editjo = "<a href='" + getbasepath() + "/bkubos/editbkujo/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubos/hapusbkujo/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editrt = "<a href='" + getbasepath() + "/bkubos/editbkurt/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubos/hapusbkurt/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editjg = "<a href='" + getbasepath() + "/bkubos/editbkujg/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubos/hapusbkujg/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editsetor = "<a href='" + getbasepath() + "/bkubos/editbkusetor/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubos/hapusbkusetor/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editspj = "<a href='" + getbasepath() + "/bkubos/editbkuspj/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubos/hapusbkuspj/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editpajak = "<a href='" + getbasepath() + "/bkubos/editbkupajak/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubos/hapusbkupajak/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                var editc12 = "<a href='" + getbasepath() + "/bkubos/editbkuc12/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/bkubos/hapusbkuc12/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";
                // arsip bku - hanya bisa lihat transaksi
                var arsipjo = "<a href='" + getbasepath() + "/bkubos/arsipbkujo/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsiprt = "<a href='" + getbasepath() + "/bkubos/arsipbkurt/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsipjg = "<a href='" + getbasepath() + "/bkubos/arsipbkujg/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsippajak = "<a href='" + getbasepath() + "/bkubos/arsipbkupajak/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsippajakcetak = "<a href='" + getbasepath() + "/bkubos/arsipbkupajak/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a> - <a href='" + getbasepath() + "/bkubos/json/prosescetakbpn/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "&idsekolah=" + idsekolah + "&kodetrx=" + aData['kodeTransaksi'] + "' class='icon-print' ></a>";
                var arsipspj = "<a href='" + getbasepath() + "/bkubos/arsipbkuspj/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsipst = "<a href='" + getbasepath() + "/bkubos/arsipbkust/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsipstcetak = "<a href='" + getbasepath() + "/bkubos/arsipbkust/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a> - <a href='" + getbasepath() + "/bkubos/json/prosescetakbku/" + "?idbku=" + aData['idBku'] + "&idsekolah=" + idsekolah + "' class='icon-print' ></a>";
                var arsiprtcetak = "<a href='" + getbasepath() + "/bkubos/arsipbkurt/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a> - <a href='" + getbasepath() + "/bkubos/json/prosescetakbku/" + "?idbku=" + aData['idBku'] + "&idsekolah=" + idsekolah + "' class='icon-print' ></a>";
                var arsipc12 = "<a href='" + getbasepath() + "/bkubos/arsipbkuc12/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var arsipspjcetak = "<a href='" + getbasepath() + "/bkubos/arsipbkuspj/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a> - <a href='" + getbasepath() + "/bkubos/json/prosescetakbku/" + "?idbku=" + aData['idBku'] + "&idsekolah=" + idsekolah + "' class='icon-print' ></a>";
                var arsipjgcetak = "<a href='" + getbasepath() + "/bkubos/arsipbkujg/" + "?idbku=" + aData['idBku'] + "&tahun=" + tahun + "' class='icon-file-text' ></a> - <a href='" + getbasepath() + "/bkubos/json/prosescetakbku/" + "?idbku=" + aData['idBku'] + "&idsekolah=" + idsekolah + "' class='icon-print' ></a>";
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
                } else if (aData['kodeTutup'] == 2 || aData['kodeKoreksi'] != 0) { // jika sudah tutup buku triwulan atau jika koreksi
                    if (kodetrans == "JJ") {
                        if (aData['statusBank'] == '1') {
                            editvalid = arsipspjcetak;
                        } else {
                            editvalid = arsipspj;
                        }
                    } else if (kodetrans == "JO") {
                        editvalid = arsipjo;
                    } else if (kodetrans == "RT") {
                        editvalid = arsiprtcetak;
                    } else if (kodetrans == "ST") {
                        editvalid = arsipstcetak;
                    } else if (kodetrans == "JG") {
                        editvalid = arsipjgcetak;
                    } else if (kodetrans.substr(0, 1) == "P") {
                        editvalid = arsippajak;
                    } else if (kodetrans.substr(0, 1) == "C") {
                        editvalid = arsipc12;
                    }

                } else if (kodetrans == "JJ") {
                    if (aData['tglPosting'] == "" || aData['tglPosting'] == null) { //  jika belum ditransfer
                        if (aData['banyakPajak'] != 0) {
                            editvalid = editspjbukti;
                        } else if (aData['bkuStatus'] < 3) { // saat input awal permohonan
                            editvalid = editspj;

                        } /*else if (aData['bkuStatus'] == 3) { // setelah pengajuan ke sudin
                         editvalid = editspjsusulan;

                         } */ else {
                            editvalid = editspjbukti; // jika sudah diajukan ke PA tapi belum diajukan ke sudin
                        }

                    } else { // jika sudah ditransfer
                        if (aData['statusBank'] == '1') {
                            editvalid = editspjbukticetak;
                        } else {
                            editvalid = editspjbukti;
                        }
                    }

                } else if (kodetrans == "JO") {
                    editvalid = editjo;
                } else if (kodetrans == "ST") {
                    if (aData['bkuStatus'] == 3) { // jika sudah approve oleh PA, khusus PG (karena PG saat input status = 1, harus diapprove dl)
                        if (aData['statusBank'] == '1') {
                            editvalid = arsipstcetak;
                        } else {
                            editvalid = arsipst;
                        }
                    } else {
                        editvalid = editsetor;
                    }

                } else if (kodetrans == "JG") {
                    if (parseInt(aData['nilaiMasuk']) > 0) { // JIKA PENERIMAAN
                        if (parseInt(aData['kodePajak']) >= 1) { // JIKA SUDAH DIBUAT JG PG NYA; (PN saat input status = 3, tidak diapprove dl)
                            editvalid = arsipjg;
                        } else {
                            editvalid = editjg;
                        }

                    } else { // JIKA PENGELUARAN
                        if (aData['bkuStatus'] == 3) { // jika sudah approve oleh PA, khusus PG (karena PG saat input status = 1, harus diapprove dl)
                            if (aData['statusBank'] == '1') {
                                editvalid = arsipjgcetak;
                            } else {
                                editvalid = arsipjg;
                            }
                        } else {
                            editvalid = editjg;
                        }
                    }

                    /*
                     if (kodetrxid[aData['idBku']] == "JG") { // JIKA EXISTS DI TM SETOR; KHUSUS PN (PN saat input status = 3, tidak diapprove dl)
                     editvalid = arsipjg;
                     } else if (aData['bkuStatus'] == 3) { // jika sudah approve oleh PA, khusus PG (karena PG saat input status = 1, harus diapprove dl)
                     editvalid = arsipjg;
                     } else {
                     editvalid = editjg;
                     }*/

                } else if (kodetrans == "RT") {
                    if (parseInt(aData['nilaiMasuk']) > 0) { // JIKA PENERIMAAN
                        if (parseInt(aData['kodePajak']) >= 1) { // JIKA SUDAH DIBUAT SETORAN RT
                            editvalid = arsiprt;
                        } else {
                            editvalid = editrt;
                        }

                    } else { // JIKA PENGELUARAN
                        if (aData['bkuStatus'] == 3) { // jika sudah approve oleh PA, khusus PG (karena PG saat input status = 1, harus diapprove dl)
                            if (aData['statusBank'] == '1') {
                                editvalid = arsiprtcetak;
                            } else {
                                editvalid = arsiprt;
                            }
                        } else {
                            editvalid = editrt;
                        }
                    }

                } else if (kodetrans.substr(0, 1) == "P") {
                    var kodepj = aData['kodeBkuPajak'];
                    if (kodepj == "0") { // JIKA PAJAK DIINPUT SEBELUM TRANSFER
                        if (aData['tglPosting'] == "null" || aData['tglPosting'] == null || aData['tglPosting'] == "") { // jika belum TF masih bisa diedit
                            editvalid = editpajak;
                        } else {
                            editvalid = arsippajak;
                        }

                    } else {// JIKA PAJAK DIINPUT SETELAH TRANSFER
                        if (parseInt(aData['nilaiMasuk']) > 0) { // JIKA PENERIMAAN
                            if (parseInt(aData['kodePajak']) >= 1) { // JIKA SUDAH DIBUAT PG NYA; (PN saat input status = 3, tidak diapprove dl)
                                editvalid = arsippajak;
                            } else {
                                editvalid = editpajak;
                            }
                        } else { // JIKA PENGELUARAN
                            if (aData['bkuStatus'] == 3) { // jika sudah approve oleh PA, khusus PG (karena PG saat input status = 1, harus diapprove dl)
                                if (aData['statusBpn'] == '1') {
                                    editvalid = arsippajakcetak;
                                } else {
                                    editvalid = arsippajak;
                                }
                            } else {
                                editvalid = editpajak;
                            }
                        }

                    }

                } else if (kodetrans.substr(0, 1) == "C") {
                    editvalid = editc12;
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
    } else
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
    $.getJSON(getbasepath() + "/bkubos/json/getTotal", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan, kodetotal: kodetotal},
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
    $.getJSON(getbasepath() + "/bkubos/json/getKodeSetor", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan},
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
    $.getJSON(getbasepath() + "/bkubos/json/getMaxTriwulan", {tahun: tahun, idsekolah: idsekolah},
    function(result) {

        $('#triwulan').val(result);
        getTotal();
        getKodeSetor();
    });
}

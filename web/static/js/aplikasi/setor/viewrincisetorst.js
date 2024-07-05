$(document).ready(function() {
    //setPanelAwal();
    //setCurrentDate();
    getNilaiSetorST();
    //getNilaiSaldoKasBelumSetor();
    setJenisTriwulan();
    setJenisTransaksi();
    setNilaiSetor();
    gridRinciReal();
});

//var nilaisaldokas = 0;
var nilaist = 0;

function tutuppopup(){
    parent.$.fancybox.close();
}

function setNilaiSetor() {
    var nilaisetor = accounting.unformat($('#nilaisetor').val(), ",");
    //console.log("nilai setor === "+nilaisetor);
    $('#nilaisetor').val(nilaisetor);
}

function setJenisTransaksi() {
    var text_ctrx;
    if ($('#kodetransaksi').val() == "ST" && $('#kodeSumbdana').val() == "1") {
        text_ctrx = "SETORAN SISA BELANJA BOS";
    } else if ($('#kodetransaksi').val() == "ST" && $('#kodeSumbdana').val() != "1") {
        text_ctrx = "SETORAN SISA BELANJA BOP";
    } else {
        text_ctrx = "JASA GIRO";
    }
    $('#textkodetransaksi').val(text_ctrx);
}

function setJenisTriwulan() {
    var text_kd3wulan = $('#kodetriwulan').val() == "1" ? "TRIWULAN 1" : $('#kodetriwulan').val() == "2" ? "TRIWULAN 2" :
            $('#kodetriwulan').val() == "3" ? "TRIWULAN 3" : $('#kodetriwulan').val() == "4" ? "TRIWULAN 4" : "";
    $('#textkodetriwulan').val(text_kd3wulan);
}

function getNilaiSetorST() { 
    if ($('#idsetor').val() != 0 && $('#idsetor').val() != null && $('#idsetor').val() != "") {
        var idsetor = $('#idsetor').val();
        $.getJSON(getbasepath() + "/setor/json/getnilaist/", {
            idsekolah: $("#idsekolah").val(),
            tahunsetor: $("#tahunsetor").val(),
            idsetor: idsetor,
            kodetriwulan: $("#kodetriwulan").val()
        },
        function(result) {
            nilaist = result.sisaKas;
            $('#nilaikas').val(accounting.formatNumber(result.totalMohon, 2, '.', ","));
            $('#nilaitotalreal').val(accounting.formatNumber(result.totalRealisasi, 2, '.', ","));
            $('#nilaisetorsebelum').val(accounting.formatNumber(result.nilaiSetorSebelum, 2, '.', ","));
            $('#nilaisisa').val(accounting.formatNumber(result.sisaKas, 2, '.', ","));

        });
    }
}
/*
 function setPanelAwal() {
 document.getElementById("nosetorpane").style.display = "none";
 document.getElementById("tglsetorpane").style.display = "none";
 document.getElementById("nilaisetorpane").style.display = "none";
 document.getElementById("bkupilihbutton").style.display = "none";
 document.getElementById("uraianpane").style.display = "none";
 }
 
 function setCurrentDate() {
 var dd = new Date();
 var m = dd.getMonth() + 1;
 var y = dd.getFullYear();
 var d = dd.getUTCDate();
 
 if (d < 10) {
 d = '0' + d;
 }
 if (m < 10) {
 m = '0' + m;
 }
 
 var tanggal = d + "/" + m + "/" + y;
 $('#tglsetor').val(tanggal);
 // console.log(" tglPosting == " + tanggal);
 
 }
 
 function setPanelTambah(ctrx) {
 //get Nomor Setor
 if ($('#nosetor').val() == '' || $('#nosetor').val() == null) {
 getNomorSetor();
 }
 
 //set panel berdasarkan pilihan jenis transaksi
 if (ctrx == "ST") {
 document.getElementById("nosetorpane").style.display = "block";
 document.getElementById("tglsetorpane").style.display = "block";
 document.getElementById("nilaisetorpane").style.display = "block";
 document.getElementById("bkupilihbutton").style.display = "none";
 document.getElementById("uraianpane").style.display = "block";
 $("#nilaisetor").attr('readonly', false);
 $('#idbas').val(12780);
 getNilaiSetorST();
 } else if (ctrx == "JG") {
 document.getElementById("nosetorpane").style.display = "block";
 document.getElementById("tglsetorpane").style.display = "block";
 document.getElementById("nilaisetorpane").style.display = "block";
 document.getElementById("bkupilihbutton").style.display = "block";
 document.getElementById("uraianpane").style.display = "block";
 $("#nilaisetor").attr('readonly', 'readonly');
 $('#nilaisetor').val("");
 $('#idbas').val(12781);
 } else {
 setPanelAwal();
 $('#nilaisetor').val("");
 $('#idbas').val("");
 }
 }
 
 function getNomorSetor() {
 $.getJSON(getbasepath() + "/setor/json/getnosetor", {idsekolah: $("#idsekolah").val(), tahunsetor: $("#tahunsetor").val()},
 function(result) {
 $('#nosetor').val(result);
 });
 }
 */
/*
function getNilaiSaldoKasBelumSetor() { //ambil nilai saldo kas selain id tersebut
    if ($('#idsetor').val() != 0 && $('#idsetor').val() != null && $('#idsetor').val() != "") {
        var idsetor = $('#idsetor').val();
        $.getJSON(getbasepath() + "/setor/json/getnilaist/", {idsekolah: $("#idsekolah").val(), tahunsetor: $("#tahunsetor").val(), idsetor: idsetor, kodeSumbdana: $("#kodeSumbdana").val()},
        function(result) {
            //$('#nilaisetor').val(result);
            nilaisaldokas = result;
        });
    }
}
*/

/*
 function getNilaiSetorJG() {
 $.getJSON(getbasepath() + "/setor/json/getnilaijg/", {idsekolah: $("#idsekolah").val(), tahunsetor: $("#tahunsetor").val(), nobkureff: $("#nobkureff").val()},
 function(result) {
 $('#nilaisetor').val(result);
 });
 }
 */
/*
function cekNilaiST() {
    /* sebelumnya
    if ($('#kodetransaksi').val() == "ST") {
        if ($('#nilaisetor').val() > nilaisaldokas) {
            bootbox.alert("Nilai Setoran tidak boleh melebihi saldo kas sebesar " + nilaisaldokas + ".", function(result) {
                $('#nilaisetor').val(nilaisaldokas);
            });
        }
    }
    -/
    if ($('#nilaisetor').val() > nilaist) {
            bootbox.alert("Nilai Setoran tidak boleh melebihi sisa kas sebesar " + nilaist + ".", function(result) {
                $('#nilaisetor').val(accounting.formatNumber(nilaist, 2, '.', ","));
            });
        }
}
*/
function gridRinciReal() {
    document.getElementById("panelrincireal").style.display = "block";
    if (typeof myTable == 'undefined') {
        myTable = $('#rincirealtabel').dataTable({"bPaginate": true,
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
            "sAjaxSource": getbasepath() + "/setor/json/listrincireal",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahunsetor", value: $("#tahunsetor").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "kodetriwulan", value: $("#kodetriwulan").val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                var totalmohon = 0;
                var totalreal = 0;
                var totalselisih = 0;
                for (var i = iStart; i < iEnd; i++) {
                    totalmohon += parseFloat(aaData[i]['totalMohon']);
                    totalreal += parseFloat(aaData[i]['totalRealisasi']);
                    totalselisih += parseFloat(aaData[i]['sisaKas']);
                }
                $('#totmohon').val(accounting.formatNumber(totalmohon, 2, '.', ","));
                $('#totreal').val(accounting.formatNumber(totalreal, 2, '.', ","));
                $('#totselisih').val(accounting.formatNumber(totalselisih, 2, '.', ","));
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

                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(accounting.formatNumber(aData['totalMohon'], 2, '.', ","));
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['totalRealisasi'], 2, '.', ","));
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['sisaKas'], 2, '.', ","));

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "kodeAkun", "bSortable": false, sClass: "center", "sWidth": "17%"},
                {"mDataProp": "namaAkun", "bSortable": false, sClass: "left", "sWidth": "18%"},
                {"mDataProp": "totalMohon", "bSortable": false, sClass: "right", "sWidth": "11%"},
                {"mDataProp": "totalRealisasi", "bSortable": false, sClass: "right", "sWidth": "11%"},
                {"mDataProp": "sisaKas", "bSortable": false, sClass: "right", "sWidth": "11%"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}
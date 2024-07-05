$(document).ready(function() {
    //setPanelAwal();
    //setCurrentDate();
    //getNilaiSaldoKasBelumSetor();
    setJenisTransaksi();
    setJenisTriwulan();
    setNilaiSetor();
});

var nilaisaldokas = 0;

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

/*
 function getNilaiSetorJG() {
 $.getJSON(getbasepath() + "/setor/json/getnilaijg/", {idsekolah: $("#idsekolah").val(), tahunsetor: $("#tahunsetor").val(), nobkureff: $("#nobkureff").val()},
 function(result) {
 $('#nilaisetor').val(result);
 });
 }
 */

function cekNilaiST() {
    if ($('#kodetransaksi').val() == "ST") {
        if ($('#nilaisetor').val() > nilaisaldokas) {
            bootbox.alert("Nilai Setoran tidak boleh melebihi saldo kas sebesar " + nilaisaldokas + ".", function(result) {
                $('#nilaisetor').val(nilaisaldokas);
            });
        }
    }
}

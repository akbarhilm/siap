$(document).ready(function() {
depag();

temppa = $("#nrkKepsek").val()
temppk=$("#nrkBendahara").val();
getDataNPWP();
});
function tampil(){
    var a=$("#idSekolah").val()
        $.getJSON(getbasepath() + "/sekolah/json/getSekolah", {id: a},
    function(result) {
       
       $("#idSekolah").val(result.aData['idSekolah']);
   $("#npsn").val(result.aData['npsn']);
   $("#idSkpd").val(result.aData['idSkpd']);
   $("#namaSekolah").val(result.aData['namaSekolah']);
   $("#namaSekolahPendek").val(result.aData['namaSekolahPendek']);
   $("#alamatSekolah").val(result.aData['alamatSekolah']);
   $("#nrkKepsek").val(result.aData['nrkKepsek']);
   $("#nipKepsek").val(result.aData['nipKepsek']);
   $("#namaKepsek").val(result.aData['namaKepsek']);
   $("#pangkatKepsek").val(result.aData['pangkatKepsek']);
   $("#nrkBendahara").val(result.aData['nrkBendahara']);
   $("#nipBendahara").val(result.aData['nipBendahara']);
   $("#namaBendahara").val(result.aData['namaBendahara']);
   $("#noTelpon").val(result.aData['noTelpon']);
   $("#noFax").val(result.aData['noFax']);
   $("#website").val(result.aData['website']);
   $("#email").val(result.aData['email']);
   $("#namaLogo").val(result.aData['namaLogo']);
   $("#kodeWilayah").val(result.aData['kodeWilayah']);
   $("#kodeJenjang").val(result.aData['kodeJenjang']);
   $("#kodeNegeri").val(result.aData['kodeNegeri']);
   $("#kodeLokasi").val(result.aData['kodeLokasi']);
   $("#nss").val(result.aData['nss']);
   $("#jumlahSiswa").val(result.aData['jumlahSiswa']);
   $("#jumlahRomBel").val(result.aData['jumlahRomBel']);
   $("#idLokasi").val(result.aData['idLokasi']);
   $("#aktif").val(result.aData['aktif']);
   $("#sekolah").val(result.aData['sekolahGabung']);
   //$("#sekolahGabung").val(result.aData['sekolahGabung']);
   $("#npwp").val(result.aData['noNPWP']);
   $("#namanpwp").val(result.aData['namaNPWP']);
   $("#alamatnpwp").val(result.aData['alamatNPWP']);
   $("#kotanpwp").val(result.aData['kotaNPWP']);
   $("#noBOP").val(result.aData['noBOP']);
   $("#namaBOP").val(result.aData['namaBOP']);
   $("#noBOS").val(result.aData['noBOS']);
   $("#namaBOS").val(result.aData['namaBOS']);
   $("#kodeSkpd").val(result.aData['kodeSkpd']);
   $("#namaSkpd").val(result.aData['namaSkpd']);
   $("#skpd").val(result.aData['skpd']);
   
   

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
        success: function (result) {
            if (result['error'] == null) {
                $("#namanpwp").val(result['NAMA']);
                $("#alamatnpwp").val(result['ALAMAT']);
                $("#kotanpwp").val(result['KABKOT']);
//                if (result['STATUS_PKP'] == 'NON PKP') {
//                    document.getElementById("cmbpkp").checked = false;
//                } else {
//                    document.getElementById("cmbpkp").checked = true;
//                }
            } else {
                bootbox.alert(result['error']);
            }
        },
        error: function () {
            bootbox.alert("Sambungan DJP Terputus");
        }
    });
}
//function setBulan() {
//    var currentbulan;
//
//    $("#tglHide").datepicker("setDate", new Date());
//    currentbulan = $("#tglHide").val();
//    var mm = currentbulan.substr(3, 2);
//    $('#bulan').val(mm);
//
//    gridbku();
//    getTotal();
//}

//function gridbku() {
//
//    if (typeof myTable == 'undefined') {
//        myTable = $('#jourtable').dataTable({
//            "bPaginate": true,
//            "sPaginationType": "bootstrap",
//            "bJQueryUI": false,
//            "bProcessing": true,
//            "bServerSide": true,
//            "bInfo": true,
//            "bScrollCollapse": true,
//            //"sScrollY": ($(window).height() * 108 / 100),
//            "bFilter": false,
//            "aLengthMenu": [[25, 50, 100, 200, 5000], [25, 50, 100, 200, "All"]],
//            "iDisplayLength": 50,
//            "sAjaxSource": getbasepath() + "/bku/json/listindex",
//            "aaSorting": [[1, "asc"]],
//            "fnDrawCallback": function() {
//                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
//
//            },
//            "fnServerParams": function(aoData) {
//                aoData.push(
//                        {name: "tahun", value: $("#tahun").val()},
//                {name: "idsekolah", value: $("#idsekolah").val()},
//                {name: "bulan", value: $("#bulan").val()}
//                );
//            },
//            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
//
//            },
//            "fnServerData": function(sSource, aoData, fnCallback) {
//                $.ajax({
//                    "dataType": 'json',
//                    "type": "GET",
//                    "url": sSource,
//                    "data": aoData,
//                    "success": fnCallback
//                });
//            },
//            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
//                var numStart = this.fnPagingInfo().iStart;
//                var index = numStart + iDisplayIndexFull + 1;
//                var dpost = aData['tglPosting'];
//                var tglPost = "";
//                var tahun, mm, dd;
//                //if (index > 1) {
//                    tahun = dpost.substr(0, 4);
//                    mm = dpost.substr(4, 2);
//                    dd = dpost.substr(6, 2);
//                    tglPost = dd + "/" + mm + "/" + tahun;
//                //}
//                var nobukti = aData['noBukti'];
//                var nobku = aData['noBKU'];
//                var idsekolah = $("#idsekolah").val();
//
//                var nilaim = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
//                var nilaik = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");
//                var nilais = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");
//
//                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + nilaim + "' readOnly='true' />";
//                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + nilaik + "' readOnly='true'/>";
//                var saldo = "<input type='text' name='saldo" + index + "' id='saldo" + index + "'  class='inputmoney'  value='" + nilais + "' readOnly='true'/>";
//                var idtrans = "<input type='hidden' id='kodetransaksi" + index + "' value='" + aData['idTransaksi'] + "' />";
//                var uraian = "<textarea id='uraianbukti" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['uraianBukti'] + "</textarea>";
//                var kegiatan = "<textarea id='kegiatan" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['ketKegiatan'] + "</textarea>";
//                var edit = "<a href='" + getbasepath() + "/bku/editbkuspj/" +"?tahun=" + tahun + "&nobku=" + nobku + "&idsekolah=" + idsekolah +"' class='icon-edit' ></a>";
//                var editpajak = "<a href='" + getbasepath() + "/bku/editbkupajak/" + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-";
//                var editspj = "<a href='" + getbasepath() + "/bku/editbkuspj/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-edit' ></a>";
//
//                var tanggalDok;
//                var editvalid;
//
//
//                tanggalDok = tglPost;
//
//                if (aData['ketKegiatan'] == null) {
//                    kegiatan = "";
//                }
//
//                if (index == 1 || aData['bkuStatus'] == 1) {
//                    editvalid = "";
//
//                } else {
//
//                    editvalid = edit;
//
//                }
//
//                $('td:eq(0)', nRow).html(index);
//                $('td:eq(1)', nRow).html(tanggalDok);
//                $('td:eq(5)', nRow).html(uraian);
//                $('td:eq(6)', nRow).html(kegiatan);
//                $('td:eq(7)', nRow).html(nilaimasuk + idtrans);
//                $('td:eq(8)', nRow).html(nilaikeluar);
//                $('td:eq(9)', nRow).html(saldo);
//                $('td:eq(10)', nRow).html(editvalid);
//
//                return nRow;
//            },
//            "aoColumns": [
//                {"mDataProp": "idBas", "bSortable": false, "sWidth": "5%", sClass: "center"},
//                {"mDataProp": "tglDok", "bSortable": true, sClass: "center"},
//                {"mDataProp": "noBKU", "bSortable": true, sClass: "center"},
//                {"mDataProp": "noBukti", "bSortable": false, sClass: "left"},
//                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
//                {"mDataProp": "uraianBukti", "bSortable": false, sClass: "left"},
//                {"mDataProp": "ketKegiatan", "bSortable": false, sClass: "left"},
//                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "right"},
//                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "right"},
//                {"mDataProp": "saldoKas", "bSortable": false, sClass: "right"},
//                {"mDataProp": "idBas", "bSortable": false, "sWidth": "5%", sClass: "center"}
//            ]
//        });
//    }
//    else
//    {
//        myTable.fnClearTable(0);
//        myTable.fnDraw();
//    }
//}

//function getTotal() {
//    var tahun = $('#tahun').val();
//    var idsekolah = $('#idsekolah').val();
//    var bulan = $('#bulan').val();
//
//    $.getJSON(getbasepath() + "/bku/json/getTotal", {tahun: tahun, idsekolah: idsekolah, bulan: bulan},
//    function(result) {
//
//        var tm = result.aData['nilaiMasuk'];
//        var tk = result.aData['nilaiKeluar'];
//        var ts = result.aData['nilaiSisa'];
//
//        $("#totmasuk").val(accounting.formatNumber(tm, 2, '.', ","));
//        $("#totkeluar").val(accounting.formatNumber(tk, 2, '.', ","));
//        $("#totsaldokas").val(accounting.formatNumber(ts, 2, '.', ","));
//        $("#saldokas").val(accounting.formatNumber(ts, 2, '.', ","));
//
//    });
//}

function cek() {
    var cek1 = '0';
    var cek2 = '0';
    var filel = file.files.length;
    
    if(filel>0){
        $("#namaLogo").val(file.files[0]['name']);
    }
    console.log($("#nrkKepsek").val() + " TEST 1");
    if ($("#nrkKepsek").val() != '' && ($("#namaKepsek").val() == '' || $("#nipKepsek").val() == '' || $("#pangkatKepsek").val() == '')) {
        cek1 = '1';
        console.log("MASUK A1");
    } else {
        cek1 = '2';
        console.log("MASUK A2");
    }
    console.log($("#nrkBendahara").val() + " TEST 2");
    console.log("var " + cek1 + " " + cek2);
    if ($("#nrkBendahara").val() != '' && ($("#namaBendahara").val() == '' || $("#nipBendahara").val() == '')) {
        cek1 = '1';
        console.log("MASUK B1");
    } else {
        cek1 = '2';
        console.log("MASUK B2");
    }

    if (cek1 == '1' || cek2 == '1' || $("#npwp").val() == ''
            || $("#namanpwp").val() == '' || $("#kotanpwp").val() == ''
            || $("#noBOP").val() == '' || $("#namaBOP").val() == ''
            || $("#noBOS").val() == '' || $("#namaBOS").val() == '') {
        bootbox.alert("Semua data harus diisi");
        return false;
    } else {
        return true;
    }
}

function clear() {
//    if ($("#nrkKepsek").val() != '') {
//        $("#namaKepsek").val('');
//        $("#nipKepsek").val('');
//        $("#pangkatKepsek").val('');
//    }
//    if ($("#nrkBendahara").val() != '') {
//        $("#namaBendahara").val('');
//        $("#nipBendahara").val('');
//    }
$("input").val("");
}

function getData(jenis) {
    if (jenis == "1") {
        var nrk = $("#nrkKepsek").val();
    } else {
        var nrk = $("#nrkBendahara").val();
    }
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

        var varurl = getbasepath() + "/sekolah/json/getpegawai";
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
                if (jenis == "1") {
                    $("#namaKepsek").val(result['NAMA']);
                    $("#nipKepsek").val(result['NIP18']);
                    $("#pangkatKepsek").val(result['NAPANG'] + ", " + result['GOL']);
                } else {
                    $("#namaBendahara").val(result['NAMA']);
                    $("#nipBendahara").val(result['NIP18']);
                }
            },
            error: function() {
                bootbox.alert('boo!');
            },
        });
    }
}

function getData2(jenis) {
    if (jenis == "1") {
        var nrk = $("#nrkKepsek").val();
    } else {
        var nrk = $("#nrkBendahara").val();
    }
    if ((nrk != "")) {
        $.ajax({
            type: 'POST',
            url: "http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD",
            data: {nrk: nrk},
            crossDomain: true,
            beforeSend: function(xhr) {
                xhr.setRequestHeader('Authorization', 'Basic ' + window.btoa(unescape(encodeURIComponent())))
            },
            success: function(data) {
                if (data.results[0] != null) {
                    if (jenis == "1") {
                        $("#namaKepsek").val(data.results[0]['NAMA']);
                        $("#nipKepsek").val(data.results[0]['NIP18']);
                        $("#pangkatKepsek").val(data.results[0]['NAPANG'] + ", " + data.results[0]['GOL']);
                    } else {
                        $("#namaBendahara").val(data.results[0]['NAMA']);
                        $("#nipBendahara").val(data.results[0]['NIP18']);
                    }
                } else {
                    bootbox.alert("Data tidak ada");
                }
            }
        });

    }
}

function addjpg() {
    
    if($("#namaLogo").val()==""){
        $("#namaLogo").val("jaya_raya.jpg");
    }
    
    if ($("#namaLogo").val().substr(-4) != ".jpg") {
        $("#namaLogo").val($("#namaLogo").val() + ".jpg");
    }

}
function simpanup() {

    
    var varurl = getbasepath() + "/sekolah/json/prosesupdate";
    var dataac = [];

    var datajour = {
        idsekolah: $("#idsekolah").val(),
        namakepsek: $("#namaKepsek").val(),
        nipkepsek: $("#nipKepsek").val(),
        namabend: $("#namaBendahara").val(),
        nipbend: $("#nipBendahara").val(),
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

        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");

    });

}
//depag
function depagpa(){
        $('#depag1').change(function(){
            
    $("#nrkKepsek").val('')
     if(this.checked){
         
        $("#btnCek1").hide();
        $("#namaKepsek").attr('readonly',false);
        $("#nipKepsek").attr('readonly',false);
        $("#pangkatKepsek").attr('readonly',false);
       
        $("#nrkKepsek").val(nrkbaru);
        if($("#depag2").is(":checked")==true){
            
            if(a.toString().length==1){
                a = "NRK00"+a;
            } if(a.toString().length==2){
                a = "NRK0"+a;
            }
            $("#nrkKepsek").val(a);
        }
    }else{
       if($("#depag2").is(":checked")==true){
           $("#nrkBendahara").val(nrkbaru);
       }
    
    $("#btnCek1").show();
    $("#nrkKepsek").val(temppa);
       $("#namaKepsek").attr('readonly',true);
        $("#nipKepsek").attr('readonly',true);
        $("#pangkatKepsek").attr('readonly',true);
    }   
    });
}
        
function depag(){
    $.getJSON(getbasepath()+"/useradm/json/maxdepag",
                function(Data) {
                  
                        
                  
                      nrkbaru=Data['aaData']['NRKBARU'];
                    a = parseInt(nrkbaru.substr(3))+1;
//                  if($("#depag1").is(":checked")==true){
//                 $("#nrkKepsek").val(nrkbaru);
//                   
//             }
         }
         );
}
var nrkbaru="";
var temppa = "";
var temppk = "";
var count = 0;
var a = 0;

function depagpk(){
        $('#depag2').change(function(){
    $("#nrkBendahara").val('')
     if(this.checked){
         count= count+1;
      console.log(count);
        $("#btnCek2").hide();
        $("#namaBendahara").attr('readonly',false);
        $("#nipBendahara").attr('readonly',false);
        $("#pangkatBendahara").attr('readonly',false);
    
        $("#nrkBendahara").val(nrkbaru);
        if($("#depag1").is(":checked")==true){
            if(a.toString().length==1){
                a = "NRK00"+a;
            } if(a.toString().length==2){
                a = "NRK0"+a;
            }
            $("#nrkBendahara").val(a);
        }
       
    }else{
        
         if($("#depag1").is(":checked")==true){
             $("#nrkKepsek").val(nrkbaru);
         }
    $("#btnCek2").show();
    $("#nrkBendahara").val(temppk);
       $("#namaBendahara").attr('readonly',true);
        $("#nipBendahara").attr('readonly',true);
        $("#pangkatBendahara").attr('readonly',true);
    }   
    });
}
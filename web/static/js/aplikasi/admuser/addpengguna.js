$(document).ready(function() {
    /* $.validator.addMethod('IP4Checker', function (value) {
     var ip = "^(?:(?:25[0-5]2[0-4][0-9][01]?[0-9][0-9]?)\.){3}" +
     "(?:25[0-5]2[0-4][0-9][01]?[0-9][0-9]?)$";
     return value.match(ip);
     }, 'Invalid IP address');*/
    // setPanel();
    cek();
    $("#progcmd").valid();
    $("#spanerror").html("");
});
function cek() {
    if ($("#kodeAktif").val() == "1") {
        $("#aktif").val("Aktif");
    } else {
        $("#aktif").val("Tidak Aktif");
    }
    if ($("#kodeGroup").val() == "1") {
        $("#grup").val("BOS");
    }
    if ($("#kodeGroup").val() == "2") {
        $("#grup").val("BOP");
    }
    if ($("#kodeGroup").val() == "3") {
        $("#grup").val("BOS/BOP");
    }
    if ($("#kodeOtoritas").val() == "0") {
        $("#otor").val("SUDIN/DINAS");
    }
    if ($("#kodeOtoritas").val() == "1") {
        $("#otor").val("SEKOLAH (PA)");
    }
    if ($("#kodeOtoritas").val() == "2") {
        $("#otor").val("SEKOLAH (PK)");
    }
    if ($("#kodeOtoritas").val() == "8") {
        $("#otor").val("ADMIN");
    }
    if ($("#kodeOtoritas").val() == "9") {
        $("#otor").val("SUPER ADMIN");
    }
}

$(function() {
    $('#depag').change(function() {
        $("#idNrk").val('')
        if (this.checked) {

            $("#ws").hide();
            $("#namaPengguna").attr('readonly', false);
            $("#nipPengguna").attr('readonly', false);
            $("#jabatanPengguna").attr('readonly', false);

            depag();
        } else {
            $("#ws").show();
            $("#namaPengguna").attr('readonly', true);
            $("#nipPengguna").attr('readonly', true);
            $("#jabatanPengguna").attr('readonly', true);
        }
    })
});

function depag() {
    $.getJSON(getbasepath() + "/useradm/json/maxdepag",
            function(Data) {

                $("#idNrk").val(Data['aaData']['NRKBARU']);



            }
    );
}
function wspeg() {
//$.post("http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD",{nrk:$("#idNrk").val()},
//
//             function(data){
//                 if(data.results[0]!=null){
//                   $("#namaPengguna").val(data.results[0]['NAMA']);
//                   $("#nipPengguna").val(data.results[0]['NIP18'])
//                   $("#jabatanPengguna").val(data.results[0]['NAPANG']+", "+data.results[0]['GOL'])
//                   $("#spanerror").html("");
//                    }else{
//
//                     $("#spanerror").html("Data Tidak Ada");
//                 }
//             })
    var nrk = $("#idNrk").val().substr(0, 6);
    var varurl = getbasepath() + "/bkubop/json/getpegawai";
    var dataac = [];
    var datajour = {
        nrk: nrk
    };
    dataac = datajour;
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
                $("#namaPengguna").val(result['NAMA']);
                $("#nipPengguna").val(result['NIP18'])
                $("#jabatanPengguna").val(result['NAPANG'] + ", " + result['GOL'])
                $("#spanerror").html("");
            } else {
                $("#spanerror").html("Data Tidak Ada");
            }
        },
        error: function() {
            //bootbox.alert('boo!');
        }
    });
}
//function cek(){
//   /* if($("#kodeWilayahProses").val() == '8' && $("#idSkpd").val()== '761' && $("#kodeGrup").val()=='9'){
//        alert("Kode Wilayah SP2D Proses Harus di pilih")
//    }*/
//    var userPassword = $("#userPassword").length();
//    console.log("cek userPassword ="+userPassword);
//    if(userPassword < '5'  && userPassword > '16' ){ //userPassword
//        console.log("masuk ke if cek userPassword ="+userPassword);
//        alert("Panjang Karakter : 5-16 Karakter");
//    }
//}

//function setPanel(){
//    //20-02-2017 - anita
//    //fungsi untuk set panel jika c_pgun_group = 11 (LPJ BPKD)
//    var kodeGrup = $("#kodeGrup").val();
//    if (kodeGrup === '11') {
//        document.getElementById("sah").style.display = "block";
//        document.getElementById("batal").style.display = "block";
//        document.getElementById("koreksi").style.display = "block";
//    }
//    else {
//        document.getElementById("sah").style.display = "none";
//        document.getElementById("batal").style.display = "none";
//        document.getElementById("koreksi").style.display = "none";
//    }
//}
//
//function cekdatanull() {
//    datalengkap = true;
//var userName = $("#userName").val();
//var userPassword = $("#userPassword").val();
//var userNamaPeg = $("#userNamaPeg").val();
//    if (userName === "" || userPassword === "" || userNamaPeg==="")
//    {
//        console.log("Pengisian Data Harus Lengkap");
//        //bootbox.alert("Pengisian Data Harus Lengkap");
//    }
//    else {
//        simpan();
//
//        console.log("Data udah lengkap user,pswd,nama");
//    }
//}
//
//function simpan() {
//    var varurl = getbasepath() + "/useradm/json/prosessimpanuser";
//    var dataac = [];
//    //kodeGrup userName  userPassword idSkpd kodeWilayahProses ipAddress userEmail userNipPeg userNrkPeg userNamaPeg kodeAktif
//
//    var datajour = {
//        kodeGrup: $("#kodeGrup").val(),
//        userName: $("#userName").val(),
//        userPassword: $("#userPassword").val(),
//        idSkpd: $("#idSkpd").val(),
//        kodeWilayahProses: $("#kodeWilayahProses").val(),
//        ipAddress: $("#ipAddress").val(),
//        userEmail: $("#userEmail").val(),
//        userNipPeg: $("#userNipPeg").val(),
//        userNrkPeg: $("#userNrkPeg").val(),
//        userNamaPeg: $("#userNamaPeg").val(),
//        kodeAktif: $("#kodeAktif").val()
//    }
//
//    dataac = datajour;
//    return   $.ajax({
//        type: "POST",
//        url: varurl,
//        contentType: "text/plain; charset=utf-8",
//        headers: {
//            'Accept': 'application/json',
//            'Content-Type': 'application/json'
//        },
//        data: JSON.stringify(dataac)
//    }).always(function(data) {
//        bootbox.alert("Data User Berhasil Disimpan");
//        window.location.href = getbasepath() + "/useradm"; // ke halaman depan
//
//    });
//
//}
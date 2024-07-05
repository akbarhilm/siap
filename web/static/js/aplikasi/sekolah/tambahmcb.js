/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function simpan(){
    var idsekolah = $("#idSekolah").val();
    var nomcb = $("#noMcb").val();
    var namamcb = $("#namaMcb").val();
    
   var url = getbasepath()+"/sekolah/json/simpan"
        var dataac = [];
     
        var datajour = {
            idsekolah: idsekolah,
            nomcb:nomcb,
            namamcb:namamcb
        };
        dataac = datajour;
//        console.log("DATAAC: " + JSON.stringify(dataac));
//
        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(dataac),
            contentType: 'text/plain; charset=utf-8',
            headers: {
                'Accept': 'application/json, text/javascript',
                'Content-Type': 'application/json',
            }
//            always: function(){
//                window.location.replace(window.location.host+"/BKUS/sekolah/indexmcb");
//            }
           
        }).always(function() {
           cleardata();

       
                    bootbox.alert("Data Berhasil Disimpan");
                    
                
            });
    }
    
    function cleardata() {

    $('input').val("");

}

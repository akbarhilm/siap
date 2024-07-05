$(document).ready(function() {
    
   var idskpd = $('#idsekolah').val();
   //alert(idskpd);
   if (idskpd != '')
   {
        $("#btnSimpan").attr("disabled", false);
   }else
   {
        $("#btnSimpan").attr("disabled", true);
   }    
});


function zzz()
{
  var idskpd = $('#idskpd').val();
  var opt = [];
  var banyak = 10;
  //alert(idskpd);
  if (idskpd == '761')
   {
            opt[0] = '<option value="">=========================== :: Pilih Jenis Laporan :: ===================================</option>';
            opt[1] = '<option value="01">DPPA - SKPD Ringkasan</option>';
            opt[2] = '<option value="02">DPPA - SKPD 1 - Rincian Pendapatan</option>';
            opt[3] = '<option value="03">DPPA - SKPD 2 - Rekapitulasi Belanja</option>';
            opt[4] = '<option value="04">DPPA - SKPD 2.1 - Rincian Belanja Tidak Langsung</option>';
            opt[5] = '<option value="05">DPPA - SKPD 2.1.1 - Rincian Belanja Tidak Langsung Bantuan Keuangan (Per Koordinator)</option>';
           // opt[6] = '<option value="06">DPPA - SKPD 2.1.1A - Rincian Belanja Tidak Langsung Bantuan Keuangan (Per Rekening)</option>';
            opt[7] = '<option value="10">DPPA - SKPD 3.1 Penerimaan Pembiayaan</option>';
            opt[8] = '<option value="11">DPPA - SKPD 3.2 Pengeluaran Pembiayaan</option>';
           
      
        
        $("#kodeLap").html(opt);
        
   }else
   {
       opt[0] = '<option value="">=========================== :: Pilih Jenis Laporan :: ===================================</option>';
            opt[1] = '<option value="01">DPPA - SKPD Ringkasan</option>';
            opt[2] = '<option value="02">DPPA - SKPD 1 - Rincian Pendapatan</option>';
            opt[3] = '<option value="03">DPPA - SKPD 2 - Rekapitulasi Belanja</option>';
            opt[4] = '<option value="04">DPPA - SKPD 2.1 - Rincian Belanja Tidak Langsung</option>';
            opt[5] = '<option value="07">DPPA - SKPD 2.2 - Rekapitulasi Belanja Langsung</option>';
            opt[6] = '<option value="08">DPPA - SKPD 2.2.1 - Rincian Belanja Langsung</option>';
            
           
            
         $("#kodeLap").html(opt);   
   }
   
}

function xxx()
{
     zzz();  
     $("#tmblctk").attr("disabled", false);
}

function cetak() {
    var idsekolah = $('#idsekolah').val();
    var tahun = $("#tahun").val();
    var kodlap = $("#kodelap").val();
    var bulan = $("#bulan").val();
    var idkegiatan = $('#idKegpop').val();
    var nama = $("#sekolah").val();
   // alert("Par==> "+kodlap+" == "+tahun+" == "+idskpd);
   
    if (idsekolah == "") {
        bootbox.alert("Data Tidak Tersedia");
    } else {
        window.location.href = getbasepath() + "/monitoring/json/prosescetak?tahun=" + tahun + "&idsekolah=" + idsekolah + "&kodlap=" + kodlap + "&bulan="+bulan+"&idkeg="+idkegiatan+"&nama="+nama;
    }
}

$(document).ready(function() {

});

var ketbaris, ketidbas, nilaispj;
var rowpajak = 0;

function ceknilai(){
    var total = accounting.unformat($("#sumpajak").val(), ",");
    
    if (total <= nilaispj){
        ambilskpd();
    } else {
        bootbox.alert("Total Pajak Tidak Boleh Lebih Besar Dari Nilai SPJ");
    }
}

function grid() {
    rowpajak = 0
    $("#spjtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#spjtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkuspjpajak/json/listpajak",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            }, "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "idbas", value: ketidbas}
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
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                
               /* var textnama = "<input type='hidden' id='nama" + index + "' value='" + aData['namaKeg'] + "' />";
                var textkode = "<input type='hidden' id='kode" + index + "' value='" + aData['kodeKeg'] + "' />";
                var tnama = "<textarea id='txtnama" + index + "'readonly style='border:none;margin:0;width:960px;'>" + aData['namaKeg'] + "</textarea>";
                var textket = "<input type='hidden' id='ket" + index + "' value='" + aData['kodeKeg'] + "/" + aData['namaKeg'] + "' />";
                var textbeban = "<input type='hidden' id='beban" + index + "' value='" + aData['beban'] + "' />";
                var textjenis = "<input type='hidden' id='idkeg" + index + "' value='" + aData['idKegiatan'] + "' />";
                */
               
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + $('#ketnilaip'+index).val() + "' onkeyup='hitungtotal()' />";
                var hitung; // "<select id='jenistrans" + index + "' name='jenistrans" + index + "' ><option value='0'>NON PPN</option><option value='1'>PPN</option></select>";
           
                if (aData['idBas'] == "3904"){ // PPh 21
                    hitung = "";
                } else if (aData['idBas'] == "3905"){ // PPh 22
                    hitung = "<select id='ppnP2' name='ppnP2' onchange=hitungP2(this.value," + index + ") ><option value='-'>Pilih</option><option value='0'>NON PPN</option><option value='1'>PPN</option></select>";
                }else if (aData['idBas'] == "3906"){ // PPh 23
                    hitung = "<select id='ppnP3' name='ppnP3' onchange=hitungP3(" + index + ") ><option value='-'>Pilih</option><option value='0'>NON PPN</option><option value='1'>PPN</option></select> &nbsp <select id='persenP3' name='persenP3' onchange=hitungP3(" + index + ") ><option value='-'>Pilih</option><option value='2'>2%</option></select>";
                }else if (aData['idBas'] == "3908"){ // PPh Pasal 4 ayat (2)
                    hitung = "<select id='ppnP4' name='ppnP4' onchange=hitungP4(" + index + ") ><option value='-'>Pilih</option><option value='0'>NON PPN</option><option value='1'>PPN</option></select> &nbsp <select id='persenP4' name='persenP4' onchange=hitungP4(" + index + ") ><option value='-'>Pilih</option><option value='2'>2%</option><option value='3'>3%</option><option value='4'>4%</option><option value='5'>5%</option><option value='6'>6%</option><option value='10'>10%</option></select>";
                }else if (aData['idBas'] == "3909"){ // PPN
                    hitung = "<select id='persenP5' name='persenP5' onchange=hitungP5(this.value," + index + ")><option value='-'>Pilih</option><option value='1'>1%</option><option value='10'>10%</option></select>";
                }else if (aData['idBas'] == "3907"){ // PPh 26
                    hitung = "";
                }

                rowpajak = rowpajak + 1;
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(hitung);
                $('td:eq(3)', nRow).html(nilaiinput);
                // $('td:eq(4)', nRow).html(textidkeg+textbeban + textket + textkode + textnama + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "left"},
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiPajak", "bSortable": true, sClass: "right"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    //hitungtotal();
                
}

function hitungtotal() {

    var total = 0;

    for (var a = 1; a <= rowpajak; a++) {
        total += parseFloat(accounting.unformat($("#nilaiinput" + a).val(), ","));
    }

    $("#sumpajak").val(accounting.formatNumber(total, 2, '.', ","));

}

function hitungP2(ppn,index) {
    var hasil;
    
    if (ppn == 0) { // NON PPN
        hasil = (nilaispj) * (1.5 / 100);
    } else if (ppn == 1) {
        hasil = (nilaispj / 1.1) * (1.5 / 100);
    } else {
        hasil = 0;
    }

    $('#nilaiinput'+index).val(accounting.formatNumber(hasil, 2, '.', ","));
    hitungtotal();
    
}

function hitungP3(index) {
    var hasil;
    var persen = $('#persenP3').val();
    var ppn = $('#ppnP3').val();
    
    if (ppn == "-" || persen == "-") {
        hasil = 0;
        
    } else {
        var pengali = ppn == 0 ? 1 : 1.1;
        //console.log("pengali = "+pengali);
        hasil = (nilaispj / pengali) * (persen / 100);
    }
    
    $('#nilaiinput'+index).val(accounting.formatNumber(hasil, 2, '.', ","));
    hitungtotal();
    
}

function hitungP4(index) {
    var hasil;
    var persen = $('#persenP4').val();
    var ppn = $('#ppnP4').val();
    
    if (ppn == "-" || persen == "-") {
        hasil = 0;
        
    } else {
        var pengali = ppn == 0 ? 1 : 1.1;
        //console.log("pengali = "+pengali);
        hasil = (nilaispj / pengali) * (persen / 100);
    }
    
    $('#nilaiinput'+index).val(accounting.formatNumber(hasil, 2, '.', ","));
    hitungtotal();
    
}

function hitungP5(persen,index) {
    var hasil;
    
    if (persen == 1) { // 1%
        hasil = (nilaispj / 1.01) * (1 / 100);
    } else if (persen == 10) { //10%
        hasil = (nilaispj / 1.1) * (10 / 100);
    } else {
        hasil = 0;
    }

    $('#nilaiinput'+index).val(accounting.formatNumber(hasil, 2, '.', ","));
    hitungtotal();
    
}
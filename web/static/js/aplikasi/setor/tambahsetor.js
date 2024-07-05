$(document).ready(function() {
    //getNomorSetor();
    //setPanelST();
    setPanelAwal();
    //setCurrentDate();
    //$('#idbas').val(12780);
    //$('#kodetransaksi').val('ST'); //ditambah default c_trx = 'ST' karena setoran hanya untuk transaksi ST, tidak ada JG
    //$('#kodesumbdana').val('2');
});

var nilaist = 0;

function setPanelST() {
    document.getElementById("nosetorpane").style.display = "block";
    document.getElementById("tglsetorpane").style.display = "block";
    document.getElementById("nilaisetorpane").style.display = "block";
    //document.getElementById("bkupilihbutton").style.display = "none";
    document.getElementById("uraianpane").style.display = "block";
    document.getElementById("panelrincireal").style.display = "none";
    //$("#nilaisetor").attr('readonly', false);
    //getNilaiSetorST();
}
function setPanelAwal() {
    document.getElementById("nosetorpane").style.display = "none";
    document.getElementById("tglsetorpane").style.display = "none";
    document.getElementById("nilaikasmasukpane").style.display = "none";
    document.getElementById("nilairealisasipane").style.display = "none";
    document.getElementById("nilaisetorsebelumpane").style.display = "none";
    document.getElementById("nilaisisapane").style.display = "none";
    document.getElementById("nilaisetorpane").style.display = "none";
    document.getElementById("bkupilihbutton").style.display = "none";
    document.getElementById("uraianpane").style.display = "none";
    document.getElementById("panelrincireal").style.display = "none";
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


// tidak dipake lagi karena setoran hanya untuk sisa kas BOP
function setPanelTambah(ctrx) {
    if ($('#kodetriwulan').val() == "0") {
        bootbox.alert('Silahkan pilih Triwulan lebih dulu.');
    } else {
        setCurrentDate();
        //get Nomor Setor
        if ($('#nosetor').val() == '' || $('#nosetor').val() == null) {
            getNomorSetor();
        }
        //set panel berdasarkan pilihan jenis transaksi
        if (ctrx.substr(0, 2) == "ST") {
            document.getElementById("nosetorpane").style.display = "block";
            document.getElementById("tglsetorpane").style.display = "block";
            document.getElementById("nilaikasmasukpane").style.display = "block";
            document.getElementById("nilairealisasipane").style.display = "block";
            document.getElementById("nilaisetorsebelumpane").style.display = "block";
            document.getElementById("nilaisisapane").style.display = "block";
            document.getElementById("nilaisetorpane").style.display = "block";
            document.getElementById("bkupilihbutton").style.display = "none";
            document.getElementById("uraianpane").style.display = "block";
            document.getElementById("panelrincireal").style.display = "block";
            $("#nilaisetor").attr('readonly', false);
            $('#idbas').val(12780);
            $('#nobkureff').val(-1);
            $('#kodeSumbdana').val(ctrx.substr(2, 2));
            console.log($('#kodeSumbdana').val());
            getNilaiSetorST();
            gridRinciReal();
            /*
             } else if (ctrx.substr(0, 2) == "JG") {
             document.getElementById("nosetorpane").style.display = "block";
             document.getElementById("tglsetorpane").style.display = "block";
             document.getElementById("nilaisetorpane").style.display = "block";
             document.getElementById("bkupilihbutton").style.display = "block";
             document.getElementById("uraianpane").style.display = "block";
             $('#kodeSumbdana').val(ctrx.substr(2, 2));
             console.log("AAAAA " + $('#kodeSumbdana').val());
             //            $("#nilaisetor").attr('readonly', 'readonly');
             $('#nilaisetor').val("");
             $('#idbas').val(12781);
             */
        } else if (ctrx.substr(0, 2) == "RT") {
            document.getElementById("nosetorpane").style.display = "block";
            document.getElementById("tglsetorpane").style.display = "block";
            document.getElementById("nilaikasmasukpane").style.display = "none";
            document.getElementById("nilairealisasipane").style.display = "none";
            document.getElementById("nilaisetorsebelumpane").style.display = "none";
            document.getElementById("nilaisisapane").style.display = "none";
            document.getElementById("nilaisetorpane").style.display = "block";
            document.getElementById("bkupilihbutton").style.display = "block";
            document.getElementById("uraianpane").style.display = "block";
            document.getElementById("panelrincireal").style.display = "none";
            $('#kodeSumbdana').val(ctrx.substr(2, 2));
            console.log("AAAAA " + $('#kodeSumbdana').val());
            $("#nilaisetor").attr('readonly', 'readonly');
            $('#nilaisetor').val("");
            $('#idbas').val(1955);
        } else {
            setPanelAwal();
            $('#nilaisetor').val("");
            $('#idbas').val("");
        }
    }
}

function getNomorSetor() {
    $.getJSON(getbasepath() + "/setor/json/getnosetor", {idsekolah: $("#idsekolah").val(), tahunsetor: $("#tahunsetor").val()},
    function(result) {
        $('#nosetor').val(result);
    });
}

function getNilaiSetorST() { //ambil nilai saldo kas selain id -99 karena saat tambah belum ada id nya
//    $("#nilaisetor").attr('readonly', false);
    //var kodeSumbdana = $("#kodeSumbdana").val();
    //console.log("INI KODE A " + kodeSumbdana);
    var idsetor = -99;
    $.getJSON(getbasepath() + "/setor/json/getnilaist/", {
        idsekolah: $("#idsekolah").val(),
        tahunsetor: $("#tahunsetor").val(),
        idsetor: idsetor,
        kodetriwulan: $("#kodetriwulan").val()
                //kodeSumbdana: kodeSumbdana
    },
    function(result) {
        nilaist = result.sisaKas;
        $('#nilaikas').val(accounting.formatNumber(result.totalMohon, 2, '.', ","));
        $('#nilaitotalreal').val(accounting.formatNumber(result.totalRealisasi, 2, '.', ","));
        $('#nilaisetorsebelum').val(accounting.formatNumber(result.nilaiSetorSebelum, 2, '.', ","));
        $('#nilaisisa').val(accounting.formatNumber(result.sisaKas, 2, '.', ","));
        if (parseFloat(result.sisaKas) < 0) {
            $('#nilaisetor').val(accounting.formatNumber(0, 2, '.', ","));
            //nilaist = 0;
        } else {
            $('#nilaisetor').val(accounting.formatNumber(nilaist, 2, '.', ","));
            //nilaist = result;
        }
    });
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
    if ($('#kodetransaksi').val().substr(0, 2) == "ST") {
        if (nilaist < 0) {
            bootbox.alert("Nilai Saldo Kas minus sebesar " + nilaist + ".", function(result) {
                $('#nilaisetor').val(accounting.formatNumber(0, 2, '.', ","));
            });
        } else {
            if ($('#nilaisetor').val() > nilaist) {
                bootbox.alert("Nilai Setoran tidak boleh melebihi sisa kas sebesar " + nilaist + ".", function(result) {
                    $('#nilaisetor').val(accounting.formatNumber(nilaist, 2, '.', ","));
                });
            }
        }
    }
}

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

$(document).ready(function() {

});
// variabel global
var idpilihbank = 0;
var berhasil;

function getBanyakBankBerhasil() {
    var tahun = $('#tahun').val();
    var idbku = $('#idBku').val();
    var kodesumb = $('#kodesumb').val();
    $.getJSON(getbasepath() + "/statustransfer/json/getBanyakBankBerhasil", {tahun: tahun, idbku: idbku, kodesumb: kodesumb},
    function(result) {
        berhasil = result;
        if (result == 0) {
            document.getElementById("labelubahdatabank").style.display = "block";
        } else {
            document.getElementById("labelubahdatabank").style.display = "none";
        }

        grid();
    });
}

function grid() {
    var urljson = getbasepath() + "/statustransfer/json/listdatabank";
    if (typeof myTable == 'undefined') {
        myTable = $('#bkutable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            //"sDom": '<"top"i>lrt<"bottom"i>p<"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".checkbox").hide();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "idsekolah", value: $('#idsekolah').val()},
                {name: "kodesumb", value: $('#kodesumb').val()},
                {name: "berhasil", value: berhasil},
                {name: "idbku", value: $('#idBku').val()}

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
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {///cetakspp
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var nilaitf = accounting.formatNumber(aData['nilaiTransfer'], 2, '.', ",");
                var pilihan = "";
                var id = "<input type='hidden' id='idbank" + index + "' value='" + aData['id'] + "'>";
                var nilai = "<input type='text' id='nilai" + index + "'  class='inputmoney'  value='" + nilaitf + "' readOnly='true' />";
                if (berhasil == 0) {
                    pilihan = "<input type='radio' onclick='setPilih(" + index + ")' class='ischecked' name='isapproved' id='isapproved" + index + "'/>";
                }

                $('td:eq(0)', nRow).html(index + id);
                $('td:eq(2)', nRow).html(nilai);
                $('td:eq(5)', nRow).html(pilihan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglProses", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "nilaiTransfer", "bSortable": false, sClass: "right", sDefaultContent: "-"},
                {"mDataProp": "msgTerimaBank", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "bit11", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function setPilih(id) {
    idpilihbank = id;
    console.log("idpilihbank = " + idpilihbank);
}

function cleardata() {
//console.log("masuk clear");
    $('#noBuktiDok').val("");
    $('#idBku').val("");
    $('#noMohon').val("");
    $('#uraian').val("");
    clearrow();
}

function clearrow() {
    var i;
    var table;
    table = document.getElementById('bkutablebody');
    var rows = table.rows;
    var jum = rows.length;
    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}

function cekStatusTf() {
    if (berhasil == 0) {
        bootbox.alert("Tidak Ada Data Bank dengan Status Berhasil, Silahkan Update Data Bank Terlebih Dulu ");
    } else {
        updatebku();
    }
}

function cekpilihbank() {
    if (idpilihbank == 0) {
        bootbox.alert("Silahkan Pilih Data Bank yang Akan Diubah");
    } else {
        updatebank();
    }
}

function updatebku() { // update status transfer di tabel master transaksi
    var varurl = getbasepath() + "/statustransfer/json/prosesupdatebku";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        idsekolah: $("#idsekolah").val(),
        kodesumb: $("#kodesumb").val(),
        nomohon: $("#noMohon").val()
    };
    dataac = datajour;
    return   $.ajax({
        type: "POST",
        url: varurl, contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {

        bootbox.alert("Status Transfer Berhasil Diubah");

    });
}

function updatebank() {
    var varurl = getbasepath() + "/statustransfer/json/prosesupdatedatabank";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        idbank: $("#idbank" + idpilihbank).val(),
        kodesumb: $("#kodesumb").val(),
        idbku: $("#idBku").val()
    };
    dataac = datajour;
    return   $.ajax({
        type: "POST",
        url: varurl, contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {

        bootbox.alert("Data Bank Berhasil Diubah");
        getBanyakBankBerhasil();

    });
}
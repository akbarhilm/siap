$(document).ready(function() {

});

var triwulan, jenisbayarpajak, kodetransaksi;

function grid() {

    $("#skpdtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkubop/json/listspjrinci",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "nobku", value: $('#nobku').val()},
                {name: "nobukti", value: $('#nobukti').val()},
                {name: "triwulan", value: triwulan},
                {name: "jenisbayarpajak", value: jenisbayarpajak},
                {name: "kodetransaksi", value: kodetransaksi},
                {name: "idsekolah", value: $('#idsekolah').val()}
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

                var nilai = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");

                var idbku = "<input type='hidden' id='idbku" + index + "' value='" + aData['idBku'] + "' />";
                //var idrinci = "<input type='hidden' id='idrinci" + index + "' value='" + aData['idRinci'] + "' />";
                var nobku = "<input type='hidden' id='nobku" + index + "' value='" + aData['noBkuMohon'] + "' />";
                //var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                //var kodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var idkeg = "<input type='hidden' id='idkeg" + index + "' value='" + aData['idKegiatan'] + "' />";
                var kodekeg = "<input type='hidden' id='kodekeg" + index + "' value='" + aData['kodeKeg'] + "' />";
                var nobukti = "<input type='hidden' id='nobukti" + index + "' value='" + aData['noBuktiDok'] + "' />";
                //var idkomponen = "<input type='hidden' id='idkomponen" + index + "' value='" + aData['bkuRinci']['idKomponen'] + "' />";

                var kegiatan = "<textarea id='ketkeg" + index + "'readonly style='border:none;margin:0;width:600px;'>" + aData['kodeKeg'] + "/" + aData['namaKeg'] + "</textarea>";
                //var akun = "<textarea id='ketakun" + index + "'readonly style='border:none;margin:0;width:240px;'>" + aData['kodeakun'] + "/" + aData['namaakun'] + "</textarea>";
                //var komponen = "<textarea id='komponen" + index + "'readonly style='border:none;margin:0;width:230px;'>" + aData['bkuRinci']['namaKomponen'] + "</textarea>";
                var nilaipajak = "<input type='text' id='nilai" + index + "'  class='inputmoney'  value='" + nilai + "' readOnly='true' />";

                $('td:eq(0)', nRow).html(index + nobku + idbku);
                $('td:eq(3)', nRow).html(kegiatan + idkeg + kodekeg);
                $('td:eq(4)', nRow).html(nilaipajak + nobukti);
                $('td:eq(5)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambildata(" + index + ")'></span>");

                return nRow;

            },
            "aoColumns": [
                {"mDataProp": "idBku", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center", "sWidth": "10%"},
                {"mDataProp": "noDok", "bSortable": false, sClass: "left", "sWidth": "12%"},
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center", "sWidth": "19%"},
                {"mDataProp": "nilaiKeluar", "bSortable": true, sClass: "right", "sWidth": "16%"},
                {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center", "sWidth": "3%"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

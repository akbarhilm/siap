$(document).ready(function() {

});

var kodetrans, nobkumohon, triwulan;

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
            "sAjaxSource": getbasepath() + "/bkubop/json/listsetor",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "kodetransaksi", value: kodetrans},
                {name: "nobkumohon", value: nobkumohon},
                {name: "triwulan", value: triwulan},
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

                var nilai = accounting.formatNumber(aData['nilaiSetor'], 2, '.', ",");
                var tgl = aData['tglSetor'];
                var dd, mm, yy, tanggal;

                yy = tgl.substr(0, 4);
                mm = tgl.substr(5, 2);
                dd = tgl.substr(8, 2);
                tanggal = dd + "/" + mm + "/" + yy;

                var textnosts = "<input type='hidden' id='nosts" + index + "' value='" + aData['noSts'] + "' />";
                var nilaisetor = "<input type='text' id='nilai" + index + "'  class='inputmoney'  value='" + nilai + "' readOnly='true' />";
                var uraian = "<textarea id='uraian" + index + "'readonly style='border:none;margin:0;width:170px;'>" + aData['uraian'] + "</textarea>";
                var texttanggal = "<input type='hidden' id='tanggal" + index + "' value='" + tanggal + "' />";
                var noref = "<input type='hidden' id='nobkuref" + index + "' value='" + aData['noBkuReff'] + "' />";

                $('td:eq(0)', nRow).html(index + textnosts + texttanggal);
                $('td:eq(1)', nRow).html(tanggal);
                //$('td:eq(3)', nRow).html(uraian);
                $('td:eq(4)', nRow).html(nilaisetor + noref);
                $('td:eq(5)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambildata(" + index + ")'></span>");

                return nRow;

            },
            "aoColumns": [
                {"mDataProp": "noSts", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSts", "bSortable": false, sClass: "center"},
                {"mDataProp": "uraian", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sClass: "right"},
                {"mDataProp": "noSts", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

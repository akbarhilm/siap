$(document).ready(function() {
    grid();
});

function grid() {
    $("#indexsetortable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#indexsetortable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/setor/json/listindexsetor",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
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
console.log(aData['kodeSumbdana']+" == "+aData['kodeTransaksi']);
                var ctrx = aData['kodeTransaksi'] == "JG" ? "Jasa Giro" : 
                        aData['kodeTransaksi'] == "ST" && aData['kodeSumbdana'] == '1' ? "Setoran Sisa Belanja BOS" : 
                        aData['kodeTransaksi'] == "ST" && aData['kodeSumbdana'] == '2' ? "Setoran Sisa Belanja BOP" : 
                        aData['kodeTransaksi'] == "RT" && aData['kodeSumbdana'] == '1' ? "Pendapatan Lain-Lain BOS" : 
                        aData['kodeTransaksi'] == "RT" && aData['kodeSumbdana'] == '2' ? "Pendapatan Lain-Lain BOP" : "";


                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(ctrx);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiSetor'], 2, '.', ","));
                $('td:eq(6)', nRow).html("<a href='" + getbasepath() + "/setor/ubahsetor/" + aData['kodeTransaksi'] + "/" + aData['idSetor'] + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/setor/hapussetor/" + aData['kodeTransaksi'] + "/" + aData['idSetor'] + "' class='icon-remove' ></a>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeTransaksi", "bSortable": false, sClass: "left"},
                {"mDataProp": "uraian", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiSetor", "bSortable": false, sClass: "right"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
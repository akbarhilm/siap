$(document).ready(function() {

});

var ketval, ketcarabayar, triwulan;

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
            "sAjaxSource": getbasepath() + "/common/json/listkegboppertw",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "nama", value: $('#nama').val()},
                {name: "tahun", value: $('#tahun').val()},
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

                var textnama = "<input type='hidden' id='nama" + index + "' value='" + aData['namaKegiatan'] + "' />";
                var textkode = "<input type='hidden' id='kode" + index + "' value='" + aData['kodeKegiatan'] + "' />";
                var tnama = "<textarea id='txtnama" + index + "'readonly style='border:none;margin:0;width:370px;'>" + aData['namaKegiatan'] + "</textarea>";
                var textket = "<input type='hidden' id='ket" + index + "' value='" + aData['kodeKegiatan'] + "/" + aData['namaKegiatan'] + "' />";
                var textidkeg = "<input type='hidden' id='idkeg" + index + "' value='" + aData['idKegiatan'] + "' />";

                var textsnp = "<input type='hidden' id='snp" + index + "' value='" + aData['ketSnp'] + "' />";
                var textbidang = "<input type='hidden' id='bidang" + index + "' value='" + aData['ketBidang'] + "' />";
                var textsumbdana = "<input type='hidden' id='sumbdana" + index + "' value='" + aData['ketSumbdana'] + "' />";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(tnama + textsnp + textbidang + textsumbdana);
                $('td:eq(6)', nRow).html(textidkeg + textket + textkode + textnama + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeKegiatan", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaKegiatan", "bSortable": true, sClass: "center"},
                {"mDataProp": "ketSnp", "bSortable": false, sClass: "left"},
                {"mDataProp": "ketBidang", "bSortable": true, sClass: "left"},
                {"mDataProp": "ketSumbdana", "bSortable": true, sClass: "left"},
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

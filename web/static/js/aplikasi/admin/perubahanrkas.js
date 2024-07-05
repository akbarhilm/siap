$(document).ready(function() {
    getKegiatan();
});

function getKegiatan() {
    var kodesumbdana = $('#kodesumbdana').val();

    // get sisa kas pagu per-kegiatan
    $.getJSON(getbasepath() + "/perubahanrkas/json/getKegiatan", {tahun: tahun, idsekolah: idsekolah, triwulan: triwulan, nobku: nobku, idkegiatan: idkegiatan},
    function(result) {

        var saldo = result.aData['saldoKas'];
        var pagu = result.aData['paguAkb'];

        $('#sisakas').val(accounting.formatNumber(saldo, 2, '.', ","));
        $('#paguakb').val(accounting.formatNumber(pagu, 2, '.', ","));

    });
}

function check() {

}

function grid() {
    if (typeof myTable == 'undefined') {

        myTable = $('#jourtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, 200, 5000], [25, 50, 100, 200, "All"]],
            "iDisplayLength": 50,
            "sAjaxSource": getbasepath() + "/perubahanrkas/json/listindex",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idskpd", value: $("#idskpd").val()},
                {name: "triwulan", value: $("#triwulan").val()},
                {name: "tipe", value: $("#jenis").val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

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
                var tahun = $("#tahun").val();
                var tanggal = aData['tanggalMohon'];
                var tanggalMohon, thn, mm, dd;
                console.aData;
                thn = tanggal.substr(0, 4);
                mm = tanggal.substr(5, 2);
                dd = tanggal.substr(8, 2);
                tanggalMohon = dd + "/" + mm + "/" + thn;
                var vdana = accounting.formatNumber(aData['danaTalangan'], 2, '.', ",");

                var dana = "<input type='text' name='nilai" + index + "' id='nilai" + index + "'  class='inputmoney'  value='" + vdana + "' readOnly='true'/>";
                var uraian = "<textarea id='uraian" + index + "'readonly style='border:none;margin:0;width:180px;'>" + aData['uraian'] + "</textarea>";

                var edit = "<a href='" + getbasepath() + "/danatalangan/editdanatalangan/" + "?idtalangan=" + aData['id'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-<a href='" + getbasepath() + "/danatalangan/hapusdanatalangan/" + "?idtalangan=" + aData['id'] + "&tahun=" + tahun + "' class='icon-remove' ></a>";

                var arsip = "<a href='" + getbasepath() + "/danatalangan/arsipdanatalangan/" + "?idtalangan=" + aData['id'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";

                var editvalid = "";

                if ($("#max").val() != $("#triwulan").val()) { // jika sudah tutup buku triwulan
                    editvalid = arsip;
                } else {
                    editvalid = edit;
                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(tanggalMohon);
                $('td:eq(4)', nRow).html(uraian);
                $('td:eq(7)', nRow).html(dana);
                $('td:eq(8)', nRow).html(editvalid);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "tanggalMohon", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaSekolah", "bSortable": true, sClass: "center"},
                {"mDataProp": "bulanTagihan", "bSortable": true, sClass: "center"},
                {"mDataProp": "uraian", "bSortable": false, sClass: "left"},
                {"mDataProp": "iMcb", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaMcb", "bSortable": false, sClass: "left"},
                {"mDataProp": "danaTalangan", "bSortable": false, sClass: "right"},
                {"mDataProp": "id", "bSortable": false, "sWidth": "5%", sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();

    }

}
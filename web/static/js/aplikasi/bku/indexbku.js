$(document).ready(function() {
    setBulan();

});

function setBulan() {
    var currentbulan;

    $("#tglHide").datepicker("setDate", new Date());
    currentbulan = $("#tglHide").val();
    var mm = currentbulan.substr(3, 2);
    $('#bulan').val(mm);

    gridbku();
    getTotal();
}

function gridbku() {

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
            "sAjaxSource": getbasepath() + "/bku/json/listindex",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "bulan", value: $("#bulan").val()}
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
                var dpost = aData['tglPosting'];
                var tglPost = "";
                var tahun, mm, dd;
                //if (index > 1) {
                tahun = dpost.substr(0, 4);
                mm = dpost.substr(4, 2);
                dd = dpost.substr(6, 2);
                tglPost = dd + "/" + mm + "/" + tahun;
                //}
                var kodetrans = aData['kodeTransaksi'];
                var nobku = aData['noBKU'];
                var idsekolah = $("#idsekolah").val();
                var nobkuref = aData['noBkuRef'];

                var nilaim = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                var nilaik = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");
                var nilais = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + nilaim + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + nilaik + "' readOnly='true'/>";
                var saldo = "<input type='text' name='saldo" + index + "' id='saldo" + index + "'  class='inputmoney'  value='" + nilais + "' readOnly='true'/>";
                var idtrans = "<input type='hidden' id='kodetransaksi" + index + "' value='" + aData['idTransaksi'] + "' />";
                var uraian = "<textarea id='uraianbukti" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['uraianBukti'] + "</textarea>";
                var kegiatan = "<textarea id='kegiatan" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['ketKegiatan'] + "</textarea>";

                var edit = "<a href='" + getbasepath() + "/bku/editbkuspj/" + "?tahun=" + tahun + "&nobku=" + nobku + "&idsekolah=" + idsekolah + "&kodetrans=" + kodetrans + "' class='icon-edit' ></a>";
                var editpjpn = "<a href='" + getbasepath() + "/bku/editbkuspj/" + "?tahun=" + tahun + "&nobku=" + nobkuref + "&idsekolah=" + idsekolah + "&kodetrans=JJ' class='icon-edit' ></a>"; // penerimaan pajak panel nya ke SPJ
                var editjo = "<a href='" + getbasepath() + "/bku/editbkujo/" + "?nobku=" + aData['noBKU'] + "&tahun=" + tahun + "&idsekolah=" + idsekolah + "&kodetrans=" + kodetrans + "' class='icon-edit' ></a>";
                var editsetor = "<a href='" + getbasepath() + "/bku/editbkusetor/" + "?nobku=" + aData['noBKU'] + "&tahun=" + tahun + "&idsekolah=" + idsekolah + "&kodetrans=" + kodetrans + "' class='icon-edit' ></a>";
                //var editsetor = "<a href='" + getbasepath() + "/bku/editbkusetor/" + aData['noBKU'] + "?tahun=" + tahun + "&idsekolah=" + idsekolah + "&kodetrans=" + kodetrans + "' class='icon-edit' ></a>";
                var editjg = "<a href='" + getbasepath() + "/bku/editbkujg/" + "?nobku=" + aData['noBKU'] + "&tahun=" + tahun + "&idsekolah=" + idsekolah + "&kodetrans=" + kodetrans + "' class='icon-edit' ></a>";
                var editpjpg = "<a href='" + getbasepath() + "/bku/editbkupajak/" + "?nobku=" + aData['noBKU'] + "&tahun=" + tahun + "&idsekolah=" + idsekolah + "&kodetrans=" + kodetrans + "' class='icon-edit' ></a>";

                var tanggalDok;
                var editvalid = "";


                tanggalDok = tglPost;

                if (aData['ketKegiatan'] == null) {
                    kegiatan = "";
                }

                if (index == 1 || aData['bkuStatus'] == 1) {
                    editvalid = "";

                } else if (kodetrans == "JJ") {
                    editvalid = edit;

                } else if (kodetrans == "JO") {
                    editvalid = editjo;

                } else if (kodetrans == "ST") {
                    editvalid = editsetor;

                } else if (kodetrans == "JG") {
                    editvalid = editjg;

                } else if (kodetrans.substr(0, 1) == "P") {

                    if (aData['nilaiMasuk'] > 0) {
                        editvalid = editpjpn;
                    } else if (aData['nilaiKeluar'] > 0) {
                        editvalid = editpjpg;
                    }

                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(tanggalDok);
                $('td:eq(5)', nRow).html(uraian);
                $('td:eq(6)', nRow).html(kegiatan);
                $('td:eq(7)', nRow).html(nilaimasuk + idtrans);
                $('td:eq(8)', nRow).html(nilaikeluar);
                $('td:eq(9)', nRow).html(saldo);
                $('td:eq(10)', nRow).html(editvalid);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "tglDok", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "uraianBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "ketKegiatan", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "right"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "right"},
                {"mDataProp": "idBas", "bSortable": false, "sWidth": "5%", sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
        
    }

    
}

function getTotal() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var bulan = $('#bulan').val();

    $.getJSON(getbasepath() + "/bku/json/getTotal", {tahun: tahun, idsekolah: idsekolah, bulan: bulan},
    function(result) {

        var tm = result.aData['nilaiMasuk'];
        var tk = result.aData['nilaiKeluar'];
        var ts = result.aData['nilaiSisa'];

        $("#totmasuk").val(accounting.formatNumber(tm, 2, '.', ","));
        $("#totkeluar").val(accounting.formatNumber(tk, 2, '.', ","));
        $("#totsaldokas").val(accounting.formatNumber(ts, 2, '.', ","));
        $("#saldokas").val(accounting.formatNumber(ts, 2, '.', ","));

    });
}
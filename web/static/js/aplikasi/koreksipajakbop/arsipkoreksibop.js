$(document).ready(function() {
    gridkoreksi();

});

var jumbariskoreksi;

function gridkoreksi() {
    var total = 0;
    jumbariskoreksi = 0;
    if (typeof myTablePajak == 'undefined') {
        myTablePajak = $('#pajaktable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[50, 100, 500, -1], [50, 100, 500, "All"]],
            "iDisplayLength": 100,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/koreksiPajakBop/json/listkoreksipjpn",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "nomohon", value: $("#noBkuRef").val()}
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

                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var idblrinci = "<input type='hidden' id='idblrinci" + index + "' value='" + aData['idBlRinci'] + "' />";
                var kodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                var idkomponen = "<input type='hidden' id='idkomponen" + index + "' value='" + aData['idKomponen'] + "' />";
                var akun = "<textarea id='akun" + index + "'readonly style='border:none;margin:0;width:500px;'>" + aData['ketakun'] + "</textarea>";
                var komponen = "<textarea id='komponen" + index + "'readonly style='border:none;margin:0;width:500px;'>" + aData['namaKomponen'] + "</textarea>";

                var nourut = "<input type='hidden' id='nourut" + index + "' value='" + aData['noUrut'] + "' />";
                var namasubkeg = "<input type='hidden' id='namasubkeg" + index + "' value='" + aData['namaSubKegiatan'] + "' />";
                var ketrinci = "<input type='hidden' id='ketrinci" + index + "' value='" + aData['keteranganRinci'] + "' />";
                var komponenpajak = "<input type='hidden' id='komponenpajak" + index + "' value='" + aData['komponenPajak'] + "' />";
                var volume = "<input type='hidden' id='volume" + index + "' value='" + aData['volume'] + "' />";
                var hargasatuan = "<input type='hidden' id='hargasatuan" + index + "' value='" + aData['hargaSatuan'] + "' />";

                var nilaitotal = aData['nilaiBkuInput'] * (-1);
                var nilaispj = "<input type='text' name='nilaispj" + index + "' id='nilaispj" + index + "'  class='inputmoney'  value='" + nilaitotal + "' readOnly='true' />";
                var nilai = "<input type='text' name='nilai" + index + "' id='nilai" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' readOnly='true' />";

                total = total + nilaitotal;
                $('#sumspj').autoNumeric('set', (total * -1));
                $('#sumpjpn').autoNumeric('set', total);
                jumbariskoreksi = jumbariskoreksi + 1;

                $('td:eq(0)', nRow).html(index + nourut + namasubkeg);
                $('td:eq(1)', nRow).html(akun + idbas + ketrinci + volume);
                $('td:eq(2)', nRow).html(komponen + idkomponen + idblrinci);
                $('td:eq(3)', nRow).html(nilai);
                $('td:eq(4)', nRow).html(nilaispj + komponenpajak + hargasatuan + kodeakun);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "right"},
                {"mDataProp": "namaKomponen", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right"}
            ]
        });
    }
    else
    {
        myTablePajak.fnClearTable(0);
        myTablePajak.fnDraw();
    }
}


function hapus() {
    
    bootbox.confirm("Anda akan menghapus data Koreksi Penerimaan Pajak ini, lanjutkan ? ", function(result) {
        if (result) {
            var varurl = getbasepath() + "/koreksiPajakBop/json/prosesdelete";
            var dataac = [];

            var datajour = {
                tahun: $("#tahun").val(),
                idsekolah: $("#idsekolah").val(),
                nobkuref: $("#noBkuRef").val()
            };
            dataac = datajour;
            return   $.ajax({
                type: "POST",
                url: varurl,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(dataac)
            }).always(function(data) {
                bootbox.alert("Koreksi Data Berhasil Dihapus");
                window.location.href = getbasepath() + "/koreksiPajakBop/indexbop"; // ke form index
            });


        } else {
            bootbox.hideAll();
            return result;
        }
    });


}
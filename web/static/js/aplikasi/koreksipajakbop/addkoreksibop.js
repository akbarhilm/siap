$(document).ready(function() {


});

var jumbariskoreksi;

function getKegiatan() {
    var tahun = $('#tahun').val();
    var idsekolah = $('#idsekolah').val();
    var idbku = $('#idBku').val();

    $.getJSON(getbasepath() + "/koreksiPajakBop/json/getDataKegiatan", {tahun: tahun, idsekolah: idsekolah, idbku: idbku},
    function(result) {

        if (result.aData !== null) { // jika ada datanya

            var y = result.aData['tglDok'].substr(0, 4);
            var m = result.aData['tglDok'].substr(5, 2);
            var d = result.aData['tglDok'].substr(8, 2);

            var tanggal = d + "/" + m + "/" + y;

            $('#noBkuRef').val(result.aData['noBkuRef']);
            $('#idKegiatan').val(result.aData['idKegiatan']);
            $('#kodeKeg').val(result.aData['kodeKeg']);
            //$('#noBukti').val("KOREKSI - "+result.aData['noBukti']);
            //$('#tglDok').val(tanggal);
            $('#inboxFile').val(result.aData['inboxFile']);
            $('#kodePembayaran').val(result.aData['kodePembayaran']);
            $('#nrkPptk').val(result.aData['nrkPptk']);
            $('#nipPptk').val(result.aData['nipPptk']);
            $('#namaPptk').val(result.aData['namaPptk']);
            $('#namaRekan').val(result.aData['namaRekan']);
            $('#npwp').val(result.aData['npwp']);
            $('#masaPajak').val(result.aData['masaPajak']);
            $('#tahunPajak').val(result.aData['tahunPajak']);
            //$('#uraian').val(result.aData['uraian']);

            $('#masapajak1').val($('#masaPajak').val().substr(0, 2));
            $('#masapajak2').val($('#masaPajak').val().substr(2, 2));

        }

    });
}


function clearData() {
    $('#noBkuRef').val("");
    $('#idKegiatan').val("");
    $('#kodeKeg').val("");
    $('#noBukti').val("");
    $('#tglDok').val("");
    $('#inboxFile').val("");
    $('#kodePembayaran').val("");
    $('#nrkPptk').val("");
    $('#nipPptk').val("");
    $('#namaPptk').val("");
    $('#namaRekan').val("");
    $('#npwp').val("");
    $('#masaPajak').val("");
    $('#tahunPajak').val("");
    $('#uraian').val("");
    $('#ketKegiatan').val("");
    $('#noBkuMohon').val("");
    $('#idBku').val("");
    $('#sumpjpn').val("0");
}


function gridpjpn() {
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
                {name: "nomohon", value: $("#noBkuMohon").val()}
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

function cekSimpan() {
    if ($("#tglDok").val() == "" || $("#ketKegiatan").val() == "" || $("#noBukti").val() == "" || $("#uraian").val() == "") {
        bootbox.alert("Pengisian Data Harus Lengkap");
    } else {
        simpan();
    }

}

function simpan() {
    console.log("jumbariskoreksi = " + jumbariskoreksi);
    //BUAT SEMENTARA
    //document.getElementById("btnSimpan").style.visibility = "hidden";
    if (jumbariskoreksi > 0) {
        var varurl = getbasepath() + "/koreksiPajakBop/json/prosessimpan";
        var dataac = [];
        var nilailist = [];
        var i;
        var banyak = 0;


        for (i = 0; i < jumbariskoreksi; i++) { // list
            var id = i + 1;

            var pararr = {
                nilai: parseFloat(accounting.unformat($('#nilaispj' + id).val(), ",")).toString(),
                idbas: $("#idbas" + id).val(),
                kodeakun: $("#kodeakun" + id).val(),
                volume: $("#volume" + id).val(),
                idkomponen: $("#idkomponen" + id).val(),
                idblrinci: $("#idblrinci" + id).val(),
                nourut: $("#nourut" + id).val(),
                namasubkeg: $("#namasubkeg" + id).val(),
                komponenpajak: $("#komponenpajak" + id).val(),
                hargasatuan: $("#hargasatuan" + id).val(),
                ketrinci: $("#ketrinci" + id).val()
            };

            nilailist[i] = pararr;

        }

        var datajour = {
            tahun: $("#tahun").val(),
            idsekolah: $("#idsekolah").val(),
            kodetransaksi: $('#kodeTransaksi').val(),
            nobukti: $("#noBukti").val(),
            tgldok: $("#tglDok").val(),
            jenis: "3",
            beban: "TU",
            fileinbox: $("#inboxFile").val(),
            nrk: $("#nrkPptk").val(),
            namapptk: $("#namaPptk").val(),
            nippptk: $("#nipPptk").val(),
            uraian: $("#uraian").val(),
            carabayar: $("#kodePembayaran").val(),
            idkegiatan: $("#idKegiatan").val(),
            kodekeg: $("#kodeKeg").val(),
            //norek: $("#rekeningBank").val().trim(),
            namarekan: $("#namaRekan").val(),
            //kodebank: $("#kodeBank").val(),
            //kodebanktf: $("#kodeBankTf").val(),
            //namabanktf: $("#namaBank").val(),
            npwp: $("#npwp").val(),
            masapajak: $("#masaPajak").val(),
            tahunpajak: $("#tahunPajak").val(),
            triwulan: $("#triwulan").val(),
            nobkuref: $("#noBkuMohon").val(),
            nilailist: nilailist
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

            bootbox.alert("Koreksi Penerimaan Pajak Berhasil Disimpan");

        });

    } else {
        document.getElementById("btnSimpan").style.visibility = "visible";
    }

}


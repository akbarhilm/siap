/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setGrid() {

    if (typeof myTable == 'undefined') {

        myTable = $('#tokentable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, 200, -1], [25, 50, 100, 200, "All"]],
            "iDisplayLength": 50,
            "sAjaxSource": getbasepath() + "/token/json/listToken",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idsekolah", value: $("#idsekolah").val()},
                {name: "triwulan", value: $("#triwulan").val()},
                {name: "kodeSumbdana", value: '1'}
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
                var noBkuMohon = "<input type='hidden' id='noBkuMohon" + index + "' value='" + aData['noBkuMohon'] + "' />" + aData['noBkuMohon'];
                var token = "<input type='text' id='token" + index + "' value='' readonly='true'/><input type='hidden' id='requesttime" + index + "' value='' /><input type='hidden' id='requesttimedki" + index + "' value='' />";

                var uraian = "<textarea id='uraian" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['uraian'] + "</textarea>";
                var ketKegiatan = "<textarea id='ketKegiatan" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['ketKegiatan'] + "</textarea>";
                var request = "<button type='button' id='btnGetToken" + index + "' class='btn blue' onclick='getTimeLimit(" + index + ")'>Mohon</button> <button type='button' id='btnHapusToken" + index + "' class='btn red' disabled='true' onclick='hapusToken(" + index + ")'>Hapus</button>";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(noBkuMohon);
                $('td:eq(3)', nRow).html(ketKegiatan);
                $('td:eq(4)', nRow).html(uraian);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiBku'], 2, '.', ","));
                $('td:eq(6)', nRow).html(accounting.formatNumber(aData['nilaiNetto'], 2, '.', ","));
                $('td:eq(7)', nRow).html(token);
                $('td:eq(8)', nRow).html(request);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noBkuMohon", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "noBkuMohon", "bSortable": true, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "namaRekan", "bSortable": false, sClass: "center"},
                {"mDataProp": "ketKegiatan", "bSortable": false, "sWidth": "15%", sClass: "left"},
                {"mDataProp": "uraian", "bSortable": false, "sWidth": "15%", sClass: "left"},
                {"mDataProp": "nilaiBku", "bSortable": false, "sWidth": "15%", sClass: "right"},
                {"mDataProp": "nilaiNetto", "bSortable": false, "sWidth": "15%", sClass: "right"},
                {"mDataProp": "noBkuMohon", "bSortable": false, "sWidth": "15%", sClass: "center"},
                {"mDataProp": "noBkuMohon", "bSortable": false, "sWidth": "5%", sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();

    }

}
function getTimeLimit(index) {
    $.getJSON(getbasepath() + "/token/json/getTimeLimit", {kodesumbdana: 1, nomohon: $('#noBkuMohon' + index).val(), idsekolah: $('#idsekolah').val(),tahun:$("#tahun").val()},
    function(result) {
        var limit = result['dPayment'];
        getDate(index, limit);
    });
}
function getDate(index, limit) {
    $.getJSON(getbasepath() + "/token/json/getDate", {},
            function(result) {
                var date = result.aData['dMohon'];
                getDataBankDki(index, date, limit);
            });
}
function getDataBankDki(index, date, limit) {
    var currentdate = date;
    var newdate = new Date();            // formatnya 19-02-2018 09:22:56
    var dd, mm, yyyy, h, m, s;
    var limithh, limitmm;
    if (limit.substr(0, 2) != "00") {
        limithh = parseInt(limit.substr(0, 2));
    } else {
        limithh = 24;
    }
    if (limit.substr(3, 2) != "00") {
        limitmm = parseInt(limit.substr(3, 2));
    } else {
        limitmm = 60;
        limithh -= 1;
    }
    limitmm -= 1;
    var batas = (limithh * 3600000) + (limitmm * 60000) + (59 * 1000);
    dd = newdate.getDate();
    if (newdate.getDate() < 10) {
        dd = "0" + dd;
    }
    mm = newdate.getMonth() + 1;
    if (newdate.getMonth() < 10) {
        mm = "0" + mm;
    }
    yyyy = newdate.getFullYear();

    h = newdate.getHours();
    if (newdate.getHours() < 10) {
        h = "0" + h;
    }
    m = newdate.getMinutes();
    if (newdate.getMinutes() < 10) {
        m = "0" + m;
    }
    s = newdate.getSeconds();
    if (newdate.getSeconds() < 10) {
        s = "0" + s;
    }
    var date = dd + "-" + mm + "-" + yyyy + " " + h + ":" + m + ":" + s;
    var varurl = getbasepath() + "/token/json/token";
    var dataac = [];
    var datajour = {
        userid: $("#nrk").val()

    };
    dataac = datajour;

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {
            //console.log(data);
            var h, m, s;
            var dd, mm, yyyy;
            dd = parseInt(currentdate.substr(0, 2));
            mm = parseInt(currentdate.substr(3, 2));
            yyyy = parseInt(currentdate.substr(6, 4));
            var tanggal = new Date(yyyy, mm - 1, dd);

            h = parseInt(data.datetimerequest.substr(11, 2)) * 3600000;
            m = parseInt(data.datetimerequest.substr(14, 2)) * 60000;
            s = parseInt(data.datetimerequest.substr(17, 2)) * 1000;

            if (((h + m + s) > batas) || tanggal.getDay() == 0 || tanggal.getDay() == 6) {
                bootbox.alert("Permohonan token hanya dapat dilakukan pada hari Senin - Jumat sebelum pukul 15:30 WIB (Selain Bank DKI) dan pukul 20:30 WIB (Untuk Bank DKI). Silahkan  melakukan permohonan token kembali dihari kerja berikutnya.");
            } else {
                $('#token' + index).val(data.token);
//            $('#request').val(displaytime);
                $('#requesttime' + index).val(currentdate);
                console.log("A " + $('#requesttime' + index).val());
                $('#requesttimedki' + index).val(data.datetimerequest);
                console.log("B " + $('#requesttimedki' + index).val());
                $('#btnGetToken' + index).prop('disabled', true);
                $('#btnHapusToken' + index).prop('disabled', false);
            }


        },
        error: function(xhr) {
            console.error(xhr);
            bootbox.alert("Sambungan Bank DKI Terputus");
            //$('#btnSimpan').attr("disabled", true);
        }
    }).always(function(data) {

    });

}

function hapusToken(index) {
    $('#token' + index).val("");
    $('#requesttime' + index).val("");
    $('#requesttimedki' + index).val("");

    $('#btnGetToken' + index).prop('disabled', false);
    $('#btnHapusToken' + index).prop('disabled', true);
}

function simpan() {
    var length = document.getElementById('mytablebody').rows.length;
    var count = 0;
    for (var i = 1; i <= length; i++) {
        console.log($("#token" + i).val() + " " + $("#requesttime" + i).val());
        if ($("#token" + i).val() != "" && $("#requesttime" + i).val() != "") {
            console.log("masuk sini " + i);
            count++;
        }
    }
    if (count > 0) {
        bootbox.confirm("Anda akan menyimpan token sejumlah " + count + ",  lanjutkan ? ", function(result) {
            if (result) {
                count = 0;
                for (var i = 1; i <= length; i++) {
                    console.log($("#token" + i).val() + " " + $("#requesttime" + i).val());
                    if ($("#token" + i).val() != "" && $("#requesttime" + i).val() != "" && $("#requesttimedki" + i).val() != "") {
                        console.log("masuk sini2 " + i);
                        var varurl = getbasepath() + "/token/json/prosessimpan";

                        var dataac = [];

                        var datajour = {
                            nrk: $("#nrk").val(),
                            token: $("#token" + i).val(),
                            requesttime: $("#requesttime" + i).val(),
                            requesttimedki: $("#requesttimedki" + i).val(),
                            idsekolah: $("#idsekolah").val(),
                            nobkumohon: $("#noBkuMohon" + i).val(),
                            kodesumbdana: '1'
                        };
                        dataac = datajour;

                        $.ajax({
                            type: 'POST',
                            contentType: 'application/json',
                            url: varurl,
                            data: JSON.stringify(dataac),
                            success: function(data) {
                                //console.log(data);
                                count++;

                            },
                            error: function(xhr) {
                                console.error(xhr);
                                bootbox.alert("Sambungan Bank DKI Terputus");
                                //$('#btnSimpan').attr("disabled", true);
                            }
                        }).always(function(data) {

                        });
                    }
                }
                bootbox.alert("Simpan token berhasil");
                setGrid();
            } else {
                bootbox.hideAll();
            }
        });
    } else {
        bootbox.alert("Tidak ada data yang dapat disimpan");
    }



}

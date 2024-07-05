$(document).ready(function() {
    showGrid();
//    clear();
});


function showGrid() {

    var urljson = getbasepath() + "/admin/indexrkas/json/listrkas";
     if (typeof myTable == 'undefined') {
    myTable = $('#rkastable').dataTable({
        "bPaginate": true,
        "sPaginationType": "bootstrap",
        "bJQueryUI": true,
        "bProcessing": true,
        "bServerSide": true,
        "bInfo": true,
        "bScrollCollapse": true,
        //"sScrollY": ($(window).height() * 108 / 100),
        "bFilter": false,
        "sAjaxSource": urljson,
        "bDestroy": true,
        "aaSorting": [[1, "asc"]],
        "fnServerParams": function(aoData) {
            aoData.push(
                    {name: "npsnFilter", value: $("#npsnFilter").val()},
            {name: "namaSekolahFilter", value: $("#namaSekolahFilter").val()},
            {name: "namaPAFilter", value: $("#namaPAFilter").val()}
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
            var edit = "<a href='" + getbasepath() + "/admin/indexrkas/updaterkas/" + aData['idSekolah'] + "' class='icon-edit' ></a>";

            $('td:eq(0)', nRow).html(index);
            $('td:eq(18)', nRow).html(edit);

            return nRow;
        },
        "aoColumns": [
            {"mDataProp": "tahun", "bSortable": false, sClass: "center"},
            {"mDataProp": "tahun", "bSortable": false, sClass: "center"},
            {"mDataProp": "npsn", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaSekolah", "bSortable": true, sClass: "center"},
            {"mDataProp": "nrkPA", "bSortable": true, sClass: "center"},
            {"mDataProp": "nipPA", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaPA", "bSortable": true, sClass: "center"},
            {"mDataProp": "pangkatPA", "bSortable": true, sClass: "center"},
            {"mDataProp": "nrkPK", "bSortable": true, sClass: "center"},
            {"mDataProp": "nipPK", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaPK", "bSortable": true, sClass: "center"},
            {"mDataProp": "noNPWP", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaNPWP", "bSortable": true, sClass: "center"},
            {"mDataProp": "kotaNPWP", "bSortable": true, sClass: "center"},
            {"mDataProp": "noBOP", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaBOP", "bSortable": true, sClass: "center"},
            {"mDataProp": "noBOS", "bSortable": true, sClass: "center"},
            {"mDataProp": "namaBOS", "bSortable": true, sClass: "center"},
            {"mDataProp": "tahun", "bSortable": false, sClass: "center"}

        ]
    });
}
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function cek() {
    var cek1 = '0';
    var cek2 = '0';
    var cek3 = '0';
    console.log($("#nrkPA").val() + " TEST 1");
    if ($("#nrkPA").val() != '' && ($("#namaPA").val() == '' || $("#nipPA").val() == '' || $("#pangkatPA").val() == '')) {
        cek1 = '1';
        console.log("MASUK A1");
    } else {
        console.log("MASUK A2");
    }
    console.log($("#nrkPK").val() + " TEST 2");
    console.log("var " + cek1 + " " + cek2);
    if ($("#nrkPK").val() != '' && ($("#namaPK").val() == '' || $("#nipPK").val() == '')) {
        cek2 = '1';
        console.log("MASUK B1");
    } else {
        console.log("MASUK B2");
    }

    if ($("#noNPWP").val() == '' || $("#namaNPWP").val() == '' || $("#kotaNPWP").val() == '') {
        cek3 = '1';
    }
    if (cek1 == '1' || cek2 == '1' || $("#noNPWP").val() == ''
            || $("#namaNPWP").val() == '' || $("#kotaNPWP").val() == ''
            || $("#noBOP").val() == '' || $("#namaBOP").val() == ''
            || $("#noBOS").val() == '' || $("#namaBOS").val() == '') {
        bootbox.alert("Semua data harus diisi");
        return false;
    } else {
        return true;
    }
}

function clear() {
    if ($("#nrkPA").val() != '') {
        $("#namaPA").val('');
        $("#nipPA").val('');
        $("#pangkatPA").val('');
    } else if ($("#nrkPA").val() == ''
            && ($("#namaPA").val() != ''
                    || $("#nipPA").val() != ''
                    || $("#pangkatPA").val() != '')) {
        $("#namaPA").val('');
        $("#nipPA").val('');
        $("#pangkatPA").val('');
    }
    if ($("#nrkPK").val() != '') {
        $("#namaPK").val('');
        $("#nipPK").val('');
    }
}
function getData(jenis) {
    if (jenis == "1") {
        var nrk = $("#nrkPA").val();
    } else {
        var nrk = $("#nrkPK").val();
    }
    var user = 'sipkdprod';
    var pass = 'sipkdprod!!';
//    var user = 'sipkddev';
//    var pass = 'sipkddev';

    if ((nrk != "")) {
//        $.getJSON("http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD", {nrk: nrk},
//        function(data) {
//            if (data.results[0] != null) {
//                $("#namaPptk").val(data.results[0]['NAMA']);
//                $("#nipPptk").val(data.results[0]['NIP18']);
//            } else {
//                bootbox.alert("Data tidak ada");
//            }
//        }
//        );

        var varurl = getbasepath() + "/admin/indexrkas/json/getpegawai";
//        var varurl = "https://cors-anywhere.herokuapp.com/http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegSIPKD";
//        var varurl = "https://cors-anywhere.herokuapp.com/http://soadki.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD";
//        var varurl = "http://" + user + ":" + pass + "@soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegSIPKD";
//        var varurl = "http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegSIPKD";
//        var varurl = "http://soadki.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD";
        var dataac = [];
        console.log("NRK: " + nrk);
        var datajour = {
            nrk: nrk
        };
        dataac = datajour;
//        console.log("DATAAC: " + JSON.stringify(dataac));
//
        $.ajax({
            url: varurl,
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(dataac),
            contentType: 'text/plain; charset=utf-8',
            headers: {
                'Accept': 'application/json, text/javascript',
                'Content-Type': 'application/json',
            },
            success: function(result) {
                if (jenis == "1") {
                    $("#namaPA").val(result['NAMA']);
                    $("#nipPA").val(result['NIP18']);
                    $("#pangkatPA").val(result['NAPANG'] + ", " + result['GOL']);
                } else {
                    $("#namaPK").val(result['NAMA']);
                    $("#nipPK").val(result['NIP18']);
                }
            },
            error: function() {
                bootbox.alert('boo!');
            },
        });
    }
}

function cariNpsn() {
    $("#npsnFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            showGrid();
            $("#npsnFilter").focus();
        }
    });
}
function cariNamaSekolah() {
    $("#namaSekolahFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            showGrid();
            $("#namaSekolahFilter").focus();
        }
    });
}
function cariNamaPA() {
    $("#namaPAFilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            showGrid();
            $("#namaPAFilter").focus();
        }
    });
}
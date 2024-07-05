<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function () {
        grid();
    });

    function ambilskpd(id) {
        $('#nobkureff', window.parent.document).val($("#nobku" + id).val()).change();
        $('#nilaisetor', window.parent.document).val(accounting.unformat($("#nilaiterima" + id).val(), ",")).change();
        parent.$.fancybox.close();
    }

    function grid() {
        //var bkunoreff = $('#nobkureff', window.parent.document).val() == "" || $('#nobkureff', window.parent.document).val() == null ? -99 : $('#nobkureff', window.parent.document).val();
        var bkunoreff = $('#addoredit', window.parent.document).val() == "1" ? -99 : $('#nobkurefawal', window.parent.document).val();

        var kodetriwulan = $('#kodetriwulan', window.parent.document).val();
        var kodeSumbdana = $('#kodeSumbdana', window.parent.document).val();

        console.log("bku no reff ==== " + bkunoreff);
        $("#popupbkurtpntable").show();
        if (typeof myTable == 'undefined') {
            myTable = $('#popupbkurtpntable').dataTable({
                "bPaginate": true,
                "sPaginationType": "bootstrap",
                "bJQueryUI": false,
                "bProcessing": true,
                "bServerSide": true,
                "bInfo": true,
                "bScrollCollapse": true,
                "bFilter": false,
                "sAjaxSource": getbasepath() + "/setor/json/listpopupbkurtpenerimaan",
                "aaSorting": [[1, "asc"]],
                "fnServerParams": function (aoData) {
                    aoData.push(
                            {name: "tahun", value: $('#tahun').val()},
                            {name: "idsekolah", value: $('#idsekolah').val()},
                            {name: "nobkuref", value: bkunoreff},
                            {name: "kodetriwulan", value: kodetriwulan},
                            {name: "kodeSumbdana", value: kodeSumbdana}
                    );
                },
                "fnServerData": function (sSource, aoData, fnCallback) {
                    $.ajax({
                        "dataType": 'json',
                        "type": "GET",
                        "url": sSource,
                        "data": aoData,
                        "success": fnCallback
                    });
                },
                "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                    var numStart = this.fnPagingInfo().iStart;
                    var index = numStart + iDisplayIndexFull + 1;

                    var nilai = accounting.formatNumber(aData['nilaiBku'], 2, '.', ",");
                    var textnobku = "<input type='hidden' id='nobku" + index + "' value='" + aData['noBkuMohon'] + "' />";
                    var nilaiterima = "<input type='text' id='nilaiterima" + index + "'  class='inputmoney'  value='" + nilai + "' readOnly='true' />";

                    $('td:eq(0)', nRow).html(index + textnobku);
                    $('td:eq(4)', nRow).html(nilaiterima);
                    $('td:eq(5)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                    return nRow;
                },
                "aoColumns": [
                    {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center"},
                    {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center"},
                    {"mDataProp": "noDok", "bSortable": false, sClass: "left"},
                    {"mDataProp": "uraianBukti", "bSortable": false, sClass: "left"},
                    {"mDataProp": "nilaiBku", "bSortable": true, sClass: "right"},
                    {"mDataProp": "noBkuMohon", "bSortable": false, sClass: "center"}
                ]
            });
        } else
        {
            myTable.fnClearTable(0);
            myTable.fnDraw();
        }

    }

</script>

<form:form   method="post" commandName="formbkujgpn"  id="formbkujgpn"   action="" class="form-horizontal">
    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Penerimaan Pendapatan Lain-lain</div>
        </div>


        <div class="portlet-body">



            <div class="form-horizontal" >

                <div class="form-group" style="display: none">
                    <br></br>
                    <label class="col-md-4 control-label">No BKU :</label>
                    <div class="col-md-6">
                        <div class="input-group">
                            <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                            <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />

                            <input type="text"  name="kode"  id="kode"  class="form-control " size="20" onkeyup="if (event.keyCode == 13)
                                        tampil()" />
                        </div>
                    </div>
                </div>
                <!--
                                <div class="form-group" style="display: none">
                                    <label class="col-md-4 control-label">Kode Kegiatan : </label>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <input type="text"  name="kodekeg"  id="kodekeg"  class="form-control " size="20" onkeyup="if (event.keyCode == 13)
                                                        tampil()" />
                                        </div>
                                    </div>
                                </div>
                <!--
                <div class="form-actions fluid">
                    <div class="col-md-offset-4 col-md-9">
                        <button type="button" class="btn dark" onclick='tampil()'>Cari</button>
                    </div>
                </div>
                -->
            </div>
        </div>

    </div>
    <div class="portlet box">

        <div >
            <table id="popupbkurtpntable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>No BKU Ref</th>
                        <th>No Dokumen</th>
                        <th>Keterangan</th>
                        <th>Nilai</th>
                        <th>Pilih</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table>
        </div>
    </div>
</form:form>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/koreksipajakbop/listkoreksibop.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        triwulan = $('#triwulan', window.parent.document).val();
        kodetrx = $('#kodeTransaksi', window.parent.document).val();
        grid();
    });

    function ambildata(id) {
        
        if (accounting.unformat($('#nilai' + id).val(), ",") > 5000) {
            $('#noBkuMohon', window.parent.document).val($("#nobkumohon" + id).val()).change();
            //$('#noBuktiDok', window.parent.document).val($("#nobukti" + id).val()).change();
            $('#idBku', window.parent.document).val($("#idbku" + id).val()).change();
            $('#kodeKeg', window.parent.document).val($("#kodekeg" + id).val()).change();
            $('#ketKegiatan', window.parent.document).val($("#ketkeg" + id).val()).change();
            $('#idKegiatan', window.parent.document).val($("#idkeg" + id).val()).change();
            parent.$.fancybox.close();

        } else {
            bootbox.alert("Nilai Koreksi Pajak Harus Diatas Rp 5.000");
        }

        
    }
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Pajak Penerimaan</div>
        </div>

        <div class="portlet-body">

            <table  width="100%" >
                <tr><td colspan="5"  ></td></tr>
            </table>

            <div class="form-horizontal" >

                <div class="form-group" style="display: block">
                    <br></br>
                    <label class="col-md-4 control-label">No BKU Mohon:</label>
                    <div class="col-md-6">
                        <div class="input-group">
                            <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                            <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />

                            <input type="text"  name="nobku"  id="nobku"  class="form-control " size="20" onkeyup="if (event.keyCode == 13)
                                        grid()" />
                        </div>
                    </div>
                </div>

                <div class="form-group" style="display: block">
                    <label class="col-md-4 control-label">Nomor Bukti : </label>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text"  name="nobukti"  id="nobukti"  class="form-control " size="20" onkeyup="if (event.keyCode == 13)
                                        grid()" />
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="portlet box">

        <div >
            <table id="skpdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>BKU Mohon</th>
                        <th>No Bukti</th>
                        <th>Kegiatan</th>
                        <th>Keterangan</th>
                        <th>Total Pajak</th>
                        <th>Pilih</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table>
        </div>
    </div>
</form:form>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/statustransfer/listdok.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        triwulan = $('#triwulan', window.parent.document).val();
        idsekolah = $('#idsekolah', window.parent.document).val();
        kodeTransaksi = $('#kodeTransaksi', window.parent.document).val();
        kodesumb = $('#kodesumb', window.parent.document).val();
        grid();
    });

    function ambildata(id) {
        $('#noMohon', window.parent.document).val($("#nobku" + id).val()).change();
        $('#idBku', window.parent.document).val($("#idbku" + id).val()).change();
        $('#noBuktiDok', window.parent.document).val($("#nodok" + id).val()).change();
        $('#uraian', window.parent.document).val($("#uraian" + id).val()).change();

        parent.$.fancybox.close();
    }
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar SPJ </div>
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
                            <input type="text"  name="nobku"  id="nobku"  class="form-control " size="20" onkeyup="if (event.keyCode == 13)
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
                        <th>Uraian</th>
                        <th>Nilai Transfer</th>
                        <th>Pilih</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table>
        </div>
    </div>
</form:form>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkubop/listpnjasagiro.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        kodetrans = $('#kodeTransaksi', window.parent.document).val();
        nobkumohon = $('#noBkuMohon', window.parent.document).val();
        triwulan = $('#triwulan', window.parent.document).val();

        grid();
    });
    
    
    function ambildata(id) {
        $('#nobkuref', window.parent.document).val($("#nomohon" + id).val()).change();
        $('#nilaijg', window.parent.document).val($("#nilai" + id).val()).change();
        
        // untuk edit
        $('#noBkuRef', window.parent.document).val($("#nomohon" + id).val()).change();
        
        parent.$.fancybox.close();
    }
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Penerimaan Jasa Giro</div>
        </div>

        <div class="portlet-body">

            <table  width="100%" >

                <tr><td colspan="5"  ></td></tr>


            </table>

            <div class="form-horizontal" >

                <div class="form-group" style="display: none">
                    <br></br>
                    <label class="col-md-4 control-label">No BKU :</label>
                    <div class="col-md-6">
                        <div class="input-group">
                            <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                            <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />

                            <input type="text"  name="kode"  id="kode"  class="form-control " size="20" onkeyup="if (event.keyCode == 13)
                                    grid()" />
                        </div>
                    </div>
                </div>

                <div class="form-group" style="display: none">
                    <label class="col-md-4 control-label">Kode Kegiatan : </label>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text"  name="kodekeg"  id="kodekeg"  class="form-control " size="20" onkeyup="if (event.keyCode == 13)
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
                        <th>No Mohon</th>
                        <th>No Bukti Dok</th>
                        <th>Uraian</th>
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
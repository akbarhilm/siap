<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bank/listbank.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        //ketval = $('#keteranganKeg', window.parent.document).val();
        
        tampil();
    });
    
    function tampil() {
        grid();
    }
    
    function ambilskpd(id) {
        $('#kodeBank', window.parent.document).val($("#kodebank" + id).val()).change();
        $('#kodeBankTf', window.parent.document).val($("#kodetf" + id).val()).change();
        $('#namaBank', window.parent.document).val($("#namatf" + id).val()).change();
        
        parent.$.fancybox.close();
    }
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Bank</div>
    </div>
    
    <div class="portlet-body">
        
        <table  width="100%" >

        <tr><td colspan="5"  ></td></tr>
            
 
    </table>
         
        <div class="form-horizontal" >
            
            <div class="form-group">
                 <br></br>
                <label class="col-md-4 control-label">Kode Bank :</label>
                <div class="col-md-6">
                    <div class="input-group">
                        <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />
                        <input type="text"  name="kode"  id="kode"  class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-4 control-label">Nama Bank : </label>
                <div class="col-md-6">
                    <div class="input-group">
                        <input type="text"  name="nama"  id="nama"  class="form-control " size="50" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
            
            <div class="form-actions fluid">
                <div class="col-md-offset-4 col-md-9">
                    <button type="button" class="btn dark" onclick='tampil()'>Cari</button>
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
                    <th>Kode Bank</th>
                    <th>Nama Bank</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
</form:form>
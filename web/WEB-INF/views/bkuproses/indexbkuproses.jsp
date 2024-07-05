<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">


</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Batas Waktu Pengajuan Belanja Sekolah</a></li>
</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title"> <!-- cogs-->
            <div class="caption"><i class="icon-cogs"></i>Batas Waktu Pengajuan Belanja Sekolah</div>
            <div class="actions">
                <a onclick="" href="${pageContext.request.contextPath}/bkuproses/add"  class="btn dark"  ><i class="icon-plus"></i> Tambah</a>
            </div>
        </div>

        <div class="portlet-body flip-scroll">
            
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>
                
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idskpd" id='idskpd' value="${pengguna.idSkpd}"  />
                        <input name="skpd" id="skpd" type="text" size="80" readonly='true' />
                        
                    </div>
                </div>
            </div>


        </div>
    </div>

    <div class="portlet box">
        <div class="portlet-body">
            <table id="jourtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead  >
                    <tr>
                        <th>No</th>
                        <th>Sumber Dana</th>
                        <th>Triwulan</th>
                        <th>Batas Waktu</th>
                        <th>Edit</th>
                    </tr>
                </thead>

            </table>

        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkuproses/indexbkuproses.js"></script>


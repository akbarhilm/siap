<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Setor</a></li>
</ul>

<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Setor</div>
        <div class="actions">
            <a href="${pageContext.request.contextPath}/setor/tambahsetor"  class="btn dark"><i class="icon-plus"></i> Tambah</a> 
        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-4">
                    <input type="hidden" id="idsekolah" name="idsekolah" value="${sekolah.idSekolah}"  />
                    <input type="text"  name="textsekolah" readonly="true"  id="textsekolah"  class="m-wrap large" size="80"  value="${sekolah.npsn} / ${sekolah.namaSekolah}"  />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" readonly="true" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>
<div class="portlet box">
    <div >
        <table id="indexsetortable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th> 
                    <th>No Setor</th>
                    <th>Tanggal Setor</th>
                    <th>Jenis Transaksi</th>
                    <th>Uraian</th>
                    <th>Nilai Setor</th>
                    <th>Pilihan</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/setor/indexsetor.js"></script>  
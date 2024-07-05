<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">  Cetak Setoran</a></li>
</ul>
<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Cetak  Setoran</div>
        <!--  <div class="actions">
              <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
          </div>-->
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  readonly="true" class="m-wrap medium inputnumber" /> 
                        <input type="hidden" name="idpengguna" id="idpengguna" value="${pengguna.idPengguna}"/> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Sekolah : </label>
                <div class="col-md-4">
                    <input type="hidden" id="idsekolah" name="idsekolah" value="${sekolah.idSekolah}"  />
                    <input type="text"  name="sekolah"  id="sekolah"  readonly="true" class="m-wrap large" size="90"  value="${sekolah.npsn} / ${sekolah.namaSekolah}"  />

                </div>
            </div>

            <!-- <div class="form-actions fluid">
                 <div class="col-md-offset-3 col-md-9">
                     <button type="button" class="btn dark" onclick='gridpagusppup()'>Cari</button>
                 </div>
             </div>-->

        </form>
    </div>
</div>        
<div class="portlet box">
    <form id="formcetaksetortable">
        <div class="portlet-title">

        </div>
        <div class="portlet-body">
            <table id="cetaksetortable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr  >
                        <th>No</th>
                        <th>Tahun</th>
                        <th>No Setor</th>
                        <th>Jenis Transaksi</th> 
                        <th>Nilai</th>
                        <th>Status</th>
                        <th>Pejabat TTD</th>  
                        <th>Pilih</th>
                        <th>Unduh PDF</th>
                        <th>Batal</th>
                    </tr>
                </thead>
                <tbody id="cetaksetortablebody" >
                </tbody>                
            </table>
        </div>
    </form>
</div>


<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/setor/cetaksetor.js"></script>  

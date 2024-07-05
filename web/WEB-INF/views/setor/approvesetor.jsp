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
                        <input type="hidden" name="nrkpengguna" id="nrkpengguna" value="${pengguna.namaPengguna}"/>
                        <input type="hidden" id="idskpd" name="idskpd" value="${pengguna.idSkpd}"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Sekolah : </label>
                <div class="col-md-4">
                    <!--input type="hidden" id="idsekolah" name="idsekolah" value="${sekolah.idSekolah}"  /-->
                    <input type="text"  name="textsekolah"  id="textsekolah"  class="m-wrap large" size="90" value=""  />

                </div>
            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='grid()'>Cari</button>
                </div>
            </div>

        </form>
    </div>
</div>
<div class="portlet box">
    <form id="formapprovesetortable">
        <div class="portlet-title">

        </div>
        <div class="portlet-body">
            <table id="approvesetortable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr  >
                        <th>No</th>
                        <th>Sekolah</th>
                        <th>No Setor</th>
                        <th>Jenis Transaksi</th>
                        <th>Nilai</th>
                        <th>Status</th>
                        <th>Cek</th>
                        <th>Pilihan</th>
                    </tr>
                </thead>
                <tbody id="approvesetortablebody" >
                </tbody>
            </table>
        </div>
    </form>
</div>


<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/setor/approvesetor.js"></script>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU Sekolah</a>
        <span class="icon-angle-right"></span>
    </li>
</ul>
<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar  Berita Acara Pemeriksaan Kas - BOP</div>
        <div class="actions">
            <a href="#" onclick="pindahhalaman()" class="btn dark" id="tambahspj"><i class="icon-plus"></i> Tambah</a>
            <!--   <a id="tambahspj" onclick="setbtntambah()" class="btn dark" ><i class="icon-plus"></i> Tambah</a>-->
        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="tutupbuku" name="tutupbuku" />
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  readOnly="true"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-4">
                    <input type="hidden" id="idsekolah" name="idsekolah" value="${sekolah.idSekolah}"  />
                    <input type="hidden" id="tglBkuPros" name="tglBkuPros" value="${tglBkuPros}" />
                    <input type="text"  name="sekolah"  id="sekolah"  class="m-wrap large" size="75"  value="${sekolah.npsn}/${sekolah.namaSekolahPendek}" readOnly="true"/>
                </div>
            </div>

        </form>
    </div>
</div>
<div class="portlet box">
    <form id="formpagusppgup">
        <div class="portlet-title">

        </div>
        <div class="portlet-body">
            <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Tgl BAP Kas</th>
                        <th>Triwulan BAP Kas</th>
                        <th>Uraian</th>
                        <th id="kolom5">Edit  -  Hapus  -  Unduh</th>
                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                </tbody>
            </table>
        </div>
    </form>
</div>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bapkasbop/indexbapkasbop.js"></script>

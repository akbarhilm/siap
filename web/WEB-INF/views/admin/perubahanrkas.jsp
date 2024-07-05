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

    <li><a href="#">Pengajuan Perubahan RKAS</a></li>
</ul>

<form:form   method="post" commandName="refrkas"  id="refrkas"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title"> <!-- cogs-->
            <div class="caption"><i class="icon-cogs"></i>Pengajuan Perubahan RKAS</div>
            <div class="actions">
                <a onclick="" href="${pageContext.request.contextPath}/perubahanrkas/indexperubahanrkas"  class="btn dark"  ><i class="icon-plus"></i> Tambah</a>
            </div>
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:hidden path="sekolah.idSkpd" id='idskpd' value="${sekolah.idSkpd}"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>
            <div id="labeljenis" class="form-group">
                <label class="col-md-3 control-label">Sumberdana :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="kodesumbdana" id="jenis" onchange="" >
                            <option value="1">BOP</option>
                            <option value="2">BOS</option>
                        </select>

                    </div>
                </div>
            </div>
            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="idkegiatan" name="idkegiatan"  />
                        <select name="kegiatan" id="kegiatan" onchange="" >

                        </select>
                    </div>
                </div>
            </div>
            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Akun :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="idakun" name="idakun"  />
                        <select name="akun" id="akun" onchange="" >

                        </select>
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
                        <th>Kode Komponen</th>
                        <th>Nama Komponen</th>
                        <th>Keterangan Rinci</th>
                        <th>Nilai Anggaran</th>
                    </tr>
                </thead>

            </table>

        </div>
    </div>
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9">
            <button id="ajukan"  type="button"  class="btn blue" onclick=""> Ajukan </button>

        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/admin/perubahanrkas.js"></script>


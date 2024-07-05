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

    <li><a href="#">List RKAS</a></li>
</ul>

<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>

<form:form   method="post" commandName="listrkas"  id="listrkas"   action="" class="form-horizontal">
    <!--    <div onload="" class="portlet box tosca">
            <div class="portlet-title">
                <div class="caption"><i class="icon-cogs"></i>Data RKAS</div>

            </div>

            <div class="portlet-body flip-scroll">
                <div class="form-group">
                    <label class="col-md-3 control-label">Tahun :</label>
                    <div class="col-md-4">
                        <input type="hidden" id="isadd" name="isadd"  />
                        <input id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Pilih :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <select  id="modul" onchange='setGrid();'>
                                <option value=''>-- Pilih --</option>
                                <option value='cdpl'>1.Cek Kegiatan Duplikat</option>
                                <option value='crnc'>2.Cek Kegiatan Rinci</option>
                                <option value='cakb'>3.Cek Kegiatan AKB</option>

                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-actions fluid">
                    <div class="col-md-offset-3 col-md-9">
                        <button id="cetak"  type="button"  class="btn blue" onclick="cetakXls()"> Download Xls </button>
                    </div>
                </div>
            </div>
        </div>-->



    <div id="mygrid" class="portlet box">
        <div id="tabelRkas" class="portlet-body">
            <table id="rkastable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th rowspan="2">No</th>
                        <th rowspan="2">Tahun</th>
                        <th>NPSN</th>
                        <th>Nama Sekolah</th>
                        <th rowspan="2">NRK Pengguna Anggaran</th>
                        <th rowspan="2">NIP Pengguna Anggaran</th>
                        <th >Nama Pengguna Anggaran</th>
                        <th rowspan="2">Pangkat Pengguna Anggaran</th>
                        <th rowspan="2">NRK Pemegang Kas</th>
                        <th rowspan="2">NIP Pemegang Kas</th>
                        <th rowspan="2">Nama Pemegang Kas</th>
                        <th rowspan="2">No NPWP</th>
                        <th rowspan="2">Nama NPWP</th>
                        <th rowspan="2">Kota NPWP</th>
                        <th rowspan="2">No Rekening BOP</th>
                        <th rowspan="2">Nama Rekening BOP</th>
                        <th rowspan="2">No Rekening BOS</th>
                        <th rowspan="2">Nama Rekening BOS</th>
                        <th rowspan="2">Action</th>
                    </tr>
                    <tr>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="npsnFilter" onkeyup="cariNpsn()"  /></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="namaSekolahFilter" onkeyup="cariNamaSekolah()"  /></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="namaPAFilter" onkeyup="cariNamaPA()"  /></th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table>
        </div>
    </div>




    <!--
      <div onload="" class="portlet box red col-md-5" >


            <div class="portlet-body">
                <div class="form-group">
                    <label class="col-md-3 control-label">Record Count :</label>
                    <div class="col-md-4">

                        <input id="rcdcount" type="text" size="8" maxlength="4" readonly='true'   />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Total :</label>
                    <div class="col-md-4">

                        <input id="total" type="text" size="35" maxlength="4" readonly='true'   />
                    </div>
                </div>
            </div>
        </div>
    -->
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/admin/listrkas.js"></script>


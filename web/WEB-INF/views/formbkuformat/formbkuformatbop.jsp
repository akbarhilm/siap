<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Laporan BKU BOP Sesuai Format</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/laporanlra/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Laporan BKU BOP</div>
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran : </label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="kodesumbdana" name="kodesumbdana" value="2"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  onchange="setTriwulan()"/>
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeskpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaskpd' value="${sekolah.npsn}"  />
                        <form:hidden path="sekolah.idSkpd" id='idSkpd' value="${sekolah.idSkpd}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <c:if test="${pengguna.kodeOtoritas==1}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <c:if test="${pengguna.kodeOtoritas==0}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahbyidskpd?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Laporan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="jenislap"  onchange="setPanelAkun()">
                            <option value="0" >-- Pilih Jenis Laporan --</option>
                            <option value="1" >Form 1A : Laporan Bulanan Penerimaan dan Pengeluaran</option>
                            <option value="2" >Form 1B : Laporan Triwulan Penerimaan dan Pengeluaran</option>
                            <option value="3" >Form 1C : Laporan Triwulan Penerimaan dan Pengeluaran Pajak</option>
                            <option value="4" >Form 1D : Laporan Tahunan Penerimaan dan Pengeluaran</option>
                            <option value="5" >Form 1E : Laporan Rekapitulasi Penggunaan BOP</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group" id="labeltriwulan">
                <label class="col-md-3 control-label" >Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="triwulan" id="triwulan" >
                            <option value="1">Triwulan 1</option>
                            <option value="2">Triwulan 2</option>
                            <option value="3">Triwulan 3</option>
                            <option value="4">Triwulan 4</option>
                        </select>
                    </div>
                </div>
            </div>
            <!--
    <div class="form-group" id="labelsemester">
        <label class="col-md-3 control-label" >Semester :</label>
        <div class="col-md-4">
            <div class="input-group">
                <select name="semester" id="semester">
                    <option value="1">Semester 1</option>
                    <option value="2">Semester 2</option>
                </select>
            </div>
        </div>
    </div>
            <div class="form-group" id="bulan1">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="bulan" id="bulan"  >
                            <option value="01">Januari</option>
                            <option value="02">Februari</option>
                            <option value="03">Maret</option>
                            <option value="04">April</option>
                            <option value="05">Mei</option>
                            <option value="06">Juni</option>
                            <option value="07">Juli</option>
                            <option value="08">Agustus</option>
                            <option value="09">September</option>
                            <option value="10">Oktober</option>
                            <option value="11">November</option>
                            <option value="12">Desember</option>
                        </select>
                    </div>
                </div>
            </div>-->
            <!--
    <div class="form-group" id="labeltanggal">
        <label  class="col-md-3 control-label">Tanggal :</label>
        <div class="col-md-4">
            <div class="input-group">
                <input type="text" name="tanggal" id="tanggal" class="required date-picker2 entrytanggal valid" size="14" value=""/>
            </div>
        </div>
    </div>
    <div id="labelakunbelanja" class="form-group">
        <label class="col-md-3 control-label">Akun Belanja :</label>
        <div class="col-md-4">
            <div class="input-group">
                <select  id="akunbelanja" name="akunbelanja">
                </select>
            </div>
        </div>
    </div>
    <div id="labelkegiatan" class="form-group">
        <label class="col-md-3 control-label">Nama Kegiatan :</label>
        <div class="col-md-4">
            <div class="input-group">
                <select  id="kegiatan" name="kegiatan">
                </select>
            </div>
        </div>
    </div>-->
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" id="btncetak" class="btn blue" onclick='cetak()' href="#" > Cetak</button>
                </div>
            </div>
        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/formbkuformat/formbku.js"></script>


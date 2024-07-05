<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Laporan BKU BOS</a></li>
</ul>

<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/formbkubos/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Laporan BKU BOS</div>
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran : </label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="kodesumbdana" name="kodesumbdana" value="1"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeskpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaskpd' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <c:if test="${pengguna.kodeOtoritas==0}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahbyidskpd?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <c:if test="${pengguna.kodeOtoritas==1}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
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
                            <option value="22" >Form 22 : BKU (Tutup BKU)</option>
                            <option value="8" >Form 23 : Bank</option>
                            <option value="P1" >Form 25 : PPH PS 21</option>
                            <!--<option value="P2" >Form 25 : PPH PS 22</option> -->
                            <option value="P3" >Form 25 : PPH PS 23 Jasa I</option>
                            <!--<option value="P4" >Form 25 : PPH PS 4 Ayat 2</option>-->
                            <option value="P5" >Form 25 : PPN</option>
                            <!--<option value="P6" >Form 25 : PPH</option>-->
                            <!--<option value="7" >Form 26 : Panjar</option>-->
                            <!--<option value="27" >Form 27 : Buku Rincian Objek Belanja</option> -->
                            <!--<option value="28" >Form 28 : Register SPP/SPM/SP2D</option>-->
                            <!--<option value="48" >Form 48 : SPJ Belanja (A3)</option>-->
                            <!--<option value="49" >Form 49 : SPJ Belanja - Administratif (A3)</option>-->
                            <!--<option value="50" >Form 50 : SPJ Belanja - Fungsional (A3)</option>-->
                            <option value="PJK" >Form : Pajak (Rekap)</option>
                            <!--<option value="SK1" >Form : Laporan Saldo Kas Beban Belanja</option>-->
                            <!--<option value="TU" >Form : Laporan SKPD Belum TU</option>-->
                            <!--<option value="TB" >Form : Laporan SKPD Belum Tutup Buku</option>-->
                            <option value="KK" >Form : Laporan Kartu Kendali</option>
                            <option value="KKK" >Form : Laporan Kartu Kendali Komponen</option>
                            <option value="SPT" >Form : Surat Pernyataan Tanggung Jawab</option>
                            <!--<option value="BKUBOS" >Form : Laporan Buku Kas Umum Bos (Bulanan)</option> -->
                            <!--<option value="RD" >Form : Laporan Realisasi Dana Bos</option>
                            <option value="TJ" >Form : Laporan Surat Pernyataan Tanggung Jawab</option> -->
                            <option value="MT" >Form : Laporan Monitoring Transfer</option>
                            <option value="MT9" >Form : Laporan Monitoring Pengeluaran Triwulan</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group" id="labeltriwulan">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="triwulan" id="triwulan">
                            <option value="1">Triwulan 1</option>
                            <option value="2">Triwulan 2</option>
                            <option value="3">Triwulan 3</option>
                            <option value="4">Triwulan 4</option>
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
            </div>
            <div class="form-group" id="labelsemester">
                <label class="col-md-3 control-label">Semester :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="semester" id="semester">
                            <option value="1">Semester 1</option>
                            <option value="2">Semester 2</option>
                        </select>
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
            </div>
            <div class="form-group" id="labeltanggal">
                <label  class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tanggal" id="tanggal" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                    </div>
                </div>
            </div>
            <!--<div id="labelsurat" class="form-group">
                <label class="col-md-3 control-label">No Surat :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input id="nosurat" name="nosurat">
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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/formbku/formbkubos.js"></script>


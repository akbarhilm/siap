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
            <div id="labelnrk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NRK :</label>
                <div class="col-md-4">
                    <input name="nrkPptk" id="nrkPptk" type="text" maxlength="6"  onkeypress="return isNumber(event)" value="${pengguna.namaPengguna}" readonly="true" />
                </div>
            </div>
            <div id="labelidskpd" name="labelidskpd" class="form-group">
                <label id="textidskpd" class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-4">
                    <input name="idskpd" id="idskpd" type="text" maxlength="6" value="" readonly="true" />
                </div>
            </div>
            <div class="form-group"  id="formsudin">
                <label class="col-md-3 control-label">Jenis Laporan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="jenislap"  onchange="setPanelAkun()">
                            <option value="0" >-- Pilih Jenis Laporan --</option>
                            <option value="2" >Form 2 : Laporan Rekapitulasi Penggunaan Dana BOP Kecamatan </option>
                            <option value="3" >Form 3 : Laporan Rekapitulasi Penggunaan Dana BOP Sudin </option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group" id="formdinas">
                <label class="col-md-3 control-label">Jenis Laporan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="jenislapdinas"  onchange="setPanelAkundinas()">
                            <option value="0" >-- Pilih Jenis Laporan --</option>
                            <option value="4" >Form 4 : Laporan Rekapitulasi Penggunaan Dana BOP Dinas </option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group" id="labeltriwulan">
                <label class="col-md-3 control-label" >Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="triwulan" id="triwulan" onchange="setKegiatan()">
                            <option value="1">Triwulan 1</option>
                            <option value="2">Triwulan 2</option>
                            <option value="3">Triwulan 3</option>
                            <option value="4">Triwulan 4</option>
                        </select>
                    </div>
                </div>
            </div>
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
            </div>
            <div class="form-group" id="labeltanggal">
                <label  class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tanggal" id="tanggal" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                    </div>
                </div>
            </div>
            <div id="labelkecamatan" class="form-group">
                <label class="col-md-3 control-label">Kecamatan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="kecamatan" name="kecamatan" onchange="setSisdik()">
                        </select>
                    </div>
                </div>
            </div>
            <div id="labelNipSisdik" name="labelNipSisdik" class="form-group">
                <label id="textlabelNipSisdik" class="col-md-3 control-label">NIP SISDIK :</label>
                <div class="col-md-4">
                    <input name="nipSisdik" id="nipSisdik" type="text" value="" maxlength="18" size="18" onkeypress="return isNumber(event)"/>
                </div>
            </div>
            <div id="labelNamaSisdik" name="labelNamaSisdik" class="form-group">
                <label id="textlabelNamaSisdik" class="col-md-3 control-label">NAMA SISDIK :</label>
                <div class="col-md-4">
                    <input name="namaSisdik" id="namaSisdik" type="text" value=""  maxlength="75" size="60"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" id="btnUpdSisdik" class="btn blue" onclick='updateSisdik()' href="#" > Perbarui Data Sisdik</button>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" id="btncetak" class="btn blue" onclick='cetak()' href="#" > Cetak</button>
                </div>
            </div>
        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/formbkuformat/formbkusudin.js"></script>


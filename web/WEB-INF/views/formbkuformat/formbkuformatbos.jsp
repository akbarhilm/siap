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


<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/laporanformatbos/prosessimpan" class="form-horizontal">
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
                        <select  id="jenislap"  onchange="setPanelAkun(), setComboBulan()">
                            <option value="0" >-- Pilih Jenis Laporan --</option>
                            <option value="1" >Form K1 : Laporan Buku Kas Umum </option>
                            <option value="2" >Form K2 : Laporan Buku Pembantu Kas </option>
                            <option value="3" >Form K3 : Laporan Buku Pembantu Kas Bank </option>
                            <option value="4" >Form K4 : Laporan Buku Pembantu Pajak </option>
                            <option value="5" >Form K5 : Laporan Buku Pembantu Rincian Objek Belanja </option>
                            <option value="6" >Form K6 : Laporan Realisasi Dana BOS</option>
                            <option value="7" >Form K7 : Laporan Surat Pernyataan Tanggung Jawab</option>
                            <option value="8" >Form K7A : Laporan Rekapitulasi Realisasi Penggunaan Dana BOS</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group" id="labeltriwulan">
                <label class="col-md-3 control-label" >Triwulan :</label>
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
            <!-- <div class="form-group" id="bulan1">
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
             </div> -->
            <div class="form-group"  id="bulan1">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="bulan" id="bulan" name="bulan" onchange="cekProsesBkuBulan()" >
                            <form:options />
                        </form:select >
                        <!--<//form:errors path="bulan" cssClass="error"  />-->
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
            <div id="labelNosurat" name="labelNosurat" class="form-group">
                <label id="textlabelNosurat" class="col-md-3 control-label">NOMOR SURAT :</label>
                <div class="col-md-4">
                    <input name="nosurat" id="nosurat" type="text" value="" />
                </div>
            </div>
            <div id="labelKastunai" name="labelKastunai" class="form-group">
                <label id="textlabelKastunai" class="col-md-3 control-label">NILAI KAS TUNAI :</label>
                <div class="col-md-4">
                    <input name="kastunai" id="kastunai" type="text" value="" />
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="btnproses" type="button" class="btn blue"  onclick='simpan()' href="#" >Proses Bku</button>
                    <button type="button" id="btncetak" class="btn blue" onclick='cetak()' href="#" > Cetak</button>

                </div>
            </div>
        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/formbkuformat/formbkubos.js"></script>


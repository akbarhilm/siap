<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Laporan Monitoring BOP</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/laporanlra/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Laporan Monitoring BOP</div>
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran : </label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="kodesumbdana" name="kodesumbdana" value="2"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                    <form:hidden path="pengguna.idSkpd" id='idSkpd' value="${pengguna.idSkpd}"  />
                    <form:hidden path="pengguna.kodeOtoritas" id='kodeOtoritas' value="${pengguna.kodeOtoritas}"  />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Laporan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <!-- <select  id="jenislap"  onchange="setSaldoAwal(), setPanelAkun()"> -->
                        <select  id="jenislap"  onchange="setPanelAkun()">
                            <option value="0" >-- Pilih Jenis Laporan --</option>
                            <option value="1" >Rincian Pengajuan Pembayaran Anggaran BOP</option>
                            <option value="2" >Buku Pembantu Rincian Objek</option>
                            <option value="3" >Laporan BOP Per Triwulan</option>
                            <option value="4" >Report Rekap BOP</option>
                            <option value="5" >Report Monitoring Transaksi Belanja BOP</option>
                            <option value="6" >Report Monitoring Transfer BOP</option>
                            <option value="7" >Report Daftar Sekolah Tutup BKU</option>
                            <option value="8" >Report Surat Tanda Setoran</option>
                            <option value="9" >Report Monitoring Pengeluaran Triwulan</option>
                            <option value="10" >Report Realisasi Kegiatan SKPD-BOP</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group" id="labelsekolah">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}" onchange="setAkunBelanja(), grid()" />
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
            <!--
        <div class="form-group">
            <label class="col-md-3 control-label">SKPD :</label>
            <div class="col-md-5">
                <div class="input-group">
                    <//form:hidden path="idskpd" id='idskpd' value="${skpd.idSkpd}"  />
                    <//form:input path="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="75"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />
                    <//c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> <///c:if>
                    <//form:errors path="idskpd" class="label label-important" />
                </div>
            </div>
        </div>
            -->
            <!--
         <div id="labelwilayah" class="form-group">
             <label class="col-md-3 control-label">Wilayah :</label>
             <div class="col-md-4">
                 <div class="input-group">
                     <select name="wilayah" id="wilayah" onchange="">

                     </select>
                 </div>
             </div>
         </div>
            -->
            <div class="form-group" id="labeltriwulan">
                <label class="col-md-3 control-label" >Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <!--<select name="bulan" id="bulan" onchange="setSaldoAwal()" >-->
                        <select name="triwulan" id="triwulan">
                            <option value="1">Triwulan 1</option>
                            <option value="2">Triwulan 2</option>
                            <option value="3">Triwulan 3</option>
                            <option value="4">Triwulan 4</option>
                        </select>

                    </div>
                </div>
            </div>
            <div class="form-group" id="bulan1" >
                <label  class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="bulan" id="bulan" onchange="">
                            <option value="01">01 - Januari</option>
                            <option value="02">02 - Februari</option>
                            <option value="03">03 - Maret</option>
                            <option value="04">04 - April</option>
                            <option value="05">05 - Mei</option>
                            <option value="06">06 - Juni</option>
                            <option value="07">07 - Juli</option>
                            <option value="08">08 - Agustus</option>
                            <option value="09">09 - September</option>
                            <option value="10">10 - Oktober</option>
                            <option value="11">11 - November</option>
                            <option value="12">12 - Desember</option>
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

            <div class="form-group" id="jenjang1" >
                <label  class="col-md-3 control-label">Jenjang :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="jenjang" id="jenjang" onchange="">
                            <option value="TK">TK</option>
                            <option value="SD">SD</option>
                            <option value="MI">MI</option>
                            <option value="SMP">SMP</option>
                            <option value="MTS">MTS</option>
                            <option value="SMA">SMA</option>
                            <option value="MA">MA</option>
                            <option value="SMK">SMK</option>
                            <option value="SLB">SLB</option>
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
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" id="btncetak" class="btn blue" onclick='cetak()' href="#" > Cetak</button>
                </div>
            </div>

        </div>
    </div>

    <div class="portlet box" id="formcetaksetortablelabel">
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
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/formbku/laporanmonitoringbop.js"></script>


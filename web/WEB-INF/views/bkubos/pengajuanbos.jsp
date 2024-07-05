<%--
    Document   : pengajuanbop
    Created on : Nov 17, 2017, 4:21:30 PM
    Author     : Racka
--%>

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

    <li><a href="#">Pengajuan Pembayaran BKU - BOS</a></li>
</ul>

<form:form   method="post" commandName="pengajuanbos"  id="pengajuanbos"   action="${pageContext.request.contextPath}/" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pengajuan Pembayaran BKU - BOS</div>
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idSekolah' value="${sekolah.idSekolah}"  />
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeSkpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaSkpd' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="tutup1" name="tutup1"  />
                        <input type="hidden" id="tutup2" name="tutup2"  />
                        <input type="hidden" id="tutup3" name="tutup3"  />
                        <input type="hidden" id="tutup4" name="tutup4"  />
                        <select path="triwulan" id="triwulan" onchange="cek()">
                            <!--                            <option value="-" selected>-- Pilih --</option>
                                                        <option value="1">1</option>
                                                        <option value="2">2</option>
                                                        <option value="3">3</option>
                                                        <option value="4">4</option>-->
                        </select>
                    </div>
                </div>
            </div>
            <div id="belumPengajuan" class="form-group">
                <label class="col-md-3 control-label">Jumlah Yang Belum Diajukan :</label>
                <div class="col-md-4">
                    <input id="belum" type="text" size="6" maxlength="4" readonly='true' />
                </div>
            </div>
            <div id="sudahPengajuan" class="form-group">
                <label class="col-md-3 control-label">Jumlah Yang Sudah Diajukan :</label>
                <div class="col-md-4">
                    <input id="sudah" type="text" size="6" maxlength="4" readonly='true' />
                </div>
            </div>
            <!--            <div id="sudahApproval" class="form-group">
                            <label class="col-md-3 control-label">Jumlah Yang Sudah Disetujui :</label>
                            <div class="col-md-4">
                                <input id="approve" type="text" size="6" maxlength="4" readonly='true' />
                            </div>
                        </div>-->
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <input type="hidden" id="sudin" name="sudin"  />
                    <input type="hidden" id="approve" name="approve"  />
                    <button id="ajukan"  type="button"  class="btn blue" onclick="buttonAjukan()"> Ajukan </button>
                    <!--<button id="kirim"  type="button"  class="btn blue" onclick="prosesKirim()"> Kirim & Cetak </button>-->
                    <button id="undo"  type="button"  class="btn red" onclick="buttonBatalkan()"> Batalkan </button>
                    <a href="${pageContext.request.contextPath}/" class="btn blue" >Kembali</a>

                </div>
            </div>
        </div>
    </div>

    <div class="portlet box">
        <div id="tabelpengajuan" class="portlet-body">
            <table id="pnjtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead  >
                    <tr>
                        <th rowspan="2">No</th>
                        <th>Status</th>
                        <th rowspan="2">No Mohon</th>
                        <th>Kode Kegiatan</th>
                        <th>Nama Kegiatan</th>
                        <th>Kode Akun</th>
                        <th>Nama Akun</th>
                        <th rowspan="2">Kode Komponen</th>
                        <th rowspan="2">Nama Komponen</th>
                        <th rowspan="2">Nilai Keluar</th>
                    </tr>
                    <tr>
                        <th><input type='checkbox' onclick='checkAll(this)' name='isapproved' id='isapproved'/></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="kodeKegiatanFilter" onkeyup="cariKodeKegiatan()"  /></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="namaKegiatanFilter" onkeyup="cariNamaKegiatan()"  /></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="kodeAkunFilter" onkeyup="cariKodeAkun()"  /></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="namaAkunFilter" onkeyup="cariNamaAkun()"  /></th>
                    </tr>
                </thead>
                <tbody id="mytablebody" >
                    <tr>

                    </tr>
                </tbody>
                <tfoot id="jourtablefoot" >
                    <tr>
                        <th colspan="9" style="text-align:right">Jumlah Nilai Keluar : </th>
                        <th >
                            <input  type='text' id="totkeluar1"  name="totkeluar1" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                    </tr>

                </tfoot>
            </table>
        </div>
        <!--        <div id="tabelapproval" class="portlet-body">
                    <table id="apptable" class="table table-striped table-bordered table-condensed table-hover " >
                        <thead  >
                            <tr>
                                <th rowspan="2">No</th>
                                <th rowspan="2">No BKU Mohon</th>
                                <th>Kode Kegiatan</th>
                                <th>Nama Kegiatan</th>
                                <th>Kode Akun</th>
                                <th>Nama Akun</th>
                                <th rowspan="2">Kode Komponen</th>
                                <th rowspan="2">Nama Komponen</th>
                                <th rowspan="2">Nilai Keluar</th>
                            </tr>
                            <tr>
                                <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="kodeKegiatanFilter" onkeyup="cariKodeKegiatan()"  /></th>
                                <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="namaKegiatanFilter" onkeyup="cariNamaKegiatan()"  /></th>
                                <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="kodeAkunFilter" onkeyup="cariKodeAkun()"  /></th>
                                <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="namaAkunFilter" onkeyup="cariNamaAkun()"  /></th>
                            </tr>
                        </thead>

                        <tfoot id="jourtablefoot" >
                            <tr>
                                <th colspan="8" style="text-align:right">Jumlah Nilai Keluar : </th>
                                <th >
                                    <input  type='text' id="totkeluar2"  name="totkeluar2" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                                </th>
                            </tr>

                        </tfoot>
                    </table>
                </div>-->
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkubos/pengajuanbos.js"></script>



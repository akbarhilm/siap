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

    <li><a href="#">Persetujuan Pembayaran BKU - BOP</a></li>
</ul>

<form:form   method="post" commandName="persetujuanboplain"  id="persetujuanboplain"   action="${pageContext.request.contextPath}/" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Persetujuan Pembayaran BKU - BOP</div>
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
                        <form:hidden path="sekolah.idSekolah" id='idSekolah' value="${sekolah.idSekolah}" onchange='cek()' />
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeSkpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaSkpd' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <c:if test="${pengguna.kodeOtoritas==1}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tipe Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="trx" id="trx" onchange="getTutupBuku()">
                            <option value="-" selected>-- Pilih --</option>
                            <option value="JJ">SPJ</option>
                            <option value="P">Pajak</option>
                            <option value="JG">Jasa Giro</option>
                            <option value="ST">Setor Sisa Kas</option>
                            <option value="RT">Pendapatan Lain-Lain</option>
                        </select>
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
            <div class="form-group">
                <label class="col-md-3 control-label">Jumlah Yang Belum Disetujui :</label>
                <div class="col-md-4">
                    <input id="sudah" type="text" size="6" maxlength="4" readonly='true' />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jumlah Yang Sudah Disetujui :</label>
                <div class="col-md-4">
                    <input id="sudin" type="text" size="6" maxlength="4" readonly='true' />
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <input type="hidden" id="belum" name="belum"  />
                    <input type="hidden" id="approve" name="approve"  />
                    <button id="setujui"  type="button"  class="btn blue" onclick="buttonSetujui()"> Setujui </button>
                    <button id="undo"  type="button"  class="btn red" onclick="buttonBatalkan()"> Batalkan </button>
                    <a href="${pageContext.request.contextPath}/" class="btn blue" >Kembali</a>

                </div>
            </div>
        </div>
    </div>

    <div class="portlet box">
        <div id="tabel" class="portlet-body">
            <table id="mytable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead  >
                    <tr>
                        <th rowspan="2">No</th>
                        <th >Status</th>
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
                            <input  type='text' id="totkeluar"  name="totkeluar" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                    </tr>

                </tfoot>
            </table>
        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkubop/persetujuanboplain.js"></script>



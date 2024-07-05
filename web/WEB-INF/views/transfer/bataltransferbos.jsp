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

    <li><a href="#">Pembatalan Transfer - BOS</a></li>
</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pembatalan Transfer - BOS</div>
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="kodeOtoritas" name="kodeOtoritas" value="${pengguna.kodeOtoritas}" />
                    <input id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idSekolah' value="${sekolah.idSekolah}" onchange="setCombo()"/>
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeSkpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaSkpd' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${pengguna.kodeOtoritas==1}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
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
                <label class="col-md-3 control-label">Jenis Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="kodetransaksi" id="kodetransaksi" onchange="cek()">
                            <option value="-">-- Pilih --</option>
                            <option value="JJ">SPJ</option>
                            <!--<option value="P1">PPH PS 21</option>
                            <option value="P2">PPH PS 22</option>
                            <option value="P3">PPH PS 23 JASA I</option>
                            <option value="P5">PPN</option>-->
                            <option value="ST">SETOR SISA KAS</option>
                            <option value="RT">PENDAPATAN LAIN-LAIN</option>
                        </select>
                    </div>
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
                    <input type="hidden" id="jumlah" name="jumlah"  />
                    <input type="hidden" id="sudah" name="sudah"  />
                    <input type="hidden" id="belum" name="belum"  />
                    <button id="proses"  type="button"  class="btn blue" onclick="buttonProses()"> Button </button>
                    <!--<button id="kirim"  type="button"  class="btn blue" onclick="prosesKirim()"> Kirim & Cetak </button>-->
                    <button id="batal"  type="button"  class="btn red" onclick="buttonBatal()"> Batalkan </button>
                    <a href="${pageContext.request.contextPath}/" class="btn blue" >Kembali</a>

                </div>
            </div>
        </div>
    </div>

    <div class="portlet box">
        <div id="tabelbatal" class="portlet-body">
            <table id="btltable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead  >
                    <tr>
                        <th rowspan="2">No</th>
                        <th rowspan="2">Status</th>
                        <th rowspan="2">No Mohon</th>
                        <th>Kode Kegiatan</th>
                        <th>Nama Kegiatan</th>
                        <th rowspan="2">Nilai Keluar</th>
                        <th rowspan="2">Nilai Pajak</th>
                        <th rowspan="2">Nilai Netto</th>
                    </tr>
                    <tr>
                        <!--<th><input type='checkbox' onclick='checkAll(this)' name='isapproved' id='isapproved'/></th>-->
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="kodeKegiatanFilter" onkeyup="cariKodeKegiatan()"  /></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="namaKegiatanFilter" onkeyup="cariNamaKegiatan()"  /></th>
                    </tr>
                </thead>
                <tbody id="mytablebody" >
                    <tr>

                    </tr>
                </tbody>
                <tfoot id="jourtablefoot" >
                    <tr>
                        <th colspan="5" style="text-align:right">Jumlah Nilai : </th>
                        <th >
                            <input  type='text' id="totkeluar"  name="totkeluar" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                        <th >
                            <input  type='text' id="totpajak"  name="totpajak" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                        <th >
                            <input  type='text' id="totnetto"  name="totnetto" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                    </tr>

                </tfoot>
            </table>
        </div>
        <div id="tabelbatalpajak" class="portlet-body">
            <table id="btltablepajak" class="table table-striped table-bordered table-condensed table-hover " >
                <thead  >
                    <tr>
                        <th rowspan="2">No</th>
                        <th rowspan="2">Status</th>
                        <th rowspan="2">No Mohon Pengeluaran</th>
                        <th rowspan="2">No Mohon Penerimaan</th>
                        <th rowspan="2">No Mohon SPJ</th>
                        <th rowspan="2">No Bukti</th>
                        <th>Kode Kegiatan</th>
                        <th>Nama Kegiatan</th>
                        <th rowspan="2">Uraian</th>
                        <th rowspan="2">Nilai Keluar</th>
                    </tr>
                    <tr>
                        <!--<th><input type='checkbox' onclick='checkAll(this)' name='isapproved' id='isapproved'/></th>-->
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="kodeKegiatanPFilter" onkeyup="cariKodeKegiatan()"  /></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="namaKegiatanPFilter" onkeyup="cariNamaKegiatan()"  /></th>
                    </tr>
                </thead>
                <tbody id="mytablebodypajak" >
                    <tr>

                    </tr>
                </tbody>
                <tfoot id="jourtablefootpajak" >
                    <tr>
                        <th colspan="8" style="text-align:right">Jumlah Nilai : </th>
                        <th >
                            <input  type='text' id="totkeluarpajak"  name="totkeluarpajak" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                    </tr>

                </tfoot>
            </table>
        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/transfer/bataltransferbos.js"></script>



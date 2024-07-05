<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script>
    $(document).ready(function() {

    });
</script>

<style>
    .inputmoneyX{
        border:none;margin:0;text-align:right;width:80%;background: aliceblue;
    }
</style>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU Sekolah</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Berita Acara Pemeriksaan (BAP) Kas - BOP</a></li>
</ul>


<form:form   method="post" commandName="refsppup"  id="refsppup"  onsubmit="return simpan()" action="${pageContext.request.contextPath}/bapkasbop/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Input Berita Acara Pemerikasaan (BAP) Kas - BOP</div>
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
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
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text"  path="tglBkuBa" id="tglBkuBa"  class="required  date-picker entrytanggal2 valid"  size="14"  />

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Triwulan BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="triwulan" id="triwulan" name="triwulan" onchange="getNilaiKas()">
                            <form:options   />
                        </form:select >

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NRK PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nrkPa" cssClass="required" id="nrkPa"  size="7" maxlength="6" onKeyPress="return numbersonly(this, event)"  />

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPa" cssClass="required" id="nipPa"  size="20" maxlength="18" onKeyPress="return numbersonly(this, event)"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="namaPa" cssClass="required" id="namaPa"  size="67" maxlength="50"   />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="jabatanPa" cssClass="required" id="jabatanPa"  size="67" maxlength="50"   />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NRK Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="nrkBend" cssClass="required" id="nrkBend"  size="7" maxlength="6" onKeyPress="return numbersonly(this, event)"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NIP Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipBend" cssClass="required" id="nipBend"  size="20" maxlength="18" onKeyPress="return numbersonly(this, event)"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="namaBend" cssClass="required" id="namaBend"  size="67" maxlength="50"   />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="jabatanBend" cssClass="required" id="jabatanBend"  size="67" maxlength="50"   />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">No SK Gubernur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <!--<//form:input   path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   /> -->
                        <c:if test="${sekolah.kodeJenjang =='TK'}"  ><form:input value="Nomor 24 Tahun 2018"  path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   /> </c:if>
                        <c:if test="${sekolah.kodeJenjang =='SD'}"  ><form:input value="Nomor 24 Tahun 2018"  path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   /> </c:if>
                        <c:if test="${sekolah.kodeJenjang =='MI'}"  ><form:input value="Nomor 24 Tahun 2018"  path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   /> </c:if>
                        <c:if test="${sekolah.kodeJenjang =='SMP'}"  ><form:input value=""  path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   /> </c:if>
                        <c:if test="${sekolah.kodeJenjang =='MTS'}"  ><form:input value=""  path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   /> </c:if>
                        <c:if test="${sekolah.kodeJenjang =='SMA'}"  ><form:input value=""  path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   /> </c:if>
                        <c:if test="${sekolah.kodeJenjang =='MA'}"  ><form:input value=""  path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   /> </c:if>
                        <c:if test="${sekolah.kodeJenjang =='SMK'}"  ><form:input value="Nomor 182 Tahun 2018"  path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   /> </c:if>
                        <c:if test="${sekolah.kodeJenjang =='SLB'}"  ><form:input value="Nomor 182 Tahun 2018"  path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   /> </c:if>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Tanggal SK Gubernur:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                        <form:input  path="tglSkGub" cssClass="required" id="tglSkGub"  size="20" maxlength="18"   />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Uang Kertas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangKertas"  id="nilaiUangKertas1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()"  class="inputmoneyX" size="19" value="0" style="text-align:right" />&nbsp;&nbsp;&nbsp;(1)
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Uang Logam:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangLogam"  id="nilaiUangLogam1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" class="inputmoneyX" size="19" value="0" style="text-align:right" />&nbsp;&nbsp;&nbsp;(2)
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Belanja yang Belum Dibayarkan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSp2d"  id="nilaiUangSp2d1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" class="inputmoneyX" size="19" value="0" style="text-align:right" />&nbsp;&nbsp;&nbsp;(3)
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Saldo Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSaldoBank"  id="nilaiUangSaldoBank1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()"  class="inputmoneyX" size="19" value="0" style="text-align:right" readonly="true" />&nbsp;&nbsp;&nbsp;(4)
                        <input type='hidden'  value="${sekolah.noBOP}" id='rekeningBOP'>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Surat Berharga:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSuratBerharga"  id="nilaiUangSuratBerharga1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" class="inputmoneyX" size="19" value="0" style="text-align:right" />&nbsp;&nbsp;&nbsp;(5)
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Total BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangTotalBkuBa"   id="nilaiUangTotalBkuBa1"   class="inputmoneyX" size="9" readonly="true"  style="text-align:right" />(6=1+2+3+4+5)
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Saldo BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSaldoBkuBa"  id="nilaiUangSaldoBkuBa" readonly="true" value="0"  onkeyup="getNilaiTotalSelisihBAPKas(),cektotalbk()" size="21" style="text-align:right" /> &nbsp;(7)
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Selisih BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSelisihBkuBa"  id="nilaiUangSelisihBkuBa"  class="inputmoneyX" readonly="true" size="19" value="0" style="text-align:right" /> &nbsp;&nbsp;&nbsp; (8=6-7)
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Penjelasan Perbedaan Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:textarea  cols="70" rows="3"   path="ketBkuBa" id="ketBkuBa"  maxlength="250" />
                    </div>
                </div>
            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" type="submit" class="btn blue" >Simpan</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="${pageContext.request.contextPath}/bapkasbop/indexbapkas">Kembali</a>
                </div>
            </div>

        </div>
    </div>
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" class="btn blue" onclick='tambahRow()' >Tambah Data</button>

        </div>
    </div>
    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Rincian Berita Acara Pemerikasaan (BAP) Kas - BOP</div>
        </div>

        <div class="portlet-body">

            <input type="hidden" id="banyakrinci" name="banyakrinci"  value="banyakrinci"/>

            <table id="bapkasrincitable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Keterangan Berita Acara</th>
                        <th>Nilai Berita Acara</th>
                        <th>Pilihan</th>

                    </tr>
                </thead>

                <tbody id="bapkasrincitablebody" >
                </tbody>

                <tfoot id="bapkasrincitablefoot" >
                    <tr>
                        <th colspan="2" style="text-align:right">Total:</th>
                        <th colspan="1">
                            <input  type='text' id="totalbk"  name="totalbk" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;' disabled   />
                        </th>
                    </tr>
                </tfoot>

                <tfoot id="bapkasrincitablefoot2" >
                </tfoot>

            </table>
        </div>
    </div>


</form:form>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bapkasbop/addbapkasbop.js"></script>

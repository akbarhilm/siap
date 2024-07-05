<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

    });


</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU - BOS</a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bkubos/indexbkubos" >Buku Kas Umum - BOS</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Ubah Setoran</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Buku Kas Umum - BOS</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="idKegpop" name="idKegpop"  onchange="getKegiatan()" value="">
                    <input type="hidden" id="kodeKegpop" name="kodeKegpop"  onchange="" value="">
                    <input type="hidden" id="namaKegpop" name="namaKegpop"  onchange="" value="">
                    <input type="hidden" id="keteranganKegPop" name="keteranganKegPop"  onchange="" value="">

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
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">No BKU Mohon :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idBku" id='idBku' />
                        <form:hidden path="idRinci" id='idRinci' />
                        <form:input path="noBkuMohon" id='noBkuMohon' type="text" readonly="true"  size="16" />
                    </div>
                </div>
            </div>

            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="triwulan" id="triwulan" >
                            <form:option value="1">Triwulan I</form:option>
                            <form:option value="2">Triwulan II</form:option>
                            <form:option value="3">Triwulan III</form:option>
                            <form:option value="4">Triwulan IV</form:option>
                        </form:select>

                        <form:errors path="triwulan" class="error" />

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:select path="kodeTransaksi" id="kodeTransaksi" disabled="true" readonly="true" >
                            <form:option value="JO">PENGISIAN KAS</form:option>
                            <form:option value="JJ">SPJ + Pajak Penerimaan</form:option>
                            <form:option value="P1">PPH PS 21</form:option>
                            <form:option value="P2">PPH PS 22</form:option>
                            <form:option value="P3">PPH PS 23 JASA I</form:option>
                            <form:option value="P4">PPH PS 4 Ayat 2</form:option>
                            <form:option value="P5">PPN </form:option>
                            <form:option value="P6">PPH Pasal 26 </form:option>
                            <form:option value="ST">SETOR SISA KAS</form:option>
                            <form:option value="JG">JASA GIRO</form:option>

                        </form:select>

                        <form:errors path="kodeTransaksi" class="error" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label id="labelnobuktidok" class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <form:input path="noBukti" id="noBukti" type="text" readonly="true"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label id="labeltgldok" class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglDok" id="tglDok" disabled="true" class="required  date-picker2 entrytanggal2 valid" size="14" value="" />
                        <form:errors path="tglDok" class="error" />

                    </div>
                </div>
            </div>

            <div id="labelfileinbox" name="labelfileinbox" class="form-group">
                <label class="col-md-3 control-label">No Arsip :</label>
                <div class="col-md-4">
                    <form:input path="inboxFile" id="inboxFile" type="text"  class="required "  size="14" maxlength="10" />
                    <form:errors path="inboxFile" class="error" />
                </div>
            </div>

            <div id="labelcarabayar" name="labelcarabayar" class="form-group">
                <label class="col-md-3 control-label">Cara Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="kodePembayaran" id="kodePembayaran" onchange="">
                            <!-- <form:option value="1">1 - Tunai</form:option> -->
                            <form:option value="2">2 - Bank/Transfer/Cek</form:option>
                        </form:select>
                    </div>
                </div>
            </div>

            <div id="labelnilaijg" name="labelnilaijg" class="form-group">
                <label id="labelpengeluarantext" class="col-md-3 control-label">Nilai Setoran :</label>
                <div class="col-md-4">
                    <input name="nilaijg" id="nilaijg" type="text" onchange="setformatpengeluaran(this.value)" readonly="true"/> &nbsp <a  class="fancybox fancybox.iframe btn green" id="pilihjg"  href="${pageContext.request.contextPath}/bkubos/listsetoran?target='_top'" title="Pilih Data Setoran"  ><i class="icon-zoom-in"></i> Pilih</a>
                    <form:hidden path="nilaiMasuk" id='nilaiMasuk' />
                    <form:hidden path="nilaiKeluar" id='nilaiKeluar' />
                    <form:hidden path="noBkuRef" id='noBkuRef' />
                </div>
            </div>

            <div id="labelnrk" name="labelnrk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NRK Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="nrkPptk" id="nrkPptk" type="text"  class="required "  maxlength="6" readonly="true"/>
                    <!--<input type="button" id="btnCek" class="btn blue" onclick="getData()" value="Cek NRK">-->
                </div>
            </div>

            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NIP Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="nipPptk" id="nipPptk" type="text"  class="required "  maxlength="18" readonly="true"/>
                </div>
            </div>

            <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                <label id="textNamaPptk" class="col-md-3 control-label">Nama Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="namaPptk" id="namaPptk" type="text"  class="required " size="50" maxlength="50" readonly="true"/>
                </div>
            </div>

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <form:textarea path="uraian" id="uraian" class="required " cols="80" ROWS="3" maxlength="400" />
                </div>
            </div>


        </div>
    </div>

    <div id="mygrid" class="portlet box">


    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" >
            <button type="button" id="btnSimpan" class="btn blue" onclick='simpan()'>Simpan</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/bkubos/indexbkubos" >Kembali</a>
        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkubos/editbkubosst.js"></script>


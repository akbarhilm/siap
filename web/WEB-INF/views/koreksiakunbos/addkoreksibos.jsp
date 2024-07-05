<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {
        $('#sekolah').val($('#npsn').val() + "/" + $('#namasekolah').val());
    });


</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU - BOS</a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/koreksiakunbos/index" >Koreksi - BOS </a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Input Koreksi - BOS</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Koreksi Kode Akun - BOS</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="nilaiSpj" name="nilaiSpj"  />

                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />
                        <form:hidden path="sekolah.npsn" id='npsn' value="${sekolah.npsn}"  />
                        <form:hidden path="sekolah.namaSekolahPendek" id='namasekolah' value="${sekolah.namaSekolahPendek}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value=""  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="triwulan" id="triwulan" onchange="" >
                            <option value="-">---- Pilih ----</option>
                            <option value="1">Triwulan I</option>
                            <option value="2">Triwulan II</option>
                            <option value="3">Triwulan III</option>
                            <option value="4">Triwulan IV</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="kodeTransaksi" id="kodeTransaksi" onchange="setPanel(), setRumusPajak()">
                            <option value="JJ">SPJ (PENCATATAN BELANJA)</option>
                        </select>
                    </div>
                </div>
            </div>

            <div id="labelkegiatanpajak" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKeg"  id="keteranganKeg"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;
                    <a  class="fancybox fancybox.iframe btn green" id="pilihKegiatan" onclick="" href="${pageContext.request.contextPath}/koreksiakunbos/listpj?target='_top'" title="Pilih Kegiatan" ><i class="icon-zoom-in"></i> Pilih</a>
                    <input type="hidden" id="idKegpop" name="idKegpop"  onchange="gridspj()" value="">
                    <input type="hidden" id="kodeKegpop" name="kodeKegpop"  onchange="" value="">

                </div>
            </div>

            <div class="form-group">
                <label id="labelnobuktidok" class="col-md-3 control-label">No Mohon Referensi :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <input type="hidden" id="idBku" name="idBku"  >
                        <input name="noBkuMohon" id="noBkuMohon" type="text"  readonly="true" size="14" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label id="labelnobuktidok" class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <input name="noBuktiDok" id="noBuktiDok" type="text"  maxlength="50" size="32" />
                    </div>
                </div>
            </div>

            <div class="form-group" id="labeltgldok">
                <label id="labeltgl" class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tglDok" id="tglDok" class="required date-picker2 entrytanggal valid" size="14" value=""/>

                    </div>
                </div>
            </div>

            <div id="labelpaguakb" class="form-group">
                <label class="col-md-3 control-label">Pagu SPJ Koreksi :</label>
                <div class="col-md-4">
                    <input name="pagukoreksi" id="pagukoreksi" type="text" readonly="true" style="text-align:right" />
                </div>
            </div>

            <div id="labelnrk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NRK Pengoreksi :</label>
                <div class="col-md-4">
                    <input name="nrkPptk" id="nrkPptk" type="text" maxlength="6"  onkeypress="return isNumber(event)" value="${pengguna.namaPengguna}" readonly="true" />

                </div>
            </div>

            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NIP Pengoreksi :</label>
                <div class="col-md-4">
                    <input name="nipPptk" id="nipPptk" type="text" maxlength="18"  onkeypress="return isNumber(event)" readonly="true"/>
                </div>
            </div>

            <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                <label id="textNamaPptk" class="col-md-3 control-label">Nama Pengoreksi :</label>
                <div class="col-md-4">
                    <input name="namaPptk" id="namaPptk" type="text" size="50" maxlength="50" readonly="true"/>
                </div>
            </div>

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <TEXTAREA name="uraian" id="uraian" cols="80" ROWS="3" maxlength="100"></TEXTAREA>
                </div>
            </div>

        </div>

    </div>


    <div id="mygrid" class="portlet box">

        <div id="tabelSPJ" class="portlet-body">

            <table id="spjtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Akun</th>
                        <th>Komponen</th>
                        <th>Keterangan Rinci</th>
                        <th>Nilai Anggaran</th>
                        <th>Nilai Sebelum</th>
                        <th>Sisa Anggaran</th>
                        <th>Nilai Koreksi</th>

                    </tr>
                </thead>

                <tbody id="spjtablebody" >
                    <tr>

                    </tr>
                </tbody>

                <tfoot>
                    <tr>
                        <th colspan="7" style="text-align:right">Total:</th>

                        <th colspan="1" >
                            <input type='text' id="sumspj"   name="sumspj" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>

                    </tr>
                </tfoot>

            </table>
        </div>

    </div>

    <div class="form-actions fluid">
        <div id="divButton" class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" id="btnSimpan" class="btn blue" onclick='simpan()'>Simpan</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/koreksiakunbos/index" >Kembali</a>

        </div>
    </div>


</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/koreksiakunbos/addkoreksibos.js"></script>


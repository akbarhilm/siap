<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {
        $('#sekolah').val($('#npsn').val() + "/" + $('#namasekolah').val());
        var nilai = accounting.unformat($("#nilaiSpj").val(), ",");
        $("#nilaiSpj").val(nilai);
        $("#pagukoreksi").val(accounting.formatNumber(nilai, 2, '.', ","));
        $("#sumspj").val(nilai);
        gridspj();
    });


</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU - BOP</a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/koreksinilaispjbop/index" >Koreksi - BOP </a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Ubah Koreksi - BOP</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Koreksi Nilai Belanja - BOP</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="bukti" name="bukti" value="0" />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />
                        <form:hidden path="sekolah.namaSekolahPendek" id='namasekolah' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='npsn' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label id="labelnobuktidok" class="col-md-3 control-label">No Mohon Referensi :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <form:hidden path="idBku" id='idBku' />
                        <form:input path="noBkuRef" id='noBkuRef' type="text" readonly="true"  size="16" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No BKU Mohon :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input path="noBkuMohon" id='noBkuMohon' type="text" readonly="true"  size="16" />
                    </div>
                </div>
            </div>

            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="triwulanKoreksi" id='triwulanKoreksi' />
                        <form:select path="triwulan" id="triwulan"  disabled="true" >
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
                            <form:option value="JJ">SPJ (PENCATATAN BELANJA)</form:option>

                        </form:select>

                        <form:errors path="kodeTransaksi" class="error" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label id="labelnobuktidok" class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <form:input path="noBukti" id="noBuktiDok" type="text" maxlength="50" readonly="true"/>
                    </div>
                </div>
            </div>

            <div class="form-group" id="labeltglpost">
                <label id="labeltglpost" class="col-md-3 control-label">Tanggal Posting :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglPosting" id="tglPosting"  class="required  date-picker2 entrytanggal2 valid" size="14" value="" />

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label id="labeltgldok" class="col-md-3 control-label">Tanggal Pengajuan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglDok" id="tglDok"  class="required  date-picker2 entrytanggal2 valid" size="14" disabled="true" />
                        <form:errors path="tglDok" class="error" />

                    </div>
                </div>
            </div>

            <div id=""  class="form-group">
                <label id="" class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <form:hidden path="idKegiatan" id='idKegpop' />
                    <form:hidden path="kodeKeg" id='kodeKeg' />
                    <form:input path="ketKegiatan" id="ketKegiatan" type="text"  class="m-wrap large" size="80" readOnly="true"/>

                </div>
            </div>

            <div id="labelpaguakb" class="form-group">
                <label class="col-md-3 control-label">Pagu SPJ Koreksi :</label>
                <div class="col-md-4">
                    <form:hidden path="nilaiSpj" id='nilaiSpj' onchange="" />
                    <input name="pagukoreksi" id="pagukoreksi" type="text" readonly="true" style="text-align:right" />
                </div>
            </div>

            <div id="labelnrk" name="labelnrk" class="form-group">
                <label id="textNrkPptk" class="col-md-3 control-label">NRK Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="nrkPptk" id="nrkPptk" type="text"  class="required "  maxlength="6" onkeypress="return isNumber(event)" readonly="true"/>
                    <!--<input type="button" id="btnCek" class="btn blue" onclick="getData()" value="Cek NRK">-->
                </div>
            </div>

            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NIP Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="nipPptk" id="nipPptk" type="text"  class="required "  maxlength="18" onkeypress="return isNumber(event)" readonly="true"/>
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
                    <form:textarea path="uraianBukti" id="uraian" class="required " cols="80" ROWS="3" maxlength="100" readonly="true"/>
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
                        <th>Nilai SPJ</th>
                        <th>Nilai Pengurang</th>

                    </tr>
                </thead>

                <tbody id="spjtablebody" >
                    <tr>

                    </tr>
                </tbody>

                <tfoot>
                    <tr>
                        <th colspan="5" style="text-align:right">Total:</th>

                        <th colspan="1" >
                            <input type='text' id="sumspj"   name="sumspj" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>

                    </tr>
                </tfoot>

            </table>
        </div>

    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <a class="btn blue"  href="${pageContext.request.contextPath}/koreksinilaispjbop/index" >Kembali</a>

        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/koreksinilaispjbop/editkoreksibop.js"></script>


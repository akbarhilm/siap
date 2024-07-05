<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/babatal/editba.js"></script>

<script type="text/javascript">
    $(document).ready(function() {

    });
</script>
<form:form   method="post" commandName="formbatal"  id="formbatal"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Ubah Berita Acara Pembatalan BKU</div>

        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idBa" id='idBa' />
                        <input type="hidden" id="idsekolah" name="idsekolah"  />
                        <input type="hidden" id="npsn" name="npsn"  />
                        <input type="hidden" id="kodesumbdana" name="kodesumbdana"  />
                        <input type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  />
                    </div>
                </div>
            </div>

            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" id="triwulan" readonly="true" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No Berita Acara :</label>
                <div class="col-md-9">
                    <div class="input-group" >
                        <form:hidden path="noBa" id='noBa' />
                        <input name="noBeritaAcaraFormatted" id="noBeritaAcaraFormatted" type="text"  maxlength="50" size="32" readonly="true" />
                    </div>
                </div>
            </div>
            <div class="form-group" >
                <label  class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="tanggal" onchange="setNoBa()" type="text" name="tanggal" id="tanggal" class="required date-picker entrytanggal valid" size="14"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="kodetransaksi" name="kodetransaksi"  />
                        <input type="text" id="transaksi" readonly="true" />
                    </div>
                </div>
            </div>

            <div  class="form-group">
                <label class="col-md-3 control-label">No Mohon :</label>
                <div class="col-md-4">
                    <input type="text" id="nomohon" readonly="true" />
                </div>
            </div>
            <div  class="form-group">
                <label class="col-md-3 control-label">Nilai Yang Dibatalkan :</label>
                <div class="col-md-4">
                    <input type="hidden" id="nilaiparent" />
                    <form:input path="nilai" type="text" id="nilai" onchange="setformatpengeluaran(this.value)"  onkeypress="return isNumber(event)" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <form:textarea path="uraian" class="required" name="uraian" id="uraian" cols="80" ROWS="3" maxlength="400"/>
                </div>
            </div>
            <div id="divButton" class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9" align="Right">
                    <button type="button" id="btnSimpan" class="btn blue" onclick='check()'>Simpan</button>
                    <button type="button" id="btnCetak" class="btn blue" onclick='cetak()'>Cetak</button>
                </div>
            </div>
        </div>
    </div>
</form:form>


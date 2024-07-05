<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

    });


</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU Sekolah</a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bkuproses/indexbkuproses" >Batas Waktu Pengajuan Belanja Sekolah </a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Edit Batas Waktu Pengajuan Belanja Sekolah</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Batas Waktu Pengajuan Belanja Sekolah</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="id" id='id' />
                        <form:hidden path="idskpd" id='idskpd' value="${pengguna.idSkpd}"  />
                        <input name="skpd" id="skpd" type="text" size="80" readonly='true' />

                    </div>
                </div>
            </div>

            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="triwulan" id="triwulan"  >
                            <form:option value="1">Triwulan I</form:option>
                            <form:option value="2">Triwulan II</form:option>
                            <form:option value="3">Triwulan III</form:option>
                            <form:option value="4">Triwulan IV</form:option>
                        </form:select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sumber Dana :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="kodeSumbdana" id="kodeSumbdana"  >
                            <form:option value="1">BOS</form:option>
                            <form:option value="2">BOP</form:option>
                        </form:select>
                        <form:errors path="kodeSumbdana" class="error" />
                    </div>
                </div>
            </div>
            
            <div id=""  class="form-group">
                <label class="col-md-3 control-label">Batas Waktu (Tanggal-Bulan):</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="batasWaktu" id='batasWaktu' />
                        <input name="tanggal" id="tanggal" type="text" size="3" maxlength="2" onkeypress="return isNumber(event)" onchange="setmaxtgl(this.value)" />
                        <select name="bulan" id="bulan" >
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
            </div>

        </div>

    </div>

    <div class="form-actions fluid">
        <div id="divButton" class="col-md-offset-3 col-md-9" >
            <button type="button" id="btnSimpan" class="btn blue" onclick='cekSimpan()'>Simpan</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/bkuproses/indexbkuproses" >Kembali</a>

        </div>
    </div>


</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkuproses/editbkuproses.js"></script>


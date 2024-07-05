<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

    });


</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Beranda</a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/danatalangan/indexdanatalangan" >Pagu Talangan </a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Input Pagu Talangan</a></li>

</ul>

<form:form   method="post" commandName="reftalangan"  id="reftalangan"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pagu Talangan</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />


                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="hidden" id="kodesumbdana" name="kodesumbdana" value="0"  />
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}" onchange="getMcb()" />
                        <form:hidden path="sekolah.idSkpd" id='idskpd' value="${sekolah.idSkpd}"  />
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeskpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaskpd' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${pengguna.kodeOtoritas==0}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahbyidskpd?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>
            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="triwulan" id="triwulan" onchange="" >
                            <option value="1">Triwulan I</option>
                            <option value="2">Triwulan II</option>
                            <option value="3">Triwulan III</option>
                            <option value="4">Triwulan IV</option>
                        </select>

                    </div>
                </div>
            </div>
            <!--            <div class="form-group" id="labeltgl">
                            <label id="labeltgl" class="col-md-3 control-label">Tanggal Mohon :</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input type="text" name="tglDok" id="tglDok" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                                </div>
                            </div>
                        </div>-->
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan Tagihan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="bulan" id="bulan">
                            <option value="01">Januari</option>
                            <option value="02">Februari</option>
                            <option value="03">Maret</option>
                            <option value="04">April</option>
                            <option value="05">Mei</option>
                            <option value="06">Juni</option>
                            <option value="07">Juli</option>
                            <option value="08">Agustus</option>
                            <option value="09">September </option>
                            <option value="10">Oktober</option>
                            <option value="11">November</option>
                            <option value="12">Desember</option>

                        </select>
                    </div>
                </div>
            </div>

            <div id="labeljenis" name="labeljenis" class="form-group">
                <label class="col-md-3 control-label">Jenis :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="jenis" id="jenis" onchange="" >
                            <option value="2">BOP</option>
                            <option value="1">BOS</option>
                        </select>
                    </div>
                </div>
            </div>

            <div id="labelnilaibku" name="labelnilaibku" class="form-group">
                <label id="labelnilaibkutext" class="col-md-3 control-label">Nilai :</label>
                <div class="col-md-4">
                    <input name="nilai" id="nilai" type="text" onchange="setformatpengeluaran(this.value)"  onkeypress="return isNumber(event)" />
                </div>
            </div>
            <div id="labelnilaibku" name="labelnilaibku" class="form-group">
                <label id="labelnilaibkutext" class="col-md-3 control-label">MCB :</label>
                <div class="col-md-4">
                    <select name="cbmcb" id="cbmcb" onchange="" >
                    </select>
                </div>
            </div>
            <div id="labelnrk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NRK Bendahara :</label>
                <div class="col-md-4">
                    <input name="nrkPptk" id="nrkPptk" type="text" maxlength="6"  onkeypress="return isNumber(event)" value="${pengguna.namaPengguna}" readonly="true" />
                    <!--<input type="button" id="btnCek" class="btn blue" onclick="getData()" value="Cek NRK">-->

                </div>
            </div>


            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NIP Bendahara :</label>
                <div class="col-md-4">
                    <input name="nipPptk" id="nipPptk" type="text" maxlength="18"  onkeypress="return isNumber(event)" readonly="true" />
                </div>
            </div>

            <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                <label id="textNamaPptk" class="col-md-3 control-label">Nama Bendahara :</label>
                <div class="col-md-4">
                    <input name="namaPptk" id="namaPptk" type="text" size="50" maxlength="50" readonly="true"/>
                </div>
            </div>

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <TEXTAREA name="uraian" id="uraian" cols="80" ROWS="3" maxlength="400"></TEXTAREA>
                </div>
            </div>

        </div>

    </div>

    <div class="form-actions fluid">
        <div id="divButton" class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" id="btnSimpan" class="btn blue" onclick='simpan()'>Simpan</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/danatalangan/indexdanatalangan" >Kembali</a>

        </div>
    </div>


</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/talangan/addtalangan.js"></script>


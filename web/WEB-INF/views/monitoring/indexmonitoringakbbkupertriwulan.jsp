<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">

    function idSekolahChange() {
        //if($('#idSekolah').val() != '' && $('#triwulan').val() != '')
        if ($('#idSekolah').val() != '')
            gridmon();
    }

    function triwulanChange() {
        //if($('#idSekolah').val() != '' && $('#triwulan').val() != ''){
        if ($('#idSekolah').val() != '') {
            gridmon();
            var tw = 'X';
            var triwulan = $('#triwulan').val();
            if (triwulan == '1')
                tw = 'I';
            else if (triwulan == '2')
                tw = 'II';
            else if (triwulan == '3')
                tw = 'III';
            else if (triwulan == '4')
                tw = 'IV';
            $('#idakbtwx').html(tw);
            $('#idbkutwx').html(tw);
        }
    }

</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Monitoring AKB - BKU Pengajuan Per Triwulan</a></li>
</ul>

<form:form   method="post" commandName="refakbbkutriwulan"  id="refakbbkutriwulan"   action="" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Monitoring AKB - BKU Pengajuan Per Triwulan</div>
            <div class="actions">
                <%--<a onclick="" href="${pageContext.request.contextPath}/bkubop/addbkubop"  class="btn dark"  ><i class="icon-plus"></i> Tambah</a> --%>
            </div>
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-4">
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-6">
                    <div class="input-group">
                        <input type="hidden" id="kodesumbdana" name="kodesumbdana" value="2"  />
                        <form:hidden path="sekolah.idSekolah" id='idSekolah' value="${sekolah.idSekolah}" onchange="idSekolahChange()"  />
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeskpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaskpd' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  " />

                        <c:if test="${pengguna.kodeOtoritas==0}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahbyidskpd?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="triwulan"  onchange="triwulanChange()"  >
                            <option value="0">--- Pilih ---</option>
                            <option value="1">Triwulan I</option>
                            <option value="2">Triwulan II</option>
                            <option value="3">Triwulan III</option>
                            <option value="4">Triwulan IV</option>
                        </form:select>
                    </div>
                </div>
            </div>

            <!--            <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" id="btncetak" class="btn blue" onclick='cetak()' href="#" >Cetak</button>
                            </div>
                        </div>   -->

        </div>
    </div>

    <div class="portlet box">
        <div class="portlet-body">
            <table id="montriwlnjourtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead  >
                    <tr>
                        <th>&nbsp;</th>
                        <th><input type="text"  style="border:none;margin:0;width:98%;" id="kodeKegiatanFilter"  onkeyup="cariKodeKegiatan()" /></th>
                        <th><input type="text"  style="border:none;margin:0;width:98%;" id="namaKegiatanFilter"  onkeyup="cariNamaKegiatan()" /></th>
                        <th><input type="text"  style="border:none;margin:0;width:98%;" id="kodeAkunFilter"  onkeyup="cariKodeAkun()" /></th>
                        <th><input type="text"  style="border:none;margin:0;width:98%;" id="namaAkunFilter"  onkeyup="cariNamaAkun()" /></th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <th>
                            <form:select path="selisih" style="border:none;margin:0;width:100%;" id="selisihFilter" onchange="cariSelisih()"  >
                    <option value="" selected="true">--- Pilih ---</option>
                    <option value="-1">Selisih < 0</option>
                    <option value="0">Selisih = 0</option>
                    <option value="1">Selisih > 0</option>
                </form:select>
                </th>
                <th>&nbsp;</th>
                </tr>
                <tr>
                    <th>No</th>
                    <th>Kode Kegiatan</th>
                    <th>Nama Kegiatan</th>
                    <th>Kode Akun</th>
                    <th>Nama Akun</th>
                    <th>AKB TW <label id="idakbtwx"><strong>X</strong></label></th>
                    <th>Realisasi BKU TW <label id="idbkutwx"><strong>X</strong></label></th>
                    <th>Selisih</th>
                    <th>Tanda</th>
                </tr>
                </thead>
                <%--
                <tfoot id="jourtablefoot" >
                    <tr>
                        <th colspan="6" style="text-align:right">Jumlah : </th>

                        <th colspan="1">
                            <input  type='text' id="totmasuk"  name="totmasuk" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                        <th colspan="1" >
                            <input type='text' id="totkeluar"   name="totkeluar" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                        <th colspan="1">
                            <input  type='text' id="totsaldokas"  name="totsaldokas" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>

                    </tr>

                </tfoot>
                --%>
            </table>

        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/monitoring/monitoringakbbkupertriwulan.js"></script>


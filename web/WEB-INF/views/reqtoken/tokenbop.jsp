
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

    });


</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Permohonan Token</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Permohonan Token</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input  id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <input  type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <input  type="hidden" id="nrk" readonly="true" class="m-wrap large" size="75"  value="${pengguna.namaPengguna}"/>
                        <input  type="hidden" id="idsekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.idSekolah}" onchange="setGrid()"/>
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <c:if test="${pengguna.kodeOtoritas==1}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Triwulan :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <select  id="triwulan" onchange='setGrid();'>
                                <option value='0'>-- Pilih --</option>
                                <option value='1'>Triwulan I</option>
                                <option value='2'>Triwulan II</option>
                                <option value='3'>Triwulan III</option>
                                <option value='4'>Triwulan IV</option>

                            </select>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="portlet box">
            <div class="portlet-body">
                <table id="tokentable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead  >
                        <tr>
                            <th>No</th>
                            <th>No Mohon</th>
                            <th>Nama Rekan</th>
                            <th>Kegiatan</th>
                            <th>Uraian</th>
                            <th>Nilai</th>
                            <th>Nilai Netto</th>
                            <th>Token</th>
                            <th>Permohonan</th>
                        </tr>
                    </thead>
                    <tbody id="mytablebody" >
                        <tr>

                        </tr>
                    </tbody>
                </table>

            </div>
        </div>

        <div class="form-actions fluid">
            <div id="divButton" class="col-md-offset-3 col-md-9" align="Right">
                <button type="button" id="btnSimpan" class="btn blue" onclick='simpan()'>Simpan</button>
                <a class="btn blue"  href="${pageContext.request.contextPath}/" >Kembali</a>

        </div>
    </div>



</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/reqtoken/tokenbop.js"></script>


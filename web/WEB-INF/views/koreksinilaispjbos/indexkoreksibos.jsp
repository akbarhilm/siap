<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Koreksi Nilai Belanja - BOS</a></li>
</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="" class="form-horizontal">

    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Koreksi Nilai Belanja - BOS</div>
            <div class="actions">
                <a onclick="cekSekolah()" href="#" id="btnTambah" class="btn dark"  ><i class="icon-plus"></i> Tambah</a>
            </div>

        </div>
        <div class="portlet-body flip-scroll">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-3 control-label">Tahun:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  readonly="true" class="m-wrap medium" />
                            <input type="hidden" name="idpengguna" id="idpengguna" value="${pengguna.idPengguna}"/>
                            <input type="hidden" name="nrkpengguna" id="nrkpengguna" value="${pengguna.namaPengguna}"/>
                            <input type="hidden" id="idskpd" name="idskpd" value="${pengguna.idSkpd}"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Sekolah :</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}" name="idsekolah" onchange='setTw()' />
                            <form:hidden path="sekolah.npsn" id='npsn' value="${sekolah.npsn}"  />
                            <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="65"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  " onchange=''  /> &nbsp;
                            <c:if test="${pengguna.kodeOtoritas==0}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahbyidskpd?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                            <c:if test="${pengguna.kodeOtoritas==1}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                            <form:errors path="sekolah.idSekolah" class="label label-important" />
                        </div>
                    </div>
                </div>

                <div id="labeltriwulan" class="form-group">
                    <label class="col-md-3 control-label">Triwulan :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <select name="triwulan" id="triwulan" onchange="grid()" >
                                <option value="-">---- Pilih ----</option>
                                <option value="1">Triwulan I</option>
                                <option value="2">Triwulan II</option>
                                <option value="3">Triwulan III</option>
                                <option value="4">Triwulan IV</option>
                            </select>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>

    <div class="portlet box">
        <form id="formapprovesetortable">
            <div class="portlet-title">

            </div>
            <div class="portlet-body">
                <table id="bkutable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr  >
                            <th>No</th>
                            <th>No Mohon</th>
                            <th>Tanggal</th>
                            <th>No Bukti</th>
                            <th>Uraian</th>
                            <th>Nilai</th>
                            <th>Pilihan</th>
                        </tr>
                    </thead>
                    <tbody id="bkutablebody" >
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</form:form>




<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/koreksinilaispjbos/indexkoreksibos.js"></script>

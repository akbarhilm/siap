<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">

    function idSekolahChange() {
        if ($('#idSekolah').val() != '' && $('#tglPosting').val() != '')
            gridmon();
    }

    function tanggalChange() {
        if ($('#idSekolah').val() != '' && $('#tglPosting').val() != '')
            gridmon();
    }

</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Monitoring Harian BKU - BOP</a></li>
</ul>

<form:form   method="post" commandName="refmonbkubop"  id="refmonbkubop"   action="" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Monitoring Harian BKU - BOP</div>
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
                <label class="col-md-3 control-label">Tanggal Posting :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="14" value=""  onchange="tanggalChange()" />
                    </div>

                </div>
            </div>


            <!--            <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" id="btncetak" class="btn blue" onclick='cetak()' href="#" >Cetak</button>
                            </div>
                        </div>-->


        </div>
    </div>

    <div class="portlet box">
        <div class="portlet-body">
            <table id="monjourtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead  >
                    <tr>
                        <th>No</th>
                        <th>No BKU</th>
                        <th>Transaksi</th>
                        <th>No Bukti</th>
                        <th>Tgl Bukti</th>
                        <th>Keterangan</th>
                        <th>Terima</th>
                        <th>Keluar</th>
                        <th>Saldo</th>
                    </tr>
                </thead>
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
            </table>

        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/monitoring/monitoringbkubopharian.js"></script>
<%--<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkubop/indexbkubop.js"></script>--%>


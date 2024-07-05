<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">


</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Monitoring BKU - BOS</a></li>
</ul>

<form:form   method="post" commandName="refmonbkubos"  id="refmonbkubos"   action="" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Monitoring BKU - BOS</div>
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
                        <form:hidden path="sekolah.idSekolah" id='idSekolah' value="${sekolah.idSekolah}"  />
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeskpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaskpd' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  " />

                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>
            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="triwulan" id="triwulan" onchange="check()">
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
                <label class="col-md-3 control-label">Saldo Kas :</label>
                <div class="col-md-4">
                    <input name="saldokas" id="saldokas" type="text" readonly='true'style="text-align:right" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Total Pembayaran :</label>
                <div class="col-md-4">
                    <input name="totbayar" id="totbayar" type="text" readonly='true' style="text-align:right"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Sisa Kas :</label>
                <div class="col-md-4">
                    <input name="sisakas" id="sisakas" type="text" readonly='true' style="text-align:right"/>
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
                        <th>Tanggal</th>
                        <th>No Mohon</th>
                        <th>No Bukti</th>
                        <th>Akun</th>
                        <th>Uraian</th>
                        <th>Kegiatan</th>
                        <th>Pengeluaran</th>
                    </tr>
                </thead>
                <tfoot id="jourtablefoot" >
                    <tr>
                        <th colspan="7" style="text-align:right">Jumlah : </th>

                        <th colspan="1" >
                            <input type='text' id="totkeluar"   name="totkeluar" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>

                    </tr>

                </tfoot>
            </table>

        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/monitoring/monitoringbkubostransfer.js"></script>
<%--<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkubop/indexbkubop.js"></script>--%>


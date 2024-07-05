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

    <li><a href="#">Pagu Talangan</a></li>
</ul>

<form:form   method="post" commandName="reftalangan"  id="reftalangan"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title"> <!-- cogs-->
            <div class="caption"><i class="icon-cogs"></i>Monitoring Pagu Talangan</div>
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="npsn" name="npsn" value="${sekolah.npsn}" />
                    <input type="hidden" id="kodeotoritas" name="kodeotoritas" value="${pengguna.kodeOtoritas}" />
                    <form:hidden path="sekolah.idSkpd" id='idskpd' value="${sekolah.idSkpd}"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>
            <div id="labeljenis" class="form-group">
                <label class="col-md-3 control-label">Jenis :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="jenis" id="jenis" onchange="check()" >
                            <option value="1">BOP</option>
                            <option value="2">BOS</option>
                        </select>

                    </div>
                </div>
            </div>
            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="max" name="max"  />
                        <select name="triwulan" id="triwulan" onchange="setGrid()" >
                            <option value="1">Triwulan I</option>
                            <option value="2">Triwulan II</option>
                            <option value="3">Triwulan III</option>
                            <option value="4">Triwulan IV</option>
                        </select>

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Total Nilai :</label>
                <div class="col-md-4">
                    <input name="totaldana" id="totaldana" type="text" readonly='true' />
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="unduh"  type="button"  class="btn blue" onclick="cetak()"> Cetak </button>

                </div>
            </div>
        </div>
    </div>

    <div class="portlet box">
        <div class="portlet-body">
            <table id="jourtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead  >
                    <tr>
                        <th>No</th>
                        <th>Tanggal Terima</th>
                        <th>Sekolah</th>
                        <th>ID MCB</th>
                        <th>Nama MCB</th>
                        <th>Bulan Tagihan</th>
                        <th>Tanggal Bayar</th>
                        <th>Kode Transfer</th>
                        <th>Nilai</th>
                    </tr>
                </thead>

                <tfoot id="jourtablefoot" >
                    <tr>
                        <th colspan="8" style="text-align:right">Jumlah sampai periode ini : </th>
                        <th colspan="1">
                            <input  type='text' id="totdana"  name="totdana" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>

                    </tr>

                </tfoot>

            </table>

        </div>
    </div>

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/talangan/monitoringtalangan.js"></script>


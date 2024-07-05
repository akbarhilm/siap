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

    <li><a href="#">Buku Kas Umum Sekolah - BOP</a></li>
</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title"> <!-- cogs-->
            <div class="caption"><i class="icon-cogs"></i>Entry Buku Kas Umum Sekolah - BOP</div>
            <div class="actions">
                <a onclick="" href="${pageContext.request.contextPath}/bkubop/addbkubop"  class="btn dark"  ><i class="icon-plus"></i> Tambah</a>
            </div>
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <input type="hidden" id="kodesumbdana" name="kodesumbdana" value="2"  />
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}" onchange="getMaxTriwulan()" />
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeskpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaskpd' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${pengguna.kodeOtoritas==0}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahbyidskpd?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="triwulan" id="triwulan" onchange='setGrid()'>
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
                <label class="col-md-3 control-label">Pilihan BKU :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="cbbku" onchange='setGrid();'>
                            <option value='1'>Semua BKU</option>
                            <option value='2'>Sudah Transfer</option>
                            <option value='3'>Belum Disetujui</option>

                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Saldo Kas :</label>
                <div class="col-md-4">
                    <input name="saldokas" id="saldokas" type="text" readonly='true' />
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
                        <th>Tanggal</th>
                        <th>No Mohon</th>
                        <th>No Bukti</th>
                        <th>Akun</th>
                        <th>Uraian</th>
                        <th>Kegiatan</th>
                        <th>Penerimaan</th>
                        <th>Pengeluaran</th>
                        <th>Nilai Saldo</th>
                        <th></th>
                        <th>Edit</th>
                    </tr>
                </thead>

                <tfoot id="jourtablefoot" >
                    <tr>
                        <th colspan="7" style="text-align:right">Jumlah sampai periode ini : </th>

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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkubop/sudinbkubop.js"></script>


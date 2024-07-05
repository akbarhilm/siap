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

    <li><a href="#">Buku Kas Umum Pengeluaran Sekolah</a></li>
</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Entry Buku Kas Umum Pengeluaran Sekolah</div>   
            <div class="actions">
                <a onclick="" href="${pageContext.request.contextPath}/bku/addbku"  class="btn dark"  ><i class="icon-plus"></i> Tambah</a> 
            </div>
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeskpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaskpd' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>   

            <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="tglHide" class="required date-picker2 entrytanggal " value=""/>
                        <select path="bulan" id="bulan" onchange="gridbku()">
                            <option value="01" selected>01 - Januari</option> 
                            <option value="02">02 - Februari</option>
                            <option value="03">03 - Maret</option>
                            <option value="04">04 - April</option>
                            <option value="05">05 - Mei</option>
                            <option value="06">06 - Juni</option>
                            <option value="07">07 - Juli</option>
                            <option value="08">08 - Agustus</option>
                            <option value="09">09 - September</option>
                            <option value="10">10 - Oktober</option>
                            <option value="11">11 - November</option>
                            <option value="12">12 - Desember</option>
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
                        <th>No BKU</th>
                        <th>No Bukti</th>
                        <th>Akun</th>
                        <th>Uraian</th>
                        <th>Kegiatan</th>
                        <th>Penerimaan</th> 
                        <th>Pengeluaran</th> 
                        <th>Saldo</th> 
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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bku/indexbku.js"></script>  


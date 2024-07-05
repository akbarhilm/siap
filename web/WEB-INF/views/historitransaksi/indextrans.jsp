<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/historitransaksi/histori.js"></script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#"  >Histori Transaksi</a>
    </li>
</ul>
<form:form   method="post" commandName="progcmd"  id="progcmd"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">

    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Transaksi BOS dan BOP</div>
        </div>
        <div class="portlet-body">
            <div class="form-horizontal" >
                <div class="form-group">
                    <label class="col-md-3 control-label">NPSN :</label>
                    <div class="col-md-5">
                        <div class="input-group">

                            <form:input path="npsn" readonly="true"  id='npsn' size="8" />

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Sekolah :</label>
                    <div class="col-md-5">
                        <div class="input-group">

                            <form:hidden path="idSekolah"  id='idSekolah'/>
                            <form:input path="namaSekolahPendek" id='namaSekolah' readonly="true"  size="60" />
                            <c:if test="${plt>1}"><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih </a></c:if>
                            <c:if test="${pengguna.kodeOtoritas==0}"><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahbyidskpd?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih </a></c:if>
                        </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Rekening :</label>
                        <div class="col-md-4">
                            <div class="input-group">
                            <c:if test="${rek=='bop'}"> <form:input path="noBOP"  readonly="true" id="rekeningBOP"/><input type="hidden" id="tiperek" value="BOP" /></c:if>
                            <c:if test="${rek=='bos'}"> <form:input path="noBOS"  readonly="true" id="rekeningBOS"/><input type="hidden" id="tiperek" value="BOS" /></c:if>




                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama Rekening :</label>
                        <div class="col-md-4">
                            <div class="input-group">
                            <c:if test="${rek=='bop'}"> <form:input path="namaBOP"  readonly="true" id="namaBOP"/></c:if>
                            <c:if test="${rek=='bos'}"> <form:input path="namaBOS"  readonly="true" id="namaBOS"/></c:if>




                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Saldo Akhir :</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <input type="text"   readonly="true" id="saldoakhir" size="25"/>



                            </div>
                        </div>
                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <a hre="#" id="tmp" class='btn blue' onclick="getRekKor()">Tampil</a>
                            <a href="#" class="btn dark" onclick='cetak()'>Cetak</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
    <div class="portlet box">

        <div >
            <!--        <div style="float: right;margin-bottom: 10px;">

               &nbsp;&nbsp;<button type="button"class="btn btn-defaul blue" onclick='cetak()'>Cetak</button>

           </div>-->
            <table id="usertable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Tanggal Transaksi</th>
                        <th>Jam Transaksi</th>
                        <th>Keterangan</th>
                        <th>Terima</th>
                        <th>Keluar</th>
                        <th>Ditampilkan Pada</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
                <tfoot>
                    <tr>
                        <th colspan="4" style="text-align:right">Total:</th>

                        <th >
                            <input type='text' id="sumterima"   name="sumterima" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>

                        <th >
                            <input type='text' id="sumkeluar"   name="sumkeluar" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                    </tr>


                </tfoot>
            </table>
        </div>

        <!--                <div>
                            <table  class="table table-striped table-bordered table-condensed table-hover " >
                              <tbody>

                              </tbody>
                            </table>

                    </div>-->
    </div>
</form:form>

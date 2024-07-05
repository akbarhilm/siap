<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/setor/indexsetor" >Daftar Setor</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Setor</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-warning'":""}   >${pesan}</div>
<form:form  method="post" commandName="formsetor"  id="formsetor"   action="${pageContext.request.contextPath}/setor/simpanubahsetor" class="form-horizontal">
    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form Setor</div>
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Setor :</label>
                <div class="col-md-4">
                    <form:input path="tahunSetor" id="tahunsetor" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />
                        <input type="text"  name="sekolah"  id="sekolah" readonly="true" class="m-wrap large" size="90"  value="${sekolah.npsn} / ${sekolah.namaSekolah}  "  />
                        <form:errors path="idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="textkodetriwulan"  id="textkodetriwulan" readonly="true" class="m-wrap large" size="20"   />
                        <form:hidden path="kodeTriwulan" id='kodetriwulan' />
                        <form:errors path="kodeTriwulan" class="error" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="textkodetransaksi"  id="textkodetransaksi" readonly="true" class="m-wrap large" size="30"   />
                        <form:hidden path="kodeTransaksi" id='kodetransaksi' />
                        <form:hidden path="kodeSumbdana" id='kodeSumbdana' />
                        <form:errors path="kodeTransaksi" class="error" />
                    </div>
                </div>
            </div>

            <div id="nosetorpane" class="form-group">
                <label class="col-md-3 control-label">Nomor Setor :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text"  path="noSetor" id="nosetor" readonly="true" class="required m-wrap large" size="5" />
                        <form:errors path="noSetor" class="error" />
                        <form:hidden path="idSetor" id='idsetor'  />
                    </div>
                </div>
            </div>

            <div id="tglsetorpane" class="form-group">
                <label class="col-md-3 control-label">Tanggal Setor :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text"  path="tglSetor" id="tglsetor"    class="required date-picker2 entrytanggal2 valid" size="14" value=""/>
                        <form:errors path="tglSetor" class="error" />
                    </div>
                </div>
            </div>

            <div id="nilaisetorpane" class="form-group">
                <label class="col-md-3 control-label">Nilai Kas Masuk (1) :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text"  id="nilaikas"  readonly="true" class="required m-wrap large" style="text-align:right" size="15"/>
                    </div>
                </div>
            </div>

            <div id="nilaisetorpane" class="form-group">
                <label class="col-md-3 control-label">Nilai Total Realisasi (2) :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text"  id="nilaitotalreal"  readonly="true" class="required m-wrap large" style="text-align:right" size="15"/>
                    </div>
                </div>
            </div>

            <div id="nilaisetorpane" class="form-group">
                <label class="col-md-3 control-label">Nilai Setor Sebelumnya (3) :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text"  id="nilaisetorsebelum"  readonly="true" class="required m-wrap large" style="text-align:right" size="15"/>
                    </div>
                </div>
            </div>

            <div id="nilaisetorpane" class="form-group">
                <label class="col-md-3 control-label">Nilai Sisa (4)=(1)-(2)-(3) :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text"  id="nilaisisa"  readonly="true" class="required m-wrap large" style="text-align:right" size="15"/>
                    </div>
                </div>
            </div>

            <div id="nilaisetorpane" class="form-group">
                <label class="col-md-3 control-label">Nilai Setor :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text"  path="nilaiSetor" id="nilaisetor" onchange="cekNilaiST()" readonly="false" class="required m-wrap large ruleA03 ruleNilai0" style="text-align:right" size="15"/>
                        <form:errors path="nilaiSetor" class="error" />
                    </div>
                </div>
            </div>

            <div id="uraianpane" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:textarea path="uraian" id="uraian" class="required" cols="50" rows="3" maxlength="150" readonly="false" />
                    </div>
                </div>
            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Ubah </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/setor/indexsetor" >Kembali</a>
                </div>
            </div>

        </div>
    </div>

    <div id="panelrincireal" class="portlet">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Rincian Realisasi</div>
        </div>
        <div>
            <table id="rincirealtabel" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Kode Akun</th>
                        <th>Nama Akun</th>
                        <th>Nilai RKAS</th>
                        <th>Nilai Realisasi</th>
                        <th>Selisih</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
                <tfoot id="jourtablefoot" >
                    <tr>
                        <th colspan="3" style="text-align:right">Jumlah : </th>
                        <th >
                            <input  type='text' id="totmohon"  name="totmohon" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                        <th >
                            <input  type='text' id="totreal"  name="totreal" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                        <th >
                            <input  type='text' id="totselisih"  name="totselisih" readonly="true" style='border:none;margin:0;width:90%;text-align: right'    />
                        </th>
                    </tr>

                </tfoot>
            </table>
        </div>
    </div>
</form:form>


<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/setor/ubahsetorst.js"></script>

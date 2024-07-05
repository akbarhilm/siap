<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">

    function alertUbah() {
        alert("Data Rkas Berhasil Diubah");
    }

    function alertError() {
        alert("Data gagal diubah");
    }

</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Edit Rkas</a></li>
</ul>

<%--<form:form   method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/" class="form-horizontal" enctype="multipart/form-data">--%>
<form:form   method="post" commandName="progcmd"  id="spdBTLMasterform" onsubmit="return cek()"  action="${pageContext.request.contextPath}/admin/indexrkas/prosesupdate" class="form-horizontal" enctype="multipart/form-data">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Edit Rkas</div>
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-4">
                    <form:input path='tahun' name="tahun" id="idSkpd" type="text" readonly='true' />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NPSN :</label>
                <div class="col-md-4">
                    <form:input path='npsn' name="npsn" id="idSkpd" type="text" readonly='true' />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="id" id='id'/>
                        <form:hidden path="idSekolah" id='idsekolah'/>
                        <form:input path="namaSekolah" type="text" id="namaSekolah" readonly="true"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK Pengguna Anggaran:</label>
                <div class="col-md-4">
                    <form:input path="nrkPA" id="nrkPA" type="text" />
                    <input type="button" id="btnCek1" class="btn blue" onclick="getData(1)" value="Cek NRK">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pengguna Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="namaPA" id="namaPA" type="text"  size='40' readonly="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Pengguna Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="nipPA" id="nipPA" type="text" readonly="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pangkat Pengguna Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="pangkatPA" id="pangkatPA" type="text"  readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK Pemegang Kas :</label>
                <div class="col-md-4">
                    <form:input path="nrkPK" id="nrkPK" type="text"/>
                    <input type="button" id="btnCek2" class="btn blue" onclick="getData(2)" value="Cek NRK">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pemegang Kas :</label>
                <div class="col-md-4">
                    <form:input path="namaPK" id="namaPK" type="text"  size='40' readonly="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Pemegang Kas :</label>
                <div class="col-md-4">
                    <form:input path="nipPK" id="nipPK" type="text" readonly="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nomor NPWP :</label>
                <div class="col-md-4">
                    <form:input path="noNPWP" id="noNPWP" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama NPWP :</label>
                <div class="col-md-4">
                    <form:input path="namaNPWP" id="namaNPWP" type="text" size='40'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kota NPWP :</label>
                <div class="col-md-4">
                    <form:input path="kotaNPWP" id="kotaNPWP" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No Rekening BOP :</label>
                <div class="col-md-4">
                    <form:input path="noBOP" id="noBOP" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Rekening BOP :</label>
                <div class="col-md-4">
                    <form:input path="namaBOP" id="namaBOP" type="text" size='40'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No Rekening BOS :</label>
                <div class="col-md-4">
                    <form:input path="noBOS" id="noBOS" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Rekening BOS :</label>
                <div class="col-md-4">
                    <form:input path="namaBOS" id="namaBOS" type="text" size='40'/>
                </div>
            </div>
        </div>
    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="left">
            <input type="submit" id="btnSimpan" class="btn blue"  value="Simpan">
            <a class="btn blue"  href="${pageContext.request.contextPath}/admin/indexrkas" >Kembali</a>
        </div>
    </div>
    <!--    <div class="portlet box">
            <div class="portlet-body">
                <table id="jourtable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
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
        </div>-->
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/admin/listrkas.js"></script>


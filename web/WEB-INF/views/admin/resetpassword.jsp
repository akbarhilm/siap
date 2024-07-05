<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    function alertUbah() {
        alert("Reset Password Berhasil");
    }
    function alertError() {
        alert("Reset Password Gagal");
    }
</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Reset Password</a></li>
</ul>

<%--<form:form   method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/" class="form-horizontal" enctype="multipart/form-data">--%>
<form:form   method="post" commandName="progcmd"  id="spdBTLMasterform"  onsubmit="return cek()" action="${pageContext.request.contextPath}/sekolah/json/prosesupdate" class="form-horizontal" enctype="multipart/form-data">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Reset Password</div>
            <!--            <div class="actions">
                            <a onclick="" href="${pageContext.request.contextPath}/bku/addbku"  class="btn dark"  ><i class="icon-plus"></i> Tambah</a>
                        </div>-->
        </div>

        <div class="portlet-body flip-scroll">



            <div class="form-group">
                <label class="col-md-3 control-label">NRK :</label>
                <div class="col-md-4">
                    <input type="hidden" id="tahun" readonly="true"  >
                    <input type="hidden" id="idpengguna" readonly="true"  >
                    <input type="text" id="nrk" onchange="hapuspassword()" >
                    <input type="button" id="getpengguna" class="btn blue" onclick="getPengguna()"  value="Cari Pengguna">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Pengguna :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text" id="namapengguna" readonly="true"  >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text" id="sekolah" readonly="true" class="m-wrap large" size="75" >
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Password Baru:</label>
                <div class="col-md-4">
                    <input type="text" id="passwordBaru" readonly="true"  >
                    <input type="button" id="btnGenerate" class="btn blue" onclick="generatePassword()" value="Generate">
                </div>
            </div>
        </div>
    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="left">
            <input type="button" id="btnSimpan" class="btn blue" onclick="simpan()"value="Simpan">
            <input type="button" id="btnCetak" class="btn blue" onclick="cetak()"value="Cetak">
            <!--<input type="button" id="btnSimpan" class="btn blue" onclick="cek()"value="Simpan">-->
            <a class="btn blue"  href="${pageContext.request.contextPath}" >Kembali</a>
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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/admin/resetpassword.js"></script>


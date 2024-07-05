<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    function alertUbah() {
        alert("Data Sekolah Berhasil Diubah");
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

    <li><a href="#">Buku Kas Umum Pengeluaran Sekolah</a></li>
</ul>

<%--<form:form   method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/" class="form-horizontal" enctype="multipart/form-data">--%>
<form:form   method="post" commandName="progcmd"  id="spdBTLMasterform"  onsubmit="return cek()" action="${pageContext.request.contextPath}/sekolah/json/prosesupdate" class="form-horizontal" enctype="multipart/form-data">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Entry Buku Kas Umum Pengeluaran Sekolah</div>
            <!--            <div class="actions">
                            <a onclick="" href="${pageContext.request.contextPath}/bku/addbku"  class="btn dark"  ><i class="icon-plus"></i> Tambah</a>
                        </div>-->
        </div>

        <div class="portlet-body flip-scroll">


            
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-4">
                    <input type="hidden" id="tampung" name="tampung" value="${plt}">
                    <form:hidden path='idSkpd' name="plt" id="idSkpd"  readonly='true' />
                    
                    <form:input path='skpd' name="plt" id="skpd" type="text" size="75" readonly="true"/>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">

                        <form:hidden path="idSekolah" id='idSekolah'/>
                        <form:hidden path="namaSekolahPendek" id='namaSekolahPendek' value="${namaSekolahPendek}"  />
                        <form:hidden path="npsn" id='npsn' value="${npsn}"  />
                        <form:input path="sekolahGabung" name="plt" type="text" id="sekolah"  class="m-wrap large" size="75" readonly='true' />
                       <a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih </a>
<!--                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="btnSimpan" class="btn blue" onclick="tampil()" value="Tampil">
                      -->
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Alamat Sekolah :</label>
                <div class="col-md-4">
                    <form:input path='alamatSekolah' name="plt" id="alamatSekolah" type="text" readonly='true' size="75" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK Pengguna Anggaran:</label>
                <div class="col-md-4">
                    <form:input path="nrkKepsek" name="plt" id="nrkKepsek" type="text" readonly='true'/>
             
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pengguna Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="namaKepsek" name="plt" id="namaKepsek" type="text"  size='40' readonly="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Pengguna Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="nipKepsek" name="plt" id="nipKepsek" type="text" readonly="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pangkat Pengguna Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="pangkatKepsek" name="plt" id="pangkatKepsek" type="text" readonly="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK Pemegang Kas :</label>
                <div class="col-md-4">
                    <form:input path="nrkBendahara" name="plt" id="nrkBendahara" readonly='true' type="text"/>
        
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pemegang Kas :</label>
                <div class="col-md-4">
                    <form:input path="namaBendahara" name="plt" id="namaBendahara" type="text"  size='40' readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Pemegang Kas :</label>
                <div class="col-md-4">
                    <form:input path="nipBendahara" name="plt" id="nipBendahara" type="text" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Telp :</label>
                <div class="col-md-4">
                    <form:input path='noTelpon' name="plt" id="saldokas" type="text" readonly='true' />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Fax :</label>
                <div class="col-md-4">
                    <form:input path="noFax" name="plt" id="noFax" type="text" readonly='true' />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Email :</label>
                <div class="col-md-4">
                    <form:input path="email" name="plt" id="email" type="text" readonly='true' size='30'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Website :</label>
                <div class="col-md-4">
                    <form:input path="website" name="plt" id="website" type="text" readonly='true' size='40'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Logo :</label>
                <div class="col-md-8">

                    <form:input path="namaLogo" name="plt" id="namaLogo" type="text" readonly='true'/>
                </div>
                
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Wilayah :</label>
                <div class="col-md-4">
                    <form:input path="kodeWilayah" name="plt" id="kodeWilayah" type="text" readonly='true' />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jenjang :</label>
                <div class="col-md-4">
                    <form:input path="kodeJenjang" name="plt" id="kodeJenjang" type="text" readonly='true' />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Negeri :</label>
                <div class="col-md-4">
                    <form:input path="kodeNegeri" name="plt" id="kodeNegeri" type="text" readonly='true' />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Lokasi :</label>
                <div class="col-md-4">
                    <form:input path="kodeLokasi" name="plt" id="kodeLokasi" type="text" readonly='true' />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nomor NPWP :</label>
                <div class="col-md-4">
                    <form:input path="noNPWP" name="plt" id="noNPWP" type="text" readonly='true'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama NPWP :</label>
                <div class="col-md-4">
                    <form:input path="namaNPWP" name="plt" id="namaNPWP" type="text" size='40' readonly='true'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kota NPWP :</label>
                <div class="col-md-4">
                    <form:input path="kotaNPWP" name="plt" id="kotaNPWP" type="text" readonly='true'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No Rekening BOP :</label>
                <div class="col-md-4">
                    <form:input path="noBOP" name="plt" id="noBOP" type="text" readonly='true'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Rekening BOP :</label>
                <div class="col-md-4">
                    <form:input path="namaBOP" name="plt" id="namaBOP" type="text" size='40' readonly='true'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No Rekening BOS :</label>
                <div class="col-md-4">
                    <form:input path="noBOS" name="plt" id="noBOS" type="text" readonly='true'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Rekening BOS :</label>
                <div class="col-md-4">
                    <form:input path="namaBOS" name="plt" id="namaBOS" type="text" size='40' readonly='true'/>
                </div>
            </div>
        </div>
    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="left">
          
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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sekolah/indexsekolah.js"></script>


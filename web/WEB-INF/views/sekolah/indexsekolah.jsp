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
                    <form:hidden path='idSkpd' name="idSkpd" id="idSkpd"  readonly='true' />
                    <form:hidden path='tahun' name="tahun" id="tahun" value="${tahunAnggaran}"  readonly='true' />
                    <form:input path='skpd' name="skpd" id="skpd" type="text" size="75" readonly="true"/>
                </div>
            </div>
          
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">

                        <form:hidden path="idSekolah" id='idsekolah'/>
                        <form:hidden path="namaSekolahPendek" id='namaSekolahPendek' value="${namaSekolahPendek}"  />
                        <form:hidden path="npsn" id='npsn' value="${npsn}"  />
                        <form:input path="sekolahGabung" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  />
                       <%--<c:if test="${pengguna.kodeOtoritas=='8'}">--%>  
                            <!--<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolah?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a>--> 
                    <%--</c:if>--%>
                         <%--<c:if test="${pengguna.kodeOtoritas=='9'}">--%> 
                            <!--<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolah?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a>--> 
                    <%--</c:if>--%>
                      
                            <%--<c:if test="${pengguna.kodeOtoritas==8} || ${pengguna.kodeOtoritas==9}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>--%>
                        <c:if test="${pengguna.kodeOtoritas==1}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Alamat Sekolah :</label>
                <div class="col-md-4">
                    <form:input path='alamatSekolah' name="alamatSekolah" id="alamatSekolah" type="text"  size="75" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Depag:</label>
                <div class="col-md-4">
                   
                    <input type="checkbox" id="depag1" onclick="depagpa()">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK Pengguna Anggaran:</label>
                <div class="col-md-4">
                    <form:input path="nrkKepsek" id="nrkKepsek" type="text" />
                    <input type="button" id="btnCek1" class="btn blue" onclick="getData(1)" value="Cek NRK">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pengguna Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="namaKepsek" id="namaKepsek" type="text"  size='40' readonly="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Pengguna Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="nipKepsek" id="nipKepsek" type="text" readonly="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pangkat Pengguna Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="pangkatKepsek" id="pangkatKepsek" type="text" readonly="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Depag:</label>
                <div class="col-md-4">
                   
                    <input type="checkbox" id="depag2" onclick="depagpk()" >
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK Pemegang Kas :</label>
                <div class="col-md-4">
                    <form:input path="nrkBendahara" id="nrkBendahara" type="text"/>
                    <input type="button" id="btnCek2" class="btn blue" onclick="getData(2)" value="Cek NRK">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pemegang Kas :</label>
                <div class="col-md-4">
                    <form:input path="namaBendahara" id="namaBendahara" type="text"  size='40' readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Pemegang Kas :</label>
                <div class="col-md-4">
                    <form:input path="nipBendahara" id="nipBendahara" type="text" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Telp :</label>
                <div class="col-md-4">
                    <form:input path='noTelpon' name="noTelpon" id="saldokas" type="text"  />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Fax :</label>
                <div class="col-md-4">
                    <form:input path="noFax" id="noFax" type="text"  />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Email :</label>
                <div class="col-md-4">
                    <form:input path="email" id="email" type="text"  size='30'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Website :</label>
                <div class="col-md-4">
                    <form:input path="website" id="website" type="text"  size='40'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Logo :</label>
                <div class="col-md-8">

                    <form:input path="namaLogo" name="namaLogo" id="namaLogo" readonly="true" type="text"/><input type="file" id="file" name="file" accept="image/*">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Wilayah :</label>
                <div class="col-md-4">
                    <form:input path="kodeWilayah" id="kodeWilayah" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jenjang :</label>
                <div class="col-md-4">
                    <form:input path="kodeJenjang" id="kodeJenjang" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Negeri :</label>
                <div class="col-md-4">
                    <form:input path="kodeNegeri" id="kodeNegeri" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Lokasi :</label>
                <div class="col-md-4">
                    <form:input path="kodeLokasi" id="kodeLokasi" type="text"  />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nomor NPWP :</label>
                <div class="col-md-4">
                    <form:input path="noNPWP" id="npwp" type="text" onchange="getDataNPWP()" maxlength="15" onkeypress="return isNumber(event)"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama NPWP :</label>
                <div class="col-md-4">
                    <form:input path="namaNPWP" id="namanpwp" type="text" size='40' maxlength="100" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Alamat NPWP Penyetor :</label>
                <div class="col-md-4">
                    <form:textarea path="alamatNPWP" id="alamatnpwp" cols="80" ROWS="3" maxlength="400" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kota NPWP :</label>
                <div class="col-md-4">
                    <form:input path="kotaNPWP" id="kotanpwp" type="text" maxlength="100" readonly="true"/>
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
            <input type="submit" id="btnSimpan" class="btn blue" onclick="addjpg()"value="Simpan">
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


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/admuser/addpengguna.js"></script>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/useradm"  >Daftar Pengguna</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Pengguna<span id='statusaddedit'></span></a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah User</div>
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd"  id="progcmd"  action='${pageContext.request.contextPath}/useradm/simpanuser' class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Group :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="hidden" value="${kodeotor}">
                        <form:select path="kodeGroup" id="kodeGroup" items="${listkodegrup}">

                        </form:select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Otoritas :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:select path="kodeOtoritas" id="kodeOtoritas"  items="${listkodeotor}">

                        </form:select>

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Depag :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <input type="checkbox" id="depag">

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:input path="idNrk" id="idNrk"   cssClass="required"  size="10"    /><span id="spanerror" class="error"></span><br>
                        <button class="dark" type='button' id="ws" onclick="wspeg()">Cek</button>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Password:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:password  path="idSandi" id="idSandi"   cssClass="required ruleCekPassword"  size="16" maxlength="16" />

                    </div>
                </div>
            </div>
            <div class="form-group"  id="skpd">
                <label class="col-md-3 control-label">Nama Pengguna :</label>
                <div class="col-md-4">
                    <form:input path="namaPengguna" id="namaPengguna" readonly="true"/>


                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Pengguna :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="nipPengguna" id="nipPengguna" readonly="true"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan Pengguna :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="jabatanPengguna" id="jabatanPengguna" readonly="true" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">EMAIL ADDRESS :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="namaEmail" id="namaEmail"     size="46" maxlength="46"   />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No HP :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="noHp" id="noHp"      size="18" maxlength="18"   />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">IP Address :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="ipAddress" id="ipAddress"   cssClass="IP4Checker"   size="15" maxlength="16"   />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="idSekolah" id="idSekolah" />
                        <input type="text"  name="sekolah"  id="sekolah"  class="m-wrap large" size="40" readonly />
                        <c:if test="${pengguna.kodeOtoritas==8}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolah?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih </a> </c:if>
                        <c:if test="${pengguna.kodeOtoritas==9}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolah?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">SKPD :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                        <form:hidden path="idSkpd" id="idSkpd" />
                        <input type="text"  name="skpd"  id="skpdda"  class="m-wrap large" size="40" readonly />
                        <c:if test="${pengguna.kodeOtoritas==8}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/skpdpopup/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih </a> </c:if>
                        <c:if test="${pengguna.kodeOtoritas==9}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/skpdpopup/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Aktif :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                        <form:select path="kodeAktif"    >
                            <option value="1">Aktif</option>
                            <option value="0">Tidak Aktif</option>
                        </form:select>

                    </div>
                </div>
            </div>



            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Tambah </button>
                    <a href="${pageContext.request.contextPath}/useradm" class="btn blue" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/admin/addmodul.js"></script>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bku/indexadmmodul"  >Daftar Modul</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Modul<span id='statusaddedit'></span></a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah Modul</div>
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd"  id="progcmd"  action='${pageContext.request.contextPath}/bku/indexadmmodul/simpanmodul' class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Id Induk :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:input path="idInduk" id="idInduk" readonly="true"   cssClass="required"   size="15" maxlength="15"   />
                        <form:errors path="idInduk" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No Modul :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:input path="noModul" id="noModul"  cssClass="required" onchange="formatnomodul(this.value)"  size="15" maxlength="15"  placeholder="Format xx.xx.xx" />
                        <form:errors path="noModul" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Modul :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:input path="namaModul" id="namaModul"   cssClass="required"   size="40" maxlength="40"   />
                        <form:errors path="namaModul" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Link :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:input path="namaModulLink" id="namaModulLink"  size="80" maxlength="80"   />
                        <form:errors path="namaModulLink" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="keterangan" id="keterangan"   cssClass="required"   size="80" maxlength="200"   />
                        <form:errors path="keterangan" class="error" />
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
            <div class="form-group">
                <label class="col-md-3 control-label">Super Admin :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="modulSuperAdmin" id="modulSuperAdmin"/>
                        <input type="checkbox" id="ceksuperadmin" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Admin :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="modulAdmin" id="modulAdmin"/>
                        <input type="checkbox" id="cekadmin">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Sudin :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="modulSudin" id="modulSudin"/>
                         <input type="checkbox" id="ceksudin">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">PA :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="modulPA" id="modulPA"/>
                         <input type="checkbox" id="cekpa">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">PK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="modulPK" id="modulPK"/>
                         <input type="checkbox" id="cekpk">
                    </div>
                </div>
            </div>
            
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Tambah </button>
                    <a href="${pageContext.request.contextPath}/bku/indexadmmodul" class="btn blue" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div>

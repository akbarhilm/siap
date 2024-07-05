<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/sekolah/tambahmcb.js"></script>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/sekolah/indexmcb"  >Daftar Pengguna</a>
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
        <form:form  method="post" commandName="progcmd"  id="progcmd"  action='${pageContext.request.contextPath}/sekolah/updatemcb' class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="idSekolah"   id="idSekolah" />
                        <form:input  path="namaSekolah"   name="sekolah"  id="sekolah"  class="m-wrap large" size="40" readonly="true" />
                        <c:if test="${pengguna.kodeOtoritas==8}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolah?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih </a> </c:if>
                        <c:if test="${pengguna.kodeOtoritas==9}"  ><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolah?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        
                        </div>
                    </div>
                </div>

            
            <div class="form-group">
                <label class="col-md-3 control-label">No MCB :</label>
                <div class="col-md-4">
                    <div class="input-group">
                    <form:hidden path="idMcb" id="idMcb"/>
                        <form:input  path="noMcb" id="noMcb"   cssClass="required"  size="15"   maxlength="30"  />
                        
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama MCB</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="namaMcb" id="namaMcb"   cssClass="required ruleCekPassword"  size="50" maxlength="100" />
                      
                    </div>
                </div>
            </div>
            



            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="submit" id="buttoninduk"  class="btn blue" > Ubah </button>
                    <a href="${pageContext.request.contextPath}/sekolah/indexmcb" class="btn blue" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div>

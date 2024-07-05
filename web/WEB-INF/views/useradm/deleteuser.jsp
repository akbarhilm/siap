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
    <li><a href="#">Hapus Pengguna<span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Hapus User</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"  action='${pageContext.request.contextPath}/useradm/deleteuser' class="form-horizontal">

            <div class="form-group">
        <label class="col-md-3 control-label">Kode Group :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idPengguna" id="idPengguna"/>
                        <form:hidden path="kodeGroup" id="kodeGroup" />
                            
                        <input type="text" id="grup" readonly="true">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Otoritas :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:hidden path="kodeOtoritas" id="kodeOtoritas" />
                           <input type="text" id="otor" readonly="true">
                        
                        
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">NRK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        
                        <form:input path="idNrk" id="idNrk"   cssClass="required"  size="10"  readonly="true" /><span id="spanerror" class="error"></span><br>
<!--                        <button class="dark" type='button' onclick="ws()">Cek</button>-->
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Password:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="idSandi" id="idSandi" readonly="true"  cssClass="required ruleCekPassword"  size="16" maxlength="16" />  
                        
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
                        <form:input path="jabatanPengguna" id="jabatanPengguna"  readonly="true"   />   
                    </div>
                </div>  
            </div>           
            <div class="form-group">
                <label class="col-md-3 control-label">EMAIL ADDRESS :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="namaEmail" id="namaEmail"  readonly="true"   size="46" maxlength="46"   />   
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">No HP :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="noHp" id="noHp"    readonly="true"  size="18" maxlength="18"   />   
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">IP Address :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="ipAddress" id="ipAddress"  readonly="true" cssClass="IP4Checker"   size="15" maxlength="16"   />   
                    </div>
                </div>  
            </div>    
            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="idSekolah" id="idSekolah" /> 
                    <input type="text"  name="sekolah"   id="sekolah"  class="m-wrap large" size="40" value="${sekolah.npsn}/${sekolah.namaSekolahPendek} " readonly />
                    

                    </div>
                </div>  
            </div>             
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-4">
                    <div class="input-group">
                          <form:hidden path="idSkpd" id="idSkpd" /> 
                    <input type="text"  name="skpd"  id="skpdda"  class="m-wrap large" size="40" value="${skpd.kodeSkpd}/${skpd.namaSkpd}" readonly />
                    


                    </div>
                </div>  
            </div>  
             
            <div class="form-group">
                <label class="col-md-3 control-label">Aktif :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="kodeAktif" id="kodeAktif"/>
                        <input type="text" id="aktif" readonly="true">

                    </div>
                </div>  
            </div>    
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn btn-danger" onclick="closer()"> Hapus </button>
                    <a href="${pageContext.request.contextPath}/useradm" class="btn blue" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 

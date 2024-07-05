<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/konversi/listbastrx.js"></script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#"  >Daftar BAS VS TRX</a>
    </li>
</ul> 

<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form   method="post" commandName="bastrx"  id="bastrx"   action="${pageContext.request.contextPath}/refbtl" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar BAS VS TRX</div>
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input id="tahun" type="text" size="6" maxlength="4" value="${tahunAnggaran}" onkeyup="caritahun()" /> 
                </div>
            </div> 
        </div>    
    </div>

    <div class="portlet box">
        <div>
            <table id="usertable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr> 
                        <th rowspan="2">No</th> 
                        <th>Keterangan</th>
                        <th>Kode Rekening</th>
                        <th rowspan="2">Uraian</th>
                    </tr>
                    <tr> 
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="ketfilter" onkeyup="cariket()"  /></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="koderekfilter" onkeyup="carikoderek()"  /></th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table> 
        </div>    
    </div>    
</form:form>
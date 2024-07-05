<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/downloadxls/xlsbop.js"></script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#"  >RKAS VS BOP</a>
    </li>
</ul>
<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>RKAS VS BOP</div>
    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden"  id="idSekolah" />
                        <input type="hidden" id="namaSekolah"/>
                        <input type="hidden" id="npsn"/>
                        <input type="hidden" value="${tahunAnggaran}" id="tahun">
                        <input type="text"  name="pengguna"  id="sekolah"  class="m-wrap large" size="40"  readonly />
                        <a  id="buttonskpd" class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/sekolahpopup/listsekolah?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a>


                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button class='btn blue' onclick="cetakjurnalxls();">Unduh XLS</button>
                    <!--                        <button type="button" class="btn dark" onclick='grid()'>Tambah</button>-->
                </div>
            </div>
        </div>
    </div>
</div>




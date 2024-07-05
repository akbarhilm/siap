<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/admin/listmodul.js"></script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#"  >Daftar Modul</a>
    </li>
</ul>
<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Modul</div>
    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Modul</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="namaModul"  id="namaModul"   title="klik enter atau tekan tombol cari untuk melakukan pencarian"  class="form-control " size="30"
                               onkeyup="if (event.keyCode == 13)
                                           grid()" />
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='grid()'>Cari</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<div class="portlet box">

    <div >
        <!--        <div style="float: right;margin-bottom: 10px;">

           &nbsp;&nbsp;<button type="button"class="btn btn-defaul blue" onclick='cetak()'>Cetak</button>

       </div>-->
        <table id="modultable" class="table table-striped table-bordered table-condensed table-hover" >
            <thead>
                <tr>
                    <th>No</th>
                    <th>No ID</th>
                    <th>No ID INDUK</th>
                    <th>No Modul</th>
                    <th><b>+</b></th>
                    <th>Nama Modul</th>
                    <th>Nama Link</th>
                    <th>Keterangan</th>
                    <th>Aktif</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/penggunamodul/penggunamodul.js"></script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#"  >Daftar Pengguna Modul</a>
    </li>
</ul>
<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Pengguna Modul</div>
    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >

            <div class="form-group">
                <label class="col-md-3 control-label">Pengguna :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden"  id="idPengguna" /> 
                        <input type="hidden" id="kodeotor"/>
                        <input type="text"  name="pengguna"  id="pengguna"  class="m-wrap large" size="40"  readonly />
                        <a  id="buttonskpd" class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/penggunapopup/listpengguna?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>


                    </div>
                </div>  
            
                    <div class="col-md-offset-3 col-md-9">
                       
                        <button type="button" class="btn green" onclick='cek()'>Tampil</button>
                        
                    </div>
                </div>
                        
        </div>
    </div>
      <div class="form-actions fluid">
                    <div class="col-md-offset-10">
                        <button id="simpan"   disabled='true' class='btn blue' onclick="simpan()">Simpan</button>
                        <button class='btn blue' id='tgl' disabled='true' onClick='togle()'>Tambah/Edit</button>
<!--                        <button type="button" class="btn dark" onclick='grid()'>Tambah</button>-->
                    </div>
                </div>                  
</div>

<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<div class="portlet box">

    <div >
        <!--        <div style="float: right;margin-bottom: 10px;">
          
           &nbsp;&nbsp;<button type="button"class="btn btn-defaul blue" onclick='cetak()'>Cetak</button>
        
       </div>-->
        <table id="usertable" class="table table-striped table-bordered table-condensed table-hover " border="1" >
            <thead>
                <tr>
                    
                    <th>No Modul</th> 
                    <th>Nama Modul</th>
                    <th>Uraian Modul</th>
                    <th>Pilih</th>

                </tr>
            </thead>
            <tbody  >
               
            </tbody>
        </table> 
         
    </div>    
</div>
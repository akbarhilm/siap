<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Monitoring Retur</a></li>
</ul>

<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Monitoring Retur</div>
    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                        <input type="hidden" id="tiperek" value="${rek}">
                        
                    </div>
                </div>
            </div>
       
                   
         <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                         <input type="hidden"  id='idsekolah' value="${sekolah.idSekolah}"  />
                         
                         <input type="hidden"  id='namasekolahpendek' value="${sekolah.namaSekolahPendek}"  />
                        <input type="hidden" id='npsn' value="${sekolah.npsn}"  />
                        <input type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listsekolah?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <c:if test="${plt>1}"><a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolahplt?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih </a></c:if>
                    </div>
                </div>
            </div>  
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        
                        <select  id="tw">
                            <option value="1" selected>Triwulan I</option> 
                            <option value="2">Triwulan II</option>
                            <option value="3">Triwulan III</option>
                            <option value="4">Triwulan IV</option>
                            
                        </select>
                    </div>
                </div>  
            </div>
        </div>
    
</div> </div>
 <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" >
            <button type="button" id="btnSimpan" class="btn blue" onclick='tampil()'>Tampil</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/" >Kembali</a>
        </div>
    </div>   
 
        
<div class="portlet box">

    <div >
        <!--        <div style="float: right;margin-bottom: 10px;">

           &nbsp;&nbsp;<button type="button"class="btn btn-defaul blue" onclick='cetak()'>Cetak</button>

       </div>-->
        <table id="usertable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>Tanggal Transfer</th>
                    <th>No Bukti</th>
                    <th>Rekening Tujuan</th>
                    <th>Nama Tujuan</th>
                    <th>Nilai Transfer</th>
                    <th>Keterangan Gagal</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
            
        </table>
    </div>
    
<!--                <div>
                    <table  class="table table-striped table-bordered table-condensed table-hover " >
                      <tbody>
                         
                      </tbody>
                    </table>
                
            </div>-->
</div>
 


<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/monitoring/retur.js"></script>  

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Buku Kas Umum Bendahara Pengeluaran</a></li>
</ul>

<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Buku Kas Umum Bendahara Pengeluaran</div>
    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                    </div>
                </div>
            </div>
       
                   
         <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                         <input type="hidden"  id='idsekolah' value="${sekolah.idSekolah}"  />
                         <input type='hidden' id='kodelap' value='01'>
                         <input type="hidden"  id='namasekolahpendek' value="${sekolah.namaSekolahPendek}"  />
                        <input type="hidden" id='npsn' value="${sekolah.npsn}"  />
                        <input type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listsekolah?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                       
                    </div>
                </div>
            </div>  
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        
                        <select path="bulan" id="bulan">
                            <option value="01" selected>01 - Januari</option> 
                            <option value="02">02 - Februari</option>
                            <option value="03">03 - Maret</option>
                            <option value="04">04 - April</option>
                            <option value="05">05 - Mei</option>
                            <option value="06">06 - Juni</option>
                            <option value="07">07 - Juli</option>
                            <option value="08">08 - Agustus</option>
                            <option value="09">09 - September</option>
                            <option value="10">10 - Oktober</option>
                            <option value="11">11 - November</option>
                            <option value="12">12 - Desember</option>
                        </select>
                    </div>
                </div>  
            </div>
        </div>
    
</div> </div>
 <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" >
            <button type="button" id="btnSimpan" class="btn blue" onclick='cetak()' disabled>Cetak</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/" >Kembali</a>
        </div>
    </div>   
        
 


<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/monitoring/monitoring.js"></script>  

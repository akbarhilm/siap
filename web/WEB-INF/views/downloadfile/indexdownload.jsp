<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">File</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#"> Download File</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Download File</div>
        <!--  <div class="actions">
              <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
          </div>-->
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <!--
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" readonly="true" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}" /> 
                    </div>
                </div>
            </div>

            <div class="form-group" id="labeltanggal">
                <label class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" id="tanggal" name="tanggal"  class="required date-picker entrytanggal2 valid" size="14" /> 
                    </div>
                </div>  
            </div> 
            -->
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" id="btncetak" class="btn blue" onclick='downloadfilepdf()' href="#" > Unduh Pdf</button>

                </div>
            </div> 

        </form>

    </div>

</div>    

<script type="text/javascript">
    function downloadfilepdf() {
        window.location.href = getbasepath() + "/downloadfile/json/getfilepdf";
    }
</script>

<!--script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/rekon/rekon.js"></script-->  

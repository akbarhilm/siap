<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">

</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Tambah Hari Libur</a></li>
</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Tambah Hari Libur</div>

        </div>

        <div class="portlet-body flip-scroll">


            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text" name="tglLibur" id="tglLibur" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                    </div>
                </div>
            </div>

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <TEXTAREA name="uraian" id="uraian" cols="80" ROWS="3" maxlength="400"></TEXTAREA>
                </div>
            </div>
        </div>
    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" >
            <button type="button" id="btnSimpan" class="btn blue" onclick='cekSimpan(1)'>Simpan</button>

        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/cutoff/harilibur.js"></script>


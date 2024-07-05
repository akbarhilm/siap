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

    <li><a href="#">List Hari Libur</a></li>
</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca ">
        <div class="portlet-title"> <!-- cogs-->
            <div class="caption"><i class="icon-cogs"></i>List Hari Libur</div>
            <div class="actions">
                <a onclick="" href="${pageContext.request.contextPath}/cutoff/tambahlibur"  class="btn dark"  ><i class="icon-plus"></i> Tambah</a>
            </div>
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:select id="tahun" path="tahun" onchange="grid()" >
                        <option ${tahunAnggaran} selected>${tahunAnggaran}</option>
                        <option ${tahunAnggaran+1} >${tahunAnggaran+1}</option>
                        <option ${tahunAnggaran+2} >${tahunAnggaran+2}</option>
                    </form:select>
                </div>
            </div>
        </div>
    </div>

    <div class="portlet box">
        <div class="portlet-body">
            <table id="jourtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead  >
                    <tr>
                        <th>No</th>
                        <th>Tahun</th>
                        <th>Tanggal Libur</th>
                        <th>Uraian</th>
                        <th>Edit</th>
                    </tr>
                </thead>
            </table>

        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/cutoff/indexharilibur.js"></script>


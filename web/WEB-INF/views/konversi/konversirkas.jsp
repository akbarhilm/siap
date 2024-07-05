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

    <li><a href="#">Konversi RKAS</a></li>
</ul>

<form:form   method="post" commandName="konversirkas"  id="konversirkas"   action="" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Konversi</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="dupe" name="dupe"  />
                    <input type="hidden" id="giat" name="giat"  />
                    <input type="hidden" id="giatr1" name="giatr1"  />
                    <input type="hidden" id="giatr2" name="giatr2"  />
                    <input id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Pilih :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="modul" onchange='getBudget();'>
                            <option value=''>-- Pilih --</option>
                            <option value='kgt'>1.Kegiatan</option>
                            <option value='kgtk'>2.Kegiatan Rinci</option>
                            <option value='kgta'>3.Kegiatan AKB</option>
                            <option value='dsa'>4.Data Sekolah Yang Memiliki Anggaran</option>

                        </select>
                    </div>
                </div>
            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="proses"  type="button"  class="btn blue" onclick="prosesKonversi()"> Proses </button>

                    <a href="${pageContext.request.contextPath}/" class="btn blue" >Kembali</a>

                </div>
            </div>
        </div>
    </div>



    <table width='100%'>

        <tr>
            <td>
                <div  class="portlet box tosca">
                    <div class="portlet-title">
                        <div class="caption"><i class="icon-file"></i>Data RKAS</div>

                    </div>
                    <div class="portlet-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Record Count :</label>
                            <div class="col-md-4">

                                <input id="rcdcountbudget" type="text" size="10" maxlength="4" readonly='true'   />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Total :</label>
                            <div class="col-md-4">

                                <input id="totalbudget" type="text" size="35" maxlength="4" readonly='true'   />
                            </div>
                        </div>
                    </div>
                </div>
            </td>
            <td>
                <div  class="portlet box tosca">
                    <div class="portlet-title">
                        <div class="caption"><i class="icon-refresh"></i>Hasil Konversi</div>

                    </div>
                    <div class="portlet-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Record Count :</label>
                            <div class="col-md-4">

                                <input id="rcdcountkonversi" type="text" size="10" maxlength="4" readonly='true'   />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Total :</label>
                            <div class="col-md-4">

                                <input id="totalkonversi" type="text" size="35" maxlength="4" readonly='true'   />
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>

    </table>




    <!--
      <div onload="" class="portlet box red col-md-5" >


            <div class="portlet-body">
                <div class="form-group">
                    <label class="col-md-3 control-label">Record Count :</label>
                    <div class="col-md-4">

                        <input id="rcdcount" type="text" size="8" maxlength="4" readonly='true'   />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Total :</label>
                    <div class="col-md-4">

                        <input id="total" type="text" size="35" maxlength="4" readonly='true'   />
                    </div>
                </div>
            </div>
        </div>
    -->
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/konversi/konversirkas.js"></script>


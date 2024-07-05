<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    function setProsesTutup() {
        ambilidskpd();
        $("#kodebutton").val(2);
        //console.log("val tombol proses = "+kodeTombol);
        cekBapKasValidasi();
        //document.getElementById("btnproses").setAttribute("href","${pageContext.request.contextPath}/tutupbuku/tutupbkupop?target='_top'" title="Tutup BKU Pengeluaran");
    }
    function setDraft() {
        ambilidskpd();
        //var kodeTombol = $("#btndraft").val();
        $("#kodebutton").val(1);
        //console.log("val tombol draft = "+kodeTombol);
    }
    function ambilidskpd() {
        var id = $("#idsekolah").val();//$("#idskpdpop").val();
        $("#idsekolahval").val(id);

        var triwul = $("#triwulan").val();
        $("#triwulanval").val(triwul);

    }
//    function setProsesTutup() {
//        ambilidskpd();
//        //var kodeTombol = $("#btnproses").val();
//        window.localStorage.setItem("kodebutton", 2);
//        //console.log("val tombol proses = "+kodeTombol);
//        cekBapKasValidasi();
//
//        //document.getElementById("btnproses").setAttribute("href","${pageContext.request.contextPath}/tutupbuku/tutupbkupop?target='_top'" title="Tutup BKU Pengeluaran");
//    }
//    function setDraft() {
//        ambilidskpd();
//        //var kodeTombol = $("#btndraft").val();
//        window.localStorage.setItem("kodebutton", 1);
//        //console.log("val tombol draft = "+kodeTombol);
//    }
//    function ambilidskpd() {
//        var id = $("#idsekolah").val();//$("#idskpdpop").val();
//        window.localStorage.setItem("idsekolahval", id);
//
//        var triwul = $("#triwulan").val();
//        window.localStorage.setItem("triwulanval", triwul);
//
//    }

</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Proses Tutup BKU</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/laporanbkukeluar/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Proses Tutup BKU</div>
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <!--input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="kodegrup" name="kodegrup" value="${pengguna.kodeGrup}" /-->
                    <input type="hidden" id="updatetgl" name="updatetgl" onchange="setComboTriwulan()" />
                    <input type="hidden" id="kodebutton" name="kodebutton" onchange="" />
                    <input type="hidden" id="idsekolahval" name="idsekolahval" onchange="" />
                    <input type="hidden" id="triwulanval" name="triwulanval" onchange="" />
                    <input type="hidden" id="nilaibapkas" name="nilaibapkas" onchange="" />
                    <!--input type="hidden" id="updatetgl1" name="updatetgl1" onchange="cekProsesTutup()" /-->
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />
                        <form:input path="sekolah" type="text"  name="sekolah"  id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolah}  "  />
                        <%--
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp; <a class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        --%>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <!--div class="form-group">
                <label class="col-md-3 control-label">Jenis Laporan BKU :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select id="jenislaporan" name="jenislaporan" onchange="setpanel(this.value)">
                            <option value="1" selected>1 - BKU Pengeluaran</option>
                            <option value="2">2 - BKU Per Kegiatan</option>

                        </select>
                    </div>
                </div>
            </div-->

            <!--
            Last Edited by Mustakim
            Date 05 Feb 2016
            Memindahkan posisi field kegiatan yang sebelumnya dibawah field bulan, sekarang diatas field bulan
            -->
            <%--
            <div id="labelkegiatan" class="form-group">
                <!--   <div id="hiddenDiv2" style="height:45px;width:490px;border:1px;visibility:hidden;">  -->
                <label class="col-md-3 control-label">Kegiatan : </label>
                <div class="col-md-5">
                    <form:hidden path="idKegiatan" id='idKegiatan' onchange="cekProsesTutup()" />
                    <form:input path="namaKeg" id="namaKeg"  cssClass="required"   type="text" size="65" maxlength="180" readonly='true'  /> <a id="btnpilih" onclick="ambilidskpd()" class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/laporanbkukeluar/listkegiatanpopup?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a><br>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
            <%--
            <input type="hidden"  name="tgl1"  id="tgl1"  readonly="true" size="14"  value="20150101" />
            <input type="hidden"  name="tgl2"  id="tgl2"  class="m-wrap large date-picker entrytanggal1"  size="14"  value="${tgl}" />
            -%>
            <form:select  path="bulan" id="bulan" name="bulan" onchange="cekProsesTutup()"  >  <!--onclick="cekProsesTutup()"-->
                <form:options   />
            </form:select >
            <form:errors path="bulan" cssClass="error"  />

                    </div>
                </div>
            </div>
            --%>
            <div class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <%--
                        <input type="hidden"  name="tgl1"  id="tgl1"  readonly="true" size="14"  value="20150101" />
                        <input type="hidden"  name="tgl2"  id="tgl2"  class="m-wrap large date-picker entrytanggal1"  size="14"  value="${tgl}" />
                        --%>
                        <form:select  path="triwulan" id="triwulan" name="triwulan" onchange="cekProsesTutup()"  >  <!--onclick="cekProsesTutup()"-->
                            <form:options   />
                        </form:select >
                        <form:errors path="triwulan" cssClass="error"  />

                    </div>
                </div>
            </div>

            <div class="form-actions fluid" id="panelTombol">
                <div class="col-md-offset-3 col-md-9">
                    <a id="btndraft" value="0" onclick="setDraft()" class="fancybox fancybox.iframe btn blue" href="${pageContext.request.contextPath}/tutupbkubos/panelsimpanpop?target='_top'" title="Draft BKU Pengeluaran"  ></i>Draft</a>
                    <!-- <button id="btnproses" type="button" class="btn blue"  onclick='simpan()' href="#" >Proses Laporan</button> -->
                    <button id="btncetak" type="button" class="btn blue"  onclick='cetakbkukeluar()' href="#" > Unduh PDF</button>
                    <button id="btncetakxls" type="button" class="btn blue" onclick="cetakjurnalxls()" href="#"> Unduh XLS</button>
                    <a id="btnproses" value="1" onclick="setProsesTutup()" class="fancybox fancybox.iframe btn blue" href="${pageContext.request.contextPath}/tutupbkubos/panelsimpanpop?target='_top'" title="Tutup BKU Pengeluaran"  ></i>Proses Tutup BKU</a>
                </div>
            </div>
        </div>
    </div>

    <div class="portlet box">

        <div class="portlet-body">
            <table id="bkutable" class="table table-striped table-bordered table-condensed table-hover" >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Tanggal</th>
                        <th>No Bukti</th>
                        <th>Uraian</th>
                        <th>Kode Rekening</th>
                        <th>Penerimaan</th>
                        <th>Pengeluaran</th>
                        <th>Saldo</th>
                    </tr>
                </thead>
                <tbody id="bkutablebody" >
                </tbody>
            </table>
        </div>
    </div>

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/tutupbku/indextutupbkubos.js"></script>


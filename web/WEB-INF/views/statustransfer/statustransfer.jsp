<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Koreksi Status Transfer</a></li>
</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="" class="form-horizontal">

    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Koreksi Status Transfer</div>

        </div>
        <div class="portlet-body flip-scroll">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-3 control-label">Tahun:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  readonly="true" class="m-wrap medium" />
                            <input type="hidden" name="idpengguna" id="idpengguna" value="${pengguna.idPengguna}"/>
                            <input type="hidden" id="idskpd" name="idskpd" value="${pengguna.idSkpd}"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Sekolah :</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:hidden path="sekolah.idSekolah" id='idsekolah' value="" name="idsekolah" />
                            <form:hidden path="sekolah.npsn" id='npsn' value=""  />
                            <input name="sekolah" id="sekolah" type="text" size="65" readonly="true" />&nbsp;
                            <a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sekolahpopup/listsekolah?target='_top'" title="Pilih Sekolah"  ><i class="icon-zoom-in"></i> Pilih</a>
                            <form:errors path="sekolah.idSekolah" class="label label-important" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Sumber Dana :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <select name="kodesumb" id="kodesumb" onchange="cleardata()">
                                <option value="-" selected>Pilih</option>
                                <option value="BOS">BOS</option>
                                <option value="BOP">BOP</option>

                            </select>
                        </div>
                    </div>
                </div>

                <div id="labeltriwulan" class="form-group">
                    <label class="col-md-3 control-label">Triwulan :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <select name="triwulan" id="triwulan" onchange="cleardata()" >
                                <option value="-">---- Pilih ----</option>
                                <option value="1">Triwulan I</option>
                                <option value="2">Triwulan II</option>
                                <option value="3">Triwulan III</option>
                                <option value="4">Triwulan IV</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Jenis Transaksi :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <select name="kodeTransaksi" id="kodeTransaksi" onchange="cleardata()">
                                <option value="-" selected> -------------------- Pilih --------------------</option>
                                <option value="JJ">SPJ (PENCATATAN BELANJA)</option>
                                <option value="ST">SETOR SISA KAS </option>
                                <option value="JG">JASA GIRO</option>
                                <option value="RT">PENDAPATAN LAIN-LAIN</option>

                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label id="labelnobuktidok" class="col-md-3 control-label">No Bukti Dok :</label>
                    <div class="col-md-9">
                        <div class="input-group" id="nobuktiketerangan">
                            <input type="hidden" name="idBku" id="idBku" onchange="getBanyakBankBerhasil()" />
                            <input name="noBuktiDok" id="noBuktiDok" type="text"  maxlength="50" size="32" readonly="true" /> &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihDok"  href="${pageContext.request.contextPath}/statustransfer/listdok?target='_top'" title="Pilih Dokumen"  ><i class="icon-zoom-in"></i> Pilih</a>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label id="labelnobuktidok" class="col-md-3 control-label">No Mohon BKU :</label>
                    <div class="col-md-9">
                        <div class="input-group" id="nobuktiketerangan">
                            <input name="noMohon" id="noMohon" type="text"  maxlength="50" size="32" readonly="true" />
                        </div>
                    </div>
                </div>

                <div id="labeluraian" name="labeluraian" class="form-group">
                    <label class="col-md-3 control-label">Uraian :</label>
                    <div class="col-md-4">
                        <TEXTAREA name="uraian" id="uraian" cols="80" ROWS="3" maxlength="400" readonly="true" ></TEXTAREA>
                    </div>
                </div>

                <div class="form-actions fluid">
                    <div id="divButton" class="col-md-offset-3 col-md-9" align="">
                        <button type="button" id="btnTf" class="btn blue" onclick='cekStatusTf()'>Ubah Status Transfer</button>

                    </div>
                </div>

            </form>
        </div>
    </div>

    <div class="portlet box">
        <form id="formapprovesetortable">
            <div class="portlet-title">

            </div>
            <div class="portlet-body">
                <table id="bkutable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr  >
                            <th>No</th>
                            <th>Tgl Proses</th>
                            <th>Nilai</th>
                            <th>Status</th>
                            <th>No Referensi</th>
                            <th>Pilihan</th>
                        </tr>
                    </thead>
                    <tbody id="bkutablebody" >
                    </tbody>
                </table>
            </div>
        </form>
    </div>

    <div class="form-actions fluid" id="labelubahdatabank">
        <div id="divButton" class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" id="btnBank" class="btn blue" onclick='cekpilihbank()'>Ubah Data Bank</button>

        </div>
    </div>
</form:form>




<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/statustransfer/statustransfer.js"></script>

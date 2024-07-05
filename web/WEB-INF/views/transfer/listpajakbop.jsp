<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/transfer/listpajakbop.js"></script>

<script type="text/javascript">
    $(document).ready(function() {

        ketbaris = $('#idbutton', window.parent.document).val(); //window.localStorage.getItem("ketBaris");
        idrinci = $('#idrinci' + ketbaris, window.parent.document).val();

        idblrinci = $('#idblrinci' + ketbaris, window.parent.document).val();
        namasubkeg = $('#namasubkeg' + ketbaris, window.parent.document).val();
        ketrinci = $('#ketrinci' + ketbaris, window.parent.document).val();

        idbas = $('#idbas' + ketbaris, window.parent.document).val();
        kodeakun = $('#kodeakun' + ketbaris, window.parent.document).val();
        idkomponen = $('#idkomponen' + ketbaris, window.parent.document).val();

        nobkuref = $('#noBkuMohon', window.parent.document).val();
        nodok = $('#noBukti', window.parent.document).val();

        nrk = $('#nrkPptk', window.parent.document).val();
        nama = $('#namaPptk', window.parent.document).val();
        nip = $('#nipPptk', window.parent.document).val();
        idkeg = $('#idKegiatan', window.parent.document).val();
        kodekeg = $('#kodeKeg', window.parent.document).val();
        triwulan = $('#triwulan', window.parent.document).val();
        fileinbox = $('#inboxFile', window.parent.document).val();
        tglpost = $('#tglPosting', window.parent.document).val();
        idspj = $('#idBku', window.parent.document).val();

        $("#tglDok").val($('#tglDok', window.parent.document).val());

        $("#nilaispjakun").val($('#nilaispj' + ketbaris, window.parent.document).val());

        nilaispj = accounting.unformat($('#nilaispj' + ketbaris, window.parent.document).val(), ",");

        tampil();
        getDataPajakSpj($('#idblrinci' + ketbaris, window.parent.document).val(), $('#noBkuMohon', window.parent.document).val());

    });

    function tampil() {
        grid();
    }

    function ambilskpd() {
        $('#pajakunformat' + ketbaris, window.parent.document).val(parseFloat(accounting.unformat($("#sumpajak").val(), ",", "."))).change();
        $('#totalpajak' + ketbaris, window.parent.document).val($("#sumpajak").val()).change();
        parent.$.fancybox.close();
    }
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
    <div class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Input Pajak SPJ</div>
        </div>

        <div class="portlet-body flip-scroll">
            <div id="labeltahunpajak"  class="form-group">
                <label id="textNamaPptk" class="col-md-4 control-label">Tahun Pajak :</label>
                <div class="col-md-6">
                    <input name="tahunpajak" id="tahunpajak" type="text" size="6" maxlength="4" value="${tahunAnggaran}" onkeypress="return isNumber(event)" />
                </div>
            </div>

            <div id="labelmasapajak"  class="form-group">
                <label class="col-md-4 control-label">Masa Pajak :</label>
                <div class="col-md-6">
                    <div class="input-group">
                        <select name="masapajak1" id="masapajak1" onchange="setMasaPajak2()" >
                            <option value="-">Pilih</option>
                            <option value="01">Januari</option>
                            <option value="02">Februari</option>
                            <option value="03">Maret</option>
                            <option value="04">April</option>
                            <option value="05">Mei</option>
                            <option value="06">Juni</option>
                            <option value="07">Juli</option>
                            <option value="08">Agustus</option>
                            <option value="09">September</option>
                            <option value="10">Oktober</option>
                            <option value="11">November</option>
                            <option value="12">Desember</option>

                        </select> &nbsp; sampai &nbsp;
                        <select name="masapajak2" id="masapajak2" >
                            <option value="-">Pilih</option>
                            <option value="01">Januari</option>
                            <option value="02">Februari</option>
                            <option value="03">Maret</option>
                            <option value="04">April</option>
                            <option value="05">Mei</option>
                            <option value="06">Juni</option>
                            <option value="07">Juli</option>
                            <option value="08">Agustus</option>
                            <option value="09">September</option>
                            <option value="10">Oktober</option>
                            <option value="11">November</option>
                            <option value="12">Desember</option>
                        </select>
                    </div>
                </div>
            </div>

            <div id="labelnamarekanan" class="form-group">
                <label id="textnamarekan" class="col-md-4 control-label">Nama Wajib Pajak :</label>
                <div class="col-md-6">
                    <div class="input-group">
                        <input name="namarekanan" id="namarekanan" type="text" size="50" maxlength="120" readonly="true"/>
                    </div>
                </div>
            </div>

            <div id="labelnpwp" name="labelnamapptk" class="form-group">
                <label id="textNamaPptk" class="col-md-4 control-label">NPWP Penyetor:</label>
                <div class="col-md-6">
                    <input name="npwp" id="npwp" type="text" maxlength="30" readonly="true"/>
                </div>
            </div>

            <div class="form-group" id="labeltgldok" style="display: none">
                <label class="col-md-4 control-label">Tanggal Dokumen :</label>
                <div class="col-md-6">
                    <div class="input-group">
                        <input type="text" name="tglDok" id="tglDok" readonly="true" disabled="true" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                    </div>
                </div>
            </div>

            <div  class="form-group">
                <label class="col-md-4 control-label">Nilai SPJ :</label>
                <div class="col-md-6">
                    <input name="nilaispjakun" id="nilaispjakun" type="text" readonly="true"/>
                    <input type="hidden" id="idsekolah" name="idsekolah" value="${sekolah.idSekolah}" />
                </div>
            </div>
        </div>

        <div class="form-horizontal" >

            <div class="form-group" style="display: none">
                <br></br>
                <label class="col-md-4 control-label">Kode Keg :</label>
                <div class="col-md-6">
                    <div class="input-group">
                        <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                        <input type="hidden" id="ketnilaip1" />
                        <input type="hidden" id="ketnilaip2" />
                        <input type="hidden" id="ketnilaip3" />
                        <input type="hidden" id="ketnilaip4" />
                        <input type="hidden" id="ketnilaip5" />
                        <input type="hidden" id="ketnilaip6" />
                        <input type="text"  name="kode"  id="kode"  class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                    tampil()" />
                    </div>
                </div>
            </div>

        </div>

    </div>


    <div id="mygrid" class="portlet box">

        <div id="tabelSPJ" class="portlet-body">

            <table id="spjtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Jenis Pajak</th>
                        <th>Persentase</th>
                        <th>Nilai Pajak</th>

                    </tr>
                </thead>

                <tbody id="spjtablebody" >
                    <tr>

                    </tr>
                </tbody>

                <tfoot>
                    <tr>
                        <th colspan="3" style="text-align:right">Total:</th>

                        <th colspan="1" >
                            <input type='text' id="sumpajak"   name="sumpajak" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>

                    </tr>
                </tfoot>

            </table>
        </div>

    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <!--<button type="button" id="" class="btn blue" onclick='cek()' >Cek</button>-->
            <button type="button" id="btnTambah" class="btn blue" onclick='ceksimpan()' >Simpan</button>
        </div>
    </div>
</form:form>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function () {
        getSaldoAwal();
    });

    function getSaldoAwal() {
        var triwulan = $('#triwulan').val();
        var idsekolah = $('#idsekolah').val();
        var nobku = $('#noBkuMohon').val();

        $.getJSON(getbasepath() + "/bkubos/json/getSaldoAwal", {idsekolah: idsekolah, triwulan: triwulan, nobku: nobku},
                function (result) {

                    var saldoawal = result.aData;
                    $('#saldoawal').val(accounting.formatNumber(saldoawal, 2, '.', ","));

                });
    }

</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU - BOS</a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bkubos/indexbkubos" >Buku Kas Umum - BOS</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Hapus SPJ</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Buku Kas Umum - BOS</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />
                        <form:hidden path="sekolah.namaSekolahPendek" id='kodeskpd' value="${sekolah.namaSekolahPendek}"  />
                        <form:hidden path="sekolah.npsn" id='namaskpd' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">No BKU Mohon :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idBku" id='idBku' />
                        <form:hidden path="idParam" id='idParam' />
                        <form:input path="noBkuMohon" id='noBkuMohon' type="text" readonly="true"  size="16" />
                    </div>
                </div>
            </div>

            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="triwulan" id="triwulan"  disabled="true" >
                            <form:option value="1">Triwulan I</form:option>
                            <form:option value="2">Triwulan II</form:option>
                            <form:option value="3">Triwulan III</form:option>
                            <form:option value="4">Triwulan IV</form:option>
                        </form:select>

                        <form:errors path="triwulan" class="error" />

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:select path="kodeTransaksi" id="kodeTransaksi" disabled="true" readonly="true" >
                            <form:option value="JO">PENGISIAN KAS</form:option>
                            <form:option value="JJ">SPJ (PENCATATAN BELANJA)</form:option>
                            <form:option value="P1">PPH PS 21</form:option>
                            <form:option value="P2">PPH PS 22</form:option>
                            <form:option value="P3">PPH PS 23 JASA I</form:option>
                            <form:option value="P4">PPH PS 4 Ayat 2</form:option>
                            <form:option value="P5">PPN </form:option>
                            <form:option value="P6">PPH Pasal 26 </form:option>
                            <form:option value="ST">SETOR SISA KAS</form:option>
                            <form:option value="JG">JASA GIRO</form:option>

                        </form:select>

                        <form:errors path="kodeTransaksi" class="error" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label id="labelnobuktidok" class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <form:input path="noBukti" id="noBukti" type="text" readonly="true" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label id="labeltgldok" class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglDok" id="tglDok" disabled="true" class="required  date-picker2 entrytanggal2 valid" size="14" value="" />
                        <form:errors path="tglDok" class="error" />

                    </div>
                </div>
            </div>

            <div id="labelfileinbox" name="labelfileinbox" class="form-group">
                <label class="col-md-3 control-label">No Arsip :</label>
                <div class="col-md-4">
                    <form:input path="inboxFile" id="inboxFile" type="text" readonly="true"  class="required "  size="14" maxlength="10" />
                    <form:errors path="inboxFile" class="error" />
                </div>
            </div>

            <div id="labelcarabayar" name="labelcarabayar" class="form-group">
                <label class="col-md-3 control-label">Cara Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="kodePembayaran" id="kodePembayaran"  disabled="true">
                            <form:option value="1">1 - Tunai</form:option>
                            <form:option value="2">2 - Bank/Transfer/Cek</form:option>
                        </form:select>
                    </div>
                </div>
            </div>

            <div id=""  class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="hidden" id="totalspjhidden" name="totalspjhidden"  />

                    <form:hidden path="idKegiatan" id='idKegiatan' onchange="getKegiatan()" />
                    <form:hidden path="kodeKeg" id='kodeKeg' />
                    <form:input path="ketKegiatan" id="ketKegiatan" type="text"  class="m-wrap large" size="80" readOnly="true"/>

                </div>
            </div>
            <div id="labelbidang" name="labelbidang" class="form-group">
                <label id="textlabelsisaspj" class="col-md-3 control-label">Bidang :</label>
                <div class="col-md-4">
                    <input name="bidang" id="bidang" type="text" readonly="true" size="43"/>
                </div>
            </div>
            <div id="labeltalangan" name="labeltalangan" class="form-group">
                <label id="textlabeltalangan" class="col-md-3 control-label">Dana Talangan :</label>
                <div class="col-md-4">
                    <form:hidden path="kodeTalangan" id='kodeTalangan' />
                    <input type="checkbox" id="cbTalangan" id="cbTalangan" onchange="setTalangan()" disabled="true"/>
                </div>
            </div>
            <div id="labelbulan" name="labelbulan"class="form-group">
                <label class="col-md-3 control-label">Bulan Tagihan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="bulanTagihan" id='bulanTagihan' />
                        <select path="bulan" id="bulan" disabled="true">
                            <option value="01">Januari</option>
                            <option value="02">Februari</option>
                            <option value="03">Maret</option>
                            <option value="04">April</option>
                            <option value="05">Mei</option>
                            <option value="06">Juni</option>
                            <option value="07">Juli</option>
                            <option value="08">Agustus</option>
                            <option value="09">September </option>
                            <option value="10">Oktober</option>
                            <option value="11">November</option>
                            <option value="12">Desember</option>

                        </select>
                    </div>
                </div>
            </div>
            <div id="labelmcb" name="labelmcb" class="form-group">
                <label id="labelnilaibkutext" class="col-md-3 control-label">MCB :</label>
                <div class="col-md-4">
                    <form:hidden path="idMcb" id='idMcb' />
                    <select name="cbmcb" id="cbmcb" onchange="" disabled="true" >
                    </select>
                </div>
            </div>
            <div id="labelbidang" name="labelbidang" class="form-group">
                <label id="textlabelsisaspj" class="col-md-3 control-label">Bidang :</label>
                <div class="col-md-4">
                    <input name="bidang" id="bidang" type="text" readonly="true" size="43"/>
                </div>
            </div>

            <div id="labelsnp" name="labelsnp" class="form-group">
                <label class="col-md-3 control-label">Standar Pendidikan :</label>
                <div class="col-md-4">
                    <input name="snp" id="snp" type="text" readonly="true" size="43" />
                </div>
            </div>

            <div id="labelsumbdanaspj" name="labelsumbdanaspj" class="form-group">
                <label class="col-md-3 control-label">Sumber Dana :</label>
                <div class="col-md-4">
                    <input name="sumbdana" id="sumbdana" type="text" readonly="true" size="43"/>
                </div>
            </div>

            <div id="labelsaldoawal" name="labelsaldoawal" class="form-group">
                <label class="col-md-3 control-label">Saldo Kas :</label>
                <div class="col-md-4">
                    <input name="saldoawal" id="saldoawal" type="text" readonly="true" style="text-align:right" />
                </div>
            </div>

            <div id="labelpaguakb" name="labelsisakas" class="form-group">
                <label class="col-md-3 control-label">Pagu AKB Kegiatan :</label>
                <div class="col-md-4">
                    <input name="paguakb" id="paguakb" type="text" readonly="true" style="text-align:right" />
                </div>
            </div>

            <div id="labelsisakas" name="labelsisakas" class="form-group">
                <label class="col-md-3 control-label">Sisa Pagu AKB Kegiatan :</label>
                <div class="col-md-4">
                    <input name="sisakas" id="sisakas" type="text" readonly="true" style="text-align:right" />
                </div>
            </div>

            <div id="labelnrk" name="labelnrk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NRK Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="nrkPptk" id="nrkPptk" type="text" readonly="true"  class="required "  maxlength="6" onkeypress="return isNumber(event)"/>
                </div>
            </div>

            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NIP Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="nipPptk" id="nipPptk" type="text" readonly="true"  class="required "  maxlength="18" onkeypress="return isNumber(event)"/>
                </div>
            </div>

            <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                <label id="textNamaPptk" class="col-md-3 control-label">Nama Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="namaPptk" id="namaPptk" type="text" readonly="true"  class="required " size="50" maxlength="50"/>
                </div>
            </div>

            <div id="labelnamabank" class="form-group">
                <label class="col-md-3 control-label">Nama Bank :</label>
                <div class="col-md-7">
                    <div class="input-group">
                        <form:hidden path="kodeBank" id='kodeBank' />
                        <form:hidden path="kodeBankTf" id='kodeBankTf' />
                        <form:input path="namaBank" id="namaBank" type="text"  class="required " size="65" readonly="true"/>
                    </div>
                </div>
            </div>

            <div id="labelrekbank" class="form-group">
                <label class="col-md-3 control-label">No Rekening Bank :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="norekBank" id="norekBank" type="text" readonly="true"  class="required " maxlength="30" onkeypress="return isNumber(event)"/>
                    </div>
                </div>
            </div>

            <div id="labelnamarekanan" class="form-group">
                <label class="col-md-3 control-label">Nama Rekanan/PFK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="namaRekan" id="namaRekan" type="text" readonly="true"  class="required " size="50" maxlength="120"/>
                    </div>
                </div>
            </div>
            <div id="labelbelanja" class="form-group">
                <label class="col-md-3 control-label">Jenis Pajak Belanja :</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:hidden path="kodeBelanja" id='kodeBelanja' />
                        <select path="belanja" id="belanja" disabled="true" onchange="pilihBelanja()">
                            <option value="-">---- Pilih ----</option>
                            <option value="1">Pegawai</option>
                            <option value="2">Barang</option>
                            <option value="3">Jasa</option>
                        </select>
                    </div>
                </div>
            </div>
            <div id="labelpns" class="form-group">
                <label class="col-md-3 control-label">Status PNS Rekanan/PFK :</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:hidden path="kodePNS" id='kodePNS' />
                        <select path="pns" id="pns" onchange="pilihGolongan()" disabled="true">
                            <option value="-">---- Pilih ----</option>
                            <option value="1">PNS</option>
                            <option value="0">Bukan PNS</option>
                        </select>
                        &nbsp;
                        <select path="golongan" id="golongan" disabled="true">
                            <option value="-">---- Pilih ----</option>
                            <option value="1">Golongan I</option>
                            <option value="2">Golongan II</option>
                            <option value="3">Golongan III</option>
                            <option value="4">Golongan IV</option>
                        </select>
                    </div>
                </div>
            </div>
            <div id="labelpegawai" class="form-group">
                <label class="col-md-3 control-label">Jenis Pegawai :</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:hidden path="kodePegawai" id='kodePegawai' />
                        <select path="pegawai" id="pegawai" onchange="pilihPegawai()" disabled="true">
                            <option value="-">---- Pilih ----</option>
                            <option value="1">Pegawai Tidak Tetap/Tenaga Kerja Lepas</option>
                            <option value="2">Bukan Pegawai</option>
                            <option value="3">Peserta Kegiatan</option>
                        </select>
                    </div>
                </div>
            </div>
            <div id="labelperiode" class="form-group">
                <label class="col-md-3 control-label">Jenis Periode :</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:hidden path="kodePeriode" id='kodePeriode' />
                        <select path="periode" id="periode" disabled="true">
                            <option value="-">---- Pilih ----</option>
                            <option value="1">Harian</option>
                            <option value="2">Mingguan</option>
                            <option value="3">Bulanan</option>
                            <option value="4">Triwulanan</option>
                        </select>
                    </div>
                </div>
            </div>
            <div id="labelnpwp" name="labelnpwp" class="form-group">
                <label id="textnpwp" class="col-md-3 control-label">NPWP Rekanan/PFK :</label>
                <div class="col-md-4">
                    <form:hidden path="npwp" id='datanpwp' />
                    <input name="npwp" id="npwp" type="text" maxlength="30" onchange="getDataNPWP()" maxlength="15" onkeypress="return isNumber(event)" readonly="true"/>
                </div>
            </div>
            <div id="labelnamanpwp" name="labelnamanpwp" class="form-group">
                <label id="textnamanpwp" class="col-md-3 control-label">Nama NPWP Rekanan/PFK :</label>
                <div class="col-md-4">
                    <form:input path="namaNpwp" id="namanpwp" type="text"  class="required "  maxlength="100" readonly="true"/>
                </div>
            </div>
            <div id="labelalamatnpwp" name="labelalamatnpwp" class="form-group">
                <label id="textalamatnpwp" class="col-md-3 control-label">Alamat NPWP Rekanan/PFK :</label>
                <div class="col-md-4">
                    <form:textarea path="alamatNpwp" id="alamatnpwp" class="required " cols="80" ROWS="3" maxlength="400" readonly="true"/>
                </div>
            </div>
            <div id="labelkotanpwp" name="labelkotanpwp" class="form-group">
                <label id="textkotanpwp" class="col-md-3 control-label">Kota NPWP Rekanan/PFK :</label>
                <div class="col-md-4">
                    <form:input path="kotaNpwp" id="kotanpwp" type="text"  class="required "  maxlength="100" readonly="true"/>
                </div>
            </div>
            <div id="labelpkp" class="form-group">
                <label class="col-md-3 control-label">Status PKP :</label>
                <div class="col-md-9">
                    <form:hidden path="kodePKP" id='kodePKP' />
                    <input type="checkbox" id="cmbpkp" id="cmbpkp" disabled="true"/>
                </div>
            </div>
            <div id="labelptkp" class="form-group">
                <label class="col-md-3 control-label">Jenis PTKP :</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:hidden path="kodePTKP" id='kodePTKP' />
                        <select path="ptkp" id="ptkp" disabled="true">
                            <option value="-">---- Pilih ----</option>
                            <option value="TK0">TK0 - Tidak Kawin, 0 Tanggungan</option>
                            <option value="TK1">TK1 - Tidak Kawin, 1 Tanggungan</option>
                            <option value="TK2">TK2 - Tidak Kawin, 2 Tanggungan</option>
                            <option value="TK3">TK3 - Tidak Kawin, 3 Tanggungan</option>
                            <option value="K0">K0 - Kawin, 0 Tanggungan</option>
                            <option value="K1">K1 - Kawin, 1 Tanggungan</option>
                            <option value="K2">K2 - Kawin, 2 Tanggungan</option>
                            <option value="K3">K3 - Kawin, 3 Tanggungan</option>
                        </select>
                    </div>
                </div>
            </div>
            <div id="labelsk" class="form-group">
                <label class="col-md-3 control-label">Surat Keterangan Pajak :</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:hidden path="kodeSK" id='kodeSK' />
                        <select path="cmbsk" id="cmbsk" onchange="inputSK()" disabled="true">
                            <option value="-">---- Pilih ----</option>
                            <option value="1">Ada</option>
                            <option value="0">Tidak Ada</option>
                        </select>
                        &nbsp;
                        <input name="kodesk" id="kodesk" type="text" maxlength="100" readonly="true" />
                    </div>
                </div>
            </div>
            <div id="labelva" class="form-group">
                <label class="col-md-3 control-label">Virtual Account :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="kodeVA" id='kodeVA' />
                        <input type="checkbox" id="cbVA" id="cbVA" onchange="setVA()" disabled="true"/>
                    </div>
                </div>
            </div>

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <form:textarea path="uraian" id="uraian" class="required " cols="80" readonly="true"  ROWS="3" maxlength="400" />
                </div>
            </div>


        </div>
    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" >
            <button type="button" id="btnSimpan" class="btn blue" onclick='hapus()'>Hapus</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/bkubos/indexbkubos" >Kembali</a>

        </div>
    </div>

    <div id="mygrid" class="portlet box">
        <div id="tabelSPJ" class="portlet-body">

            <table id="spjtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Akun</th>
                        <th>Komponen</th>
                        <th>Keterangan Rinci</th>
                        <th>Nilai Anggaran</th>
                        <th>Nilai Sebelum</th>
                        <th>Sisa Anggaran</th>
                        <th>Quantity</th>
                        <th>Nilai SPJ</th>
                        <th>Pajak</th>
                    </tr>
                </thead>

                <tbody id="spjtablebody" >
                    <tr>

                    </tr>
                </tbody>

                <tfoot>
                    <tr>
                        <th colspan="8" style="text-align:right">Total:</th>

                        <th colspan="1" >
                            <input type='text' id="sumspj"   name="sumspj" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>

                    </tr>
                </tfoot>

            </table>
        </div>

    </div>


</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkubos/editbkubosspj.js"></script>


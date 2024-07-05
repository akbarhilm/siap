<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function () {

    });


</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU - BOP</a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bkubop/indexbkubop" >Buku Kas Umum - BOP</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Hapus Pajak</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Buku Kas Umum - BOP</div>

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
                        <form:hidden path="idBkuRef" id='idBkuRef' />
                        <form:hidden path="noBkuRef" id='noBkuRef' />
                        <form:hidden path="idBl" id='idBl' />
                        <input type="hidden" id="idparam" name="idparam"  >
                        <input type="hidden" id="cnpwp" name="cnpwp"  >
                        <input type="hidden" id="cpkp" name="cpkp"  >
                        <input type="hidden" id="cpns" name="cpns"  >
                        <input type="hidden" id="csk" name="csk"  >
                        <input type="hidden" id="cbelanja" name="cbelanja"  >
                        <input type="hidden" id="cpegawai" name="cpegawai"  >
                        <input type="hidden" id="cperiode" name="cperiode"  >
                        <input type="hidden" id="cptkp" name="cptkp"  >
                        <form:input path="noBkuMohon" id='noBkuMohon' type="text" readonly="true"  size="16" />
                    </div>
                </div>
            </div>

            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="triwulan" id="triwulan"  disabled="true">
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
                            <form:option value="JJ">SPJ + Pajak Penerimaan</form:option>
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

            <div id=""  class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <form:hidden path="idKegiatan" id='idKegiatan' onchange="getKegiatan()" />
                    <form:hidden path="kodeKeg" id='kodeKeg' />
                    <form:input path="ketKegiatan" id="ketKegiatan" type="text"  class="m-wrap large" size="80" readOnly="true"/>

                </div>
            </div>

            <div class="form-group">
                <label id="labelnobuktidok" class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <form:input path="noBukti" id="noBukti" type="text" readonly="true"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label id="labeltgldok" class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglDok" id="tglDok" readonly="true" class="required  date-picker2 entrytanggal2 valid" size="14" value="" />
                        <form:errors path="tglDok" class="error" />

                    </div>
                </div>
            </div>

            <div id="labelfileinbox" name="labelfileinbox" class="form-group">
                <label class="col-md-3 control-label">No Arsip :</label>
                <div class="col-md-4">
                    <form:input path="inboxFile" id="inboxFile" type="text" readonly="true" class="required "  size="14" maxlength="10" />
                    <form:errors path="inboxFile" class="error" />
                </div>
            </div>

            <div id="labeljenisbayar" name="labeljenisbayar" class="form-group">
                <label class="col-md-3 control-label">Jenis Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="jenisPembayaran" id="jenisPembayaran" disabled="true" >
                            <form:option value="PN">Penerimaan</form:option>
                            <form:option value="PG">Pengeluaran</form:option>
                        </form:select>
                    </div>
                </div>
            </div>

            <div id="labelbayarpajak" name="labelbayarpajak" class="form-group">
                <label class="col-md-3 control-label"></label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="kodePajak" id="kodePajak" disabled="true" >
                            <form:option value="0">Pajak SPJ (Belanja yang belum dibayar)</form:option>
                            <form:option value="1">Pengembalian Pajak Dari Rekanan (Belanja yang sudah dibayar)</form:option>
                        </form:select>
                    </div>
                </div>
            </div>
            <div id="labelpkp" class="form-group">
                <label class="col-md-3 control-label">Status PKP :</label>
                <div class="col-md-9">
                    <form:hidden path="kodePKP" id='kodePKP' />
                    <input type="checkbox" id="cmbpkp" id="cmbpkp" disabled="true"/>
                </div>
            </div>
            <div class="form-group" id="labelkjs">
                <label class="col-md-3 control-label">Kode Jenis Setor :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="kodeMap" id='kodeMap' />
                        <form:hidden path="kodeKjs" id='kodeKjs' />
                        <select  id="kodekjs">

                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group" id="labelrumuspajakp1">
                <label class="col-md-3 control-label">Persentase :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="rumuspajakp1" >
                            <option value="-">Pilih</option>
                            <option value="1">NPWP</option>
                            <option value="2">NON NPWP</option>
                        </select>

                        <select  id="rumuspersenp1" >

                        </select>

                    </div>
                </div>
            </div>

            <div class="form-group" id="labelrumuspajakp2">
                <label class="col-md-3 control-label">Persentase :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="rumuspajakp2" >
                            <option value="-">Pilih</option>
                            <option value="0">NON PPN</option>
                            <option value="1">PPN</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group" id="labelrumuspajakp3">
                <label class="col-md-3 control-label">Persentase :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="rumuspajakp3" >
                            <option value="-">Pilih</option>
                            <option value="0">NON PPN</option>
                            <option value="1">PPN</option>
                        </select>

                        <select  id="rumuspersenp3" >
                            <option value="2">2%</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group" id="labelrumuspajakp4">
                <label class="col-md-3 control-label">Persentase :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="rumuspajakp4" onchange="setPnP4()">
                            <option value="-">Pilih</option>
                            <option value="0">TIDAK ADA PPN</option>
                            <option value="1">ADA PPN</option>
                        </select>

                        <select  id="rumuspersenp4" onchange="setPnP4()">
                            <option value="0.5">0.5%</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group" id="labelrumuspajakp5">
                <label class="col-md-3 control-label">Persentase :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="rumuspajakp5" >
                            <option value="-">Pilih</option>
                            <option value="1">1%</option>
                            <option value="10">10%</option>
                        </select>
                    </div>
                </div>
            </div>

            <div id="labelcarabayar" name="labelcarabayar" class="form-group">
                <label class="col-md-3 control-label">Cara Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="kodePembayaran" id="kodePembayaran" disabled="true" >
                            <form:option value="1">1 - Tunai</form:option>
                            <form:option value="2">2 - Bank/Transfer/Cek</form:option>
                        </form:select>
                    </div>
                </div>
            </div>



            <div id="labelnrk" name="labelnrk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NRK Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="nrkPptk" id="nrkPptk" type="text"  class="required " readonly="true"  maxlength="6" onkeypress="return isNumber(event)"/>
                </div>
            </div>

            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NIP Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="nipPptk" id="nipPptk" type="text"  class="required " readonly="true" maxlength="18" onkeypress="return isNumber(event)"/>
                </div>
            </div>

            <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                <label id="textNamaPptk" class="col-md-3 control-label">Nama Bendahara :</label>
                <div class="col-md-4">
                    <form:input path="namaPptk" id="namaPptk" type="text"  class="required " readonly="true" size="50" maxlength="50"/>
                </div>
            </div>


            <div id="" class="form-group">
                <label id="textnamarekan" class="col-md-3 control-label">Nama Penyetor :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="namaRekan" id="namaRekan" type="text"  class="required " readonly="true" size="50"  maxlength="120"/>
                    </div>
                </div>
            </div>

            <div id="" class="form-group">
                <label id="textnpwp"  class="col-md-3 control-label">NPWP Penyetor :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="npwp" id="npwp" type="text"  class="required " readonly="true" maxlength="30"/>
                    </div>
                </div>
            </div>
            <div id="labelnamanpwp" name="labelnamanpwp" class="form-group">
                <label id="textnamanpwp" class="col-md-3 control-label">Nama NPWP Penyetor :</label>
                <div class="col-md-4">
                    <form:input path="namaNpwp" name="namanpwp" id="namanpwp" type="text" maxlength="100" readonly="true" />
                </div>
            </div>
            <div id="labelalamatnpwp" name="labelalamatnpwp" class="form-group">
                <label id="textalamatnpwp" class="col-md-3 control-label">Alamat NPWP Penyetor :</label>
                <div class="col-md-4">
                    <form:textarea path="alamatNpwp" name="alamatnpwp" id="alamatnpwp" cols="80" ROWS="3" maxlength="400" readonly="true"/>
                </div>
            </div>
            <div id="labelkotanpwp" name="labelkotanpwp" class="form-group">
                <label id="textkotanpwp" class="col-md-3 control-label">Kota NPWP Penyetor :</label>
                <div class="col-md-4">
                    <form:input path="kotaNpwp" name="kotanpwp" id="kotanpwp" type="text" maxlength="100" readonly="true" />
                </div>
            </div>
            <div id="labelmasapajak"  class="form-group">
                <label class="col-md-3 control-label">Masa Pajak :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="masaPajak" id='masaPajak' />

                        <select name="masapajak1" id="masapajak1" onchange="setMasaPajak2()" disabled="true">
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
                        <select name="masapajak2" id="masapajak2" disabled="true">
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

            <div id="" class="form-group">
                <label class="col-md-3 control-label">Tahun Pajak :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="tahunPajak" id="tahunPajak" type="text" readonly="true" class="required " size="6" maxlength="4" onkeypress="return isNumber(event)"/>
                    </div>
                </div>
            </div>


            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <form:textarea path="uraian" id="uraian" class="required " readonly="true" cols="80" ROWS="3" maxlength="400" />
                </div>
            </div>


        </div>
    </div>

    <div id="mygrid" class="portlet box">
        <div id="tabelPajak" class="portlet-body">

            <table id="pajaktable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Akun</th>
                        <th>Komponen</th>
                        <th>Nilai SPJ</th>
                        <th>Sisa SPJ</th>
                        <th>Persentase</th>
                        <th>Nilai Pajak</th>
                    </tr>
                </thead>

                <tbody id="pajaktablebody" >
                    <tr>

                    </tr>
                </tbody>

                <tfoot>
                    <tr>
                        <th colspan="6" style="text-align:right">Total:</th>

                        <th colspan="1" >
                            <input type='text' id="sumpjpn"   name="sumpjpn" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>

                    </tr>
                </tfoot>

            </table>
        </div>

        <div id="tabelPajakPg" class="portlet-body">

            <table id="pajakpgtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Akun</th>
                        <th>Komponen</th>
                        <th>Nilai Pajak</th>
                    </tr>
                </thead>

                <tbody id="pajakpgtablebody" >
                    <tr>

                    </tr>
                </tbody>

            </table>
        </div>

    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" id="btnSimpan" class="btn blue" onclick='cekhapus()'>Hapus</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/bkubop/indexbkubop" >Kembali</a>

        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkubop/editbkuboppajak.js"></script>


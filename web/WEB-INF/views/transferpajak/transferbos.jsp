<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function () {

    });


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
    <li><a href="#">Pembayaran Pajak BKU - BOS</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pembayaran Pajak Buku Kas Umum - BOS</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="col-md-7">
                <div class="form-group">
                    <label class="col-md-4 control-label">Tahun Anggaran:</label>
                    <div class="col-md-7">
                        <input type="hidden" id="isadd" name="isadd"  />
                        <input type="hidden" id="validatetime" name="validatetime"  />
                        <input type="hidden" id="idrequest" name="idrequest"  />
                        <input type="hidden" id="bulkid" name="bulkid"  />
                        <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Sekolah :</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <input type="hidden" id="wilayah" name="wilayah"  />
                            <input type="hidden" id="idbutton" name="idbutton"  />

                            <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}"  />
                            <form:hidden path="sekolah.npsn" id='npsn' value="${sekolah.npsn}"  />
                            <form:hidden path="sekolah.namaSekolahPendek" id='namasekolah' value="${sekolah.namaSekolahPendek}"  />
                            <form:hidden path="sekolah.npsn" id='namaskpd' value="${sekolah.npsn}"  />
                            <form:hidden path="idskpd" id='idskpd' value=""  />
                            <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                            <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                            <form:errors path="sekolah.idSekolah" class="label label-important" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">No BKU Mohon :</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <form:hidden path="idBku" id='idBku' />
                            <form:hidden path="norekBank" id='norekBank' />
                            <form:hidden path="namaPengirimBank" id='namaPengirimBank' />
                            <form:hidden path="kodeJenisSetor" id='kodeJenisSetor' />
                            <form:hidden path="akunPajak" id='akunPajak' />
                            <form:hidden path="noSk" id='noSk' />
                            <form:hidden path="tglBillExp" id='tglBillExp' />
                            <form:hidden path="tglBuku" id='tglBuku' />
                            <form:hidden path="statusBpn" id='statusBpn' />
                            <form:hidden path="npwpSekolah" id='npwpSekolah' />
                            <form:hidden path="namaWpSekolah" id='namaWpSekolah' />
                            <form:hidden path="alamatWpSekolah" id='alamatWpSekolah' />
                            <form:hidden path="kotaWpSekolah" id='kotaWpSekolah' />
                            <form:hidden path="persenPot" id='persenPot' />
                            <form:hidden path="tglBayar" id='tglBayar' />
                            <form:hidden path="idApp" id='idApp' />

                            <form:input path="noBkuMohon" id='noBkuMohon' type="text" readonly="true"  size="16" />
                        </div>
                    </div>
                </div>

                <div class="form-group" id="">
                    <label  class="col-md-4 control-label">Tanggal Pembayaran :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <input type="text" name="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="14" disabled="true"/>
                        </div>
                    </div>
                </div>

                <div id="labeltriwulan" class="form-group">
                    <label class="col-md-4 control-label">Triwulan :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:select path="bku.triwulan" id="triwulan"  disabled="true" >
                                <form:option value="1">Triwulan I</form:option>
                                <form:option value="2">Triwulan II</form:option>
                                <form:option value="3">Triwulan III</form:option>
                                <form:option value="4">Triwulan IV</form:option>
                            </form:select>

                            <form:errors path="bku.triwulan" class="error" />

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Jenis Transaksi :</label>
                    <div class="col-md-7">
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
                                <form:option value="RT">PENDAPATAN LAIN-LAIN</form:option>
                            </form:select>

                            <form:errors path="kodeTransaksi" class="error" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label id="labelnobuktidok" class="col-md-4 control-label">No Bukti Dok :</label>
                    <div class="col-md-5">
                        <div class="input-group" id="nobuktiketerangan">
                            <form:input path="bku.noBukti" id="noBukti" type="text" size="40" readonly="true"/>
                        </div>
                    </div>
                </div>

                <div id="labelkegiatan"  class="form-group">
                    <label id="" class="col-md-4 control-label">Kegiatan :</label>
                    <div class="col-md-5">

                        <form:hidden path="bku.idKegiatan" id='idKegiatan' onchange="" />
                        <form:hidden path="bku.kodeKeg" id='kodeKeg' />
                        <form:input path="bku.ketKegiatan" id="ketKegiatan" type="text"  class="m-wrap large" size="80" readOnly="true"/>

                    </div>
                </div>

                <div id="labelnrk" name="labelnrk" class="form-group">
                    <label id="textNipPptk" class="col-md-4 control-label">NRK PPTK :</label>
                    <div class="col-md-7">
                        <form:input path="bku.nrkPptk" id="nrkPptk" type="text"  class="required " readonly="true" maxlength="6" onkeypress="return isNumber(event)"/>
                    </div>
                </div>

                <div id="labelnippptk" name="labelnippptk" class="form-group">
                    <label id="textNipPptk" class="col-md-4 control-label">NIP Bendahara :</label>
                    <div class="col-md-7">
                        <form:input path="bku.nipPptk" id="nipPptk" type="text"  class="required " readonly="true" maxlength="18" onkeypress="return isNumber(event)"/>
                    </div>
                </div>

                <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                    <label id="textNamaPptk" class="col-md-4 control-label">Nama Bendahara :</label>
                    <div class="col-md-7">
                        <form:input path="bku.namaPptk" id="namaPptk" type="text"  class="required " readonly="true" size="50" maxlength="50"/>
                    </div>
                </div>

                <div id="labelsaldokas" class="form-group">
                    <label id="" class="col-md-4 control-label">Saldo Kas :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <input name="saldo" id="saldo" type="text" readonly="true" />
                        </div>
                    </div>
                </div>


                <div id="" class="form-group">
                    <label class="col-md-4 control-label">Total Pembayaran :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="nilaiPajak" id="nilaiPajak" type="text"  class="required " readonly="true"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Kode Billing :</label>
                    <div class="col-md-7">
                        <form:input path="kodeBilling" id="kodeBilling" type="text"  readonly='true' />
                    </div>
                </div>

                <div id="labelnpwppenyetor" class="form-group">
                    <label class="col-md-4 control-label">NPWP Penyetor :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="npwpRekanan" id="npwpRekanan" type="text"  class="required " readonly="true" maxlength="30"/>
                        </div>
                    </div>
                </div>

                <div id="labelnamapenyetor" class="form-group">
                    <label id="textnamarekan" class="col-md-4 control-label">Nama WP Penyetor :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="namaWp" id="namaWp" type="text"  size="50" class="required " readonly="true" />
                        </div>
                    </div>
                </div>

                <div id="labelalamat" class="form-group">
                    <label id="textnamarekan" class="col-md-4 control-label">Alamat WP Penyetor :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="alamatWp" id="alamatWp" type="text"  size="50" class="required " readonly="true" />
                        </div>
                    </div>
                </div>

                <div id="labelkota" class="form-group">
                    <label id="textnamarekan" class="col-md-4 control-label">Kota WP Penyetor :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="kotaWp" id="kotaWp" type="text"  size="50" class="required " readonly="true" />
                        </div>
                    </div>
                </div>

                <div id="labelmasapajak"  class="form-group">
                    <label class="col-md-4 control-label">Masa Pajak :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="masaPajak" id='masaPajak' />

                            <select name="masapajak1" id="masapajak1" onchange="setMasaPajak2()" disabled="true" >
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
                            <select name="masapajak2" id="masapajak2" disabled="true" >
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

                <div id="labeltahunpajak" class="form-group">
                    <label class="col-md-4 control-label">Tahun Pajak :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="tahunPajak" id="tahunPajak" type="text"  class="required " size="6" maxlength="4" readonly="true" onkeypress="return isNumber(event)"/>
                        </div>
                    </div>
                </div>

                <div id="labeluraian" name="labeluraian" class="form-group">
                    <label class="col-md-4 control-label">Uraian :</label>
                    <div class="col-md-7">
                        <form:textarea path="uraian" id="uraian" class="required " cols="80" readonly="true"  ROWS="3" maxlength="400" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Nomor Transaksi Bank :</label>
                    <div class="col-md-7">
                        <form:input path="ntb" id="ntb" type="text"  readonly='true' style="font-weight: bold;"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Nomor Transaksi Penerimaan Negara :</label>
                    <div class="col-md-7">
                        <form:input path="ntpn" id="ntpn" type="text"  readonly='true' style="font-weight: bold;"/>
                    </div>
                </div>

            </div>

            <div class="col-md-4 col-md-offset-1" id="panelhasilinquiry">
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>


                <div id="databank" class="portlet box tosca">
                    <div class="portlet-title ">

                        <div class="caption" style="font-size: 15px">Data Pajak</div>

                    </div>
                    <div class="portlet-body flip-scroll">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4 control-label">Kode Billing :</label>
                                <div class="col-md-5">
                                    <input name="djpkodebill" id="djpkodebill" type="text" readonly="true" style="font-weight: bold;"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Nilai Pajak :</label>
                                <div class="col-md-5">
                                    <input name="djpnilai" id="djpnilai" type="text" readonly="true" style="font-weight: bold;"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">NPWP Penyetor :</label>
                                <div class="col-md-5">
                                    <input name="djpnpwp" id="djpnpwp" type="text" readonly="true" size="31" style="font-weight: bold;"/>

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama WP :</label>
                                <div class="col-md-5">
                                    <input name="djpnamawp" id="djpnamawp" type="text" readonly="true" size="31" style="font-weight: bold;"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Alamat WP :</label>
                                <div class="col-md-5">
                                    <TEXTAREA name="djpalamat" id="djpalamat" cols="30" ROWS="3" readonly="true" style="font-weight: bold;"></TEXTAREA>
                                    <!-- <input name="dkinama" id="dkinama" type="text" readonly="true" size="31" /> -->
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="portlet box ">

            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9" align="">
                    <button type="button" id="btnSimpan" class="btn blue" onclick='cekbayar()'>Bayar Pajak BKU </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bkutf/indexbkubos" >Kembali</a>
                </div>
            </div>

        </div>
    </div>

    <div id="mygrid" class="portlet box">

        <div id="tabelBkus" class="portlet-body">

            <table id="bkustable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Akun</th>
                        <th>Nama Akun</th>
                        <th>Komponen</th>
                        <th>Nilai</th>

                    </tr>
                </thead>

                <tbody id="bkustablebody" >
                    <tr>

                    </tr>
                </tbody>

            </table>
        </div>
    </div>


</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/transferpajak/transferbos.js"></script>


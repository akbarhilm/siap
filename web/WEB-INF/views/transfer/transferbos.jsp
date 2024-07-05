<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

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
    <li><a href="#">Pembayaran BKU - BOS</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pembayaran Buku Kas Umum - BOS</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="col-md-7">


                <div class="form-group">
                    <label class="col-md-3 control-label">Tahun Anggaran:</label>
                    <div class="col-md-4">
                        <input type="hidden" id="isadd" name="isadd"  />
                        <input type="hidden" id="triger" onchange="trigerPajakSpj()"  />
                        <input type="hidden" id="validatetime" name="validatetime"  />
                        <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Sekolah :</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <input type="hidden" id="norekpengirim" name="norekpengirim"  />
                            <input type="hidden" id="namapengirim" name="namapengirim"  />
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
                    <label class="col-md-3 control-label">No BKU Mohon :</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <form:hidden path="idBku" id='idBku' />
                            <form:hidden path="kodeBatalTf" id='kodeBatalTf' />
                            <form:hidden path="kodeSumbdana" id='kodeSumbdana' />
                            <form:hidden path="tglDok" id="tglDok"  class="required  date-picker2 entrytanggal2 valid" size="14" value="" />

                            <form:hidden path="inboxFile" id='inboxFile' />
                            <form:input path="noBkuMohon" id='noBkuMohon' type="text" readonly="true"  size="16" />
                        </div>
                    </div>
                </div>

                <div class="form-group" id="">
                    <label  class="col-md-3 control-label">Tanggal Pembayaran :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" name="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="14" disabled="true"/>

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
                                <form:option value="RT">PENDAPATAN LAIN-LAIN</form:option>
                            </form:select>

                            <form:errors path="kodeTransaksi" class="error" />
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label id="labelnobuktidok" class="col-md-3 control-label">No Bukti Dok :</label>
                    <div class="col-md-9">
                        <div class="input-group" id="nobuktiketerangan">
                            <form:input path="noBukti" id="noBukti" type="text" size="40" readonly="true"/>
                        </div>
                    </div>
                </div>

                <div id="labelkegiatan"  class="form-group">
                    <label id="textNipPptk" class="col-md-3 control-label">Kegiatan :</label>
                    <div class="col-md-9">

                        <form:hidden path="idKegiatan" id='idKegiatan' onchange="getKegiatan()" />
                        <form:hidden path="kodeKeg" id='kodeKeg' />
                        <form:input path="ketKegiatan" id="ketKegiatan" type="text"  class="m-wrap large" size="80" readOnly="true"/>

                    </div>
                </div>
                <div id="labeltalangan" class="form-group">
                    <label class="col-md-3 control-label">Dana Talangan :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:hidden path="kodeTalangan" id='kodeTalangan' />
                            <input type="checkbox" id="cbTalangan" id="cbTalangan" disabled="true"/>
                        </div>
                    </div>
                </div>
                <div id="labelbulan" name="labelbulan"class="form-group">
                    <label class="col-md-3 control-label">Bulan Tagihan :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="bulanTagihan" id="bulanTagihan" type="text" size="40" readonly="true"/>
                            <form:hidden path="kodeBulan" id='kodeBulan' />
                        </div>
                    </div>
                </div>
                <div id="labelmcb" name="labelmcb" class="form-group">
                    <label id="labelnilaibkutext" class="col-md-3 control-label">MCB :</label>
                    <div class="col-md-4">
                        <form:input path="mcb" id="mcb" type="text" size="40" readonly="true"/>
                        <form:hidden path="idMcb" id='idMcb' />
                    </div>
                </div>
                <div id="labelnrk" name="labelnrk" class="form-group">
                    <label id="textNipPptk" class="col-md-3 control-label">NRK PPTK :</label>
                    <div class="col-md-4">
                        <form:input path="nrkPptk" id="nrkPptk" type="text"  class="required " readonly="true" maxlength="6" onkeypress="return isNumber(event)"/>
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

                <div id="labelsaldokas" class="form-group">
                    <label id="" class="col-md-3 control-label">Saldo Kas :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input name="saldo" id="saldo" type="text" readonly="true" />
                        </div>
                    </div>
                </div>


                <div id="" class="form-group">
                    <label class="col-md-3 control-label">Total Pembayaran :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="nilaiBku" id="nilaiBku" type="text"  class="required " readonly="true"/>
                        </div>
                    </div>
                </div>

                <div id="labelnamabank" class="form-group">
                    <label class="col-md-3 control-label">Nama Bank Tujuan :</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:hidden path="kodeBank" id='kodeBank' />
                            <form:hidden path="kodeBankTf" id='kodeBankTf' onchange="cekDataBankDki()" />
                            <form:input path="namaBank" id="namaBank" type="text"  class="required " size="50" readonly="true"/> &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihBank" onclick="" href="${pageContext.request.contextPath}/bank/listbank?target='_top'" title="Pilih Bank"  ><i class="icon-zoom-in"></i> Pilih</a>
                        </div>
                    </div>
                </div>

                <div id="labelrekbank" class="form-group">
                    <label class="col-md-3 control-label">No Rekening Bank Tujuan:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="norekBank" id="norekBank" type="text" size="50" class="required " maxlength="30" onkeypress="return isNumber(event)" onchange="cekDataBankDki()"/>
                        </div>
                    </div>
                </div>

                <div id="labelnamarekanan" class="form-group">
                    <label class="col-md-3 control-label">Nama Rekanan (Tujuan) :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="namaRekan" id="namaRekan" type="text"  class="required " size="50" maxlength="120"/>
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

                <div id="labelnpwp" class="form-group">
                    <label class="col-md-3 control-label">NPWP Penyetor :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="npwp" id="npwp" type="text"  class="required " readonly="true" maxlength="30"/>
                        </div>
                    </div>
                </div>

                <div id="labelnamapenyetor" class="form-group">
                    <label id="textnamarekan" class="col-md-3 control-label">Nama Penyetor :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input name="namapenyetor" id="namapenyetor" type="text" readonly="true" size="50" />
                        </div>
                    </div>
                </div>

                <div id="labelalamat" class="form-group">
                    <label id="textnamarekan" class="col-md-3 control-label">Alamat Penyetor :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input name="alamatpenyetor" id="alamatpenyetor" type="text" size="50" readonly="true" />
                        </div>
                    </div>
                </div>

                <div id="labelkota" class="form-group">
                    <label id="textnamarekan" class="col-md-3 control-label">Kota Penyetor :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input name="kotapenyetor" id="kotapenyetor" type="text" size="50" readonly="true" />
                        </div>
                    </div>
                </div>

                <div id="labelmasapajak"  class="form-group">
                    <label class="col-md-3 control-label">Masa Pajak :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="masaPajak" id='masaPajak' />

                            <select name="masapajak1" id="masapajak1" onchange="setMasaPajak2()" >
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
                    <label class="col-md-3 control-label">Tahun Pajak :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="tahunPajak" id="tahunPajak" type="text"  class="required " size="6" maxlength="4" onkeypress="return isNumber(event)"/>
                        </div>
                    </div>
                </div>

                <div id="labeluraian" name="labeluraian" class="form-group">
                    <label class="col-md-3 control-label">Uraian :</label>
                    <div class="col-md-4">
                        <form:textarea path="uraian" id="uraian" class="required " cols="80" readonly="true"  ROWS="3" maxlength="400" />
                    </div>
                </div>
                <div id="labeltoken" name="labeltoken" class="form-group">
                    <label class="col-md-3 control-label">Token :</label>
                    <div class="col-md-4">
                        <input name="token" id="token" type="text" readonly="true" />
                    </div>
                </div>

            </div>

            <div class="col-md-4 col-md-offset-1">
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>

                <div id="databank" class="portlet box tosca">
                    <div class="portlet-title ">

                        <div class="caption" style="font-size: 15px">Data Bank DKI</div>

                    </div>
                    <div class="portlet-body flip-scroll">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4 control-label">Kode Bank :</label>
                                <div class="col-md-5">
                                    <input name="dkikodebank" id="dkikodebank" type="text" readonly="true" />
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama Bank :</label>
                                <div class="col-md-5">
                                    <input name="dkinamabank" id="dkinamabank" type="text" readonly="true" size="31" />

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">No Rekening :</label>
                                <div class="col-md-5">
                                    <input name="dkinorek" id="dkinorek" type="text" readonly="true" size="31" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama :</label>
                                <div class="col-md-5">
                                    <TEXTAREA name="dkinama" id="dkinama" cols="30" ROWS="3" readonly="true"></TEXTAREA>
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
                    <button type="button" id="btnSimpan" class="btn blue" onclick='validateTime()'>Bayar BKU </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bkutf/indexbkubos" >Kembali</a>

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
                        <th>Akun</th>
                        <th>Nama Akun</th>
                        <th>Komponen</th>
                        <th>Nilai SPJ</th>
                        <!--<th>Pajak</th>-->
                        <th>Nilai Pajak</th>
                        <th>Nilai Transfer</th>
                    </tr>
                </thead>

                <tbody id="spjtablebody" >
                    <tr>

                    </tr>
                </tbody>

                <tfoot>
                    <tr>
                        <th colspan="4" style="text-align:right">Total:</th>

                        <th colspan="1" >
                            <input type='text' id="sumspj"   name="sumspj" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>

                        <th colspan="1" >
                            <input type='text' id="sumpajak"   name="sumpajak" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>

                        <th colspan="1" >
                            <input type='text' id="sumtf"   name="sumtf" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                    </tr>
                </tfoot>

            </table>
        </div>

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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/transfer/transferbos.js"></script>


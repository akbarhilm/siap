<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

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
    <li><a href="#">Konfirmasi Pembayaran BKU (Virtual Account) - BOP</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Konfirmasi Pembayaran BKU (Virtual Account) - BOP</div>

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
                        <form:input path="noBkuMohon" id='noBkuMohon' type="text" readonly="true"  size="16" />
                    </div>
                </div>
            </div>

            <div class="form-group" id="">
                <label  class="col-md-3 control-label">Tanggal Pembayaran VA :</label>
                <div class="col-md-4">
                    <div class="input-group"> <!-- nanti pake  date-picker aja biar <= sysdate -->
                        <input type="text" name="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="14" />

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
                        <form:input path="norekBank" id="norekBank" type="text"  class="required " maxlength="30" size="50" onkeypress="return isNumber(event)" onchange="cekDataBankDki()"/>
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


            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <form:textarea path="uraian" id="uraian" class="required " cols="80" readonly="true"  ROWS="3" maxlength="400" />
                </div>
            </div>
            <!--            <div id="labeltoken" name="labeltoken" class="form-group">
                            <label class="col-md-3 control-label">Token :</label>
                            <div class="col-md-4">
                                <select name="cbtoken" id="cbtoken"  >
                                    <option value="0">--Pilih Token--</option>
                                </select>
                            </div>
                        </div>-->
            <div class="portlet box ">

            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9" align="">
                    <button type="button" id="btnSimpan" class="btn blue" onclick='cekbayar()'>Simpan </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bkutf/indexbkubop" >Kembali</a>

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
                        <th>Nilai</th>

                    </tr>
                </thead>

                <tbody id="spjtablebody" >
                    <tr>

                    </tr>
                </tbody>


            </table>
        </div>

    </div>


</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/transfer/transfervabop.js"></script>


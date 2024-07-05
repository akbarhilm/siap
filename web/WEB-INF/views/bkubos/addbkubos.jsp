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
        <a href="${pageContext.request.contextPath}/bkubos/indexbkubos" >Buku Kas Umum Sekolah - BOS </a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Input Buku Kas Umum Sekolah - BOS</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Buku Kas Umum Sekolah - BOS</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="noBkuMohon" name="noBkuMohon" value="-99" />
                    <input type="hidden" id="totalspjhidden" name="totalspjhidden"  />
                    <input type="hidden" id="idbastemp" name="idbastemp"  />
                    <input type="hidden" id="indextemp" name="indextemp"  />


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

            <div id="labeltriwulan" class="form-group">
                <label class="col-md-3 control-label">Triwulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="triwulan" id="triwulan" onchange="setSpj(), cekTW()" >
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
                        <select path="kodeTransaksi" id="kodeTransaksi" onchange="setPanel()">
                            <option value="-" selected> --------------------- PILIH ---------------------</option>
                            <option value="JO">PENGISIAN KAS</option>
                            <option value="JJ">SPJ (PENCATATAN BELANJA)</option>
                            <option value="C1">C1</option>
                            <option value="C2">C2</option>
                            <option value="P1">PPH PS 21</option>
                            <option value="P2">PPH PS 22</option>
                            <option value="P3">PPH PS 23 JASA I</option>
                            <option value="P4">PPH PS 4 AYAT 2</option>
                            <option value="P5">PPN</option>
                            <option value="ST">SETOR SISA KAS </option>
                            <option value="JG">JASA GIRO</option>
                            <option value="RT">PENDAPATAN LAIN-LAIN</option>

                        </select>
                    </div>
                </div>
            </div>
            <div id="labelcbsaldo" name="labelcbsaldo" class="form-group">
                <label id="textlabeltalangan" class="col-md-3 control-label">Saldo Awal :</label>
                <div class="col-md-4">
                    <input type="hidden" id="kodeSaldoAwal" name="kodeSaldoAwal" value="0" >
                    <input type="checkbox" id="cbSaldoAwal" id="cbSaldoAwal" onclick="clickCheckbox()"/>
                </div>
            </div>
            <div id="labeljenisbayar" name="labeljenisbayar" class="form-group">
                <label class="col-md-3 control-label">Jenis Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="jenisPembayaran" id="jenisPembayaran" onchange="setJenisBayar();
                                setOptPnPg()" >
                            <option value="-">Pilih</option>
                            <option value="PN">Penerimaan</option>
                            <option value="PG">Pengeluaran</option>
                        </select>
                    </div>
                </div>
            </div>

            <div id="labelbayarpajak" name="labelbayarpajak" class="form-group">
                <label class="col-md-3 control-label"></label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="jenisBayarPajak" id="jenisBayarPajak" onchange="setKegiatanPajak()" >
                            <option value="0" selected>Pajak SPJ (Belanja yang belum dibayar)</option>
                            <option value="1">Pengembalian Pajak Dari Rekanan (Belanja yang sudah dibayar)</option>
                        </select>
                    </div>
                </div>
            </div>

            <div id="labelkegiatanpajak" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKegPj"  id="keteranganKegPj"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegPj" onclick="setKegiatanPajak()" href="#" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a>
                    <input type="hidden" id="idKegiatanPj" name="idKegiatanPj"  value="">
                    <input type="hidden" id="kodeKegPj" name="kodeKegPj"  value="">
                </div>
            </div>

            <div class="form-group" id="labelrumuspajakp1">
                <label class="col-md-3 control-label">Persentase :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="rumuspajakp1" onchange="setPersenP1(this.value)">
                            <option value="-">Pilih</option>
                            <option value="1">NPWP</option>
                            <option value="2">NON NPWP</option>
                        </select>

                        <select  id="rumuspersenp1" onchange="setPnP1()">

                        </select>

                    </div>
                </div>
            </div>

            <div class="form-group" id="labelrumuspajakp2">
                <label class="col-md-3 control-label">Persentase :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="rumuspajakp2" onchange="setPnP2(this.value)">
                            <option value="-">Pilih</option>
                            <option value="0">TIDAK ADA PPN</option>
                            <option value="1">ADA PPN</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group" id="labelrumuspajakp3">
                <label class="col-md-3 control-label">Persentase :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="rumuspajakp3" onchange="setPnP3()">
                            <option value="-">Pilih</option>
                            <option value="0">TIDAK ADA PPN</option>
                            <option value="1">ADA PPN</option>
                        </select>

                        <select  id="rumuspersenp3" onchange="setPnP3()">
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
                        <select  id="rumuspajakp5" onchange="setPnP5(this.value)">
                            <option value="-">Pilih</option>
                            <option value="1">1%</option>
                            <option value="10">10%</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label id="labelnobuktidok" class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <input name="noBuktiDok" id="noBuktiDok" type="text" maxlength="50" size="32"/>

                    </div>
                </div>
            </div>

            <div class="form-group" id="labeltgldok">
                <label class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tglDok" id="tglDok" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                        <input type="hidden" id="idKegpop" name="idKegpop"  onchange="getKegiatan()" value="">
                        <input type="hidden" id="kodeKegpop" name="kodeKegpop"  onchange="" value="">
                        <input type="hidden" id="namaKegpop" name="namaKegpop"  onchange="" value="">
                        <input type="hidden" id="keteranganKegPop" name="keteranganKegPop"  onchange="" value="">
                        <input type="hidden" id="nobkuref" name="nobkuref"  >
                        <input type="hidden" id="idrinci" name="idrinci"  >
                        <input type="hidden" id="idbku" name="idbku"  >
                        <input type="hidden" id="ketmasapajak" name="ketmasapajak"  >
                        <input type="hidden" id="idparam" name="idparam"  >
                        <input type="hidden" id="cnpwp" name="cnpwp"  >
                        <input type="hidden" id="cpkp" name="cpkp"  >
                        <input type="hidden" id="cpns" name="cpns"  >
                        <input type="hidden" id="csk" name="csk"  >
                        <input type="hidden" id="cbelanja" name="cbelanja"  >
                        <input type="hidden" id="cpegawai" name="cpegawai"  >
                        <input type="hidden" id="cperiode" name="cperiode"  >
                        <input type="hidden" id="cptkp" name="cptkp"  >
                        <input type="hidden" id="p2" name="p2"  >
                        <input type="hidden" id="p3" name="p3"  >
                    </div>
                </div>
            </div>

            <div class="form-group" id="labeltgldokjo">
                <label  class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group"> <!-- UNTUK VALIDASI KALENDER <= SYSDATE, GUNAKAN CLASS date-picker -->
                        <input type="text" name="tglDokJo" id="tglDokJo" class="required date-picker2 entrytanggal valid" size="14" value=""/>

                    </div>
                </div>
            </div>

            <div id="labelfileinbox" name="labelfileinbox" class="form-group">
                <label class="col-md-3 control-label">No Arsip :</label>
                <div class="col-md-4">
                    <input name="fileInbox" id="fileInbox" type="text" maxlength="10"/>
                </div>
            </div>

            <div id="labelcarabayar" name="labelcarabayar" class="form-group">
                <label class="col-md-3 control-label">Cara Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="kodePembayaran" id="kodePembayaran" onchange="" >
                            <option value="2" selected>2 - Bank/Transfer/Cek</option>
                        </select>
                    </div>
                </div>
            </div>

            <div id="labelkegiatanspj" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKegSpj"  id="keteranganKegSpj"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegSpj" onclick="cekTW()" href="#" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a>
                    <input type="hidden" id="idKegiatanSpj" name="idKegiatanSpj"  value="">
                    <input type="hidden" id="kodeKegSpj" name="kodeKegSpj"  value="">
                </div>
            </div>

            <div id="labelkegiatansts" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKegSts"  id="keteranganKegSts"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegPj" onclick="" href="${pageContext.request.contextPath}/bku/listkegpajakpn?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a>
                    <input type="hidden" id="idKegiatanSts" name="idKegiatanSts"  value="">
                    <input type="hidden" id="kodeKegSts" name="kodeKegSts"  value="">
                </div>
            </div>
            <div id="labeltalangan" name="labeltalangan" class="form-group">
                <label id="textlabeltalangan" class="col-md-3 control-label">Dana Talangan :</label>
                <div class="col-md-4">
                    <input type="hidden" id="kodeTalangan" name="kodeTalangan" value="0" >
                    <input type="checkbox" id="cbTalangan" id="cbTalangan" onchange="setTalangan()"/>
                </div>
            </div>
            <div id="labelbulan" name="labelbulan"class="form-group">
                <label class="col-md-3 control-label">Bulan Tagihan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="bulan" id="bulan">
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
                    <select name="cbmcb" id="cbmcb" onchange="" >
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

            <div id="labelnilaibku" name="labelnilaibku" class="form-group">
                <label id="labelnilaibkutext" class="col-md-3 control-label">Nilai Penerimaan Kas :</label>
                <div class="col-md-4">
                    <input name="nilaibku" id="nilaibku" type="text" onchange="setformatpengeluaran(this.value)"  onkeypress="return isNumber(event)" />
                </div>
            </div>

            <div id="labelnilaijg" name="labelnilaijg" class="form-group">
                <label id="labelpengeluarantext" class="col-md-3 control-label">Nilai Jasa Giro :</label>
                <div class="col-md-4">
                    <input name="nilaijg" id="nilaijg" type="text" onchange="setformatpengeluaran(this.value)"  onkeypress="return isNumber(event)" /> &nbsp <a  class="fancybox fancybox.iframe btn green" id="pilihjg" onclick="setJgSt()" href="${pageContext.request.contextPath}/bkubos/listsetoran?target='_top'" title="Pilih Data"  ><i class="icon-zoom-in"></i> Pilih</a>
                </div>
            </div>

            <div id="labelnrk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NRK Bendahara :</label>
                <div class="col-md-4">
                    <input name="nrkPptk" id="nrkPptk" type="text" maxlength="6"  onkeypress="return isNumber(event)" readonly="true" value="${pengguna.namaPengguna}"/>
                    <!--<input type="button" id="btnCek" class="btn blue" onclick="getData()" value="Cek NRK">-->
                </div>
            </div>


            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NIP Bendahara :</label>
                <div class="col-md-4">
                    <input name="nipPptk" id="nipPptk" type="text" maxlength="18"  onkeypress="return isNumber(event)" readonly="true"/>
                </div>
            </div>

            <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                <label id="textNamaPptk" class="col-md-3 control-label">Nama Bendahara :</label>
                <div class="col-md-4">
                    <input name="namaPptk" id="namaPptk" type="text" size="50" maxlength="50" readonly="true"/>
                </div>
            </div>

            <div id="labelnamabank" class="form-group">
                <label class="col-md-3 control-label">Nama Bank :</label>
                <div class="col-md-7">
                    <div class="input-group">
                        <input type="hidden" id="kodeBank" name="kodeBank"  >
                        <input type="hidden" id="kodeBankTf" name="kodeBankTf"  >
                        <input name="namaBank" id="namaBank" type="text" size="65" readonly="true"/> &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihBank" onclick="" href="${pageContext.request.contextPath}/bank/listbank?target='_top'" title="Pilih Bank"  ><i class="icon-zoom-in"></i> Pilih</a>

                    </div>
                </div>
            </div>

            <div id="labelrekbank" class="form-group">
                <label class="col-md-3 control-label">No Rekening Bank :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input name="rekeningBank" id="rekeningBank" type="text" size="50" maxlength="30" onkeypress="return isNumber(event)" onchange="" />
                    </div>
                </div>
            </div>

            <div id="labelnamarekanan" class="form-group">
                <label id="textnamarekan" class="col-md-3 control-label">Nama Rekanan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input name="namarekanan" id="namarekanan" type="text" size="50" maxlength="50" onkeypress="return isNamaRekanan(event)" />
                    </div>
                </div>
            </div>
            <div id="labelbelanja" class="form-group">
                <label class="col-md-3 control-label">Jenis Belanja :</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <select path="belanja" id="belanja" onchange="pilihBelanja()">
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
                        <select path="pns" id="pns" onchange="pilihGolongan()">
                            <option value="-">---- Pilih ----</option>
                            <option value="1">PNS</option>
                            <option value="0">Bukan PNS</option>
                        </select>
                        &nbsp;
                        <select path="golongan" id="golongan">
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
                <label class="col-md-3 control-label">Jenis Pajak Belanja :</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <select path="pegawai" id="pegawai" onchange="pilihPegawai()">
                            <option value="-">---- Pilih ----</option>
                            <option value="1">Pegawai Tidak Tetap/Tenaga Kerja Lepas/Honorer</option>
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
                        <select path="periode" id="periode">
                            <option value="-">---- Pilih ----</option>
                            <option value="1">Harian</option>
                            <option value="2">Mingguan</option>
                            <option value="3">Bulanan</option>
                            <option value="4">Triwulanan</option>
                        </select>
                    </div>
                </div>
            </div>
            <div id="labelnpwp" name="labelnamapptk" class="form-group">
                <label id="textnpwp" class="col-md-3 control-label"><i class="icon-info-sign" title="Wajib Diisi Jika Rekanan Memiliki NPWP dan Dipotong Pajak."></i> NPWP Penyetor :</label>
                <div class="col-md-4">
                    <input name="npwp" onchange="getDataNPWP()" id="npwp" type="text" maxlength="15" onkeypress="return isNumber(event)"/>
                </div>
            </div>
            <div id="labelnamanpwp" name="labelnamanpwp" class="form-group">
                <label id="textnamanpwp" class="col-md-3 control-label">Nama NPWP Penyetor :</label>
                <div class="col-md-4">
                    <input name="namanpwp" id="namanpwp" type="text" size="50" maxlength="100" readonly="true" />
                </div>
            </div>
            <div id="labelalamatnpwp" name="labelalamatnpwp" class="form-group">
                <label id="textalamatnpwp" class="col-md-3 control-label">Alamat NPWP Penyetor :</label>
                <div class="col-md-4">
                    <TEXTAREA name="alamatnpwp" id="alamatnpwp" cols="80" ROWS="3" maxlength="400" readonly="true"></TEXTAREA>
                </div>
            </div>
            <div id="labelkotanpwp" name="labelkotanpwp" class="form-group">
                <label id="textkotanpwp" class="col-md-3 control-label">Kota NPWP Penyetor :</label>
                <div class="col-md-4">
                    <input name="kotanpwp" id="kotanpwp" type="text" size="50" maxlength="100" readonly="true" />
                </div>
            </div>
            <div id="labelpkp" class="form-group">
                <label class="col-md-3 control-label">Status PKP :</label>
                <div class="col-md-9">
                    <input type="checkbox" id="cmbpkp" id="cmbpkp" disabled="true"/>
                </div>
            </div>
                        <div id="labelptkp" class="form-group">
                <label class="col-md-3 control-label">Jenis PTKP :</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <select path="ptkp" id="ptkp">
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
            <div class="form-group" id="labelkjs">
                <label class="col-md-3 control-label">Kode Jenis Setor :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="kodemap" name="kodemap"  >
                        <select  id="kodekjs">

                        </select>
                    </div>
                </div>
            </div>
            <div id="labelsk" class="form-group">
                <label class="col-md-3 control-label">Surat Keterangan Pajak :</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <select path="cmbsk" id="cmbsk" onchange="inputSK()">
                            <option value="-">---- Pilih ----</option>
                            <option value="1">Ada</option>
                            <option value="0">Tidak Ada</option>
                        </select>
                        &nbsp;
                        <input name="kodesk" id="kodesk" type="text" maxlength="20" />
                    </div>
                </div>
            </div>
            <div id="labelmasapajak"  class="form-group">
                <label class="col-md-3 control-label"><i class="icon-info-sign" title="Bulan Saat Anda Membayar Pajak / Transfer Online."></i> &nbsp; Masa Pajak (Bulan Bayar):</label>
                <div class="col-md-4">
                    <div class="input-group">
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

            <div id="labeltahunpajak"  class="form-group">
                <label id="" class="col-md-3 control-label">Tahun Pajak :</label>
                <div class="col-md-4">
                    <input name="tahunpajak" id="tahunpajak" type="text" size="6" maxlength="4" value="${tahunAnggaran}" onkeypress="return isNumber(event)" />
                </div>
            </div>

            <div id="labelva" class="form-group">
                <label class="col-md-3 control-label">Virtual Account :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="checkbox" id="cbVA" id="cbVA" onchange="setVA()" />
                        <input type="hidden" id="kodeVA" name="kodeVA" value="0" >
                    </div>
                </div>
            </div>

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <TEXTAREA name="uraian" id="uraian" cols="80" ROWS="3" maxlength="400"></TEXTAREA>
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
                        <th>Komponen</th>
                        <th>Keterangan Rinci</th>
                        <th>Nilai Anggaran</th>
                        <th>Nilai Sebelum</th>
                        <th>Sisa Anggaran</th>
                        <th>Quantity</th>
                        <th>Nilai SPJ</th>

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
        <div id="divButton" class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" id="btnSimpan" class="btn blue" onclick='simpan()'>Simpan</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/bkubos/indexbkubos" >Kembali</a>

        </div>
    </div>


</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkubos/addbkubos.js"></script>


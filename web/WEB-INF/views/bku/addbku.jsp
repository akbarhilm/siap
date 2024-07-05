<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

    });


</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bku/indexbku" >Buku Kas Umum Sekolah - Pengeluaran </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Entry Buku Kas Umum Sekolah</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Buku Kas Umum</div>   

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="noBKU" name="noBKU" value="-99" />
                    <input type="hidden" id="totalspjhidden" name="totalspjhidden"  />
                    <input type="hidden" id="totalpajakhidden" name="totalpajakhidden"  />
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

            <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="bulan" id="bulan" onchange="">
                            <option value="01" selected>01 - Januari</option> 
                            <option value="02">02 - Februari</option>
                            <option value="03">03 - Maret</option>
                            <option value="04">04 - April</option>
                            <option value="05">05 - Mei</option>
                            <option value="06">06 - Juni</option>
                            <option value="07">07 - Juli</option>
                            <option value="08">08 - Agustus</option>
                            <option value="09">09 - September</option>
                            <option value="10">10 - Oktober</option>
                            <option value="11">11 - November</option>
                            <option value="12">12 - Desember</option>
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
                            <option value="JO">1 - PENGISIAN KAS</option> 
                            <option value="JJ">2 - SPJ  + PAJAK PENERIMAAN</option>
                            <option value="-"> PENGELUARAN PAJAK ------------------</option>
                            <option value="P1">3.1 - PPH PS 21</option>
                            <option value="P2">3.2 - PPH PS 22</option>
                            <option value="P3">3.3 - PPH PS 23 JASA I</option>
                            <option value="P4">3.4 - PPH PS 4 Ayat 2</option>
                            <option value="P5">3.5 - PPN</option>
                            <option value="P6">3.6 - PPH 26</option>
                            <option value="-">SETORAN -----------------------------------</option>
                            <option value="ST">4 - SETOR SISA KAS </option>
                            <option value="-">JASA GIRO ----------------------------------</option>
                            <option value="JG">5 - Jasa Giro</option>

                        </select>
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label id="labeltglpost" class="col-md-3 control-label">Tanggal Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" path="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="14" />
                        <input type="hidden" id="tglHide" class="required date-picker2 entrytanggal " value=""/>
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label id="labelnobuktidok" class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <input name="noBuktiDok" id="noBuktiDok" type="text" />

                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label id="labeltgldok" class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tglDok" id="tglDok" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                        <input type="hidden" id="idKegpop" name="idKegpop"  onchange="getKegiatan()" value="">
                        <input type="hidden" id="kodeKegpop" name="kodeKegpop"  onchange="" value="">
                        <input type="hidden" id="namaKegpop" name="namaKegpop"  onchange="" value="">
                        <input type="hidden" id="keteranganKegPop" name="keteranganKegPop"  onchange="" value="">

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
                            <option value="1">1 - Tunai</option> 
                            <option value="2" selected>2 - Bank/Transfer/Cek</option>  
                        </select>
                    </div>
                </div> 
            </div>

            <div id="labelcbsumberdana" class="form-group">
                <label class="col-md-3 control-label">Sumber Dana :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select id="cbsumberdana" onchange="getSisaKas()" >
                            <option value="1">1 - BOS</option> 
                            <option value="2">2 - BOP</option>  
                        </select>
                    </div>
                </div> 
            </div>

            <div id="labelkegiatanspj" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKegSpj"  id="keteranganKegSpj"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegSpj" onclick="" href="${pageContext.request.contextPath}/common/listkegiatansekolah?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <input type="hidden" id="idKegiatanSpj" name="idKegiatanSpj"  value="">
                    <input type="hidden" id="kodeKegSpj" name="kodeKegSpj"  value="">
                </div>
            </div>

            <div id="labelkegiatanpajak" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKegPj"  id="keteranganKegPj"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegPj" onclick="" href="${pageContext.request.contextPath}/bku/listkegpajakpn?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <input type="hidden" id="idKegiatanPj" name="idKegiatanPj"  value="">
                    <input type="hidden" id="kodeKegPj" name="kodeKegPj"  value="">
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
                    
            <div id="labelsisakas" name="labelsisakas" class="form-group">
                <label class="col-md-3 control-label">Sisa Kas :</label>
                <div class="col-md-4">
                    <input name="sisakas" id="sisakas" type="text" readonly="true" />
                </div>
            </div>

            <div id="labeljenisbayar" name="labeljenisbayar" class="form-group">
                <label class="col-md-3 control-label">Jenis Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="jenisPembayaran" id="jenisPembayaran" onchange="setJasaGiro()" >
                            <option value="PN">Penerimaan</option> 
                            <option value="PG">Pengeluaran</option>
                        </select>
                    </div>
                </div>  
            </div>

            <div id="labelnilaibku" name="labelnilaibku" class="form-group">
                <label id="labelnilaibkutext" class="col-md-3 control-label">Nilai Penerimaan Kas :</label>
                <div class="col-md-4">
                    <input name="nilaibku" id="nilaibku" type="text" onchange="setformatpengeluaran(this.value)"  onkeypress="return isNumber(event)" /> 
                    <input type="hidden" id="nobkuref" name="nobkuref"  >
                </div>
            </div>

            <div id="labelnilaijg" name="labelnilaijg" class="form-group">
                <label id="labelpengeluarantext" class="col-md-3 control-label">Nilai Jasa Giro :</label>
                <div class="col-md-4">
                    <input name="nilaijg" id="nilaijg" type="text" onchange="setformatpengeluaran(this.value)"  onkeypress="return isNumber(event)" /> &nbsp <a  class="fancybox fancybox.iframe btn green" id="pilihjg"  href="${pageContext.request.contextPath}/bku/listpnjasagiro?target='_top'" title="Pilih Jasa Giro Penerimaan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                </div>
            </div>

            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NIP Bendahara :</label>
                <div class="col-md-4">
                    <input name="nipPptk" id="nipPptk" type="text" maxlength="18"  onkeypress="return isNumber(event)" />
                </div>
            </div>

            <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                <label id="textNamaPptk" class="col-md-3 control-label">Nama Bendahara :</label>
                <div class="col-md-4">
                    <input name="namaPptk" id="namaPptk" type="text" size="50" maxlength="50"/>
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
                        <th>Nama Akun</th>
                        <th>Nilai Anggaran</th>
                        <th>Nilai SPJ Sebelum</th> 
                        <th>Sisa Anggaran</th>
                        <th>Nilai SPJ</th>
                        <th>Pajak</th>
                        <th>Nilai Pajak</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody id="spjtablebody" >
                    <tr>

                    </tr>
                </tbody> 
                
                <tfoot>
                    <tr>
                        <th colspan="6" style="text-align:right">Total:</th>
                       
                        <th colspan="1" >
                            <input type='text' id="sumspj"   name="sumspj" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                        <th colspan="1" >
                            
                        </th>
                        <th colspan="1" >
                            <input type='text' id="sumpajak"   name="sumpajak" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
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
                        <th>No BKU Ref</th>
                        <th>Kode Akun</th>
                        <th>Nama Akun</th>
                        <th>Nilai Pajak</th>
                    </tr>
                </thead>

                <tbody id="pajaktablebody" >
                    <tr>

                    </tr>
                </tbody> 
                
            </table>
        </div>
        
    </div>
                
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" id="btnSimpan" class="btn blue" onclick='simpan()'>Simpan</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/bku/indexbku" >Kembali</a>
        </div>
    </div>                    
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bku/addbku.js"></script>  


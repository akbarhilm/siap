<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<script>
    $(document).ready(function() {

        var mm = $('#tglBkuBaformat').val().substr(4, 2);
        var dd = $('#tglBkuBaformat').val().substr(6, 2);
        var yyyy = $('#tglBkuBaformat').val().substr(0, 4);

        var tgl = dd + "/" + mm + "/" + yyyy;
        $('#tglBkuBa').val(tgl);
        
    });
</script>

<style>
    .inputmoneyX{
        border:none;margin:0;text-align:right;width:80%;background: aliceblue;
    }
</style>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU Sekolah</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bapkasbos/indexbapkas">Berita Acara Pemeriksaan (BAP) Kas - BOS</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Berita Acara Pemeriksaan (BAP) Kas - BOS</a></li>
</ul>


<form:form   method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/bapkasbos/prosesdelete" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Berita Acara Pemerikasaan (BAP) Kas - BOS</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                    <form:hidden path="idSekolahBAPKas" id="idSekolahBAPKas" size="11"  /> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Sekolah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="sekolah.idSekolah" id='idsekolah' value="${sekolah.idSekolah}" name="idsekolah" />
                        <form:hidden path="sekolah.npsn" id='npsn' value="${sekolah.npsn}"  />
                        <form:input path="sekolah" type="text" id="sekolah" readonly="true" class="m-wrap large" size="75"  value="${sekolah.npsn} / ${sekolah.namaSekolahPendek}  "  />
                        <form:errors path="sekolah.idSekolah" class="label label-important" />
                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="tglBkuBaformat" id='tglBkuBaformat' />
                        <form:input type="text"  path="tglBkuBa" id="tglBkuBa"  class="required  date-picker2 entrytanggal2 valid" disabled="true"  size="14"  />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Triwulan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="triwulan" id='triwulan' />
                        <input name="tw" id="tw" type="text" size="15" disabled="true" readonly="true"/>

                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">NRK PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nrkPa" id="nrkPa"  size="7" maxlength="6" readonly="true" onKeyPress="return numbersonly(this, event)"  />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nipPa" id="nipPa"  size="20" maxlength="18" readonly="true" onKeyPress="return numbersonly(this, event)" />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="namaPa" id="namaPa"  size="67" maxlength="50" readonly="true"  />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="jabatanPa" id="jabatanPa"  size="67" maxlength="50" readonly="true"  />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nrkBend" id="nrkBend"  size="7" maxlength="6" readonly="true" onKeyPress="return numbersonly(this, event)" />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nipBend" id="nipBend"  size="20" maxlength="18" readonly="true" onKeyPress="return numbersonly(this, event)" />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="namaBend" id="namaBend"  size="67" maxlength="50" readonly="true"  />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="jabatanBend" id="jabatanBend"  size="67" maxlength="50" readonly="true"  />  
                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">No SK Gubernur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="noSkGub" id="noSkGub"  size="50" maxlength="50" readonly="true" />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal SK Gubernur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="tglSkGub" id="tglSkGub"  size="20" maxlength="18" readonly="true"  />
                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Uang Kertas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangKertas"  id="nilaiUangKertas1" readonly="true" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" onchange="setformatKertas(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />&nbsp;&nbsp;&nbsp;(1)
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Uang Logam:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangLogam"  id="nilaiUangLogam1" readonly="true" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" onchange="setformatLogam(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />&nbsp;&nbsp;&nbsp;(2)  

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai SP2D:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSp2d"  id="nilaiUangSp2d1" readonly="true" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" onchange="setformatSp2d(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />&nbsp;&nbsp;&nbsp;(3)  

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Saldo Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSaldoBank"  id="nilaiUangSaldoBank1" readonly="true" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()"  onchange="setformatSaldoBank(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />&nbsp;&nbsp;&nbsp;(4)  

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Surat Berharga:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSuratBerharga"  id="nilaiUangSuratBerharga1" readonly="true" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" onchange="setformatSuratBerharga(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />&nbsp;&nbsp;&nbsp;(5) 

                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Total BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangTotalBkuBa"   id="nilaiUangTotalBkuBa1" size="19" readonly="true"  style="text-align:right" />(6=1+2+3+4+5)

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Saldo BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSaldoBkuBa"  id="nilaiUangSaldoBkuBa" readonly="true" onkeyup="getNilaiTotalSelisihBAPKas(),cektotalbk()" onchange="setformatSaldoBkuBa(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Selisih BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSelisihBkuBa"  id="nilaiUangSelisihBkuBa" readonly="true" onchange="setformatSaldoBkuBa(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right" />  
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Penjelasan Perbedaan positif/negatif:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:textarea  cols="70" rows="3"   path="ketBkuBa" id="ketBkuBa"  readonly="true" />
                    </div>
                </div>
            </div>        

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="${pageContext.request.contextPath}/bapkasbos/indexbapkas">Kembali</a>
                </div>
            </div>

        </div>
    </div>
    
    <div class="portlet box tosca">
        <form id="formasettetap">
            <div class="portlet-title">
                <div class="caption"><i class="icon-cogs"></i>Rincian Berita Acara Pemerikasaan (BAP) Kas</div>       
            </div>
            <div class="portlet-body">

                <input type="hidden" id="banyakrinci" name="banyakrinci"  value="banyakrinci"/> 
                <input type="hidden" id="idrowbaru" name="idrowbaru"  value="idrowbaru"/> 

                <table id="bapkasrincitable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th> 
                            <th>Nama Berita Acara</th>
                            <th>Nilai Berita Acara</th> 
                            <th>Pilih</th>

                        </tr>
                    </thead>

                    <tbody id="bapkasrincitablebody" >
                    </tbody> 

                    <tfoot id="bapkasrincitablefoot" >
                        <tr>
                            <th colspan="2" style="text-align:right">Total:</th>
                            <th colspan="1">
                                <input  type='text' id="totalbk"  name="totalbk" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;' disabled   />
                            </th>
                        </tr>
                    </tfoot>

                    <tfoot id="bapkasrincitablefoot2" >
                    </tfoot>

                </table>
            </div>
        </form>
    </div> 


</form:form>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bapkasbos/editbapkasbos.js"></script>  

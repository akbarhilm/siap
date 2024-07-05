<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkuspjpajak/listpajak.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {

        ketbaris = $('#indextemp', window.parent.document).val(); //window.localStorage.getItem("ketBaris");
        ketidbas = $('#idbastemp', window.parent.document).val(); //window.localStorage.getItem("ketIdBas");

        /*$("#sumpajak").val(window.localStorage.getItem("ketSumPajak"));
         $("#ketnilaip1").val(window.localStorage.getItem("ketNilaiP1"));
         $("#ketnilaip2").val(window.localStorage.getItem("ketNilaiP2"));
         $("#ketnilaip3").val(window.localStorage.getItem("ketNilaiP3"));
         $("#ketnilaip4").val(window.localStorage.getItem("ketNilaiP4"));
         $("#ketnilaip5").val(window.localStorage.getItem("ketNilaiP5"));
         $("#ketnilaip6").val(window.localStorage.getItem("ketNilaiP6"));*/

        $("#sumpajak").val(accounting.unformat($('#totalpajak' + ketbaris, window.parent.document).val(), ","));
        $("#ketnilaip1").val($('#nilaip1' + ketbaris, window.parent.document).val());
        $("#ketnilaip2").val($('#nilaip2' + ketbaris, window.parent.document).val());
        $("#ketnilaip3").val($('#nilaip3' + ketbaris, window.parent.document).val());
        $("#ketnilaip4").val($('#nilaip4' + ketbaris, window.parent.document).val());
        $("#ketnilaip5").val($('#nilaip5' + ketbaris, window.parent.document).val());
        $("#ketnilaip6").val($('#nilaip6' + ketbaris, window.parent.document).val());
        nilaispj = accounting.unformat($('#nilaiinput' + ketbaris, window.parent.document).val(), ",");
        $("#nilaispjakun").val($('#nilaiinput' + ketbaris, window.parent.document).val());
        tampil();
    });

    function tampil() {
        grid();
    }

    function ambilskpd() {
        $('#nilaip1' + ketbaris, window.parent.document).val(parseFloat(accounting.unformat($("#nilaiinput1").val(), ","))).change();
        $('#nilaip2' + ketbaris, window.parent.document).val(parseFloat(accounting.unformat($("#nilaiinput2").val(), ","))).change();
        $('#nilaip3' + ketbaris, window.parent.document).val(parseFloat(accounting.unformat($("#nilaiinput3").val(), ","))).change();
        $('#nilaip4' + ketbaris, window.parent.document).val(parseFloat(accounting.unformat($("#nilaiinput4").val(), ","))).change();
        $('#nilaip5' + ketbaris, window.parent.document).val(parseFloat(accounting.unformat($("#nilaiinput5").val(), ","))).change();
        $('#nilaip6' + ketbaris, window.parent.document).val(parseFloat(accounting.unformat($("#nilaiinput6").val(), ","))).change();
        $('#pajakunformat' + ketbaris, window.parent.document).val(parseFloat(accounting.unformat($("#sumpajak").val(), ","))).change();
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
            <div  class="form-group">
                <label class="col-md-4 control-label">Nilai SPJ :</label>
                <div class="col-md-6">
                    <input name="nilaispjakun" id="nilaispjakun" type="text" readonly="true"/>
                        
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
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />
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
            <!-- <button type="button" id="" class="btn blue" onclick='cekcek()' >Cek</button>  -->

            <button type="button" id="btnTambah" class="btn blue" onclick='ceknilai()' >Simpan</button>

        </div>
    </div>  
</form:form>